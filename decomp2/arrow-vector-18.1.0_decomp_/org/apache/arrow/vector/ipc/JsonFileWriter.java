package org.apache.arrow.vector.ipc;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.NopIndenter;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BaseVariableWidthVector;
import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.BitVectorHelper;
import org.apache.arrow.vector.BufferLayout;
import org.apache.arrow.vector.DateDayVector;
import org.apache.arrow.vector.DateMilliVector;
import org.apache.arrow.vector.Decimal256Vector;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.DurationVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.FixedSizeBinaryVector;
import org.apache.arrow.vector.Float4Vector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.IntervalDayVector;
import org.apache.arrow.vector.IntervalMonthDayNanoVector;
import org.apache.arrow.vector.IntervalYearVector;
import org.apache.arrow.vector.SmallIntVector;
import org.apache.arrow.vector.TimeMicroVector;
import org.apache.arrow.vector.TimeMilliVector;
import org.apache.arrow.vector.TimeNanoVector;
import org.apache.arrow.vector.TimeSecVector;
import org.apache.arrow.vector.TimeStampMicroTZVector;
import org.apache.arrow.vector.TimeStampMicroVector;
import org.apache.arrow.vector.TimeStampMilliTZVector;
import org.apache.arrow.vector.TimeStampMilliVector;
import org.apache.arrow.vector.TimeStampNanoTZVector;
import org.apache.arrow.vector.TimeStampNanoVector;
import org.apache.arrow.vector.TimeStampSecTZVector;
import org.apache.arrow.vector.TimeStampSecVector;
import org.apache.arrow.vector.TinyIntVector;
import org.apache.arrow.vector.TypeLayout;
import org.apache.arrow.vector.UInt1Vector;
import org.apache.arrow.vector.UInt2Vector;
import org.apache.arrow.vector.UInt4Vector;
import org.apache.arrow.vector.UInt8Vector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.util.DecimalUtility;
import org.apache.arrow.vector.util.DictionaryUtility;
import org.apache.commons.codec.binary.Hex;

public class JsonFileWriter implements AutoCloseable {
   private final JsonGenerator generator;
   private Schema schema;

   public static JSONWriteConfig config() {
      return new JSONWriteConfig();
   }

   public JsonFileWriter(File outputFile) throws IOException {
      this(outputFile, config());
   }

   public JsonFileWriter(File outputFile, JSONWriteConfig config) throws IOException {
      MappingJsonFactory jsonFactory = new MappingJsonFactory();
      this.generator = jsonFactory.createGenerator(outputFile, JsonEncoding.UTF8);
      if (config.pretty) {
         DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
         prettyPrinter.indentArraysWith(NopIndenter.instance);
         this.generator.setPrettyPrinter(prettyPrinter);
      }

      this.generator.configure(Feature.QUOTE_NON_NUMERIC_NUMBERS, false);
   }

   public void start(Schema schema, DictionaryProvider provider) throws IOException {
      List<Field> fields = new ArrayList(schema.getFields().size());
      Set<Long> dictionaryIdsUsed = new HashSet();
      this.schema = schema;

      for(Field field : schema.getFields()) {
         fields.add(DictionaryUtility.toMessageFormat(field, provider, dictionaryIdsUsed));
      }

      Schema updatedSchema = new Schema(fields, schema.getCustomMetadata());
      this.generator.writeStartObject();
      this.generator.writeObjectField("schema", updatedSchema);
      if (!dictionaryIdsUsed.isEmpty()) {
         this.writeDictionaryBatches(this.generator, dictionaryIdsUsed, provider);
      }

      this.generator.writeArrayFieldStart("batches");
   }

   private void writeDictionaryBatches(JsonGenerator generator, Set dictionaryIdsUsed, DictionaryProvider provider) throws IOException {
      generator.writeArrayFieldStart("dictionaries");

      for(Long id : dictionaryIdsUsed) {
         generator.writeStartObject();
         generator.writeObjectField("id", id);
         generator.writeFieldName("data");
         Dictionary dictionary = provider.lookup(id);
         FieldVector vector = dictionary.getVector();
         List<Field> fields = Collections.singletonList(vector.getField());
         List<FieldVector> vectors = Collections.singletonList(vector);
         VectorSchemaRoot root = new VectorSchemaRoot(fields, vectors, vector.getValueCount());
         this.writeBatch(root);
         generator.writeEndObject();
      }

      generator.writeEndArray();
   }

