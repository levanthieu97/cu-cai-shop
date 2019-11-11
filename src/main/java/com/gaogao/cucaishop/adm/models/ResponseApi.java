package com.gaogao.cucaishop.adm.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gaogao.cucaishop.home.models.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseApi {

    @JsonProperty("user_info")
    private Users account;

    private String token;

    private String role;
}
