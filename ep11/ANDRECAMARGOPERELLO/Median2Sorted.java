/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Median 2 sorted
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

public class Median2Sorted {

    /*Explicacao do algoritmo:
      Para a resolucao do problema, realizamos uma busca binaria simultanea nos dois vetores ordenados.
      Para isso, guardamos o indice em cada vetor, mantendo os indices de referencia indexA e indexB.
      Como o algortimo segue o modelo de busca binaria, apenas em 2 vetores simultaneamente, sua complexidade sera O(lgn).

     */

    public static Comparable select(Comparable[] a, Comparable[] b, int k) {
        int sizeA = a.length, sizeB = b.length;
        //Como comecamos a contar do 0, arrumamos o indice
        if (k != 0) k += 1;

        int indexA = 0, indexB = 0, step;
        while (indexA + indexB < k - 1) {
            step = (k - indexA - indexB) / 2;
            int stepA = indexA + step;
            int stepB = indexB + step;
            if (sizeA > stepA - 1 && (sizeB <= stepB - 1 || a[stepA - 1].compareTo(b[stepB - 1]) < 0)) {
                // Escolhemos o elemento tal que  indexA = stepA - 1
                indexA = stepA;
            } else {
                // Escolhemos o elemento tal que  indexB = stepA - 1
                indexB = stepB;
            }
        }
        // Caso base se indexA + indexB == k - 1

        if (sizeA > indexA && (sizeB <= indexB || a[indexA].compareTo(b[indexB]) < 0)) {
            return a[indexA];
        } else {
            return b[indexB];
        }
    }


}
