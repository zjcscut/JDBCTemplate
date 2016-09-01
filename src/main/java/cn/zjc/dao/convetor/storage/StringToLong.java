package cn.zjc.dao.convetor.storage;

/**
 * @author zhangjinci
 * @version 2016/9/1 9:42
 * @function
 */
public class StringToLong  extends StringToNumber<Long>{

    @Override
    protected Long getDefaultValue() {
        return 0L;
    }

    @Override
    protected Long valueOf(String str) {
        return Long.valueOf(str);
    }
}
