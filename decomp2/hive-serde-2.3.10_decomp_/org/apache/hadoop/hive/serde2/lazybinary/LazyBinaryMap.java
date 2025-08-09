package org.apache.hadoop.hive.serde2.lazybinary;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazybinary.objectinspector.LazyBinaryMapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyBinaryMap extends LazyBinaryNonPrimitive {
   private static final Logger LOG = LoggerFactory.getLogger(LazyBinaryMap.class.getName());
   boolean parsed;
   int mapSize = 0;
   int[] keyStart;
   int[] keyLength;
   int[] valueStart;
   int[] valueLength;
   boolean[] keyInited;
   boolean[] valueInited;
   boolean[] keyIsNull;
   boolean[] valueIsNull;
   LazyBinaryPrimitive[] keyObjects;
   LazyBinaryObject[] valueObjects;
   boolean nullMapKey = false;
   LazyBinaryUtils.VInt vInt = new LazyBinaryUtils.VInt();
   LazyBinaryUtils.RecordInfo recordInfo = new LazyBinaryUtils.RecordInfo();
   LinkedHashMap cachedMap;

   protected LazyBinaryMap(LazyBinaryMapObjectInspector oi) {
      super(oi);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      super.init(bytes, start, length);
      this.parsed = false;
   }

   protected void adjustArraySize(int newSize) {
      if (this.keyStart == null || this.keyStart.length < newSize) {
         this.keyStart = new int[newSize];
         this.keyLength = new int[newSize];
         this.valueStart = new int[newSize];
         this.valueLength = new int[newSize];
         this.keyInited = new boolean[newSize];
         this.keyIsNull = new boolean[newSize];
         this.valueInited = new boolean[newSize];
         this.valueIsNull = new boolean[newSize];
         this.keyObjects = new LazyBinaryPrimitive[newSize];
         this.valueObjects = new LazyBinaryObject[newSize];
      }

   }

   private void parse() {
      byte[] bytes = this.bytes.getData();
      LazyBinaryUtils.readVInt(bytes, this.start, this.vInt);
      this.mapSize = this.vInt.value;
      if (0 == this.mapSize) {
         this.parsed = true;
      } else {
         this.adjustArraySize(this.mapSize);
         int mapByteStart = this.start + this.vInt.length;
         int nullByteCur = mapByteStart;
         int nullByteEnd = mapByteStart + (this.mapSize * 2 + 7) / 8;
         int lastElementByteEnd = nullByteEnd;

         for(int i = 0; i < this.mapSize; ++i) {
            this.keyIsNull[i] = true;
            if ((bytes[nullByteCur] & 1 << i * 2 % 8) != 0) {
               this.keyIsNull[i] = false;
               LazyBinaryUtils.checkObjectByteInfo(((MapObjectInspector)this.oi).getMapKeyObjectInspector(), bytes, lastElementByteEnd, this.recordInfo, this.vInt);
               this.keyStart[i] = lastElementByteEnd + this.recordInfo.elementOffset;
               this.keyLength[i] = this.recordInfo.elementSize;
               lastElementByteEnd = this.keyStart[i] + this.keyLength[i];
            } else if (!this.nullMapKey) {
               this.nullMapKey = true;
               LOG.warn("Null map key encountered! Ignoring similar problems.");
            }

            this.valueIsNull[i] = true;
            if ((bytes[nullByteCur] & 1 << (i * 2 + 1) % 8) != 0) {
               this.valueIsNull[i] = false;
               LazyBinaryUtils.checkObjectByteInfo(((MapObjectInspector)this.oi).getMapValueObjectInspector(), bytes, lastElementByteEnd, this.recordInfo, this.vInt);
               this.valueStart[i] = lastElementByteEnd + this.recordInfo.elementOffset;
               this.valueLength[i] = this.recordInfo.elementSize;
               lastElementByteEnd = this.valueStart[i] + this.valueLength[i];
            }

            if (3 == i % 4) {
               ++nullByteCur;
            }
         }

         Arrays.fill(this.keyInited, 0, this.mapSize, false);
         Arrays.fill(this.valueInited, 0, this.mapSize, false);
         this.parsed = true;
      }
   }

   private LazyBinaryObject uncheckedGetValue(int index) {
      if (this.valueIsNull[index]) {
         return null;
      } else {
         if (!this.valueInited[index]) {
            this.valueInited[index] = true;
            if (this.valueObjects[index] == null) {
               this.valueObjects[index] = LazyBinaryFactory.createLazyBinaryObject(((MapObjectInspector)this.oi).getMapValueObjectInspector());
            }

            this.valueObjects[index].init(this.bytes, this.valueStart[index], this.valueLength[index]);
         }

         return this.valueObjects[index];
      }
   }

   public Object getMapValueElement(Object key) {
      if (!this.parsed) {
         this.parse();
      }

      for(int i = 0; i < this.mapSize; ++i) {
         LazyBinaryPrimitive<?, ?> lazyKeyI = this.uncheckedGetKey(i);
         if (lazyKeyI != null) {
            Object keyI = lazyKeyI.getWritableObject();
            if (keyI != null && keyI.equals(key)) {
               LazyBinaryObject v = this.uncheckedGetValue(i);
               return v == null ? v : v.getObject();
            }
         }
      }

      return null;
   }

   private LazyBinaryPrimitive uncheckedGetKey(int index) {
      if (this.keyIsNull[index]) {
         return null;
      } else {
         if (!this.keyInited[index]) {
            this.keyInited[index] = true;
            if (this.keyObjects[index] == null) {
               this.keyObjects[index] = LazyBinaryFactory.createLazyBinaryPrimitiveClass((PrimitiveObjectInspector)((MapObjectInspector)this.oi).getMapKeyObjectInspector());
            }

            this.keyObjects[index].init(this.bytes, this.keyStart[index], this.keyLength[index]);
         }

         return this.keyObjects[index];
      }
   }

   public Map getMap() {
      if (!this.parsed) {
         this.parse();
      }

      if (this.cachedMap == null) {
         this.cachedMap = new LinkedHashMap();
      } else {
         this.cachedMap.clear();
      }

      for(int i = 0; i < this.mapSize; ++i) {
         LazyBinaryPrimitive<?, ?> lazyKey = this.uncheckedGetKey(i);
         if (lazyKey != null) {
            Object key = lazyKey.getObject();
            if (key != null && !this.cachedMap.containsKey(key)) {
               LazyBinaryObject lazyValue = this.uncheckedGetValue(i);
               Object value = lazyValue == null ? null : lazyValue.getObject();
               this.cachedMap.put(key, value);
            }
         }
      }

      return this.cachedMap;
   }

   public int getMapSize() {
      if (!this.parsed) {
         this.parse();
      }

      return this.mapSize;
   }
}
