package scala.util.control;

import scala.None$;
import scala.Option;
import scala.Some;

public final class NonFatal$ {
   public static final NonFatal$ MODULE$ = new NonFatal$();

   public boolean apply(final Throwable t) {
      return !(t instanceof VirtualMachineError ? true : (t instanceof ThreadDeath ? true : (t instanceof InterruptedException ? true : (t instanceof LinkageError ? true : t instanceof ControlThrowable))));
   }

   public Option unapply(final Throwable t) {
      return (Option)(this.apply(t) ? new Some(t) : None$.MODULE$);
   }

   private NonFatal$() {
   }
}
