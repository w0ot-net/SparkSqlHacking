package org.apache.parquet.format;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.parquet.format.event.Consumers;
import org.apache.parquet.format.event.EventBasedThriftReader;
import org.apache.parquet.format.event.TypedConsumer;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;
import shaded.parquet.org.apache.thrift.transport.TMemoryBuffer;
import shaded.parquet.org.apache.thrift.transport.TTransportException;

public class Util {
   private static final int INIT_MEM_ALLOC_ENCR_BUFFER = 100;

   public static void writeColumnIndex(ColumnIndex columnIndex, OutputStream to) throws IOException {
      writeColumnIndex(columnIndex, to, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public static void writeColumnIndex(ColumnIndex columnIndex, OutputStream to, BlockCipher.Encryptor encryptor, byte[] AAD) throws IOException {
      write(columnIndex, to, encryptor, AAD);
   }

   public static ColumnIndex readColumnIndex(InputStream from) throws IOException {
      return readColumnIndex(from, (BlockCipher.Decryptor)null, (byte[])null);
   }

   public static ColumnIndex readColumnIndex(InputStream from, BlockCipher.Decryptor decryptor, byte[] AAD) throws IOException {
      return (ColumnIndex)read(from, new ColumnIndex(), decryptor, AAD);
   }

   public static void writeOffsetIndex(OffsetIndex offsetIndex, OutputStream to) throws IOException {
      writeOffsetIndex(offsetIndex, to, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public static void writeOffsetIndex(OffsetIndex offsetIndex, OutputStream to, BlockCipher.Encryptor encryptor, byte[] AAD) throws IOException {
      write(offsetIndex, to, encryptor, AAD);
   }

   public static OffsetIndex readOffsetIndex(InputStream from) throws IOException {
      return readOffsetIndex(from, (BlockCipher.Decryptor)null, (byte[])null);
   }

   public static OffsetIndex readOffsetIndex(InputStream from, BlockCipher.Decryptor decryptor, byte[] AAD) throws IOException {
      return (OffsetIndex)read(from, new OffsetIndex(), decryptor, AAD);
   }

   public static BloomFilterHeader readBloomFilterHeader(InputStream from) throws IOException {
      return readBloomFilterHeader(from, (BlockCipher.Decryptor)null, (byte[])null);
   }

   public static void writeBloomFilterHeader(BloomFilterHeader header, OutputStream out) throws IOException {
      writeBloomFilterHeader(header, out, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public static BloomFilterHeader readBloomFilterHeader(InputStream from, BlockCipher.Decryptor decryptor, byte[] AAD) throws IOException {
      return (BloomFilterHeader)read(from, new BloomFilterHeader(), decryptor, AAD);
   }

   public static void writeBloomFilterHeader(BloomFilterHeader header, OutputStream out, BlockCipher.Encryptor encryptor, byte[] AAD) throws IOException {
      write(header, out, encryptor, AAD);
   }

   public static void writePageHeader(PageHeader pageHeader, OutputStream to) throws IOException {
      writePageHeader(pageHeader, to, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public static void writePageHeader(PageHeader pageHeader, OutputStream to, BlockCipher.Encryptor encryptor, byte[] AAD) throws IOException {
      write(pageHeader, to, encryptor, AAD);
   }

   public static PageHeader readPageHeader(InputStream from) throws IOException {
      return readPageHeader(from, (BlockCipher.Decryptor)null, (byte[])null);
   }

   public static PageHeader readPageHeader(InputStream from, BlockCipher.Decryptor decryptor, byte[] AAD) throws IOException {
      return MetadataValidator.validate((PageHeader)read(from, new PageHeader(), decryptor, AAD));
   }

   public static void writeFileMetaData(FileMetaData fileMetadata, OutputStream to) throws IOException {
      writeFileMetaData(fileMetadata, to, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public static void writeFileMetaData(FileMetaData fileMetadata, OutputStream to, BlockCipher.Encryptor encryptor, byte[] AAD) throws IOException {
      write(fileMetadata, to, encryptor, AAD);
   }

   public static FileMetaData readFileMetaData(InputStream from) throws IOException {
      return readFileMetaData(from, (BlockCipher.Decryptor)null, (byte[])null);
   }

   public static FileMetaData readFileMetaData(InputStream from, BlockCipher.Decryptor decryptor, byte[] AAD) throws IOException {
      return (FileMetaData)read(from, new FileMetaData(), decryptor, AAD);
   }

   public static void writeColumnMetaData(ColumnMetaData columnMetaData, OutputStream to, BlockCipher.Encryptor encryptor, byte[] AAD) throws IOException {
      write(columnMetaData, to, encryptor, AAD);
   }

   public static ColumnMetaData readColumnMetaData(InputStream from, BlockCipher.Decryptor decryptor, byte[] AAD) throws IOException {
      return (ColumnMetaData)read(from, new ColumnMetaData(), decryptor, AAD);
   }

   public static FileMetaData readFileMetaData(InputStream from, boolean skipRowGroups) throws IOException {
      return readFileMetaData(from, skipRowGroups, (BlockCipher.Decryptor)null, (byte[])null);
   }

   public static FileMetaData readFileMetaData(InputStream from, boolean skipRowGroups, BlockCipher.Decryptor decryptor, byte[] AAD) throws IOException {
      FileMetaData md = new FileMetaData();
      if (skipRowGroups) {
         readFileMetaData(from, new DefaultFileMetaDataConsumer(md), skipRowGroups, decryptor, AAD);
      } else {
         read(from, md, decryptor, AAD);
      }

      return md;
   }

   public static void writeFileCryptoMetaData(FileCryptoMetaData cryptoMetadata, OutputStream to) throws IOException {
      write(cryptoMetadata, to, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public static FileCryptoMetaData readFileCryptoMetaData(InputStream from) throws IOException {
      return (FileCryptoMetaData)read(from, new FileCryptoMetaData(), (BlockCipher.Decryptor)null, (byte[])null);
   }

   public static void readFileMetaData(InputStream from, FileMetaDataConsumer consumer) throws IOException {
      readFileMetaData(from, consumer, (BlockCipher.Decryptor)null, (byte[])null);
   }

   public static void readFileMetaData(InputStream from, FileMetaDataConsumer consumer, BlockCipher.Decryptor decryptor, byte[] AAD) throws IOException {
      readFileMetaData(from, consumer, false, decryptor, AAD);
   }

   public static void readFileMetaData(InputStream from, FileMetaDataConsumer consumer, boolean skipRowGroups) throws IOException {
      readFileMetaData(from, consumer, skipRowGroups, (BlockCipher.Decryptor)null, (byte[])null);
   }

   public static void readFileMetaData(InputStream input, final FileMetaDataConsumer consumer, boolean skipRowGroups, BlockCipher.Decryptor decryptor, byte[] AAD) throws IOException {
      try {
         Consumers.DelegatingFieldConsumer eventConsumer = Consumers.fieldConsumer().onField(FileMetaData._Fields.VERSION, new TypedConsumer.I32Consumer() {
            public void consume(int value) {
               consumer.setVersion(value);
            }
         }).onField(FileMetaData._Fields.SCHEMA, Consumers.listOf(SchemaElement.class, new Consumers.Consumer() {
            public void consume(List schema) {
               consumer.setSchema(schema);
            }
         })).onField(FileMetaData._Fields.NUM_ROWS, new TypedConsumer.I64Consumer() {
            public void consume(long value) {
               consumer.setNumRows(value);
            }
         }).onField(FileMetaData._Fields.KEY_VALUE_METADATA, Consumers.listElementsOf(Consumers.struct(KeyValue.class, new Consumers.Consumer() {
            public void consume(KeyValue kv) {
               consumer.addKeyValueMetaData(kv);
            }
         }))).onField(FileMetaData._Fields.CREATED_BY, new TypedConsumer.StringConsumer() {
            public void consume(String value) {
               consumer.setCreatedBy(value);
            }
         }).onField(FileMetaData._Fields.ENCRYPTION_ALGORITHM, Consumers.struct(EncryptionAlgorithm.class, new Consumers.Consumer() {
            public void consume(EncryptionAlgorithm encryptionAlgorithm) {
               consumer.setEncryptionAlgorithm(encryptionAlgorithm);
            }
         })).onField(FileMetaData._Fields.FOOTER_SIGNING_KEY_METADATA, new TypedConsumer.StringConsumer() {
            public void consume(String value) {
               byte[] keyMetadata = value.getBytes(StandardCharsets.UTF_8);
               consumer.setFooterSigningKeyMetadata(keyMetadata);
            }
         });
         if (!skipRowGroups) {
            eventConsumer = eventConsumer.onField(FileMetaData._Fields.ROW_GROUPS, Consumers.listElementsOf(Consumers.struct(RowGroup.class, new Consumers.Consumer() {
               public void consume(RowGroup rowGroup) {
                  consumer.addRowGroup(rowGroup);
               }
            })));
         }

         InputStream from;
         if (null == decryptor) {
            from = input;
         } else {
            byte[] plainText = decryptor.decrypt(input, AAD);
            from = new ByteArrayInputStream(plainText);
         }

         (new EventBasedThriftReader(protocol(from))).readStruct(eventConsumer);
      } catch (TException e) {
         throw new IOException("can not read FileMetaData: " + e.getMessage(), e);
      }
   }

   private static TProtocol protocol(OutputStream to) throws TTransportException {
      return protocol(new TIOStreamTransport(to));
   }

   private static TProtocol protocol(InputStream from) throws TTransportException {
      return protocol(new TIOStreamTransport(from));
   }

   private static InterningProtocol protocol(TIOStreamTransport t) {
      return new InterningProtocol(new TCompactProtocol(t));
   }

   private static TBase read(InputStream input, TBase tbase, BlockCipher.Decryptor decryptor, byte[] AAD) throws IOException {
      InputStream from;
      if (null == decryptor) {
         from = input;
      } else {
         byte[] plainText = decryptor.decrypt(input, AAD);
         from = new ByteArrayInputStream(plainText);
      }

      try {
         tbase.read(protocol(from));
         return tbase;
      } catch (TException e) {
         throw new IOException("can not read " + tbase.getClass() + ": " + e.getMessage(), e);
      }
   }

   private static void write(TBase tbase, OutputStream to, BlockCipher.Encryptor encryptor, byte[] AAD) throws IOException {
      if (null == encryptor) {
         try {
            tbase.write(protocol(to));
         } catch (TException e) {
            throw new IOException("can not write " + tbase, e);
         }
      } else {
         try {
            TMemoryBuffer thriftMemoryBuffer = new TMemoryBuffer(100);
            Throwable var5 = null;

            try {
               tbase.write(new InterningProtocol(new TCompactProtocol(thriftMemoryBuffer)));
               byte[] encryptedBuffer = encryptor.encrypt(thriftMemoryBuffer.getArray(), AAD);
               to.write(encryptedBuffer);
            } catch (Throwable var17) {
               var5 = var17;
               throw var17;
            } finally {
               if (thriftMemoryBuffer != null) {
                  if (var5 != null) {
                     try {
                        thriftMemoryBuffer.close();
                     } catch (Throwable var15) {
                        var5.addSuppressed(var15);
                     }
                  } else {
                     thriftMemoryBuffer.close();
                  }
               }

            }

         } catch (TException e) {
            throw new IOException("can not write " + tbase, e);
         }
      }
   }

   public abstract static class FileMetaDataConsumer {
      public abstract void setVersion(int var1);

      public abstract void setSchema(List var1);

      public abstract void setNumRows(long var1);

      public abstract void addRowGroup(RowGroup var1);

      public abstract void addKeyValueMetaData(KeyValue var1);

      public abstract void setCreatedBy(String var1);

      public abstract void setEncryptionAlgorithm(EncryptionAlgorithm var1);

      public abstract void setFooterSigningKeyMetadata(byte[] var1);
   }

   public static final class DefaultFileMetaDataConsumer extends FileMetaDataConsumer {
      private final FileMetaData md;

      public DefaultFileMetaDataConsumer(FileMetaData md) {
         this.md = md;
      }

      public void setVersion(int version) {
         this.md.setVersion(version);
      }

      public void setSchema(List schema) {
         this.md.setSchema(schema);
      }

      public void setNumRows(long numRows) {
         this.md.setNum_rows(numRows);
      }

      public void setCreatedBy(String createdBy) {
         this.md.setCreated_by(createdBy);
      }

      public void addRowGroup(RowGroup rowGroup) {
         this.md.addToRow_groups(rowGroup);
      }

      public void addKeyValueMetaData(KeyValue kv) {
         this.md.addToKey_value_metadata(kv);
      }

      public void setEncryptionAlgorithm(EncryptionAlgorithm encryptionAlgorithm) {
         this.md.setEncryption_algorithm(encryptionAlgorithm);
      }

      public void setFooterSigningKeyMetadata(byte[] footerSigningKeyMetadata) {
         this.md.setFooter_signing_key_metadata(footerSigningKeyMetadata);
      }
   }
}
