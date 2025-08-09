package org.apache.zookeeper.server.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Adler32;
import java.util.zip.Checksum;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.Record;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.TxnLogEntry;
import org.apache.zookeeper.server.persistence.FileHeader;
import org.apache.zookeeper.server.persistence.FileTxnLog;
import org.apache.zookeeper.txn.TxnHeader;
import org.apache.zookeeper.util.ServiceUtils;

@Public
public class LogChopper {
   public static void main(String[] args) {
      ExitCode rc = ExitCode.INVALID_INVOCATION;
      if (args.length != 3) {
         System.out.println("Usage: LogChopper zxid_to_chop_to txn_log_to_chop chopped_filename");
         System.out.println("    this program will read the txn_log_to_chop file and copy all the transactions");
         System.out.println("    from it up to (and including) the given zxid into chopped_filename.");
         ServiceUtils.requestSystemExit(rc.getValue());
      }

      String txnLog = args[1];
      String choppedLog = args[2];

      try {
         InputStream is = new BufferedInputStream(new FileInputStream(txnLog));

         try {
            OutputStream os = new BufferedOutputStream(new FileOutputStream(choppedLog));

            try {
               long zxid = Long.decode(args[0]);
               if (chop(is, os, zxid)) {
                  rc = ExitCode.EXECUTION_FINISHED;
               }
            } catch (Throwable var10) {
               try {
                  os.close();
               } catch (Throwable var9) {
                  var10.addSuppressed(var9);
               }

               throw var10;
            }

            os.close();
         } catch (Throwable var11) {
            try {
               is.close();
            } catch (Throwable var8) {
               var11.addSuppressed(var8);
            }

            throw var11;
         }

         is.close();
      } catch (Exception e) {
         System.out.println("Got exception: " + e.getMessage());
      }

      ServiceUtils.requestSystemExit(rc.getValue());
   }

   public static boolean chop(InputStream is, OutputStream os, long zxid) throws IOException {
      BinaryInputArchive logStream = BinaryInputArchive.getArchive(is);
      BinaryOutputArchive choppedStream = BinaryOutputArchive.getArchive(os);
      FileHeader fhdr = new FileHeader();
      fhdr.deserialize(logStream, "fileheader");
      if (fhdr.getMagic() != FileTxnLog.TXNLOG_MAGIC) {
         System.err.println("Invalid magic number in txn log file");
         return false;
      } else {
         System.out.println("ZooKeeper Transactional Log File with dbid " + fhdr.getDbid() + " txnlog format version " + fhdr.getVersion());
         fhdr.serialize(choppedStream, "fileheader");
         int count = 0;
         boolean hasZxid = false;
         long previousZxid = -1L;

         while(true) {
            long crcValue;
            byte[] bytes;
            try {
               crcValue = logStream.readLong("crcvalue");
               bytes = logStream.readBuffer("txnEntry");
            } catch (EOFException var26) {
               System.out.println("EOF reached after " + count + " txns.");
               return false;
            }

            if (bytes.length == 0) {
               System.out.println("EOF reached after " + count + " txns.");
               return false;
            }

            Checksum crc = new Adler32();
            crc.update(bytes, 0, bytes.length);
            if (crcValue != crc.getValue()) {
               throw new IOException("CRC doesn't match " + crcValue + " vs " + crc.getValue());
            }

            TxnLogEntry entry = SerializeUtils.deserializeTxn(bytes);
            TxnHeader hdr = entry.getHeader();
            Record txn = entry.getTxn();
            if (logStream.readByte("EOR") != 66) {
               System.out.println("Last transaction was partial.");
               throw new EOFException("Last transaction was partial.");
            }

            long txnZxid = hdr.getZxid();
            if (txnZxid == zxid) {
               hasZxid = true;
            }

            if (previousZxid != -1L && txnZxid != previousZxid + 1L) {
               long txnEpoch = ZxidUtils.getEpochFromZxid(txnZxid);
               long txnCounter = ZxidUtils.getCounterFromZxid(txnZxid);
               long previousEpoch = ZxidUtils.getEpochFromZxid(previousZxid);
               if (txnEpoch == previousEpoch) {
                  System.out.println(String.format("There is intra-epoch gap between %x and %x", previousZxid, txnZxid));
               } else if (txnCounter != 1L) {
                  System.out.println(String.format("There is inter-epoch gap between %x and %x", previousZxid, txnZxid));
               }
            }

            previousZxid = txnZxid;
            if (txnZxid > zxid) {
               if (count != 0 && hasZxid) {
                  System.out.println(String.format("Chopping at %x new log has %d records", zxid, count));
                  return true;
               }

               System.out.println(String.format("This log does not contain zxid %x", zxid));
               return false;
            }

            choppedStream.writeLong(crcValue, "crcvalue");
            choppedStream.writeBuffer(bytes, "txnEntry");
            choppedStream.writeByte((byte)66, "EOR");
            ++count;
         }
      }
   }
}
