package breeze.stats.distributions;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4\u0001b\u0001\u0003\u0011\u0002\u0007\u00051B\u0015\u0005\u0006'\u0001!\t\u0001\u0006\u0005\u00061\u0001!)!\u0007\u0002\u000b!\u00124\u0017j]+Gk:\u001c'BA\u0003\u0007\u00035!\u0017n\u001d;sS\n,H/[8og*\u0011q\u0001C\u0001\u0006gR\fGo\u001d\u0006\u0002\u0013\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0003\r\u0019f\u001b6C\u0001\u0001\u000e!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012!\u0006\t\u0003\u001dYI!aF\b\u0003\tUs\u0017\u000e^\u0001\u0004a\u00124Wc\u0001\u000e]=Q\u00111$\u001a\u000b\u00039}\u0002\"!\b\u0010\r\u0001\u0011IqD\u0001Q\u0001\u0002\u0003\u0015\r\u0001\t\u0002\u0003-J\u000b\"!\t\u0013\u0011\u00059\u0011\u0013BA\u0012\u0010\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AD\u0013\n\u0005\u0019z!aA!os\"*a\u0004K\u00166uA\u0011a\"K\u0005\u0003U=\u00111b\u001d9fG&\fG.\u001b>fIF*1\u0005L\u00170]9\u0011a\"L\u0005\u0003]=\t1!\u00138uc\u0011!\u0003\u0007\u000e\t\u000f\u0005E\"T\"\u0001\u001a\u000b\u0005MR\u0011A\u0002\u001fs_>$h(C\u0001\u0011c\u0015\u0019cgN\u001d9\u001d\tqq'\u0003\u00029\u001f\u00051Ai\\;cY\u0016\fD\u0001\n\u00195!E*1e\u000f\u001f?{9\u0011a\u0002P\u0005\u0003{=\tQA\u00127pCR\fD\u0001\n\u00195!!)\u0001I\u0001a\u0002\u0003\u0006!\u0011.\u001c9m!\u0019\u0011\u0005j\u0013*\\99\u00111IR\u0007\u0002\t*\u0011Q\tC\u0001\bO\u0016tWM]5d\u0013\t9E)A\u0003V\rVt7-\u0003\u0002J\u0015\n1Q+S7qYJR!a\u0012#\u0011\u0005uaE!B'\u0001\u0005\u0004q%!A+\u0012\u0005\u0005z\u0005CA\"Q\u0013\t\tFIA\u0003V\rVt7\r\u0005\u0002\u001e'\u0012)A\u000b\u0001b\u0001+\n\t\u0001+\u0005\u0002\"-B)q\u000bA&Y%6\tA\u0001\u0005\u0002\u001e3\u0012)!\f\u0001b\u0001A\t\tA\u000b\u0005\u0002\u001e9\u0012IQL\u0001Q\u0001\u0002\u0003\u0015\r\u0001\t\u0002\u0002-\"*A\fK0bGF*1\u0005L\u0017a]E\"A\u0005\r\u001b\u0011c\u0015\u0019cg\u000e29c\u0011!\u0003\u0007\u000e\t2\u000b\rZD\bZ\u001f2\t\u0011\u0002D\u0007\u0005\u0005\u0006M\n\u0001\raW\u0001\u0002m\u0002"
)
public interface PdfIsUFunc {
   // $FF: synthetic method
   static Object pdf$(final PdfIsUFunc $this, final Object v, final UFunc.UImpl2 impl) {
      return $this.pdf(v, impl);
   }

   default Object pdf(final Object v, final UFunc.UImpl2 impl) {
      return impl.apply(this, v);
   }

   // $FF: synthetic method
   static double pdf$mDDc$sp$(final PdfIsUFunc $this, final double v, final UFunc.UImpl2 impl) {
      return $this.pdf$mDDc$sp(v, impl);
   }

   default double pdf$mDDc$sp(final double v, final UFunc.UImpl2 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(this, BoxesRunTime.boxToDouble(v)));
   }

   // $FF: synthetic method
   static float pdf$mDFc$sp$(final PdfIsUFunc $this, final double v, final UFunc.UImpl2 impl) {
      return $this.pdf$mDFc$sp(v, impl);
   }

   default float pdf$mDFc$sp(final double v, final UFunc.UImpl2 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(this, BoxesRunTime.boxToDouble(v)));
   }

   // $FF: synthetic method
   static int pdf$mDIc$sp$(final PdfIsUFunc $this, final double v, final UFunc.UImpl2 impl) {
      return $this.pdf$mDIc$sp(v, impl);
   }

   default int pdf$mDIc$sp(final double v, final UFunc.UImpl2 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(this, BoxesRunTime.boxToDouble(v)));
   }

   // $FF: synthetic method
   static double pdf$mFDc$sp$(final PdfIsUFunc $this, final float v, final UFunc.UImpl2 impl) {
      return $this.pdf$mFDc$sp(v, impl);
   }

   default double pdf$mFDc$sp(final float v, final UFunc.UImpl2 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(this, BoxesRunTime.boxToFloat(v)));
   }

   // $FF: synthetic method
   static float pdf$mFFc$sp$(final PdfIsUFunc $this, final float v, final UFunc.UImpl2 impl) {
      return $this.pdf$mFFc$sp(v, impl);
   }

   default float pdf$mFFc$sp(final float v, final UFunc.UImpl2 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(this, BoxesRunTime.boxToFloat(v)));
   }

   // $FF: synthetic method
   static int pdf$mFIc$sp$(final PdfIsUFunc $this, final float v, final UFunc.UImpl2 impl) {
      return $this.pdf$mFIc$sp(v, impl);
   }

   default int pdf$mFIc$sp(final float v, final UFunc.UImpl2 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(this, BoxesRunTime.boxToFloat(v)));
   }

   // $FF: synthetic method
   static double pdf$mIDc$sp$(final PdfIsUFunc $this, final int v, final UFunc.UImpl2 impl) {
      return $this.pdf$mIDc$sp(v, impl);
   }

   default double pdf$mIDc$sp(final int v, final UFunc.UImpl2 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(this, BoxesRunTime.boxToInteger(v)));
   }

   // $FF: synthetic method
   static float pdf$mIFc$sp$(final PdfIsUFunc $this, final int v, final UFunc.UImpl2 impl) {
      return $this.pdf$mIFc$sp(v, impl);
   }

   default float pdf$mIFc$sp(final int v, final UFunc.UImpl2 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(this, BoxesRunTime.boxToInteger(v)));
   }

   // $FF: synthetic method
   static int pdf$mIIc$sp$(final PdfIsUFunc $this, final int v, final UFunc.UImpl2 impl) {
      return $this.pdf$mIIc$sp(v, impl);
   }

   default int pdf$mIIc$sp(final int v, final UFunc.UImpl2 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(this, BoxesRunTime.boxToInteger(v)));
   }

   static void $init$(final PdfIsUFunc $this) {
   }
}
