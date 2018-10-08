package br.ufrn.imd.residencia.springmvchomerun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="br.ufrn.imd.residencia")
@EntityScan("br.ufrn.imd.residencia.model")
@EnableJpaRepositories("br.ufrn.imd.residencia.repository")
public class SpringMvcHomerunApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcHomerunApplication.class, args);
	}
}
