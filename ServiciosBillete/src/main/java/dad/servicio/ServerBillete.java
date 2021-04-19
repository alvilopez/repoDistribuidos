package dad.servicio;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class ServerBillete {
	
	public static void main(String args[]) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(4444);
			System.out.println("Servidor iniciado en el puerto 44444");
			while(true) {
				Socket socket = serverSocket.accept();
				BufferedReader leerCliente = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String linea;
				Document document = new Document(); 
				PdfWriter.getInstance(document, new FileOutputStream(new File("Billete.pdf")));
				document.open();
				Paragraph paragraph = new Paragraph();
				paragraph.add("VUELANDING\n");
				
				System.out.println(leerCliente.readLine());
				
				while ((linea = leerCliente.readLine ()) != null ) {
					paragraph.add(linea+"\n");
					paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
				}
				document.add(paragraph);
				document.close();
				socket.close();
				
				socket = serverSocket.accept();
				PrintWriter escribirServer = new PrintWriter(socket.getOutputStream(),true);
				File file = new File("Billete.pdf");
				escribirServer.print(file.toURL());
				escribirServer.close();
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
