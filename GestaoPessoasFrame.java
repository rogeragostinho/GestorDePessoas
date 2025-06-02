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

class GestaoPessoasFrame extends JFrame implements
{
	private final JList<String> listPessoas;
	private final JPanel panelListPessoas;
	private final JPanel panelListPessoasOptions;
	private final JButton buttonEditarPessoa;
	private final JButton buttonRemoverPessoa;

	// painel south
	private final JPanel panelSouth;
	private final JTextArea textArea;
	private final JPanel panelAdicionarPessoa;

	// painel adicionar pessoa
	private final JTextField textFieldNome;
	private final JTextField textFieldIdade;
	private final JButton buttonAdicionarPessoa;
	private final JPanel panelNome;
	private final JPanel panelIdade;

	private final List<Pessoa> pessoas;

	public GestaoPessoasFrame()
	{
		super("Gestor de Pessoas");
		setLayout(new BorderLayout());

		//
		pessoas = new ArrayList<>();
		//

		// ==== inicio: painelCenter ====
		panelListPessoas = new JPanel(new BorderLayout());

		listPessoas = new JList<>(new String[]{"Roger", "Hugo", "Adão"});
		listPessoas.setBackground(Color.RED);

		panelListPessoasOptions = new JPanel(new GridLayout(2, 1));

		buttonEditarPessoa = new JButton("Editar");
		buttonRemoverPessoa = new JButton("Remover");

		panelListPessoasOptions.add(buttonEditarPessoa);
		panelListPessoasOptions.add(buttonRemoverPessoa);

		panelListPessoas.add(pessoas);
		panelListPessoas.add(panelListPessoasOptions, BorderLayout.EAST);
		// ==== fim: painelCenter ====


		// ==== inicio: painelSouth ====
		panelSouth = new JPanel(new BorderLayout());

		textArea = new JTextArea(10, 20);

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

		buttonAdicionarPessoa = new JButton("Adicionar pessoa");

		panelAdicionarPessoa.add(new JLabel("Adicionar"));
			// fim painel adicionar pessoa

		panelAdicionarPessoa.add(panelNome);
		panelAdicionarPessoa.add(panelIdade);
		panelAdicionarPessoa.add(buttonAdicionarPessoa);

		panelSouth.add(textArea, BorderLayout.WEST);
		panelSouth.add(panelAdicionarPessoa/*, BorderLayout.EAST*/);
		// ==== fim: painelSouth ====

		// adiciona paineis principais a interface
		add(panelListPessoas);
		add(panelSouth, BorderLayout.SOUTH);

		// eventos
		buttonAdicionarPessoa.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == buttonAdicionarPessoa)
			adicionarPessoa();
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

	}

	private void atualizarLista()
	{
		// estou a criar a logica para atualizar as listas de acordo com pessoas
		for (Pessoa pessoa: pessoas)
		{
			if (!listPessoas.contains(pessoa.getNome()))
				listPessoas.add(pessoa.getNome());
		}
	}
}