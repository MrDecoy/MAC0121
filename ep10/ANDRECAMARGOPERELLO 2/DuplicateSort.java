/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Anagrams
 * Data: 07/09/2017
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

public class DuplicateSort {

    //Wrapper para o quick sort, chamando ele do comeco ao fim do array.
    static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        qsort(a, 0, (a.length)-1);

    }
    /**********************************************/
    /*  O codigo abaixo é a implementacao do 3-way  */
    /*  partitioning quicksort (Bentley-MccInroy)   */
    /*  Como apresentado em aula, esta implementacao*/
    /*  ira sortar o vetor com, no maximo, O(nlogt) */
    /*  e tomar, no maximo, tempo n^2.              */
    /*  o algoritmo abaixo foi o apresentado nos    */
    /* slides de aula.                              */
    /*                                              */

    private static void qsort(Comparable[] a, int l, int r)
    {

        //tomamos o valor mais a esquerda como pivot
        if (r <= l) return;
        int i = l-1, j = r;
        int p = l-1, q = r;
        while(true)
        {
            //Achamos o primeiro maior (estrito) do que o pivot
            while (less(a[++i], a[r])) ;

            //Achamos o "ultimo" menor (estrito) do que o pivot
            while (less(a[r], a[--j])) if (j == l) break;
            if (i >= j) break;
            exch(a, i, j);
            //Troca caso forem iguais ao pivot
            if (eq(a[i], a[r])) exch(a, ++p, i);
            if (eq(a[j], a[r])) exch(a, --q, j);
        }
        exch(a, i, r);
        j = i - 1;
        i = i + 1;
        //Coloca menores a esquerda e maiores a direita
        for (int k = l ; k <= p; k++) exch(a, k, j--);
        for (int k = r-1; k >= q; k--) exch(a, k, i++);
        qsort(a, l, j);
        qsort(a, i, r);
    }

    //Funcao para checar se dois elementos sao iguais.
    private static boolean eq(Comparable a, Comparable b) {return (a.compareTo(b) == 0);}
    //Funcao para checar se o primeiro elemento é menor (estrito) que o segundo.
    private static boolean less(Comparable a, Comparable b) {return (a.compareTo(b) < 0);}

    //Troca o elemento na posicao i com o da posicao j.
    private static void exch(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
