package org.json4s.scalap;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class $tilde$ implements Serializable {
   public static final $tilde$ MODULE$ = new $tilde$();

   public final String toString() {
      return "~";
   }

   public $tilde apply(final Object _1, final Object _2) {
      return new $tilde(_1, _2);
   }

   public Option unapply(final $tilde x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0._1(), x$0._2())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy($tilde$.class);
   }

   private $tilde$() {
   }
}
