public class PosPago{
    public class PosPago extends Assinante{

        private float assinatura; 
    
        public PosPago(long cpf, String nome, int numero, float assinatura) {
            super(cpf, nome, numero); 
            this.assinatura = assinatura;
        }

        public void getAssinatura() {
            return assinatura;
        }
    
        public void setAssinatura(double assinatura) {
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
