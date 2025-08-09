package org.apache.zookeeper;

import org.apache.yetus.audience.InterfaceAudience.Public;

@Public
public interface Watcher {
   void process(WatchedEvent var1);

   @Public
   public static enum WatcherType {
      Children(1),
      Data(2),
      Persistent(4),
      PersistentRecursive(5),
      Any(3);

      private final int intValue;

      private WatcherType(int intValue) {
         this.intValue = intValue;
      }

      public int getIntValue() {
         return this.intValue;
      }

      public static WatcherType fromInt(int intValue) {
         switch (intValue) {
            case 1:
               return Children;
            case 2:
               return Data;
            case 3:
               return Any;
            case 4:
               return Persistent;
            case 5:
               return PersistentRecursive;
            default:
               throw new RuntimeException("Invalid integer value for conversion to WatcherType");
         }
      }
   }

   @Public
   public interface Event {
      @Public
      public static enum KeeperState {
         /** @deprecated */
         @Deprecated
         Unknown(-1),
         Disconnected(0),
         /** @deprecated */
         @Deprecated
         NoSyncConnected(1),
         SyncConnected(3),
         AuthFailed(4),
         ConnectedReadOnly(5),
         SaslAuthenticated(6),
         Expired(-112),
         Closed(7);

         private final int intValue;

         private KeeperState(int intValue) {
            this.intValue = intValue;
         }

         public int getIntValue() {
            return this.intValue;
         }

         public static KeeperState fromInt(int intValue) {
            switch (intValue) {
               case -112:
                  return Expired;
               case -1:
                  return Unknown;
               case 0:
                  return Disconnected;
               case 1:
                  return NoSyncConnected;
               case 3:
                  return SyncConnected;
               case 4:
                  return AuthFailed;
               case 5:
                  return ConnectedReadOnly;
               case 6:
                  return SaslAuthenticated;
               case 7:
                  return Closed;
               default:
                  throw new RuntimeException("Invalid integer value for conversion to KeeperState");
            }
         }
      }

      @Public
      public static enum EventType {
         None(-1),
         NodeCreated(1),
         NodeDeleted(2),
         NodeDataChanged(3),
         NodeChildrenChanged(4),
         DataWatchRemoved(5),
         ChildWatchRemoved(6),
         PersistentWatchRemoved(7);

         private final int intValue;

         private EventType(int intValue) {
            this.intValue = intValue;
         }

         public int getIntValue() {
            return this.intValue;
         }

         public static EventType fromInt(int intValue) {
            switch (intValue) {
               case -1:
                  return None;
               case 0:
               default:
                  throw new RuntimeException("Invalid integer value for conversion to EventType");
               case 1:
                  return NodeCreated;
               case 2:
                  return NodeDeleted;
               case 3:
                  return NodeDataChanged;
               case 4:
                  return NodeChildrenChanged;
               case 5:
                  return DataWatchRemoved;
               case 6:
                  return ChildWatchRemoved;
               case 7:
                  return PersistentWatchRemoved;
            }
         }
      }
   }
}
