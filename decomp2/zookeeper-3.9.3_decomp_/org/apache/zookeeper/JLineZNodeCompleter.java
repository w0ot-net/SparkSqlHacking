package org.apache.zookeeper;

import java.util.Collections;
import java.util.List;
import jline.console.completer.Completer;

class JLineZNodeCompleter implements Completer {
   private ZooKeeper zk;

   public JLineZNodeCompleter(ZooKeeper zk) {
      this.zk = zk;
   }

   public int complete(String buffer, int cursor, List candidates) {
      buffer = buffer.substring(0, cursor);
      String token = "";
      if (!buffer.endsWith(" ")) {
         String[] tokens = buffer.split(" ");
         if (tokens.length != 0) {
            token = tokens[tokens.length - 1];
         }
      }

      return token.startsWith("/") ? this.completeZNode(buffer, token, candidates) : this.completeCommand(buffer, token, candidates);
   }

   private int completeCommand(String buffer, String token, List candidates) {
      for(String cmd : ZooKeeperMain.getCommands()) {
         if (cmd.startsWith(token)) {
            candidates.add(cmd);
         }
      }

      return buffer.lastIndexOf(" ") + 1;
   }

   private int completeZNode(String buffer, String token, List candidates) {
      String path = token;
      int idx = token.lastIndexOf("/") + 1;
      String prefix = token.substring(idx);

      try {
         String dir = idx == 1 ? "/" : path.substring(0, idx - 1);

         for(String child : this.zk.getChildren(dir, false)) {
            if (child.startsWith(prefix)) {
               candidates.add(child);
            }
         }
      } catch (InterruptedException var11) {
         return 0;
      } catch (KeeperException var12) {
         return 0;
      }

      Collections.sort(candidates);
      return candidates.size() == 0 ? buffer.length() : buffer.lastIndexOf("/") + 1;
   }
}
