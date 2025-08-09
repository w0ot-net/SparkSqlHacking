package org.apache.hadoop.hive.serde2.lazy;

import com.google.common.primitives.Bytes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeStatsStruct;
import org.apache.hadoop.hive.serde2.StructObject;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazySimpleStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyStruct extends LazyNonPrimitive implements StructObject, SerDeStatsStruct {
   private static final Logger LOG = LoggerFactory.getLogger(LazyStruct.class.getName());
   boolean parsed;
   long serializedSize;
   int[] startPosition;
   LazyObjectBase[] fields;
   boolean[] fieldInited;
   boolean missingFieldWarned = false;
   boolean extraFieldWarned = false;
   private transient List cachedList;

   public LazyStruct(LazySimpleStructObjectInspector oi) {
      super(oi);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      super.init(bytes, start, length);
      this.parsed = false;
      this.serializedSize = (long)length;
   }

   private void parse() {
      byte separator = ((LazySimpleStructObjectInspector)this.oi).getSeparator();
      boolean lastColumnTakesRest = ((LazySimpleStructObjectInspector)this.oi).getLastColumnTakesRest();
      boolean isEscaped = ((LazySimpleStructObjectInspector)this.oi).isEscaped();
      byte escapeChar = ((LazySimpleStructObjectInspector)this.oi).getEscapeChar();
      if (this.fields == null) {
         this.initLazyFields(((LazySimpleStructObjectInspector)this.oi).getAllStructFieldRefs());
      }

      int structByteEnd = this.start + this.length;
      int fieldId = 0;
      int fieldByteBegin = this.start;
      int fieldByteEnd = this.start;
      byte[] bytes = this.bytes.getData();

      while(fieldByteEnd <= structByteEnd) {
         if (fieldByteEnd != structByteEnd && bytes[fieldByteEnd] != separator) {
            if (isEscaped && bytes[fieldByteEnd] == escapeChar && fieldByteEnd + 1 < structByteEnd) {
               fieldByteEnd += 2;
            } else {
               ++fieldByteEnd;
            }
         } else {
            if (lastColumnTakesRest && fieldId == this.fields.length - 1) {
               fieldByteEnd = structByteEnd;
            }

            this.startPosition[fieldId] = fieldByteBegin;
            ++fieldId;
            if (fieldId == this.fields.length || fieldByteEnd == structByteEnd) {
               for(int i = fieldId; i <= this.fields.length; ++i) {
                  this.startPosition[i] = fieldByteEnd + 1;
               }
               break;
            }

            fieldByteBegin = fieldByteEnd + 1;
            ++fieldByteEnd;
         }
      }

      if (!this.extraFieldWarned && fieldByteEnd < structByteEnd) {
         this.extraFieldWarned = true;
         LOG.warn("Extra bytes detected at the end of the row! Ignoring similar problems.");
      }

      if (!this.missingFieldWarned && fieldId < this.fields.length) {
         this.missingFieldWarned = true;
         LOG.info("Missing fields! Expected " + this.fields.length + " fields but only got " + fieldId + "! Ignoring similar problems.");
      }

      Arrays.fill(this.fieldInited, false);
      this.parsed = true;
   }

   protected final void initLazyFields(List fieldRefs) {
      this.fields = new LazyObjectBase[fieldRefs.size()];

      for(int i = 0; i < this.fields.length; ++i) {
         try {
            this.fields[i] = this.createLazyField(i, (StructField)fieldRefs.get(i));
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      this.fieldInited = new boolean[this.fields.length];
      this.startPosition = new int[this.fields.length + 1];
   }

   protected LazyObjectBase createLazyField(int fieldID, StructField fieldRef) throws SerDeException {
      return LazyFactory.createLazyObject(fieldRef.getFieldObjectInspector());
   }

   public Object getField(int fieldID) {
      if (!this.parsed) {
         this.parse();
      }

      return this.uncheckedGetField(fieldID);
   }

   private Object uncheckedGetField(int fieldID) {
      if (this.fieldInited[fieldID]) {
         return this.fields[fieldID].getObject();
      } else {
         this.fieldInited[fieldID] = true;
         int fieldByteBegin = this.startPosition[fieldID];
         int fieldLength = this.startPosition[fieldID + 1] - this.startPosition[fieldID] - 1;
         if (this.isNull(((LazySimpleStructObjectInspector)this.oi).getNullSequence(), this.bytes, fieldByteBegin, fieldLength)) {
            this.fields[fieldID].setNull();
         } else {
            this.fields[fieldID].init(this.bytes, fieldByteBegin, fieldLength);
         }

         return this.fields[fieldID].getObject();
      }
   }

   public List getFieldsAsList() {
      if (!this.parsed) {
         this.parse();
      }

      if (this.cachedList == null) {
         this.cachedList = new ArrayList();
      } else {
         this.cachedList.clear();
      }

      for(int i = 0; i < this.fields.length; ++i) {
         this.cachedList.add(this.uncheckedGetField(i));
      }

      return this.cachedList;
   }

   protected boolean getParsed() {
      return this.parsed;
   }

   protected void setParsed(boolean parsed) {
      this.parsed = parsed;
   }

   protected LazyObjectBase[] getFields() {
      return this.fields;
   }

   protected void setFields(LazyObject[] fields) {
      this.fields = fields;
   }

   protected boolean[] getFieldInited() {
      return this.fieldInited;
   }

   protected void setFieldInited(boolean[] fieldInited) {
      this.fieldInited = fieldInited;
   }

   public long getRawDataSerializedSize() {
      return this.serializedSize;
   }

   public void parseMultiDelimit(byte[] rawRow, byte[] fieldDelimit) {
      if (rawRow != null && fieldDelimit != null) {
         if (this.fields == null) {
            List<? extends StructField> fieldRefs = ((LazySimpleStructObjectInspector)this.oi).getAllStructFieldRefs();
            this.fields = new LazyObject[fieldRefs.size()];

            for(int i = 0; i < this.fields.length; ++i) {
               this.fields[i] = LazyFactory.createLazyObject(((StructField)fieldRefs.get(i)).getFieldObjectInspector());
            }

            this.fieldInited = new boolean[this.fields.length];
            this.startPosition = new int[this.fields.length + 1];
         }

         int[] delimitIndexes = this.findIndexes(rawRow, fieldDelimit);
         int diff = fieldDelimit.length - 1;
         this.startPosition[0] = 0;

         for(int i = 1; i < this.fields.length; ++i) {
            if (delimitIndexes[i - 1] != -1) {
               int start = delimitIndexes[i - 1] + fieldDelimit.length;
               this.startPosition[i] = start - i * diff;
            } else {
               this.startPosition[i] = this.length + 1;
            }
         }

         this.startPosition[this.fields.length] = this.length + 1;
         Arrays.fill(this.fieldInited, false);
         this.parsed = true;
      }
   }

   private int[] findIndexes(byte[] array, byte[] target) {
      if (this.fields.length <= 1) {
         return new int[0];
      } else {
         int[] indexes = new int[this.fields.length - 1];
         Arrays.fill(indexes, -1);
         indexes[0] = Bytes.indexOf(array, target);
         if (indexes[0] == -1) {
            return indexes;
         } else {
            int indexInNewArray = indexes[0];

            for(int i = 1; i < indexes.length; ++i) {
               array = Arrays.copyOfRange(array, indexInNewArray + target.length, array.length);
               indexInNewArray = Bytes.indexOf(array, target);
               if (indexInNewArray == -1) {
                  break;
               }

               indexes[i] = indexInNewArray + indexes[i - 1] + target.length;
            }

            return indexes;
         }
      }
   }

   public byte[] getBytes() {
      return this.bytes.getData();
   }
}
