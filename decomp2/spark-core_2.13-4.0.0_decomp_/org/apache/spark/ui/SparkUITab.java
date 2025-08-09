package org.apache.spark.ui;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2aAB\u0004\u0002\u0002%y\u0001\u0002\u0003\u000b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\f\t\u0013e\u0001!\u0011!Q\u0001\ni9\u0003\"\u0002\u0015\u0001\t\u0003I\u0003\"B\u0017\u0001\t\u0003q\u0003\"B\u0018\u0001\t\u0003q#AC*qCJ\\W+\u0013+bE*\u0011\u0001\"C\u0001\u0003k&T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\n\u0003\u0001A\u0001\"!\u0005\n\u000e\u0003\u001dI!aE\u0004\u0003\u0011]+'-V%UC\n\fa\u0001]1sK:$8\u0001\u0001\t\u0003#]I!\u0001G\u0004\u0003\u000fM\u0003\u0018M]6V\u0013\u00061\u0001O]3gSb\u0004\"a\u0007\u0013\u000f\u0005q\u0011\u0003CA\u000f!\u001b\u0005q\"BA\u0010\u0016\u0003\u0019a$o\\8u})\t\u0011%A\u0003tG\u0006d\u0017-\u0003\u0002$A\u00051\u0001K]3eK\u001aL!!\n\u0014\u0003\rM#(/\u001b8h\u0015\t\u0019\u0003%\u0003\u0002\u001a%\u00051A(\u001b8jiz\"2AK\u0016-!\t\t\u0002\u0001C\u0003\u0015\u0007\u0001\u0007a\u0003C\u0003\u001a\u0007\u0001\u0007!$A\u0004baBt\u0015-\\3\u0016\u0003i\tq\"\u00199q'B\f'o\u001b,feNLwN\u001c"
)
public abstract class SparkUITab extends WebUITab {
   private final SparkUI parent;

   public String appName() {
      return this.parent.appName();
   }

   public String appSparkVersion() {
      return this.parent.appSparkVersion();
   }

   public SparkUITab(final SparkUI parent, final String prefix) {
      super(parent, prefix);
      this.parent = parent;
   }
}
