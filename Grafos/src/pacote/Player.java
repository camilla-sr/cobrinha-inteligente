package pacote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Player {
    private No posicao;         //onde a cobrinha esta
    private List<No> memoria;   //guarda todo o caminho percorrido
    private Map<No, List<Integer>> memoriaTentativas = new HashMap<>();     //vai criar a lista de alternativas disponiveis para cada posicao visitada na matrix
    private List<No> semSaida;
    private Random ran = new Random();

    public Player(No posicao){
        this.posicao = posicao;
        this.memoria = new ArrayList<>();
        this.semSaida = new ArrayList<>();
        atualizarMemoriaGeral(posicao);      //inclui a posicao inicial na memoria
    }
    
    public boolean andar(No[][] tabuleiro){
        if(!memoriaTentativas.containsKey(this.posicao)) { iniciaTentativas(posicao); }
        
        Integer direcao = proximaTentativa(posicao);
        while(direcao != null){
            boolean andou = false;
            switch(direcao){
                case 0: andou = subir(tabuleiro); break;
                case 1: andou = descer(tabuleiro); break;
                case 2: andou = direita(tabuleiro); break;
                case 3: andou = esquerda(tabuleiro); break;
            }
            if(andou){
                return true;
            }else{
                direcao = proximaTentativa(posicao);
            }
        }
        atualizaBecos(this.posicao);
        return false;
    }
    
    public boolean voltar(){
        if(!semSaida.contains(posicao)){
            memoriaTentativas.remove(posicao);
            memoria.remove(getUltimoPasso());
            posicao = getUltimaMemoria();
            return true;
        }
        return false;
    }
    
    public boolean subir(No[][] tabuleiro){
        int linha = posicao.getLinha();
        int coluna = posicao.getColuna();
        
        if(linha > 0){
            No proximoPasso = tabuleiro[linha-1][coluna];   //guarda o proximo passo para cima
            if(proximoPasso.isCaminho() && !memoria.contains(proximoPasso)){    //se esse caminho nao e uma parede e ainda nao foi percorrido
                posicao = proximoPasso;     //atualiza a posicao atual
                atualizarMemoriaGeral(proximoPasso);  //adiciona o novo passo na memoria
                return true;    //retorna que a cobra andou
            }
        }
        return false;   //nao andou
    }
    
    public boolean descer(No[][] tabuleiro){
        int linha = posicao.getLinha();
        int coluna = posicao.getColuna();
        
        if(linha < tabuleiro.length - 1){
            No proximoPasso = tabuleiro[linha+1][coluna];
            if(proximoPasso.isCaminho() && !memoria.contains(proximoPasso)){
                posicao = proximoPasso;
                atualizarMemoriaGeral(proximoPasso);
                return true;    //retorna que a cobra andou
            }
        }
        return false;
    }
    
    public boolean esquerda(No[][] tabuleiro){
        int linha = posicao.getLinha();
        int coluna = posicao.getColuna();
        
        if(coluna > 0){
            No proximoPasso = tabuleiro[linha][coluna-1];
            if(proximoPasso.isCaminho() && !memoria.contains(proximoPasso)){
                posicao = proximoPasso;
                atualizarMemoriaGeral(proximoPasso);
                return true;    //retorna que a cobra andou
            }
        }
        return false;
    }
    
    public boolean direita(No[][] tabuleiro){
        int linha = posicao.getLinha();
        int coluna = posicao.getColuna();
        
        if(coluna < tabuleiro[0].length-1){
            No proximoPasso = tabuleiro[linha][coluna+1];
            if(proximoPasso.isCaminho() && !memoria.contains(proximoPasso)){
                posicao = proximoPasso;
                atualizarMemoriaGeral(proximoPasso);
                return true;    //retorna que a cobra andou
            }
        }
        return false;
    }
    
    private Integer proximaTentativa(No no){
        List<Integer> tentativas = memoriaTentativas.get(no);
        if(tentativas == null || tentativas.isEmpty()){ return null; }
        return tentativas.remove(0);
    }
    
    private void iniciaTentativas(No no){
        List<Integer> tentativas = new ArrayList<>();
        tentativas.add(0);  //sobe
        tentativas.add(1);  //desce
        tentativas.add(2);  //direita
        tentativas.add(3);  //esquerda
        Collections.shuffle(tentativas, ran);
        memoriaTentativas.put(no, tentativas);
    }
    
    private void atualizarMemoriaGeral(No posicaoNova){ memoria.add(posicaoNova); }
    private int getUltimoPasso() { return memoria.size() - 1; }
    private No getUltimaMemoria() { return memoria.get(getUltimoPasso()); }
   
    private void atualizaBecos(No novoBeco) { this.semSaida.add(novoBeco); }
    public No getPosicao() { return posicao; }
    public void setPosicao(No posicao) { this.posicao = posicao; }
    public List<No> getMemoria() { return memoria; }
}