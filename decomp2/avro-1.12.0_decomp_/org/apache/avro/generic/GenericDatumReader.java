package org.apache.avro.generic;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Conversion;
import org.apache.avro.Conversions;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.ResolvingDecoder;
import org.apache.avro.util.Utf8;
import org.apache.avro.util.WeakIdentityHashMap;
import org.apache.avro.util.internal.ThreadLocalWithInitial;

public class GenericDatumReader implements DatumReader {
   private final GenericData data;
   private Schema actual;
   private Schema expected;
   private DatumReader fastDatumReader;
   private ResolvingDecoder creatorResolver;
   private final Thread creator;
   private static final ThreadLocal RESOLVER_CACHE = ThreadLocalWithInitial.of(WeakIdentityHashMap::new);
   private final ReaderCache readerCache;

   public GenericDatumReader() {
      this((Schema)null, (Schema)null, GenericData.get());
   }

   public GenericDatumReader(Schema schema) {
      this(schema, schema, GenericData.get());
   }

   public GenericDatumReader(Schema writer, Schema reader) {
      this(writer, reader, GenericData.get());
   }

   public GenericDatumReader(Schema writer, Schema reader, GenericData data) {
      this(data);
      this.actual = writer;
      this.expected = reader;
   }

   protected GenericDatumReader(GenericData data) {
      this.fastDatumReader = null;
      this.creatorResolver = null;
      this.readerCache = new ReaderCache(this::findStringClass);
      this.data = data;
      this.creator = Thread.currentThread();
   }

   public GenericData getData() {
      return this.data;
   }

   public Schema getSchema() {
      return this.actual;
   }

   public void setSchema(Schema writer) {
      this.actual = writer;
      if (this.expected == null) {
         this.expected = this.actual;
      }

      this.creatorResolver = null;
      this.fastDatumReader = null;
   }

   public Schema getExpected() {
      return this.expected;
   }

   public void setExpected(Schema reader) {
      this.expected = reader;
      this.creatorResolver = null;
   }

   protected final ResolvingDecoder getResolver(Schema actual, Schema expected) throws IOException {
      Thread currThread = Thread.currentThread();
      if (currThread == this.creator && this.creatorResolver != null) {
         return this.creatorResolver;
      } else {
         Map<Schema, ResolvingDecoder> cache = (Map)((Map)RESOLVER_CACHE.get()).get(actual);
         if (cache == null) {
            cache = new WeakIdentityHashMap();
            ((Map)RESOLVER_CACHE.get()).put(actual, cache);
         }

         ResolvingDecoder resolver = (ResolvingDecoder)cache.get(expected);
         if (resolver == null) {
            resolver = DecoderFactory.get().resolvingDecoder(Schema.applyAliases(actual, expected), expected, (Decoder)null);
            cache.put(expected, resolver);
         }

         if (currThread == this.creator) {
            this.creatorResolver = resolver;
         }

         return resolver;
      }
   }

   public Object read(Object reuse, Decoder in) throws IOException {
      if (this.data.isFastReaderEnabled()) {
         if (this.fastDatumReader == null) {
            this.fastDatumReader = this.data.getFastReaderBuilder().createDatumReader(this.actual, this.expected);
         }

         return this.fastDatumReader.read(reuse, in);
      } else {
         ResolvingDecoder resolver = this.getResolver(this.actual, this.expected);
         resolver.configure(in);
         D result = (D)this.read(reuse, this.expected, resolver);
         resolver.drain();
         return result;
      }
   }

   protected Object read(Object old, Schema expected, ResolvingDecoder in) throws IOException {
      Object datum = this.readWithoutConversion(old, expected, in);
      LogicalType logicalType = expected.getLogicalType();
      if (logicalType != null) {
         Conversion<?> conversion = this.getData().getConversionFor(logicalType);
         if (conversion != null) {
            return this.convert(datum, expected, logicalType, conversion);
         }
      }

      return datum;
   }

   protected Object readWithConversion(Object old, Schema expected, LogicalType logicalType, Conversion conversion, ResolvingDecoder in) throws IOException {
      return this.convert(this.readWithoutConversion(old, expected, in), expected, logicalType, conversion);
   }

   protected Object readWithoutConversion(Object old, Schema expected, ResolvingDecoder in) throws IOException {
      switch (expected.getType()) {
         case RECORD:
            return this.readRecord(old, expected, in);
         case ENUM:
            return this.readEnum(expected, in);
         case ARRAY:
            return this.readArray(old, expected, in);
         case MAP:
            return this.readMap(old, expected, in);
         case UNION:
            return this.read(old, (Schema)expected.getTypes().get(in.readIndex()), in);
         case FIXED:
            return this.readFixed(old, expected, in);
         case STRING:
            return this.readString(old, expected, in);
         case BYTES:
            return this.readBytes(old, expected, in);
         case INT:
            return this.readInt(old, expected, in);
         case LONG:
            return in.readLong();
         case FLOAT:
            return in.readFloat();
         case DOUBLE:
            return in.readDouble();
         case BOOLEAN:
            return in.readBoolean();
         case NULL:
            in.readNull();
            return null;
         default:
            throw new AvroRuntimeException("Unknown type: " + String.valueOf(expected));
      }
   }

