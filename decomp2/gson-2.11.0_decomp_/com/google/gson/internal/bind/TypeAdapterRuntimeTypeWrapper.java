package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class TypeAdapterRuntimeTypeWrapper extends TypeAdapter {
   private final Gson context;
   private final TypeAdapter delegate;
   private final Type type;

   TypeAdapterRuntimeTypeWrapper(Gson context, TypeAdapter delegate, Type type) {
      this.context = context;
      this.delegate = delegate;
      this.type = type;
   }

   public Object read(JsonReader in) throws IOException {
      return this.delegate.read(in);
   }

   public void write(JsonWriter out, Object value) throws IOException {
      TypeAdapter<T> chosen = this.delegate;
      Type runtimeType = getRuntimeTypeIfMoreSpecific(this.type, value);
      if (runtimeType != this.type) {
         TypeAdapter<T> runtimeTypeAdapter = this.context.getAdapter(TypeToken.get(runtimeType));
         if (!(runtimeTypeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter)) {
            chosen = runtimeTypeAdapter;
         } else if (!isReflective(this.delegate)) {
            chosen = this.delegate;
         } else {
            chosen = runtimeTypeAdapter;
         }
      }

      chosen.write(out, value);
   }

   private static boolean isReflective(TypeAdapter typeAdapter) {
      while(true) {
         if (typeAdapter instanceof SerializationDelegatingTypeAdapter) {
            TypeAdapter<?> delegate = ((SerializationDelegatingTypeAdapter)typeAdapter).getSerializationDelegate();
            if (delegate != typeAdapter) {
               typeAdapter = delegate;
               continue;
            }
         }

         return typeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter;
      }
   }

   private static Type getRuntimeTypeIfMoreSpecific(Type type, Object value) {
      if (value != null && (type instanceof Class || type instanceof TypeVariable)) {
         type = value.getClass();
      }

      return type;
   }
}
