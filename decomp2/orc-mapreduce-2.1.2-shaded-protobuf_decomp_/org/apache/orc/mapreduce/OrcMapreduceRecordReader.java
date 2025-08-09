package org.apache.orc.mapreduce;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.orc.Reader;
import org.apache.orc.TypeDescription;
import org.apache.orc.TypeDescription.Category;
import org.apache.orc.mapred.OrcMapredRecordReader;
import org.apache.orc.mapred.OrcStruct;

public class OrcMapreduceRecordReader extends RecordReader {
   private final TypeDescription schema;
   private final org.apache.orc.RecordReader batchReader;
   private final VectorizedRowBatch batch;
   private int rowInBatch;
   private final WritableComparable row;

   public OrcMapreduceRecordReader(org.apache.orc.RecordReader reader, TypeDescription schema) throws IOException {
      this.batchReader = reader;
      this.batch = schema.createRowBatch();
      this.schema = schema;
      this.rowInBatch = 0;
      this.row = OrcStruct.createValue(schema);
   }

   public OrcMapreduceRecordReader(Reader fileReader, Reader.Options options) throws IOException {
      this(fileReader, options, options.getRowBatchSize());
   }

   public OrcMapreduceRecordReader(Reader fileReader, Reader.Options options, int rowBatchSize) throws IOException {
      this.batchReader = fileReader.rows(options);
      if (options.getSchema() == null) {
         this.schema = fileReader.getSchema();
      } else {
         this.schema = options.getSchema();
      }

      this.batch = this.schema.createRowBatch(rowBatchSize);
      this.rowInBatch = 0;
      this.row = OrcStruct.createValue(this.schema);
   }

   boolean ensureBatch() throws IOException {
      if (this.rowInBatch >= this.batch.size) {
         this.rowInBatch = 0;
         return this.batchReader.nextBatch(this.batch);
      } else {
         return true;
      }
   }

   public void close() throws IOException {
      this.batchReader.close();
   }

   public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) {
   }

   public boolean nextKeyValue() throws IOException, InterruptedException {
      if (!this.ensureBatch()) {
         return false;
      } else {
         int rowIdx = this.batch.selectedInUse ? this.batch.selected[this.rowInBatch] : this.rowInBatch;
         if (this.schema.getCategory() == Category.STRUCT) {
            OrcStruct result = (OrcStruct)this.row;
            List<TypeDescription> children = this.schema.getChildren();
            int numberOfChildren = children.size();

            for(int i = 0; i < numberOfChildren; ++i) {
               result.setFieldValue(i, OrcMapredRecordReader.nextValue(this.batch.cols[i], rowIdx, (TypeDescription)children.get(i), result.getFieldValue(i)));
            }
         } else {
            OrcMapredRecordReader.nextValue(this.batch.cols[0], rowIdx, this.schema, this.row);
         }

         ++this.rowInBatch;
         return true;
      }
   }

   public NullWritable getCurrentKey() throws IOException, InterruptedException {
      return NullWritable.get();
   }

   public WritableComparable getCurrentValue() throws IOException, InterruptedException {
      return this.row;
   }

   public float getProgress() throws IOException {
      return this.batchReader.getProgress();
   }
}
