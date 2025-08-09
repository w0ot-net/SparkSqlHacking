package scala.xml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ProcInstr$ extends AbstractFunction2 implements Serializable {
   public static final ProcInstr$ MODULE$ = new ProcInstr$();

   public final String toString() {
      return "ProcInstr";
   }

   public ProcInstr apply(final String target, final String proctext) {
      return new ProcInstr(target, proctext);
   }

   public Option unapply(final ProcInstr x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.target(), x$0.proctext())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProcInstr$.class);
   }

   private ProcInstr$() {
   }
}
