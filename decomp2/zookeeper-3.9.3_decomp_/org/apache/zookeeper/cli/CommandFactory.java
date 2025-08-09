package org.apache.zookeeper.cli;

import java.util.function.Supplier;

public class CommandFactory {
   public static CliCommand getInstance(Command command) {
      return command.getInstance();
   }

   public static enum Command {
      CLOSE(CloseCommand::new),
      CREATE(CreateCommand::new),
      DELETE(DeleteCommand::new),
      DELETE_ALL(DeleteAllCommand::new),
      SET(SetCommand::new),
      GET(GetCommand::new),
      LS(LsCommand::new),
      GET_ACL(GetAclCommand::new),
      SET_ACL(SetAclCommand::new),
      STAT(StatCommand::new),
      SYNC(SyncCommand::new),
      SET_QUOTA(SetQuotaCommand::new),
      LIST_QUOTA(ListQuotaCommand::new),
      DEL_QUOTA(DelQuotaCommand::new),
      ADD_AUTH(AddAuthCommand::new),
      RECONFIG(ReconfigCommand::new),
      GET_CONFIG(GetConfigCommand::new),
      REMOVE_WATCHES(RemoveWatchesCommand::new),
      GET_EPHEMERALS(GetEphemeralsCommand::new),
      GET_ALL_CHILDREN_NUMBER(GetAllChildrenNumberCommand::new),
      VERSION(VersionCommand::new),
      ADD_WATCH(AddWatchCommand::new),
      WHO_AM_I(WhoAmICommand::new);

      private Supplier instantiator;

      private CliCommand getInstance() {
         return (CliCommand)this.instantiator.get();
      }

      private Command(Supplier instantiator) {
         this.instantiator = instantiator;
      }
   }
}
