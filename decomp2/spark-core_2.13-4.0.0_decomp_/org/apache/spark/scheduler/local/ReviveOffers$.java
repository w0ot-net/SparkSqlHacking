package org.apache.spark.scheduler.local;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class ReviveOffers$ extends AbstractFunction0 implements Serializable {
   public static final ReviveOffers$ MODULE$ = new ReviveOffers$();

   public final String toString() {
      return "ReviveOffers";
   }

   public ReviveOffers apply() {
      return new ReviveOffers();
   }

   public boolean unapply(final ReviveOffers x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReviveOffers$.class);
   }

   private ReviveOffers$() {
   }
}
