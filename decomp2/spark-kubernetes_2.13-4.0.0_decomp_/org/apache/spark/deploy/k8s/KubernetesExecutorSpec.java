package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md!B\r\u001b\u0001z!\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u0011\u0005\u0003!\u0011#Q\u0001\nuB\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t+\u0002\u0011\t\u0012)A\u0005\t\")a\u000b\u0001C\u0001/\"91\fAA\u0001\n\u0003a\u0006bB0\u0001#\u0003%\t\u0001\u0019\u0005\bW\u0002\t\n\u0011\"\u0001m\u0011\u001dq\u0007!!A\u0005B=Dq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0001\u0005\u0005I\u0011\u0001@\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003g\u0001\u0011\u0011!C!\u0003k9!\"!\u000f\u001b\u0003\u0003E\tAHA\u001e\r%I\"$!A\t\u0002y\ti\u0004\u0003\u0004W'\u0011\u0005\u00111\u000b\u0005\n\u0003_\u0019\u0012\u0011!C#\u0003cA\u0011\"!\u0016\u0014\u0003\u0003%\t)a\u0016\t\u0013\u0005u3#!A\u0005\u0002\u0006}\u0003\"CA9'\u0005\u0005I\u0011BA:\u0005YYUOY3s]\u0016$Xm]#yK\u000e,Ho\u001c:Ta\u0016\u001c'BA\u000e\u001d\u0003\rY\u0007h\u001d\u0006\u0003;y\ta\u0001Z3qY>L(BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0014\t\u0001)3F\f\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0019b\u0013BA\u0017(\u0005\u001d\u0001&o\u001c3vGR\u0004\"a\f\u001d\u000f\u0005A2dBA\u00196\u001b\u0005\u0011$BA\u001a5\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0015\n\u0005]:\u0013a\u00029bG.\fw-Z\u0005\u0003si\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aN\u0014\u0002\u0007A|G-F\u0001>!\tqt(D\u0001\u001b\u0013\t\u0001%D\u0001\u0005Ta\u0006\u00148\u000eU8e\u0003\u0011\u0001x\u000e\u001a\u0011\u00027\u0015DXmY;u_J\\UOY3s]\u0016$Xm\u001d*fg>,(oY3t+\u0005!\u0005cA\u0018F\u000f&\u0011aI\u000f\u0002\u0004'\u0016\f\bC\u0001%T\u001b\u0005I%B\u0001&L\u0003\u0015iw\u000eZ3m\u0015\taU*A\u0002ba&T!AT(\u0002\u0015-,(-\u001a:oKR,7O\u0003\u0002Q#\u00069a-\u00192sS\u000eD$\"\u0001*\u0002\u0005%|\u0017B\u0001+J\u0005-A\u0015m]'fi\u0006$\u0017\r^1\u00029\u0015DXmY;u_J\\UOY3s]\u0016$Xm\u001d*fg>,(oY3tA\u00051A(\u001b8jiz\"2\u0001W-[!\tq\u0004\u0001C\u0003<\u000b\u0001\u0007Q\bC\u0003C\u000b\u0001\u0007A)\u0001\u0003d_BLHc\u0001-^=\"91H\u0002I\u0001\u0002\u0004i\u0004b\u0002\"\u0007!\u0003\u0005\r\u0001R\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\t'FA\u001fcW\u0005\u0019\u0007C\u00013j\u001b\u0005)'B\u00014h\u0003%)hn\u00195fG.,GM\u0003\u0002iO\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005),'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A7+\u0005\u0011\u0013\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001q!\t\th/D\u0001s\u0015\t\u0019H/\u0001\u0003mC:<'\"A;\u0002\t)\fg/Y\u0005\u0003oJ\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001>\u0011\u0005\u0019Z\u0018B\u0001?(\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ry\u0018Q\u0001\t\u0004M\u0005\u0005\u0011bAA\u0002O\t\u0019\u0011I\\=\t\u0011\u0005\u001d1\"!AA\u0002i\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0007!\u0015\ty!!\u0006\u0000\u001b\t\t\tBC\u0002\u0002\u0014\u001d\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9\"!\u0005\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003;\t\u0019\u0003E\u0002'\u0003?I1!!\t(\u0005\u001d\u0011un\u001c7fC:D\u0001\"a\u0002\u000e\u0003\u0003\u0005\ra`\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002q\u0003SA\u0001\"a\u0002\u000f\u0003\u0003\u0005\rA_\u0001\tQ\u0006\u001c\bnQ8eKR\t!0\u0001\u0005u_N#(/\u001b8h)\u0005\u0001\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002\u001e\u0005]\u0002\u0002CA\u0004#\u0005\u0005\t\u0019A@\u0002--+(-\u001a:oKR,7/\u0012=fGV$xN]*qK\u000e\u0004\"AP\n\u0014\u000bM\ty$a\u0013\u0011\u000f\u0005\u0005\u0013qI\u001fE16\u0011\u00111\t\u0006\u0004\u0003\u000b:\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u0013\n\u0019EA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!!\u0014\u0002R5\u0011\u0011q\n\u0006\u0003%RL1!OA()\t\tY$A\u0003baBd\u0017\u0010F\u0003Y\u00033\nY\u0006C\u0003<-\u0001\u0007Q\bC\u0003C-\u0001\u0007A)A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0005\u0014Q\u000e\t\u0006M\u0005\r\u0014qM\u0005\u0004\u0003K:#AB(qi&|g\u000eE\u0003'\u0003SjD)C\u0002\u0002l\u001d\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA8/\u0005\u0005\t\u0019\u0001-\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002vA\u0019\u0011/a\u001e\n\u0007\u0005e$O\u0001\u0004PE*,7\r\u001e"
)
public class KubernetesExecutorSpec implements Product, Serializable {
   private final SparkPod pod;
   private final Seq executorKubernetesResources;

   public static Option unapply(final KubernetesExecutorSpec x$0) {
      return KubernetesExecutorSpec$.MODULE$.unapply(x$0);
   }

   public static KubernetesExecutorSpec apply(final SparkPod pod, final Seq executorKubernetesResources) {
      return KubernetesExecutorSpec$.MODULE$.apply(pod, executorKubernetesResources);
   }

   public static Function1 tupled() {
      return KubernetesExecutorSpec$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return KubernetesExecutorSpec$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SparkPod pod() {
      return this.pod;
   }

   public Seq executorKubernetesResources() {
      return this.executorKubernetesResources;
   }

   public KubernetesExecutorSpec copy(final SparkPod pod, final Seq executorKubernetesResources) {
      return new KubernetesExecutorSpec(pod, executorKubernetesResources);
   }

   public SparkPod copy$default$1() {
      return this.pod();
   }

   public Seq copy$default$2() {
      return this.executorKubernetesResources();
   }

   public String productPrefix() {
      return "KubernetesExecutorSpec";
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
            return this.executorKubernetesResources();
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
      return x$1 instanceof KubernetesExecutorSpec;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "pod";
         }
         case 1 -> {
            return "executorKubernetesResources";
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
            if (x$1 instanceof KubernetesExecutorSpec) {
               label48: {
                  KubernetesExecutorSpec var4 = (KubernetesExecutorSpec)x$1;
                  SparkPod var10000 = this.pod();
                  SparkPod var5 = var4.pod();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Seq var7 = this.executorKubernetesResources();
                  Seq var6 = var4.executorKubernetesResources();
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

   public KubernetesExecutorSpec(final SparkPod pod, final Seq executorKubernetesResources) {
      this.pod = pod;
      this.executorKubernetesResources = executorKubernetesResources;
      Product.$init$(this);
   }
}
