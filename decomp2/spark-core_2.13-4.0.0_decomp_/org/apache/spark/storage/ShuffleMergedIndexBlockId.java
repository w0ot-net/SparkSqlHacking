package org.apache.spark.storage;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005me\u0001B\u0010!\u0001&B\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u0005\"A1\n\u0001BK\u0002\u0013\u0005A\n\u0003\u0005Q\u0001\tE\t\u0015!\u0003N\u0011!\t\u0006A!f\u0001\n\u0003a\u0005\u0002\u0003*\u0001\u0005#\u0005\u000b\u0011B'\t\u0011M\u0003!Q3A\u0005\u00021C\u0001\u0002\u0016\u0001\u0003\u0012\u0003\u0006I!\u0014\u0005\u0006+\u0002!\tA\u0016\u0005\u00069\u0002!\t%\u0011\u0005\b;\u0002\t\t\u0011\"\u0001_\u0011\u001d\u0019\u0007!%A\u0005\u0002\u0011Dqa\u001c\u0001\u0012\u0002\u0013\u0005\u0001\u000fC\u0004s\u0001E\u0005I\u0011\u00019\t\u000fM\u0004\u0011\u0013!C\u0001a\"9A\u000fAA\u0001\n\u0003*\bbB?\u0001\u0003\u0003%\t\u0001\u0014\u0005\b}\u0002\t\t\u0011\"\u0001\u0000\u0011%\tY\u0001AA\u0001\n\u0003\ni\u0001C\u0005\u0002\u001c\u0001\t\t\u0011\"\u0001\u0002\u001e!I\u0011q\u0005\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0006\u0005\n\u0003[\u0001\u0011\u0011!C!\u0003_A\u0011\"!\r\u0001\u0003\u0003%\t%a\r\b\u0013\u0005=\u0003%!A\t\u0002\u0005Ec\u0001C\u0010!\u0003\u0003E\t!a\u0015\t\rUKB\u0011AA6\u0011%\ti'GA\u0001\n\u000b\ny\u0007C\u0005\u0002re\t\t\u0011\"!\u0002t!I\u0011QP\r\u0002\u0002\u0013\u0005\u0015q\u0010\u0005\n\u0003#K\u0012\u0011!C\u0005\u0003'\u0013\u0011d\u00155vM\u001adW-T3sO\u0016$\u0017J\u001c3fq\ncwnY6JI*\u0011\u0011EI\u0001\bgR|'/Y4f\u0015\t\u0019C%A\u0003ta\u0006\u00148N\u0003\u0002&M\u00051\u0011\r]1dQ\u0016T\u0011aJ\u0001\u0004_J<7\u0001A\n\u0005\u0001)rC\u0007\u0005\u0002,Y5\t\u0001%\u0003\u0002.A\t9!\t\\8dW&#\u0007CA\u00183\u001b\u0005\u0001$\"A\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0002$a\u0002)s_\u0012,8\r\u001e\t\u0003kur!AN\u001e\u000f\u0005]RT\"\u0001\u001d\u000b\u0005eB\u0013A\u0002\u001fs_>$h(C\u00012\u0013\ta\u0004'A\u0004qC\u000e\\\u0017mZ3\n\u0005yz$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001f1\u0003\u0015\t\u0007\u000f]%e+\u0005\u0011\u0005CA\"H\u001d\t!U\t\u0005\u00028a%\u0011a\tM\u0001\u0007!J,G-\u001a4\n\u0005!K%AB*ue&twM\u0003\u0002Ga\u00051\u0011\r\u001d9JI\u0002\n\u0011b\u001d5vM\u001adW-\u00133\u0016\u00035\u0003\"a\f(\n\u0005=\u0003$aA%oi\u0006Q1\u000f[;gM2,\u0017\n\u001a\u0011\u0002\u001dMDWO\u001a4mK6+'oZ3JI\u0006y1\u000f[;gM2,W*\u001a:hK&#\u0007%\u0001\u0005sK\u0012,8-Z%e\u0003%\u0011X\rZ;dK&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0006/bK&l\u0017\t\u0003W\u0001AQ\u0001Q\u0005A\u0002\tCQaS\u0005A\u00025CQ!U\u0005A\u00025CQaU\u0005A\u00025\u000bAA\\1nK\u0006!1m\u001c9z)\u00159v\fY1c\u0011\u001d\u00015\u0002%AA\u0002\tCqaS\u0006\u0011\u0002\u0003\u0007Q\nC\u0004R\u0017A\u0005\t\u0019A'\t\u000fM[\u0001\u0013!a\u0001\u001b\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A3+\u0005\t37&A4\u0011\u0005!lW\"A5\u000b\u0005)\\\u0017!C;oG\",7m[3e\u0015\ta\u0007'\u0001\u0006b]:|G/\u0019;j_:L!A\\5\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003ET#!\u00144\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001w!\t9H0D\u0001y\u0015\tI(0\u0001\u0003mC:<'\"A>\u0002\t)\fg/Y\u0005\u0003\u0011b\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u0002\u0005\u001d\u0001cA\u0018\u0002\u0004%\u0019\u0011Q\u0001\u0019\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002\nI\t\t\u00111\u0001N\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u0002\t\u0007\u0003#\t9\"!\u0001\u000e\u0005\u0005M!bAA\u000ba\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005e\u00111\u0003\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002 \u0005\u0015\u0002cA\u0018\u0002\"%\u0019\u00111\u0005\u0019\u0003\u000f\t{w\u000e\\3b]\"I\u0011\u0011\u0002\u000b\u0002\u0002\u0003\u0007\u0011\u0011A\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002w\u0003WA\u0001\"!\u0003\u0016\u0003\u0003\u0005\r!T\u0001\tQ\u0006\u001c\bnQ8eKR\tQ*\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003?\t)\u0004C\u0005\u0002\n]\t\t\u00111\u0001\u0002\u0002!*\u0001!!\u000f\u0002DA!\u00111HA \u001b\t\tiD\u0003\u0002mE%!\u0011\u0011IA\u001f\u0005\u0015\u0019\u0016N\\2fC\t\t)%A\u00034]Ir\u0003\u0007K\u0002\u0001\u0003\u0013\u0002B!a\u000f\u0002L%!\u0011QJA\u001f\u00051!UM^3m_B,'/\u00119j\u0003e\u0019\u0006.\u001e4gY\u0016lUM]4fI&sG-\u001a=CY>\u001c7.\u00133\u0011\u0005-J2#B\r\u0002V\u0005\u0005\u0004#CA,\u0003;\u0012U*T'X\u001b\t\tIFC\u0002\u0002\\A\nqA];oi&lW-\u0003\u0003\u0002`\u0005e#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oiA!\u00111MA5\u001b\t\t)GC\u0002\u0002hi\f!![8\n\u0007y\n)\u0007\u0006\u0002\u0002R\u0005AAo\\*ue&tw\rF\u0001w\u0003\u0015\t\u0007\u000f\u001d7z)%9\u0016QOA<\u0003s\nY\bC\u0003A9\u0001\u0007!\tC\u0003L9\u0001\u0007Q\nC\u0003R9\u0001\u0007Q\nC\u0003T9\u0001\u0007Q*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0005\u0015Q\u0012\t\u0006_\u0005\r\u0015qQ\u0005\u0004\u0003\u000b\u0003$AB(qi&|g\u000eE\u00040\u0003\u0013\u0013U*T'\n\u0007\u0005-\u0005G\u0001\u0004UkBdW\r\u000e\u0005\t\u0003\u001fk\u0012\u0011!a\u0001/\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005U\u0005cA<\u0002\u0018&\u0019\u0011\u0011\u0014=\u0003\r=\u0013'.Z2u\u0001"
)
public class ShuffleMergedIndexBlockId extends BlockId implements Product, Serializable {
   private final String appId;
   private final int shuffleId;
   private final int shuffleMergeId;
   private final int reduceId;

