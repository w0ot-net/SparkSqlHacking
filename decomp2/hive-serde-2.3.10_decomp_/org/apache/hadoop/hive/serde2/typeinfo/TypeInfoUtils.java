package org.apache.hadoop.hive.serde2.typeinfo;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.UnionObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;

public final class TypeInfoUtils {
   public static List numericTypeList = new ArrayList();
   public static EnumMap numericTypes = new EnumMap(PrimitiveObjectInspector.PrimitiveCategory.class);
   static ConcurrentHashMap cachedStandardObjectInspector;
   static ConcurrentHashMap cachedStandardJavaObjectInspector;

   private TypeInfoUtils() {
   }

   private static TypeInfo getExtendedTypeInfoFromJavaType(Type t, Method m) {
      if (t == Object.class) {
         return TypeInfoFactory.unknownTypeInfo;
      } else {
         if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType)t;
            if (List.class == (Class)pt.getRawType() || ArrayList.class == (Class)pt.getRawType()) {
               return TypeInfoFactory.getListTypeInfo(getExtendedTypeInfoFromJavaType(pt.getActualTypeArguments()[0], m));
            }

            if (Map.class == (Class)pt.getRawType() || HashMap.class == (Class)pt.getRawType()) {
               return TypeInfoFactory.getMapTypeInfo(getExtendedTypeInfoFromJavaType(pt.getActualTypeArguments()[0], m), getExtendedTypeInfoFromJavaType(pt.getActualTypeArguments()[1], m));
            }

            t = pt.getRawType();
         }

