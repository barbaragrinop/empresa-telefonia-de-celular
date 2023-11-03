import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PrePago extends Assinante {
    private Chamada[] chamadas;
    private int numRecargas;
    private float creditos;

    public int totalChamadas = 10;


    public PrePago(long cpf, String nome, int numero){
        super(cpf, nome, numero);
        this.numRecargas = 0;
        this.creditos = 0;
        this.chamadas = new Chamada[totalChamadas];
    }

    public float fazerChamada(GregorianCalendar data, int duracao){
        Calendar dataAtual = Calendar.getInstance();
        Chamada c = new Chamada(data, duracao);
        int qtdChamadas = this.chamadas.length;
        float valorChamadaAtual = (float)(duracao * 1.45);


        if(data.before(dataAtual)){
            System.out.println("Data precisa ser igual ou posterior ao dia de hoje");
            return 0f;
        }

        if(valorChamadaAtual > this.creditos || qtdChamadas == totalChamadas){
            System.out.println("Chamada não pode ser realizada.");
            return 0f;
        }





        return 1.12f;
    }


    public void recarregar(GregorianCalendar data, float valor){

    }


    public void imprimirFatura(int mes){}
}