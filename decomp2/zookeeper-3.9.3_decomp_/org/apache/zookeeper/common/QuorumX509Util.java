package org.apache.zookeeper.common;

public class QuorumX509Util extends X509Util {
   protected String getConfigPrefix() {
      return "zookeeper.ssl.quorum.";
   }

   protected boolean shouldVerifyClientHostname() {
      return true;
   }
}
