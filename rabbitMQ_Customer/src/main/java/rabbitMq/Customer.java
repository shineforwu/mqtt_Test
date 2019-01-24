package rabbitMq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import tool.IniRead;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
@Slf4j
public  class Customer {
     private  static String QUEUE_NAME = "/parsed_data";
    private final static String Host = "180.101.204.103";
    private final static int Port =5672;
    public final static String USER_NAME = "guest";
    public final static String PASSWORD = "guest";
    //public final static String EXCHANGE_NAME = "direct_one_exchange";
    public  static String EXCHANGE_NAME = "direct_one_exchange";
    public final static String EXCHANGE_Type = "direct";
    public  static String ROUTING_KEY = "/parsed_data";
     public static void Run()throws IOException, TimeoutException,Exception {

         QUEUE_NAME= IniRead.read(IniRead.filePath,"QUEUE_NAME");
         EXCHANGE_NAME= IniRead.read(IniRead.filePath,"EXCHANGE_NAME");
         ROUTING_KEY= IniRead.read(IniRead.filePath,"ROUTING_KEY");

         // 创建连接工厂
         ConnectionFactory factory = new ConnectionFactory();
         //设置RabbitMQ地址
         factory.setHost(Host);
         factory.setPort(Port);
         factory.setUsername(USER_NAME);
         factory.setPassword(PASSWORD);
         //建立到代理服务器到连接
         Connection conn = factory.newConnection();
         //获得信道
         final Channel channel = conn.createChannel();
         //声明交换器
         channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_Type, true);
         //声明队列
         String queueName = channel.queueDeclare().getQueue();
         //绑定队列，通过键 hola 将队列和交换器绑定起来
         channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
         //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
         // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
         Consumer consumer = new DefaultConsumer(channel) {
             @Override
             public void handleDelivery(String consumerTag, Envelope envelope,
                                        AMQP.BasicProperties properties, byte[] body)
                     throws IOException {
                 String message = new String(body, "UTF-8");
                 log.info( "QUEUE_NAME:"+QUEUE_NAME);
                 log.info("message:"+message);
                 System.out.println( "QUEUE_NAME:"+QUEUE_NAME);
                 System.out.println("Customer Received '" + message + "'");
             }
         };
         //自动回复队列应答 -- RabbitMQ中的消息确认机制
         channel.basicConsume(QUEUE_NAME, true, consumer);

     }
}
