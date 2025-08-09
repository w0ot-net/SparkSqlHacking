package scala.collection.concurrent;

import java.io.Serializable;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.runtime.ModuleSerializationProxy;

public final class RDCSS_Descriptor$ implements Serializable {
   public static final RDCSS_Descriptor$ MODULE$ = new RDCSS_Descriptor$();

   public final String toString() {
      return "RDCSS_Descriptor";
   }

   public RDCSS_Descriptor apply(final INode old, final MainNode expectedmain, final INode nv) {
      return new RDCSS_Descriptor(old, expectedmain, nv);
   }

   public Option unapply(final RDCSS_Descriptor x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple3(x$0.old(), x$0.expectedmain(), x$0.nv())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RDCSS_Descriptor$.class);
   }

   private RDCSS_Descriptor$() {
   }
}
