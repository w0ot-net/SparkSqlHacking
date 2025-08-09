package breeze.stats.distributions;

import breeze.generic.MappingUFunc;
import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054\u0001b\u0003\u0007\u0011\u0002\u0007\u00051\u0003\u0018\u0005\u0006C\u0001!\tAI\u0004\u0006M\u0001A\u0019a\n\u0004\u0006S\u0001A\tA\u000b\u0005\u0006\u0019\u000e!\tA\u0015\u0005\u0006'\u000e!\t\u0001\u0016\u0004\u0005c\u0001\t!\u0007\u0003\u0005C\r\t\u0015\r\u0011\"\u0001D\u0011!YeA!A!\u0002\u0013!\u0005\"\u0002'\u0007\t\u0003i\u0005bB-\u0001\u0003\u0003%\u0019A\u0017\u0002$\u0007>tG/\u001b8v_V\u001cH)[:ue&\u0014W\u000f^5p]V3UO\\2Qe>4\u0018\u000eZ3s\u0015\tia\"A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003\u001fA\tQa\u001d;biNT\u0011!E\u0001\u0007EJ,WM_3\u0004\u0001U\u0019A#O#\u0014\u0007\u0001)2\u0004\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f\u0014VM\u001a\t\u00039}i\u0011!\b\u0006\u0003=A\tqaZ3oKJL7-\u0003\u0002!;\taQ*\u00199qS:<WKR;oG\u00061A%\u001b8ji\u0012\"\u0012a\t\t\u0003-\u0011J!!J\f\u0003\tUs\u0017\u000e^\u0001\nE\u0006\u001c\u0018nY%na2\u0004\"\u0001K\u0002\u000e\u0003\u0001\u0011\u0011BY1tS\u000eLU\u000e\u001d7\u0014\u0007\r)2\u0006E\u0003)YA:t*\u0003\u0002.]\t)\u0011*\u001c9me%\u0011q&\b\u0002\u0006+\u001a+hn\u0019\t\u0003Q\u0019\u00111dQ8oi&tWo\\;t\t&\u001cHO]+Gk:\u001cwK]1qa\u0016\u00148c\u0001\u0004\u0016gA)A'\u000e\u00158a5\tA\"\u0003\u00027\u0019\tQ\u0001\u000b\u001a4JgV3UO\\2\u0011\u0005aJD\u0002\u0001\u0003\u0006u\u0001\u0011\ra\u000f\u0002\u0002)F\u0011Ah\u0010\t\u0003-uJ!AP\f\u0003\u000f9{G\u000f[5oOB\u0011a\u0003Q\u0005\u0003\u0003^\u00111!\u00118z\u0003\u0011!\u0017n\u001d;\u0016\u0003\u0011\u0003\"\u0001O#\u0005\u000b\u0019\u0003!\u0019A$\u0003\u0003\u0011\u000b\"\u0001\u0010%\u0011\u0007QJu'\u0003\u0002K\u0019\ty1i\u001c8uS:,x.^:ESN$(/A\u0003eSN$\b%\u0001\u0004=S:LGO\u0010\u000b\u0003a9CQAQ\u0005A\u0002\u0011\u0003\"A\u0006)\n\u0005E;\"A\u0002#pk\ndW\rF\u0001(\u0003\u0015\t\u0007\u000f\u001d7z)\ryUk\u0016\u0005\u0006-\u0016\u0001\r\u0001M\u0001\u0002o\")\u0001,\u0002a\u0001o\u0005\ta/A\u000eD_:$\u0018N\\;pkN$\u0015n\u001d;s+\u001a+hnY,sCB\u0004XM\u001d\u000b\u0003amCQA\u0011\u0006A\u0002\u0011\u00132!X0a\r\u0011q\u0006\u0001\u0001/\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\tQ\u0002q\u0007\u0012\t\u000399\u0002"
)
public interface ContinuousDistributionUFuncProvider extends MappingUFunc {
   basicImpl$ basicImpl();

   // $FF: synthetic method
   static ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper$(final ContinuousDistributionUFuncProvider $this, final ContinuousDistr dist) {
      return $this.ContinuousDistrUFuncWrapper(dist);
   }

   default ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return new ContinuousDistrUFuncWrapper(dist);
   }

   static void $init$(final ContinuousDistributionUFuncProvider $this) {
   }

   public class basicImpl$ implements UFunc.UImpl2 {
      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public double apply(final ContinuousDistrUFuncWrapper w, final Object v) {
         return w.dist().pdf(v);
      }
   }

   public class ContinuousDistrUFuncWrapper implements PdfIsUFunc {
      private final ContinuousDistr dist;
      // $FF: synthetic field
      public final ContinuousDistributionUFuncProvider $outer;

      public final Object pdf(final Object v, final UFunc.UImpl2 impl) {
         return PdfIsUFunc.pdf$(this, v, impl);
      }

      public final double pdf$mDDc$sp(final double v, final UFunc.UImpl2 impl) {
         return PdfIsUFunc.pdf$mDDc$sp$(this, v, impl);
      }

      public final float pdf$mDFc$sp(final double v, final UFunc.UImpl2 impl) {
         return PdfIsUFunc.pdf$mDFc$sp$(this, v, impl);
      }

      public final int pdf$mDIc$sp(final double v, final UFunc.UImpl2 impl) {
         return PdfIsUFunc.pdf$mDIc$sp$(this, v, impl);
      }

      public final double pdf$mFDc$sp(final float v, final UFunc.UImpl2 impl) {
         return PdfIsUFunc.pdf$mFDc$sp$(this, v, impl);
      }

      public final float pdf$mFFc$sp(final float v, final UFunc.UImpl2 impl) {
         return PdfIsUFunc.pdf$mFFc$sp$(this, v, impl);
      }

      public final int pdf$mFIc$sp(final float v, final UFunc.UImpl2 impl) {
         return PdfIsUFunc.pdf$mFIc$sp$(this, v, impl);
      }

      public final double pdf$mIDc$sp(final int v, final UFunc.UImpl2 impl) {
         return PdfIsUFunc.pdf$mIDc$sp$(this, v, impl);
      }

      public final float pdf$mIFc$sp(final int v, final UFunc.UImpl2 impl) {
         return PdfIsUFunc.pdf$mIFc$sp$(this, v, impl);
      }

      public final int pdf$mIIc$sp(final int v, final UFunc.UImpl2 impl) {
         return PdfIsUFunc.pdf$mIIc$sp$(this, v, impl);
      }

      public ContinuousDistr dist() {
         return this.dist;
      }

      // $FF: synthetic method
      public ContinuousDistributionUFuncProvider breeze$stats$distributions$ContinuousDistributionUFuncProvider$ContinuousDistrUFuncWrapper$$$outer() {
         return this.$outer;
      }

      public ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
         this.dist = dist;
         if (ContinuousDistributionUFuncProvider.this == null) {
            throw null;
         } else {
            this.$outer = ContinuousDistributionUFuncProvider.this;
            super();
            PdfIsUFunc.$init$(this);
         }
      }
   }
}
