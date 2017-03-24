package br.ufc.quixada.eda.listaprioridades;

import java.util.List;

/**
 * Implementa a lista de prioridade usando vetor ordenado.
 * Reparem que aqui voc� devem utilizar obrigat�riamente o quickSort 
 * para realizar a ordena��o, quando for necess�rio.
 * @author fabio
 *
 */
public class LPMaximaOrdenada {
	private int nMaximo = 0;
	private int vetor[] = null;
	private int n = 0;
	
	public LPMaximaOrdenada(int Nmaximo){
		nMaximo = Nmaximo;
		vetor = new int[Nmaximo];
	}
	
	public void contruir(List<Integer> entrada){
		for(int i = 0; i < entrada.size(); i++){
			vetor[i] = entrada.get(i);
		}
		n = entrada.size();
		quickSort(0, n - 1);
	}
	
	public int getMaximaPrioridade(){
		if(n > 0){
			return this.vetor[this.n - 1];
		}
		return -1;
	}
	
	public int remove(){
		if(n > 0){
			return vetor[--n];
		}
		return 0;
	}	
	
	public void inserir(int prioridade){
		if(this.nMaximo != this.n){
			int i;
			for (i = 0; i < this.n; i++)
				if(vetor[i] < prioridade)
					break;
			this.vetor[this.n] = prioridade;
			this.n++;
			for (int j = this.n - 1; j > i; j--){
				int aux = this.vetor[j];
				this.vetor[j] = this.vetor[j - 1];
				this.vetor[j - 1] = aux;
			}
		}
	}
	
	public void alterarPrioridade(int prioridade, int novaPrioridade){
		int i;
		for (i = 0; i < this.n && this.vetor[i] != prioridade; i++)
		if(i == this.n) return;
		vetor[i] = novaPrioridade;
		if(prioridade > novaPrioridade) IrPraDireita(i);
		else IrPraEsquerda(i);
	}
	
	private void IrPraEsquerda(int indice){
		for (int i = indice; i > 0 && this.vetor[i - 1] < this.vetor[i]; i--) {
			int aux = this.vetor[i - 1];
			this.vetor[i - 1] = this.vetor[i];
			this.vetor[i] = aux;
		}
	}
	
	private void IrPraDireita(int indice){
		for (int i = indice; i < this.n - 1 && this.vetor[i] < this.vetor[i + 1]; i++) {
			int aux = this.vetor[i + 1];
			this.vetor[i + 1] = this.vetor[i];
			this.vetor[i] = aux;
		}
	}
	
	private void quickSort(int ini, int fim){
		if(ini < fim){
			int meio = particiona(ini, fim);
			quickSort(ini, meio - 1);
			quickSort(meio + 1, fim);
		}
	}
		
	private int particiona(int p, int r){		
		int pivo = vetor[p];
		int i = p + 1;
		int f = r;
		while(i <= f){
			if(vetor[i] <= pivo){
				i++;
			}else if(pivo < vetor[f]){
				f--;
			}else{
				int aux = vetor[i];
				vetor[i] = vetor[f];
				vetor[f] = aux;
				i++;
				f--;
			}
		}
		vetor[p] = vetor[f];
		vetor[f] = pivo;
		return f;
	}
	
	
	public void show(){
		for(int i : vetor){
			System.out.print(i+" ");
		}
		System.out.println(">> "+this.n);
		System.out.println();
	}
}