package org.apache.zookeeper.server.command;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FourLetterCommands {
   public static final int confCmd = ByteBuffer.wrap("conf".getBytes()).getInt();
   public static final int consCmd = ByteBuffer.wrap("cons".getBytes()).getInt();
   public static final int crstCmd = ByteBuffer.wrap("crst".getBytes()).getInt();
   public static final int dirsCmd = ByteBuffer.wrap("dirs".getBytes()).getInt();
   public static final int dumpCmd = ByteBuffer.wrap("dump".getBytes()).getInt();
   public static final int enviCmd = ByteBuffer.wrap("envi".getBytes()).getInt();
   public static final int getTraceMaskCmd = ByteBuffer.wrap("gtmk".getBytes()).getInt();
   public static final int ruokCmd = ByteBuffer.wrap("ruok".getBytes()).getInt();
   public static final int setTraceMaskCmd = ByteBuffer.wrap("stmk".getBytes()).getInt();
   public static final int srvrCmd = ByteBuffer.wrap("srvr".getBytes()).getInt();
   public static final int srstCmd = ByteBuffer.wrap("srst".getBytes()).getInt();
   public static final int statCmd = ByteBuffer.wrap("stat".getBytes()).getInt();
   public static final int wchcCmd = ByteBuffer.wrap("wchc".getBytes()).getInt();
   public static final int wchpCmd = ByteBuffer.wrap("wchp".getBytes()).getInt();
   public static final int wchsCmd = ByteBuffer.wrap("wchs".getBytes()).getInt();
   public static final int mntrCmd = ByteBuffer.wrap("mntr".getBytes()).getInt();
   public static final int isroCmd = ByteBuffer.wrap("isro".getBytes()).getInt();
   protected static final int hashCmd = ByteBuffer.wrap("hash".getBytes()).getInt();
   public static final int telnetCloseCmd = -720899;
   private static final String ZOOKEEPER_4LW_COMMANDS_WHITELIST = "zookeeper.4lw.commands.whitelist";
   private static final Logger LOG = LoggerFactory.getLogger(FourLetterCommands.class);
   private static final Map cmd2String = new HashMap();
   private static final Set whiteListedCommands = new HashSet();
   private static boolean whiteListInitialized = false;

   public static synchronized void resetWhiteList() {
      whiteListInitialized = false;
      whiteListedCommands.clear();
   }

   public static String getCommandString(int command) {
      return (String)cmd2String.get(command);
   }

   public static boolean isKnown(int command) {
      return cmd2String.containsKey(command);
   }

   public static synchronized boolean isEnabled(String command) {
      if (whiteListInitialized) {
         return whiteListedCommands.contains(command);
      } else {
         String commands = System.getProperty("zookeeper.4lw.commands.whitelist");
         if (commands != null) {
            String[] list = commands.split(",");

            for(String cmd : list) {
               if (cmd.trim().equals("*")) {
                  for(Map.Entry entry : cmd2String.entrySet()) {
                     whiteListedCommands.add((String)entry.getValue());
                  }
                  break;
               }

               if (!cmd.trim().isEmpty()) {
                  whiteListedCommands.add(cmd.trim());
               }
            }
         }

         if (System.getProperty("readonlymode.enabled", "false").equals("true")) {
            whiteListedCommands.add("isro");
         }

         whiteListedCommands.add("srvr");
         whiteListInitialized = true;
         LOG.info("The list of known four letter word commands is : {}", Arrays.asList(cmd2String));
         LOG.info("The list of enabled four letter word commands is : {}", Arrays.asList(whiteListedCommands));
         return whiteListedCommands.contains(command);
      }
   }

   static {
      cmd2String.put(confCmd, "conf");
      cmd2String.put(consCmd, "cons");
      cmd2String.put(crstCmd, "crst");
      cmd2String.put(dirsCmd, "dirs");
      cmd2String.put(dumpCmd, "dump");
      cmd2String.put(enviCmd, "envi");
      cmd2String.put(getTraceMaskCmd, "gtmk");
      cmd2String.put(ruokCmd, "ruok");
      cmd2String.put(setTraceMaskCmd, "stmk");
      cmd2String.put(srstCmd, "srst");
      cmd2String.put(srvrCmd, "srvr");
      cmd2String.put(statCmd, "stat");
      cmd2String.put(wchcCmd, "wchc");
      cmd2String.put(wchpCmd, "wchp");
      cmd2String.put(wchsCmd, "wchs");
      cmd2String.put(mntrCmd, "mntr");
      cmd2String.put(isroCmd, "isro");
      cmd2String.put(-720899, "telnet close");
      cmd2String.put(hashCmd, "hash");
   }
}
