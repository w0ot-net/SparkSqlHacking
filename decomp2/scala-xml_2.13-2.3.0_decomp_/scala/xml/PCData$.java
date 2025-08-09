package scala.xml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class PCData$ implements Serializable {
   public static final PCData$ MODULE$ = new PCData$();

   public PCData apply(final String data) {
      return new PCData(data);
   }

   public Option unapply(final Object other) {
      if (other instanceof PCData) {
         PCData var4 = (PCData)other;
         return new Some(var4.data());
      } else {
         return .MODULE$;
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PCData$.class);
   }

   private PCData$() {
   }
}
