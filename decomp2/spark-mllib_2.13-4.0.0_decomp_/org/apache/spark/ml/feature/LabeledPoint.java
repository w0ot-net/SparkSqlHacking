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
   bytes = "\u0006\u0005\u0005Mf\u0001B\u000f\u001f\u0001&B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u001b\u0002\u0011\t\u0012)A\u0005\u0003\"Aq\n\u0001BK\u0002\u0013\u0005\u0001\u000b\u0003\u0005Y\u0001\tE\t\u0015!\u0003R\u0011\u0015Q\u0006\u0001\"\u0001\\\u0011\u0015\u0011\u0007\u0001\"\u0001A\u0011\u0015\u0019\u0007\u0001\"\u0001Q\u0011\u0015!\u0007\u0001\"\u0011f\u0011\u0019q\u0007\u0001\"\u0001#_\"1a\u000e\u0001C\u0001EUDqA\u001e\u0001\u0002\u0002\u0013\u0005q\u000fC\u0004{\u0001E\u0005I\u0011A>\t\u0013\u0005-\u0001!%A\u0005\u0002\u00055\u0001\"CA\t\u0001\u0005\u0005I\u0011IA\n\u0011%\t\u0019\u0003AA\u0001\n\u0003\t)\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0001\u00020!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0013Q\b\u0005\n\u0003\u0017\u0002\u0011\u0011!C\u0001\u0003\u001bB\u0011\"a\u0016\u0001\u0003\u0003%\t%!\u0017\t\u0013\u0005u\u0003!!A\u0005B\u0005}\u0003\"CA1\u0001\u0005\u0005I\u0011IA2\u000f%\tIGHA\u0001\u0012\u0003\tYG\u0002\u0005\u001e=\u0005\u0005\t\u0012AA7\u0011\u0019Qv\u0003\"\u0001\u0002\u0006\"AAmFA\u0001\n\u000b\n9\tC\u0005\u0002\n^\t\t\u0011\"!\u0002\f\"I\u0011QS\f\u0002\u0002\u0013\u0005\u0015q\u0013\u0005\n\u0003S;\u0012\u0011!C\u0005\u0003W\u0013A\u0002T1cK2,G\rU8j]RT!a\b\u0011\u0002\u000f\u0019,\u0017\r^;sK*\u0011\u0011EI\u0001\u0003[2T!a\t\u0013\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u00152\u0013AB1qC\u000eDWMC\u0001(\u0003\ry'oZ\u0002\u0001'\u0011\u0001!\u0006M\u001a\u0011\u0005-rS\"\u0001\u0017\u000b\u00035\nQa]2bY\u0006L!a\f\u0017\u0003\r\u0005s\u0017PU3g!\tY\u0013'\u0003\u00023Y\t9\u0001K]8ek\u000e$\bC\u0001\u001b=\u001d\t)$H\u0004\u00027s5\tqG\u0003\u00029Q\u00051AH]8pizJ\u0011!L\u0005\u0003w1\nq\u0001]1dW\u0006<W-\u0003\u0002>}\ta1+\u001a:jC2L'0\u00192mK*\u00111\bL\u0001\u0006Y\u0006\u0014W\r\\\u000b\u0002\u0003B\u00111FQ\u0005\u0003\u00072\u0012a\u0001R8vE2,\u0007fA\u0001F\u0017B\u0011a)S\u0007\u0002\u000f*\u0011\u0001JI\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001&H\u0005\u0015\u0019\u0016N\\2fC\u0005a\u0015!\u0002\u001a/a9\u0002\u0014A\u00027bE\u0016d\u0007\u0005K\u0002\u0003\u000b.\u000b\u0001BZ3biV\u0014Xm]\u000b\u0002#B\u0011!+V\u0007\u0002'*\u0011A\u000bI\u0001\u0007Y&t\u0017\r\\4\n\u0005Y\u001b&A\u0002,fGR|'\u000fK\u0002\u0004\u000b.\u000b\u0011BZ3biV\u0014Xm\u001d\u0011)\u0007\u0011)5*\u0001\u0004=S:LGO\u0010\u000b\u00049z\u0003\u0007CA/\u0001\u001b\u0005q\u0002\"B \u0006\u0001\u0004\t\u0005f\u00010F\u0017\")q*\u0002a\u0001#\"\u001a\u0001-R&\u0002\u0011\u001d,G\u000fT1cK2\f1bZ3u\r\u0016\fG/\u001e:fg\u0006AAo\\*ue&tw\rF\u0001g!\t97N\u0004\u0002iSB\u0011a\u0007L\u0005\u0003U2\na\u0001\u0015:fI\u00164\u0017B\u00017n\u0005\u0019\u0019FO]5oO*\u0011!\u000eL\u0001\u000bi>Len\u001d;b]\u000e,GC\u00019t!\ti\u0016/\u0003\u0002s=\tA\u0011J\\:uC:\u001cW\rC\u0003u\u0013\u0001\u0007\u0011)\u0001\u0004xK&<\u0007\u000e^\u000b\u0002a\u0006!1m\u001c9z)\ra\u00060\u001f\u0005\b\u007f-\u0001\n\u00111\u0001B\u0011\u001dy5\u0002%AA\u0002E\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001}U\t\tUpK\u0001\u007f!\ry\u0018qA\u0007\u0003\u0003\u0003QA!a\u0001\u0002\u0006\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u00112JA!!\u0003\u0002\u0002\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\u0002\u0016\u0003#v\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u000b!\u0011\t9\"!\t\u000e\u0005\u0005e!\u0002BA\u000e\u0003;\tA\u0001\\1oO*\u0011\u0011qD\u0001\u0005U\u00064\u0018-C\u0002m\u00033\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\n\u0011\u0007-\nI#C\u0002\u0002,1\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\r\u00028A\u00191&a\r\n\u0007\u0005UBFA\u0002B]fD\u0011\"!\u000f\u0011\u0003\u0003\u0005\r!a\n\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u0004\u0005\u0004\u0002B\u0005\u001d\u0013\u0011G\u0007\u0003\u0003\u0007R1!!\u0012-\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0013\n\u0019E\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA(\u0003+\u00022aKA)\u0013\r\t\u0019\u0006\f\u0002\b\u0005>|G.Z1o\u0011%\tIDEA\u0001\u0002\u0004\t\t$\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u000b\u00037B\u0011\"!\u000f\u0014\u0003\u0003\u0005\r!a\n\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\n\u0002\r\u0015\fX/\u00197t)\u0011\ty%!\u001a\t\u0013\u0005eR#!AA\u0002\u0005E\u0002f\u0001\u0001F\u0017\u0006aA*\u00192fY\u0016$\u0007k\\5oiB\u0011QlF\n\u0006/\u0005=\u00141\u0010\t\b\u0003c\n9(Q)]\u001b\t\t\u0019HC\u0002\u0002v1\nqA];oi&lW-\u0003\u0003\u0002z\u0005M$!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011QPAB\u001b\t\tyH\u0003\u0003\u0002\u0002\u0006u\u0011AA5p\u0013\ri\u0014q\u0010\u000b\u0003\u0003W\"\"!!\u0006\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000bq\u000bi)!%\t\u000b}R\u0002\u0019A!)\t\u00055Ui\u0013\u0005\u0006\u001fj\u0001\r!\u0015\u0015\u0005\u0003#+5*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005e\u0015Q\u0015\t\u0006W\u0005m\u0015qT\u0005\u0004\u0003;c#AB(qi&|g\u000eE\u0003,\u0003C\u000b\u0015+C\u0002\u0002$2\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CAT7\u0005\u0005\t\u0019\u0001/\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002.B!\u0011qCAX\u0013\u0011\t\t,!\u0007\u0003\r=\u0013'.Z2u\u0001"
)
public class LabeledPoint implements Product, Serializable {
   private final double label;
   private final Vector features;

