package net.engineeringdigest.journal.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalApplication {

	//RestAPI:Representation State Tranfer/ Application Programming Interface

	public static void main(String[] args) {

		SpringApplication.run(JournalApplication.class, args);


	}

	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbfactory){
		return new MongoTransactionManager(dbfactory);
		//enables transactional operations for MongoDB.
	}

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
