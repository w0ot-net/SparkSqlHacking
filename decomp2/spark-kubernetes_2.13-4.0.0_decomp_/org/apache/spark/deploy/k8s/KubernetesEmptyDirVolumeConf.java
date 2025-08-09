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
   bytes = "\u0006\u0005\u0005\u0015d!B\r\u001b\u0001z!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u00111\u0003!\u0011#Q\u0001\n\u0005C\u0001\"\u0014\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0003\")q\n\u0001C\u0001!\"9A\u000bAA\u0001\n\u0003)\u0006b\u0002-\u0001#\u0003%\t!\u0017\u0005\bI\u0002\t\n\u0011\"\u0001Z\u0011\u001d)\u0007!!A\u0005B\u0019DqA\u001c\u0001\u0002\u0002\u0013\u0005q\u000eC\u0004t\u0001\u0005\u0005I\u0011\u0001;\t\u000fi\u0004\u0011\u0011!C!w\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0011q\u0001\u0005\n\u0003#\u0001\u0011\u0011!C!\u0003'A\u0011\"a\u0006\u0001\u0003\u0003%\t%!\u0007\t\u0013\u0005m\u0001!!A\u0005B\u0005u\u0001\"CA\u0010\u0001\u0005\u0005I\u0011IA\u0011\u000f)\t)CGA\u0001\u0012\u0003q\u0012q\u0005\u0004\n3i\t\t\u0011#\u0001\u001f\u0003SAaaT\n\u0005\u0002\u0005\u0005\u0003\"CA\u000e'\u0005\u0005IQIA\u000f\u0011%\t\u0019eEA\u0001\n\u0003\u000b)\u0005C\u0005\u0002LM\t\t\u0011\"!\u0002N!I\u00111L\n\u0002\u0002\u0013%\u0011Q\f\u0002\u001d\u0017V\u0014WM\u001d8fi\u0016\u001cX)\u001c9us\u0012K'OV8mk6,7i\u001c8g\u0015\tYB$A\u0002lqMT!!\b\u0010\u0002\r\u0011,\u0007\u000f\\8z\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7#\u0002\u0001&W=\u0012\u0004C\u0001\u0014*\u001b\u00059#\"\u0001\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005):#AB!osJ+g\r\u0005\u0002-[5\t!$\u0003\u0002/5\ta2*\u001e2fe:,G/Z:W_2,X.Z*qK\u000eLg-[2D_:4\u0007C\u0001\u00141\u0013\t\ttEA\u0004Qe>$Wo\u0019;\u0011\u0005MbdB\u0001\u001b;\u001d\t)\u0014(D\u00017\u0015\t9\u0004(\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005A\u0013BA\u001e(\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005m:\u0013AB7fI&,X.F\u0001B!\r1#\tR\u0005\u0003\u0007\u001e\u0012aa\u00149uS>t\u0007CA#J\u001d\t1u\t\u0005\u00026O%\u0011\u0001jJ\u0001\u0007!J,G-\u001a4\n\u0005)[%AB*ue&twM\u0003\u0002IO\u00059Q.\u001a3jk6\u0004\u0013!C:ju\u0016d\u0015.\\5u\u0003)\u0019\u0018N_3MS6LG\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007E\u00136\u000b\u0005\u0002-\u0001!)q(\u0002a\u0001\u0003\")Q*\u0002a\u0001\u0003\u0006!1m\u001c9z)\r\tfk\u0016\u0005\b\u007f\u0019\u0001\n\u00111\u0001B\u0011\u001die\u0001%AA\u0002\u0005\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001[U\t\t5lK\u0001]!\ti&-D\u0001_\u0015\ty\u0006-A\u0005v]\u000eDWmY6fI*\u0011\u0011mJ\u0001\u000bC:tw\u000e^1uS>t\u0017BA2_\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\r\u0005\u0002i[6\t\u0011N\u0003\u0002kW\u0006!A.\u00198h\u0015\u0005a\u0017\u0001\u00026bm\u0006L!AS5\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003A\u0004\"AJ9\n\u0005I<#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA;y!\t1c/\u0003\u0002xO\t\u0019\u0011I\\=\t\u000fe\\\u0011\u0011!a\u0001a\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012\u0001 \t\u0005{\u0006\u0005Q/D\u0001\u007f\u0015\tyx%\u0001\u0006d_2dWm\u0019;j_:L1!a\u0001\u007f\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005%\u0011q\u0002\t\u0004M\u0005-\u0011bAA\u0007O\t9!i\\8mK\u0006t\u0007bB=\u000e\u0003\u0003\u0005\r!^\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002h\u0003+Aq!\u001f\b\u0002\u0002\u0003\u0007\u0001/\u0001\u0005iCND7i\u001c3f)\u0005\u0001\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u001d\fa!Z9vC2\u001cH\u0003BA\u0005\u0003GAq!_\t\u0002\u0002\u0003\u0007Q/\u0001\u000fLk\n,'O\\3uKN,U\u000e\u001d;z\t&\u0014hk\u001c7v[\u0016\u001cuN\u001c4\u0011\u00051\u001a2#B\n\u0002,\u0005]\u0002cBA\u0017\u0003g\t\u0015)U\u0007\u0003\u0003_Q1!!\r(\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u000e\u00020\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005e\u0012qH\u0007\u0003\u0003wQ1!!\u0010l\u0003\tIw.C\u0002>\u0003w!\"!a\n\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000bE\u000b9%!\u0013\t\u000b}2\u0002\u0019A!\t\u000b53\u0002\u0019A!\u0002\u000fUt\u0017\r\u001d9msR!\u0011qJA,!\u00111#)!\u0015\u0011\u000b\u0019\n\u0019&Q!\n\u0007\u0005UsE\u0001\u0004UkBdWM\r\u0005\t\u00033:\u0012\u0011!a\u0001#\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0003c\u00015\u0002b%\u0019\u00111M5\u0003\r=\u0013'.Z2u\u0001"
)
public class KubernetesEmptyDirVolumeConf implements KubernetesVolumeSpecificConf, Product, Serializable {
   private final Option medium;
   private final Option sizeLimit;

