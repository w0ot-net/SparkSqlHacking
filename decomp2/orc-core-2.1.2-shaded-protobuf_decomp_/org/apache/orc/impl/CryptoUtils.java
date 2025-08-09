package org.apache.orc.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import org.apache.hadoop.conf.Configuration;
import org.apache.orc.InMemoryKeystore;
import org.apache.orc.OrcConf;
import org.apache.orc.OrcProto;

public class CryptoUtils {
   private static final int COLUMN_ID_LENGTH = 3;
   private static final int KIND_LENGTH = 2;
   private static final int STRIPE_ID_LENGTH = 3;
   private static final int MIN_COUNT_BYTES = 8;
   static final int MAX_COLUMN = 16777215;
   static final int MAX_KIND = 65535;
   static final int MAX_STRIPE = 16777215;
   private static final Map keyProviderCache = new HashMap();

   public static Consumer modifyIvForStream(StreamName name, long stripeId) {
      return modifyIvForStream(name.getColumn(), name.getKind(), stripeId);
   }

   public static Consumer modifyIvForStream(int columnId, OrcProto.Stream.Kind kind, long stripeId) {
      if (columnId >= 0 && columnId <= 16777215) {
         int k = kind.getNumber();
         if (k >= 0 && k <= 65535) {
            return (iv) -> {
               if (iv.length - 8 < 8) {
                  throw new IllegalArgumentException("Not enough space in the iv for the count");
               } else {
                  iv[0] = (byte)(columnId >> 16);
                  iv[1] = (byte)(columnId >> 8);
                  iv[2] = (byte)columnId;
                  iv[3] = (byte)(k >> 8);
                  iv[4] = (byte)k;
                  modifyIvForStripe(stripeId).accept(iv);
               }
            };
         } else {
            throw new IllegalArgumentException("ORC encryption is limited to 65535 stream kinds. Value = " + k);
         }
      } else {
         throw new IllegalArgumentException("ORC encryption is limited to 16777215 columns. Value = " + columnId);
      }
   }

   public static Consumer modifyIvForStripe(long stripeId) {
      if (stripeId >= 1L && stripeId <= 16777215L) {
         return (iv) -> {
            iv[5] = (byte)((int)(stripeId >> 16));
            iv[6] = (byte)((int)(stripeId >> 8));
            iv[7] = (byte)((int)stripeId);
            clearCounter(iv);
         };
      } else {
         throw new IllegalArgumentException("ORC encryption is limited to 16777215 stripes. Value = " + stripeId);
      }
   }

   public static void clearCounter(byte[] iv) {
      for(int i = 8; i < iv.length; ++i) {
         iv[i] = 0;
      }

   }

   public static KeyProvider getKeyProvider(Configuration conf, Random random) throws IOException {
      String kind = OrcConf.KEY_PROVIDER.getString(conf);
      String cacheKey = kind + "." + random.getClass().getName();
      KeyProvider result = (KeyProvider)keyProviderCache.get(cacheKey);
      if (result == null) {
         for(KeyProvider.Factory factory : ServiceLoader.load(KeyProvider.Factory.class)) {
            result = factory.create(kind, conf, random);
            if (result != null) {
               keyProviderCache.put(cacheKey, result);
               break;
            }
         }
      }

      return result;
   }

   public static class HadoopKeyProviderFactory implements KeyProvider.Factory {
      public KeyProvider create(String kind, Configuration conf, Random random) throws IOException {
         if ("hadoop".equals(kind)) {
            return HadoopShimsFactory.get().getHadoopKeyProvider(conf, random);
         } else {
            return "memory".equals(kind) ? new InMemoryKeystore(random) : null;
         }
      }
   }
}
