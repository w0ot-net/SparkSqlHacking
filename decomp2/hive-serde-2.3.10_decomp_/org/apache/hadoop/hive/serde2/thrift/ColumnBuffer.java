package org.apache.hadoop.hive.serde2.thrift;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.primitives.Booleans;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;
import java.nio.ByteBuffer;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import org.apache.hive.service.rpc.thrift.TBinaryColumn;
import org.apache.hive.service.rpc.thrift.TBoolColumn;
import org.apache.hive.service.rpc.thrift.TByteColumn;
import org.apache.hive.service.rpc.thrift.TColumn;
import org.apache.hive.service.rpc.thrift.TDoubleColumn;
import org.apache.hive.service.rpc.thrift.TI16Column;
import org.apache.hive.service.rpc.thrift.TI32Column;
import org.apache.hive.service.rpc.thrift.TI64Column;
import org.apache.hive.service.rpc.thrift.TStringColumn;

public class ColumnBuffer extends AbstractList {
   private static final int DEFAULT_SIZE = 100;
   private final Type type;
   private BitSet nulls;
   private int size;
   private boolean[] boolVars;
   private byte[] byteVars;
   private short[] shortVars;
   private int[] intVars;
   private long[] longVars;
   private double[] doubleVars;
   private List stringVars;
   private List binaryVars;
   private static final byte[] MASKS = new byte[]{1, 2, 4, 8, 16, 32, 64, -128};
   private static final ByteBuffer EMPTY_BINARY = ByteBuffer.allocate(0);
   private static final String EMPTY_STRING = "";

   public ColumnBuffer(Type type, BitSet nulls, Object values) {
      this.type = type;
      this.nulls = nulls;
      if (type == Type.BOOLEAN_TYPE) {
         this.boolVars = (boolean[])values;
         this.size = this.boolVars.length;
      } else if (type == Type.TINYINT_TYPE) {
         this.byteVars = (byte[])values;
         this.size = this.byteVars.length;
      } else if (type == Type.SMALLINT_TYPE) {
         this.shortVars = (short[])values;
         this.size = this.shortVars.length;
      } else if (type == Type.INT_TYPE) {
         this.intVars = (int[])values;
         this.size = this.intVars.length;
      } else if (type == Type.BIGINT_TYPE) {
         this.longVars = (long[])values;
         this.size = this.longVars.length;
      } else if (type != Type.DOUBLE_TYPE && type != Type.FLOAT_TYPE) {
         if (type == Type.BINARY_TYPE) {
            this.binaryVars = (List)values;
            this.size = this.binaryVars.size();
         } else {
            if (type != Type.STRING_TYPE) {
               throw new IllegalStateException("invalid union object");
            }

            this.stringVars = (List)values;
            this.size = this.stringVars.size();
         }
      } else {
         this.doubleVars = (double[])values;
         this.size = this.doubleVars.length;
      }

   }

   public ColumnBuffer(Type type) {
      this.nulls = new BitSet();
      switch (type) {
         case BOOLEAN_TYPE:
            this.boolVars = new boolean[100];
            break;
         case TINYINT_TYPE:
            this.byteVars = new byte[100];
            break;
         case SMALLINT_TYPE:
            this.shortVars = new short[100];
            break;
         case INT_TYPE:
            this.intVars = new int[100];
            break;
         case BIGINT_TYPE:
            this.longVars = new long[100];
            break;
         case FLOAT_TYPE:
            type = Type.FLOAT_TYPE;
            this.doubleVars = new double[100];
            break;
         case DOUBLE_TYPE:
            type = Type.DOUBLE_TYPE;
            this.doubleVars = new double[100];
            break;
         case BINARY_TYPE:
            this.binaryVars = new ArrayList();
            break;
         default:
            type = Type.STRING_TYPE;
            this.stringVars = new ArrayList();
      }

      this.type = type;
   }

