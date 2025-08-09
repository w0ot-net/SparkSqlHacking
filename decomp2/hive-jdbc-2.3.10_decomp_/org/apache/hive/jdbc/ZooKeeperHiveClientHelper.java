package org.apache.hive.jdbc;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ZooKeeperHiveClientHelper {
   static final Logger LOG = LoggerFactory.getLogger(ZooKeeperHiveClientHelper.class.getName());
   private static final Pattern kvPattern = Pattern.compile("([^=;]*)=([^;]*)[;]?");

   static void configureConnParams(Utils.JdbcConnectionParams connParams) throws ZooKeeperHiveClientException {
      String zooKeeperEnsemble = connParams.getZooKeeperEnsemble();
      String zooKeeperNamespace = (String)connParams.getSessionVars().get("zooKeeperNamespace");
      if (zooKeeperNamespace == null || zooKeeperNamespace.isEmpty()) {
         zooKeeperNamespace = "hiveserver2";
      }

      Random randomizer = new Random();
      CuratorFramework zooKeeperClient = CuratorFrameworkFactory.builder().connectString(zooKeeperEnsemble).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

      try {
         zooKeeperClient.start();
         List<String> serverHosts = (List)zooKeeperClient.getChildren().forPath("/" + zooKeeperNamespace);
         serverHosts.removeAll(connParams.getRejectedHostZnodePaths());
         if (serverHosts.isEmpty()) {
            throw new ZooKeeperHiveClientException("Tried all existing HiveServer2 uris from ZooKeeper.");
         }

         String serverNode = (String)serverHosts.get(randomizer.nextInt(serverHosts.size()));
         connParams.setCurrentHostZnodePath(serverNode);
         String dataStr = new String((byte[])zooKeeperClient.getData().forPath("/" + zooKeeperNamespace + "/" + serverNode), Charset.forName("UTF-8"));
         Matcher matcher = kvPattern.matcher(dataStr);
         if (dataStr != null && !matcher.find()) {
            String[] split = dataStr.split(":");
            if (split.length != 2) {
               throw new ZooKeeperHiveClientException("Unable to read HiveServer2 uri from ZooKeeper: " + dataStr);
            }

            connParams.setHost(split[0]);
            connParams.setPort(Integer.parseInt(split[1]));
         } else {
            applyConfs(dataStr, connParams);
         }
      } catch (Exception e) {
         throw new ZooKeeperHiveClientException("Unable to read HiveServer2 configs from ZooKeeper", e);
      } finally {
         if (zooKeeperClient != null) {
            zooKeeperClient.close();
         }

      }

   }

   private static void applyConfs(String serverConfStr, Utils.JdbcConnectionParams connParams) throws Exception {
      Matcher matcher = kvPattern.matcher(serverConfStr);

      while(matcher.find()) {
         if (matcher.group(1) != null) {
            if (matcher.group(2) == null) {
               throw new Exception("Null config value for: " + matcher.group(1) + " published by the server.");
            }

            if (matcher.group(1).equals("hive.server2.thrift.bind.host")) {
               connParams.setHost(matcher.group(2));
            }

            if (matcher.group(1).equals("hive.server2.transport.mode") && !connParams.getSessionVars().containsKey("transportMode")) {
               connParams.getSessionVars().put("transportMode", matcher.group(2));
            }

            if (matcher.group(1).equals("hive.server2.thrift.port")) {
               connParams.setPort(Integer.parseInt(matcher.group(2)));
            }

            if (matcher.group(1).equals("hive.server2.thrift.http.port") && connParams.getPort() <= 0) {
               connParams.setPort(Integer.parseInt(matcher.group(2)));
            }

            if (matcher.group(1).equals("hive.server2.thrift.sasl.qop") && !connParams.getSessionVars().containsKey("saslQop")) {
               connParams.getSessionVars().put("saslQop", matcher.group(2));
            }

            if (matcher.group(1).equals("hive.server2.thrift.http.path") && !connParams.getSessionVars().containsKey("httpPath")) {
               connParams.getSessionVars().put("httpPath", matcher.group(2));
            }

            if (matcher.group(1) != null && matcher.group(1).equals("hive.server2.use.SSL") && !connParams.getSessionVars().containsKey("ssl")) {
               connParams.getSessionVars().put("ssl", matcher.group(2));
            }

            if (matcher.group(1).equals("hive.server2.authentication") && matcher.group(2).equalsIgnoreCase("NOSASL") && (!connParams.getSessionVars().containsKey("auth") || !((String)connParams.getSessionVars().get("auth")).equalsIgnoreCase("noSasl"))) {
               connParams.getSessionVars().put("auth", "noSasl");
            }

            if (matcher.group(1).equalsIgnoreCase("hive.server2.authentication.kerberos.principal") && (!connParams.getSessionVars().containsKey("auth") || !((String)connParams.getSessionVars().get("auth")).equalsIgnoreCase("delegationToken")) && !connParams.getSessionVars().containsKey("principal")) {
               connParams.getSessionVars().put("principal", matcher.group(2));
            }
         }
      }

   }

   static class DummyWatcher implements Watcher {
      public void process(WatchedEvent event) {
      }
   }
}
