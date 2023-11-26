import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class PosPago extends Assinante {

    private float assinatura;
    
    // chama o construtor da classe pai, passando os parâmetros cpf, nome e numero
    // atribui o valor do parâmetro assinatura ao atributo assinatura
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

    // realiza uma chamada telefônica.
    // verifica se o array de chamadas do assinante está cheio.
    // se estiver, imprime uma mensagem de erro e retorna.

    // itera sobre o array de chamadas.
    // para cada posição do array, verifica se a posição está vazia.
    // se a posição estiver vazia, cria uma nova instância da classe chamada e a adiciona à posição.
    // incrementa o número de chamadas realizadas pelo assinante.
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

    // imprime os dados do assinante
    // verifica se houve chamadas no mês especificado
    // se houver, imprime os dados das chamadas
    // imprime o valor total da fatura
    public void imprimirFatura(int mes) {

        float totalCusto = 0;
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Dados do assinante: " + this.toString());
        System.out.println("Valor da assinatura: " + this.assinatura);

        if (this.numChamadas <= 0) {
            System.out.println("Não houveram chamadas");
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
