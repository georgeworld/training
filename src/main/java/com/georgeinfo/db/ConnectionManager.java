package com.georgeinfo.db;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.georgeinfo.endetoolkit.EndeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionManager.class);
    private static ConnectionManager instance;

    DataSource dataSource;


    /**
     * 获取当前类的实例对象
     *
     * @return
     */
    public static ConnectionManager getInstance() {
        if (null == instance) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    /*
     * 读取配置文件
     * */
    private ConnectionManager() {
        //数据源配置
        Properties prop = new Properties();
        //读取配置文件
        InputStream is = ConnectionManager.class.getResourceAsStream("/druid.properties");
        try {
            prop.load(is);
            //解密密文数据库密码
            String encPassword = prop.getProperty("password");
            if (StringUtils.startsWith(encPassword, "ENC(") && StringUtils.endsWith(encPassword, ")")) {
                encPassword = StringUtils.removeStart(encPassword, "ENC(");
                encPassword = StringUtils.removeEnd(encPassword, ")");
                String pwd = EndeService.decrypt(encPassword);
                prop.setProperty("password", pwd);
            }
        } catch (IOException e) {
            LOG.error("创建数据库连接时发生异常", e);
        }
        try {
            //返回的是DataSource
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            LOG.error("创建数据库连接池时出现异常", e);
        }
    }

    /**
     * 关闭链接
     *
     * @return
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("创建数据库连接时出现异常", e);
        }
        return conn;
    }

    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception ex) {
                LOG.error("关闭数据库连接时出现异常", ex);
            }
        }
    }
}
