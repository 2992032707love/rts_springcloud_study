
package com.rts.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.baomidou.mybatisplus.generator.type.ITypeConvertHandler;
import com.baomidou.mybatisplus.generator.type.TypeRegistry;
import org.apache.ibatis.type.JdbcType;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 代码自动生成器
public class Generator {
    // 数据库连接字段配置
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/transactional?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER_NAME = "root";
    private static final String JDBC_PASSWORD = "root";

    // 父工程名
    private static final String PROJECT_PATH = "/rts_springcloud_study";

    // 包名和模块名
    private static final String PACKAGE_NAME = "/com/rts";
    private static final String SERVICE_PROJECT_PATH = "/Use-And-Invalidation-Of-Transaction-Annotations1119";

    private static final String SERVICE_ENTITY_PATH = "/entity";

    // 表名,多个表使用英文逗号分割
    private static final String[] TBL_NAMES = {"t_custom"};

    // 表名的前缀,从表生成代码时会去掉前缀
    private static final String TABLE_PREFIX = "t_";
    /**
     * 服务模块名称
     */

    // 各层包名
//    private static final String ENTITY_PATH = "entity";
//    private static final String MAPPER_PATH = "mapper";
//    private static final String XML_PATH = "resources/com/rts/mapper";
//    private static final String SERVICE_PATH = "service";
//    private static final String SERVICE_IMPL_PATH = "service/impl";
//    private static final String CONTROLLER_PATH = "controller";
    private static final String ENTITY_PATH = "entity";
    private static final String MAPPER_PATH = "/mapper/";
    private static final String XML_PATH = "/resources/com/rts/mapper/";
    private static final String SERVICE_PATH = "/service/";
    private static final String SERVICE_IMPL_PATH = "/service/impl/";
    private static final String CONTROLLER_PATH = "/controller/";


//    // 各层文件输出到模块, 没有则置空
//    /**
//     * Entity.java, Mapper.java, Mapper.xml输出模块路径
//     */
//    private static final String MAPPER_OUTPUT_MODULE = "module";
//    /**
//     * mapper.xml输出模块路径(需要注意放置的位置:默认从模块/src/main下开始)
//     */
//    private static final String XML_OUTPUT_MODULE = "module";
//    /**
//     * IService.java, serviceImpl.java输出模块路径
//     */
//    private static final String SERVICE_OUTPUT_MODULE = "module";
//    /**
//     * Controller.java输出模块路径
//     */
//    private static final String CONTROLLER_OUTPUT_MODULE = "module";

    // 自定义输出模板和位置
    // 文件位置输出模式: file output path = projectPath + XX_PATH  + XX_OUTPUT_MODULE
    /**
     * entity输出模板
     */
    private static final String ENTITY_TEMPLATE = "templates/entity.java";
    private static final String ENTITY_OUTPUT_PATH =  ENTITY_PATH ;

    private static final String ENTITY_MOUDLE_OUTPUT_PATH = "/java" + PACKAGE_NAME + "/" + ENTITY_PATH + "/";

    /**
     * mapper.xml输出模板
     */
    private static final String XML_TEMPLATE = "templates/mapper.xml";
    private static final String XML_OUTPUT_PATH = XML_PATH;
    /**
     * mapper.java输出模板
     */
    private static final String MAPPER_TEMPLATE = "templates/mapper.java";
    private static final String MAPPER_OUTPUT_PATH = MAPPER_PATH ;
    /**
     * service输出模板
     */
    private static final String SERVICE_TEMPLATE = "templates/service.java";
    private static final String SERVICE_OUTPUT_PATH = SERVICE_PATH ;
    /**
     * serviceImpl输出模板
     */
    private static final String SERVICE_IMPL_TEMPLATE = "templates/serviceImpl.java";
    private static final String SERVICE_IMPL_OUTPUT_PATH = SERVICE_IMPL_PATH ;
    /**
     * controller输出模板
     */
    private static final String CONTROLLER_TEMPLATE = "templates/controller.java";
    private static final String CONTROLLER_OUTPUT_PATH = CONTROLLER_PATH ;

