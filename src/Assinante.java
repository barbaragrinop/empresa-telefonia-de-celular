public class Assinante {
    private long cpf;
    private String nome;
    private int numero;
    protected int numChamadas;
    protected Chamada[] chamadas;

    public Assinante(long cpf, String nome, int numero) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.chamadas = new Chamada[10];
        this.numChamadas = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }


    public long getCpf(){
        return cpf;
    }

    @Override
    public String toString() {
        return "Assinante{" +
                "CPF=" + cpf +
                ", Nome='" + nome + '\'' +
                ", Numero=" + numero +
                ", Numero de Chamadas=" + numChamadas +
                '}';
    }


}
