package com.jiezh.pub.util.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: DV
 * @Description: 数据验证
 * @author 陈继龙 E-mail: cqcnihao@139.com
 * @date 2016年6月1日 下午3:16:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface DV {

    // 是否可以为空
    boolean nullable() default false;

    // 最大长度
    int maxLength() default 0;

    // 最小长度
    int minLength() default 0;

    // 自定义正则验证
    String regexExpression() default "";

    // 参数或者字段描述,这样能够显示友好的异常信息
    String description() default "";

}
