package controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class DistroController {

	public DistroController() {
		super();
	}
	
	private String os() {
		
		String nome = System.getProperty("os.name");
		
		return nome;
	}
	
	public void exibeDistro() {
		
		String sistema = os();
		
		if(sistema.toLowerCase().contains("linux")) {
			linux();
		}else {
			System.out.println("Este sistema operacional não é Linux.\nNão é possível realizar esta operação.");
		}
	}
	
	private void linux() {
		
		String comando = "cat /etc/os-release";
		
		String[] comandoArr = comando.split(" ");
		String[] distribuicao;
		
		try {
			// Criando a variavel do tipo proceso
			Process proc = Runtime.getRuntime().exec(comandoArr);
			
			// Criando o fluxo de bits
			InputStream fluxo = proc.getInputStream();
			
			// Convertendo o fluxo em String e passando para o bufferReader
			InputStreamReader leitorFluxo = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitorFluxo);
			
			String linha = buffer.readLine();
			
			while(linha!=null) {
				
				if(linha.toLowerCase().contains("pretty")) {
					distribuicao = linha.split("=");
					comando = distribuicao[1];
				}
				linha = buffer.readLine();
			}
			
		}catch(Exception exc) {
			System.err.println(exc.getMessage());
		}
		
		System.out.println("Distribuicao deste Linux: "+comando);
	}

}