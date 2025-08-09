package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Unstable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005eg\u0001B\u0010!\u0001.B\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005\u0007\"A\u0001\n\u0001BK\u0002\u0013\u0005\u0011\n\u0003\u0005\\\u0001\tE\t\u0015!\u0003K\u0011!a\u0006A!f\u0001\n\u0003I\u0005\u0002C/\u0001\u0005#\u0005\u000b\u0011\u0002&\t\u0011y\u0003!Q3A\u0005\u0002}C\u0001b\u001b\u0001\u0003\u0012\u0003\u0006I\u0001\u0019\u0005\u0006Y\u0002!\t!\u001c\u0005\bg\u0002\t\t\u0011\"\u0001u\u0011\u001dI\b!%A\u0005\u0002iD\u0011\"a\u0003\u0001#\u0003%\t!!\u0004\t\u0013\u0005E\u0001!%A\u0005\u0002\u00055\u0001\"CA\n\u0001E\u0005I\u0011AA\u000b\u0011%\tI\u0002AA\u0001\n\u0003\nY\u0002C\u0005\u0002,\u0001\t\t\u0011\"\u0001\u0002.!I\u0011Q\u0007\u0001\u0002\u0002\u0013\u0005\u0011q\u0007\u0005\n\u0003\u0007\u0002\u0011\u0011!C!\u0003\u000bB\u0011\"a\u0015\u0001\u0003\u0003%\t!!\u0016\t\u0013\u0005}\u0003!!A\u0005B\u0005\u0005\u0004\"CA3\u0001\u0005\u0005I\u0011IA4\u0011%\tI\u0007AA\u0001\n\u0003\nY\u0007C\u0005\u0002n\u0001\t\t\u0011\"\u0011\u0002p\u001dI\u00111\u0013\u0011\u0002\u0002#\u0005\u0011Q\u0013\u0004\t?\u0001\n\t\u0011#\u0001\u0002\u0018\"1A.\u0007C\u0001\u0003[C\u0011\"!\u001b\u001a\u0003\u0003%)%a\u001b\t\u0013\u0005=\u0016$!A\u0005\u0002\u0006E\u0006\"CA^3\u0005\u0005I\u0011QA_\u0011%\ty-GA\u0001\n\u0013\t\tN\u0001\u000bLk\n,'O\\3uKN$%/\u001b<feN\u0003Xm\u0019\u0006\u0003C\t\n1a\u001b\u001dt\u0015\t\u0019C%\u0001\u0004eKBdw.\u001f\u0006\u0003K\u0019\nQa\u001d9be.T!a\n\u0015\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0013aA8sO\u000e\u00011\u0003\u0002\u0001-eU\u0002\"!\f\u0019\u000e\u00039R\u0011aL\u0001\u0006g\u000e\fG.Y\u0005\u0003c9\u0012a!\u00118z%\u00164\u0007CA\u00174\u0013\t!dFA\u0004Qe>$Wo\u0019;\u0011\u0005YrdBA\u001c=\u001d\tA4(D\u0001:\u0015\tQ$&\u0001\u0004=e>|GOP\u0005\u0002_%\u0011QHL\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0004I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002>]\u0005\u0019\u0001o\u001c3\u0016\u0003\r\u0003\"\u0001R#\u000e\u0003\u0001J!A\u0012\u0011\u0003\u0011M\u0003\u0018M]6Q_\u0012\fA\u0001]8eA\u0005aBM]5wKJ\u0004&/Z&vE\u0016\u0014h.\u001a;fgJ+7o\\;sG\u0016\u001cX#\u0001&\u0011\u0007YZU*\u0003\u0002M\u0001\n\u00191+Z9\u0011\u00059KV\"A(\u000b\u0005A\u000b\u0016!B7pI\u0016d'B\u0001*T\u0003\r\t\u0007/\u001b\u0006\u0003)V\u000b!b[;cKJtW\r^3t\u0015\t1v+A\u0004gC\n\u0014\u0018n\u0019\u001d\u000b\u0003a\u000b!![8\n\u0005i{%a\u0003%bg6+G/\u00193bi\u0006\fQ\u0004\u001a:jm\u0016\u0014\bK]3Lk\n,'O\\3uKN\u0014Vm]8ve\u000e,7\u000fI\u0001\u001aIJLg/\u001a:Lk\n,'O\\3uKN\u0014Vm]8ve\u000e,7/\u0001\u000eee&4XM]&vE\u0016\u0014h.\u001a;fgJ+7o\\;sG\u0016\u001c\b%\u0001\ttsN$X-\u001c)s_B,'\u000f^5fgV\t\u0001\r\u0005\u0003bK\"DgB\u00012d!\tAd&\u0003\u0002e]\u00051\u0001K]3eK\u001aL!AZ4\u0003\u00075\u000b\u0007O\u0003\u0002e]A\u0011\u0011-[\u0005\u0003U\u001e\u0014aa\u0015;sS:<\u0017!E:zgR,W\u000e\u0015:pa\u0016\u0014H/[3tA\u00051A(\u001b8jiz\"RA\\8qcJ\u0004\"\u0001\u0012\u0001\t\u000b\u0005K\u0001\u0019A\"\t\u000b!K\u0001\u0019\u0001&\t\u000bqK\u0001\u0019\u0001&\t\u000byK\u0001\u0019\u00011\u0002\t\r|\u0007/\u001f\u000b\u0006]V4x\u000f\u001f\u0005\b\u0003*\u0001\n\u00111\u0001D\u0011\u001dA%\u0002%AA\u0002)Cq\u0001\u0018\u0006\u0011\u0002\u0003\u0007!\nC\u0004_\u0015A\u0005\t\u0019\u00011\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t1P\u000b\u0002Dy.\nQ\u0010E\u0002\u007f\u0003\u000fi\u0011a \u0006\u0005\u0003\u0003\t\u0019!A\u0005v]\u000eDWmY6fI*\u0019\u0011Q\u0001\u0018\u0002\u0015\u0005tgn\u001c;bi&|g.C\u0002\u0002\n}\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!a\u0004+\u0005)c\u0018AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\t9B\u000b\u0002ay\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!\b\u0011\t\u0005}\u0011\u0011F\u0007\u0003\u0003CQA!a\t\u0002&\u0005!A.\u00198h\u0015\t\t9#\u0001\u0003kCZ\f\u0017b\u00016\u0002\"\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011q\u0006\t\u0004[\u0005E\u0012bAA\u001a]\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011HA !\ri\u00131H\u0005\u0004\u0003{q#aA!os\"I\u0011\u0011I\t\u0002\u0002\u0003\u0007\u0011qF\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\u001d\u0003CBA%\u0003\u001f\nI$\u0004\u0002\u0002L)\u0019\u0011Q\n\u0018\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002R\u0005-#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0016\u0002^A\u0019Q&!\u0017\n\u0007\u0005mcFA\u0004C_>dW-\u00198\t\u0013\u0005\u00053#!AA\u0002\u0005e\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\b\u0002d!I\u0011\u0011\t\u000b\u0002\u0002\u0003\u0007\u0011qF\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011qF\u0001\ti>\u001cFO]5oOR\u0011\u0011QD\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005]\u0013\u0011\u000f\u0005\n\u0003\u0003:\u0012\u0011!a\u0001\u0003sA3\u0001AA;!\u0011\t9(a\u001f\u000e\u0005\u0005e$bAA\u0003I%!\u0011QPA=\u0005!)fn\u001d;bE2,\u0007f\u0001\u0001\u0002\u0002B!\u0011qOAB\u0013\u0011\t))!\u001f\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5)\u000b\u0001\tI)a$\u0011\t\u0005]\u00141R\u0005\u0005\u0003\u001b\u000bIHA\u0003TS:\u001cW-\t\u0002\u0002\u0012\u0006)1GL\u001a/a\u0005!2*\u001e2fe:,G/Z:Ee&4XM]*qK\u000e\u0004\"\u0001R\r\u0014\u000be\tI*!*\u0011\u0013\u0005m\u0015\u0011U\"K\u0015\u0002tWBAAO\u0015\r\tyJL\u0001\beVtG/[7f\u0013\u0011\t\u0019+!(\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tG\u0007\u0005\u0003\u0002(\u0006-VBAAU\u0015\rA\u0016QE\u0005\u0004\u007f\u0005%FCAAK\u0003\u0015\t\u0007\u000f\u001d7z)%q\u00171WA[\u0003o\u000bI\fC\u0003B9\u0001\u00071\tC\u0003I9\u0001\u0007!\nC\u0003]9\u0001\u0007!\nC\u0003_9\u0001\u0007\u0001-A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005}\u00161\u001a\t\u0006[\u0005\u0005\u0017QY\u0005\u0004\u0003\u0007t#AB(qi&|g\u000eE\u0004.\u0003\u000f\u001c%J\u00131\n\u0007\u0005%gF\u0001\u0004UkBdW\r\u000e\u0005\t\u0003\u001bl\u0012\u0011!a\u0001]\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005M\u0007\u0003BA\u0010\u0003+LA!a6\u0002\"\t1qJ\u00196fGR\u0004"
)
public class KubernetesDriverSpec implements Product, Serializable {
   private final SparkPod pod;
   private final Seq driverPreKubernetesResources;
   private final Seq driverKubernetesResources;
   private final Map systemProperties;