   public static Option unapply(final LabeledPoint x$0) {
      return LabeledPoint$.MODULE$.unapply(x$0);
   }

   public static LabeledPoint apply(final double label, final Vector features) {
      return LabeledPoint$.MODULE$.apply(label, features);
   }

   public static Function1 tupled() {
      return LabeledPoint$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return LabeledPoint$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double label() {
      return this.label;
   }

   public Vector features() {
      return this.features;
   }

   public double getLabel() {
      return this.label();
   }

   public Vector getFeatures() {
      return this.features();
   }

   public String toString() {
      double var10000 = this.label();
      return "(" + var10000 + "," + this.features() + ")";
   }

   public Instance toInstance(final double weight) {
      return new Instance(this.label(), weight, this.features());
   }

   public Instance toInstance() {
      return new Instance(this.label(), (double)1.0F, this.features());
   }

   public LabeledPoint copy(final double label, final Vector features) {
      return new LabeledPoint(label, features);
   }

   public double copy$default$1() {
      return this.label();
   }

   public Vector copy$default$2() {
      return this.features();
   }

   public String productPrefix() {
      return "LabeledPoint";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToDouble(this.label());
         }
         case 1 -> {
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
      return x$1 instanceof LabeledPoint;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "label";
         }
         case 1 -> {
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
      var1 = Statics.mix(var1, Statics.anyHash(this.features()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof LabeledPoint) {
               LabeledPoint var4 = (LabeledPoint)x$1;
               if (this.label() == var4.label()) {
                  label44: {
                     Vector var10000 = this.features();
                     Vector var5 = var4.features();
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

   public LabeledPoint(final double label, final Vector features) {
      this.label = label;
      this.features = features;
      Product.$init$(this);
   }
}
