package org.apache.spark.status.api.v1;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2A\u0001C\u0005\u0001-!AQ\u0004\u0001BC\u0002\u0013\u0005a\u0004\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003 \u0011!Y\u0003A!b\u0001\n\u0003q\u0002\u0002\u0003\u0017\u0001\u0005\u0003\u0005\u000b\u0011B\u0010\t\u00115\u0002!Q1A\u0005\u0002yA\u0001B\f\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\u0007_\u0001!\ta\u0004\u0019\u0003\u0017I+h\u000e^5nK&sgm\u001c\u0006\u0003\u0015-\t!A^\u0019\u000b\u00051i\u0011aA1qS*\u0011abD\u0001\u0007gR\fG/^:\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001/A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\f1B[1wCZ+'o]5p]V\tq\u0004\u0005\u0002!O9\u0011\u0011%\n\t\u0003Eei\u0011a\t\u0006\u0003IU\ta\u0001\u0010:p_Rt\u0014B\u0001\u0014\u001a\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001&\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0019J\u0012\u0001\u00046bm\u00064VM]:j_:\u0004\u0013\u0001\u00036bm\u0006Du.\\3\u0002\u0013)\fg/\u0019%p[\u0016\u0004\u0013\u0001D:dC2\fg+\u001a:tS>t\u0017!D:dC2\fg+\u001a:tS>t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005cM\"T\u0007\u0005\u00023\u00015\t\u0011\u0002C\u0003\u001e\u000f\u0001\u0007q\u0004C\u0003,\u000f\u0001\u0007q\u0004C\u0003.\u000f\u0001\u0007q\u0004"
)
public class RuntimeInfo {
   private final String javaVersion;
   private final String javaHome;
   private final String scalaVersion;

   public String javaVersion() {
      return this.javaVersion;
   }

   public String javaHome() {
      return this.javaHome;
   }

   public String scalaVersion() {
      return this.scalaVersion;
   }

   public RuntimeInfo(final String javaVersion, final String javaHome, final String scalaVersion) {
      this.javaVersion = javaVersion;
      this.javaHome = javaHome;
      this.scalaVersion = scalaVersion;
   }
}
