package org.apache.curator.framework.api;

import java.util.List;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;

public interface CuratorEvent {
   CuratorEventType getType();

   int getResultCode();

   String getPath();

   Object getContext();

   Stat getStat();

   byte[] getData();

   String getName();

   List getChildren();

   List getACLList();

   List getOpResults();

   WatchedEvent getWatchedEvent();
}
