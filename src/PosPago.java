import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        SimpleDateFormat formato = new SimpleDateFormat(
                "dd/MM/yyyy");

        if (numChamadas <= 0) {
            System.out.println("Fatura do mês " + mes + ": " + assinatura);
        } else {
            for (int i = 0; i < numChamadas; i++) {
                if (chamadas[i] != null && chamadas[i].getData().get(Calendar.MONTH) == mes) {
                    System.out.println("Duração: " + chamadas[i].getDuracao());
                    System.out.println("Custo: " + chamadas[i].getDuracao() * 1.04);
                    System.out.println("Data: " + formato.format(chamadas[i].getData().getTime()));
                    totalCusto += chamadas[i].getDuracao() * 1.04;
                }

            }

            System.out.println("Valor total da fatura: " + (totalCusto + assinatura));
            System.out.println("Dados do assinante: " + this.toString());
            System.out.println("Valor da assinatura: " +this.assinatura);
        }

    }
}
