package org.apache.avro.ipc;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.FixedSize;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificFixed;

@FixedSize(16)
@AvroGenerated
public class MD5 extends SpecificFixed {
   private static final long serialVersionUID = 6731146766938681575L;
   public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"fixed\",\"name\":\"MD5\",\"namespace\":\"org.apache.avro.ipc\",\"size\":16}");
   private static final DatumWriter WRITER$;
   private static final DatumReader READER$;

   public static Schema getClassSchema() {
      return SCHEMA$;
   }

   public Schema getSchema() {
      return SCHEMA$;
   }

   public MD5() {
   }

   public MD5(byte[] bytes) {
      super(bytes);
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      WRITER$.write(this, SpecificData.getEncoder(out));
   }

   public void readExternal(ObjectInput in) throws IOException {
      READER$.read(this, SpecificData.getDecoder(in));
   }

   static {
      WRITER$ = new SpecificDatumWriter(SCHEMA$);
      READER$ = new SpecificDatumReader(SCHEMA$);
   }
}
