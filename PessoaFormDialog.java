import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public abstract class PessoaFormDialog extends JDialog
{
	protected Pessoa pessoa;
	private JLabel labelNome;
	private JLabel labelIdade;
	protected JTextField textFieldNome;
	protected JTextField textFieldIdade;
	protected JButton buttonPessoa;
	private JPanel panelCenter;

	public PessoaFormDialog(JFrame own, String titulo, Pessoa pessoa)
	{
		super(own, titulo, true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		this.pessoa = pessoa; 

		initComponents();
		addListeners();

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(own);
	}

	// Configuração dos componentes
	public void initComponents()
	{
		GridBagLayout layout = new GridBagLayout();
		panelCenter = new JPanel();
		panelCenter.setLayout(layout);

		labelNome = new JLabel("Nome");
		labelIdade = new JLabel("Idade");

		initTextFields();
		initButton();

		textFieldNome.setBorder(new EmptyBorder(4, 5, 4, 5));
		textFieldIdade.setBorder(new EmptyBorder(4, 5, 4, 5));

		addComponent(labelNome, GridBagConstraints.WEST, GridBagConstraints.NONE, GridBagConstraints.REMAINDER, 0, new Insets(0, 0, 3, 0));

		addComponent(textFieldNome, GridBagConstraints.CENTER, GridBagConstraints.BOTH, GridBagConstraints.REMAINDER, 1);

		addComponent(labelIdade, GridBagConstraints.WEST, GridBagConstraints.NONE, GridBagConstraints.REMAINDER, 0, new Insets(15, 0, 3, 0));
	
		addComponent(textFieldIdade, GridBagConstraints.CENTER, GridBagConstraints.BOTH, GridBagConstraints.REMAINDER, 1, new Insets(0, 0, 0, 0));

		addComponent(buttonPessoa, GridBagConstraints.CENTER, GridBagConstraints.NONE, GridBagConstraints.REMAINDER, 0, new Insets(15, 0, 0, 0));

		add(Box.createHorizontalStrut(40));
		add(panelCenter);
		add(Box.createHorizontalStrut(40));
	}

	public void addComponent(Component component, int anchor, int fill, int gridwidth, int weightx)
	{
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = anchor;
		constraints.fill = fill;
		constraints.gridwidth = gridwidth;
		constraints.weightx = weightx;

		GridBagLayout layout = (GridBagLayout) panelCenter.getLayout();
		layout.setConstraints(component, constraints);
		panelCenter.add(component);
	}

	public void addComponent(Component component, int anchor, int fill, int gridwidth, int weightx, Insets insets)
	{
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = anchor;
		constraints.fill = fill;
		constraints.gridwidth = gridwidth;
		constraints.weightx = weightx;
		constraints.insets = insets;

		GridBagLayout layout = (GridBagLayout) panelCenter.getLayout();
		layout.setConstraints(component, constraints);
		panelCenter.add(component);
	}

	public abstract void initTextFields();

	public abstract void initButton();
	// Fim da Configuração dos componentes

	public void addListeners()
	{
		buttonPessoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//JOptionPane.showMessageDialog(null, "e.getMessage()");

				String nome = textFieldNome.getText();

				int idade = 0;

				try
				{
					//System.out.println("linha 1");
					idade = Integer.parseInt(textFieldIdade.getText());	
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(null, "Digite uma idade válida", "Valor inválido", JOptionPane.ERROR_MESSAGE);
					return;
				}

				pessoa.setNome(nome);
				pessoa.setIdade(idade);

				dispose();
			}
		});
	}
}