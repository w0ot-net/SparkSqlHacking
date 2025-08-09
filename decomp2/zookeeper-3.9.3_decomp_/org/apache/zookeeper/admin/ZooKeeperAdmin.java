package org.apache.zookeeper.admin;

import java.io.IOException;
import java.util.List;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.common.StringUtils;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.GetDataResponse;
import org.apache.zookeeper.proto.ReconfigRequest;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.proto.RequestHeader;
import org.apache.zookeeper.server.DataTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
public class ZooKeeperAdmin extends ZooKeeper {
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperAdmin.class);

   public ZooKeeperAdmin(String connectString, int sessionTimeout, Watcher watcher) throws IOException {
      super(connectString, sessionTimeout, watcher);
   }

   public ZooKeeperAdmin(String connectString, int sessionTimeout, Watcher watcher, ZKClientConfig conf) throws IOException {
      super(connectString, sessionTimeout, watcher, conf);
   }

   public ZooKeeperAdmin(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly, ZKClientConfig conf) throws IOException {
      super(connectString, sessionTimeout, watcher, canBeReadOnly, conf);
   }

   public ZooKeeperAdmin(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly) throws IOException {
      super(connectString, sessionTimeout, watcher, canBeReadOnly);
   }

   public byte[] reconfigure(String joiningServers, String leavingServers, String newMembers, long fromConfig, Stat stat) throws KeeperException, InterruptedException {
      RequestHeader h = new RequestHeader();
      h.setType(16);
      ReconfigRequest request = new ReconfigRequest(joiningServers, leavingServers, newMembers, fromConfig);
      GetDataResponse response = new GetDataResponse();
      ReplyHeader r = this.cnxn.submitRequest(h, request, response, (ZooKeeper.WatchRegistration)null);
      if (r.getErr() != 0) {
         throw KeeperException.create(KeeperException.Code.get(r.getErr()), "");
      } else {
         if (stat != null) {
            DataTree.copyStat(response.getStat(), stat);
         }

         return response.getData();
      }
   }

   public byte[] reconfigure(List joiningServers, List leavingServers, List newMembers, long fromConfig, Stat stat) throws KeeperException, InterruptedException {
      return this.reconfigure(StringUtils.joinStrings(joiningServers, ","), StringUtils.joinStrings(leavingServers, ","), StringUtils.joinStrings(newMembers, ","), fromConfig, stat);
   }

   public void reconfigure(String joiningServers, String leavingServers, String newMembers, long fromConfig, AsyncCallback.DataCallback cb, Object ctx) {
      RequestHeader h = new RequestHeader();
      h.setType(16);
      ReconfigRequest request = new ReconfigRequest(joiningServers, leavingServers, newMembers, fromConfig);
      GetDataResponse response = new GetDataResponse();
      this.cnxn.queuePacket(h, new ReplyHeader(), request, response, cb, "/zookeeper/config", "/zookeeper/config", ctx, (ZooKeeper.WatchRegistration)null);
   }

   public void reconfigure(List joiningServers, List leavingServers, List newMembers, long fromConfig, AsyncCallback.DataCallback cb, Object ctx) {
      this.reconfigure(StringUtils.joinStrings(joiningServers, ","), StringUtils.joinStrings(leavingServers, ","), StringUtils.joinStrings(newMembers, ","), fromConfig, cb, ctx);
   }

   public String toString() {
      return super.toString();
   }
}
