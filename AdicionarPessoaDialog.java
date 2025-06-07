import javax.swing.JFrame;

public class AdicionarPessoaDialog extends PessoaFormDialog
{
	public AdicionarPessoaDialog(JFrame own, Pessoa pessoa)
	{
		super(own, "Adicionar Pessoa");
		this.pessoa = pessoa;
	}
}