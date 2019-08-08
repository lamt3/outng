package com.dapp.outng.common.http.response;

import com.dapp.outng.common.exception.OutngErrorResponse;
import lombok.Data;

@Data
public class BaseResponse {

    private OutngErrorResponse error;

    public BaseResponse() {}

    public BaseResponse(OutngErrorResponse error) {
        this.error = error;
    }
}
