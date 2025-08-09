package org.apache.spark.util;

import java.io.Serializable;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;

public final class StatCounter$ implements Serializable {
   public static final StatCounter$ MODULE$ = new StatCounter$();

   public StatCounter apply(final IterableOnce values) {
      return new StatCounter(values);
   }

   public StatCounter apply(final Seq values) {
      return new StatCounter(values);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StatCounter$.class);
   }

   private StatCounter$() {
   }
}
