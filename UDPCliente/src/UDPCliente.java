import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPCliente {

	int puerto;
	InetAddress servidorDestino;
	DatagramSocket socketUDP;
	DatagramPacket paqueteRecibido;
	DatagramPacket paqueteEnviar;
	
public UDPCliente(String h ,int p) throws UnknownHostException {
	servidorDestino = InetAddress.getByName(h);
	puerto = p;
}

public void iniciar() throws IOException {
	
	
	Scanner leer = new Scanner(System.in);
	
	socketUDP = new DatagramSocket();
	
	System.out.println("\n Mensaje que dese aenviar:");
	String mensajeEnviar= leer.nextLine();
	byte mensajeByte[] = new byte[1024];
	mensajeByte = mensajeEnviar.getBytes();
	paqueteEnviar = new DatagramPacket(mensajeByte, mensajeByte.length, servidorDestino, puerto);
	socketUDP.send(paqueteEnviar);
	
	//Recibiendo el paquete
	
	byte mensajeRecibidoByte[] = new byte[1024];
	paqueteRecibido = new DatagramPacket(mensajeRecibidoByte, mensajeRecibidoByte.length);
	
	socketUDP.receive(paqueteRecibido);
	
	String recibido = new String(paqueteRecibido.getData());
	System.out.println(" Respuesta del servidor: " + recibido);
	finalizar();
	
	
				
	
}

public void finalizar() {

	try {
		socketUDP.close();
		System.out.println("Conexion Finalizada");
	} catch (Exception e) {
		e.printStackTrace();
		}
	
}

}
