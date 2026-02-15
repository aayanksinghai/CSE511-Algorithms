import java.util.Scanner;

public class Hextech {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String p = sc.next();

        sc.close();

        // Logic
        boolean ans = true;
        int sLen = s.length();
        int pLen = p.length();

        int sIndex = 0, pIndex = 0;
        int maxLen = sLen < pLen ? pLen : sLen;
        for (int i = 0; i < maxLen; i++) {
            if (s.charAt(sIndex) == p.charAt(pIndex) || (p.charAt(pIndex) == '.')) {
                sIndex++;
                pIndex++;
                continue;
            }

            if (s.charAt(sIndex) != p.charAt(pIndex) && p.charAt(pIndex) != '*') {
                if (pIndex < pLen && p.charAt(pIndex + 1) == '*') { // zero case
                    pIndex += 2;
                    continue;
                } else {
                    ans = false;
                    break;
                }
            } else {
                char ch = p.charAt(pIndex - 1);
                if (ch == s.charAt(sIndex) || ch == '.') {
                    sIndex++;
                    pIndex++;
                    continue;
                } else {
                    ans = false;
                    break;
                }

            }

        }

        System.out.println(ans);
    }
}
