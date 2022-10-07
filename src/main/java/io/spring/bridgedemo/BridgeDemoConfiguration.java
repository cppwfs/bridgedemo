package io.spring.bridgedemo;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.task.batch.listener.support.TaskEventProperties;
import org.springframework.cloud.task.configuration.SimpleTaskAutoConfiguration;
import org.springframework.cloud.task.listener.TaskLifecycleListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(StreamBridge.class)
//@ConditionalOnBean(TaskLifecycleListener.class)
@ConditionalOnExpression("T(org.springframework.util.StringUtils).isEmpty('${spring.batch.job.jobName:}')")
// @checkstyle:off
//@ConditionalOnProperty(prefix = "spring.cloud.task.events", name = "enabled", havingValue = "true",
//        matchIfMissing = true)
// @checkstyle:on
@PropertySource("classpath:/org/springframework/cloud/task/application.properties")
@AutoConfigureBefore(BindingServiceConfiguration.class)
@AutoConfigureAfter(SimpleTaskAutoConfiguration.class)
@EnableConfigurationProperties(TaskEventProperties.class)
public class BridgeDemoConfiguration {

    @Bean
    public BridgeLifecycleListener bridgeLifecycleListener(StreamBridge streamBridge) {
        return new BridgeLifecycleListener(streamBridge);
    }
}
