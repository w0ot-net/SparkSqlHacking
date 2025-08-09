package org.apache.avro.reflect;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Conversion;
import org.apache.avro.JsonProperties;
import org.apache.avro.LogicalType;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.SchemaNormalization;
import org.apache.avro.generic.GenericContainer;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericFixed;
import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.io.BinaryData;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.FixedSize;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.ClassUtils;
import org.apache.avro.util.MapUtil;

public class ReflectData extends SpecificData {
   private static final String STRING_OUTER_PARENT_REFERENCE = "this$0";
   private static final ReflectData INSTANCE = new ReflectData();
   private boolean defaultGenerated = false;
   private final Map defaultValues = new WeakHashMap();
   static final ClassValue ACCESSOR_CACHE = new ClassValue() {
      protected ClassAccessorData computeValue(Class c) {
         return !IndexedRecord.class.isAssignableFrom(c) ? new ClassAccessorData(c) : null;
      }
   };
   private static final Map CLASS_CACHE = new ConcurrentHashMap();
   private static final Class BYTES_CLASS = byte[].class;
   private static final IdentityHashMap ARRAY_CLASSES = new IdentityHashMap();
   static final String NS_MAP_ARRAY_RECORD = "org.apache.avro.reflect.Pair";
   static final String NS_MAP_KEY = "key";
   static final int NS_MAP_KEY_INDEX = 0;
   static final String NS_MAP_VALUE = "value";
   static final int NS_MAP_VALUE_INDEX = 1;
   private static final Schema THROWABLE_MESSAGE;
   private static final ConcurrentMap FIELDS_CACHE;

   public boolean useCustomCoders() {
      return false;
   }

   public ReflectData() {
   }

   public ReflectData(ClassLoader classLoader) {
      super(classLoader);
   }

   public static ReflectData get() {
      return INSTANCE;
   }

   public ReflectData addStringable(Class c) {
      this.stringableClasses.add(c);
      return this;
   }

   public ReflectData setDefaultsGenerated(boolean enabled) {
      this.defaultGenerated = enabled;
      return this;
   }

   public ReflectData setDefaultGeneratedValue(Type type, Object value) {
      this.defaultValues.put(type, value);
      this.setDefaultsGenerated(true);
      return this;
   }

   protected Object getOrCreateDefaultValue(Type type, Field field) {
      Object defaultValue = null;
      field.setAccessible(true);

      try {
         Object typeValue = this.getOrCreateDefaultValue(type);
         if (typeValue != null) {
            defaultValue = field.get(typeValue);
         }
      } catch (Exception var5) {
      }

      return defaultValue;
   }

