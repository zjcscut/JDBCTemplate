package cn.zjc.dao.convetor.storage;

/**
 * @author zhangjinci
 * @version 2016/8/31 21:16
 * @function
 */
public class StringToInteger extends StringToNumber<Integer> {

    @Override
    protected Integer getDefaultValue() {
        return 0;
    }

    @Override
    protected Integer valueOf(String str) {
        return Integer.valueOf(str);
    }
}
