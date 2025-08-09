package dev.ludovic.netlib.blas;

public interface NativeBLAS extends BLAS {
   static NativeBLAS getInstance() {
      return InstanceBuilder.nativeBlas();
   }
}
