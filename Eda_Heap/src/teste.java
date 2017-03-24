import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.eda.listaprioridades.LPMaximaOrdenada;

public class teste {
	public static void main(String args[]){
		LPMaximaOrdenada lpnordenada = new LPMaximaOrdenada(10);
		List<Integer> list = new ArrayList();
		list.add(1);
		list.add(4);
		list.add(10);
		list.add(3);
		list.add(9);
		list.add(122);		
		lpnordenada.contruir(list);		
		
		lpnordenada.show();
	}
}
