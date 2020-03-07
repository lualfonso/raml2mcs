package com.mcs.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class StoreApplication implements CommandLineRunner {
    
    private static final Logger log = LoggerFactory.getLogger(StoreApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

    @Override
    public void run(String... args) {
        log.info("StartApplication...");
        runJDBC();
    }
	void runJDBC() {
	log.info("Creating tables for testing...");
         
        jdbcTemplate.execute("DROP TABLE IF EXISTS invoice ");
        jdbcTemplate.execute("CREATE TABLE invoice(" +        
        "invoice_id  int , " + 
        "date  date, " + 
        "client  int , " +  
        "PRIMARY KEY (`invoice_id`) )");
         
        jdbcTemplate.execute("DROP TABLE IF EXISTS invoice_detail ");
        jdbcTemplate.execute("CREATE TABLE invoice_detail(" +        
        "invoice_id  int , " + 
        "detail_id  int , " + 
        "amount  int , " +  
        "PRIMARY KEY (`invoice_id`, `detail_id`) )");
         
        jdbcTemplate.execute("DROP TABLE IF EXISTS product ");
        jdbcTemplate.execute("CREATE TABLE product(" +        
        "product_id  int , " + 
        "description  VARCHAR(255), " +  
        "PRIMARY KEY (`product_id`) )");
         
        jdbcTemplate.execute("DROP TABLE IF EXISTS client ");
        jdbcTemplate.execute("CREATE TABLE client(" +        
        "client_id  int , " + 
        "name  VARCHAR(255), " + 
        "age  int , " +  
        "PRIMARY KEY (`client_id`) )");
        
	}
}
