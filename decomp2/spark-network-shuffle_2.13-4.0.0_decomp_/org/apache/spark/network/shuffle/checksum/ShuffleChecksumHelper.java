package org.apache.spark.network.shuffle.checksum;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CRC32C;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.spark.annotation.Private;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.PATH.;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.sparkproject.guava.io.ByteStreams;

@Private
public class ShuffleChecksumHelper {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(ShuffleChecksumHelper.class);
   public static final int CHECKSUM_CALCULATION_BUFFER = 8192;
   public static final Checksum[] EMPTY_CHECKSUM = new Checksum[0];
   public static final long[] EMPTY_CHECKSUM_VALUE = new long[0];

   public static Checksum[] createPartitionChecksums(int numPartitions, String algorithm) {
      return getChecksumsByAlgorithm(numPartitions, algorithm);
   }

   private static Checksum[] getChecksumsByAlgorithm(int num, String algorithm) {
      Checksum[] checksums;
      switch (algorithm) {
         case "ADLER32":
            checksums = new Adler32[num];

            for(int i = 0; i < num; ++i) {
               checksums[i] = new Adler32();
            }
            break;
         case "CRC32":
            checksums = new CRC32[num];

            for(int i = 0; i < num; ++i) {
               checksums[i] = new CRC32();
            }
            break;
         case "CRC32C":
            checksums = new CRC32C[num];

            for(int i = 0; i < num; ++i) {
               checksums[i] = new CRC32C();
            }
            break;
         default:
            throw new UnsupportedOperationException("Unsupported shuffle checksum algorithm: " + algorithm);
      }

      return checksums;
   }

   public static Checksum getChecksumByAlgorithm(String algorithm) {
      return getChecksumsByAlgorithm(1, algorithm)[0];
   }

   public static String getChecksumFileName(String blockName, String algorithm) {
      return String.format("%s.%s", blockName, algorithm);
   }

   private static long readChecksumByReduceId(File checksumFile, int reduceId) throws IOException {
      DataInputStream in = new DataInputStream(new FileInputStream(checksumFile));

      long var3;
      try {
         ByteStreams.skipFully(in, (long)reduceId * 8L);
         var3 = in.readLong();
      } catch (Throwable var6) {
         try {
            in.close();
         } catch (Throwable var5) {
            var6.addSuppressed(var5);
         }

         throw var6;
      }

      in.close();
      return var3;
   }

   private static long calculateChecksumForPartition(ManagedBuffer partitionData, Checksum checksumAlgo) throws IOException {
      InputStream in = partitionData.createInputStream();
      byte[] buffer = new byte[8192];
      CheckedInputStream checksumIn = new CheckedInputStream(in, checksumAlgo);

      long var5;
      try {
         while(checksumIn.read(buffer, 0, 8192) != -1) {
         }

         var5 = checksumAlgo.getValue();
      } catch (Throwable var8) {
         try {
            checksumIn.close();
         } catch (Throwable var7) {
            var8.addSuppressed(var7);
         }

         throw var8;
      }

      checksumIn.close();
      return var5;
   }

   public static Cause diagnoseCorruption(String algorithm, File checksumFile, int reduceId, ManagedBuffer partitionData, long checksumByReader) {
      long duration = -1L;
      long checksumByWriter = -1L;
      long checksumByReCalculation = -1L;

      Cause cause;
      try {
         long diagnoseStartNs = System.nanoTime();
         Checksum checksumAlgo = getChecksumByAlgorithm(algorithm);
         checksumByWriter = readChecksumByReduceId(checksumFile, reduceId);
         checksumByReCalculation = calculateChecksumForPartition(partitionData, checksumAlgo);
         duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - diagnoseStartNs);
         if (checksumByWriter != checksumByReCalculation) {
            cause = Cause.DISK_ISSUE;
         } else if (checksumByWriter != checksumByReader) {
            cause = Cause.NETWORK_ISSUE;
         } else {
            cause = Cause.CHECKSUM_VERIFY_PASS;
         }
      } catch (UnsupportedOperationException var16) {
         cause = Cause.UNSUPPORTED_CHECKSUM_ALGORITHM;
      } catch (FileNotFoundException var17) {
         logger.warn("Checksum file {} doesn't exit", new MDC[]{MDC.of(.MODULE$, checksumFile.getName())});
         cause = Cause.UNKNOWN_ISSUE;
      } catch (Exception e) {
         logger.warn("Unable to diagnose shuffle block corruption", e);
         cause = Cause.UNKNOWN_ISSUE;
      }

      if (logger.isDebugEnabled()) {
         logger.debug("Shuffle corruption diagnosis took {} ms, checksum file {}, cause {}, checksumByReader {}, checksumByWriter {}, checksumByReCalculation {}", new Object[]{duration, checksumFile.getAbsolutePath(), cause, checksumByReader, checksumByWriter, checksumByReCalculation});
      } else {
         logger.info("Shuffle corruption diagnosis took {} ms, checksum file {}, cause {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.TIME..MODULE$, duration), MDC.of(.MODULE$, checksumFile.getAbsolutePath()), MDC.of(org.apache.spark.internal.LogKeys.REASON..MODULE$, cause)});
      }

      return cause;
   }
}