   public void write(VectorSchemaRoot recordBatch) throws IOException {
      if (!recordBatch.getSchema().equals(this.schema)) {
         throw new IllegalArgumentException("record batches must have the same schema: " + String.valueOf(this.schema));
      } else {
         this.writeBatch(recordBatch);
      }
   }

   private void writeBatch(VectorSchemaRoot recordBatch) throws IOException {
      this.generator.writeStartObject();
      this.generator.writeObjectField("count", recordBatch.getRowCount());
      this.generator.writeArrayFieldStart("columns");

      for(Field field : recordBatch.getSchema().getFields()) {
         FieldVector vector = recordBatch.getVector(field);
         this.writeFromVectorIntoJson(field, vector);
      }

      this.generator.writeEndArray();
      this.generator.writeEndObject();
   }

   private void writeFromVectorIntoJson(Field field, FieldVector vector) throws IOException {
      TypeLayout typeLayout = TypeLayout.getTypeLayout(field.getType());
      List<BufferLayout.BufferType> vectorTypes = typeLayout.getBufferTypes();
      List<ArrowBuf> vectorBuffers = vector.getFieldBuffers();
      if (typeLayout.isFixedBufferCount()) {
         if (vectorTypes.size() != vectorBuffers.size()) {
            int var10002 = vectorTypes.size();
            throw new IllegalArgumentException("vector types and inner vector buffers are not the same size: " + var10002 + " != " + vectorBuffers.size());
         }
      } else {
         vectorTypes.add(BufferLayout.BufferType.VARIADIC_DATA_BUFFERS);
      }

      this.generator.writeStartObject();
      this.generator.writeObjectField("name", field.getName());
      int valueCount = vector.getValueCount();
      this.generator.writeObjectField("count", valueCount);

      for(int v = 0; v < vectorTypes.size(); ++v) {
         BufferLayout.BufferType bufferType = (BufferLayout.BufferType)vectorTypes.get(v);
         ArrowBuf vectorBuffer = (ArrowBuf)vectorBuffers.get(v);
         this.generator.writeArrayFieldStart(bufferType.getName());
         int bufferValueCount = bufferType.equals(BufferLayout.BufferType.OFFSET) && vector.getMinorType() != Types.MinorType.DENSEUNION && vector.getMinorType() != Types.MinorType.LISTVIEW && vector.getMinorType() != Types.MinorType.LARGELISTVIEW ? valueCount + 1 : valueCount;

         for(int i = 0; i < bufferValueCount; ++i) {
            if (!bufferType.equals(BufferLayout.BufferType.DATA) || vector.getMinorType() != Types.MinorType.VARCHAR && vector.getMinorType() != Types.MinorType.VARBINARY) {
               if (!bufferType.equals(BufferLayout.BufferType.VIEWS) || vector.getMinorType() != Types.MinorType.VIEWVARCHAR && vector.getMinorType() != Types.MinorType.VIEWVARBINARY) {
                  if (!bufferType.equals(BufferLayout.BufferType.VARIADIC_DATA_BUFFERS) || vector.getMinorType() != Types.MinorType.VIEWVARCHAR && vector.getMinorType() != Types.MinorType.VIEWVARBINARY) {
                     if (!bufferType.equals(BufferLayout.BufferType.OFFSET) || vector.getValueCount() != 0 || vector.getMinorType() != Types.MinorType.LIST && vector.getMinorType() != Types.MinorType.LISTVIEW && vector.getMinorType() != Types.MinorType.MAP && vector.getMinorType() != Types.MinorType.VARBINARY && vector.getMinorType() != Types.MinorType.VARCHAR) {
                        if (bufferType.equals(BufferLayout.BufferType.OFFSET) && vector.getValueCount() == 0 && (vector.getMinorType() == Types.MinorType.LARGELIST || vector.getMinorType() == Types.MinorType.LARGELISTVIEW || vector.getMinorType() == Types.MinorType.LARGEVARBINARY || vector.getMinorType() == Types.MinorType.LARGEVARCHAR)) {
                           ArrowBuf vectorBufferTmp = vector.getAllocator().buffer(8L);

                           try {
                              vectorBufferTmp.setLong(0L, 0L);
                              this.writeValueToGenerator(bufferType, vectorBufferTmp, (ArrowBuf)null, vector, i);
                           } catch (Throwable var17) {
                              if (vectorBufferTmp != null) {
                                 try {
                                    vectorBufferTmp.close();
                                 } catch (Throwable var15) {
                                    var17.addSuppressed(var15);
                                 }
                              }

                              throw var17;
                           }

                           if (vectorBufferTmp != null) {
                              vectorBufferTmp.close();
                           }
                        } else {
                           this.writeValueToGenerator(bufferType, vectorBuffer, (ArrowBuf)null, vector, i);
                        }
                     } else {
                        ArrowBuf vectorBufferTmp = vector.getAllocator().buffer(4L);

                        try {
                           vectorBufferTmp.setInt(0L, 0);
                           this.writeValueToGenerator(bufferType, vectorBufferTmp, (ArrowBuf)null, vector, i);
                        } catch (Throwable var18) {
                           if (vectorBufferTmp != null) {
                              try {
                                 vectorBufferTmp.close();
                              } catch (Throwable var16) {
                                 var18.addSuppressed(var16);
                              }
                           }

                           throw var18;
                        }

                        if (vectorBufferTmp != null) {
                           vectorBufferTmp.close();
                        }
                     }
                  } else {
                     ArrowBuf viewBuffer = (ArrowBuf)vectorBuffers.get(1);
                     List<ArrowBuf> dataBuffers = vectorBuffers.subList(v, vectorBuffers.size());
                     if (!dataBuffers.isEmpty()) {
                        this.writeValueToDataBufferGenerator(bufferType, viewBuffer, dataBuffers, vector);
                        break;
                     }
                  }
               } else {
                  ArrowBuf viewBuffer = (ArrowBuf)vectorBuffers.get(1);
                  List<ArrowBuf> dataBuffers = vectorBuffers.subList(v + 1, vectorBuffers.size());
                  this.writeValueToViewGenerator(bufferType, viewBuffer, dataBuffers, vector, i);
               }
            } else {
               this.writeValueToGenerator(bufferType, vectorBuffer, (ArrowBuf)vectorBuffers.get(v - 1), vector, i);
            }
         }

         this.generator.writeEndArray();
      }

      List<Field> fields = field.getChildren();
      List<FieldVector> children = vector.getChildrenFromFields();
      if (fields.size() != children.size()) {
         int var28 = fields.size();
         throw new IllegalArgumentException("fields and children are not the same size: " + var28 + " != " + children.size());
      } else {
         if (fields.size() > 0) {
            this.generator.writeArrayFieldStart("children");

            for(int i = 0; i < fields.size(); ++i) {
               Field childField = (Field)fields.get(i);
               FieldVector childVector = (FieldVector)children.get(i);
               this.writeFromVectorIntoJson(childField, childVector);
            }

            this.generator.writeEndArray();
         }

         this.generator.writeEndObject();
      }
   }