   public static Option unapply(final KubernetesDriverSpec x$0) {
      return KubernetesDriverSpec$.MODULE$.unapply(x$0);
   }

   public static KubernetesDriverSpec apply(final SparkPod pod, final Seq driverPreKubernetesResources, final Seq driverKubernetesResources, final Map systemProperties) {
      return KubernetesDriverSpec$.MODULE$.apply(pod, driverPreKubernetesResources, driverKubernetesResources, systemProperties);
   }

   public static Function1 tupled() {
      return KubernetesDriverSpec$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return KubernetesDriverSpec$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SparkPod pod() {
      return this.pod;
   }

   public Seq driverPreKubernetesResources() {
      return this.driverPreKubernetesResources;
   }

   public Seq driverKubernetesResources() {
      return this.driverKubernetesResources;
   }

   public Map systemProperties() {
      return this.systemProperties;
   }

   public KubernetesDriverSpec copy(final SparkPod pod, final Seq driverPreKubernetesResources, final Seq driverKubernetesResources, final Map systemProperties) {
      return new KubernetesDriverSpec(pod, driverPreKubernetesResources, driverKubernetesResources, systemProperties);
   }

   public SparkPod copy$default$1() {
      return this.pod();
   }

   public Seq copy$default$2() {
      return this.driverPreKubernetesResources();
   }

   public Seq copy$default$3() {
      return this.driverKubernetesResources();
   }

   public Map copy$default$4() {
      return this.systemProperties();
   }

   public String productPrefix() {
      return "KubernetesDriverSpec";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.pod();
         }
         case 1 -> {
            return this.driverPreKubernetesResources();
         }
         case 2 -> {
            return this.driverKubernetesResources();
         }
         case 3 -> {
            return this.systemProperties();
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
      return x$1 instanceof KubernetesDriverSpec;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "pod";
         }
         case 1 -> {
            return "driverPreKubernetesResources";
         }
         case 2 -> {
            return "driverKubernetesResources";
         }
         case 3 -> {
            return "systemProperties";
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
      boolean var12;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof KubernetesDriverSpec) {
               label64: {
                  KubernetesDriverSpec var4 = (KubernetesDriverSpec)x$1;
                  SparkPod var10000 = this.pod();
                  SparkPod var5 = var4.pod();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label64;
                  }

                  Seq var9 = this.driverPreKubernetesResources();
                  Seq var6 = var4.driverPreKubernetesResources();
                  if (var9 == null) {
                     if (var6 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var6)) {
                     break label64;
                  }

                  var9 = this.driverKubernetesResources();
                  Seq var7 = var4.driverKubernetesResources();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var7)) {
                     break label64;
                  }

                  Map var11 = this.systemProperties();
                  Map var8 = var4.systemProperties();
                  if (var11 == null) {
                     if (var8 != null) {
                        break label64;
                     }
                  } else if (!var11.equals(var8)) {
                     break label64;
                  }

                  if (var4.canEqual(this)) {
                     break label71;
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public KubernetesDriverSpec(final SparkPod pod, final Seq driverPreKubernetesResources, final Seq driverKubernetesResources, final Map systemProperties) {
      this.pod = pod;
      this.driverPreKubernetesResources = driverPreKubernetesResources;
      this.driverKubernetesResources = driverKubernetesResources;
      this.systemProperties = systemProperties;
      Product.$init$(this);
   }
}
