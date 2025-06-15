import javax.swing.*;

public class AdicionarPessoaDialog extends PessoaFormDialog
{
	public AdicionarPessoaDialog(JFrame own, Pessoa pessoa)
	{
		super(own, "Adicionar Pessoa", pessoa);
	}

	@Override
	public void initTextFields()
	{
		textFieldNome = new JTextField(6);
		textFieldIdade = new JTextField(6);
	}

	@Override
	public void initButton()
	{
		buttonPessoa = new JButton("Adicionar");
	}
}