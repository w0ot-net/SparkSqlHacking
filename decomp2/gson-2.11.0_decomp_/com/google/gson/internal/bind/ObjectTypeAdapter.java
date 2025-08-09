package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public final class ObjectTypeAdapter extends TypeAdapter {
   private static final TypeAdapterFactory DOUBLE_FACTORY;
   private final Gson gson;
   private final ToNumberStrategy toNumberStrategy;

   private ObjectTypeAdapter(Gson gson, ToNumberStrategy toNumberStrategy) {
      this.gson = gson;
      this.toNumberStrategy = toNumberStrategy;
   }

   private static TypeAdapterFactory newFactory(final ToNumberStrategy toNumberStrategy) {
      return new TypeAdapterFactory() {
         public TypeAdapter create(Gson gson, TypeToken type) {
            return type.getRawType() == Object.class ? new ObjectTypeAdapter(gson, toNumberStrategy) : null;
         }
      };
   }

   public static TypeAdapterFactory getFactory(ToNumberStrategy toNumberStrategy) {
      return toNumberStrategy == ToNumberPolicy.DOUBLE ? DOUBLE_FACTORY : newFactory(toNumberStrategy);
   }

   private Object tryBeginNesting(JsonReader in, JsonToken peeked) throws IOException {
      switch (peeked) {
         case BEGIN_ARRAY:
            in.beginArray();
            return new ArrayList();
         case BEGIN_OBJECT:
            in.beginObject();
            return new LinkedTreeMap();
         default:
            return null;
      }
   }

   private Object readTerminal(JsonReader in, JsonToken peeked) throws IOException {
      switch (peeked) {
         case STRING:
            return in.nextString();
         case NUMBER:
            return this.toNumberStrategy.readNumber(in);
         case BOOLEAN:
            return in.nextBoolean();
         case NULL:
            in.nextNull();
            return null;
         default:
            throw new IllegalStateException("Unexpected token: " + peeked);
      }
   }

   public Object read(JsonReader in) throws IOException {
      JsonToken peeked = in.peek();
      Object current = this.tryBeginNesting(in, peeked);
      if (current == null) {
         return this.readTerminal(in, peeked);
      } else {
         Deque<Object> stack = new ArrayDeque();

         while(true) {
            while(!in.hasNext()) {
               if (current instanceof List) {
                  in.endArray();
               } else {
                  in.endObject();
               }

               if (stack.isEmpty()) {
                  return current;
               }

               current = stack.removeLast();
            }

            String name = null;
            if (current instanceof Map) {
               name = in.nextName();
            }

            peeked = in.peek();
            Object value = this.tryBeginNesting(in, peeked);
            boolean isNesting = value != null;
            if (value == null) {
               value = this.readTerminal(in, peeked);
            }

            if (current instanceof List) {
               List<Object> list = (List)current;
               list.add(value);
            } else {
               Map<String, Object> map = (Map)current;
               map.put(name, value);
            }

            if (isNesting) {
               stack.addLast(current);
               current = value;
            }
         }
      }
   }

   public void write(JsonWriter out, Object value) throws IOException {
      if (value == null) {
         out.nullValue();
      } else {
         TypeAdapter<Object> typeAdapter = this.gson.getAdapter(value.getClass());
         if (typeAdapter instanceof ObjectTypeAdapter) {
            out.beginObject();
            out.endObject();
         } else {
            typeAdapter.write(out, value);
         }
      }
   }

   static {
      DOUBLE_FACTORY = newFactory(ToNumberPolicy.DOUBLE);
   }
}
