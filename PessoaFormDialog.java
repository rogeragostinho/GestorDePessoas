import javax.swing.*;
import java.awt.*;

abstract class PessoaFormaDialog extends JDialog
{
	private Pessoa pessoa;
	private JTextField textFieldNome;
	private JTextField textFieldIdade;
	private JButton buttonAdicionarPessoa;

	public PessoaFormaDialog(JFrame own, String titulo, Pessoa pessoa)
	{
		super(own, titulo);
		this.pessoa = pessoa;
		setLayout(new GridLayout(5, 1));

		initComponents();
		addListeners();
	}

	public void initComponents()
	{
		textFieldNome = new JTextField(6);
		
		textFieldIdade = new JTextField(6);

		add(new JLabel("Nome: "));
		add(textFieldNome);
		add(new JLabel("Idade: "));
		add(textFieldIdade);
		add(buttonAdicionarPessoa);
	}

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