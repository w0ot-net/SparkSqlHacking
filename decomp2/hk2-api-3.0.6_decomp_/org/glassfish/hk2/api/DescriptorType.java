package org.glassfish.hk2.api;

public enum DescriptorType {
   CLASS,
   PROVIDE_METHOD;

   // $FF: synthetic method
   private static DescriptorType[] $values() {
      return new DescriptorType[]{CLASS, PROVIDE_METHOD};
   }
}
