package scala.reflect.api;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553\u0001\u0002D\u0007\u0011\u0002\u0007\u0005A#\u0013\u0005\u00063\u0001!\tA\u0007\u0003\u0006=\u0001\u0011\ta\b\u0005\bq\u0001\u0011\rQ\"\u0001:\r\u0015Y\u0004!!\u0001=\u0011\u0015QC\u0001\"\u0001>\u0011\u0015qDA\"\u0001@\u0011\u0015\u0011EA\"\u0001D\r\u0015A\u0003!!\u0001*\u0011\u0015Q\u0003\u0002\"\u0001,\u0011\u001da\u0003B1A\u0007\u00025BQ!\r\u0005\u0007\u0002I\u0012\u0011bQ8ogR\fg\u000e^:\u000b\u00059y\u0011aA1qS*\u0011\u0001#E\u0001\be\u00164G.Z2u\u0015\u0005\u0011\u0012!B:dC2\f7\u0001A\n\u0003\u0001U\u0001\"AF\f\u000e\u0003EI!\u0001G\t\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t1\u0004\u0005\u0002\u00179%\u0011Q$\u0005\u0002\u0005+:LGO\u0001\u0005D_:\u001cH/\u00198u#\t\u00013\u0005\u0005\u0002\u0017C%\u0011!%\u0005\u0002\u0005\u001dVdGNE\u0002%+\u00192A!\n\u0001\u0001G\taAH]3gS:,W.\u001a8u}A\u0011q\u0005C\u0007\u0002\u0001\tY1i\u001c8ti\u0006tG/\u00119j'\tAQ#\u0001\u0004=S:LGO\u0010\u000b\u0002M\u0005)a/\u00197vKV\ta\u0006\u0005\u0002\u0017_%\u0011\u0001'\u0005\u0002\u0004\u0003:L\u0018a\u0001;qKV\t1\u0007\u0005\u0002(i%\u0011QG\u000e\u0002\u0005)f\u0004X-\u0003\u00028\u001b\t)A+\u001f9fg\u0006A1i\u001c8ti\u0006tG/F\u0001;!\t9CAA\tD_:\u001cH/\u00198u\u000bb$(/Y2u_J\u001c\"\u0001B\u000b\u0015\u0003i\nQ!\u00199qYf$\"\u0001Q!\u0011\u0005\u001d\u0012\u0001\"\u0002\u0017\u0007\u0001\u0004q\u0013aB;oCB\u0004H.\u001f\u000b\u0003\t\u001e\u00032AF#/\u0013\t1\u0015C\u0001\u0004PaRLwN\u001c\u0005\u0006\u0011\u001e\u0001\r\u0001Q\u0001\u0004CJ<\u0007C\u0001&L\u001b\u0005i\u0011B\u0001'\u000e\u0005!)f.\u001b<feN,\u0007"
)
public interface Constants {
   ConstantExtractor Constant();

   static void $init$(final Constants $this) {
   }

   public abstract class ConstantExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract ConstantApi apply(final Object value);

      public abstract Option unapply(final ConstantApi arg);

      // $FF: synthetic method
      public Universe scala$reflect$api$Constants$ConstantExtractor$$$outer() {
         return this.$outer;
      }

      public ConstantExtractor() {
         if (Constants.this == null) {
            throw null;
         } else {
            this.$outer = Constants.this;
            super();
         }
      }
   }

   public abstract class ConstantApi {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Object value();

      public abstract Types.TypeApi tpe();

      // $FF: synthetic method
      public Universe scala$reflect$api$Constants$ConstantApi$$$outer() {
         return this.$outer;
      }

      public ConstantApi() {
         if (Constants.this == null) {
            throw null;
         } else {
            this.$outer = Constants.this;
            super();
         }
      }
   }
}
