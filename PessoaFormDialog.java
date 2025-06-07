import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class PessoaFormDialog extends JDialog
{
	protected Pessoa pessoa;
	protected JTextField textFieldNome;
	protected JTextField textFieldIdade;
	private JButton buttonAdicionarPessoa;

	public PessoaFormDialog(JFrame own, String titulo, Pessoa pessoa)
	{
		super(own, titulo);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 400);

		setLayout(new GridLayout(5, 1));

		this.pessoa = pessoa;
		initComponents();
		addListeners();
	}

	public void initComponents()
	{
		initTextFields();
		
		buttonAdicionarPessoa = new JButton("Adicionar");

		add(new JLabel("Nome: "));
		add(textFieldNome);
		add(new JLabel("Idade: "));
		add(textFieldIdade);
		add(buttonAdicionarPessoa);
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