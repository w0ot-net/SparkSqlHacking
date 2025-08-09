package org.apache.zookeeper.server.persistence;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
   private static final Logger LOG = LoggerFactory.getLogger(Util.class);
   private static final String SNAP_DIR = "snapDir";
   private static final String LOG_DIR = "logDir";
   private static final String DB_FORMAT_CONV = "dbFormatConversion";

   public static String makeURIString(String dataDir, String dataLogDir, String convPolicy) {
      String uri = "file:snapDir=" + dataDir + ";" + "logDir" + "=" + dataLogDir;
      if (convPolicy != null) {
         uri = uri + ";dbFormatConversion=" + convPolicy;
      }

      return uri.replace('\\', '/');
   }

   public static URI makeFileLoggerURL(File dataDir, File dataLogDir) {
      return URI.create(makeURIString(dataDir.getPath(), dataLogDir.getPath(), (String)null));
   }

   public static URI makeFileLoggerURL(File dataDir, File dataLogDir, String convPolicy) {
      return URI.create(makeURIString(dataDir.getPath(), dataLogDir.getPath(), convPolicy));
   }

   public static String makeLogName(long zxid) {
      return "log." + Long.toHexString(zxid);
   }

   public static String makeSnapshotName(long zxid) {
      return "snapshot." + Long.toHexString(zxid) + SnapStream.getStreamMode().getFileExtension();
   }

   public static File getSnapDir(Properties props) {
      return new File(props.getProperty("snapDir"));
   }

   public static File getLogDir(Properties props) {
      return new File(props.getProperty("logDir"));
   }

   public static String getFormatConversionPolicy(Properties props) {
      return props.getProperty("dbFormatConversion");
   }

   public static long getZxidFromName(String name, String prefix) {
      long zxid = -1L;
      String[] nameParts = name.split("\\.");
      if (nameParts.length >= 2 && nameParts[0].equals(prefix)) {
         try {
            zxid = Long.parseLong(nameParts[1], 16);
         } catch (NumberFormatException var6) {
         }
      }

      return zxid;
   }

   public static byte[] readTxnBytes(InputArchive ia) throws IOException {
      try {
         byte[] bytes = ia.readBuffer("txtEntry");
         if (bytes.length == 0) {
            return bytes;
         } else if (ia.readByte("EOF") != 66) {
            LOG.error("Last transaction was partial.");
            return null;
         } else {
            return bytes;
         }
      } catch (EOFException var2) {
         return null;
      }
   }

   public static byte[] marshallTxnEntry(TxnHeader hdr, Record txn, TxnDigest digest) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      OutputArchive boa = BinaryOutputArchive.getArchive(baos);
      hdr.serialize(boa, "hdr");
      if (txn != null) {
         txn.serialize(boa, "txn");
      }

      if (digest != null) {
         digest.serialize(boa, "digest");
      }

      return baos.toByteArray();
   }

   public static void writeTxnBytes(OutputArchive oa, byte[] bytes) throws IOException {
      oa.writeBuffer(bytes, "txnEntry");
      oa.writeByte((byte)66, "EOR");
   }

   public static List sortDataDir(File[] files, String prefix, boolean ascending) {
      if (files == null) {
         return new ArrayList(0);
      } else {
         List<File> filelist = Arrays.asList(files);
         Collections.sort(filelist, new DataDirFileComparator(prefix, ascending));
         return filelist;
      }
   }

   public static boolean isLogFileName(String fileName) {
      return fileName.startsWith("log.");
   }

   public static boolean isSnapshotFileName(String fileName) {
      return fileName.startsWith("snapshot.");
   }

   private static class DataDirFileComparator implements Comparator, Serializable {
      private static final long serialVersionUID = -2648639884525140318L;
      private String prefix;
      private boolean ascending;

      public DataDirFileComparator(String prefix, boolean ascending) {
         this.prefix = prefix;
         this.ascending = ascending;
      }

      public int compare(File o1, File o2) {
         long z1 = Util.getZxidFromName(o1.getName(), this.prefix);
         long z2 = Util.getZxidFromName(o2.getName(), this.prefix);
         int result = z1 < z2 ? -1 : (z1 > z2 ? 1 : 0);
         return this.ascending ? result : -result;
      }
   }
}
