package com.twitter.bot.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.twitter.bot.util.BotUtils;

@Component
public class ConsumeTweetsRoute extends RouteBuilder{
	
	@Value("${twitter.endpoint}")
	private String twitterEndPoint;

	@Override
	public void configure() throws Exception {
		
		from(twitterEndPoint)
			.log("${body}")
			.bean(BotUtils.class, "filterMessages")
			.choice()
				.when(header("isValid").isEqualTo("yes"))
					//+.bean(BotUtils.class, "formatMessage")
					.to("telegram:bots?chatId=1424906052")
			.end();
		
	}

}
