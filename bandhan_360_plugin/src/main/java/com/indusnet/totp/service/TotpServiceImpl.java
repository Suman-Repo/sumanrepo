package com.indusnet.totp.service;


import com.indusnet.totp.dto.GenerateOtpDto;
import com.indusnet.totp.dto.GenerateOtpUsingEpochDto;
import com.indusnet.totp.dto.ValidateOtpDto;
import com.indusnet.totp.util.TotpUtil;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TotpServiceImpl {

  public String generateOtp(GenerateOtpDto generateOtpDto) {
    ZoneId zoneId = ZoneId.systemDefault();
    long epoch = generateOtpDto.getCurrentTime().atZone(zoneId).toEpochSecond();
    long startTime = 0;
    long finalStep = (epoch - startTime) / generateOtpDto.getOtpSteps();
    String steps = Long.toHexString(finalStep).toUpperCase();
    return TotpUtil.generateTOTP(generateOtpDto.getKey(), steps, generateOtpDto.getReturnDigits());
  }

  public Map<String, String> validateOtp(ValidateOtpDto validateOtpDto) {
    Map<String, String> response = new HashMap<>();

    List<String> otpWindowValueList = new ArrayList<>();
    long divisorNumber = validateOtpDto.getValidationWindow() / validateOtpDto.getOtpSteps();
    long iterationCount = validateOtpDto.getValidationWindow() == divisorNumber * validateOtpDto.getOtpSteps() ? divisorNumber : divisorNumber + 1;
    LocalDateTime currentTime = LocalDateTime.now();
//        generating the other otps within the same validation window
    for (long i = 0; i < iterationCount; i++) {
      String generatedOtp = generateOtp(GenerateOtpDto.builder()
          .key(validateOtpDto.getKey())
          .currentTime(currentTime)
          .otpSteps(validateOtpDto.getOtpSteps())
          .returnDigits(validateOtpDto.getReturnDigit())
          .build());
      otpWindowValueList.add(generatedOtp);
      currentTime = currentTime.minusSeconds(validateOtpDto.getOtpSteps());
    }

    if (otpWindowValueList.contains(validateOtpDto.getOtp())) {
      response.put("status", "SUCCESS");
    } else {
      response.put("status", "FAILURE");
    }
    return response;
  }


  public String generateOtpfromEpoch(GenerateOtpUsingEpochDto generateOtpUsingEpochDto) {
    long startTime = 0;
    long finalStep = (Long.parseLong(generateOtpUsingEpochDto.getEpoch()) - startTime) / generateOtpUsingEpochDto.getOtpSteps();
    String steps = Long.toHexString(finalStep).toUpperCase();
    return TotpUtil.generateTOTP(generateOtpUsingEpochDto.getKey(), steps, generateOtpUsingEpochDto.getReturnDigits());
  }
}