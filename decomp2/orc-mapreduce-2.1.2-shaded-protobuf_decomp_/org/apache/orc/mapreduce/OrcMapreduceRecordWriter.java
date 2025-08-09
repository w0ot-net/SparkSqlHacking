package org.apache.orc.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.orc.OrcConf;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;
import org.apache.orc.TypeDescription.Category;
import org.apache.orc.mapred.OrcKey;
import org.apache.orc.mapred.OrcMapredRecordWriter;
import org.apache.orc.mapred.OrcStruct;
import org.apache.orc.mapred.OrcValue;

public class OrcMapreduceRecordWriter extends RecordWriter {
   private final Writer writer;
   private final VectorizedRowBatch batch;
   private final TypeDescription schema;
   private final boolean isTopStruct;
   private final List variableLengthColumns;
   private final int maxChildLength;

   public OrcMapreduceRecordWriter(Writer writer) {
      this(writer, 1024);
   }

   public OrcMapreduceRecordWriter(Writer writer, int rowBatchSize) {
      this(writer, rowBatchSize, (Integer)OrcConf.ROW_BATCH_CHILD_LIMIT.getDefaultValue());
   }

   public OrcMapreduceRecordWriter(Writer writer, int rowBatchSize, int maxChildLength) {
      this.variableLengthColumns = new ArrayList();
      this.writer = writer;
      this.schema = writer.getSchema();
      this.batch = this.schema.createRowBatch(rowBatchSize);
      this.isTopStruct = this.schema.getCategory() == Category.STRUCT;
      OrcMapredRecordWriter.addVariableLengthColumns(this.variableLengthColumns, this.batch);
      this.maxChildLength = maxChildLength;
   }

   public void write(NullWritable nullWritable, Writable v) throws IOException {
      if (this.batch.size == this.batch.getMaxSize() || OrcMapredRecordWriter.getMaxChildLength(this.variableLengthColumns) >= this.maxChildLength) {
         this.writer.addRowBatch(this.batch);
         this.batch.reset();
      }

      int row = this.batch.size++;
      if (v instanceof OrcKey) {
         v = (V)((OrcKey)v).key;
      } else if (v instanceof OrcValue) {
         v = (V)((OrcValue)v).value;
      }

      if (this.isTopStruct) {
         for(int f = 0; f < this.schema.getChildren().size(); ++f) {
            OrcMapredRecordWriter.setColumn((TypeDescription)this.schema.getChildren().get(f), this.batch.cols[f], row, ((OrcStruct)v).getFieldValue(f));
         }
      } else {
         OrcMapredRecordWriter.setColumn(this.schema, this.batch.cols[0], row, v);
      }

   }

   public void close(TaskAttemptContext taskAttemptContext) throws IOException {
      if (this.batch.size != 0) {
         this.writer.addRowBatch(this.batch);
      }

      this.writer.close();
   }
}
