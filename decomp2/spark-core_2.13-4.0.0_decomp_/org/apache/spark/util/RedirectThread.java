package org.apache.spark.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;
import scala.util.control.Exception.;

@ScalaSignature(
   bytes = "\u0006\u0005q3Qa\u0003\u0007\u0001\u001dQA\u0001\"\b\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\tK\u0001\u0011\t\u0011)A\u0005M!A\u0011\u0006\u0001B\u0001B\u0003%!\u0006\u0003\u00058\u0001\t\u0005\t\u0015!\u00039\u0011\u0015a\u0004\u0001\"\u0001>\u0011\u0015!\u0005\u0001\"\u0011F\u000f!IE\"!A\t\u00029Qe\u0001C\u0006\r\u0003\u0003E\tAD&\t\u000bqBA\u0011A(\t\u000fAC\u0011\u0013!C\u0001#\nq!+\u001a3je\u0016\u001cG\u000f\u00165sK\u0006$'BA\u0007\u000f\u0003\u0011)H/\u001b7\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c\"\u0001A\u000b\u0011\u0005YYR\"A\f\u000b\u0005aI\u0012\u0001\u00027b]\u001eT\u0011AG\u0001\u0005U\u00064\u0018-\u0003\u0002\u001d/\t1A\u000b\u001b:fC\u0012\f!!\u001b8\u0004\u0001A\u0011\u0001eI\u0007\u0002C)\u0011!%G\u0001\u0003S>L!\u0001J\u0011\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\\\u0001\u0004_V$\bC\u0001\u0011(\u0013\tA\u0013E\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW.\u0001\u0003oC6,\u0007CA\u00165\u001d\ta#\u0007\u0005\u0002.a5\taF\u0003\u00020=\u00051AH]8pizR\u0011!M\u0001\u0006g\u000e\fG.Y\u0005\u0003gA\na\u0001\u0015:fI\u00164\u0017BA\u001b7\u0005\u0019\u0019FO]5oO*\u00111\u0007M\u0001\raJ|\u0007/Y4bi\u0016,uN\u001a\t\u0003sij\u0011\u0001M\u0005\u0003wA\u0012qAQ8pY\u0016\fg.\u0001\u0004=S:LGO\u0010\u000b\u0006}\u0001\u000b%i\u0011\t\u0003\u007f\u0001i\u0011\u0001\u0004\u0005\u0006;\u0015\u0001\ra\b\u0005\u0006K\u0015\u0001\rA\n\u0005\u0006S\u0015\u0001\rA\u000b\u0005\bo\u0015\u0001\n\u00111\u00019\u0003\r\u0011XO\u001c\u000b\u0002\rB\u0011\u0011hR\u0005\u0003\u0011B\u0012A!\u00168ji\u0006q!+\u001a3je\u0016\u001cG\u000f\u00165sK\u0006$\u0007CA \t'\tAA\n\u0005\u0002:\u001b&\u0011a\n\r\u0002\u0007\u0003:L(+\u001a4\u0015\u0003)\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\"T#\u0001*+\u0005a\u001a6&\u0001+\u0011\u0005USV\"\u0001,\u000b\u0005]C\u0016!C;oG\",7m[3e\u0015\tI\u0006'\u0001\u0006b]:|G/\u0019;j_:L!a\u0017,\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r"
)
public class RedirectThread extends Thread {
   private final InputStream in;
   private final OutputStream out;
   private final boolean propagateEof;

   public static boolean $lessinit$greater$default$4() {
      return RedirectThread$.MODULE$.$lessinit$greater$default$4();
   }

   public void run() {
      .MODULE$.ignoring(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{IOException.class}))).apply((JFunction0.mcV.sp)() -> Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
            byte[] buf = new byte[1024];

            for(int len = this.in.read(buf); len != -1; len = this.in.read(buf)) {
               this.out.write(buf, 0, len);
               this.out.flush();
            }

         }, (JFunction0.mcV.sp)() -> {
            if (this.propagateEof) {
               this.out.close();
            }
         }));
   }

   public RedirectThread(final InputStream in, final OutputStream out, final String name, final boolean propagateEof) {
      super(name);
      this.in = in;
      this.out = out;
      this.propagateEof = propagateEof;
      this.setDaemon(true);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
