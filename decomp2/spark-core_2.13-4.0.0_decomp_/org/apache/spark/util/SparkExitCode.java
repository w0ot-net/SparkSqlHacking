package org.apache.spark.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015;aa\u0006\r\t\u0002i\u0001cA\u0002\u0012\u0019\u0011\u0003Q2\u0005C\u0003+\u0003\u0011\u0005A\u0006C\u0004.\u0003\t\u0007I\u0011\u0001\u0018\t\rI\n\u0001\u0015!\u00030\u0011\u001d\u0019\u0014A1A\u0005\u00029Ba\u0001N\u0001!\u0002\u0013y\u0003bB\u001b\u0002\u0005\u0004%\tA\f\u0005\u0007m\u0005\u0001\u000b\u0011B\u0018\t\u000f]\n!\u0019!C\u0001]!1\u0001(\u0001Q\u0001\n=Bq!O\u0001C\u0002\u0013\u0005a\u0006\u0003\u0004;\u0003\u0001\u0006Ia\f\u0005\bw\u0005\u0011\r\u0011\"\u0001/\u0011\u0019a\u0014\u0001)A\u0005_!9Q(\u0001b\u0001\n\u0003q\u0003B\u0002 \u0002A\u0003%q\u0006C\u0004@\u0003\t\u0007I\u0011\u0001\u0018\t\r\u0001\u000b\u0001\u0015!\u00030\u0011\u001d\t\u0015A1A\u0005\u00029BaAQ\u0001!\u0002\u0013y\u0003bB\"\u0002\u0005\u0004%\tA\f\u0005\u0007\t\u0006\u0001\u000b\u0011B\u0018\u0002\u001bM\u0003\u0018M]6Fq&$8i\u001c3f\u0015\tI\"$\u0001\u0003vi&d'BA\u000e\u001d\u0003\u0015\u0019\b/\u0019:l\u0015\tib$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002?\u0005\u0019qN]4\u0011\u0005\u0005\nQ\"\u0001\r\u0003\u001bM\u0003\u0018M]6Fq&$8i\u001c3f'\t\tA\u0005\u0005\u0002&Q5\taEC\u0001(\u0003\u0015\u00198-\u00197b\u0013\tIcE\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t\u0001%\u0001\u0007F1&#vlU+D\u0007\u0016\u001b6+F\u00010!\t)\u0003'\u0003\u00022M\t\u0019\u0011J\u001c;\u0002\u001b\u0015C\u0016\nV0T+\u000e\u001bUiU*!\u00031)\u0005,\u0013+`\r\u0006KE*\u0016*F\u00035)\u0005,\u0013+`\r\u0006KE*\u0016*FA\u0005QRI\u0015*P%~k\u0015jU+T\u000b~\u001b\u0006*\u0012'M?\n+\u0016\n\u0014+J\u001d\u0006YRI\u0015*P%~k\u0015jU+T\u000b~\u001b\u0006*\u0012'M?\n+\u0016\n\u0014+J\u001d\u0002\nA#\u0012*S\u001fJ{\u0006+\u0011+I?:{Ek\u0018$P+:#\u0015!F#S%>\u0013v\fU!U\u0011~su\nV0G\u001fVsE\tI\u0001\u001d\u000bb\u001bU)\u0012#`\u001b\u0006Cv,\u0012-F\u0007V#vJU0G\u0003&cUKU#T\u0003u)\u0005lQ#F\t~k\u0015\tW0F1\u0016\u001bU\u000bV(S?\u001a\u000b\u0015\nT+S\u000bN\u0003\u0013AE+O\u0007\u0006+v\t\u0013+`\u000bb\u001bU\t\u0015+J\u001f:\u000b1#\u0016(D\u0003V;\u0005\nV0F1\u000e+\u0005\u000bV%P\u001d\u0002\n\u0001$\u0016(D\u0003V;\u0005\nV0F1\u000e+\u0005\u000bV%P\u001d~#v+S\"F\u0003e)fjQ!V\u000f\"#v,\u0012-D\u000bB#\u0016j\u0014(`)^K5)\u0012\u0011\u0002\u0007={U*\u0001\u0003P\u001f6\u0003\u0013A\u0004#S\u0013Z+%k\u0018+J\u001b\u0016{U\u000bV\u0001\u0010\tJKe+\u0012*`)&kUiT+UA\u00059RI\u0015*P%~\u001bu*T'B\u001d\u0012{fj\u0014+`\r>+f\nR\u0001\u0019\u000bJ\u0013vJU0D\u001f6k\u0015I\u0014#`\u001d>#vLR(V\u001d\u0012\u0003\u0003"
)
public final class SparkExitCode {
   public static int ERROR_COMMAND_NOT_FOUND() {
      return SparkExitCode$.MODULE$.ERROR_COMMAND_NOT_FOUND();
   }

   public static int DRIVER_TIMEOUT() {
      return SparkExitCode$.MODULE$.DRIVER_TIMEOUT();
   }

   public static int OOM() {
      return SparkExitCode$.MODULE$.OOM();
   }

   public static int UNCAUGHT_EXCEPTION_TWICE() {
      return SparkExitCode$.MODULE$.UNCAUGHT_EXCEPTION_TWICE();
   }

   public static int UNCAUGHT_EXCEPTION() {
      return SparkExitCode$.MODULE$.UNCAUGHT_EXCEPTION();
   }

   public static int EXCEED_MAX_EXECUTOR_FAILURES() {
      return SparkExitCode$.MODULE$.EXCEED_MAX_EXECUTOR_FAILURES();
   }

   public static int ERROR_PATH_NOT_FOUND() {
      return SparkExitCode$.MODULE$.ERROR_PATH_NOT_FOUND();
   }

   public static int ERROR_MISUSE_SHELL_BUILTIN() {
      return SparkExitCode$.MODULE$.ERROR_MISUSE_SHELL_BUILTIN();
   }

   public static int EXIT_FAILURE() {
      return SparkExitCode$.MODULE$.EXIT_FAILURE();
   }

   public static int EXIT_SUCCESS() {
      return SparkExitCode$.MODULE$.EXIT_SUCCESS();
   }
}
