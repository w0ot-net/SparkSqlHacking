package scala.xml.dtd;

import java.io.Serializable;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.xml.dtd.impl.Base;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=c\u0001\u0002\f\u0018\u0001zA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0005\u000e\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005k!)\u0001\t\u0001C\u0001\u0003\")A\t\u0001C!\u000b\"9A\nAA\u0001\n\u0003i\u0005bB(\u0001#\u0003%\t\u0001\u0015\u0005\b7\u0002\t\t\u0011\"\u0011]\u0011\u001d)\u0007!!A\u0005\u0002\u0019DqA\u001b\u0001\u0002\u0002\u0013\u00051\u000eC\u0004r\u0001\u0005\u0005I\u0011\t:\t\u000fe\u0004\u0011\u0011!C\u0001u\"Aq\u0010AA\u0001\n\u0003\n\t\u0001C\u0005\u0002\u0006\u0001\t\t\u0011\"\u0011\u0002\b!I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00131B\u0004\n\u0003\u001f9\u0012\u0011!E\u0001\u0003#1\u0001BF\f\u0002\u0002#\u0005\u00111\u0003\u0005\u0007\u0001B!\t!a\u000b\t\u0013\u00055\u0002#!A\u0005F\u0005=\u0002\"CA\u0019!\u0005\u0005I\u0011QA\u001a\u0011%\t9\u0004EA\u0001\n\u0003\u000bI\u0004C\u0005\u0002FA\t\t\u0011\"\u0003\u0002H\t)Q*\u0013-F\t*\u0011\u0001$G\u0001\u0004IR$'B\u0001\u000e\u001c\u0003\rAX\u000e\u001c\u0006\u00029\u0005)1oY1mC\u000e\u00011\u0003\u0002\u0001 G\u001d\u0002\"\u0001I\u0011\u000e\u0003]I!AI\f\u0003\u001f\u00113\u0015iQ8oi\u0016tG/T8eK2\u0004\"\u0001J\u0013\u000e\u0003mI!AJ\u000e\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001\u0006\r\b\u0003S9r!AK\u0017\u000e\u0003-R!\u0001L\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0012BA\u0018\u001c\u0003\u001d\u0001\u0018mY6bO\u0016L!!\r\u001a\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005=Z\u0012!\u0001:\u0016\u0003U\u0002\"AN\u001d\u000f\u0005\u0001:\u0014B\u0001\u001d\u0018\u00031\u0019uN\u001c;f]Rlu\u000eZ3m\u0013\tQ4H\u0001\u0004SK\u001e,\u0005\u0010]\u0005\u0003yu\u0012AAQ1tK*\u0011ahF\u0001\u0005S6\u0004H.\u0001\u0002sA\u00051A(\u001b8jiz\"\"AQ\"\u0011\u0005\u0001\u0002\u0001\"B\u001a\u0004\u0001\u0004)\u0014a\u00032vS2$7\u000b\u001e:j]\u001e$\"A\u0012&\u0011\u0005\u001dCeB\u0001\u0013/\u0013\tI%GA\u0007TiJLgn\u001a\"vS2$WM\u001d\u0005\u0006\u0017\u0012\u0001\rAR\u0001\u0003g\n\fAaY8qsR\u0011!I\u0014\u0005\bg\u0015\u0001\n\u00111\u00016\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0015\u0016\u0003kI[\u0013a\u0015\t\u0003)fk\u0011!\u0016\u0006\u0003-^\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005a[\u0012AC1o]>$\u0018\r^5p]&\u0011!,\u0016\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001^!\tq6-D\u0001`\u0015\t\u0001\u0017-\u0001\u0003mC:<'\"\u00012\u0002\t)\fg/Y\u0005\u0003I~\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A4\u0011\u0005\u0011B\u0017BA5\u001c\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\taw\u000e\u0005\u0002%[&\u0011an\u0007\u0002\u0004\u0003:L\bb\u00029\n\u0003\u0003\u0005\raZ\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003M\u00042\u0001^<m\u001b\u0005)(B\u0001<\u001c\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003qV\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u00111P \t\u0003IqL!!`\u000e\u0003\u000f\t{w\u000e\\3b]\"9\u0001oCA\u0001\u0002\u0004a\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2!XA\u0002\u0011\u001d\u0001H\"!AA\u0002\u001d\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002O\u00061Q-];bYN$2a_A\u0007\u0011\u001d\u0001h\"!AA\u00021\fQ!T%Y\u000b\u0012\u0003\"\u0001\t\t\u0014\u000bA\t)\"!\t\u0011\r\u0005]\u0011QD\u001bC\u001b\t\tIBC\u0002\u0002\u001cm\tqA];oi&lW-\u0003\u0003\u0002 \u0005e!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u00111EA\u0015\u001b\t\t)CC\u0002\u0002(\u0005\f!![8\n\u0007E\n)\u0003\u0006\u0002\u0002\u0012\u0005AAo\\*ue&tw\rF\u0001^\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0011\u0015Q\u0007\u0005\u0006gM\u0001\r!N\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tY$!\u0011\u0011\t\u0011\ni$N\u0005\u0004\u0003\u007fY\"AB(qi&|g\u000e\u0003\u0005\u0002DQ\t\t\u00111\u0001C\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0013\u00022AXA&\u0013\r\tie\u0018\u0002\u0007\u001f\nTWm\u0019;"
)
public class MIXED extends DFAContentModel implements Product, Serializable {
   private final Base.RegExp r;

   public static Option unapply(final MIXED x$0) {
      return MIXED$.MODULE$.unapply(x$0);
   }

   public static MIXED apply(final Base.RegExp r) {
      return MIXED$.MODULE$.apply(r);
   }

   public static Function1 andThen(final Function1 g) {
      return MIXED$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return MIXED$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Base.RegExp r() {
      return this.r;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      Base.RegExp var4 = this.r();
      if (var4 instanceof Base.Alt) {
         Base.Alt var5 = (Base.Alt)var4;
         Some var6 = ContentModel$.MODULE$.Alt().unapplySeq(var5);
         if (!var6.isEmpty()) {
            Seq rs = (Seq)var6.get();
            Base.Alt newAlt = ContentModel$.MODULE$.Alt().apply((Seq)rs.drop(1));
            sb.append("(#PCDATA|");
            ContentModel$.MODULE$.buildString((Base.RegExp)newAlt, sb);
            return sb.append(")*");
         }
      }

      throw new MatchError(var4);
   }

   public MIXED copy(final Base.RegExp r) {
      return new MIXED(r);
   }

   public Base.RegExp copy$default$1() {
      return this.r();
   }

   public String productPrefix() {
      return "MIXED";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.r();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof MIXED;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "r";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof MIXED) {
               label40: {
                  MIXED var4 = (MIXED)x$1;
                  Base.RegExp var10000 = this.r();
                  Base.RegExp var5 = var4.r();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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

   public MIXED(final Base.RegExp r) {
      this.r = r;
      Product.$init$(this);
   }
}
