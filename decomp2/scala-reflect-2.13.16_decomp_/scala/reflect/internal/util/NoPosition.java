package scala.reflect.internal.util;

import scala.Option;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.macros.Attachments;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005m;Qa\u0003\u0007\t\u0002V1Qa\u0006\u0007\t\u0002bAQ\u0001L\u0001\u0005\u00025BqAL\u0001\u0002\u0002\u0013\u0005s\u0006C\u00049\u0003\u0005\u0005I\u0011A\u001d\t\u000fu\n\u0011\u0011!C\u0001}!9A)AA\u0001\n\u0003*\u0005b\u0002'\u0002\u0003\u0003%\t!\u0014\u0005\b%\u0006\t\t\u0011\"\u0011T\u0011\u001d!\u0016!!A\u0005BUCqAV\u0001\u0002\u0002\u0013%q+\u0001\u0006O_B{7/\u001b;j_:T!!\u0004\b\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u001fA\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003#I\tqA]3gY\u0016\u001cGOC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"AF\u0001\u000e\u00031\u0011!BT8Q_NLG/[8o'\u0011\t\u0011\u0004\b\u0011\u0011\u0005YQ\u0012BA\u000e\r\u0005E)f\u000eZ3gS:,G\rU8tSRLwN\u001c\t\u0003;yi\u0011AE\u0005\u0003?I\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\"S9\u0011!e\n\b\u0003G\u0019j\u0011\u0001\n\u0006\u0003KQ\ta\u0001\u0010:p_Rt\u0014\"A\n\n\u0005!\u0012\u0012a\u00029bG.\fw-Z\u0005\u0003U-\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000b\n\u0002\rqJg.\u001b;?)\u0005)\u0012!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u00011!\t\td'D\u00013\u0015\t\u0019D'\u0001\u0003mC:<'\"A\u001b\u0002\t)\fg/Y\u0005\u0003oI\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001\u001e\u0011\u0005uY\u0014B\u0001\u001f\u0013\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ty$\t\u0005\u0002\u001e\u0001&\u0011\u0011I\u0005\u0002\u0004\u0003:L\bbB\"\u0006\u0003\u0003\u0005\rAO\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003\u0019\u00032a\u0012&@\u001b\u0005A%BA%\u0013\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003\u0017\"\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0011a*\u0015\t\u0003;=K!\u0001\u0015\n\u0003\u000f\t{w\u000e\\3b]\"91iBA\u0001\u0002\u0004y\u0014\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003i\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\t\u0001\f\u0005\u000223&\u0011!L\r\u0002\u0007\u001f\nTWm\u0019;"
)
public final class NoPosition {
   public static String toString() {
      return NoPosition$.MODULE$.toString();
   }

   public static int hashCode() {
      return NoPosition$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return NoPosition$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return NoPosition$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return NoPosition$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return NoPosition$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return NoPosition$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return NoPosition$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return NoPosition$.MODULE$.productElementName(n);
   }

   public static boolean samePointAs(final Position that) {
      return NoPosition$.MODULE$.samePointAs(that);
   }

   public static Nothing end() {
      return NoPosition$.MODULE$.end();
   }

   public static Nothing point() {
      return NoPosition$.MODULE$.point();
   }

   public static Nothing start() {
      return NoPosition$.MODULE$.start();
   }

   public static NoSourceFile$ source() {
      return NoPosition$.MODULE$.source();
   }

   public static boolean isRange() {
      return NoPosition$.MODULE$.isRange();
   }

   public static boolean isDefined() {
      return NoPosition$.MODULE$.isDefined();
   }

   public static Attachments withPos(final Position newPos) {
      return NoPosition$.MODULE$.withPos(newPos);
   }

   public static Position pos() {
      return NoPosition$.MODULE$.pos();
   }

   /** @deprecated */
   public static int endOrPoint() {
      return NoPosition$.MODULE$.endOrPoint();
   }

   /** @deprecated */
   public static int startOrPoint() {
      return NoPosition$.MODULE$.startOrPoint();
   }

   /** @deprecated */
   public static Position withSource(final SourceFile source, final int shift) {
      return NoPosition$.MODULE$.withSource(source, shift);
   }

   /** @deprecated */
   public static Tuple2 lineWithCarat(final int maxWidth) {
      return NoPosition$.MODULE$.lineWithCarat(maxWidth);
   }

   /** @deprecated */
   public static Position inUltimateSource(final SourceFile source) {
      return NoPosition$.MODULE$.inUltimateSource(source);
   }

   /** @deprecated */
   public static String dbgString() {
      return NoPosition$.MODULE$.dbgString();
   }

   /** @deprecated */
   public static int safeLine() {
      return NoPosition$.MODULE$.safeLine();
   }

   /** @deprecated */
   public static Position toSingleLine() {
      return NoPosition$.MODULE$.toSingleLine();
   }

