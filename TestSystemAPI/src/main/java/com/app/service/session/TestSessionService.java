package com.app.service.session;

import com.app.model.Test;
import org.springframework.stereotype.Service;

@Service
public class TestSessionService {

   public Long startTestSession(Long userId, Test testId) {
    return null;
   }

   public void addResponseToSession(Long sessionId, Long questionId, String response) {

   }

   public void completeTestSession(Long sessionId) {

   }
}
