package org.apache.hive.common.util;

import org.apache.hadoop.hive.common.classification.InterfaceAudience;
import org.apache.hadoop.hive.common.classification.InterfaceStability;
import org.apache.hive.common.HiveVersionAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@InterfaceAudience.Private
@InterfaceStability.Unstable
public class HiveVersionInfo {
   private static final Logger LOG = LoggerFactory.getLogger(HiveVersionInfo.class);
   private static Package myPackage = HiveVersionAnnotation.class.getPackage();
   private static HiveVersionAnnotation version;

   static Package getPackage() {
      return myPackage;
   }

   public static String getVersion() {
      return version != null ? version.version() : "Unknown";
   }

   public static String getShortVersion() {
      return version != null ? version.shortVersion() : "Unknown";
   }

   public static String getRevision() {
      return version != null ? version.revision() : "Unknown";
   }

   public static String getBranch() {
      return version != null ? version.branch() : "Unknown";
   }

   public static String getDate() {
      return version != null ? version.date() : "Unknown";
   }

   public static String getUser() {
      return version != null ? version.user() : "Unknown";
   }

   public static String getUrl() {
      return version != null ? version.url() : "Unknown";
   }

   public static String getSrcChecksum() {
      return version != null ? version.srcChecksum() : "Unknown";
   }

   public static String getBuildVersion() {
      return getVersion() + " from " + getRevision() + " by " + getUser() + " source checksum " + getSrcChecksum();
   }

   public static void main(String[] args) {
      LOG.debug("version: " + version);
      System.out.println("Hive " + getVersion());
      System.out.println("Git " + getUrl() + " -r " + getRevision());
      System.out.println("Compiled by " + getUser() + " on " + getDate());
      System.out.println("From source with checksum " + getSrcChecksum());
   }

   static {
      version = (HiveVersionAnnotation)myPackage.getAnnotation(HiveVersionAnnotation.class);
   }
}
