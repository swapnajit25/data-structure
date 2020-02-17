package com.sm.ds.pattern;

public class KMPPatternSearching
{

	public static void main(String[] args)
	{
		String txt = "ABABDABACDABABCABAB"; 
        String pat = "ABABCABAB";
        
        new KMPPatternSearching().searchKMP(pat, txt);
	}
	
	public void searchKMP(final String pat, final String txt)
	{
		final int M = pat.length();
		final int N = txt.length();
		
		// Create lps[] which will hold the longest prefix suffix
		// values for the pattern
		final int[] lps = new int[M];
		int j = 0; // index for pat[]
		
		// Process the pattern and calculate the lps[]
		computeLPSArray(pat, M, lps);
		
		int i = 0; // index for txt[]
		while(i < N)
		{
			if(pat.charAt(j) == txt.charAt(i))
			{
				i++;
				j++;
			}
			if(j == M)
			{
				System.out.println("Found pattern at index " + (i - j));
				j = lps[j-1];
			}
			else if(i < N && pat.charAt(j) != txt.charAt(i))
			{
				if(j != 0)
				{
					j = lps[j-1];
				}
				else
				{
					i++;
				}
			}
		}
		
	}
	
	private void computeLPSArray(final String pat, final int M, final int[] lps)
	{
		int len = 0;
		int i = 1;
		lps[0] = 0;
		
		while(i < M)
		{
			if(pat.charAt(i) == pat.charAt(len))
			{
				++len;
				lps[i] = len;
				++i;
			}
			else
			{
				if(len == 0)
				{
					lps[i] = len;
					++i;
				}
				else
				{
					len = lps[len-1];
					// We don't have to decrement i, because that will be matched aghain
				}
			}
		}
	}

}
