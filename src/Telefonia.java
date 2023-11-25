import java.util.Scanner;
import java.util.GregorianCalendar;

public class Telefonia {
  Scanner entrada = new Scanner(System.in);

  // atributos
  private PrePago[] prePagos; // representa os assinantes pré-pagos cadastrados
  private PosPago[] posPagos; // representa os assinantes pós-pagos cadastrados
  private int numPrePagos, numPosPagos; // representam a quantidade de assinantes pré-pagos e pós-pagos

  // construtor -> instanciar os vetores prePagos e posPagos
  public Telefonia() {
    this.prePagos = new PrePago[10];
    this.posPagos = new PosPago[10];
    this.numPrePagos = 0;
    this.numPosPagos = 0;
  }

  // funções/metodos:

  // Cadastrar assinante: o sistema deverá solicitar o tipo de assinante, pré-pago
  // ou pós-pago. Depois solicitar os dados do assinante específico;
  private void cadastrarAssinante() {
    String nome;
    long cpf;
    int opcao, numero;

    // solicitar tipo de assinante
    do {
      System.out.println("\nTipos de assinatura");
      System.out.println("1- Pré-pago \n2- Pós-pago");
      System.out.print("Opção: ");
      opcao = entrada.nextInt();
    } while (opcao != 1 && opcao != 2); // repete até que o usuário insira um valor válido

    // conferir se é possível cadastrar o tipo de assinante solicitado
    if (opcao == 1 && numPrePagos >= prePagos.length) {
      System.out.println("Não há mais espaço para cadastro de assinantes pré pagos!"); // exibir mensagem caso não seja
                                                                                       // tenha mais espaço no vetor
    } else if (opcao == 2 && numPosPagos >= posPagos.length) {
      System.out.println("Não há mais espaço para cadastro de assinantes pós pagos!");
    } else {
      // solicitar os dados do assinante, caso seja possível cadastrar
      System.out.print("\nNome do assinante: ");
      nome = entrada.next();
      System.out.print("CPF do assinante: ");
      cpf = entrada.nextLong();
      System.out.print("Número de telefone do assinante: ");
      numero = entrada.nextInt();

      if (opcao == 1) { // se opcao == prePago...
        // armazenar um objeto do tipo apropriado no vetor correspondente
        prePagos[numPrePagos] = new PrePago(cpf, nome, numero);

        // incrementar o número de assinantes cadastrados deste tipo
        this.numPrePagos++;

        System.out.println("Cadastro realizado.\n");
      } else { 
        float assinatura;
        System.out.print("Valor da assinatura: ");
        assinatura = entrada.nextFloat();

        // armazenar um objeto do tipo apropriado no vetor correspondente
        posPagos[numPosPagos] = new PosPago(cpf, nome, numero, assinatura);

        // incrementar o número de assinantes cadastrados deste tipo
        this.numPosPagos++;

        System.out.println("Cadastro realizado.\n");
      }
    }
  }

  // Listar assinantes: o sistema deverá listar os dados de todos os assinantes
  // pré-pagos e pós-pagos cadastrados
  private void listarAssinantes() {
    // lista de assinantes pre pagos, caso exista algum cadastrado
    if (this.numPrePagos > 0) {
      System.out.println("Assinantes do tipo pré-pago: ");

      // percorrer o vetor de assinantes pre-pagos
      for (int i = 0; i < this.numPrePagos; i++) {
        // exibir metodo toString do objeto prePago
        System.out.println((i + 1) + " - " + this.prePagos[i].toString() + ";\n");
      }
    } else {
      System.out.println("Não há assinantes pré-pago cadastrados.\n");
    }

    // lista de assinantes pos pagos, caso exista algum cadastrado
    if (this.numPosPagos > 0) {
      System.out.println("\nAssinantes do tipo pos-pago: ");

      // percorrer o vetor de assinantes pos-pagos
      for (int i = 0; i < this.numPosPagos; i++) {
        // exibir método toString do objeto posPago
        System.out.println((i + 1) + " - " + this.posPagos[i].toString() + ";\n");
      }
    } else {
      System.out.println("Não há assinantes pós-pago cadastrados.\n");
    }
  }

  // Fazer chamada: o sistema deve solicitar o tipo de assinante e seu CPF. Caso o
  // assinante seja localizado,o sistema deve solicitar duração e data da chamada
  // e registrar a chamada para o assinante através do método fazerChamada() de
  // Pre Pago ou PosPago. Se o assinante não for localizado, exibir mensagem
  private void fazerChamada() {
    GregorianCalendar data = new GregorianCalendar();
    int opcao, duracao;
    long cpf;

    System.out.println("\nTipos de assinatura");
    System.out.println("1- Pré-pago \n2- Pós-pago");

    // solicitar tipo de assinante
    do {
      System.out.print("Opção: ");
      opcao = entrada.nextInt();
    } while (opcao != 1 && opcao != 2); // repete até que o usuário insira um valor válido

    System.out.println("\nCPF do assinante: ");
    cpf = entrada.nextLong();
    System.out.println("Duração da chamada: ");
    duracao = entrada.nextInt();
    System.out.println("Informe a data da chamada");
    data = retornaData();

    // localizar assinante
    if (opcao == 1 && this.localizarPrePago(cpf) != null) {

      // fazer chamada pre-paga
      PrePago localizado = this.localizarPrePago(cpf);
      System.out.println(localizado.fazerChamada(data, duracao));

    } else if (opcao == 2 && this.localizarPosPago(cpf) != null) {

      // fazer chamada pos-paga
      PosPago localizado = this.localizarPosPago(cpf);
      localizado.fazerChamada(data, duracao);

    } else { // se nao encontrar...
      // ...exibir mensagem apropriada
      System.out.println("Assinante com cpf '" + cpf + "' não localizado no sistema!");
    }
  }

