package org.apache.spark.memory;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Tests$;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble.;

public final class UnifiedMemoryManager$ {
   public static final UnifiedMemoryManager$ MODULE$ = new UnifiedMemoryManager$();
   private static final int RESERVED_SYSTEM_MEMORY_BYTES = 314572800;

   private int RESERVED_SYSTEM_MEMORY_BYTES() {
      return RESERVED_SYSTEM_MEMORY_BYTES;
   }

   public UnifiedMemoryManager apply(final SparkConf conf, final int numCores) {
      long maxMemory = this.getMaxMemory(conf);
      return new UnifiedMemoryManager(conf, maxMemory, (long)((double)maxMemory * BoxesRunTime.unboxToDouble(conf.get(org.apache.spark.internal.config.package$.MODULE$.MEMORY_STORAGE_FRACTION()))), numCores);
   }

   private long getMaxMemory(final SparkConf conf) {
      long systemMemory = BoxesRunTime.unboxToLong(conf.get(Tests$.MODULE$.TEST_MEMORY()));
      long reservedMemory = conf.getLong(Tests$.MODULE$.TEST_RESERVED_MEMORY().key(), conf.contains((ConfigEntry)Tests$.MODULE$.IS_TESTING()) ? 0L : (long)this.RESERVED_SYSTEM_MEMORY_BYTES());
      long minSystemMemory = (long).MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper((double)reservedMemory * (double)1.5F));
      if (systemMemory < minSystemMemory) {
         throw new SparkIllegalArgumentException("INVALID_DRIVER_MEMORY", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("systemMemory"), Long.toString(systemMemory)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("minSystemMemory"), Long.toString(minSystemMemory)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("config"), org.apache.spark.internal.config.package$.MODULE$.DRIVER_MEMORY().key())}))));
      } else {
         if (conf.contains(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_MEMORY())) {
            long executorMemory = conf.getSizeAsBytes(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_MEMORY().key());
            if (executorMemory < minSystemMemory) {
               throw new SparkIllegalArgumentException("INVALID_EXECUTOR_MEMORY", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("executorMemory"), Long.toString(executorMemory)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("minSystemMemory"), Long.toString(minSystemMemory)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("config"), org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_MEMORY().key())}))));
            }
         }

         long usableMemory = systemMemory - reservedMemory;
         double memoryFraction = BoxesRunTime.unboxToDouble(conf.get(org.apache.spark.internal.config.package$.MODULE$.MEMORY_FRACTION()));
         return (long)((double)usableMemory * memoryFraction);
      }
   }

   private UnifiedMemoryManager$() {
   }
}
