package scala.reflect.internal.util;

import scala.Array.;
import scala.collection.Iterator;

public final class NoSourceFile$ extends SourceFile {
   public static final NoSourceFile$ MODULE$ = new NoSourceFile$();

   public char[] content() {
      return (char[]).MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Char());
   }

   public NoFile$ file() {
      return NoFile$.MODULE$;
   }

   public boolean isLineBreak(final int idx) {
      return false;
   }

   public boolean isEndOfLine(final int idx) {
      return false;
   }

   public boolean isSelfContained() {
      return true;
   }

   public int length() {
      return -1;
   }

   public int lineCount() {
      return 0;
   }

   public int offsetToLine(final int offset) {
      return -1;
   }

   public int lineToOffset(final int index) {
      return -1;
   }

   public Iterator lines(final int start, final int end) {
      if (scala.package..MODULE$.Iterator() == null) {
         throw null;
      } else {
         return scala.collection.Iterator..scala$collection$Iterator$$_empty;
      }
   }

   public String toString() {
      return "<no source file>";
   }

   private NoSourceFile$() {
   }
}
