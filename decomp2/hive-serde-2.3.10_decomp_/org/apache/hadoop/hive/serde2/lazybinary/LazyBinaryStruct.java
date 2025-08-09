package org.apache.hadoop.hive.serde2.lazybinary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.hadoop.hive.serde2.SerDeStatsStruct;
import org.apache.hadoop.hive.serde2.StructObject;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazybinary.objectinspector.LazyBinaryStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.io.BinaryComparable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyBinaryStruct extends LazyBinaryNonPrimitive implements StructObject, SerDeStatsStruct {
   private static final Logger LOG = LoggerFactory.getLogger(LazyBinaryStruct.class.getName());
   boolean parsed;
   long serializedSize;
   LazyBinaryObject[] fields;
   boolean[] fieldInited;
   boolean[] fieldIsNull;
   int[] fieldStart;
   int[] fieldLength;
   final LazyBinaryUtils.VInt vInt = new LazyBinaryUtils.VInt();
   final LazyBinaryUtils.RecordInfo recordInfo = new LazyBinaryUtils.RecordInfo();
   boolean missingFieldWarned = false;
   boolean extraFieldWarned = false;
   ArrayList cachedList;

   protected LazyBinaryStruct(LazyBinaryStructObjectInspector oi) {
      super(oi);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      super.init(bytes, start, length);
      this.parsed = false;
      this.serializedSize = (long)length;
   }

   private void parse() {
      List<? extends StructField> fieldRefs = ((StructObjectInspector)this.oi).getAllStructFieldRefs();
      if (this.fields == null) {
         this.fields = new LazyBinaryObject[fieldRefs.size()];

         for(int i = 0; i < this.fields.length; ++i) {
            ObjectInspector insp = ((StructField)fieldRefs.get(i)).getFieldObjectInspector();
            this.fields[i] = insp == null ? null : LazyBinaryFactory.createLazyBinaryObject(insp);
         }

         this.fieldInited = new boolean[this.fields.length];
         this.fieldIsNull = new boolean[this.fields.length];
         this.fieldStart = new int[this.fields.length];
         this.fieldLength = new int[this.fields.length];
      }

      int fieldId = 0;
      int structByteEnd = this.start + this.length;
      byte[] bytes = this.bytes.getData();
      byte nullByte = bytes[this.start];
      int lastFieldByteEnd = this.start + 1;

      for(int i = 0; i < this.fields.length; ++i) {
         this.fieldIsNull[i] = true;
         if ((nullByte & 1 << i % 8) != 0) {
            this.fieldIsNull[i] = false;
            LazyBinaryUtils.checkObjectByteInfo(((StructField)fieldRefs.get(i)).getFieldObjectInspector(), bytes, lastFieldByteEnd, this.recordInfo, this.vInt);
            this.fieldStart[i] = lastFieldByteEnd + this.recordInfo.elementOffset;
            this.fieldLength[i] = this.recordInfo.elementSize;
            lastFieldByteEnd = this.fieldStart[i] + this.fieldLength[i];
         }

         if (lastFieldByteEnd <= structByteEnd) {
            ++fieldId;
         }

         if (7 == i % 8) {
            if (lastFieldByteEnd < structByteEnd) {
               nullByte = bytes[lastFieldByteEnd];
               ++lastFieldByteEnd;
            } else {
               nullByte = 0;
               ++lastFieldByteEnd;
            }
         }
      }

      if (!this.extraFieldWarned && lastFieldByteEnd < structByteEnd) {
         this.extraFieldWarned = true;
         LOG.warn("Extra bytes detected at the end of the row! Last field end " + lastFieldByteEnd + " and serialize buffer end " + structByteEnd + ". Ignoring similar problems.");
      }

      if (!this.missingFieldWarned && lastFieldByteEnd > structByteEnd) {
         this.missingFieldWarned = true;
         LOG.info("Missing fields! Expected " + this.fields.length + " fields but only got " + fieldId + "! Last field end " + lastFieldByteEnd + " and serialize buffer end " + structByteEnd + ". Ignoring similar problems.");
      }

      Arrays.fill(this.fieldInited, false);
      this.parsed = true;
   }

   public Object getField(int fieldID) {
      if (!this.parsed) {
         this.parse();
      }

      return this.uncheckedGetField(fieldID);
   }

   private Object uncheckedGetField(int fieldID) {
      if (this.fieldIsNull[fieldID]) {
         return null;
      } else {
         if (!this.fieldInited[fieldID]) {
            this.fieldInited[fieldID] = true;
            this.fields[fieldID].init(this.bytes, this.fieldStart[fieldID], this.fieldLength[fieldID]);
         }

         return this.fields[fieldID].getObject();
      }
   }

   public ArrayList getFieldsAsList() {
      if (!this.parsed) {
         this.parse();
      }

      if (this.cachedList == null) {
         this.cachedList = new ArrayList(this.fields.length);

         for(int i = 0; i < this.fields.length; ++i) {
            this.cachedList.add(this.uncheckedGetField(i));
         }
      } else {
         assert this.fields.length == this.cachedList.size();

         for(int i = 0; i < this.fields.length; ++i) {
            this.cachedList.set(i, this.uncheckedGetField(i));
         }
      }

      return this.cachedList;
   }

   public Object getObject() {
      return this;
   }

   public long getRawDataSerializedSize() {
      return this.serializedSize;
   }

   public static final class SingleFieldGetter {
      private final LazyBinaryUtils.VInt vInt = new LazyBinaryUtils.VInt();
      private final LazyBinaryStructObjectInspector soi;
      private final int fieldIndex;
      private final LazyBinaryUtils.RecordInfo recordInfo = new LazyBinaryUtils.RecordInfo();
      private byte[] fieldBytes;
      private int fieldStart;
      private int fieldLength;

      public SingleFieldGetter(LazyBinaryStructObjectInspector soi, int fieldIndex) {
         this.soi = soi;
         this.fieldIndex = fieldIndex;
      }

      public void init(BinaryComparable src) {
         List<? extends StructField> fieldRefs = this.soi.getAllStructFieldRefs();
         this.fieldBytes = src.getBytes();
         int length = src.getLength();
         byte nullByte = this.fieldBytes[0];
         int lastFieldByteEnd = 1;
         int fieldStart = -1;
         int fieldLength = -1;

         for(int i = 0; i <= this.fieldIndex; ++i) {
            if ((nullByte & 1 << i % 8) != 0) {
               LazyBinaryUtils.checkObjectByteInfo(((StructField)fieldRefs.get(i)).getFieldObjectInspector(), this.fieldBytes, lastFieldByteEnd, this.recordInfo, this.vInt);
               fieldStart = lastFieldByteEnd + this.recordInfo.elementOffset;
               fieldLength = this.recordInfo.elementSize;
               lastFieldByteEnd = fieldStart + fieldLength;
            } else {
               fieldLength = -1;
               fieldStart = -1;
            }

            if (7 == i % 8) {
               nullByte = lastFieldByteEnd < length ? this.fieldBytes[lastFieldByteEnd] : 0;
               ++lastFieldByteEnd;
            }
         }

      }

      public short getShort() {
         assert 2 == this.fieldLength;

         return LazyBinaryUtils.byteArrayToShort(this.fieldBytes, this.fieldStart);
      }
   }
}
