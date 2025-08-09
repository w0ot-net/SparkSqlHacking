package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class VersionFieldSerializer extends FieldSerializer {
   private int typeVersion;
   private int[] fieldVersion;
   private boolean compatible;

   public VersionFieldSerializer(Kryo kryo, Class type) {
      super(kryo, type);
      this.typeVersion = 0;
      this.compatible = true;
      this.initializeCachedFields();
   }

   public VersionFieldSerializer(Kryo kryo, Class type, boolean compatible) {
      this(kryo, type);
      this.compatible = compatible;
   }

   protected void initializeCachedFields() {
      FieldSerializer.CachedField[] fields = this.getFields();
      this.fieldVersion = new int[fields.length];
      int i = 0;

      for(int n = fields.length; i < n; ++i) {
         Field field = fields[i].getField();
         Since since = (Since)field.getAnnotation(Since.class);
         if (since != null) {
            this.fieldVersion[i] = since.value();
            this.typeVersion = Math.max(this.fieldVersion[i], this.typeVersion);
         } else {
            this.fieldVersion[i] = 0;
         }
      }

      this.removedFields.clear();
      if (Log.DEBUG) {
         Log.debug("Version for type " + this.getType().getName() + " is " + this.typeVersion);
      }

   }

   public void removeField(String fieldName) {
      super.removeField(fieldName);
      this.initializeCachedFields();
   }

   public void removeField(FieldSerializer.CachedField field) {
      super.removeField(field);
      this.initializeCachedFields();
   }

   public void write(Kryo kryo, Output output, Object object) {
      FieldSerializer.CachedField[] fields = this.getFields();
      output.writeVarInt(this.typeVersion, true);
      int i = 0;

      for(int n = fields.length; i < n; ++i) {
         fields[i].write(output, object);
      }

   }

   public Object read(Kryo kryo, Input input, Class type) {
      T object = (T)this.create(kryo, input, type);
      kryo.reference(object);
      int version = input.readVarInt(true);
      if (!this.compatible && version != this.typeVersion) {
         throw new KryoException("Version not compatible: " + version + " <-> " + this.typeVersion);
      } else {
         FieldSerializer.CachedField[] fields = this.getFields();
         int i = 0;

         for(int n = fields.length; i < n; ++i) {
            if (this.fieldVersion[i] > version) {
               if (Log.DEBUG) {
                  Log.debug("Skip field " + fields[i].getField().getName());
               }
            } else {
               fields[i].read(input, object);
            }
         }

         return object;
      }
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.FIELD})
   public @interface Since {
      int value() default 0;
   }
}
