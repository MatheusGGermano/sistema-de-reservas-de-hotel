import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static ArrayList<Reserva> listaDeReservas = new ArrayList<>();
    public static int reservasMaximas = 10;


    //Menu principal
    public static void main() {
        Scanner sc = new Scanner(System.in);
        int escolha = 0;

        System.out.println("            Bem-vindo ao sistema de reserva de quarto!");

        while (true) {

            System.out.println();
            System.out.println("Qual operação deseja realizar?");
            System.out.println();
            System.out.println("1. Nova reserva");
            System.out.println("2. Listar reservas");
            System.out.println("3. Busca por nome do hóspede");
            System.out.println("4. Listar reservas por tempo de estadia");
            System.out.println("5. Sair do sistema");
            escolha = sc.nextInt();
            sc.nextLine();

            //Switch para executar uma ação de acordo com a escolha do usuário, reinicia o menu caso a escolha
            //não esteja na lista
            switch (escolha) {
                case 1:
                    novaReserva(sc);
                    break;
                case 2:
                    consultarReservas();
                    break;
                case 3:
                    consultarPorHospede(sc);
                    break;
                case 4:
                    consultarPorEstadia();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }


    //Método para cadastrar reserva, verifica o limite de reservas,e caso esteja dentro do limite
    //coleta os dados do hóspede.
    public static void novaReserva(Scanner sc){

        //Verificação do limite de reservas
        if(listaDeReservas.size() > reservasMaximas){
            System.out.println("Limite de reservas atingido!");
            return;
        }

        //Inicialização das váriaveis que armazenam tipo do quarto e valor, usadas para adicionar os valores
        //ao vetor de reservas
        double valorReserva = 0;
        String quarto = "";


        //Exibição do menu de reserva
        System.out.print("Insira o nome do hóspede:");
        String nome = sc.nextLine();
        System.out.println();
        if (nome == null){
            System.out.println("Nome não pode ser vazio! Reserva cancelada.");
        }

        System.out.println("Selecione o quarto que deseja reservar:");
        System.out.println("1. Suíte Padrão R$ 350,00 por noite");
        System.out.println("2. Suíte de Luxo R$ 750,00 por noite");
        System.out.println("3. Suíte Presidencial R$ 2.500,00 por noite");
        int quartoSelecionado = sc.nextInt();
        sc.nextLine();
        System.out.println();
        if(quartoSelecionado <= 0 || quartoSelecionado > 3){
            System.out.println("Opção inválida! Reserva cancelada.");
            return;
        }

        System.out.println("Quantos dias deseja passar na suíte selecionada?");
        int estadia = sc.nextInt();
        if (estadia <= 0){
            System.out.println("Quantidade inválida de dias.");
        }

        switch(quartoSelecionado){
            case 1:
                quarto = "Suíte Padrão.";
                valorReserva = 350 * estadia;
                break;

            case 2:
                quarto = "Suíte de Luxo.";
                valorReserva = 750 * estadia;
                break;

            case 3:
                quarto = "Suíte Presidencial.";
                valorReserva = 2500 * estadia;
                break;
        }

        //Resumo das informações da reserva
        System.out.println();
        System.out.println("Resumo da reserva:");
        System.out.printf("Nome do hóspede: %s.\n", nome);
        System.out.printf("Quarto selecionado: %s\n", quarto);
        System.out.printf("Duração da estadia: %d dias.\n", estadia);
        System.out.printf("Valor total da reserva: R$%g reais.\n", valorReserva);
        System.out.println("Deseja confirmar a reserva? 1.Sim  2.Não");
        int confirmarReserva = sc.nextInt();


        //Etapa de confirmação da reserva, cadastra a reserva ou retorna ao menu inicial
        //de acordo com a escolha do usuário e exibe uma mensagem.
        if (confirmarReserva == 1){
            listaDeReservas.add(new Reserva(nome,quarto,estadia,valorReserva));
            System.out.println("Reserva confirmada!");
        }else if (confirmarReserva == 2){
            System.out.println("Reserva Cancelada.");
        }

    }


    //Método para consulta de todas as reservas cadastradas
    public static void consultarReservas(){

        //Verificação para saber se há reservas cadastradas, caso não haja retorna ao menu
        //e exibe uma mensagem
        if(listaDeReservas.isEmpty()){
            System.out.println("Não há reservas no momento.");
            return;
        }

        //Loop que itera pelo vetor de reservas e as imprime na tela
        for (Reserva listaDeReserva : listaDeReservas) {
            System.out.println();
            System.out.println(listaDeReserva);
        }

    }

    //Método para consulta de reservas filtrando por nome do hóspede
    public static void consultarPorHospede(Scanner sc) {

        //Verificação para saber se há reservas cadastradas, caso não haja retorna ao menu
        //e exibe uma mensagem
        if (listaDeReservas.isEmpty()) {
            System.out.println("Não há reservas no momento.");
            return;
        }

        System.out.print("Digite o nome do hóspede para listar suas reservas: ");
        String hospede = sc.nextLine();

        //Loop que verifica se o nome digitado não é vazio e se ele possui alguma reserva
        if(hospede.isBlank()){
            System.out.println();
            System.out.println("Nome consultado não pode ser vazio!");
            return;
        }else if(!listaDeReservas.contains(hospede)){
            System.out.println();
            System.out.println("O hóspede solicitado não possui reservas.");
        }

        for (Reserva listaDeReserva : listaDeReservas) {
            if (hospede.equals(listaDeReserva.getHospede().toLowerCase())) {
                System.out.println();
                System.out.println(listaDeReserva);
            }
        }

    }

    //Método para consulta de reservas filtrando por quantidade de dias
    public static void consultarPorEstadia(){

        //Verificação para saber se há reservas cadastradas, caso não haja retorna ao menu
        //e exibe uma mensagem
        if(listaDeReservas.isEmpty()){
            System.out.println();
            System.out.println("Não há reservas no momento.");
            return;
        }

        //Criação de um vetor para armazenamento temporário das reservas e ordenação
        Reserva[] numeroDeReservas = new Reserva[listaDeReservas.size()];

        //Loop que insere os valores do vetor de reservas no vetor de ordenação
        for (int i = 0; i < listaDeReservas.size(); i++) {
            numeroDeReservas[i] = listaDeReservas.get(i);
        }

        //Loop de ordenação por dias utilizando Bubble Sort
        for (int i = 0; i < numeroDeReservas.length; i++) {
            for (int j = i; j < numeroDeReservas.length; j++) {
                if(numeroDeReservas[i].getEstadia() > numeroDeReservas[j].getEstadia()) {
                    Reserva temp = numeroDeReservas[i];
                    numeroDeReservas[i] = numeroDeReservas[j];
                    numeroDeReservas[j] = temp;
                }

            }
        }

        //Exibição das reservas ordenadas
        for (Reserva numeroDeReserva : numeroDeReservas) {
            System.out.println();
            System.out.println(numeroDeReserva);
        }

    }

}