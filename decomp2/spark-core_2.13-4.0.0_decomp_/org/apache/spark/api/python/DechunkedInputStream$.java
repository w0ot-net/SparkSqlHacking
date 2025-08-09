package org.apache.spark.api.python;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.util.Utils$;
import scala.runtime.java8.JFunction0;

public final class DechunkedInputStream$ {
   public static final DechunkedInputStream$ MODULE$ = new DechunkedInputStream$();

   public void dechunkAndCopyToOutput(final InputStream chunked, final OutputStream out) {
      DechunkedInputStream dechunked = new DechunkedInputStream(chunked);
      Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcJ.sp)() -> Utils$.MODULE$.copyStream(dechunked, out, Utils$.MODULE$.copyStream$default$3(), Utils$.MODULE$.copyStream$default$4()), (JFunction0.mcV.sp)() -> {
         JavaUtils.closeQuietly(out);
         JavaUtils.closeQuietly(dechunked);
      });
   }

   private DechunkedInputStream$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
