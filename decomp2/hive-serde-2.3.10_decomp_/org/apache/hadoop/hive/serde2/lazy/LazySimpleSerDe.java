package org.apache.hadoop.hive.serde2.lazy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.hadoop.hive.serde2.AbstractEncodingAwareSerDe;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeSpec;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.SerDeUtils;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParametersImpl;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.UnionObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.StructTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.BinaryComparable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

@SerDeSpec(
   schemaProps = {"columns", "columns.types", "field.delim", "colelction.delim", "mapkey.delim", "serialization.format", "serialization.null.format", "serialization.escape.crlf", "serialization.last.column.takes.rest", "escape.delim", "serialization.encoding", "hive.serialization.extend.nesting.levels", "hive.serialization.extend.additional.nesting.levels"}
)
@Public
@Stable
public class LazySimpleSerDe extends AbstractEncodingAwareSerDe {
   private LazySerDeParameters serdeParams = null;
   private ObjectInspector cachedObjectInspector;
   private long serializedSize;
   private SerDeStats stats;
   private boolean lastOperationSerialize;
   private boolean lastOperationDeserialize;
   LazyStruct cachedLazyStruct;
   ByteArrayRef byteArrayRef;
   Text serializeCache = new Text();
   ByteStream.Output serializeStream = new ByteStream.Output();

   public String toString() {
      return this.getClass().toString() + "[" + Arrays.asList(this.serdeParams.getSeparators()) + ":" + ((StructTypeInfo)this.serdeParams.getRowTypeInfo()).getAllStructFieldNames() + ":" + ((StructTypeInfo)this.serdeParams.getRowTypeInfo()).getAllStructFieldTypeInfos() + "]";
   }

   public LazySimpleSerDe() throws SerDeException {
   }

   public void initialize(Configuration job, Properties tbl) throws SerDeException {
      super.initialize(job, tbl);
      this.serdeParams = new LazySerDeParameters(job, tbl, this.getClass().getName());
      this.cachedObjectInspector = LazyFactory.createLazyStructInspector(this.serdeParams.getColumnNames(), this.serdeParams.getColumnTypes(), new LazyObjectInspectorParametersImpl(this.serdeParams));
      this.cachedLazyStruct = (LazyStruct)LazyFactory.createLazyObject(this.cachedObjectInspector);
      this.serializedSize = 0L;
      this.stats = new SerDeStats();
      this.lastOperationSerialize = false;
      this.lastOperationDeserialize = false;
   }

   public Object doDeserialize(Writable field) throws SerDeException {
      if (this.byteArrayRef == null) {
         this.byteArrayRef = new ByteArrayRef();
      }

      BinaryComparable b = (BinaryComparable)field;
      this.byteArrayRef.setData(b.getBytes());
      this.cachedLazyStruct.init(this.byteArrayRef, 0, b.getLength());
      this.lastOperationSerialize = false;
      this.lastOperationDeserialize = true;
      return this.cachedLazyStruct;
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return this.cachedObjectInspector;
   }

   public Class getSerializedClass() {
      return Text.class;
   }

