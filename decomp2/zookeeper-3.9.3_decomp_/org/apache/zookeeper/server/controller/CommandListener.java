package org.apache.zookeeper.server.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.util.ServiceUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandListener {
   private static final Logger LOG = LoggerFactory.getLogger(CommandListener.class);
   private ZooKeeperServerController controller;
   private Server server;

   public CommandListener(ZooKeeperServerController controller, ControllerServerConfig config) {
      try {
         this.controller = controller;
         String host = config.getControllerAddress().getHostName();
         int port = config.getControllerAddress().getPort();
         this.server = new Server(port);
         LOG.info("CommandListener server host: {} with port: {}", host, port);
         this.server.setHandler(new CommandHandler());
         this.server.start();
      } catch (Exception ex) {
         LOG.error("Failed to instantiate CommandListener.", ex);
         ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
      }

   }

   public void close() {
      try {
         if (this.server != null) {
            this.server.stop();
            this.server = null;
         }
      } catch (Exception ex) {
         LOG.warn("Exception during shutdown CommandListener server", ex);
      }

   }

   private class CommandHandler extends AbstractHandler {
      private CommandHandler() {
      }

      public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
         String commandStr = request.getPathInfo().substring(1);
         response.setContentType("text/html;charset=utf-8");

         int responseCode;
         try {
            ControlCommand command = ControlCommand.parseUri(commandStr);
            CommandListener.this.controller.processCommand(command);
            baseRequest.setHandled(true);
            responseCode = 200;
         } catch (IllegalArgumentException ex) {
            CommandListener.LOG.error("Bad argument or command", ex);
            responseCode = 400;
         } catch (Exception ex) {
            CommandListener.LOG.error("Failed processing the request", ex);
            throw ex;
         }

         response.setStatus(responseCode);
         response.getWriter().println(commandStr);
         CommandListener.LOG.info("CommandListener processed command {} with response code {}", commandStr, responseCode);
      }
   }
}
