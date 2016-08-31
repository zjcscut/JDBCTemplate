package cn.zjc.dao.convetor.storage;

/**
 * @author zhangjinci
 * @version 2016/8/31 21:55
 * @function
 */
public class StringToDouble extends StringToNumber<Double>{

    @Override
    protected Double getDefaultValue() {
        return 0D;
    }

    @Override
    protected Double valueOf(String str) {
        return Double.valueOf(str);
    }
}
