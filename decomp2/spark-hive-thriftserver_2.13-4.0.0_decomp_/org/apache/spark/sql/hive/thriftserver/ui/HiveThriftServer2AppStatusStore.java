package org.apache.spark.sql.hive.thriftserver.ui;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import org.apache.spark.status.KVUtils.;
import org.apache.spark.util.kvstore.KVStore;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4Aa\u0003\u0007\u00017!A!\u0005\u0001B\u0001B\u0003%1\u0005C\u0003,\u0001\u0011\u0005A\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u0003B\u0001\u0011\u0005!\tC\u0003H\u0001\u0011\u0005\u0001\nC\u0003M\u0001\u0011\u0005Q\nC\u0003\\\u0001\u0011\u0005A\fC\u0003a\u0001\u0011\u0005\u0001\nC\u0003b\u0001\u0011\u0005!\rC\u0003g\u0001\u0011\u0005!MA\u0010ISZ,G\u000b\u001b:jMR\u001cVM\u001d<feJ\n\u0005\u000f]*uCR,8o\u0015;pe\u0016T!!\u0004\b\u0002\u0005UL'BA\b\u0011\u00031!\bN]5giN,'O^3s\u0015\t\t\"#\u0001\u0003iSZ,'BA\n\u0015\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003+Y\tQa\u001d9be.T!a\u0006\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0012aA8sO\u000e\u00011C\u0001\u0001\u001d!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\u0019\te.\u001f*fM\u0006)1\u000f^8sKB\u0011A%K\u0007\u0002K)\u0011aeJ\u0001\bWZ\u001cHo\u001c:f\u0015\tAC#\u0001\u0003vi&d\u0017B\u0001\u0016&\u0005\u001dYek\u0015;pe\u0016\fa\u0001P5oSRtDCA\u00170!\tq\u0003!D\u0001\r\u0011\u0015\u0011#\u00011\u0001$\u000399W\r^*fgNLwN\u001c'jgR,\u0012A\r\t\u0004gmrdB\u0001\u001b:\u001d\t)\u0004(D\u00017\u0015\t9$$\u0001\u0004=e>|GOP\u0005\u0002?%\u0011!HH\u0001\ba\u0006\u001c7.Y4f\u0013\taTHA\u0002TKFT!A\u000f\u0010\u0011\u00059z\u0014B\u0001!\r\u0005-\u0019Vm]:j_:LeNZ8\u0002!\u001d,G/\u0012=fGV$\u0018n\u001c8MSN$X#A\"\u0011\u0007MZD\t\u0005\u0002/\u000b&\u0011a\t\u0004\u0002\u000e\u000bb,7-\u001e;j_:LeNZ8\u0002'\u001d,Go\u00148mS:,7+Z:tS>tg*^7\u0016\u0003%\u0003\"!\b&\n\u0005-s\"aA%oi\u0006Qq-\u001a;TKN\u001c\u0018n\u001c8\u0015\u00059\u000b\u0006cA\u000fP}%\u0011\u0001K\b\u0002\u0007\u001fB$\u0018n\u001c8\t\u000bI3\u0001\u0019A*\u0002\u0013M,7o]5p]&#\u0007C\u0001+Y\u001d\t)f\u000b\u0005\u00026=%\u0011qKH\u0001\u0007!J,G-\u001a4\n\u0005eS&AB*ue&twM\u0003\u0002X=\u0005aq-\u001a;Fq\u0016\u001cW\u000f^5p]R\u0011QL\u0018\t\u0004;=#\u0005\"B0\b\u0001\u0004\u0019\u0016aC3yK\u000e,H/[8o\u0013\u0012\fqbZ3u)>$\u0018\r\u001c*v]:LgnZ\u0001\u0010O\u0016$8+Z:tS>t7i\\;oiV\t1\r\u0005\u0002\u001eI&\u0011QM\b\u0002\u0005\u0019>tw-A\thKR,\u00050Z2vi&|gnQ8v]R\u0004"
)
public class HiveThriftServer2AppStatusStore {
   private final KVStore store;

   public Seq getSessionList() {
      return .MODULE$.viewToSeq(this.store.view(SessionInfo.class));
   }

   public Seq getExecutionList() {
      return .MODULE$.viewToSeq(this.store.view(ExecutionInfo.class));
   }

   public int getOnlineSessionNum() {
      return .MODULE$.count(this.store.view(SessionInfo.class), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$getOnlineSessionNum$1(x$1)));
   }

   public Option getSession(final String sessionId) {
      Object var10000;
      try {
         var10000 = new Some(this.store.read(SessionInfo.class, sessionId));
      } catch (NoSuchElementException var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   public Option getExecution(final String executionId) {
      Object var10000;
      try {
         var10000 = new Some(this.store.read(ExecutionInfo.class, executionId));
      } catch (NoSuchElementException var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   public int getTotalRunning() {
      return .MODULE$.count(this.store.view(ExecutionInfo.class), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$getTotalRunning$1(x$2)));
   }

   public long getSessionCount() {
      return this.store.count(SessionInfo.class);
   }

   public long getExecutionCount() {
      return this.store.count(ExecutionInfo.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getOnlineSessionNum$1(final SessionInfo x$1) {
      return x$1.finishTimestamp() == 0L;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getTotalRunning$1(final ExecutionInfo x$2) {
      return x$2.isExecutionActive();
   }

   public HiveThriftServer2AppStatusStore(final KVStore store) {
      this.store = store;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
