package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000552Aa\u0002\u0005\u0001#!Aa\u0003\u0001B\u0001B\u0003%q\u0003\u0003\u0005\u001b\u0001\t\u0005\t\u0015!\u0003\u001c\u0011!y\u0002A!A!\u0002\u0013Y\u0002\u0002\u0003\u0011\u0001\u0005\u0003\u0005\u000b\u0011B\u000e\t\u000b\u0005\u0002A\u0011\u0001\u0012\t\u000b!\u0002A\u0011I\u0015\u0003'Q\u0013\u0018M\\:qCJ,g\u000e\u001e)pg&$\u0018n\u001c8\u000b\u0005%Q\u0011\u0001B;uS2T!a\u0003\u0007\u0002\u0011%tG/\u001a:oC2T!!\u0004\b\u0002\u000fI,g\r\\3di*\tq\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001\u0011\u0002CA\n\u0015\u001b\u0005A\u0011BA\u000b\t\u00055\u0011\u0016M\\4f!>\u001c\u0018\u000e^5p]\u0006A1o\\;sG\u0016Le\u000e\u0005\u0002\u00141%\u0011\u0011\u0004\u0003\u0002\u000b'>,(oY3GS2,\u0017aB:uCJ$\u0018J\u001c\t\u00039ui\u0011AD\u0005\u0003=9\u00111!\u00138u\u0003\u001d\u0001x.\u001b8u\u0013:\fQ!\u001a8e\u0013:\fa\u0001P5oSRtD#B\u0012%K\u0019:\u0003CA\n\u0001\u0011\u00151R\u00011\u0001\u0018\u0011\u0015QR\u00011\u0001\u001c\u0011\u0015yR\u00011\u0001\u001c\u0011\u0015\u0001S\u00011\u0001\u001c\u00035I7\u000f\u0016:b]N\u0004\u0018M]3oiV\t!\u0006\u0005\u0002\u001dW%\u0011AF\u0004\u0002\b\u0005>|G.Z1o\u0001"
)
public class TransparentPosition extends RangePosition {
   public boolean isTransparent() {
      return true;
   }

   public TransparentPosition(final SourceFile sourceIn, final int startIn, final int pointIn, final int endIn) {
      super(sourceIn, startIn, pointIn, endIn);
   }
}
