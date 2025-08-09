package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import scala.Function1;
import scala.Function2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001;a!\u0002\u0004\t\u0002\u0019\u0001bA\u0002\n\u0007\u0011\u000311\u0003C\u0003\u001b\u0003\u0011\u0005A\u0004C\u0003\u001e\u0003\u0011\u0005a\u0004C\u0003\u001e\u0003\u0011\u0005a'\u0001\u0007BaBd\u00170\u00138QY\u0006\u001cWM\u0003\u0002\b\u0011\u0005\u0019\u0011M\u001c8\u000b\u0005%Q\u0011AA7m\u0015\tYA\"A\u0003ta\u0006\u00148N\u0003\u0002\u000e\u001d\u00051\u0011\r]1dQ\u0016T\u0011aD\u0001\u0004_J<\u0007CA\t\u0002\u001b\u00051!\u0001D!qa2L\u0018J\u001c)mC\u000e,7CA\u0001\u0015!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0011\u0003\u0015\t\u0007\u000f\u001d7z)\u0011y\"eL\u0019\u0011\u0005U\u0001\u0013BA\u0011\u0017\u0005\u0011)f.\u001b;\t\u000b\r\u001a\u0001\u0019\u0001\u0013\u0002\u0003a\u00042!\n\u0016-\u001b\u00051#BA\u0014)\u0003\u0019a\u0017N\\1mO*\t\u0011&\u0001\u0004ce\u0016,'0Z\u0005\u0003W\u0019\u00121\u0002R3og\u0016l\u0015\r\u001e:jqB\u0011Q#L\u0005\u0003]Y\u0011a\u0001R8vE2,\u0007\"\u0002\u0019\u0004\u0001\u0004!\u0013!A=\t\u000bI\u001a\u0001\u0019A\u001a\u0002\t\u0019,hn\u0019\t\u0005+QbC&\u0003\u00026-\tIa)\u001e8di&|g.\r\u000b\u0006?]J4\b\u0010\u0005\u0006q\u0011\u0001\r\u0001J\u0001\u0003qFBQA\u000f\u0003A\u0002\u0011\n!\u0001\u001f\u001a\t\u000bA\"\u0001\u0019\u0001\u0013\t\u000bI\"\u0001\u0019A\u001f\u0011\u000bUqD\u0006\f\u0017\n\u0005}2\"!\u0003$v]\u000e$\u0018n\u001c83\u0001"
)
public final class ApplyInPlace {
   public static void apply(final DenseMatrix x1, final DenseMatrix x2, final DenseMatrix y, final Function2 func) {
      ApplyInPlace$.MODULE$.apply(x1, x2, y, func);
   }

   public static void apply(final DenseMatrix x, final DenseMatrix y, final Function1 func) {
      ApplyInPlace$.MODULE$.apply(x, y, func);
   }
}
