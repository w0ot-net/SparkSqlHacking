package org.apache.spark.api.r;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-:a!\u0003\u0006\t\u00029!bA\u0002\f\u000b\u0011\u0003qq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005C\u0004\"\u0003\t\u0007I\u0011\u0001\u0012\t\r\u0019\n\u0001\u0015!\u0003$\u0011\u001d9\u0013A1A\u0005\u0002\tBa\u0001K\u0001!\u0002\u0013\u0019\u0003bB\u0015\u0002\u0005\u0004%\tA\t\u0005\u0007U\u0005\u0001\u000b\u0011B\u0012\u0002\u0019I\u0013VO\u001c8fe6{G-Z:\u000b\u0005-a\u0011!\u0001:\u000b\u00055q\u0011aA1qS*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014x\r\u0005\u0002\u0016\u00035\t!B\u0001\u0007S%Vtg.\u001a:N_\u0012,7o\u0005\u0002\u00021A\u0011\u0011\u0004H\u0007\u00025)\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e5\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002)\u0005\u0019!\u000b\u0012#\u0016\u0003\r\u0002\"!\u0007\u0013\n\u0005\u0015R\"aA%oi\u0006!!\u000b\u0012#!\u0003A!\u0015\tV!G%\u0006kUi\u0018#B!Bc\u0015,A\tE\u0003R\u000beIU!N\u000b~#\u0015\t\u0015)M3\u0002\n\u0001\u0003R!U\u0003\u001a\u0013\u0016)T#`\u000f\u0006\u0003\u0006\u000bT-\u0002#\u0011\u000bE+\u0011$S\u00036+ulR!Q!2K\u0006\u0005"
)
public final class RRunnerModes {
   public static int DATAFRAME_GAPPLY() {
      return RRunnerModes$.MODULE$.DATAFRAME_GAPPLY();
   }

   public static int DATAFRAME_DAPPLY() {
      return RRunnerModes$.MODULE$.DATAFRAME_DAPPLY();
   }

   public static int RDD() {
      return RRunnerModes$.MODULE$.RDD();
   }
}
