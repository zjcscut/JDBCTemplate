package cn.zjc.dao.convetor.storage;

/**
 * @author zhangjinci
 * @version 2016/9/1 9:46
 * @function
 */
public class StringToShort  extends StringToNumber<Short>{

    @Override
    protected Short getDefaultValue() {
        return 0;
    }

    @Override
    protected Short valueOf(String str) {
        return Short.valueOf(str);
    }
}
