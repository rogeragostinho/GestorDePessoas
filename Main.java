import javax.swing.JFrame;
import java.awt.Dimension;

public class Main
{
	public static void main(String[] args)
	{
		GestaoPessoasFrame gestaoPessoasFrame = new GestaoPessoasFrame();
		gestaoPessoasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gestaoPessoasFrame.setSize(600, 400);
		gestaoPessoasFrame.setLocationRelativeTo(null);
		gestaoPessoasFrame.setMinimumSize(new Dimension(400, 200));
		gestaoPessoasFrame.setVisible(true);
	}
}