package org.apache.hadoop.hive.serde2.lazy.objectinspector;

import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.serde2.BaseStructObjectInspector;
import org.apache.hadoop.hive.serde2.StructObject;
import org.apache.hadoop.hive.serde2.avro.AvroLazyObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParameters;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParametersImpl;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.io.Text;

public class LazySimpleStructObjectInspector extends BaseStructObjectInspector {
   private byte separator;
   private LazyObjectInspectorParameters lazyParams;

   protected LazySimpleStructObjectInspector() {
   }

   /** @deprecated */
   @Deprecated
   protected LazySimpleStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, byte separator, Text nullSequence, boolean lastColumnTakesRest, boolean escaped, byte escapeChar) {
      this.init(structFieldNames, structFieldObjectInspectors, (List)null, separator, nullSequence, lastColumnTakesRest, escaped, escapeChar);
   }

   /** @deprecated */
   @Deprecated
   public LazySimpleStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments, byte separator, Text nullSequence, boolean lastColumnTakesRest, boolean escaped, byte escapeChar) {
      this.init(structFieldNames, structFieldObjectInspectors, structFieldComments, separator, nullSequence, lastColumnTakesRest, escaped, escapeChar);
   }

   public LazySimpleStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments, byte separator, LazyObjectInspectorParameters lazyParams) {
      this.init(structFieldNames, structFieldObjectInspectors, structFieldComments, separator, lazyParams);
   }

   protected void init(List structFieldNames, List structFieldObjectInspectors, List structFieldComments, byte separator, Text nullSequence, boolean lastColumnTakesRest, boolean escaped, byte escapeChar) {
      LazyObjectInspectorParameters lazyParams = new LazyObjectInspectorParametersImpl(escaped, escapeChar, false, (List)null, (byte[])null, nullSequence, lastColumnTakesRest);
      this.init(structFieldNames, structFieldObjectInspectors, structFieldComments, separator, lazyParams);
   }

   protected void init(List structFieldNames, List structFieldObjectInspectors, List structFieldComments, byte separator, LazyObjectInspectorParameters lazyParams) {
      this.init(structFieldNames, structFieldObjectInspectors, structFieldComments);
      this.separator = separator;
      this.lazyParams = lazyParams;
   }

   public Object getStructFieldData(Object data, StructField fieldRef) {
      if (data == null) {
         return null;
      } else {
         StructObject struct = (StructObject)data;
         BaseStructObjectInspector.MyField f = (BaseStructObjectInspector.MyField)fieldRef;
         int fieldID = f.getFieldID();

         assert fieldID >= 0 && fieldID < this.fields.size();

         ObjectInspector oi = f.getFieldObjectInspector();
         if (oi instanceof AvroLazyObjectInspector) {
            return ((AvroLazyObjectInspector)oi).getStructFieldData(data, fieldRef);
         } else {
            if (oi instanceof MapObjectInspector) {
               ObjectInspector valueOI = ((MapObjectInspector)oi).getMapValueObjectInspector();
               if (valueOI instanceof AvroLazyObjectInspector) {
                  return ((AvroLazyObjectInspector)valueOI).getStructFieldData(data, fieldRef);
               }
            }

            return struct.getField(fieldID);
         }
      }
   }

   public List getStructFieldsDataAsList(Object data) {
      if (data == null) {
         return null;
      } else {
         List<Object> result = new ArrayList(this.fields.size());

         for(BaseStructObjectInspector.MyField myField : this.fields) {
            result.add(this.getStructFieldData(data, myField));
         }

         return result;
      }
   }

   public byte getSeparator() {
      return this.separator;
   }

   public Text getNullSequence() {
      return this.lazyParams.getNullSequence();
   }

   public boolean getLastColumnTakesRest() {
      return this.lazyParams.isLastColumnTakesRest();
   }

   public boolean isEscaped() {
      return this.lazyParams.isEscaped();
   }

   public byte getEscapeChar() {
      return this.lazyParams.getEscapeChar();
   }

   public LazyObjectInspectorParameters getLazyParams() {
      return this.lazyParams;
   }
}
