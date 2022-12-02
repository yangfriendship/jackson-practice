package me.youzheng.userservice.controller;

import lombok.RequiredArgsConstructor;
import me.youzheng.userservice.converter.ResponseConverter;
import me.youzheng.userservice.model.ClientType;
import me.youzheng.userservice.model.request.BaseRequest;
import me.youzheng.userservice.model.response.HomePageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final ResponseConverter responseConverter;

    @PostMapping("/api/{clientType}/test1")
    public ResponseEntity<HomePageResponse> test1(@RequestBody final BaseRequest request, @PathVariable final ClientType clientType) {
        // logic..
        return ResponseEntity.ok(this.responseConverter.convert(new HomePageResponse(request.getRsvNo()), clientType));
    }

}
