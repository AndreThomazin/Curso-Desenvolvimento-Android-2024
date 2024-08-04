package devandroid.thomazin.appgaseta.apoio;

public class UtilGasEta {

    public void metodoNaoEstatico(){

    }
    public static void metodoEstatico(){

    }

    public static String calcularMelhorOpcao(double gasolina, double etanol){
        // preco da gasolina: R$ 5,12
        // preco do etanol: R$ 3,99

        // preco ideal = gasolina * 0,70 = R$ 3,548

        double precoIdeal = gasolina * 0.70;
        String mensagemDeRetorno;

        if(etanol<=precoIdeal) mensagemDeRetorno = "Abastecer com Etanol";
        else mensagemDeRetorno = "Abastecer com Gasolina";

        // se o preco do etanol for igual ou menor que o preco ideal,
        // melhor abastecer com etanol, caso contrário, a gasolina é
        // mais vantagem.

        return mensagemDeRetorno; // Abastecer com Gasolina - Abastecer com Etanol

    }

    public static String mensagem(){
        return "Qualquer mensagem...";
    }

}
