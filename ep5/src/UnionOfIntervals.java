/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Union of Intervals
 * Data: 08/09/2017
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
import java.awt.Color;
import java.util.Arrays;


public class UnionOfIntervals {
    public static void main(String[] args) {

        //le a entrada com complexidade O(n). isto poderia ser amenizado, entretanto como o algortimo roda em O(nlogn) nao vi necessidade.
        In input = new In("example.txt");
        double[] inputList = input.readAllDoubles();
        int n = inputList.length;

        //Cria um array de intervalos
        Interval1D[] intervalList = new Interval1D[n/2];
        for(int i = 0; i < n; i += 2) {
            intervalList[i/2] = new Interval1D(inputList[i], inputList[i+1]);

        }
        /*Ordenamos os intervalos em respeito ao inicio deles
        /*Arrays.sort ira ordenar a lista utilizando TimSort (uma variacao de mergesort com insertionsort).
        possuindo complexidade O(nlogn)*/

        Arrays.sort(intervalList, Interval1D.MIN_ENDPOINT_ORDER);

        double size = 0;
        int start = 0;
        //Acha os intervalos maximos com complexidade O(n)

        for(int i = 0; i < intervalList.length; ++i) {
            if(intervalList[i].min() > intervalList[0].max()) {
                size += intervalList[i-1].max() - intervalList[start].min();
                start = i;
            }
        }

        //a complexidade sera O(n + nlogn) o que resultara em O(nlogn)
        if(args.length < 1) {
            System.out.println("Total length: " + size + " [" + intervalList.length + "]");
        }
        //A complexidade tambem sera O(nlogn) no modo verboso, entretanto tera um O(n) a mais devido a impressao
        else if (args[0].equals("-v")) {
            System.out.println("Total length: " + size + " [" + intervalList.length + "]");

            double yStep = .8/(intervalList.length+1);
            for(int i = 0 ;i < intervalList.length; ++i) {
                //Desenha as linhas verticais
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.line(intervalList[i].min(), 0, intervalList[i].min(),1);
                StdDraw.line(intervalList[i].max(), 0, intervalList[i].max(),1);

                //Desenha cada intervalo da entrada
                StdDraw.line(intervalList[i].min(), yStep*(i+1), intervalList[i].max(),yStep*(i+1));

                //Desenha as unioes dos intervalos
                StdDraw.setPenColor(Color.RED);
                StdDraw.line(intervalList[i].min(), .8, intervalList[i].max(),.8);
                System.out.println(intervalList[i]);
            }

        }
    }
}
