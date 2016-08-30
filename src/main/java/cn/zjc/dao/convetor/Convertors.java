package cn.zjc.dao.convetor;

/**
 * @author zjc
 * @version 2016/8/30 23:38
 * @description 转换器工厂
 */
public class Convertors {

	private static Convertors single = new Convertors();

	private Convertors() {
	}

	//单例
	public static Convertors getInstance(){
		return single;
	}
}
