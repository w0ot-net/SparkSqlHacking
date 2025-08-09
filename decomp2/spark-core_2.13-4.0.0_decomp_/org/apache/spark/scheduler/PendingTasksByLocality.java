package org.apache.spark.scheduler;

import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193Q\u0001D\u0007\u0001\u001bUAQ\u0001\b\u0001\u0005\u0002yAq!\t\u0001C\u0002\u0013\u0005!\u0005\u0003\u0004=\u0001\u0001\u0006Ia\t\u0005\b{\u0001\u0011\r\u0011\"\u0001#\u0011\u0019q\u0004\u0001)A\u0005G!9q\b\u0001b\u0001\n\u0003\u0001\u0005BB!\u0001A\u0003%a\u0007C\u0004C\u0001\t\u0007I\u0011\u0001\u0012\t\r\r\u0003\u0001\u0015!\u0003$\u0011\u001d!\u0005A1A\u0005\u0002\u0001Ca!\u0012\u0001!\u0002\u00131$A\u0006)f]\u0012Lgn\u001a+bg.\u001c()\u001f'pG\u0006d\u0017\u000e^=\u000b\u00059y\u0011!C:dQ\u0016$W\u000f\\3s\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7C\u0001\u0001\u0017!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001 !\t\u0001\u0003!D\u0001\u000e\u0003-1wN]#yK\u000e,Ho\u001c:\u0016\u0003\r\u0002B\u0001J\u0015,m5\tQE\u0003\u0002'O\u00059Q.\u001e;bE2,'B\u0001\u0015\u0019\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003U\u0015\u0012q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0002-g9\u0011Q&\r\t\u0003]ai\u0011a\f\u0006\u0003au\ta\u0001\u0010:p_Rt\u0014B\u0001\u001a\u0019\u0003\u0019\u0001&/\u001a3fM&\u0011A'\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005IB\u0002c\u0001\u00138s%\u0011\u0001(\n\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000f\u0005\u0002\u0018u%\u00111\b\u0007\u0002\u0004\u0013:$\u0018\u0001\u00044pe\u0016CXmY;u_J\u0004\u0013a\u00024pe\"{7\u000f^\u0001\tM>\u0014\bj\\:uA\u00059an\u001c)sK\u001a\u001cX#\u0001\u001c\u0002\u00119|\u0007K]3gg\u0002\nqAZ8s%\u0006\u001c7.\u0001\u0005g_J\u0014\u0016mY6!\u0003\r\tG\u000e\\\u0001\u0005C2d\u0007\u0005"
)
public class PendingTasksByLocality {
   private final HashMap forExecutor = new HashMap();
   private final HashMap forHost = new HashMap();
   private final ArrayBuffer noPrefs = new ArrayBuffer();
   private final HashMap forRack = new HashMap();
   private final ArrayBuffer all = new ArrayBuffer();

   public HashMap forExecutor() {
      return this.forExecutor;
   }

   public HashMap forHost() {
      return this.forHost;
   }

   public ArrayBuffer noPrefs() {
      return this.noPrefs;
   }

   public HashMap forRack() {
      return this.forRack;
   }

   public ArrayBuffer all() {
      return this.all;
   }
}
