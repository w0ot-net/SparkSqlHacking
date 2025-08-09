package org.apache.arrow.vector.ipc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BitVectorHelper;
import org.apache.arrow.vector.BufferLayout;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.TypeLayout;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.util.DecimalUtility;
import org.apache.arrow.vector.util.DictionaryUtility;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class JsonFileReader implements AutoCloseable, DictionaryProvider {
   private final JsonParser parser;
   private final BufferAllocator allocator;
   private Schema schema;
   private Map dictionaries;
   private Boolean started = false;

   public JsonFileReader(File inputFile, BufferAllocator allocator) throws JsonParseException, IOException {
      this.allocator = allocator;
      MappingJsonFactory jsonFactory = new MappingJsonFactory((new ObjectMapper()).configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true));
      this.parser = jsonFactory.createParser(inputFile);
      this.parser.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
   }

   public Dictionary lookup(long id) {
      if (!this.started) {
         throw new IllegalStateException("Unable to lookup until after read() has started");
      } else {
         return (Dictionary)this.dictionaries.get(id);
      }
   }

   public Set getDictionaryIds() {
      return this.dictionaries.keySet();
   }

   public Schema start() throws JsonParseException, IOException {
      this.readToken(JsonToken.START_OBJECT);
      Schema originalSchema = (Schema)this.readNextField("schema", Schema.class);
      List<Field> fields = new ArrayList();
      this.dictionaries = new HashMap();

      for(Field field : originalSchema.getFields()) {
         fields.add(DictionaryUtility.toMemoryFormat(field, this.allocator, this.dictionaries));
      }

      this.schema = new Schema(fields, originalSchema.getCustomMetadata());
      if (!this.dictionaries.isEmpty()) {
         this.nextFieldIs("dictionaries");
         this.readDictionaryBatches();
      }

      this.nextFieldIs("batches");
      this.readToken(JsonToken.START_ARRAY);
      this.started = true;
      return this.schema;
   }

   private void readDictionaryBatches() throws JsonParseException, IOException {
      this.readToken(JsonToken.START_ARRAY);
      JsonToken token = this.parser.nextToken();

      for(boolean haveDictionaryBatch = token == JsonToken.START_OBJECT; haveDictionaryBatch; haveDictionaryBatch = token == JsonToken.START_OBJECT) {
         long id = (Long)this.readNextField("id", Long.class);
         Dictionary dict = (Dictionary)this.dictionaries.get(id);
         if (dict == null) {
            throw new IllegalArgumentException("Dictionary with id: " + id + " missing encoding from schema Field");
         }

         this.nextFieldIs("data");
         FieldVector vector = dict.getVector();
         List<Field> fields = Collections.singletonList(vector.getField());
         List<FieldVector> vectors = Collections.singletonList(vector);
         VectorSchemaRoot root = new VectorSchemaRoot(fields, vectors, vector.getValueCount());
         this.read(root);
         this.readToken(JsonToken.END_OBJECT);
         token = this.parser.nextToken();
      }

      if (token != JsonToken.END_ARRAY) {
         String var10002 = String.valueOf(token);
         throw new IllegalArgumentException("Invalid token: " + var10002 + " expected end of array at " + String.valueOf(this.parser.getTokenLocation()));
      }
   }

   public boolean read(VectorSchemaRoot root) throws IOException {
      JsonToken t = this.parser.nextToken();
      if (t != JsonToken.START_OBJECT) {
         if (t == JsonToken.END_ARRAY) {
            root.setRowCount(0);
            return false;
         } else {
            throw new IllegalArgumentException("Invalid token: " + String.valueOf(t));
         }
      } else {
         int count = (Integer)this.readNextField("count", Integer.class);
         this.nextFieldIs("columns");
         this.readToken(JsonToken.START_ARRAY);

         for(Field field : root.getSchema().getFields()) {
            FieldVector vector = root.getVector(field);
            this.readFromJsonIntoVector(field, vector);
         }

         this.readToken(JsonToken.END_ARRAY);
         root.setRowCount(count);
         this.readToken(JsonToken.END_OBJECT);
         return true;
      }
   }

   public VectorSchemaRoot read() throws IOException {
      JsonToken t = this.parser.nextToken();
      if (t != JsonToken.START_OBJECT) {
         if (t == JsonToken.END_ARRAY) {
            return null;
         } else {
            throw new IllegalArgumentException("Invalid token: " + String.valueOf(t));
         }
      } else {
         VectorSchemaRoot recordBatch = VectorSchemaRoot.create(this.schema, this.allocator);
         int count = (Integer)this.readNextField("count", Integer.class);
         recordBatch.setRowCount(count);
         this.nextFieldIs("columns");
         this.readToken(JsonToken.START_ARRAY);

         for(Field field : this.schema.getFields()) {
            FieldVector vector = recordBatch.getVector(field);
            this.readFromJsonIntoVector(field, vector);
         }

         this.readToken(JsonToken.END_ARRAY);
         this.readToken(JsonToken.END_OBJECT);
         return recordBatch;
      }
   }

   public int skip(int numBatches) throws IOException {
      for(int i = 0; i < numBatches; ++i) {
         JsonToken t = this.parser.nextToken();
         if (t != JsonToken.START_OBJECT) {
            if (t == JsonToken.END_ARRAY) {
               return i;
            }

            throw new IllegalArgumentException("Invalid token: " + String.valueOf(t));
         }

         this.parser.skipChildren();

         assert this.parser.getCurrentToken() == JsonToken.END_OBJECT;
      }

      return numBatches;
   }

   List readVariadicBuffers(BufferAllocator allocator, int variadicBuffersCount) throws IOException {
      this.readToken(JsonToken.START_ARRAY);
      ArrayList<ArrowBuf> dataBuffers = new ArrayList(variadicBuffersCount);

      for(int i = 0; i < variadicBuffersCount; ++i) {
         this.parser.nextToken();
         String variadicStr = (String)this.parser.readValueAs(String.class);
         byte[] value;
         if (variadicStr == null) {
            value = new byte[0];
         } else {
            value = this.decodeHexSafe(variadicStr);
         }

         ArrowBuf buf = allocator.buffer((long)value.length);
         buf.writeBytes(value);
         dataBuffers.add(buf);
      }

      this.readToken(JsonToken.END_ARRAY);
      return dataBuffers;
   }

   private ArrowBuf readViewBuffers(BufferAllocator allocator, int count, List variadicBufferIndices, Types.MinorType type) throws IOException {
      this.readToken(JsonToken.START_ARRAY);
      ArrayList<byte[]> values = new ArrayList(count);
      long bufferSize = 0L;

      for(int i = 0; i < count; ++i) {
         this.readToken(JsonToken.START_OBJECT);
         int length = (Integer)this.readNextField("SIZE", Integer.class);
         byte[] value;
         if (length > 12) {
            byte[] prefix = this.decodeHexSafe((String)this.readNextField("PREFIX_HEX", String.class));
            int bufferIndex = (Integer)this.readNextField("BUFFER_INDEX", Integer.class);
            if (variadicBufferIndices.isEmpty()) {
               variadicBufferIndices.add(bufferIndex);
            } else {
               int lastBufferIndex = (Integer)variadicBufferIndices.get(variadicBufferIndices.size() - 1);
               if (lastBufferIndex != bufferIndex) {
                  variadicBufferIndices.add(bufferIndex);
               }
            }

            int offset = (Integer)this.readNextField("OFFSET", Integer.class);
            ByteBuffer buffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
            buffer.putInt(length);
            buffer.put(prefix);
            buffer.putInt(bufferIndex);
            buffer.putInt(offset);
            value = buffer.array();
         } else {
            ByteBuffer buffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
            buffer.putInt(length);
            if (type == Types.MinorType.VIEWVARCHAR) {
               buffer.put(((String)this.readNextField("INLINED", String.class)).getBytes(StandardCharsets.UTF_8));
            } else {
               String inlined = (String)this.readNextField("INLINED", String.class);
               if (inlined == null) {
                  buffer.put(new byte[length]);
               } else {
                  buffer.put(this.decodeHexSafe(inlined));
               }
            }

            value = buffer.array();
         }

         values.add(value);
         bufferSize += (long)value.length;
         this.readToken(JsonToken.END_OBJECT);
      }

      ArrowBuf buf = allocator.buffer(bufferSize);

      for(byte[] value : values) {
         buf.writeBytes(value);
      }

      this.readToken(JsonToken.END_ARRAY);
      return buf;
   }

   private List readIntoBuffer(BufferAllocator allocator, BufferLayout.BufferType bufferType, Types.MinorType type, int count, List variadicBufferIndices) throws IOException {
      BufferHelper helper = new BufferHelper();
      BufferReader reader;
      if (bufferType.equals(BufferLayout.BufferType.VALIDITY)) {
         reader = helper.BIT;
      } else if (!bufferType.equals(BufferLayout.BufferType.OFFSET) && !bufferType.equals(BufferLayout.BufferType.SIZE)) {
         if (bufferType.equals(BufferLayout.BufferType.TYPE)) {
            reader = helper.INT1;
         } else {
            if (!bufferType.equals(BufferLayout.BufferType.DATA)) {
               if (bufferType.equals(BufferLayout.BufferType.VIEWS)) {
                  return Collections.singletonList(this.readViewBuffers(allocator, count, variadicBufferIndices, type));
               }

               if (bufferType.equals(BufferLayout.BufferType.VARIADIC_DATA_BUFFERS)) {
                  return this.readVariadicBuffers(allocator, variadicBufferIndices.size());
               }

               throw new InvalidArrowFileException("Unrecognized buffer type " + String.valueOf(bufferType));
            }

            switch (type) {
               case BIT:
                  reader = helper.BIT;
                  break;
               case TINYINT:
                  reader = helper.INT1;
                  break;
               case SMALLINT:
                  reader = helper.INT2;
                  break;
               case INT:
                  reader = helper.INT4;
                  break;
               case BIGINT:
                  reader = helper.INT8;
                  break;
               case UINT1:
                  reader = helper.UINT1;
                  break;
               case UINT2:
                  reader = helper.UINT2;
                  break;
               case UINT4:
                  reader = helper.UINT4;
                  break;
               case UINT8:
                  reader = helper.UINT8;
                  break;
               case FLOAT4:
                  reader = helper.FLOAT4;
                  break;
               case FLOAT8:
                  reader = helper.FLOAT8;
                  break;
               case DECIMAL:
                  reader = helper.DECIMAL;
                  break;
               case DECIMAL256:
                  reader = helper.DECIMAL256;
                  break;
               case FIXEDSIZEBINARY:
                  reader = helper.FIXEDSIZEBINARY;
                  break;
               case VARCHAR:
                  reader = helper.VARCHAR;
                  break;
               case LARGEVARCHAR:
                  reader = helper.LARGEVARCHAR;
                  break;
               case VARBINARY:
                  reader = helper.VARBINARY;
                  break;
               case LARGEVARBINARY:
                  reader = helper.LARGEVARBINARY;
                  break;
               case DATEDAY:
                  reader = helper.INT4;
                  break;
               case DATEMILLI:
                  reader = helper.INT8;
                  break;
               case TIMESEC:
               case TIMEMILLI:
                  reader = helper.INT4;
                  break;
               case TIMEMICRO:
               case TIMENANO:
                  reader = helper.INT8;
                  break;
               case TIMESTAMPNANO:
               case TIMESTAMPMICRO:
               case TIMESTAMPMILLI:
               case TIMESTAMPSEC:
               case TIMESTAMPNANOTZ:
               case TIMESTAMPMICROTZ:
               case TIMESTAMPMILLITZ:
               case TIMESTAMPSECTZ:
                  reader = helper.INT8;
                  break;
               case INTERVALYEAR:
                  reader = helper.INT4;
                  break;
               case INTERVALDAY:
                  reader = helper.DAY_MILLIS;
                  break;
               case INTERVALMONTHDAYNANO:
                  reader = helper.MONTH_DAY_NANOS;
                  break;
               case DURATION:
                  reader = helper.INT8;
                  break;
               default:
                  throw new UnsupportedOperationException("Cannot read array of type " + String.valueOf(type));
            }
         }
      } else if (type != Types.MinorType.LARGELIST && type != Types.MinorType.LARGEVARCHAR && type != Types.MinorType.LARGEVARBINARY && type != Types.MinorType.LARGELISTVIEW) {
         reader = helper.INT4;
      } else {
         reader = helper.INT8;
      }

      ArrowBuf buf = reader.readBuffer(allocator, count);
      Preconditions.checkNotNull(buf);
      return Collections.singletonList(buf);
   }

   private void readFromJsonIntoVector(Field field, FieldVector vector) throws IOException {
      ArrowType type = field.getType();
      TypeLayout typeLayout = TypeLayout.getTypeLayout(type);
      List<BufferLayout.BufferType> vectorTypes = typeLayout.getBufferTypes();
      List<ArrowBuf> vectorBuffers = new ArrayList(vectorTypes.size());
      List<Integer> variadicBufferIndices = new ArrayList();
      if (!typeLayout.isFixedBufferCount()) {
         vectorTypes.add(BufferLayout.BufferType.VARIADIC_DATA_BUFFERS);
      }

      this.readToken(JsonToken.START_OBJECT);
      String name = (String)this.readNextField("name", String.class);
      if (this.started && !Objects.equals(field.getName(), name)) {
         String var22 = field.getName();
         throw new IllegalArgumentException("Expected field " + var22 + " but got " + name);
      } else {
         int valueCount = (Integer)this.readNextField("count", Integer.class);
         vector.setInitialCapacity(valueCount);

         for(int v = 0; v < vectorTypes.size(); ++v) {
            BufferLayout.BufferType bufferType = (BufferLayout.BufferType)vectorTypes.get(v);
            this.nextFieldIs(bufferType.getName());
            int innerBufferValueCount = valueCount;
            if (bufferType.equals(BufferLayout.BufferType.OFFSET) && !(type instanceof ArrowType.Union) && !(type instanceof ArrowType.ListView) && !(type instanceof ArrowType.LargeListView)) {
               innerBufferValueCount = valueCount + 1;
            }

            vectorBuffers.addAll(this.readIntoBuffer(this.allocator, bufferType, vector.getMinorType(), innerBufferValueCount, variadicBufferIndices));
         }

         int nullCount = 0;
         if (type instanceof ArrowType.Null) {
            nullCount = valueCount;
         } else if (!(type instanceof ArrowType.Union)) {
            nullCount = BitVectorHelper.getNullCount((ArrowBuf)vectorBuffers.get(0), valueCount);
         }

         ArrowFieldNode fieldNode = new ArrowFieldNode((long)valueCount, (long)nullCount);
         vector.loadFieldBuffers(fieldNode, vectorBuffers);
         List<Field> fields = field.getChildren();
         if (!fields.isEmpty()) {
            List<FieldVector> vectorChildren = vector.getChildrenFromFields();
            if (fields.size() != vectorChildren.size()) {
               int var10002 = fields.size();
               throw new IllegalArgumentException("fields and children are not the same size: " + var10002 + " != " + vectorChildren.size());
            }

            this.nextFieldIs("children");
            this.readToken(JsonToken.START_ARRAY);

            for(int i = 0; i < fields.size(); ++i) {
               Field childField = (Field)fields.get(i);
               FieldVector childVector = (FieldVector)vectorChildren.get(i);
               this.readFromJsonIntoVector(childField, childVector);
            }

            this.readToken(JsonToken.END_ARRAY);
         }

         this.readToken(JsonToken.END_OBJECT);

         for(ArrowBuf buffer : vectorBuffers) {
            buffer.getReferenceManager().release();
         }

      }
   }

   private byte[] decodeHexSafe(String hexString) throws IOException {
      try {
         return Hex.decodeHex(hexString.toCharArray());
      } catch (DecoderException e) {
         throw new IOException("Unable to decode hex string: " + hexString, e);
      }
   }

   public void close() throws IOException {
      this.parser.close();
      if (this.dictionaries != null) {
         for(Dictionary dictionary : this.dictionaries.values()) {
            dictionary.getVector().close();
         }
      }

   }

   private Object readNextField(String expectedFieldName, Class c) throws IOException, JsonParseException {
      this.nextFieldIs(expectedFieldName);
      this.parser.nextToken();
      return this.parser.readValueAs(c);
   }

   private void nextFieldIs(String expectedFieldName) throws IOException, JsonParseException {
      String name = this.parser.nextFieldName();
      if (name == null || !name.equals(expectedFieldName)) {
         throw new IllegalStateException("Expected " + expectedFieldName + " but got " + name);
      }
   }

   private void readToken(JsonToken expected) throws JsonParseException, IOException {
      JsonToken t = this.parser.nextToken();
      if (t != expected) {
         String var10002 = String.valueOf(expected);
         throw new IllegalStateException("Expected " + var10002 + " but got " + String.valueOf(t));
      }
   }

   private abstract class BufferReader {
      protected abstract ArrowBuf read(BufferAllocator var1, int var2) throws IOException;

      ArrowBuf readBuffer(BufferAllocator allocator, int count) throws IOException {
         JsonFileReader.this.readToken(JsonToken.START_ARRAY);
         ArrowBuf buf = this.read(allocator, count);
         JsonFileReader.this.readToken(JsonToken.END_ARRAY);
         return buf;
      }
   }

   private class BufferHelper {
      BufferReader BIT = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            int bufferSize = BitVectorHelper.getValidityBufferSize(count);
            ArrowBuf buf = allocator.buffer((long)bufferSize);
            buf.setZero(0L, (long)bufferSize);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               BitVectorHelper.setValidityBit(buf, i, (Boolean)JsonFileReader.this.parser.readValueAs(Boolean.class) ? 1 : 0);
            }

            buf.writerIndex((long)bufferSize);
            return buf;
         }
      };
      BufferReader DAY_MILLIS = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 8L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.readToken(JsonToken.START_OBJECT);
               buf.writeInt((Integer)JsonFileReader.this.readNextField("days", Integer.class));
               buf.writeInt((Integer)JsonFileReader.this.readNextField("milliseconds", Integer.class));
               JsonFileReader.this.readToken(JsonToken.END_OBJECT);
            }

            return buf;
         }
      };
      BufferReader MONTH_DAY_NANOS = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 16L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.readToken(JsonToken.START_OBJECT);
               buf.writeInt((Integer)JsonFileReader.this.readNextField("months", Integer.class));
               buf.writeInt((Integer)JsonFileReader.this.readNextField("days", Integer.class));
               buf.writeLong((Long)JsonFileReader.this.readNextField("nanoseconds", Long.class));
               JsonFileReader.this.readToken(JsonToken.END_OBJECT);
            }

            return buf;
         }
      };
      BufferReader INT1 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 1L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               buf.writeByte(JsonFileReader.this.parser.getByteValue());
            }

            return buf;
         }
      };
      BufferReader INT2 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 2L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               buf.writeShort(JsonFileReader.this.parser.getShortValue());
            }

            return buf;
         }
      };
      BufferReader INT4 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 4L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               buf.writeInt(JsonFileReader.this.parser.getIntValue());
            }

            return buf;
         }
      };
      BufferReader INT8 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 8L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               String value = JsonFileReader.this.parser.getValueAsString();
               buf.writeLong(Long.valueOf(value));
            }

            return buf;
         }
      };
      BufferReader UINT1 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 1L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               buf.writeByte(JsonFileReader.this.parser.getShortValue() & 255);
            }

            return buf;
         }
      };
      BufferReader UINT2 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 2L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               buf.writeShort(JsonFileReader.this.parser.getIntValue() & '\uffff');
            }

            return buf;
         }
      };
      BufferReader UINT4 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 4L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               buf.writeInt((int)JsonFileReader.this.parser.getLongValue());
            }

            return buf;
         }
      };
      BufferReader UINT8 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 8L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               BigInteger value = new BigInteger(JsonFileReader.this.parser.getValueAsString());
               buf.writeLong(value.longValue());
            }

            return buf;
         }
      };
      BufferReader FLOAT4 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 4L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               buf.writeFloat(JsonFileReader.this.parser.getFloatValue());
            }

            return buf;
         }
      };
      BufferReader FLOAT8 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 8L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               buf.writeDouble(JsonFileReader.this.parser.getDoubleValue());
            }

            return buf;
         }
      };
      BufferReader DECIMAL = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 16L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               BigDecimal decimalValue = new BigDecimal((String)JsonFileReader.this.parser.readValueAs(String.class));
               DecimalUtility.writeBigDecimalToArrowBuf(decimalValue, buf, i, 16);
            }

            buf.writerIndex(size);
            return buf;
         }
      };
      BufferReader DECIMAL256 = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            long size = (long)count * 32L;
            ArrowBuf buf = allocator.buffer(size);

            for(int i = 0; i < count; ++i) {
               JsonFileReader.this.parser.nextToken();
               BigDecimal decimalValue = new BigDecimal((String)JsonFileReader.this.parser.readValueAs(String.class));
               DecimalUtility.writeBigDecimalToArrowBuf(decimalValue, buf, i, 32);
            }

            buf.writerIndex(size);
            return buf;
         }
      };
      BufferReader FIXEDSIZEBINARY = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            return BufferHelper.this.readBinaryValues(allocator, count);
         }
      };
      BufferReader VARCHAR = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            return BufferHelper.this.readStringValues(allocator, count);
         }
      };
      BufferReader LARGEVARCHAR = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            return BufferHelper.this.readStringValues(allocator, count);
         }
      };
      BufferReader VARBINARY = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            return BufferHelper.this.readBinaryValues(allocator, count);
         }
      };
      BufferReader LARGEVARBINARY = new BufferReader() {
         protected ArrowBuf read(BufferAllocator allocator, int count) throws IOException {
            return BufferHelper.this.readBinaryValues(allocator, count);
         }
      };

      ArrowBuf readBinaryValues(BufferAllocator allocator, int count) throws IOException {
         ArrayList<byte[]> values = new ArrayList(count);
         long bufferSize = 0L;

         for(int i = 0; i < count; ++i) {
            JsonFileReader.this.parser.nextToken();
            byte[] value = JsonFileReader.this.decodeHexSafe((String)JsonFileReader.this.parser.readValueAs(String.class));
            values.add(value);
            bufferSize += (long)value.length;
         }

         ArrowBuf buf = allocator.buffer(bufferSize);

         for(byte[] value : values) {
            buf.writeBytes(value);
         }

         return buf;
      }

      ArrowBuf readStringValues(BufferAllocator allocator, int count) throws IOException {
         ArrayList<byte[]> values = new ArrayList(count);
         long bufferSize = 0L;

         for(int i = 0; i < count; ++i) {
            JsonFileReader.this.parser.nextToken();
            byte[] value = JsonFileReader.this.parser.getValueAsString().getBytes(StandardCharsets.UTF_8);
            values.add(value);
            bufferSize += (long)value.length;
         }

         ArrowBuf buf = allocator.buffer(bufferSize);

         for(byte[] value : values) {
            buf.writeBytes(value);
         }

         return buf;
      }
   }
}
