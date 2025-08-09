package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uh\u0001B\u0010!\u00016B\u0001b\u0011\u0001\u0003\u0016\u0004%\t\u0001\u0012\u0005\t#\u0002\u0011\t\u0012)A\u0005\u000b\"A1\u000b\u0001BK\u0002\u0013\u0005A\u000b\u0003\u0005Z\u0001\tE\t\u0015!\u0003V\u0011!Y\u0006A!f\u0001\n\u0003a\u0006\u0002C:\u0001\u0005#\u0005\u000b\u0011B/\t\u0011U\u0004!Q3A\u0005\u0002YD\u0001b\u001f\u0001\u0003\u0012\u0003\u0006Ia\u001e\u0005\u0006{\u0002!\tA \u0005\b\u0003'\u0001A\u0011IA\u000b\u0011%\t9\u0003AA\u0001\n\u0003\tI\u0003C\u0005\u00024\u0001\t\n\u0011\"\u0001\u00026!I\u0011\u0011\n\u0001\u0012\u0002\u0013\u0005\u00111\n\u0005\n\u0003\u001f\u0002\u0011\u0013!C\u0001\u0003#B\u0011\"!\u0016\u0001#\u0003%\t!a\u0016\t\u0013\u0005m\u0003!!A\u0005B\u0005u\u0003\u0002CA7\u0001\u0005\u0005I\u0011\u0001#\t\u0013\u0005=\u0004!!A\u0005\u0002\u0005E\u0004\"CA?\u0001\u0005\u0005I\u0011IA@\u0011%\ti\tAA\u0001\n\u0003\ty\tC\u0005\u0002\u001a\u0002\t\t\u0011\"\u0011\u0002\u001c\"I\u0011q\u0014\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0015\u0005\n\u0003G\u0003\u0011\u0011!C!\u0003K;\u0011\"a+!\u0003\u0003E\t!!,\u0007\u0011}\u0001\u0013\u0011!E\u0001\u0003_Ca!`\r\u0005\u0002\u0005\u001d\u0007\"CA\n3\u0005\u0005IQIAe\u0011%\tY-GA\u0001\n\u0003\u000bi\rC\u0005\u0002`f\t\t\u0011\"!\u0002b\"I\u00111_\r\u0002\u0002\u0013%\u0011Q\u001f\u0002\u0006'Bd\u0017\u000e\u001e\u0006\u0003C\t\nQ!\\8eK2T!a\t\u0013\u0002\tQ\u0014X-\u001a\u0006\u0003K\u0019\nQ!\u001c7mS\nT!a\n\u0015\u0002\u000bM\u0004\u0018M]6\u000b\u0005%R\u0013AB1qC\u000eDWMC\u0001,\u0003\ry'oZ\u0002\u0001'\u0011\u0001a\u0006N\u001c\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0003E\nQa]2bY\u0006L!a\r\u0019\u0003\r\u0005s\u0017PU3g!\tyS'\u0003\u00027a\t9\u0001K]8ek\u000e$\bC\u0001\u001dA\u001d\tIdH\u0004\u0002;{5\t1H\u0003\u0002=Y\u00051AH]8pizJ\u0011!M\u0005\u0003\u007fA\nq\u0001]1dW\u0006<W-\u0003\u0002B\u0005\na1+\u001a:jC2L'0\u00192mK*\u0011q\bM\u0001\bM\u0016\fG/\u001e:f+\u0005)\u0005CA\u0018G\u0013\t9\u0005GA\u0002J]RD3!A%P!\tQU*D\u0001L\u0015\tae%\u0001\u0006b]:|G/\u0019;j_:L!AT&\u0003\u000bMKgnY3\"\u0003A\u000bQ!\r\u00181]A\n\u0001BZ3biV\u0014X\r\t\u0015\u0004\u0005%{\u0015!\u0003;ie\u0016\u001c\bn\u001c7e+\u0005)\u0006CA\u0018W\u0013\t9\u0006G\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0007%{\u0015A\u0003;ie\u0016\u001c\bn\u001c7eA!\u001aA!S(\u0002\u0017\u0019,\u0017\r^;sKRK\b/Z\u000b\u0002;B\u0011al\u001c\b\u0003?2t!\u0001\u00196\u000f\u0005\u0005LgB\u00012i\u001d\t\u0019wM\u0004\u0002eM:\u0011!(Z\u0005\u0002W%\u0011\u0011FK\u0005\u0003O!J!!\n\u0014\n\u0005\r\"\u0013BA6#\u00035\u0019wN\u001c4jOV\u0014\u0018\r^5p]&\u0011QN\\\u0001\f\r\u0016\fG/\u001e:f)f\u0004XM\u0003\u0002lE%\u0011\u0001/\u001d\u0002\f\r\u0016\fG/\u001e:f)f\u0004XM\u0003\u0002n]\"\u001aQ!S(\u0002\u0019\u0019,\u0017\r^;sKRK\b/\u001a\u0011)\u0007\u0019Iu*\u0001\u0006dCR,wm\u001c:jKN,\u0012a\u001e\t\u0004qa,\u0016BA=C\u0005\u0011a\u0015n\u001d;)\u0007\u001dIu*A\u0006dCR,wm\u001c:jKN\u0004\u0003f\u0001\u0005J\u001f\u00061A(\u001b8jiz\"\u0012b`A\u0002\u0003\u000f\tY!a\u0004\u0011\u0007\u0005\u0005\u0001!D\u0001!\u0011\u0015\u0019\u0015\u00021\u0001FQ\u0011\t\u0019!S(\t\u000bMK\u0001\u0019A+)\t\u0005\u001d\u0011j\u0014\u0005\u00067&\u0001\r!\u0018\u0015\u0005\u0003\u0017Iu\nC\u0003v\u0013\u0001\u0007q\u000f\u000b\u0003\u0002\u0010%{\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005]\u0001\u0003BA\r\u0003CqA!a\u0007\u0002\u001eA\u0011!\bM\u0005\u0004\u0003?\u0001\u0014A\u0002)sK\u0012,g-\u0003\u0003\u0002$\u0005\u0015\"AB*ue&twMC\u0002\u0002 A\nAaY8qsRIq0a\u000b\u0002.\u0005=\u0012\u0011\u0007\u0005\b\u0007.\u0001\n\u00111\u0001F\u0011\u001d\u00196\u0002%AA\u0002UCqaW\u0006\u0011\u0002\u0003\u0007Q\fC\u0004v\u0017A\u0005\t\u0019A<\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\u0007\u0016\u0004\u000b\u0006e2FAA\u001e!\u0011\ti$!\u0012\u000e\u0005\u0005}\"\u0002BA!\u0003\u0007\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u00051\u0003\u0014\u0002BA$\u0003\u007f\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!!\u0014+\u0007U\u000bI$\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005M#fA/\u0002:\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"TCAA-U\r9\u0018\u0011H\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005}\u0003\u0003BA1\u0003Wj!!a\u0019\u000b\t\u0005\u0015\u0014qM\u0001\u0005Y\u0006twM\u0003\u0002\u0002j\u0005!!.\u0019<b\u0013\u0011\t\u0019#a\u0019\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u00111OA=!\ry\u0013QO\u0005\u0004\u0003o\u0002$aA!os\"A\u00111\u0010\n\u0002\u0002\u0003\u0007Q)A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0003\u0003b!a!\u0002\n\u0006MTBAAC\u0015\r\t9\tM\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAF\u0003\u000b\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011\u0011SAL!\ry\u00131S\u0005\u0004\u0003+\u0003$a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003w\"\u0012\u0011!a\u0001\u0003g\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011qLAO\u0011!\tY(FA\u0001\u0002\u0004)\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0015\u000ba!Z9vC2\u001cH\u0003BAI\u0003OC\u0011\"a\u001f\u0018\u0003\u0003\u0005\r!a\u001d)\u0007\u0001Iu*A\u0003Ta2LG\u000fE\u0002\u0002\u0002e\u0019R!GAY\u0003{\u0003\u0012\"a-\u0002:\u0016+Vl^@\u000e\u0005\u0005U&bAA\\a\u00059!/\u001e8uS6,\u0017\u0002BA^\u0003k\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c85!\u0011\ty,!2\u000e\u0005\u0005\u0005'\u0002BAb\u0003O\n!![8\n\u0007\u0005\u000b\t\r\u0006\u0002\u0002.R\u0011\u0011qL\u0001\u0006CB\u0004H.\u001f\u000b\n\u007f\u0006=\u00171[Al\u00037DQa\u0011\u000fA\u0002\u0015CC!a4J\u001f\")1\u000b\ba\u0001+\"\"\u00111[%P\u0011\u0015YF\u00041\u0001^Q\u0011\t9.S(\t\u000bUd\u0002\u0019A<)\t\u0005m\u0017jT\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\u0019/a<\u0011\u000b=\n)/!;\n\u0007\u0005\u001d\bG\u0001\u0004PaRLwN\u001c\t\b_\u0005-X)V/x\u0013\r\ti\u000f\r\u0002\u0007)V\u0004H.\u001a\u001b\t\u0011\u0005EX$!AA\u0002}\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\u0010\u0005\u0003\u0002b\u0005e\u0018\u0002BA~\u0003G\u0012aa\u00142kK\u000e$\b"
)
public class Split implements Product, Serializable {
   private final int feature;
   private final double threshold;
   private final Enumeration.Value featureType;
   private final List categories;

