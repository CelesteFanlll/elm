package com.example.Achitecture.common.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;


//生成后别忘了在mapper里加注解！！！！！
//！！！！！！！！！！
public class CodeGenerator {

    public static void main(String[] args) {
        String url = "jdbc:mysql:///business";
        String username = "root";
        String password = "wangzhiquan1217";
        String author = "wzq";
        String outputDir = "D:\\Java\\code\\Achitecture\\src\\main\\java";
        String basePackage = "com.example.Achitecture";
        String moduleName = "sys";
        String mapperLocation = "D:\\Java\\code\\Achitecture\\src\\main\\resources\\mapper\\" + moduleName;
        String tableName = "business,customer,item,list_item,order,shop";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            ; // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
