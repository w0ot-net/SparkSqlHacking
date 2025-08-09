package org.apache.spark.deploy;

import org.apache.spark.SparkConf;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592\u0001BA\u0002\u0011\u0002G\u0005Qa\u0003\u0005\u0006%\u00011\t\u0001\u0006\u0002\u0011'B\f'o[!qa2L7-\u0019;j_:T!\u0001B\u0003\u0002\r\u0011,\u0007\u000f\\8z\u0015\t1q!A\u0003ta\u0006\u00148N\u0003\u0002\t\u0013\u00051\u0011\r]1dQ\u0016T\u0011AC\u0001\u0004_J<7C\u0001\u0001\r!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fM\u0006)1\u000f^1si\u000e\u0001AcA\u000b\u0019QA\u0011QBF\u0005\u0003/9\u0011A!\u00168ji\")\u0011$\u0001a\u00015\u0005!\u0011M]4t!\ri1$H\u0005\u000399\u0011Q!\u0011:sCf\u0004\"AH\u0013\u000f\u0005}\u0019\u0003C\u0001\u0011\u000f\u001b\u0005\t#B\u0001\u0012\u0014\u0003\u0019a$o\\8u}%\u0011AED\u0001\u0007!J,G-\u001a4\n\u0005\u0019:#AB*ue&twM\u0003\u0002%\u001d!)\u0011&\u0001a\u0001U\u0005!1m\u001c8g!\tYC&D\u0001\u0006\u0013\tiSAA\u0005Ta\u0006\u00148nQ8oM\u0002"
)
public interface SparkApplication {
   void start(final String[] args, final SparkConf conf);
}
