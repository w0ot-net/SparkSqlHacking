package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.ObjectMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

public class JavaSerializer extends Serializer {
   public void write(Kryo kryo, Output output, Object object) {
      try {
         ObjectMap graphContext = kryo.getGraphContext();
         ObjectOutputStream objectStream = (ObjectOutputStream)graphContext.get(this);
         if (objectStream == null) {
            objectStream = new ObjectOutputStream(output);
            graphContext.put(this, objectStream);
         }

         objectStream.writeObject(object);
         objectStream.flush();
      } catch (Exception ex) {
         throw new KryoException("Error during Java serialization.", ex);
      }
   }

   public Object read(Kryo kryo, Input input, Class type) {
      try {
         ObjectMap graphContext = kryo.getGraphContext();
         ObjectInputStream objectStream = (ObjectInputStream)graphContext.get(this);
         if (objectStream == null) {
            objectStream = new ObjectInputStreamWithKryoClassLoader(input, kryo);
            graphContext.put(this, objectStream);
         }

         return objectStream.readObject();
      } catch (Exception ex) {
         throw new KryoException("Error during Java deserialization.", ex);
      }
   }

   private static class ObjectInputStreamWithKryoClassLoader extends ObjectInputStream {
      private final ClassLoader loader;

      ObjectInputStreamWithKryoClassLoader(InputStream in, Kryo kryo) throws IOException {
         super(in);
         this.loader = kryo.getClassLoader();
      }

      protected Class resolveClass(ObjectStreamClass desc) {
         try {
            return Class.forName(desc.getName(), false, this.loader);
         } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found: " + desc.getName(), e);
         }
      }
   }
}
