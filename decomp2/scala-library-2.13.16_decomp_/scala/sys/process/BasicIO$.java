package scala.sys.process;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.runtime.BoxedUnit;
import scala.runtime.Null$;

public final class BasicIO$ {
   public static final BasicIO$ MODULE$ = new BasicIO$();
   private static final String Newline = System.lineSeparator();
   private static final Function1 connectToStdIn = (x$3) -> {
      $anonfun$connectToStdIn$1(x$3);
      return BoxedUnit.UNIT;
   };
   private static final Function1 connectNoOp = (x$4) -> {
      $anonfun$connectNoOp$1(x$4);
      return BoxedUnit.UNIT;
   };

   public final int BufferSize() {
      return 8192;
   }

   public final String Newline() {
      return Newline;
   }

   public ProcessIO apply(final boolean withIn, final Function1 output, final Option log) {
      return new ProcessIO(this.input(withIn), (in) -> {
         $anonfun$processFully$1(processLine, in);
         return BoxedUnit.UNIT;
      }, this.getErr(log));
   }

   public ProcessIO apply(final boolean withIn, final Appendable buffer, final Option log) {
      return new ProcessIO(this.input(withIn), this.processFully(buffer), this.getErr(log));
   }

   public ProcessIO apply(final boolean withIn, final ProcessLogger log) {
      return new ProcessIO(this.input(withIn), this.processOutFully(log), this.processErrFully(log));
   }

   public Function1 getErr(final Option log) {
      if (log instanceof Some) {
         ProcessLogger lg = (ProcessLogger)((Some)log).value();
         return this.processErrFully(lg);
      } else if (None$.MODULE$.equals(log)) {
         return this.toStdErr();
      } else {
         throw new MatchError(log);
      }
   }

   private Function1 processErrFully(final ProcessLogger log) {
      return (in) -> {
         $anonfun$processFully$1(processLine, in);
         return BoxedUnit.UNIT;
      };
   }

   private Function1 processOutFully(final ProcessLogger log) {
      return (in) -> {
         $anonfun$processFully$1(processLine, in);
         return BoxedUnit.UNIT;
      };
   }

   public void close(final Closeable c) {
      try {
         c.close();
      } catch (IOException var2) {
      }
   }

   public Function1 processFully(final Appendable buffer) {
      return this.processFully(this.appendLine(buffer));
   }

   public Function1 processFully(final Function1 processLine) {
      return (in) -> {
         $anonfun$processFully$1(processLine, in);
         return BoxedUnit.UNIT;
      };
   }

   public void processLinesFully(final Function1 processLine, final Function0 readLine) {
      while(working$1()) {
         String var10000;
         try {
            var10000 = (String)readLine.apply();
         } catch (Throwable var5) {
            if (var5 instanceof InterruptedException) {
               halting$1();
               var10000 = null;
            } else {
               if (!(var5 instanceof IOException) || working$1()) {
                  throw var5;
               }

               halting$1();
               var10000 = null;
            }
         }

         String readFully$1_line = var10000;
         if (readFully$1_line == null) {
            break;
         }

         processLine.apply(readFully$1_line);
      }

   }

   public void connectToIn(final OutputStream o) {
      package$ var10002 = package$.MODULE$;
      this.transferFully(BasicIO.Uncloseable$.MODULE$.protect(System.in), o);
   }

   public Function1 input(final boolean connect) {
      return connect ? this.connectToStdIn() : this.connectNoOp();
   }

   public Function1 connectToStdIn() {
      return connectToStdIn;
   }

   public Function1 connectNoOp() {
      return connectNoOp;
   }

   public ProcessIO standard(final boolean connectInput) {
      return this.standard(this.input(connectInput));
   }

   public ProcessIO standard(final Function1 in) {
      return new ProcessIO(in, this.toStdOut(), this.toStdErr());
   }

   public Function1 toStdErr() {
      return (in) -> {
         $anonfun$toStdErr$1(in);
         return BoxedUnit.UNIT;
      };
   }

   public Function1 toStdOut() {
      return (in) -> {
         $anonfun$toStdOut$1(in);
         return BoxedUnit.UNIT;
      };
   }

