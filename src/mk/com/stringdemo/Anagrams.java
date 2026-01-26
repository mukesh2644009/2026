package mk.com.stringdemo;

import java.util.Arrays;

public class Anagrams 
{
	public static void main(String[] args) {
		boolean abcd  = anagramTest("java", "avaJ");
		System.out.println(abcd);
	}
	
	public static boolean anagramTest(String s1, String s2) {
		char[] arr1 = s1.toLowerCase().toCharArray();
		char[] arr2 = s2.toLowerCase().toCharArray();
		
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		return Arrays.equals(arr1,  arr2);
	}
}
