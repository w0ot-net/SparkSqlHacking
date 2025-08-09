package org.apache.orc.impl.reader;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.orc.OrcProto;
import org.apache.orc.StripeInformation;
import org.apache.orc.TypeDescription;
import org.apache.orc.impl.BufferChunk;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.KeyProvider;
import org.apache.orc.impl.MaskDescriptionImpl;

public class ReaderEncryption {
   private final KeyProvider keyProvider;
   private final ReaderEncryptionKey[] keys;
   private final MaskDescriptionImpl[] masks;
   private final ReaderEncryptionVariant[] variants;
   private final ReaderEncryptionVariant[] columnVariants;

   public ReaderEncryption() {
      this.keyProvider = null;
      this.keys = new ReaderEncryptionKey[0];
      this.masks = new MaskDescriptionImpl[0];
      this.variants = new ReaderEncryptionVariant[0];
      this.columnVariants = null;
   }

   public ReaderEncryption(OrcProto.Footer footer, TypeDescription schema, long stripeStatisticsOffset, BufferChunk serializedTail, List stripes, KeyProvider provider, Configuration conf) throws IOException {
      if (footer != null && footer.hasEncryption()) {
         this.keyProvider = provider != null ? provider : CryptoUtils.getKeyProvider(conf, new SecureRandom());
         OrcProto.Encryption encrypt = footer.getEncryption();
         this.masks = new MaskDescriptionImpl[encrypt.getMaskCount()];

         for(int m = 0; m < this.masks.length; ++m) {
            this.masks[m] = new MaskDescriptionImpl(m, encrypt.getMask(m));
         }

         this.keys = new ReaderEncryptionKey[encrypt.getKeyCount()];

         for(int k = 0; k < this.keys.length; ++k) {
            this.keys[k] = new ReaderEncryptionKey(encrypt.getKey(k));
         }

         this.variants = new ReaderEncryptionVariant[encrypt.getVariantsCount()];
         long offset = stripeStatisticsOffset;

         for(int v = 0; v < this.variants.length; ++v) {
            OrcProto.EncryptionVariant variant = encrypt.getVariants(v);
            this.variants[v] = new ReaderEncryptionVariant(this.keys[variant.getKey()], v, variant, schema, stripes, offset, serializedTail, this.keyProvider);
            offset += this.variants[v].getStripeStatisticsLength();
         }

         this.columnVariants = new ReaderEncryptionVariant[schema.getMaximumId() + 1];

         for(ReaderEncryptionVariant variant : this.variants) {
            TypeDescription root = variant.getRoot();

            for(int c = root.getId(); c <= root.getMaximumId(); ++c) {
               if (this.columnVariants[c] == null) {
                  this.columnVariants[c] = variant;
               }
            }
         }
      } else {
         this.keyProvider = null;
         this.keys = new ReaderEncryptionKey[0];
         this.masks = new MaskDescriptionImpl[0];
         this.variants = new ReaderEncryptionVariant[0];
         this.columnVariants = null;
      }

   }

   public MaskDescriptionImpl[] getMasks() {
      return this.masks;
   }

   public ReaderEncryptionKey[] getKeys() {
      return this.keys;
   }

   public ReaderEncryptionVariant[] getVariants() {
      return this.variants;
   }

   private ReaderEncryptionVariant findNextVariant(int column, int lastVariant) {
      for(int v = lastVariant + 1; v < this.variants.length; ++v) {
         TypeDescription root = this.variants[v].getRoot();
         if (root.getId() <= column && column <= root.getMaximumId()) {
            return this.variants[v];
         }
      }

      return null;
   }

   public ReaderEncryptionVariant getVariant(int column) {
      if (this.columnVariants == null) {
         return null;
      } else {
         while(this.columnVariants[column] != null && !this.columnVariants[column].getKeyDescription().isAvailable()) {
            if (this.keyProvider != null) {
               this.columnVariants[column] = this.findNextVariant(column, this.columnVariants[column].getVariantId());
            }
         }

         return this.columnVariants[column];
      }
   }
}
