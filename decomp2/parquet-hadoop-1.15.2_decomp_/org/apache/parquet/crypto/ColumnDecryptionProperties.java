package org.apache.parquet.crypto;

import org.apache.parquet.hadoop.metadata.ColumnPath;

public class ColumnDecryptionProperties {
   private final ColumnPath columnPath;
   private final byte[] keyBytes;

   private ColumnDecryptionProperties(ColumnPath columnPath, byte[] keyBytes) {
      if (null == columnPath) {
         throw new IllegalArgumentException("Null column path");
      } else if (null == keyBytes) {
         throw new IllegalArgumentException("Null key for column " + columnPath);
      } else if (keyBytes.length != 16 && keyBytes.length != 24 && keyBytes.length != 32) {
         throw new IllegalArgumentException("Wrong key length: " + keyBytes.length + " on column: " + columnPath);
      } else {
         this.columnPath = columnPath;
         this.keyBytes = keyBytes;
      }
   }

   public static Builder builder(String name) {
      return builder(ColumnPath.get(new String[]{name}));
   }

   public static Builder builder(ColumnPath path) {
      return new Builder(path);
   }

   public ColumnPath getPath() {
      return this.columnPath;
   }

   public byte[] getKeyBytes() {
      return this.keyBytes;
   }

   public static class Builder {
      private final ColumnPath columnPath;
      private byte[] keyBytes;

      private Builder(ColumnPath path) {
         this.columnPath = path;
      }

      public Builder withKey(byte[] columnKey) {
         if (null != this.keyBytes) {
            throw new IllegalStateException("Key already set on column: " + this.columnPath);
         } else {
            this.keyBytes = new byte[columnKey.length];
            System.arraycopy(columnKey, 0, this.keyBytes, 0, columnKey.length);
            return this;
         }
      }

      public ColumnDecryptionProperties build() {
         return new ColumnDecryptionProperties(this.columnPath, this.keyBytes);
      }
   }
}
