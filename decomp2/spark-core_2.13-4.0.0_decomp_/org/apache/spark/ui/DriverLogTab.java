package org.apache.spark.ui;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00112QAB\u0004\u0001\u000f=A\u0001\u0002\u0006\u0001\u0003\u0002\u0003\u0006IA\u0006\u0005\u00063\u0001!\tA\u0007\u0005\b;\u0001\u0011\r\u0011\"\u0003\u001f\u0011\u0019\u0011\u0003\u0001)A\u0005?!)1\u0005\u0001C\u0001=\taAI]5wKJdun\u001a+bE*\u0011\u0001\"C\u0001\u0003k&T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\n\u0003\u0001A\u0001\"!\u0005\n\u000e\u0003\u001dI!aE\u0004\u0003\u0015M\u0003\u0018M]6V\u0013R\u000b'-\u0001\u0004qCJ,g\u000e^\u0002\u0001!\t\tr#\u0003\u0002\u0019\u000f\t91\u000b]1sWVK\u0015A\u0002\u001fj]&$h\b\u0006\u0002\u001c9A\u0011\u0011\u0003\u0001\u0005\u0006)\t\u0001\rAF\u0001\u0005a\u0006<W-F\u0001 !\t\t\u0002%\u0003\u0002\"\u000f\tiAI]5wKJdun\u001a)bO\u0016\fQ\u0001]1hK\u0002\nqaZ3u!\u0006<W\r"
)
public class DriverLogTab extends SparkUITab {
   private final DriverLogPage page;

   private DriverLogPage page() {
      return this.page;
   }

   public DriverLogPage getPage() {
      return this.page();
   }

   public DriverLogTab(final SparkUI parent) {
      super(parent, "logs");
      this.page = new DriverLogPage(this, parent.conf());
      this.attachPage(this.page());
   }
}
