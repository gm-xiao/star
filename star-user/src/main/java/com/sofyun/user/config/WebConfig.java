package com.sofyun.user.config;

import com.sofyun.common.util.IdUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebConfig
 * @Description TODO
 * @Author gm
 * @Date 2019/2/20 14:11
 * @Version 1.0
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public IdUtils getIdUtils(){
        return new IdUtils(0,0);
    }

}
