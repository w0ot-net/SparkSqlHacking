package org.apache.spark.ml.python;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A:Q\u0001B\u0003\t\u0002A1QAE\u0003\t\u0002MAQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0002u\ta!\u0014'Vi&d'B\u0001\u0004\b\u0003\u0019\u0001\u0018\u0010\u001e5p]*\u0011\u0001\"C\u0001\u0003[2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\u0002\u0001!\t\t\u0012!D\u0001\u0006\u0005\u0019iE*\u0016;jYN\u0011\u0011\u0001\u0006\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\u0001\u0012!F2paf4\u0015\u000e\\3Ge>lGj\\2bYR{gi\u001d\u000b\u0004=\u0005r\u0003CA\u000b \u0013\t\u0001cC\u0001\u0003V]&$\b\"\u0002\u0012\u0004\u0001\u0004\u0019\u0013!\u00037pG\u0006d\u0007+\u0019;i!\t!3F\u0004\u0002&SA\u0011aEF\u0007\u0002O)\u0011\u0001fD\u0001\u0007yI|w\u000e\u001e \n\u0005)2\u0012A\u0002)sK\u0012,g-\u0003\u0002-[\t11\u000b\u001e:j]\u001eT!A\u000b\f\t\u000b=\u001a\u0001\u0019A\u0012\u0002\u0011\u0011,7\u000f\u001e)bi\"\u0004"
)
public final class MLUtil {
   public static void copyFileFromLocalToFs(final String localPath, final String destPath) {
      MLUtil$.MODULE$.copyFileFromLocalToFs(localPath, destPath);
   }
}
