package org.apache.parquet.crypto;

import org.apache.parquet.format.BlockCipher;
import org.apache.parquet.format.ColumnCryptoMetaData;
import org.apache.parquet.format.EncryptionWithColumnKey;
import org.apache.parquet.format.EncryptionWithFooterKey;

public class InternalColumnEncryptionSetup {
   private final ColumnEncryptionProperties encryptionProperties;
   private final BlockCipher.Encryptor metadataEncryptor;
   private final BlockCipher.Encryptor dataEncryptor;
   private final ColumnCryptoMetaData columnCryptoMetaData;
   private final int ordinal;

   InternalColumnEncryptionSetup(ColumnEncryptionProperties encryptionProperties, int ordinal, BlockCipher.Encryptor dataEncryptor, BlockCipher.Encryptor metaDataEncryptor) {
      this.encryptionProperties = encryptionProperties;
      this.dataEncryptor = dataEncryptor;
      this.metadataEncryptor = metaDataEncryptor;
      this.ordinal = ordinal;
      if (encryptionProperties.isEncrypted()) {
         if (encryptionProperties.isEncryptedWithFooterKey()) {
            this.columnCryptoMetaData = ColumnCryptoMetaData.ENCRYPTION_WITH_FOOTER_KEY(new EncryptionWithFooterKey());
         } else {
            EncryptionWithColumnKey withColumnKeyStruct = new EncryptionWithColumnKey(encryptionProperties.getPath().toList());
            if (null != encryptionProperties.getKeyMetaData()) {
               withColumnKeyStruct.setKey_metadata(encryptionProperties.getKeyMetaData());
            }

            this.columnCryptoMetaData = ColumnCryptoMetaData.ENCRYPTION_WITH_COLUMN_KEY(withColumnKeyStruct);
         }
      } else {
         this.columnCryptoMetaData = null;
      }

   }

   public boolean isEncrypted() {
      return this.encryptionProperties.isEncrypted();
   }

   public BlockCipher.Encryptor getMetaDataEncryptor() {
      return this.metadataEncryptor;
   }

   public BlockCipher.Encryptor getDataEncryptor() {
      return this.dataEncryptor;
   }

   public ColumnCryptoMetaData getColumnCryptoMetaData() {
      return this.columnCryptoMetaData;
   }

   public int getOrdinal() {
      return this.ordinal;
   }

   public boolean isEncryptedWithFooterKey() {
      return this.encryptionProperties.isEncryptedWithFooterKey();
   }
}
