package com.georgeinfo.excel.handler;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.property.ExcelWriteHeadProperty;
import org.apache.commons.beanutils.BeanUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ColumnSortRowWriteHandler implements RowWriteHandler {
    public void beforeRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                Integer rowIndex, Integer relativeRowIndex, Boolean isHead) {
        if (isHead) {
            // 获取传入的包含的列(字段的名字list),将headMap的索引按照传入的列的顺序重新放入,即可实现排序
            ExcelWriteHeadProperty excelWriteHeadProperty = writeSheetHolder.getExcelWriteHeadProperty();
            Map<Integer, Head> headMap = excelWriteHeadProperty.getHeadMap();
            Map<Integer, Head> newHeadMap = new LinkedHashMap<>();
            for (Head headObj : headMap.values()) {
                if (headObj == null) {
                    continue;
                }

                if (contains(headObj.getHeadNameList(), "工资")) {
                    newHeadMap.put(0, headObj);
                } else if (contains(headObj.getHeadNameList(), "姓")) {
                    newHeadMap.put(2, headObj);
                } else {
                    newHeadMap.put(headObj.getColumnIndex(), headObj);
                }
            }

            int index = 0;
            for (Head head : newHeadMap.values()) {
                head.setColumnIndex(index);
                index++;
            }

            excelWriteHeadProperty.setHeadMap(newHeadMap);

        }
    }

    private boolean contains(List<String> nameList, String name) {
        for (String n : nameList) {
            if (n.trim().equals(name.trim())) {
                return true;
            }
        }
        return false;
    }
}
