package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]e!\u0002\u0011\"\u0001\u000eZ\u0003\u0002\u0003\"\u0001\u0005+\u0007I\u0011A\"\t\u0011\u001d\u0003!\u0011#Q\u0001\n\u0011C\u0001\u0002\u0013\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\t\"A!\n\u0001BK\u0002\u0013\u00051\t\u0003\u0005L\u0001\tE\t\u0015!\u0003E\u0011!a\u0005A!f\u0001\n\u0003i\u0005\u0002\u0003+\u0001\u0005#\u0005\u000b\u0011\u0002(\t\u000bU\u0003A\u0011\u0001,\t\u000bu\u0003A\u0011\u00010\t\u000f\t\u0004\u0011\u0011!C\u0001G\"9\u0001\u000eAI\u0001\n\u0003I\u0007b\u0002;\u0001#\u0003%\t!\u001b\u0005\bk\u0002\t\n\u0011\"\u0001j\u0011\u001d1\b!%A\u0005\u0002]Dq!\u001f\u0001\u0002\u0002\u0013\u0005#\u0010C\u0005\u0002\b\u0001\t\t\u0011\"\u0001\u0002\n!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00111\u0003\u0005\n\u0003?\u0001\u0011\u0011!C!\u0003CA\u0011\"a\f\u0001\u0003\u0003%\t!!\r\t\u0013\u0005m\u0002!!A\u0005B\u0005u\u0002\"CA!\u0001\u0005\u0005I\u0011IA\"\u0011%\t)\u0005AA\u0001\n\u0003\n9\u0005C\u0005\u0002J\u0001\t\t\u0011\"\u0011\u0002L\u001dQ\u0011qJ\u0011\u0002\u0002#\u00051%!\u0015\u0007\u0013\u0001\n\u0013\u0011!E\u0001G\u0005M\u0003BB+\u001b\t\u0003\tY\u0007C\u0005\u0002Fi\t\t\u0011\"\u0012\u0002H!I\u0011Q\u000e\u000e\u0002\u0002\u0013\u0005\u0015q\u000e\u0005\n\u0003sR\u0012\u0011!CA\u0003wB\u0011\"!$\u001b\u0003\u0003%I!a$\u0003\u001d=3gm]3u\u0013:\u001cH/\u00198dK*\u0011!eI\u0001\bM\u0016\fG/\u001e:f\u0015\t!S%\u0001\u0002nY*\u0011aeJ\u0001\u0006gB\f'o\u001b\u0006\u0003Q%\na!\u00199bG\",'\"\u0001\u0016\u0002\u0007=\u0014xm\u0005\u0003\u0001YI*\u0004CA\u00171\u001b\u0005q#\"A\u0018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Er#AB!osJ+g\r\u0005\u0002.g%\u0011AG\f\u0002\b!J|G-^2u!\t1tH\u0004\u00028{9\u0011\u0001\bP\u0007\u0002s)\u0011!hO\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq&\u0003\u0002?]\u00059\u0001/Y2lC\u001e,\u0017B\u0001!B\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tqd&A\u0003mC\n,G.F\u0001E!\tiS)\u0003\u0002G]\t1Ai\\;cY\u0016\fa\u0001\\1cK2\u0004\u0013AB<fS\u001eDG/A\u0004xK&<\u0007\u000e\u001e\u0011\u0002\r=4gm]3u\u0003\u001dygMZ:fi\u0002\n\u0001BZ3biV\u0014Xm]\u000b\u0002\u001dB\u0011qJU\u0007\u0002!*\u0011\u0011kI\u0001\u0007Y&t\u0017\r\\4\n\u0005M\u0003&A\u0002,fGR|'/A\u0005gK\u0006$XO]3tA\u00051A(\u001b8jiz\"RaV-[7r\u0003\"\u0001\u0017\u0001\u000e\u0003\u0005BQAQ\u0005A\u0002\u0011CQ\u0001S\u0005A\u0002\u0011CQAS\u0005A\u0002\u0011CQ\u0001T\u0005A\u00029\u000b!\u0002^8J]N$\u0018M\\2f+\u0005y\u0006C\u0001-a\u0013\t\t\u0017E\u0001\u0005J]N$\u0018M\\2f\u0003\u0011\u0019w\u000e]=\u0015\u000b]#WMZ4\t\u000f\t[\u0001\u0013!a\u0001\t\"9\u0001j\u0003I\u0001\u0002\u0004!\u0005b\u0002&\f!\u0003\u0005\r\u0001\u0012\u0005\b\u0019.\u0001\n\u00111\u0001O\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u001b\u0016\u0003\t.\\\u0013\u0001\u001c\t\u0003[Jl\u0011A\u001c\u0006\u0003_B\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005Et\u0013AC1o]>$\u0018\r^5p]&\u00111O\u001c\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\u0012\u0001\u001f\u0016\u0003\u001d.\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A>\u0011\u0007q\f\u0019!D\u0001~\u0015\tqx0\u0001\u0003mC:<'BAA\u0001\u0003\u0011Q\u0017M^1\n\u0007\u0005\u0015QP\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u0017\u00012!LA\u0007\u0013\r\tyA\f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003+\tY\u0002E\u0002.\u0003/I1!!\u0007/\u0005\r\te.\u001f\u0005\n\u0003;\u0011\u0012\u0011!a\u0001\u0003\u0017\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0012!\u0019\t)#a\u000b\u0002\u00165\u0011\u0011q\u0005\u0006\u0004\u0003Sq\u0013AC2pY2,7\r^5p]&!\u0011QFA\u0014\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005M\u0012\u0011\b\t\u0004[\u0005U\u0012bAA\u001c]\t9!i\\8mK\u0006t\u0007\"CA\u000f)\u0005\u0005\t\u0019AA\u000b\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007m\fy\u0004C\u0005\u0002\u001eU\t\t\u00111\u0001\u0002\f\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\f\u0005AAo\\*ue&tw\rF\u0001|\u0003\u0019)\u0017/^1mgR!\u00111GA'\u0011%\ti\u0002GA\u0001\u0002\u0004\t)\"\u0001\bPM\u001a\u001cX\r^%ogR\fgnY3\u0011\u0005aS2#\u0002\u000e\u0002V\u0005\u0005\u0004#CA,\u0003;\"E\t\u0012(X\u001b\t\tIFC\u0002\u0002\\9\nqA];oi&lW-\u0003\u0003\u0002`\u0005e#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oiA!\u00111MA5\u001b\t\t)GC\u0002\u0002h}\f!![8\n\u0007\u0001\u000b)\u0007\u0006\u0002\u0002R\u0005)\u0011\r\u001d9msRIq+!\u001d\u0002t\u0005U\u0014q\u000f\u0005\u0006\u0005v\u0001\r\u0001\u0012\u0005\u0006\u0011v\u0001\r\u0001\u0012\u0005\u0006\u0015v\u0001\r\u0001\u0012\u0005\u0006\u0019v\u0001\rAT\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti(!#\u0011\u000b5\ny(a!\n\u0007\u0005\u0005eF\u0001\u0004PaRLwN\u001c\t\b[\u0005\u0015E\t\u0012#O\u0013\r\t9I\f\u0002\u0007)V\u0004H.\u001a\u001b\t\u0011\u0005-e$!AA\u0002]\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\nE\u0002}\u0003'K1!!&~\u0005\u0019y%M[3di\u0002"
)
public class OffsetInstance implements Product, Serializable {
   private final double label;
   private final double weight;
   private final double offset;
   private final Vector features;

