package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import java.io.Serializable;
import org.apache.spark.internal.Logging;
import scala.Option;
import scala.Product;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015a!\u0002\u0014(\u00016\u001a\u0004\u0002\u0003&\u0001\u0005+\u0007I\u0011A&\t\u0011m\u0003!\u0011#Q\u0001\n1C\u0001\u0002\u0018\u0001\u0003\u0016\u0004%\t!\u0018\u0005\t=\u0002\u0011\t\u0012)A\u0005)\")q\f\u0001C\u0001A\")A\r\u0001C\u0001K\"9a\u000fAA\u0001\n\u00039\bb\u0002>\u0001#\u0003%\ta\u001f\u0005\n\u0003\u001b\u0001\u0011\u0013!C\u0001\u0003\u001fA\u0011\"a\u0005\u0001\u0003\u0003%\t%!\u0006\t\u0013\u0005\u001d\u0002!!A\u0005\u0002\u0005%\u0002\"CA\u0019\u0001\u0005\u0005I\u0011AA\u001a\u0011%\ty\u0004AA\u0001\n\u0003\n\t\u0005C\u0005\u0002P\u0001\t\t\u0011\"\u0001\u0002R!I\u00111\f\u0001\u0002\u0002\u0013\u0005\u0013Q\f\u0005\n\u0003C\u0002\u0011\u0011!C!\u0003GB\u0011\"!\u001a\u0001\u0003\u0003%\t%a\u001a\t\u0013\u0005%\u0004!!A\u0005B\u0005-taBA8O!\u0005\u0011\u0011\u000f\u0004\u0007M\u001dB\t!a\u001d\t\r}#B\u0011AAE\u0011-\tY\t\u0006a\u0001\u0002\u0004%I!!$\t\u0017\u0005=E\u00031AA\u0002\u0013%\u0011\u0011\u0013\u0005\f\u00037#\u0002\u0019!A!B\u0013\t\u0019\u0006C\u0005\u0002\u001eR\u0001\r\u0011\"\u0003\u0002 \"I\u0011Q\u0015\u000bA\u0002\u0013%\u0011q\u0015\u0005\t\u0003W#\u0002\u0015)\u0003\u0002\"\"9\u0011Q\u0016\u000b\u0005\u0002\u0005=\u0006bBAW)\u0011\u0005\u00111\u0018\u0005\b\u0003{#B\u0011AA`\u0011\u001d\t)\r\u0006C\u0001\u0003\u000fDq!!4\u0015\t\u0013\ty\rC\u0004\u0002TR!I!!6\t\u000f\u0005mG\u0003\"\u0003\u0002^\"I\u0011Q\u0016\u000b\u0002\u0002\u0013\u0005\u0015\u0011\u001d\u0005\n\u0003O$\u0012\u0011!CA\u0003SD\u0011\"a?\u0015\u0003\u0003%I!!@\u0003)\u0015CXmY;u_J\u0004v\u000eZ:T]\u0006\u00048\u000f[8u\u0015\tA\u0013&A\u0002lqMT!AK\u0016\u0002\u000f\rdWo\u001d;fe*\u0011A&L\u0001\ng\u000eDW\rZ;mKJT!AL\u0018\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\n\u0014AB1qC\u000eDWMC\u00013\u0003\ry'oZ\n\u0005\u0001QRT\b\u0005\u00026q5\taGC\u00018\u0003\u0015\u00198-\u00197b\u0013\tIdG\u0001\u0004B]f\u0014VM\u001a\t\u0003kmJ!\u0001\u0010\u001c\u0003\u000fA\u0013x\u000eZ;diB\u0011ah\u0012\b\u0003\u007f\u0015s!\u0001\u0011#\u000e\u0003\u0005S!AQ\"\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aN\u0005\u0003\rZ\nq\u0001]1dW\u0006<W-\u0003\u0002I\u0013\na1+\u001a:jC2L'0\u00192mK*\u0011aIN\u0001\rKb,7-\u001e;peB{Gm]\u000b\u0002\u0019B!Q*\u0015+X\u001d\tqu\n\u0005\u0002Am%\u0011\u0001KN\u0001\u0007!J,G-\u001a4\n\u0005I\u001b&aA'ba*\u0011\u0001K\u000e\t\u0003kUK!A\u0016\u001c\u0003\t1{gn\u001a\t\u00031fk\u0011aJ\u0005\u00035\u001e\u0012\u0001#\u0012=fGV$xN\u001d)pIN#\u0018\r^3\u0002\u001b\u0015DXmY;u_J\u0004v\u000eZ:!\u000391W\u000f\u001c7T]\u0006\u00048\u000f[8u)N,\u0012\u0001V\u0001\u0010MVdGn\u00158baNDw\u000e\u001e+tA\u00051A(\u001b8jiz\"2!\u00192d!\tA\u0006\u0001C\u0003K\u000b\u0001\u0007A\nC\u0003]\u000b\u0001\u0007A+\u0001\u0006xSRDW\u000b\u001d3bi\u0016$\"!\u00194\t\u000b\u001d4\u0001\u0019\u00015\u0002\u0015U\u0004H-\u0019;fIB{G\r\u0005\u0002ji6\t!N\u0003\u0002lY\u0006)Qn\u001c3fY*\u0011QN\\\u0001\u0004CBL'BA8q\u0003)YWOY3s]\u0016$Xm\u001d\u0006\u0003cJ\fqAZ1ce&\u001c\u0007HC\u0001t\u0003\tIw.\u0003\u0002vU\n\u0019\u0001k\u001c3\u0002\t\r|\u0007/\u001f\u000b\u0004CbL\bb\u0002&\b!\u0003\u0005\r\u0001\u0014\u0005\b9\u001e\u0001\n\u00111\u0001U\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001 \u0016\u0003\u0019v\\\u0013A \t\u0004\u007f\u0006%QBAA\u0001\u0015\u0011\t\u0019!!\u0002\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0004m\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005-\u0011\u0011\u0001\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003#Q#\u0001V?\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t9\u0002\u0005\u0003\u0002\u001a\u0005\rRBAA\u000e\u0015\u0011\ti\"a\b\u0002\t1\fgn\u001a\u0006\u0003\u0003C\tAA[1wC&!\u0011QEA\u000e\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u00111\u0006\t\u0004k\u00055\u0012bAA\u0018m\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011QGA\u001e!\r)\u0014qG\u0005\u0004\u0003s1$aA!os\"I\u0011Q\b\u0007\u0002\u0002\u0003\u0007\u00111F\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\r\u0003CBA#\u0003\u0017\n)$\u0004\u0002\u0002H)\u0019\u0011\u0011\n\u001c\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002N\u0005\u001d#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0015\u0002ZA\u0019Q'!\u0016\n\u0007\u0005]cGA\u0004C_>dW-\u00198\t\u0013\u0005ub\"!AA\u0002\u0005U\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u0006\u0002`!I\u0011QH\b\u0002\u0002\u0003\u0007\u00111F\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u00111F\u0001\ti>\u001cFO]5oOR\u0011\u0011qC\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005M\u0013Q\u000e\u0005\n\u0003{\u0011\u0012\u0011!a\u0001\u0003k\tA#\u0012=fGV$xN\u001d)pIN\u001cf.\u00199tQ>$\bC\u0001-\u0015'\u0019!B'!\u001e\u0002\u0002B!\u0011qOA?\u001b\t\tIHC\u0002\u0002|5\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0005\u0003\u007f\nIHA\u0004M_\u001e<\u0017N\\4\u0011\t\u0005\r\u0015qQ\u0007\u0003\u0003\u000bS1a]A\u0010\u0013\rA\u0015Q\u0011\u000b\u0003\u0003c\n\u0001d\u001d5pk2$7\t[3dW\u0006cGnQ8oi\u0006Lg.\u001a:t+\t\t\u0019&\u0001\u000ftQ>,H\u000eZ\"iK\u000e\\\u0017\t\u001c7D_:$\u0018-\u001b8feN|F%Z9\u0015\t\u0005M\u0015\u0011\u0014\t\u0004k\u0005U\u0015bAALm\t!QK\\5u\u0011%\tidFA\u0001\u0002\u0004\t\u0019&A\rtQ>,H\u000eZ\"iK\u000e\\\u0017\t\u001c7D_:$\u0018-\u001b8feN\u0004\u0013AE:qCJ\\7i\u001c8uC&tWM\u001d(b[\u0016,\"!!)\u0011\u00075\u000b\u0019+C\u0002\u0002&M\u000bac\u001d9be.\u001cuN\u001c;bS:,'OT1nK~#S-\u001d\u000b\u0005\u0003'\u000bI\u000bC\u0005\u0002>i\t\t\u00111\u0001\u0002\"\u0006\u00192\u000f]1sW\u000e{g\u000e^1j]\u0016\u0014h*Y7fA\u0005)\u0011\r\u001d9msR)\u0011-!-\u0002:\"1!\n\ba\u0001\u0003g\u0003BAPA[Q&\u0019\u0011qW%\u0003\u0007M+\u0017\u000fC\u0003]9\u0001\u0007A\u000bF\u0001b\u0003m\u0019X\r^*i_VdGm\u00115fG.\fE\u000e\\\"p]R\f\u0017N\\3sgR!\u00111SAa\u0011\u001d\t\u0019M\ba\u0001\u0003'\n!c^1uG\"\fE\u000e\\\"p]R\f\u0017N\\3sg\u0006)2/\u001a;Ta\u0006\u00148nQ8oi\u0006Lg.\u001a:OC6,G\u0003BAJ\u0003\u0013Dq!a3 \u0001\u0004\t\t+A\u0007d_:$\u0018-\u001b8fe:\u000bW.Z\u0001\u0015i>\u001cF/\u0019;fg\nKX\t_3dkR|'/\u00133\u0015\u00071\u000b\t\u000e\u0003\u0004KA\u0001\u0007\u00111W\u0001\bi>\u001cF/\u0019;f)\r9\u0016q\u001b\u0005\u0007\u00033\f\u0003\u0019\u00015\u0002\u0007A|G-A\u0005jg\u0012+G.\u001a;fIR!\u00111KAp\u0011\u0019\tIN\ta\u0001QR)\u0011-a9\u0002f\")!j\ta\u0001\u0019\")Al\ta\u0001)\u00069QO\\1qa2LH\u0003BAv\u0003o\u0004R!NAw\u0003cL1!a<7\u0005\u0019y\u0005\u000f^5p]B)Q'a=M)&\u0019\u0011Q\u001f\u001c\u0003\rQ+\b\u000f\\33\u0011!\tI\u0010JA\u0001\u0002\u0004\t\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q \t\u0005\u00033\u0011\t!\u0003\u0003\u0003\u0004\u0005m!AB(cU\u0016\u001cG\u000f"
)
public class ExecutorPodsSnapshot implements Product, Serializable {
   private final Map executorPods;
   private final long fullSnapshotTs;

