package org.apache.hadoop.hive.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerUtils {
   public static final Logger LOG = LoggerFactory.getLogger(ServerUtils.class);

   public static void cleanUpScratchDir(HiveConf hiveConf) {
      if (hiveConf.getBoolVar(HiveConf.ConfVars.HIVE_START_CLEANUP_SCRATCHDIR)) {
         String hiveScratchDir = hiveConf.get(HiveConf.ConfVars.SCRATCHDIR.varname);

         try {
            Path jobScratchDir = new Path(hiveScratchDir);
            LOG.info("Cleaning scratchDir : " + hiveScratchDir);
            FileSystem fileSystem = jobScratchDir.getFileSystem(hiveConf);
            fileSystem.delete(jobScratchDir, true);
         } catch (Throwable e) {
            LOG.warn("Unable to delete scratchDir : " + hiveScratchDir, e);
         }
      }

   }

   public static InetAddress getHostAddress(String hostname) throws UnknownHostException {
      InetAddress serverIPAddress;
      if (hostname != null && !hostname.isEmpty()) {
         serverIPAddress = InetAddress.getByName(hostname);
      } else {
         serverIPAddress = InetAddress.getLocalHost();
      }

      return serverIPAddress;
   }

   public static String hostname() {
      try {
         return InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException e) {
         LOG.error("Unable to resolve my host name " + e.getMessage());
         throw new RuntimeException(e);
      }
   }
}
