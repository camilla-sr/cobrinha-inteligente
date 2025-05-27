package pacote;

import java.util.Random;

public class Grafo {        //esse e o nosso labirinto
    private No[][] tabuleiro;
    private No inicio;
    private No fim;

    public Grafo(int tamanho){
        this.tabuleiro = new No[tamanho][tamanho];
        iniciarNos(tamanho);
    }
    
    public void iniciarNos(int tamanho){
        Random ran = new Random();    
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                char valor = ran.nextBoolean() ? '1' : '0';    //preenche aleatoriamente com 1 ou 0
                tabuleiro[i][j] = new No(i,j,valor);    //cria o no com o valor aleatorio e a posicao dele
            }
        }
        tabuleiro[0][0].setValor('E');        //isso forca a entrada livre do labirinto
        tabuleiro[tamanho-1][tamanho-1].setValor('C');    //e isso forca a saida livre
        
        this.inicio = tabuleiro[0][0];
        this.fim = tabuleiro[tamanho-1][tamanho-1];
        conecarNos(tamanho);
    }
    
    public void conecarNos(int tamanho){
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                No noAtual = tabuleiro[i][j];
                if(!noAtual.isParede()) continue;      //ignorar as paredes que serao os 1
                
                if(i > 0){      //isso evita estouro da matriz
                    No noCima = tabuleiro[i-1][j];      //armazeno o tal no em variavel semantica
                    //SE o no de cima ao atual nao for uma parede
                    if(noCima.isParede()) noAtual.addVizinho(noCima); //crio uma aresta entre eles
                }
                if(i < tamanho-1){
                    No noBaixo = tabuleiro[i+1][j];
                    if(noBaixo.isParede()) noAtual.addVizinho(noBaixo);
                }
                if(j > 0){
                    No noEsquerda = tabuleiro[i][j-1];
                    if(noEsquerda.isParede()) noAtual.addVizinho(noEsquerda);
                }
                if(j < tamanho-1){
                    No noDireita = tabuleiro[i][j+1];
                    if(noDireita.isParede()) noAtual.addVizinho(noDireita);
                }
            }
        }
    }

    public void diagrama(){         //esse metodo vai tentar criar um diagrama com as ligacoes
        System.out.println("===============================================");
        for (int i = 0; i < tabuleiro.length; i++) {
            //aqui vao ter as ligacoes horizontais
            for (int j = 0; j < tabuleiro[i].length; j++) {
                No v = tabuleiro[i][j];
                System.out.print(v.getValor());     //isso vai trazer o valor que esta no no
                
                if(j < tabuleiro[i].length - 1){    //verifica se tem um vizinho a direita
                    No vDireita = tabuleiro[i][j+1];
                    
                    if(v.getVizinhos().contains(vDireita)){
                        System.out.print("----");
                    }else{
                        System.out.print("    ");
                    }
                }
            }
            System.out.println();
            
            //aqui vao ter as ligacoes verticais
            for (int j = 0; j < tabuleiro[i].length; j++) {
                No v = tabuleiro[i][j];
                
                if(i < tabuleiro.length - 1){
                    No vBaixo = tabuleiro[i+1][j];
                    
                    if(v.getVizinhos().contains(vBaixo)){
                        System.out.print("|    ");
                    }else{
                        System.out.print("     ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("===============================================");
    }
    
    public void imprimirLabirinto(){
        System.out.println(" -------------------------------");
        for (int i = 0; i < tabuleiro.length; i++) {
        System.out.print("| ");
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j].getValor() + "  ");
            }
            System.out.println("| ");
        }
        System.out.println(" -------------------------------");
    }

    public No[][] getTabuleiro() { return tabuleiro; }
    public No getInicio() { return inicio; }
    public No getFim() { return fim; }
}