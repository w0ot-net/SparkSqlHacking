package scala.runtime;

import scala.collection.immutable.NumericRange;
import scala.collection.immutable.NumericRange$;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003$\u0001\u0011\u0005A\u0005C\u0003)\u0001\u0019M\u0011&\u0002\u00032\u0001\u0001\u0011\u0004\"\u0002\u001e\u0001\t\u0003Y\u0004\"\u0002\u001e\u0001\t\u0003)\u0005\"B%\u0001\t\u0003Q\u0005\"B%\u0001\t\u0003y%!D%oi\u0016<'/\u00197Qe>D\u0018P\u0003\u0002\u000b\u0017\u00059!/\u001e8uS6,'\"\u0001\u0007\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011qBG\n\u0005\u0001A!\u0002\u0005\u0005\u0002\u0012%5\t1\"\u0003\u0002\u0014\u0017\t\u0019\u0011I\\=\u0011\u0007U1\u0002$D\u0001\n\u0013\t9\u0012BA\u000bTG\u0006d\u0017m\u00165pY\u0016tU/\u001c2feB\u0013x\u000e_=\u0011\u0005eQB\u0002\u0001\u0003\u00067\u0001\u0011\r\u0001\b\u0002\u0002)F\u0011Q\u0004\u0005\t\u0003#yI!aH\u0006\u0003\u000f9{G\u000f[5oOB\u0019Q#\t\r\n\u0005\tJ!a\u0003*b]\u001e,G\r\u0015:pqf\fa\u0001J5oSR$C#A\u0013\u0011\u0005E1\u0013BA\u0014\f\u0005\u0011)f.\u001b;\u0002\u00079,X.F\u0001+!\rYc\u0006\u0007\b\u0003#1J!!L\u0006\u0002\u000fA\f7m[1hK&\u0011q\u0006\r\u0002\t\u0013:$Xm\u001a:bY*\u0011Qf\u0003\u0002\u0012%\u0016\u001cX\u000f\u001c;XSRDw.\u001e;Ti\u0016\u0004\bcA\u001a915\tAG\u0003\u00026m\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003o-\t!bY8mY\u0016\u001cG/[8o\u0013\tIDG\u0001\u0007Ok6,'/[2SC:<W-A\u0003v]RLG\u000e\u0006\u0002=\u0007B\u0019Q\b\u0011\r\u000f\u0005Mr\u0014BA 5\u00031qU/\\3sS\u000e\u0014\u0016M\\4f\u0013\t\t%IA\u0005Fq\u000edWo]5wK*\u0011q\b\u000e\u0005\u0006\t\u0012\u0001\r\u0001G\u0001\u0004K:$Gc\u0001\u001fG\u000f\")A)\u0002a\u00011!)\u0001*\u0002a\u00011\u0005!1\u000f^3q\u0003\t!x\u000e\u0006\u0002L\u001dB\u0019Q\b\u0014\r\n\u00055\u0013%!C%oG2,8/\u001b<f\u0011\u0015!e\u00011\u0001\u0019)\rY\u0005+\u0015\u0005\u0006\t\u001e\u0001\r\u0001\u0007\u0005\u0006\u0011\u001e\u0001\r\u0001\u0007"
)
public interface IntegralProxy extends ScalaWholeNumberProxy, RangedProxy {
   Integral num();

   // $FF: synthetic method
   static NumericRange.Exclusive until$(final IntegralProxy $this, final Object end) {
      return $this.until(end);
   }

   default NumericRange.Exclusive until(final Object end) {
      NumericRange$ var10000 = NumericRange$.MODULE$;
      var10000 = (NumericRange$)this.self();
      Object var10001 = this.num().one();
      Integral apply_num = this.num();
      Object apply_step = var10001;
      Object apply_start = var10000;
      return new NumericRange.Exclusive(apply_start, end, apply_step, apply_num);
   }

   // $FF: synthetic method
   static NumericRange.Exclusive until$(final IntegralProxy $this, final Object end, final Object step) {
      return $this.until(end, step);
   }

   default NumericRange.Exclusive until(final Object end, final Object step) {
      NumericRange$ var10000 = NumericRange$.MODULE$;
      var10000 = (NumericRange$)this.self();
      Integral apply_num = this.num();
      Object apply_start = var10000;
      return new NumericRange.Exclusive(apply_start, end, step, apply_num);
   }

   // $FF: synthetic method
   static NumericRange.Inclusive to$(final IntegralProxy $this, final Object end) {
      return $this.to(end);
   }

   default NumericRange.Inclusive to(final Object end) {
      NumericRange$ var10000 = NumericRange$.MODULE$;
      var10000 = (NumericRange$)this.self();
      Object var10001 = this.num().one();
      Integral inclusive_num = this.num();
      Object inclusive_step = var10001;
      Object inclusive_start = var10000;
      return new NumericRange.Inclusive(inclusive_start, end, inclusive_step, inclusive_num);
   }

   // $FF: synthetic method
   static NumericRange.Inclusive to$(final IntegralProxy $this, final Object end, final Object step) {
      return $this.to(end, step);
   }

   default NumericRange.Inclusive to(final Object end, final Object step) {
      NumericRange$ var10000 = NumericRange$.MODULE$;
      var10000 = (NumericRange$)this.self();
      Integral inclusive_num = this.num();
      Object inclusive_start = var10000;
      return new NumericRange.Inclusive(inclusive_start, end, step, inclusive_num);
   }

   static void $init$(final IntegralProxy $this) {
   }
}
