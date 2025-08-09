package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=g!\u0002\u0016,\u0001>*\u0004\u0002\u0003)\u0001\u0005+\u0007I\u0011A)\t\u0011i\u0003!\u0011#Q\u0001\nIC\u0001b\u0017\u0001\u0003\u0016\u0004%\t\u0001\u0018\u0005\tA\u0002\u0011\t\u0012)A\u0005;\"A\u0011\r\u0001BK\u0002\u0013\u0005A\f\u0003\u0005c\u0001\tE\t\u0015!\u0003^\u0011!\u0019\u0007A!f\u0001\n\u0003!\u0007\u0002C5\u0001\u0005#\u0005\u000b\u0011B3\t\u0011)\u0004!Q3A\u0005\u0002\u0011D\u0001b\u001b\u0001\u0003\u0012\u0003\u0006I!\u001a\u0005\u0006Y\u0002!\t!\u001c\u0005\bi\u0002\t\t\u0011\"\u0001v\u0011\u001dY\b!%A\u0005\u0002qD\u0011\"a\u0004\u0001#\u0003%\t!!\u0005\t\u0013\u0005U\u0001!%A\u0005\u0002\u0005E\u0001\"CA\f\u0001E\u0005I\u0011AA\r\u0011%\ti\u0002AI\u0001\n\u0003\tI\u0002C\u0005\u0002 \u0001\t\t\u0011\"\u0011\u0002\"!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003w\u0001\u0011\u0011!C\u0001\u0003{A\u0011\"!\u0013\u0001\u0003\u0003%\t%a\u0013\t\u0013\u0005e\u0003!!A\u0005\u0002\u0005m\u0003\"CA3\u0001\u0005\u0005I\u0011IA4\u0011%\tY\u0007AA\u0001\n\u0003\ni\u0007C\u0005\u0002p\u0001\t\t\u0011\"\u0011\u0002r!I\u00111\u000f\u0001\u0002\u0002\u0013\u0005\u0013QO\u0004\u000b\u0003sZ\u0013\u0011!E\u0001_\u0005md!\u0003\u0016,\u0003\u0003E\taLA?\u0011\u0019aG\u0004\"\u0001\u0002\u0016\"I\u0011q\u000e\u000f\u0002\u0002\u0013\u0015\u0013\u0011\u000f\u0005\n\u0003/c\u0012\u0011!CA\u00033C\u0011\"!*\u001d#\u0003%\t!!\u0005\t\u0013\u0005\u001dF$%A\u0005\u0002\u0005E\u0001\"CAU9E\u0005I\u0011AA\r\u0011%\tY\u000bHI\u0001\n\u0003\tI\u0002C\u0005\u0002.r\t\t\u0011\"!\u00020\"I\u0011Q\u0018\u000f\u0012\u0002\u0013\u0005\u0011\u0011\u0003\u0005\n\u0003\u007fc\u0012\u0013!C\u0001\u0003#A\u0011\"!1\u001d#\u0003%\t!!\u0007\t\u0013\u0005\rG$%A\u0005\u0002\u0005e\u0001\"CAc9\u0005\u0005I\u0011BAd\u0005]YUOY3s]\u0016$Xm\u001d)W\u0007Z{G.^7f\u0007>tgM\u0003\u0002-[\u0005\u00191\u000eO:\u000b\u00059z\u0013A\u00023fa2|\u0017P\u0003\u00021c\u0005)1\u000f]1sW*\u0011!gM\u0001\u0007CB\f7\r[3\u000b\u0003Q\n1a\u001c:h'\u0015\u0001a\u0007\u0010!D!\t9$(D\u00019\u0015\u0005I\u0014!B:dC2\f\u0017BA\u001e9\u0005\u0019\te.\u001f*fMB\u0011QHP\u0007\u0002W%\u0011qh\u000b\u0002\u001d\u0017V\u0014WM\u001d8fi\u0016\u001chk\u001c7v[\u0016\u001c\u0006/Z2jM&\u001c7i\u001c8g!\t9\u0014)\u0003\u0002Cq\t9\u0001K]8ek\u000e$\bC\u0001#N\u001d\t)5J\u0004\u0002G\u00156\tqI\u0003\u0002I\u0013\u00061AH]8piz\u001a\u0001!C\u0001:\u0013\ta\u0005(A\u0004qC\u000e\\\u0017mZ3\n\u00059{%\u0001D*fe&\fG.\u001b>bE2,'B\u0001'9\u0003%\u0019G.Y5n\u001d\u0006lW-F\u0001S!\t\u0019vK\u0004\u0002U+B\u0011a\tO\u0005\u0003-b\na\u0001\u0015:fI\u00164\u0017B\u0001-Z\u0005\u0019\u0019FO]5oO*\u0011a\u000bO\u0001\u000bG2\f\u0017.\u001c(b[\u0016\u0004\u0013\u0001D:u_J\fw-Z\"mCN\u001cX#A/\u0011\u0007]r&+\u0003\u0002`q\t1q\n\u001d;j_:\fQb\u001d;pe\u0006<Wm\u00117bgN\u0004\u0013\u0001B:ju\u0016\fQa]5{K\u0002\na\u0001\\1cK2\u001cX#A3\u0011\u0007]rf\r\u0005\u0003TOJ\u0013\u0016B\u00015Z\u0005\ri\u0015\r]\u0001\bY\u0006\u0014W\r\\:!\u0003-\tgN\\8uCRLwN\\:\u0002\u0019\u0005tgn\u001c;bi&|gn\u001d\u0011\u0002\rqJg.\u001b;?)\u0019qw\u000e]9sgB\u0011Q\b\u0001\u0005\u0006!.\u0001\rA\u0015\u0005\b7.\u0001\n\u00111\u0001^\u0011\u001d\t7\u0002%AA\u0002uCqaY\u0006\u0011\u0002\u0003\u0007Q\rC\u0004k\u0017A\u0005\t\u0019A3\u0002\t\r|\u0007/\u001f\u000b\u0007]Z<\b0\u001f>\t\u000fAc\u0001\u0013!a\u0001%\"91\f\u0004I\u0001\u0002\u0004i\u0006bB1\r!\u0003\u0005\r!\u0018\u0005\bG2\u0001\n\u00111\u0001f\u0011\u001dQG\u0002%AA\u0002\u0015\fabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001~U\t\u0011fpK\u0001\u0000!\u0011\t\t!a\u0003\u000e\u0005\u0005\r!\u0002BA\u0003\u0003\u000f\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005%\u0001(\u0001\u0006b]:|G/\u0019;j_:LA!!\u0004\u0002\u0004\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u0003\u0016\u0003;z\fabY8qs\u0012\"WMZ1vYR$3'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005m!FA3\u007f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0012!\u0011\t)#a\f\u000e\u0005\u0005\u001d\"\u0002BA\u0015\u0003W\tA\u0001\\1oO*\u0011\u0011QF\u0001\u0005U\u00064\u0018-C\u0002Y\u0003O\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u000e\u0011\u0007]\n9$C\u0002\u0002:a\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0010\u0002FA\u0019q'!\u0011\n\u0007\u0005\r\u0003HA\u0002B]fD\u0011\"a\u0012\u0015\u0003\u0003\u0005\r!!\u000e\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ti\u0005\u0005\u0004\u0002P\u0005U\u0013qH\u0007\u0003\u0003#R1!a\u00159\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003/\n\tF\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA/\u0003G\u00022aNA0\u0013\r\t\t\u0007\u000f\u0002\b\u0005>|G.Z1o\u0011%\t9EFA\u0001\u0002\u0004\ty$\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0012\u0003SB\u0011\"a\u0012\u0018\u0003\u0003\u0005\r!!\u000e\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u000e\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\t\u0002\r\u0015\fX/\u00197t)\u0011\ti&a\u001e\t\u0013\u0005\u001d#$!AA\u0002\u0005}\u0012aF&vE\u0016\u0014h.\u001a;fgB36IV8mk6,7i\u001c8g!\tiDdE\u0003\u001d\u0003\u007f\nY\t\u0005\u0006\u0002\u0002\u0006\u001d%+X/fK:l!!a!\u000b\u0007\u0005\u0015\u0005(A\u0004sk:$\u0018.\\3\n\t\u0005%\u00151\u0011\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:,\u0004\u0003BAG\u0003'k!!a$\u000b\t\u0005E\u00151F\u0001\u0003S>L1ATAH)\t\tY(A\u0003baBd\u0017\u0010F\u0006o\u00037\u000bi*a(\u0002\"\u0006\r\u0006\"\u0002) \u0001\u0004\u0011\u0006bB. !\u0003\u0005\r!\u0018\u0005\bC~\u0001\n\u00111\u0001^\u0011\u001d\u0019w\u0004%AA\u0002\u0015DqA[\u0010\u0011\u0002\u0003\u0007Q-A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00133\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u001a\u0014aD1qa2LH\u0005Z3gCVdG\u000f\n\u001b\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIU\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u00022\u0006e\u0006\u0003B\u001c_\u0003g\u0003\u0002bNA[%vkV-Z\u0005\u0004\u0003oC$A\u0002+va2,W\u0007\u0003\u0005\u0002<\u0012\n\t\u00111\u0001o\u0003\rAH\u0005M\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%i\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIU\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!3\u0011\t\u0005\u0015\u00121Z\u0005\u0005\u0003\u001b\f9C\u0001\u0004PE*,7\r\u001e"
)
public class KubernetesPVCVolumeConf implements KubernetesVolumeSpecificConf, Product, Serializable {
   private final String claimName;
   private final Option storageClass;
   private final Option size;
   private final Option labels;
   private final Option annotations;

