package org.apache.avro.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Conversion;
import org.apache.avro.Conversions;
import org.apache.avro.Resolver;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericArray;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.avro.generic.GenericFixed;
import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.io.parsing.ResolvingGrammarGenerator;
import org.apache.avro.reflect.ReflectionUtil;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.avro.util.Utf8;
import org.apache.avro.util.WeakIdentityHashMap;
import org.apache.avro.util.internal.Accessor;

public class FastReaderBuilder {
   private final GenericData data;
   private final Map readerCache = Collections.synchronizedMap(new WeakIdentityHashMap());
   private boolean keyClassEnabled = true;
   private boolean classPropEnabled = true;

   public static FastReaderBuilder get() {
      return new FastReaderBuilder(GenericData.get());
   }

   public static FastReaderBuilder getSpecific() {
      return new FastReaderBuilder(SpecificData.get());
   }

   public static boolean isSupportedData(GenericData data) {
      return data.getClass() == GenericData.class || data.getClass() == SpecificData.class;
   }

   public FastReaderBuilder(GenericData parentData) {
      this.data = parentData;
   }

   public FastReaderBuilder withKeyClassEnabled(boolean enabled) {
      this.keyClassEnabled = enabled;
      return this;
   }

   public boolean isKeyClassEnabled() {
      return this.keyClassEnabled;
   }

   public FastReaderBuilder withClassPropEnabled(boolean enabled) {
      this.classPropEnabled = enabled;
      return this;
   }

   public boolean isClassPropEnabled() {
      return this.classPropEnabled;
   }

   public DatumReader createDatumReader(Schema schema) throws IOException {
      return this.createDatumReader(schema, schema);
   }

   public DatumReader createDatumReader(Schema writerSchema, Schema readerSchema) throws IOException {
      Schema resolvedWriterSchema = Schema.applyAliases(writerSchema, readerSchema);
      return this.getReaderFor(readerSchema, resolvedWriterSchema);
   }

   private FieldReader getReaderFor(Schema readerSchema, Schema writerSchema) throws IOException {
      Resolver.Action resolvedAction = Resolver.resolve(writerSchema, readerSchema, this.data);
      return this.getReaderFor((Resolver.Action)resolvedAction, (Conversion)null);
   }

   private FieldReader getReaderFor(Resolver.Action action, Conversion explicitConversion) throws IOException {
      FieldReader baseReader = this.getNonConvertedReader(action);
      return this.applyConversions(action.reader, baseReader, explicitConversion);
   }

   private RecordReader createRecordReader(Resolver.RecordAdjust action) throws IOException {
      RecordReader recordReader = this.getRecordReaderFromCache(action.reader, action.writer);
      synchronized(recordReader) {
         if (recordReader.getInitializationStage() == FastReaderBuilder.RecordReader.Stage.NEW) {
            this.initializeRecordReader(recordReader, action);
         }

         return recordReader;
      }
   }

   private RecordReader initializeRecordReader(RecordReader recordReader, Resolver.RecordAdjust action) throws IOException {
      recordReader.startInitialization();
      Object testInstance = action.instanceSupplier.newInstance((Object)null, action.reader);
      IntFunction<Conversion<?>> conversionSupplier = this.getConversionSupplier(testInstance);
      ExecutionStep[] readSteps = new ExecutionStep[action.fieldActions.length + action.readerOrder.length - action.firstDefault];
      int i = 0;

      int fieldCounter;
      for(fieldCounter = 0; i < action.fieldActions.length; ++i) {
         Resolver.Action fieldAction = action.fieldActions[i];
         if (fieldAction instanceof Resolver.Skip) {
            readSteps[i] = (r, decoder) -> GenericDatumReader.skip(fieldAction.writer, decoder);
         } else {
            Schema.Field readerField = action.readerOrder[fieldCounter++];
            Conversion<?> conversion = (Conversion)conversionSupplier.apply(readerField.pos());
            FieldReader reader = this.getReaderFor(fieldAction, conversion);
            readSteps[i] = this.createFieldSetter(readerField, reader);
         }
      }

      while(i < readSteps.length) {
         readSteps[i] = this.getDefaultingStep(action.readerOrder[fieldCounter++]);
         ++i;
      }

      recordReader.finishInitialization(readSteps, action.reader, action.instanceSupplier);
      return recordReader;
   }

