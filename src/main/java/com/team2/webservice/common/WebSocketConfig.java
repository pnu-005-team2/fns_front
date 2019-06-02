package com.team2.webservice.common;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 해당 경로로 SimpleBroker를 등록, SimpleBroker는 해당하는 경로를
        // Subscribe하는 client에게 메시지를 전달하는 작업 수행
        // /topic은 일대다, /queue는 일대일 통신에 사용
        registry.enableSimpleBroker("/topic", "queue");

        // 클라이언트에서 send 요청을 처리, /topic/hello 토픽에 대해 구독신청시, 실제 경로는 /app/topic/hello가 된다.
        registry.setApplicationDestinationPrefixes("/app");
    }

    //  handshake와 통신을 담당할 endpoint 지정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/room")
                .addInterceptors(new HttpHandshakeInterceptor())
                .withSockJS();
    }
}
