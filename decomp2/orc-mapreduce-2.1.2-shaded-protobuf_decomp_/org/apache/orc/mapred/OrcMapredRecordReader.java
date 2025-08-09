package org.apache.orc.mapred;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ListColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.MapColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.StructColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.UnionColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.ShortWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.orc.Reader;
import org.apache.orc.TypeDescription;
import org.apache.orc.TypeDescription.Category;

public class OrcMapredRecordReader implements RecordReader {
   private final TypeDescription schema;
   private final org.apache.orc.RecordReader batchReader;
   private final VectorizedRowBatch batch;
   private int rowInBatch;

   public OrcMapredRecordReader(org.apache.orc.RecordReader reader, TypeDescription schema) throws IOException {
      this.batchReader = reader;
      this.batch = schema.createRowBatch();
      this.schema = schema;
      this.rowInBatch = 0;
   }

   protected OrcMapredRecordReader(Reader fileReader, Reader.Options options) throws IOException {
      this(fileReader, options, options.getRowBatchSize());
   }

   protected OrcMapredRecordReader(Reader fileReader, Reader.Options options, int rowBatchSize) throws IOException {
      this.batchReader = fileReader.rows(options);
      if (options.getSchema() == null) {
         this.schema = fileReader.getSchema();
      } else {
         this.schema = options.getSchema();
      }

      this.batch = this.schema.createRowBatch(rowBatchSize);
      this.rowInBatch = 0;
   }

   boolean ensureBatch() throws IOException {
      if (this.rowInBatch >= this.batch.size) {
         this.rowInBatch = 0;
         return this.batchReader.nextBatch(this.batch);
      } else {
         return true;
      }
   }

   public boolean next(NullWritable key, WritableComparable value) throws IOException {
      if (!this.ensureBatch()) {
         return false;
      } else {
         int rowIdx = this.batch.selectedInUse ? this.batch.selected[this.rowInBatch] : this.rowInBatch;
         if (this.schema.getCategory() == Category.STRUCT) {
            OrcStruct result = (OrcStruct)value;
            List<TypeDescription> children = this.schema.getChildren();
            int numberOfChildren = children.size();

            for(int i = 0; i < numberOfChildren; ++i) {
               result.setFieldValue(i, nextValue(this.batch.cols[i], rowIdx, (TypeDescription)children.get(i), result.getFieldValue(i)));
            }
         } else {
            nextValue(this.batch.cols[0], rowIdx, this.schema, value);
         }

         ++this.rowInBatch;
         return true;
      }
   }

   public NullWritable createKey() {
      return NullWritable.get();
   }

   public WritableComparable createValue() {
      return OrcStruct.createValue(this.schema);
   }

   public long getPos() throws IOException {
      return 0L;
   }

   public void close() throws IOException {
      this.batchReader.close();
   }

   public float getProgress() throws IOException {
      return 0.0F;
   }

