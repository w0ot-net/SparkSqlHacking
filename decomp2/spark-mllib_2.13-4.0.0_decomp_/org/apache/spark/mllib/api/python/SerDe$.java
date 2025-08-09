package org.apache.spark.mllib.api.python;

import java.io.Serializable;
import org.apache.spark.api.python.SerDeUtil.;
import scala.runtime.ModuleSerializationProxy;

public final class SerDe$ extends SerDeBase implements Serializable {
   public static final SerDe$ MODULE$ = new SerDe$();
   private static final String PYSPARK_PACKAGE = "pyspark.mllib";
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
            (new SerDe.DenseVectorPickler()).register();
            (new SerDe.DenseMatrixPickler()).register();
            (new SerDe.SparseMatrixPickler()).register();
            (new SerDe.SparseVectorPickler()).register();
            (new SerDe.LabeledPointPickler()).register();
            (new SerDe.RatingPickler()).register();
            this.initialized_$eq(true);
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SerDe$.class);
   }

   private SerDe$() {
   }
}
