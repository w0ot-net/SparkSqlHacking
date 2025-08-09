package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.SerDeStatsStruct;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazybinary.objectinspector.LazyBinaryUnionObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyBinaryUnion extends LazyBinaryNonPrimitive implements SerDeStatsStruct {
   private static Logger LOG = LoggerFactory.getLogger(LazyBinaryUnion.class.getName());
   boolean parsed;
   long serializedSize;
   LazyBinaryObject field;
   boolean fieldInited;
   int fieldStart;
   int fieldLength;
   byte tag;
   final LazyBinaryUtils.VInt vInt = new LazyBinaryUtils.VInt();
   LazyBinaryUtils.RecordInfo recordInfo = new LazyBinaryUtils.RecordInfo();
   boolean missingFieldWarned = false;
   boolean extraFieldWarned = false;
   Object cachedObject;

   protected LazyBinaryUnion(LazyBinaryUnionObjectInspector oi) {
      super(oi);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      super.init(bytes, start, length);
      this.parsed = false;
      this.serializedSize = (long)length;
      this.fieldInited = false;
      this.field = null;
      this.cachedObject = null;
   }

   private void parse() {
      LazyBinaryUnionObjectInspector uoi = (LazyBinaryUnionObjectInspector)this.oi;
      int unionByteEnd = this.start + this.length;
      byte[] byteArr = this.bytes.getData();
      int tagEnd = this.start + 1;
      this.tag = byteArr[this.start];
      this.field = LazyBinaryFactory.createLazyBinaryObject((ObjectInspector)uoi.getObjectInspectors().get(this.tag));
      LazyBinaryUtils.checkObjectByteInfo((ObjectInspector)uoi.getObjectInspectors().get(this.tag), byteArr, tagEnd, this.recordInfo, this.vInt);
      this.fieldStart = tagEnd + this.recordInfo.elementOffset;
      this.fieldLength = this.recordInfo.elementSize;
      if (!this.extraFieldWarned && this.fieldStart + this.fieldLength < unionByteEnd) {
         this.extraFieldWarned = true;
         LOG.warn("Extra bytes detected at the end of the row! Ignoring similar problems.");
      }

      if (!this.missingFieldWarned && this.fieldStart + this.fieldLength > unionByteEnd) {
         this.missingFieldWarned = true;
         LOG.info("Missing fields! Expected 1 fields but only got " + this.field + "! Ignoring similar problems.");
      }

      this.parsed = true;
   }

   public Object getField() {
      if (!this.parsed) {
         this.parse();
      }

      return this.cachedObject == null ? this.uncheckedGetField() : this.cachedObject;
   }

   private Object uncheckedGetField() {
      if (!this.fieldInited) {
         this.fieldInited = true;
         this.field.init(this.bytes, this.fieldStart, this.fieldLength);
      }

      this.cachedObject = this.field.getObject();
      return this.field.getObject();
   }

   public Object getObject() {
      return this;
   }

   public long getRawDataSerializedSize() {
      return this.serializedSize;
   }

   public byte getTag() {
      if (!this.parsed) {
         this.parse();
      }

      return this.tag;
   }
}
