import javax.swing.*;

public class EditarPessoaDialog extends PessoaFormDialog
{
	public EditarPessoaDialog(JFrame own, Pessoa pessoa)
	{
		super(own, "Editar Pessoa", pessoa);
	}

	@Override
	public void initTextFields()
	{
		textFieldNome = new JTextField(pessoa.getNome(), 6);
		textFieldIdade = new JTextField(Integer.toString(pessoa.getIdade()), 6);
	}
}