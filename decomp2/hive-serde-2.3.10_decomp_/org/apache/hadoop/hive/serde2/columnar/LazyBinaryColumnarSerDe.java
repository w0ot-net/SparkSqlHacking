package org.apache.hadoop.hive.serde2.columnar;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.ColumnProjectionUtils;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeSpec;
import org.apache.hadoop.hive.serde2.lazy.LazySerDeParameters;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryFactory;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinarySerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.io.Writable;

@SerDeSpec(
   schemaProps = {"columns", "columns.types"}
)
public class LazyBinaryColumnarSerDe extends ColumnarSerDeBase {
   private List columnNames;
   private List columnTypes;
   static final byte[] INVALID_UTF__SINGLE_BYTE = new byte[]{(byte)Integer.parseInt("10111111", 2)};

   public String toString() {
      return this.getClass().toString() + "[" + this.columnNames + ":" + this.columnTypes + "]";
   }

   public void initialize(Configuration conf, Properties tbl) throws SerDeException {
      LazySerDeParameters serdeParams = new LazySerDeParameters(conf, tbl, this.getClass().getName());
      this.columnNames = serdeParams.getColumnNames();
      this.columnTypes = serdeParams.getColumnTypes();
      this.cachedObjectInspector = LazyBinaryFactory.createColumnarStructInspector(this.columnNames, this.columnTypes);
      int size = this.columnTypes.size();
      List<Integer> notSkipIDs = new ArrayList();
      if (conf != null && !ColumnProjectionUtils.isReadAllColumns(conf)) {
         notSkipIDs = ColumnProjectionUtils.getReadColumnIDs(conf);
      } else {
         for(int i = 0; i < size; ++i) {
            notSkipIDs.add(i);
         }
      }

      this.cachedLazyStruct = new LazyBinaryColumnarStruct(this.cachedObjectInspector, notSkipIDs);
      super.initialize(size);
   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      if (objInspector.getCategory() != ObjectInspector.Category.STRUCT) {
         throw new SerDeException(this.getClass().toString() + " can only serialize struct types, but we got: " + objInspector.getTypeName());
      } else {
         StructObjectInspector soi = (StructObjectInspector)objInspector;
         List<? extends StructField> fields = soi.getAllStructFieldRefs();
         List<Object> list = soi.getStructFieldsDataAsList(obj);
         LazyBinarySerDe.BooleanRef warnedOnceNullMapKey = new LazyBinarySerDe.BooleanRef(false);
         this.serializeStream.reset();
         this.serializedSize = 0L;
         int streamOffset = 0;

         for(int i = 0; i < fields.size(); ++i) {
            ObjectInspector foi = ((StructField)fields.get(i)).getFieldObjectInspector();
            Object f = list == null ? null : list.get(i);
            if (f != null && foi.getCategory().equals(ObjectInspector.Category.PRIMITIVE) && ((PrimitiveObjectInspector)foi).getPrimitiveCategory().equals(PrimitiveObjectInspector.PrimitiveCategory.STRING) && ((StringObjectInspector)foi).getPrimitiveJavaObject(f).length() == 0) {
               this.serializeStream.write(INVALID_UTF__SINGLE_BYTE, 0, 1);
            } else {
               LazyBinarySerDe.serialize(this.serializeStream, f, foi, true, warnedOnceNullMapKey);
            }

            this.field[i].set(this.serializeStream.getData(), streamOffset, this.serializeStream.getLength() - streamOffset);
            streamOffset = this.serializeStream.getLength();
         }

         this.serializedSize = (long)this.serializeStream.getLength();
         this.lastOperationSerialize = true;
         this.lastOperationDeserialize = false;
         return this.serializeCache;
      }
   }
}
