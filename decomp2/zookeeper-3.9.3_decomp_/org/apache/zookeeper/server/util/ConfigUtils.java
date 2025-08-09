package org.apache.zookeeper.server.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

public class ConfigUtils {
   public static String getClientConfigStr(String configData) {
      Properties props = new Properties();

      try {
         props.load(new StringReader(configData));
      } catch (IOException e) {
         e.printStackTrace();
         return "";
      }

      StringBuffer sb = new StringBuffer();
      boolean first = true;
      String version = "";

      for(Map.Entry entry : props.entrySet()) {
         String key = entry.getKey().toString().trim();
         String value = entry.getValue().toString().trim();
         if (key.equals("version")) {
            version = value;
         }

         if (key.startsWith("server.")) {
            QuorumPeer.QuorumServer qs;
            try {
               qs = new QuorumPeer.QuorumServer(-1L, value);
            } catch (QuorumPeerConfig.ConfigException e) {
               e.printStackTrace();
               continue;
            }

            if (!first) {
               sb.append(",");
            } else {
               first = false;
            }

            if (null != qs.clientAddr) {
               sb.append(qs.clientAddr.getHostString() + ":" + qs.clientAddr.getPort());
            }
         }
      }

      return version + " " + sb.toString();
   }

   public static String[] getHostAndPort(String s) throws QuorumPeerConfig.ConfigException {
      if (s.startsWith("[")) {
         int i = s.indexOf("]");
         if (i < 0) {
            throw new QuorumPeerConfig.ConfigException(s + " starts with '[' but has no matching ']:'");
         } else if (i + 2 == s.length()) {
            throw new QuorumPeerConfig.ConfigException(s + " doesn't have a port after colon");
         } else if (i + 2 < s.length()) {
            String[] sa = s.substring(i + 2).split(":");
            String[] nsa = new String[sa.length + 1];
            nsa[0] = s.substring(1, i);
            System.arraycopy(sa, 0, nsa, 1, sa.length);
            return nsa;
         } else {
            return new String[]{s.replaceAll("\\[|\\]", "")};
         }
      } else {
         return s.split(":");
      }
   }

   public static String getPropertyBackwardCompatibleWay(String newPropertyKey) {
      String newKeyValue = System.getProperty(newPropertyKey);
      if (newKeyValue != null) {
         return newKeyValue.trim();
      } else {
         String oldPropertyKey = newPropertyKey.replace("zookeeper.", "");
         String oldKeyValue = System.getProperty(oldPropertyKey);
         return oldKeyValue != null ? oldKeyValue.trim() : null;
      }
   }
}
