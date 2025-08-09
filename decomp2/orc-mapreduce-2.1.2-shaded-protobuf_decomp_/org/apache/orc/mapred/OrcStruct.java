package org.apache.orc.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.ShortWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.orc.TypeDescription;

public final class OrcStruct implements WritableComparable {
   private WritableComparable[] fields;
   private final TypeDescription schema;

   public OrcStruct(TypeDescription schema) {
      this.schema = schema;
      this.fields = new WritableComparable[schema.getChildren().size()];
   }

   public WritableComparable getFieldValue(int fieldIndex) {
      return this.fields[fieldIndex];
   }

   public void setFieldValue(int fieldIndex, WritableComparable value) {
      this.fields[fieldIndex] = value;
   }

   public int getNumFields() {
      return this.fields.length;
   }

   public void write(DataOutput output) throws IOException {
      for(WritableComparable field : this.fields) {
         output.writeBoolean(field != null);
         if (field != null) {
            field.write(output);
         }
      }

   }

   public void readFields(DataInput input) throws IOException {
      for(int f = 0; f < this.fields.length; ++f) {
         if (input.readBoolean()) {
            if (this.fields[f] == null) {
               this.fields[f] = createValue((TypeDescription)this.schema.getChildren().get(f));
            }

            this.fields[f].readFields(input);
         } else {
            this.fields[f] = null;
         }
      }

   }

   public TypeDescription getSchema() {
      return this.schema;
   }

   public void setAllFields(WritableComparable... values) {
      if (this.fields.length != values.length) {
         throw new IllegalArgumentException("Wrong number (" + values.length + ") of fields for " + String.valueOf(this.schema));
      } else {
         for(int col = 0; col < this.fields.length && col < values.length; ++col) {
            this.fields[col] = values[col];
         }

      }
   }

   public void setFieldValue(String fieldName, WritableComparable value) {
      int fieldIdx = this.schema.getFieldNames().indexOf(fieldName);
      if (fieldIdx == -1) {
         throw new IllegalArgumentException("Field " + fieldName + " not found in " + String.valueOf(this.schema));
      } else {
         this.fields[fieldIdx] = value;
      }
   }

   public WritableComparable getFieldValue(String fieldName) {
      int fieldIdx = this.schema.getFieldNames().indexOf(fieldName);
      if (fieldIdx == -1) {
         throw new IllegalArgumentException("Field " + fieldName + " not found in " + String.valueOf(this.schema));
      } else {
         return this.fields[fieldIdx];
      }
   }

   public boolean equals(Object other) {
      if (other != null && other.getClass() == OrcStruct.class) {
         OrcStruct oth = (OrcStruct)other;
         if (this.fields.length != oth.fields.length) {
            return false;
         } else {
            for(int i = 0; i < this.fields.length; ++i) {
               if (this.fields[i] == null) {
                  if (oth.fields[i] != null) {
                     return false;
                  }
               } else if (!this.fields[i].equals(oth.fields[i])) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.fields);
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append("{");

      for(int i = 0; i < this.fields.length; ++i) {
         if (i != 0) {
            buffer.append(", ");
         }

         buffer.append(this.fields[i]);
      }

      buffer.append("}");
      return buffer.toString();
   }

   public static WritableComparable createValue(TypeDescription type) {
      switch (type.getCategory()) {
         case BOOLEAN:
            return new BooleanWritable();
         case BYTE:
            return new ByteWritable();
         case SHORT:
            return new ShortWritable();
         case INT:
            return new IntWritable();
         case LONG:
            return new LongWritable();
         case FLOAT:
            return new FloatWritable();
         case DOUBLE:
            return new DoubleWritable();
         case BINARY:
            return new BytesWritable();
         case CHAR:
         case VARCHAR:
         case STRING:
            return new Text();
         case DATE:
            return new DateWritable();
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return new OrcTimestamp();
         case DECIMAL:
            return new HiveDecimalWritable();
         case STRUCT:
            OrcStruct result = new OrcStruct(type);
            int c = 0;

            for(TypeDescription child : type.getChildren()) {
               result.setFieldValue(c++, createValue(child));
            }

            return result;
         case UNION:
            return new OrcUnion(type);
         case LIST:
            return new OrcList(type);
         case MAP:
            return new OrcMap(type);
         default:
            throw new IllegalArgumentException("Unknown type " + String.valueOf(type));
      }
   }

   public int compareTo(OrcStruct other) {
      if (other == null) {
         return -1;
      } else {
         int result = this.schema.compareTo(other.schema);
         if (result != 0) {
            return result;
         } else {
            for(int c = 0; c < this.fields.length && c < other.fields.length; ++c) {
               if (this.fields[c] == null) {
                  if (other.fields[c] != null) {
                     return 1;
                  }
               } else {
                  if (other.fields[c] == null) {
                     return -1;
                  }

                  int val = this.fields[c].compareTo(other.fields[c]);
                  if (val != 0) {
                     return val;
                  }
               }
            }

            return this.fields.length - other.fields.length;
         }
      }
   }
}
