import java.io.IOException;
import java.net.UnknownHostException;

public class MainUDPCliente {
public static void main(String[] args) throws IOException {
	UDPCliente Cliente  = new UDPCliente("127.0.0.1", 6000);
	Cliente.iniciar();
}
	
}