   private ExecutionStep createFieldSetter(Schema.Field field, FieldReader reader) {
      int pos = field.pos();
      return reader.canReuse() ? (object, decoder) -> {
         IndexedRecord record = (IndexedRecord)object;
         record.put(pos, reader.read(record.get(pos), decoder));
      } : (object, decoder) -> {
         IndexedRecord record = (IndexedRecord)object;
         record.put(pos, reader.read((Object)null, decoder));
      };
   }

   private ExecutionStep getDefaultingStep(Schema.Field field) throws IOException {
      Object defaultValue = this.data.getDefaultValue(field);
      if (this.isObjectImmutable(defaultValue)) {
         return this.createFieldSetter(field, (old, d) -> defaultValue);
      } else if (defaultValue instanceof Utf8) {
         return this.createFieldSetter(field, reusingReader((old, d) -> this.readUtf8(old, (Utf8)defaultValue)));
      } else if (defaultValue instanceof List && ((List)defaultValue).isEmpty()) {
         return this.createFieldSetter(field, reusingReader((old, d) -> this.data.newArray(old, 0, field.schema())));
      } else if (defaultValue instanceof Map && ((Map)defaultValue).isEmpty()) {
         return this.createFieldSetter(field, reusingReader((old, d) -> this.data.newMap(old, 0)));
      } else {
         DatumReader<Object> datumReader = this.createDatumReader(field.schema());
         byte[] encoded = this.getEncodedValue(field);
         FieldReader fieldReader = reusingReader((old, decoder) -> datumReader.read(old, DecoderFactory.get().binaryDecoder((byte[])encoded, (BinaryDecoder)null)));
         return this.createFieldSetter(field, fieldReader);
      }
   }

   private boolean isObjectImmutable(Object object) {
      return object == null || object instanceof Number || object instanceof String || object instanceof GenericEnumSymbol || object.getClass().isEnum();
   }

   private Utf8 readUtf8(Object reuse, Utf8 newValue) {
      if (reuse instanceof Utf8) {
         Utf8 oldUtf8 = (Utf8)reuse;
         oldUtf8.set(newValue);
         return oldUtf8;
      } else {
         return new Utf8(newValue);
      }
   }

   private byte[] getEncodedValue(Schema.Field field) throws IOException {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      Encoder encoder = EncoderFactory.get().binaryEncoder(out, (BinaryEncoder)null);
      ResolvingGrammarGenerator.encode(encoder, field.schema(), Accessor.defaultValue(field));
      encoder.flush();
      return out.toByteArray();
   }

   private IntFunction getConversionSupplier(Object record) {
      if (record instanceof SpecificRecordBase) {
         SpecificRecordBase var10000 = (SpecificRecordBase)record;
         Objects.requireNonNull((SpecificRecordBase)record);
         return var10000::getConversion;
      } else {
         return (index) -> null;
      }
   }

   private RecordReader getRecordReaderFromCache(Schema readerSchema, Schema writerSchema) {
      return (RecordReader)((Map)this.readerCache.computeIfAbsent(readerSchema, (k) -> new WeakIdentityHashMap())).computeIfAbsent(writerSchema, (k) -> new RecordReader());
   }

   private FieldReader applyConversions(Schema readerSchema, FieldReader reader, Conversion explicitConversion) {
      Conversion<?> conversion = explicitConversion;
      if (explicitConversion == null) {
         if (readerSchema.getLogicalType() == null) {
            return reader;
         }

         conversion = this.data.getConversionFor(readerSchema.getLogicalType());
         if (conversion == null) {
            return reader;
         }
      }

      return (old, decoder) -> Conversions.convertToLogicalType(reader.read(old, decoder), readerSchema, readerSchema.getLogicalType(), conversion);
   }

