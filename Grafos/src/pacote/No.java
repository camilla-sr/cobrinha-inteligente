package pacote;

import java.util.ArrayList;
import java.util.List;

public class No {       //classe para gerenciar as `casas` do tabuleiro
    private int linha, coluna;
    private char valor;
    private List<No> caminhos;  //armazenamos separadamente tudo que e um caminho valido
    private List<No> paredes;   // de uma parede. Isso cria tipos diferentes de nos
    
    public No(int linha, int coluna, char valor) {
        this.linha = linha;
        this.coluna = coluna;
        this.valor = valor;
        this.caminhos  = new ArrayList<>();
        this.paredes  = new ArrayList<>();
    }
    
    public void addParede(No parede) { paredes.add(parede); }   //adiciona nos proximos na lista
    public List<No> getParede() { return paredes; }      //retorna todos os nos proximos
    public boolean isParede() { return this.valor == '1'; }      //retorna se a aresta existe
    
    public void addCaminho(No caminho) { caminhos.add(caminho); }
    public List<No> getCaminho() { return caminhos; }
    public boolean isCaminho() { return this.valor == '0' || this.valor ==  'E' || this.valor == 'C'; }
    
    public int getLinha() { return linha; }
    public void setLinha(int linha) { this.linha = linha; }
    public int getColuna() { return coluna; }
    public void setColuna(int coluna) { this.coluna = coluna; }
    public char getValor() { return valor; }
    public void setValor(char valor) { this.valor = valor; }
}