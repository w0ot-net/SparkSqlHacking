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
   bytes = "\u0006\u0005\u0005\rd!B\r\u001b\u0001z!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011%\u0003!\u0011#Q\u0001\n\u0005C\u0001B\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0003\")A\n\u0001C\u0001\u001b\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\n\u0011\"\u0001W\u0011\u001d\u0011\u0007!!A\u0005B\rDqa\u001b\u0001\u0002\u0002\u0013\u0005A\u000eC\u0004q\u0001\u0005\u0005I\u0011A9\t\u000f]\u0004\u0011\u0011!C!q\"Aq\u0010AA\u0001\n\u0003\t\t\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003+\u0001\u0011\u0011!C!\u0003/A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\b\u0015\u0005}!$!A\t\u0002y\t\tCB\u0005\u001a5\u0005\u0005\t\u0012\u0001\u0010\u0002$!1Aj\u0005C\u0001\u0003wA\u0011\"!\u0006\u0014\u0003\u0003%)%a\u0006\t\u0013\u0005u2#!A\u0005\u0002\u0006}\u0002\"CA#'\u0005\u0005I\u0011QA$\u0011%\tIfEA\u0001\n\u0013\tYF\u0001\u000fLk\n,'O\\3uKNDun\u001d;QCRDgk\u001c7v[\u0016\u001cuN\u001c4\u000b\u0005ma\u0012aA69g*\u0011QDH\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005}\u0001\u0013!B:qCJ\\'BA\u0011#\u0003\u0019\t\u0007/Y2iK*\t1%A\u0002pe\u001e\u001cR\u0001A\u0013,_I\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0007C\u0001\u0017.\u001b\u0005Q\u0012B\u0001\u0018\u001b\u0005qYUOY3s]\u0016$Xm\u001d,pYVlWm\u00159fG&4\u0017nY\"p]\u001a\u0004\"A\n\u0019\n\u0005E:#a\u0002)s_\u0012,8\r\u001e\t\u0003gqr!\u0001\u000e\u001e\u000f\u0005UJT\"\u0001\u001c\u000b\u0005]B\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003!J!aO\u0014\u0002\u000fA\f7m[1hK&\u0011QH\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003w\u001d\n\u0001\u0002[8tiB\u000bG\u000f[\u000b\u0002\u0003B\u0011!I\u0012\b\u0003\u0007\u0012\u0003\"!N\u0014\n\u0005\u0015;\u0013A\u0002)sK\u0012,g-\u0003\u0002H\u0011\n11\u000b\u001e:j]\u001eT!!R\u0014\u0002\u0013!|7\u000f\u001e)bi\"\u0004\u0013A\u0003<pYVlW\rV=qK\u0006Yao\u001c7v[\u0016$\u0016\u0010]3!\u0003\u0019a\u0014N\\5u}Q\u0019aj\u0014)\u0011\u00051\u0002\u0001\"B \u0006\u0001\u0004\t\u0005\"\u0002&\u0006\u0001\u0004\t\u0015\u0001B2paf$2AT*U\u0011\u001dyd\u0001%AA\u0002\u0005CqA\u0013\u0004\u0011\u0002\u0003\u0007\u0011)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003]S#!\u0011-,\u0003e\u0003\"AW0\u000e\u0003mS!\u0001X/\u0002\u0013Ut7\r[3dW\u0016$'B\u00010(\u0003)\tgN\\8uCRLwN\\\u0005\u0003An\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00013\u0011\u0005\u0015TW\"\u00014\u000b\u0005\u001dD\u0017\u0001\u00027b]\u001eT\u0011![\u0001\u0005U\u00064\u0018-\u0003\u0002HM\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tQ\u000e\u0005\u0002']&\u0011qn\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003eV\u0004\"AJ:\n\u0005Q<#aA!os\"9aoCA\u0001\u0002\u0004i\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001z!\rQXP]\u0007\u0002w*\u0011ApJ\u0001\u000bG>dG.Z2uS>t\u0017B\u0001@|\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\r\u0011\u0011\u0002\t\u0004M\u0005\u0015\u0011bAA\u0004O\t9!i\\8mK\u0006t\u0007b\u0002<\u000e\u0003\u0003\u0005\rA]\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002e\u0003\u001fAqA\u001e\b\u0002\u0002\u0003\u0007Q.\u0001\u0005iCND7i\u001c3f)\u0005i\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0011\fa!Z9vC2\u001cH\u0003BA\u0002\u0003;AqA^\t\u0002\u0002\u0003\u0007!/\u0001\u000fLk\n,'O\\3uKNDun\u001d;QCRDgk\u001c7v[\u0016\u001cuN\u001c4\u0011\u00051\u001a2#B\n\u0002&\u0005E\u0002cBA\u0014\u0003[\t\u0015IT\u0007\u0003\u0003SQ1!a\u000b(\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\f\u0002*\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005M\u0012\u0011H\u0007\u0003\u0003kQ1!a\u000ei\u0003\tIw.C\u0002>\u0003k!\"!!\t\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b9\u000b\t%a\u0011\t\u000b}2\u0002\u0019A!\t\u000b)3\u0002\u0019A!\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011JA+!\u00151\u00131JA(\u0013\r\tie\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u0019\n\t&Q!\n\u0007\u0005MsE\u0001\u0004UkBdWM\r\u0005\t\u0003/:\u0012\u0011!a\u0001\u001d\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0003cA3\u0002`%\u0019\u0011\u0011\r4\u0003\r=\u0013'.Z2u\u0001"
)
public class KubernetesHostPathVolumeConf implements KubernetesVolumeSpecificConf, Product, Serializable {
   private final String hostPath;
   private final String volumeType;

   public static Option unapply(final KubernetesHostPathVolumeConf x$0) {
      return KubernetesHostPathVolumeConf$.MODULE$.unapply(x$0);
   }

   public static KubernetesHostPathVolumeConf apply(final String hostPath, final String volumeType) {
      return KubernetesHostPathVolumeConf$.MODULE$.apply(hostPath, volumeType);
   }

   public static Function1 tupled() {
      return KubernetesHostPathVolumeConf$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return KubernetesHostPathVolumeConf$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String hostPath() {
      return this.hostPath;
   }

   public String volumeType() {
      return this.volumeType;
   }

   public KubernetesHostPathVolumeConf copy(final String hostPath, final String volumeType) {
      return new KubernetesHostPathVolumeConf(hostPath, volumeType);
   }

   public String copy$default$1() {
      return this.hostPath();
   }

   public String copy$default$2() {
      return this.volumeType();
   }

   public String productPrefix() {
      return "KubernetesHostPathVolumeConf";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.hostPath();
         }
         case 1 -> {
            return this.volumeType();
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
      return x$1 instanceof KubernetesHostPathVolumeConf;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "hostPath";
         }
         case 1 -> {
            return "volumeType";
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
            if (x$1 instanceof KubernetesHostPathVolumeConf) {
               label48: {
                  KubernetesHostPathVolumeConf var4 = (KubernetesHostPathVolumeConf)x$1;
                  String var10000 = this.hostPath();
                  String var5 = var4.hostPath();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.volumeType();
                  String var6 = var4.volumeType();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var6)) {
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

   public KubernetesHostPathVolumeConf(final String hostPath, final String volumeType) {
      this.hostPath = hostPath;
      this.volumeType = volumeType;
      Product.$init$(this);
   }
}