   public static Option unapply(final OffsetInstance x$0) {
      return OffsetInstance$.MODULE$.unapply(x$0);
   }

   public static OffsetInstance apply(final double label, final double weight, final double offset, final Vector features) {
      return OffsetInstance$.MODULE$.apply(label, weight, offset, features);
   }

   public static Function1 tupled() {
      return OffsetInstance$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return OffsetInstance$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double label() {
      return this.label;
   }

   public double weight() {
      return this.weight;
   }

   public double offset() {
      return this.offset;
   }

   public Vector features() {
      return this.features;
   }

   public Instance toInstance() {
      return new Instance(this.label(), this.weight(), this.features());
   }

   public OffsetInstance copy(final double label, final double weight, final double offset, final Vector features) {
      return new OffsetInstance(label, weight, offset, features);
   }

   public double copy$default$1() {
      return this.label();
   }

   public double copy$default$2() {
      return this.weight();
   }

   public double copy$default$3() {
      return this.offset();
   }

   public Vector copy$default$4() {
      return this.features();
   }

   public String productPrefix() {
      return "OffsetInstance";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToDouble(this.label());
         }
         case 1 -> {
            return BoxesRunTime.boxToDouble(this.weight());
         }
         case 2 -> {
            return BoxesRunTime.boxToDouble(this.offset());
         }
         case 3 -> {
            return this.features();
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
      return x$1 instanceof OffsetInstance;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "label";
         }
         case 1 -> {
            return "weight";
         }
         case 2 -> {
            return "offset";
         }
         case 3 -> {
            return "features";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.label()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.weight()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.offset()));
      var1 = Statics.mix(var1, Statics.anyHash(this.features()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof OffsetInstance) {
               OffsetInstance var4 = (OffsetInstance)x$1;
               if (this.label() == var4.label() && this.weight() == var4.weight() && this.offset() == var4.offset()) {
                  label52: {
                     Vector var10000 = this.features();
                     Vector var5 = var4.features();
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

   public OffsetInstance(final double label, final double weight, final double offset, final Vector features) {
      this.label = label;
      this.weight = weight;
      this.offset = offset;
      this.features = features;
      Product.$init$(this);
   }
}
