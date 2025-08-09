package org.apache.zookeeper.server;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.data.StatPersisted;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class DataNode implements Record {
   private volatile long digest;
   volatile boolean digestCached;
   byte[] data;
   Long acl;
   public StatPersisted stat;
   private Set children = null;
   private static final Set EMPTY_SET = Collections.emptySet();

   DataNode() {
   }

   public DataNode(byte[] data, Long acl, StatPersisted stat) {
      this.data = data;
      this.acl = acl;
      this.stat = stat;
   }

   public synchronized boolean addChild(String child) {
      if (this.children == null) {
         this.children = new HashSet(8);
      }

      return this.children.add(child);
   }

   public synchronized boolean removeChild(String child) {
      return this.children == null ? false : this.children.remove(child);
   }

   public synchronized void setChildren(HashSet children) {
      this.children = children;
   }

   public synchronized Set getChildren() {
      return this.children == null ? EMPTY_SET : Collections.unmodifiableSet(this.children);
   }

   public synchronized void copyStat(Stat to) {
      to.setAversion(this.stat.getAversion());
      to.setCtime(this.stat.getCtime());
      to.setCzxid(this.stat.getCzxid());
      to.setMtime(this.stat.getMtime());
      to.setMzxid(this.stat.getMzxid());
      to.setPzxid(this.stat.getPzxid());
      to.setVersion(this.stat.getVersion());
      to.setEphemeralOwner(getClientEphemeralOwner(this.stat));
      to.setDataLength(this.data == null ? 0 : this.data.length);
      int numChildren = 0;
      if (this.children != null) {
         numChildren = this.children.size();
      }

      to.setCversion(this.stat.getCversion() * 2 - numChildren);
      to.setNumChildren(numChildren);
   }

   private static long getClientEphemeralOwner(StatPersisted stat) {
      EphemeralType ephemeralType = EphemeralType.get(stat.getEphemeralOwner());
      return ephemeralType != EphemeralType.NORMAL ? 0L : stat.getEphemeralOwner();
   }

   public synchronized void deserialize(InputArchive archive, String tag) throws IOException {
      archive.startRecord("node");
      this.data = archive.readBuffer("data");
      this.acl = archive.readLong("acl");
      this.stat = new StatPersisted();
      this.stat.deserialize(archive, "statpersisted");
      archive.endRecord("node");
   }

   public synchronized void serialize(OutputArchive archive, String tag) throws IOException {
      archive.startRecord(this, "node");
      archive.writeBuffer(this.data, "data");
      archive.writeLong(this.acl, "acl");
      this.stat.serialize(archive, "statpersisted");
      archive.endRecord(this, "node");
   }

   public boolean isDigestCached() {
      return this.digestCached;
   }

   public void setDigestCached(boolean digestCached) {
      this.digestCached = digestCached;
   }

   public long getDigest() {
      return this.digest;
   }

   public void setDigest(long digest) {
      this.digest = digest;
   }

   public synchronized byte[] getData() {
      return this.data;
   }
}
