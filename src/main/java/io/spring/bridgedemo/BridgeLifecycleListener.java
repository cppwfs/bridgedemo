package io.spring.bridgedemo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.Ordered;

public class BridgeLifecycleListener
        implements ApplicationListener<ApplicationEvent>, SmartLifecycle, DisposableBean, Ordered {

    private StreamBridge streamBridge;

    public BridgeLifecycleListener (StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }
    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }

    @Override
    public void start() {
        System.out.println("****SAYING HELLO ****");
        this.streamBridge.send("howdy", "hello");
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
