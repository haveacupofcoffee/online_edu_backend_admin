package com.codingforfun.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.codingforfun.eduservice.mapper")
public class EduConfig {

     /**
     * SQL performance analysis plugin
     * only used in development and test environments, not in product environment
     * maxTime is the max execution time of SQL
     *
     * 3 environments
     *      * dev：development environment
     *      * test：test environment
     *      * prod：product environment
     */
    @Bean
    @Profile({"dev","test"})// enable in development and test environments
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(10000);//if a SQL's execution time over this value, this SQL would not be executed
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * Logic deletion plugin
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * Pagination Plugin
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
