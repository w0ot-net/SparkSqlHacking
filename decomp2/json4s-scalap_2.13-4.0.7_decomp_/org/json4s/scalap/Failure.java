package org.json4s.scalap;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<Q\u0001D\u0007\t\u0002R1QAF\u0007\t\u0002^AQ\u0001M\u0001\u0005\u0002EBQAM\u0001\u0005\u0002MBq\u0001N\u0001\u0002\u0002\u0013\u0005S\u0007C\u0004?\u0003\u0005\u0005I\u0011A \t\u000f\r\u000b\u0011\u0011!C\u0001\t\"9!*AA\u0001\n\u0003Z\u0005b\u0002*\u0002\u0003\u0003%\ta\u0015\u0005\b1\u0006\t\t\u0011\"\u0011Z\u0011\u001dQ\u0016!!A\u0005BmCq\u0001X\u0001\u0002\u0002\u0013%Q,A\u0004GC&dWO]3\u000b\u00059y\u0011AB:dC2\f\u0007O\u0003\u0002\u0011#\u00051!n]8oiMT\u0011AE\u0001\u0004_J<7\u0001\u0001\t\u0003+\u0005i\u0011!\u0004\u0002\b\r\u0006LG.\u001e:f'\u0011\t\u0001$\t\u0013\u0011\u0007UI2$\u0003\u0002\u001b\u001b\tIaj\\*vG\u000e,7o\u001d\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\b\u001d>$\b.\u001b8h!\ta\"%\u0003\u0002$;\t9\u0001K]8ek\u000e$\bCA\u0013.\u001d\t13F\u0004\u0002(U5\t\u0001F\u0003\u0002*'\u00051AH]8pizJ\u0011AH\u0005\u0003Yu\tq\u0001]1dW\u0006<W-\u0003\u0002/_\ta1+\u001a:jC2L'0\u00192mK*\u0011A&H\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Q\tQ!\u001a:s_J,\u0012aG\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003Y\u0002\"a\u000e\u001f\u000e\u0003aR!!\u000f\u001e\u0002\t1\fgn\u001a\u0006\u0002w\u0005!!.\u0019<b\u0013\ti\u0004H\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002\u0001B\u0011A$Q\u0005\u0003\u0005v\u00111!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u0012%\u0011\u0005q1\u0015BA$\u001e\u0005\r\te.\u001f\u0005\b\u0013\u001a\t\t\u00111\u0001A\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tA\nE\u0002N!\u0016k\u0011A\u0014\u0006\u0003\u001fv\t!bY8mY\u0016\u001cG/[8o\u0013\t\tfJ\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGC\u0001+X!\taR+\u0003\u0002W;\t9!i\\8mK\u0006t\u0007bB%\t\u0003\u0003\u0005\r!R\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0001)\u0001\u0005u_N#(/\u001b8h)\u00051\u0014\u0001D<sSR,'+\u001a9mC\u000e,G#\u00010\u0011\u0005]z\u0016B\u000119\u0005\u0019y%M[3di\u0002"
)
public final class Failure {
   public static String toString() {
      return Failure$.MODULE$.toString();
   }

   public static int hashCode() {
      return Failure$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return Failure$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return Failure$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return Failure$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return Failure$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return Failure$.MODULE$.productPrefix();
   }

   public static Nothing error() {
      return Failure$.MODULE$.error();
   }

   public static Iterator productElementNames() {
      return Failure$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return Failure$.MODULE$.productElementName(n);
   }

   public static Result orElse(final Function0 other) {
      return Failure$.MODULE$.orElse(other);
   }

   public static NoSuccess flatMap(final Function2 f) {
      return Failure$.MODULE$.flatMap(f);
   }

   public static NoSuccess map(final Function2 f) {
      return Failure$.MODULE$.map(f);
   }

   public static NoSuccess mapOut(final Function1 f) {
      return Failure$.MODULE$.mapOut(f);
   }

   public static NoSuccess map(final Function1 f) {
      return Failure$.MODULE$.map(f);
   }

   public static None toOption() {
      return Failure$.MODULE$.toOption();
   }

   public static Nothing value() {
      return Failure$.MODULE$.value();
   }

   public static Nothing out() {
      return Failure$.MODULE$.out();
   }
}
