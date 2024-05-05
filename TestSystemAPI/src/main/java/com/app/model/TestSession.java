package com.app.model;

import java.util.HashMap;
import java.util.Map;

public class TestSession {
    private Long sessionId;
    private Long testId;
    private Map<Integer, String> responses;

    public TestSession(Long sessionId, Long testId) {
        this.sessionId = sessionId;
        this.testId =  testId;
        this.responses = new HashMap<>();
    }

    public Long getSessionId() {
        return sessionId;
    }



}
