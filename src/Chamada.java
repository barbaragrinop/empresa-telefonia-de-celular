import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Chamada {
    private GregorianCalendar data;
    private int duracao;

    public Chamada(GregorianCalendar data, int duracao) {
        this.data = data;
        this.duracao = duracao;
    }

    public GregorianCalendar getData(){
        return data;
    }

    public int getDuracao(){
        return duracao;
    }

    @Override
    public String toString(){
        //Cria um objeto da classe SimpleDateFormat para formatar a data no padrão "dia/mes/ano"
        SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
        //Método format é chamado e formata a data como uma string no formato especificado
        String dataFormatada = formatacao.format(data);

        return "Chamada{" +
                "Data=" + dataFormatada +
                ", Duracao=" + duracao + "}";
                
    }


}
