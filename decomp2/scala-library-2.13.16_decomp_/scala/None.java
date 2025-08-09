package scala;

import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;
import scala.util.Either;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<Q\u0001D\u0007\t\u0002B1QAE\u0007\t\u0002NAQ!K\u0001\u0005\u0002)BQaK\u0001\u0005\u00021Bq!L\u0001\u0002\u0002\u0013\u0005c\u0006C\u00048\u0003\u0005\u0005I\u0011\u0001\u001d\t\u000fq\n\u0011\u0011!C\u0001{!91)AA\u0001\n\u0003\"\u0005bB&\u0002\u0003\u0003%\t\u0001\u0014\u0005\b#\u0006\t\t\u0011\"\u0011S\u0011\u001d\u0019\u0016!!A\u0005BQCq!V\u0001\u0002\u0002\u0013%a+\u0001\u0003O_:,'\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011\u0011#A\u0007\u0002\u001b\t!aj\u001c8f'\u0011\tACG\u000f\u0011\u0007E)r#\u0003\u0002\u0017\u001b\t1q\n\u001d;j_:\u0004\"!\u0005\r\n\u0005ei!a\u0002(pi\"Lgn\u001a\t\u0003#mI!\u0001H\u0007\u0003\u000fA\u0013x\u000eZ;diB\u0011aD\n\b\u0003?\u0011r!\u0001I\u0012\u000e\u0003\u0005R!AI\b\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011BA\u0013\u000e\u0003\u001d\u0001\u0018mY6bO\u0016L!a\n\u0015\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u0015j\u0011A\u0002\u001fj]&$h\bF\u0001\u0011\u0003\r9W\r^\u000b\u0002/\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012a\f\t\u0003aUj\u0011!\r\u0006\u0003eM\nA\u0001\\1oO*\tA'\u0001\u0003kCZ\f\u0017B\u0001\u001c2\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0011\b\u0005\u0002\u0012u%\u00111(\u0004\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003}\u0005\u0003\"!E \n\u0005\u0001k!aA!os\"9!IBA\u0001\u0002\u0004I\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001F!\r1\u0015JP\u0007\u0002\u000f*\u0011\u0001*D\u0001\u000bG>dG.Z2uS>t\u0017B\u0001&H\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u00055\u0003\u0006CA\tO\u0013\tyUBA\u0004C_>dW-\u00198\t\u000f\tC\u0011\u0011!a\u0001}\u0005A\u0001.Y:i\u0007>$W\rF\u0001:\u0003!!xn\u0015;sS:<G#A\u0018\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003]\u0003\"\u0001\r-\n\u0005e\u000b$AB(cU\u0016\u001cG\u000f\u000b\u0003\u00027z{\u0006CA\t]\u0013\tiVB\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKzAa\t\u0015\u0013w(*'J\u0016\u000b\u0003\u00017z{\u0006"
)
public final class None {
   public static String toString() {
      return None$.MODULE$.toString();
   }

   public static int hashCode() {
      return None$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return None$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return None$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return None$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return None$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return None$.MODULE$.productPrefix();
   }

   public static Nothing$ get() {
      return None$.MODULE$.get();
   }

   public static Either toLeft(final Function0 right) {
      return None$.MODULE$.toLeft(right);
   }

   public static Either toRight(final Function0 left) {
      return None$.MODULE$.toRight(left);
   }

   public static List toList() {
      return None$.MODULE$.toList();
   }

   public static Iterator iterator() {
      return None$.MODULE$.iterator();
   }

   public static Tuple3 unzip3(final $less$colon$less asTriple) {
      return None$.MODULE$.unzip3(asTriple);
   }

   public static Tuple2 unzip(final $less$colon$less asPair) {
      return None$.MODULE$.unzip(asPair);
   }

   public static Option zip(final Option that) {
      return None$.MODULE$.zip(that);
   }

   public static Option orElse(final Function0 alternative) {
      return None$.MODULE$.orElse(alternative);
   }

   public static Option collect(final PartialFunction pf) {
      return None$.MODULE$.collect(pf);
   }

   public static void foreach(final Function1 f) {
      None$.MODULE$.foreach(f);
   }

   public static boolean forall(final Function1 p) {
      return None$.MODULE$.forall(p);
   }

   public static boolean exists(final Function1 p) {
      return None$.MODULE$.exists(p);
   }

   public static boolean contains(final Object elem) {
      return None$.MODULE$.contains(elem);
   }

   public static Option.WithFilter withFilter(final Function1 p) {
      return None$.MODULE$.withFilter(p);
   }

   public static boolean nonEmpty() {
      return None$.MODULE$.nonEmpty();
   }

   public static Option filterNot(final Function1 p) {
      return None$.MODULE$.filterNot(p);
   }

   public static Option filter(final Function1 p) {
      return None$.MODULE$.filter(p);
   }

   public static Option flatten(final $less$colon$less ev) {
      return None$.MODULE$.flatten(ev);
   }

   public static Option flatMap(final Function1 f) {
      return None$.MODULE$.flatMap(f);
   }

   public static Object fold(final Function0 ifEmpty, final Function1 f) {
      return None$.MODULE$.fold(ifEmpty, f);
   }

   public static Option map(final Function1 f) {
      return None$.MODULE$.map(f);
   }

   public static Object orNull(final $less$colon$less ev) {
      return None$.MODULE$.orNull(ev);
   }

   public static Object getOrElse(final Function0 default) {
      return None$.MODULE$.getOrElse(default);
   }

   public static int knownSize() {
      return None$.MODULE$.knownSize();
   }

   public static boolean isDefined() {
      return None$.MODULE$.isDefined();
   }

   public static boolean isEmpty() {
      return None$.MODULE$.isEmpty();
   }

   public static Iterator productElementNames() {
      return None$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return None$.MODULE$.productElementName(n);
   }

   public static Stepper stepper(final StepperShape shape) {
      return None$.MODULE$.stepper(shape);
   }
}
