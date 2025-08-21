package Strings;

import java.util.ArrayList;

public class Anagram {
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();

        a.add("a");
        a.add("jk");
        a.add("abb");
        a.add("mn");
        a.add("abc");

        b.add("bb");
        b.add("kj");
        b.add("bbs");
        b.add("op");
        b.add("def");
        System.out.println(checkDifferenceString(a, b));
    }   
    
    
    public static ArrayList<Integer> checkDifferenceString(ArrayList<String> a, ArrayList<String> b){
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < a.size(); i++){
                ans.add(checkAnagram(a.get(i), b.get(i)));
            }

        return ans;
    }

    public static int checkAnagram(String a, String b){

        // Anagrams must have the same length.
        if (a.length() != b.length()) {
            return -1;
        }

        // Create a frequency map using an array (assuming lowercase English letters).
        int[] charFrequencies = new int[26];

        // Increment frequency for each character in the first string.
        for (char c : a.toCharArray()) {
            charFrequencies[c - 'a']++;
        }

        // Decrement frequency for each character in the second string.
        for (char c : b.toCharArray()) {
            charFrequencies[c - 'a']--;
        }

        int changesNeeded = 0;
        // Sum up all the positive counts. This represents the characters in 'a'
        // that need to be replaced by characters missing in 'a' (which have negative counts).
        for (int frequency : charFrequencies) {
            if (frequency > 0) {
                changesNeeded += frequency;
            }
        }

        return changesNeeded;
        /*
        if(a.length() != b.length()) return -1;

        HashMap<Character, Integer> aMap = new HashMap<>();
        HashMap<Character, Integer> bMap = new HashMap<>();

        for(int i = 0; i < a.length(); i++){
            aMap.put(a.charAt(i), aMap.getOrDefault(a.charAt(i), 0)+1);
            bMap.put(b.charAt(i), bMap.getOrDefault(b.charAt(i), 0)+1);
        }

        Set<Character> commonKeys = new HashSet<>(aMap.keySet());
        commonKeys.retainAll(bMap.keySet());

        for(Character key : commonKeys){
            aMap.remove(key);
            bMap.remove(key);
        }

        int count = 0;
        for(Character key : aMap.keySet()){
            count++;
        }
        
        for(Character key : bMap.keySet()){
            count++;
        }

        return count/2;
         */
    }
}
