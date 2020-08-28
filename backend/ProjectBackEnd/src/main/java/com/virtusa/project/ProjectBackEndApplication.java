package com.virtusa.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.virtusa.project.been.Mail;

@SpringBootApplication
public class ProjectBackEndApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ProjectBackEndApplication.class, args);
		Mail mail = new Mail();
        mail.setContext(ctx);

	}

}
