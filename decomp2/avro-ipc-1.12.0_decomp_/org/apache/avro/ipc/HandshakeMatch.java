package org.apache.avro.ipc;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.avro.specific.AvroGenerated;

@AvroGenerated
public enum HandshakeMatch implements GenericEnumSymbol {
   BOTH,
   CLIENT,
   NONE;

   public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"enum\",\"name\":\"HandshakeMatch\",\"namespace\":\"org.apache.avro.ipc\",\"symbols\":[\"BOTH\",\"CLIENT\",\"NONE\"]}");

   public static Schema getClassSchema() {
      return SCHEMA$;
   }

   public Schema getSchema() {
      return SCHEMA$;
   }

   // $FF: synthetic method
   private static HandshakeMatch[] $values() {
      return new HandshakeMatch[]{BOTH, CLIENT, NONE};
   }
}
