package com.sf.ground.lcn.extend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath*:META-INF/spring/*"})
@EnableDiscoveryClient
@SpringBootApplication(exclude = {RedisAutoConfiguration.class, DataSourceAutoConfiguration.class,
        TransactionAutoConfiguration.class})
public class Main {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Main.class).web(false).run(args);
    }
}
