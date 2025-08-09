package org.apache.spark.ml.source.libsvm;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class LibSVMOptions$ implements Serializable {
   public static final LibSVMOptions$ MODULE$ = new LibSVMOptions$();
   private static final String NUM_FEATURES = "numFeatures";
   private static final String VECTOR_TYPE = "vectorType";
   private static final String DENSE_VECTOR_TYPE = "dense";
   private static final String SPARSE_VECTOR_TYPE = "sparse";

   public String NUM_FEATURES() {
      return NUM_FEATURES;
   }

   public String VECTOR_TYPE() {
      return VECTOR_TYPE;
   }

   public String DENSE_VECTOR_TYPE() {
      return DENSE_VECTOR_TYPE;
   }

   public String SPARSE_VECTOR_TYPE() {
      return SPARSE_VECTOR_TYPE;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LibSVMOptions$.class);
   }

   private LibSVMOptions$() {
   }
}
