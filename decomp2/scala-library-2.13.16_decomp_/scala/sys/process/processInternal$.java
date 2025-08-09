package scala.sys.process;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Serializable;
import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.collection.immutable.Seq;
import scala.runtime.ScalaRunTime$;
import scala.sys.SystemProperties;

public final class processInternal$ {
   public static final processInternal$ MODULE$ = new processInternal$();
   private static final boolean processDebug;

   static {
      scala.sys.package$ var10000 = scala.sys.package$.MODULE$;
      processDebug = (new SystemProperties()).contains("scala.process.debug");
      MODULE$.dbg(ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{"Initializing process package."}));
   }

   public final boolean processDebug() {
      return processDebug;
   }

   public PartialFunction onError(final Function1 handler) {
      return new Serializable(handler) {
         private static final long serialVersionUID = 0L;
         private final Function1 handler$1;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            return this.handler$1.apply(x1);
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return true;
         }

         public {
            this.handler$1 = handler$1;
         }
      };
   }

   public PartialFunction onIOInterrupt(final Function0 handler) {
      return new Serializable(handler) {
         private static final long serialVersionUID = 0L;
         private final Function0 handler$2;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            return x1 instanceof InterruptedIOException ? this.handler$2.apply() : default.apply(x1);
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return x1 instanceof InterruptedIOException;
         }

         public {
            this.handler$2 = handler$2;
         }
      };
   }

   public PartialFunction onInterrupt(final Function0 handler) {
      return new Serializable(handler) {
         private static final long serialVersionUID = 0L;
         private final Function0 handler$3;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            return x1 instanceof InterruptedException ? this.handler$3.apply() : default.apply(x1);
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return x1 instanceof InterruptedException;
         }

         public {
            this.handler$3 = handler$3;
         }
      };
   }

   public PartialFunction ioFailure(final Function1 handler) {
      return new Serializable(handler) {
         private static final long serialVersionUID = 0L;
         private final Function1 handler$4;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (x1 instanceof IOException) {
               IOException var3 = (IOException)x1;
               return this.handler$4.apply(var3);
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return x1 instanceof IOException;
         }

         public {
            this.handler$4 = handler$4;
         }
      };
   }

   public void dbg(final Seq msgs) {
      if (this.processDebug()) {
         Console$ var10000 = Console$.MODULE$;
         StringBuilder var10001 = (new StringBuilder(10)).append("[process] ");
         String mkString_sep = " ";
         if (msgs == null) {
            throw null;
         } else {
            String var10002 = msgs.mkString("", mkString_sep, "");
            Object var3 = null;
            var10000.println(var10001.append(var10002).toString());
         }
      }
   }

   private processInternal$() {
   }
}
