package com.yd;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成器
 *
 * @author YD
 */
public class Run {

    public static void  main(String[] args) {
        // 设置包名
        String packageName = "com.yd";
        // user -> UserService, 设置成true: user -> IUserService
        boolean serviceNameStartWithI = true;
        // 生成你需要的表名，多个表名传数组
        String tableNames = "sys_menu";
        generateByTables(serviceNameStartWithI, packageName, tableNames);
    }

    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://119.23.71.201:3306/faster?characterEncoding=utf8")
                .setUsername("root")
                .setPassword("kDfh9GZ?Q(BE")
                .setDriverName("com.mysql.jdbc.Driver");

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setEntityTableFieldAnnotationEnable(true)   // 是否生成实体时，生成字段注解
                .setEntityLombokModel(true)     // 使用lombok注解
                .setNaming(NamingStrategy.underline_to_camel)     // 下划线转驼峰命名
                .setSuperEntityClass("com.yd.entity.BaseEntity")   // 设置公共实体类
                .setInclude(tableNames); // 需要生成的表
        // .setExclude(new String[]{"test"}); // 排除生成的表

        // 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor("YD")
                .setOutputDir("F:\\codeGen")  // 输出路径
                .setFileOverride(true)
                .setBaseResultMap(true)  // 生成 BaseResultMap
                .setBaseColumnList(true); // 生成通用查询结果列

        // service前缀加 I
        if (!serviceNameStartWithI) {
            config.setServiceName("I%sService");
        }
        //config.setDateType(DateType.ONLY_DATE);  // 设置时间类使用哪个包
        /* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
        // config.setMapperName("%sDao");
        // config.setXmlName("%sDao");
        // config.setServiceName("MP%sService");
        // config.setServiceImplName("%sServiceDiy");
        // config.setControllerName("%sAction");


        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                /*Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);*/
            }
        };

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/my_templates/controller.java.vm");
        /*tc.setService("/templatesMybatis/service.java.vm");
        tc.setServiceImpl("/templatesMybatis/serviceImpl.java.vm");
        tc.setEntity("/templatesMybatis/entity.java.vm");
        tc.setMapper("/templatesMybatis/mapper.java.vm");
        tc.setXml("/templatesMybatis/mapper.xml.vm");*/
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).setCfg(cfg)
                .setTemplate(tc)
                .execute();
    }

}
