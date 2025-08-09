package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192\u0001b\u0001\u0003\u0011\u0002\u0007\u0005\u0011b\t\u0005\u0006!\u0001!\t!\u0005\u0005\b+\u0001\u0011\r\u0011b\u0002\u0017\u0005Q\t5OS:p]&s\u0007/\u001e;J]N$\u0018M\\2fg*\u0011QAB\u0001\u0007UN|g\u000eN:\u000b\u0003\u001d\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-qQ\"\u0001\u0007\u000b\u00035\tQa]2bY\u0006L!a\u0004\u0007\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0003\u0005\u0002\f'%\u0011A\u0003\u0004\u0002\u0005+:LG/A\bgS2,\u0017i\u001d&t_:Le\u000e];u+\u00059\u0002c\u0001\r\u001a75\tA!\u0003\u0002\u001b\t\tY\u0011i\u001d&t_:Le\u000e];u!\ta\u0012%D\u0001\u001e\u0015\tqr$\u0001\u0002j_*\t\u0001%\u0001\u0003kCZ\f\u0017B\u0001\u0012\u001e\u0005\u00111\u0015\u000e\\3\u000f\u0005a!\u0013BA\u0013\u0005\u0003-\t5OS:p]&s\u0007/\u001e;"
)
public interface AsJsonInputInstances {
   void org$json4s$AsJsonInputInstances$_setter_$fileAsJsonInput_$eq(final AsJsonInput x$1);

   AsJsonInput fileAsJsonInput();

   static void $init$(final AsJsonInputInstances $this) {
      $this.org$json4s$AsJsonInputInstances$_setter_$fileAsJsonInput_$eq(((AsJsonInput$)$this).fromFunction((x) -> new FileInput(x)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
