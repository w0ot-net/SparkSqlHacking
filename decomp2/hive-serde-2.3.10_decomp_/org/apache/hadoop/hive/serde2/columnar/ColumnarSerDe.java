package org.apache.hadoop.hive.serde2.columnar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.ColumnProjectionUtils;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeSpec;
import org.apache.hadoop.hive.serde2.SerDeUtils;
import org.apache.hadoop.hive.serde2.lazy.LazyFactory;
import org.apache.hadoop.hive.serde2.lazy.LazySerDeParameters;
import org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.StructTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SerDeSpec(
   schemaProps = {"columns", "columns.types", "field.delim", "colelction.delim", "mapkey.delim", "serialization.format", "serialization.null.format", "serialization.escape.crlf", "serialization.last.column.takes.rest", "escape.delim", "serialization.encoding", "hive.serialization.extend.nesting.levels", "hive.serialization.extend.additional.nesting.levels"}
)
public class ColumnarSerDe extends ColumnarSerDeBase {
   public static final Logger LOG = LoggerFactory.getLogger(ColumnarSerDe.class);
   protected LazySerDeParameters serdeParams = null;

   public String toString() {
      return this.getClass().toString() + "[" + Arrays.asList(this.serdeParams.getSeparators()) + ":" + ((StructTypeInfo)this.serdeParams.getRowTypeInfo()).getAllStructFieldNames() + ":" + ((StructTypeInfo)this.serdeParams.getRowTypeInfo()).getAllStructFieldTypeInfos() + "]";
   }

   public ColumnarSerDe() throws SerDeException {
   }

   public void initialize(Configuration conf, Properties tbl) throws SerDeException {
      this.serdeParams = new LazySerDeParameters(conf, tbl, this.getClass().getName());
      this.cachedObjectInspector = LazyFactory.createColumnarStructInspector(this.serdeParams.getColumnNames(), this.serdeParams.getColumnTypes(), this.serdeParams);
      int size = this.serdeParams.getColumnTypes().size();
      List<Integer> notSkipIDs = new ArrayList();
      if (conf != null && !ColumnProjectionUtils.isReadAllColumns(conf)) {
         notSkipIDs = ColumnProjectionUtils.getReadColumnIDs(conf);
      } else {
         for(int i = 0; i < size; ++i) {
            notSkipIDs.add(i);
         }
      }

      this.cachedLazyStruct = new ColumnarStruct(this.cachedObjectInspector, notSkipIDs, this.serdeParams.getNullSequence());
      super.initialize(size);
   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      if (objInspector.getCategory() != ObjectInspector.Category.STRUCT) {
         throw new SerDeException(this.getClass().toString() + " can only serialize struct types, but we got: " + objInspector.getTypeName());
      } else {
         StructObjectInspector soi = (StructObjectInspector)objInspector;
         List<? extends StructField> fields = soi.getAllStructFieldRefs();
         List<Object> list = soi.getStructFieldsDataAsList(obj);
         List<? extends StructField> declaredFields = this.serdeParams.getRowTypeInfo() != null && ((StructTypeInfo)this.serdeParams.getRowTypeInfo()).getAllStructFieldNames().size() > 0 ? ((StructObjectInspector)this.getObjectInspector()).getAllStructFieldRefs() : null;

         try {
            this.serializeStream.reset();
            this.serializedSize = 0L;
            int count = 0;

            for(int i = 0; i < fields.size(); ++i) {
               ObjectInspector foi = ((StructField)fields.get(i)).getFieldObjectInspector();
               Object f = list == null ? null : list.get(i);
               if (declaredFields != null && i >= declaredFields.size()) {
                  throw new SerDeException("Error: expecting " + declaredFields.size() + " but asking for field " + i + "\ndata=" + obj + "\ntableType=" + this.serdeParams.getRowTypeInfo().toString() + "\ndataType=" + TypeInfoUtils.getTypeInfoFromObjectInspector(objInspector));
               }

               if (foi.getCategory().equals(ObjectInspector.Category.PRIMITIVE) || declaredFields != null && !((StructField)declaredFields.get(i)).getFieldObjectInspector().getCategory().equals(ObjectInspector.Category.PRIMITIVE)) {
                  LazySimpleSerDe.serialize(this.serializeStream, f, foi, this.serdeParams.getSeparators(), 1, this.serdeParams.getNullSequence(), this.serdeParams.isEscaped(), this.serdeParams.getEscapeChar(), this.serdeParams.getNeedsEscape());
               } else {
                  LazySimpleSerDe.serialize(this.serializeStream, SerDeUtils.getJSONString(f, foi), PrimitiveObjectInspectorFactory.javaStringObjectInspector, this.serdeParams.getSeparators(), 1, this.serdeParams.getNullSequence(), this.serdeParams.isEscaped(), this.serdeParams.getEscapeChar(), this.serdeParams.getNeedsEscape());
               }

               this.field[i].set(this.serializeStream.getData(), count, this.serializeStream.getLength() - count);
               count = this.serializeStream.getLength();
            }

            this.serializedSize = (long)this.serializeStream.getLength();
            this.lastOperationSerialize = true;
            this.lastOperationDeserialize = false;
         } catch (IOException e) {
            throw new SerDeException(e);
         }

         return this.serializeCache;
      }
   }
}
