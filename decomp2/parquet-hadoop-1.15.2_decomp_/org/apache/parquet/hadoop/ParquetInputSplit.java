package org.apache.parquet.hadoop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;

/** @deprecated */
@Private
@Deprecated
public class ParquetInputSplit extends FileSplit implements Writable {
   private long end;
   private long[] rowGroupOffsets;

   public ParquetInputSplit() {
      super((Path)null, 0L, 0L, new String[0]);
   }

   /** @deprecated */
   @Deprecated
   public ParquetInputSplit(Path path, long start, long length, String[] hosts, List blocks, String requestedSchema, String fileSchema, Map extraMetadata, Map readSupportMetadata) {
      this(path, start, end(blocks, requestedSchema), length, hosts, offsets(blocks));
   }

   private static long end(List blocks, String requestedSchema) {
      MessageType requested = MessageTypeParser.parseMessageType(requestedSchema);
      long length = 0L;

      for(BlockMetaData block : blocks) {
         for(ColumnChunkMetaData column : block.getColumns()) {
            if (requested.containsPath(column.getPath().toArray())) {
               length += column.getTotalSize();
            }
         }
      }

      return length;
   }

   private static long[] offsets(List blocks) {
      long[] offsets = new long[blocks.size()];

      for(int i = 0; i < offsets.length; ++i) {
         offsets[i] = ((BlockMetaData)blocks.get(i)).getStartingPos();
      }

      return offsets;
   }

   /** @deprecated */
   @Deprecated
   public List getBlocks() {
      throw new UnsupportedOperationException("Splits no longer have row group metadata, see PARQUET-234");
   }

   static ParquetInputSplit from(FileSplit split) throws IOException {
      return new ParquetInputSplit(split.getPath(), split.getStart(), split.getStart() + split.getLength(), split.getLength(), split.getLocations(), (long[])null);
   }

   static ParquetInputSplit from(org.apache.hadoop.mapred.FileSplit split) throws IOException {
      return new ParquetInputSplit(split.getPath(), split.getStart(), split.getStart() + split.getLength(), split.getLength(), split.getLocations(), (long[])null);
   }

   public ParquetInputSplit(Path file, long start, long end, long length, String[] hosts, long[] rowGroupOffsets) {
      super(file, start, length, hosts);
      this.end = end;
      this.rowGroupOffsets = rowGroupOffsets;
   }

   /** @deprecated */
   @Deprecated
   String getRequestedSchema() {
      throw new UnsupportedOperationException("Splits no longer have the requested schema, see PARQUET-234");
   }

   /** @deprecated */
   @Deprecated
   public String getFileSchema() {
      throw new UnsupportedOperationException("Splits no longer have the file schema, see PARQUET-234");
   }

   public long getEnd() {
      return this.end;
   }

   /** @deprecated */
   @Deprecated
   public Map getExtraMetadata() {
      throw new UnsupportedOperationException("Splits no longer have file metadata, see PARQUET-234");
   }

   /** @deprecated */
   @Deprecated
   Map getReadSupportMetadata() {
      throw new UnsupportedOperationException("Splits no longer have read-support metadata, see PARQUET-234");
   }

   /** @deprecated */
   public long[] getRowGroupOffsets() {
      return this.rowGroupOffsets;
   }

   public String toString() {
      String hosts;
      try {
         hosts = Arrays.toString(this.getLocations());
      } catch (Exception e) {
         hosts = "(" + e + ")";
      }

      return this.getClass().getSimpleName() + "{part: " + this.getPath() + " start: " + this.getStart() + " end: " + this.getEnd() + " length: " + this.getLength() + " hosts: " + hosts + (this.rowGroupOffsets == null ? "" : " row groups: " + Arrays.toString(this.rowGroupOffsets)) + "}";
   }

   public void readFields(DataInput hin) throws IOException {
      byte[] bytes = readArray(hin);
      DataInputStream in = new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(bytes)));
      super.readFields(in);
      this.end = in.readLong();
      if (in.readBoolean()) {
         this.rowGroupOffsets = new long[in.readInt()];

         for(int i = 0; i < this.rowGroupOffsets.length; ++i) {
            this.rowGroupOffsets[i] = in.readLong();
         }
      }

      in.close();
   }

   public void write(DataOutput hout) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(new GZIPOutputStream(baos));
      Throwable var4 = null;

      try {
         super.write(out);
         out.writeLong(this.end);
         out.writeBoolean(this.rowGroupOffsets != null);
         if (this.rowGroupOffsets != null) {
            out.writeInt(this.rowGroupOffsets.length);

            for(long o : this.rowGroupOffsets) {
               out.writeLong(o);
            }
         }

         out.flush();
      } catch (Throwable var17) {
         var4 = var17;
         throw var17;
      } finally {
         if (out != null) {
            if (var4 != null) {
               try {
                  out.close();
               } catch (Throwable var16) {
                  var4.addSuppressed(var16);
               }
            } else {
               out.close();
            }
         }

      }

      writeArray(hout, baos.toByteArray());
   }

   private static void writeArray(DataOutput out, byte[] bytes) throws IOException {
      out.writeInt(bytes.length);
      out.write(bytes, 0, bytes.length);
   }

   private static byte[] readArray(DataInput in) throws IOException {
      int len = in.readInt();
      byte[] bytes = new byte[len];
      in.readFully(bytes);
      return bytes;
   }
}
