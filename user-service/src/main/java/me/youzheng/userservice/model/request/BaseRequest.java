package me.youzheng.userservice.model.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BaseRequest {

    protected BaseRequest(final String rsvNo) {
        this.rsvNo = rsvNo;
    }

    private String rsvNo;

    public static BaseRequest of(final String rsvNo) {
        return new BaseRequest(rsvNo);
    }

}