package org.apache.curator.framework.imps;

import java.util.Arrays;
import java.util.List;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

class CuratorEventImpl implements CuratorEvent {
   private final CuratorEventType type;
   private final int resultCode;
   private final String path;
   private final String name;
   private final List children;
   private final Object context;
   private final Stat stat;
   private final byte[] data;
   private final WatchedEvent watchedEvent;
   private final List aclList;
   private final List opResults;

   public CuratorEventType getType() {
      return this.type;
   }

   public int getResultCode() {
      return this.resultCode;
   }

   public String getPath() {
      return this.path;
   }

   public Object getContext() {
      return this.context;
   }

   public Stat getStat() {
      return this.stat;
   }

   public byte[] getData() {
      return this.data;
   }

   public String getName() {
      return this.name;
   }

   public List getChildren() {
      return this.children;
   }

   public WatchedEvent getWatchedEvent() {
      return this.watchedEvent;
   }

   public List getACLList() {
      return this.aclList;
   }

   public List getOpResults() {
      return this.opResults;
   }

   public String toString() {
      return "CuratorEventImpl{type=" + this.type + ", resultCode=" + this.resultCode + ", path='" + this.path + '\'' + ", name='" + this.name + '\'' + ", children=" + this.children + ", context=" + this.context + ", stat=" + this.stat + ", data=" + Arrays.toString(this.data) + ", watchedEvent=" + this.watchedEvent + ", aclList=" + this.aclList + ", opResults=" + this.opResults + '}';
   }

   CuratorEventImpl(CuratorFrameworkImpl client, CuratorEventType type, int resultCode, String path, String name, Object context, Stat stat, byte[] data, List children, WatchedEvent watchedEvent, List aclList, List opResults) {
      this.type = type;
      this.resultCode = resultCode;
      this.opResults = opResults != null ? ImmutableList.copyOf(opResults) : null;
      this.path = client.unfixForNamespace(path);
      this.name = client.unfixForNamespace(name);
      this.context = context;
      this.stat = stat;
      this.data = data;
      this.children = children;
      this.watchedEvent = watchedEvent != null ? new NamespaceWatchedEvent(client, watchedEvent) : null;
      this.aclList = aclList != null ? ImmutableList.copyOf(aclList) : null;
   }
}
