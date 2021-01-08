package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.entity.Config;
import com.nwpu.melonbookkeeping.repository.ConfigRepository;
import com.nwpu.melonbookkeeping.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author noorall
 * @date 2021/1/79:56 下午
 * @Description: 网站全局配置实现层
 */

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    ConfigRepository configRepository;

    @Override
    public Map<String, String> getWebConfig() {
        Map<String, String> webConfig = new HashMap<>();
        List<Config> configList = configRepository.findAllByKind(0);
        for (Config config : configList) {
            webConfig.put(config.getKey(), config.getValue());
        }
        return webConfig;
    }
}
