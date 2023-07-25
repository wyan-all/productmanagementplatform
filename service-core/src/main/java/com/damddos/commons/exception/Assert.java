package com.damddos.commons.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.damddos.api.ProductCardApi;
import com.damddos.commons.ResponseCode;
import com.damddos.utils.StringUtil;

public class Assert {
	private static final Logger log = LogManager.getLogger(Assert.class.getCanonicalName());
	
    /**     	
     * 	断言对象不为空
     * @param object       对象为空则，抛出异常
     * @param responseEnum
     */
    public static void notNull(Object object, ResponseCode responseEnum) {
        if (object == null) {
            log.info("object is null...............");
            throw new BusinessException(responseEnum);
        }
    }

    /** 
     * 
     * @param object       对象不为空，则抛出异常
     * @param responseEnum
     */
    public static void isNull(Object object, ResponseCode responseEnum) {
        if (object != null) {
            log.info("object is not null......");
            throw new BusinessException(responseEnum);
        }
    }

    /**
               * 断言表达式为真
     * 如果不为真，则抛出异常
     *
     * @param expression 是否成功
     */
    public static void isTrue(boolean expression, ResponseCode responseEnum) {
        if (!expression) {
            log.info("fail...............");
            throw new BusinessException(responseEnum);
        }
    }

    /**
     * 断言两个对象不相等
     * 如果相等，则抛出异常
     *
     * @param m1
     * @param m2
     * @param responseEnum
     */
    public static void notEquals(Object m1, Object m2, ResponseCode responseEnum) {
        if (m1.equals(m2)) {
            log.info("equals...............");
            throw new BusinessException(responseEnum);
        }
    }

    /**
     * 断言两个对象相等
     * 如果不相等，则抛出异常
     *
     * @param m1
     * @param m2
     * @param responseEnum
     */
    public static void equals(Object m1, Object m2, ResponseCode responseEnum) {
        if (!m1.equals(m2)) {
            log.info("not equals...............");
            throw new BusinessException(responseEnum);
        }
    }

    /**
     * 断言参数不为空
     * 如果为空，则抛出异常
     *
     * @param s
     * @param responseEnum
     */
    public static void notEmpty(String s, ResponseCode responseEnum) {
        if (StringUtil.isEmpty(s)) {
            log.info("is empty...............");
            throw new BusinessException(responseEnum);
        }
    }
}