  // Fazer recarga: o sistema deverá solicitar o CPF de um assinante pré-pago,
  // caso assinante seja localizado através do método localizarPrePago(), o
  // sistema deve solicitar o valor e a data da recarga e registrar a recarga
  // através do método recarregar() da classe PrePago. Se o assinante não for
  // localizado, exibir uma mensagem apropriada;
  private void fazerRecarga() {
    float valor;
    long cpf;

    // solicitar cpf
    System.out.println("\nCPF do assinante pré-pago: ");
    cpf = entrada.nextLong();

    // localizar assinante
    if (this.localizarPrePago(cpf) != null) {
      // solicitar valor da recarga
      System.out.println("Valor da recarga: ");
      valor = entrada.nextFloat();

      // fazer chamada pre-paga
      PrePago localizado = this.localizarPrePago(cpf);
      //localizado.recarregar(data, valor);
    } else { // se nao encontrar...
      // ...exibir mensagem apropriada
      System.out.println("Assinante pré-pago com cpf '" + cpf + "' não localizado no sistema!");
    }
  }

  // localizar PrePago: devolve o assinante do tipo pré-pago registrado no vetor
  // prePagos que possuir o CPF igual ao fornecido como argumento Caso o assinante
  // não seja localizado, o método devolve null;
  private PrePago localizarPrePago(long cpf) {

    for (int i = 0; i < this.numPrePagos; i++) {
      if (this.prePagos[i].getCpf() == cpf) {
        return this.prePagos[i];
      }
    }
    return null;

  }

  // localizar PosPago: devolve o assinante do tipo pré-pago registrado no vetor
  // posPagos que possuir o CPF igual ao fornecido como argumento Caso o assinante
  // não seja localizado, o método devolve null;
  private PosPago localizarPosPago(long cpf) {
    for (int i = 0; i < this.numPosPagos; i++) {
      if (this.posPagos[i].getCpf() == cpf) {
        return this.posPagos[i];
      }
    }
    return null;

  }

  // Imprimir faturas: o sistema deverá solicitar o mês e imprimir todas as
  // faturas dos assinantes pré-pagos e pós-pagos;
  private void imprimirFaturas() {
    int mes;

    // exibir os meses com seus numeros correspondentes
    System.out.println("1 - Janeiro");
    System.out.println("2 - Fevereiro");
    System.out.println("3 - Março");
    System.out.println("4 - Abril");
    System.out.println("5 - Maio");
    System.out.println("6 - Junho");
    System.out.println("7 - Julho");
    System.out.println("8 - Agosto");
    System.out.println("9 - Setembro");
    System.out.println("10 - Outubro");
    System.out.println("11 - Novembro");
    System.out.println("12 - Dezembro\n");

    // solicitar o mes desejado...
    do {
      System.out.print("Mês da fatura desejada: ");
      mes = entrada.nextInt();
    } while (mes < 1 || mes > 12); // ...enquanto a resposta nao for valida

    // pre pagos
    System.out.println("\nAssinantes pré-pago: ");

    if (this.numPrePagos > 0) {
      // percorrer o vetor de assinantes pre-pagos
      for (int i = 0; i < this.numPrePagos; i++) {
        // exibir metodo imprimirFatura dos assinantes prePagos no mes inserido
        this.prePagos[i].imprimirFatura(mes);
        System.out.println("\n");
      }
    } else {
      System.out.println("Não há assinantes pré-pago cadastros.\n");
    }

    // pos pagos
    System.out.println("\nAssinantes pós-pago: ");

    if (numPosPagos > 0) {
      // percorrer o vetor de assinantes pos-pagos
      for (int i = 0; i < numPosPagos; i++) {
        // exibir método imprimirFatura dos assinantes posPagos no mes inserido
        this.posPagos[i].imprimirFatura(mes);
        System.out.println("\n");
      }
    } else {
      System.out.println("Não há assinantes pós-pago cadastros.\n");
    }
  }

  // menu
  // este método deve instanciar um objeto da classe telefonia, exibir
  // repetidamente o menu de opções e invocar os métodos apropriados a partir da
  // seleção do usuário
  public static void main(String[] args) {

    // instanciar um objeto da classe telefonia
    Telefonia tel = new Telefonia();

    try (Scanner entrada = new Scanner(System.in)) {
      int op;

      // exibir repetidamene o menu de opções
      do {
        System.out.println("Menu Telefonia: \n");
        System.out.println("1- Cadastrar assinante");
        System.out.println("2- Listar assinantes");
        System.out.println("3- Fazer chamada");
        System.out.println("4- Fazer recarga");
        System.out.println("5- Imprimir faturas");
        System.out.println("6- Sair do programa \n");

        System.out.print("Opção: ");
        op = entrada.nextInt();

        // invocar o método que o usuário escolher
        switch (op) {
          case 1:
            tel.cadastrarAssinante();
            break;
          case 2:
            tel.listarAssinantes();
            break;
          case 3:
            tel.fazerChamada();
            break;
          case 4:
            tel.fazerRecarga();
            break;
          case 5:
            tel.imprimirFaturas();
            break;
          case 6:
            System.out.println("Encerrando sistema...");
            break;
          default:
            System.out.println("Opção inválida!\n");
            break;
        }

      } while (op != 6); // até usuário digitar 6
    }
  }

  public GregorianCalendar retornaData(){
    int dia, mes, ano;
    
    System.out.println("Dia: ");
    dia = entrada.nextInt();
    System.out.println("Mês: ");
    mes = entrada.nextInt();
    System.out.println("Ano: ");
    ano = entrada.nextInt();

    return new GregorianCalendar(ano, mes, dia);
  }
}