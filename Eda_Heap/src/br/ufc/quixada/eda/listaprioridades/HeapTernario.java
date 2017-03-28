package br.ufc.quixada.eda.listaprioridades;

import java.util.List;

public class HeapTernario {
	private int nMaximo = 0;
	private int vetor[] = null;
	private int n = 0;
	
	public HeapTernario(int Nmaximo){
		nMaximo = Nmaximo;
		vetor = new int[Nmaximo];
	}
	
	private int pai(int i){
		return (i+1) / 3;
	}
	
	private int filhoEsquerda(int i){
		return i*3-1;
	}
	
	private int filhoMeio(int i){
		return i*3;
	}
	
	private int filhoDireita(int i){
		return i*3+1;
	}
	
	private void swap(int a, int b){
		int aux = this.vetor[a];
		this.vetor[a] = this.vetor[b];
		this.vetor[b] = aux;
	}
	
	private void subir(int i){
		int j = pai(i);
		
		if(j >= 1){
			if(this.vetor[i] > this.vetor[j]){
				swap(i,j);
				subir(j);
			}
		}
	}
	
	
	private void descer(int i){
		int meio = filhoMeio(i);
		int maior = meio; 
		
		if(meio <= this.n){
			if(meio + 1 <= this.n){
				if(vetor[meio - 1] > vetor[meio] && vetor[meio - 1] > vetor[meio + 1]) maior = meio - 1;
				else if(vetor[meio + 1] > vetor[meio]) maior = meio + 1;
			}else{
				if(vetor[meio - 1] > vetor[meio]) maior = meio - 1;
			}		
			if(vetor[i] < vetor[maior]){
				swap(i,maior);
				descer(maior);
			}			
		}else if(meio - 1 <= this.n){
			maior = meio - 1;					
			if(vetor[i] < vetor[maior]){
				swap(i,maior);
				descer(maior);
			}
				
		}
		
	}
	
	public void contruir(List<Integer> entrada){
		for (int i = 0; i < entrada.size(); i++) {
			vetor[i] = entrada.get(i);
		}
		n = entrada.size();
		for (int i = (this.n / 3); i >= 1; i--)
			descer(i);
	}
	
	public int getMaximaPrioridade(){
		if(this.n > 0)
			return this.vetor[1];
		return 0;
	}
	
	public int remove(){
		if(this.n > 0){
			int aux = this.vetor[1];
			this.vetor[1] = this.vetor[n - 1];
			this.n--;
			descer(1);
			return aux;
		}
		return 0;
	}	
	
	public void inserir(int prioridade){
		if(this.n < this.nMaximo){
			vetor[this.n] = prioridade;
			this.n++;
			subir(n);
		}
	}
	
	public void alterarPrioridade(int prioridade, int novaPrioridade){
		int i;
		for (i = 1; i < this.n && this.vetor[i] != prioridade; i++)
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