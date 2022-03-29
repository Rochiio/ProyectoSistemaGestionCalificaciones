import View.VistaPrincipal;
import View.VistaSecundaria;

public class Main {

    public static void main(String[] args){
        VistaPrincipal pantalla = new VistaPrincipal(new VistaSecundaria());
        pantalla.program();

    }
}
