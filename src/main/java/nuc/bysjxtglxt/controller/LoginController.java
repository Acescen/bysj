package nuc.bysjxtglxt.controller;

import nuc.bysjxtglxt.domain.BysjResponse;
import nuc.bysjxtglxt.domain.NucUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.controller
 * @Description:
 * @Date: Created in 19:52 2019-12-16
 */
@Controller
public class LoginController {

    @Value("${bysj.loginUrl}")
    private String loginUrl;

    @PostMapping("/login")
    @ResponseBody
    public BysjResponse login(HttpServletRequest request) throws Exception {
        BysjResponse bysjResponse = new BysjResponse();

        String exception = (String) request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "账号或密码错误";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "账号或密码错误";
            } else {
                msg = "else >> " + exception;
            }
        }
        bysjResponse.fail().message("登陆成功");

        if ("".equals(msg)) {
            bysjResponse.success().message("登陆成功");
            //获取当前用户
            NucUser user = (NucUser) SecurityUtils.getSubject().getPrincipal();
            //将密码去除
            user.setPassword("");

            List<String> permission = new ArrayList<>();
            String role = user.getUserRole();
            if (NucUser.ADMIN.equals(role)) {
                permission.add("");
            } else if (NucUser.STUDENT.equals(role)) {

            } else if (NucUser.TEACHER.equals(role)) {

            }

            bysjResponse.put("user", user);
        }
        return bysjResponse;
    }
}
