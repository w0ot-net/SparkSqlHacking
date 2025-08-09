package org.apache.spark.ml.python;

import java.io.Serializable;
import org.apache.spark.api.python.SerDeUtil.;
import org.apache.spark.mllib.api.python.SerDeBase;
import scala.runtime.ModuleSerializationProxy;

public final class MLSerDe$ extends SerDeBase implements Serializable {
   public static final MLSerDe$ MODULE$ = new MLSerDe$();
   private static final String PYSPARK_PACKAGE = "pyspark.ml";
   private static boolean initialized = false;

   static {
      MODULE$.initialize();
   }

   public String PYSPARK_PACKAGE() {
      return PYSPARK_PACKAGE;
   }

   public boolean initialized() {
      return initialized;
   }

   public void initialized_$eq(final boolean x$1) {
      initialized = x$1;
   }

   public void initialize() {
      .MODULE$.initialize();
      synchronized(this){}

      try {
         if (!this.initialized()) {
            (new MLSerDe.DenseVectorPickler()).register();
            (new MLSerDe.DenseMatrixPickler()).register();
            (new MLSerDe.SparseMatrixPickler()).register();
            (new MLSerDe.SparseVectorPickler()).register();
            this.initialized_$eq(true);
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MLSerDe$.class);
   }

   private MLSerDe$() {
   }
}
