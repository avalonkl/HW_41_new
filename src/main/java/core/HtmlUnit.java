package core;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.*;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class HtmlUnit {
	static Properties p = new Properties();
	static WebClient driver;

	public static void main(String[] args) throws Exception {
		p.load(new FileInputStream("./input.properties"));
		Logger.getLogger("").setLevel(Level.OFF);
		
		System.out.println("Browser: HtmlUnit"); 
	    System.out.println("===================================================");
	    
	    final long start = System.currentTimeMillis();
	    
	    driver = new WebClient();
	    
	    HtmlPage index_page = driver.getPage(p.getProperty("url"));
	    System.out.println("Page URI:   " + index_page.getUrl());
	    System.out.println("Page Title: " + index_page.getTitleText());
	    
	    HtmlForm form = index_page.getFormByName(p.getProperty("form"));
	    
	    HtmlTextInput fname = index_page.getHtmlElementById(p.getProperty("fname_id"));
	    fname.setText(p.getProperty("fname_value"));
	    HtmlTextInput lname = index_page.getHtmlElementById(p.getProperty("lname_id"));
	    lname.setText(p.getProperty("lname_value"));
	    HtmlTextInput email = index_page.getHtmlElementById(p.getProperty("email_id"));
	    email.setText(p.getProperty("email_value"));
	    HtmlTextInput phone = index_page.getHtmlElementById(p.getProperty("phone_id"));
	    phone.setText(p.getProperty("phone_value"));
	    
	    HtmlSubmitInput button = form.getInputByValue("Submit");	    
	    HtmlPage confirmation_page = button.click();
	    
	    System.out.println("===================================================");
	    System.out.println("Page URI:   " + confirmation_page.getUrl());
	    System.out.println("Page Title: " + confirmation_page.getTitleText());
	    
	    System.out.println("First Namw: " + confirmation_page.getHtmlElementById(p.getProperty("fname_id")).getTextContent().trim());
	    System.out.println("Last Name:  " + confirmation_page.getHtmlElementById(p.getProperty("lname_id")).getTextContent().trim());
	    System.out.println("E-mail:     " + confirmation_page.getHtmlElementById(p.getProperty("email_id")).getTextContent().trim());
	    System.out.println("Phone:      " + confirmation_page.getHtmlElementById(p.getProperty("phone_id")).getTextContent().trim());

	    driver.close();
	    
	    final long finish = System.currentTimeMillis();
	    
	    System.out.println("Response time: " + (finish - start) / 1000.0);
	}

}
