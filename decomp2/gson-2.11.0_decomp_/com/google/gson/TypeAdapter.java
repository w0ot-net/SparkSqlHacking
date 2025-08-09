package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class TypeAdapter {
   public abstract void write(JsonWriter var1, Object var2) throws IOException;

   public final void toJson(Writer out, Object value) throws IOException {
      JsonWriter writer = new JsonWriter(out);
      this.write(writer, value);
   }

   public final String toJson(Object value) {
      StringWriter stringWriter = new StringWriter();

      try {
         this.toJson(stringWriter, value);
      } catch (IOException e) {
         throw new JsonIOException(e);
      }

      return stringWriter.toString();
   }

   public final JsonElement toJsonTree(Object value) {
      try {
         JsonTreeWriter jsonWriter = new JsonTreeWriter();
         this.write(jsonWriter, value);
         return jsonWriter.get();
      } catch (IOException e) {
         throw new JsonIOException(e);
      }
   }

   public abstract Object read(JsonReader var1) throws IOException;

   public final Object fromJson(Reader in) throws IOException {
      JsonReader reader = new JsonReader(in);
      return this.read(reader);
   }

   public final Object fromJson(String json) throws IOException {
      return this.fromJson((Reader)(new StringReader(json)));
   }

   public final Object fromJsonTree(JsonElement jsonTree) {
      try {
         JsonReader jsonReader = new JsonTreeReader(jsonTree);
         return this.read(jsonReader);
      } catch (IOException e) {
         throw new JsonIOException(e);
      }
   }

   public final TypeAdapter nullSafe() {
      return new TypeAdapter() {
         public void write(JsonWriter out, Object value) throws IOException {
            if (value == null) {
               out.nullValue();
            } else {
               TypeAdapter.this.write(out, value);
            }

         }

         public Object read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
               reader.nextNull();
               return null;
            } else {
               return TypeAdapter.this.read(reader);
            }
         }
      };
   }
}
