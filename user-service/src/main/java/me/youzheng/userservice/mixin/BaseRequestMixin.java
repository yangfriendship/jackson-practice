package me.youzheng.userservice.mixin;

import com.fasterxml.jackson.annotation.JsonAlias;

public interface BaseRequestMixin {

    @JsonAlias({"rsvNo", "reservationNo"})
    String getRsvNo();

}
