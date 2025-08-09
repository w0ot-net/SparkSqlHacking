package org.apache.avro.data;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.ResolvingDecoder;
import org.apache.avro.util.internal.JacksonUtils;

public class Json {
   static final JsonFactory FACTORY = new JsonFactory();
   static final ObjectMapper MAPPER;
   public static final Schema SCHEMA;

   private Json() {
   }

   public static Object parseJson(String s) {
      try {
         return JacksonUtils.toObject((JsonNode)MAPPER.readTree(FACTORY.createParser(s)));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static String toString(Object datum) {
      return JacksonUtils.toJsonNode(datum).toString();
   }

   private static void write(JsonNode node, Encoder out) throws IOException {
      switch (node.asToken()) {
         case VALUE_NUMBER_INT:
            out.writeIndex(Json.JsonType.LONG.ordinal());
            out.writeLong(node.longValue());
            break;
         case VALUE_NUMBER_FLOAT:
            out.writeIndex(Json.JsonType.DOUBLE.ordinal());
            out.writeDouble(node.doubleValue());
            break;
         case VALUE_STRING:
            out.writeIndex(Json.JsonType.STRING.ordinal());
            out.writeString(node.textValue());
            break;
         case VALUE_TRUE:
            out.writeIndex(Json.JsonType.BOOLEAN.ordinal());
            out.writeBoolean(true);
            break;
         case VALUE_FALSE:
            out.writeIndex(Json.JsonType.BOOLEAN.ordinal());
            out.writeBoolean(false);
            break;
         case VALUE_NULL:
            out.writeIndex(Json.JsonType.NULL.ordinal());
            out.writeNull();
            break;
         case START_ARRAY:
            out.writeIndex(Json.JsonType.ARRAY.ordinal());
            out.writeArrayStart();
            out.setItemCount((long)node.size());

            for(JsonNode element : node) {
               out.startItem();
               write(element, out);
            }

            out.writeArrayEnd();
            break;
         case START_OBJECT:
            out.writeIndex(Json.JsonType.OBJECT.ordinal());
            out.writeMapStart();
            out.setItemCount((long)node.size());
            Iterator<String> i = node.fieldNames();

            while(i.hasNext()) {
               out.startItem();
               String name = (String)i.next();
               out.writeString(name);
               write(node.get(name), out);
            }

            out.writeMapEnd();
            break;
         default:
            String var10002 = String.valueOf(node.asToken());
            throw new AvroRuntimeException(var10002 + " unexpected: " + String.valueOf(node));
      }

   }

   private static JsonNode read(Decoder in) throws IOException {
      switch (Json.JsonType.values()[in.readIndex()].ordinal()) {
         case 0:
            return new LongNode(in.readLong());
         case 1:
            return new DoubleNode(in.readDouble());
         case 2:
            return new TextNode(in.readString());
         case 3:
            return in.readBoolean() ? BooleanNode.TRUE : BooleanNode.FALSE;
         case 4:
            in.readNull();
            return NullNode.getInstance();
         case 5:
            ArrayNode array = JsonNodeFactory.instance.arrayNode();

            for(long l = in.readArrayStart(); l > 0L; l = in.arrayNext()) {
               for(long i = 0L; i < l; ++i) {
                  array.add(read(in));
               }
            }

            return array;
         case 6:
            ObjectNode object = JsonNodeFactory.instance.objectNode();

            for(long l = in.readMapStart(); l > 0L; l = in.mapNext()) {
               for(long i = 0L; i < l; ++i) {
                  object.set(in.readString(), read(in));
               }
            }

            return object;
         default:
            throw new AvroRuntimeException("Unexpected Json node type");
      }
   }

   private static void writeObject(Object datum, Encoder out) throws IOException {
      write(JacksonUtils.toJsonNode(datum), out);
   }

   private static Object readObject(Decoder in) throws IOException {
      return JacksonUtils.toObject(read(in));
   }

   static {
      MAPPER = new ObjectMapper(FACTORY);

      try {
         InputStream in = Json.class.getResourceAsStream("/org/apache/avro/data/Json.avsc");

         try {
            SCHEMA = (new Schema.Parser()).parse(in);
         } catch (Throwable var4) {
            if (in != null) {
               try {
                  in.close();
               } catch (Throwable var3) {
                  var4.addSuppressed(var3);
               }
            }

            throw var4;
         }

         if (in != null) {
            in.close();
         }

      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      }
   }

   public static class ObjectWriter implements DatumWriter {
      public void setSchema(Schema schema) {
         if (!Json.SCHEMA.equals(schema)) {
            throw new RuntimeException("Not the Json schema: " + String.valueOf(schema));
         }
      }

      public void write(Object datum, Encoder out) throws IOException {
         Json.writeObject(datum, out);
      }
   }

   public static class ObjectReader implements DatumReader {
      private Schema written;
      private ResolvingDecoder resolver;

      public void setSchema(Schema schema) {
         this.written = Json.SCHEMA.equals(this.written) ? null : schema;
      }

      public Object read(Object reuse, Decoder in) throws IOException {
         if (this.written == null) {
            return Json.readObject(in);
         } else {
            if (this.resolver == null) {
               this.resolver = DecoderFactory.get().resolvingDecoder(this.written, Json.SCHEMA, (Decoder)null);
            }

            this.resolver.configure(in);
            Object result = Json.readObject(this.resolver);
            this.resolver.drain();
            return result;
         }
      }
   }

   private static enum JsonType {
      LONG,
      DOUBLE,
      STRING,
      BOOLEAN,
      NULL,
      ARRAY,
      OBJECT;

      // $FF: synthetic method
      private static JsonType[] $values() {
         return new JsonType[]{LONG, DOUBLE, STRING, BOOLEAN, NULL, ARRAY, OBJECT};
      }
   }
}
