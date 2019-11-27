import java.util.Scanner;

public class MailSystemTester
{
	private static final int MAILBOX_COUNT = 20;
	
   public static void main(String[] args){
      MailSystem system = new MailSystem(MAILBOX_COUNT);
      Scanner console = new Scanner(System.in);
      Connection connection = new Connection(system);
      Telephone phone = new Telephone(connection, console);
      MyUI ui = new MyUI(connection);
      phone.run(connection);
   }
}