   public static Option unapply(final ExecutorPodsSnapshot x$0) {
      return ExecutorPodsSnapshot$.MODULE$.unapply(x$0);
   }

   public static ExecutorPodsSnapshot apply(final Map executorPods, final long fullSnapshotTs) {
      return ExecutorPodsSnapshot$.MODULE$.apply(executorPods, fullSnapshotTs);
   }

   public static void setSparkContainerName(final String containerName) {
      ExecutorPodsSnapshot$.MODULE$.setSparkContainerName(containerName);
   }

   public static void setShouldCheckAllContainers(final boolean watchAllContainers) {
      ExecutorPodsSnapshot$.MODULE$.setShouldCheckAllContainers(watchAllContainers);
   }

   public static ExecutorPodsSnapshot apply() {
      return ExecutorPodsSnapshot$.MODULE$.apply();
   }

   public static ExecutorPodsSnapshot apply(final Seq executorPods, final long fullSnapshotTs) {
      return ExecutorPodsSnapshot$.MODULE$.apply(executorPods, fullSnapshotTs);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return ExecutorPodsSnapshot$.MODULE$.LogStringContext(sc);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Map executorPods() {
      return this.executorPods;
   }

   public long fullSnapshotTs() {
      return this.fullSnapshotTs;
   }

   public ExecutorPodsSnapshot withUpdate(final Pod updatedPod) {
      Map newExecutorPods = (Map)this.executorPods().$plus$plus(ExecutorPodsSnapshot$.MODULE$.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshot$$toStatesByExecutorId(new .colon.colon(updatedPod, scala.collection.immutable.Nil..MODULE$)));
      return new ExecutorPodsSnapshot(newExecutorPods, this.fullSnapshotTs());
   }

   public ExecutorPodsSnapshot copy(final Map executorPods, final long fullSnapshotTs) {
      return new ExecutorPodsSnapshot(executorPods, fullSnapshotTs);
   }

   public Map copy$default$1() {
      return this.executorPods();
   }

   public long copy$default$2() {
      return this.fullSnapshotTs();
   }

   public String productPrefix() {
      return "ExecutorPodsSnapshot";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.executorPods();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.fullSnapshotTs());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ExecutorPodsSnapshot;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "executorPods";
         }
         case 1 -> {
            return "fullSnapshotTs";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.executorPods()));
      var1 = Statics.mix(var1, Statics.longHash(this.fullSnapshotTs()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof ExecutorPodsSnapshot) {
               ExecutorPodsSnapshot var4 = (ExecutorPodsSnapshot)x$1;
               if (this.fullSnapshotTs() == var4.fullSnapshotTs()) {
                  label44: {
                     Map var10000 = this.executorPods();
                     Map var5 = var4.executorPods();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
                     }
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public ExecutorPodsSnapshot(final Map executorPods, final long fullSnapshotTs) {
      this.executorPods = executorPods;
      this.fullSnapshotTs = fullSnapshotTs;
      Product.$init$(this);
   }
}
