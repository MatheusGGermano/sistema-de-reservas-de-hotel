import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static ArrayList<Reserva> listaDeReservas = new ArrayList<>();
    public static int reservasMaximas = 10;

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

    public static void novaReserva(Scanner sc){

        if(listaDeReservas.size() > reservasMaximas){
            System.out.println("Limite de reservas atingido!");
            return;
        }

        double valorReserva = 0;
        String quarto = "";

        System.out.print("Insira o nome do hóspede:");
        String nome = sc.nextLine();
        System.out.println();

        System.out.println("Selecione o quarto que deseja reservar:");
        System.out.println("1. Suíte Padrão R$ 350,00 por noite");
        System.out.println("2. Suíte de Luxo R$ 750,00 por noite");
        System.out.println("3. Suíte Presidencial R$ 2.500,00 por noite");
        int quartoSelecionado = sc.nextInt();
        sc.nextLine();
        System.out.println();

        System.out.println("Quantos dias deseja passar na suíte selecionada?");
        int estadia = sc.nextInt();


        System.out.println();
        System.out.println("Resumo da reserva:");
        System.out.printf("Nome do hóspede: %s.\n", nome);
        if (quartoSelecionado == 1){
            quarto = "Suíte Padrão.";
            System.out.printf("Quarto selecionado: %s\n", quarto);
            valorReserva = 350 * estadia;
        }else if (quartoSelecionado == 2){
            quarto = "Suíte de Luxo.";
            System.out.printf("Quarto selecionado: %s\n", quarto);
            valorReserva = 750 * estadia;
        }else if (quartoSelecionado == 3){
            quarto = "Suíte Presidencial.";
            System.out.printf("Quarto selecionado: %s\n", quarto);
            valorReserva = 2500 * estadia;
        }
        System.out.printf("Duração da estadia: %d dias.\n", estadia);
        System.out.printf("Valor total da reserva: R$%g reais.\n", valorReserva);
        System.out.println("Deseja confirmar a reserva? 1.Sim  2.Não");
        int confirmarReserva = sc.nextInt();

        if (confirmarReserva == 1){
            listaDeReservas.add(new Reserva(nome,quarto,estadia,valorReserva));
            System.out.println("Reserva confirmada!");
        }else if (confirmarReserva == 2){
            System.out.println("Reserva Cancelada.");
        }

    }

    public static void consultarReservas(){
        if(listaDeReservas.isEmpty()){
            System.out.println("Não há reservas no momento.");
            return;
        }

        for (Reserva listaDeReserva : listaDeReservas) {
            System.out.println();
            System.out.println(listaDeReserva);
        }

    }

    public static void consultarPorHospede(Scanner sc) {
        if (listaDeReservas.isEmpty()) {
            System.out.println("Não há reservas no momento.");
            return;
        }

        System.out.print("Digite o nome do hóspede para listar suas reservas: ");
        String hospede = sc.nextLine();

        for (Reserva listaDeReserva : listaDeReservas) {
            if (listaDeReserva.getHospede().equals(hospede)) {
                System.out.println();
                System.out.println(listaDeReserva);
            }
        }

    }

    public static void consultarPorEstadia(){
        if(listaDeReservas.isEmpty()){
            System.out.println("Não há reservas no momento.");
            return;
        }

        Reserva[] numeroDeReservas = new Reserva[listaDeReservas.size()];

        for (int i = 0; i < listaDeReservas.size(); i++) {
            numeroDeReservas[i] = listaDeReservas.get(i);
        }

        for (int i = 0; i < numeroDeReservas.length; i++) {
            for (int j = i; j < numeroDeReservas.length; j++) {
                if(numeroDeReservas[i].getEstadia() > numeroDeReservas[j].getEstadia()) {
                    Reserva temp = numeroDeReservas[i];
                    numeroDeReservas[i] = numeroDeReservas[j];
                    numeroDeReservas[j] = temp;
                }

            }
        }

        for (Reserva numeroDeReserva : numeroDeReservas) {
            System.out.println();
            System.out.println(numeroDeReserva);
        }

    }

}