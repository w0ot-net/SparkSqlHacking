package org.apache.orc.mapred;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ListColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.MapColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.MultiValuedColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.StructColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.UnionColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.io.BinaryComparable;
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
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.orc.OrcConf;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;
import org.apache.orc.TypeDescription.Category;

public class OrcMapredRecordWriter implements RecordWriter {
   private static final int GROWTH_FACTOR = 3;
   private final Writer writer;
   private final VectorizedRowBatch batch;
   private final TypeDescription schema;
   private final boolean isTopStruct;
   private final List variableLengthColumns;
   private final int maxChildLength;
   private static final ThreadLocal SPACE_BUFFER = new ThreadLocal() {
      protected byte[] initialValue() {
         byte[] result = new byte[100];
         Arrays.fill(result, (byte)32);
         return result;
      }
   };

   public OrcMapredRecordWriter(Writer writer) {
      this(writer, 1024);
   }

   public OrcMapredRecordWriter(Writer writer, int rowBatchSize) {
      this(writer, rowBatchSize, (Integer)OrcConf.ROW_BATCH_CHILD_LIMIT.getDefaultValue());
   }

   public OrcMapredRecordWriter(Writer writer, int rowBatchSize, int maxChildLength) {
      this.variableLengthColumns = new ArrayList();
      this.writer = writer;
      this.schema = writer.getSchema();
      this.batch = this.schema.createRowBatch(rowBatchSize);
      addVariableLengthColumns(this.variableLengthColumns, this.batch);
      this.isTopStruct = this.schema.getCategory() == Category.STRUCT;
      this.maxChildLength = maxChildLength;
   }

   private static void addVariableLengthColumns(List result, ColumnVector vector) {
      switch (vector.type) {
         case LIST:
            ListColumnVector cv = (ListColumnVector)vector;
            result.add(cv);
            addVariableLengthColumns(result, cv.child);
            break;
         case MAP:
            MapColumnVector cv = (MapColumnVector)vector;
            result.add(cv);
            addVariableLengthColumns(result, cv.keys);
            addVariableLengthColumns(result, cv.values);
            break;
         case STRUCT:
            for(ColumnVector child : ((StructColumnVector)vector).fields) {
               addVariableLengthColumns(result, child);
            }
            break;
         case UNION:
            for(ColumnVector child : ((UnionColumnVector)vector).fields) {
               addVariableLengthColumns(result, child);
            }
      }

   }

   public static void addVariableLengthColumns(List result, VectorizedRowBatch batch) {
      for(ColumnVector cv : batch.cols) {
         addVariableLengthColumns(result, cv);
      }

   }

   static void setLongValue(ColumnVector vector, int row, long value) {
      ((LongColumnVector)vector).vector[row] = value;
   }

   static void setDoubleValue(ColumnVector vector, int row, double value) {
      ((DoubleColumnVector)vector).vector[row] = value;
   }

   static void setBinaryValue(ColumnVector vector, int row, BinaryComparable value) {
      ((BytesColumnVector)vector).setVal(row, value.getBytes(), 0, value.getLength());
   }

   static void setBinaryValue(ColumnVector vector, int row, BinaryComparable value, int maxLength) {
      ((BytesColumnVector)vector).setVal(row, value.getBytes(), 0, Math.min(maxLength, value.getLength()));
   }

   static void setCharValue(BytesColumnVector vector, int row, Text value, int length) {
      int actualLength = value.getLength();
      if (actualLength >= length) {
         setBinaryValue(vector, row, value, length);
      } else {
         byte[] spaces = (byte[])SPACE_BUFFER.get();
         if (length - actualLength > spaces.length) {
            spaces = new byte[length - actualLength];
            Arrays.fill(spaces, (byte)32);
            SPACE_BUFFER.set(spaces);
         }

         vector.setConcat(row, value.getBytes(), 0, actualLength, spaces, 0, length - actualLength);
      }

   }

   static void setStructValue(TypeDescription schema, StructColumnVector vector, int row, OrcStruct value) {
      List<TypeDescription> children = schema.getChildren();

      for(int c = 0; c < value.getNumFields(); ++c) {
         setColumn((TypeDescription)children.get(c), vector.fields[c], row, value.getFieldValue(c));
      }

   }

   static void setUnionValue(TypeDescription schema, UnionColumnVector vector, int row, OrcUnion value) {
      List<TypeDescription> children = schema.getChildren();
      int tag = value.getTag() & 255;
      vector.tags[row] = tag;
      setColumn((TypeDescription)children.get(tag), vector.fields[tag], row, value.getObject());
   }