   /** @deprecated */
   public static Option offset() {
      return NoPosition$.MODULE$.offset();
   }

   public static String show() {
      return NoPosition$.MODULE$.show();
   }

   public static String showDebug() {
      return NoPosition$.MODULE$.showDebug();
   }

   public static String showError(final String msg) {
      return NoPosition$.MODULE$.showError(msg);
   }

   /** @deprecated */
   public static String lineCarat() {
      return NoPosition$.MODULE$.lineCarat();
   }

   public static String lineCaret() {
      return NoPosition$.MODULE$.lineCaret();
   }

   public static String lineContent() {
      return NoPosition$.MODULE$.lineContent();
   }

   public static int column() {
      return NoPosition$.MODULE$.column();
   }

   public static int line() {
      return NoPosition$.MODULE$.line();
   }

   public static boolean overlaps(final Position pos) {
      return NoPosition$.MODULE$.overlaps(pos);
   }

   public static boolean sameRange(final Position pos) {
      return NoPosition$.MODULE$.sameRange(pos);
   }

   public static boolean properlyPrecedes(final Position pos) {
      return NoPosition$.MODULE$.properlyPrecedes(pos);
   }

   public static boolean precedes(final Position pos) {
      return NoPosition$.MODULE$.precedes(pos);
   }

   public static boolean properlyIncludes(final Position pos) {
      return NoPosition$.MODULE$.properlyIncludes(pos);
   }

   public static boolean includes(final Position pos) {
      return NoPosition$.MODULE$.includes(pos);
   }

   public static Position union(final Position pos) {
      return NoPosition$.MODULE$.union(pos);
   }

   public static Position $up$bar(final Position that) {
      return NoPosition$.MODULE$.$up$bar(that);
   }

   public static Position $bar$up(final Position that) {
      return NoPosition$.MODULE$.$bar$up(that);
   }

   public static Position $up(final int point) {
      return NoPosition$.MODULE$.$up(point);
   }

   public static Position $bar(final Position that) {
      return NoPosition$.MODULE$.$bar(that);
   }

   public static Position focusEnd() {
      return NoPosition$.MODULE$.focusEnd();
   }

   public static Position focus() {
      return NoPosition$.MODULE$.focus();
   }

   public static Position focusStart() {
      return NoPosition$.MODULE$.focusStart();
   }

   public static Position withShift(final int shift) {
      return NoPosition$.MODULE$.withShift(shift);
   }

   public static Position withSource(final SourceFile source) {
      return NoPosition$.MODULE$.withSource(source);
   }

   public static Position withEnd(final int end) {
      return NoPosition$.MODULE$.withEnd(end);
   }

   public static Position withPoint(final int point) {
      return NoPosition$.MODULE$.withPoint(point);
   }

   public static Position withStart(final int start) {
      return NoPosition$.MODULE$.withStart(start);
   }

   public static Position makeTransparentIf(final boolean cond) {
      return NoPosition$.MODULE$.makeTransparentIf(cond);
   }

   public static Position makeTransparent() {
      return NoPosition$.MODULE$.makeTransparent();
   }

   public static int pointOrElse(final int alt) {
      return NoPosition$.MODULE$.pointOrElse(alt);
   }

   public static boolean isOpaqueRange() {
      return NoPosition$.MODULE$.isOpaqueRange();
   }

   public static boolean isOffset() {
      return NoPosition$.MODULE$.isOffset();
   }

   public static boolean isTransparent() {
      return NoPosition$.MODULE$.isTransparent();
   }

   public static Position finalPosition() {
      return NoPosition$.MODULE$.finalPosition();
   }

   public static boolean isEmpty() {
      return NoPosition$.MODULE$.isEmpty();
   }

   public static Attachments remove(final ClassTag evidence$9) {
      return NoPosition$.MODULE$.remove(evidence$9);
   }

   public static Attachments update(final Object newAtt, final ClassTag evidence$8) {
      return NoPosition$.MODULE$.update(newAtt, evidence$8);
   }

   public static boolean contains(final ClassTag evidence$7) {
      return NoPosition$.MODULE$.contains(evidence$7);
   }

   public static Option get(final ClassTag evidence$6) {
      return NoPosition$.MODULE$.get(evidence$6);
   }

   public static scala.collection.immutable.Set all() {
      return NoPosition$.MODULE$.all();
   }

   public static Attachments cloneAttachments() {
      return NoPosition$.MODULE$.cloneAttachments();
   }

   public static boolean containsElement(final Object element) {
      return NoPosition$.MODULE$.containsElement(element);
   }

   public static Attachments addElement(final Object attachment) {
      return NoPosition$.MODULE$.addElement(attachment);
   }

   public static Attachments removeElement(final Object attachment) {
      return NoPosition$.MODULE$.removeElement(attachment);
   }
}
