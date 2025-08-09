package org.apache.orc.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcProto;
import org.apache.orc.TypeDescription;
import org.apache.orc.protobuf.ByteString;

public class BloomFilterIO {
   private BloomFilterIO() {
   }

   public static BloomFilter deserialize(OrcProto.Stream.Kind kind, OrcProto.ColumnEncoding encoding, OrcFile.WriterVersion fileVersion, TypeDescription.Category type, OrcProto.BloomFilter bloomFilter) {
      if (bloomFilter == null) {
         return null;
      } else {
         int numFuncs = bloomFilter.getNumHashFunctions();
         switch (kind) {
            case BLOOM_FILTER:
               long[] values = new long[bloomFilter.getBitsetCount()];

               for(int i = 0; i < values.length; ++i) {
                  values[i] = bloomFilter.getBitset(i);
               }

               return (BloomFilter)(!fileVersion.includes(OrcFile.WriterVersion.HIVE_12055) || type != TypeDescription.Category.STRING && type != TypeDescription.Category.CHAR && type != TypeDescription.Category.VARCHAR ? new BloomFilter(values, numFuncs) : new BloomFilterUtf8(values, numFuncs));
            case BLOOM_FILTER_UTF8:
               Encoding version = BloomFilterIO.Encoding.from(encoding);
               if (version != BloomFilterIO.Encoding.FUTURE && (type != TypeDescription.Category.TIMESTAMP || version != BloomFilterIO.Encoding.ORIGINAL)) {
                  ByteString bits = bloomFilter.getUtf8Bitset();
                  long[] values = new long[bits.size() / 8];
                  bits.asReadOnlyByteBuffer().order(ByteOrder.LITTLE_ENDIAN).asLongBuffer().get(values);
                  return new BloomFilterUtf8(values, numFuncs);
               }

               return null;
            default:
               throw new IllegalArgumentException("Unknown bloom filter kind " + String.valueOf(kind));
         }
      }
   }

   public static void serialize(OrcProto.BloomFilter.Builder builder, BloomFilter bloomFilter) {
      builder.clear();
      builder.setNumHashFunctions(bloomFilter.getNumHashFunctions());
      long[] bitset = bloomFilter.getBitSet();
      if (bloomFilter instanceof BloomFilterUtf8) {
         ByteBuffer buffer = ByteBuffer.allocate(bitset.length * 8);
         buffer.order(ByteOrder.LITTLE_ENDIAN);
         buffer.asLongBuffer().put(bitset);
         builder.setUtf8Bitset(ByteString.copyFrom(buffer));
      } else {
         for(int i = 0; i < bitset.length; ++i) {
            builder.addBitset(bitset[i]);
         }
      }

   }

   public static enum Encoding {
      ORIGINAL(0),
      UTF8_UTC(1),
      FUTURE(Integer.MAX_VALUE);

      public static final Encoding CURRENT = UTF8_UTC;
      private final int id;

      private Encoding(int id) {
         this.id = id;
      }

      public int getId() {
         return this.id;
      }

      public static Encoding from(OrcProto.ColumnEncoding encoding) {
         if (!encoding.hasBloomEncoding()) {
            return ORIGINAL;
         } else {
            switch (encoding.getBloomEncoding()) {
               case 0 -> {
                  return ORIGINAL;
               }
               case 1 -> {
                  return UTF8_UTC;
               }
               default -> {
                  return FUTURE;
               }
            }
         }
      }

      // $FF: synthetic method
      private static Encoding[] $values() {
         return new Encoding[]{ORIGINAL, UTF8_UTC, FUTURE};
      }
   }
}
