package com.georgeinfo.mapstruct;

import com.georgeinfo.exceldb.TestTableHeader;
import com.georgeinfo.model.TestTable;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TestTableMapper {
    List<TestTableHeader> ttList2List(List<TestTable> ttList);
}