   public static Option unapply(final ShuffleMergedIndexBlockId x$0) {
      return ShuffleMergedIndexBlockId$.MODULE$.unapply(x$0);
   }

   public static ShuffleMergedIndexBlockId apply(final String appId, final int shuffleId, final int shuffleMergeId, final int reduceId) {
      return ShuffleMergedIndexBlockId$.MODULE$.apply(appId, shuffleId, shuffleMergeId, reduceId);
   }

   public static Function1 tupled() {
      return ShuffleMergedIndexBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShuffleMergedIndexBlockId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String appId() {
      return this.appId;
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public int shuffleMergeId() {
      return this.shuffleMergeId;
   }

   public int reduceId() {
      return this.reduceId;
   }

   public String name() {
      String var10000 = this.appId();
      return "shuffleMerged_" + var10000 + "_" + this.shuffleId() + "_" + this.shuffleMergeId() + "_" + this.reduceId() + ".index";
   }

   public ShuffleMergedIndexBlockId copy(final String appId, final int shuffleId, final int shuffleMergeId, final int reduceId) {
      return new ShuffleMergedIndexBlockId(appId, shuffleId, shuffleMergeId, reduceId);
   }

   public String copy$default$1() {
      return this.appId();
   }

   public int copy$default$2() {
      return this.shuffleId();
   }

   public int copy$default$3() {
      return this.shuffleMergeId();
   }

   public int copy$default$4() {
      return this.reduceId();
   }

   public String productPrefix() {
      return "ShuffleMergedIndexBlockId";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.appId();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.shuffleMergeId());
         }
         case 3 -> {
            return BoxesRunTime.boxToInteger(this.reduceId());
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
      return x$1 instanceof ShuffleMergedIndexBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "appId";
         }
         case 1 -> {
            return "shuffleId";
         }
         case 2 -> {
            return "shuffleMergeId";
         }
         case 3 -> {
            return "reduceId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.appId()));
      var1 = Statics.mix(var1, this.shuffleId());
      var1 = Statics.mix(var1, this.shuffleMergeId());
      var1 = Statics.mix(var1, this.reduceId());
      return Statics.finalizeHash(var1, 4);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof ShuffleMergedIndexBlockId) {
               ShuffleMergedIndexBlockId var4 = (ShuffleMergedIndexBlockId)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.shuffleMergeId() == var4.shuffleMergeId() && this.reduceId() == var4.reduceId()) {
                  label52: {
                     String var10000 = this.appId();
                     String var5 = var4.appId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
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

   public ShuffleMergedIndexBlockId(final String appId, final int shuffleId, final int shuffleMergeId, final int reduceId) {
      this.appId = appId;
      this.shuffleId = shuffleId;
      this.shuffleMergeId = shuffleMergeId;
      this.reduceId = reduceId;
      Product.$init$(this);
   }
}
