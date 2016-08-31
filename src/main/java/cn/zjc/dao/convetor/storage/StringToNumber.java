package cn.zjc.dao.convetor.storage;

import cn.zjc.dao.convetor.DefaultConvertor;
import cn.zjc.exception.ConverteException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zjc
 * @version 2016/8/30 23:57
 * @description String类型转化为Number类型的转换器
 */
public abstract class StringToNumber<T> extends DefaultConvertor<String, T> {

    protected boolean isNull(String str) {
        return StringUtils.isBlank(str) || "null".equalsIgnoreCase(str);
    }

    protected abstract T getDefaultValue();

    protected abstract T valueOf(String str);

    @Override
    public T convert(String s, Class<?> toType, String... args) {
        if (isNull(s)) {
            return toType.isPrimitive() ? getDefaultValue() : null;  //如果是原生类型,返回默认值
        } else if (!toType.isPrimitive() && isNull(s)) {
            return null;
        }
        try {
            return valueOf(s);
        } catch (Exception e) {
            throw new ConverteException(String.format(
                    "Fail to convet '%s' to <%s>",
                    s,
                    toType.getName()
            ), e);
        }
    }
}
