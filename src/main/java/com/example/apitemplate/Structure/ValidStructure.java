package com.example.apitemplate.Structure;

import com.example.apitemplate.Response.ValidDeleteResponse;
import com.example.apitemplate.Response.ValidGetResponse;
import com.example.apitemplate.Response.ValidPostResponse;
import com.example.apitemplate.Response.ValidPutResponse;
import tools.jackson.core.type.TypeReference;

public interface ValidStructure {
    public Class<? extends ValidGetResponse> validGetResponseType();
    public Class<? extends ValidPostResponse> validPostResponseType();
    public Class<? extends ValidPutResponse> validPutResponseType();
    public Class<? extends ValidDeleteResponse> validDeleteResponseType();
}
