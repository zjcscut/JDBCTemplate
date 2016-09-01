package cn.zjc.dao.convetor.storage;

/**
 * @author zhangjinci
 * @version 2016/9/1 9:44
 * @function
 */
public class StringToFloat  extends StringToNumber<Float>{

    @Override
    protected Float getDefaultValue() {
        return 0F;
    }

    @Override
    protected Float valueOf(String str) {
        return Float.valueOf(str);
    }
}
