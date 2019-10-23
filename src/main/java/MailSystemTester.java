import java.util.Scanner;

public class MailSystemTester
{
	private static final int MAILBOX_COUNT = 20;
	
   public static void init(){
      MailSystem system = new MailSystem(MAILBOX_COUNT);
      Scanner console = new Scanner(System.in);
      Telephone p = new Telephone(console);
      ConnectionObservable observable = new ConnectionObservable();
      observable.addObserver(p);
      Connection c = new Connection(system, observable);
      p.run(c);
   }
}
