import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
 
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
 
/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("valo")
public class MyUI extends UI {
	MailSystem system = new MailSystem(20);
    String input = "";
    Scanner console = new Scanner(input);
    Telephone p = new Telephone(console);
    Connection c = new Connection(system, p);
 
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
    	final HorizontalLayout base = new HorizontalLayout();
    	
        final VerticalLayout layout = new VerticalLayout();
         
        final TextField name = new TextField();
        name.setReadOnly(true);
        
        TextArea menu = new TextArea(); 
        menu.setReadOnly(true);
        menu.setValue(p.getResponse());
 
        Button button_number_1= new Button("1");
        button_number_1.addClickListener( e -> {
            name.setValue(name.getValue()+"1");
        });
        Button button_number_2 = new Button("2");
        button_number_2.addClickListener( e -> {
        	name.setValue(name.getValue()+"2");
        });
        Button button_number_3 = new Button("3");
        button_number_3.addClickListener( e -> {
        	name.setValue(name.getValue()+"3");
        });
        Button button_number_4 = new Button("4");
        button_number_4.addClickListener( e -> {
        	name.setValue(name.getValue()+"4");
        });
        Button button_number_5 = new Button("5");
        button_number_5.addClickListener( e -> {
        	name.setValue(name.getValue()+"5");
        });
        Button button_number_6 = new Button("6");
        button_number_6.addClickListener( e -> {
        	name.setValue(name.getValue()+"6");
        });
        Button button_number_7 = new Button("7");
        button_number_7.addClickListener( e -> {
        	name.setValue(name.getValue()+"7");
        });
        Button button_number_8 = new Button("8");
        button_number_8.addClickListener( e -> {
        	name.setValue(name.getValue()+"8");
        });
        Button button_number_9 = new Button("9");
        button_number_9.addClickListener( e -> {
        	name.setValue(name.getValue()+"9");
        });
        Button button_number_0 = new Button("0");
        button_number_0.addClickListener( e -> {
        	name.setValue(name.getValue()+"0");
        });
        
        final HorizontalLayout first_row = new HorizontalLayout();
        
        first_row.addComponents(button_number_1,button_number_2,button_number_3);
        
        final HorizontalLayout second_row = new HorizontalLayout();
        
        second_row.addComponents(button_number_4,button_number_5,button_number_6);

        final HorizontalLayout third_row = new HorizontalLayout();
        
        third_row.addComponents(button_number_7,button_number_8,button_number_9);
        
        final HorizontalLayout fourt_row = new HorizontalLayout();
        
        fourt_row.addComponents(button_number_0);
        
        Button call = new Button("CALL");
        call.addClickListener( e -> pressCall(name, menu));
        Button enter = new Button("enter");
        enter.addClickListener( e -> pressEnter(name, menu));
        Button hangup = new Button("HANGUP");
        hangup.addClickListener( e -> pressHangup(name, menu));
         
        final HorizontalLayout display = new HorizontalLayout();
        
        display.addComponents(name,call,hangup,enter);
        
        layout.addComponents(display, first_row,second_row, third_row, fourt_row);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        base.addComponents(layout, menu);
         
        setContent(base);
        
        
    }
    
    public void pressCall(TextField data, TextArea display) {
    	c.dial(data.getValue());
    	c.dial("#");
    	data.setValue("");
    	display.setValue(p.getResponse());
    }

    public void pressEnter(TextField data, TextArea display) {
    	c.dial(data.getValue());
    	data.setValue("");
    	display.setValue(p.getResponse());
    }
    
    public void pressHangup(TextField data, TextArea display) {
    	c.hangup();
    	display.setValue(p.getResponse());
    }
 
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}