   protected Object convert(Object datum, Schema schema, LogicalType type, Conversion conversion) {
      return Conversions.convertToLogicalType(datum, schema, type, conversion);
   }

   protected Object readRecord(Object old, Schema expected, ResolvingDecoder in) throws IOException {
      Object record = this.data.newRecord(old, expected);
      Object state = this.data.getRecordState(record, expected);

      for(Schema.Field field : in.readFieldOrder()) {
         int pos = field.pos();
         String name = field.name();
         Object oldDatum = null;
         if (old != null) {
            oldDatum = this.data.getField(record, name, pos, state);
         }

         this.readField(record, field, oldDatum, in, state);
      }

      return record;
   }

   protected void readField(Object record, Schema.Field field, Object oldDatum, ResolvingDecoder in, Object state) throws IOException {
      this.data.setField(record, field.name(), field.pos(), this.read(oldDatum, field.schema(), in), state);
   }

   protected Object readEnum(Schema expected, Decoder in) throws IOException {
      return this.createEnum((String)expected.getEnumSymbols().get(in.readEnum()), expected);
   }

   protected Object createEnum(String symbol, Schema schema) {
      return this.data.createEnum(symbol, schema);
   }

   protected Object readArray(Object old, Schema expected, ResolvingDecoder in) throws IOException {
      Schema expectedType = expected.getElementType();
      long l = in.readArrayStart();
      long base = 0L;
      if (l <= 0L) {
         return this.pruneArray(this.newArray(old, 0, expected));
      } else {
         LogicalType logicalType = expectedType.getLogicalType();
         Conversion<?> conversion = this.getData().getConversionFor(logicalType);
         Object array = this.newArray(old, (int)l, expected);

         do {
            if (logicalType != null && conversion != null) {
               for(long i = 0L; i < l; ++i) {
                  this.addToArray(array, base + i, this.readWithConversion(this.peekArray(array), expectedType, logicalType, conversion, in));
               }
            } else {
               for(long i = 0L; i < l; ++i) {
                  this.addToArray(array, base + i, this.readWithoutConversion(this.peekArray(array), expectedType, in));
               }
            }

            base += l;
         } while((l = in.arrayNext()) > 0L);

         return this.pruneArray(array);
      }
   }

   private Object pruneArray(Object object) {
      if (object instanceof GenericArray) {
         ((GenericArray)object).prune();
      }

      return object;
   }

   protected Object peekArray(Object array) {
      return array instanceof GenericArray ? ((GenericArray)array).peek() : null;
   }

   protected void addToArray(Object array, long pos, Object e) {
      ((Collection)array).add(e);
   }

   protected Object readMap(Object old, Schema expected, ResolvingDecoder in) throws IOException {
      Schema eValue = expected.getValueType();
      long l = in.readMapStart();
      LogicalType logicalType = eValue.getLogicalType();
      Conversion<?> conversion = this.getData().getConversionFor(logicalType);
      Object map = this.newMap(old, (int)l);
      if (l > 0L) {
         do {
            if (logicalType != null && conversion != null) {
               for(int i = 0; (long)i < l; ++i) {
                  this.addToMap(map, this.readMapKey((Object)null, expected, in), this.readWithConversion((Object)null, eValue, logicalType, conversion, in));
               }
            } else {
               for(int i = 0; (long)i < l; ++i) {
                  this.addToMap(map, this.readMapKey((Object)null, expected, in), this.readWithoutConversion((Object)null, eValue, in));
               }
            }
         } while((l = in.mapNext()) > 0L);
      }

      return map;
   }

   protected Object readMapKey(Object old, Schema expected, Decoder in) throws IOException {
      return this.readString(old, expected, in);
   }

   protected void addToMap(Object map, Object key, Object value) {
      ((Map)map).put(key, value);
   }

   protected Object readFixed(Object old, Schema expected, Decoder in) throws IOException {
      GenericFixed fixed = (GenericFixed)this.data.createFixed(old, expected);
      in.readFixed(fixed.bytes(), 0, expected.getFixedSize());
      return fixed;
   }

   /** @deprecated */
   @Deprecated
   protected Object createFixed(Object old, Schema schema) {
      return this.data.createFixed(old, schema);
   }

   /** @deprecated */
   @Deprecated
   protected Object createFixed(Object old, byte[] bytes, Schema schema) {
      return this.data.createFixed(old, bytes, schema);
   }

   /** @deprecated */
   @Deprecated
   protected Object newRecord(Object old, Schema schema) {
      return this.data.newRecord(old, schema);
   }

   protected Object newArray(Object old, int size, Schema schema) {
      return this.data.newArray(old, size, schema);
   }

