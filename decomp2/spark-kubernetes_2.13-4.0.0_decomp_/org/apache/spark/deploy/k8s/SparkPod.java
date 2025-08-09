package org.apache.spark.deploy.k8s;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.Pod;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Unstable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ee\u0001\u0002\u000e\u001c\u0001\u001aB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005}!AQ\n\u0001BK\u0002\u0013\u0005a\n\u0003\u0005S\u0001\tE\t\u0015!\u0003P\u0011\u0015\u0019\u0006\u0001\"\u0001U\u0011\u0015I\u0006\u0001\"\u0001[\u0011\u001d\u0001\u0007!!A\u0005\u0002\u0005Dq\u0001\u001a\u0001\u0012\u0002\u0013\u0005Q\rC\u0004q\u0001E\u0005I\u0011A9\t\u000fM\u0004\u0011\u0011!C!i\"9Q\u0010AA\u0001\n\u0003q\b\"CA\u0003\u0001\u0005\u0005I\u0011AA\u0004\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)\u0002C\u0005\u0002$\u0001\t\t\u0011\"\u0001\u0002&!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003k\u0001\u0011\u0011!C!\u0003oA\u0011\"!\u000f\u0001\u0003\u0003%\t%a\u000f\t\u0013\u0005u\u0002!!A\u0005B\u0005}r\u0001CA,7!\u0005q$!\u0017\u0007\u000fiY\u0002\u0012A\u0010\u0002\\!11\u000b\u0006C\u0001\u0003KBq!a\u001a\u0015\t\u0003\tI\u0007C\u0005\u0002lQ\t\t\u0011\"!\u0002n!I\u00111\u000f\u000b\u0002\u0002\u0013\u0005\u0015Q\u000f\u0005\n\u0003\u000f#\u0012\u0011!C\u0005\u0003\u0013\u0013\u0001b\u00159be.\u0004v\u000e\u001a\u0006\u00039u\t1a\u001b\u001dt\u0015\tqr$\u0001\u0004eKBdw.\u001f\u0006\u0003A\u0005\nQa\u001d9be.T!AI\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0013aA8sO\u000e\u00011\u0003\u0002\u0001([A\u0002\"\u0001K\u0016\u000e\u0003%R\u0011AK\u0001\u0006g\u000e\fG.Y\u0005\u0003Y%\u0012a!\u00118z%\u00164\u0007C\u0001\u0015/\u0013\ty\u0013FA\u0004Qe>$Wo\u0019;\u0011\u0005EJdB\u0001\u001a8\u001d\t\u0019d'D\u00015\u0015\t)T%\u0001\u0004=e>|GOP\u0005\u0002U%\u0011\u0001(K\u0001\ba\u0006\u001c7.Y4f\u0013\tQ4H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00029S\u0005\u0019\u0001o\u001c3\u0016\u0003y\u0002\"a\u0010&\u000e\u0003\u0001S!!\u0011\"\u0002\u000b5|G-\u001a7\u000b\u0005\r#\u0015aA1qS*\u0011QIR\u0001\u000bWV\u0014WM\u001d8fi\u0016\u001c(BA$I\u0003\u001d1\u0017M\u0019:jGbR\u0011!S\u0001\u0003S>L!a\u0013!\u0003\u0007A{G-\u0001\u0003q_\u0012\u0004\u0013!C2p]R\f\u0017N\\3s+\u0005y\u0005CA Q\u0013\t\t\u0006IA\u0005D_:$\u0018-\u001b8fe\u0006Q1m\u001c8uC&tWM\u001d\u0011\u0002\rqJg.\u001b;?)\r)v\u000b\u0017\t\u0003-\u0002i\u0011a\u0007\u0005\u0006y\u0015\u0001\rA\u0010\u0005\u0006\u001b\u0016\u0001\raT\u0001\niJ\fgn\u001d4pe6$\"!V.\t\u000bq3\u0001\u0019A/\u0002\u0005\u0019t\u0007\u0003\u0002\u0015_+VK!aX\u0015\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\fAaY8qsR\u0019QKY2\t\u000fq:\u0001\u0013!a\u0001}!9Qj\u0002I\u0001\u0002\u0004y\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002M*\u0012ahZ\u0016\u0002QB\u0011\u0011N\\\u0007\u0002U*\u00111\u000e\\\u0001\nk:\u001c\u0007.Z2lK\u0012T!!\\\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002pU\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\t!O\u000b\u0002PO\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u001e\t\u0003mnl\u0011a\u001e\u0006\u0003qf\fA\u0001\\1oO*\t!0\u0001\u0003kCZ\f\u0017B\u0001?x\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tq\u0010E\u0002)\u0003\u0003I1!a\u0001*\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tI!a\u0004\u0011\u0007!\nY!C\u0002\u0002\u000e%\u00121!\u00118z\u0011!\t\t\u0002DA\u0001\u0002\u0004y\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0018A1\u0011\u0011DA\u0010\u0003\u0013i!!a\u0007\u000b\u0007\u0005u\u0011&\u0001\u0006d_2dWm\u0019;j_:LA!!\t\u0002\u001c\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9#!\f\u0011\u0007!\nI#C\u0002\u0002,%\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u00129\t\t\u00111\u0001\u0002\n\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r)\u00181\u0007\u0005\t\u0003#y\u0011\u0011!a\u0001\u007f\u0006A\u0001.Y:i\u0007>$W\rF\u0001\u0000\u0003!!xn\u0015;sS:<G#A;\u0002\r\u0015\fX/\u00197t)\u0011\t9#!\u0011\t\u0013\u0005E!#!AA\u0002\u0005%\u0001f\u0001\u0001\u0002FA!\u0011qIA&\u001b\t\tIE\u0003\u0002n?%!\u0011QJA%\u0005!)fn\u001d;bE2,\u0007f\u0001\u0001\u0002RA!\u0011qIA*\u0013\u0011\t)&!\u0013\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u0011M\u0003\u0018M]6Q_\u0012\u0004\"A\u0016\u000b\u0014\tQ9\u0013Q\f\t\u0005\u0003?\n\u0019'\u0004\u0002\u0002b)\u0011\u0011*_\u0005\u0004u\u0005\u0005DCAA-\u0003)Ig.\u001b;jC2\u0004v\u000e\u001a\u000b\u0002+\u0006)\u0011\r\u001d9msR)Q+a\u001c\u0002r!)Ah\u0006a\u0001}!)Qj\u0006a\u0001\u001f\u00069QO\\1qa2LH\u0003BA<\u0003\u0007\u0003R\u0001KA=\u0003{J1!a\u001f*\u0005\u0019y\u0005\u000f^5p]B)\u0001&a ?\u001f&\u0019\u0011\u0011Q\u0015\u0003\rQ+\b\u000f\\33\u0011!\t)\tGA\u0001\u0002\u0004)\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0012\t\u0004m\u00065\u0015bAAHo\n1qJ\u00196fGR\u0004"
)
public class SparkPod implements Product, Serializable {
   private final Pod pod;
   private final Container container;