   private FieldReader getNonConvertedReader(Resolver.Action action) throws IOException {
      switch (action.type) {
         case CONTAINER:
            switch (action.reader.getType()) {
               case MAP:
                  return this.createMapReader(action.reader, (Resolver.Container)action);
               case ARRAY:
                  return this.createArrayReader(action.reader, (Resolver.Container)action);
               default:
                  throw new IllegalStateException("Error getting reader for action type " + String.valueOf(action.getClass()));
            }
         case DO_NOTHING:
            return this.getReaderForBaseType(action.reader, action.writer);
         case RECORD:
            return this.createRecordReader((Resolver.RecordAdjust)action);
         case ENUM:
            return this.createEnumReader((Resolver.EnumAdjust)action);
         case PROMOTE:
            return this.createPromotingReader((Resolver.Promote)action);
         case WRITER_UNION:
            return this.createUnionReader((Resolver.WriterUnion)action);
         case READER_UNION:
            return this.getReaderFor((Resolver.Action)((Resolver.ReaderUnion)action).actualAction, (Conversion)null);
         case ERROR:
            return (old, decoder) -> {
               throw new AvroTypeException(action.toString());
            };
         default:
            throw new IllegalStateException("Error getting reader for action type " + String.valueOf(action.getClass()));
      }
   }

   private FieldReader getReaderForBaseType(Schema readerSchema, Schema writerSchema) throws IOException {
      switch (readerSchema.getType()) {
         case MAP:
         case ARRAY:
         case RECORD:
         case UNION:
         case ENUM:
         default:
            throw new IllegalStateException("Error getting reader for type " + readerSchema.getFullName());
         case NULL:
            return (old, decoder) -> {
               decoder.readNull();
               return null;
            };
         case BOOLEAN:
            return (old, decoder) -> decoder.readBoolean();
         case STRING:
            return this.createStringReader(readerSchema, writerSchema);
         case INT:
            return (old, decoder) -> decoder.readInt();
         case LONG:
            return (old, decoder) -> decoder.readLong();
         case FLOAT:
            return (old, decoder) -> decoder.readFloat();
         case DOUBLE:
            return (old, decoder) -> decoder.readDouble();
         case BYTES:
            return this.createBytesReader();
         case FIXED:
            return this.createFixedReader(readerSchema, writerSchema);
      }
   }

   private FieldReader createPromotingReader(Resolver.Promote promote) throws IOException {
      label24:
      switch (promote.reader.getType()) {
         case STRING:
            return this.createBytesPromotingToStringReader(promote.reader);
         case INT:
         default:
            break;
         case LONG:
            return (reuse, decoder) -> (long)decoder.readInt();
         case FLOAT:
            switch (promote.writer.getType()) {
               case INT:
                  return (reuse, decoder) -> (float)decoder.readInt();
               case LONG:
                  return (reuse, decoder) -> (float)decoder.readLong();
               default:
                  break label24;
            }
         case DOUBLE:
            switch (promote.writer.getType()) {
               case INT:
                  return (reuse, decoder) -> (double)decoder.readInt();
               case LONG:
                  return (reuse, decoder) -> (double)decoder.readLong();
               case FLOAT:
                  return (reuse, decoder) -> (double)decoder.readFloat();
               default:
                  break label24;
            }
         case BYTES:
            return (reuse, decoder) -> ByteBuffer.wrap(decoder.readString((Utf8)null).getBytes());
      }

      String var10002 = String.valueOf(promote.writer.getType());
      throw new IllegalStateException("No promotion possible for type " + var10002 + " to " + String.valueOf(promote.reader.getType()));
   }

   private FieldReader createStringReader(Schema readerSchema, Schema writerSchema) {
      FieldReader stringReader = this.createSimpleStringReader(readerSchema);
      return this.isClassPropEnabled() ? this.getTransformingStringReader(readerSchema.getProp("java-class"), stringReader) : stringReader;
   }

