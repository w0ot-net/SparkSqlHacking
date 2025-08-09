package org.json4s;

import scala.None;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mr!\u0002\t\u0012\u0011\u00033b!\u0002\r\u0012\u0011\u0003K\u0002\"\u0002\u001a\u0002\t\u0003\u0019\u0004b\u0002\u001b\u0002\u0005\u0004%\t!\u000e\u0005\u0007\r\u0006\u0001\u000b\u0011\u0002\u001c\t\u000b9\u000bA\u0011A(\t\u000bi\u000bA\u0011A.\t\u000b!\fA\u0011I5\t\u000fM\f\u0011\u0011!C!i\"9A0AA\u0001\n\u0003i\b\"CA\u0002\u0003\u0005\u0005I\u0011AA\u0003\u0011%\tY!AA\u0001\n\u0003\ni\u0001C\u0005\u0002\u001c\u0005\t\t\u0011\"\u0001\u0002\u001e!I\u0011\u0011E\u0001\u0002\u0002\u0013\u0005\u00131\u0005\u0005\n\u0003K\t\u0011\u0011!C!\u0003OA\u0011\"!\u000b\u0002\u0003\u0003%I!a\u000b\u0002\u00179{G+\u001f9f\u0011&tGo\u001d\u0006\u0003%M\taA[:p]R\u001a(\"\u0001\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005]\tQ\"A\t\u0003\u00179{G+\u001f9f\u0011&tGo]\n\u0006\u0003i\u00013E\n\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]\t\u0013B\u0001\u0012\u0012\u0005%!\u0016\u0010]3IS:$8\u000f\u0005\u0002\u001cI%\u0011Q\u0005\b\u0002\b!J|G-^2u!\t9sF\u0004\u0002)[9\u0011\u0011\u0006L\u0007\u0002U)\u00111&F\u0001\u0007yI|w\u000e\u001e \n\u0003uI!A\f\u000f\u0002\u000fA\f7m[1hK&\u0011\u0001'\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003]q\ta\u0001P5oSRtD#\u0001\f\u0002\u000b!Lg\u000e^:\u0016\u0003Y\u00022aJ\u001c:\u0013\tA\u0014G\u0001\u0003MSN$\bG\u0001\u001eE!\rYtH\u0011\b\u0003yu\u0002\"!\u000b\u000f\n\u0005yb\u0012A\u0002)sK\u0012,g-\u0003\u0002A\u0003\n)1\t\\1tg*\u0011a\b\b\t\u0003\u0007\u0012c\u0001\u0001B\u0005F\t\u0005\u0005\t\u0011!B\u0001\u000f\n\u0019q\fJ\u0019\u0002\r!Lg\u000e^:!#\tA5\n\u0005\u0002\u001c\u0013&\u0011!\n\b\u0002\b\u001d>$\b.\u001b8h!\tYB*\u0003\u0002N9\t\u0019\u0011I\\=\u0002\u000f!Lg\u000e\u001e$peR\u0011\u0001k\u0015\t\u00037ES!A\u0015\u000f\u0002\t9{g.\u001a\u0005\u0006)\u0016\u0001\r!V\u0001\u0006G2\f'P\u001f\u0019\u0003-b\u00032aO X!\t\u0019\u0005\fB\u0005Z'\u0006\u0005\t\u0011!B\u0001\u000f\n\u0019q\f\n\u001a\u0002\u0011\rd\u0017m]:G_J$2\u0001\u0015/b\u0011\u0015if\u00011\u0001_\u0003\u0011A\u0017N\u001c;\u0011\u0005mz\u0016B\u00011B\u0005\u0019\u0019FO]5oO\")!M\u0002a\u0001G\u00061\u0001/\u0019:f]R\u0004$\u0001\u001a4\u0011\u0007mzT\r\u0005\u0002DM\u0012Iq-YA\u0001\u0002\u0003\u0015\ta\u0012\u0002\u0004?\u0012\u001a\u0014AE:i_VdG-\u0012=ue\u0006\u001cG\u000fS5oiN$\"A[7\u0011\u0005mY\u0017B\u00017\u001d\u0005\u001d\u0011un\u001c7fC:DQ\u0001V\u0004A\u00029\u0004$a\\9\u0011\u0007mz\u0004\u000f\u0005\u0002Dc\u0012I!/\\A\u0001\u0002\u0003\u0015\ta\u0012\u0002\u0004?\u0012\"\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001v!\t180D\u0001x\u0015\tA\u00180\u0001\u0003mC:<'\"\u0001>\u0002\t)\fg/Y\u0005\u0003A^\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A \t\u00037}L1!!\u0001\u001d\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rY\u0015q\u0001\u0005\t\u0003\u0013Q\u0011\u0011!a\u0001}\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0004\u0011\u000b\u0005E\u0011qC&\u000e\u0005\u0005M!bAA\u000b9\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005e\u00111\u0003\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000eF\u0002k\u0003?A\u0001\"!\u0003\r\u0003\u0003\u0005\raS\u0001\tQ\u0006\u001c\bnQ8eKR\ta0\u0001\u0005u_N#(/\u001b8h)\u0005)\u0018\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u0017!\r1\u0018qF\u0005\u0004\u0003c9(AB(cU\u0016\u001cG\u000f"
)
public final class NoTypeHints {
   public static String toString() {
      return NoTypeHints$.MODULE$.toString();
   }

   public static int hashCode() {
      return NoTypeHints$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return NoTypeHints$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return NoTypeHints$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return NoTypeHints$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return NoTypeHints$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return NoTypeHints$.MODULE$.productPrefix();
   }

   public static boolean shouldExtractHints(final Class clazz) {
      return NoTypeHints$.MODULE$.shouldExtractHints(clazz);
   }

   public static None classFor(final String hint, final Class parent) {
      return NoTypeHints$.MODULE$.classFor(hint, parent);
   }

   public static None hintFor(final Class clazz) {
      return NoTypeHints$.MODULE$.hintFor(clazz);
   }

   public static List hints() {
      return NoTypeHints$.MODULE$.hints();
   }

   public static Iterator productElementNames() {
      return NoTypeHints$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return NoTypeHints$.MODULE$.productElementName(n);
   }

   public static TypeHints $plus(final TypeHints hints) {
      return NoTypeHints$.MODULE$.$plus(hints);
   }

   public static List components() {
      return NoTypeHints$.MODULE$.components();
   }

   public static PartialFunction serialize() {
      return NoTypeHints$.MODULE$.serialize();
   }

   public static PartialFunction deserialize() {
      return NoTypeHints$.MODULE$.deserialize();
   }

   public static boolean containsHint(final Class clazz) {
      return NoTypeHints$.MODULE$.containsHint(clazz);
   }

   public static Option typeHintFieldNameForClass(final Class clazz) {
      return NoTypeHints$.MODULE$.typeHintFieldNameForClass(clazz);
   }

   public static Option typeHintFieldNameForHint(final String hint, final Class parent) {
      return NoTypeHints$.MODULE$.typeHintFieldNameForHint(hint, parent);
   }

   public static boolean isTypeHintField(final Tuple2 f, final Class parent) {
      return NoTypeHints$.MODULE$.isTypeHintField(f, parent);
   }

   public static String typeHintFieldName() {
      return NoTypeHints$.MODULE$.typeHintFieldName();
   }
}
