package org.apache.avro.specific;

import java.io.IOException;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.path.LocationStep;
import org.apache.avro.path.PathTracingException;
import org.apache.avro.path.TracingAvroTypeException;
import org.apache.avro.path.TracingClassCastException;
import org.apache.avro.path.TracingNullPointException;

public class SpecificDatumWriter extends GenericDatumWriter {
   public SpecificDatumWriter() {
      super((GenericData)SpecificData.get());
   }

   public SpecificDatumWriter(Class c) {
      super(SpecificData.get().getSchema(c), SpecificData.getForClass(c));
   }

   public SpecificDatumWriter(Schema schema) {
      super(schema, SpecificData.getForSchema(schema));
   }

   public SpecificDatumWriter(Schema root, SpecificData specificData) {
      super(root, specificData);
   }

   protected SpecificDatumWriter(SpecificData specificData) {
      super((GenericData)specificData);
   }

   public SpecificData getSpecificData() {
      return (SpecificData)this.getData();
   }

   protected void writeEnum(Schema schema, Object datum, Encoder out) throws IOException {
      if (!(datum instanceof Enum)) {
         super.writeEnum(schema, datum, out);
      } else {
         out.writeEnum(((Enum)datum).ordinal());
      }

   }

   protected void writeString(Schema schema, Object datum, Encoder out) throws IOException {
      if (!(datum instanceof CharSequence) && this.getSpecificData().isStringable(datum.getClass())) {
         datum = datum.toString();
      }

      this.writeString(datum, out);
   }

   protected void writeRecord(Schema schema, Object datum, Encoder out) throws IOException {
      if (datum instanceof SpecificRecordBase && this.getSpecificData().useCustomCoders()) {
         SpecificRecordBase d = (SpecificRecordBase)datum;
         if (d.hasCustomCoders()) {
            try {
               d.customEncode(out);
               return;
            } catch (NullPointerException e) {
               throw new TracingNullPointException(e, (Schema)null, true);
            }
         }
      }

      super.writeRecord(schema, datum, out);
   }

   protected void writeField(Object datum, Schema.Field f, Encoder out, Object state) throws IOException {
      if (datum instanceof SpecificRecordBase) {
         Conversion<?> conversion = ((SpecificRecordBase)datum).getConversion(f.pos());
         Schema fieldSchema = f.schema();
         LogicalType logicalType = fieldSchema.getLogicalType();
         Object value = this.getData().getField(datum, f.name(), f.pos());
         if (conversion != null && logicalType != null) {
            value = this.convert(fieldSchema, logicalType, conversion, value);
         }

         try {
            this.writeWithoutConversion(fieldSchema, value, out);
         } catch (TracingClassCastException | TracingAvroTypeException | TracingNullPointException e) {
            ((PathTracingException)e).tracePath(new LocationStep(".", f.name()));
            throw e;
         } catch (AvroTypeException ate) {
            throw this.addAvroTypeMsg(ate, " in field '" + f.name() + "'");
         }
      } else {
         super.writeField(datum, f, out, state);
      }

   }
}