   static BooleanWritable nextBoolean(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         BooleanWritable result;
         if (previous != null && previous.getClass() == BooleanWritable.class) {
            result = (BooleanWritable)previous;
         } else {
            result = new BooleanWritable();
         }

         result.set(((LongColumnVector)vector).vector[row] != 0L);
         return result;
      }
   }

   static ByteWritable nextByte(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         ByteWritable result;
         if (previous != null && previous.getClass() == ByteWritable.class) {
            result = (ByteWritable)previous;
         } else {
            result = new ByteWritable();
         }

         result.set((byte)((int)((LongColumnVector)vector).vector[row]));
         return result;
      }
   }

   static ShortWritable nextShort(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         ShortWritable result;
         if (previous != null && previous.getClass() == ShortWritable.class) {
            result = (ShortWritable)previous;
         } else {
            result = new ShortWritable();
         }

         result.set((short)((int)((LongColumnVector)vector).vector[row]));
         return result;
      }
   }

   static IntWritable nextInt(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         IntWritable result;
         if (previous != null && previous.getClass() == IntWritable.class) {
            result = (IntWritable)previous;
         } else {
            result = new IntWritable();
         }

         result.set((int)((LongColumnVector)vector).vector[row]);
         return result;
      }
   }

   static LongWritable nextLong(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         LongWritable result;
         if (previous != null && previous.getClass() == LongWritable.class) {
            result = (LongWritable)previous;
         } else {
            result = new LongWritable();
         }

         result.set(((LongColumnVector)vector).vector[row]);
         return result;
      }
   }

   static FloatWritable nextFloat(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         FloatWritable result;
         if (previous != null && previous.getClass() == FloatWritable.class) {
            result = (FloatWritable)previous;
         } else {
            result = new FloatWritable();
         }

         result.set((float)((DoubleColumnVector)vector).vector[row]);
         return result;
      }
   }

   static DoubleWritable nextDouble(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         DoubleWritable result;
         if (previous != null && previous.getClass() == DoubleWritable.class) {
            result = (DoubleWritable)previous;
         } else {
            result = new DoubleWritable();
         }

         result.set(((DoubleColumnVector)vector).vector[row]);
         return result;
      }
   }

   static Text nextString(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         Text result;
         if (previous != null && previous.getClass() == Text.class) {
            result = (Text)previous;
         } else {
            result = new Text();
         }

         BytesColumnVector bytes = (BytesColumnVector)vector;
         result.set(bytes.vector[row], bytes.start[row], bytes.length[row]);
         return result;
      }
   }

   static BytesWritable nextBinary(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         BytesWritable result;
         if (previous != null && previous.getClass() == BytesWritable.class) {
            result = (BytesWritable)previous;
         } else {
            result = new BytesWritable();
         }

         BytesColumnVector bytes = (BytesColumnVector)vector;
         result.set(bytes.vector[row], bytes.start[row], bytes.length[row]);
         return result;
      }
   }

   static HiveDecimalWritable nextDecimal(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         HiveDecimalWritable result;
         if (previous != null && previous.getClass() == HiveDecimalWritable.class) {
            result = (HiveDecimalWritable)previous;
         } else {
            result = new HiveDecimalWritable();
         }

         result.set(((DecimalColumnVector)vector).vector[row]);
         return result;
      }
   }

   static DateWritable nextDate(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         DateWritable result;
         if (previous != null && previous.getClass() == DateWritable.class) {
            result = (DateWritable)previous;
         } else {
            result = new DateWritable();
         }

         int date = (int)((LongColumnVector)vector).vector[row];
         result.set(date);
         return result;
      }
   }

   static OrcTimestamp nextTimestamp(ColumnVector vector, int row, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         OrcTimestamp result;
         if (previous != null && previous.getClass() == OrcTimestamp.class) {
            result = (OrcTimestamp)previous;
         } else {
            result = new OrcTimestamp();
         }

         TimestampColumnVector tcv = (TimestampColumnVector)vector;
         result.setTime(tcv.time[row]);
         result.setNanos(tcv.nanos[row]);
         return result;
      }
   }

   static OrcStruct nextStruct(ColumnVector vector, int row, TypeDescription schema, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         List<TypeDescription> childrenTypes = schema.getChildren();
         int numChildren = childrenTypes.size();
         OrcStruct result;
         if (isReusable(previous, schema)) {
            result = (OrcStruct)previous;
         } else {
            result = new OrcStruct(schema);
         }

         StructColumnVector struct = (StructColumnVector)vector;

         for(int f = 0; f < numChildren; ++f) {
            result.setFieldValue(f, nextValue(struct.fields[f], row, (TypeDescription)childrenTypes.get(f), result.getFieldValue(f)));
         }

         return result;
      }
   }

   private static boolean isReusable(Object previous, TypeDescription schema) {
      return previous != null && previous.getClass() == OrcStruct.class ? ((OrcStruct)previous).getSchema().equals(schema) : false;
   }

   static OrcUnion nextUnion(ColumnVector vector, int row, TypeDescription schema, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         List<TypeDescription> childrenTypes = schema.getChildren();
         OrcUnion result;
         if (previous != null && previous.getClass() == OrcUnion.class) {
            result = (OrcUnion)previous;
         } else {
            result = new OrcUnion(schema);
         }

         UnionColumnVector union = (UnionColumnVector)vector;
         byte tag = (byte)union.tags[row];
         result.set(tag, nextValue(union.fields[tag], row, (TypeDescription)childrenTypes.get(tag), result.getObject()));
         return result;
      }
   }

   static OrcList nextList(ColumnVector vector, int row, TypeDescription schema, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         List<TypeDescription> childrenTypes = schema.getChildren();
         TypeDescription valueType = (TypeDescription)childrenTypes.get(0);
         OrcList result;
         if (previous != null && previous.getClass() == ArrayList.class) {
            result = (OrcList)previous;
         } else {
            result = new OrcList(schema);
         }

         ListColumnVector list = (ListColumnVector)vector;
         int length = (int)list.lengths[row];
         int offset = (int)list.offsets[row];
         result.ensureCapacity(length);
         int oldLength = result.size();

         int idx;
         for(idx = 0; idx < length && idx < oldLength; ++idx) {
            result.set(idx, nextValue(list.child, offset + idx, valueType, result.get(idx)));
         }

         if (length < oldLength) {
            for(int i = oldLength - 1; i >= length; --i) {
               result.remove(i);
            }
         } else if (oldLength < length) {
            while(idx < length) {
               result.add(nextValue(list.child, offset + idx, valueType, (Object)null));
               ++idx;
            }
         }

         return result;
      }
   }

   static OrcMap nextMap(ColumnVector vector, int row, TypeDescription schema, Object previous) {
      if (vector.isRepeating) {
         row = 0;
      }

      if (!vector.noNulls && vector.isNull[row]) {
         return null;
      } else {
         MapColumnVector map = (MapColumnVector)vector;
         int length = (int)map.lengths[row];
         int offset = (int)map.offsets[row];
         List<TypeDescription> childrenTypes = schema.getChildren();
         TypeDescription keyType = (TypeDescription)childrenTypes.get(0);
         TypeDescription valueType = (TypeDescription)childrenTypes.get(1);
         OrcMap result;
         if (previous != null && previous.getClass() == OrcMap.class) {
            result = (OrcMap)previous;
            result.clear();
         } else {
            result = new OrcMap(schema);
         }

         for(int e = 0; e < length; ++e) {
            result.put(nextValue(map.keys, e + offset, keyType, (Object)null), nextValue(map.values, e + offset, valueType, (Object)null));
         }

         return result;
      }
   }

   public static WritableComparable nextValue(ColumnVector vector, int row, TypeDescription schema, Object previous) {
      switch (schema.getCategory()) {
         case BOOLEAN:
            return nextBoolean(vector, row, previous);
         case BYTE:
            return nextByte(vector, row, previous);
         case SHORT:
            return nextShort(vector, row, previous);
         case INT:
            return nextInt(vector, row, previous);
         case LONG:
            return nextLong(vector, row, previous);
         case FLOAT:
            return nextFloat(vector, row, previous);
         case DOUBLE:
            return nextDouble(vector, row, previous);
         case STRING:
         case CHAR:
         case VARCHAR:
            return nextString(vector, row, previous);
         case BINARY:
            return nextBinary(vector, row, previous);
         case DECIMAL:
            return nextDecimal(vector, row, previous);
         case DATE:
            return nextDate(vector, row, previous);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return nextTimestamp(vector, row, previous);
         case STRUCT:
            return nextStruct(vector, row, schema, previous);
         case UNION:
            return nextUnion(vector, row, schema, previous);
         case LIST:
            return nextList(vector, row, schema, previous);
         case MAP:
            return nextMap(vector, row, schema, previous);
         default:
            throw new IllegalArgumentException("Unknown type " + String.valueOf(schema));
      }
   }
}
