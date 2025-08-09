package org.apache.zookeeper.cli;

import java.util.List;
import org.apache.zookeeper.data.ClientInfo;

public class WhoAmICommand extends CliCommand {
   public WhoAmICommand() {
      super("whoami", "");
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      return this;
   }

   public boolean exec() throws CliException {
      try {
         List<ClientInfo> clientInfos = this.zk.whoAmI();
         this.out.println("Auth scheme: User");
         if (clientInfos != null) {
            clientInfos.forEach((clientInfo) -> this.out.println(clientInfo.getAuthScheme() + ": " + clientInfo.getUser()));
         }

         return false;
      } catch (Exception ex) {
         throw new CliWrapperException(ex);
      }
   }
}
