package com.security.securityframework.tool;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;

public class Generator {
    public static void main(String[] args) {

        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig
                .Builder("jdbc:mysql://localhost:3306/chat?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=Hongkong",
                "root", "123456")
                .dbQuery(new MySqlQuery())
                .schema("mybatis-plus")
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler());

        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dataSourceConfig)
                .globalConfig(globalConfig -> {
                    globalConfig.outputDir("src\\main\\java\\")
                            .author("wei")
                            //.enableSwagger()
                            .disableOpenDir()
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd");
                })
                .packageConfig(packageConfig -> {
                    packageConfig.parent("com.generator")
                            .entity("beans.po")
                            .mapper("dao")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .other("other")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    System.getProperty("user.dir") + "/src/main/resources/mapper"));
                    ;
                })
                //.templateEngine()
                .templateConfig(velocity -> {
                    velocity.disable(TemplateType.XML)
                            .entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            //.xml("/templates/mapper.xml")
                            .xml("") //设置不生成xml
                            .controller("/templates/controller.java");
                })
                .strategyConfig(strategyConfig -> {
                    strategyConfig
                            .addInclude("t_content")
                            //.addExclude("")
                            .addTablePrefix("t_")
                            .entityBuilder()
                            .fileOverride()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .enableActiveRecord()
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            //.superClass(Test.class)
                            //.addSuperEntityColumns("created_time" ,"updated_time")
                            //.addTableFills(new Column("create_time", FieldFill.INSERT))
                            //.addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                            .idType(IdType.AUTO)
                            //.formatFileName("%sEntity")

                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .formatMapperFileName("%sDao")
                            .formatXmlFileName("%sXml")

                            .serviceBuilder()
                            .superServiceClass(IService.class)
                            .superServiceImplClass(ServiceImpl.class)
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")

                            .controllerBuilder()
                            .enableRestStyle()
                            .formatFileName("%sController")
                            .build();
                    ;
                });
        fastAutoGenerator.execute();
    }

}