   public void transferFully(final InputStream in, final OutputStream out) {
      try {
         this.transferFullyImpl(in, out);
      } catch (Throwable var6) {
         processInternal$ var10000 = processInternal$.MODULE$;
         Function0 onIOInterrupt_handler = () -> {
         };
         Serializable var8 = new Serializable(onIOInterrupt_handler) {
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
         onIOInterrupt_handler = null;
         PartialFunction catchExpr$1 = var8;
         if (((<undefinedtype>)catchExpr$1).isDefinedAt(var6)) {
            catchExpr$1.apply(var6);
         } else {
            throw var6;
         }
      }
   }

   private Function1 appendLine(final Appendable buffer) {
      return (line) -> {
         $anonfun$appendLine$1(buffer, line);
         return BoxedUnit.UNIT;
      };
   }

   private void transferFullyImpl(final InputStream in, final OutputStream out) {
      byte[] buffer = new byte[8192];
      this.loop$1(in, buffer, out);
      in.close();
   }

   // $FF: synthetic method
   public static final void $anonfun$processErrFully$1(final ProcessLogger log$1, final String x$1) {
      log$1.err(() -> x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$processOutFully$1(final ProcessLogger log$2, final String x$2) {
      log$2.out(() -> x$2);
   }

   // $FF: synthetic method
   public static final String $anonfun$processFully$2(final BufferedReader reader$1) {
      return reader$1.readLine();
   }

   // $FF: synthetic method
   public static final void $anonfun$processFully$1(final Function1 processLine$1, final InputStream in) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));

      try {
         BasicIO$ var10000 = MODULE$;

         while(working$1()) {
            try {
               var11 = reader.readLine();
            } catch (Throwable var8) {
               if (var8 instanceof InterruptedException) {
                  halting$1();
                  var11 = null;
               } else {
                  if (!(var8 instanceof IOException) || working$1()) {
                     throw var8;
                  }

                  halting$1();
                  var11 = null;
               }
            }

            String processLinesFully_readFully$1_line = var11;
            if (processLinesFully_readFully$1_line == null) {
               break;
            }

            processLine$1.apply(processLinesFully_readFully$1_line);
         }

         Object var10 = null;
         Object var5 = null;
      } finally {
         reader.close();
      }

   }

   private static final boolean working$1() {
      return !Thread.currentThread().isInterrupted();
   }

   private static final Null$ halting$1() {
      Thread.currentThread().interrupt();
      return null;
   }

   private final void readFully$1(final Function0 readLine$1, final Function1 processLine$2) {
      while(working$1()) {
         String var10000;
         try {
            var10000 = (String)readLine$1.apply();
         } catch (Throwable var5) {
            if (var5 instanceof InterruptedException) {
               halting$1();
               var10000 = null;
            } else {
               if (!(var5 instanceof IOException) || working$1()) {
                  throw var5;
               }

               halting$1();
               var10000 = null;
            }
         }

         String line = var10000;
         if (line == null) {
            break;
         }

         processLine$2.apply(line);
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$connectToStdIn$1(final OutputStream x$3) {
   }

   // $FF: synthetic method
   public static final void $anonfun$connectNoOp$1(final OutputStream x$4) {
   }

   // $FF: synthetic method
   public static final void $anonfun$toStdErr$1(final InputStream in) {
      package$ var10002 = package$.MODULE$;
      MODULE$.transferFully(in, System.err);
   }

   // $FF: synthetic method
   public static final void $anonfun$toStdOut$1(final InputStream in) {
      package$ var10002 = package$.MODULE$;
      MODULE$.transferFully(in, System.out);
   }

   // $FF: synthetic method
   public static final void $anonfun$appendLine$1(final Appendable buffer$1, final String line) {
      buffer$1.append(line);
      buffer$1.append(MODULE$.Newline());
   }

   private final void loop$1(final InputStream in$2, final byte[] buffer$2, final OutputStream out$2) {
      while(true) {
         int byteCount = in$2.read(buffer$2);
         if (byteCount > 0) {
            out$2.write(buffer$2, 0, byteCount);

            boolean var10000;
            try {
               out$2.flush();
               var10000 = true;
            } catch (IOException var5) {
               var10000 = false;
            }

            if (var10000) {
               continue;
            }
         }

         return;
      }
   }

   private BasicIO$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
