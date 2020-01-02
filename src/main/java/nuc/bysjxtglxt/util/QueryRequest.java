package nuc.bysjxtglxt.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.util
 * @Description:
 * @Date: Created in 9:41 2019-12-18
 */
@Data
public class QueryRequest implements Serializable {

    private int pageSize = 1;
    private int pageNum = 10;

}