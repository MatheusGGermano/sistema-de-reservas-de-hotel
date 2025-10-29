public class Reserva {

    private String hospede;
    private String quarto;
    private int estadia;
    private double valor;

    public Reserva(String nome, String quarto, int estadia, double valor){
        this.hospede = nome;
        this.quarto = quarto;
        this.estadia = estadia;
        this.valor = valor;
    }

    public String getHospede() {
        return hospede;
    }

    public String getQuarto() {
        return quarto;
    }

    public int getEstadia() {
        return estadia;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Resumo da estadia:" +
                "\nHospéde: " + hospede +
                "\nQuarto: " + quarto +
                "\nEstadia: " + estadia + " dias." +
                "\nValor: " + valor + " reais.";
    }
}
