package org.apache.spark.util;

import java.io.File;
import org.apache.spark.internal.Logging;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015qA\u0002\u000b\u0016\u0011\u00039RD\u0002\u0004 +!\u0005q\u0003\t\u0005\u0006[\u0005!\ta\f\u0005\ba\u0005\u0011\r\u0011\"\u00012\u0011\u0019)\u0014\u0001)A\u0005e!9a'\u0001b\u0001\n\u0003\t\u0004BB\u001c\u0002A\u0003%!\u0007C\u00049\u0003\t\u0007I\u0011A\u0019\t\re\n\u0001\u0015!\u00033\u0011!Q\u0014\u0001#b\u0001\n\u0013Y\u0004bB \u0002\u0005\u0004%I\u0001\u0011\u0005\u0007)\u0006\u0001\u000b\u0011B!\t\u000bU\u000bA\u0011\u0001,\t\u000b\u0011\fA\u0011A3\t\u000b\u001d\fA\u0011\u00015\t\u000b5\fA\u0011\u00018\t\u000bA\fA\u0011A9\t\u000bI\fA\u0011A:\t\u000bI\fA\u0011A=\t\u000by\fA\u0011A@\u0002'MCW\u000f\u001e3po:Dun\\6NC:\fw-\u001a:\u000b\u0005Y9\u0012\u0001B;uS2T!\u0001G\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005iY\u0012AB1qC\u000eDWMC\u0001\u001d\u0003\ry'o\u001a\t\u0003=\u0005i\u0011!\u0006\u0002\u0014'\",H\u000fZ8x]\"{wn['b]\u0006<WM]\n\u0004\u0003\u0005:\u0003C\u0001\u0012&\u001b\u0005\u0019#\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a#AB!osJ+g\r\u0005\u0002)W5\t\u0011F\u0003\u0002+/\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002-S\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003u\t\u0011\u0004R#G\u0003VcEkX*I+R#uj\u0016(`!JKuJU%U3V\t!\u0007\u0005\u0002#g%\u0011Ag\t\u0002\u0004\u0013:$\u0018A\u0007#F\r\u0006+F\nV0T\u0011V#FiT,O?B\u0013\u0016j\u0014*J)f\u0003\u0013aH*Q\u0003J[ulQ(O)\u0016CFkX*I+R#uj\u0016(`!JKuJU%U3\u0006\u00013\u000bU!S\u0017~\u001buJ\u0014+F1R{6\u000bS+U\t>;fj\u0018)S\u0013>\u0013\u0016\nV-!\u0003i!V)\u0014)`\t&\u0013vl\u0015%V)\u0012{uKT0Q%&{%+\u0013+Z\u0003m!V)\u0014)`\t&\u0013vl\u0015%V)\u0012{uKT0Q%&{%+\u0013+ZA\u0005i1\u000f[;uI><h\u000eS8pWN,\u0012\u0001\u0010\t\u0003=uJ!AP\u000b\u00031M\u0003\u0018M]6TQV$Hm\\<o\u0011>|7.T1oC\u001e,'/A\ntQV$Hm\\<o\t\u0016dW\r^3QCRD7/F\u0001B!\r\u0011u)S\u0007\u0002\u0007*\u0011A)R\u0001\b[V$\u0018M\u00197f\u0015\t15%\u0001\u0006d_2dWm\u0019;j_:L!\u0001S\"\u0003\u000f!\u000b7\u000f[*fiB\u0011!*\u0015\b\u0003\u0017>\u0003\"\u0001T\u0012\u000e\u00035S!A\u0014\u0018\u0002\rq\u0012xn\u001c;?\u0013\t\u00016%\u0001\u0004Qe\u0016$WMZ\u0005\u0003%N\u0013aa\u0015;sS:<'B\u0001)$\u0003Q\u0019\b.\u001e;e_^tG)\u001a7fi\u0016\u0004\u0016\r\u001e5tA\u0005I\"/Z4jgR,'o\u00155vi\u0012|wO\u001c#fY\u0016$X\rR5s)\t9&\f\u0005\u0002#1&\u0011\u0011l\t\u0002\u0005+:LG\u000fC\u0003\\\u0019\u0001\u0007A,\u0001\u0003gS2,\u0007CA/c\u001b\u0005q&BA0a\u0003\tIwNC\u0001b\u0003\u0011Q\u0017M^1\n\u0005\rt&\u0001\u0002$jY\u0016\fqC]3n_Z,7\u000b[;uI><h\u000eR3mKR,G)\u001b:\u0015\u0005]3\u0007\"B.\u000e\u0001\u0004a\u0016\u0001\u00065bgNCW\u000f\u001e3po:$U\r\\3uK\u0012K'\u000f\u0006\u0002jYB\u0011!E[\u0005\u0003W\u000e\u0012qAQ8pY\u0016\fg\u000eC\u0003\\\u001d\u0001\u0007A,\u0001\u000eiCN\u0014vn\u001c;BgNCW\u000f\u001e3po:$U\r\\3uK\u0012K'\u000f\u0006\u0002j_\")1l\u0004a\u00019\u0006Q\u0011N\\*ikR$wn\u001e8\u0015\u0003%\fq\"\u00193e'\",H\u000fZ8x]\"{wn\u001b\u000b\u0003CQDQ!^\tA\u0002Y\fA\u0001[8pWB\u0019!e^,\n\u0005a\u001c#!\u0003$v]\u000e$\u0018n\u001c81)\tQH\u0010\u0006\u0002\"w\")QO\u0005a\u0001m\")QP\u0005a\u0001e\u0005A\u0001O]5pe&$\u00180\u0001\nsK6|g/Z*ikR$wn\u001e8I_>\\GcA5\u0002\u0002!1\u00111A\nA\u0002\u0005\n1A]3g\u0001"
)
public final class ShutdownHookManager {
   public static boolean removeShutdownHook(final Object ref) {
      return ShutdownHookManager$.MODULE$.removeShutdownHook(ref);
   }

