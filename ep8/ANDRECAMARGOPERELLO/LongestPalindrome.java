/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Longest Palindrome
 * Data: 31/08/2017
 *
 *
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class LongestPalindrome {

    private static boolean isPalindrome(String s) {
        return s.equals(new StringBuffer(s).reverse().toString());

    }
    public static void main(String[] args) {
        String s;
        if(args.length < 1) {
            s = (new In()).readString();
        }
        else {
            s = (new In(args[0])).readString();
        }
        s = s.replaceAll("\\s+", " ").trim();

        String pali = new String("");
        int maxSize = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int next = s.indexOf(s.substring(i,i+1), i+1);
            boolean possible = true;
            while(next != -1) {
                possible = isPalindrome(s.substring(i, next+1));
                if (possible &&(next - i) > maxSize) {
                    pali = s.substring(i, next+1);
                    maxSize = (next-i);
                }
                next = s.indexOf(s.substring(i,i+1), next+1);
            }

        }
        System.out.println(pali);

    }
}
