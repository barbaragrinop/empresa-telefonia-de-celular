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
      System.out.print("\nCPF do assinante: ");
      cpf = entrada.nextLong();

      while (!validaCPF(cpf)) {
        System.out.println("CPF INVÁLIDO! Insira um CPF válido");
        System.out.print("\nCPF do assinante: ");
        cpf = entrada.nextLong();
      }

      //validar se ja ha assinante com cpf inserido
      if ((opcao == 1 && this.localizarPrePago(cpf) == null) || (opcao == 2 && this.localizarPosPago(cpf) == null)) {

        System.out.print("Nome do assinante: ");
        nome = entrada.next();
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
      } else {
        System.out.println("\nCPF " + cpf + " já é assinante do tipo " + opcao);
      }
    }
  }

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

  private void fazerChamada() {
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

    // localizar assinante
    if (opcao == 1 && this.localizarPrePago(cpf) != null) {

      System.out.println("Duração da chamada: ");
      duracao = entrada.nextInt();
      System.out.println("Informe a data da chamada");
      GregorianCalendar dataFuncao = retornaData();

      // fazer chamada pre-paga
      PrePago localizado = this.localizarPrePago(cpf);
      localizado.fazerChamada(dataFuncao, duracao);

    } else if (opcao == 2 && this.localizarPosPago(cpf) != null) {

      System.out.println("Duração da chamada: ");
      duracao = entrada.nextInt();
      System.out.println("Informe a data da chamada");
      GregorianCalendar dataFuncao = retornaData();

      // fazer chamada pos-paga
      PosPago localizado = this.localizarPosPago(cpf);
      localizado.fazerChamada(dataFuncao, duracao);

    } else { // se nao encontrar...
      // ...exibir mensagem apropriada
      System.out.println("Assinante com cpf '" + cpf + "' não localizado no sistema!\n");
    }
  }

  private void fazerRecarga() {
    GregorianCalendar data = new GregorianCalendar();
    float valor;
    long cpf;

    // solicitar cpf
    System.out.println("\nCPF do assinante pré-pago: ");
    cpf = entrada.nextLong();

    // localizar assinante
    if (this.localizarPrePago(cpf) != null) {
      System.out.println("Informe a data da recarga");
      data = retornaData();
      do {
        System.out.println("\nValor da recarga: ");
        valor = entrada.nextFloat();
      } while (valor <= 0);

      // fazer chamada pre-paga
      PrePago localizado = this.localizarPrePago(cpf);
      localizado.recarregar(data, valor);
    } else { // se nao encontrar...
      // ...exibir mensagem apropriada
      System.out.println("Assinante pré-pago com cpf '" + cpf + "' não localizado no sistema!");
    }
  }

  private PrePago localizarPrePago(long cpf) {

    for (int i = 0; i < this.numPrePagos; i++) {
      if (this.prePagos[i].getCpf() == cpf) {
        return this.prePagos[i];
      }
    }
    return null;

  }

  private PosPago localizarPosPago(long cpf) {
    for (int i = 0; i < this.numPosPagos; i++) {
      if (this.posPagos[i].getCpf() == cpf) {
        return this.posPagos[i];
      }
    }
    return null;

  }

  private void imprimirFaturas() {
    int mes = exibirMeses();

    System.out.println("\n ====================================FATURA==================================== ");
    // pre pagos
    System.out.println("\nASSINANTES PRÉ-PAGOS: ");

    if (this.numPrePagos > 0) {
      // percorrer o vetor de assinantes pre-pagos
      for (int i = 0; i < this.numPrePagos; i++) {
        // exibir metodo imprimirFatura dos assinantes prePagos no mes inserido
        this.prePagos[i].imprimirFatura(mes);
      }
    } else {
      System.out.println("Não há assinantes pré-pago cadastros.\n");
    }

    System.out.println("\n");
    System.out.println(" ================================================================================ ");
    System.out.println("\n");

    // pos pagos
    System.out.println("ASSINANTES PÓS-PAGOS: ");

    if (numPosPagos > 0) {
      // percorrer o vetor de assinantes pos-pagos
      for (int i = 0; i < numPosPagos; i++) {
        // exibir método imprimirFatura dos assinantes posPagos no mes inserido
        this.posPagos[i].imprimirFatura(mes);
      }
    } else {
      System.out.println("Não há assinantes pós-pago cadastros.");
    }
  }

  // menu
  public static void main(String[] args) {

    // instanciar um objeto da classe telefonia
    Telefonia tel = new Telefonia();

    try (Scanner entrada = new Scanner(System.in)) {
      int op;

      // exibir repetidamene o menu de opções
      do {
        System.out.println("\n\n==========MENU==========");
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

  public GregorianCalendar retornaData() {
    int dia, mes, ano;
    boolean validade;

    do {

      // solicitar o dia até que o usuário insira um valor válido
      do {
        System.out.print("Dia (1-31): ");
        dia = entrada.nextInt();
      } while (dia < 1 || dia > 31);

      // método de exibir e solicitar mês
      mes = exibirMeses();

      // verificar se data dia/mes é válida
      if (((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) || (mes == 2 && dia > 28)) {
        validade = false;
        System.out.println("Data " + dia + "/" + mes + " inválida!");
        System.out.println("Insira a data novamente.\n");
      } else {
        validade = true;
      }
    } while (validade == false); // enquanto dia/mes nao for valido, solicitar novamente

    // solicitar ano até usuário digitar valor válido
    do {
      System.out.print("Ano com 4 dígitos: ");
      ano = entrada.nextInt();
    } while (ano < 1877 || String.valueOf(ano).length() != 4); // 1877 = ano que telefonia chegou ao br

    // instanciar objeto GregorianCalendar com valores inseridos
    GregorianCalendar calendar = new GregorianCalendar(ano, mes - 1, dia);
    return calendar;
  }

  public int exibirMeses() {
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

    int resposta;

    do {
      System.out.print("Mês (1-12): ");
      resposta = entrada.nextInt();
    } while (resposta < 1 || resposta > 12); // ...enquanto a resposta nao for valida

    return resposta;
  }

  public static boolean validaCPF(long cpf) {
    String cpfStr = String.valueOf(cpf);

    // Verifica se o CPF tem 11 dígitos
    if (cpfStr.length() != 11) {
      return false;
    }

    // Verifica se todos os dígitos são iguais (caso contrário, o CPF é inválido)
    if (cpfStr.chars().allMatch(c -> c == cpfStr.charAt(0))) {
      return false;
    }

    // Calcula o primeiro dígito verificador
    int digito1 = calculaDigito(cpfStr.substring(0, 9), 10);
    // Calcula o segundo dígito verificador
    int digito2 = calculaDigito(cpfStr.substring(0, 9) + digito1, 11);

    // Verifica se os dígitos calculados são iguais aos dígitos informados no CPF
    return cpfStr.endsWith(String.valueOf(digito1) + String.valueOf(digito2));
  }

  private static int calculaDigito(String str, int peso) {
    int total = 0;
    for (int i = 0; i < str.length(); i++) {
      total += Integer.parseInt(String.valueOf(str.charAt(i))) * peso--;
    }
    int resto = total % 11;
    return (resto < 2) ? 0 : 11 - resto;
  }
}