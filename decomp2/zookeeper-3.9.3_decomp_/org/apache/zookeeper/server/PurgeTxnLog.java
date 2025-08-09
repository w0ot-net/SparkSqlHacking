package org.apache.zookeeper.server;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.apache.zookeeper.server.persistence.Util;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
public class PurgeTxnLog {
   private static final Logger LOG = LoggerFactory.getLogger(PurgeTxnLog.class);
   private static final String COUNT_ERR_MSG = "count should be greater than or equal to 3";
   private static final String PREFIX_SNAPSHOT = "snapshot";
   private static final String PREFIX_LOG = "log";

   static void printUsage() {
      System.out.println("Usage:");
      System.out.println("PurgeTxnLog dataLogDir [snapDir] -n count");
      System.out.println("\tdataLogDir -- path to the txn log directory");
      System.out.println("\tsnapDir -- path to the snapshot directory");
      System.out.println("\tcount -- the number of old snaps/logs you want to keep, value should be greater than or equal to 3");
   }

   public static void purge(File dataDir, File snapDir, int num) throws IOException {
      if (num < 3) {
         throw new IllegalArgumentException("count should be greater than or equal to 3");
      } else {
         FileTxnSnapLog txnLog = new FileTxnSnapLog(dataDir, snapDir);
         List<File> snaps = txnLog.findNValidSnapshots(num);
         int numSnaps = snaps.size();
         if (numSnaps > 0) {
            purgeOlderSnapshots(txnLog, (File)snaps.get(numSnaps - 1));
         }

      }
   }

   static void purgeOlderSnapshots(FileTxnSnapLog txnLog, File snapShot) {
      final long leastZxidToBeRetain = Util.getZxidFromName(snapShot.getName(), "snapshot");
      final Set<File> retainedTxnLogs = new HashSet();
      retainedTxnLogs.addAll(Arrays.asList(txnLog.getSnapshotLogs(leastZxidToBeRetain)));

      class MyFileFilter implements FileFilter {
         private final String prefix;

         MyFileFilter(String prefix) {
            this.prefix = prefix;
         }

         public boolean accept(File f) {
            if (!f.getName().startsWith(this.prefix + ".")) {
               return false;
            } else if (retainedTxnLogs.contains(f)) {
               return false;
            } else {
               long fZxid = Util.getZxidFromName(f.getName(), this.prefix);
               return fZxid < leastZxidToBeRetain;
            }
         }
      }

      File[] logs = txnLog.getDataLogDir().listFiles(new MyFileFilter("log"));
      List<File> files = new ArrayList();
      if (logs != null) {
         files.addAll(Arrays.asList(logs));
      }

      File[] snapshots = txnLog.getSnapDir().listFiles(new MyFileFilter("snapshot"));
      if (snapshots != null) {
         files.addAll(Arrays.asList(snapshots));
      }

      for(File f : files) {
         String msg = String.format("Removing file: %s\t%s", DateFormat.getDateTimeInstance().format(f.lastModified()), f.getPath());
         LOG.info(msg);
         System.out.println(msg);
         if (!f.delete()) {
            System.err.println("Failed to remove " + f.getPath());
         }
      }

   }

   public static void main(String[] args) throws IOException {
      if (args.length < 3 || args.length > 4) {
         printUsageThenExit();
      }

      File dataDir = validateAndGetFile(args[0]);
      File snapDir = dataDir;
      int num = -1;
      String countOption = "";
      if (args.length == 3) {
         countOption = args[1];
         num = validateAndGetCount(args[2]);
      } else {
         snapDir = validateAndGetFile(args[1]);
         countOption = args[2];
         num = validateAndGetCount(args[3]);
      }

      if (!"-n".equals(countOption)) {
         printUsageThenExit();
      }

      purge(dataDir, snapDir, num);
   }

   private static File validateAndGetFile(String path) {
      File file = new File(path);
      if (!file.exists()) {
         System.err.println("Path '" + file.getAbsolutePath() + "' does not exist. ");
         printUsageThenExit();
      }

      return file;
   }

   private static int validateAndGetCount(String number) {
      int result = 0;

      try {
         result = Integer.parseInt(number);
         if (result < 3) {
            System.err.println("count should be greater than or equal to 3");
            printUsageThenExit();
         }
      } catch (NumberFormatException var3) {
         System.err.println("'" + number + "' can not be parsed to integer.");
         printUsageThenExit();
      }

      return result;
   }

   private static void printUsageThenExit() {
      printUsage();
      ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
   }
}
