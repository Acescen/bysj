package nuc.bysjxtglxt.domain;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.domain
 * @Description:
 * @Date: Created in 17:51 2019-12-17
 */
public class BysjResponse extends HashMap<String, Object> {


    private static final long serialVersionUID = -8713837118340960775L;

    public BysjResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public BysjResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public BysjResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    /**
     *  200
     * @return
     */
    public BysjResponse success() {
        this.code(HttpStatus.OK);
        return this;
    }

    /**
     * 500
     * @return
     */
    public BysjResponse fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public BysjResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
