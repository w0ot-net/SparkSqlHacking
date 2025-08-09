package spire.macros;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.ModuleSerializationProxy;

public final class CheckedRewriter$ implements Serializable {
   public static final CheckedRewriter$ MODULE$ = new CheckedRewriter$();

   public final String toString() {
      return "CheckedRewriter";
   }

   public CheckedRewriter apply(final Context c) {
      return new CheckedRewriter(c);
   }

   public Option unapply(final CheckedRewriter x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.c()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CheckedRewriter$.class);
   }

   private CheckedRewriter$() {
   }
}
