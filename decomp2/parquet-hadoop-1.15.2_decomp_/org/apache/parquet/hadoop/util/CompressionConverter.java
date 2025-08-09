package org.apache.parquet.hadoop.util;

import java.io.IOException;
import java.util.List;
import org.apache.parquet.ParquetReadOptions;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.format.PageHeader;
import org.apache.parquet.format.Util;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.hadoop.rewrite.MaskMode;
import org.apache.parquet.hadoop.rewrite.ParquetRewriter;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.SeekableInputStream;
import org.apache.parquet.schema.MessageType;

/** @deprecated */
@Deprecated
public class CompressionConverter {
   private ParquetRewriter rewriter;

   public void processBlocks(TransParquetFileReader reader, ParquetFileWriter writer, ParquetMetadata meta, MessageType schema, String createdBy, CompressionCodecName codecName) throws IOException {
      this.rewriter = new ParquetRewriter(reader, writer, meta, schema, createdBy, codecName, (List)null, (MaskMode)null);
      this.rewriter.processBlocks();
   }

   public BytesInput readBlock(int length, TransParquetFileReader reader) throws IOException {
      return this.rewriter.readBlock(length, reader);
   }

   public BytesInput readBlockAllocate(int length, TransParquetFileReader reader) throws IOException {
      return this.rewriter.readBlockAllocate(length, reader);
   }

   public static final class TransParquetFileReader extends ParquetFileReader {
      public TransParquetFileReader(InputFile file, ParquetReadOptions options) throws IOException {
         super(file, options);
      }

      public void setStreamPosition(long newPos) throws IOException {
         this.f.seek(newPos);
      }

      public void blockRead(byte[] data, int start, int len) throws IOException {
         this.f.readFully(data, start, len);
      }

      public PageHeader readPageHeader() throws IOException {
         return Util.readPageHeader(this.f);
      }

      public long getPos() throws IOException {
         return this.f.getPos();
      }

      public SeekableInputStream getStream() {
         return this.f;
      }
   }
}
