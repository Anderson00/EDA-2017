package br.ufc.quixada.eda.listaprioridades;
import java.util.List;

/**
 * Implementa a lista de prioridade usando Heap Maximo.
 * @author fabio
 *
 */
public class HeapMaximo {
	private int nMaximo = 0;
	private int vetor[] = null;
	private int n = 0;
	
	public HeapMaximo(int Nmaximo){
		nMaximo = Nmaximo;
		vetor = new int[Nmaximo];
	}
	
	private void subir(int i){
		int j = (i - 1) / 2;
		if(j >= 0){
			if(this.vetor[i] > this.vetor[j]){
				int aux = this.vetor[i];
				this.vetor[i] = this.vetor[j];
				this.vetor[j] = aux;
				subir(j);
			}
		}
	}
	
	private void descer(int i){
		int j = 2 * (i + 1);
		if(j <= this.n){
			j--;
			if(j + 1 < this.n && this.vetor[j] < this.vetor[j + 1]) j++;
			if(this.vetor[i] < this.vetor[j]){
				int aux = this.vetor[i];
				this.vetor[i] = this.vetor[j];
				this.vetor[j] = aux;
				descer(j);
			}
		}
	}
	
	public void contruir(List<Integer> entrada){
		for (int i = 0; i < entrada.size(); i++) {
			vetor[i] = entrada.get(i);
		}
		n = entrada.size();
		for (int i = (this.n / 2); i >= 0; i--)
			descer(i);
	}
	
	public int getMaximaPrioridade(){
		if(this.n > 0)
			return this.vetor[0];
		return 0;
	}
	
	public int remove(){
		if(this.n > 0){
			int aux = this.vetor[0];
			this.vetor[0] = this.vetor[n - 1];
			this.n--;
			descer(0);
			return aux;
		}
		return 0;
	}	
	
	public void inserir(int prioridade){
		if(this.n != this.nMaximo){
			vetor[this.n] = prioridade;
			this.n++;
			subir(n - 1);
		}
	}
	
	public void alterarPrioridade(int prioridade, int novaPrioridade){
		int i;
		for (i = 0; i < this.n && this.vetor[i] != prioridade; i++)
		if(i == this.n) return;
		vetor[i] = novaPrioridade;
		if(prioridade > novaPrioridade) descer(i);
		else subir(i);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String text = "";
		
		for(int a : vetor){
			text += a+" ";
		}
		
		return text;
	}
}
