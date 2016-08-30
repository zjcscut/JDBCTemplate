package cn.zjc.dao.convetor.storage;

import cn.zjc.dao.convetor.DefaultConvertor;

/**
 * @author zjc
 * @version 2016/8/30 23:57
 * @description String类型转化为Number类型的转换器
 */
public class StringToNumber<T> extends DefaultConvertor<String,T> {

	@Override
	protected T convert(String s, Class<?> toType, Object... args) {
		return null;
	}
}
