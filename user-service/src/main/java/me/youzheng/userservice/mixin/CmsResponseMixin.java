package me.youzheng.userservice.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface CmsResponseMixin {

    @JsonProperty("reservationNo")
    String getRsvNo();

}
