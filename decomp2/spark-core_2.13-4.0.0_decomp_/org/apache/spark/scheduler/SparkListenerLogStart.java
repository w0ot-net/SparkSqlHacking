package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc\u0001\u0002\f\u0018\u0001\u0002B\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\t\u0002\u0011\t\u0012)A\u0005y!)Q\t\u0001C\u0001\r\"9\u0011\nAA\u0001\n\u0003Q\u0005b\u0002'\u0001#\u0003%\t!\u0014\u0005\b1\u0002\t\t\u0011\"\u0011Z\u0011\u001d\t\u0007!!A\u0005\u0002\tDqA\u001a\u0001\u0002\u0002\u0013\u0005q\rC\u0004n\u0001\u0005\u0005I\u0011\t8\t\u000fU\u0004\u0011\u0011!C\u0001m\"91\u0010AA\u0001\n\u0003b\bb\u0002@\u0001\u0003\u0003%\te \u0005\n\u0003\u0003\u0001\u0011\u0011!C!\u0003\u0007A\u0011\"!\u0002\u0001\u0003\u0003%\t%a\u0002\b\u0013\u0005]q#!A\t\u0002\u0005ea\u0001\u0003\f\u0018\u0003\u0003E\t!a\u0007\t\r\u0015\u0003B\u0011AA\u001a\u0011%\t\t\u0001EA\u0001\n\u000b\n\u0019\u0001C\u0005\u00026A\t\t\u0011\"!\u00028!I\u00111\b\t\u0002\u0002\u0013\u0005\u0015Q\b\u0005\n\u0003\u0013\u0002\u0012\u0011!C\u0005\u0003\u0017\u0012Qc\u00159be.d\u0015n\u001d;f]\u0016\u0014Hj\\4Ti\u0006\u0014HO\u0003\u0002\u00193\u0005I1o\u00195fIVdWM\u001d\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sO\u000e\u00011#\u0002\u0001\"O-r\u0003C\u0001\u0012&\u001b\u0005\u0019#\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a#AB!osJ+g\r\u0005\u0002)S5\tq#\u0003\u0002+/\t\u00112\u000b]1sW2K7\u000f^3oKJ,e/\u001a8u!\t\u0011C&\u0003\u0002.G\t9\u0001K]8ek\u000e$\bCA\u00188\u001d\t\u0001TG\u0004\u00022i5\t!G\u0003\u00024?\u00051AH]8pizJ\u0011\u0001J\u0005\u0003m\r\nq\u0001]1dW\u0006<W-\u0003\u00029s\ta1+\u001a:jC2L'0\u00192mK*\u0011agI\u0001\rgB\f'o\u001b,feNLwN\\\u000b\u0002yA\u0011Q(\u0011\b\u0003}}\u0002\"!M\u0012\n\u0005\u0001\u001b\u0013A\u0002)sK\u0012,g-\u0003\u0002C\u0007\n11\u000b\u001e:j]\u001eT!\u0001Q\u0012\u0002\u001bM\u0004\u0018M]6WKJ\u001c\u0018n\u001c8!\u0003\u0019a\u0014N\\5u}Q\u0011q\t\u0013\t\u0003Q\u0001AQAO\u0002A\u0002q\nAaY8qsR\u0011qi\u0013\u0005\bu\u0011\u0001\n\u00111\u0001=\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u0014\u0016\u0003y=[\u0013\u0001\u0015\t\u0003#Zk\u0011A\u0015\u0006\u0003'R\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005U\u001b\u0013AC1o]>$\u0018\r^5p]&\u0011qK\u0015\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001[!\tY\u0006-D\u0001]\u0015\tif,\u0001\u0003mC:<'\"A0\u0002\t)\fg/Y\u0005\u0003\u0005r\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012a\u0019\t\u0003E\u0011L!!Z\u0012\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005!\\\u0007C\u0001\u0012j\u0013\tQ7EA\u0002B]fDq\u0001\u001c\u0005\u0002\u0002\u0003\u00071-A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002_B\u0019\u0001o\u001d5\u000e\u0003ET!A]\u0012\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002uc\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t9(\u0010\u0005\u0002#q&\u0011\u0011p\t\u0002\b\u0005>|G.Z1o\u0011\u001da'\"!AA\u0002!\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011!, \u0005\bY.\t\t\u00111\u0001d\u0003!A\u0017m\u001d5D_\u0012,G#A2\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012AW\u0001\u0007KF,\u0018\r\\:\u0015\u0007]\fI\u0001C\u0004m\u001d\u0005\u0005\t\u0019\u00015)\u0007\u0001\ti\u0001\u0005\u0003\u0002\u0010\u0005MQBAA\t\u0015\t)\u0016$\u0003\u0003\u0002\u0016\u0005E!\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017!F*qCJ\\G*[:uK:,'\u000fT8h'R\f'\u000f\u001e\t\u0003QA\u0019R\u0001EA\u000f\u0003S\u0001b!a\b\u0002&q:UBAA\u0011\u0015\r\t\u0019cI\u0001\beVtG/[7f\u0013\u0011\t9#!\t\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002,\u0005ERBAA\u0017\u0015\r\tyCX\u0001\u0003S>L1\u0001OA\u0017)\t\tI\"A\u0003baBd\u0017\u0010F\u0002H\u0003sAQAO\nA\u0002q\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002@\u0005\u0015\u0003\u0003\u0002\u0012\u0002BqJ1!a\u0011$\u0005\u0019y\u0005\u000f^5p]\"A\u0011q\t\u000b\u0002\u0002\u0003\u0007q)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0014\u0011\u0007m\u000by%C\u0002\u0002Rq\u0013aa\u00142kK\u000e$\b"
)
public class SparkListenerLogStart implements SparkListenerEvent, Product, Serializable {
   private final String sparkVersion;

   public static Option unapply(final SparkListenerLogStart x$0) {
      return SparkListenerLogStart$.MODULE$.unapply(x$0);
   }

   public static SparkListenerLogStart apply(final String sparkVersion) {
      return SparkListenerLogStart$.MODULE$.apply(sparkVersion);
   }

   public static Function1 andThen(final Function1 g) {
      return SparkListenerLogStart$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SparkListenerLogStart$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public String sparkVersion() {
      return this.sparkVersion;
   }

   public SparkListenerLogStart copy(final String sparkVersion) {
      return new SparkListenerLogStart(sparkVersion);
   }

   public String copy$default$1() {
      return this.sparkVersion();
   }

   public String productPrefix() {
      return "SparkListenerLogStart";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.sparkVersion();
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
      return x$1 instanceof SparkListenerLogStart;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "sparkVersion";
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
            if (x$1 instanceof SparkListenerLogStart) {
               label40: {
                  SparkListenerLogStart var4 = (SparkListenerLogStart)x$1;
                  String var10000 = this.sparkVersion();
                  String var5 = var4.sparkVersion();
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

   public SparkListenerLogStart(final String sparkVersion) {
      this.sparkVersion = sparkVersion;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
