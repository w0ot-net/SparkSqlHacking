package org.apache.spark.executor;

import org.apache.spark.annotation.DeveloperApi;
import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

@DeveloperApi
public final class DataWriteMethod$ extends Enumeration {
   public static final DataWriteMethod$ MODULE$ = new DataWriteMethod$();
   private static final Enumeration.Value Hadoop;

   static {
      Hadoop = MODULE$.Value();
   }

   public Enumeration.Value Hadoop() {
      return Hadoop;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DataWriteMethod$.class);
   }

   private DataWriteMethod$() {
   }
}
