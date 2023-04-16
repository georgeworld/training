package com.georgeinfo.excel.handler;

import com.alibaba.excel.metadata.data.DataFormatData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class HeadStyleHandler extends HorizontalCellStyleStrategy {
    private static final Logger logger = LoggerFactory.getLogger(HeadStyleHandler.class);

    //设置头样式
    @Override
    protected void setHeadCellStyle(CellWriteHandlerContext context) {
        logger.info("\n进入了表头样式设置器");
        WriteCellStyle wcs = new WriteCellStyle();
        DataFormatData dataFormatData = new DataFormatData();
        dataFormatData.setFormat("0.00");
        dataFormatData.setIndex((short) 49);
        wcs.setDataFormatData(dataFormatData);
        WriteCellData<?> cellData = context.getFirstCellData();
        WriteCellStyle.merge(wcs, cellData.getOrCreateStyle());
    }
}