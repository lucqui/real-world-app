package com.lsq.realWorldApplication;

import com.lsq.realWorldApplication.repository.InvoiceRepoIntegrationTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {InvoiceRepoIntegrationTestConfig.class, FlywayAutoConfiguration.FlywayConfiguration.class})
class RealWorldAppTests {

	@Test
	void contextLoads() {}

}