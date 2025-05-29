package pacote;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        boolean escapou = false;

        while (!escapou) {
            System.out.println("\n\t\t\t\t\t\t\t\t\tGerando novo tabuleiro");
            Grafo labirinto = new Grafo(10);
            Player cobrinha = new Player(labirinto.getInicio());
            No fim = labirinto.getFim();
            
            labirinto.diagrama(cobrinha);

            boolean encontrou = false;

            while (!cobrinha.getPosicao().equals(fim)) {
                Thread.sleep(400);

                if (!cobrinha.andar(labirinto.getTabuleiro())) {
                    if (!cobrinha.voltar()) {
                        break;
                    }
                }
                labirinto.diagrama(cobrinha);
            }

            if (cobrinha.getPosicao().equals(fim)) {
                System.out.println("\n\t\t\t\t\t\t\t\t\tEscapou!");
                System.out.println("Caminho percorrido:");
                for (No passo : cobrinha.getMemoria()) {
                    System.out.println("(" + passo.getLinha() + ", " + passo.getColuna() + ")");
                }
                escapou = true;
            }
        }
    }
}
