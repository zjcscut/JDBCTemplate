package cn.zjc.jdbc.pool;

import cn.zjc.exception.DaoException;
import cn.zjc.jdbc.utils.PropertiesUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author zhangjinci
 * @version 2016/8/29 15:56
 * @function 使用druid连接池
 */
public class DruidDataSourceConfig {


    private static final Logger log = LoggerFactory.getLogger(DruidDataSourceConfig.class);

    private static DruidDataSourceConfig pool = new DruidDataSourceConfig();

    private static String location;

    public static void setLocation(String location) {
        DruidDataSourceConfig.location = location;
    }

    private DruidDataSourceConfig() {

    }

    public static DruidDataSourceConfig getInstance() {
        return pool;
    }

    private  DruidDataSource dds = null;


    private void init() {
        try {
            Properties properties = PropertiesUtils.loadPropertisFile(location);
            if (properties == null) {
                throw new DaoException("Properties file could not be null");
            }
            dds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            log.error("create DruidDataSource failed:" + e.getMessage());
        }
    }

    public DruidDataSource getDataSource(){
        init();
        return this.dds;
    }
}