    public static void main(String[] args) {

        // 获取当前工程路径(这里无需修改)
        String projectPath = System.getProperty("user.dir");


        /**
         * 1.数据库配置(设置数据源)
         配置数据库连接以及需要使用的字段
         */
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(JDBC_URL, JDBC_USER_NAME,
                JDBC_PASSWORD)
                .schema("mybatis-plus")
//                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert())
                .typeConvertHandler(new ITypeConvertHandler() {
                    @Override
                    public @NotNull IColumnType convert(GlobalConfig globalConfig, TypeRegistry typeRegistry, TableField.MetaInfo metaInfo) {
                        // 兼容旧版本转换成Integer
                        if (JdbcType.TINYINT == metaInfo.getJdbcType()) {
                            return DbColumnType.BOOLEAN;
                        }
                        return typeRegistry.getColumnType(metaInfo);
                    }
                })
                .keyWordsHandler(new MySqlKeyWordsHandler());
        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dataSourceConfigBuilder);

        /**
         * 2.全局配置
         */
        fastAutoGenerator.globalConfig(
                globalConfigBuilder -> globalConfigBuilder
//                        .fileOverride()     // 覆盖已生成文件
                        .disableOpenDir()   // 不打开生成文件目录
                        .outputDir(projectPath + SERVICE_PROJECT_PATH + "/src/main/java") // 指定输出目录,注意斜杠的表示
                        .author("rts") // 设置注释的作者
                        .commentDate("yyyy-MM-dd HH:mm:ss") // 设置注释的日期格式
                        .dateType(DateType.TIME_PACK)   // 使用java8新的时间类型
        );

        /**
         日期类型 DateType
         DateType.ONLY_DATE 使用 java.util.date包下的 Date
         DateType.SQL_PACK 使用 java.sql包下的 Date
         DateType.TIME_PACK   因为会使用 java.time.LocalDateTime jdk1.8以上才支持  (推荐使用)
         */


        /**
         * 3.包配置
         */
//        fastAutoGenerator.packageConfig(
//                packageConfigBuilder -> packageConfigBuilder
//                        .parent(PACKAGE_NAME.replace('/', '.').substring(1))   // 设置父包名
//                        // .moduleName(MODULE_NAME) // 设置父包模块名
//                        .entity(ENTITY_OUTPUT_PATH) // 设置MVC下各个模块的包名
//                        .mapper(MAPPER_OUTPUT_PATH)
//                        .service(SERVICE_OUTPUT_PATH)
//                        .serviceImpl(SERVICE_IMPL_OUTPUT_PATH)
//                        .controller(CONTROLLER_OUTPUT_PATH)
//                        .xml("mapper")
//                        .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + SERVICE_PROJECT_PATH + "\\src\\main" + XML_OUTPUT_PATH))// 设置XML资源文件的目录
//        );

        Map<OutputFile,String> map = new HashMap<>();
        map.put(OutputFile.xml, projectPath + SERVICE_PROJECT_PATH + "\\src\\main" + XML_OUTPUT_PATH);
        map.put(OutputFile.entity, projectPath + SERVICE_ENTITY_PATH + "\\src\\main" + ENTITY_MOUDLE_OUTPUT_PATH);

        fastAutoGenerator.packageConfig(
                packageConfigBuilder -> packageConfigBuilder
                        .parent(PACKAGE_NAME.replace('/', '.').substring(1))   // 设置父包名
                        // .moduleName(MODULE_NAME) // 设置父包模块名
                        .entity((ENTITY_OUTPUT_PATH)) // 设置MVC下各个模块的包名
                        .mapper((MAPPER_OUTPUT_PATH).replace('/', '.').substring(1, (MAPPER_OUTPUT_PATH).length() - 1))
                        .service((SERVICE_OUTPUT_PATH).replace('/', '.').substring(1, (SERVICE_OUTPUT_PATH).length() - 1))
                        .serviceImpl((SERVICE_IMPL_OUTPUT_PATH).replace('/', '.').substring(1, (SERVICE_IMPL_OUTPUT_PATH).length() - 1))
                        .controller((CONTROLLER_OUTPUT_PATH).replace('/', '.').substring(1, (CONTROLLER_OUTPUT_PATH).length() - 1))
                        .xml("mapper")
                        .pathInfo(map)
//                        .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + SERVICE_PROJECT_PATH + "\\src\\main" + XML_OUTPUT_PATH))// 设置XML资源文件的目录
//                        .pathInfo(Collections.singletonMap(OutputFile.entity, projectPath + SERVICE_ENTITY_PATH + "\\src\\main" + ENTITY_MOUDLE_OUTPUT_PATH))
        );

