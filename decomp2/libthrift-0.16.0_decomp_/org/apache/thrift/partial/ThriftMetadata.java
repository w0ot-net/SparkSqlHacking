package org.apache.thrift.partial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TUnion;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.SetMetaData;
import org.apache.thrift.meta_data.StructMetaData;

public class ThriftMetadata {
   static IllegalArgumentException fieldNotFoundException(String fieldName) {
      return new IllegalArgumentException("field not found: '" + fieldName + "'");
   }

   static UnsupportedOperationException unsupportedFieldTypeException(byte fieldType) {
      return new UnsupportedOperationException("field type not supported: '" + fieldType + "'");
   }

   static enum FieldTypeEnum implements TFieldIdEnum {
      ROOT((short)0, "root"),
      ENUM((short)1, "enum"),
      LIST_ELEMENT((short)2, "listElement"),
      MAP_KEY((short)3, "mapKey"),
      MAP_VALUE((short)4, "mapValue"),
      SET_ELEMENT((short)5, "setElement");

      private short id;
      private String name;

      private FieldTypeEnum(short id, String name) {
         this.id = id;
         this.name = name;
      }

      public short getThriftFieldId() {
         return this.id;
      }

      public String getFieldName() {
         return this.name;
      }
   }

   private static enum ComparisonResult {
      UNKNOWN,
      EQUAL,
      NOT_EQUAL;
   }

   public abstract static class ThriftObject implements Serializable {
      public final ThriftObject parent;
      public final TFieldIdEnum fieldId;
      public final FieldMetaData data;
      public Object additionalData;
      protected List noFields = Collections.emptyList();

      ThriftObject(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data) {
         this.parent = parent;
         this.fieldId = fieldId;
         this.data = data;
      }

      protected abstract void toPrettyString(StringBuilder var1, int var2);

      protected String getIndent(int level) {
         return StringUtils.repeat(" ", level * 4);
      }

      protected void append(StringBuilder sb, String format, Object... args) {
         sb.append(String.format(format, args));
      }

      protected String getName() {
         return this.fieldId.getFieldName();
      }

      protected String getSubElementName(TFieldIdEnum fieldId) {
         return this.getSubElementName(fieldId, "element");
      }

      protected String getSubElementName(TFieldIdEnum fieldId, String suffix) {
         return String.format("%s_%s", fieldId.getFieldName(), suffix);
      }

      private static class Factory {
         static ThriftObject createNew(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data, List fields) {
            byte fieldType = data.valueMetaData.type;
            switch (fieldType) {
               case 2:
               case 3:
               case 4:
               case 6:
               case 8:
               case 10:
               case 11:
                  return new ThriftPrimitive(parent, fieldId, data);
               case 5:
               case 7:
               case 9:
               default:
                  throw ThriftMetadata.unsupportedFieldTypeException(fieldType);
               case 12:
                  return ThriftMetadata.ThriftStructBase.create(parent, fieldId, data, fields);
               case 13:
                  return new ThriftMap(parent, fieldId, data, fields);
               case 14:
                  return new ThriftSet(parent, fieldId, data, fields);
               case 15:
                  return new ThriftList(parent, fieldId, data, fields);
               case 16:
                  return new ThriftEnum(parent, fieldId, data);
            }
         }
      }
   }

   public static class ThriftPrimitive extends ThriftObject {
      ThriftPrimitive(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data) {
         super(parent, fieldId, data);
      }

      public boolean isBinary() {
         return this.data.valueMetaData.isBinary();
      }

      protected void toPrettyString(StringBuilder sb, int level) {
         String fieldType = this.getTypeName();
         this.append(sb, "%s%s %s;\n", new Object[]{this.getIndent(level), fieldType, this.getName()});
      }

      private String getTypeName() {
         byte fieldType = this.data.valueMetaData.type;
         switch (fieldType) {
            case 2:
               return "bool";
            case 3:
               return "byte";
            case 4:
               return "double";
            case 5:
            case 7:
            case 9:
            default:
               throw ThriftMetadata.unsupportedFieldTypeException(fieldType);
            case 6:
               return "i16";
            case 8:
               return "i32";
            case 10:
               return "i64";
            case 11:
               return this.isBinary() ? "binary" : "string";
         }
      }
   }

   public static class ThriftEnum extends ThriftObject {
      private static EnumCache enums = new EnumCache();

      ThriftEnum(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data) {
         super(parent, fieldId, data);
      }

      protected void toPrettyString(StringBuilder sb, int level) {
         this.append(sb, "%senum %s;\n", new Object[]{this.getIndent(level), this.getName()});
      }
   }

