package org.apache.zookeeper.server.quorum;

import java.net.InetSocketAddress;
import java.net.Socket;
import javax.management.ObjectName;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.jmx.ZKMBeanInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LearnerHandlerBean implements LearnerHandlerMXBean, ZKMBeanInfo {
   private static final Logger LOG = LoggerFactory.getLogger(LearnerHandlerBean.class);
   private final LearnerHandler learnerHandler;
   private final String remoteAddr;

   public LearnerHandlerBean(LearnerHandler learnerHandler, Socket socket) {
      this.learnerHandler = learnerHandler;
      InetSocketAddress sockAddr = (InetSocketAddress)socket.getRemoteSocketAddress();
      if (sockAddr == null) {
         this.remoteAddr = "Unknown";
      } else {
         this.remoteAddr = sockAddr.getAddress().getHostAddress() + ":" + sockAddr.getPort();
      }

   }

   public String getName() {
      return MBeanRegistry.getInstance().makeFullPath("Learner_Connections", ObjectName.quote(this.remoteAddr), String.format("\"id:%d\"", this.learnerHandler.getSid()));
   }

   public boolean isHidden() {
      return false;
   }

   public void terminateConnection() {
      LOG.info("terminating learner handler connection on demand {}", this.toString());
      this.learnerHandler.shutdown();
   }

   public String toString() {
      return "LearnerHandlerBean{remoteIP=" + this.remoteAddr + ",ServerId=" + this.learnerHandler.getSid() + "}";
   }
}
