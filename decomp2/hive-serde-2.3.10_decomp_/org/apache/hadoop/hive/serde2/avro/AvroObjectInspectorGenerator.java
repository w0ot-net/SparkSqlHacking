package org.apache.hadoop.hive.serde2.avro;

import java.util.ArrayList;
import java.util.List;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.ListTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.MapTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.StructTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.UnionTypeInfo;

public class AvroObjectInspectorGenerator {
   private final List columnNames;
   private final List columnTypes;
   private final List columnComments;
   private final ObjectInspector oi;

   public AvroObjectInspectorGenerator(Schema schema) throws SerDeException {
      this.verifySchemaIsARecord(schema);
      this.columnNames = generateColumnNames(schema);
      this.columnTypes = SchemaToTypeInfo.generateColumnTypes(schema);
      this.columnComments = generateColumnComments(schema);

      assert this.columnNames.size() == this.columnTypes.size();

      this.oi = this.createObjectInspector();
   }

   private void verifySchemaIsARecord(Schema schema) throws SerDeException {
      if (!schema.getType().equals(Type.RECORD)) {
         throw new AvroSerdeException("Schema for table must be of type RECORD. Received type: " + schema.getType());
      }
   }

   public List getColumnNames() {
      return this.columnNames;
   }

   public List getColumnTypes() {
      return this.columnTypes;
   }

   public ObjectInspector getObjectInspector() {
      return this.oi;
   }

   private ObjectInspector createObjectInspector() throws SerDeException {
      List<ObjectInspector> columnOIs = new ArrayList(this.columnNames.size());

      for(int i = 0; i < this.columnNames.size(); ++i) {
         columnOIs.add(i, this.createObjectInspectorWorker((TypeInfo)this.columnTypes.get(i)));
      }

      return ObjectInspectorFactory.getStandardStructObjectInspector(this.columnNames, columnOIs, this.columnComments);
   }

   private ObjectInspector createObjectInspectorWorker(TypeInfo ti) throws SerDeException {
      if (!this.supportedCategories(ti)) {
         throw new AvroSerdeException("Don't yet support this type: " + ti);
      } else {
         ObjectInspector result;
         switch (ti.getCategory()) {
            case PRIMITIVE:
               PrimitiveTypeInfo pti = (PrimitiveTypeInfo)ti;
               result = PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(pti);
               break;
            case STRUCT:
               StructTypeInfo sti = (StructTypeInfo)ti;
               ArrayList<ObjectInspector> ois = new ArrayList(sti.getAllStructFieldTypeInfos().size());

               for(TypeInfo typeInfo : sti.getAllStructFieldTypeInfos()) {
                  ois.add(this.createObjectInspectorWorker(typeInfo));
               }

               result = ObjectInspectorFactory.getStandardStructObjectInspector(sti.getAllStructFieldNames(), ois);
               break;
            case MAP:
               MapTypeInfo mti = (MapTypeInfo)ti;
               result = ObjectInspectorFactory.getStandardMapObjectInspector(PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(PrimitiveObjectInspector.PrimitiveCategory.STRING), this.createObjectInspectorWorker(mti.getMapValueTypeInfo()));
               break;
            case LIST:
               ListTypeInfo ati = (ListTypeInfo)ti;
               result = ObjectInspectorFactory.getStandardListObjectInspector(this.createObjectInspectorWorker(ati.getListElementTypeInfo()));
               break;
            case UNION:
               UnionTypeInfo uti = (UnionTypeInfo)ti;
               List<TypeInfo> allUnionObjectTypeInfos = uti.getAllUnionObjectTypeInfos();
               List<ObjectInspector> unionObjectInspectors = new ArrayList(allUnionObjectTypeInfos.size());

               for(TypeInfo typeInfo : allUnionObjectTypeInfos) {
                  unionObjectInspectors.add(this.createObjectInspectorWorker(typeInfo));
               }

               result = ObjectInspectorFactory.getStandardUnionObjectInspector(unionObjectInspectors);
               break;
            default:
               throw new AvroSerdeException("No Hive categories matched: " + ti);
         }

         return result;
      }
   }

   private boolean supportedCategories(TypeInfo ti) {
      ObjectInspector.Category c = ti.getCategory();
      return c.equals(ObjectInspector.Category.PRIMITIVE) || c.equals(ObjectInspector.Category.MAP) || c.equals(ObjectInspector.Category.LIST) || c.equals(ObjectInspector.Category.STRUCT) || c.equals(ObjectInspector.Category.UNION);
   }

   public static List generateColumnNames(Schema schema) {
      List<Schema.Field> fields = schema.getFields();
      List<String> fieldsList = new ArrayList(fields.size());

      for(Schema.Field field : fields) {
         fieldsList.add(field.name());
      }

      return fieldsList;
   }

   public static List generateColumnComments(Schema schema) {
      List<Schema.Field> fields = schema.getFields();
      List<String> fieldComments = new ArrayList(fields.size());

      for(Schema.Field field : fields) {
         String fieldComment = field.doc() == null ? "" : field.doc();
         fieldComments.add(fieldComment);
      }

      return fieldComments;
   }
}
