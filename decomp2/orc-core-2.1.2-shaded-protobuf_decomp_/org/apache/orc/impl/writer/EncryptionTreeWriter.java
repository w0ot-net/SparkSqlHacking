package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.ColumnStatistics;
import org.apache.orc.DataMask;
import org.apache.orc.StripeStatistics;
import org.apache.orc.TypeDescription;
import org.apache.orc.impl.TypeUtils;

public class EncryptionTreeWriter implements TreeWriter {
   private final TreeWriter[] childrenWriters;
   private final DataMask[] masks;
   private final ColumnVector scratch;
   private final VectorizedRowBatch scratchBatch;

   EncryptionTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      this.scratch = TypeUtils.createColumn(schema, TypeDescription.RowBatchVersion.USE_DECIMAL64, 1024);
      this.childrenWriters = new TreeWriterBase[2];
      this.masks = new DataMask[this.childrenWriters.length];
      if (schema.getCategory() == TypeDescription.Category.STRUCT) {
         this.scratchBatch = new VectorizedRowBatch(schema.getChildren().size(), 1024);
      } else {
         this.scratchBatch = new VectorizedRowBatch(1, 1024);
      }

      this.masks[0] = null;
      this.childrenWriters[0] = TreeWriter.Factory.createSubtree(schema, encryption, context);
      this.masks[1] = context.getUnencryptedMask(schema.getId());
      this.childrenWriters[1] = TreeWriter.Factory.createSubtree(schema, (WriterEncryptionVariant)null, context);
   }

   public void writeRootBatch(VectorizedRowBatch batch, int offset, int length) throws IOException {
      this.scratchBatch.ensureSize(offset + length);

      for(int alt = 0; alt < this.childrenWriters.length; ++alt) {
         if (this.masks[alt] == null) {
            this.childrenWriters[alt].writeRootBatch(batch, offset, length);
         } else {
            for(int col = 0; col < this.scratchBatch.cols.length; ++col) {
               this.masks[alt].maskData(batch.cols[col], this.scratchBatch.cols[col], offset, length);
            }

            this.childrenWriters[alt].writeRootBatch(this.scratchBatch, offset, length);
         }
      }

   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      this.scratch.ensureSize(length, false);

      for(int alt = 0; alt < this.childrenWriters.length; ++alt) {
         if (this.masks[alt] != null) {
            this.masks[alt].maskData(vector, this.scratch, offset, length);
            this.childrenWriters[alt].writeBatch(this.scratch, offset, length);
         } else {
            this.childrenWriters[alt].writeBatch(vector, offset, length);
         }
      }

   }

   public void createRowIndexEntry() throws IOException {
      for(TreeWriter child : this.childrenWriters) {
         child.createRowIndexEntry();
      }

   }

   public void flushStreams() throws IOException {
      for(TreeWriter child : this.childrenWriters) {
         child.flushStreams();
      }

   }

   public void writeStripe(int requiredIndexEntries) throws IOException {
      for(TreeWriter child : this.childrenWriters) {
         child.writeStripe(requiredIndexEntries);
      }

   }

   public void addStripeStatistics(StripeStatistics[] stripeStatistics) throws IOException {
      for(TreeWriter child : this.childrenWriters) {
         child.addStripeStatistics(stripeStatistics);
      }

   }

   public long estimateMemory() {
      long result = 0L;

      for(TreeWriter writer : this.childrenWriters) {
         result += writer.estimateMemory();
      }

      return result;
   }

   public long getRawDataSize() {
      return this.childrenWriters[0].getRawDataSize();
   }

   public void prepareStripe(int stripeId) {
      for(TreeWriter writer : this.childrenWriters) {
         writer.prepareStripe(stripeId);
      }

   }

   public void writeFileStatistics() throws IOException {
      for(TreeWriter child : this.childrenWriters) {
         child.writeFileStatistics();
      }

   }

   public void getCurrentStatistics(ColumnStatistics[] output) {
      this.childrenWriters[0].getCurrentStatistics(output);
   }
}
