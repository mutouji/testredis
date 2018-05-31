package org.delphy.testredis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@Component
@SpringBootApplication
public class TestredisApplication {
	@Value("${redisson.address:}")
	private String address;
	@Value("${redisson.database:0}")
	private int database;
	@Value("${redisson.password:}")
	private String password;

	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		SingleServerConfig singleServerConfig = config.useSingleServer();
		if (!StringUtils.isEmpty(address)) {
			singleServerConfig.setAddress(address);
		}
		if (!StringUtils.isEmpty(password)) {
			singleServerConfig.setPassword(password);
		}
		singleServerConfig.setDatabase(database);
		return Redisson.create(config);
	}
	public static void main(String[] args) {
		SpringApplication.run(TestredisApplication.class, args);
	}
}
