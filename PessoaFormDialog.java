import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class PessoaFormDialog extends JDialog
{
	protected Pessoa pessoa;
	private JTextField textFieldNome;
	private JTextField textFieldIdade;
	private JButton buttonAdicionarPessoa;

	public PessoaFormDialog(JFrame own, String titulo)
	{
		super(own, titulo);
		setLayout(new GridLayout(5, 1));

		initComponents();
		addListeners();
	}

	public void initComponents()
	{
		//initTextFields();
		textFieldNome = new JTextField(6);
		textFieldIdade = new JTextField(6);

		buttonAdicionarPessoa = new JButton("Adicionar");

		add(new JLabel("Nome: "));
		add(textFieldNome);
		add(new JLabel("Idade: "));
		add(textFieldIdade);
		add(buttonAdicionarPessoa);
	}

	/*public void initTextFields()
	{
		
	}*/

	public void addListeners()
	{
		buttonAdicionarPessoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String nome = textFieldNome.getText();
				int idade = Integer.parseInt(textFieldIdade.getText());

				pessoa.setNome(nome);
				pessoa.setIdade(idade);

				dispose();
			}
		});
	}
}