package com.mcs.usermcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class UsermcsApplication implements CommandLineRunner {
    
    private static final Logger log = LoggerFactory.getLogger(UsermcsApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(UsermcsApplication.class, args);
	}

    @Override
    public void run(String... args) {
        log.info("StartApplication...");
        runJDBC();
    }
	void runJDBC() {
	log.info("Creating tables for testing...");
         
        jdbcTemplate.execute("DROP TABLE IF EXISTS user ");
        jdbcTemplate.execute("CREATE TABLE user(" +
        "user_id int NOT NULL AUTO_INCREMENT ," + 
        "user_age  int , " +  
        "user_name  VARCHAR(255), " +  
        "user_address  VARCHAR(255), " +  
        "PRIMARY KEY (`user_id`) )");
        
	}
}
