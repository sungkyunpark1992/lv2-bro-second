package com.example.lv2brosecond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing//이 애너테이션을 추가해줘야 시간값을 사용할 수 있다.
@SpringBootApplication
public class Lv2BroSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lv2BroSecondApplication.class, args);
	}

}
