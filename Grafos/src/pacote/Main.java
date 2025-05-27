package pacote;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean escapou = false;
//        Grafo g = new Grafo(10);
//        Player cobrinha = new Player(g.getInicio());
//        g.diagrama(cobrinha);

        while (!escapou) {
            Grafo labirinto = new Grafo(10);
            Player cobrinha = new Player(labirinto.getInicio());
            No fim = labirinto.getFim();

            System.out.println("Novo labirinto gerado:");
            labirinto.diagrama(cobrinha);

            boolean encontrou = false;

            while (!cobrinha.getPosicao().equals(fim)) {
                Thread.sleep(400);

                if (!cobrinha.andar(labirinto.getTabuleiro())) {
                    if (!cobrinha.voltar()) {
                        System.out.println("Gerando novo tabuleiro...\n");
                        break;
                    }
                }
                labirinto.diagrama(cobrinha);
            }

            if (cobrinha.getPosicao().equals(fim)) {
                System.out.println("Escapou!");
                System.out.println("Caminho percorrido:");
                for (No passo : cobrinha.getMemoria()) {
                    System.out.println("(" + passo.getLinha() + ", " + passo.getColuna() + ")");
                }
                escapou = true;
            }
        }
    }
}
