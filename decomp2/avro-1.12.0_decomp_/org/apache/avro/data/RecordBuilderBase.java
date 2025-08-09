package org.apache.avro.data;

import java.io.IOException;
import java.util.Arrays;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;

public abstract class RecordBuilderBase implements RecordBuilder {
   private final Schema schema;
   private final Schema.Field[] fields;
   private final boolean[] fieldSetFlags;
   private final GenericData data;

   protected final Schema schema() {
      return this.schema;
   }

   protected final Schema.Field[] fields() {
      return this.fields;
   }

   protected final boolean[] fieldSetFlags() {
      return this.fieldSetFlags;
   }

   protected final GenericData data() {
      return this.data;
   }

   protected RecordBuilderBase(Schema schema, GenericData data) {
      this.schema = schema;
      this.data = data;
      this.fields = (Schema.Field[])schema.getFields().toArray(new Schema.Field[0]);
      this.fieldSetFlags = new boolean[this.fields.length];
   }

   protected RecordBuilderBase(RecordBuilderBase other, GenericData data) {
      this.schema = other.schema;
      this.data = data;
      this.fields = (Schema.Field[])this.schema.getFields().toArray(new Schema.Field[0]);
      this.fieldSetFlags = Arrays.copyOf(other.fieldSetFlags, other.fieldSetFlags.length);
   }

   protected void validate(Schema.Field field, Object value) {
      if (!isValidValue(field, value) && field.defaultVal() == null) {
         throw new AvroRuntimeException("Field " + String.valueOf(field) + " does not accept null values");
      }
   }

   protected static boolean isValidValue(Schema.Field f, Object value) {
      if (value != null) {
         return true;
      } else {
         Schema schema = f.schema();
         Schema.Type type = schema.getType();
         if (type == Schema.Type.NULL) {
            return true;
         } else {
            if (type == Schema.Type.UNION) {
               for(Schema s : schema.getTypes()) {
                  if (s.getType() == Schema.Type.NULL) {
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   protected Object defaultValue(Schema.Field field) throws IOException {
      return this.data.deepCopy(field.schema(), this.data.getDefaultValue(field));
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + Arrays.hashCode(this.fieldSetFlags);
      result = 31 * result + (this.schema == null ? 0 : this.schema.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         RecordBuilderBase other = (RecordBuilderBase)obj;
         if (!Arrays.equals(this.fieldSetFlags, other.fieldSetFlags)) {
            return false;
         } else if (this.schema == null) {
            return other.schema == null;
         } else {
            return this.schema.equals(other.schema);
         }
      }
   }
}