   private FieldReader createSimpleStringReader(Schema readerSchema) {
      String stringProperty = readerSchema.getProp("avro.java.string");
      return GenericData.StringType.String.name().equals(stringProperty) ? (old, decoder) -> decoder.readString() : (old, decoder) -> decoder.readString(old instanceof Utf8 ? (Utf8)old : null);
   }

   private FieldReader createBytesPromotingToStringReader(Schema readerSchema) {
      String stringProperty = readerSchema.getProp("avro.java.string");
      return GenericData.StringType.String.name().equals(stringProperty) ? (old, decoder) -> this.getStringFromByteBuffer(decoder.readBytes((ByteBuffer)null)) : (old, decoder) -> this.getUtf8FromByteBuffer(old, decoder.readBytes((ByteBuffer)null));
   }

   private String getStringFromByteBuffer(ByteBuffer buffer) {
      return new String(buffer.array(), buffer.position(), buffer.remaining(), StandardCharsets.UTF_8);
   }

   private Utf8 getUtf8FromByteBuffer(Object old, ByteBuffer buffer) {
      return old instanceof Utf8 ? ((Utf8)old).set(new Utf8(buffer.array())) : new Utf8(buffer.array());
   }

   private FieldReader createUnionReader(Resolver.WriterUnion action) throws IOException {
      FieldReader[] unionReaders = new FieldReader[action.actions.length];

      for(int i = 0; i < action.actions.length; ++i) {
         unionReaders[i] = this.getReaderFor((Resolver.Action)action.actions[i], (Conversion)null);
      }

      return this.createUnionReader(unionReaders);
   }

   private FieldReader createUnionReader(FieldReader[] unionReaders) {
      return reusingReader((reuse, decoder) -> {
         int selection = decoder.readIndex();
         return unionReaders[selection].read((Object)null, decoder);
      });
   }

   private FieldReader createMapReader(Schema readerSchema, Resolver.Container action) throws IOException {
      FieldReader keyReader = this.createMapKeyReader(readerSchema);
      FieldReader valueReader = this.getReaderFor((Resolver.Action)action.elementAction, (Conversion)null);
      return new MapReader(keyReader, valueReader);
   }

   private FieldReader createMapKeyReader(Schema readerSchema) {
      FieldReader stringReader = this.createSimpleStringReader(readerSchema);
      return this.isKeyClassEnabled() ? this.getTransformingStringReader(readerSchema.getProp("java-key-class"), this.createSimpleStringReader(readerSchema)) : stringReader;
   }

   private FieldReader getTransformingStringReader(String valueClass, FieldReader stringReader) {
      if (valueClass == null) {
         return stringReader;
      } else {
         Function<String, ?> transformer = (Function)this.findClass(valueClass).map((clazz) -> ReflectionUtil.getConstructorAsFunction(String.class, clazz)).orElse((Object)null);
         return transformer != null ? (old, decoder) -> transformer.apply((String)stringReader.read((Object)null, decoder)) : stringReader;
      }
   }

   private Optional findClass(String clazz) {
      try {
         return Optional.of(this.data.getClassLoader().loadClass(clazz));
      } catch (ReflectiveOperationException var3) {
         return Optional.empty();
      }
   }

   private FieldReader createArrayReader(Schema readerSchema, Resolver.Container action) throws IOException {
      FieldReader elementReader = this.getReaderFor((Resolver.Action)action.elementAction, (Conversion)null);
      return reusingReader((reuse, decoder) -> {
         if (reuse instanceof GenericArray) {
            GenericArray<Object> reuseArray = (GenericArray)reuse;
            long l = decoder.readArrayStart();
            reuseArray.clear();

            while(l > 0L) {
               for(long i = 0L; i < l; ++i) {
                  reuseArray.add(elementReader.read(reuseArray.peek(), decoder));
               }

               l = decoder.arrayNext();
            }

            return reuseArray;
         } else {
            long l = decoder.readArrayStart();
            List<Object> array = (List<Object>)(reuse instanceof List ? (List)reuse : new GenericData.Array((int)l, readerSchema));
            array.clear();

            while(l > 0L) {
               for(long i = 0L; i < l; ++i) {
                  array.add(elementReader.read((Object)null, decoder));
               }

               l = decoder.arrayNext();
            }

            return array;
         }
      });
   }

