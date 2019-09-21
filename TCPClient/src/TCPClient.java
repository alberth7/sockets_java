import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

	Socket sCliente;
	Scanner entrada;
	PrintStream salida;
	String host;
	int puerto;
	String mensajeSolicitud;
	String mensajeRespuesta;
	
	
public TCPClient(String h, int p) {
	host = h;
	puerto = p;
}

public void iniciar() {
	try {
		 sCliente = new Socket(host, puerto);
		 System.out.println("Conectado a "+ sCliente.getRemoteSocketAddress());
		 salida = new PrintStream(sCliente.getOutputStream());
		 entrada = new Scanner(sCliente.getInputStream());
		 
		 Scanner leer = new Scanner(System.in);
		 System.out.println("\nIngrese su mensaje para el servidor");
		 
		 mensajeSolicitud = leer.nextLine();
		 salida.println(mensajeSolicitud);
		 
		 mensajeRespuesta = entrada.nextLine();
		 System.out.println("Respuesta del servidor:"+ mensajeRespuesta);
	} catch (Exception e) {
		e.printStackTrace();
		finalizar();
	}
	 
	 
	 
}

public void finalizar() {

	try {
		salida.close();
		entrada.close();
		sCliente.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

}
