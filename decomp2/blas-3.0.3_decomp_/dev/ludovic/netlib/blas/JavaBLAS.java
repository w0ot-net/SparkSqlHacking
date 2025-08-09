package dev.ludovic.netlib.blas;

public interface JavaBLAS extends BLAS {
   static JavaBLAS getInstance() {
      return InstanceBuilder.javaBlas();
   }
}
