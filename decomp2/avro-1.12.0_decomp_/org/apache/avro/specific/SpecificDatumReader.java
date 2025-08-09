package org.apache.avro.specific;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Conversion;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.ResolvingDecoder;
import org.apache.avro.util.ClassUtils;

public class SpecificDatumReader extends GenericDatumReader {
   public static final String[] SERIALIZABLE_PACKAGES = System.getProperty("org.apache.avro.SERIALIZABLE_PACKAGES", "java.lang,java.math,java.io,java.net,org.apache.avro.reflect").split(",");
   private final List trustedPackages;

   public SpecificDatumReader() {
      this((Schema)null, (Schema)null, SpecificData.get());
   }

   public SpecificDatumReader(Class c) {
      this(SpecificData.getForClass(c));
      this.setSchema(this.getSpecificData().getSchema(c));
   }

   public SpecificDatumReader(Schema schema) {
      this(schema, schema, SpecificData.getForSchema(schema));
   }

   public SpecificDatumReader(Schema writer, Schema reader) {
      this(writer, reader, SpecificData.getForSchema(reader));
   }

   public SpecificDatumReader(Schema writer, Schema reader, SpecificData data) {
      super(writer, reader, data);
      this.trustedPackages = new ArrayList();
      this.trustedPackages.addAll(Arrays.asList(SERIALIZABLE_PACKAGES));
   }

   public SpecificDatumReader(SpecificData data) {
      super((GenericData)data);
      this.trustedPackages = new ArrayList();
   }

   public SpecificData getSpecificData() {
      return (SpecificData)this.getData();
   }

   public void setSchema(Schema actual) {
      if (this.getExpected() == null && actual != null && actual.getType() == Schema.Type.RECORD) {
         SpecificData data = this.getSpecificData();
         Class c = data.getClass(actual);
         if (c != null && SpecificRecord.class.isAssignableFrom(c)) {
            this.setExpected(data.getSchema(c));
         }
      }

      super.setSchema(actual);
   }

   protected Class findStringClass(Schema schema) {
      Class stringClass = null;
      switch (schema.getType()) {
         case STRING:
            stringClass = this.getPropAsClass(schema, "java-class");
            break;
         case MAP:
            stringClass = this.getPropAsClass(schema, "java-key-class");
      }

      return stringClass != null ? stringClass : super.findStringClass(schema);
   }

   private Class getPropAsClass(Schema schema, String prop) {
      String name = schema.getProp(prop);
      if (name == null) {
         return null;
      } else {
         try {
            Class clazz = ClassUtils.forName(this.getData().getClassLoader(), name);
            this.checkSecurity(clazz);
            return clazz;
         } catch (ClassNotFoundException e) {
            throw new AvroRuntimeException(e);
         }
      }
   }

   private boolean trustAllPackages() {
      return this.trustedPackages.size() == 1 && "*".equals(this.trustedPackages.get(0));
   }

   private void checkSecurity(Class clazz) throws ClassNotFoundException {
      if (!this.trustAllPackages() && !clazz.isPrimitive()) {
         boolean found = false;
         Package thePackage = clazz.getPackage();
         if (thePackage != null) {
            for(String trustedPackage : this.getTrustedPackages()) {
               if (thePackage.getName().equals(trustedPackage) || thePackage.getName().startsWith(trustedPackage + ".")) {
                  found = true;
                  break;
               }
            }

            if (!found) {
               throw new SecurityException("Forbidden " + String.valueOf(clazz) + "! This class is not trusted to be included in Avro schema using java-class. Please set org.apache.avro.SERIALIZABLE_PACKAGES system property with the packages you trust.");
            }
         }

      }
   }

   public final List getTrustedPackages() {
      return this.trustedPackages;
   }

   protected Object readRecord(Object old, Schema expected, ResolvingDecoder in) throws IOException {
      SpecificData data = this.getSpecificData();
      if (data.useCustomCoders()) {
         old = data.newRecord(old, expected);
         if (old instanceof SpecificRecordBase) {
            SpecificRecordBase d = (SpecificRecordBase)old;
            if (d.hasCustomCoders()) {
               d.customDecode(in);
               return d;
            }
         }
      }

      return super.readRecord(old, expected, in);
   }

   protected void readField(Object record, Schema.Field field, Object oldDatum, ResolvingDecoder in, Object state) throws IOException {
      if (record instanceof SpecificRecordBase) {
         Conversion<?> conversion = ((SpecificRecordBase)record).getConversion(field.pos());
         Object datum;
         if (conversion != null) {
            datum = this.readWithConversion(oldDatum, field.schema(), field.schema().getLogicalType(), conversion, in);
         } else {
            datum = this.readWithoutConversion(oldDatum, field.schema(), in);
         }

         this.getData().setField(record, field.name(), field.pos(), datum);
      } else {
         super.readField(record, field, oldDatum, in, state);
      }

   }
}
