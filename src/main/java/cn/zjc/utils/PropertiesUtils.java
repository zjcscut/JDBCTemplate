package cn.zjc.utils;

import cn.zjc.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zhangjinci
 * @version 2016/8/29 15:42
 * @function 资源文件工具类
 */
public class PropertiesUtils {

    private static final Logger log = LoggerFactory.getLogger(PropertiesUtils.class);

    /**
     * 读取classpath下的资源文件
     * @param path
     * @return
     */
    public static Properties loadPropertisFile(String path) {
        if (null == path || "".equals(path)) {
            throw new DaoException("Properties file path could not be null:" + path);
        }

        if ("classpath:".startsWith(path)) {
            path = path.replace("classpath:", "");  //去掉多余的classpath
        }

        try (InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(path)) {

            Properties info = new Properties();
            info.load(in);
            return info;
        } catch (IOException e) {
            log.error("Load properties file failed:" + e.getMessage());

        }
        return null;
    }
}
