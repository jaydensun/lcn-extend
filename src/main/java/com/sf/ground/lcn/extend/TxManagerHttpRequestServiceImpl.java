package com.sf.ground.lcn.extend;

import com.codingapi.tx.netty.service.TxManagerHttpRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TxManagerHttpRequestServiceImpl implements TxManagerHttpRequestService {
    private static Logger logger = LoggerFactory.getLogger(TxManagerHttpRequestServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;


    @Bean
    public RestTemplate restTemplate(LoadBalancerInterceptor loadBalancerInterceptor) {
        RestTemplate template = new RestTemplate();
        SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory) template.getRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        List<ClientHttpRequestInterceptor> list = new ArrayList<>(
                template.getInterceptors());
        list.add(loadBalancerInterceptor);
        template.setInterceptors(list);
        return template;
    }

    @Override
    public String httpGet(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            logger.error("http get failed", e);
            if (url.toLowerCase().endsWith("getserver")) {
                return "{\"ip\":\"1.1.1.1\",\"port\":1111,\"heart\":5,\"delay\":5,\"compensateMaxWaitTime\":5000}";
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public String httpPost(String url, String params) {
        try {
            return restTemplate.postForObject(url, params, String.class);
        } catch (Exception e) {
            logger.error("http post failed", e);
            throw new RuntimeException(e);
        }
    }
}
