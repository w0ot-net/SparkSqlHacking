package org.apache.spark.util;

import scala.Function2;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d;a\u0001B\u0003\t\u0002\u001diaAB\b\u0006\u0011\u00039\u0001\u0003C\u0003\u0018\u0003\u0011\u0005\u0011\u0004C\u0003\u001b\u0003\u0011\u00051$\u0001\tD_2dWm\u0019;j_:\u001cX\u000b^5mg*\u0011aaB\u0001\u0005kRLGN\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h!\tq\u0011!D\u0001\u0006\u0005A\u0019u\u000e\u001c7fGRLwN\\:Vi&d7o\u0005\u0002\u0002#A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u001b\u0005\u0001R.Y6f\u0005&t\u0017M]=TK\u0006\u00148\r[\u000b\u00039\u0015\"2!H\u0019@!\u0015\u0011b\u0004I\u0012/\u0013\ty2CA\u0005Gk:\u001cG/[8oeA\u0019!#I\u0012\n\u0005\t\u001a\"!B!se\u0006L\bC\u0001\u0013&\u0019\u0001!QAJ\u0002C\u0002\u001d\u0012\u0011aS\t\u0003Q-\u0002\"AE\u0015\n\u0005)\u001a\"a\u0002(pi\"Lgn\u001a\t\u0003%1J!!L\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0013_%\u0011\u0001g\u0005\u0002\u0004\u0013:$\bb\u0002\u001a\u0004\u0003\u0003\u0005\u001daM\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004c\u0001\u001b=G9\u0011QG\u000f\b\u0003mej\u0011a\u000e\u0006\u0003qa\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000b\n\u0005m\u001a\u0012a\u00029bG.\fw-Z\u0005\u0003{y\u0012\u0001b\u0014:eKJLgn\u001a\u0006\u0003wMAq\u0001Q\u0002\u0002\u0002\u0003\u000f\u0011)\u0001\u0006fm&$WM\\2fII\u00022AQ#$\u001b\u0005\u0019%B\u0001#\u0014\u0003\u001d\u0011XM\u001a7fGRL!AR\"\u0003\u0011\rc\u0017m]:UC\u001e\u0004"
)
public final class CollectionsUtils {
   public static Function2 makeBinarySearch(final Ordering evidence$1, final ClassTag evidence$2) {
      return CollectionsUtils$.MODULE$.makeBinarySearch(evidence$1, evidence$2);
   }
}
