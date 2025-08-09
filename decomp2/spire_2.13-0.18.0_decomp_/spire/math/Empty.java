package spire.math;

import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.math.interval.EmptyBound;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb\u0001\u0002\u000b\u0016\u0001jAa!\u0010\u0001\u0005\u0002]q\u0004\"\u0002!\u0001\t\u0003\t\u0005\"\u0002%\u0001\t\u0003\t\u0005bB%\u0001\u0003\u0003%\tA\u0013\u0005\b\u001f\u0002\t\t\u0011\"\u0011Q\u0011\u001dI\u0006!!A\u0005\u0002iCqA\u0018\u0001\u0002\u0002\u0013\u0005q\fC\u0004c\u0001\u0005\u0005I\u0011I2\t\u000f)\u0004\u0011\u0011!C\u0001W\"9\u0001\u000fAA\u0001\n\u0003\n\bbB:\u0001\u0003\u0003%\t\u0005\u001e\u0005\bk\u0002\t\t\u0011\"\u0011w\u000f\u001dAX#!A\t\u0002e4q\u0001F\u000b\u0002\u0002#\u0005!\u0010\u0003\u0004>\u001d\u0011\u0005\u0011q\u0001\u0005\n\u0003\u0013q\u0011\u0011!C#\u0003\u0017A\u0011\"!\u0004\u000f\u0003\u0003%\t)a\u0004\t\u0013\u0005ea\"!A\u0005\u0002\u0006m\u0001\"CA\u0015\u001d\u0005\u0005I\u0011BA\u0016\u0005\u0015)U\u000e\u001d;z\u0015\t1r#\u0001\u0003nCRD'\"\u0001\r\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u00111DI\n\u0005\u0001qq\u0013\u0007E\u0002\u001e=\u0001j\u0011!F\u0005\u0003?U\u0011\u0001\"\u00138uKJ4\u0018\r\u001c\t\u0003C\tb\u0001\u0001B\u0003$\u0001\t\u0007AEA\u0001B#\t)3\u0006\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsEA\u0004O_RD\u0017N\\4\u0011\u0005\u0019b\u0013BA\u0017(\u0005\r\te.\u001f\t\u0003M=J!\u0001M\u0014\u0003\u000fA\u0013x\u000eZ;diB\u0011!G\u000f\b\u0003gar!\u0001N\u001c\u000e\u0003UR!AN\r\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013BA\u001d(\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000f\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005e:\u0013A\u0002\u001fj]&$h\bF\u0001@!\ri\u0002\u0001I\u0001\u000bY><XM\u001d\"pk:$W#\u0001\"\u0011\u0007\r3\u0005%D\u0001E\u0015\t)U#\u0001\u0005j]R,'O^1m\u0013\t9EI\u0001\u0006F[B$\u0018PQ8v]\u0012\f!\"\u001e9qKJ\u0014u.\u001e8e\u0003\u0011\u0019w\u000e]=\u0016\u0005-sE#\u0001'\u0011\u0007u\u0001Q\n\u0005\u0002\"\u001d\u0012)1\u0005\u0002b\u0001I\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u0015\t\u0003%^k\u0011a\u0015\u0006\u0003)V\u000bA\u0001\\1oO*\ta+\u0001\u0003kCZ\f\u0017B\u0001-T\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t1\f\u0005\u0002'9&\u0011Ql\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003W\u0001Dq!Y\u0004\u0002\u0002\u0003\u00071,A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002IB\u0019Q\r[\u0016\u000e\u0003\u0019T!aZ\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002jM\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\taw\u000e\u0005\u0002'[&\u0011an\n\u0002\b\u0005>|G.Z1o\u0011\u001d\t\u0017\"!AA\u0002-\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011\u0011K\u001d\u0005\bC*\t\t\u00111\u0001\\\u0003!A\u0017m\u001d5D_\u0012,G#A.\u0002\r\u0015\fX/\u00197t)\taw\u000fC\u0004b\u0019\u0005\u0005\t\u0019A\u0016\u0002\u000b\u0015k\u0007\u000f^=\u0011\u0005uq1c\u0001\b|}B\u0011a\u0005`\u0005\u0003{\u001e\u0012a!\u00118z%\u00164\u0007cA@\u0002\u00065\u0011\u0011\u0011\u0001\u0006\u0004\u0003\u0007)\u0016AA5p\u0013\rY\u0014\u0011\u0001\u000b\u0002s\u0006AAo\\*ue&tw\rF\u0001R\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\t\t\"a\u0006\u0015\u0005\u0005M\u0001\u0003B\u000f\u0001\u0003+\u00012!IA\f\t\u0015\u0019\u0013C1\u0001%\u0003\u001d)h.\u00199qYf,B!!\b\u0002(Q\u0019A.a\b\t\u0013\u0005\u0005\"#!AA\u0002\u0005\r\u0012a\u0001=%aA!Q\u0004AA\u0013!\r\t\u0013q\u0005\u0003\u0006GI\u0011\r\u0001J\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003[\u00012AUA\u0018\u0013\r\t\td\u0015\u0002\u0007\u001f\nTWm\u0019;"
)
public class Empty extends Interval implements Product {
   public static boolean unapply(final Empty x$0) {
      return Empty$.MODULE$.unapply(x$0);
   }

   public static Empty apply() {
      return Empty$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public EmptyBound lowerBound() {
      return new EmptyBound();
   }

   public EmptyBound upperBound() {
      return new EmptyBound();
   }

   public Empty copy() {
      return new Empty();
   }

   public String productPrefix() {
      return "Empty";
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
      return x$1 instanceof Empty;
   }

   public String productElementName(final int x$1) {
      String var2 = (String)Statics.ioobe(x$1);
      return var2;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var2;
      if (x$1 instanceof Empty) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2 && ((Empty)x$1).canEqual(this);
   }

   public Empty() {
      Product.$init$(this);
   }
}