   private byte[] getView(ArrowBuf viewBuffer, List dataBuffers, int index) {
      int dataLength = viewBuffer.getInt((long)index * 16L);
      byte[] result = new byte[dataLength];
      int inlineSize = 12;
      int elementSize = 16;
      int lengthWidth = 4;
      int prefixWidth = 4;
      int bufIndexWidth = 4;
      if (dataLength > 12) {
         int bufferIndex = viewBuffer.getInt((long)index * 16L + 4L + 4L);
         int dataOffset = viewBuffer.getInt((long)index * 16L + 4L + 4L + 4L);
         ((ArrowBuf)dataBuffers.get(bufferIndex)).getBytes((long)dataOffset, result, 0, dataLength);
      } else {
         viewBuffer.getBytes((long)index * 16L + 4L, result, 0, dataLength);
      }

      return result;
   }

   private void writeValueToViewGenerator(BufferLayout.BufferType bufferType, ArrowBuf viewBuffer, List dataBuffers, FieldVector vector, int index) throws IOException {
      Preconditions.checkNotNull(viewBuffer);
      byte[] b = this.getView(viewBuffer, dataBuffers, index);
      int elementSize = 16;
      int lengthWidth = 4;
      int prefixWidth = 4;
      int bufIndexWidth = 4;
      int length = viewBuffer.getInt((long)index * 16L);
      this.generator.writeStartObject();
      this.generator.writeFieldName("SIZE");
      this.generator.writeObject(length);
      if (length > 12) {
         byte[] prefix = Arrays.copyOfRange(b, 0, 4);
         int bufferIndex = viewBuffer.getInt((long)index * 16L + 4L + 4L);
         int dataOffset = viewBuffer.getInt((long)index * 16L + 4L + 4L + 4L);
         this.generator.writeFieldName("PREFIX_HEX");
         this.generator.writeString(Hex.encodeHexString(prefix));
         this.generator.writeFieldName("BUFFER_INDEX");
         this.generator.writeObject(bufferIndex);
         this.generator.writeFieldName("OFFSET");
         this.generator.writeObject(dataOffset);
      } else {
         this.generator.writeFieldName("INLINED");
         if (vector.getMinorType() == Types.MinorType.VIEWVARCHAR) {
            this.generator.writeString(new String(b, "UTF-8"));
         } else {
            this.generator.writeString(Hex.encodeHexString(b));
         }
      }

      this.generator.writeEndObject();
   }