   public Writable doSerialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      if (objInspector.getCategory() != ObjectInspector.Category.STRUCT) {
         throw new SerDeException(this.getClass().toString() + " can only serialize struct types, but we got: " + objInspector.getTypeName());
      } else {
         StructObjectInspector soi = (StructObjectInspector)objInspector;
         List<? extends StructField> fields = soi.getAllStructFieldRefs();
         List<Object> list = soi.getStructFieldsDataAsList(obj);
         List<? extends StructField> declaredFields = this.serdeParams.getRowTypeInfo() != null && ((StructTypeInfo)this.serdeParams.getRowTypeInfo()).getAllStructFieldNames().size() > 0 ? ((StructObjectInspector)this.getObjectInspector()).getAllStructFieldRefs() : null;
         this.serializeStream.reset();
         this.serializedSize = 0L;

         for(int i = 0; i < fields.size(); ++i) {
            if (i > 0) {
               this.serializeStream.write(this.serdeParams.getSeparators()[0]);
            }

            ObjectInspector foi = ((StructField)fields.get(i)).getFieldObjectInspector();
            Object f = list == null ? null : list.get(i);
            if (declaredFields != null && i >= declaredFields.size()) {
               throw new SerDeException("Error: expecting " + declaredFields.size() + " but asking for field " + i + "\ndata=" + obj + "\ntableType=" + this.serdeParams.getRowTypeInfo().toString() + "\ndataType=" + TypeInfoUtils.getTypeInfoFromObjectInspector(objInspector));
            }

            this.serializeField(this.serializeStream, f, foi, this.serdeParams);
         }

         this.serializeCache.set(this.serializeStream.getData(), 0, this.serializeStream.getLength());
         this.serializedSize = (long)this.serializeStream.getLength();
         this.lastOperationSerialize = true;
         this.lastOperationDeserialize = false;
         return this.serializeCache;
      }
   }

   protected void serializeField(ByteStream.Output out, Object obj, ObjectInspector objInspector, LazySerDeParameters serdeParams) throws SerDeException {
      try {
         serialize(out, obj, objInspector, serdeParams.getSeparators(), 1, serdeParams.getNullSequence(), serdeParams.isEscaped(), serdeParams.getEscapeChar(), serdeParams.getNeedsEscape());
      } catch (IOException e) {
         throw new SerDeException(e);
      }
   }

   public static void serialize(ByteStream.Output out, Object obj, ObjectInspector objInspector, byte[] separators, int level, Text nullSequence, boolean escaped, byte escapeChar, boolean[] needsEscape) throws IOException, SerDeException {
      if (obj == null) {
         out.write(nullSequence.getBytes(), 0, nullSequence.getLength());
      } else {
         switch (objInspector.getCategory()) {
            case PRIMITIVE:
               LazyUtils.writePrimitiveUTF8(out, obj, (PrimitiveObjectInspector)objInspector, escaped, escapeChar, needsEscape);
               return;
            case LIST:
               char separator = (char)LazyUtils.getSeparator(separators, level);
               ListObjectInspector loi = (ListObjectInspector)objInspector;
               List<?> list = loi.getList(obj);
               ObjectInspector eoi = loi.getListElementObjectInspector();
               if (list == null) {
                  out.write(nullSequence.getBytes(), 0, nullSequence.getLength());
               } else {
                  for(int i = 0; i < list.size(); ++i) {
                     if (i > 0) {
                        out.write(separator);
                     }

                     serialize(out, list.get(i), eoi, separators, level + 1, nullSequence, escaped, escapeChar, needsEscape);
                  }
               }

               return;
            case MAP:
               char separator = (char)LazyUtils.getSeparator(separators, level);
               char keyValueSeparator = (char)LazyUtils.getSeparator(separators, level + 1);
               MapObjectInspector moi = (MapObjectInspector)objInspector;
               ObjectInspector koi = moi.getMapKeyObjectInspector();
               ObjectInspector voi = moi.getMapValueObjectInspector();
               Map<?, ?> map = moi.getMap(obj);
               if (map == null) {
                  out.write(nullSequence.getBytes(), 0, nullSequence.getLength());
               } else {
                  boolean first = true;

                  for(Map.Entry entry : map.entrySet()) {
                     if (first) {
                        first = false;
                     } else {
                        out.write(separator);
                     }

                     serialize(out, entry.getKey(), koi, separators, level + 2, nullSequence, escaped, escapeChar, needsEscape);
                     out.write(keyValueSeparator);
                     serialize(out, entry.getValue(), voi, separators, level + 2, nullSequence, escaped, escapeChar, needsEscape);
                  }
               }

               return;
            case STRUCT:
               char separator = (char)LazyUtils.getSeparator(separators, level);
               StructObjectInspector soi = (StructObjectInspector)objInspector;
               List<? extends StructField> fields = soi.getAllStructFieldRefs();
               List<?> list = soi.getStructFieldsDataAsList(obj);
               if (list == null) {
                  out.write(nullSequence.getBytes(), 0, nullSequence.getLength());
               } else {
                  for(int i = 0; i < list.size(); ++i) {
                     if (i > 0) {
                        out.write(separator);
                     }

                     serialize(out, list.get(i), ((StructField)fields.get(i)).getFieldObjectInspector(), separators, level + 1, nullSequence, escaped, escapeChar, needsEscape);
                  }
               }

               return;
            case UNION:
               char separator = (char)LazyUtils.getSeparator(separators, level);
               UnionObjectInspector uoi = (UnionObjectInspector)objInspector;
               List<? extends ObjectInspector> ois = uoi.getObjectInspectors();
               if (ois == null) {
                  out.write(nullSequence.getBytes(), 0, nullSequence.getLength());
               } else {
                  LazyUtils.writePrimitiveUTF8(out, new Byte(uoi.getTag(obj)), PrimitiveObjectInspectorFactory.javaByteObjectInspector, escaped, escapeChar, needsEscape);
                  out.write(separator);
                  serialize(out, uoi.getField(obj), (ObjectInspector)ois.get(uoi.getTag(obj)), separators, level + 1, nullSequence, escaped, escapeChar, needsEscape);
               }

               return;
            default:
               throw new RuntimeException("Unknown category type: " + objInspector.getCategory());
         }
      }
   }

   public SerDeStats getSerDeStats() {
      assert this.lastOperationSerialize != this.lastOperationDeserialize;

      if (this.lastOperationSerialize) {
         this.stats.setRawDataSize(this.serializedSize);
      } else {
         this.stats.setRawDataSize(this.cachedLazyStruct.getRawDataSerializedSize());
      }

      return this.stats;
   }

   protected Writable transformFromUTF8(Writable blob) {
      Text text = (Text)blob;
      return SerDeUtils.transformTextFromUTF8(text, this.charset);
   }

   protected Writable transformToUTF8(Writable blob) {
      Text text = (Text)blob;
      return SerDeUtils.transformTextToUTF8(text, this.charset);
   }

   /** @deprecated */
   @Deprecated
   public static SerDeParameters initSerdeParams(Configuration job, Properties tbl, String serdeName) throws SerDeException {
      return new SerDeParameters(job, tbl, serdeName);
   }

   /** @deprecated */
   @Deprecated
   public static class SerDeParameters extends LazySerDeParameters {
      public SerDeParameters(Configuration job, Properties tbl, String serdeName) throws SerDeException {
         super(job, tbl, serdeName);
      }
   }
}
