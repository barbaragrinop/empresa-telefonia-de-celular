import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Recarga {

    private GregorianCalendar data; 
    private float valor; 

    public Recarga(GregorianCalendar data, float valor) {
        // construtor da classe recarga
        // inicializa os campos data e valor com os valores fornecidos
        this.data = data;
        this.valor = valor;
    }

    public GregorianCalendar getData() {
        // retorna o campo data do objeto recarga
        // o campo data representa a data da recarga
        return data;
    }

    public float getValor() {
        // retorna o campo valor do objeto recarga
        // o campo valor representa o valor da recarga
        return valor;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // sobrescreve o método toString() padrão
        // retorna uma representação formatada do objeto recarga.
        return "Data: " + sdf.format(data) + " | Valor: R$" + valor;
    }
}
