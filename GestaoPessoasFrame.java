import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

class GestaoPessoasFrame extends JFrame implements ActionListener, ListSelectionListener
{
	// Declaração das variáveis
	private JPanel panelMain;
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
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		// teste
		pessoas = new ArrayList<>();
		pessoas.add(new Pessoa("Roger", 21));
		pessoas.add(new Pessoa("Hugo", 20));
		//

		initComponents();
		addListeners();
		atualizarLista(); // teste

		// define as propriedades da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(400, 200));
	}

	/**
	 * Configuração dos componentes
	 **/
	public void initComponents()
	{
		panelMain = new JPanel(new BorderLayout());
		panelMain.setBorder(new EmptyBorder(15, 15, 15, 15));

		initPanelNorth();
		initPanelCenter();

		add(panelMain);
	}

	public void initPanelNorth()
	{
		panelNorth = new JPanel();
		panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS));

		buttonAdicionarPessoa = new JButton("Adicionar pessoa");

		buttonEditarPessoa = new JButton("Editar");
		buttonRemoverPessoa = new JButton("Remover");
		desabilitarButtons();

		panelNorth.add(buttonAdicionarPessoa);
		panelNorth.add(Box.createGlue());
		panelNorth.add(buttonEditarPessoa);
		panelNorth.add(Box.createHorizontalStrut(5));
		panelNorth.add(buttonRemoverPessoa);

		panelNorth.setBorder(new EmptyBorder(0, 0, 7, 0));

		panelMain.add(panelNorth, BorderLayout.NORTH);
	}

	public void initPanelCenter()
	{
		GridBagLayout layout = new GridBagLayout();
		panelCenter = new JPanel(layout);

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;

		// textarea
		textArea = new JTextArea("Nenhum registro selecionado" ,10, 10);
		textArea.setEditable(false);
		textArea.setBorder(new EmptyBorder(5, 7, 7, 5));	

		layout.setConstraints(textArea, constraints);
		panelCenter.add(textArea);

		// list
		listPessoas = new JList<>();
		listPessoas.setBorder(new EmptyBorder(5, 7, 7, 5));
		listPessoas.setPreferredSize(textArea.getPreferredSize());
		
		constraints.insets = new Insets(0, 7, 0, 0);
		layout.setConstraints(listPessoas, constraints);
		panelCenter.add(listPessoas);
		//

		panelMain.add(panelCenter);
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

			Pessoa pessoa = null;
			try
			{
				pessoa = pessoas.get(index);
			}
			catch (IndexOutOfBoundsException e)
			{
				JOptionPane.showMessageDialog(null, "Selecione um registo", "Nenhuma seleção", JOptionPane.ERROR_MESSAGE);
				return;
			}

			EditarPessoaDialog editarPessoaDialog = new EditarPessoaDialog(this, pessoa);

			editarPessoaDialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e)
				{
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
		if (index != -1) 
		{
			textArea.setText(
				"Nome: " + pessoas.get(index).getNome() +
				"\nIdade: " + pessoas.get(index).getIdade()
			);
			habilitarButtons();
		}
		else
		{
			textArea.setText("Nenhum registro selecionado");
			desabilitarButtons();
		}
			
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

		// pega o index do registo selecionado para voltar a selecionar depois de atualizar a lista
		int index = listPessoas.getSelectedIndex();

		listPessoas.setListData(nomes);

		// Reseleciona o index anterior selecionado
		listPessoas.setSelectedIndex(index);
	}

	private void removerPessoa(int index)
	{
		try
		{
			pessoas.remove(index);
		}
		catch (IndexOutOfBoundsException e)
		{
			JOptionPane.showMessageDialog(null, "Selecione um registo", "Nenhuma seleção", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		atualizarLista();
	}

	private void habilitarButtons()
	{
		buttonEditarPessoa.setEnabled(true);
		buttonRemoverPessoa.setEnabled(true);
	}

	private void desabilitarButtons()
	{
		buttonEditarPessoa.setEnabled(false);
		buttonRemoverPessoa.setEnabled(false);
	}
}