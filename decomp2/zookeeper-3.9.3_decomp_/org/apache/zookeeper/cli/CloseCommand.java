package org.apache.zookeeper.cli;

public class CloseCommand extends CliCommand {
   public CloseCommand() {
      super("close", "");
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      return this;
   }

   public boolean exec() throws CliException {
      try {
         this.zk.close();
         return false;
      } catch (Exception ex) {
         throw new CliWrapperException(ex);
      }
   }
}
