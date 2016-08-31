package cn.zjc.dao.convetor;


import java.util.ArrayList;
import java.util.List;

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


	private static List<Class<?>> convertors = new ArrayList<>(); //存放所有转换器class

	static {
       convertors.add(cn.zjc.dao.convetor.storage.StringToNumber.class);

	}


}
