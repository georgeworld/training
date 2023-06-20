package test.gittest;

import com.alibaba.excel.metadata.data.DataFormatData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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