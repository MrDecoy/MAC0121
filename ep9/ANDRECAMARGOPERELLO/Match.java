/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Nuts and bolts
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
import pieces.*;
public class Match {
    private static void swap(NutsAndBolts nb, int i, int j) {
        Bolt tmpBolts = nb.bolts[i];
        nb.bolts[i] = nb.bolts[j];
        nb.bolts[j] = tmpBolts;

    }

    public static void match(NutsAndBolts nutsAndBolts) {
        int n = nutsAndBolts.N();
        for(int i = 0; i < n; ++i) {
            Nut obj = nutsAndBolts.nuts[i];
            for(int j = i; j < n; ++j) {
                int compare = nutsAndBolts.nuts[i].compareTo(nutsAndBolts.bolts[j]);
                if (compare == 0) {
                    swap(nutsAndBolts, i, j);
                    break;

                }
            }
        }

    }


}
