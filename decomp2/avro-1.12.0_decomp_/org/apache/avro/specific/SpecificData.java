package org.apache.avro.specific;

import java.io.File;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.util.ClassUtils;
import org.apache.avro.util.MapUtil;
import org.apache.avro.util.SchemaUtil;
import org.apache.avro.util.internal.ClassValueCache;

public class SpecificData extends GenericData {
   private static final SpecificData INSTANCE = new SpecificData();
   private static final Class[] NO_ARG = new Class[0];
   private static final Class[] SCHEMA_ARG = new Class[]{Schema.class};
   private static final Function CTOR_CACHE = new ClassValueCache((c) -> {
      boolean useSchema = SchemaConstructable.class.isAssignableFrom(c);

      try {
         Constructor<?> meth = c.getDeclaredConstructor(useSchema ? SCHEMA_ARG : NO_ARG);
         meth.setAccessible(true);
         return meth;
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   });
   private static final Function MODEL_CACHE = new ClassValueCache((c) -> {
      try {
         Field specificDataField = c.getDeclaredField("MODEL$");
         specificDataField.setAccessible(true);
         return (SpecificData)specificDataField.get((Object)null);
      } catch (NoSuchFieldException var3) {
         return get();
      } catch (IllegalAccessException e) {
         throw new AvroRuntimeException("while trying to access field MODEL$ on " + c.getCanonicalName(), e);
      }
   });
   public static final String CLASS_PROP = "java-class";
   public static final String KEY_CLASS_PROP = "java-key-class";
   public static final String ELEMENT_PROP = "java-element-class";
   public static final char RESERVED_WORD_ESCAPE_CHAR = '$';
   public static final Set RESERVED_WORDS = new HashSet(Arrays.asList("_", "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null", "Builder"));
   public static final Set ACCESSOR_MUTATOR_RESERVED_WORDS = new HashSet(Arrays.asList("class", "schema", "classSchema"));
   public static final Set TYPE_IDENTIFIER_RESERVED_WORDS;
   public static final Set ERROR_RESERVED_WORDS;
   protected Set stringableClasses = new HashSet(Arrays.asList(BigDecimal.class, BigInteger.class, URI.class, URL.class, File.class));
   private boolean useCustomCoderFlag = Boolean.parseBoolean(System.getProperty("org.apache.avro.specific.use_custom_coders", "false"));
   private final ConcurrentMap classCache = new ConcurrentHashMap();
   private static final Class NO_CLASS;
   private static final Schema NULL_SCHEMA;
   private final ClassValueCache schemaClassCache = new ClassValueCache((c) -> this.createSchema(c, new HashMap()));
   private final Map schemaTypeCache = Collections.synchronizedMap(new WeakHashMap());

   public SpecificData() {
   }

   public SpecificData(ClassLoader classLoader) {
      super(classLoader);
   }

   public DatumReader createDatumReader(Schema schema) {
      return this.createDatumReader(schema, schema);
   }

   public DatumReader createDatumReader(Schema writer, Schema reader) {
      return new SpecificDatumReader(writer, reader, this);
   }

   public DatumWriter createDatumWriter(Schema schema) {
      return new SpecificDatumWriter(schema, this);
   }

   public static SpecificData get() {
      return INSTANCE;
   }

   public static SpecificData getForSchema(Schema reader) {
      if (reader != null && (reader.getType() == Schema.Type.RECORD || reader.getType() == Schema.Type.UNION)) {
         Class<?> clazz = get().getClass(reader);
         if (clazz != null) {
            return getForClass(clazz);
         }
      }

      return get();
   }

   public static SpecificData getForClass(Class c) {
      return SpecificRecordBase.class.isAssignableFrom(c) ? (SpecificData)MODEL_CACHE.apply(c) : get();
   }

   public boolean useCustomCoders() {
      return this.useCustomCoderFlag;
   }

   public void setCustomCoders(boolean flag) {
      this.useCustomCoderFlag = flag;
   }

   protected boolean isEnum(Object datum) {
      return datum instanceof Enum || super.isEnum(datum);
   }

   public Object createEnum(String symbol, Schema schema) {
      Class c = this.getClass(schema);
      if (c == null) {
         return super.createEnum(symbol, schema);
      } else {
         if (RESERVED_WORDS.contains(symbol)) {
            symbol = symbol + "$";
         }

         return Enum.valueOf(c, symbol);
      }
   }

   protected Schema getEnumSchema(Object datum) {
      return datum instanceof Enum ? this.getSchema(datum.getClass()) : super.getEnumSchema(datum);
   }

   public static String mangleFullyQualified(String fullName) {
      int lastDot = fullName.lastIndexOf(46);
      if (lastDot < 0) {
         return mangleTypeIdentifier(fullName);
      } else {
         String namespace = fullName.substring(0, lastDot);
         String typeName = fullName.substring(lastDot + 1);
         String var10000 = mangle(namespace);
         return var10000 + "." + mangleTypeIdentifier(typeName);
      }
   }

   public static String mangle(String word) {
      return mangle(word, false);
   }

   public static String mangle(String word, boolean isError) {
      return mangle(word, isError ? ERROR_RESERVED_WORDS : RESERVED_WORDS);
   }

   public static String mangleTypeIdentifier(String word) {
      return mangleTypeIdentifier(word, false);
   }

   public static String mangleTypeIdentifier(String word, boolean isError) {
      return mangle(word, isError ? ERROR_RESERVED_WORDS : TYPE_IDENTIFIER_RESERVED_WORDS);
   }

   public static String mangle(String word, Set reservedWords) {
      return mangle(word, reservedWords, false);
   }

   public static String mangleMethod(String word, boolean isError) {
      return mangle(word, isError ? ERROR_RESERVED_WORDS : ACCESSOR_MUTATOR_RESERVED_WORDS, true);
   }

   public static String mangle(String word, Set reservedWords, boolean isMethod) {
      if (isBlank(word)) {
         return word;
      } else if (word.contains(".")) {
         String[] packageWords = word.split("\\.");
         String[] newPackageWords = new String[packageWords.length];

         for(int i = 0; i < packageWords.length; ++i) {
            String oldName = packageWords[i];
            newPackageWords[i] = mangle(oldName, reservedWords, false);
         }

         return String.join(".", newPackageWords);
      } else if (!reservedWords.contains(word)) {
         if (isMethod) {
            char var10001 = Character.toLowerCase(word.charAt(0));
            if (reservedWords.contains(var10001 + (word.length() > 1 ? word.substring(1) : ""))) {
               return word + "$";
            }
         }

         return word;
      } else {
         return word + "$";
      }
   }

   protected static String unmangle(String word) {
      while(word.endsWith("$")) {
         word = word.substring(0, word.length() - 1);
      }

      return word;
   }

   private static boolean isBlank(CharSequence cs) {
      int strLen = cs == null ? 0 : cs.length();
      if (strLen == 0) {
         return true;
      } else {
         for(int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(cs.charAt(i))) {
               return false;
            }
         }

         return true;
      }
   }

