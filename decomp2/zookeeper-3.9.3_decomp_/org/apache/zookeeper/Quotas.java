package org.apache.zookeeper;

public class Quotas {
   public static final String procZookeeper = "/zookeeper";
   public static final String quotaZookeeper = "/zookeeper/quota";
   public static final String limitNode = "zookeeper_limits";
   public static final String statNode = "zookeeper_stats";

   public static String quotaPath(String path) {
      return "/zookeeper/quota" + path;
   }

   public static String limitPath(String path) {
      return "/zookeeper/quota" + path + "/" + "zookeeper_limits";
   }

   public static String statPath(String path) {
      return "/zookeeper/quota" + path + "/" + "zookeeper_stats";
   }

   public static String trimQuotaPath(String quotaPath) {
      return quotaPath.substring("/zookeeper/quota".length());
   }
}
