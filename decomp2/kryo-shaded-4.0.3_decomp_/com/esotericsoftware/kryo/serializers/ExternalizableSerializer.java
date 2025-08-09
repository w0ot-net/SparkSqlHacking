package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.KryoObjectInput;
import com.esotericsoftware.kryo.io.KryoObjectOutput;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.ObjectMap;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;

public class ExternalizableSerializer extends Serializer {
   private ObjectMap javaSerializerByType;
   private KryoObjectInput objectInput = null;
   private KryoObjectOutput objectOutput = null;

   public void write(Kryo kryo, Output output, Object object) {
      JavaSerializer serializer = this.getJavaSerializerIfRequired(object.getClass());
      if (serializer == null) {
         this.writeExternal(kryo, output, object);
      } else {
         serializer.write(kryo, output, object);
      }

   }

   public Object read(Kryo kryo, Input input, Class type) {
      JavaSerializer serializer = this.getJavaSerializerIfRequired(type);
      return serializer == null ? this.readExternal(kryo, input, type) : serializer.read(kryo, input, type);
   }

   private void writeExternal(Kryo kryo, Output output, Object object) {
      try {
         ((Externalizable)object).writeExternal(this.getObjectOutput(kryo, output));
      } catch (ClassCastException e) {
         throw new KryoException(e);
      } catch (IOException e) {
         throw new KryoException(e);
      }
   }

   private Object readExternal(Kryo kryo, Input input, Class type) {
      try {
         Externalizable object = (Externalizable)kryo.newInstance(type);
         object.readExternal(this.getObjectInput(kryo, input));
         return object;
      } catch (ClassCastException e) {
         throw new KryoException(e);
      } catch (ClassNotFoundException e) {
         throw new KryoException(e);
      } catch (IOException e) {
         throw new KryoException(e);
      }
   }

   private ObjectOutput getObjectOutput(Kryo kryo, Output output) {
      if (this.objectOutput == null) {
         this.objectOutput = new KryoObjectOutput(kryo, output);
      } else {
         this.objectOutput.setOutput(output);
      }

      return this.objectOutput;
   }

   private ObjectInput getObjectInput(Kryo kryo, Input input) {
      if (this.objectInput == null) {
         this.objectInput = new KryoObjectInput(kryo, input);
      } else {
         this.objectInput.setInput(input);
      }

      return this.objectInput;
   }

   private JavaSerializer getJavaSerializerIfRequired(Class type) {
      JavaSerializer javaSerializer = this.getCachedSerializer(type);
      if (javaSerializer == null && this.isJavaSerializerRequired(type)) {
         javaSerializer = new JavaSerializer();
      }

      return javaSerializer;
   }

   private JavaSerializer getCachedSerializer(Class type) {
      if (this.javaSerializerByType == null) {
         this.javaSerializerByType = new ObjectMap();
         return null;
      } else {
         return (JavaSerializer)this.javaSerializerByType.get(type);
      }
   }

   private boolean isJavaSerializerRequired(Class type) {
      return hasInheritableReplaceMethod(type, "writeReplace") || hasInheritableReplaceMethod(type, "readResolve");
   }

   private static boolean hasInheritableReplaceMethod(Class type, String methodName) {
      Method method = null;

      for(Class<?> current = type; current != null; current = current.getSuperclass()) {
         try {
            method = current.getDeclaredMethod(methodName);
            break;
         }
      }

      return method != null && method.getReturnType() == Object.class;
   }
}
