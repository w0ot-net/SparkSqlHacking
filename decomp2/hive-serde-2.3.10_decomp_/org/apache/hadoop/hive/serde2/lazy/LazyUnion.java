package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazyUnionObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public class LazyUnion extends LazyNonPrimitive {
   private boolean parsed;
   private int startPosition;
   private Object field;
   private byte tag;
   private boolean fieldInited = false;
   private boolean fieldSet = false;

   public LazyUnion(LazyUnionObjectInspector oi) {
      super(oi);
   }

   public LazyUnion(LazyUnionObjectInspector oi, byte tag, Object field) {
      super(oi);
      this.field = field;
      this.tag = tag;
      this.fieldSet = true;
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      super.init(bytes, start, length);
      this.parsed = false;
   }

   private void parse() {
      byte separator = ((LazyUnionObjectInspector)this.oi).getSeparator();
      boolean isEscaped = ((LazyUnionObjectInspector)this.oi).isEscaped();
      byte escapeChar = ((LazyUnionObjectInspector)this.oi).getEscapeChar();
      boolean tagStarted = false;
      boolean tagParsed = false;
      int tagStart = -1;
      int tagEnd = -1;
      int unionByteEnd = this.start + this.length;
      int fieldByteEnd = this.start;

      byte[] bytes;
      for(bytes = this.bytes.getData(); fieldByteEnd < unionByteEnd; ++fieldByteEnd) {
         if (bytes[fieldByteEnd] != separator) {
            if (isEscaped && bytes[fieldByteEnd] == escapeChar && fieldByteEnd + 1 < unionByteEnd) {
               ++fieldByteEnd;
            } else if (!tagStarted) {
               tagStart = fieldByteEnd;
               tagStarted = true;
            }
         } else if (!tagParsed) {
            tagEnd = fieldByteEnd - 1;
            this.startPosition = fieldByteEnd + 1;
            tagParsed = true;
         }
      }

      this.tag = LazyByte.parseByte(bytes, tagStart, tagEnd - tagStart + 1);
      this.field = LazyFactory.createLazyObject((ObjectInspector)((LazyUnionObjectInspector)this.oi).getObjectInspectors().get(this.tag));
      this.fieldInited = false;
      this.parsed = true;
   }

   private Object uncheckedGetField() {
      LazyObject field = (LazyObject)this.field;
      if (this.fieldInited) {
         return field.getObject();
      } else {
         this.fieldInited = true;
         int fieldStart = this.startPosition;
         int fieldLength = this.start + this.length - this.startPosition;
         if (this.isNull(((LazyUnionObjectInspector)this.oi).getNullSequence(), this.bytes, fieldStart, fieldLength)) {
            field.setNull();
         } else {
            field.init(this.bytes, fieldStart, fieldLength);
         }

         return field.getObject();
      }
   }

   public Object getField() {
      if (this.fieldSet) {
         return this.field;
      } else {
         if (!this.parsed) {
            this.parse();
         }

         return this.uncheckedGetField();
      }
   }

   public byte getTag() {
      if (this.fieldSet) {
         return this.tag;
      } else {
         if (!this.parsed) {
            this.parse();
         }

         return this.tag;
      }
   }
}
