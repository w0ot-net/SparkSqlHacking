package org.apache.zookeeper.cli;

public class CommandNotFoundException extends CliException {
   public CommandNotFoundException(String command) {
      super((String)("Command not found: " + command), 127);
   }
}
