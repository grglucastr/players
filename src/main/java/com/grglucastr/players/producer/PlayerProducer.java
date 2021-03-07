package com.grglucastr.players.producer;

public interface PlayerProducer {
    void postContentInTopic(String content);
}