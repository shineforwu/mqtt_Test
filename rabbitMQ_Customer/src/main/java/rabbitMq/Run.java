package rabbitMq;

import java.io.File;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import lombok.extern.slf4j.Slf4j;


import org.slf4j.LoggerFactory;
import rabbitMq.Customer;
import tool.IniRead;

import java.io.File;
@Slf4j
public class Run {

    public static void main(String[] args) {
        System.out.println("main Start");
        addLogConfig();
        log.info("main Start");

        try {
            Customer.Run();

        }
        catch (Exception ex)
        {
            log.error( ex.getMessage());
        }
//        while(true)
//        {
//            int a=1;
//        }
        //log.info("main End");
        //System.out.println("main End");
    }
    static void  addLogConfig()
    {
        String path = new File("").getAbsolutePath() + File.separator + "config" + File.separator;
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure(path + "logback.xml");
        } catch (JoranException e) {
            e.printStackTrace();
        }
    }
}
