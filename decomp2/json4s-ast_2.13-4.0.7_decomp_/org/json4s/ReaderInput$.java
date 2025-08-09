package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ReaderInput$ extends AbstractFunction1 implements Serializable {
   public static final ReaderInput$ MODULE$ = new ReaderInput$();

   public final String toString() {
      return "ReaderInput";
   }

   public ReaderInput apply(final java.io.Reader reader) {
      return new ReaderInput(reader);
   }

   public Option unapply(final ReaderInput x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.reader()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReaderInput$.class);
   }

   private ReaderInput$() {
   }
}
