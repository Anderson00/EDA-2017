package br.ufc.quixada.eda.testes;

import java.io.IOException;
import java.util.List;

import br.ufc.quixada.eda.listaprioridades.LPMaximaNOrdenada;
import br.ufc.quixada.eda.util.CriarInstancia;
import br.ufc.quixada.eda.util.EDAConstants;
import br.ufc.quixada.eda.util.EDAUtil;
import br.ufc.quixada.eda.util.Operacao;

public class TesteListaPrioridadesNOrdenado {
	public static void main(String args[]){		
		new TesteListaPrioridadesNOrdenado().executar();
	}
	
	public void executar(){
		try {
			for (int tamanho : CriarInstancia.tamanhoInstancias) {	
				System.out.println("-----------------------------------------------------------");
				String path = EDAConstants.caminhoPasta + "tarefa" + tamanho + ".txt";
				List<Integer> entrada = EDAUtil.getDadosIniciais(path);
				System.out.println(">> tarefa " + tamanho);
				
				executeAllOperations(entrada, tamanho);
				
				System.out.println("-----------------------------------------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void executeAllOperations(List<Integer> entrada, int tamanho) throws IOException{
		char operations[] = new char[]{'I','A','R','S'};
		for(char op : operations){
			String arquivoOperacao = "operacoes"+op+"_" + tamanho;
			String path = EDAConstants.caminhoPasta + arquivoOperacao + ".txt";
			List<Operacao> operacoes = EDAUtil.getOperacoes(path);
			
			long tempoInicial = System.currentTimeMillis();				
			LPMaximaNOrdenada listaPrioridade = new LPMaximaNOrdenada(2*entrada.size());
			listaPrioridade.contruir(entrada);	
			for (Operacao operacao : operacoes) {
				//System.out.println(operacao.getId() + " " + operacao.getValor() + " " + operacao.getNovoValor());
				switch(operacao.getId()){
					case "I" :
						listaPrioridade.inserir(operacao.getValor());
					break;
					case "R" :
						listaPrioridade.remove();
					break;
					case "A" :
						listaPrioridade.alterarPrioridade(operacao.getValor(), operacao.getNovoValor());
					break;
					case "S" :
						listaPrioridade.getMaximaPrioridade();
					break;
				}
			}	
			long tempo = System.currentTimeMillis() - tempoInicial;			  
			System.out.println(arquivoOperacao + ": " + tempo+"ms" + ", "+tempo/1000 + "s");
		}
	}
}
