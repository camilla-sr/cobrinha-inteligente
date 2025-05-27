package pacote;

import java.util.ArrayList;
import java.util.List;

public class No {       //classe para gerenciar as `casas` do tabuleiro
    private int linha, coluna;
    private char valor;
    private List<No> vizinhos;
    
    public No(int linha, int coluna, char valor) {
        this.linha = linha;
        this.coluna = coluna;
        this.valor = valor;
        this.vizinhos  = new ArrayList<>();
    }
    
    public void addVizinho(No vizinho) { vizinhos.add(vizinho); }   //adiciona nos proximos na lista
    public List<No> getVizinhos() { return vizinhos; }      //retorna todos os nos proximos
    public boolean isParede() { return this.valor == '1'; }      //retorna se a aresta existe
    
    public int getLinha() { return linha; }
    public void setLinha(int linha) { this.linha = linha; }
    public int getColuna() { return coluna; }
    public void setColuna(int coluna) { this.coluna = coluna; }
    public char getValor() { return valor; }
    public void setValor(char valor) { this.valor = valor; }
}