import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
	ServerSocket myServidor;
	Socket sCliente;
	int puerto;
	PrintStream salida;
	Scanner entrada;
	String mensajeSolicitud = "";
	String mensajeRespuesta = "";
	

public TCPServer(int p) {
	puerto = p;
}

public void inciar() {
	
	try {
		myServidor = new ServerSocket(puerto);
		System.out.println("------- Esperando Cliente -------- ");
		
		while(true) {
			sCliente = myServidor.accept();
			entrada = new Scanner(sCliente.getInputStream());
			salida = new PrintStream(sCliente.getOutputStream());
			mensajeSolicitud = entrada.nextLine();
			
			System.out.println("solicitud del cliente: "+ mensajeSolicitud);
			mensajeRespuesta = "Bienvenido "+ mensajeSolicitud+  " al servidor.." ;
			salida.println(mensajeRespuesta);	
		}
	}catch (Exception e) {
		e.printStackTrace();
		
	}

	finally {
		finalizar();
	}
	
	
}

public void finalizar() {
	try {
		salida.close();
		entrada.close();
		myServidor.close();
		sCliente.close();
		System.out.println(" Conexion Finalizada ...");	
	}catch (Exception e) {
		e.printStackTrace();
	}	

}


}
