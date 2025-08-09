package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ScalaSig$ extends AbstractFunction3 implements Serializable {
   public static final ScalaSig$ MODULE$ = new ScalaSig$();

   public final String toString() {
      return "ScalaSig";
   }

   public ScalaSig apply(final int majorVersion, final int minorVersion, final Seq table) {
      return new ScalaSig(majorVersion, minorVersion, table);
   }

   public Option unapply(final ScalaSig x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.majorVersion()), BoxesRunTime.boxToInteger(x$0.minorVersion()), x$0.table())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ScalaSig$.class);
   }

   private ScalaSig$() {
   }
}
