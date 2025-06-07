import javax.swing.JFrame;

public class EditarPessoaDialog extends PessoaFormDialog
{
	public EditarPessoaDialog(JFrame own, Pessoa pessoa)
	{
		super(own, "Editar Pessoa");
		this.pessoa = pessoa;
	}



	/*@Override
	public void initTextFields()
	{
		textFieldNome = new JTextField(pessoa.getNome(), 6);
		textFieldIdade = new JTextField(pessoa.getIdade().toString(), 6);
	}*/
}