   public static Option unapply(final KubernetesEmptyDirVolumeConf x$0) {
      return KubernetesEmptyDirVolumeConf$.MODULE$.unapply(x$0);
   }

   public static KubernetesEmptyDirVolumeConf apply(final Option medium, final Option sizeLimit) {
      return KubernetesEmptyDirVolumeConf$.MODULE$.apply(medium, sizeLimit);
   }

   public static Function1 tupled() {
      return KubernetesEmptyDirVolumeConf$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return KubernetesEmptyDirVolumeConf$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Option medium() {
      return this.medium;
   }

   public Option sizeLimit() {
      return this.sizeLimit;
   }

   public KubernetesEmptyDirVolumeConf copy(final Option medium, final Option sizeLimit) {
      return new KubernetesEmptyDirVolumeConf(medium, sizeLimit);
   }

   public Option copy$default$1() {
      return this.medium();
   }

   public Option copy$default$2() {
      return this.sizeLimit();
   }

   public String productPrefix() {
      return "KubernetesEmptyDirVolumeConf";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.medium();
         }
         case 1 -> {
            return this.sizeLimit();
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
      return x$1 instanceof KubernetesEmptyDirVolumeConf;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "medium";
         }
         case 1 -> {
            return "sizeLimit";
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
            if (x$1 instanceof KubernetesEmptyDirVolumeConf) {
               label48: {
                  KubernetesEmptyDirVolumeConf var4 = (KubernetesEmptyDirVolumeConf)x$1;
                  Option var10000 = this.medium();
                  Option var5 = var4.medium();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.sizeLimit();
                  Option var6 = var4.sizeLimit();
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

   public KubernetesEmptyDirVolumeConf(final Option medium, final Option sizeLimit) {
      this.medium = medium;
      this.sizeLimit = sizeLimit;
      Product.$init$(this);
   }
}
