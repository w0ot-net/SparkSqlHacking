package org.apache.zookeeper;

import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.proto.WatcherEvent;

@Public
public class WatchedEvent {
   public static final long NO_ZXID = -1L;
   private final Watcher.Event.KeeperState keeperState;
   private final Watcher.Event.EventType eventType;
   private final String path;
   private final long zxid;

   public WatchedEvent(Watcher.Event.EventType eventType, Watcher.Event.KeeperState keeperState, String path, long zxid) {
      this.keeperState = keeperState;
      this.eventType = eventType;
      this.path = path;
      this.zxid = zxid;
   }

   public WatchedEvent(Watcher.Event.EventType eventType, Watcher.Event.KeeperState keeperState, String path) {
      this(eventType, keeperState, path, -1L);
   }

   public WatchedEvent(WatcherEvent eventMessage, long zxid) {
      this.keeperState = Watcher.Event.KeeperState.fromInt(eventMessage.getState());
      this.eventType = Watcher.Event.EventType.fromInt(eventMessage.getType());
      this.path = eventMessage.getPath();
      this.zxid = zxid;
   }

   public Watcher.Event.KeeperState getState() {
      return this.keeperState;
   }

   public Watcher.Event.EventType getType() {
      return this.eventType;
   }

   public String getPath() {
      return this.path;
   }

   public long getZxid() {
      return this.zxid;
   }

   public String toString() {
      return "WatchedEvent state:" + this.keeperState + " type:" + this.eventType + " path:" + this.path + " zxid: " + this.zxid;
   }

   public WatcherEvent getWrapper() {
      return new WatcherEvent(this.eventType.getIntValue(), this.keeperState.getIntValue(), this.path);
   }
}
