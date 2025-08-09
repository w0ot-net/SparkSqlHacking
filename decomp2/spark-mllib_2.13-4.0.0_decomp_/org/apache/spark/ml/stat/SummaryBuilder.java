package org.apache.spark.ml.stat;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.functions.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]2Q\u0001B\u0003\u0002\"AAQa\u0006\u0001\u0005\u0002aAQa\u0007\u0001\u0007\u0002qAQa\u0007\u0001\u0005\u0002A\u0012abU;n[\u0006\u0014\u0018PQ;jY\u0012,'O\u0003\u0002\u0007\u000f\u0005!1\u000f^1u\u0015\tA\u0011\"\u0001\u0002nY*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u00023A\u0011!\u0004A\u0007\u0002\u000b\u000591/^7nCJLHcA\u000f$KA\u0011a$I\u0007\u0002?)\u0011\u0001%C\u0001\u0004gFd\u0017B\u0001\u0012 \u0005\u0019\u0019u\u000e\\;n]\")AE\u0001a\u0001;\u0005Ya-Z1ukJ,7oQ8m\u0011\u00151#\u00011\u0001\u001e\u0003%9X-[4ii\u000e{G\u000eK\u0002\u0003Q9\u0002\"!\u000b\u0017\u000e\u0003)R!aK\u0005\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002.U\t)1+\u001b8dK\u0006\nq&A\u00033]Mr\u0003\u0007\u0006\u0002\u001ec!)Ae\u0001a\u0001;!\u001a1\u0001\u000b\u0018*\u0005\u0001!\u0014BA\u001b\u0006\u0005I\u0019V/\\7bef\u0014U/\u001b7eKJLU\u000e\u001d7)\u0007\u0001Ac\u0006"
)
public abstract class SummaryBuilder {
   public abstract Column summary(final Column featuresCol, final Column weightCol);

   public Column summary(final Column featuresCol) {
      return this.summary(featuresCol, .MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }
}
