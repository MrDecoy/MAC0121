/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Ortho
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



public class Ortho {

    private static int BinarySearch(String[] v, int first, int last, String key) {
        int mid = first + (last - first)/2;
        //Caso base
        if(last <= first) return -1;


        int cmp = v[mid].compareTo(key);
        //Procura no vetor v[mid+1, last]
        if(cmp > 0) return BinarySearch(v, first, mid, key);

        //Procura no vetor v[first, mid]
        else if(cmp < 0) return BinarySearch(v, mid+1, last, key);

        else return mid;
        }
    //Faz a busca binaria no vetor inteiro
    private static int binaryWrap(String[] v, String key) {
        return BinarySearch(v, 0, v.length, key);
    }


    public static void main(String[] args) {

        //Leitura do dicionario para uma lista de Strings
        In dictPage = new In(args[0]);
        String[] dictList = dictPage.readAllLines();

        //Leitura do texto como uma lista de Strings
        In textInput = new In("mobydick.txt");
        String text = textInput.readAll();
        String[] textWords = text.split("[\\W\\r\\n]+");
        int n = textWords.length;



        //Caso de palavras marcadas
        if(args[1].equals("-m")) {

            //Checa as palavras encontradas no texto.
            for(int i = 0; i < n; ++i) {
                int repetido = 0;

                //Caso a palavra ja tenha sido marcada
                for(int j = 0; j < i; ++j)
                    if(textWords[i].compareToIgnoreCase(textWords[j]) == 0) repetido = 1;

                //Busca se a palvra esta no dicionario
                if ((binaryWrap(dictList, textWords[i]) == -1 && binaryWrap(dictList, textWords[i].toLowerCase()) == -1) && repetido != 1)
                    text = text.replaceAll("\\b" + textWords[i] + "\\b", "*"+textWords[i]+"*");


            }
            //Printa o texto resultante
            System.out.println(text);

        }

        //Caso de encontar palavras presentes no dicionario
        else {

            for(int i = 0; i < n; ++i) {
                if ((binaryWrap(dictList, textWords[i]) == -1 && binaryWrap(dictList, textWords[i].toLowerCase()) == -1))
                    System.out.println(textWords[i]);
            }
        }
    }
}