   public Class getClass(Schema schema) {
      switch (schema.getType()) {
         case FIXED:
         case RECORD:
         case ENUM:
            String name = schema.getFullName();
            if (name == null) {
               return null;
            }

            Class<?> c = (Class)MapUtil.computeIfAbsent(this.classCache, name, (n) -> {
               try {
                  return ClassUtils.forName(this.getClassLoader(), getClassName(schema));
               } catch (ClassNotFoundException var8) {
                  StringBuilder nestedName = new StringBuilder(n);

                  for(int lastDot = n.lastIndexOf(46); lastDot != -1; lastDot = n.lastIndexOf(46, lastDot - 1)) {
                     nestedName.setCharAt(lastDot, '$');

                     try {
                        return ClassUtils.forName(this.getClassLoader(), nestedName.toString());
                     }
                  }

                  return NO_CLASS;
               }
            });
            return c == NO_CLASS ? null : c;
         case ARRAY:
            return List.class;
         case MAP:
            return Map.class;
         case UNION:
            List<Schema> types = schema.getTypes();
            if (types.size() == 2 && types.contains(NULL_SCHEMA)) {
               return this.getWrapper((Schema)types.get(((Schema)types.get(0)).equals(NULL_SCHEMA) ? 1 : 0));
            }

            return Object.class;
         case STRING:
            if ("String".equals(schema.getProp("avro.java.string"))) {
               return String.class;
            }

            return CharSequence.class;
         case BYTES:
            return ByteBuffer.class;
         case INT:
            return Integer.TYPE;
         case LONG:
            return Long.TYPE;
         case FLOAT:
            return Float.TYPE;
         case DOUBLE:
            return Double.TYPE;
         case BOOLEAN:
            return Boolean.TYPE;
         case NULL:
            return Void.TYPE;
         default:
            throw new AvroRuntimeException("Unknown type: " + String.valueOf(schema));
      }
   }

