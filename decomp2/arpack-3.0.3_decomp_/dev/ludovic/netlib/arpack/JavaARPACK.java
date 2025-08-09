package dev.ludovic.netlib.arpack;

public interface JavaARPACK extends ARPACK {
   static JavaARPACK getInstance() {
      return InstanceBuilder.javaArpack();
   }
}
