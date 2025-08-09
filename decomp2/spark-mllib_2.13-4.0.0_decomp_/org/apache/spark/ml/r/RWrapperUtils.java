package org.apache.spark.ml.r;

import org.apache.spark.internal.Logging;
import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.feature.RFormulaModel;
import org.apache.spark.sql.Dataset;
import scala.StringContext;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t<a!\u0002\u0004\t\u0002\u0019\u0001bA\u0002\n\u0007\u0011\u000311\u0003C\u0003!\u0003\u0011\u0005!\u0005C\u0003$\u0003\u0011\u0005A\u0005C\u0003E\u0003\u0011\u0005Q)A\u0007S/J\f\u0007\u000f]3s+RLGn\u001d\u0006\u0003\u000f!\t\u0011A\u001d\u0006\u0003\u0013)\t!!\u001c7\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u0004\"!E\u0001\u000e\u0003\u0019\u0011QBU,sCB\u0004XM]+uS2\u001c8cA\u0001\u00155A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\u0004\"a\u0007\u0010\u000e\u0003qQ!!\b\u0006\u0002\u0011%tG/\u001a:oC2L!a\b\u000f\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0011\u0003A\u0019\u0007.Z2l\t\u0006$\u0018mQ8mk6t7\u000fF\u0002&QA\u0002\"!\u0006\u0014\n\u0005\u001d2\"\u0001B+oSRDQ!K\u0002A\u0002)\n\u0001B\u001d$pe6,H.\u0019\t\u0003W9j\u0011\u0001\f\u0006\u0003[!\tqAZ3biV\u0014X-\u0003\u00020Y\tA!KR8s[Vd\u0017\rC\u00032\u0007\u0001\u0007!'\u0001\u0003eCR\f\u0007GA\u001a<!\r!t'O\u0007\u0002k)\u0011aGC\u0001\u0004gFd\u0017B\u0001\u001d6\u0005\u001d!\u0015\r^1tKR\u0004\"AO\u001e\r\u0001\u0011IA\bMA\u0001\u0002\u0003\u0015\t!\u0010\u0002\u0004?\u0012\n\u0014C\u0001 B!\t)r(\u0003\u0002A-\t9aj\u001c;iS:<\u0007CA\u000bC\u0013\t\u0019eCA\u0002B]f\fAcZ3u\r\u0016\fG/\u001e:fg\u0006sG\rT1cK2\u001cHc\u0001$X9B!QcR%J\u0013\tAeC\u0001\u0004UkBdWM\r\t\u0004+)c\u0015BA&\u0017\u0005\u0015\t%O]1z!\tiEK\u0004\u0002O%B\u0011qJF\u0007\u0002!*\u0011\u0011+I\u0001\u0007yI|w\u000e\u001e \n\u0005M3\u0012A\u0002)sK\u0012,g-\u0003\u0002V-\n11\u000b\u001e:j]\u001eT!a\u0015\f\t\u000ba#\u0001\u0019A-\u0002\u001bI4uN]7vY\u0006lu\u000eZ3m!\tY#,\u0003\u0002\\Y\ti!KR8s[Vd\u0017-T8eK2DQ!\r\u0003A\u0002u\u0003$A\u00181\u0011\u0007Q:t\f\u0005\u0002;A\u0012I\u0011\rXA\u0001\u0002\u0003\u0015\t!\u0010\u0002\u0004?\u0012\u0012\u0004"
)
public final class RWrapperUtils {
   public static Tuple2 getFeaturesAndLabels(final RFormulaModel rFormulaModel, final Dataset data) {
      return RWrapperUtils$.MODULE$.getFeaturesAndLabels(rFormulaModel, data);
   }

   public static void checkDataColumns(final RFormula rFormula, final Dataset data) {
      RWrapperUtils$.MODULE$.checkDataColumns(rFormula, data);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return RWrapperUtils$.MODULE$.LogStringContext(sc);
   }
}
