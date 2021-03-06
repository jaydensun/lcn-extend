package com.sf.ground.lcn;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath*:META-INF/spring/*"})
@EnableDiscoveryClient
@SpringBootApplication(exclude = {RedisAutoConfiguration.class, DataSourceAutoConfiguration.class,
        TransactionAutoConfiguration.class, PersistenceExceptionTranslationAutoConfiguration.class})
@ComponentScan({"com.codingapi.tx", "com.sf.ground.lcn.extend"})
public class Main {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Main.class).web(false).run(args);
    }
}
