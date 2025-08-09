package org.apache.orc.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.crypto.key.KeyProviderFactory;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.hdfs.client.HdfsDataOutputStream;
import org.apache.hadoop.hdfs.client.HdfsDataOutputStream.SyncFlag;
import org.apache.hadoop.io.compress.snappy.SnappyDecompressor;
import org.apache.hadoop.io.compress.zlib.ZlibDecompressor;
import org.apache.hadoop.io.compress.zlib.ZlibDecompressor.CompressionHeader;
import org.apache.orc.EncryptionAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HadoopShimsCurrent implements HadoopShims {
   private static final Logger LOG = LoggerFactory.getLogger(HadoopShimsCurrent.class);

   static HadoopShims.DirectDecompressor getDecompressor(HadoopShims.DirectCompressionType codec) {
      switch (codec) {
         case ZLIB -> {
            return new ZlibDirectDecompressWrapper(new ZlibDecompressor.ZlibDirectDecompressor());
         }
         case ZLIB_NOHEADER -> {
            return new ZlibDirectDecompressWrapper(new ZlibDecompressor.ZlibDirectDecompressor(CompressionHeader.NO_HEADER, 0));
         }
         case SNAPPY -> {
            return new SnappyDirectDecompressWrapper(new SnappyDecompressor.SnappyDirectDecompressor());
         }
         default -> {
            return null;
         }
      }
   }

   static EncryptionAlgorithm findAlgorithm(org.apache.hadoop.crypto.key.KeyProvider.Metadata meta) {
      String cipher = meta.getCipher();
      if (cipher.startsWith("AES/")) {
         int bitLength = meta.getBitLength();
         if (bitLength == 128) {
            return EncryptionAlgorithm.AES_CTR_128;
         } else {
            if (bitLength != 256) {
               LOG.info("ORC column encryption does not support " + bitLength + " bit keys. Using 256 bits instead.");
            }

            return EncryptionAlgorithm.AES_CTR_256;
         }
      } else {
         throw new IllegalArgumentException("ORC column encryption only supports AES and not " + cipher);
      }
   }

   static String buildKeyVersionName(HadoopShims.KeyMetadata key) {
      String var10000 = key.getKeyName();
      return var10000 + "@" + key.getVersion();
   }

   static KeyProvider createKeyProvider(Configuration conf, Random random) throws IOException {
      List<org.apache.hadoop.crypto.key.KeyProvider> result = KeyProviderFactory.getProviders(conf);
      if (result.size() == 0) {
         LOG.debug("Can't get KeyProvider for ORC encryption from hadoop.security.key.provider.path.");
         return new NullKeyProvider();
      } else {
         return new KeyProviderImpl((org.apache.hadoop.crypto.key.KeyProvider)result.get(0), random);
      }
   }

   public HadoopShims.DirectDecompressor getDirectDecompressor(HadoopShims.DirectCompressionType codec) {
      return getDecompressor(codec);
   }

   public HadoopShims.ZeroCopyReaderShim getZeroCopyReader(FSDataInputStream in, HadoopShims.ByteBufferPoolShim pool) throws IOException {
      return ZeroCopyShims.getZeroCopyReader(in, pool);
   }

   public boolean endVariableLengthBlock(OutputStream output) throws IOException {
      if (output instanceof HdfsDataOutputStream hdfs) {
         hdfs.hsync(EnumSet.of(SyncFlag.END_BLOCK));
         return true;
      } else {
         return false;
      }
   }

   public KeyProvider getHadoopKeyProvider(Configuration conf, Random random) throws IOException {
      return createKeyProvider(conf, random);
   }
}
