package org.zerock.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListener 을 활성화시키기 위함
public class GuestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestbookApplication.class, args);
	}

}
