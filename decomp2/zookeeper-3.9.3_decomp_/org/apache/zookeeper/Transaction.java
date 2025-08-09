package org.apache.zookeeper;

import java.util.ArrayList;
import java.util.List;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.data.ACL;

@Public
public class Transaction {
   private ZooKeeper zk;
   private List ops = new ArrayList();

   protected Transaction(ZooKeeper zk) {
      this.zk = zk;
   }

   public Transaction create(String path, byte[] data, List acl, CreateMode createMode) {
      this.ops.add(Op.create(path, data, acl, createMode.toFlag()));
      return this;
   }

   public Transaction delete(String path, int version) {
      this.ops.add(Op.delete(path, version));
      return this;
   }

   public Transaction check(String path, int version) {
      this.ops.add(Op.check(path, version));
      return this;
   }

   public Transaction setData(String path, byte[] data, int version) {
      this.ops.add(Op.setData(path, data, version));
      return this;
   }

   public List commit() throws InterruptedException, KeeperException {
      return this.zk.multi(this.ops);
   }

   public void commit(AsyncCallback.MultiCallback cb, Object ctx) {
      this.zk.multi(this.ops, cb, ctx);
   }
}