   public static Option unapply(final SparkPod x$0) {
      return SparkPod$.MODULE$.unapply(x$0);
   }

   public static SparkPod apply(final Pod pod, final Container container) {
      return SparkPod$.MODULE$.apply(pod, container);
   }

   public static SparkPod initialPod() {
      return SparkPod$.MODULE$.initialPod();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Pod pod() {
      return this.pod;
   }

   public Container container() {
      return this.container;
   }

   public SparkPod transform(final PartialFunction fn) {
      return (SparkPod)((Option)fn.lift().apply(this)).getOrElse(() -> this);
   }

   public SparkPod copy(final Pod pod, final Container container) {
      return new SparkPod(pod, container);
   }

   public Pod copy$default$1() {
      return this.pod();
   }

   public Container copy$default$2() {
      return this.container();
   }

   public String productPrefix() {
      return "SparkPod";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.pod();
         }
         case 1 -> {
            return this.container();
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
      return x$1 instanceof SparkPod;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "pod";
         }
         case 1 -> {
            return "container";
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
            if (x$1 instanceof SparkPod) {
               label48: {
                  SparkPod var4 = (SparkPod)x$1;
                  Pod var10000 = this.pod();
                  Pod var5 = var4.pod();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Container var7 = this.container();
                  Container var6 = var4.container();
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

   public SparkPod(final Pod pod, final Container container) {
      this.pod = pod;
      this.container = container;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
