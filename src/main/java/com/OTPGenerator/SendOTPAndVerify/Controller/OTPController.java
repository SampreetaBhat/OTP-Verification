package com.OTPGenerator.SendOTPAndVerify.Controller;

import com.OTPGenerator.SendOTPAndVerify.OTPResponseDTO.OtpResponse;
import com.OTPGenerator.SendOTPAndVerify.Service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @GetMapping("/otp")
    public ResponseEntity<OtpResponse> sendOtp(@RequestParam String phoneNumber) {
        OtpResponse response = otpService.sendOtp(phoneNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/otp")
    public ResponseEntity<OtpResponse> verifyOtp(@RequestParam String sessionId, @RequestParam String otp) {
        OtpResponse response = otpService.verifyOtp(sessionId, otp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

