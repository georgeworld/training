package com.georgeinfo.paging;

import com.georgeinfo.db.ConnectionManager;
import com.georgeinfo.response.Result;
import com.georgeinfo.thread.ThreadUtils;
import com.georgeinfo.utils.JSON;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class MultiTablePagingService {
    private static final Logger LOG = LoggerFactory.getLogger(MultiTablePagingService.class);

    private Result<CountPagingInfo> countData(Integer pageSize) {
        Result<CountPagingInfo> result = Result.createSuccess();
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            List<Future<Long>> futureList = new ArrayList<>();
            //使用一个线程查询第1个表
            Future<Long> future = ThreadUtils.getExecutor().submit(() -> {
                try {
                    QueryRunner queryRunner = new QueryRunner();
                    Long count = queryRunner.query(conn, "select count(*) from test_table", new ScalarHandler<Long>());
                    return count;
                } catch (Exception ex) {
                    LOG.error("查询第一个表count时出现异常", ex);
                    return 0L;
                }
            });
            futureList.add(future);

            //使用一个线程查询第2个表
            Future<Long> future2 = ThreadUtils.getExecutor().submit(() -> {
                try {
                    QueryRunner queryRunner = new QueryRunner();
                    Long count = queryRunner.query(conn, "select count(*) from test_table2", new ScalarHandler<Long>());
                    return count;
                } catch (Exception ex) {
                    LOG.error("查询第2个表count时出现异常", ex);
                    return 0L;
                }
            });
            futureList.add(future);

            long count = 0L;
            CountPagingInfo cpi = new CountPagingInfo();
            for (Future<Long> f : futureList) {
                try {
                    count += f.get();
                } catch (Exception ex) {
                    LOG.error("累加查询出来的数据量时发生异常", ex);
                }
            }
            Map<Integer, PagingTableInfo> ptiMap = cpi.getPtiMap();

            //有了总记录以后，循环2类数据得到的count，计算每张表所负责的页码区间
            Result<Map<Integer, PagingTableInfo>> pageRangeResult = PagingHelper.fillPageRange(pageSize, ptiMap);
            if (!pageRangeResult.isSuccess()) {
                return result.failure().setMsg(pageRangeResult.getMsg());
            } else {
                cpi.setPtiMap(pageRangeResult.getData());
            }
            LOG.info("经过分页count计算后，每个数据段分配的页码区间是：{}", JSON.toJSONString(cpi));

            return result.success().setData(cpi).setDetail(pageRangeResult.getDetail());
        } catch (Exception ex) {
            LOG.error("查询分页count时出现异常", ex);
            return result.failure().setMsg("查询分页count时出现异常");
        }finally {
            ConnectionManager.getInstance().close(conn);
        }
    }


}