package com.sicnu.liaoshijie.labEqmMS;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

/**
 * @author guangyin
 */
@RunWith(SpringJUnit4ClassRunner.class)
/// **
// * ContextConfiguration spring引用的properties位置需要放在src下。
// 测试时需要将app-context*.xml中的测试配置文件打开： <value>classpath:com/better517na/config/test-jdbc.properties</value>
// * <value>classpath:com/better517na/config/test-log_config.properties</value>
// 关闭外部配置文件 <value>/WEB-INF/config/jdbc.properties</value> <value>/WEB-INF/config/log_config.properties</value>
// */
@ContextConfiguration("/com/sicnu/liaoshijie/labEqmMS/config/spring/app-context*.xml")
public class BaseTest {
    /**
     * 添加字段注释.
     */
    protected Logger logger = Logger.getLogger(BaseTest.class);

    static {
        try {
            Log4jConfigurer.initLogging("WebRoot/WEB-INF/config/log4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }
}
