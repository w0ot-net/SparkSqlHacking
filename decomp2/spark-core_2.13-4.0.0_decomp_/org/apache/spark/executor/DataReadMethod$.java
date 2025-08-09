package org.apache.spark.executor;

import org.apache.spark.annotation.DeveloperApi;
import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

@DeveloperApi
public final class DataReadMethod$ extends Enumeration {
   public static final DataReadMethod$ MODULE$ = new DataReadMethod$();
   private static final Enumeration.Value Memory;
   private static final Enumeration.Value Disk;
   private static final Enumeration.Value Hadoop;
   private static final Enumeration.Value Network;

   static {
      Memory = MODULE$.Value();
      Disk = MODULE$.Value();
      Hadoop = MODULE$.Value();
      Network = MODULE$.Value();
   }

   public Enumeration.Value Memory() {
      return Memory;
   }

   public Enumeration.Value Disk() {
      return Disk;
   }

   public Enumeration.Value Hadoop() {
      return Hadoop;
   }

   public Enumeration.Value Network() {
      return Network;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DataReadMethod$.class);
   }

   private DataReadMethod$() {
   }
}
