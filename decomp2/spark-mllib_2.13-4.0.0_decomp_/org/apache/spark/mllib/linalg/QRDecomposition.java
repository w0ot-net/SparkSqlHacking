package org.apache.spark.mllib.linalg;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005f\u0001B\r\u001b\u0001\u0016B\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005}!A!\n\u0001BK\u0002\u0013\u00051\n\u0003\u0005P\u0001\tE\t\u0015!\u0003M\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u001d1\u0006!!A\u0005\u0002]Cq\u0001\u0019\u0001\u0012\u0002\u0013\u0005\u0011\rC\u0004p\u0001E\u0005I\u0011\u00019\t\u000fU\u0004\u0011\u0011!C!m\"Aq\u0010AA\u0001\n\u0003\t\t\u0001C\u0005\u0002\n\u0001\t\t\u0011\"\u0001\u0002\f!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003C\u0001\u0011\u0011!C\u0001\u0003GA\u0011\"!\f\u0001\u0003\u0003%\t%a\f\t\u0013\u0005M\u0002!!A\u0005B\u0005U\u0002\"CA\u001c\u0001\u0005\u0005I\u0011IA\u001d\u0011%\tY\u0004AA\u0001\n\u0003\nidB\u0005\u0002Ri\t\t\u0011#\u0001\u0002T\u0019A\u0011DGA\u0001\u0012\u0003\t)\u0006\u0003\u0004Q'\u0011\u0005\u0011\u0011\r\u0005\n\u0003o\u0019\u0012\u0011!C#\u0003sA\u0011\"a\u0019\u0014\u0003\u0003%\t)!\u001a\t\u0013\u0005]4#!A\u0005\u0002\u0006e\u0004\"CAL'\u0005\u0005I\u0011BAM\u0005=\t&\u000bR3d_6\u0004xn]5uS>t'BA\u000e\u001d\u0003\u0019a\u0017N\\1mO*\u0011QDH\u0001\u0006[2d\u0017N\u0019\u0006\u0003?\u0001\nQa\u001d9be.T!!\t\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0013aA8sO\u000e\u0001Qc\u0001\u0014A\u001bN!\u0001aJ\u00171!\tA3&D\u0001*\u0015\u0005Q\u0013!B:dC2\f\u0017B\u0001\u0017*\u0005\u0019\te.\u001f*fMB\u0011\u0001FL\u0005\u0003_%\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00022s9\u0011!g\u000e\b\u0003gYj\u0011\u0001\u000e\u0006\u0003k\u0011\na\u0001\u0010:p_Rt\u0014\"\u0001\u0016\n\u0005aJ\u0013a\u00029bG.\fw-Z\u0005\u0003um\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001O\u0015\u0002\u0003E+\u0012A\u0010\t\u0003\u007f\u0001c\u0001\u0001B\u0003B\u0001\t\u0007!IA\u0003R)f\u0004X-\u0005\u0002D\rB\u0011\u0001\u0006R\u0005\u0003\u000b&\u0012qAT8uQ&tw\r\u0005\u0002)\u000f&\u0011\u0001*\u000b\u0002\u0004\u0003:L\u0018AA)!\u0003\u0005\u0011V#\u0001'\u0011\u0005}jE!\u0002(\u0001\u0005\u0004\u0011%!\u0002*UsB,\u0017A\u0001*!\u0003\u0019a\u0014N\\5u}Q\u0019!\u000bV+\u0011\tM\u0003a\bT\u0007\u00025!)A(\u0002a\u0001}!)!*\u0002a\u0001\u0019\u0006!1m\u001c9z+\rA6,\u0018\u000b\u00043z{\u0006\u0003B*\u00015r\u0003\"aP.\u0005\u000b\u00053!\u0019\u0001\"\u0011\u0005}jF!\u0002(\u0007\u0005\u0004\u0011\u0005b\u0002\u001f\u0007!\u0003\u0005\rA\u0017\u0005\b\u0015\u001a\u0001\n\u00111\u0001]\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*2AY7o+\u0005\u0019'F\u0001 eW\u0005)\u0007C\u00014l\u001b\u00059'B\u00015j\u0003%)hn\u00195fG.,GM\u0003\u0002kS\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00051<'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)\u0011i\u0002b\u0001\u0005\u0012)aj\u0002b\u0001\u0005\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TcA9tiV\t!O\u000b\u0002MI\u0012)\u0011\t\u0003b\u0001\u0005\u0012)a\n\u0003b\u0001\u0005\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012a\u001e\t\u0003qvl\u0011!\u001f\u0006\u0003un\fA\u0001\\1oO*\tA0\u0001\u0003kCZ\f\u0017B\u0001@z\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u00111\u0001\t\u0004Q\u0005\u0015\u0011bAA\u0004S\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019a)!\u0004\t\u0013\u0005=1\"!AA\u0002\u0005\r\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0016A)\u0011qCA\u000f\r6\u0011\u0011\u0011\u0004\u0006\u0004\u00037I\u0013AC2pY2,7\r^5p]&!\u0011qDA\r\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u0015\u00121\u0006\t\u0004Q\u0005\u001d\u0012bAA\u0015S\t9!i\\8mK\u0006t\u0007\u0002CA\b\u001b\u0005\u0005\t\u0019\u0001$\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004o\u0006E\u0002\"CA\b\u001d\u0005\u0005\t\u0019AA\u0002\u0003!A\u0017m\u001d5D_\u0012,GCAA\u0002\u0003!!xn\u0015;sS:<G#A<\u0002\r\u0015\fX/\u00197t)\u0011\t)#a\u0010\t\u0011\u0005=\u0011#!AA\u0002\u0019CS\u0001AA\"\u0003\u001b\u0002B!!\u0012\u0002J5\u0011\u0011q\t\u0006\u0003UzIA!a\u0013\u0002H\t)1+\u001b8dK\u0006\u0012\u0011qJ\u0001\u0006c9*d\u0006M\u0001\u0010#J#UmY8na>\u001c\u0018\u000e^5p]B\u00111kE\n\u0005'\u001d\n9\u0006\u0005\u0003\u0002Z\u0005}SBAA.\u0015\r\tif_\u0001\u0003S>L1AOA.)\t\t\u0019&A\u0003baBd\u00170\u0006\u0004\u0002h\u00055\u0014\u0011\u000f\u000b\u0007\u0003S\n\u0019(!\u001e\u0011\rM\u0003\u00111NA8!\ry\u0014Q\u000e\u0003\u0006\u0003Z\u0011\rA\u0011\t\u0004\u007f\u0005ED!\u0002(\u0017\u0005\u0004\u0011\u0005B\u0002\u001f\u0017\u0001\u0004\tY\u0007\u0003\u0004K-\u0001\u0007\u0011qN\u0001\bk:\f\u0007\u000f\u001d7z+\u0019\tY(a#\u0002\u0010R!\u0011QPAI!\u0015A\u0013qPAB\u0013\r\t\t)\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000f!\n))!#\u0002\u000e&\u0019\u0011qQ\u0015\u0003\rQ+\b\u000f\\33!\ry\u00141\u0012\u0003\u0006\u0003^\u0011\rA\u0011\t\u0004\u007f\u0005=E!\u0002(\u0018\u0005\u0004\u0011\u0005\"CAJ/\u0005\u0005\t\u0019AAK\u0003\rAH\u0005\r\t\u0007'\u0002\tI)!$\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005m\u0005c\u0001=\u0002\u001e&\u0019\u0011qT=\u0003\r=\u0013'.Z2u\u0001"
)
public class QRDecomposition implements Product, Serializable {
   private final Object Q;
   private final Object R;

   public static Option unapply(final QRDecomposition x$0) {
      return QRDecomposition$.MODULE$.unapply(x$0);
   }

   public static QRDecomposition apply(final Object Q, final Object R) {
      return QRDecomposition$.MODULE$.apply(Q, R);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object Q() {
      return this.Q;
   }

   public Object R() {
      return this.R;
   }

   public QRDecomposition copy(final Object Q, final Object R) {
      return new QRDecomposition(Q, R);
   }

   public Object copy$default$1() {
      return this.Q();
   }

   public Object copy$default$2() {
      return this.R();
   }

   public String productPrefix() {
      return "QRDecomposition";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.Q();
         }
         case 1 -> {
            return this.R();
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
      return x$1 instanceof QRDecomposition;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "Q";
         }
         case 1 -> {
            return "R";
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
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof QRDecomposition) {
               QRDecomposition var4 = (QRDecomposition)x$1;
               if (BoxesRunTime.equals(this.Q(), var4.Q()) && BoxesRunTime.equals(this.R(), var4.R()) && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public QRDecomposition(final Object Q, final Object R) {
      this.Q = Q;
      this.R = R;
      Product.$init$(this);
   }
}
