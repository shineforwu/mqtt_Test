import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class Run {
    public static void main(String[] args) {
        System.out.println("main Start");
        //String path = new File("").getAbsolutePath() + File.separator + "config" + File.separator;
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure("D:\\config\\" + "logback.xml");
        } catch (JoranException e) {
            e.printStackTrace();
        }
        log.info("aaaaaaaa");
        System.out.println("main End");
    }
}
