package com.google.crypto.tink.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.Nullable;

public final class JsonParser {
   private static final JsonElementTypeAdapter JSON_ELEMENT = new JsonElementTypeAdapter();

   public static boolean isValidString(String s) {
      int length = s.length();
      int i = 0;

      while(i != length) {
         char ch = s.charAt(i);
         ++i;
         if (Character.isSurrogate(ch)) {
            if (Character.isLowSurrogate(ch) || i == length || !Character.isLowSurrogate(s.charAt(i))) {
               return false;
            }

            ++i;
         }
      }

      return true;
   }

   public static JsonElement parse(String json) throws IOException {
      try {
         JsonReader jsonReader = new JsonReader(new StringReader(json));
         jsonReader.setLenient(false);
         return JSON_ELEMENT.read(jsonReader);
      } catch (NumberFormatException e) {
         throw new IOException(e);
      }
   }

   public static long getParsedNumberAsLongOrThrow(Number number) {
      if (!(number instanceof LazilyParsedNumber)) {
         throw new IllegalArgumentException("does not contain a parsed number.");
      } else {
         return Long.parseLong(number.toString());
      }
   }

   private JsonParser() {
   }

   private static final class LazilyParsedNumber extends Number {
      private final String value;

      public LazilyParsedNumber(String value) {
         this.value = value;
      }

      public int intValue() {
         try {
            return Integer.parseInt(this.value);
         } catch (NumberFormatException var4) {
            try {
               return (int)Long.parseLong(this.value);
            } catch (NumberFormatException var3) {
               return (new BigDecimal(this.value)).intValue();
            }
         }
      }

      public long longValue() {
         try {
            return Long.parseLong(this.value);
         } catch (NumberFormatException var2) {
            return (new BigDecimal(this.value)).longValue();
         }
      }

      public float floatValue() {
         return Float.parseFloat(this.value);
      }

      public double doubleValue() {
         return Double.parseDouble(this.value);
      }

      public String toString() {
         return this.value;
      }

      private Object writeReplace() throws NotSerializableException {
         throw new NotSerializableException("serialization is not supported");
      }

      private void readObject(ObjectInputStream in) throws NotSerializableException {
         throw new NotSerializableException("serialization is not supported");
      }

      public int hashCode() {
         return this.value.hashCode();
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj instanceof LazilyParsedNumber) {
            LazilyParsedNumber other = (LazilyParsedNumber)obj;
            return this.value.equals(other.value);
         } else {
            return false;
         }
      }
   }

   private static final class JsonElementTypeAdapter extends TypeAdapter {
      private static final int RECURSION_LIMIT = 100;

      private JsonElementTypeAdapter() {
      }

      @Nullable
      private JsonElement tryBeginNesting(JsonReader in, JsonToken peeked) throws IOException {
         switch (peeked) {
            case BEGIN_ARRAY:
               in.beginArray();
               return new JsonArray();
            case BEGIN_OBJECT:
               in.beginObject();
               return new JsonObject();
            default:
               return null;
         }
      }

      private JsonElement readTerminal(JsonReader in, JsonToken peeked) throws IOException {
         switch (peeked) {
            case STRING:
               String value = in.nextString();
               if (!JsonParser.isValidString(value)) {
                  throw new IOException("illegal characters in string");
               }

               return new JsonPrimitive(value);
            case NUMBER:
               String number = in.nextString();
               return new JsonPrimitive(new LazilyParsedNumber(number));
            case BOOLEAN:
               return new JsonPrimitive(in.nextBoolean());
            case NULL:
               in.nextNull();
               return JsonNull.INSTANCE;
            default:
               throw new IllegalStateException("Unexpected token: " + peeked);
         }
      }

      public JsonElement read(JsonReader in) throws IOException {
         JsonToken peeked = in.peek();
         JsonElement current = this.tryBeginNesting(in, peeked);
         if (current == null) {
            return this.readTerminal(in, peeked);
         } else {
            Deque<JsonElement> stack = new ArrayDeque();

            while(true) {
               while(!in.hasNext()) {
                  if (current instanceof JsonArray) {
                     in.endArray();
                  } else {
                     in.endObject();
                  }

                  if (stack.isEmpty()) {
                     return current;
                  }

                  current = (JsonElement)stack.removeLast();
               }

               String name = null;
               if (current instanceof JsonObject) {
                  name = in.nextName();
                  if (!JsonParser.isValidString(name)) {
                     throw new IOException("illegal characters in string");
                  }
               }

               peeked = in.peek();
               JsonElement value = this.tryBeginNesting(in, peeked);
               boolean isNesting = value != null;
               if (value == null) {
                  value = this.readTerminal(in, peeked);
               }

               if (current instanceof JsonArray) {
                  ((JsonArray)current).add(value);
               } else {
                  if (((JsonObject)current).has(name)) {
                     throw new IOException("duplicate key: " + name);
                  }

                  ((JsonObject)current).add(name, value);
               }

               if (isNesting) {
                  stack.addLast(current);
                  if (stack.size() > 100) {
                     throw new IOException("too many recursions");
                  }

                  current = value;
               }
            }
         }
      }

      public void write(JsonWriter out, JsonElement value) {
         throw new UnsupportedOperationException("write is not supported");
      }
   }
}
