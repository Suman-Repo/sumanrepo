package com.indusnet.totp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateOtpUsingEpochDto {

  @NotBlank
  private String key;

  @NotBlank
  private String epoch;

  @NotNull
  private Long otpSteps;

  @NotBlank
  private String returnDigits;
}

