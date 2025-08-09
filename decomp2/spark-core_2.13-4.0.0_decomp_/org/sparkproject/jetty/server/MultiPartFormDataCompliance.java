package org.sparkproject.jetty.server;

public enum MultiPartFormDataCompliance {
   LEGACY,
   RFC7578;

   // $FF: synthetic method
   private static MultiPartFormDataCompliance[] $values() {
      return new MultiPartFormDataCompliance[]{LEGACY, RFC7578};
   }
}
