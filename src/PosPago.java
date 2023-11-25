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
        if (numChamadas < chamadas.length) {
            chamadas[numChamadas] = new Chamada(data, duracao);
            numChamadas++;
        } else {
            System.out.println("Não há espaço para mais chamadas.");
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
            
        }

    }
}
