package org.apache.hadoop.hive.serde2.columnar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.serde2.SerDeStatsStruct;
import org.apache.hadoop.hive.serde2.StructObject;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyObjectBase;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

public abstract class ColumnarStructBase implements StructObject, SerDeStatsStruct {
   protected int[] prjColIDs = null;
   private FieldInfo[] fieldInfoList = null;
   private ArrayList cachedList;

   public ColumnarStructBase(ObjectInspector oi, List notSkippedColumnIDs) {
      List<? extends StructField> fieldRefs = ((StructObjectInspector)oi).getAllStructFieldRefs();
      int num = fieldRefs.size();
      this.fieldInfoList = new FieldInfo[num];

      for(int i = 0; i < num; ++i) {
         ObjectInspector foi = ((StructField)fieldRefs.get(i)).getFieldObjectInspector();
         this.fieldInfoList[i] = new FieldInfo(this.createLazyObjectBase(foi), !notSkippedColumnIDs.contains(i), foi);
      }

      int min = notSkippedColumnIDs.size() > num ? num : notSkippedColumnIDs.size();
      this.prjColIDs = new int[min];
      int i = 0;

      for(int index = 0; i < notSkippedColumnIDs.size(); ++i) {
         int readCol = (Integer)notSkippedColumnIDs.get(i);
         if (readCol < num) {
            this.prjColIDs[index] = readCol;
            ++index;
         }
      }

   }

   public Object getField(int fieldID) {
      return this.fieldInfoList[fieldID].uncheckedGetField();
   }

   protected abstract int getLength(ObjectInspector var1, ByteArrayRef var2, int var3, int var4);

   protected abstract LazyObjectBase createLazyObjectBase(ObjectInspector var1);

   public void init(BytesRefArrayWritable cols) {
      for(int i = 0; i < this.prjColIDs.length; ++i) {
         int fieldIndex = this.prjColIDs[i];
         if (fieldIndex < cols.size()) {
            this.fieldInfoList[fieldIndex].init(cols.unCheckedGet(fieldIndex));
         } else {
            this.fieldInfoList[fieldIndex].init((BytesRefWritable)null);
         }
      }

   }

   public ArrayList getFieldsAsList() {
      if (this.cachedList == null) {
         this.cachedList = new ArrayList();
      } else {
         this.cachedList.clear();
      }

      for(int i = 0; i < this.fieldInfoList.length; ++i) {
         this.cachedList.add(this.fieldInfoList[i].uncheckedGetField());
      }

      return this.cachedList;
   }

   public long getRawDataSerializedSize() {
      long serializedSize = 0L;

      for(int i = 0; i < this.fieldInfoList.length; ++i) {
         serializedSize += this.fieldInfoList[i].getSerializedSize();
      }

      return serializedSize;
   }

   class FieldInfo {
      LazyObjectBase field;
      ByteArrayRef cachedByteArrayRef;
      BytesRefWritable rawBytesField;
      boolean inited;
      boolean fieldSkipped;
      ObjectInspector objectInspector;

      public FieldInfo(LazyObjectBase lazyObject, boolean fieldSkipped, ObjectInspector oi) {
         this.field = lazyObject;
         this.cachedByteArrayRef = new ByteArrayRef();
         this.objectInspector = oi;
         if (fieldSkipped) {
            this.fieldSkipped = true;
            this.inited = true;
         } else {
            this.inited = false;
         }

      }

      public void init(BytesRefWritable col) {
         if (col != null) {
            this.rawBytesField = col;
            this.inited = false;
            this.fieldSkipped = false;
         } else {
            this.fieldSkipped = true;
         }

      }

      public long getSerializedSize() {
         return this.rawBytesField == null ? 0L : (long)this.rawBytesField.getLength();
      }

      protected Object uncheckedGetField() {
         if (this.fieldSkipped) {
            return null;
         } else if (!this.inited) {
            try {
               this.cachedByteArrayRef.setData(this.rawBytesField.getData());
            } catch (IOException e) {
               throw new RuntimeException(e);
            }

            this.inited = true;
            int byteLength = ColumnarStructBase.this.getLength(this.objectInspector, this.cachedByteArrayRef, this.rawBytesField.getStart(), this.rawBytesField.getLength());
            if (byteLength == -1) {
               return null;
            } else {
               this.field.init(this.cachedByteArrayRef, this.rawBytesField.getStart(), byteLength);
               return this.field.getObject();
            }
         } else {
            return ColumnarStructBase.this.getLength(this.objectInspector, this.cachedByteArrayRef, this.rawBytesField.getStart(), this.rawBytesField.getLength()) == -1 ? null : this.field.getObject();
         }
      }
   }
}
