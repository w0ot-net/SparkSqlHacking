package org.apache.zookeeper;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

@Public
public class ZooDefs {
   public static final String CONFIG_NODE = "/zookeeper/config";
   public static final String ZOOKEEPER_NODE_SUBTREE = "/zookeeper/";

   @Public
   public interface Ids {
      Id ANYONE_ID_UNSAFE = new Id("world", "anyone");
      Id AUTH_IDS = new Id("auth", "");
      @SuppressFBWarnings(
         value = {"MS_MUTABLE_COLLECTION"},
         justification = "Cannot break API"
      )
      ArrayList OPEN_ACL_UNSAFE = new ArrayList(Collections.singletonList(new ACL(31, ANYONE_ID_UNSAFE)));
      @SuppressFBWarnings(
         value = {"MS_MUTABLE_COLLECTION"},
         justification = "Cannot break API"
      )
      ArrayList CREATOR_ALL_ACL = new ArrayList(Collections.singletonList(new ACL(31, AUTH_IDS)));
      @SuppressFBWarnings(
         value = {"MS_MUTABLE_COLLECTION"},
         justification = "Cannot break API"
      )
      ArrayList READ_ACL_UNSAFE = new ArrayList(Collections.singletonList(new ACL(1, ANYONE_ID_UNSAFE)));
   }

   @Public
   public interface AddWatchModes {
      int persistent = 0;
      int persistentRecursive = 1;
   }

   @Public
   public interface OpCode {
      int notification = 0;
      int create = 1;
      int delete = 2;
      int exists = 3;
      int getData = 4;
      int setData = 5;
      int getACL = 6;
      int setACL = 7;
      int getChildren = 8;
      int sync = 9;
      int ping = 11;
      int getChildren2 = 12;
      int check = 13;
      int multi = 14;
      int create2 = 15;
      int reconfig = 16;
      int checkWatches = 17;
      int removeWatches = 18;
      int createContainer = 19;
      int deleteContainer = 20;
      int createTTL = 21;
      int multiRead = 22;
      int auth = 100;
      int setWatches = 101;
      int sasl = 102;
      int getEphemerals = 103;
      int getAllChildrenNumber = 104;
      int setWatches2 = 105;
      int addWatch = 106;
      int whoAmI = 107;
      int createSession = -10;
      int closeSession = -11;
      int error = -1;
   }

   @Public
   public interface Perms {
      int READ = 1;
      int WRITE = 2;
      int CREATE = 4;
      int DELETE = 8;
      int ADMIN = 16;
      int ALL = 31;
   }
}
