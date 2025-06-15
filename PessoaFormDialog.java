import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class PessoaFormDialog extends JDialog
{
	protected Pessoa pessoa;
	private JLabel labelNome;
	private JLabel labelIdade;
	protected JTextField textFieldNome;
	protected JTextField textFieldIdade;
	private JButton buttonAdicionarPessoa;
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
		setLocationRelativeTo(own);
	}

	public void initComponents()
	{
		GridBagLayout layout = new GridBagLayout();
		panelCenter = new JPanel();
		panelCenter.setLayout(layout);

		GridBagConstraints constraints = new GridBagConstraints();

		labelNome = new JLabel("Nome: ");
		labelIdade = new JLabel("Idade: ");

		initTextFields();
		
		buttonAdicionarPessoa = new JButton("Adicionar");

		// 
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(labelNome, constraints);
		panelCenter.add(labelNome);
		// 

		// 
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		layout.setConstraints(textFieldNome, constraints);
		panelCenter.add(textFieldNome);
		// 

		// 
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(15, 0, 0, 0);
		layout.setConstraints(labelIdade, constraints);
		panelCenter.add(labelIdade);
		// 
		
		// 
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		layout.setConstraints(textFieldIdade, constraints);
		panelCenter.add(textFieldIdade);
		// 

		// ´
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0;
		constraints.insets = new Insets(15, 0, 0, 0);
		layout.setConstraints(buttonAdicionarPessoa, constraints);
		panelCenter.add(buttonAdicionarPessoa);
		// 

		//panelCenter.add(buttonAdicionarPessoa);

		add(Box.createHorizontalStrut(40));
		add(panelCenter);
		add(Box.createHorizontalStrut(40));
	}

	public void initTextFields()
	{
		textFieldNome = new JTextField(6);
		textFieldIdade = new JTextField(6);
	}

	public void addListeners()
	{
		buttonAdicionarPessoa.addActionListener(new ActionListener() {
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