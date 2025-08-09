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
   bytes = "\u0006\u0005\u0005mc\u0001\u0002\f\u0018\u0001\u0012B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0001\")q\n\u0001C\u0001!\"91\u000bAA\u0001\n\u0003!\u0006b\u0002,\u0001#\u0003%\ta\u0016\u0005\bE\u0002\t\t\u0011\"\u0011d\u0011\u001da\u0007!!A\u0005\u00025Dq!\u001d\u0001\u0002\u0002\u0013\u0005!\u000fC\u0004y\u0001\u0005\u0005I\u0011I=\t\u0013\u0005\u0005\u0001!!A\u0005\u0002\u0005\r\u0001\"CA\u0007\u0001\u0005\u0005I\u0011IA\b\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)\u0002C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0011\u0002\u001a!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013QD\u0004\n\u0003C9\u0012\u0011!E\u0001\u0003G1\u0001BF\f\u0002\u0002#\u0005\u0011Q\u0005\u0005\u0007\u001fB!\t!a\u000f\t\u0013\u0005]\u0001#!A\u0005F\u0005e\u0001\"CA\u001f!\u0005\u0005I\u0011QA \u0011%\t\u0019\u0005EA\u0001\n\u0003\u000b)\u0005C\u0005\u0002RA\t\t\u0011\"\u0003\u0002T\tQ\u0001k\u001c3V].twn\u001e8\u000b\u0005aI\u0012aA69g*\u0011!dG\u0001\bG2,8\u000f^3s\u0015\taR$A\u0005tG\",G-\u001e7fe*\u0011adH\u0001\u0006gB\f'o\u001b\u0006\u0003A\u0005\na!\u00199bG\",'\"\u0001\u0012\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001)3f\f\u001a\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\r\u0005s\u0017PU3g!\taS&D\u0001\u0018\u0013\tqsC\u0001\tFq\u0016\u001cW\u000f^8s!>$7\u000b^1uKB\u0011a\u0005M\u0005\u0003c\u001d\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00024w9\u0011A'\u000f\b\u0003kaj\u0011A\u000e\u0006\u0003o\r\na\u0001\u0010:p_Rt\u0014\"\u0001\u0015\n\u0005i:\u0013a\u00029bG.\fw-Z\u0005\u0003yu\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!AO\u0014\u0002\u0007A|G-F\u0001A!\t\tE*D\u0001C\u0015\t\u0019E)A\u0003n_\u0012,GN\u0003\u0002F\r\u0006\u0019\u0011\r]5\u000b\u0005\u001dC\u0015AC6vE\u0016\u0014h.\u001a;fg*\u0011\u0011JS\u0001\bM\u0006\u0014'/[29\u0015\u0005Y\u0015AA5p\u0013\ti%IA\u0002Q_\u0012\fA\u0001]8eA\u00051A(\u001b8jiz\"\"!\u0015*\u0011\u00051\u0002\u0001\"\u0002 \u0004\u0001\u0004\u0001\u0015\u0001B2paf$\"!U+\t\u000fy\"\u0001\u0013!a\u0001\u0001\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001-+\u0005\u0001K6&\u0001.\u0011\u0005m\u0003W\"\u0001/\u000b\u0005us\u0016!C;oG\",7m[3e\u0015\tyv%\u0001\u0006b]:|G/\u0019;j_:L!!\u0019/\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002IB\u0011QM[\u0007\u0002M*\u0011q\r[\u0001\u0005Y\u0006twMC\u0001j\u0003\u0011Q\u0017M^1\n\u0005-4'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001o!\t1s.\u0003\u0002qO\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u00111O\u001e\t\u0003MQL!!^\u0014\u0003\u0007\u0005s\u0017\u0010C\u0004x\u0011\u0005\u0005\t\u0019\u00018\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005Q\bcA>\u007fg6\tAP\u0003\u0002~O\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005}d(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0002\u0002\fA\u0019a%a\u0002\n\u0007\u0005%qEA\u0004C_>dW-\u00198\t\u000f]T\u0011\u0011!a\u0001g\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r!\u0017\u0011\u0003\u0005\bo.\t\t\u00111\u0001o\u0003!A\u0017m\u001d5D_\u0012,G#\u00018\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001Z\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u0015\u0011q\u0004\u0005\bo:\t\t\u00111\u0001t\u0003)\u0001v\u000eZ+oW:|wO\u001c\t\u0003YA\u0019R\u0001EA\u0014\u0003g\u0001b!!\u000b\u00020\u0001\u000bVBAA\u0016\u0015\r\ticJ\u0001\beVtG/[7f\u0013\u0011\t\t$a\u000b\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u00026\u0005eRBAA\u001c\u0015\tY\u0005.C\u0002=\u0003o!\"!a\t\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007E\u000b\t\u0005C\u0003?'\u0001\u0007\u0001)A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u001d\u0013Q\n\t\u0005M\u0005%\u0003)C\u0002\u0002L\u001d\u0012aa\u00149uS>t\u0007\u0002CA()\u0005\u0005\t\u0019A)\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002VA\u0019Q-a\u0016\n\u0007\u0005ecM\u0001\u0004PE*,7\r\u001e"
)
public class PodUnknown implements ExecutorPodState, Product, Serializable {
   private final Pod pod;

   public static Option unapply(final PodUnknown x$0) {
      return PodUnknown$.MODULE$.unapply(x$0);
   }

   public static PodUnknown apply(final Pod pod) {
      return PodUnknown$.MODULE$.apply(pod);
   }

   public static Function1 andThen(final Function1 g) {
      return PodUnknown$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return PodUnknown$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Pod pod() {
      return this.pod;
   }

   public PodUnknown copy(final Pod pod) {
      return new PodUnknown(pod);
   }

   public Pod copy$default$1() {
      return this.pod();
   }

   public String productPrefix() {
      return "PodUnknown";
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
      return x$1 instanceof PodUnknown;
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
            if (x$1 instanceof PodUnknown) {
               label40: {
                  PodUnknown var4 = (PodUnknown)x$1;
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

   public PodUnknown(final Pod pod) {
      this.pod = pod;
      Product.$init$(this);
   }
}
