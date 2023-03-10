import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

class Xadrez extends JFrame implements ActionListener {

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		new Xadrez();
	}
	
	static class Casa {
		int linha, coluna;
		JButton botao = new JButton();

		Casa(int linha, int coluna) {
			this.linha = linha;
			this.coluna = coluna;
		}

		void pintaComCorOriginal() {
			if (this.coluna % 2 == 0)
				if (this.linha % 2 == 0)
					this.botao.setBackground(Color.WHITE);
				else
					this.botao.setBackground(Color.LIGHT_GRAY);
			else if (this.linha % 2 == 0)
				this.botao.setBackground(Color.LIGHT_GRAY);
			else
				this.botao.setBackground(Color.WHITE);
		}
	}

	Casa[][] casas = new Casa[8][8];
	static final Color COR_MOVIMENTO_VALIDO = Color.GREEN;
	static final Color COR_CASA_INICIAL = Color.YELLOW;

	// imagens das pecas pretas
	HashMap<String, Image> mapaImgs = new HashMap<>();

	GridLayout gerenciadorDeLayout = new GridLayout(8, 8);

	static final String TEXTO_BARRA_SUPERIOR = "Xadrez (IP-2022)";

	Xadrez() {
		mapaImgs.put("Peao-Preta", new ImageIcon("imagens/pecas/pretas/peao.png", "").getImage().getScaledInstance(30,
				45, java.awt.Image.SCALE_SMOOTH));
		mapaImgs.put("Torre-Preta", new ImageIcon("imagens/pecas/pretas/torre.png", "").getImage().getScaledInstance(45,
				50, java.awt.Image.SCALE_SMOOTH));
		mapaImgs.put("Cavalo-Preta", new ImageIcon("imagens/pecas/pretas/cavalo.png", "").getImage()
				.getScaledInstance(40, 55, java.awt.Image.SCALE_SMOOTH));
		mapaImgs.put("Bispo-Preta", new ImageIcon("imagens/pecas/pretas/bispo.png", "").getImage().getScaledInstance(40,
				58, java.awt.Image.SCALE_SMOOTH));
		mapaImgs.put("Rainha-Preta", new ImageIcon("imagens/pecas/pretas/rainha.png", "").getImage()
				.getScaledInstance(35, 50, java.awt.Image.SCALE_SMOOTH));
		mapaImgs.put("Rei-Preta", new ImageIcon("imagens/pecas/pretas/rei.png", "").getImage().getScaledInstance(40, 60,
				java.awt.Image.SCALE_SMOOTH));

		mapaImgs.put("Peao-Branca", new ImageIcon("imagens/pecas/brancas/peao.png", "").getImage().getScaledInstance(30,
				45, java.awt.Image.SCALE_SMOOTH));
		mapaImgs.put("Torre-Branca", new ImageIcon("imagens/pecas/brancas/torre.png", "").getImage()
				.getScaledInstance(45, 50, java.awt.Image.SCALE_SMOOTH));
		mapaImgs.put("Cavalo-Branca", new ImageIcon("imagens/pecas/brancas/cavalo.png", "").getImage()
				.getScaledInstance(40, 55, java.awt.Image.SCALE_SMOOTH));
		mapaImgs.put("Bispo-Branca", new ImageIcon("imagens/pecas/brancas/bispo.png", "").getImage()
				.getScaledInstance(40, 58, java.awt.Image.SCALE_SMOOTH));
		mapaImgs.put("Rainha-Branca", new ImageIcon("imagens/pecas/brancas/rainha.png", "").getImage()
				.getScaledInstance(35, 50, java.awt.Image.SCALE_SMOOTH));
		mapaImgs.put("Rei-Branca", new ImageIcon("imagens/pecas/brancas/rei.png", "").getImage().getScaledInstance(40,
				60, java.awt.Image.SCALE_SMOOTH));
		super.setTitle(TEXTO_BARRA_SUPERIOR);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.montaTabuleiro();
		super.pack();
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] coordenada = e.getActionCommand().split(",");
		int linha = Integer.parseInt(coordenada[0]);
		int coluna = Integer.parseInt(coordenada[1]);
		trataCliqueSobreUmaCasa(linha, coluna);
	}

	void montaTabuleiro() {
		criaEPintaAsCasas();
		JPanel painelTabuleiro = new JPanel();
		painelTabuleiro.setLayout(gerenciadorDeLayout);

		// adiciona as casas
		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				painelTabuleiro.add(obtemBotaoCasa(linha, coluna));
				obtemBotaoCasa(linha, coluna).addActionListener(this);
				obtemBotaoCasa(linha, coluna).setActionCommand(linha + "," + coluna);
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tab[i][j] != null) {
					obtemBotaoCasa(i, j).setIcon(new ImageIcon(mapaImgs.get(tab[i][j].tipo + "-" + tab[i][j].cor)));
				}
			}
		}

		super.getContentPane().add(painelTabuleiro);
	}

	void pintaComCorOriginal() {
		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				casas[linha][coluna].pintaComCorOriginal();
			}
		}
	}

	void criaEPintaAsCasas() {
		// adiciona as casas
		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				casas[linha][coluna] = new Casa(linha, coluna);
			}
		}

		pintaComCorOriginal();
	}

	JButton obtemBotaoCasa(int linha, int coluna) {
		return casas[linha][coluna].botao;
	}

	void mostraMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	int l, c;
	boolean ehPrimeiroClique = true;
	Trabalho.Peca[][] tab = Trabalho.tabuleiroInicial();
	boolean[][] movs;

	void trataCliqueSobreUmaCasa(int linha, int coluna) {
		if (ehPrimeiroClique) {
			l = linha;
			c = coluna;

			if (tab[l][c] == null) {
				JOptionPane.showMessageDialog(null, "Clique em uma peça");
			} else {
				ehPrimeiroClique = false;
				obtemBotaoCasa(linha, coluna).setBackground(COR_CASA_INICIAL);

				if (tab[linha][coluna].tipo == Trabalho.Tipo.Peao) {
					movs = Trabalho.movsPeao(tab, linha, coluna);
				} else if (tab[linha][coluna].tipo == Trabalho.Tipo.Torre) {
					movs = Trabalho.movsTorre(tab, linha, coluna);
				} else if (tab[linha][coluna].tipo == Trabalho.Tipo.Cavalo) {
					movs = Trabalho.movsCavalo(tab, linha, coluna);
				} else if (tab[linha][coluna].tipo == Trabalho.Tipo.Bispo) {
					movs = Trabalho.movsBispo(tab, linha, coluna);
				} else if (tab[linha][coluna].tipo == Trabalho.Tipo.Rei) {
					movs = Trabalho.movsRei(tab, linha, coluna);
				} else { // if (tab[linha][coluna].tipo == Trabalho.Tipo.Rainha) {
					movs = Trabalho.movsRainha(tab, linha, coluna);
				}

				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (movs[i][j]) {
							obtemBotaoCasa(i, j).setBackground(COR_MOVIMENTO_VALIDO);
						}
					}
				}
			}
		} else {
			Trabalho.Cor corPecaJogada;
			
			if(movs[linha][coluna]) {
				obtemBotaoCasa(linha, coluna).setIcon(obtemBotaoCasa(l, c).getIcon());
				obtemBotaoCasa(l, c).setIcon(null);
				tab[linha][coluna] = tab[l][c];
				tab[l][c] = null;
				corPecaJogada = tab[linha][coluna].cor;
			} else {
				corPecaJogada = null;
				JOptionPane.showMessageDialog(null, "Movimento Inválido");
			}
			
			pintaComCorOriginal();
			ehPrimeiroClique = true;
			int l_rei = -1, c_rei = -1;
			
			if(corPecaJogada != null) {
				for(int ii = 0; ii < 8; ii++) {
					for(int jj = 0; jj < 8; jj++) {
						if(tab[ii][jj] != null && tab[ii][jj].cor != corPecaJogada && tab[ii][jj].tipo == Trabalho.Tipo.Rei) {
							l_rei = ii;
							c_rei = jj;
							break;
						}
					}					
				}
				
				if(Trabalho.estaEmCheque(tab, l_rei, c_rei)) {
					if(corPecaJogada == Trabalho.Cor.Preta)
						JOptionPane.showMessageDialog(null, "Rei branco em cheque");
					else
						JOptionPane.showMessageDialog(null, "Rei preto em cheque");
				}
			}
		}
	}
}
