class Pessoa
{
	private String nome;
	private int idade;

	public Pessoa(String nome, int idade)
	{
		this.nome = nome;
		this.idade = idade;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public String get()
	{
		return this.nome;
	}

	public void setIdade(int idade)
	{
		this.idade = idade;
	}
	public int get()
	{
		return this.idade;
	}
}