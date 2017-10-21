/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Andre Camargo Perello
 * Numero USP: 9912403
 * Tarefa: ColoredBallOO
 * Data: 10/08/2017
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

public class ColoredBallOO {
    private  Vector pos,vel;
    private double rad;
    private Color c;


    public ColoredBallOO(Vector p, Vector v, double r, Color c) {
         this.pos = p;
         this.vel = v;
         this.rad = r;
         this.c = c;

    }
    //Retorna a posicao da bola
    public Vector pos() {
        return this.pos;
    }

    //Retorna a velocidade da bola
    public Vector vel() {
        return this.vel;
    }

    //Retorna o raio da bola
    public double radius() {
        return this.rad;
    }

    //Muda a velocidade da bola
    public void setVel(Vector v) {
        this.vel = v;
    }

    //Atualiza a posicao
    public void updatePosition(double dt) {
        this.pos = this.pos.plus(vel.scale(dt));
    }

    //Checa se esta na tela.
    //update 1 de cada vez para o caso da bola estar nos 4 cantos.
    public void treatWalls(double size, double dt) {
        Vector futurePos = this.pos.plus(vel.scale(dt));

        double x = this.vel.cartesian(0);
        double y = this.vel.cartesian(1);

        //Checa se saiu no eixo x
        if(futurePos.cartesian(0) + this.radius() > size || futurePos.cartesian(0) - this.radius() < 0) {
            x = -this.vel.cartesian(0);
        }

        //Checa se saiu no eixo y
        if(futurePos.cartesian(1) + this.radius() > size || futurePos.cartesian(1) - this.radius() < 0) {
            y = -this.vel.cartesian(1);

        }
        double[] data = {x,y};
        this.setVel(new Vector(data));
    }

    //Move para a nova posicao
    public void move(double size, double dt) {
        for(int i = 0; i < dt; ++i) {
            this.treatWalls(size, dt);
            this.updatePosition(dt);

        }
    }
    //Desenha a bola
    public void draw() {
        StdDraw.setPenColor(this.c);
        StdDraw.filledCircle(this.pos.cartesian(0), this.pos.cartesian(1), this.rad);
    }



}