   private Class getWrapper(Schema schema) {
      switch (schema.getType()) {
         case INT:
            return Integer.class;
         case LONG:
            return Long.class;
         case FLOAT:
            return Float.class;
         case DOUBLE:
            return Double.class;
         case BOOLEAN:
            return Boolean.class;
         default:
            return this.getClass(schema);
      }
   }

   public static String getClassName(Schema schema) {
      String namespace = schema.getNamespace();
      String name = schema.getName();
      if (namespace != null && !"".equals(namespace)) {
         String dot = namespace.endsWith("$") ? "" : ".";
         return mangle(namespace) + dot + mangleTypeIdentifier(name);
      } else {
         return name;
      }
   }

   public Schema getSchema(Type type) {
      try {
         return type instanceof Class ? (Schema)this.schemaClassCache.apply((Class)type) : (Schema)this.schemaTypeCache.computeIfAbsent(type, (t) -> this.createSchema(t, new HashMap()));
      } catch (Exception e) {
         throw e instanceof AvroRuntimeException ? (AvroRuntimeException)e : new AvroRuntimeException(e);
      }
   }

   protected Schema createSchema(Type type, Map names) {
      if (type instanceof Class && CharSequence.class.isAssignableFrom((Class)type)) {
         return Schema.create(Schema.Type.STRING);
      } else if (type == ByteBuffer.class) {
         return Schema.create(Schema.Type.BYTES);
      } else if (type != Integer.class && type != Integer.TYPE) {
         if (type != Long.class && type != Long.TYPE) {
            if (type != Float.class && type != Float.TYPE) {
               if (type != Double.class && type != Double.TYPE) {
                  if (type != Boolean.class && type != Boolean.TYPE) {
                     if (type != Void.class && type != Void.TYPE) {
                        if (type instanceof ParameterizedType) {
                           ParameterizedType ptype = (ParameterizedType)type;
                           Class raw = (Class)ptype.getRawType();
                           Type[] params = ptype.getActualTypeArguments();
                           if (Collection.class.isAssignableFrom(raw)) {
                              if (params.length != 1) {
                                 throw new AvroTypeException("No array type specified.");
                              } else {
                                 return Schema.createArray(this.createSchema(params[0], names));
                              }
                           } else if (Map.class.isAssignableFrom(raw)) {
                              Type key = params[0];
                              Type value = params[1];
                              if (key instanceof Class && CharSequence.class.isAssignableFrom((Class)key)) {
                                 return Schema.createMap(this.createSchema(value, names));
                              } else {
                                 throw new AvroTypeException("Map key class not CharSequence: " + SchemaUtil.describe((Object)key));
                              }
                           } else {
                              return Optional.class.isAssignableFrom(raw) ? Schema.createUnion(Schema.create(Schema.Type.NULL), this.createSchema(params[0], names)) : this.createSchema(raw, names);
                           }
                        } else if (type instanceof Class) {
                           Class c = (Class)type;
                           String fullName = c.getName();
                           Schema schema = (Schema)names.get(fullName);
                           if (schema == null) {
                              try {
                                 schema = (Schema)c.getDeclaredField("SCHEMA$").get((Object)null);
                                 if (!fullName.equals(getClassName(schema))) {
                                    schema = (new Schema.Parser()).parse(schema.toString().replace(schema.getNamespace(), c.getPackage().getName()));
                                 }
                              } catch (NoSuchFieldException var8) {
                                 throw new AvroRuntimeException("Not a Specific class: " + String.valueOf(c));
                              } catch (IllegalAccessException e) {
                                 throw new AvroRuntimeException(e);
                              }
                           }

                           names.put(fullName, schema);
                           return schema;
                        } else {
                           throw new AvroTypeException("Unknown type: " + String.valueOf(type));
                        }
                     } else {
                        return Schema.create(Schema.Type.NULL);
                     }
                  } else {
                     return Schema.create(Schema.Type.BOOLEAN);
                  }
               } else {
                  return Schema.create(Schema.Type.DOUBLE);
               }
            } else {
               return Schema.create(Schema.Type.FLOAT);
            }
         } else {
            return Schema.create(Schema.Type.LONG);
         }
      } else {
         return Schema.create(Schema.Type.INT);
      }
   }

