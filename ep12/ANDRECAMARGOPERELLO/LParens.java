/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Left Parens
 * Data: 21/10/2017
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

import java.util.Stack;

public class LParens {

    //Ideia do algoritmo:
    //Vamos empilhando elementos (inteiros, operacoes) em uma pilha ate encontrarmos um ).
    //Ao encontrar, desempilhamos os primeiros 3 elementos da pilha e concatenamos eles.
    //Depois, empilhamos o resultado da concatenacao.
    public static void main(String[] args) {
        //Lemos o input
        In input = new In();
        String exp = input.readAllLines()[0];

        //Criamos o Stack que utiliaremos
        Stack<String> expChars = new Stack<String>();

        //removemos os espacos para facilitar a leitura
        exp = exp.replaceAll("\\s+","");
        System.out.println(exp);
        int n = exp.length();
        for(int i = 0; i < n; i++) {

            //Percorremos ate encontrar o primeiro )
            while( i < n && (exp.charAt(i)) != ')' ) {

                //Encontramos o inteiro
                StringBuilder tmp = new StringBuilder();
                while (i < n && Character.isDigit(exp.charAt(i))){
                    tmp.append(Character.toString(exp.charAt(i)));
                    i++;
                }
                //Adicionamos o inteiro
                if(!tmp.toString().isEmpty())
                    expChars.push(tmp.toString());

                //Adicionamos a operacao
                if(i < n && (exp.charAt(i)) != ')') {
                    expChars.push(Character.toString(exp.charAt(i)));
                    i++;
                }
            }
            // Ao encontrarmos o parenteses, pegamos os primeiros 3 elementos de nossa pilha e empilhamos o "agrupamento" deles
            String integerTwo, operation, integerOne;

            integerTwo = expChars.pop();

            operation = expChars.pop();
            integerOne = expChars.pop();
            String aux = "( " + integerOne + " " + operation + " " + integerTwo + " )";
            expChars.push(aux);
            System.out.println(expChars.peek());
            System.out.println("------");
        }
        System.out.println(expChars.pop());
    }


}
