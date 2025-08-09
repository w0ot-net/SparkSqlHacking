package breeze.linalg;

import breeze.generic.UFunc;
import scala.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00059;Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0004yAQ!P\u0001\u0005\u0004y\n1\u0001Z5n\u0015\t9\u0001\"\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0013\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\r\u00035\taAA\u0002eS6\u001c2!A\b\u0016!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0011a#G\u0007\u0002/)\u0011\u0001\u0004C\u0001\bO\u0016tWM]5d\u0013\tQrCA\u0003V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0017\u0005A\u0011.\u001c9m-\u0012KW.F\u0002 w\u0019\"\"\u0001\t\u001a\u0011\t\u0005\u0012CeL\u0007\u0002\u0003%\u00111%\u0007\u0002\u0005\u00136\u0004H\u000e\u0005\u0002&M1\u0001A!B\u0014\u0004\u0005\u0004A#!\u0001,\u0012\u0005%b\u0003C\u0001\t+\u0013\tY\u0013CA\u0004O_RD\u0017N\\4\u0011\u0005Ai\u0013B\u0001\u0018\u0012\u0005\r\te.\u001f\t\u0003!AJ!!M\t\u0003\u0007%sG\u000fC\u00034\u0007\u0001\u000fA'\u0001\u0003wS\u0016<\b\u0003\u0002\t6I]J!AN\t\u0003!\u0011bWm]:%G>dwN\u001c\u0013mKN\u001c\bc\u0001\u00079u%\u0011\u0011H\u0002\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u0005\u0015ZD!\u0002\u001f\u0004\u0005\u0004A#!\u0001+\u0002\u0011%l\u0007\u000f\\'ES6,2aP'C)\t\u0001u\t\u0005\u0003\"E\u0005#\u0005CA\u0013C\t\u0015\u0019EA1\u0001)\u0005\u0005i\u0005\u0003\u0002\tF_=J!AR\t\u0003\rQ+\b\u000f\\33\u0011\u0015\u0019D\u0001q\u0001I!\u0011\u0001R'Q%\u0011\u00071QE*\u0003\u0002L\r\t1Q*\u0019;sSb\u0004\"!J'\u0005\u000bq\"!\u0019\u0001\u0015"
)
public final class dim {
   public static UFunc.UImpl implMDim(final .less.colon.less view) {
      return dim$.MODULE$.implMDim(view);
   }

   public static UFunc.UImpl implVDim(final .less.colon.less view) {
      return dim$.MODULE$.implVDim(view);
   }

   public static Object withSink(final Object s) {
      return dim$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return dim$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return dim$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return dim$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return dim$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return dim$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return dim$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return dim$.MODULE$.apply(v, impl);
   }
}