   protected String getSchemaName(Object datum) {
      if (datum != null) {
         Class c = datum.getClass();
         if (this.isStringable(c)) {
            return Schema.Type.STRING.getName();
         }
      }

      return super.getSchemaName(datum);
   }

   protected boolean isStringable(Class c) {
      return this.stringableClasses.contains(c);
   }

   protected boolean isStringType(Class c) {
      return CharSequence.class.isAssignableFrom(c);
   }

   public Protocol getProtocol(Class iface) {
      try {
         Protocol p = (Protocol)iface.getDeclaredField("PROTOCOL").get((Object)null);
         if (!p.getNamespace().equals(iface.getPackage().getName())) {
            p = Protocol.parse(p.toString().replace(p.getNamespace(), iface.getPackage().getName()));
         }

         return p;
      } catch (NoSuchFieldException var3) {
         throw new AvroRuntimeException("Not a Specific protocol: " + String.valueOf(iface));
      } catch (IllegalAccessException e) {
         throw new AvroRuntimeException(e);
      }
   }

   protected int compare(Object o1, Object o2, Schema s, boolean eq) {
      switch (s.getType()) {
         case ENUM:
            if (o1 instanceof Enum) {
               return ((Enum)o1).ordinal() - ((Enum)o2).ordinal();
            }
         default:
            return super.compare(o1, o2, s, eq);
      }
   }

   public static Object newInstance(Class c, Schema s) {
      boolean useSchema = SchemaConstructable.class.isAssignableFrom(c);

      try {
         Constructor<?> meth = (Constructor)CTOR_CACHE.apply(c);
         Object result = meth.newInstance(useSchema ? new Object[]{s} : null);
         return result;
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public Object createFixed(Object old, Schema schema) {
      Class c = this.getClass(schema);
      if (c == null) {
         return super.createFixed(old, schema);
      } else {
         return c.isInstance(old) ? old : newInstance(c, schema);
      }
   }

   public Object newRecord(Object old, Schema schema) {
      Class c = this.getClass(schema);
      if (c == null) {
         return super.newRecord(old, schema);
      } else {
         return c.isInstance(old) ? old : newInstance(c, schema);
      }
   }

   public GenericData.InstanceSupplier getNewRecordSupplier(Schema schema) {
      Class c = this.getClass(schema);
      if (c == null) {
         return super.getNewRecordSupplier(schema);
      } else {
         boolean useSchema = SchemaConstructable.class.isAssignableFrom(c);
         Constructor<?> meth = (Constructor)CTOR_CACHE.apply(c);
         Object[] params = useSchema ? new Object[]{schema} : (Object[])null;
         return (old, sch) -> {
            try {
               return c.isInstance(old) ? old : meth.newInstance(params);
            } catch (ReflectiveOperationException e) {
               throw new RuntimeException(e);
            }
         };
      }
   }

   public static BinaryDecoder getDecoder(ObjectInput in) {
      return DecoderFactory.get().directBinaryDecoder(new ExternalizableInput(in), (BinaryDecoder)null);
   }

   public static BinaryEncoder getEncoder(ObjectOutput out) {
      return EncoderFactory.get().directBinaryEncoder(new ExternalizableOutput(out), (BinaryEncoder)null);
   }

   public Object createString(Object value) {
      if (value instanceof String) {
         return value;
      } else {
         return this.isStringable(value.getClass()) ? value : super.createString(value);
      }
   }

   static {
      ACCESSOR_MUTATOR_RESERVED_WORDS.addAll(RESERVED_WORDS);
      TYPE_IDENTIFIER_RESERVED_WORDS = new HashSet(Arrays.asList("var", "yield", "record"));
      TYPE_IDENTIFIER_RESERVED_WORDS.addAll(RESERVED_WORDS);
      ERROR_RESERVED_WORDS = new HashSet(Arrays.asList("message", "cause"));
      ERROR_RESERVED_WORDS.addAll(ACCESSOR_MUTATOR_RESERVED_WORDS);
      NO_CLASS = (new Object() {
      }).getClass();
      NULL_SCHEMA = Schema.create(Schema.Type.NULL);
   }

   public interface SchemaConstructable {
   }
}
