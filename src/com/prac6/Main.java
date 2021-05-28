package com.prac6;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Task 1");
	    System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"));
	    System.out.println();

        System.out.println("Task 2");
        System.out.println(collect("intercontinentalisationalism", 6));
        System.out.println(collect("strengths", 3));
        System.out.println(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15));
        System.out.println();

        System.out.println("Task 3");
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(nicoCipher("andiloveherso", "tesha"));
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        System.out.println(nicoCipher("iloveher", "612345"));
        System.out.println();

        System.out.println("Task 4");
        System.out.println(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15, 3}, 45));
        System.out.println(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45));
        System.out.println();

        System.out.println("Task 5");
        System.out.println(isExact(6));
        System.out.println(isExact(24));
        System.out.println(isExact(125));
        System.out.println(isExact(720));
        System.out.println(isExact(1024));
        System.out.println(isExact(40320));
        System.out.println();

        System.out.println("Task 6");
        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.19(2367)"));
        System.out.println(fractions("0.1097(3)"));
        System.out.println();

        System.out.println("Task 7");
        System.out.println(pilish_string("33314444"));
        System.out.println(pilish_string("TOP"));
        System.out.println(pilish_string("X"));
        System.out.println(pilish_string(""));
        System.out.println();

        System.out.println("Task 8");
        System.out.println(generateNonconsecutive(1));
        System.out.println(generateNonconsecutive(2));
        System.out.println(generateNonconsecutive(3));
        System.out.println(generateNonconsecutive(4));
        System.out.println();

        System.out.println("Task 9");
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println();

        System.out.println("Task 10");
        System.out.println(sumsUp(new int[]{1, 2, 3, 4, 5}));
        System.out.println(sumsUp(new int[]{1, 2, 3, 7, 9}));
        System.out.println(sumsUp(new int[]{10, 9, 7, 2, 8}));
        System.out.println(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7}));
        System.out.println();
    }

    public static ArrayList<Character> strToCharArray(String s){
        ArrayList<Character> result = new ArrayList<Character>();
        for (char c : s.toCharArray()) {
            result.add(c);
        }
        return result;
    }

    public static ArrayList<Integer> strToIntArray(String s){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (char c : s.toCharArray()) {
            result.add(Character.getNumericValue(c));
        }
        return result;
    }

    public static String hiddenAnagram(String s1, String s2){
        s1 = s1.toLowerCase().replaceAll("\\W|\\d", "");
        s2 = s2.toLowerCase().replaceAll("\\W|\\d", "");
        ArrayList<Character> chs1 = strToCharArray(s1);
        ArrayList<Character> chs2 = strToCharArray(s2);
        ArrayList<Character> chs2temp;
        String result = "";
        while (chs1.size() > 0) {
            result = "";
            chs2temp = new ArrayList<>(chs2);
            for (Character sourceChr : chs1){
                if (!result.equals("") && !chs2temp.contains(sourceChr) && chs2temp.size() > 0) {
                    chs1.remove(0);
                    result = "";
                    break;
                } else if (chs2temp.contains(sourceChr)){
                    result += sourceChr;
                    chs2temp.remove(sourceChr);
                }
            }

            if (!result.equals("") && chs2temp.size() > 0){
                chs1.remove(0);
                continue;
            }

            if (!result.equals("")) {
                break;
            }
        }
        if (chs1.size() == 0) {
            return "noutfond";
        }
        return result;
    }

    public static ArrayList<String> collect(String s, int n){
        ArrayList<String> result = new ArrayList<String>();
        if (n > s.length()){
            return result;
        }
        result.add(s.substring(0, n));
        result.addAll(collect(s.substring(n), n));
        Collections.sort(result);
        return result;
    }

    public static String nicoCipher(String message, String key) {
        ArrayList<Character> chars = strToCharArray(key);

        HashMap<Character, ArrayList<Character>> data = new HashMap<>();
        int counter = 0;
        while (!message.equals("")) {
            counter += 1;
            for (Character chr : chars){
                if (message.equals("")){
                    data.get(chr).add(' ');
                } else if (!data.containsKey(chr)) {
                    data.put(chr, new ArrayList<>());
                    data.get(chr).add(message.charAt(0));
                } else {
                    data.get(chr).add(message.charAt(0));
                }

                if (message.length() == 1){
                    message = "";
                } else if (message.length() > 1) {
                    message = message.substring(1);
                }
            }
        }
        Collections.sort(chars);

        String result = "";
        for (int i = 0; i < counter; i++){
            for (Character chr: chars){
                result += data.get(chr).get(0);
                data.get(chr).remove(0);
            }
        }

        return result;
    }

    public static ArrayList<Integer> twoProduct(int[] numbers, int n){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++){
            for (int j = 0; j < i; j++){
                if (numbers[i] * numbers[j] == n){
                    result.add(numbers[j]);
                    result.add(numbers[i]);
                    return result;
                }
            }
        }
        return result;
    }

    public static ArrayList<Integer> isExact(int n, int c){
        c++;

        ArrayList<Integer> result = new ArrayList<>();
        if (n == 1){
            result.add(1);
            result.add(1);
            return result;
        } else if (n == 0) {
            return result;
        }
        else {
            if (n % c != 0) {
                return result;
            }

            ArrayList<Integer> exact = isExact(n / c, c);
            if (exact.size() == 2) {
                result.add(n);
                result.add(exact.get(1) + 1);
                return result;
            } else if (exact.size() == 0){
                return result;
            }
        }

        return result;
    }

    public static ArrayList<Integer> isExact(int n){
        ArrayList<Integer> result = new ArrayList<>();
        if (n == 1){
            result.add(1);
            result.add(1);
            return result;
        } else if (n == 0) {
            return result;
        }
        else {
            ArrayList<Integer> exact = isExact(n , 0);
            if (exact.size() == 2) {
               result.add(n);
               result.add(exact.get(1) - 1);
               return result;
            } else if (exact.size() == 0){
               return result;
            }
        }

        return result;
    }

    public static String fractions(String sourceFrac){
        String intPart = sourceFrac.split("\\.")[0];
        String fracPart = sourceFrac.split("\\.")[1];
        int Y = Integer.parseInt(intPart);
        int k = fracPart.split("\\(")[1].replace(")", "").length();
        int m = fracPart.split("\\(")[0].length();
        int a = Integer.parseInt(fracPart.replace("(", "").replace(")", ""));
        int b;
        if (fracPart.startsWith("(")) {
            b = 0;
        } else {
            b = Integer.parseInt(fracPart.split("\\(")[0]);
        }

        String denomStr = "";
        for (int i = 0; i < k; i++){
            denomStr += "9";
        }
        for (int i = 0; i < m; i++){
            denomStr += "0";
        }

        int numerator = a - b;
        int denominator = Integer.parseInt(denomStr);

        boolean active = true;
        while (active) {
            for (int i = 2; i <= Math.max(numerator, denominator); i++){
                if (numerator % i == 0 && denominator % i == 0){
                    numerator = numerator / i;
                    denominator = denominator / i;
                    break;
                }
                if (i == Math.max(numerator, denominator)) {
                    active = false;
                }
            }
        }

        return (numerator + (Y * denominator)) + "/" + denominator;
    }

    public static String pilish_string(String s){
        String result = "";
        if (s.trim().equals("")){
            return result;
        }
        ArrayList<Integer> pi = strToIntArray("314159265358979");
        for (Integer number : pi){
            if (s.length() > number) {
                result += s.substring(0, number);
                s = s.substring(number);
            } else if (s.length() == number) {
                result += s.substring(0, number);
                break;
            } else {
                for (int i = 0; i <= number - s.length(); i++){
                    s += s.charAt(s.length() - 1);
                }
                result += s.substring(0, number);
                break;
            }
            result += " ";
        }
        return result;
    }


    public static String generateNonconsecutive(int n){
        String result = "";
        for (int i = 0; i < Math.pow(2, n); i++){
            String binary = Integer.toBinaryString(i);
            if (!binary.contains("11")){
                while (binary.length() < n){
                    binary = "0" + binary;
                }
                result += binary;
                result += " ";
            }
        }
        return result;
    }

    public static Integer count(ArrayList<Character> arr, Character sourceChr){
        int c = 0;
        for (Character chr: arr){
            if (chr.equals(sourceChr)){
                c++;
            }
        }
        return c;
    }

    public static String isValid(String s){
        HashMap<Integer, HashSet<Character>> data = new HashMap<>();
        ArrayList<Character> chrs = strToCharArray(s);
        for (Character chr: chrs){
            int chCount = count(chrs, chr);
            if (!data.containsKey(chCount)){
                data.put(chCount, new HashSet<>());
                data.get(chCount).add(chr);
            } else {
                data.get(chCount).add(chr);
            }
        }

        if (data.size() == 1){
            return "YES";
        } else if (data.size() == 2) {
            ArrayList<Integer> keys = new ArrayList<Integer>(data.keySet());
            if (Math.abs(keys.get(0) - keys.get(1)) == 1 && data.get(Math.max(keys.get(0), keys.get(1))).size() == 1) {
                return "YES";
            }
        }
        return "NO";
    }

    public static ArrayList<ArrayList<Integer>> sumsUp(int[] numbers){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++){
            for (int j = i + 1; j < numbers.length; j++){
                if (numbers[i] + numbers[j] == 8){
                    ArrayList<Integer> value = new ArrayList<>();
                    value.add(numbers[i]);
                    value.add(numbers[j]);
                    Collections.sort(value);
                    result.add(value);
                }
            }
        }
        return result;
    }
}
