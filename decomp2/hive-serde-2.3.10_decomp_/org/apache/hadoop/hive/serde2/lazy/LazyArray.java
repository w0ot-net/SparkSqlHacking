package org.apache.hadoop.hive.serde2.lazy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazyListObjectInspector;

public class LazyArray extends LazyNonPrimitive {
   boolean parsed = false;
   int arrayLength = 0;
   int[] startPosition;
   boolean[] elementInited;
   LazyObject[] arrayElements;
   ArrayList cachedList;

   protected LazyArray(LazyListObjectInspector oi) {
      super(oi);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      super.init(bytes, start, length);
      this.parsed = false;
      this.cachedList = null;
   }

   private void enlargeArrays() {
      if (this.startPosition == null) {
         int initialSize = 2;
         this.startPosition = new int[initialSize];
         this.arrayElements = new LazyObject[initialSize];
         this.elementInited = new boolean[initialSize];
      } else {
         this.startPosition = Arrays.copyOf(this.startPosition, this.startPosition.length * 2);
         this.arrayElements = (LazyObject[])Arrays.copyOf(this.arrayElements, this.arrayElements.length * 2);
         this.elementInited = Arrays.copyOf(this.elementInited, this.elementInited.length * 2);
      }

   }

   private void parse() {
      this.parsed = true;
      byte separator = ((LazyListObjectInspector)this.oi).getSeparator();
      boolean isEscaped = ((LazyListObjectInspector)this.oi).isEscaped();
      byte escapeChar = ((LazyListObjectInspector)this.oi).getEscapeChar();
      if (this.length == 0) {
         this.arrayLength = 0;
      } else {
         byte[] bytes = this.bytes.getData();
         this.arrayLength = 0;
         int arrayByteEnd = this.start + this.length;
         int elementByteBegin = this.start;
         int elementByteEnd = this.start;

         while(elementByteEnd <= arrayByteEnd) {
            if (elementByteEnd != arrayByteEnd && bytes[elementByteEnd] != separator) {
               if (isEscaped && bytes[elementByteEnd] == escapeChar && elementByteEnd + 1 < arrayByteEnd) {
                  elementByteEnd += 2;
               } else {
                  ++elementByteEnd;
               }
            } else {
               if (this.startPosition == null || this.arrayLength + 1 == this.startPosition.length) {
                  this.enlargeArrays();
               }

               this.startPosition[this.arrayLength] = elementByteBegin;
               ++this.arrayLength;
               elementByteBegin = elementByteEnd + 1;
               ++elementByteEnd;
            }
         }

         this.startPosition[this.arrayLength] = arrayByteEnd + 1;
         if (this.arrayLength > 0) {
            Arrays.fill(this.elementInited, 0, this.arrayLength, false);
         }

      }
   }

   public Object getListElementObject(int index) {
      if (!this.parsed) {
         this.parse();
      }

      return index >= 0 && index < this.arrayLength ? this.uncheckedGetElement(index) : null;
   }

   private Object uncheckedGetElement(int index) {
      if (this.elementInited[index]) {
         return this.arrayElements[index].getObject();
      } else {
         this.elementInited[index] = true;
         int elementStart = this.startPosition[index];
         int elementLength = this.startPosition[index + 1] - elementStart - 1;
         if (this.arrayElements[index] == null) {
            this.arrayElements[index] = LazyFactory.createLazyObject(((LazyListObjectInspector)this.oi).getListElementObjectInspector());
         }

         if (this.isNull(((LazyListObjectInspector)this.oi).getNullSequence(), this.bytes, elementStart, elementLength)) {
            this.arrayElements[index].setNull();
         } else {
            this.arrayElements[index].init(this.bytes, elementStart, elementLength);
         }

         return this.arrayElements[index].getObject();
      }
   }

   public int getListLength() {
      if (!this.parsed) {
         this.parse();
      }

      return this.arrayLength;
   }

   public List getList() {
      if (!this.parsed) {
         this.parse();
      }

      if (this.arrayLength == -1) {
         return null;
      } else if (this.cachedList != null) {
         return this.cachedList;
      } else {
         this.cachedList = new ArrayList(this.arrayLength);

         for(int index = 0; index < this.arrayLength; ++index) {
            this.cachedList.add(this.uncheckedGetElement(index));
         }

         return this.cachedList;
      }
   }
}