   public ColumnBuffer(TColumn colValues) {
      if (colValues.isSetBoolVal()) {
         this.type = Type.BOOLEAN_TYPE;
         this.nulls = toBitset(colValues.getBoolVal().getNulls());
         this.boolVars = Booleans.toArray(colValues.getBoolVal().getValues());
         this.size = this.boolVars.length;
      } else if (colValues.isSetByteVal()) {
         this.type = Type.TINYINT_TYPE;
         this.nulls = toBitset(colValues.getByteVal().getNulls());
         this.byteVars = Bytes.toArray(colValues.getByteVal().getValues());
         this.size = this.byteVars.length;
      } else if (colValues.isSetI16Val()) {
         this.type = Type.SMALLINT_TYPE;
         this.nulls = toBitset(colValues.getI16Val().getNulls());
         this.shortVars = Shorts.toArray(colValues.getI16Val().getValues());
         this.size = this.shortVars.length;
      } else if (colValues.isSetI32Val()) {
         this.type = Type.INT_TYPE;
         this.nulls = toBitset(colValues.getI32Val().getNulls());
         this.intVars = Ints.toArray(colValues.getI32Val().getValues());
         this.size = this.intVars.length;
      } else if (colValues.isSetI64Val()) {
         this.type = Type.BIGINT_TYPE;
         this.nulls = toBitset(colValues.getI64Val().getNulls());
         this.longVars = Longs.toArray(colValues.getI64Val().getValues());
         this.size = this.longVars.length;
      } else if (colValues.isSetDoubleVal()) {
         this.type = Type.DOUBLE_TYPE;
         this.nulls = toBitset(colValues.getDoubleVal().getNulls());
         this.doubleVars = Doubles.toArray(colValues.getDoubleVal().getValues());
         this.size = this.doubleVars.length;
      } else if (colValues.isSetBinaryVal()) {
         this.type = Type.BINARY_TYPE;
         this.nulls = toBitset(colValues.getBinaryVal().getNulls());
         this.binaryVars = colValues.getBinaryVal().getValues();
         this.size = this.binaryVars.size();
      } else {
         if (!colValues.isSetStringVal()) {
            throw new IllegalStateException("invalid union object");
         }

         this.type = Type.STRING_TYPE;
         this.nulls = toBitset(colValues.getStringVal().getNulls());
         this.stringVars = colValues.getStringVal().getValues();
         this.size = this.stringVars.size();
      }

   }

   public ColumnBuffer extractSubset(int end) {
      BitSet subNulls = this.nulls.get(0, end);
      if (this.type == Type.BOOLEAN_TYPE) {
         ColumnBuffer subset = new ColumnBuffer(this.type, subNulls, Arrays.copyOfRange(this.boolVars, 0, end));
         this.boolVars = Arrays.copyOfRange(this.boolVars, end, this.size);
         this.nulls = this.nulls.get(end, this.size);
         this.size = this.boolVars.length;
         return subset;
      } else if (this.type == Type.TINYINT_TYPE) {
         ColumnBuffer subset = new ColumnBuffer(this.type, subNulls, Arrays.copyOfRange(this.byteVars, 0, end));
         this.byteVars = Arrays.copyOfRange(this.byteVars, end, this.size);
         this.nulls = this.nulls.get(end, this.size);
         this.size = this.byteVars.length;
         return subset;
      } else if (this.type == Type.SMALLINT_TYPE) {
         ColumnBuffer subset = new ColumnBuffer(this.type, subNulls, Arrays.copyOfRange(this.shortVars, 0, end));
         this.shortVars = Arrays.copyOfRange(this.shortVars, end, this.size);
         this.nulls = this.nulls.get(end, this.size);
         this.size = this.shortVars.length;
         return subset;
      } else if (this.type == Type.INT_TYPE) {
         ColumnBuffer subset = new ColumnBuffer(this.type, subNulls, Arrays.copyOfRange(this.intVars, 0, end));
         this.intVars = Arrays.copyOfRange(this.intVars, end, this.size);
         this.nulls = this.nulls.get(end, this.size);
         this.size = this.intVars.length;
         return subset;
      } else if (this.type == Type.BIGINT_TYPE) {
         ColumnBuffer subset = new ColumnBuffer(this.type, subNulls, Arrays.copyOfRange(this.longVars, 0, end));
         this.longVars = Arrays.copyOfRange(this.longVars, end, this.size);
         this.nulls = this.nulls.get(end, this.size);
         this.size = this.longVars.length;
         return subset;
      } else if (this.type != Type.DOUBLE_TYPE && this.type != Type.FLOAT_TYPE) {
         if (this.type == Type.BINARY_TYPE) {
            ColumnBuffer subset = new ColumnBuffer(this.type, subNulls, this.binaryVars.subList(0, end));
            this.binaryVars = this.binaryVars.subList(end, this.binaryVars.size());
            this.nulls = this.nulls.get(end, this.size);
            this.size = this.binaryVars.size();
            return subset;
         } else if (this.type == Type.STRING_TYPE) {
            ColumnBuffer subset = new ColumnBuffer(this.type, subNulls, this.stringVars.subList(0, end));
            this.stringVars = this.stringVars.subList(end, this.stringVars.size());
            this.nulls = this.nulls.get(end, this.size);
            this.size = this.stringVars.size();
            return subset;
         } else {
            throw new IllegalStateException("invalid union object");
         }
      } else {
         ColumnBuffer subset = new ColumnBuffer(this.type, subNulls, Arrays.copyOfRange(this.doubleVars, 0, end));
         this.doubleVars = Arrays.copyOfRange(this.doubleVars, end, this.size);
         this.nulls = this.nulls.get(end, this.size);
         this.size = this.doubleVars.length;
         return subset;
      }
   }

