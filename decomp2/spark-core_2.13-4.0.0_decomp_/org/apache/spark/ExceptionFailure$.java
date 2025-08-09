package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple8;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.AbstractFunction8;
import scala.runtime.ModuleSerializationProxy;

public final class ExceptionFailure$ extends AbstractFunction8 implements Serializable {
   public static final ExceptionFailure$ MODULE$ = new ExceptionFailure$();

   public Seq $lessinit$greater$default$6() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Seq $lessinit$greater$default$7() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public Seq $lessinit$greater$default$8() {
      return (Seq).MODULE$.Seq().empty();
   }

   public final String toString() {
      return "ExceptionFailure";
   }

   public ExceptionFailure apply(final String className, final String description, final StackTraceElement[] stackTrace, final String fullStackTrace, final Option exceptionWrapper, final Seq accumUpdates, final Seq accums, final Seq metricPeaks) {
      return new ExceptionFailure(className, description, stackTrace, fullStackTrace, exceptionWrapper, accumUpdates, accums, metricPeaks);
   }

   public Seq apply$default$6() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Seq apply$default$7() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public Seq apply$default$8() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Option unapply(final ExceptionFailure x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple8(x$0.className(), x$0.description(), x$0.stackTrace(), x$0.fullStackTrace(), x$0.org$apache$spark$ExceptionFailure$$exceptionWrapper(), x$0.accumUpdates(), x$0.accums(), x$0.metricPeaks())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExceptionFailure$.class);
   }

   private ExceptionFailure$() {
   }
}
