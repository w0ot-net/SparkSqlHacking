package dev.ludovic.netlib.arpack;

public interface NativeARPACK extends ARPACK {
   static NativeARPACK getInstance() {
      return InstanceBuilder.nativeArpack();
   }
}
