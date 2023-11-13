import java.util.List;

public class Assinante {
    private long cpf;
    private String nome;
    private int numero;
    protected int numChamadas;

    private List<Chamada> chamadas;

    public Assinante(long cpf, String nome, int numero) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
    }
    public long getCpf(){
        return this.cpf;
    }

    @Override
    public String toString() {
        return "Assinante{" +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", numero=" + numero +
                ", numChamadas=" + numChamadas +
                '}';
    }


}
