package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.storage.BlockManagerId;
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
   bytes = "\u0006\u0005\u0005\rg\u0001\u0002\u0014(\u0001BB\u0001B\u0013\u0001\u0003\u0016\u0004%\ta\u0013\u0005\t\u001f\u0002\u0011\t\u0012)A\u0005\u0019\"A\u0001\u000b\u0001BK\u0002\u0013\u0005\u0011\u000b\u0003\u0005Y\u0001\tE\t\u0015!\u0003S\u0011!I\u0006A!f\u0001\n\u0003Y\u0005\u0002\u0003.\u0001\u0005#\u0005\u000b\u0011\u0002'\t\u0011m\u0003!Q3A\u0005\u0002qC\u0001\u0002\u0019\u0001\u0003\u0012\u0003\u0006I!\u0018\u0005\tC\u0002\u0011)\u001a!C\u00019\"A!\r\u0001B\tB\u0003%Q\fC\u0003d\u0001\u0011\u0005A\rC\u0004l\u0001\u0005\u0005I\u0011\u00017\t\u000fI\u0004\u0011\u0013!C\u0001g\"9a\u0010AI\u0001\n\u0003y\b\u0002CA\u0002\u0001E\u0005I\u0011A:\t\u0013\u0005\u0015\u0001!%A\u0005\u0002\u0005\u001d\u0001\"CA\u0006\u0001E\u0005I\u0011AA\u0004\u0011%\ti\u0001AA\u0001\n\u0003\ny\u0001C\u0005\u0002\"\u0001\t\t\u0011\"\u0001\u0002$!I\u00111\u0006\u0001\u0002\u0002\u0013\u0005\u0011Q\u0006\u0005\n\u0003s\u0001\u0011\u0011!C!\u0003wA\u0011\"!\u0013\u0001\u0003\u0003%\t!a\u0013\t\u0013\u0005U\u0003!!A\u0005B\u0005]\u0003\"CA.\u0001\u0005\u0005I\u0011IA/\u0011%\ty\u0006AA\u0001\n\u0003\n\t\u0007C\u0005\u0002d\u0001\t\t\u0011\"\u0011\u0002f\u001dI\u0011QO\u0014\u0002\u0002#\u0005\u0011q\u000f\u0004\tM\u001d\n\t\u0011#\u0001\u0002z!11\r\bC\u0001\u0003#C\u0011\"a\u0018\u001d\u0003\u0003%)%!\u0019\t\u0013\u0005ME$!A\u0005\u0002\u0006U\u0005\"CAQ9E\u0005I\u0011AA\u0004\u0011%\t\u0019\u000bHI\u0001\n\u0003\t9\u0001C\u0005\u0002&r\t\t\u0011\"!\u0002(\"I\u0011Q\u0017\u000f\u0012\u0002\u0013\u0005\u0011q\u0001\u0005\n\u0003oc\u0012\u0013!C\u0001\u0003\u000fA\u0011\"!/\u001d\u0003\u0003%I!a/\u0003=M\u0003\u0018M]6MSN$XM\\3s\u00052|7m['b]\u0006<WM]!eI\u0016$'B\u0001\u0015*\u0003%\u00198\r[3ek2,'O\u0003\u0002+W\u0005)1\u000f]1sW*\u0011A&L\u0001\u0007CB\f7\r[3\u000b\u00039\n1a\u001c:h\u0007\u0001\u0019R\u0001A\u00198wy\u0002\"AM\u001b\u000e\u0003MR\u0011\u0001N\u0001\u0006g\u000e\fG.Y\u0005\u0003mM\u0012a!\u00118z%\u00164\u0007C\u0001\u001d:\u001b\u00059\u0013B\u0001\u001e(\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u0005Ib\u0014BA\u001f4\u0005\u001d\u0001&o\u001c3vGR\u0004\"aP$\u000f\u0005\u0001+eBA!E\u001b\u0005\u0011%BA\"0\u0003\u0019a$o\\8u}%\tA'\u0003\u0002Gg\u00059\u0001/Y2lC\u001e,\u0017B\u0001%J\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t15'\u0001\u0003uS6,W#\u0001'\u0011\u0005Ij\u0015B\u0001(4\u0005\u0011auN\\4\u0002\u000bQLW.\u001a\u0011\u0002\u001d\tdwnY6NC:\fw-\u001a:JIV\t!\u000b\u0005\u0002T-6\tAK\u0003\u0002VS\u000591\u000f^8sC\u001e,\u0017BA,U\u00059\u0011En\\2l\u001b\u0006t\u0017mZ3s\u0013\u0012\fqB\u00197pG.l\u0015M\\1hKJLE\rI\u0001\u0007[\u0006DX*Z7\u0002\u000f5\f\u00070T3nA\u0005aQ.\u0019=P]\"+\u0017\r]'f[V\tQ\fE\u00023=2K!aX\u001a\u0003\r=\u0003H/[8o\u00035i\u0017\r_(o\u0011\u0016\f\u0007/T3nA\u0005iQ.\u0019=PM\u001aDU-\u00199NK6\fa\"\\1y\u001f\u001a4\u0007*Z1q\u001b\u0016l\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0007K\u001a<\u0007.\u001b6\u0011\u0005a\u0002\u0001\"\u0002&\f\u0001\u0004a\u0005\"\u0002)\f\u0001\u0004\u0011\u0006\"B-\f\u0001\u0004a\u0005bB.\f!\u0003\u0005\r!\u0018\u0005\bC.\u0001\n\u00111\u0001^\u0003\u0011\u0019w\u000e]=\u0015\r\u0015lgn\u001c9r\u0011\u001dQE\u0002%AA\u00021Cq\u0001\u0015\u0007\u0011\u0002\u0003\u0007!\u000bC\u0004Z\u0019A\u0005\t\u0019\u0001'\t\u000fmc\u0001\u0013!a\u0001;\"9\u0011\r\u0004I\u0001\u0002\u0004i\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002i*\u0012A*^\u0016\u0002mB\u0011q\u000f`\u0007\u0002q*\u0011\u0011P_\u0001\nk:\u001c\u0007.Z2lK\u0012T!a_\u001a\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002~q\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011\u0011\u0001\u0016\u0003%V\fabY8qs\u0012\"WMZ1vYR$3'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005%!FA/v\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\t!\u0011\t\u0019\"!\b\u000e\u0005\u0005U!\u0002BA\f\u00033\tA\u0001\\1oO*\u0011\u00111D\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002 \u0005U!AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002&A\u0019!'a\n\n\u0007\u0005%2GA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u00020\u0005U\u0002c\u0001\u001a\u00022%\u0019\u00111G\u001a\u0003\u0007\u0005s\u0017\u0010C\u0005\u00028Q\t\t\u00111\u0001\u0002&\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0010\u0011\r\u0005}\u0012QIA\u0018\u001b\t\t\tEC\u0002\u0002DM\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9%!\u0011\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u001b\n\u0019\u0006E\u00023\u0003\u001fJ1!!\u00154\u0005\u001d\u0011un\u001c7fC:D\u0011\"a\u000e\u0017\u0003\u0003\u0005\r!a\f\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003#\tI\u0006C\u0005\u00028]\t\t\u00111\u0001\u0002&\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002&\u0005AAo\\*ue&tw\r\u0006\u0002\u0002\u0012\u00051Q-];bYN$B!!\u0014\u0002h!I\u0011q\u0007\u000e\u0002\u0002\u0003\u0007\u0011q\u0006\u0015\u0004\u0001\u0005-\u0004\u0003BA7\u0003cj!!a\u001c\u000b\u0005mL\u0013\u0002BA:\u0003_\u0012A\u0002R3wK2|\u0007/\u001a:Ba&\fad\u00159be.d\u0015n\u001d;f]\u0016\u0014(\t\\8dW6\u000bg.Y4fe\u0006#G-\u001a3\u0011\u0005ab2#\u0002\u000f\u0002|\u0005\u001d\u0005CCA?\u0003\u0007c%\u000bT/^K6\u0011\u0011q\u0010\u0006\u0004\u0003\u0003\u001b\u0014a\u0002:v]RLW.Z\u0005\u0005\u0003\u000b\u000byHA\tBEN$(/Y2u\rVt7\r^5p]V\u0002B!!#\u0002\u00106\u0011\u00111\u0012\u0006\u0005\u0003\u001b\u000bI\"\u0001\u0002j_&\u0019\u0001*a#\u0015\u0005\u0005]\u0014!B1qa2LHcC3\u0002\u0018\u0006e\u00151TAO\u0003?CQAS\u0010A\u00021CQ\u0001U\u0010A\u0002ICQ!W\u0010A\u00021CqaW\u0010\u0011\u0002\u0003\u0007Q\fC\u0004b?A\u0005\t\u0019A/\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIQ\nq\"\u00199qYf$C-\u001a4bk2$H%N\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tI+!-\u0011\tIr\u00161\u0016\t\te\u00055FJ\u0015'^;&\u0019\u0011qV\u001a\u0003\rQ+\b\u000f\\36\u0011!\t\u0019LIA\u0001\u0002\u0004)\u0017a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012*\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA_!\u0011\t\u0019\"a0\n\t\u0005\u0005\u0017Q\u0003\u0002\u0007\u001f\nTWm\u0019;"
)
public class SparkListenerBlockManagerAdded implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final BlockManagerId blockManagerId;
   private final long maxMem;
   private final Option maxOnHeapMem;
   private final Option maxOffHeapMem;

   public static Option $lessinit$greater$default$5() {
      return SparkListenerBlockManagerAdded$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return SparkListenerBlockManagerAdded$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option unapply(final SparkListenerBlockManagerAdded x$0) {
      return SparkListenerBlockManagerAdded$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$5() {
      return SparkListenerBlockManagerAdded$.MODULE$.apply$default$5();
   }

   public static Option apply$default$4() {
      return SparkListenerBlockManagerAdded$.MODULE$.apply$default$4();
   }

   public static SparkListenerBlockManagerAdded apply(final long time, final BlockManagerId blockManagerId, final long maxMem, final Option maxOnHeapMem, final Option maxOffHeapMem) {
      return SparkListenerBlockManagerAdded$.MODULE$.apply(time, blockManagerId, maxMem, maxOnHeapMem, maxOffHeapMem);
   }

   public static Function1 tupled() {
      return SparkListenerBlockManagerAdded$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerBlockManagerAdded$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public long time() {
      return this.time;
   }

   public BlockManagerId blockManagerId() {
      return this.blockManagerId;
   }

   public long maxMem() {
      return this.maxMem;
   }

   public Option maxOnHeapMem() {
      return this.maxOnHeapMem;
   }

   public Option maxOffHeapMem() {
      return this.maxOffHeapMem;
   }

   public SparkListenerBlockManagerAdded copy(final long time, final BlockManagerId blockManagerId, final long maxMem, final Option maxOnHeapMem, final Option maxOffHeapMem) {
      return new SparkListenerBlockManagerAdded(time, blockManagerId, maxMem, maxOnHeapMem, maxOffHeapMem);
   }

   public long copy$default$1() {
      return this.time();
   }

   public BlockManagerId copy$default$2() {
      return this.blockManagerId();
   }

   public long copy$default$3() {
      return this.maxMem();
   }

   public Option copy$default$4() {
      return this.maxOnHeapMem();
   }

   public Option copy$default$5() {
      return this.maxOffHeapMem();
   }

   public String productPrefix() {
      return "SparkListenerBlockManagerAdded";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 1 -> {
            return this.blockManagerId();
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.maxMem());
         }
         case 3 -> {
            return this.maxOnHeapMem();
         }
         case 4 -> {
            return this.maxOffHeapMem();
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
      return x$1 instanceof SparkListenerBlockManagerAdded;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "blockManagerId";
         }
         case 2 -> {
            return "maxMem";
         }
         case 3 -> {
            return "maxOnHeapMem";
         }
         case 4 -> {
            return "maxOffHeapMem";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.time()));
      var1 = Statics.mix(var1, Statics.anyHash(this.blockManagerId()));
      var1 = Statics.mix(var1, Statics.longHash(this.maxMem()));
      var1 = Statics.mix(var1, Statics.anyHash(this.maxOnHeapMem()));
      var1 = Statics.mix(var1, Statics.anyHash(this.maxOffHeapMem()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof SparkListenerBlockManagerAdded) {
               SparkListenerBlockManagerAdded var4 = (SparkListenerBlockManagerAdded)x$1;
               if (this.time() == var4.time() && this.maxMem() == var4.maxMem()) {
                  label64: {
                     BlockManagerId var10000 = this.blockManagerId();
                     BlockManagerId var5 = var4.blockManagerId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label64;
                     }

                     Option var8 = this.maxOnHeapMem();
                     Option var6 = var4.maxOnHeapMem();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label64;
                        }
                     } else if (!var8.equals(var6)) {
                        break label64;
                     }

                     var8 = this.maxOffHeapMem();
                     Option var7 = var4.maxOffHeapMem();
                     if (var8 == null) {
                        if (var7 != null) {
                           break label64;
                        }
                     } else if (!var8.equals(var7)) {
                        break label64;
                     }

                     if (var4.canEqual(this)) {
                        break label71;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public SparkListenerBlockManagerAdded(final long time, final BlockManagerId blockManagerId, final long maxMem, final Option maxOnHeapMem, final Option maxOffHeapMem) {
      this.time = time;
      this.blockManagerId = blockManagerId;
      this.maxMem = maxMem;
      this.maxOnHeapMem = maxOnHeapMem;
      this.maxOffHeapMem = maxOffHeapMem;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
