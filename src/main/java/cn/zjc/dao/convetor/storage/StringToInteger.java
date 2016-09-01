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


    //Integer的类型转换需要特殊处理,存在精度丢失的可能
    @Override
    protected Integer valueOf(String str) {
        return Integer.valueOf(str);
    }
}
