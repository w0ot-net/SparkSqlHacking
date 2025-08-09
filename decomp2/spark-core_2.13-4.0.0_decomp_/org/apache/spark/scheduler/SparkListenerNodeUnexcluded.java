package org.apache.spark.scheduler;

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
   bytes = "\u0006\u0005\u0005\re\u0001B\r\u001b\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005N\u0001\tE\t\u0015!\u0003F\u0011\u0015q\u0005\u0001\"\u0001P\u0011\u001d\u0019\u0006!!A\u0005\u0002QCqa\u0016\u0001\u0012\u0002\u0013\u0005\u0001\fC\u0004d\u0001E\u0005I\u0011\u00013\t\u000f\u0019\u0004\u0011\u0011!C!O\"9q\u000eAA\u0001\n\u0003\u0001\bb\u0002;\u0001\u0003\u0003%\t!\u001e\u0005\bw\u0002\t\t\u0011\"\u0011}\u0011%\t9\u0001AA\u0001\n\u0003\tI\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131\u0004\u0005\n\u0003;\u0001\u0011\u0011!C!\u0003?A\u0011\"!\t\u0001\u0003\u0003%\t%a\t\b\u0013\u0005}\"$!A\t\u0002\u0005\u0005c\u0001C\r\u001b\u0003\u0003E\t!a\u0011\t\r9\u001bB\u0011AA.\u0011%\tibEA\u0001\n\u000b\ny\u0002C\u0005\u0002^M\t\t\u0011\"!\u0002`!I\u0011QM\n\u0002\u0002\u0013\u0005\u0015q\r\u0005\n\u0003s\u001a\u0012\u0011!C\u0005\u0003w\u00121d\u00159be.d\u0015n\u001d;f]\u0016\u0014hj\u001c3f+:,\u0007p\u00197vI\u0016$'BA\u000e\u001d\u0003%\u00198\r[3ek2,'O\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h\u0007\u0001\u0019R\u0001\u0001\u0013+]E\u0002\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012a!\u00118z%\u00164\u0007CA\u0016-\u001b\u0005Q\u0012BA\u0017\u001b\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u0005\u0015z\u0013B\u0001\u0019'\u0005\u001d\u0001&o\u001c3vGR\u0004\"A\r\u001e\u000f\u0005MBdB\u0001\u001b8\u001b\u0005)$B\u0001\u001c#\u0003\u0019a$o\\8u}%\tq%\u0003\u0002:M\u00059\u0001/Y2lC\u001e,\u0017BA\u001e=\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tId%\u0001\u0003uS6,W#A \u0011\u0005\u0015\u0002\u0015BA!'\u0005\u0011auN\\4\u0002\u000bQLW.\u001a\u0011\u0002\r!|7\u000f^%e+\u0005)\u0005C\u0001$K\u001d\t9\u0005\n\u0005\u00025M%\u0011\u0011JJ\u0001\u0007!J,G-\u001a4\n\u0005-c%AB*ue&twM\u0003\u0002JM\u00059\u0001n\\:u\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0002Q#J\u0003\"a\u000b\u0001\t\u000bu*\u0001\u0019A \t\u000b\r+\u0001\u0019A#\u0002\t\r|\u0007/\u001f\u000b\u0004!V3\u0006bB\u001f\u0007!\u0003\u0005\ra\u0010\u0005\b\u0007\u001a\u0001\n\u00111\u0001F\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0017\u0016\u0003\u007fi[\u0013a\u0017\t\u00039\u0006l\u0011!\u0018\u0006\u0003=~\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u00014\u0013AC1o]>$\u0018\r^5p]&\u0011!-\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002K*\u0012QIW\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003!\u0004\"!\u001b8\u000e\u0003)T!a\u001b7\u0002\t1\fgn\u001a\u0006\u0002[\u0006!!.\u0019<b\u0013\tY%.\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001r!\t)#/\u0003\u0002tM\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011a/\u001f\t\u0003K]L!\u0001\u001f\u0014\u0003\u0007\u0005s\u0017\u0010C\u0004{\u0017\u0005\u0005\t\u0019A9\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005i\b\u0003\u0002@\u0002\u0004Yl\u0011a \u0006\u0004\u0003\u00031\u0013AC2pY2,7\r^5p]&\u0019\u0011QA@\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0017\t\t\u0002E\u0002&\u0003\u001bI1!a\u0004'\u0005\u001d\u0011un\u001c7fC:DqA_\u0007\u0002\u0002\u0003\u0007a/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00015\u0002\u0018!9!PDA\u0001\u0002\u0004\t\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003E\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002Q\u00061Q-];bYN$B!a\u0003\u0002&!9!0EA\u0001\u0002\u00041\bf\u0001\u0001\u0002*A!\u00111FA\u0018\u001b\t\tiC\u0003\u0002a9%!\u0011\u0011GA\u0017\u00051!UM^3m_B,'/\u00119jQ\u0015\u0001\u0011QGA\u001e!\u0011\tY#a\u000e\n\t\u0005e\u0012Q\u0006\u0002\u0006'&t7-Z\u0011\u0003\u0003{\tQa\r\u00182]A\n1d\u00159be.d\u0015n\u001d;f]\u0016\u0014hj\u001c3f+:,\u0007p\u00197vI\u0016$\u0007CA\u0016\u0014'\u0015\u0019\u0012QIA)!\u001d\t9%!\u0014@\u000bBk!!!\u0013\u000b\u0007\u0005-c%A\u0004sk:$\u0018.\\3\n\t\u0005=\u0013\u0011\n\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA*\u00033j!!!\u0016\u000b\u0007\u0005]C.\u0001\u0002j_&\u00191(!\u0016\u0015\u0005\u0005\u0005\u0013!B1qa2LH#\u0002)\u0002b\u0005\r\u0004\"B\u001f\u0017\u0001\u0004y\u0004\"B\"\u0017\u0001\u0004)\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003S\n)\bE\u0003&\u0003W\ny'C\u0002\u0002n\u0019\u0012aa\u00149uS>t\u0007#B\u0013\u0002r}*\u0015bAA:M\t1A+\u001e9mKJB\u0001\"a\u001e\u0018\u0003\u0003\u0005\r\u0001U\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA?!\rI\u0017qP\u0005\u0004\u0003\u0003S'AB(cU\u0016\u001cG\u000f"
)
public class SparkListenerNodeUnexcluded implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String hostId;

   public static Option unapply(final SparkListenerNodeUnexcluded x$0) {
      return SparkListenerNodeUnexcluded$.MODULE$.unapply(x$0);
   }

   public static SparkListenerNodeUnexcluded apply(final long time, final String hostId) {
      return SparkListenerNodeUnexcluded$.MODULE$.apply(time, hostId);
   }

   public static Function1 tupled() {
      return SparkListenerNodeUnexcluded$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerNodeUnexcluded$.MODULE$.curried();
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

   public String hostId() {
      return this.hostId;
   }

   public SparkListenerNodeUnexcluded copy(final long time, final String hostId) {
      return new SparkListenerNodeUnexcluded(time, hostId);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.hostId();
   }

   public String productPrefix() {
      return "SparkListenerNodeUnexcluded";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 1 -> {
            return this.hostId();
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
      return x$1 instanceof SparkListenerNodeUnexcluded;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "hostId";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.hostId()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof SparkListenerNodeUnexcluded) {
               SparkListenerNodeUnexcluded var4 = (SparkListenerNodeUnexcluded)x$1;
               if (this.time() == var4.time()) {
                  label44: {
                     String var10000 = this.hostId();
                     String var5 = var4.hostId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
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

   public SparkListenerNodeUnexcluded(final long time, final String hostId) {
      this.time = time;
      this.hostId = hostId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
