package org.apache.parquet.example.data.simple;

import java.util.ArrayList;
import java.util.List;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.Type;

public class SimpleGroup extends Group {
   private final GroupType schema;
   private final List[] data;

   public SimpleGroup(GroupType schema) {
      this.schema = schema;
      this.data = new List[schema.getFields().size()];

      for(int i = 0; i < schema.getFieldCount(); ++i) {
         this.data[i] = new ArrayList();
      }

   }

   public String toString() {
      return this.toString("");
   }

   private StringBuilder appendToString(StringBuilder builder, String indent) {
      int i = 0;

      for(Type field : this.schema.getFields()) {
         String name = field.getName();
         List<Object> values = this.data[i];
         ++i;
         if (values != null && !values.isEmpty()) {
            for(Object value : values) {
               builder.append(indent).append(name);
               if (value == null) {
                  builder.append(": NULL\n");
               } else if (value instanceof Group) {
                  builder.append('\n');
                  ((SimpleGroup)value).appendToString(builder, indent + "  ");
               } else {
                  builder.append(": ").append(value).append('\n');
               }
            }
         }
      }

      return builder;
   }

   public String toString(String indent) {
      StringBuilder builder = new StringBuilder();
      this.appendToString(builder, indent);
      return builder.toString();
   }

   public Group addGroup(int fieldIndex) {
      SimpleGroup g = new SimpleGroup(this.schema.getType(fieldIndex).asGroupType());
      this.add(fieldIndex, (Group)g);
      return g;
   }

   public Object getObject(String field, int index) {
      return this.getObject(this.getType().getFieldIndex(field), index);
   }

   public Object getObject(int fieldIndex, int index) {
      Object wrapped = this.getValue(fieldIndex, index);
      if (wrapped instanceof BooleanValue) {
         return ((BooleanValue)wrapped).getBoolean();
      } else if (wrapped instanceof IntegerValue) {
         return ((IntegerValue)wrapped).getInteger();
      } else if (wrapped instanceof LongValue) {
         return ((LongValue)wrapped).getLong();
      } else if (wrapped instanceof Int96Value) {
         return ((Int96Value)wrapped).getInt96();
      } else if (wrapped instanceof FloatValue) {
         return ((FloatValue)wrapped).getFloat();
      } else if (wrapped instanceof DoubleValue) {
         return ((DoubleValue)wrapped).getDouble();
      } else {
         return wrapped instanceof BinaryValue ? ((BinaryValue)wrapped).getBinary() : wrapped;
      }
   }

   public Group getGroup(int fieldIndex, int index) {
      return (Group)this.getValue(fieldIndex, index);
   }

   private Object getValue(int fieldIndex, int index) {
      List<Object> list;
      try {
         list = this.data[fieldIndex];
      } catch (IndexOutOfBoundsException var6) {
         throw new RuntimeException("not found " + fieldIndex + "(" + this.schema.getFieldName(fieldIndex) + ") in group:\n" + this);
      }

      try {
         return list.get(index);
      } catch (IndexOutOfBoundsException var5) {
         throw new RuntimeException("not found " + fieldIndex + "(" + this.schema.getFieldName(fieldIndex) + ") element number " + index + " in group:\n" + this);
      }
   }

   private void add(int fieldIndex, Primitive value) {
      Type type = this.schema.getType(fieldIndex);
      List<Object> list = this.data[fieldIndex];
      if (!type.isRepetition(Type.Repetition.REPEATED) && !list.isEmpty()) {
         throw new IllegalStateException("field " + fieldIndex + " (" + type.getName() + ") can not have more than one value: " + list);
      } else {
         list.add(value);
      }
   }

   public int getFieldRepetitionCount(int fieldIndex) {
      List<Object> list = this.data[fieldIndex];
      return list == null ? 0 : list.size();
   }

   public String getValueToString(int fieldIndex, int index) {
      return String.valueOf(this.getValue(fieldIndex, index));
   }

   public String getString(int fieldIndex, int index) {
      return ((BinaryValue)this.getValue(fieldIndex, index)).getString();
   }

   public int getInteger(int fieldIndex, int index) {
      return ((IntegerValue)this.getValue(fieldIndex, index)).getInteger();
   }

   public long getLong(int fieldIndex, int index) {
      return ((LongValue)this.getValue(fieldIndex, index)).getLong();
   }

   public double getDouble(int fieldIndex, int index) {
      return ((DoubleValue)this.getValue(fieldIndex, index)).getDouble();
   }

   public float getFloat(int fieldIndex, int index) {
      return ((FloatValue)this.getValue(fieldIndex, index)).getFloat();
   }

   public boolean getBoolean(int fieldIndex, int index) {
      return ((BooleanValue)this.getValue(fieldIndex, index)).getBoolean();
   }

   public Binary getBinary(int fieldIndex, int index) {
      return ((BinaryValue)this.getValue(fieldIndex, index)).getBinary();
   }

   public NanoTime getTimeNanos(int fieldIndex, int index) {
      return NanoTime.fromInt96((Int96Value)this.getValue(fieldIndex, index));
   }

   public Binary getInt96(int fieldIndex, int index) {
      return ((Int96Value)this.getValue(fieldIndex, index)).getInt96();
   }

   public void add(int fieldIndex, int value) {
      this.add(fieldIndex, (Primitive)(new IntegerValue(value)));
   }

   public void add(int fieldIndex, long value) {
      this.add(fieldIndex, (Primitive)(new LongValue(value)));
   }

   public void add(int fieldIndex, String value) {
      this.add(fieldIndex, (Primitive)(new BinaryValue(Binary.fromString(value))));
   }

   public void add(int fieldIndex, NanoTime value) {
      this.add(fieldIndex, (Primitive)value.toInt96());
   }

   public void add(int fieldIndex, boolean value) {
      this.add(fieldIndex, (Primitive)(new BooleanValue(value)));
   }

   public void add(int fieldIndex, Binary value) {
      switch (this.getType().getType(fieldIndex).asPrimitiveType().getPrimitiveTypeName()) {
         case BINARY:
         case FIXED_LEN_BYTE_ARRAY:
            this.add(fieldIndex, (Primitive)(new BinaryValue(value)));
            break;
         case INT96:
            this.add(fieldIndex, (Primitive)(new Int96Value(value)));
            break;
         default:
            throw new UnsupportedOperationException(this.getType().asPrimitiveType().getName() + " not supported for Binary");
      }

   }

   public void add(int fieldIndex, float value) {
      this.add(fieldIndex, (Primitive)(new FloatValue(value)));
   }

   public void add(int fieldIndex, double value) {
      this.add(fieldIndex, (Primitive)(new DoubleValue(value)));
   }

   public void add(int fieldIndex, Group value) {
      this.data[fieldIndex].add(value);
   }

   public GroupType getType() {
      return this.schema;
   }

   public void writeValue(int field, int index, RecordConsumer recordConsumer) {
      ((Primitive)this.getValue(field, index)).writeValue(recordConsumer);
   }
}
