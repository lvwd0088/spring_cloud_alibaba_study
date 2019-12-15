package cn.lvwd.study;


import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author lvweida
 */
public final class ExceptionUtil {

    public static String handleException(BlockException exception){
        System.out.println(exception.getClass().getCanonicalName());
        return "系统繁忙，请稍后重试";
    }

}
