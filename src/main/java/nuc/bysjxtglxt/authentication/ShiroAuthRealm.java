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

        System.out.println("AuthorizationInfo  授权  "+user);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String role = user.getUserRole();
        if ("管理员".equals(role)) {
            authorizationInfo.addRole("admin");
            authorizationInfo.addStringPermission("");
        } else if ("教师".equals(role)) {
            authorizationInfo.addRole("student");

        } else if ("学生".equals(role)) {
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


        String stuOrWorId = usernamePasswordToken.getUsername();

        NucUser nucUser=nucUserService.findNucUserByStuOrWorId(stuOrWorId);

        System.out.println("AuthenticationInfo  认证  "+nucUser);

        if (nucUser == null) {
            throw new AuthenticationException("账号或密码错误");
        }

        if ("不可用".equals(nucUser.getStatus())) {
            throw new AuthenticationException("该账号被冻结");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                nucUser,
                nucUser.getPassword(),
                ByteSource.Util.bytes(NucUserEncry.SALT+nucUser.getStuOrWorId()),
                getName()
        );

        return authenticationInfo;
    }
}