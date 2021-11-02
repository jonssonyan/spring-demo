package com.jonssonyan.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 和客户端链接成功的时候触发该方法
     *
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("和客户端建立连接");
    }

    /**
     * 和客户端建立连接后，处理客户端发送的请求
     *
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        Object content = webSocketMessage.getPayload();
        log.info(content.toString());
        // 发送消息给客户端
        webSocketSession.sendMessage(new TextMessage(fakeAi(content.toString())));
    }

    private static String fakeAi(String input) {
        if (input == null || "".equals(input)) {
            return "你说什么？没听清︎";
        }
        return input.replace('你', '我')
                .replace("吗", "")
                .replace('?', '!')
                .replace('？', '！');
    }

    /**
     * 和客户端连接失败的时候触发该方法
     *
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        webSocketSession.close(CloseStatus.SERVER_ERROR);
        log.error("连接异常", throwable);
    }

    /**
     * 和客户端断开连接的时候触发该方法
     *
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("断开连接");
    }

    /**
     * 是否支持部分消息：如果设置为true，那么一个大的或未知尺寸的消息将会被分割，并会收到多次消息（会通过多次调用方法handleMessage(WebSocketSession, WebSocketMessage). ）
     * 如果分为多条消息，那么可以通过一个api：org.springframework.web.socket.WebSocketMessage.isLast() 是否是某条消息的最后一部分。
     * 默认一般为false，消息不分割
     *
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
