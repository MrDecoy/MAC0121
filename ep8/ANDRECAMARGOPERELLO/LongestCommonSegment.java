/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Longest common segment
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


public class LongestCommonSegment {

    public static void main(String[] args) {
        String s, t;

        if(args.length < 1) {
            In bothStrings = new In();
            s = bothStrings.readLine();
            t = bothStrings.readLine();
        }
        else {
            s = (new In(args[0]).readString());
            t = (new In(args[1]).readString());
        }
        s = s.replaceAll("\\s+", " ").trim();
        t = t.replaceAll("\\s+", " ").trim();
        int m = s.length();
        int n = s.length();
        int maxSize = 0;

        String subs = new String("");
        for (int i = 0; i < n; ++i) {
            int start = t.indexOf(s.substring(i, i+1));
            int step = 0;
            int size = 0;
            if (start >= 0)
                while(i + step < n && start + step < m && t.substring(start, start + step).equals(s.substring(i, i + step))) {
                step++;
                size++;


                }
            if (size >= maxSize) {
                maxSize = size;
                subs = s.substring(i, i + step - 1);
            }

        }
        System.out.println(subs);
    }
}