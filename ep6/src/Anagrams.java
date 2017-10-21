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


import java.util.Arrays;



public class Anagrams {

    //Classe de apoio a fim de guardar o indice original da palavra
    private static class Word implements Comparable<Word>{
        public int index;
        public String name;

        public Word(String s, int index) {
            this.name = s;
            this.index = index;
        }
        //Torna a classe comparable
        public int compareTo(Word w) {
            return this.name.compareTo(w.name);
        }
    }


        public static void main(String[] args) {
            //Le a entrada com complexidade O(n)
            In input = new In();
            String[] wordList = input.readAllLines();

            //Cria um array de Words, a fim de guardar as palavras e seus indices em wordList
            Word[] anagrams = new Word[wordList.length];

            //Iremos ordenar cada palvra obtida e guardar ela como um objeto Word.
            //O loop tera complexidade O(n) e cada palavra consumira O(nlogn) para ser ordenada (devido ao uso do TimSort)
            for(int i = 0;i < wordList.length; ++i) {
                char[] tmp = wordList[i].toLowerCase().toCharArray();
                Arrays.sort(tmp);
                anagrams[i] = new Word(new String(tmp),i);
            }

            //Iremos ordenar as Words com complexidade O(nlogn) devido ao uso do TimSort
            Arrays.sort(anagrams);
            int i = 0;

            //Os anagramas estarao em ordem, permitindo imprimi-los na mesma linha.
            while(i < anagrams.length) {
                int j = i;
                String s = "* ";
                while(j < anagrams.length && anagrams[i].compareTo(anagrams[j]) == 0) {
                    s += wordList[anagrams[j].index]+ " ";
                    ++j;
                }
                if(j != i+1)
                    System.out.println(s);
                i = j;
            }
    }

}