   @VisibleForTesting
   BitSet getNulls() {
      return this.nulls;
   }

   private static BitSet toBitset(byte[] nulls) {
      BitSet bitset = new BitSet();
      int bits = nulls.length * 8;

      for(int i = 0; i < bits; ++i) {
         bitset.set(i, (nulls[i / 8] & MASKS[i % 8]) != 0);
      }

      return bitset;
   }

   private static byte[] toBinary(BitSet bitset) {
      byte[] nulls = new byte[1 + bitset.length() / 8];

      for(int i = 0; i < bitset.length(); ++i) {
         nulls[i / 8] |= bitset.get(i) ? MASKS[i % 8] : 0;
      }

      return nulls;
   }

   public Type getType() {
      return this.type;
   }

   public Object get(int index) {
      if (this.nulls.get(index)) {
         return null;
      } else {
         switch (this.type) {
            case BOOLEAN_TYPE:
               return this.boolVars[index];
            case TINYINT_TYPE:
               return this.byteVars[index];
            case SMALLINT_TYPE:
               return this.shortVars[index];
            case INT_TYPE:
               return this.intVars[index];
            case BIGINT_TYPE:
               return this.longVars[index];
            case FLOAT_TYPE:
            case DOUBLE_TYPE:
               return this.doubleVars[index];
            case BINARY_TYPE:
               return ((ByteBuffer)this.binaryVars.get(index)).array();
            case STRING_TYPE:
               return this.stringVars.get(index);
            default:
               return null;
         }
      }
   }

   public int size() {
      return this.size;
   }

   public TColumn toTColumn() {
      TColumn value = new TColumn();
      ByteBuffer nullMasks = ByteBuffer.wrap(toBinary(this.nulls));
      switch (this.type) {
         case BOOLEAN_TYPE:
            value.setBoolVal(new TBoolColumn(Booleans.asList(Arrays.copyOfRange(this.boolVars, 0, this.size)), nullMasks));
            break;
         case TINYINT_TYPE:
            value.setByteVal(new TByteColumn(Bytes.asList(Arrays.copyOfRange(this.byteVars, 0, this.size)), nullMasks));
            break;
         case SMALLINT_TYPE:
            value.setI16Val(new TI16Column(Shorts.asList(Arrays.copyOfRange(this.shortVars, 0, this.size)), nullMasks));
            break;
         case INT_TYPE:
            value.setI32Val(new TI32Column(Ints.asList(Arrays.copyOfRange(this.intVars, 0, this.size)), nullMasks));
            break;
         case BIGINT_TYPE:
            value.setI64Val(new TI64Column(Longs.asList(Arrays.copyOfRange(this.longVars, 0, this.size)), nullMasks));
            break;
         case FLOAT_TYPE:
         case DOUBLE_TYPE:
            value.setDoubleVal(new TDoubleColumn(Doubles.asList(Arrays.copyOfRange(this.doubleVars, 0, this.size)), nullMasks));
            break;
         case BINARY_TYPE:
            value.setBinaryVal(new TBinaryColumn(this.binaryVars, nullMasks));
            break;
         case STRING_TYPE:
            value.setStringVal(new TStringColumn(this.stringVars, nullMasks));
      }

      return value;
   }

