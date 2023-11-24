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

        
        // for
        
        this.creditos -= duracao * 1.45;

        this.numChamadas++;
        






        



        // if(this,)
        return 1.12f;
    }


    public void recarregar(GregorianCalendar data, float valor){

    }


    public void imprimirFatura(int mes){}
}