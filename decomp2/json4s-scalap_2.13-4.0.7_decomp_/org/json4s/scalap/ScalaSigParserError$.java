package org.json4s.scalap;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ScalaSigParserError$ extends AbstractFunction1 implements Serializable {
   public static final ScalaSigParserError$ MODULE$ = new ScalaSigParserError$();

   public final String toString() {
      return "ScalaSigParserError";
   }

   public ScalaSigParserError apply(final String msg) {
      return new ScalaSigParserError(msg);
   }

   public Option unapply(final ScalaSigParserError x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.msg()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ScalaSigParserError$.class);
   }

   private ScalaSigParserError$() {
   }
}
