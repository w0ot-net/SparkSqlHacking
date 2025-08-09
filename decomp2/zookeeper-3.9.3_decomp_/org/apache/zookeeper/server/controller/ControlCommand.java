package org.apache.zookeeper.server.controller;

public class ControlCommand {
   public static final String ENDPOINT = "command";
   public static final String ENDPOINT_PREFIX = "command/";
   private Action action;
   private String parameter;

   public Action getAction() {
      return this.action;
   }

   protected String getParameter() {
      return this.parameter;
   }

   public ControlCommand(Action action) {
      this(action, (String)null);
   }

   public ControlCommand(Action action, String param) {
      this.action = action;
      this.parameter = param;
   }

   public static String createCommandUri(Action action, String parameter) {
      return "command/" + action.toString() + (parameter != null && !parameter.isEmpty() ? "/" + parameter : "");
   }

   public static ControlCommand parseUri(String commandUri) {
      if (commandUri == null) {
         throw new IllegalArgumentException("commandUri can't be null.");
      } else if (!commandUri.startsWith("command/")) {
         throw new IllegalArgumentException("Missing required prefix: command/");
      } else {
         String uri = commandUri.substring("command/".length());
         int separatorIndex = uri.indexOf(47);
         String name;
         String param;
         if (separatorIndex < 0) {
            name = uri;
            param = null;
         } else {
            name = uri.substring(0, separatorIndex);
            param = uri.substring(separatorIndex + 1);
         }

         return new ControlCommand(ControlCommand.Action.valueOf(name.toUpperCase()), param);
      }
   }

   public static enum Action {
      PING,
      SHUTDOWN,
      CLOSECONNECTION,
      EXPIRESESSION,
      REJECTCONNECTIONS,
      ADDDELAY,
      FAILREQUESTS,
      NORESPONSE,
      RESET,
      ELECTNEWLEADER;
   }
}