   public static Option $lessinit$greater$default$5() {
      return KubernetesPVCVolumeConf$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return KubernetesPVCVolumeConf$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option $lessinit$greater$default$3() {
      return KubernetesPVCVolumeConf$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return KubernetesPVCVolumeConf$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final KubernetesPVCVolumeConf x$0) {
      return KubernetesPVCVolumeConf$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$5() {
      return KubernetesPVCVolumeConf$.MODULE$.apply$default$5();
   }

   public static Option apply$default$4() {
      return KubernetesPVCVolumeConf$.MODULE$.apply$default$4();
   }

   public static Option apply$default$3() {
      return KubernetesPVCVolumeConf$.MODULE$.apply$default$3();
   }

   public static Option apply$default$2() {
      return KubernetesPVCVolumeConf$.MODULE$.apply$default$2();
   }

   public static KubernetesPVCVolumeConf apply(final String claimName, final Option storageClass, final Option size, final Option labels, final Option annotations) {
      return KubernetesPVCVolumeConf$.MODULE$.apply(claimName, storageClass, size, labels, annotations);
   }

   public static Function1 tupled() {
      return KubernetesPVCVolumeConf$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return KubernetesPVCVolumeConf$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String claimName() {
      return this.claimName;
   }

   public Option storageClass() {
      return this.storageClass;
   }

   public Option size() {
      return this.size;
   }

   public Option labels() {
      return this.labels;
   }

   public Option annotations() {
      return this.annotations;
   }

   public KubernetesPVCVolumeConf copy(final String claimName, final Option storageClass, final Option size, final Option labels, final Option annotations) {
      return new KubernetesPVCVolumeConf(claimName, storageClass, size, labels, annotations);
   }

   public String copy$default$1() {
      return this.claimName();
   }

   public Option copy$default$2() {
      return this.storageClass();
   }

   public Option copy$default$3() {
      return this.size();
   }

   public Option copy$default$4() {
      return this.labels();
   }

   public Option copy$default$5() {
      return this.annotations();
   }

   public String productPrefix() {
      return "KubernetesPVCVolumeConf";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.claimName();
         }
         case 1 -> {
            return this.storageClass();
         }
         case 2 -> {
            return this.size();
         }
         case 3 -> {
            return this.labels();
         }
         case 4 -> {
            return this.annotations();
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
      return x$1 instanceof KubernetesPVCVolumeConf;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "claimName";
         }
         case 1 -> {
            return "storageClass";
         }
         case 2 -> {
            return "size";
         }
         case 3 -> {
            return "labels";
         }
         case 4 -> {
            return "annotations";
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
      boolean var14;
      if (this != x$1) {
         label79: {
            if (x$1 instanceof KubernetesPVCVolumeConf) {
               label72: {
                  KubernetesPVCVolumeConf var4 = (KubernetesPVCVolumeConf)x$1;
                  String var10000 = this.claimName();
                  String var5 = var4.claimName();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label72;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label72;
                  }

                  Option var10 = this.storageClass();
                  Option var6 = var4.storageClass();
                  if (var10 == null) {
                     if (var6 != null) {
                        break label72;
                     }
                  } else if (!var10.equals(var6)) {
                     break label72;
                  }

                  var10 = this.size();
                  Option var7 = var4.size();
                  if (var10 == null) {
                     if (var7 != null) {
                        break label72;
                     }
                  } else if (!var10.equals(var7)) {
                     break label72;
                  }

                  var10 = this.labels();
                  Option var8 = var4.labels();
                  if (var10 == null) {
                     if (var8 != null) {
                        break label72;
                     }
                  } else if (!var10.equals(var8)) {
                     break label72;
                  }

                  var10 = this.annotations();
                  Option var9 = var4.annotations();
                  if (var10 == null) {
                     if (var9 != null) {
                        break label72;
                     }
                  } else if (!var10.equals(var9)) {
                     break label72;
                  }

                  if (var4.canEqual(this)) {
                     break label79;
                  }
               }
            }

            var14 = false;
            return var14;
         }
      }

      var14 = true;
      return var14;
   }

   public KubernetesPVCVolumeConf(final String claimName, final Option storageClass, final Option size, final Option labels, final Option annotations) {
      this.claimName = claimName;
      this.storageClass = storageClass;
      this.size = size;
      this.labels = labels;
      this.annotations = annotations;
      Product.$init$(this);
   }
}
