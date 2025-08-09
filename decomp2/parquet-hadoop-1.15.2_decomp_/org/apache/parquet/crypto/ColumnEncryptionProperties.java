package org.apache.parquet.crypto;

import java.nio.charset.StandardCharsets;
import org.apache.parquet.hadoop.metadata.ColumnPath;

public class ColumnEncryptionProperties {
   private final boolean encrypted;
   private final ColumnPath columnPath;
   private final boolean encryptedWithFooterKey;
   private final byte[] keyBytes;
   private final byte[] keyMetaData;

   private ColumnEncryptionProperties(boolean encrypted, ColumnPath columnPath, byte[] keyBytes, byte[] keyMetaData) {
      if (null == columnPath) {
         throw new IllegalArgumentException("Null column path");
      } else {
         if (!encrypted) {
            if (null != keyBytes) {
               throw new IllegalArgumentException("Setting key on unencrypted column: " + columnPath);
            }

            if (null != keyMetaData) {
               throw new IllegalArgumentException("Setting key metadata on unencrypted column: " + columnPath);
            }
         }

         if (null != keyBytes && keyBytes.length != 16 && keyBytes.length != 24 && keyBytes.length != 32) {
            throw new IllegalArgumentException("Wrong key length: " + keyBytes.length + ". Column: " + columnPath);
         } else {
            this.encryptedWithFooterKey = encrypted && null == keyBytes;
            if (this.encryptedWithFooterKey && null != keyMetaData) {
               throw new IllegalArgumentException("Setting key metadata on column encrypted with footer key:  " + columnPath);
            } else {
               this.encrypted = encrypted;
               this.columnPath = columnPath;
               this.keyBytes = keyBytes;
               this.keyMetaData = keyMetaData;
            }
         }
      }
   }

   public static Builder builder(String name) {
      return builder(ColumnPath.get(new String[]{name}), true);
   }

   public static Builder builder(ColumnPath path) {
      return builder(path, true);
   }

   public static Builder builder(ColumnPath path, boolean encrypt) {
      return new Builder(path, encrypt);
   }

   public ColumnPath getPath() {
      return this.columnPath;
   }

   public boolean isEncrypted() {
      return this.encrypted;
   }

   public byte[] getKeyBytes() {
      return this.keyBytes;
   }

   public boolean isEncryptedWithFooterKey() {
      return !this.encrypted ? false : this.encryptedWithFooterKey;
   }

   public byte[] getKeyMetaData() {
      return this.keyMetaData;
   }

   public static class Builder {
      private final boolean encrypted;
      private final ColumnPath columnPath;
      private byte[] keyBytes;
      private byte[] keyMetaData;

      private Builder(ColumnPath path, boolean encrypted) {
         this.encrypted = encrypted;
         this.columnPath = path;
      }

      public Builder withKey(byte[] columnKey) {
         if (null == columnKey) {
            return this;
         } else if (null != this.keyBytes) {
            throw new IllegalStateException("Key already set on column: " + this.columnPath);
         } else {
            this.keyBytes = new byte[columnKey.length];
            System.arraycopy(columnKey, 0, this.keyBytes, 0, columnKey.length);
            return this;
         }
      }

      public Builder withKeyMetaData(byte[] keyMetaData) {
         if (null == keyMetaData) {
            return this;
         } else if (null != this.keyMetaData) {
            throw new IllegalStateException("Key metadata already set on column: " + this.columnPath);
         } else {
            this.keyMetaData = keyMetaData;
            return this;
         }
      }

      public Builder withKeyID(String keyId) {
         if (null == keyId) {
            return this;
         } else {
            byte[] metaData = keyId.getBytes(StandardCharsets.UTF_8);
            return this.withKeyMetaData(metaData);
         }
      }

      public ColumnEncryptionProperties build() {
         return new ColumnEncryptionProperties(this.encrypted, this.columnPath, this.keyBytes, this.keyMetaData);
      }
   }
}
