package test.v1.exceldb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgeinfo.exceldb.ExcelUtil;
import com.georgeinfo.exceldb.TestTableHeader;
import com.georgeinfo.model.TestTable;
import com.georgeinfo.db.ConnectionManager;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.LongFunction;

public class ExportExcelUT {
    private static final Logger LOG = LoggerFactory.getLogger(ExportExcelUT.class);
    private static ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void readDbAndCreateExcel() throws InterruptedException {
        final long pageSize = 10;

        String pathName = "/Users/george/GeorgeProjects/study/training/temp/test-excel.xlsx";
        LongFunction<List<TestTableHeader>> function = (long pageNo) -> {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from test_table order by id asc limit ?,?";
            //查询一页的数据
            QueryRunner query = new QueryRunner();
            try {
                long start = pageSize * (pageNo - 1);
                List<TestTable> ttList = query.query(conn, sql, new BeanListHandler<TestTable>(TestTable.class), start, pageSize);

                List<TestTableHeader> list = new ArrayList<>();
                for (TestTable tt : ttList) {
                    TestTableHeader tth = new TestTableHeader();
                    try {
                        BeanUtils.copyProperties(tth, tt);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                    list.add(tth);
                }
                return list;
            } catch (SQLException e) {
                LOG.error("分页查询时出现异常", e);
                return new ArrayList<>();
            } finally {
                ConnectionManager.getInstance().close(conn);
            }
        };
        ExcelUtil.writeForParallel(pathName, TestTableHeader.class, 2, function);
    }

    public void writeTestTable() {
        Connection conn = null;
        String sql = "insert into test_table2(name,address) values (?,?)";
        try {
            conn = ConnectionManager.getInstance().getConnection();
            //开启事务
            conn.setAutoCommit(false);

            QueryRunner queryRunner = new QueryRunner();
            // 循环插入数据
            for (int i = 1000; i < 2000; i++) {
                int affectedRow = queryRunner.update(conn, sql, "上海用户" + i, "上海地址：" + i);
                LOG.info("出入数据：{}", affectedRow);
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                LOG.error("插入测试数据时发生异常", e);
            } finally {
                // 关闭连接
                ConnectionManager.getInstance().close(conn);
            }
        }
    }

    @Test
    public void readData() {
        Connection conn = null;
        String sql = "select * from test_table order by id asc limit 0,100";
        try {
            conn = ConnectionManager.getInstance().getConnection();
            QueryRunner queryRunner = new QueryRunner();
            List<Map<String, Object>> record = queryRunner.query(conn, sql, new MapListHandler());
            // 循环插入数据
            for (Map<String, Object> map : record) {
                LOG.info("读取到数据：{}", MAPPER.writeValueAsString(map));
            }
        } catch (Exception e) {
            LOG.error("读取数据时发生异常", e);
            // 关闭连接
            ConnectionManager.getInstance().close(conn);
        }
    }
}
