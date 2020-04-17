package com.codingforfun.eduservice;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author
 * @since 2018/12/13
 */
public class CodeGenerator {

    @Test
    public void run() {

        // 1、create code generator
        AutoGenerator mpg = new AutoGenerator();

        // 2、global configuration
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //set the out put directory of code, it's better to use absolute path
        gc.setOutputDir("D:\\Jason\\Projects\\online_education_parent\\service\\service_online_edu" + "/src/main/java");

        gc.setAuthor("qdl");
        gc.setOpen(false); //set if auto open the folder of the generated file
        gc.setFileOverride(false); //override the generated file, set to false since we need to write code to this file

        //UserServie
        gc.setServiceName("%sService");	//remove the start character 'I'

        gc.setIdType(IdType.ID_WORKER_STR); //primary key strategy,
        gc.setDateType(DateType.ONLY_DATE);//date type in entity class
        gc.setSwagger2(true);//support for Swagger2

        mpg.setGlobalConfig(gc);

        // 3、Datasource configuration
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://mysqlfortest.cw5qdk7eto66.ca-central-1.rds.amazonaws.com:3306/online_education?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("ARookie");
        dsc.setPassword("HelloMySQL");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、package configuration
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("eduservice"); //模块名
        //包  com.codingforfun.eduservice
        pc.setParent("com.codingforfun");
        //包  com.codingforfun.eduservice.controller
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、strategy configuration between entity object class and table
        StrategyConfig strategy = new StrategyConfig();

        strategy.setInclude("edu_course", "edu_chapter", "edu_course_description", "edu_section");

        strategy.setNaming(NamingStrategy.underline_to_camel);//column names of table to attribute names in entity class
        strategy.setTablePrefix(pc.getModuleName() + "_"); //

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、execute
        mpg.execute();
    }
}
