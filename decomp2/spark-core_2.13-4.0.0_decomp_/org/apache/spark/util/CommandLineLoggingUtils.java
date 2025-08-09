package org.apache.spark.util;

import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005%3\u0001\u0002C\u0005\u0011\u0002\u0007\u00051\"\u0005\u0005\u00061\u0001!\tA\u0007\u0005\t=\u0001\u0001\r\u0011\"\u0001\f?!Aa\u0005\u0001a\u0001\n\u0003Yq\u0005\u0003\u0005+\u0001\u0001\u0007I\u0011A\u0006,\u0011!!\u0004\u00011A\u0005\u0002-)\u0004BB\u001c\u0001\t\u0003Y\u0001\b\u0003\u0004G\u0001\u0011\u00051b\u0012\u0002\u0018\u0007>lW.\u00198e\u0019&tW\rT8hO&tw-\u0016;jYNT!AC\u0006\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0011\u0001A\u0005\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012a\u0007\t\u0003'qI!!\b\u000b\u0003\tUs\u0017\u000e^\u0001\u0007KbLGO\u00128\u0016\u0003\u0001\u0002BaE\u0011$7%\u0011!\u0005\u0006\u0002\n\rVt7\r^5p]F\u0002\"a\u0005\u0013\n\u0005\u0015\"\"aA%oi\u0006QQ\r_5u\r:|F%Z9\u0015\u0005mA\u0003bB\u0015\u0004\u0003\u0003\u0005\r\u0001I\u0001\u0004q\u0012\n\u0014a\u00039sS:$8\u000b\u001e:fC6,\u0012\u0001\f\t\u0003[Ij\u0011A\f\u0006\u0003_A\n!![8\u000b\u0003E\nAA[1wC&\u00111G\f\u0002\f!JLg\u000e^*ue\u0016\fW.A\bqe&tGo\u0015;sK\u0006lw\fJ3r)\tYb\u0007C\u0004*\u000b\u0005\u0005\t\u0019\u0001\u0017\u0002\u0019A\u0014\u0018N\u001c;NKN\u001c\u0018mZ3\u0015\u0005mI\u0004\"\u0002\u001e\u0007\u0001\u0004Y\u0014aA:ueB\u0011Ah\u0011\b\u0003{\u0005\u0003\"A\u0010\u000b\u000e\u0003}R!\u0001Q\r\u0002\rq\u0012xn\u001c;?\u0013\t\u0011E#\u0001\u0004Qe\u0016$WMZ\u0005\u0003\t\u0016\u0013aa\u0015;sS:<'B\u0001\"\u0015\u0003E\u0001(/\u001b8u\u000bJ\u0014xN]!oI\u0016C\u0018\u000e\u001e\u000b\u00037!CQAO\u0004A\u0002m\u0002"
)
public interface CommandLineLoggingUtils {
   Function1 exitFn();

   void exitFn_$eq(final Function1 x$1);

   PrintStream printStream();

   void printStream_$eq(final PrintStream x$1);

   // $FF: synthetic method
   static void printMessage$(final CommandLineLoggingUtils $this, final String str) {
      $this.printMessage(str);
   }

   default void printMessage(final String str) {
      this.printStream().println(str);
   }

   // $FF: synthetic method
   static void printErrorAndExit$(final CommandLineLoggingUtils $this, final String str) {
      $this.printErrorAndExit(str);
   }

   default void printErrorAndExit(final String str) {
      this.printMessage("Error: " + str);
      this.printMessage("Run with --help for usage help or --verbose for debug output");
      this.exitFn().apply$mcVI$sp(1);
   }

   static void $init$(final CommandLineLoggingUtils $this) {
      $this.exitFn_$eq((JFunction1.mcVI.sp)(exitCode) -> System.exit(exitCode));
      $this.printStream_$eq(System.err);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
