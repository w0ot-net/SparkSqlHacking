package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.InputChunked;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.OutputChunked;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.minlog.Log;

public class CompatibleFieldSerializer extends FieldSerializer {
   private static final int THRESHOLD_BINARY_SEARCH = 32;

   public CompatibleFieldSerializer(Kryo kryo, Class type) {
      super(kryo, type);
   }

   public void write(Kryo kryo, Output output, Object object) {
      FieldSerializer.CachedField[] fields = this.getFields();
      ObjectMap context = kryo.getGraphContext();
      if (!context.containsKey(this)) {
         context.put(this, (Object)null);
         if (Log.TRACE) {
            Log.trace("kryo", "Write " + fields.length + " field names.");
         }

         output.writeVarInt(fields.length, true);
         int i = 0;

         for(int n = fields.length; i < n; ++i) {
            output.writeString(this.getCachedFieldName(fields[i]));
         }
      }

      OutputChunked outputChunked = new OutputChunked(output, 1024);
      int i = 0;

      for(int n = fields.length; i < n; ++i) {
         fields[i].write(outputChunked, object);
         outputChunked.endChunks();
      }

   }

   public Object read(Kryo kryo, Input input, Class type) {
      T object = (T)this.create(kryo, input, type);
      kryo.reference(object);
      ObjectMap context = kryo.getGraphContext();
      FieldSerializer.CachedField[] fields = (FieldSerializer.CachedField[])context.get(this);
      if (fields == null) {
         int length = input.readVarInt(true);
         if (Log.TRACE) {
            Log.trace("kryo", "Read " + length + " field names.");
         }

         String[] names = new String[length];

         for(int i = 0; i < length; ++i) {
            names[i] = input.readString();
         }

         fields = new FieldSerializer.CachedField[length];
         FieldSerializer.CachedField[] allFields = this.getFields();
         if (length < 32) {
            label81:
            for(int i = 0; i < length; ++i) {
               String schemaName = names[i];
               int ii = 0;

               for(int nn = allFields.length; ii < nn; ++ii) {
                  if (this.getCachedFieldName(allFields[ii]).equals(schemaName)) {
                     fields[i] = allFields[ii];
                     continue label81;
                  }
               }

               if (Log.TRACE) {
                  Log.trace("kryo", "Ignore obsolete field: " + schemaName);
               }
            }
         } else {
            int maxFieldLength = allFields.length;

            label94:
            for(int i = 0; i < length; ++i) {
               String schemaName = names[i];
               int low = 0;
               int high = maxFieldLength - 1;

               while(low <= high) {
                  int mid = low + high >>> 1;
                  String midVal = this.getCachedFieldName(allFields[mid]);
                  int compare = schemaName.compareTo(midVal);
                  if (compare < 0) {
                     high = mid - 1;
                  } else {
                     if (compare <= 0) {
                        fields[i] = allFields[mid];
                        continue label94;
                     }

                     low = mid + 1;
                  }
               }

               if (Log.TRACE) {
                  Log.trace("kryo", "Ignore obsolete field: " + schemaName);
               }
            }
         }

         context.put(this, fields);
      }

      InputChunked inputChunked = new InputChunked(input, 1024);
      boolean hasGenerics = this.getGenerics() != null;
      int i = 0;

      for(int n = fields.length; i < n; ++i) {
         FieldSerializer.CachedField cachedField = fields[i];
         if (cachedField != null && hasGenerics) {
            cachedField = this.getField(this.getCachedFieldName(cachedField));
         }

         if (cachedField == null) {
            if (Log.TRACE) {
               Log.trace("kryo", "Skip obsolete field.");
            }

            inputChunked.nextChunks();
         } else {
            cachedField.read(inputChunked, object);
            inputChunked.nextChunks();
         }
      }

      return object;
   }
}
