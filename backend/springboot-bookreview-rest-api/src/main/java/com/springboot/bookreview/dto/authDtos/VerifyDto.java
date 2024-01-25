package com.springboot.bookreview.dto.authDtos;

public class VerifyDto {
    private boolean verified;
    private Long userId;

    public VerifyDto() {
    }

    public VerifyDto(boolean verified, Long userId) {
        this.verified = verified;
        this.userId = userId;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
