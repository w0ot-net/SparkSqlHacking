package org.apache.spark.sql.hive;

import java.io.Serializable;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ee\u0001B\r\u001b\u0001\u0016B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t7\u0002\u0011\t\u0012)A\u0005{!AA\f\u0001BK\u0002\u0013\u0005Q\f\u0003\u0005b\u0001\tE\t\u0015!\u0003_\u0011\u0015\u0011\u0007\u0001\"\u0001d\u0011\u001dA\u0007!!A\u0005\u0002%Dq\u0001\u001c\u0001\u0012\u0002\u0013\u0005Q\u000eC\u0004y\u0001E\u0005I\u0011A=\t\u000fm\u0004\u0011\u0011!C!y\"I\u00111\u0002\u0001\u0002\u0002\u0013\u0005\u0011Q\u0002\u0005\n\u0003+\u0001\u0011\u0011!C\u0001\u0003/A\u0011\"a\t\u0001\u0003\u0003%\t%!\n\t\u0013\u0005M\u0002!!A\u0005\u0002\u0005U\u0002\"CA\u001d\u0001\u0005\u0005I\u0011IA\u001e\u0011%\ty\u0004AA\u0001\n\u0003\n\t\u0005C\u0005\u0002D\u0001\t\t\u0011\"\u0011\u0002F!I\u0011q\t\u0001\u0002\u0002\u0013\u0005\u0013\u0011J\u0004\n\u0003\u001bR\u0012\u0011!E\u0001\u0003\u001f2\u0001\"\u0007\u000e\u0002\u0002#\u0005\u0011\u0011\u000b\u0005\u0007EN!\t!!\u001b\t\u0013\u0005\r3#!A\u0005F\u0005\u0015\u0003\"CA6'\u0005\u0005I\u0011QA7\u0011%\t\u0019hEA\u0001\n\u0003\u000b)\bC\u0005\u0002\bN\t\t\u0011\"\u0003\u0002\n\nq\u0001*\u001b<f+\u0012\u000beIQ;gM\u0016\u0014(BA\u000e\u001d\u0003\u0011A\u0017N^3\u000b\u0005uq\u0012aA:rY*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u00011Cf\f\t\u0003O)j\u0011\u0001\u000b\u0006\u0002S\u0005)1oY1mC&\u00111\u0006\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u001dj\u0013B\u0001\u0018)\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\r\u001d\u000f\u0005E2dB\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b%\u0003\u0019a$o\\8u}%\t\u0011&\u0003\u00028Q\u00059\u0001/Y2lC\u001e,\u0017BA\u001d;\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t9\u0004&A\u0002ck\u001a,\u0012!\u0010\t\u0003}as!aP+\u000f\u0005\u0001\u0013fBA!P\u001d\t\u0011EJ\u0004\u0002D\u0015:\u0011A\t\u0013\b\u0003\u000b\u001es!A\r$\n\u0003\rJ!!\t\u0012\n\u0005%\u0003\u0013A\u00025bI>|\u0007/\u0003\u0002\u001c\u0017*\u0011\u0011\nI\u0005\u0003\u001b:\u000b!!\u001d7\u000b\u0005mY\u0015B\u0001)R\u0003\r)HM\u001a\u0006\u0003\u001b:K!a\u0015+\u0002\u000f\u001d,g.\u001a:jG*\u0011\u0001+U\u0005\u0003-^\u000bAcR3oKJL7-\u0016#B\r\u00163\u0018\r\\;bi>\u0014(BA*U\u0013\tI&LA\tBO\u001e\u0014XmZ1uS>t')\u001e4gKJT!AV,\u0002\t\t,h\rI\u0001\u000bG\u0006tGi\\'fe\u001e,W#\u00010\u0011\u0005\u001dz\u0016B\u00011)\u0005\u001d\u0011un\u001c7fC:\f1bY1o\t>lUM]4fA\u00051A(\u001b8jiz\"2\u0001\u001a4h!\t)\u0007!D\u0001\u001b\u0011\u0015YT\u00011\u0001>\u0011\u0015aV\u00011\u0001_\u0003\u0011\u0019w\u000e]=\u0015\u0007\u0011T7\u000eC\u0004<\rA\u0005\t\u0019A\u001f\t\u000fq3\u0001\u0013!a\u0001=\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00018+\u0005uz7&\u00019\u0011\u0005E4X\"\u0001:\u000b\u0005M$\u0018!C;oG\",7m[3e\u0015\t)\b&\u0001\u0006b]:|G/\u0019;j_:L!a\u001e:\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003iT#AX8\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005i\bc\u0001@\u0002\b5\tqP\u0003\u0003\u0002\u0002\u0005\r\u0011\u0001\u00027b]\u001eT!!!\u0002\u0002\t)\fg/Y\u0005\u0004\u0003\u0013y(AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0010A\u0019q%!\u0005\n\u0007\u0005M\u0001FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u001a\u0005}\u0001cA\u0014\u0002\u001c%\u0019\u0011Q\u0004\u0015\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002\"-\t\t\u00111\u0001\u0002\u0010\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\n\u0011\r\u0005%\u0012qFA\r\u001b\t\tYCC\u0002\u0002.!\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\t$a\u000b\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004=\u0006]\u0002\"CA\u0011\u001b\u0005\u0005\t\u0019AA\r\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007u\fi\u0004C\u0005\u0002\"9\t\t\u00111\u0001\u0002\u0010\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0010\u0005AAo\\*ue&tw\rF\u0001~\u0003\u0019)\u0017/^1mgR\u0019a,a\u0013\t\u0013\u0005\u0005\u0012#!AA\u0002\u0005e\u0011A\u0004%jm\u0016,F)\u0011$Ck\u001a4WM\u001d\t\u0003KN\u0019RaEA*\u0003?\u0002r!!\u0016\u0002\\urF-\u0004\u0002\u0002X)\u0019\u0011\u0011\f\u0015\u0002\u000fI,h\u000e^5nK&!\u0011QLA,\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003C\n9'\u0004\u0002\u0002d)!\u0011QMA\u0002\u0003\tIw.C\u0002:\u0003G\"\"!a\u0014\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b\u0011\fy'!\u001d\t\u000bm2\u0002\u0019A\u001f\t\u000bq3\u0002\u0019\u00010\u0002\u000fUt\u0017\r\u001d9msR!\u0011qOAB!\u00159\u0013\u0011PA?\u0013\r\tY\b\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u001d\ny(\u00100\n\u0007\u0005\u0005\u0005F\u0001\u0004UkBdWM\r\u0005\t\u0003\u000b;\u0012\u0011!a\u0001I\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005-\u0005c\u0001@\u0002\u000e&\u0019\u0011qR@\u0003\r=\u0013'.Z2u\u0001"
)
public class HiveUDAFBuffer implements Product, Serializable {
   private final GenericUDAFEvaluator.AggregationBuffer buf;
   private final boolean canDoMerge;