   public static Object addShutdownHook(final int priority, final Function0 hook) {
      return ShutdownHookManager$.MODULE$.addShutdownHook(priority, hook);
   }

   public static Object addShutdownHook(final Function0 hook) {
      return ShutdownHookManager$.MODULE$.addShutdownHook(hook);
   }

   public static boolean inShutdown() {
      return ShutdownHookManager$.MODULE$.inShutdown();
   }

   public static boolean hasRootAsShutdownDeleteDir(final File file) {
      return ShutdownHookManager$.MODULE$.hasRootAsShutdownDeleteDir(file);
   }

   public static boolean hasShutdownDeleteDir(final File file) {
      return ShutdownHookManager$.MODULE$.hasShutdownDeleteDir(file);
   }

   public static void removeShutdownDeleteDir(final File file) {
      ShutdownHookManager$.MODULE$.removeShutdownDeleteDir(file);
   }

   public static void registerShutdownDeleteDir(final File file) {
      ShutdownHookManager$.MODULE$.registerShutdownDeleteDir(file);
   }

   public static int TEMP_DIR_SHUTDOWN_PRIORITY() {
      return ShutdownHookManager$.MODULE$.TEMP_DIR_SHUTDOWN_PRIORITY();
   }

   public static int SPARK_CONTEXT_SHUTDOWN_PRIORITY() {
      return ShutdownHookManager$.MODULE$.SPARK_CONTEXT_SHUTDOWN_PRIORITY();
   }

   public static int DEFAULT_SHUTDOWN_PRIORITY() {
      return ShutdownHookManager$.MODULE$.DEFAULT_SHUTDOWN_PRIORITY();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return ShutdownHookManager$.MODULE$.LogStringContext(sc);
   }
}
