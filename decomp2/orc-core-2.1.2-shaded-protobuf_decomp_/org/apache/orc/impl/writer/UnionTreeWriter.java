package org.apache.orc.impl.writer;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.UnionColumnVector;
import org.apache.orc.ColumnStatistics;
import org.apache.orc.StripeStatistics;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.RunLengthByteWriter;
import org.apache.orc.impl.StreamName;

public class UnionTreeWriter extends TreeWriterBase {
   private final RunLengthByteWriter tags;
   private final TreeWriter[] childrenWriters;

   UnionTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      List<TypeDescription> children = schema.getChildren();
      this.childrenWriters = new TreeWriterBase[children.size()];

      for(int i = 0; i < this.childrenWriters.length; ++i) {
         this.childrenWriters[i] = TreeWriter.Factory.create((TypeDescription)children.get(i), encryption, context);
      }

      this.tags = new RunLengthByteWriter(context.createStream(new StreamName(this.id, Kind.DATA, encryption)));
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      UnionColumnVector vec = (UnionColumnVector)vector;
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            byte tag = (byte)vec.tags[0];

            for(int i = 0; i < length; ++i) {
               this.tags.write(tag);
            }

            if (this.createBloomFilter) {
               if (this.bloomFilter != null) {
                  this.bloomFilter.addLong((long)tag);
               }

               this.bloomFilterUtf8.addLong((long)tag);
            }

            this.childrenWriters[tag].writeBatch(vec.fields[tag], offset, length);
         }
      } else {
         int[] currentStart = new int[vec.fields.length];
         int[] currentLength = new int[vec.fields.length];

         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               byte tag = (byte)vec.tags[offset + i];
               this.tags.write(tag);
               if (currentLength[tag] == 0) {
                  currentStart[tag] = i + offset;
                  currentLength[tag] = 1;
               } else if (currentStart[tag] + currentLength[tag] == i + offset) {
                  int var10002 = currentLength[tag]++;
               } else {
                  this.childrenWriters[tag].writeBatch(vec.fields[tag], currentStart[tag], currentLength[tag]);
                  currentStart[tag] = i + offset;
                  currentLength[tag] = 1;
               }

               if (this.createBloomFilter) {
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addLong((long)tag);
                  }

                  this.bloomFilterUtf8.addLong((long)tag);
               }
            }
         }

         for(int tag = 0; tag < currentStart.length; ++tag) {
            if (currentLength[tag] != 0) {
               this.childrenWriters[tag].writeBatch(vec.fields[tag], currentStart[tag], currentLength[tag]);
            }
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

   void recordPosition(PositionRecorder recorder) throws IOException {
      super.recordPosition(recorder);
      this.tags.getPosition(recorder);
   }

   public void addStripeStatistics(StripeStatistics[] stats) throws IOException {
      super.addStripeStatistics(stats);

      for(TreeWriter child : this.childrenWriters) {
         child.addStripeStatistics(stats);
      }

   }

   public long estimateMemory() {
      long children = 0L;

      for(TreeWriter writer : this.childrenWriters) {
         children += writer.estimateMemory();
      }

      return children + super.estimateMemory() + this.tags.estimateMemory();
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
      this.tags.flush();

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
      this.tags.changeIv(CryptoUtils.modifyIvForStripe((long)stripeId));

      for(TreeWriter child : this.childrenWriters) {
         child.prepareStripe(stripeId);
      }

   }
}