   public void addValue(Object field) {
      this.addValue(this.type, field);
   }

   public void addValue(Type type, Object field) {
      switch (type) {
         case BOOLEAN_TYPE:
            this.nulls.set(this.size, field == null);
            this.boolVars()[this.size] = field == null ? true : (Boolean)field;
            break;
         case TINYINT_TYPE:
            this.nulls.set(this.size, field == null);
            this.byteVars()[this.size] = field == null ? 0 : (Byte)field;
            break;
         case SMALLINT_TYPE:
            this.nulls.set(this.size, field == null);
            this.shortVars()[this.size] = field == null ? 0 : (Short)field;
            break;
         case INT_TYPE:
            this.nulls.set(this.size, field == null);
            this.intVars()[this.size] = field == null ? 0 : (Integer)field;
            break;
         case BIGINT_TYPE:
            this.nulls.set(this.size, field == null);
            this.longVars()[this.size] = field == null ? 0L : (Long)field;
            break;
         case FLOAT_TYPE:
            this.nulls.set(this.size, field == null);
            this.doubleVars()[this.size] = field == null ? (double)0.0F : new Double(field.toString());
            break;
         case DOUBLE_TYPE:
            this.nulls.set(this.size, field == null);
            this.doubleVars()[this.size] = field == null ? (double)0.0F : (Double)field;
            break;
         case BINARY_TYPE:
            this.nulls.set(this.binaryVars.size(), field == null);
            this.binaryVars.add(field == null ? EMPTY_BINARY : ByteBuffer.wrap((byte[])field));
            break;
         default:
            this.nulls.set(this.stringVars.size(), field == null);
            this.stringVars.add(field == null ? "" : String.valueOf(field));
      }

      ++this.size;
   }

   private boolean[] boolVars() {
      if (this.boolVars.length == this.size) {
         boolean[] newVars = new boolean[this.size << 1];
         System.arraycopy(this.boolVars, 0, newVars, 0, this.size);
         return this.boolVars = newVars;
      } else {
         return this.boolVars;
      }
   }

   private byte[] byteVars() {
      if (this.byteVars.length == this.size) {
         byte[] newVars = new byte[this.size << 1];
         System.arraycopy(this.byteVars, 0, newVars, 0, this.size);
         return this.byteVars = newVars;
      } else {
         return this.byteVars;
      }
   }

   private short[] shortVars() {
      if (this.shortVars.length == this.size) {
         short[] newVars = new short[this.size << 1];
         System.arraycopy(this.shortVars, 0, newVars, 0, this.size);
         return this.shortVars = newVars;
      } else {
         return this.shortVars;
      }
   }

   private int[] intVars() {
      if (this.intVars.length == this.size) {
         int[] newVars = new int[this.size << 1];
         System.arraycopy(this.intVars, 0, newVars, 0, this.size);
         return this.intVars = newVars;
      } else {
         return this.intVars;
      }
   }

   private long[] longVars() {
      if (this.longVars.length == this.size) {
         long[] newVars = new long[this.size << 1];
         System.arraycopy(this.longVars, 0, newVars, 0, this.size);
         return this.longVars = newVars;
      } else {
         return this.longVars;
      }
   }

   private double[] doubleVars() {
      if (this.doubleVars.length == this.size) {
         double[] newVars = new double[this.size << 1];
         System.arraycopy(this.doubleVars, 0, newVars, 0, this.size);
         return this.doubleVars = newVars;
      } else {
         return this.doubleVars;
      }
   }
}