   public abstract static class ThriftContainer extends ThriftObject {
      public ThriftContainer(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data) {
         super(parent, fieldId, data);
      }

      public abstract boolean hasUnion();
   }

   public static class ThriftList extends ThriftContainer {
      public final ThriftObject elementData;

      ThriftList(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data, List fields) {
         super(parent, fieldId, data);
         this.elementData = ThriftMetadata.ThriftObject.Factory.createNew(this, ThriftMetadata.FieldTypeEnum.LIST_ELEMENT, new FieldMetaData(this.getSubElementName(fieldId), (byte)1, ((ListMetaData)data.valueMetaData).elemMetaData), fields);
      }

      public boolean hasUnion() {
         return this.elementData instanceof ThriftUnion;
      }

      protected void toPrettyString(StringBuilder sb, int level) {
         this.append(sb, "%slist<\n", new Object[]{this.getIndent(level)});
         this.elementData.toPrettyString(sb, level + 1);
         this.append(sb, "%s> %s;\n", new Object[]{this.getIndent(level), this.getName()});
      }
   }

   public static class ThriftSet extends ThriftContainer {
      public final ThriftObject elementData;

      ThriftSet(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data, List fields) {
         super(parent, fieldId, data);
         this.elementData = ThriftMetadata.ThriftObject.Factory.createNew(this, ThriftMetadata.FieldTypeEnum.SET_ELEMENT, new FieldMetaData(this.getSubElementName(fieldId), (byte)1, ((SetMetaData)data.valueMetaData).elemMetaData), fields);
      }

      public boolean hasUnion() {
         return this.elementData instanceof ThriftUnion;
      }

      protected void toPrettyString(StringBuilder sb, int level) {
         this.append(sb, "%sset<\n", new Object[]{this.getIndent(level)});
         this.elementData.toPrettyString(sb, level + 1);
         this.append(sb, "%s> %s;\n", new Object[]{this.getIndent(level), this.getName()});
      }
   }

   public static class ThriftMap extends ThriftContainer {
      public final ThriftObject keyData;
      public final ThriftObject valueData;

      ThriftMap(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data, List fields) {
         super(parent, fieldId, data);
         this.keyData = ThriftMetadata.ThriftObject.Factory.createNew(this, ThriftMetadata.FieldTypeEnum.MAP_KEY, new FieldMetaData(this.getSubElementName(fieldId, "key"), (byte)1, ((MapMetaData)data.valueMetaData).keyMetaData), Collections.emptyList());
         this.valueData = ThriftMetadata.ThriftObject.Factory.createNew(this, ThriftMetadata.FieldTypeEnum.MAP_VALUE, new FieldMetaData(this.getSubElementName(fieldId, "value"), (byte)1, ((MapMetaData)data.valueMetaData).valueMetaData), fields);
      }

      public boolean hasUnion() {
         return this.keyData instanceof ThriftUnion || this.valueData instanceof ThriftUnion;
      }

      protected void toPrettyString(StringBuilder sb, int level) {
         this.append(sb, "%smap<\n", new Object[]{this.getIndent(level)});
         this.append(sb, "%skey = {\n", new Object[]{this.getIndent(level + 1)});
         this.keyData.toPrettyString(sb, level + 2);
         this.append(sb, "%s},\n", new Object[]{this.getIndent(level + 1)});
         this.append(sb, "%svalue = {\n", new Object[]{this.getIndent(level + 1)});
         this.valueData.toPrettyString(sb, level + 2);
         this.append(sb, "%s}\n", new Object[]{this.getIndent(level + 1)});
         this.append(sb, "%s> %s;\n", new Object[]{this.getIndent(level), this.getName()});
      }
   }

   public abstract static class ThriftStructBase extends ThriftObject {
      public ThriftStructBase(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data) {
         super(parent, fieldId, data);
      }

      public Class getStructClass() {
         return getStructClass(this.data);
      }

      public static Class getStructClass(FieldMetaData data) {
         return ((StructMetaData)data.valueMetaData).structClass;
      }

      public boolean isUnion() {
         return isUnion(this.data);
      }

      public static boolean isUnion(FieldMetaData data) {
         return TUnion.class.isAssignableFrom(getStructClass(data));
      }

      public static ThriftStructBase create(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data, Iterable fieldsData) {
         return (ThriftStructBase)(isUnion(data) ? new ThriftUnion(parent, fieldId, data, fieldsData) : new ThriftStruct(parent, fieldId, data, fieldsData));
      }
   }

   public static class ThriftUnion extends ThriftStructBase {
      public ThriftUnion(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data, Iterable fieldsData) {
         super(parent, fieldId, data);
      }

