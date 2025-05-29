package pacote;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        boolean escapou = false;
        int grafosGerados = 0;

        while (!escapou) {
            System.out.print("\tGerando novo tabuleiro\n");
            Grafo labirinto = new Grafo(10);
            grafosGerados++;
            Player cobrinha = new Player(labirinto.getInicio());
            No fim = labirinto.getFim();
            
            labirinto.diagrama(cobrinha);

            boolean encontrou = false;

            while (!cobrinha.getPosicao().equals(fim)) {
                Thread.sleep(400);
                System.out.printf("\t\t\t\t\t\t\t\t\tTabuleiros gerados: %d\n", grafosGerados);

                if (!cobrinha.andar(labirinto.getTabuleiro())) {
                    if (!cobrinha.voltar()) {
                        break;
                    }
                }
                labirinto.diagrama(cobrinha);
            }

            if (cobrinha.getPosicao().equals(fim)) {
                System.out.println("\n\t\t\t\t\t\t\t\t\tEscapou!");
                escapou = true;
            }
        }
    }
}
