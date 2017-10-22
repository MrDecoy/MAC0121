/******************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Data files:   http://introcs.cs.princeton.edu/43stack/tobe.txt  
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt 
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: Queue shuffle.
 * Data: 22/10/2017
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code Queue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the top item,
 *  testing if the queue is empty, getting the number of items in the
 *  queue, and iterating over the items in FIFO order.
 *  <p>
 *  This implementation uses a singly-linked list with a nested class for
 *  linked-list nodes.
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/43stack">Section 4.3</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> the generic type of an item in this queue
 */
public class Queue<Item> implements Iterable<Item> {
    private int n;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        public String toString() {
            StringBuilder s = new StringBuilder();
            Node tmp = this;
            while (tmp != null) {
                s.append(tmp.item);
                s.append(' ');
                tmp = tmp.next;
            }
            return s.toString();
        }
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int length() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    /**
     * Add the item to the queue.
     */
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    //**********************************************************************


    private Node getMiddle(Node head) {
        if(head == null) return head;
        Node fastPointer = head.next;
        Node slowPoitner = head;

        //When fastPointer hits the end, slow pointer will be at the middle, given that slowPointer moves half the "speed" of fastPointer
        while(fastPointer != null) {
            fastPointer = fastPointer.next;
            if(fastPointer != null) {
                slowPoitner = slowPoitner.next;
                fastPointer = fastPointer.next;
            }
        }
        return slowPoitner;
    }

    //Para cada chamada de merge, randomizamos a escolha, ao inves de ordenar. Por utiizar uma estrategia de divide and conquer, todos os nodes tem a mesma probabilidade de aparecer
    //nem uma dada posicao. Isso se da pois a cada merge, os nodes tem probabilidade de assumir uma dada posicao.
    //. Utilizamos a ideia de moedas para facilitar a interpretacao. Ou seja,
    //Um Node x_i assume a posicao j se x_i = 1 ( ou seja cara) com Pr(x_i = 1) = 1/2 e Pr(x_i = 0) = 1/2.
    private Node merge(Node left, Node right) {
        Node result = null;

        if(left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if(StdRandom.uniform(10) > 5) {
            result = left;
            result.next = merge(left.next, right);
        }
        else {
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }


    //Corpo do "mergeShuffle". Utiliza uma estrategia de divide and conquer para dividir a lista e aleatorizar cada merge.
    //Como isto eh um algoritmo de mergeSort com a funcao merge levemente mudada, ele tera a mesma complexidade O(nlogn) e espaco O(logn).
    private Node shuffleHelper(Node head) {
        if(head == null || head.next == null) return head;
        Node mid = getMiddle(head);
        Node nextMid = mid.next;
        mid.next = null;
        Node left = shuffleHelper(head);
        Node right = shuffleHelper(nextMid);

        return merge(left, right);

    }

    //Funcao que chama o mergesort. Um wrapper.
    public void shuffle() {

        this.first = shuffleHelper(this.first);
    }



    /**
     * Unit tests the {@code Queue} data type.
     */
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i <= 10; i++) {
            queue.enqueue(i);
        }
        queue.shuffle();
        System.out.println(queue);

    }
}
