package com.example.Achitecture.sys.controller;

import com.example.Achitecture.common.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Token的控制器")
@RequestMapping("/sys/token")
public class TokenController {

    @ApiOperation("检查Token合法性")
    @PostMapping("/check")
    public boolean check(@RequestBody String token){

        return JwtUtil.checkToken(token);



    }
}
