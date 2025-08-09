package org.apache.spark.util;

import java.util.concurrent.TimeoutException;
import org.apache.spark.SparkException;
import scala.Option;
import scala.concurrent.Awaitable;
import scala.concurrent.CanAwait;
import scala.concurrent.duration.Duration;
import scala.util.control.NonFatal.;

public final class SparkThreadUtils$ {
   public static final SparkThreadUtils$ MODULE$ = new SparkThreadUtils$();

   public Object awaitResult(final Awaitable awaitable, final Duration atMost) throws SparkException {
      try {
         return this.awaitResultNoSparkExceptionConversion(awaitable, atMost);
      } catch (Throwable var9) {
         if (var9 instanceof SparkFatalException var6) {
            throw var6.throwable();
         } else {
            if (var9 != null) {
               Option var7 = .MODULE$.unapply(var9);
               if (!var7.isEmpty()) {
                  Throwable t = (Throwable)var7.get();
                  if (!(t instanceof TimeoutException)) {
                     throw new SparkException("Exception thrown in awaitResult: ", t);
                  }
               }
            }

            throw var9;
         }
      }
   }

   public Object awaitResultNoSparkExceptionConversion(final Awaitable awaitable, final Duration atMost) {
      CanAwait awaitPermission = null;
      return awaitable.result(atMost, awaitPermission);
   }

   private SparkThreadUtils$() {
   }
}
