package org.apache.orc.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.orc.CompressionCodec;
import org.apache.orc.CompressionKind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class OrcCodecPool {
   private static final Logger LOG = LoggerFactory.getLogger(OrcCodecPool.class);
   private static final ConcurrentHashMap POOL = new ConcurrentHashMap();
   private static final int MAX_PER_KIND = 32;

   public static CompressionCodec getCodec(CompressionKind kind) {
      if (kind == CompressionKind.NONE) {
         return null;
      } else {
         CompressionCodec codec = null;
         List<CompressionCodec> codecList = (List)POOL.get(kind);
         if (codecList != null) {
            synchronized(codecList) {
               if (!codecList.isEmpty()) {
                  codec = (CompressionCodec)codecList.remove(codecList.size() - 1);
               }
            }
         }

         if (codec == null) {
            codec = WriterImpl.createCodec(kind);
            LOG.debug("Got brand-new codec {}", kind);
         } else {
            LOG.debug("Got recycled codec");
         }

         return codec;
      }
   }

   public static void returnCodec(CompressionKind kind, CompressionCodec codec) {
      if (codec != null) {
         assert kind != CompressionKind.NONE;

         try {
            codec.reset();
            List<CompressionCodec> list = (List)POOL.get(kind);
            if (list == null) {
               List<CompressionCodec> newList = new ArrayList();
               List<CompressionCodec> oldList = (List)POOL.putIfAbsent(kind, newList);
               list = oldList == null ? newList : oldList;
            }

            synchronized(list) {
               if (list.size() < 32) {
                  list.add(codec);
                  return;
               }
            }

            codec.destroy();
         } catch (Exception ex) {
            LOG.error("Ignoring codec cleanup error", ex);
         }

      }
   }

   public static int getPoolSize(CompressionKind kind) {
      if (kind == CompressionKind.NONE) {
         return 0;
      } else {
         List<CompressionCodec> codecList = (List)POOL.get(kind);
         if (codecList == null) {
            return 0;
         } else {
            synchronized(codecList) {
               return codecList.size();
            }
         }
      }
   }

   public static void clear() {
      POOL.clear();
   }

   private OrcCodecPool() {
   }
}