   private void writeValueToDataBufferGenerator(BufferLayout.BufferType bufferType, ArrowBuf viewBuffer, List dataBuffers, FieldVector vector) throws IOException {
      if (bufferType.equals(BufferLayout.BufferType.VARIADIC_DATA_BUFFERS)) {
         Preconditions.checkNotNull(viewBuffer);
         Preconditions.checkArgument(!dataBuffers.isEmpty());

         for(int i = 0; i < dataBuffers.size(); ++i) {
            ArrowBuf dataBuf = (ArrowBuf)dataBuffers.get(i);
            byte[] result = new byte[(int)dataBuf.writerIndex()];
            dataBuf.getBytes(0L, result);
            if (result != null) {
               this.generator.writeString(Hex.encodeHexString(result));
            }
         }
      }

   }

   private void writeValueToGenerator(BufferLayout.BufferType bufferType, ArrowBuf buffer, ArrowBuf offsetBuffer, FieldVector vector, int index) throws IOException {
      if (bufferType.equals(BufferLayout.BufferType.TYPE)) {
         this.generator.writeNumber((short)buffer.getByte((long)(index * 1)));
      } else if (bufferType.equals(BufferLayout.BufferType.OFFSET)) {
         switch (vector.getMinorType()) {
            case VARCHAR:
            case VARBINARY:
            case LIST:
            case MAP:
               this.generator.writeNumber(buffer.getInt((long)index * 4L));
               break;
            case LISTVIEW:
               this.generator.writeNumber(buffer.getInt((long)index * 4L));
               break;
            case LARGELISTVIEW:
               this.generator.writeNumber(buffer.getInt((long)index * 8L));
               break;
            case LARGELIST:
            case LARGEVARBINARY:
            case LARGEVARCHAR:
               this.generator.writeNumber(buffer.getLong((long)index * 8L));
               break;
            default:
               throw new IllegalArgumentException("Type has no offset buffer: " + String.valueOf(vector.getField()));
         }
      } else if (bufferType.equals(BufferLayout.BufferType.VALIDITY)) {
         this.generator.writeNumber(vector.isNull(index) ? 0 : 1);
      } else if (bufferType.equals(BufferLayout.BufferType.DATA)) {
         switch (vector.getMinorType()) {
            case VARCHAR:
               Preconditions.checkNotNull(offsetBuffer);
               byte[] b = BaseVariableWidthVector.get(buffer, offsetBuffer, index);
               this.generator.writeString(new String(b, "UTF-8"));
               break;
            case VARBINARY:
               Preconditions.checkNotNull(offsetBuffer);
               String hexString = Hex.encodeHexString(BaseVariableWidthVector.get(buffer, offsetBuffer, index));
               this.generator.writeObject(hexString);
               break;
            case LIST:
            case MAP:
            case LISTVIEW:
            case LARGELISTVIEW:
            case LARGELIST:
            case LARGEVARBINARY:
            case LARGEVARCHAR:
            default:
               throw new UnsupportedOperationException("minor type: " + String.valueOf(vector.getMinorType()));
            case TINYINT:
               this.generator.writeNumber((short)TinyIntVector.get(buffer, index));
               break;
            case SMALLINT:
               this.generator.writeNumber(SmallIntVector.get(buffer, index));
               break;
            case INT:
               this.generator.writeNumber(IntVector.get(buffer, index));
               break;
            case BIGINT:
               this.generator.writeString(String.valueOf(BigIntVector.get(buffer, index)));
               break;
            case UINT1:
               this.generator.writeNumber(UInt1Vector.getNoOverflow(buffer, index));
               break;
            case UINT2:
               this.generator.writeNumber(UInt2Vector.get(buffer, index));
               break;
            case UINT4:
               this.generator.writeNumber(UInt4Vector.getNoOverflow(buffer, index));
               break;
            case UINT8:
               this.generator.writeString(UInt8Vector.getNoOverflow(buffer, index).toString());
               break;
            case FLOAT4:
               this.generator.writeNumber(Float4Vector.get(buffer, index));
               break;
            case FLOAT8:
               this.generator.writeNumber(Float8Vector.get(buffer, index));
               break;
            case DATEDAY:
               this.generator.writeNumber(DateDayVector.get(buffer, index));
               break;
            case DATEMILLI:
               this.generator.writeNumber(DateMilliVector.get(buffer, index));
               break;
            case TIMESEC:
               this.generator.writeNumber(TimeSecVector.get(buffer, index));
               break;
            case TIMEMILLI:
               this.generator.writeNumber(TimeMilliVector.get(buffer, index));
               break;
            case TIMEMICRO:
               this.generator.writeNumber(TimeMicroVector.get(buffer, index));
               break;
            case TIMENANO:
               this.generator.writeNumber(TimeNanoVector.get(buffer, index));
               break;
            case TIMESTAMPSEC:
               this.generator.writeNumber(TimeStampSecVector.get(buffer, index));
               break;
            case TIMESTAMPMILLI:
               this.generator.writeNumber(TimeStampMilliVector.get(buffer, index));
               break;
            case TIMESTAMPMICRO:
               this.generator.writeNumber(TimeStampMicroVector.get(buffer, index));
               break;
            case TIMESTAMPNANO:
               this.generator.writeNumber(TimeStampNanoVector.get(buffer, index));
               break;
            case TIMESTAMPSECTZ:
               this.generator.writeNumber(TimeStampSecTZVector.get(buffer, index));
               break;
            case TIMESTAMPMILLITZ:
               this.generator.writeNumber(TimeStampMilliTZVector.get(buffer, index));
               break;
            case TIMESTAMPMICROTZ:
               this.generator.writeNumber(TimeStampMicroTZVector.get(buffer, index));
               break;
            case TIMESTAMPNANOTZ:
               this.generator.writeNumber(TimeStampNanoTZVector.get(buffer, index));
               break;
            case DURATION:
               this.generator.writeNumber(DurationVector.get(buffer, index));
               break;
            case INTERVALYEAR:
               this.generator.writeNumber(IntervalYearVector.getTotalMonths(buffer, index));
               break;
            case INTERVALDAY:
               this.generator.writeStartObject();
               this.generator.writeObjectField("days", IntervalDayVector.getDays(buffer, index));
               this.generator.writeObjectField("milliseconds", IntervalDayVector.getMilliseconds(buffer, index));
               this.generator.writeEndObject();
               break;
            case INTERVALMONTHDAYNANO:
               this.generator.writeStartObject();
               this.generator.writeObjectField("months", IntervalMonthDayNanoVector.getMonths(buffer, index));
               this.generator.writeObjectField("days", IntervalMonthDayNanoVector.getDays(buffer, index));
               this.generator.writeObjectField("nanoseconds", IntervalMonthDayNanoVector.getNanoseconds(buffer, index));
               this.generator.writeEndObject();
               break;
            case BIT:
               this.generator.writeNumber(BitVectorHelper.get(buffer, index));
               break;
            case FIXEDSIZEBINARY:
               int byteWidth = ((FixedSizeBinaryVector)vector).getByteWidth();
               String fixedSizeHexString = Hex.encodeHexString(FixedSizeBinaryVector.get(buffer, index, byteWidth));
               this.generator.writeObject(fixedSizeHexString);
               break;
            case DECIMAL:
               int scale = ((DecimalVector)vector).getScale();
               BigDecimal decimalValue = DecimalUtility.getBigDecimalFromArrowBuf(buffer, index, scale, 16);
               this.generator.writeString(decimalValue.unscaledValue().toString());
               break;
            case DECIMAL256:
               int scale = ((Decimal256Vector)vector).getScale();
               BigDecimal decimalValue = DecimalUtility.getBigDecimalFromArrowBuf(buffer, index, scale, 32);
               this.generator.writeString(decimalValue.unscaledValue().toString());
         }
      } else if (bufferType.equals(BufferLayout.BufferType.SIZE)) {
         if (vector.getMinorType() == Types.MinorType.LISTVIEW) {
            this.generator.writeNumber(buffer.getInt((long)index * 4L));
         } else {
            this.generator.writeNumber(buffer.getInt((long)index * 8L));
         }
      }

   }

   public void close() throws IOException {
      this.generator.writeEndArray();
      this.generator.writeEndObject();
      this.generator.close();
   }

   public static final class JSONWriteConfig {
      private final boolean pretty;

      private JSONWriteConfig(boolean pretty) {
         this.pretty = pretty;
      }

      private JSONWriteConfig() {
         this.pretty = false;
      }

      public JSONWriteConfig pretty(boolean pretty) {
         return new JSONWriteConfig(pretty);
      }
   }
}
