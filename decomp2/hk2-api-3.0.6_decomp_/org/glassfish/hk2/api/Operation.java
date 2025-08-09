package org.glassfish.hk2.api;

public enum Operation {
   LOOKUP,
   BIND,
   UNBIND;

   // $FF: synthetic method
   private static Operation[] $values() {
      return new Operation[]{LOOKUP, BIND, UNBIND};
   }
}
