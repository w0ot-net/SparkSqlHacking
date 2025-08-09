package org.apache.spark.mllib.regression;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg\u0001B\u000f\u001f\u0001&B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u001b\u0002\u0011\t\u0012)A\u0005\u0003\"Aq\n\u0001BK\u0002\u0013\u0005\u0001\u000b\u0003\u0005[\u0001\tE\t\u0015!\u0003R\u0011\u0015a\u0006\u0001\"\u0001^\u0011\u0015)\u0007\u0001\"\u0001A\u0011\u00151\u0007\u0001\"\u0001Q\u0011\u00159\u0007\u0001\"\u0011i\u0011\u0019\t\b\u0001\"\u0001#e\"9!\u0010AA\u0001\n\u0003Y\bb\u0002@\u0001#\u0003%\ta \u0005\n\u0003'\u0001\u0011\u0013!C\u0001\u0003+A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\t\u0013\u0005-\u0002!!A\u0005\u0002\u00055\u0002\"CA\u001b\u0001\u0005\u0005I\u0011AA\u001c\u0011%\t\u0019\u0005AA\u0001\n\u0003\n)\u0005C\u0005\u0002T\u0001\t\t\u0011\"\u0001\u0002V!I\u0011q\f\u0001\u0002\u0002\u0013\u0005\u0013\u0011\r\u0005\n\u0003K\u0002\u0011\u0011!C!\u0003OB\u0011\"!\u001b\u0001\u0003\u0003%\t%a\u001b\b\u000f\u0005Ed\u0004#\u0001\u0002t\u00191QD\bE\u0001\u0003kBa\u0001\u0018\f\u0005\u0002\u0005\u0005\u0005bBAB-\u0011\u0005\u0011Q\u0011\u0005\t\u0003#3B\u0011\u0001\u0012\u0002\u0014\"I\u0011\u0011\u0014\f\u0002\u0002\u0013\u0005\u00151\u0014\u0005\n\u0003K3\u0012\u0011!CA\u0003OC\u0011\"!/\u0017\u0003\u0003%I!a/\u0003\u00191\u000b'-\u001a7fIB{\u0017N\u001c;\u000b\u0005}\u0001\u0013A\u0003:fOJ,7o]5p]*\u0011\u0011EI\u0001\u0006[2d\u0017N\u0019\u0006\u0003G\u0011\nQa\u001d9be.T!!\n\u0014\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0013aA8sO\u000e\u00011\u0003\u0002\u0001+aM\u0002\"a\u000b\u0018\u000e\u00031R\u0011!L\u0001\u0006g\u000e\fG.Y\u0005\u0003_1\u0012a!\u00118z%\u00164\u0007CA\u00162\u0013\t\u0011DFA\u0004Qe>$Wo\u0019;\u0011\u0005QbdBA\u001b;\u001d\t1\u0014(D\u00018\u0015\tA\u0004&\u0001\u0004=e>|GOP\u0005\u0002[%\u00111\bL\u0001\ba\u0006\u001c7.Y4f\u0013\tidH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002<Y\u0005)A.\u00192fYV\t\u0011\t\u0005\u0002,\u0005&\u00111\t\f\u0002\u0007\t>,(\r\\3)\u0007\u0005)5\n\u0005\u0002G\u00136\tqI\u0003\u0002IE\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005);%!B*j]\u000e,\u0017%\u0001'\u0002\u000bAr\u0003H\f\u0019\u0002\r1\f'-\u001a7!Q\r\u0011QiS\u0001\tM\u0016\fG/\u001e:fgV\t\u0011\u000b\u0005\u0002S+6\t1K\u0003\u0002UA\u00051A.\u001b8bY\u001eL!AV*\u0003\rY+7\r^8sQ\r\u0019Q\tW\u0011\u00023\u0006)\u0011G\f\u0019/a\u0005Ia-Z1ukJ,7\u000f\t\u0015\u0004\t\u0015C\u0016A\u0002\u001fj]&$h\bF\u0002_A\n\u0004\"a\u0018\u0001\u000e\u0003yAQaP\u0003A\u0002\u0005C3\u0001Y#L\u0011\u0015yU\u00011\u0001RQ\r\u0011W\t\u0017\u0015\u0004\u000b\u0015C\u0016\u0001C4fi2\u000b'-\u001a7\u0002\u0017\u001d,GOR3biV\u0014Xm]\u0001\ti>\u001cFO]5oOR\t\u0011\u000e\u0005\u0002k]:\u00111\u000e\u001c\t\u0003m1J!!\u001c\u0017\u0002\rA\u0013X\rZ3g\u0013\ty\u0007O\u0001\u0004TiJLgn\u001a\u0006\u0003[2\nA!Y:N\u0019V\t1\u000f\u0005\u0002us6\tQO\u0003\u0002wo\u00069a-Z1ukJ,'B\u0001=#\u0003\tiG.\u0003\u0002\u001ek\u0006!1m\u001c9z)\rqF0 \u0005\b\u007f)\u0001\n\u00111\u0001B\u0011\u001dy%\u0002%AA\u0002E\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u0002)\u001a\u0011)a\u0001,\u0005\u0005\u0015\u0001\u0003BA\u0004\u0003\u001fi!!!\u0003\u000b\t\u0005-\u0011QB\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0013\u0017\n\t\u0005E\u0011\u0011\u0002\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003/Q3!UA\u0002\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\u0004\t\u0005\u0003?\tI#\u0004\u0002\u0002\")!\u00111EA\u0013\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u001d\u0012\u0001\u00026bm\u0006L1a\\A\u0011\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\ty\u0003E\u0002,\u0003cI1!a\r-\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tI$a\u0010\u0011\u0007-\nY$C\u0002\u0002>1\u00121!\u00118z\u0011%\t\teDA\u0001\u0002\u0004\ty#A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u000f\u0002b!!\u0013\u0002P\u0005eRBAA&\u0015\r\ti\u0005L\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA)\u0003\u0017\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011qKA/!\rY\u0013\u0011L\u0005\u0004\u00037b#a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003\u0003\n\u0012\u0011!a\u0001\u0003s\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011QDA2\u0011%\t\tEEA\u0001\u0002\u0004\ty#\u0001\u0005iCND7i\u001c3f)\t\ty#\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003/\ni\u0007C\u0005\u0002BQ\t\t\u00111\u0001\u0002:!\u001a\u0001!R&\u0002\u00191\u000b'-\u001a7fIB{\u0017N\u001c;\u0011\u0005}32\u0003\u0002\f+\u0003o\u0002B!!\u001f\u0002\u00005\u0011\u00111\u0010\u0006\u0005\u0003{\n)#\u0001\u0002j_&\u0019Q(a\u001f\u0015\u0005\u0005M\u0014!\u00029beN,Gc\u00010\u0002\b\"1\u0011\u0011\u0012\rA\u0002%\f\u0011a\u001d\u0015\u00051\u0015\u000bi)\t\u0002\u0002\u0010\u0006)\u0011GL\u0019/a\u00051aM]8n\u001b2#2AXAK\u0011\u0019\t9*\u0007a\u0001g\u0006)\u0001o\\5oi\u0006)\u0011\r\u001d9msR)a,!(\u0002\"\")qH\u0007a\u0001\u0003\"\"\u0011QT#L\u0011\u0015y%\u00041\u0001RQ\u0011\t\t+\u0012-\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011VA[!\u0015Y\u00131VAX\u0013\r\ti\u000b\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b-\n\t,Q)\n\u0007\u0005MFF\u0001\u0004UkBdWM\r\u0005\t\u0003o[\u0012\u0011!a\u0001=\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0006\u0003BA\u0010\u0003\u007fKA!!1\u0002\"\t1qJ\u00196fGRDCAF#\u0002\u000e\"\"Q#RAG\u0001"
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

   public static LabeledPoint parse(final String s) {
      return LabeledPoint$.MODULE$.parse(s);
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

   public org.apache.spark.ml.feature.LabeledPoint asML() {
      return new org.apache.spark.ml.feature.LabeledPoint(this.label(), this.features().asML());
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
