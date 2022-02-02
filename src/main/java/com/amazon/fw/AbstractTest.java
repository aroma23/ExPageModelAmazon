package com.amazon.fw;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

/**
 * This abstract class takes care of providing the things needed for a test suite
 *
 * @author Muthukumar Ramaiyah
 *
 */
public abstract class AbstractTest {
    protected static Logger logger;
    protected static Properties properties;
    protected Scenario scenario;
    private static volatile boolean areClientsInitialized = false;

    protected AbstractTest() {
        logger = LogManager.getLogger(this.getClass());
        initializeProperties();
    }
//
//    private static void checkSensitiveEmpty(String env) {
//        boolean exitFlag = false;
//        for (String sp: properties.getProperty("sensitive.list").trim().split("\\|"))
//            if (!properties.containsKey(sp) || properties.getProperty(sp).trim().isEmpty()) {
//                exitFlag = true;
//                System.err.printf("Property: %s should be added in %s.properties (Don't push to git) " +
//                        "or in mvn command with -D%s=<sensitivedata>%n", sp, env, sp);
//            }
//        if (exitFlag) System.exit(-1);
//    }
//
////    private static synchronized void initializeComponent(Component comp) {
////        if (comp.equals(Component.OPTICAL_MAPPING) && om == null)
////            om = new OM(properties);
////        else if (comp.equals(Component.DEVICE_PORTAL) && di == null)
////            di = new DI(properties);
////        else if (comp.equals(Component.DATA_VALVE) && dv == null)
////            dv = new DV(properties);
////        else if (comp.equals(Component.CONFIG) && config == null)
////            config = new Config(properties);
////        else if (comp.equals(Component.ALERTS) && alerts == null)
////            alerts = new Alerts(properties);
////        else if (comp.equals(Component.TELEMETRY) && telemetry == null)
////            telemetry = new Telemetry(properties);
////        else if (comp.equals(Component.PIPELINE) && pipeline == null)
////            pipeline = new Pipeline(properties);
////        else if (comp.equals(Component.FA) && fa == null)
////            fa = new FA(properties);
////    }
//
////    private static synchronized void initializeComponent(Component comp){
////        if (check4Null(om, comp, Component.OPTICAL_MAPPING))
////            om = new OM(properties);
////        else if (check4Null(di, comp, Component.DEVICE_PORTAL))
////            di = new DI(properties);
////        else if (check4Null(dv, comp, Component.DATA_VALVE))
////            dv = new DV(properties);
////        else if (check4Null(config, comp, Component.CONFIG))
////            config = new Config(properties);
////        else if (check4Null(alerts, comp, Component.ALERTS))
////            alerts = new Alerts(properties);
////        else if (check4Null(telemetry, comp, Component.TELEMETRY))
////            telemetry = new Telemetry(properties);
////        else if (check4Null(pipeline, comp, Component.PIPELINE))
////            pipeline = new Pipeline(properties);
////        else if (check4Null(fa, comp, Component.FA))
////            fa = new FA(properties);
////    }
////
////    private static boolean check4Null(Object compObj, Object enumObj, Component comp) {
////        return comp.equals(enumObj) && compObj == null;
////    }
//
//    private static synchronized void initializeComponent(Component comp) {
//        switch (comp) {
//            case OPTICAL_MAPPING:
//                om = initializeOnce(om, OM.class);
//                break;
//            case DEVICE_PORTAL:
//                di = initializeOnce(di, DI.class);
//                break;
//            case DATA_VALVE:
//                dv = initializeOnce(dv, DV.class);
//                break;
//            case CONFIG:
//                config = initializeOnce(config, Config.class);
//                break;
//            case ALERTS:
//                alerts = initializeOnce(alerts, Alerts.class);
//                break;
//            case TELEMETRY:
//                telemetry = initializeOnce(telemetry, Telemetry.class);
//                break;
//            case PIPELINE:
//                pipeline = initializeOnce(pipeline, Pipeline.class);
//                break;
//            case FA:
//                fa = initializeOnce(fa, FA.class);
//                break;
//        }
//    }
//
//    private static <T> T initializeOnce(T compInstance, Class<T> compClass) {
//        try {
//            if (compInstance == null)
//                return compClass.getConstructor(Properties.class).newInstance(properties);
//        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
//                IllegalAccessException ite) {
//            logger.error(ite.getMessage());
//        }
//        return compInstance;
//    }
//
    private static synchronized void initializeProperties() {
        if (!areClientsInitialized) {
            properties = new Properties();
            try {
                properties.load(Objects.requireNonNull(AbstractTest.class.getClassLoader().getResourceAsStream(
                        System.getProperty("test.env") + ".properties")));
                properties.putAll(System.getProperties());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            areClientsInitialized = true;
        }
    }
}
