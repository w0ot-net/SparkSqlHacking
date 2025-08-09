package org.apache.hadoop.hive.serde2.lazybinary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazybinary.objectinspector.LazyBinaryListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public class LazyBinaryArray extends LazyBinaryNonPrimitive {
   boolean parsed = false;
   int arraySize = 0;
   int[] elementStart;
   int[] elementLength;
   boolean[] elementInited;
   boolean[] elementIsNull;
   LazyBinaryObject[] arrayElements;
   LazyBinaryUtils.VInt vInt = new LazyBinaryUtils.VInt();
   LazyBinaryUtils.RecordInfo recordInfo = new LazyBinaryUtils.RecordInfo();
   ArrayList cachedList;

   protected LazyBinaryArray(LazyBinaryListObjectInspector oi) {
      super(oi);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      super.init(bytes, start, length);
      this.parsed = false;
   }

   private void adjustArraySize(int newSize) {
      if (this.elementStart == null || this.elementStart.length < newSize) {
         this.elementStart = new int[newSize];
         this.elementLength = new int[newSize];
         this.elementInited = new boolean[newSize];
         this.elementIsNull = new boolean[newSize];
         this.arrayElements = new LazyBinaryObject[newSize];
      }

   }

   private void parse() {
      byte[] bytes = this.bytes.getData();
      LazyBinaryUtils.readVInt(bytes, this.start, this.vInt);
      this.arraySize = this.vInt.value;
      if (0 == this.arraySize) {
         this.parsed = true;
      } else {
         this.adjustArraySize(this.arraySize);
         int arryByteStart = this.start + this.vInt.length;
         int nullByteCur = arryByteStart;
         int nullByteEnd = arryByteStart + (this.arraySize + 7) / 8;
         int lastElementByteEnd = nullByteEnd;
         ObjectInspector listEleObjectInspector = ((ListObjectInspector)this.oi).getListElementObjectInspector();

         for(int i = 0; i < this.arraySize; ++i) {
            this.elementIsNull[i] = true;
            if ((bytes[nullByteCur] & 1 << i % 8) != 0) {
               this.elementIsNull[i] = false;
               LazyBinaryUtils.checkObjectByteInfo(listEleObjectInspector, bytes, lastElementByteEnd, this.recordInfo, this.vInt);
               this.elementStart[i] = lastElementByteEnd + this.recordInfo.elementOffset;
               this.elementLength[i] = this.recordInfo.elementSize;
               lastElementByteEnd = this.elementStart[i] + this.elementLength[i];
            }

            if (7 == i % 8) {
               ++nullByteCur;
            }
         }

         Arrays.fill(this.elementInited, 0, this.arraySize, false);
         this.parsed = true;
      }
   }

   public Object getListElementObject(int index) {
      if (!this.parsed) {
         this.parse();
      }

      return index >= 0 && index < this.arraySize ? this.uncheckedGetElement(index) : null;
   }

   private Object uncheckedGetElement(int index) {
      if (this.elementIsNull[index]) {
         return null;
      } else {
         if (!this.elementInited[index]) {
            this.elementInited[index] = true;
            if (this.arrayElements[index] == null) {
               this.arrayElements[index] = LazyBinaryFactory.createLazyBinaryObject(((LazyBinaryListObjectInspector)this.oi).getListElementObjectInspector());
            }

            this.arrayElements[index].init(this.bytes, this.elementStart[index], this.elementLength[index]);
         }

         return this.arrayElements[index].getObject();
      }
   }

   public int getListLength() {
      if (!this.parsed) {
         this.parse();
      }

      return this.arraySize;
   }

   public List getList() {
      if (!this.parsed) {
         this.parse();
      }

      if (this.cachedList == null) {
         this.cachedList = new ArrayList(this.arraySize);
      } else {
         this.cachedList.clear();
      }

      for(int index = 0; index < this.arraySize; ++index) {
         this.cachedList.add(this.uncheckedGetElement(index));
      }

      return this.cachedList;
   }
}
