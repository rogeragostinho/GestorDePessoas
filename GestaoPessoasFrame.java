import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.*;

class GestaoPessoasFrame extends JFrame implements ActionListener, ListSelectionListener
{
	// Declaração das variáveis
	private JPanel panelNorth;
	
	private JPanel panelNorthEast;
	private JButton buttonAdicionarPessoa;
	private JButton buttonEditarPessoa;
	private JButton buttonRemoverPessoa;

	private JPanel panelCenter;
	private JTextArea textArea;
	private JList<String> listPessoas;

	private final List<Pessoa> pessoas;
	// Fim da declaração das variáveis

	public GestaoPessoasFrame()
	{
		super("Gestor de Pessoas");
		setLayout(new BorderLayout());

		//
		pessoas = new ArrayList<>();
		pessoas.add(new Pessoa("Roger", 21));
		pessoas.add(new Pessoa("Hugo", 20));

		initComponents();
		addListeners();
		atualizarLista();
	}

	/**
	 * Configuração dos componentes
	 **/
	public void initComponents()
	{
		initPanelNorth();
		initPanelCenter();
	}

	public void initPanelNorth()
	{
		panelNorth = new JPanel(new BorderLayout());

		buttonAdicionarPessoa = new JButton("Adicionar pessoa");

		panelNorthEast = new JPanel(new FlowLayout());

		buttonEditarPessoa = new JButton("Editar");
		buttonRemoverPessoa = new JButton("Remover");

		panelNorth.add(buttonAdicionarPessoa, BorderLayout.WEST);
		panelNorthEast.add(buttonEditarPessoa);
		panelNorthEast.add(buttonRemoverPessoa);
		panelNorth.add(panelNorthEast, BorderLayout.EAST);

		add(panelNorth, BorderLayout.NORTH);
	}

	public void initPanelCenter()
	{
		panelCenter = new JPanel(new BorderLayout());

		textArea = new JTextArea("Nenhum registro selecionado" ,10, 20);
		textArea.setEditable(false);

		listPessoas = new JList<>();
		listPessoas.setBackground(Color.RED);

		panelCenter.add(textArea, BorderLayout.WEST);
		panelCenter.add(listPessoas);

		add(panelCenter);
	}

	public void addListeners()
	{
		buttonAdicionarPessoa.addActionListener(this);
		listPessoas.addListSelectionListener(this);
		buttonRemoverPessoa.addActionListener(this);
		buttonEditarPessoa.addActionListener(this);
	}
	// Fim da configuração dos componentes

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == buttonAdicionarPessoa)
		{
			Pessoa pessoa = new Pessoa(" ", 0);
		//	adicionarPessoa();
			AdicionarPessoaDialog adicionarPessoaDialog = new AdicionarPessoaDialog(this, pessoa);
			adicionarPessoaDialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e)
				{
					adicionarPessoaNaLista(pessoa);
				}
			});
			adicionarPessoaDialog.setVisible(true);
		}

		if (event.getSource() == buttonEditarPessoa)
		{
			int index = listPessoas.getSelectedIndex();

			Pessoa pessoa = pessoas.get(index);

			EditarPessoaDialog editarPessoaDialog = new EditarPessoaDialog(this, pessoa);
			editarPessoaDialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e)
				{
					//adicionarPessoaNaLista(pessoa);
					atualizarLista();
				}
			});
			editarPessoaDialog.setVisible(true);
		}

		if (event.getSource() == buttonRemoverPessoa)
			removerPessoa(listPessoas.getSelectedIndex());
	}

	@Override
	public void valueChanged(ListSelectionEvent event)
	{
		// tratar esta exceção
		int index = listPessoas.getSelectedIndex();
		if (index != -1) {
			textArea.setText(
				"Nome: " + pessoas.get(index).getNome() +
				"\nIdade: " + pessoas.get(index).getIdade()
			);
		}
		else
			textArea.setText("Nenhum registro selecionado");
	}

	/*private void adicionarPessoa()
	{
		String nome = textFieldNome.getText();
		int idade = Integer.parseInt(textFieldIdade.getText());

		// adiciona Pessoa em pessoas e posteriormente em listPessoas
		adicionarPessoaNaLista(new Pessoa(nome, idade));
	}*/

	private void adicionarPessoaNaLista(Pessoa pessoa)
	{
		pessoas.add(pessoa);
		atualizarLista();
	}

	private void atualizarLista()
	{
		String[] nomes = new String[pessoas.size()];

		for (int i = 0; i < pessoas.size(); i++)
			nomes[i] = pessoas.get(i).getNome();

		listPessoas.setListData(nomes);
	}

	private void removerPessoa(int index)
	{
		//JOptionPane.showMessageDialog(null, index);
		pessoas.remove(index);
		atualizarLista();
	}
}