package org.apache.orc.impl.writer;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.StructColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.ColumnStatistics;
import org.apache.orc.StripeStatistics;
import org.apache.orc.TypeDescription;

public class StructTreeWriter extends TreeWriterBase {
   final TreeWriter[] childrenWriters;

   public StructTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      List<TypeDescription> children = schema.getChildren();
      this.childrenWriters = new TreeWriter[children.size()];

      for(int i = 0; i < this.childrenWriters.length; ++i) {
         this.childrenWriters[i] = TreeWriter.Factory.create((TypeDescription)children.get(i), encryption, context);
      }

      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   public void writeRootBatch(VectorizedRowBatch batch, int offset, int length) throws IOException {
      this.indexStatistics.increment(length);

      for(int i = 0; i < this.childrenWriters.length; ++i) {
         this.childrenWriters[i].writeBatch(batch.cols[i], offset, length);
      }

   }

   private static void writeFields(StructColumnVector vector, TreeWriter[] childrenWriters, int offset, int length) throws IOException {
      for(int field = 0; field < childrenWriters.length; ++field) {
         childrenWriters[field].writeBatch(vector.fields[field], offset, length);
      }

   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      StructColumnVector vec = (StructColumnVector)vector;
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            writeFields(vec, this.childrenWriters, offset, length);
         }
      } else if (vector.noNulls) {
         writeFields(vec, this.childrenWriters, offset, length);
      } else {
         int currentRun = 0;
         boolean started = false;

         for(int i = 0; i < length; ++i) {
            if (!vec.isNull[i + offset]) {
               if (!started) {
                  started = true;
                  currentRun = i;
               }
            } else if (started) {
               started = false;
               writeFields(vec, this.childrenWriters, offset + currentRun, i - currentRun);
            }
         }

         if (started) {
            writeFields(vec, this.childrenWriters, offset + currentRun, length - currentRun);
         }
      }

   }

   public void createRowIndexEntry() throws IOException {
      super.createRowIndexEntry();

      for(TreeWriter child : this.childrenWriters) {
         child.createRowIndexEntry();
      }

   }

   public void writeStripe(int requiredIndexEntries) throws IOException {
      super.writeStripe(requiredIndexEntries);

      for(TreeWriter child : this.childrenWriters) {
         child.writeStripe(requiredIndexEntries);
      }

      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   public void addStripeStatistics(StripeStatistics[] stats) throws IOException {
      super.addStripeStatistics(stats);

      for(TreeWriter child : this.childrenWriters) {
         child.addStripeStatistics(stats);
      }

   }

   public long estimateMemory() {
      long result = 0L;

      for(TreeWriter writer : this.childrenWriters) {
         result += writer.estimateMemory();
      }

      return super.estimateMemory() + result;
   }

   public long getRawDataSize() {
      long result = 0L;

      for(TreeWriter writer : this.childrenWriters) {
         result += writer.getRawDataSize();
      }

      return result;
   }

   public void writeFileStatistics() throws IOException {
      super.writeFileStatistics();

      for(TreeWriter child : this.childrenWriters) {
         child.writeFileStatistics();
      }

   }

   public void flushStreams() throws IOException {
      super.flushStreams();

      for(TreeWriter child : this.childrenWriters) {
         child.flushStreams();
      }

   }

   public void getCurrentStatistics(ColumnStatistics[] output) {
      super.getCurrentStatistics(output);

      for(TreeWriter child : this.childrenWriters) {
         child.getCurrentStatistics(output);
      }

   }

   public void prepareStripe(int stripeId) {
      super.prepareStripe(stripeId);

      for(TreeWriter child : this.childrenWriters) {
         child.prepareStripe(stripeId);
      }

   }
}
