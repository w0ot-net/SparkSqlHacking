package org.apache.hive.beeline;

import java.sql.SQLException;
import org.apache.hive.jdbc.Utils;

public class ClientCommandHookFactory {
   private static final ClientCommandHookFactory instance = new ClientCommandHookFactory();

   private ClientCommandHookFactory() {
   }

   public static ClientCommandHookFactory get() {
      return instance;
   }

   public ClientHook getHook(BeeLine beeLine, String cmdLine) {
      if (!beeLine.isBeeLine()) {
         if (cmdLine.toLowerCase().startsWith("set")) {
            return cmdLine.contains("=") ? new SetCommandHook(cmdLine) : null;
         } else {
            return cmdLine.toLowerCase().startsWith("use") ? new UseCommandHook(cmdLine) : null;
         }
      } else if (beeLine.getOpts().getShowDbInPrompt()) {
         if (cmdLine.toLowerCase().startsWith("use")) {
            return new UseCommandHook(cmdLine);
         } else if (cmdLine.toLowerCase().startsWith("connect")) {
            return new ConnectCommandHook(cmdLine);
         } else {
            return cmdLine.toLowerCase().startsWith("go") ? new GoCommandHook(cmdLine) : null;
         }
      } else {
         return null;
      }
   }

   public class SetCommandHook extends ClientHook {
      public SetCommandHook(String sql) {
         super(sql);
      }

      public void postHook(BeeLine beeLine) {
         if (!beeLine.isBeeLine()) {
            beeLine.getOpts().setHiveConf(beeLine.getCommands().getHiveConf(false));
         }

      }
   }

   public class UseCommandHook extends ClientHook {
      public UseCommandHook(String sql) {
         super(sql);
      }

      public void postHook(BeeLine beeLine) {
         String line = this.sql.replaceAll("\\s+", " ");
         String[] strs = line.split(" ");
         String dbName;
         if (strs != null && strs.length == 2) {
            dbName = strs[1];
         } else {
            dbName = "";
         }

         beeLine.setCurrentDatabase(dbName);
      }
   }

   public class ConnectCommandHook extends ClientHook {
      public ConnectCommandHook(String sql) {
         super(sql);
      }

      public void postHook(BeeLine beeLine) {
         String line = this.sql.replaceAll("\\s+", " ");
         String[] strs = line.split(" ");
         String dbName;
         if (strs != null && strs.length >= 1) {
            try {
               dbName = Utils.parseURL(strs[1]).getDbName();
            } catch (Exception var6) {
               dbName = "";
            }
         } else {
            dbName = "";
         }

         beeLine.setCurrentDatabase(dbName);
      }
   }

   public class GoCommandHook extends ClientHook {
      public GoCommandHook(String sql) {
         super(sql);
      }

      public void postHook(BeeLine beeLine) {
         String dbName = "";

         try {
            dbName = beeLine.getDatabaseConnection().getConnection().getSchema();
         } catch (SQLException var4) {
         }

         beeLine.setCurrentDatabase(dbName);
      }
   }
}
