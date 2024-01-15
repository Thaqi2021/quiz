package com.tn.quiz.model;

import lombok.Builder;

@Builder
public class JwtResponse {
    String tokens;

    public JwtResponse(String tokens) {
        this.tokens = tokens;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }
}