         if (!(t instanceof Class)) {
            throw new RuntimeException("Hive does not understand type " + t + " from " + m);
         } else {
            Class<?> c = (Class)t;
            if (PrimitiveObjectInspectorUtils.isPrimitiveJavaType(c)) {
               return getTypeInfoFromObjectInspector(PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(PrimitiveObjectInspectorUtils.getTypeEntryFromPrimitiveJavaType(c).primitiveCategory));
            } else if (PrimitiveObjectInspectorUtils.isPrimitiveJavaClass(c)) {
               return getTypeInfoFromObjectInspector(PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(PrimitiveObjectInspectorUtils.getTypeEntryFromPrimitiveJavaClass(c).primitiveCategory));
            } else if (PrimitiveObjectInspectorUtils.isPrimitiveWritableClass(c)) {
               return getTypeInfoFromObjectInspector(PrimitiveObjectInspectorFactory.getPrimitiveWritableObjectInspector(PrimitiveObjectInspectorUtils.getTypeEntryFromPrimitiveWritableClass(c).primitiveCategory));
            } else {
               Field[] fields = ObjectInspectorUtils.getDeclaredNonStaticFields(c);
               ArrayList<String> fieldNames = new ArrayList(fields.length);
               ArrayList<TypeInfo> fieldTypeInfos = new ArrayList(fields.length);

               for(Field field : fields) {
                  fieldNames.add(field.getName());
                  fieldTypeInfos.add(getExtendedTypeInfoFromJavaType(field.getGenericType(), m));
               }

               return TypeInfoFactory.getStructTypeInfo(fieldNames, fieldTypeInfos);
            }
         }
      }
   }

   public static Type getArrayElementType(Type t) {
      if (t instanceof Class && ((Class)t).isArray()) {
         Class<?> arrayClass = (Class)t;
         return arrayClass.getComponentType();
      } else if (t instanceof GenericArrayType) {
         GenericArrayType arrayType = (GenericArrayType)t;
         return arrayType.getGenericComponentType();
      } else {
         return null;
      }
   }

   public static List getParameterTypeInfos(Method m, int size) {
      Type[] methodParameterTypes = m.getGenericParameterTypes();
      Type lastParaElementType = getArrayElementType(methodParameterTypes.length == 0 ? null : methodParameterTypes[methodParameterTypes.length - 1]);
      boolean isVariableLengthArgument = lastParaElementType != null;
      List<TypeInfo> typeInfos = null;
      if (!isVariableLengthArgument) {
         if (size != methodParameterTypes.length) {
            return null;
         }

         typeInfos = new ArrayList(methodParameterTypes.length);

         for(Type methodParameterType : methodParameterTypes) {
            typeInfos.add(getExtendedTypeInfoFromJavaType(methodParameterType, m));
         }
      } else {
         if (size < methodParameterTypes.length - 1) {
            return null;
         }

         typeInfos = new ArrayList(size);

         for(int i = 0; i < methodParameterTypes.length - 1; ++i) {
            typeInfos.add(getExtendedTypeInfoFromJavaType(methodParameterTypes[i], m));
         }

         for(int i = methodParameterTypes.length - 1; i < size; ++i) {
            typeInfos.add(getExtendedTypeInfoFromJavaType(lastParaElementType, m));
         }
      }

      return typeInfos;
   }

   public static boolean hasParameters(String typeName) {
      int idx = typeName.indexOf(40);
      return idx != -1;
   }

   public static String getBaseName(String typeName) {
      int idx = typeName.indexOf(40);
      return idx == -1 ? typeName : typeName.substring(0, idx);
   }

   public static boolean doPrimitiveCategoriesMatch(TypeInfo ti1, TypeInfo ti2) {
      return ti1.getCategory() == ObjectInspector.Category.PRIMITIVE && ti2.getCategory() == ObjectInspector.Category.PRIMITIVE && ((PrimitiveTypeInfo)ti1).getPrimitiveCategory() == ((PrimitiveTypeInfo)ti2).getPrimitiveCategory();
   }

   public static PrimitiveParts parsePrimitiveParts(String typeInfoString) {
      TypeInfoParser parser = new TypeInfoParser(typeInfoString);
      return parser.parsePrimitiveParts();
   }

   public static ObjectInspector getStandardWritableObjectInspectorFromTypeInfo(TypeInfo typeInfo) {
      ObjectInspector result = (ObjectInspector)cachedStandardObjectInspector.get(typeInfo);
      if (result == null) {
         switch (typeInfo.getCategory()) {
            case PRIMITIVE:
               result = PrimitiveObjectInspectorFactory.getPrimitiveWritableObjectInspector((PrimitiveTypeInfo)typeInfo);
               break;
            case LIST:
               ObjectInspector elementObjectInspector = getStandardWritableObjectInspectorFromTypeInfo(((ListTypeInfo)typeInfo).getListElementTypeInfo());
               result = ObjectInspectorFactory.getStandardListObjectInspector(elementObjectInspector);
               break;
            case MAP:
               MapTypeInfo mapTypeInfo = (MapTypeInfo)typeInfo;
               ObjectInspector keyObjectInspector = getStandardWritableObjectInspectorFromTypeInfo(mapTypeInfo.getMapKeyTypeInfo());
               ObjectInspector valueObjectInspector = getStandardWritableObjectInspectorFromTypeInfo(mapTypeInfo.getMapValueTypeInfo());
               result = ObjectInspectorFactory.getStandardMapObjectInspector(keyObjectInspector, valueObjectInspector);
               break;
            case STRUCT:
               StructTypeInfo structTypeInfo = (StructTypeInfo)typeInfo;
               List<String> fieldNames = structTypeInfo.getAllStructFieldNames();
               List<TypeInfo> fieldTypeInfos = structTypeInfo.getAllStructFieldTypeInfos();
               List<ObjectInspector> fieldObjectInspectors = new ArrayList(fieldTypeInfos.size());

               for(int i = 0; i < fieldTypeInfos.size(); ++i) {
                  fieldObjectInspectors.add(getStandardWritableObjectInspectorFromTypeInfo((TypeInfo)fieldTypeInfos.get(i)));
               }

               result = ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldObjectInspectors);
               break;
            case UNION:
               UnionTypeInfo unionTypeInfo = (UnionTypeInfo)typeInfo;
               List<TypeInfo> objectTypeInfos = unionTypeInfo.getAllUnionObjectTypeInfos();
               List<ObjectInspector> fieldObjectInspectors = new ArrayList(objectTypeInfos.size());

               for(int i = 0; i < objectTypeInfos.size(); ++i) {
                  fieldObjectInspectors.add(getStandardWritableObjectInspectorFromTypeInfo((TypeInfo)objectTypeInfos.get(i)));
               }

               result = ObjectInspectorFactory.getStandardUnionObjectInspector(fieldObjectInspectors);
               break;
            default:
               result = null;
         }

         ObjectInspector prev = (ObjectInspector)cachedStandardObjectInspector.putIfAbsent(typeInfo, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static ObjectInspector getStandardJavaObjectInspectorFromTypeInfo(TypeInfo typeInfo) {
      ObjectInspector result = (ObjectInspector)cachedStandardJavaObjectInspector.get(typeInfo);
      if (result == null) {
         switch (typeInfo.getCategory()) {
            case PRIMITIVE:
               result = PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector((PrimitiveTypeInfo)typeInfo);
               break;
            case LIST:
               ObjectInspector elementObjectInspector = getStandardJavaObjectInspectorFromTypeInfo(((ListTypeInfo)typeInfo).getListElementTypeInfo());
               result = ObjectInspectorFactory.getStandardListObjectInspector(elementObjectInspector);
               break;
            case MAP:
               MapTypeInfo mapTypeInfo = (MapTypeInfo)typeInfo;
               ObjectInspector keyObjectInspector = getStandardJavaObjectInspectorFromTypeInfo(mapTypeInfo.getMapKeyTypeInfo());
               ObjectInspector valueObjectInspector = getStandardJavaObjectInspectorFromTypeInfo(mapTypeInfo.getMapValueTypeInfo());
               result = ObjectInspectorFactory.getStandardMapObjectInspector(keyObjectInspector, valueObjectInspector);
               break;
            case STRUCT:
               StructTypeInfo strucTypeInfo = (StructTypeInfo)typeInfo;
               List<String> fieldNames = strucTypeInfo.getAllStructFieldNames();
               List<TypeInfo> fieldTypeInfos = strucTypeInfo.getAllStructFieldTypeInfos();
               List<ObjectInspector> fieldObjectInspectors = new ArrayList(fieldTypeInfos.size());

               for(int i = 0; i < fieldTypeInfos.size(); ++i) {
                  fieldObjectInspectors.add(getStandardJavaObjectInspectorFromTypeInfo((TypeInfo)fieldTypeInfos.get(i)));
               }

               result = ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldObjectInspectors);
               break;
            case UNION:
               UnionTypeInfo unionTypeInfo = (UnionTypeInfo)typeInfo;
               List<TypeInfo> objectTypeInfos = unionTypeInfo.getAllUnionObjectTypeInfos();
               List<ObjectInspector> fieldObjectInspectors = new ArrayList(objectTypeInfos.size());

               for(int i = 0; i < objectTypeInfos.size(); ++i) {
                  fieldObjectInspectors.add(getStandardJavaObjectInspectorFromTypeInfo((TypeInfo)objectTypeInfos.get(i)));
               }

               result = ObjectInspectorFactory.getStandardUnionObjectInspector(fieldObjectInspectors);
               break;
            default:
               result = null;
         }

         ObjectInspector prev = (ObjectInspector)cachedStandardJavaObjectInspector.putIfAbsent(typeInfo, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static TypeInfo getTypeInfoFromObjectInspector(ObjectInspector oi) {
      if (oi == null) {
         return null;
      } else {
         TypeInfo result = null;
         switch (oi.getCategory()) {
            case PRIMITIVE:
               PrimitiveObjectInspector poi = (PrimitiveObjectInspector)oi;
               result = poi.getTypeInfo();
               break;
            case LIST:
               ListObjectInspector loi = (ListObjectInspector)oi;
               result = TypeInfoFactory.getListTypeInfo(getTypeInfoFromObjectInspector(loi.getListElementObjectInspector()));
               break;
            case MAP:
               MapObjectInspector moi = (MapObjectInspector)oi;
               result = TypeInfoFactory.getMapTypeInfo(getTypeInfoFromObjectInspector(moi.getMapKeyObjectInspector()), getTypeInfoFromObjectInspector(moi.getMapValueObjectInspector()));
               break;
            case STRUCT:
               StructObjectInspector soi = (StructObjectInspector)oi;
               List<? extends StructField> fields = soi.getAllStructFieldRefs();
               List<String> fieldNames = new ArrayList(fields.size());
               List<TypeInfo> fieldTypeInfos = new ArrayList(fields.size());

               for(StructField f : fields) {
                  fieldNames.add(f.getFieldName());
                  fieldTypeInfos.add(getTypeInfoFromObjectInspector(f.getFieldObjectInspector()));
               }

               result = TypeInfoFactory.getStructTypeInfo(fieldNames, fieldTypeInfos);
               break;
            case UNION:
               UnionObjectInspector uoi = (UnionObjectInspector)oi;
               List<TypeInfo> objectTypeInfos = new ArrayList();

               for(ObjectInspector eoi : uoi.getObjectInspectors()) {
                  objectTypeInfos.add(getTypeInfoFromObjectInspector(eoi));
               }

               result = TypeInfoFactory.getUnionTypeInfo(objectTypeInfos);
               break;
            default:
               throw new RuntimeException("Unknown ObjectInspector category!");
         }

         return result;
      }
   }

   public static ArrayList typeInfosFromStructObjectInspector(StructObjectInspector structObjectInspector) {
      List<? extends StructField> fields = structObjectInspector.getAllStructFieldRefs();
      ArrayList<TypeInfo> typeInfoList = new ArrayList(fields.size());

      for(StructField field : fields) {
         TypeInfo typeInfo = getTypeInfoFromTypeString(field.getFieldObjectInspector().getTypeName());
         typeInfoList.add(typeInfo);
      }

      return typeInfoList;
   }

   public static ArrayList typeInfosFromTypeNames(List typeNames) {
      ArrayList<TypeInfo> result = new ArrayList(typeNames.size());

      for(int i = 0; i < typeNames.size(); ++i) {
         TypeInfo typeInfo = getTypeInfoFromTypeString((String)typeNames.get(i));
         result.add(typeInfo);
      }

      return result;
   }

   public static ArrayList getTypeInfosFromTypeString(String typeString) {
      TypeInfoParser parser = new TypeInfoParser(typeString);
      return parser.parseTypeInfos();
   }

   public static List getTypeStringsFromTypeInfo(List typeInfos) {
      if (typeInfos == null) {
         return null;
      } else {
         List<String> result = new ArrayList(typeInfos.size());

         for(TypeInfo typeInfo : typeInfos) {
            result.add(typeInfo.toString());
         }

         return result;
      }
   }

   public static TypeInfo getTypeInfoFromTypeString(String typeString) {
      TypeInfoParser parser = new TypeInfoParser(typeString);
      return (TypeInfo)parser.parseTypeInfos().get(0);
   }

   public static boolean isConversionRequiredForComparison(TypeInfo typeA, TypeInfo typeB) {
      if (typeA.equals(typeB)) {
         return false;
      } else {
         return !doPrimitiveCategoriesMatch(typeA, typeB);
      }
   }

   public static int getCharacterLengthForType(PrimitiveTypeInfo typeInfo) {
      switch (typeInfo.getPrimitiveCategory()) {
         case CHAR:
         case VARCHAR:
            BaseCharTypeInfo baseCharTypeInfo = (BaseCharTypeInfo)typeInfo;
            return baseCharTypeInfo.getLength();
         case DECIMAL:
         default:
            return 0;
         case STRING:
            return 65535;
      }
   }

   public static synchronized void registerNumericType(PrimitiveObjectInspector.PrimitiveCategory primitiveCategory, int level) {
      numericTypeList.add(primitiveCategory);
      numericTypes.put(primitiveCategory, level);
   }

   public static boolean implicitConvertible(PrimitiveObjectInspector.PrimitiveCategory from, PrimitiveObjectInspector.PrimitiveCategory to) {
      if (from == to) {
         return true;
      } else {
         PrimitiveObjectInspectorUtils.PrimitiveGrouping fromPg = PrimitiveObjectInspectorUtils.getPrimitiveGrouping(from);
         PrimitiveObjectInspectorUtils.PrimitiveGrouping toPg = PrimitiveObjectInspectorUtils.getPrimitiveGrouping(to);
         if (fromPg == PrimitiveObjectInspectorUtils.PrimitiveGrouping.STRING_GROUP && to == PrimitiveObjectInspector.PrimitiveCategory.DOUBLE) {
            return true;
         } else if (from == PrimitiveObjectInspector.PrimitiveCategory.VOID) {
            return true;
         } else if (fromPg == PrimitiveObjectInspectorUtils.PrimitiveGrouping.DATE_GROUP && toPg == PrimitiveObjectInspectorUtils.PrimitiveGrouping.STRING_GROUP) {
            return true;
         } else if (fromPg == PrimitiveObjectInspectorUtils.PrimitiveGrouping.NUMERIC_GROUP && toPg == PrimitiveObjectInspectorUtils.PrimitiveGrouping.STRING_GROUP) {
            return true;
         } else if (fromPg == PrimitiveObjectInspectorUtils.PrimitiveGrouping.STRING_GROUP && toPg == PrimitiveObjectInspectorUtils.PrimitiveGrouping.STRING_GROUP) {
            return true;
         } else {
            Integer f = (Integer)numericTypes.get(from);
            Integer t = (Integer)numericTypes.get(to);
            if (f != null && t != null) {
               return f <= t;
            } else {
               return false;
            }
         }
      }
   }

   public static boolean implicitConvertible(TypeInfo from, TypeInfo to) {
      if (from.equals(to)) {
         return true;
      } else {
         return from.getCategory() == ObjectInspector.Category.PRIMITIVE && to.getCategory() == ObjectInspector.Category.PRIMITIVE ? implicitConvertible(((PrimitiveTypeInfo)from).getPrimitiveCategory(), ((PrimitiveTypeInfo)to).getPrimitiveCategory()) : false;
      }
   }

   static {
      registerNumericType(PrimitiveObjectInspector.PrimitiveCategory.BYTE, 1);
      registerNumericType(PrimitiveObjectInspector.PrimitiveCategory.SHORT, 2);
      registerNumericType(PrimitiveObjectInspector.PrimitiveCategory.INT, 3);
      registerNumericType(PrimitiveObjectInspector.PrimitiveCategory.LONG, 4);
      registerNumericType(PrimitiveObjectInspector.PrimitiveCategory.DECIMAL, 5);
      registerNumericType(PrimitiveObjectInspector.PrimitiveCategory.FLOAT, 6);
      registerNumericType(PrimitiveObjectInspector.PrimitiveCategory.DOUBLE, 7);
      registerNumericType(PrimitiveObjectInspector.PrimitiveCategory.STRING, 8);
      cachedStandardObjectInspector = new ConcurrentHashMap();
      cachedStandardJavaObjectInspector = new ConcurrentHashMap();
   }

   private static class TypeInfoParser {
      private final String typeInfoString;
      private final ArrayList typeInfoTokens;
      private ArrayList typeInfos;
      private int iToken;

      private static boolean isTypeChar(char c) {
         return Character.isLetterOrDigit(c) || c == '_' || c == '.' || c == ' ' || c == '$';
      }

      private static ArrayList tokenize(String typeInfoString) {
         ArrayList<Token> tokens = new ArrayList(0);
         int begin = 0;

         for(int end = 1; end <= typeInfoString.length(); ++end) {
            if (end == typeInfoString.length() || !isTypeChar(typeInfoString.charAt(end - 1)) || !isTypeChar(typeInfoString.charAt(end))) {
               Token t = new Token();
               t.position = begin;
               t.text = typeInfoString.substring(begin, end);
               t.isType = isTypeChar(typeInfoString.charAt(begin));
               tokens.add(t);
               begin = end;
            }
         }

         return tokens;
      }

      public TypeInfoParser(String typeInfoString) {
         this.typeInfoString = typeInfoString;
         this.typeInfoTokens = tokenize(typeInfoString);
      }

      public ArrayList parseTypeInfos() {
         this.typeInfos = new ArrayList();
         this.iToken = 0;

         while(this.iToken < this.typeInfoTokens.size()) {
            this.typeInfos.add(this.parseType());
            if (this.iToken < this.typeInfoTokens.size()) {
               Token separator = (Token)this.typeInfoTokens.get(this.iToken);
               if (!",".equals(separator.text) && !";".equals(separator.text) && !":".equals(separator.text)) {
                  throw new IllegalArgumentException("Error: ',', ':', or ';' expected at position " + separator.position + " from '" + this.typeInfoString + "' " + this.typeInfoTokens);
               }

               ++this.iToken;
            }
         }

         return this.typeInfos;
      }

      private Token peek() {
         return this.iToken < this.typeInfoTokens.size() ? (Token)this.typeInfoTokens.get(this.iToken) : null;
      }

      private Token expect(String item) {
         return this.expect(item, (String)null);
      }

      private Token expect(String item, String alternative) {
         if (this.iToken >= this.typeInfoTokens.size()) {
            throw new IllegalArgumentException("Error: " + item + " expected at the end of '" + this.typeInfoString + "'");
         } else {
            Token t = (Token)this.typeInfoTokens.get(this.iToken);
            if (item.equals("type")) {
               if (!"array".equals(t.text) && !"map".equals(t.text) && !"struct".equals(t.text) && !"uniontype".equals(t.text) && null == PrimitiveObjectInspectorUtils.getTypeEntryFromTypeName(t.text) && !t.text.equals(alternative)) {
                  throw new IllegalArgumentException("Error: " + item + " expected at the position " + t.position + " of '" + this.typeInfoString + "' but '" + t.text + "' is found.");
               }
            } else if (item.equals("name")) {
               if (!t.isType && !t.text.equals(alternative)) {
                  throw new IllegalArgumentException("Error: " + item + " expected at the position " + t.position + " of '" + this.typeInfoString + "' but '" + t.text + "' is found.");
               }
            } else if (!item.equals(t.text) && !t.text.equals(alternative)) {
               throw new IllegalArgumentException("Error: " + item + " expected at the position " + t.position + " of '" + this.typeInfoString + "' but '" + t.text + "' is found.");
            }

            ++this.iToken;
            return t;
         }
      }

      private String[] parseParams() {
         List<String> params = new LinkedList();
         Token t = this.peek();
         if (t != null && t.text.equals("(")) {
            this.expect("(");

            for(Token var3 = this.peek(); var3 == null || !var3.text.equals(")"); var3 = this.expect(",", ")")) {
               params.add(this.expect("name").text);
            }

            if (params.size() == 0) {
               throw new IllegalArgumentException("type parameters expected for type string " + this.typeInfoString);
            }
         }

         return (String[])params.toArray(new String[params.size()]);
      }

      private TypeInfo parseType() {
         Token t = this.expect("type");
         PrimitiveObjectInspectorUtils.PrimitiveTypeEntry typeEntry = PrimitiveObjectInspectorUtils.getTypeEntryFromTypeName(t.text);
         if (typeEntry != null && typeEntry.primitiveCategory != PrimitiveObjectInspector.PrimitiveCategory.UNKNOWN) {
            String[] params = this.parseParams();
            switch (typeEntry.primitiveCategory) {
               case CHAR:
               case VARCHAR:
                  if (params == null || params.length == 0) {
                     throw new IllegalArgumentException(typeEntry.typeName + " type is specified without length: " + this.typeInfoString);
                  } else {
                     int length = 1;
                     if (params.length == 1) {
                        length = Integer.parseInt(params[0]);
                        if (typeEntry.primitiveCategory == PrimitiveObjectInspector.PrimitiveCategory.VARCHAR) {
                           BaseCharUtils.validateVarcharParameter(length);
                           return TypeInfoFactory.getVarcharTypeInfo(length);
                        }

                        BaseCharUtils.validateCharParameter(length);
                        return TypeInfoFactory.getCharTypeInfo(length);
                     } else if (params.length > 1) {
                        throw new IllegalArgumentException("Type " + typeEntry.typeName + " only takes one parameter, but " + params.length + " is seen");
                     }
                  }
               case DECIMAL:
                  int precision = 10;
                  int scale = 0;
                  if (params != null && params.length != 0) {
                     if (params.length == 2) {
                        precision = Integer.parseInt(params[0]);
                        scale = Integer.parseInt(params[1]);
                        HiveDecimalUtils.validateParameter(precision, scale);
                     } else if (params.length > 2) {
                        throw new IllegalArgumentException("Type decimal only takes two parameter, but " + params.length + " is seen");
                     }
                  }

                  return TypeInfoFactory.getDecimalTypeInfo(precision, scale);
               default:
                  return TypeInfoFactory.getPrimitiveTypeInfo(typeEntry.typeName);
            }
         } else if ("array".equals(t.text)) {
            this.expect("<");
            TypeInfo listElementType = this.parseType();
            this.expect(">");
            return TypeInfoFactory.getListTypeInfo(listElementType);
         } else if ("map".equals(t.text)) {
            this.expect("<");
            TypeInfo mapKeyType = this.parseType();
            this.expect(",");
            TypeInfo mapValueType = this.parseType();
            this.expect(">");
            return TypeInfoFactory.getMapTypeInfo(mapKeyType, mapValueType);
         } else if (!"struct".equals(t.text)) {
            if ("uniontype".equals(t.text)) {
               List<TypeInfo> objectTypeInfos = new ArrayList();
               boolean first = true;

               while(true) {
                  if (first) {
                     this.expect("<");
                     first = false;
                  } else {
                     Token separator = this.expect(">", ",");
                     if (separator.text.equals(">")) {
                        return TypeInfoFactory.getUnionTypeInfo(objectTypeInfos);
                     }
                  }

                  objectTypeInfos.add(this.parseType());
               }
            } else {
               throw new RuntimeException("Internal error parsing position " + t.position + " of '" + this.typeInfoString + "'");
            }
         } else {
            ArrayList<String> fieldNames = new ArrayList();
            ArrayList<TypeInfo> fieldTypeInfos = new ArrayList();
            boolean first = true;

            while(true) {
               if (first) {
                  this.expect("<");
                  first = false;
               } else {
                  Token separator = this.expect(">", ",");
                  if (separator.text.equals(">")) {
                     break;
                  }
               }

               Token name = this.expect("name", ">");
               if (name.text.equals(">")) {
                  break;
               }

               fieldNames.add(name.text);
               this.expect(":");
               fieldTypeInfos.add(this.parseType());
            }

            return TypeInfoFactory.getStructTypeInfo(fieldNames, fieldTypeInfos);
         }
      }

      public PrimitiveParts parsePrimitiveParts() {
         PrimitiveParts parts = new PrimitiveParts();
         Token t = this.expect("type");
         parts.typeName = t.text;
         parts.typeParams = this.parseParams();
         return parts;
      }

      private static class Token {
         public int position;
         public String text;
         public boolean isType;

         private Token() {
         }

         public String toString() {
            return "" + this.position + ":" + this.text;
         }
      }
   }

   public static class PrimitiveParts {
      public String typeName;
      public String[] typeParams;
   }
}
