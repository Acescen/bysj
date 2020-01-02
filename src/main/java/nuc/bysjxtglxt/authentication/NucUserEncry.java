package nuc.bysjxtglxt.authentication;

import nuc.bysjxtglxt.domain.NucUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.authentication
 * @Description:  密码处理
 * @Date: Created in 10:20 2019-12-17
 */
public class NucUserEncry {

    /**
     * 密码盐
     */
    public final static String SALT = "李亮NB";

    //指定散列算法为md5
    private static String algorithmName = "MD5";
    //散列迭代次数
    private static final int hashIterations = 2;

    public static NucUser encry(NucUser nucUser) {
        String newPassword =
                new SimpleHash(algorithmName, nucUser.getPassword(),
                        ByteSource.Util.bytes(SALT+nucUser.getStuOrWorId()), hashIterations).toHex();

        nucUser.setPassword(newPassword);
        return nucUser;
    }


}