   protected Object getOrCreateDefaultValue(Type type) {
      return this.defaultValues.computeIfAbsent(type, (ignored) -> {
         try {
            Constructor constructor = ((Class)type).getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
         } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassCastException var3) {
            return null;
         }
      });
   }

   public DatumReader createDatumReader(Schema schema) {
      return new ReflectDatumReader(schema, schema, this);
   }

   public DatumReader createDatumReader(Schema writer, Schema reader) {
      return new ReflectDatumReader(writer, reader, this);
   }

   public DatumWriter createDatumWriter(Schema schema) {
      return new ReflectDatumWriter(schema, this);
   }

   public void setField(Object record, String name, int position, Object value) {
      this.setField(record, name, position, value, (Object)null);
   }

   protected void setField(Object record, String name, int position, Object value, Object state) {
      if (record instanceof IndexedRecord) {
         super.setField(record, name, position, value);
      } else {
         try {
            this.getAccessorForField(record, name, position, state).set(record, value);
         } catch (IOException | IllegalAccessException e) {
            throw new AvroRuntimeException(e);
         }
      }
   }

   public Object getField(Object record, String name, int position) {
      return this.getField(record, name, position, (Object)null);
   }

   protected Object getField(Object record, String name, int pos, Object state) {
      if (record instanceof IndexedRecord) {
         return super.getField(record, name, pos);
      } else {
         try {
            return this.getAccessorForField(record, name, pos, state).get(record);
         } catch (IllegalAccessException e) {
            throw new AvroRuntimeException(e);
         }
      }
   }

   private FieldAccessor getAccessorForField(Object record, String name, int pos, Object optionalState) {
      return optionalState != null ? ((FieldAccessor[])optionalState)[pos] : this.getFieldAccessor(record.getClass(), name);
   }

   protected boolean isRecord(Object datum) {
      if (datum == null) {
         return false;
      } else if (super.isRecord(datum)) {
         return true;
      } else if (datum instanceof Collection) {
         return false;
      } else if (datum instanceof Map) {
         return false;
      } else if (datum instanceof GenericFixed) {
         return false;
      } else {
         return this.getSchema(datum.getClass()).getType() == Schema.Type.RECORD;
      }
   }

   protected boolean isArray(Object datum) {
      if (datum == null) {
         return false;
      } else {
         Class c = datum.getClass();
         return datum instanceof Collection || c.isArray() && c.getComponentType() != Byte.TYPE || this.isNonStringMap(datum);
      }
   }

   protected Collection getArrayAsCollection(Object datum) {
      return (Collection)(datum instanceof Map ? ((Map)datum).entrySet() : (Collection)datum);
   }

   protected boolean isBytes(Object datum) {
      if (datum == null) {
         return false;
      } else if (super.isBytes(datum)) {
         return true;
      } else {
         Class c = datum.getClass();
         return c.isArray() && c.getComponentType() == Byte.TYPE;
      }
   }

   protected Schema getRecordSchema(Object record) {
      return record instanceof GenericContainer ? super.getRecordSchema(record) : this.getSchema(record.getClass());
   }

   public boolean validate(Schema schema, Object datum) {
      switch (schema.getType()) {
         case ARRAY:
            if (!datum.getClass().isArray()) {
               return super.validate(schema, datum);
            } else {
               int length = java.lang.reflect.Array.getLength(datum);

               for(int i = 0; i < length; ++i) {
                  if (!this.validate(schema.getElementType(), java.lang.reflect.Array.get(datum, i))) {
                     return false;
                  }
               }

               return true;
            }
         default:
            return super.validate(schema, datum);
      }
   }

   private ClassAccessorData getClassAccessorData(Class c) {
      return (ClassAccessorData)ACCESSOR_CACHE.get(c);
   }

   private FieldAccessor[] getFieldAccessors(Class c, Schema s) {
      ClassAccessorData data = this.getClassAccessorData(c);
      return data != null ? data.getAccessorsFor(s) : null;
   }

   private FieldAccessor getFieldAccessor(Class c, String fieldName) {
      ClassAccessorData data = this.getClassAccessorData(c);
      return data != null ? data.getAccessorFor(fieldName) : null;
   }

   static Class getClassProp(Schema schema, String prop) {
      String name = schema.getProp(prop);
      if (name == null) {
         return null;
      } else {
         Class c = (Class)CLASS_CACHE.get(name);
         if (c != null) {
            return c;
         } else {
            try {
               c = ClassUtils.forName(name);
               CLASS_CACHE.put(name, c);
               return c;
            } catch (ClassNotFoundException e) {
               throw new AvroRuntimeException(e);
            }
         }
      }
   }

   protected boolean isMap(Object datum) {
      return datum instanceof Map && !this.isNonStringMap(datum);
   }

   private boolean isNonStringMap(Object datum) {
      if (datum instanceof Map) {
         Map m = (Map)datum;
         if (m.size() > 0) {
            Class keyClass = m.keySet().iterator().next().getClass();
            return !this.isStringable(keyClass) && !this.isStringType(keyClass);
         }
      }

      return false;
   }

   public Class getClass(Schema schema) {
      Conversion<?> conversion = this.getConversionFor(schema.getLogicalType());
      if (conversion != null) {
         return conversion.getConvertedType();
      } else {
         switch (schema.getType()) {
            case ARRAY:
               Class collectionClass = getClassProp(schema, "java-class");
               if (collectionClass != null) {
                  return collectionClass;
               } else {
                  Class elementClass = this.getClass(schema.getElementType());
                  if (elementClass.isPrimitive()) {
                     return (Class)ARRAY_CLASSES.get(elementClass);
                  }

                  return java.lang.reflect.Array.newInstance(elementClass, 0).getClass();
               }
            case STRING:
               Class stringClass = getClassProp(schema, "java-class");
               if (stringClass != null) {
                  return stringClass;
               }

               return String.class;
            case BYTES:
               return BYTES_CLASS;
            case INT:
               String intClass = schema.getProp("java-class");
               if (Byte.class.getName().equals(intClass)) {
                  return Byte.TYPE;
               } else if (Short.class.getName().equals(intClass)) {
                  return Short.TYPE;
               } else if (Character.class.getName().equals(intClass)) {
                  return Character.TYPE;
               }
            default:
               return super.getClass(schema);
         }
      }
   }

   Schema createNonStringMapSchema(Type keyType, Type valueType, Map names) {
      Schema keySchema = this.createSchema(keyType, names);
      Schema valueSchema = this.createSchema(valueType, names);
      Schema.Field keyField = new Schema.Field("key", keySchema, (String)null, (Object)null);
      Schema.Field valueField = new Schema.Field("value", valueSchema, (String)null, (Object)null);
      String name = this.getNameForNonStringMapRecord(keyType, valueType, keySchema, valueSchema);
      Schema elementSchema = Schema.createRecord(name, (String)null, (String)null, false);
      elementSchema.setFields(Arrays.asList(keyField, valueField));
      Schema arraySchema = Schema.createArray(elementSchema);
      return arraySchema;
   }

   private String getNameForNonStringMapRecord(Type keyType, Type valueType, Schema keySchema, Schema valueSchema) {
      if (keyType instanceof Class && valueType instanceof Class) {
         Class keyClass = (Class)keyType;
         Class valueClass = (Class)valueType;
         Package pkg1 = keyClass.getPackage();
         Package pkg2 = valueClass.getPackage();
         if (pkg1 != null && pkg1.getName().startsWith("java") && pkg2 != null && pkg2.getName().startsWith("java")) {
            String var12 = this.simpleName(keyClass);
            return "org.apache.avro.reflect.Pair" + var12 + this.simpleName(valueClass);
         }
      }

      String var10000 = keySchema.getFullName();
      String name = var10000 + valueSchema.getFullName();
      long fingerprint = SchemaNormalization.fingerprint64(name.getBytes(StandardCharsets.UTF_8));
      if (fingerprint < 0L) {
         fingerprint = -fingerprint;
      }

      String fpString = Long.toString(fingerprint, 16);
      return "org.apache.avro.reflect.Pair" + fpString;
   }

   static boolean isNonStringMapSchema(Schema s) {
      if (s != null && s.getType() == Schema.Type.ARRAY) {
         Class c = getClassProp(s, "java-class");
         return c != null && Map.class.isAssignableFrom(c);
      } else {
         return false;
      }
   }

   protected Object createSchemaDefaultValue(Type type, Field field, Schema fieldSchema) {
      if (this.defaultGenerated) {
         Object defaultValue = this.getOrCreateDefaultValue(type, field);
         if (defaultValue != null) {
            return this.deepCopy(fieldSchema, defaultValue);
         }
      }

      AvroDefault defaultAnnotation = (AvroDefault)field.getAnnotation(AvroDefault.class);
      Object defaultValue = defaultAnnotation == null ? null : Schema.parseJsonToObject(defaultAnnotation.value());
      if (defaultValue == null && fieldSchema.isNullable()) {
         defaultValue = JsonProperties.NULL_VALUE;
      }

      return defaultValue;
   }

   protected Schema createSchema(Type type, Map names) {
      if (type instanceof GenericArrayType) {
         Type component = ((GenericArrayType)type).getGenericComponentType();
         if (component == Byte.TYPE) {
            return Schema.create(Schema.Type.BYTES);
         } else {
            Schema result = Schema.createArray(this.createSchema(component, names));
            this.setElement(result, component);
            return result;
         }
      } else {
         if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType)type;
            Class raw = (Class)ptype.getRawType();
            Type[] params = ptype.getActualTypeArguments();
            if (Map.class.isAssignableFrom(raw)) {
               Class key = (Class)params[0];
               if (this.isStringable(key)) {
                  Schema schema = Schema.createMap(this.createSchema(params[1], names));
                  schema.addProp("java-key-class", key.getName());
                  return schema;
               }

               if (key != String.class) {
                  Schema schema = this.createNonStringMapSchema(params[0], params[1], names);
                  schema.addProp("java-class", raw.getName());
                  return schema;
               }
            } else if (Collection.class.isAssignableFrom(raw)) {
               if (params.length != 1) {
                  throw new AvroTypeException("No array type specified.");
               }

               Schema schema = Schema.createArray(this.createSchema(params[0], names));
               schema.addProp("java-class", raw.getName());
               return schema;
            }
         } else {
            if (type == Byte.class || type == Byte.TYPE) {
               Schema result = Schema.create(Schema.Type.INT);
               result.addProp("java-class", Byte.class.getName());
               return result;
            }

            if (type == Short.class || type == Short.TYPE) {
               Schema result = Schema.create(Schema.Type.INT);
               result.addProp("java-class", Short.class.getName());
               return result;
            }

            if (type == Character.class || type == Character.TYPE) {
               Schema result = Schema.create(Schema.Type.INT);
               result.addProp("java-class", Character.class.getName());
               return result;
            }

            if (type instanceof Class) {
               Class<?> c;
               for(c = (Class)type; c.isAnonymousClass(); c = c.getSuperclass()) {
               }

               if (!c.isPrimitive() && c != Void.class && c != Boolean.class && c != Integer.class && c != Long.class && c != Float.class && c != Double.class && c != Byte.class && c != Short.class && c != Character.class) {
                  if (c.isArray()) {
                     Class component = c.getComponentType();
                     if (component == Byte.TYPE) {
                        Schema result = Schema.create(Schema.Type.BYTES);
                        result.addProp("java-class", c.getName());
                        return result;
                     }

                     Schema result = Schema.createArray(this.createSchema(component, names));
                     result.addProp("java-class", c.getName());
                     this.setElement(result, component);
                     return result;
                  }

                  AvroSchema explicit = (AvroSchema)c.getAnnotation(AvroSchema.class);
                  if (explicit != null) {
                     return (new Schema.Parser()).parse(explicit.value());
                  }

                  if (CharSequence.class.isAssignableFrom(c)) {
                     return Schema.create(Schema.Type.STRING);
                  }

                  if (ByteBuffer.class.isAssignableFrom(c)) {
                     return Schema.create(Schema.Type.BYTES);
                  }

                  if (Collection.class.isAssignableFrom(c)) {
                     throw new AvroRuntimeException("Can't find element type of Collection");
                  }

                  Conversion<?> conversion = this.getConversionByClass(c);
                  if (conversion != null) {
                     return conversion.getRecommendedSchema();
                  }

                  String fullName = c.getName();
                  Schema schema = (Schema)names.get(fullName);
                  if (schema == null) {
                     AvroDoc annotatedDoc = (AvroDoc)c.getAnnotation(AvroDoc.class);
                     String doc = annotatedDoc != null ? annotatedDoc.value() : null;
                     String name = c.getSimpleName();
                     String space = c.getPackage() == null ? "" : c.getPackage().getName();
                     if (c.getEnclosingClass() != null) {
                        space = c.getEnclosingClass().getName().replace('$', '.');
                     }

                     Union union = (Union)c.getAnnotation(Union.class);
                     if (union != null) {
                        return this.getAnnotatedUnion(union, names);
                     }

                     if (this.isStringable(c)) {
                        Schema result = Schema.create(Schema.Type.STRING);
                        result.addProp("java-class", c.getName());
                        return result;
                     }

                     if (c.isEnum()) {
                        List<String> symbols = new ArrayList();
                        Enum[] constants = (Enum[])c.getEnumConstants();

                        for(Enum constant : constants) {
                           symbols.add(constant.name());
                        }

                        schema = Schema.createEnum(name, doc, space, symbols);
                        this.consumeAvroAliasAnnotation(c, schema);
                     } else if (GenericFixed.class.isAssignableFrom(c)) {
                        int size = ((FixedSize)c.getAnnotation(FixedSize.class)).value();
                        schema = Schema.createFixed(name, doc, space, size);
                        this.consumeAvroAliasAnnotation(c, schema);
                     } else {
                        if (IndexedRecord.class.isAssignableFrom(c)) {
                           return super.createSchema(type, names);
                        }

                        List<Schema.Field> fields = new ArrayList();
                        boolean error = Throwable.class.isAssignableFrom(c);
                        schema = Schema.createRecord(name, doc, space, error);
                        this.consumeAvroAliasAnnotation(c, schema);
                        names.put(c.getName(), schema);

                        for(Field field : getCachedFields(c)) {
                           if ((field.getModifiers() & 136) == 0 && !field.isAnnotationPresent(AvroIgnore.class)) {
                              Schema fieldSchema = this.createFieldSchema(field, names);
                              annotatedDoc = (AvroDoc)field.getAnnotation(AvroDoc.class);
                              doc = annotatedDoc != null ? annotatedDoc.value() : null;
                              Object defaultValue = this.createSchemaDefaultValue(type, field, fieldSchema);
                              AvroName annotatedName = (AvroName)field.getAnnotation(AvroName.class);
                              String fieldName = annotatedName != null ? annotatedName.value() : field.getName();
                              if ("this$0".equals(fieldName)) {
                                 throw new AvroTypeException("Class " + fullName + " must be a static inner class");
                              }

                              Schema.Field recordField = new Schema.Field(fieldName, fieldSchema, doc, defaultValue);
                              AvroMeta[] metadata = (AvroMeta[])field.getAnnotationsByType(AvroMeta.class);

                              for(AvroMeta meta : metadata) {
                                 if (recordField.propsContainsKey(meta.key())) {
                                    throw new AvroTypeException("Duplicate field prop key: " + meta.key());
                                 }

                                 recordField.addProp(meta.key(), meta.value());
                              }

                              for(Schema.Field f : fields) {
                                 if (f.name().equals(fieldName)) {
                                    throw new AvroTypeException("double field entry: " + fieldName);
                                 }
                              }

                              this.consumeFieldAlias(field, recordField);
                              fields.add(recordField);
                           }
                        }

                        if (error) {
                           fields.add(new Schema.Field("detailMessage", THROWABLE_MESSAGE, (String)null, (Object)null));
                        }

                        schema.setFields(fields);
                        AvroMeta[] metadata = (AvroMeta[])c.getAnnotationsByType(AvroMeta.class);

                        for(AvroMeta meta : metadata) {
                           if (schema.propsContainsKey(meta.key())) {
                              throw new AvroTypeException("Duplicate type prop key: " + meta.key());
                           }

                           schema.addProp(meta.key(), meta.value());
                        }
                     }

                     names.put(fullName, schema);
                  }

                  return schema;
               }

               return super.createSchema(type, names);
            }
         }

         return super.createSchema(type, names);
      }
   }

   protected boolean isStringable(Class c) {
      return c.isAnnotationPresent(Stringable.class) || super.isStringable(c);
   }

   private String simpleName(Class c) {
      String simpleName = null;
      if (c != null) {
         while(c.isAnonymousClass()) {
            c = c.getSuperclass();
         }

         simpleName = c.getSimpleName();
      }

      return simpleName;
   }

   private void setElement(Schema schema, Type element) {
      if (element instanceof Class) {
         Class<?> c = (Class)element;
         Union union = (Union)c.getAnnotation(Union.class);
         if (union != null) {
            schema.addProp("java-element-class", c.getName());
         }

      }
   }

   private Schema getAnnotatedUnion(Union union, Map names) {
      List<Schema> branches = new ArrayList();

      for(Class branch : union.value()) {
         branches.add(this.createSchema(branch, names));
      }

      return Schema.createUnion(branches);
   }

   public static Schema makeNullable(Schema schema) {
      if (schema.getType() == Schema.Type.UNION) {
         for(Schema subType : schema.getTypes()) {
            if (subType.getType() == Schema.Type.NULL) {
               return schema;
            }
         }

         List<Schema> withNull = new ArrayList();
         withNull.add(Schema.create(Schema.Type.NULL));
         withNull.addAll(schema.getTypes());
         return Schema.createUnion(withNull);
      } else {
         return Schema.createUnion(Arrays.asList(Schema.create(Schema.Type.NULL), schema));
      }
   }

   private static Field[] getCachedFields(Class recordClass) {
      return (Field[])MapUtil.computeIfAbsent(FIELDS_CACHE, recordClass, (rc) -> getFields(rc, true));
   }

   private static Field[] getFields(Class recordClass, boolean excludeJava) {
      Map<String, Field> fields = new LinkedHashMap();
      Class<?> c = recordClass;

      while(!excludeJava || c.getPackage() == null || !c.getPackage().getName().startsWith("java.")) {
         Field[] declaredFields = c.getDeclaredFields();
         Arrays.sort(declaredFields, Comparator.comparing(Field::getName));

         for(Field field : declaredFields) {
            if ((field.getModifiers() & 136) == 0 && fields.put(field.getName(), field) != null) {
               String var10002 = String.valueOf(c);
               throw new AvroTypeException(var10002 + " contains two fields named: " + String.valueOf(field));
            }
         }

         c = c.getSuperclass();
         if (c == null) {
            break;
         }
      }

      Field[] fieldsList = (Field[])fields.values().toArray(new Field[0]);
      return fieldsList;
   }

   protected Schema createFieldSchema(Field field, Map names) {
      AvroEncode enc = (AvroEncode)field.getAnnotation(AvroEncode.class);
      if (enc != null) {
         try {
            return ((CustomEncoding)enc.using().getDeclaredConstructor().newInstance()).getSchema();
         } catch (Exception var7) {
            throw new AvroRuntimeException("Could not create schema from custom serializer for " + field.getName());
         }
      } else {
         AvroSchema explicit = (AvroSchema)field.getAnnotation(AvroSchema.class);
         if (explicit != null) {
            return (new Schema.Parser()).parse(explicit.value());
         } else {
            Union union = (Union)field.getAnnotation(Union.class);
            if (union != null) {
               return this.getAnnotatedUnion(union, names);
            } else {
               Schema schema = this.createSchema(field.getGenericType(), names);
               if (field.isAnnotationPresent(Stringable.class)) {
                  schema = Schema.create(Schema.Type.STRING);
               }

               if (field.isAnnotationPresent(Nullable.class)) {
                  schema = makeNullable(schema);
               }

               return schema;
            }
         }
      }
   }

   public Protocol getProtocol(Class iface) {
      Protocol protocol = new Protocol(this.simpleName(iface), iface.getPackage() == null ? "" : iface.getPackage().getName());
      Map<String, Schema> names = new LinkedHashMap();
      Map<String, Protocol.Message> messages = protocol.getMessages();
      Map<TypeVariable<?>, Type> genericTypeVariableMap = ReflectionUtil.resolveTypeVariables(iface);

      for(Method method : iface.getMethods()) {
         if ((method.getModifiers() & 8) == 0) {
            String name = method.getName();
            if (messages.containsKey(name)) {
               throw new AvroTypeException("Two methods with same name: " + name);
            }

            messages.put(name, this.getMessage(method, protocol, names, genericTypeVariableMap));
         }
      }

      List<Schema> types = new ArrayList(names.values());
      Collections.reverse(types);
      protocol.setTypes(types);
      return protocol;
   }

   private Protocol.Message getMessage(Method method, Protocol protocol, Map names, Map genericTypeMap) {
      List<Schema.Field> fields = new ArrayList();

      for(Parameter parameter : method.getParameters()) {
         Schema paramSchema = this.getSchema((Type)genericTypeMap.getOrDefault(parameter.getParameterizedType(), parameter.getType()), names);

         for(Annotation annotation : parameter.getAnnotations()) {
            if (annotation instanceof AvroSchema) {
               paramSchema = (new Schema.Parser()).parse(((AvroSchema)annotation).value());
            } else if (annotation instanceof Union) {
               paramSchema = this.getAnnotatedUnion((Union)annotation, names);
            } else if (annotation instanceof Nullable) {
               paramSchema = makeNullable(paramSchema);
            }
         }

         fields.add(new Schema.Field(unmangle(parameter.getName()), paramSchema, (String)null, (Object)null));
      }

      Schema request = Schema.createRecord(fields);
      Type genericReturnType = method.getGenericReturnType();
      Type returnType = (Type)genericTypeMap.getOrDefault(genericReturnType, genericReturnType);
      Union union = (Union)method.getAnnotation(Union.class);
      Schema response = union == null ? this.getSchema(returnType, names) : this.getAnnotatedUnion(union, names);
      if (method.isAnnotationPresent(Nullable.class)) {
         response = makeNullable(response);
      }

      AvroSchema explicit = (AvroSchema)method.getAnnotation(AvroSchema.class);
      if (explicit != null) {
         response = (new Schema.Parser()).parse(explicit.value());
      }

      List<Schema> errs = new ArrayList();
      errs.add(Protocol.SYSTEM_ERROR);

      for(Type err : method.getGenericExceptionTypes()) {
         errs.add(this.getSchema(err, names));
      }

      Schema errors = Schema.createUnion(errs);
      return protocol.createMessage(method.getName(), (String)null, (Map)Collections.emptyMap(), request, response, errors);
   }

   private Schema getSchema(Type type, Map names) {
      try {
         return this.createSchema(type, names);
      } catch (AvroTypeException e) {
         throw new AvroTypeException("Error getting schema for " + String.valueOf(type) + ": " + e.getMessage(), e);
      }
   }

   protected int compare(Object o1, Object o2, Schema s, boolean equals) {
      switch (s.getType()) {
         case ARRAY:
            if (o1.getClass().isArray()) {
               Schema elementType = s.getElementType();
               int l1 = java.lang.reflect.Array.getLength(o1);
               int l2 = java.lang.reflect.Array.getLength(o2);
               int l = Math.min(l1, l2);

               for(int i = 0; i < l; ++i) {
                  int compare = this.compare(java.lang.reflect.Array.get(o1, i), java.lang.reflect.Array.get(o2, i), elementType, equals);
                  if (compare != 0) {
                     return compare;
                  }
               }

               return Integer.compare(l1, l2);
            }
            break;
         case BYTES:
            if (o1.getClass().isArray()) {
               byte[] b1 = (byte[])o1;
               byte[] b2 = (byte[])o2;
               return BinaryData.compareBytes(b1, 0, b1.length, b2, 0, b2.length);
            }
      }

      return super.compare(o1, o2, s, equals);
   }

   protected Object getRecordState(Object record, Schema schema) {
      return this.getFieldAccessors(record.getClass(), schema);
   }

   private void consumeAvroAliasAnnotation(Class c, Schema schema) {
      AvroAlias[] aliases = (AvroAlias[])c.getAnnotationsByType(AvroAlias.class);

      for(AvroAlias alias : aliases) {
         String space = alias.space();
         if ("NOT A VALID NAMESPACE".equals(space)) {
            space = null;
         }

         schema.addAlias(alias.alias(), space);
      }

   }

   private void consumeFieldAlias(Field field, Schema.Field recordField) {
      AvroAlias[] aliases = (AvroAlias[])field.getAnnotationsByType(AvroAlias.class);

      for(AvroAlias alias : aliases) {
         if (!alias.space().equals("NOT A VALID NAMESPACE")) {
            throw new AvroRuntimeException("Namespaces are not allowed on field aliases. Offending field: " + recordField.name());
         }

         recordField.addAlias(alias.alias());
      }

   }

   public Object createFixed(Object old, Schema schema) {
      LogicalType logicalType = schema.getLogicalType();
      if (logicalType != null) {
         Conversion<?> conversion = this.getConversionFor(schema.getLogicalType());
         if (conversion != null) {
            return new GenericData.Fixed(schema);
         }
      }

      return super.createFixed(old, schema);
   }

   public Object newRecord(Object old, Schema schema) {
      LogicalType logicalType = schema.getLogicalType();
      if (logicalType != null) {
         Conversion<?> conversion = this.getConversionFor(schema.getLogicalType());
         if (conversion != null) {
            return new GenericData.Record(schema);
         }
      }

      return super.newRecord(old, schema);
   }

   static {
      ARRAY_CLASSES.put(Byte.TYPE, byte[].class);
      ARRAY_CLASSES.put(Character.TYPE, char[].class);
      ARRAY_CLASSES.put(Short.TYPE, short[].class);
      ARRAY_CLASSES.put(Integer.TYPE, int[].class);
      ARRAY_CLASSES.put(Long.TYPE, long[].class);
      ARRAY_CLASSES.put(Float.TYPE, float[].class);
      ARRAY_CLASSES.put(Double.TYPE, double[].class);
      ARRAY_CLASSES.put(Boolean.TYPE, boolean[].class);
      THROWABLE_MESSAGE = makeNullable(Schema.create(Schema.Type.STRING));
      FIELDS_CACHE = new ConcurrentHashMap();
   }

   public static class AllowNull extends ReflectData {
      private static final AllowNull INSTANCE = new AllowNull();

      public static AllowNull get() {
         return INSTANCE;
      }

      protected Schema createFieldSchema(Field field, Map names) {
         Schema schema = super.createFieldSchema(field, names);
         return field.getType().isPrimitive() ? schema : makeNullable(schema);
      }
   }

   static class ClassAccessorData {
      private final Class clazz;
      private final Map byName = new HashMap();
      volatile Map bySchema = new WeakHashMap();

      private ClassAccessorData(Class c) {
         this.clazz = c;

         for(Field f : ReflectData.getFields(c, false)) {
            if (!f.isAnnotationPresent(AvroIgnore.class)) {
               FieldAccessor accessor = ReflectionUtil.getFieldAccess().getAccessor(f);
               AvroName avroname = (AvroName)f.getAnnotation(AvroName.class);
               this.byName.put(avroname != null ? avroname.value() : f.getName(), accessor);
            }
         }

      }

      private FieldAccessor[] getAccessorsFor(Schema schema) {
         FieldAccessor[] result = (FieldAccessor[])this.bySchema.get(schema);
         if (result == null) {
            result = this.createAccessorsFor(schema);
            Map<Schema, FieldAccessor[]> bySchema = new WeakHashMap(this.bySchema);
            bySchema.put(schema, result);
            this.bySchema = bySchema;
         }

         return result;
      }

      private FieldAccessor[] createAccessorsFor(Schema schema) {
         List<Schema.Field> avroFields = schema.getFields();
         FieldAccessor[] result = new FieldAccessor[avroFields.size()];

         for(Schema.Field avroField : schema.getFields()) {
            result[avroField.pos()] = (FieldAccessor)this.byName.get(avroField.name());
         }

         return result;
      }

      private FieldAccessor getAccessorFor(String fieldName) {
         FieldAccessor result = (FieldAccessor)this.byName.get(fieldName);
         if (result == null) {
            throw new AvroRuntimeException("No field named " + fieldName + " in: " + String.valueOf(this.clazz));
         } else {
            return result;
         }
      }
   }
}
