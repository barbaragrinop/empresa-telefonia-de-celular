public class PosPago{
    public class PosPago extends Assinante{
    
        system.out.println("oi");
        private float assinatura; 
    
        public PosPago(long cpf, String nome, int numero, float assinatura) {
            super(cpf, nome, numero); 
            this.assinatura = assinatura;