package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.InputChunked;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.OutputChunked;
import com.esotericsoftware.minlog.Log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

public class TaggedFieldSerializer extends FieldSerializer {
   private int[] tags;
   private int writeFieldCount;
   private boolean[] deprecated;
   private boolean[] annexed;
   private static final Comparator TAGGED_VALUE_COMPARATOR = new Comparator() {
      public int compare(FieldSerializer.CachedField o1, FieldSerializer.CachedField o2) {
         return ((Tag)o1.getField().getAnnotation(Tag.class)).value() - ((Tag)o2.getField().getAnnotation(Tag.class)).value();
      }
   };

   public TaggedFieldSerializer(Kryo kryo, Class type) {
      super(kryo, type, (Class[])null, kryo.getTaggedFieldSerializerConfig().clone());
   }

   public void setSkipUnknownTags(boolean skipUnknownTags) {
      ((TaggedFieldSerializerConfig)this.config).setSkipUnknownTags(skipUnknownTags);
      this.rebuildCachedFields();
   }

   public boolean isSkipUnknownTags() {
      return ((TaggedFieldSerializerConfig)this.config).isSkipUnknownTags();
   }

   protected void initializeCachedFields() {
      FieldSerializer.CachedField[] fields = this.getFields();
      int i = 0;

      for(int n = fields.length; i < n; ++i) {
         Field field = fields[i].getField();
         if (field.getAnnotation(Tag.class) == null) {
            if (Log.TRACE) {
               Log.trace("kryo", "Ignoring field without tag: " + fields[i]);
            }

            super.removeField(fields[i]);
         }
      }

      fields = this.getFields();
      this.tags = new int[fields.length];
      this.deprecated = new boolean[fields.length];
      this.annexed = new boolean[fields.length];
      this.writeFieldCount = fields.length;
      Arrays.sort(fields, TAGGED_VALUE_COMPARATOR);
      i = 0;

      for(int n = fields.length; i < n; ++i) {
         Field field = fields[i].getField();
         this.tags[i] = ((Tag)field.getAnnotation(Tag.class)).value();
         if (i > 0 && this.tags[i] == this.tags[i - 1]) {
            throw new KryoException(String.format("The fields [%s] and [%s] both have a Tag value of %d.", field, fields[i - 1].getField(), this.tags[i]));
         }

         if (field.getAnnotation(Deprecated.class) != null) {
            this.deprecated[i] = true;
            --this.writeFieldCount;
         }

         if (((Tag)field.getAnnotation(Tag.class)).annexed()) {
            this.annexed[i] = true;
         }
      }

      this.removedFields.clear();
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
      output.writeVarInt(this.writeFieldCount, true);
      OutputChunked outputChunked = null;
      int i = 0;

      for(int n = fields.length; i < n; ++i) {
         if (!this.deprecated[i]) {
            output.writeVarInt(this.tags[i], true);
            if (this.annexed[i]) {
               if (outputChunked == null) {
                  outputChunked = new OutputChunked(output, 1024);
               }

               fields[i].write(outputChunked, object);
               outputChunked.endChunks();
            } else {
               fields[i].write(output, object);
            }
         }
      }

   }

   public Object read(Kryo kryo, Input input, Class type) {
      T object = (T)this.create(kryo, input, type);
      kryo.reference(object);
      int fieldCount = input.readVarInt(true);
      int[] tags = this.tags;
      InputChunked inputChunked = null;
      FieldSerializer.CachedField[] fields = this.getFields();
      int i = 0;

      for(int n = fieldCount; i < n; ++i) {
         int tag = input.readVarInt(true);
         FieldSerializer.CachedField cachedField = null;
         boolean isAnnexed = false;
         int ii = 0;

         for(int nn = tags.length; ii < nn; ++ii) {
            if (tags[ii] == tag) {
               cachedField = fields[ii];
               isAnnexed = this.annexed[ii];
               break;
            }
         }

         if (cachedField == null) {
            if (!this.isSkipUnknownTags()) {
               throw new KryoException("Unknown field tag: " + tag + " (" + this.getType().getName() + ")");
            }

            if (inputChunked == null) {
               inputChunked = new InputChunked(input, 1024);
            }

            inputChunked.nextChunks();
            if (Log.TRACE) {
               Log.trace(String.format("Unknown field tag: %d (%s) encountered. Assuming a future annexed tag with chunked encoding and skipping.", tag, this.getType().getName()));
            }
         } else if (isAnnexed) {
            if (inputChunked == null) {
               inputChunked = new InputChunked(input, 1024);
            }

            cachedField.read(inputChunked, object);
            inputChunked.nextChunks();
         } else {
            cachedField.read(input, object);
         }
      }

      return object;
   }

   /** @deprecated */
   @Deprecated
   public void setIgnoreUnknownTags(boolean ignoreUnknownTags) {
   }

   /** @deprecated */
   @Deprecated
   public boolean isIgnoreUnkownTags() {
      return false;
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.FIELD})
   public @interface Tag {
      int value();

      boolean annexed() default false;
   }
}
