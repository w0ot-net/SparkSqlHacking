package org.apache.spark.api.python;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m:a!\u0005\n\t\u0002YabA\u0002\u0010\u0013\u0011\u00031r\u0004C\u0003'\u0003\u0011\u0005\u0001\u0006C\u0004*\u0003\t\u0007I\u0011\u0001\u0016\t\r9\n\u0001\u0015!\u0003,\u0011\u001dy\u0013A1A\u0005\u0002)Ba\u0001M\u0001!\u0002\u0013Y\u0003bB\u0019\u0002\u0005\u0004%\tA\u000b\u0005\u0007e\u0005\u0001\u000b\u0011B\u0016\t\u000fM\n!\u0019!C\u0001U!1A'\u0001Q\u0001\n-Bq!N\u0001C\u0002\u0013\u0005!\u0006\u0003\u00047\u0003\u0001\u0006Ia\u000b\u0005\bo\u0005\u0011\r\u0011\"\u0001+\u0011\u0019A\u0014\u0001)A\u0005W!9\u0011(\u0001b\u0001\n\u0003Q\u0003B\u0002\u001e\u0002A\u0003%1&\u0001\bTa\u0016\u001c\u0017.\u00197MK:<G\u000f[:\u000b\u0005M!\u0012A\u00029zi\"|gN\u0003\u0002\u0016-\u0005\u0019\u0011\r]5\u000b\u0005]A\u0012!B:qCJ\\'BA\r\u001b\u0003\u0019\t\u0007/Y2iK*\t1$A\u0002pe\u001e\u0004\"!H\u0001\u000e\u0003I\u0011ab\u00159fG&\fG\u000eT3oORD7o\u0005\u0002\u0002AA\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u00029\u0005\u0019RI\u0014#`\u001f\u001a{F)\u0011+B?N+5\tV%P\u001dV\t1\u0006\u0005\u0002\"Y%\u0011QF\t\u0002\u0004\u0013:$\u0018\u0001F#O\t~{ei\u0018#B)\u0006{6+R\"U\u0013>s\u0005%A\fQ3RCuJT0F1\u000e+\u0005\u000bV%P\u001d~#\u0006JU(X\u001d\u0006A\u0002+\u0017+I\u001f:{V\tW\"F!RKuJT0U\u0011J{uK\u0014\u0011\u0002\u0017QKU*\u0013(H?\u0012\u000bE+Q\u0001\r)&k\u0015JT$`\t\u0006#\u0016\tI\u0001\u000e\u000b:#ul\u0014$`'R\u0013V)Q'\u0002\u001d\u0015sEiX(G?N#&+R!NA\u0005!a*\u0016'M\u0003\u0015qU\u000b\u0014'!\u0003I\u0019F+\u0011*U?\u0006\u0013&kT,`'R\u0013V)Q'\u0002'M#\u0016I\u0015+`\u0003J\u0013vjV0T)J+\u0015)\u0014\u0011\u0002%\u0015sEiX(G?6K5IU(`\u0005\u0006#6\tS\u0001\u0014\u000b:#ul\u0014$`\u001b&\u001b%kT0C\u0003R\u001b\u0005\n\t"
)
public final class SpecialLengths {
   public static int END_OF_MICRO_BATCH() {
      return SpecialLengths$.MODULE$.END_OF_MICRO_BATCH();
   }

   public static int START_ARROW_STREAM() {
      return SpecialLengths$.MODULE$.START_ARROW_STREAM();
   }

   public static int NULL() {
      return SpecialLengths$.MODULE$.NULL();
   }

   public static int END_OF_STREAM() {
      return SpecialLengths$.MODULE$.END_OF_STREAM();
   }

   public static int TIMING_DATA() {
      return SpecialLengths$.MODULE$.TIMING_DATA();
   }

   public static int PYTHON_EXCEPTION_THROWN() {
      return SpecialLengths$.MODULE$.PYTHON_EXCEPTION_THROWN();
   }

   public static int END_OF_DATA_SECTION() {
      return SpecialLengths$.MODULE$.END_OF_DATA_SECTION();
   }
}