   public static Option unapply(final Split x$0) {
      return Split$.MODULE$.unapply(x$0);
   }

   public static Split apply(final int feature, final double threshold, final Enumeration.Value featureType, final List categories) {
      return Split$.MODULE$.apply(feature, threshold, featureType, categories);
   }

   public static Function1 tupled() {
      return Split$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Split$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int feature() {
      return this.feature;
   }

   public double threshold() {
      return this.threshold;
   }

   public Enumeration.Value featureType() {
      return this.featureType;
   }

   public List categories() {
      return this.categories;
   }

   public String toString() {
      int var10000 = this.feature();
      return "Feature = " + var10000 + ", threshold = " + this.threshold() + ", featureType = " + this.featureType() + ", categories = " + this.categories();
   }

   public Split copy(final int feature, final double threshold, final Enumeration.Value featureType, final List categories) {
      return new Split(feature, threshold, featureType, categories);
   }

   public int copy$default$1() {
      return this.feature();
   }

   public double copy$default$2() {
      return this.threshold();
   }

   public Enumeration.Value copy$default$3() {
      return this.featureType();
   }

   public List copy$default$4() {
      return this.categories();
   }

   public String productPrefix() {
      return "Split";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.feature());
         }
         case 1 -> {
            return BoxesRunTime.boxToDouble(this.threshold());
         }
         case 2 -> {
            return this.featureType();
         }
         case 3 -> {
            return this.categories();
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
      return x$1 instanceof Split;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "feature";
         }
         case 1 -> {
            return "threshold";
         }
         case 2 -> {
            return "featureType";
         }
         case 3 -> {
            return "categories";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.feature());
      var1 = Statics.mix(var1, Statics.doubleHash(this.threshold()));
      var1 = Statics.mix(var1, Statics.anyHash(this.featureType()));
      var1 = Statics.mix(var1, Statics.anyHash(this.categories()));
      return Statics.finalizeHash(var1, 4);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof Split) {
               Split var4 = (Split)x$1;
               if (this.feature() == var4.feature() && this.threshold() == var4.threshold()) {
                  label56: {
                     Enumeration.Value var10000 = this.featureType();
                     Enumeration.Value var5 = var4.featureType();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label56;
                     }

                     List var7 = this.categories();
                     List var6 = var4.categories();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label56;
                        }
                     } else if (!var7.equals(var6)) {
                        break label56;
                     }

                     if (var4.canEqual(this)) {
                        break label63;
                     }
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

   public Split(final int feature, final double threshold, final Enumeration.Value featureType, final List categories) {
      this.feature = feature;
      this.threshold = threshold;
      this.featureType = featureType;
      this.categories = categories;
      Product.$init$(this);
   }
}
