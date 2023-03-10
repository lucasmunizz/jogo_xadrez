
/**
 * 
 * @author Nome completo e número USP
 *
 */
public class Trabalho {

	enum Tipo {
		Peao, Bispo, Cavalo, Torre, Rei, Rainha;
	}

	enum Cor {
		Branca, Preta;
	}

	static class Peca {
		Tipo tipo;
		Cor cor;

		Peca() {
		}

		Peca(Tipo tipo_, Cor cor_) {
			tipo = tipo_;
			cor = cor_;
		}
	}

	/**
	 * @return Devolve o tabuleiro inicial, com as peças brancas embaixo e as pretas
	 *         em cima, considerando que a linha zero é a linha superior da matriz
	 *         que representa o tabuleiro. As casas vazias devem conter a referência
	 *         para null. Cada casa ocupada deve conter uma referência para um
	 *         objeto do tipo "Peca", representando o tipo e a cor da peça que ocupa
	 *         a casa em questão.
	 */
	static Peca[][] tabuleiroInicial() {
		Peca[][] tab = new Peca[8][8];

		return tab;
	}

	/**
	 * @param tab Representa o tabuleiro. Se tab[i][j] = null, então a casa (i,j)
	 *            está vazia. Caso contrário, tab[i][j] contém a peça que está na
	 *            casa (i,j). Assuma que tab[l][c] contém um peão.
	 * @param l   linha em que está o peão
	 * @param c   coluna em que está o peão
	 * @return Devolve uma matriz "mov" booleana 8 x 8, tal que, para toda casa
	 *         (i,j) dentro do tabuleiro, mov[i][j] = true se e somente se o peão
	 *         que está na casa (l,c) puder se movimentar para a casa (i,j)
	 * 
	 *         ATENÇÃO: um movimento não é válido se colocar o próprio rei em cheque
	 *         (por exemplo: se ao movimentar um peão branco for aberta uma diagonal
	 *         entre o rei branco e um bispo preto, isso seria um "auto-cheque").
	 */
	static boolean[][] movsPeao(Peca[][] tab, int l, int c) {
		boolean[][] mov = new boolean[8][8];

		return mov;
	}

	static boolean[][] movsTorre(Peca[][] tab, int l, int c) {
		boolean[][] mov = new boolean[8][8];

		return mov;
	}

	static boolean[][] movsBispo(Peca[][] tab, int l, int c) {
		boolean[][] mov = new boolean[8][8];

		return mov;
	}

	static boolean[][] movsCavalo(Peca[][] tab, int l, int c) {
		boolean[][] mov = new boolean[8][8];

		return mov;
	}

	static boolean[][] movsRei(Peca[][] tab, int l, int c) {
		boolean[][] mov = new boolean[8][8];

		return mov;
	}

	static boolean[][] movsRainha(Peca[][] tab, int l, int c) {
		boolean[][] mov = new boolean[8][8];

		return mov;
	}

	/**
	 * Recebe a cordenada (l,c) de um rei, que pode ser branco ou preto, e devolve
	 * verdadeiro se e somente se o rei está em cheque (ou seja, se o rei está sendo
	 * atacado por alguma peça do adversário)
	 * 
	 * @param tab
	 * @param l   linha do rei
	 * @param c   coluna do rei
	 * @return
	 */
	static boolean estaEmCheque(Peca[][] tab, int l, int c) {
		return false;
	}
}
