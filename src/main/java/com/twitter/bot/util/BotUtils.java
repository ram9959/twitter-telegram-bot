package com.twitter.bot.util;

import java.util.Arrays;
import org.apache.camel.Exchange;
import twitter4j.Status;

public class BotUtils {
	
    public static enum TextType{
        NORMAL,
        HTML,
        Markdown,
        BigText
    }
	
	public void filterMessages(Exchange exchange) {
		
		Status status = exchange.getIn().getBody(Status.class);
		
		String[] keywords= {"listing", "flood", "amazing", "boom", "hodl", "greatest", "strong", "rocket", "moment", "list", 
				"impressive", "added", "aiming", "strong", "ada", "xrp", "vet", "vechain", "ripple",
				"early", "elon", "integrate", "integrating", "invest", "mana", "decent"};
		
		if(Arrays.stream(keywords).anyMatch(status.getText().toLowerCase()::contains))
			exchange.getIn().setHeader("isValid", "yes");
		else
			exchange.getIn().setHeader("isValid", "no");	
		
	}
	
}
