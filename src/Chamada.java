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
        SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatacao.format(data);

        return "Chamada{" +
                "Data=" + dataFormatada +
                ", Duracao=" + duracao + "}";
                
    }


}
