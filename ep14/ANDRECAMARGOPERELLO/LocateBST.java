/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Locate BST
 * Data: 30/10/2017
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

import edu.princeton.cs.algs4.BST;

public class LocateBST {

    public static void main(String[] args) {

        //Lemos os intervalos
        //Trataremos os intervalos com a estrutura [min, max], para facilitar a compreensao dos comentarios
        int[] intervalPoints = (new In(args[0])).readAllInts();
        In inPoints = new In("ex_stdin.in");
        int[] points = inPoints.readAllInts();

        //Vamos guardar o min como a key e o max como o value do node.
        BST<Integer, Integer> intervals = new BST<Integer, Integer>();
        for(int i = 0; i < intervalPoints.length; i += 2) {
            intervals.put(intervalPoints[i], intervalPoints[i+1]);
        }


        for(int i = 0; i < points.length; i++) {

            //Checamos se o ponto esta a esquerda de todos os intervalos dados
            if (points[i] < intervals.min())
                System.out.println(points[i] + ": left of [" + intervals.min() + ", " + intervals.get(intervals.min()) + "]");

            //Checamos se o ponto esta a direita de todos os intervalos dados
            else if (points[i] > intervals.get(intervals.max()))
                System.out.println(points[i] + ": right of [" + intervals.max() + ", " + intervals.get(intervals.max()) + "]");

            //Caso esteja no meio dos intervalos dados
            else {

                //Pegamos o maior "min" menor que o ponto
                int floor = intervals.floor(points[i]);

                //Checamos se esta no intervalo que floor faz parte
                if (points[i] <= intervals.get(floor))
                    System.out.println(points[i] + ": [" + floor + ", " + intervals.get(floor) + "]");


                //Caso em que esteja a direita do intervalo que floor faz parte
                else {
                    int ceil = intervals.ceiling(points[i]);
                    System.out.println(points[i] + ": between [" + floor + ", " + intervals.get(floor) + "] & [" + ceil + ", " + intervals.get(ceil) + "]");
                }

            }

        }

    }

}
