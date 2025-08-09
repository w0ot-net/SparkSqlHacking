package org.apache.parquet.hadoop.metadata;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.parquet.column.EncodingStats;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.crypto.AesCipher;
import org.apache.parquet.crypto.InternalColumnDecryptionSetup;
import org.apache.parquet.crypto.InternalFileDecryptor;
import org.apache.parquet.crypto.ModuleCipherFactory;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import org.apache.parquet.format.ColumnMetaData;
import org.apache.parquet.format.Util;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.schema.PrimitiveType;

class EncryptedColumnChunkMetaData extends ColumnChunkMetaData {
   private final ParquetMetadataConverter parquetMetadataConverter;
   private final byte[] encryptedMetadata;
   private final byte[] columnKeyMetadata;
   private final InternalFileDecryptor fileDecryptor;
   private final int columnOrdinal;
   private final PrimitiveType primitiveType;
   private final String createdBy;
   private ColumnPath path;
   private boolean decrypted;
   private ColumnChunkMetaData shadowColumnChunkMetaData;

   EncryptedColumnChunkMetaData(ParquetMetadataConverter parquetMetadataConverter, ColumnPath path, PrimitiveType type, byte[] encryptedMetadata, byte[] columnKeyMetadata, InternalFileDecryptor fileDecryptor, int rowGroupOrdinal, int columnOrdinal, String createdBy) {
      super((EncodingStats)null, (ColumnChunkProperties)null);
      this.parquetMetadataConverter = parquetMetadataConverter;
      this.path = path;
      this.encryptedMetadata = encryptedMetadata;
      this.columnKeyMetadata = columnKeyMetadata;
      this.fileDecryptor = fileDecryptor;
      this.rowGroupOrdinal = rowGroupOrdinal;
      this.columnOrdinal = columnOrdinal;
      this.primitiveType = type;
      this.createdBy = createdBy;
      this.decrypted = false;
   }

   protected void decryptIfNeeded() {
      if (!this.decrypted) {
         if (null == this.fileDecryptor) {
            throw new ParquetCryptoRuntimeException(this.path + ". Null File Decryptor");
         } else {
            InternalColumnDecryptionSetup columnDecryptionSetup = this.fileDecryptor.setColumnCryptoMetadata(this.path, true, false, this.columnKeyMetadata, this.columnOrdinal);
            ByteArrayInputStream tempInputStream = new ByteArrayInputStream(this.encryptedMetadata);
            byte[] columnMetaDataAAD = AesCipher.createModuleAAD(this.fileDecryptor.getFileAAD(), ModuleCipherFactory.ModuleType.ColumnMetaData, this.rowGroupOrdinal, this.columnOrdinal, -1);

            ColumnMetaData metaData;
            try {
               metaData = Util.readColumnMetaData(tempInputStream, columnDecryptionSetup.getMetaDataDecryptor(), columnMetaDataAAD);
            } catch (IOException e) {
               throw new ParquetCryptoRuntimeException(this.path + ". Failed to decrypt column metadata", e);
            }

            this.decrypted = true;
            this.shadowColumnChunkMetaData = this.parquetMetadataConverter.buildColumnChunkMetaData(metaData, this.path, this.primitiveType, this.createdBy);
            this.encodingStats = this.shadowColumnChunkMetaData.encodingStats;
            this.properties = this.shadowColumnChunkMetaData.properties;
            if (metaData.isSetBloom_filter_offset()) {
               this.setBloomFilterOffset(metaData.getBloom_filter_offset());
            }

            if (metaData.isSetBloom_filter_length()) {
               this.setBloomFilterLength(metaData.getBloom_filter_length());
            }

         }
      }
   }

   public ColumnPath getPath() {
      return this.path;
   }

   public long getFirstDataPageOffset() {
      this.decryptIfNeeded();
      return this.shadowColumnChunkMetaData.getFirstDataPageOffset();
   }

   public long getDictionaryPageOffset() {
      this.decryptIfNeeded();
      return this.shadowColumnChunkMetaData.getDictionaryPageOffset();
   }

   public long getValueCount() {
      this.decryptIfNeeded();
      return this.shadowColumnChunkMetaData.getValueCount();
   }

   public long getTotalUncompressedSize() {
      this.decryptIfNeeded();
      return this.shadowColumnChunkMetaData.getTotalUncompressedSize();
   }

   public long getTotalSize() {
      this.decryptIfNeeded();
      return this.shadowColumnChunkMetaData.getTotalSize();
   }

   public Statistics getStatistics() {
      this.decryptIfNeeded();
      return this.shadowColumnChunkMetaData.getStatistics();
   }

   public SizeStatistics getSizeStatistics() {
      this.decryptIfNeeded();
      return this.shadowColumnChunkMetaData.getSizeStatistics();
   }

   public boolean isEncrypted() {
      return true;
   }
}
