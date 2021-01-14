package com.nwpu.melonbookkeeping.service.impl;

import com.nwpu.melonbookkeeping.config.WebApplicationConfig;
import com.nwpu.melonbookkeeping.controller.admin.param.SystemConfigParam;
import com.nwpu.melonbookkeeping.entity.Config;
import com.nwpu.melonbookkeeping.repository.ConfigRepository;
import com.nwpu.melonbookkeeping.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
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
    @Autowired
    WebApplicationConfig webApplicationConfig;

    /**
     * 获取所有配置项
     * @return 所有配置项
     */
    @Override
    public Map<String, String> getWebConfig() {
        Map<String, String> webConfig = new HashMap<>();
        List<Config> configList = configRepository.findAllByKind(0);
        for (Config config : configList) {
            webConfig.put(config.getKey(), config.getValue());
        }
        return webConfig;
    }

    /**
     * 保存配置项
     * @param configs 配置的参数
     * @return 保存结果
     */
    @Override
    public boolean saveWebConfig(Map<String, String> configs) {
        try {
            configs.forEach((key, value) -> {
                Config config = configRepository.findConfigByKey(key);
                if (config != null && !value.isBlank()) {
                    config.setValue(value);
                    configRepository.saveAndFlush(config);
                }
            });
            webApplicationConfig.configureViewResolvers(null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 更新API计数器
     */
    @Override
    public void updateApiCallsCount() {
        try {
            Config config = configRepository.findConfigByKey("apiCallsCount");
            config.setValue(String.valueOf(Integer.parseInt(config.getValue()) + 1));
        } catch (Exception e) {

        }
    }
}
