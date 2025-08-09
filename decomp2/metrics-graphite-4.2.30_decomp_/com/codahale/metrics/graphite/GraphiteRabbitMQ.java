package com.codahale.metrics.graphite;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultSocketConfigurator;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class GraphiteRabbitMQ implements GraphiteSender {
   private static final Integer DEFAULT_RABBIT_CONNECTION_TIMEOUT_MS = 500;
   private static final Integer DEFAULT_RABBIT_SOCKET_TIMEOUT_MS = 5000;
   private static final Integer DEFAULT_RABBIT_REQUESTED_HEARTBEAT_SEC = 10;
   private ConnectionFactory connectionFactory;
   private Connection connection;
   private Channel channel;
   private String exchange;
   private int failures;

   public GraphiteRabbitMQ(final ConnectionFactory connectionFactory, final String exchange) {
      this.connectionFactory = connectionFactory;
      this.exchange = exchange;
   }

   public GraphiteRabbitMQ(final String rabbitHost, final Integer rabbitPort, final String rabbitUsername, final String rabbitPassword, final String exchange) {
      this(rabbitHost, rabbitPort, rabbitUsername, rabbitPassword, exchange, DEFAULT_RABBIT_CONNECTION_TIMEOUT_MS, DEFAULT_RABBIT_SOCKET_TIMEOUT_MS, DEFAULT_RABBIT_REQUESTED_HEARTBEAT_SEC);
   }

   public GraphiteRabbitMQ(final String rabbitHost, final Integer rabbitPort, final String rabbitUsername, final String rabbitPassword, final String exchange, final Integer rabbitConnectionTimeoutMS, final Integer rabbitSocketTimeoutMS, final Integer rabbitRequestedHeartbeatInSeconds) {
      this.exchange = exchange;
      this.connectionFactory = new ConnectionFactory();
      this.connectionFactory.setSocketConfigurator(new DefaultSocketConfigurator() {
         public void configure(Socket socket) throws IOException {
            super.configure(socket);
            socket.setSoTimeout(rabbitSocketTimeoutMS);
         }
      });
      this.connectionFactory.setConnectionTimeout(rabbitConnectionTimeoutMS);
      this.connectionFactory.setRequestedHeartbeat(rabbitRequestedHeartbeatInSeconds);
      this.connectionFactory.setHost(rabbitHost);
      this.connectionFactory.setPort(rabbitPort);
      this.connectionFactory.setUsername(rabbitUsername);
      this.connectionFactory.setPassword(rabbitPassword);
   }

   public void connect() throws IllegalStateException, IOException {
      if (this.isConnected()) {
         throw new IllegalStateException("Already connected");
      } else {
         try {
            this.connection = this.connectionFactory.newConnection();
         } catch (TimeoutException e) {
            throw new IllegalStateException(e);
         }

         this.channel = this.connection.createChannel();
      }
   }

   public boolean isConnected() {
      return this.connection != null && this.connection.isOpen();
   }

   public void send(String name, String value, long timestamp) throws IOException {
      try {
         String sanitizedName = this.sanitize(name);
         String sanitizedValue = this.sanitize(value);
         String message = sanitizedName + ' ' + sanitizedValue + ' ' + Long.toString(timestamp) + '\n';
         this.channel.basicPublish(this.exchange, sanitizedName, (AMQP.BasicProperties)null, message.getBytes(StandardCharsets.UTF_8));
      } catch (IOException e) {
         ++this.failures;
         throw e;
      }
   }

   public void flush() throws IOException {
   }

   public void close() throws IOException {
      if (this.connection != null) {
         this.connection.close();
      }

   }

   public int getFailures() {
      return this.failures;
   }

   public String sanitize(String s) {
      return GraphiteSanitize.sanitize(s);
   }
}
