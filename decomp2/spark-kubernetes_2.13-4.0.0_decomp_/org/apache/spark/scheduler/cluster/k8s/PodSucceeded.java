package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc\u0001\u0002\f\u0018\u0001\u0012B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0001\")q\n\u0001C\u0001!\"91\u000bAA\u0001\n\u0003!\u0006b\u0002,\u0001#\u0003%\ta\u0016\u0005\bE\u0002\t\t\u0011\"\u0011d\u0011\u001da\u0007!!A\u0005\u00025Dq!\u001d\u0001\u0002\u0002\u0013\u0005!\u000fC\u0004y\u0001\u0005\u0005I\u0011I=\t\u0013\u0005\u0005\u0001!!A\u0005\u0002\u0005\r\u0001\"CA\u0007\u0001\u0005\u0005I\u0011IA\b\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)\u0002C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0011\u0002\u001a!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013QD\u0004\n\u0003C9\u0012\u0011!E\u0001\u0003G1\u0001BF\f\u0002\u0002#\u0005\u0011Q\u0005\u0005\u0007\u001fB!\t!a\u000f\t\u0013\u0005]\u0001#!A\u0005F\u0005e\u0001\"CA\u001f!\u0005\u0005I\u0011QA \u0011%\t\u0019\u0005EA\u0001\n\u0003\u000b)\u0005C\u0005\u0002RA\t\t\u0011\"\u0003\u0002T\ta\u0001k\u001c3Tk\u000e\u001cW-\u001a3fI*\u0011\u0001$G\u0001\u0004Wb\u001a(B\u0001\u000e\u001c\u0003\u001d\u0019G.^:uKJT!\u0001H\u000f\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\u0010 \u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0013%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002E\u0005\u0019qN]4\u0004\u0001M)\u0001!J\u00160eA\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t1\u0011I\\=SK\u001a\u0004\"\u0001L\u0017\u000e\u0003]I!AL\f\u0003\u001b\u0019Kg.\u00197Q_\u0012\u001cF/\u0019;f!\t1\u0003'\u0003\u00022O\t9\u0001K]8ek\u000e$\bCA\u001a<\u001d\t!\u0014H\u0004\u00026q5\taG\u0003\u00028G\u00051AH]8pizJ\u0011\u0001K\u0005\u0003u\u001d\nq\u0001]1dW\u0006<W-\u0003\u0002={\ta1+\u001a:jC2L'0\u00192mK*\u0011!hJ\u0001\u0004a>$W#\u0001!\u0011\u0005\u0005cU\"\u0001\"\u000b\u0005\r#\u0015!B7pI\u0016d'BA#G\u0003\r\t\u0007/\u001b\u0006\u0003\u000f\"\u000b!b[;cKJtW\r^3t\u0015\tI%*A\u0004gC\n\u0014\u0018n\u0019\u001d\u000b\u0003-\u000b!![8\n\u00055\u0013%a\u0001)pI\u0006!\u0001o\u001c3!\u0003\u0019a\u0014N\\5u}Q\u0011\u0011K\u0015\t\u0003Y\u0001AQAP\u0002A\u0002\u0001\u000bAaY8qsR\u0011\u0011+\u0016\u0005\b}\u0011\u0001\n\u00111\u0001A\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u0017\u0016\u0003\u0001f[\u0013A\u0017\t\u00037\u0002l\u0011\u0001\u0018\u0006\u0003;z\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005};\u0013AC1o]>$\u0018\r^5p]&\u0011\u0011\r\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001e!\t)'.D\u0001g\u0015\t9\u0007.\u0001\u0003mC:<'\"A5\u0002\t)\fg/Y\u0005\u0003W\u001a\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u00018\u0011\u0005\u0019z\u0017B\u00019(\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t\u0019h\u000f\u0005\u0002'i&\u0011Qo\n\u0002\u0004\u0003:L\bbB<\t\u0003\u0003\u0005\rA\\\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003i\u00042a\u001f@t\u001b\u0005a(BA?(\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003\u007fr\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QAA\u0006!\r1\u0013qA\u0005\u0004\u0003\u00139#a\u0002\"p_2,\u0017M\u001c\u0005\bo*\t\t\u00111\u0001t\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007\u0011\f\t\u0002C\u0004x\u0017\u0005\u0005\t\u0019\u00018\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A\\\u0001\ti>\u001cFO]5oOR\tA-\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u000b\ty\u0002C\u0004x\u001d\u0005\u0005\t\u0019A:\u0002\u0019A{GmU;dG\u0016,G-\u001a3\u0011\u00051\u00022#\u0002\t\u0002(\u0005M\u0002CBA\u0015\u0003_\u0001\u0015+\u0004\u0002\u0002,)\u0019\u0011QF\u0014\u0002\u000fI,h\u000e^5nK&!\u0011\u0011GA\u0016\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003k\tI$\u0004\u0002\u00028)\u00111\n[\u0005\u0004y\u0005]BCAA\u0012\u0003\u0015\t\u0007\u000f\u001d7z)\r\t\u0016\u0011\t\u0005\u0006}M\u0001\r\u0001Q\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9%!\u0014\u0011\t\u0019\nI\u0005Q\u0005\u0004\u0003\u0017:#AB(qi&|g\u000e\u0003\u0005\u0002PQ\t\t\u00111\u0001R\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003+\u00022!ZA,\u0013\r\tIF\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class PodSucceeded implements FinalPodState, Product, Serializable {
   private final Pod pod;

   public static Option unapply(final PodSucceeded x$0) {
      return PodSucceeded$.MODULE$.unapply(x$0);
   }

   public static PodSucceeded apply(final Pod pod) {
      return PodSucceeded$.MODULE$.apply(pod);
   }

   public static Function1 andThen(final Function1 g) {
      return PodSucceeded$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return PodSucceeded$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Pod pod() {
      return this.pod;
   }

   public PodSucceeded copy(final Pod pod) {
      return new PodSucceeded(pod);
   }

   public Pod copy$default$1() {
      return this.pod();
   }

   public String productPrefix() {
      return "PodSucceeded";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.pod();
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
      return x$1 instanceof PodSucceeded;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "pod";
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
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof PodSucceeded) {
               label40: {
                  PodSucceeded var4 = (PodSucceeded)x$1;
                  Pod var10000 = this.pod();
                  Pod var5 = var4.pod();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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

   public PodSucceeded(final Pod pod) {
      this.pod = pod;
      Product.$init$(this);
   }
}
