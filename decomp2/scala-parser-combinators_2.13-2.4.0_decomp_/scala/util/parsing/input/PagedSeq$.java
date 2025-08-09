package scala.util.parsing.input;

import java.io.File;
import java.io.FileReader;
import java.lang.invoke.SerializedLambda;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.io.Source;
import scala.reflect.ClassTag;
import scala.runtime.BooleanRef;
import scala.runtime.BoxesRunTime;

public final class PagedSeq$ {
   public static final PagedSeq$ MODULE$ = new PagedSeq$();

   public final int UndeterminedEnd() {
      return Integer.MAX_VALUE;
   }

   public PagedSeq fromIterator(final Iterator source, final ClassTag evidence$1) {
      return new PagedSeq((data, start, len) -> BoxesRunTime.boxToInteger($anonfun$fromIterator$1(source, data, BoxesRunTime.unboxToInt(start), BoxesRunTime.unboxToInt(len))), evidence$1);
   }

   public PagedSeq fromIterable(final Iterable source, final ClassTag evidence$2) {
      return this.fromIterator(source.iterator(), evidence$2);
   }

   public PagedSeq fromStrings(final Iterator source) {
      return this.fromIterator(source.flatMap((x$1) -> .MODULE$.iterator$extension(scala.Predef..MODULE$.augmentString(x$1))), scala.reflect.ClassTag..MODULE$.Char());
   }

   public PagedSeq fromStrings(final Iterable source) {
      return this.fromStrings(source.iterator());
   }

   public PagedSeq fromLines(final Iterator source) {
      BooleanRef isFirst = BooleanRef.create(true);
      return this.fromStrings(source.map((line) -> {
         if (isFirst.elem) {
            isFirst.elem = false;
            return line;
         } else {
            return (new StringBuilder(1)).append("\n").append(line).toString();
         }
      }));
   }

   public PagedSeq fromLines(final Iterable source) {
      return this.fromLines(source.iterator());
   }

   public PagedSeq fromReader(final java.io.Reader source) {
      return new PagedSeq((x$2, x$3, x$4) -> BoxesRunTime.boxToInteger($anonfun$fromReader$1(source, x$2, BoxesRunTime.unboxToInt(x$3), BoxesRunTime.unboxToInt(x$4))), scala.reflect.ClassTag..MODULE$.Char());
   }

   public PagedSeq fromFile(final File source) {
      return this.fromReader(new FileReader(source));
   }

   public PagedSeq fromFile(final String source) {
      return this.fromFile(new File(source));
   }

   public PagedSeq fromSource(final Source source) {
      return this.fromLines(source.getLines());
   }

   // $FF: synthetic method
   public static final int $anonfun$fromIterator$1(final Iterator source$1, final Object data, final int start, final int len) {
      int i;
      for(i = 0; i < len && source$1.hasNext(); ++i) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(data, start + i, source$1.next());
      }

      return i == 0 ? -1 : i;
   }

   // $FF: synthetic method
   public static final int $anonfun$fromReader$1(final java.io.Reader source$2, final char[] x$2, final int x$3, final int x$4) {
      return source$2.read(x$2, x$3, x$4);
   }

   private PagedSeq$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
