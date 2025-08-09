package org.apache.thrift.transport.sasl;

import java.util.HashMap;
import java.util.Map;

public enum NegotiationStatus {
   START((byte)1),
   OK((byte)2),
   BAD((byte)3),
   ERROR((byte)4),
   COMPLETE((byte)5);

   private static final Map reverseMap = new HashMap();
   private final byte value;

   private NegotiationStatus(byte val) {
      this.value = val;
   }

   public byte getValue() {
      return this.value;
   }

   public static NegotiationStatus byValue(byte val) throws TSaslNegotiationException {
      if (!reverseMap.containsKey(val)) {
         throw new TSaslNegotiationException(TSaslNegotiationException.ErrorType.PROTOCOL_ERROR, "Invalid status " + val);
      } else {
         return (NegotiationStatus)reverseMap.get(val);
      }
   }

   static {
      for(NegotiationStatus s : values()) {
         reverseMap.put(s.getValue(), s);
      }

   }
}
