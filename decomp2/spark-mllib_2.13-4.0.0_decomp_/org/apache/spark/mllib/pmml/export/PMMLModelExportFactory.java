package org.apache.spark.mllib.pmml.export;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!:a\u0001B\u0003\t\u0002%\tbAB\n\u0006\u0011\u0003IA\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0003\u001f\u0003\u0011\u0005q$\u0001\fQ\u001b6cUj\u001c3fY\u0016C\bo\u001c:u\r\u0006\u001cGo\u001c:z\u0015\t1q!\u0001\u0004fqB|'\u000f\u001e\u0006\u0003\u0011%\tA\u0001]7nY*\u0011!bC\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sOB\u0011!#A\u0007\u0002\u000b\t1\u0002+T'M\u001b>$W\r\\#ya>\u0014HOR1di>\u0014\u0018p\u0005\u0002\u0002+A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002#\u0005)2M]3bi\u0016\u0004V*\u0014'N_\u0012,G.\u0012=q_J$HC\u0001\u0011$!\t\u0011\u0012%\u0003\u0002#\u000b\ty\u0001+T'M\u001b>$W\r\\#ya>\u0014H\u000fC\u0003%\u0007\u0001\u0007Q%A\u0003n_\u0012,G\u000e\u0005\u0002\u0017M%\u0011qe\u0006\u0002\u0004\u0003:L\b"
)
public final class PMMLModelExportFactory {
   public static PMMLModelExport createPMMLModelExport(final Object model) {
      return PMMLModelExportFactory$.MODULE$.createPMMLModelExport(model);
   }
}
