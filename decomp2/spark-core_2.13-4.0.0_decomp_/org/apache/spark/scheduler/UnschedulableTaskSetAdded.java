package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=c!B\r\u001b\u0001j\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011\t\u0003!\u0011#Q\u0001\n}B\u0001b\u0011\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\t\u0002\u0011\t\u0012)A\u0005\u007f!)Q\t\u0001C\u0001\r\"9!\nAA\u0001\n\u0003Y\u0005b\u0002(\u0001#\u0003%\ta\u0014\u0005\b5\u0002\t\n\u0011\"\u0001P\u0011\u001dY\u0006!!A\u0005BqCq!\u001a\u0001\u0002\u0002\u0013\u0005a\bC\u0004g\u0001\u0005\u0005I\u0011A4\t\u000f5\u0004\u0011\u0011!C!]\"9Q\u000fAA\u0001\n\u00031\bbB>\u0001\u0003\u0003%\t\u0005 \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019\u0001C\u0005\u0002\u0006\u0001\t\t\u0011\"\u0011\u0002\b\u001dQ\u00111\u0002\u000e\u0002\u0002#\u0005!$!\u0004\u0007\u0013eQ\u0012\u0011!E\u00015\u0005=\u0001BB#\u0014\t\u0003\t9\u0003C\u0005\u0002\u0002M\t\t\u0011\"\u0012\u0002\u0004!I\u0011\u0011F\n\u0002\u0002\u0013\u0005\u00151\u0006\u0005\n\u0003c\u0019\u0012\u0011!CA\u0003gA\u0011\"!\u0012\u0014\u0003\u0003%I!a\u0012\u00033Us7o\u00195fIVd\u0017M\u00197f)\u0006\u001c8nU3u\u0003\u0012$W\r\u001a\u0006\u00037q\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001cR\u0001A\u0012*[A\u0002\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a!\u00118z%\u00164\u0007C\u0001\u0016,\u001b\u0005Q\u0012B\u0001\u0017\u001b\u0005E!\u0015iR*dQ\u0016$W\u000f\\3s\u000bZ,g\u000e\u001e\t\u0003I9J!aL\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011G\u000f\b\u0003ear!aM\u001c\u000e\u0003QR!!\u000e\u001c\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AJ\u0005\u0003s\u0015\nq\u0001]1dW\u0006<W-\u0003\u0002<y\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011(J\u0001\bgR\fw-Z%e+\u0005y\u0004C\u0001\u0013A\u0013\t\tUEA\u0002J]R\f\u0001b\u001d;bO\u0016LE\rI\u0001\u000fgR\fw-Z!ui\u0016l\u0007\u000f^%e\u0003=\u0019H/Y4f\u0003R$X-\u001c9u\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0002H\u0011&\u0003\"A\u000b\u0001\t\u000bu*\u0001\u0019A \t\u000b\r+\u0001\u0019A \u0002\t\r|\u0007/\u001f\u000b\u0004\u000f2k\u0005bB\u001f\u0007!\u0003\u0005\ra\u0010\u0005\b\u0007\u001a\u0001\n\u00111\u0001@\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u0015\u0016\u0003\u007fE[\u0013A\u0015\t\u0003'bk\u0011\u0001\u0016\u0006\u0003+Z\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005]+\u0013AC1o]>$\u0018\r^5p]&\u0011\u0011\f\u0016\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003u\u0003\"AX2\u000e\u0003}S!\u0001Y1\u0002\t1\fgn\u001a\u0006\u0002E\u0006!!.\u0019<b\u0013\t!wL\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\tA7\u000e\u0005\u0002%S&\u0011!.\n\u0002\u0004\u0003:L\bb\u00027\f\u0003\u0003\u0005\raP\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003=\u00042\u0001]:i\u001b\u0005\t(B\u0001:&\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003iF\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0011qO\u001f\t\u0003IaL!!_\u0013\u0003\u000f\t{w\u000e\\3b]\"9A.DA\u0001\u0002\u0004A\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$\"!X?\t\u000f1t\u0011\u0011!a\u0001\u007f\u0005A\u0001.Y:i\u0007>$W\rF\u0001@\u0003!!xn\u0015;sS:<G#A/\u0002\r\u0015\fX/\u00197t)\r9\u0018\u0011\u0002\u0005\bYF\t\t\u00111\u0001i\u0003e)fn]2iK\u0012,H.\u00192mKR\u000b7o[*fi\u0006#G-\u001a3\u0011\u0005)\u001a2#B\n\u0002\u0012\u0005u\u0001cBA\n\u00033ythR\u0007\u0003\u0003+Q1!a\u0006&\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0007\u0002\u0016\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005}\u0011QE\u0007\u0003\u0003CQ1!a\tb\u0003\tIw.C\u0002<\u0003C!\"!!\u0004\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b\u001d\u000bi#a\f\t\u000bu2\u0002\u0019A \t\u000b\r3\u0002\u0019A \u0002\u000fUt\u0017\r\u001d9msR!\u0011QGA!!\u0015!\u0013qGA\u001e\u0013\r\tI$\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u0011\nidP \n\u0007\u0005}RE\u0001\u0004UkBdWM\r\u0005\t\u0003\u0007:\u0012\u0011!a\u0001\u000f\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005%\u0003c\u00010\u0002L%\u0019\u0011QJ0\u0003\r=\u0013'.Z2u\u0001"
)
public class UnschedulableTaskSetAdded implements DAGSchedulerEvent, Product, Serializable {
   private final int stageId;
   private final int stageAttemptId;

   public static Option unapply(final UnschedulableTaskSetAdded x$0) {
      return UnschedulableTaskSetAdded$.MODULE$.unapply(x$0);
   }

   public static UnschedulableTaskSetAdded apply(final int stageId, final int stageAttemptId) {
      return UnschedulableTaskSetAdded$.MODULE$.apply(stageId, stageAttemptId);
   }

   public static Function1 tupled() {
      return UnschedulableTaskSetAdded$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return UnschedulableTaskSetAdded$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public UnschedulableTaskSetAdded copy(final int stageId, final int stageAttemptId) {
      return new UnschedulableTaskSetAdded(stageId, stageAttemptId);
   }

   public int copy$default$1() {
      return this.stageId();
   }

   public int copy$default$2() {
      return this.stageAttemptId();
   }

   public String productPrefix() {
      return "UnschedulableTaskSetAdded";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.stageId());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.stageAttemptId());
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
      return x$1 instanceof UnschedulableTaskSetAdded;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stageId";
         }
         case 1 -> {
            return "stageAttemptId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.stageId());
      var1 = Statics.mix(var1, this.stageAttemptId());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof UnschedulableTaskSetAdded) {
               UnschedulableTaskSetAdded var4 = (UnschedulableTaskSetAdded)x$1;
               if (this.stageId() == var4.stageId() && this.stageAttemptId() == var4.stageAttemptId() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public UnschedulableTaskSetAdded(final int stageId, final int stageAttemptId) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      Product.$init$(this);
   }
}
