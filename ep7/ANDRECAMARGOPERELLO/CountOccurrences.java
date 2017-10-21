/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Count Occurrences
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


import java.util.Arrays;

public class CountOccurrences {

    public static void main(String[] args) {

        // Le as listas de entrada com complexidade O(n)
        int[] listaInts = (new In(args[0])).readAllInts();
        int[] queries = (new In("1000000_10000.in")).readAllInts();
        int m = queries.length;
        int n = listaInts.length;

        //vamos fazer uma busca binaria m vezes.
        //Isto ira nos dar complexidade O(nlog(m))
        for (int i = 0; i < m; ++i) {
            int cont = 0;
            int pos = Arrays.binarySearch(listaInts, queries[i]);

            if (pos >= 0) {
                cont++;

                //conta o numero de repeticoes.
                int j = 1;
                while((pos + j < n) && listaInts[pos+j] == queries[i]) {
                    j++;
                    cont++;
                }
                j = 1;
                while((pos - j >= 0) && listaInts[pos-j] == queries[i]) {
                    j++;
                    cont++;
                }
            }
            System.out.println(cont);
        }

    }
}