   protected Object newMap(Object old, int size) {
      return this.data.newMap(old, size);
   }

   protected Object readString(Object old, Schema expected, Decoder in) throws IOException {
      Class stringClass = this.getReaderCache().getStringClass(expected);
      if (stringClass == String.class) {
         return in.readString();
      } else {
         return stringClass == CharSequence.class ? this.readString(old, in) : this.newInstanceFromString(stringClass, in.readString());
      }
   }

   protected Object readString(Object old, Decoder in) throws IOException {
      return in.readString(old instanceof Utf8 ? (Utf8)old : null);
   }

   protected Object createString(String value) {
      return new Utf8(value);
   }

   protected Class findStringClass(Schema schema) {
      String name = schema.getProp("avro.java.string");
      if (name == null) {
         return CharSequence.class;
      } else {
         switch (GenericData.StringType.valueOf(name)) {
            case String:
               return String.class;
            default:
               return CharSequence.class;
         }
      }
   }

   ReaderCache getReaderCache() {
      return this.readerCache;
   }

   protected Object newInstanceFromString(Class c, String s) {
      return this.getReaderCache().newInstanceFromString(c, s);
   }

   protected Object readBytes(Object old, Schema s, Decoder in) throws IOException {
      return this.readBytes(old, in);
   }

   protected Object readBytes(Object old, Decoder in) throws IOException {
      return in.readBytes(old instanceof ByteBuffer ? (ByteBuffer)old : null);
   }

   protected Object readInt(Object old, Schema expected, Decoder in) throws IOException {
      return in.readInt();
   }

   protected Object createBytes(byte[] value) {
      return ByteBuffer.wrap(value);
   }

   public static void skip(Schema schema, Decoder in) throws IOException {
      switch (schema.getType()) {
         case RECORD:
            for(Schema.Field field : schema.getFields()) {
               skip(field.schema(), in);
            }
            break;
         case ENUM:
            in.readEnum();
            break;
         case ARRAY:
            Schema elementType = schema.getElementType();

            for(long l = in.skipArray(); l > 0L; l = in.skipArray()) {
               for(long i = 0L; i < l; ++i) {
                  skip(elementType, in);
               }
            }
            break;
         case MAP:
            Schema value = schema.getValueType();

            for(long l = in.skipMap(); l > 0L; l = in.skipMap()) {
               for(long i = 0L; i < l; ++i) {
                  in.skipString();
                  skip(value, in);
               }
            }
            break;
         case UNION:
            skip((Schema)schema.getTypes().get(in.readIndex()), in);
            break;
         case FIXED:
            in.skipFixed(schema.getFixedSize());
            break;
         case STRING:
            in.skipString();
            break;
         case BYTES:
            in.skipBytes();
            break;
         case INT:
            in.readInt();
            break;
         case LONG:
            in.readLong();
            break;
         case FLOAT:
            in.readFloat();
            break;
         case DOUBLE:
            in.readDouble();
            break;
         case BOOLEAN:
            in.readBoolean();
            break;
         case NULL:
            in.readNull();
            break;
         default:
            throw new RuntimeException("Unknown type: " + String.valueOf(schema));
      }

   }

   private static final class IdentitySchemaKey {
      private final Schema schema;
      private final int hashcode;

      public IdentitySchemaKey(Schema schema) {
         this.schema = schema;
         this.hashcode = System.identityHashCode(schema);
      }

      public int hashCode() {
         return this.hashcode;
      }

      public boolean equals(Object obj) {
         if (obj != null && obj instanceof IdentitySchemaKey) {
            IdentitySchemaKey key = (IdentitySchemaKey)obj;
            return this == key || this.schema == key.schema;
         } else {
            return false;
         }
      }
   }

   static class ReaderCache {
      private final Map stringClassCache = new ConcurrentHashMap();
      private final Map stringCtorCache = new ConcurrentHashMap();
      private final Function findStringClass;

      public ReaderCache(Function findStringClass) {
         this.findStringClass = findStringClass;
      }

      public Object newInstanceFromString(Class c, String s) {
         Function<String, Object> ctor = (Function)this.stringCtorCache.computeIfAbsent(c, this::buildFunction);
         return ctor.apply(s);
      }

      private Function buildFunction(Class c) {
         Constructor ctor;
         try {
            ctor = c.getDeclaredConstructor(String.class);
         } catch (NoSuchMethodException e) {
            throw new AvroRuntimeException(e);
         }

         ctor.setAccessible(true);
         return (s) -> {
            try {
               return ctor.newInstance(s);
            } catch (ReflectiveOperationException e) {
               throw new AvroRuntimeException(e);
            }
         };
      }

      public Class getStringClass(final Schema s) {
         IdentitySchemaKey key = new IdentitySchemaKey(s);
         return (Class)this.stringClassCache.computeIfAbsent(key, (k) -> (Class)this.findStringClass.apply(k.schema));
      }
   }
}