   public static Option unapply(final HiveUDAFBuffer x$0) {
      return HiveUDAFBuffer$.MODULE$.unapply(x$0);
   }

   public static HiveUDAFBuffer apply(final GenericUDAFEvaluator.AggregationBuffer buf, final boolean canDoMerge) {
      return HiveUDAFBuffer$.MODULE$.apply(buf, canDoMerge);
   }

   public static Function1 tupled() {
      return HiveUDAFBuffer$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return HiveUDAFBuffer$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public GenericUDAFEvaluator.AggregationBuffer buf() {
      return this.buf;
   }

   public boolean canDoMerge() {
      return this.canDoMerge;
   }

   public HiveUDAFBuffer copy(final GenericUDAFEvaluator.AggregationBuffer buf, final boolean canDoMerge) {
      return new HiveUDAFBuffer(buf, canDoMerge);
   }

   public GenericUDAFEvaluator.AggregationBuffer copy$default$1() {
      return this.buf();
   }

   public boolean copy$default$2() {
      return this.canDoMerge();
   }

   public String productPrefix() {
      return "HiveUDAFBuffer";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.buf();
         }
         case 1 -> {
            return BoxesRunTime.boxToBoolean(this.canDoMerge());
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
      return x$1 instanceof HiveUDAFBuffer;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "buf";
         }
         case 1 -> {
            return "canDoMerge";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.buf()));
      var1 = Statics.mix(var1, this.canDoMerge() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof HiveUDAFBuffer) {
               HiveUDAFBuffer var4 = (HiveUDAFBuffer)x$1;
               if (this.canDoMerge() == var4.canDoMerge()) {
                  label44: {
                     GenericUDAFEvaluator.AggregationBuffer var10000 = this.buf();
                     GenericUDAFEvaluator.AggregationBuffer var5 = var4.buf();
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

   public HiveUDAFBuffer(final GenericUDAFEvaluator.AggregationBuffer buf, final boolean canDoMerge) {
      this.buf = buf;
      this.canDoMerge = canDoMerge;
      Product.$init$(this);
   }
}