   static void setListValue(TypeDescription schema, ListColumnVector vector, int row, OrcList value) {
      TypeDescription elemType = (TypeDescription)schema.getChildren().get(0);
      vector.offsets[row] = (long)vector.childCount;
      vector.lengths[row] = (long)value.size();
      vector.childCount = (int)((long)vector.childCount + vector.lengths[row]);
      if (vector.child.isNull.length < vector.childCount) {
         vector.child.ensureSize(vector.childCount * 3, vector.offsets[row] != 0L);
      }

      for(int e = 0; (long)e < vector.lengths[row]; ++e) {
         setColumn(elemType, vector.child, (int)vector.offsets[row] + e, (Writable)value.get(e));
      }

   }

   static void setMapValue(TypeDescription schema, MapColumnVector vector, int row, OrcMap value) {
      TypeDescription keyType = (TypeDescription)schema.getChildren().get(0);
      TypeDescription valueType = (TypeDescription)schema.getChildren().get(1);
      vector.offsets[row] = (long)vector.childCount;
      vector.lengths[row] = (long)value.size();
      vector.childCount = (int)((long)vector.childCount + vector.lengths[row]);
      if (vector.keys.isNull.length < vector.childCount) {
         vector.keys.ensureSize(vector.childCount * 3, vector.offsets[row] != 0L);
      }

      if (vector.values.isNull.length < vector.childCount) {
         vector.values.ensureSize(vector.childCount * 3, vector.offsets[row] != 0L);
      }

      int e = 0;

      for(Map.Entry entry : value.entrySet()) {
         setColumn(keyType, vector.keys, (int)vector.offsets[row] + e, (Writable)entry.getKey());
         setColumn(valueType, vector.values, (int)vector.offsets[row] + e, (Writable)entry.getValue());
         ++e;
      }

   }

   public static void setColumn(TypeDescription schema, ColumnVector vector, int row, Writable value) {
      if (value == null) {
         vector.noNulls = false;
         vector.isNull[row] = true;
      } else {
         switch (schema.getCategory()) {
            case BOOLEAN:
               setLongValue(vector, row, ((BooleanWritable)value).get() ? 1L : 0L);
               break;
            case BYTE:
               setLongValue(vector, row, (long)((ByteWritable)value).get());
               break;
            case SHORT:
               setLongValue(vector, row, (long)((ShortWritable)value).get());
               break;
            case INT:
               setLongValue(vector, row, (long)((IntWritable)value).get());
               break;
            case LONG:
               setLongValue(vector, row, ((LongWritable)value).get());
               break;
            case FLOAT:
               setDoubleValue(vector, row, (double)((FloatWritable)value).get());
               break;
            case DOUBLE:
               setDoubleValue(vector, row, ((DoubleWritable)value).get());
               break;
            case STRING:
               setBinaryValue(vector, row, (Text)value);
               break;
            case CHAR:
               setCharValue((BytesColumnVector)vector, row, (Text)value, schema.getMaxLength());
               break;
            case VARCHAR:
               setBinaryValue(vector, row, (Text)value, schema.getMaxLength());
               break;
            case BINARY:
               setBinaryValue(vector, row, (BytesWritable)value);
               break;
            case DATE:
               setLongValue(vector, row, (long)((DateWritable)value).getDays());
               break;
            case TIMESTAMP:
            case TIMESTAMP_INSTANT:
               ((TimestampColumnVector)vector).set(row, (OrcTimestamp)value);
               break;
            case DECIMAL:
               ((DecimalColumnVector)vector).set(row, (HiveDecimalWritable)value);
               break;
            case STRUCT:
               setStructValue(schema, (StructColumnVector)vector, row, (OrcStruct)value);
               break;
            case UNION:
               setUnionValue(schema, (UnionColumnVector)vector, row, (OrcUnion)value);
               break;
            case LIST:
               setListValue(schema, (ListColumnVector)vector, row, (OrcList)value);
               break;
            case MAP:
               setMapValue(schema, (MapColumnVector)vector, row, (OrcMap)value);
               break;
            default:
               throw new IllegalArgumentException("Unknown type " + String.valueOf(schema));
         }
      }

   }

   public static int getMaxChildLength(List columns) {
      int result = 0;

      for(MultiValuedColumnVector cv : columns) {
         result = Math.max(result, cv.childCount);
      }

      return result;
   }

   public void write(NullWritable nullWritable, Writable v) throws IOException {
      if (this.batch.size == this.batch.getMaxSize() || getMaxChildLength(this.variableLengthColumns) >= this.maxChildLength) {
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
            setColumn((TypeDescription)this.schema.getChildren().get(f), this.batch.cols[f], row, ((OrcStruct)v).getFieldValue(f));
         }
      } else {
         setColumn(this.schema, this.batch.cols[0], row, v);
      }

   }

   public void close(Reporter reporter) throws IOException {
      if (this.batch.size != 0) {
         this.writer.addRowBatch(this.batch);
         this.batch.reset();
      }

      this.writer.close();
   }
}
