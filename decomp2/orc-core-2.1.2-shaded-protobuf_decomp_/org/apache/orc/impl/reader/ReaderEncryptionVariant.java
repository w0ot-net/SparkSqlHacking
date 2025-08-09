package org.apache.orc.impl.reader;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.io.BytesWritable;
import org.apache.orc.EncryptionAlgorithm;
import org.apache.orc.EncryptionKey;
import org.apache.orc.EncryptionVariant;
import org.apache.orc.OrcProto;
import org.apache.orc.StripeInformation;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.ColumnarStripeStatistics;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.BufferChunk;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.InStream;
import org.apache.orc.impl.KeyProvider;
import org.apache.orc.impl.LocalKey;
import org.apache.orc.impl.ReaderImpl;
import org.apache.orc.impl.StripeStatisticsImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReaderEncryptionVariant implements EncryptionVariant {
   private static final Logger LOG = LoggerFactory.getLogger(ReaderEncryptionVariant.class);
   private final KeyProvider provider;
   private final ReaderEncryptionKey key;
   private final TypeDescription column;
   private final int variantId;
   private final BufferChunk tailBuffer;
   private final List stripeStats;
   private final LocalKey[] localKeys;
   private final LocalKey footerKey;
   private final int stripeCount;
   private final long stripeStatsOffset;

   ReaderEncryptionVariant(ReaderEncryptionKey key, int variantId, OrcProto.EncryptionVariant proto, TypeDescription schema, List stripes, long stripeStatsOffset, BufferChunk tailBuffer, KeyProvider provider) {
      this.key = key;
      this.variantId = variantId;
      this.provider = provider;
      this.column = proto != null && proto.hasRoot() ? schema.findSubtype(proto.getRoot()) : schema;
      this.localKeys = new LocalKey[stripes.size()];
      HashMap<BytesWritable, LocalKey> cache = new HashMap();
      this.stripeCount = stripes.size();
      this.stripeStatsOffset = stripeStatsOffset;
      if (proto != null && proto.hasEncryptedKey()) {
         for(int s = 0; s < this.localKeys.length; ++s) {
            StripeInformation stripe = (StripeInformation)stripes.get(s);
            this.localKeys[s] = getCachedKey(cache, key.getAlgorithm(), stripe.getEncryptedLocalKeys()[variantId]);
         }

         this.footerKey = getCachedKey(cache, key.getAlgorithm(), proto.getEncryptedKey().toByteArray());
         key.addVariant(this);
         this.stripeStats = proto.getStripeStatisticsList();
         this.tailBuffer = tailBuffer;
      } else {
         this.footerKey = null;
         this.stripeStats = null;
         this.tailBuffer = null;
      }

   }

   public ReaderEncryptionKey getKeyDescription() {
      return this.key;
   }

   public TypeDescription getRoot() {
      return this.column;
   }

   public int getVariantId() {
      return this.variantId;
   }

   private static LocalKey getCachedKey(Map cache, EncryptionAlgorithm algorithm, byte[] encrypted) {
      BytesWritable wrap = new BytesWritable(encrypted);
      LocalKey result = (LocalKey)cache.get(wrap);
      if (result == null) {
         result = new LocalKey(algorithm, (byte[])null, encrypted);
         cache.put(wrap, result);
      }

      return result;
   }

   private Key getDecryptedKey(LocalKey localKey) throws IOException {
      Key result = localKey.getDecryptedKey();
      if (result == null) {
         switch (this.key.getState()) {
            case UNTRIED:
               try {
                  result = this.provider.decryptLocalKey(this.key.getMetadata(), localKey.getEncryptedKey());
               } catch (IOException var4) {
                  LOG.info("Can't decrypt using key {}", this.key);
               }

               if (result != null) {
                  localKey.setDecryptedKey(result);
                  this.key.setSuccess();
               } else {
                  this.key.setFailure();
               }
               break;
            case SUCCESS:
               result = this.provider.decryptLocalKey(this.key.getMetadata(), localKey.getEncryptedKey());
               if (result == null) {
                  throw new IOException("Can't decrypt local key " + String.valueOf(this.key));
               }

               localKey.setDecryptedKey(result);
               break;
            case FAILURE:
               return null;
         }
      }

      return result;
   }

   public Key getFileFooterKey() throws IOException {
      return this.key != null && this.provider != null ? this.getDecryptedKey(this.footerKey) : null;
   }

   public Key getStripeKey(long stripe) throws IOException {
      return this.key != null && this.provider != null ? this.getDecryptedKey(this.localKeys[(int)stripe]) : null;
   }

   public boolean equals(Object other) {
      if (other != null && other.getClass() == this.getClass()) {
         return this.compareTo((EncryptionVariant)other) == 0;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.key.hashCode() * 127 + this.column.getId();
   }

   public int compareTo(@NotNull EncryptionVariant other) {
      if (other == this) {
         return 0;
      } else {
         EncryptionKey otherKey = other.getKeyDescription();
         if (this.key == otherKey) {
            return Integer.compare(this.column.getId(), other.getRoot().getId());
         } else if (this.key == null) {
            return -1;
         } else {
            return otherKey == null ? 1 : this.key.compareTo(other.getKeyDescription());
         }
      }
   }

   public long getStripeStatisticsLength() {
      long result = 0L;

      for(OrcProto.Stream stream : this.stripeStats) {
         result += stream.getLength();
      }

      return result;
   }

   public List getStripeStatistics(boolean[] columns, InStream.StreamOptions compression, ReaderImpl reader) throws IOException {
      StripeStatisticsImpl[] result = new StripeStatisticsImpl[this.stripeCount];

      for(int s = 0; s < result.length; ++s) {
         result[s] = new StripeStatisticsImpl(this.column, reader.writerUsedProlepticGregorian(), reader.getConvertToProlepticGregorian());
      }

      long offset = this.stripeStatsOffset;
      Key fileKey = this.getFileFooterKey();
      if (fileKey == null) {
         throw new IOException("Can't get file footer key for " + this.key.getKeyName());
      } else {
         int root = this.column.getId();

         for(OrcProto.Stream stream : this.stripeStats) {
            long length = stream.getLength();
            int column = stream.getColumn();
            OrcProto.Stream.Kind kind = stream.getKind();
            if (kind == Kind.STRIPE_STATISTICS && (columns == null || columns[column])) {
               byte[] iv = new byte[this.key.getAlgorithm().getIvLength()];
               CryptoUtils.modifyIvForStream(column, kind, (long)(this.stripeCount + 1)).accept(iv);
               InStream.StreamOptions options = (new InStream.StreamOptions(compression)).withEncryption(this.key.getAlgorithm(), fileKey, iv);
               OrcProto.ColumnarStripeStatistics stat = ColumnarStripeStatistics.parseFrom(InStream.createCodedInputStream(InStream.create(stream, this.tailBuffer, offset, length, options)));

               for(int s = 0; s < result.length; ++s) {
                  result[s].updateColumn(column - root, stat.getColStats(s));
               }
            }

            offset += length;
         }

         return Arrays.asList(result);
      }
   }
}
