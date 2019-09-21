import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
	
	DatagramSocket socketUDP;
	DatagramPacket paqueteRecibido;
	DatagramPacket paqueteEnviar;
	int puerto;
	
public UDPServer(int p) {
	puerto = p;
}

public void iniciar() {
	
	try {
		socketUDP = new DatagramSocket(puerto);
		System.out.println(" ---- Servidor UDP Iniciado puerto:"+ puerto +" ---");;
		System.out.println("Esperadno al cliente....");
		while(true) {
			paqueteRecibido = new DatagramPacket(new byte[1024], 1024);
			socketUDP.receive(paqueteRecibido);
			System.out.println("Llego una peticion...");
			
			String mensajeRecibido = new String(paqueteRecibido.getData());
			System.out.println("Mensaje recibido: " + mensajeRecibido);
			
			String mensaje = "Binvenido al servidor UDP " + mensajeRecibido;
			
			byte mensajeEnviar[] = new byte[1024];
			mensajeEnviar = mensaje.getBytes();
			
			paqueteEnviar = new DatagramPacket(mensajeEnviar, mensajeEnviar.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
			
			socketUDP.send(paqueteEnviar);
		}
	}
		catch (Exception e) {
		e.printStackTrace();
		finalizar();
	}	
	
}

private void finalizar() {
	try {
		socketUDP.close();
		System.out.println("Conexion finalizada");

	} catch (Exception e) {
		e.printStackTrace();
}
	
}

}

