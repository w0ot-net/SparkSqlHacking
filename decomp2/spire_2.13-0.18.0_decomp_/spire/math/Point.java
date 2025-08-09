package spire.math;

import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.math.interval.Closed;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dd\u0001B\f\u0019\u0001vA\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005G!11\t\u0001C\u00015\u0011CQa\u0012\u0001\u0005\u0002!CQa\u0014\u0001\u0005\u0002!Cq\u0001\u0015\u0001\u0002\u0002\u0013\u0005\u0011\u000bC\u0004X\u0001E\u0005I\u0011\u0001-\t\u000f\u0015\u0004\u0011\u0011!C!M\"9q\u000eAA\u0001\n\u0003\u0001\bb\u0002;\u0001\u0003\u0003%\t!\u001e\u0005\bq\u0002\t\t\u0011\"\u0011z\u0011%\t\t\u0001AA\u0001\n\u0003\t\u0019\u0001C\u0005\u0002\u000e\u0001\t\t\u0011\"\u0011\u0002\u0010!I\u00111\u0003\u0001\u0002\u0002\u0013\u0005\u0013Q\u0003\u0005\n\u0003/\u0001\u0011\u0011!C!\u000339\u0011\"!\b\u0019\u0003\u0003E\t!a\b\u0007\u0011]A\u0012\u0011!E\u0001\u0003CAaaQ\t\u0005\u0002\u0005M\u0002\"CA\u001b#\u0005\u0005IQIA\u001c\u0011%\tI$EA\u0001\n\u0003\u000bY\u0004C\u0005\u0002HE\t\t\u0011\"!\u0002J!I\u0011QL\t\u0002\u0002\u0013%\u0011q\f\u0002\u0006!>Lg\u000e\u001e\u0006\u00033i\tA!\\1uQ*\t1$A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0005y)3\u0003\u0002\u0001 cQ\u00022\u0001I\u0011$\u001b\u0005A\u0012B\u0001\u0012\u0019\u0005!Ie\u000e^3sm\u0006d\u0007C\u0001\u0013&\u0019\u0001!QA\n\u0001C\u0002\u001d\u0012\u0011!Q\t\u0003Q9\u0002\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012qAT8uQ&tw\r\u0005\u0002*_%\u0011\u0001G\u000b\u0002\u0004\u0003:L\bCA\u00153\u0013\t\u0019$FA\u0004Qe>$Wo\u0019;\u0011\u0005UjdB\u0001\u001c<\u001d\t9$(D\u00019\u0015\tID$\u0001\u0004=e>|GOP\u0005\u0002W%\u0011AHK\u0001\ba\u0006\u001c7.Y4f\u0013\tqtH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002=U\u0005)a/\u00197vKV\t1%\u0001\u0004wC2,X\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u00153\u0005c\u0001\u0011\u0001G!)\u0001i\u0001a\u0001G\u0005QAn\\<fe\n{WO\u001c3\u0016\u0003%\u00032AS'$\u001b\u0005Y%B\u0001'\u0019\u0003!Ig\u000e^3sm\u0006d\u0017B\u0001(L\u0005\u0019\u0019En\\:fI\u0006QQ\u000f\u001d9fe\n{WO\u001c3\u0002\t\r|\u0007/_\u000b\u0003%V#\"a\u0015,\u0011\u0007\u0001\u0002A\u000b\u0005\u0002%+\u0012)aE\u0002b\u0001O!9\u0001I\u0002I\u0001\u0002\u0004!\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u00033\u0012,\u0012A\u0017\u0016\u0003Gm[\u0013\u0001\u0018\t\u0003;\nl\u0011A\u0018\u0006\u0003?\u0002\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0005T\u0013AC1o]>$\u0018\r^5p]&\u00111M\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!\u0002\u0014\b\u0005\u00049\u0013!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001h!\tAW.D\u0001j\u0015\tQ7.\u0001\u0003mC:<'\"\u00017\u0002\t)\fg/Y\u0005\u0003]&\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A9\u0011\u0005%\u0012\u0018BA:+\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\tqc\u000fC\u0004x\u0015\u0005\u0005\t\u0019A9\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005Q\bcA>\u007f]5\tAP\u0003\u0002~U\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005}d(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0002\u0002\fA\u0019\u0011&a\u0002\n\u0007\u0005%!FA\u0004C_>dW-\u00198\t\u000f]d\u0011\u0011!a\u0001]\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r9\u0017\u0011\u0003\u0005\bo6\t\t\u00111\u0001r\u0003!A\u0017m\u001d5D_\u0012,G#A9\u0002\r\u0015\fX/\u00197t)\u0011\t)!a\u0007\t\u000f]|\u0011\u0011!a\u0001]\u0005)\u0001k\\5oiB\u0011\u0001%E\n\u0006#\u0005\r\u0012\u0011\u0006\t\u0004S\u0005\u0015\u0012bAA\u0014U\t1\u0011I\\=SK\u001a\u0004B!a\u000b\u000225\u0011\u0011Q\u0006\u0006\u0004\u0003_Y\u0017AA5p\u0013\rq\u0014Q\u0006\u000b\u0003\u0003?\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002O\u0006)\u0011\r\u001d9msV!\u0011QHA\")\u0011\ty$!\u0012\u0011\t\u0001\u0002\u0011\u0011\t\t\u0004I\u0005\rC!\u0002\u0014\u0015\u0005\u00049\u0003B\u0002!\u0015\u0001\u0004\t\t%A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\u0005-\u0013Q\u000b\u000b\u0005\u0003\u001b\n9\u0006E\u0003*\u0003\u001f\n\u0019&C\u0002\u0002R)\u0012aa\u00149uS>t\u0007c\u0001\u0013\u0002V\u0011)a%\u0006b\u0001O!I\u0011\u0011L\u000b\u0002\u0002\u0003\u0007\u00111L\u0001\u0004q\u0012\u0002\u0004\u0003\u0002\u0011\u0001\u0003'\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0019\u0011\u0007!\f\u0019'C\u0002\u0002f%\u0014aa\u00142kK\u000e$\b"
)
public class Point extends Interval implements Product {
   private final Object value;

   public static Option unapply(final Point x$0) {
      return Point$.MODULE$.unapply(x$0);
   }

   public static Point apply(final Object value) {
      return Point$.MODULE$.apply(value);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object value() {
      return this.value;
   }

   public Closed lowerBound() {
      return new Closed(this.value());
   }

   public Closed upperBound() {
      return new Closed(this.value());
   }

   public Point copy(final Object value) {
      return new Point(value);
   }

   public Object copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "Point";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.value();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Point;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "value";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Point) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Point var4 = (Point)x$1;
               if (BoxesRunTime.equals(this.value(), var4.value()) && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Point(final Object value) {
      this.value = value;
      Product.$init$(this);
   }
}
