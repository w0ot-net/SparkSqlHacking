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
   bytes = "\u0006\u0005\u0005mc\u0001\u0002\f\u0018\u0001\u0012B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0001\")q\n\u0001C\u0001!\"91\u000bAA\u0001\n\u0003!\u0006b\u0002,\u0001#\u0003%\ta\u0016\u0005\bE\u0002\t\t\u0011\"\u0011d\u0011\u001da\u0007!!A\u0005\u00025Dq!\u001d\u0001\u0002\u0002\u0013\u0005!\u000fC\u0004y\u0001\u0005\u0005I\u0011I=\t\u0013\u0005\u0005\u0001!!A\u0005\u0002\u0005\r\u0001\"CA\u0007\u0001\u0005\u0005I\u0011IA\b\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)\u0002C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0011\u0002\u001a!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013QD\u0004\n\u0003C9\u0012\u0011!E\u0001\u0003G1\u0001BF\f\u0002\u0002#\u0005\u0011Q\u0005\u0005\u0007\u001fB!\t!a\u000f\t\u0013\u0005]\u0001#!A\u0005F\u0005e\u0001\"CA\u001f!\u0005\u0005I\u0011QA \u0011%\t\u0019\u0005EA\u0001\n\u0003\u000b)\u0005C\u0005\u0002RA\t\t\u0011\"\u0003\u0002T\tq\u0001k\u001c3UKJl\u0017N\\1uS:<'B\u0001\r\u001a\u0003\rY\u0007h\u001d\u0006\u00035m\tqa\u00197vgR,'O\u0003\u0002\u001d;\u0005I1o\u00195fIVdWM\u001d\u0006\u0003=}\tQa\u001d9be.T!\u0001I\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0013aA8sO\u000e\u00011#\u0002\u0001&W=\u0012\u0004C\u0001\u0014*\u001b\u00059#\"\u0001\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005):#AB!osJ+g\r\u0005\u0002-[5\tq#\u0003\u0002//\tia)\u001b8bYB{Gm\u0015;bi\u0016\u0004\"A\n\u0019\n\u0005E:#a\u0002)s_\u0012,8\r\u001e\t\u0003gmr!\u0001N\u001d\u000f\u0005UBT\"\u0001\u001c\u000b\u0005]\u001a\u0013A\u0002\u001fs_>$h(C\u0001)\u0013\tQt%A\u0004qC\u000e\\\u0017mZ3\n\u0005qj$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001e(\u0003\r\u0001x\u000eZ\u000b\u0002\u0001B\u0011\u0011\tT\u0007\u0002\u0005*\u00111\tR\u0001\u0006[>$W\r\u001c\u0006\u0003\u000b\u001a\u000b1!\u00199j\u0015\t9\u0005*\u0001\u0006lk\n,'O\\3uKNT!!\u0013&\u0002\u000f\u0019\f'M]5dq)\t1*\u0001\u0002j_&\u0011QJ\u0011\u0002\u0004!>$\u0017\u0001\u00029pI\u0002\na\u0001P5oSRtDCA)S!\ta\u0003\u0001C\u0003?\u0007\u0001\u0007\u0001)\u0001\u0003d_BLHCA)V\u0011\u001dqD\u0001%AA\u0002\u0001\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001YU\t\u0001\u0015lK\u0001[!\tY\u0006-D\u0001]\u0015\tif,A\u0005v]\u000eDWmY6fI*\u0011qlJ\u0001\u000bC:tw\u000e^1uS>t\u0017BA1]\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0011\u0004\"!\u001a6\u000e\u0003\u0019T!a\u001a5\u0002\t1\fgn\u001a\u0006\u0002S\u0006!!.\u0019<b\u0013\tYgM\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002]B\u0011ae\\\u0005\u0003a\u001e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u001d<\u0011\u0005\u0019\"\u0018BA;(\u0005\r\te.\u001f\u0005\bo\"\t\t\u00111\u0001o\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t!\u0010E\u0002|}Nl\u0011\u0001 \u0006\u0003{\u001e\n!bY8mY\u0016\u001cG/[8o\u0013\tyHP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0003\u0003\u0017\u00012AJA\u0004\u0013\r\tIa\n\u0002\b\u0005>|G.Z1o\u0011\u001d9(\"!AA\u0002M\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019A-!\u0005\t\u000f]\\\u0011\u0011!a\u0001]\u0006A\u0001.Y:i\u0007>$W\rF\u0001o\u0003!!xn\u0015;sS:<G#\u00013\u0002\r\u0015\fX/\u00197t)\u0011\t)!a\b\t\u000f]t\u0011\u0011!a\u0001g\u0006q\u0001k\u001c3UKJl\u0017N\\1uS:<\u0007C\u0001\u0017\u0011'\u0015\u0001\u0012qEA\u001a!\u0019\tI#a\fA#6\u0011\u00111\u0006\u0006\u0004\u0003[9\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003c\tYCA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!\u000e\u0002:5\u0011\u0011q\u0007\u0006\u0003\u0017\"L1\u0001PA\u001c)\t\t\u0019#A\u0003baBd\u0017\u0010F\u0002R\u0003\u0003BQAP\nA\u0002\u0001\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002H\u00055\u0003\u0003\u0002\u0014\u0002J\u0001K1!a\u0013(\u0005\u0019y\u0005\u000f^5p]\"A\u0011q\n\u000b\u0002\u0002\u0003\u0007\u0011+A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0016\u0011\u0007\u0015\f9&C\u0002\u0002Z\u0019\u0014aa\u00142kK\u000e$\b"
)
public class PodTerminating implements FinalPodState, Product, Serializable {
   private final Pod pod;

   public static Option unapply(final PodTerminating x$0) {
      return PodTerminating$.MODULE$.unapply(x$0);
   }

   public static PodTerminating apply(final Pod pod) {
      return PodTerminating$.MODULE$.apply(pod);
   }

   public static Function1 andThen(final Function1 g) {
      return PodTerminating$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return PodTerminating$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Pod pod() {
      return this.pod;
   }

   public PodTerminating copy(final Pod pod) {
      return new PodTerminating(pod);
   }

   public Pod copy$default$1() {
      return this.pod();
   }

   public String productPrefix() {
      return "PodTerminating";
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
      return x$1 instanceof PodTerminating;
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
            if (x$1 instanceof PodTerminating) {
               label40: {
                  PodTerminating var4 = (PodTerminating)x$1;
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

   public PodTerminating(final Pod pod) {
      this.pod = pod;
      Product.$init$(this);
   }
}
