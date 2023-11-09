import java.util.Scanner;

public class Telefonia{

  //atributos
  private static PrePago prePagos;
  private static PosPago posPagos;
  private static int numPrePagos, numPosPagos;

  //construtor -> instancia os vetores
  public Telefonia(){
    this.prePagos = new PrePago[10];
    this.posPagos = new PosPago[10];
    this.numPrePagos = 0;
    this.numPosPagos = 0;
  }

  //funções/metodos
  private void cadastrarAssinante(){
    Scanner entrada = new Scanner(System.in);
    String nome;
    long cpf;
    int opcao, numero;

    do{
      System.out.println("\nTipos de assinatura");
      System.out.println("1- Pré-pago \n2- Pós-pago");
      System.out.print("Opção: ");
      opcao = entrada.nextInt(); 
    } while( opcao != 1 && opcao != 2);

    if(opcao == 1 && numPrePagos >= prePagos.length){
      System.out.println("Não há mais espaço para cadastro de assinantes pré pagos!");
      break;
    }
    if(opcao == 2 && numPosPagos >= posPagos.length){
      System.out.println("Não há mais espaço para cadastro de assinantes pós pagos!");
      break;
    }
    
    System.out.print("\nNome do assinante: ");
    nome = entrada.next();
    System.out.print("CPF do assinante: ");
    cpf = entrada.nextLong();
    System.out.print("Número do assinante: ");
    numero = entrada.next();

    if(opcao == 2){
      float assinatura;
      System.out.print("Valor da assinatura: ");
      assinatura = entrada.nextFloat();

      posPagos[numPosPagos] = new PosPago(nome, cpf, numero, assinatura);
      numPosPagos++;
    }
    else{
      prePagos[numPrePagos] = new PrePago(nome, cpf, numero);
      numPrePagos++;
    }
  }

  private void listarAssinantes()
  {
    //assinantes pre pagos
    if(numPrePagos > 0){
      System.out.println("Assinantes do tipo pré-pago: ");
      for (int i = 0; i < numPrePagos; i++){
        System.out.println((i+1) + " - " + prePagos[i].toString() + ";");
      }
    } 

    //assinantes pos pagos
    if(numPosPagos > 0){
      System.out.println("\nAssinantes do tipo pos-pago: ");
      for (int i = 0; i < numPosPagos; i++){
        System.out.println((i+1) + " - " + posPagos[i].toString() + ";");
      }
    }
  }

  private void fazerChamada(){

  }

  private void fazerRecarga(){

  }

  private void imprimirFatura(){

  }

    //menu
  public static void main(String[] args){
    
    public tel = new Telefonia();
    
    Scanner entrada = new Scanner(System.in);

    int op;
    do{
      System.out.println("Menu Telefonia: \n");
      System.out.println("1- Cadastrar assinante");
      System.out.println("2- Listar assinantes");
      System.out.println("3- Fazer chamada");
      System.out.println("4- Fazer recarga");
      System.out.println("5- Imprimir fatura");
      System.out.println("6- Sair do programa \n");

      System.out.print("Opção: ");
      op = entrada.nextInt();

      switch(op){
        case 1: cadastrarAssinante();
            break;
        case 2: listarAssinantes();
            break;
        case 3: fazerChamada();
            break;
        case 4: fazerRecarga();
            break;
        case 5: imprimirFatura();
            break;
        case 6:
            break;
        default:
            System.out.println("Opção inválida!");
            break;
      }

    }while(op != 6);

  }

}