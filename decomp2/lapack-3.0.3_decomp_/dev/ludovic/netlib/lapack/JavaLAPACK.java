package dev.ludovic.netlib.lapack;

public interface JavaLAPACK extends LAPACK {
   static JavaLAPACK getInstance() {
      return InstanceBuilder.javaLapack();
   }
}
