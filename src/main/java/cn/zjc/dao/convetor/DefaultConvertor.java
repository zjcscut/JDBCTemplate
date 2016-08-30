package cn.zjc.dao.convetor;

/**
 * @author zjc
 * @version 2016/8/30 23:44
 * @description 默认抽象转换器 F为源类型,T为目标类型
 */
public abstract class DefaultConvertor<F, T> implements Convertor<F, T> {


	protected abstract T convert(F f, Class<?> toType, Object... args);

}
