package com.OTPGenerator.SendOTPAndVerify.OTPResponseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class OtpResponse {

        @JsonProperty("Status")
        private String status;

        @JsonProperty("Details")
        private String details;


}

