package com.georgeinfo.dao;

import com.georgeinfo.model.TestTable;
import com.georgeinfo.model.TestTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestTableMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    long countByExample(TestTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    int deleteByExample(TestTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    int insert(TestTable row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    int insertSelective(TestTable row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    List<TestTable> selectByExample(TestTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    TestTable selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    int updateByExampleSelective(@Param("row") TestTable row, @Param("example") TestTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    int updateByExample(@Param("row") TestTable row, @Param("example") TestTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    int updateByPrimaryKeySelective(TestTable row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Thu May 25 01:33:45 CST 2023
     */
    int updateByPrimaryKey(TestTable row);
}