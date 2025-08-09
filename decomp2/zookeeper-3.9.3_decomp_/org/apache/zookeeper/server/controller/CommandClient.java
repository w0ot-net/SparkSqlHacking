package org.apache.zookeeper.server.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandClient {
   private final int requestTimeoutInMs;
   private static final int DEFAULT_TIMEOUT = 10000;
   private static final Logger LOG = LoggerFactory.getLogger(CommandClient.class);
   private final int hostPort;
   private final String hostName;
   private HttpClient client;
   private boolean started;

   public CommandClient(int localHostPort, int requestTimeoutInMs) {
      this.started = false;
      this.client = new HttpClient();
      this.requestTimeoutInMs = requestTimeoutInMs;
      this.hostName = "localhost";
      this.hostPort = localHostPort;
   }

   public CommandClient(InetSocketAddress hostAddress, int requestTimeoutInMs) {
      this.started = false;
      this.client = new HttpClient();
      this.requestTimeoutInMs = requestTimeoutInMs;
      this.hostName = hostAddress.getHostName();
      this.hostPort = hostAddress.getPort();
   }

   public CommandClient(int localhostPort) {
      this(localhostPort, 10000);
   }

   public synchronized void close() {
      try {
         if (this.client != null) {
            this.client.stop();
            this.client = null;
         }
      } catch (Exception ex) {
         LOG.warn("Exception during shutdown", ex);
      }

   }

   public boolean trySendCommand(ControlCommand.Action action) {
      return this.trySendCommand(action, (String)null);
   }

   public boolean trySendCommand(ControlCommand.Action action, String commandParameter) {
      try {
         if (!this.started) {
            this.client.start();
            this.started = true;
         }

         ContentResponse response = this.sendCommand(action, commandParameter);
         LOG.info("Received {} response from the server", response);
         return response.getStatus() == 200;
      } catch (IOException | InterruptedException ex) {
         LOG.warn("Failed to get response from server", ex);
      } catch (Exception ex) {
         LOG.error("Unknown exception when sending command", ex);
      }

      return false;
   }

   public ContentResponse sendCommand(ControlCommand.Action action, String commandParameter) throws Exception {
      String command = String.format("%s%s:%s/%s", "http://", this.hostName, this.hostPort, ControlCommand.createCommandUri(action, commandParameter));
      ContentResponse response = this.client.newRequest(command).timeout((long)this.requestTimeoutInMs, TimeUnit.MILLISECONDS).send();
      LOG.info("Sent command {}", command);
      LOG.info("Response body {}", new String(response.getContent(), StandardCharsets.UTF_8));
      return response;
   }
}
