package com.automation;

public class Leet_242_IsAnagram
{
    public static boolean isAnagram(String s, String t) {

        if(s.length() != t.length()){
            return false;
        }

        int[] count = new int[26];

        for (int i=0; i<s.length(); i++){
            System.out.println( count[s.charAt(i) - 'a']++);

        }

        for (int i=0; i<t.length(); i++){
            System.out.println( count[t.charAt(i) - 'a']--);
        }

        for (int c : count){
            if(c != 0){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        boolean result = isAnagram("muk", "esh");
        System.out.println(result);
    }
}
