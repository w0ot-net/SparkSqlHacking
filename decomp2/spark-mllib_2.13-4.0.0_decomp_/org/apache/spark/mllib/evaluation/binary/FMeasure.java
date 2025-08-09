package org.apache.spark.mllib.evaluation.binary;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc!B\r\u001b\u0001r1\u0003\u0002C!\u0001\u0005+\u0007I\u0011\u0001\"\t\u0011\u0019\u0003!\u0011#Q\u0001\n\rCQa\u0012\u0001\u0005\u0002!Cqa\u0013\u0001C\u0002\u0013%!\t\u0003\u0004M\u0001\u0001\u0006Ia\u0011\u0005\u0006\u001b\u0002!\tE\u0014\u0005\b)\u0002\t\t\u0011\"\u0001V\u0011\u001d9\u0006!%A\u0005\u0002aCqa\u0019\u0001\u0002\u0002\u0013\u0005C\rC\u0004n\u0001\u0005\u0005I\u0011\u00018\t\u000fI\u0004\u0011\u0011!C\u0001g\"9\u0011\u0010AA\u0001\n\u0003R\b\"CA\u0002\u0001\u0005\u0005I\u0011AA\u0003\u0011%\ty\u0001AA\u0001\n\u0003\n\t\u0002C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131\u0004\u0005\n\u0003;\u0001\u0011\u0011!C!\u0003?9!\"a\t\u001b\u0003\u0003E\t\u0001HA\u0013\r%I\"$!A\t\u0002q\t9\u0003\u0003\u0004H'\u0011\u0005\u0011q\b\u0005\n\u00033\u0019\u0012\u0011!C#\u00037A\u0001\"T\n\u0002\u0002\u0013\u0005\u0015\u0011\t\u0005\n\u0003\u000b\u001a\u0012\u0011!CA\u0003\u000fB\u0011\"a\u0015\u0014\u0003\u0003%I!!\u0016\u0003\u0011\u0019kU-Y:ve\u0016T!a\u0007\u000f\u0002\r\tLg.\u0019:z\u0015\tib$\u0001\u0006fm\u0006dW/\u0019;j_:T!a\b\u0011\u0002\u000b5dG.\u001b2\u000b\u0005\u0005\u0012\u0013!B:qCJ\\'BA\u0012%\u0003\u0019\t\u0007/Y2iK*\tQ%A\u0002pe\u001e\u001cR\u0001A\u0014.cQ\u0002\"\u0001K\u0016\u000e\u0003%R\u0011AK\u0001\u0006g\u000e\fG.Y\u0005\u0003Y%\u0012a!\u00118z%\u00164\u0007C\u0001\u00180\u001b\u0005Q\u0012B\u0001\u0019\u001b\u0005\t\u0012\u0015N\\1ss\u000ec\u0017m]:jM&\u001c\u0017\r^5p]6+GO]5d\u0007>l\u0007/\u001e;feB\u0011\u0001FM\u0005\u0003g%\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00026}9\u0011a\u0007\u0010\b\u0003omj\u0011\u0001\u000f\u0006\u0003si\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002U%\u0011Q(K\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0004I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002>S\u0005!!-\u001a;b+\u0005\u0019\u0005C\u0001\u0015E\u0013\t)\u0015F\u0001\u0004E_V\u0014G.Z\u0001\u0006E\u0016$\u0018\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005%S\u0005C\u0001\u0018\u0001\u0011\u0015\t5\u00011\u0001D\u0003\u0015\u0011W\r^13\u0003\u0019\u0011W\r^13A\u0005)\u0011\r\u001d9msR\u00111i\u0014\u0005\u0006!\u001a\u0001\r!U\u0001\u0002GB\u0011aFU\u0005\u0003'j\u0011QCQ5oCJL8i\u001c8gkNLwN\\'biJL\u00070\u0001\u0003d_BLHCA%W\u0011\u001d\tu\u0001%AA\u0002\r\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001ZU\t\u0019%lK\u0001\\!\ta\u0016-D\u0001^\u0015\tqv,A\u0005v]\u000eDWmY6fI*\u0011\u0001-K\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00012^\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0015\u0004\"AZ6\u000e\u0003\u001dT!\u0001[5\u0002\t1\fgn\u001a\u0006\u0002U\u0006!!.\u0019<b\u0013\tawM\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002_B\u0011\u0001\u0006]\u0005\u0003c&\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001^<\u0011\u0005!*\u0018B\u0001<*\u0005\r\te.\u001f\u0005\bq.\t\t\u00111\u0001p\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t1\u0010E\u0002}\u007fRl\u0011! \u0006\u0003}&\n!bY8mY\u0016\u001cG/[8o\u0013\r\t\t! \u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\b\u00055\u0001c\u0001\u0015\u0002\n%\u0019\u00111B\u0015\u0003\u000f\t{w\u000e\\3b]\"9\u00010DA\u0001\u0002\u0004!\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2!ZA\n\u0011\u001dAh\"!AA\u0002=\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002_\u0006AAo\\*ue&tw\rF\u0001f\u0003\u0019)\u0017/^1mgR!\u0011qAA\u0011\u0011\u001dA\u0018#!AA\u0002Q\f\u0001BR'fCN,(/\u001a\t\u0003]M\u0019RaEA\u0015\u0003k\u0001b!a\u000b\u00022\rKUBAA\u0017\u0015\r\ty#K\u0001\beVtG/[7f\u0013\u0011\t\u0019$!\f\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u00028\u0005uRBAA\u001d\u0015\r\tY$[\u0001\u0003S>L1aPA\u001d)\t\t)\u0003F\u0002J\u0003\u0007BQ!\u0011\fA\u0002\r\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002J\u0005=\u0003\u0003\u0002\u0015\u0002L\rK1!!\u0014*\u0005\u0019y\u0005\u000f^5p]\"A\u0011\u0011K\f\u0002\u0002\u0003\u0007\u0011*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0016\u0011\u0007\u0019\fI&C\u0002\u0002\\\u001d\u0014aa\u00142kK\u000e$\b"
)
public class FMeasure implements BinaryClassificationMetricComputer, Product {
   private final double beta;
   private final double beta2;

   public static Option unapply(final FMeasure x$0) {
      return FMeasure$.MODULE$.unapply(x$0);
   }

   public static Function1 andThen(final Function1 g) {
      return FMeasure$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return FMeasure$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double beta() {
      return this.beta;
   }

   private double beta2() {
      return this.beta2;
   }

   public double apply(final BinaryConfusionMatrix c) {
      double precision = Precision$.MODULE$.apply(c);
      double recall = Recall$.MODULE$.apply(c);
      return precision + recall == (double)0 ? (double)0.0F : ((double)1.0F + this.beta2()) * precision * recall / (this.beta2() * precision + recall);
   }

   public FMeasure copy(final double beta) {
      return new FMeasure(beta);
   }

   public double copy$default$1() {
      return this.beta();
   }

   public String productPrefix() {
      return "FMeasure";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToDouble(this.beta());
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
      return x$1 instanceof FMeasure;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "beta";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.beta()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof FMeasure) {
               FMeasure var4 = (FMeasure)x$1;
               if (this.beta() == var4.beta() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public FMeasure(final double beta) {
      this.beta = beta;
      Product.$init$(this);
      this.beta2 = beta * beta;
   }
}