      protected void toPrettyString(StringBuilder sb, int level) {
         String indent = this.getIndent(level);
         String indent2 = this.getIndent(level + 1);
         this.append(sb, "%sunion %s {\n", new Object[]{indent, this.getName()});
         this.append(sb, "%s// unions not adequately supported at present.\n", new Object[]{indent2});
         this.append(sb, "%s}\n", new Object[]{indent});
      }
   }

   public static class ThriftStruct extends ThriftStructBase {
      public final Map fields;

      ThriftStruct(ThriftObject parent, TFieldIdEnum fieldId, FieldMetaData data, Iterable fieldsData) {
         super(parent, fieldId, data);
         Class<U> clasz = getStructClass(data);
         this.fields = getFields(this, clasz, fieldsData);
      }

      public TBase createNewStruct() {
         T instance = (T)null;

         try {
            Class<T> structClass = getStructClass(this.data);
            instance = (T)((TBase)structClass.newInstance());
            return instance;
         } catch (InstantiationException e) {
            throw new RuntimeException(e);
         } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
         }
      }

      public static ThriftStruct of(Class clasz) {
         return fromFields(clasz, Collections.emptyList());
      }

      public static ThriftStruct fromFieldNames(Class clasz, Collection fieldNames) {
         return fromFields(clasz, ThriftField.fromNames(fieldNames));
      }

      public static ThriftStruct fromFields(Class clasz, Iterable fields) {
         Validate.checkNotNull(clasz, "clasz");
         Validate.checkNotNull(fields, "fields");
         return new ThriftStruct((ThriftObject)null, ThriftMetadata.FieldTypeEnum.ROOT, new FieldMetaData(ThriftMetadata.FieldTypeEnum.ROOT.getFieldName(), (byte)1, new StructMetaData((byte)12, clasz)), fields);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         this.toPrettyString(sb, 0);
         return sb.toString();
      }

      protected void toPrettyString(StringBuilder sb, int level) {
         String indent = this.getIndent(level);
         String indent2 = this.getIndent(level + 1);
         this.append(sb, "%sstruct %s {\n", new Object[]{indent, this.getName()});
         if (this.fields.size() == 0) {
            this.append(sb, "%s*;", new Object[]{indent2});
         } else {
            List<Integer> ids = new ArrayList(this.fields.keySet());
            Collections.sort(ids);

            for(Integer id : ids) {
               ((ThriftObject)this.fields.get(id)).toPrettyString(sb, level + 1);
            }
         }

         this.append(sb, "%s}\n", new Object[]{indent});
      }

      private static Map getFields(ThriftStruct parent, Class clasz, Iterable fieldsData) {
         Map<? extends TFieldIdEnum, FieldMetaData> fieldsMetaData = FieldMetaData.getStructMetaDataMap(clasz);
         Map<Integer, ThriftObject> fields = new HashMap();
         boolean getAllFields = !fieldsData.iterator().hasNext();
         if (getAllFields) {
            for(Map.Entry entry : fieldsMetaData.entrySet()) {
               TFieldIdEnum fieldId = (TFieldIdEnum)entry.getKey();
               FieldMetaData fieldMetaData = (FieldMetaData)entry.getValue();
               ThriftObject field = ThriftMetadata.ThriftObject.Factory.createNew(parent, fieldId, fieldMetaData, Collections.emptyList());
               fields.put(Integer.valueOf(fieldId.getThriftFieldId()), field);
            }
         } else {
            for(ThriftField fieldData : fieldsData) {
               String fieldName = fieldData.name;
               FieldMetaData fieldMetaData = findFieldMetaData(fieldsMetaData, fieldName);
               TFieldIdEnum fieldId = findFieldId(fieldsMetaData, fieldName);
               ThriftObject field = ThriftMetadata.ThriftObject.Factory.createNew(parent, fieldId, fieldMetaData, fieldData.fields);
               fields.put(Integer.valueOf(fieldId.getThriftFieldId()), field);
            }
         }

         return fields;
      }

      private static FieldMetaData findFieldMetaData(Map fieldsMetaData, String fieldName) {
         for(FieldMetaData fieldData : fieldsMetaData.values()) {
            if (fieldData.fieldName.equals(fieldName)) {
               return fieldData;
            }
         }

         throw ThriftMetadata.fieldNotFoundException(fieldName);
      }

      private static TFieldIdEnum findFieldId(Map fieldsMetaData, String fieldName) {
         for(TFieldIdEnum fieldId : fieldsMetaData.keySet()) {
            if (fieldId.getFieldName().equals(fieldName)) {
               return fieldId;
            }
         }

         throw ThriftMetadata.fieldNotFoundException(fieldName);
      }
   }
}
