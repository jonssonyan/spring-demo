package com.jonssonyan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket // 开启WebSocket相关功能
public class WebSocketServerConfig implements WebSocketConfigurer {
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Autowired
    private MyHandshakeInterceptor myHandshakeInterceptor;

    /**
     * 当客户端通过/connect和服务端连接通信时，使用MyWebSocketHandler处理会话
     *
     * @param webSocketHandlerRegistry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //添加一个处理器还有定义处理器的处理路径（注意：报403加.setAllowedOrigins("*")）
        webSocketHandlerRegistry.addHandler(myWebSocketHandler, "/ws").addInterceptors(myHandshakeInterceptor).setAllowedOrigins("*");
        /*
         * 在这里我们用到.withSockJS()，SockJS是spring用来处理浏览器对websocket的兼容性，
         * 目前浏览器支持websocket还不是很好，特别是IE11以下.
         * SockJS能根据浏览器能否支持websocket来提供三种方式用于websocket请求，
         * 三种方式分别是 WebSocket, HTTP Streaming以及 HTTP Long Polling
         */
        webSocketHandlerRegistry.addHandler(myWebSocketHandler, "/ws/sockjs").addInterceptors(myHandshakeInterceptor).withSockJS();
    }
}
