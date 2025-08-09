package dev.ludovic.netlib.lapack;

public interface NativeLAPACK extends LAPACK {
   static NativeLAPACK getInstance() {
      return InstanceBuilder.nativeLapack();
   }
}