        /**
         * 4.模板配置
         */
        fastAutoGenerator.templateConfig(
                templateConfigBuilder -> templateConfigBuilder
                        // .disable(TemplateType.ENTITY)   // 禁用模板
                        // .disable(TemplateType.SERVICE)
                        // .disable(TemplateType.SERVICE_IMPL)
                        // .disable(TemplateType.MAPPER)
                        // .disable(TemplateType.CONTROLLER)
                        // .disable(TemplateType.XML)
                        .entity(ENTITY_TEMPLATE)   // 设置实体模板路径(JAVA)
                        .service(SERVICE_TEMPLATE) // 设置service模板路径
                        .serviceImpl(SERVICE_IMPL_TEMPLATE) // 设置serviceImpl模板路径
                        .controller(CONTROLLER_TEMPLATE)   // 设置controller模板路径
                        .mapper(MAPPER_TEMPLATE)   // 设置mapper模板路径
                        .xml(XML_TEMPLATE)   // 设置mapperXml模板路径
        );

        /**
         * 5.注入配置 TODO
         */
        InjectionConfig config = new InjectionConfig();

        /**
         * 6.策略配置
         */
        fastAutoGenerator.strategyConfig(
                strategyConfigBuilder -> strategyConfigBuilder
                        .enableCapitalMode()    // 开启大写命名
                        .enableSkipView()   // 开启跳过视图
                        .disableSqlFilter() // 禁用sql过滤
                        .addInclude(TBL_NAMES)  // 设置需要生成的表名
//                        .addTablePrefix(TABLE_PREFIX)   // 设置过滤表前缀
        );

        /**
         * 6.1 Entity策略配置
         */
        fastAutoGenerator.strategyConfig(
                strategyConfigBuilder -> strategyConfigBuilder.entityBuilder()
//                        .enableFileOverride()
                        .enableTableFieldAnnotation()   // 生成实体时生成字段的注解,包括@TableId注解等---
                        .naming(NamingStrategy.underline_to_camel)  // 数据库表和字段映射到实体的命名策略,为下划线转驼峰
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .idType(IdType.AUTO)    // 全局主键类型为AUTO(自增)
                        .enableLombok() // 支持lombok开启注解
                        .logicDeleteColumnName("deleted")   // 逻辑删除字段名(数据库)
                        .logicDeletePropertyName("deleted") // 逻辑删除属性名(实体)
                        .addTableFills(new Column("create_time", FieldFill.INSERT)) // 自动填充配置  create_time  update_time 两种方式
                        .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                        .versionColumnName("version")   // 开启乐观锁
//                        .disableSerialVersionUID()  // 禁用生成 serialVersionUID,默认值:true
                        .enableChainModel() // 开启实体类链式编程
                        .formatFileName("%s") // 实体名称格式化为XXX
        );

        /**
         * 6.2 Controller策略配置
         */
        fastAutoGenerator.strategyConfig(
                strategyConfigBuilder -> strategyConfigBuilder.controllerBuilder()
                        .enableRestStyle()  // 开启生成@RestController控制器
//                        .enableFileOverride()
                            // 开启驼峰转连字符 localhost:8080/hello_id_2
        );

        /**
         * 6.3 Service策略配置
         格式化service接口和实现类的文件名称,去掉默认的ServiceName前面的I ----
         */
        fastAutoGenerator.strategyConfig(
                strategyConfigBuilder -> strategyConfigBuilder.serviceBuilder()
//                        .enableFileOverride()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl"));

        /**
         * 6.4 Mapper策略配置
         格式化 mapper文件名,格式化xml实现类文件名称
         */
        fastAutoGenerator.strategyConfig(
                strategyConfigBuilder -> strategyConfigBuilder.mapperBuilder()
//                        .enableFileOverride()
                        .enableMapperAnnotation()   // 开启 @Mapper 注解
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapper"));

        /** 7.生成代码
         *
         */
        // 使用Freemarker引擎模板,默认的是Velocity引擎模板
        fastAutoGenerator.templateEngine(new FreemarkerTemplateEngine());
        fastAutoGenerator.execute();

    }
}
