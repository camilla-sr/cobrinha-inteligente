package pacote;

import java.util.Random;

public class Grafo {        //esse e o nosso labirinto
    private No[][] tabuleiro;
    private No inicio;
    private No fim;

    public Grafo(int tamanho) {
        this.tabuleiro = new No[tamanho][tamanho];
        iniciarNos(tamanho);
    }

//    public void iniciarNos(int tamanho){
//        Random ran = new Random();    
//        for (int i = 0; i < tamanho; i++) {
//            for (int j = 0; j < tamanho; j++) {
//                char valor = ran.nextBoolean() ? '1' : '0';    //preenche aleatoriamente com 1 ou 0
//                tabuleiro[i][j] = new No(i,j,valor);    //cria o no com o valor aleatorio e a posicao dele
//            }
//        }
//        tabuleiro[0][0].setValor('E');        //isso forca a entrada livre do labirinto
//        tabuleiro[tamanho-1][tamanho-1].setValor('C');    //e isso forca a saida livre
//        
//        this.inicio = tabuleiro[0][0];
//        this.fim = tabuleiro[tamanho-1][tamanho-1];
//        conecarParedes(tamanho);
//    }
    
    public void iniciarNos(int tamanho) {
        Random ran = new Random();

        //Inicializa todas as posicoes como paredes
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = new No(i, j, '1');
            }
        }

        // aqui a gente garante que haja pelo menos um caminho totalmente
        // valido do comeco do tabuleiro 'E' ate o final 'C'
        int i = 0;
        int j = 0;
        tabuleiro[i][j].setValor('E');  // defino o inicio da matriz, esta e a entrada

        while (i != tamanho - 1 || j != tamanho - 1) {
            // deixo o algoritmo decidir aleatoriamente o caminho percorrido
            // ate a saida do labirinto, o que vai criar o caminho valido
            if (i == tamanho - 1) {
                j++;
            } else if (j == tamanho - 1) {
                i++;
            } else if (ran.nextBoolean()) {
                i++;
            } else {
                j++;
            }
            tabuleiro[i][j].setValor('0');  // seto o valor das posicoes que serao o caminnho como 0
        }
        
        tabuleiro[tamanho - 1][tamanho - 1].setValor('C');  // seto a saida do labirinto

        // No restante da matriz que nao envolveu o caminho, preenncho aleatoriamente
        for (int x = 0; x < tamanho; x++) {
            for (int y = 0; y < tamanho; y++) {
                No no = tabuleiro[x][y];
                
                //nesse if eu garannto que o caminho valido, a entrada e saida nao serao alterados
                if (no.getValor() != '0' && no.getValor() != 'E' && no.getValor() != 'C') {
                    char valor = ran.nextBoolean() ? '1' : '0';
                    no.setValor(valor);     //seto os valores dos nos
                }
            }
        }
        this.inicio = tabuleiro[0][0];      //crio uma referencia para o inicio
        this.fim = tabuleiro[tamanho - 1][tamanho - 1];     //e uma para o final
        conectarParedes(tamanho);        //crio as arestas entre as paredes
    }

    public void conectarParedes(int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                No noAtual = tabuleiro[i][j];
                if (noAtual.isCaminho()) {
                    continue;      //ignorar os caminhos validos que serao os 0, E, C
                }
                if (i > 0) {      //isso evita estouro da matriz
                    No noCima = tabuleiro[i - 1][j];      //armazeno o tal no em variavel semantica
                    //SE o no de cima ao atual nao for uma parede
                    if (noCima.isParede()) {
                        noAtual.addParede(noCima); //crio uma aresta entre eles
                    }
                }
                if (i < tamanho - 1) {
                    No noBaixo = tabuleiro[i + 1][j];
                    if (noBaixo.isParede()) {
                        noAtual.addParede(noBaixo);
                    }
                }
                if (j > 0) {
                    No noEsquerda = tabuleiro[i][j - 1];
                    if (noEsquerda.isParede()) {
                        noAtual.addParede(noEsquerda);
                    }
                }
                if (j < tamanho - 1) {
                    No noDireita = tabuleiro[i][j + 1];
                    if (noDireita.isParede()) {
                        noAtual.addParede(noDireita);
                    }
                }
            }
        }
    }

    // com esse metodo, eu garanto que a cobrinha nao 'passe por cima' das paredes direto para uma posicao
    // valida. Como ela so pode andar para os lados que esta 'enxergando', ou seja, os nos adjacentes a posicao que esta,
    // aqui crio as arestas entre tudo que e considerado 'caminho' para permitir esse tipo de movimentacao
    public void conectarCaminhos(int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                No noAtual = tabuleiro[i][j];
                if (noAtual.isParede()) {
                    continue;      //ignorar as paredes que serao os 1
                }
                if (i > 0) {      //isso evita estouro da matriz
                    No noCima = tabuleiro[i - 1][j];      //armazeno o tal no em variavel semantica
                    //SE o no de cima ao atual nao for uma parede
                    if (noCima.isCaminho()) {
                        noAtual.addCaminho(noCima); //crio uma aresta entre eles
                    }
                }
                if (i < tamanho - 1) {
                    No noBaixo = tabuleiro[i + 1][j];
                    if (noBaixo.isCaminho()) {
                        noAtual.addCaminho(noBaixo);
                    }
                }
                if (j > 0) {
                    No noEsquerda = tabuleiro[i][j - 1];
                    if (noEsquerda.isCaminho()) {
                        noAtual.addCaminho(noEsquerda);
                    }
                }
                if (j < tamanho - 1) {
                    No noDireita = tabuleiro[i][j + 1];
                    if (noDireita.isCaminho()) {
                        noAtual.addCaminho(noDireita);
                    }
                }
            }
        }
    }

    //esse metodo vai criar um diagrama com as ligacoes que sera nosso tabuleiro+
    public void diagrama(Player cobrinha) {
        System.out.println("===============================================");
        for (int i = 0; i < tabuleiro.length; i++) {
            //aqui vao ter as ligacoes horizontais
            for (int j = 0; j < tabuleiro[i].length; j++) {
                No v = tabuleiro[i][j];

                if (cobrinha.getPosicao() == v) {
                    System.out.print("<>");
                } else {
                    System.out.print(v.getValor());     //isso vai trazer o valor que esta no no
                }

                if (j < tabuleiro[i].length - 1) {    //verifica se tem um vizinho a direita
                    No vDireita = tabuleiro[i][j + 1];

                    if (v.getParede().contains(vDireita)) {
                        System.out.print("----");
                    } else {
                        System.out.print("    ");
                    }
                }
            }
            System.out.println();

            //aqui vao ter as ligacoes verticais
            for (int j = 0; j < tabuleiro[i].length; j++) {
                No v = tabuleiro[i][j];

                if (i < tabuleiro.length - 1) {
                    No vBaixo = tabuleiro[i + 1][j];

                    if (v.getParede().contains(vBaixo)) {
                        System.out.print("|    ");
                    } else {
                        System.out.print("     ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("===============================================");
    }

    //esse metodo e puramente para debug, de forma a observar se a matriz esta sendo gerada corretamente
    public void imprimirLabirinto() {
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

    //puramente por nnao ter  necessidade de usar setters, eles nao foram criados
    public No[][] getTabuleiro() { return tabuleiro; }
    public No getInicio() { return inicio; }
    public No getFim() { return fim; }
}
