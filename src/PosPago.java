import java.util.GregorianCalendar;

public class PosPago extends Assinante{

        private float assinatura; 
    
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
        
        public void fazerChamada(GregorianCalendar data, int duracao) {
            if (numChamadas < chamadas.length) {
                chamadas[numChamadas] = new Chamada(data, duracao);
                numChamadas++;
            } else {
                System.out.println("Não há espaço para mais chamadas.");
            }
        }

        public void imprimirFatura(int mes) {

            System.out.println("CPF: " + this.cpf);
            System.out.println("Nome: " + this.nome);
            System.out.println("Número de telefone: " + this.numeroTelefone);
          
            for (Chamada chamada : this.chamadas) {
              System.out.println("Data: " + chamada.getData());
              System.out.println("Duração: " + chamada.getDuracao());
              System.out.println("Valor: " + chamada.getValor());
            }
          
            double valorTotal = this.assinatura + this.custoLigacoes;
            System.out.println("Valor total: " + valorTotal);
          }
        }
    