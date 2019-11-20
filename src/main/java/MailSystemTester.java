import java.util.Scanner;

public class MailSystemTester
{
	private static final int MAILBOX_COUNT = 20;
	
   public static void main(String[] args){
      MailSystem system = new MailSystem(MAILBOX_COUNT);
      Scanner console = new Scanner(System.in);
      Connection c = new Connection(system);
      Telephone p = new Telephone(c, console);
      MyUI ui = new MyUI(c);
      p.run(c);
   }
}