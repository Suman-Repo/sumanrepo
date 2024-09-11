package com.indusnet.totp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateOtpDto {

  @NotBlank
  private String key;

  @NotBlank
  private String otp;

  @NotNull
  private Long otpSteps;

  @NotNull
  private Long validationWindow;

  @NotBlank
  private String returnDigit;

}
