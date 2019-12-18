package nuc.bysjxtglxt.authentication;

import nuc.bysjxtglxt.domain.NucUser;
import nuc.bysjxtglxt.service.NucUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;



public class ShiroAuthRealm extends AuthorizingRealm {

    @Autowired
    private NucUserService nucUserService;

    /**
     *    授权
     * @param principals
     * @return AuthorizationInfo权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        NucUser user = (NucUser) principals.getPrimaryPrincipal();


        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String role = user.getUserRole();
        if (NucUser.ADMIN.equals(role)) {
            authorizationInfo.addRole("admin");
            authorizationInfo.addStringPermission("");

        } else if (NucUser.STUDENT.equals(role)) {
            authorizationInfo.addRole("student");

        } else if (NucUser.TEACHER.equals(role)) {
            authorizationInfo.addRole("teacher");

        }

        return authorizationInfo;
    }


    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;


        String phoneNum = usernamePasswordToken.getUsername();

        NucUser nucUser=nucUserService.findNucUserByPhoneNum(phoneNum);
        if (nucUser == null) {
            throw new AuthenticationException("账号或密码错误");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                nucUser,
                nucUser.getPassword(),
                ByteSource.Util.bytes(NucUserEncry.SALT+nucUser.getPhoneNum()),
                getName()
        );

        return authenticationInfo;
    }
}