//package com.rts.util;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
//import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
//import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//public class AutoCoding {
//    // 数据库连接字段配置
//    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/db2024?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true";
//    private static final String JDBC_USER_NAME = "root";
//    private static final String JDBC_PASSWORD = "root";
//
//    // 父工程名
//    private static final String PROJECT_PATH = "/com-common-service";
//
//    // 包名和 其他模块名称
//    private static final String PACKAGE_NAME = "/com/rts";
//    private static final String MODULE = "/cloud-provider-payment8001";
//
//    // 表名,多个表使用英文逗号分割
//    private static final String[] TBL_NAMES = {"t_pay"};
//
//    // 表名的前缀,从表生成代码时会去掉前缀
//    private static final String TABLE_PREFIX = "tbl_";
//
//    // 获取项目父目录
//    private static final String PARENT_PATH = System.getProperty("user.dir");
//
//    // 定义其他模块目录
//    private static final String OTHER_PATH = PARENT_PATH + "/" + MODULE + "/src/main";
//
//    /**
//     * 服务模块名称
//     */
//
//    // 各层包名
//    private static final String ENTITY_PATH = PARENT_PATH + "/entity/src/main/java/com/rts/entity";
//    private static final String MAPPER_PATH = OTHER_PATH + "/java/com/rts/mapper";
//    private static final String XML_PATH = OTHER_PATH + "/resources/com/rts/mapper";
//    private static final String SERVICE_PATH = OTHER_PATH + "/java/com/rts/service";
//    private static final String SERVICE_IMPL_PATH = OTHER_PATH + "/java/com/rts/service/impl";
//    private static final String CONTROLLER_PATH = OTHER_PATH + "/java/com/rts/controller";
//
//    public static void main(String[] args) {
//        // 定义 要生成的表名
//        String table = "t_pay";
//        // 定义其他模块名称
//        String module = "cloud-provider-payment8001";
//        // 获取项目父目录
//        String parent_path = System.getProperty("user.dir");
//        // 定义entity目录
//        String entity_path = parent_path + "/entity/src/main/java/com/rts/entity";
//        // 定义其他模块目录
//        String other_path = parent_path + "/" + module + "/src/main";
//        String mapper_path = other_path + "/java/com/rts/mapper";
//        String xml_path = other_path + "/resources/com/rts/mapper";
//        String service_path = other_path + "/java/com/rts/service";
//        String service_impl_path = other_path + "/java/com/rts/service/impl";
//        String controller_path = other_path + "/java/com/rts/controller";
//        // 数据源配置
//        DataSourceConfig.Builder dec = new DataSourceConfig.Builder(JDBC_URL, JDBC_USER_NAME, JDBC_PASSWORD)
//                .dbQuery(new MySqlQuery())
//                .schema("mybatis-plus")
//                .keyWordsHandler(new MySqlKeyWordsHandler())
//                .typeConvert(new ITypeConvert() {
//                    @Override
//                    public IColumnType processTypeConvert(@NotNull GlobalConfig globalConfig, @NotNull String fieldType) {
//                        String t = fieldType.toLowerCase();
//                        if (t.contains("timestamp") || t.contains("datetime")) {
//                            return DbColumnType.DATE;
//                        }
//                        if (t.contains("tinyint")) {
//                            return DbColumnType.BOOLEAN;
//                        }
//
//                        return new MySqlTypeConvert().processTypeConvert(globalConfig, fieldType);
//                    }
//                });
//        // 定义生成器对象
//        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dec);
//        fastAutoGenerator.globalConfig(
//                globalConfigBuilder -> globalConfigBuilder
//                    .disableOpenDir() // 不打开生成文件目录
//                    .outputDir(OTHER_PATH) // 指定输出目录,注意斜杠的表示
//                    .author("rts") // 设置注释的作者
//                    .commentDate("yyyy-MM-dd HH:mm:ss") // 设置注释的日期格式
//                    .dateType(DateType.TIME_PACK)   // 使用java8新的时间类型
//                    .enableSwagger()    // 开启swagger文档
//        );
//        /**
//         日期类型 DateType
//         DateType.ONLY_DATE 使用 java.util.date包下的 Date
//         DateType.SQL_PACK 使用 java.sql包下的 Date
//         DateType.TIME_PACK   因为会使用 java.time.LocalDateTime jdk1.8以上才支持  (推荐使用)
//         */
//
//        /**
//         * 3.包配置
//         */
//        fastAutoGenerator.packageConfig(
//                packageConfigBuilder -> packageConfigBuilder
//                        .parent(PACKAGE_NAME.replace('/', '.').substring(1))   // 设置父包名
//                        // .moduleName(MODULE_NAME) // 设置父包模块名
//                        .entity((ENTITY_OUTPUT_PATH).replace('/', '.').substring(1, (ENTITY_OUTPUT_PATH).length() - 1)) // 设置MVC下各个模块的包名
//                        .mapper((MAPPER_OUTPUT_PATH).replace('/', '.').substring(1, (MAPPER_OUTPUT_PATH).length() - 1))
//                        .service((SERVICE_OUTPUT_PATH).replace('/', '.').substring(1, (SERVICE_OUTPUT_PATH).length() - 1))
//                        .serviceImpl((SERVICE_IMPL_OUTPUT_PATH).replace('/', '.').substring(1, (SERVICE_IMPL_OUTPUT_PATH).length() - 1))
//                        .controller((CONTROLLER_OUTPUT_PATH).replace('/', '.').substring(1, (CONTROLLER_OUTPUT_PATH).length() - 1))
//                        .xml("mapper")
//                        .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + SERVICE_PROJECT_PATH + "\\src\\main" + XML_OUTPUT_PATH))// 设置XML资源文件的目录
//        );
//
//
////        // 定义生成器对象
////        AutoGenerator generator = new AutoGenerator();
////        // 全局配置
////        GlobalConfig gc = new GlobalConfig();
////        // 设置所有者
////        gc.setAuthor("rts");
////        // 每次生成后不打开目录
////        gc.setOpen(false);
////        // 设置service接口名字去掉I
////        gc.setServiceName("%sService");
////        generator.setGlobalConfig(gc);
//        // 全局配置
//        GlobalConfig globalConfig = new GlobalConfig.Builder()
//                // 每次生成后不打开目录
//                .disableOpenDir()
//                .outputDir("/src/main")
//                // 设置所有者
//                .author("rts")
//                .commentDate("yyyy-MM-dd hh:mm:ss")
//                .build();
////        DataSourceConfig dsc = new DataSourceConfig();
////        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
////        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/db2024?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true");
////        dsc.setDbType(DbType.MYSQL);
////        dsc.setUsername("root");
////        dsc.setPassword("root");
////        generator.setDataSource(dsc);
//        Map<String, String> pathInfo = new HashMap<>();
//        pathInfo.put("entity_path",entity_path);
//        pathInfo.put("mapper_path",mapper_path);
//        pathInfo.put("xml_path",xml_path);
//        pathInfo.put("service_path",service_path);
//        pathInfo.put("service_impl_path",service_impl_path);
//        pathInfo.put("controller_path",controller_path);
//
//        //包配置
//        new PackageConfig.Builder()
//            .parent("com.rts")
//            .moduleName(module)
//            .pathInfo(pathInfo)
////        pc.setParent("com.rts");
//        // 自定义各模块路径
//
//        pc.setPathInfo(pathInfo);
//        generator.setPackageInfo(pc);
//        // 策略配置
//        StrategyConfig sc = new StrategyConfig();
//        // 生成的表名
//        sc.setInclude(table);
//        sc.setRestControllerStyle(true);
//        sc.setEntityLombokModel(true);
//        sc.setNaming(NamingStrategy.underline_to_camel);
//        sc.setColumnNaming(NamingStrategy.underline_to_camel);
//        sc.setControllerMappingHyphenStyle(false);
//        generator.setStrategy(sc);
//        // 模板配置
//        generator.setTemplateEngine(new FreemarkerTemplateEngine());
//        // 执行代码生成
//        generator.execute();
//
////        FastAutoGenerator.create(url, username, password)
////                .globalConfig(builder -> {
////                    builder.author("abc") // 设置作者
////                            .enableSwagger() // 开启 swagger 模式
////                            .fileOverride() // 覆盖已生成文件
////                            .disableOpenDir() //禁止打开输出目录
////                            .outputDir(finalProjectPath + "/src/main/java"); // 指定输出目录
////                })
////                .packageConfig(builder -> {
////                    builder.parent("com.baomidou.mybatisplus.samples") // 设置父包名
////                            .moduleName("test") // 设置父包模块名
////                            .entity("model.entity") //设置entity包名
////                            .other("model.dto") // 设置dto包名
////                            .pathInfo(Collections.singletonMap(OutputFile.xml, finalProjectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径
////
////                })
////                .injectionConfig(consumer -> {
////                    Map<String, String> customFile = new HashMap<>();
////                    // DTO
////                    customFile.put("DTO.java", "/templates/entityDTO.java.ftl");
////                    consumer.customFile(customFile);
////                });
//    }
//}
