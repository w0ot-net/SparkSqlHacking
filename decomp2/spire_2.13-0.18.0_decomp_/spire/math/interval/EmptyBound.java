package spire.math.interval;

import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeSemigroup;
import java.io.Serializable;
import scala.Function1;
import scala.Function2;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rb\u0001B\n\u0015\u0001nAQ!\u0011\u0001\u0005\u0002\tCq\u0001\u0012\u0001\u0002\u0002\u0013\u0005Q\tC\u0004K\u0001\u0005\u0005I\u0011I&\t\u000fQ\u0003\u0011\u0011!C\u0001+\"9\u0011\fAA\u0001\n\u0003Q\u0006bB/\u0001\u0003\u0003%\tE\u0018\u0005\bK\u0002\t\t\u0011\"\u0001g\u0011\u001dY\u0007!!A\u0005B1DqA\u001c\u0001\u0002\u0002\u0013\u0005s\u000eC\u0004q\u0001\u0005\u0005I\u0011I9\t\u000fI\u0004\u0011\u0011!C!g\u001e9Q\u000fFA\u0001\u0012\u00031haB\n\u0015\u0003\u0003E\ta\u001e\u0005\u0006\u00036!\t! \u0005\ba6\t\t\u0011\"\u0012r\u0011\u001dqX\"!A\u0005\u0002~D\u0011\"!\u0003\u000e\u0003\u0003%\t)a\u0003\t\u0013\u0005eQ\"!A\u0005\n\u0005m!AC#naRL(i\\;oI*\u0011QCF\u0001\tS:$XM\u001d<bY*\u0011q\u0003G\u0001\u0005[\u0006$\bNC\u0001\u001a\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)\"\u0001H\u0015\u0014\u000b\u0001i2EM\u001b\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\r\u0005s\u0017PU3g!\r!SeJ\u0007\u0002)%\u0011a\u0005\u0006\u0002\u0006\u0005>,h\u000e\u001a\t\u0003Q%b\u0001\u0001B\u0003+\u0001\t\u00071FA\u0001B#\tas\u0006\u0005\u0002\u001f[%\u0011af\b\u0002\b\u001d>$\b.\u001b8h!\tq\u0002'\u0003\u00022?\t\u0019\u0011I\\=\u0011\u0005y\u0019\u0014B\u0001\u001b \u0005\u001d\u0001&o\u001c3vGR\u0004\"A\u000e \u000f\u0005]bdB\u0001\u001d<\u001b\u0005I$B\u0001\u001e\u001b\u0003\u0019a$o\\8u}%\t\u0001%\u0003\u0002>?\u00059\u0001/Y2lC\u001e,\u0017BA A\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tit$\u0001\u0004=S:LGO\u0010\u000b\u0002\u0007B\u0019A\u0005A\u0014\u0002\t\r|\u0007/_\u000b\u0003\r&#\u0012a\u0012\t\u0004I\u0001A\u0005C\u0001\u0015J\t\u0015Q#A1\u0001,\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tA\n\u0005\u0002N%6\taJ\u0003\u0002P!\u0006!A.\u00198h\u0015\u0005\t\u0016\u0001\u00026bm\u0006L!a\u0015(\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u00051\u0006C\u0001\u0010X\u0013\tAvDA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u000207\"9A,BA\u0001\u0002\u00041\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001`!\r\u00017mL\u0007\u0002C*\u0011!mH\u0001\u000bG>dG.Z2uS>t\u0017B\u00013b\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005\u001dT\u0007C\u0001\u0010i\u0013\tIwDA\u0004C_>dW-\u00198\t\u000fq;\u0011\u0011!a\u0001_\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\taU\u000eC\u0004]\u0011\u0005\u0005\t\u0019\u0001,\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AV\u0001\ti>\u001cFO]5oOR\tA*\u0001\u0004fcV\fGn\u001d\u000b\u0003ORDq\u0001X\u0006\u0002\u0002\u0003\u0007q&\u0001\u0006F[B$\u0018PQ8v]\u0012\u0004\"\u0001J\u0007\u0014\u00075i\u0002\u0010\u0005\u0002zy6\t!P\u0003\u0002|!\u0006\u0011\u0011n\\\u0005\u0003\u007fi$\u0012A^\u0001\u0006CB\u0004H._\u000b\u0005\u0003\u0003\t9\u0001\u0006\u0002\u0002\u0004A!A\u0005AA\u0003!\rA\u0013q\u0001\u0003\u0006UA\u0011\raK\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\ti!a\u0006\u0015\u0007\u001d\fy\u0001C\u0005\u0002\u0012E\t\t\u00111\u0001\u0002\u0014\u0005\u0019\u0001\u0010\n\u0019\u0011\t\u0011\u0002\u0011Q\u0003\t\u0004Q\u0005]A!\u0002\u0016\u0012\u0005\u0004Y\u0013\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u000f!\ri\u0015qD\u0005\u0004\u0003Cq%AB(cU\u0016\u001cG\u000f"
)
public class EmptyBound implements Bound, Product, Serializable {
   public static boolean unapply(final EmptyBound x$0) {
      return EmptyBound$.MODULE$.unapply(x$0);
   }

   public static EmptyBound apply() {
      return EmptyBound$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Bound map(final Function1 f) {
      return Bound.map$(this, f);
   }

   public Bound combine(final Bound rhs, final Function2 f) {
      return Bound.combine$(this, rhs, f);
   }

   public Bound unary_$minus(final AdditiveGroup ev) {
      return Bound.unary_$minus$(this, ev);
   }

   public Bound reciprocal(final MultiplicativeGroup ev) {
      return Bound.reciprocal$(this, ev);
   }

   public Bound $plus(final Object a, final AdditiveSemigroup ev) {
      return Bound.$plus$(this, (Object)a, ev);
   }

   public Bound $minus(final Object a, final AdditiveGroup ev) {
      return Bound.$minus$(this, (Object)a, ev);
   }

   public Bound $times(final Object a, final MultiplicativeSemigroup ev) {
      return Bound.$times$(this, (Object)a, ev);
   }

   public Bound $div(final Object a, final MultiplicativeGroup ev) {
      return Bound.$div$(this, (Object)a, ev);
   }

   public Bound $plus(final Bound rhs, final AdditiveSemigroup ev) {
      return Bound.$plus$(this, (Bound)rhs, ev);
   }

   public Bound $minus(final Bound rhs, final AdditiveGroup ev) {
      return Bound.$minus$(this, (Bound)rhs, ev);
   }

   public Bound $times(final Bound rhs, final MultiplicativeSemigroup ev) {
      return Bound.$times$(this, (Bound)rhs, ev);
   }

   public Bound $div(final Bound rhs, final MultiplicativeGroup ev) {
      return Bound.$div$(this, (Bound)rhs, ev);
   }

   public EmptyBound copy() {
      return new EmptyBound();
   }

   public String productPrefix() {
      return "EmptyBound";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof EmptyBound;
   }

   public String productElementName(final int x$1) {
      String var2 = (String)Statics.ioobe(x$1);
      return var2;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var2;
      if (x$1 instanceof EmptyBound) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2 && ((EmptyBound)x$1).canEqual(this);
   }

   public EmptyBound() {
      Bound.$init$(this);
      Product.$init$(this);
   }
}