   private FieldReader createEnumReader(Resolver.EnumAdjust action) {
      return reusingReader((reuse, decoder) -> {
         int index = decoder.readEnum();
         Object resultObject = action.values[index];
         if (resultObject == null) {
            String var10002 = (String)action.writer.getEnumSymbols().get(index);
            throw new AvroTypeException("No match for " + var10002);
         } else {
            return resultObject;
         }
      });
   }

   private FieldReader createFixedReader(Schema readerSchema, Schema writerSchema) {
      return reusingReader((reuse, decoder) -> {
         GenericFixed fixed = (GenericFixed)this.data.createFixed(reuse, readerSchema);
         decoder.readFixed(fixed.bytes(), 0, readerSchema.getFixedSize());
         return fixed;
      });
   }

   private FieldReader createBytesReader() {
      return reusingReader((reuse, decoder) -> decoder.readBytes(reuse instanceof ByteBuffer ? (ByteBuffer)reuse : null));
   }

   public static FieldReader reusingReader(ReusingFieldReader reader) {
      return reader;
   }

   public interface FieldReader extends DatumReader {
      Object read(Object reuse, Decoder decoder) throws IOException;

      default boolean canReuse() {
         return false;
      }

      default void setSchema(Schema schema) {
         throw new UnsupportedOperationException();
      }
   }

   public interface ReusingFieldReader extends FieldReader {
      default boolean canReuse() {
         return true;
      }
   }

   public static class RecordReader implements FieldReader {
      private ExecutionStep[] readSteps;
      private GenericData.InstanceSupplier supplier;
      private Schema schema;
      private Stage stage;

      public RecordReader() {
         this.stage = FastReaderBuilder.RecordReader.Stage.NEW;
      }

      public Stage getInitializationStage() {
         return this.stage;
      }

      public void reset() {
         this.stage = FastReaderBuilder.RecordReader.Stage.NEW;
      }

      public void startInitialization() {
         this.stage = FastReaderBuilder.RecordReader.Stage.INITIALIZING;
      }

      public void finishInitialization(ExecutionStep[] readSteps, Schema schema, GenericData.InstanceSupplier supp) {
         this.readSteps = readSteps;
         this.schema = schema;
         this.supplier = supp;
         this.stage = FastReaderBuilder.RecordReader.Stage.INITIALIZED;
      }

      public boolean canReuse() {
         return true;
      }

      public Object read(Object reuse, Decoder decoder) throws IOException {
         Object object = this.supplier.newInstance(reuse, this.schema);

         for(ExecutionStep thisStep : this.readSteps) {
            thisStep.execute(object, decoder);
         }

         return object;
      }

      public static enum Stage {
         NEW,
         INITIALIZING,
         INITIALIZED;

         // $FF: synthetic method
         private static Stage[] $values() {
            return new Stage[]{NEW, INITIALIZING, INITIALIZED};
         }
      }
   }

   public static class MapReader implements FieldReader {
      private final FieldReader keyReader;
      private final FieldReader valueReader;

      public MapReader(FieldReader keyReader, FieldReader valueReader) {
         this.keyReader = keyReader;
         this.valueReader = valueReader;
      }

      public Object read(Object reuse, Decoder decoder) throws IOException {
         long l = decoder.readMapStart();

         Map<Object, Object> targetMap;
         for(targetMap = new HashMap(); l > 0L; l = decoder.mapNext()) {
            for(int i = 0; (long)i < l; ++i) {
               Object key = this.keyReader.read((Object)null, decoder);
               Object value = this.valueReader.read((Object)null, decoder);
               targetMap.put(key, value);
            }
         }

         return targetMap;
      }
   }

   public interface ExecutionStep {
      void execute(Object record, Decoder decoder) throws IOException;
   }
}
