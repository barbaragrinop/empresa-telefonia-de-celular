import java.util.Scanner;

public class Telefonia{

    //atributos
    private static int numPrePagos = 0, numPosPagos = 0;

    //funções/metodos
  private static void cadastrarAssinante(){
    Scanner entrada = new Scanner(System.in);
    String nm;
    long cpf;
    int opc, num;

    do{
      System.out.println("\nTipos de assinatura");
      System.out.println("1- Pré-pago \n2- Pós-pago");
      System.out.print("Opção: ");
      opc = entrada.nextInt(); 
    } while( opc != 1 && opc != 2);

    System.out.print("\nNome do assinante: ");
    nm = entrada.next();
    System.out.print("CPF do assinante: ");
    cpf = entrada.nextLong();
    System.out.print("Número do assinante: ");
    nm = entrada.next();

    if(opc == 2){
        float ass;
        System.out.print("Valor da assinatura: ");
        ass = entrada.nextFloat();
      
        //Assinante novo = new PosPago(cpf, nm, num, ass);
        //PosPago novo = new PosPago(cpf, nm, num, ass);
        numPosPagos++;
    }
    else{
      //Assinante novo = new PrePago(cpf, nm, num);
      //PrePago novo = new PrePago(cpf, nm, num);
      numPrePagos++;
    }

  }
  private static void listarAssinantes(){

  }
  private static void fazerChamada(){

  }
  private static void fazerRecarga(){

  }
  private static void imprimirFatura(){

  }

    //menu
  public static void main(String[] args){
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