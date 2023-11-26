import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class PosPago extends Assinante {

    private float assinatura;

    public PosPago(long cpf, String nome, int numero, float assinatura) {
        super(cpf, nome, numero);
        this.assinatura = assinatura;
    }

    public float getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(float assinatura) {
        this.assinatura = assinatura;
    }

    public void fazerChamada(GregorianCalendar data, int duracao) {
        if (this.chamadas.length == this.numChamadas) {
            System.out.println("Não é possível realizar uma chamada.");
        } else {
            for (int i = 0; i <= numChamadas; i++) {
                if (this.chamadas[i] == null) {
                    this.chamadas[i] = new Chamada(data, duracao);
                }
            }
            this.numChamadas++;
            System.out.println("Chamada concluída com sucesso.");

        }
    }

    public void imprimirFatura(int mes) {

        float totalCusto = 0;
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Dados do assinante: " + this.toString());
        System.out.println("Valor da assinatura: " + this.assinatura);

        if (this.numChamadas <= 0) {
            System.out.println("Não houveram chamadas");
            // System.out.println("Fatura do mês " + mes + ": " + assinatura);
        } else {
            System.out.println("========== DADOS CHAMADAS ==========");
            for (int i = 0; i < this.numChamadas; i++) {
                if (this.chamadas[i] != null && this.chamadas[i].getData().get(GregorianCalendar.MONTH) == mes - 1) {
                    System.out.println("Data da chamada: " + dataFormato.format(this.chamadas[i].getData().getTime()));
                    System.out.println("Duração: " + this.chamadas[i].getDuracao() + " minutos;");
                    System.out.println("Custo: R$" + this.chamadas[i].getDuracao() * 1.45);
                    totalCusto += this.chamadas[i].getDuracao() * 1.45;
                }
            }

            System.out.println("\nValor total das chamadas: R$" + totalCusto);

            totalCusto += this.assinatura;
            System.out.println("\nValor total da fatura no mês de " + pegaNomeMesPorNumero(mes) + ": R$" + totalCusto);
        }
    }

    public String pegaNomeMesPorNumero(int numero){
            switch(numero) {
                case 1: return "Janeiro";
                case 2: return "Fevereiro";
                case 3: return "Março";
                case 4: return "Abril";
                case 5: return "Maio";
                case 6: return "Junho";
                case 7: return "Julho";
                case 8: return "Agosto";
                case 9: return "Setembro";
                case 10: return "Outubro";
                case 11: return "Novembro";
                case 12: return "Dezembro";
                default: return "Mês inválido";
            }
        }
}
