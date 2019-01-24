import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import lombok.extern.slf4j.Slf4j;
import mqtt.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.lang.Thread.sleep;

@Slf4j
public class Run {
    public static void main(String[] args) {
        System.out.println("main Start");
        addLogConfig();
        log.info("main Start");
        Client client = new Client();

        try {
            client.start();
        }catch (Exception ex)
        {
            log.info("main End");
            System.out.println("main End");
            return;
        }
        while (true)
        {
            int a=1;
        }
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
