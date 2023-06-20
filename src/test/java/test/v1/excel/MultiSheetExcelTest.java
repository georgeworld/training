package test.v1.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.georgeinfo.excel.MultiSheetExcelBuilder;
import com.georgeinfo.excel.SheetInfo;
import com.georgeinfo.excel.SheetInfoBuilder;
import com.georgeinfo.excel.handler.*;
import com.georgeinfo.excel.template.DemoTemplate;
import com.georgeinfo.excel.template.MainTemplate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.function.Consumer;
import java.util.*;

public class MultiSheetExcelTest {
    private static final Logger logger = LoggerFactory.getLogger(MultiSheetExcelTest.class);

    @Test
    public void testAnnotation() throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = MainTemplate.class.getDeclaredFields();
        for (Field f : fields) {
            //获得字段上的注解：ExcelProperty
            ExcelProperty ep = f.getAnnotation(ExcelProperty.class);
            System.out.println("修改前的index值：" + ep.index());
            // 获取代理处理器
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(ep);
            // 获取注解的私有 memberValues 属性
            Field af = invocationHandler.getClass().getDeclaredField("memberValues");
            af.setAccessible(true);
            // 获取实例的属性map
            Map<String, Object> memberValues = (Map<String, Object>) af.get(invocationHandler);
            // 修改注解中的属性值
            memberValues.put("index", 111);

        }

        for (Field f : fields) {
            ExcelProperty ep = f.getAnnotation(ExcelProperty.class);

            int index = ep.index();
            System.out.println("修改后的index的值：" + index);
        }
    }

    @Test
    public void testAnnotation2() throws Exception {
        Field[] fields = MainTemplate.class.getDeclaredFields();
        for (Field f : fields) {
            //获得字段上的注解：ExcelProperty
            ExcelProperty ep = f.getAnnotation(ExcelProperty.class);
            System.out.println("修改前的index值：" + ep.index());
            modifyFileds(f, ExcelProperty.class, map -> {
                map.put("index", 123);
            });
        }

        for (Field f : fields) {
            ExcelProperty ep = f.getAnnotation(ExcelProperty.class);

            int index = ep.index();
            System.out.println("修改后的index的值：" + index);
        }
    }

    /**
     * 动态修改注解属性值的方式二
     */
    private void modifyFileds(Field field, Class annotationClazz, Consumer<Map> consumer) throws Exception {
        // 获取实体类字段的注解类
        Annotation annotation = field.getAnnotation(annotationClazz);
        // 将注解类生成一个代理对象
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        Field annotationValues = invocationHandler.getClass().getDeclaredField("memberValues");
        annotationValues.setAccessible(true);
        Map map = (Map) annotationValues.get(invocationHandler);
        consumer.accept(map);
    }

    @Test
    public void testEasyExcel() {
        String filePath = System.getProperty("user.dir") + File.separator + "excel-template.xlsx";

        List<SheetInfo> sheetList = new ArrayList();
        List sheetOneData = getFirstSheetData();
        sheetList.add(SheetInfoBuilder.setSheetName("第一个sheet")
                .setTemplateClass(MainTemplate.class)
                .setHandlerList(Arrays.asList(new HeadStyleHandler(),
                        new DropDownMenuHandler(getDropDownData()),
                        new ContentCellHandler(sheetOneData.size()),
                        new ColumnSortRowWriteHandler()))
                .setData(sheetOneData)
                .build());

        List sheetTwoData = getSencondSheetData();
        sheetList.add(SheetInfoBuilder.setSheetName("第二个sheet")
                .setTemplateClass(DemoTemplate.class)
                .setHandlerList(Arrays.asList(new NumberColumnSheetHandler(sheetTwoData.size())))
                .setData(sheetTwoData)
                .build());

        boolean result = MultiSheetExcelBuilder
                .setFilePath(filePath)
                .setSheetList(sheetList).build();
        logger.error("\n生成excel的结果：{}，生成的文件存放在：{}", result, filePath);
    }

    private LinkedHashMap<Integer, List<String>> getDropDownData() {
        LinkedHashMap<Integer, List<String>> map = new LinkedHashMap<>();
        map.put(3, Arrays.asList("上海", "北京", "广州"));
        return map;
    }


    private List getFirstSheetData() {
        List list = new ArrayList();
        list.add(new MainTemplate("刘", "德华", "2099899.89", "香港", "2023-06-02 23:48:01"));
        list.add(new MainTemplate("张", "学友", "998.1", "香港", "2023-06-02 23:50:02"));
        list.add(new MainTemplate("黎", "明", "19886.9", "香港", "2023-06-02 23:50:03"));
        list.add(new MainTemplate("郭", "富城", "3222900", "香港", "2023-06-02 23:50:04"));
        return list;
    }

    private List getSencondSheetData() {
        List list = new ArrayList();
        list.add(new DemoTemplate(1, "歌星"));
        list.add(new DemoTemplate(2, "影星"));
        list.add(new DemoTemplate(3, "综艺明星"));
        return list;
    }

}

