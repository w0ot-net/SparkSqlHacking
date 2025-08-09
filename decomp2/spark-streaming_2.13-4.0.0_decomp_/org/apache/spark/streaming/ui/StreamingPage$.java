package org.apache.spark.streaming.ui;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ui.UIUtils.;
import scala.Option;
import scala.runtime.BoxesRunTime;

public final class StreamingPage$ {
   public static final StreamingPage$ MODULE$ = new StreamingPage$();
   private static final String BLACK_RIGHT_TRIANGLE_HTML = "&#9654;";
   private static final String BLACK_DOWN_TRIANGLE_HTML = "&#9660;";
   private static final String emptyCell = "-";

   public String BLACK_RIGHT_TRIANGLE_HTML() {
      return BLACK_RIGHT_TRIANGLE_HTML;
   }

   public String BLACK_DOWN_TRIANGLE_HTML() {
      return BLACK_DOWN_TRIANGLE_HTML;
   }

   public String emptyCell() {
      return emptyCell;
   }

   public String formatDurationOption(final Option msOption) {
      return (String)msOption.map((ms) -> $anonfun$formatDurationOption$1(BoxesRunTime.unboxToLong(ms))).getOrElse(() -> MODULE$.emptyCell());
   }

   // $FF: synthetic method
   public static final String $anonfun$formatDurationOption$1(final long ms) {
      return .MODULE$.formatDurationVerbose(ms);
   }

   private StreamingPage$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
