package com.OTPGenerator.SendOTPAndVerify.Service;

import com.OTPGenerator.SendOTPAndVerify.OTPResponseDTO.OtpResponse;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class OTPService {

    @Value("${otp.api-key}")
    private String otpApiKey;

    @Value("${otp.template-name}")
    private String otpTemplateName;

    @Value("${otp.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public OtpResponse sendOtp(String phoneNumber) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .pathSegment(otpApiKey, "SMS", phoneNumber, "AUTOGEN3", otpTemplateName)
                    .toUriString();
            String response = restTemplate.getForObject(url, String.class);
           // System.out.println("Raw response: " + response);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

            return objectMapper.readValue(response, OtpResponse.class);



        } catch (Exception e) {

            OtpResponse errorResponse = new OtpResponse();
            errorResponse.setStatus("Error");
            errorResponse.setDetails(e.getMessage());
            return errorResponse;
        }
    }

    public OtpResponse verifyOtp(String sessionId, String otp) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment(otpApiKey, "SMS", "VERIFY3", sessionId, otp)
                .toUriString();

        return restTemplate.getForObject(url, OtpResponse.class);
    }

}