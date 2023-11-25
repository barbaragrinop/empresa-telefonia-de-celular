import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PrePago extends Assinante {
    private Chamada[] chamadas;
    private int numRecargas;
    private float creditos;
    public int totalChamadas = 0;
    private Recarga[] recargas;


    public PrePago(long cpf, String nome, int numero){
        super(cpf, nome, numero);
        this.numRecargas = 0;
        this.chamadas = new Chamada[totalChamadas];
        this.recargas = new Recarga[this.numRecargas];
    }

    public float fazerChamada(GregorianCalendar data, int duracao){
        Calendar dataAtual = Calendar.getInstance();


        if(data.before(dataAtual)){ //verifica a data que foi inserida por parametro
            System.out.println("Data precisa ser igual ou posterior ao dia de hoje");
            return 0f;
        }

        if (this.numChamadas >= this.chamadas.length) { //verifica se ainda tem espaço para fazer chamada
            // Não há espaço
            System.out.println("Não há espaço para registrar a chamada.");
            return 0f;
        }

        if(duracao * 1.45 > this.creditos){
            System.out.println("Saldo insuficiente para fazer chamada");
            return 0f;
        }

        for(int i = 0; i <= this.numChamadas; i++) {
            if(this.chamadas[i] == null){
                Chamada chamada = new Chamada(data, duracao);
                this.chamadas[i] = chamada;
                this.creditos -= duracao * 1.45f;
                System.out.println("Chamada realizada com sucesso");
            }
        }

        this.numChamadas++;

        return 1f;
    }


    public void recarregar(GregorianCalendar data, float valor){
        if(this.recargas.length >= numRecargas){ //verifica se tem espaço no vetor de chamadas
            System.out.println("Não há espaço para novas recargas.");
        } else {
            for(int i = 0; i <= numRecargas; i++){ //percorre as chamdas que tem até agora
               if(recargas[i] == null){ //a primeira que for nula
                   Recarga recarga = new Recarga(data, valor);
                   this.recargas[i] = recarga;
                   this.creditos+=valor;
                   System.out.println("Valor adicionado aos créditos com sucesso.");
               }
            }
            this.numRecargas++; //incrementa as recargas;
       }
    }


    public void imprimirFatura(int mes){
        float valorTotalRecargas=0, valorTotalChamadas=0;
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Dados do assinante: " + this.toString());

        if(this.numChamadas <= 0){
            System.out.println("Não houveram chamadas");
        } else {
            for(int c = 0; c <= this.numChamadas; c++){
                if (this.chamadas[c] != null && this.chamadas[c].getData().get(GregorianCalendar.MONTH) == mes) {
                    System.out.println("Data da chamada: " + dataFormato.format(this.chamadas[c].getData().getTime()));
                    System.out.println("Duração: " + this.chamadas[c].getDuracao());
                    System.out.println("Custo: " + this.chamadas[c].getDuracao() * 1.45);
                    valorTotalChamadas=+this.chamadas[c].getDuracao()  * 1.45f;
                }
            }
            System.out.println("Valor total das chamadas no mês de " + pegaNomeMesPorNumero(mes) + ": R$" + valorTotalChamadas);
        }

        if(this.numRecargas <= 0){
            System.out.println("Não houveram recargas");
        } else {
            for(int r = 0; r <= this.numRecargas; r++){
                if (this.recargas[r] != null && this.recargas[r].getData().get(GregorianCalendar.MONTH) == mes) {
                    System.out.println("Data da recarga: " + dataFormato.format(this.recargas[r].getData().getTime()));
                    System.out.println("Valor: " + this.recargas[r].getValor());
                    valorTotalRecargas=+this.recargas[r].getValor();
                }
            }
            System.out.println("Valor total de recargas no mês de " + pegaNomeMesPorNumero(mes) + ": R$" + valorTotalRecargas);
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






