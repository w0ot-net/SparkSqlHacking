package scala.reflect.internal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing;

public final class MissingRequirementError$ implements Serializable {
   public static final MissingRequirementError$ MODULE$ = new MissingRequirementError$();
   private static final String scala$reflect$internal$MissingRequirementError$$suffix = " not found.";

   public String scala$reflect$internal$MissingRequirementError$$suffix() {
      return scala$reflect$internal$MissingRequirementError$$suffix;
   }

   public Nothing signal(final String msg) {
      throw new MissingRequirementError(msg);
   }

   public Nothing notFound(final String req) {
      String signal_msg = (new StringBuilder(0)).append(req).append(this.scala$reflect$internal$MissingRequirementError$$suffix()).toString();
      throw new MissingRequirementError(signal_msg);
   }

   public Option unapply(final Throwable x) {
      if (x instanceof MissingRequirementError) {
         MissingRequirementError var2 = (MissingRequirementError)x;
         return new Some(var2.req());
      } else {
         return .MODULE$;
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MissingRequirementError$.class);
   }

   private MissingRequirementError$() {
   }
}
