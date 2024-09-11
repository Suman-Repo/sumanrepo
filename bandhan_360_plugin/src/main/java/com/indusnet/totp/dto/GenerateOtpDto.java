package com.indusnet.totp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GenerateOtpDto {

  @NotBlank
  private String key;

  private LocalDateTime currentTime;

  @NotNull
  private Long otpSteps;

  @NotBlank
  private String returnDigits;
}