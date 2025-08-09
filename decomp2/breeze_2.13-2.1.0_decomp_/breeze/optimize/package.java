package breeze.optimize;

import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005];Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQ!F\u0001\u0005\u0002YAQaF\u0001\u0005\u0002aAQAO\u0001\u0005\u0002m\nq\u0001]1dW\u0006<WM\u0003\u0002\b\u0011\u0005Aq\u000e\u001d;j[&TXMC\u0001\n\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u0001\u0007\u0002\u001b\u00051!a\u00029bG.\fw-Z\n\u0003\u0003=\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\f\u0003!i\u0017N\\5nSj,WcA\r-;Q!!D\f\u00193)\tYb\u0005\u0005\u0002\u001d;1\u0001A!\u0002\u0010\u0004\u0005\u0004y\"A\u0002,fGR|'/\u0005\u0002!GA\u0011\u0001#I\u0005\u0003EE\u0011qAT8uQ&tw\r\u0005\u0002\u0011I%\u0011Q%\u0005\u0002\u0004\u0003:L\b\"B\u0014\u0004\u0001\bA\u0013\u0001D8qi&l\u0017N_1uS>t\u0007\u0003\u0002\u0007*WmI!A\u000b\u0004\u0003'=\u0003H/[7ju\u0006$\u0018n\u001c8QC\u000e\\\u0017mZ3\u0011\u0005qaC!B\u0017\u0004\u0005\u0004y\"!C(cU\u0016\u001cG/\u001b<f\u0011\u0015y3\u00011\u0001,\u0003\t1g\u000eC\u00032\u0007\u0001\u00071$\u0001\u0003j]&$\b\"B\u001a\u0004\u0001\u0004!\u0014aB8qi&|gn\u001d\t\u0004!U:\u0014B\u0001\u001c\u0012\u0005)a$/\u001a9fCR,GM\u0010\t\u0003\u0019aJ!!\u000f\u0004\u0003%=\u0003H/[7ju\u0006$\u0018n\u001c8PaRLwN\\\u0001\u000bSR,'/\u0019;j_:\u001cX\u0003\u0002\u001fR'*#B!\u0010+V-R\u0011a\b\u0014\t\u0004\u007f\u0019KeB\u0001!F\u001d\t\tE)D\u0001C\u0015\t\u0019%\"\u0001\u0004=e>|GOP\u0005\u0002%%\u0011Q!E\u0005\u0003\u000f\"\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003\u000bE\u0001\"\u0001\b&\u0005\u000b-#!\u0019A\u0010\u0003\u000bM#\u0018\r^3\t\u000b\u001d\"\u00019A'\u0011\u000b1q\u0005KU%\n\u0005=3!aG%uKJ\f'\r\\3PaRLW.\u001b>bi&|g\u000eU1dW\u0006<W\r\u0005\u0002\u001d#\u0012)Q\u0006\u0002b\u0001?A\u0011Ad\u0015\u0003\u0006=\u0011\u0011\ra\b\u0005\u0006_\u0011\u0001\r\u0001\u0015\u0005\u0006c\u0011\u0001\rA\u0015\u0005\u0006g\u0011\u0001\r\u0001\u000e"
)
public final class package {
   public static Iterator iterations(final Object fn, final Object init, final Seq options, final IterableOptimizationPackage optimization) {
      return package$.MODULE$.iterations(fn, init, options, optimization);
   }

   public static Object minimize(final Object fn, final Object init, final Seq options, final OptimizationPackage optimization) {
      return package$.MODULE$.minimize(fn, init, options, optimization);
   }
}
