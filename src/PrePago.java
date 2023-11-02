import java.util.Date;

public class PrePago extends Assinante {
    private float creditos;
    private int numRecargas;

    public PrePago(long cpf, String nome, int numero){
        super(cpf, nome, numero);
    }

    public void recarregar(Date data, float valor){}

    public float fazerChamada(Date data, int duracao){
        return (float)1.12;
    }

    public void imprimirFatura(int mes){}
}