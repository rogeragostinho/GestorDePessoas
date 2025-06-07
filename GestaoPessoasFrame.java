import javax.swing.JFrame;
//import java.awt.
//import javax.swing.
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JOptionPane;

class GestaoPessoasFrame extends JFrame implements ActionListener, ListSelectionListener
{
	// Declaração das variáveis
	private JPanel panelNorth;
	
	private JPanel panelNorthWest;
	private JButton buttonEditarPessoa;
	private JButton buttonRemoverPessoa;

	private JPanel panelCenter;
	private JTextArea textArea;
	private JList<String> listPessoas;
	
	private JPanel panelListPessoas;
	private JPanel panelListPessoasOptions;
	

	private JPanel panelSouth;
	
	private JPanel panelAdicionarPessoa;

	
	
	private JPanel panelNome;
	private JPanel panelIdade;

	private final List<Pessoa> pessoas;
	// Fim da declaração das variáveis

	public GestaoPessoasFrame()
	{
		super("Gestor de Pessoas");
		setLayout(new BorderLayout());

		//
		pessoas = new ArrayList<>(){};
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

		// ==== inicio: painelCenter ====
		/*panelListPessoas = new JPanel(new BorderLayout());

		

		panelListPessoasOptions = new JPanel(new GridLayout(2, 1));

		

		panelListPessoasOptions.add(buttonEditarPessoa);
		panelListPessoasOptions.add(buttonRemoverPessoa);

		panelListPessoas.add(listPessoas);
		panelListPessoas.add(panelListPessoasOptions, BorderLayout.EAST);
		// ==== fim: painelCenter ====


		// ==== inicio: painelSouth ====
		panelSouth = new JPanel(new BorderLayout());

		

			// inicio painel adicionar pessoa
		panelAdicionarPessoa = new JPanel(new GridLayout(4, 1));		

		panelNome = new JPanel(new FlowLayout());

		textFieldNome = new JTextField(10);

		panelNome.add(new JLabel("Nome"));
		panelNome.add(textFieldNome);

		panelIdade = new JPanel(new FlowLayout());

		textFieldIdade = new JTextField(10);

		panelIdade.add(new JLabel("Idade"));
		panelIdade.add(textFieldIdade);

		

		panelAdicionarPessoa.add(new JLabel("Adicionar"));
			// fim painel adicionar pessoa

		panelAdicionarPessoa.add(panelNome);
		panelAdicionarPessoa.add(panelIdade);
		panelAdicionarPessoa.add(buttonAdicionarPessoa);

		panelSouth.add(textArea, BorderLayout.WEST);
		panelSouth.add(panelAdicionarPessoa/*, BorderLayout.EAST*/);
		// ==== fim: painelSouth ====

		// adiciona paineis principais a interface*/
		add(panelListPessoas);
		add(panelSouth, BorderLayout.SOUTH);
	}

	public void initPanelNorth()
	{
		panelNorth = new JPanel(new BorderLayout());

		buttonAdicionarPessoa = new JButton("Adicionar pessoa");

		panelNorthWest = new JPanel(new FlowLayout();

		buttonEditarPessoa = new JButton("Editar");
		buttonRemoverPessoa = new JButton("Remover");

		panelNorth.add(buttonAdicionarPessoa, BorderLayout.EAST);
		panelNorthWest.add(buttonEditarPessoa);
		panelNorthWest.add(buttonRemoverPessoa);
		panelNorth.add(panelNorthWest, BorderLayout.WEST);

		add(panelNorth, BorderLayout.NORTH);
	}

	public void initPanelCenter()
	{
		panelCenter = new JPanel(new BorderLayout());

		textArea = new JTextArea(10, 20);

		listPessoas = new JList<>();
		listPessoas.setBackground(Color.RED);

		panelCenter.add(textArea, BorderLayout.EAST);
		panelCenter.add(listPessoas);

		add(panelCenter);
	}

	public void addListeners()
	{
		buttonAdicionarPessoa.addActionListener(this);
		listPessoas.addListSelectionListener(this);
		buttonRemoverPessoa.addActionListener(this);
	}
	// Fim da configuração dos componentes

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == buttonAdicionarPessoa)
			adicionarPessoa();

		if (event.getSource() == buttonRemoverPessoa)
			removerPessoa(listPessoas.getSelectedIndex());
	}

	@Override
	public void valueChanged(ListSelectionEvent event)
	{
		textArea.setText(
			"Nome: " + pessoas.get(listPessoas.getSelectedIndex()).getNome() +
			"\nIdade: " + pessoas.get(listPessoas.getSelectedIndex()).getIdade()
		);
	}

	private void adicionarPessoa()
	{
		String nome = textFieldNome.getText();
		int idade = Integer.parseInt(textFieldIdade.getText());

		// adiciona Pessoa em pessoas e posteriormente em listPessoas
		adicionarPessoaNaLista(new Pessoa(nome, idade));
	}

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