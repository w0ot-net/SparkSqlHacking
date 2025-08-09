package scala.reflect.internal;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00051:QAB\u0004\t\u000291Q\u0001E\u0004\t\u0002EAQ!F\u0001\u0005\u0002YAQaF\u0001\u0005\u0002aAQ!I\u0001\u0005B\tBQaJ\u0001\u0005\u0002!\nqAT8QQ\u0006\u001cXM\u0003\u0002\t\u0013\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u000b\u0017\u00059!/\u001a4mK\u000e$(\"\u0001\u0007\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011q\"A\u0007\u0002\u000f\t9aj\u001c)iCN,7CA\u0001\u0013!\ty1#\u0003\u0002\u0015\u000f\t)\u0001\u000b[1tK\u00061A(\u001b8jiz\"\u0012AD\u0001\u0005]\u0006lW-F\u0001\u001a!\tQr$D\u0001\u001c\u0015\taR$\u0001\u0003mC:<'\"\u0001\u0010\u0002\t)\fg/Y\u0005\u0003Am\u0011aa\u0015;sS:<\u0017aD6fKB\u001cH+\u001f9f!\u0006\u0014\u0018-\\:\u0016\u0003\r\u0002\"\u0001J\u0013\u000e\u0003-I!AJ\u0006\u0003\u000f\t{w\u000e\\3b]\u0006\u0019!/\u001e8\u0015\u0003%\u0002\"\u0001\n\u0016\n\u0005-Z!\u0001B+oSR\u0004"
)
public final class NoPhase {
   public static void run() {
      NoPhase$.MODULE$.run();
   }

   public static boolean keepsTypeParams() {
      return NoPhase$.MODULE$.keepsTypeParams();
   }

   public static String name() {
      return NoPhase$.MODULE$.name();
   }

   public static int compare(final Phase that) {
      return NoPhase$.MODULE$.compare(that);
   }

   public static boolean equals(final Object other) {
      return NoPhase$.MODULE$.equals(other);
   }

   public static int hashCode() {
      return NoPhase$.MODULE$.hashCode();
   }

   public static String toString() {
      return NoPhase$.MODULE$.toString();
   }

   public static boolean assignsFields() {
      return NoPhase$.MODULE$.assignsFields();
   }

   public static boolean refChecked() {
      return NoPhase$.MODULE$.refChecked();
   }

   public static boolean specialized() {
      return NoPhase$.MODULE$.specialized();
   }

   public static boolean flatClasses() {
      return NoPhase$.MODULE$.flatClasses();
   }

   public static boolean erasedTypes() {
      return NoPhase$.MODULE$.erasedTypes();
   }

   public static boolean checkable() {
      return NoPhase$.MODULE$.checkable();
   }

   public static String description() {
      return NoPhase$.MODULE$.description();
   }

   public static Iterator iterator() {
      return NoPhase$.MODULE$.iterator();
   }

   public static boolean hasNext() {
      return NoPhase$.MODULE$.hasNext();
   }

   public static Phase next() {
      return NoPhase$.MODULE$.next();
   }

   public static long flagMask() {
      return NoPhase$.MODULE$.flagMask();
   }

   public static long fmask() {
      return NoPhase$.MODULE$.fmask();
   }

   public static long newFlags() {
      return NoPhase$.MODULE$.newFlags();
   }

   public static long nextFlags() {
      return NoPhase$.MODULE$.nextFlags();
   }

   public static int id() {
      return NoPhase$.MODULE$.id();
   }

   public static Phase prev() {
      return NoPhase$.MODULE$.prev();
   }

   public static int compareTo(final Object that) {
      return NoPhase$.MODULE$.compareTo(that);
   }

   public static boolean $greater$eq(final Object that) {
      return NoPhase$.MODULE$.$greater$eq(that);
   }

   public static boolean $less$eq(final Object that) {
      return NoPhase$.MODULE$.$less$eq(that);
   }

   public static boolean $greater(final Object that) {
      return NoPhase$.MODULE$.$greater(that);
   }

   public static boolean $less(final Object that) {
      return NoPhase$.MODULE$.$less(that);
   }
}
