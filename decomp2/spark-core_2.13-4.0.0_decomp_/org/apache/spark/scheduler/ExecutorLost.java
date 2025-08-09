package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-d!B\r\u001b\u0001j\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011\u001d\u0003!\u0011#Q\u0001\n}B\u0001\u0002\u0013\u0001\u0003\u0016\u0004%\t!\u0013\u0005\t\u001b\u0002\u0011\t\u0012)A\u0005\u0015\")a\n\u0001C\u0001\u001f\"91\u000bAA\u0001\n\u0003!\u0006bB,\u0001#\u0003%\t\u0001\u0017\u0005\bG\u0002\t\n\u0011\"\u0001e\u0011\u001d1\u0007!!A\u0005B\u001dDqa\u001c\u0001\u0002\u0002\u0013\u0005\u0001\u000fC\u0004u\u0001\u0005\u0005I\u0011A;\t\u000fm\u0004\u0011\u0011!C!y\"I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0002\u0005\n\u0003'\u0001\u0011\u0011!C!\u0003+A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\t\u0013\u0005u\u0001!!A\u0005B\u0005}\u0001\"CA\u0011\u0001\u0005\u0005I\u0011IA\u0012\u000f)\t9CGA\u0001\u0012\u0003Q\u0012\u0011\u0006\u0004\n3i\t\t\u0011#\u0001\u001b\u0003WAaAT\n\u0005\u0002\u0005\r\u0003\"CA\u000f'\u0005\u0005IQIA\u0010\u0011%\t)eEA\u0001\n\u0003\u000b9\u0005C\u0005\u0002NM\t\t\u0011\"!\u0002P!I\u0011\u0011M\n\u0002\u0002\u0013%\u00111\r\u0002\r\u000bb,7-\u001e;pe2{7\u000f\u001e\u0006\u00037q\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001cR\u0001A\u0012*[A\u0002\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a!\u00118z%\u00164\u0007C\u0001\u0016,\u001b\u0005Q\u0012B\u0001\u0017\u001b\u0005E!\u0015iR*dQ\u0016$W\u000f\\3s\u000bZ,g\u000e\u001e\t\u0003I9J!aL\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011G\u000f\b\u0003ear!aM\u001c\u000e\u0003QR!!\u000e\u001c\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AJ\u0005\u0003s\u0015\nq\u0001]1dW\u0006<W-\u0003\u0002<y\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011(J\u0001\u0007Kb,7-\u00133\u0016\u0003}\u0002\"\u0001\u0011#\u000f\u0005\u0005\u0013\u0005CA\u001a&\u0013\t\u0019U%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000b\u001a\u0013aa\u0015;sS:<'BA\"&\u0003\u001d)\u00070Z2JI\u0002\naA]3bg>tW#\u0001&\u0011\u0005)Z\u0015B\u0001'\u001b\u0005I)\u00050Z2vi>\u0014Hj\\:t%\u0016\f7o\u001c8\u0002\u000fI,\u0017m]8oA\u00051A(\u001b8jiz\"2\u0001U)S!\tQ\u0003\u0001C\u0003>\u000b\u0001\u0007q\bC\u0003I\u000b\u0001\u0007!*\u0001\u0003d_BLHc\u0001)V-\"9QH\u0002I\u0001\u0002\u0004y\u0004b\u0002%\u0007!\u0003\u0005\rAS\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005I&FA [W\u0005Y\u0006C\u0001/b\u001b\u0005i&B\u00010`\u0003%)hn\u00195fG.,GM\u0003\u0002aK\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\tl&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A3+\u0005)S\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001i!\tIg.D\u0001k\u0015\tYG.\u0001\u0003mC:<'\"A7\u0002\t)\fg/Y\u0005\u0003\u000b*\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u001d\t\u0003IIL!a]\u0013\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005YL\bC\u0001\u0013x\u0013\tAXEA\u0002B]fDqA_\u0006\u0002\u0002\u0003\u0007\u0011/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002{B!a0a\u0001w\u001b\u0005y(bAA\u0001K\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0007\u0005\u0015qP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0006\u0003#\u00012\u0001JA\u0007\u0013\r\ty!\n\u0002\b\u0005>|G.Z1o\u0011\u001dQX\"!AA\u0002Y\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u0001.a\u0006\t\u000fit\u0011\u0011!a\u0001c\u0006A\u0001.Y:i\u0007>$W\rF\u0001r\u0003!!xn\u0015;sS:<G#\u00015\u0002\r\u0015\fX/\u00197t)\u0011\tY!!\n\t\u000fi\f\u0012\u0011!a\u0001m\u0006aQ\t_3dkR|'\u000fT8tiB\u0011!fE\n\u0006'\u00055\u0012\u0011\b\t\b\u0003_\t)d\u0010&Q\u001b\t\t\tDC\u0002\u00024\u0015\nqA];oi&lW-\u0003\u0003\u00028\u0005E\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u00111HA!\u001b\t\tiDC\u0002\u0002@1\f!![8\n\u0007m\ni\u0004\u0006\u0002\u0002*\u0005)\u0011\r\u001d9msR)\u0001+!\u0013\u0002L!)QH\u0006a\u0001\u007f!)\u0001J\u0006a\u0001\u0015\u00069QO\\1qa2LH\u0003BA)\u0003;\u0002R\u0001JA*\u0003/J1!!\u0016&\u0005\u0019y\u0005\u000f^5p]B)A%!\u0017@\u0015&\u0019\u00111L\u0013\u0003\rQ+\b\u000f\\33\u0011!\tyfFA\u0001\u0002\u0004\u0001\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\r\t\u0004S\u0006\u001d\u0014bAA5U\n1qJ\u00196fGR\u0004"
)
public class ExecutorLost implements DAGSchedulerEvent, Product, Serializable {
   private final String execId;
   private final ExecutorLossReason reason;

   public static Option unapply(final ExecutorLost x$0) {
      return ExecutorLost$.MODULE$.unapply(x$0);
   }

   public static ExecutorLost apply(final String execId, final ExecutorLossReason reason) {
      return ExecutorLost$.MODULE$.apply(execId, reason);
   }

   public static Function1 tupled() {
      return ExecutorLost$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExecutorLost$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String execId() {
      return this.execId;
   }

   public ExecutorLossReason reason() {
      return this.reason;
   }

   public ExecutorLost copy(final String execId, final ExecutorLossReason reason) {
      return new ExecutorLost(execId, reason);
   }

   public String copy$default$1() {
      return this.execId();
   }

   public ExecutorLossReason copy$default$2() {
      return this.reason();
   }

   public String productPrefix() {
      return "ExecutorLost";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.execId();
         }
         case 1 -> {
            return this.reason();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ExecutorLost;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "execId";
         }
         case 1 -> {
            return "reason";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ExecutorLost) {
               label48: {
                  ExecutorLost var4 = (ExecutorLost)x$1;
                  String var10000 = this.execId();
                  String var5 = var4.execId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  ExecutorLossReason var7 = this.reason();
                  ExecutorLossReason var6 = var4.reason();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public ExecutorLost(final String execId, final ExecutorLossReason reason) {
      this.execId = execId;
      this.reason = reason;
      Product.$init$(this);
   }
}
