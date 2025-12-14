package io.lifecore.lifecore_finance_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.lifecore.finance", "io.lifecore.lifecore_finance_app"})
public class LifecoreFinanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifecoreFinanceAppApplication.class, args);
	}

}
