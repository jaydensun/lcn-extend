package com.sf.ground.lcn.extend;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService {
    @Value("${lcn.tx.manager.url:http://tx-manager/tx/manager/}")
    private String txUrl;

    @Override
    public String getTxUrl() {
        return txUrl;
    }
}
