package org.json4s;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b\u0001\u0002\r\u001a\u0001zA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f\u0015!!\t\u0001\u0001;\u0011\u0015\u0019\u0005\u0001\"\u0001:\u0011\u001d!\u0005!!A\u0005\u0002\u0015Cqa\u0012\u0001\u0012\u0002\u0013\u0005\u0001\nC\u0004T\u0001\u0005\u0005I\u0011\t+\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAA\u0001\n\u0003\u0019\u0007bB5\u0001\u0003\u0003%\tE\u001b\u0005\bc\u0002\t\t\u0011\"\u0001s\u0011\u001d9\b!!A\u0005BaDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004}\u0001\u0005\u0005I\u0011I?\t\u000fy\u0004\u0011\u0011!C!\u007f\u001eI\u00111A\r\u0002\u0002#\u0005\u0011Q\u0001\u0004\t1e\t\t\u0011#\u0001\u0002\b!1aH\u0005C\u0001\u0003?Aq\u0001 \n\u0002\u0002\u0013\u0015S\u0010C\u0005\u0002\"I\t\t\u0011\"!\u0002$!I\u0011q\u0005\n\u0002\u0002\u0013\u0005\u0015\u0011\u0006\u0005\n\u0003k\u0011\u0012\u0011!C\u0005\u0003o\u0011qA\u0013#pk\ndWM\u0003\u0002\u001b7\u00051!n]8oiMT\u0011\u0001H\u0001\u0004_J<7\u0001A\n\u0006\u0001}\u0019c\u0005\f\t\u0003A\u0005j\u0011!G\u0005\u0003Ee\u0011aA\u0013,bYV,\u0007C\u0001\u0011%\u0013\t)\u0013DA\u0004K\u001dVl'-\u001a:\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011Q&\u000e\b\u0003]Mr!a\f\u001a\u000e\u0003AR!!M\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0013B\u0001\u001b)\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005QB\u0013a\u00018v[V\t!\b\u0005\u0002(w%\u0011A\b\u000b\u0002\u0007\t>,(\r\\3\u0002\t9,X\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0001\u000b\u0005C\u0001\u0011\u0001\u0011\u0015A4\u00011\u0001;\u0005\u00191\u0016\r\\;fg\u00061a/\u00197vKN\fAaY8qsR\u0011\u0001I\u0012\u0005\bq\u0019\u0001\n\u00111\u0001;\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0013\u0016\u0003u)[\u0013a\u0013\t\u0003\u0019Fk\u0011!\u0014\u0006\u0003\u001d>\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005AC\u0013AC1o]>$\u0018\r^5p]&\u0011!+\u0014\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001V!\t16,D\u0001X\u0015\tA\u0016,\u0001\u0003mC:<'\"\u0001.\u0002\t)\fg/Y\u0005\u00039^\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A0\u0011\u0005\u001d\u0002\u0017BA1)\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t!w\r\u0005\u0002(K&\u0011a\r\u000b\u0002\u0004\u0003:L\bb\u00025\u000b\u0003\u0003\u0005\raX\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003-\u00042\u0001\\8e\u001b\u0005i'B\u00018)\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003a6\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u00111O\u001e\t\u0003OQL!!\u001e\u0015\u0003\u000f\t{w\u000e\\3b]\"9\u0001\u000eDA\u0001\u0002\u0004!\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$\"!V=\t\u000f!l\u0011\u0011!a\u0001?\u0006A\u0001.Y:i\u0007>$W\rF\u0001`\u0003!!xn\u0015;sS:<G#A+\u0002\r\u0015\fX/\u00197t)\r\u0019\u0018\u0011\u0001\u0005\bQB\t\t\u00111\u0001e\u0003\u001dQEi\\;cY\u0016\u0004\"\u0001\t\n\u0014\u000bI\tI!!\u0006\u0011\r\u0005-\u0011\u0011\u0003\u001eA\u001b\t\tiAC\u0002\u0002\u0010!\nqA];oi&lW-\u0003\u0003\u0002\u0014\u00055!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u0011qCA\u000f\u001b\t\tIBC\u0002\u0002\u001ce\u000b!![8\n\u0007Y\nI\u0002\u0006\u0002\u0002\u0006\u0005)\u0011\r\u001d9msR\u0019\u0001)!\n\t\u000ba*\u0002\u0019\u0001\u001e\u0002\u000fUt\u0017\r\u001d9msR!\u00111FA\u0019!\u00119\u0013Q\u0006\u001e\n\u0007\u0005=\u0002F\u0001\u0004PaRLwN\u001c\u0005\t\u0003g1\u0012\u0011!a\u0001\u0001\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005e\u0002c\u0001,\u0002<%\u0019\u0011QH,\u0003\r=\u0013'.Z2u\u0001"
)
public class JDouble extends JValue implements JNumber {
   private final double num;

   public static Option unapply(final JDouble x$0) {
      return JDouble$.MODULE$.unapply(x$0);
   }

   public static Function1 andThen(final Function1 g) {
      return JDouble$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JDouble$.MODULE$.compose(g);
   }

   public double num() {
      return this.num;
   }

   public double values() {
      return this.num();
   }

   public JDouble copy(final double num) {
      return new JDouble(num);
   }

   public double copy$default$1() {
      return this.num();
   }

   public String productPrefix() {
      return "JDouble";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.num());
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
      return x$1 instanceof JDouble;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "num";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.num()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof JDouble) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               JDouble var4 = (JDouble)x$1;
               if (this.num() == var4.num() && var4.canEqual(this)) {
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

   public JDouble(final double num) {
      this.num = num;
   }
}
