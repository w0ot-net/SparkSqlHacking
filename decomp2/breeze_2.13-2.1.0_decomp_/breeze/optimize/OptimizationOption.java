package breeze.optimize;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3q!\u0002\u0004\u0011\u0002G\u00052bB\u0003B\r!\u0005!IB\u0003\u0006\r!\u00051\tC\u0003E\u0005\u0011\u0005Q\tC\u0003G\u0005\u0011\rqI\u0001\nPaRLW.\u001b>bi&|gn\u00149uS>t'BA\u0004\t\u0003!y\u0007\u000f^5nSj,'\"A\u0005\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u00192\u0001\u0001\u0007\u0013!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB!QbE\u000b\u0016\u0013\t!bBA\u0005Gk:\u001cG/[8ocA\u0011a\u0003\t\b\u0003/yq!\u0001G\u000f\u000f\u0005eaR\"\u0001\u000e\u000b\u0005mQ\u0011A\u0002\u001fs_>$h(C\u0001\n\u0013\t9\u0001\"\u0003\u0002 \r\u0005\u0019b)\u001b:ti>\u0013H-\u001a:NS:LW.\u001b>fe&\u0011\u0011E\t\u0002\n\u001fB$\b+\u0019:b[NT!a\b\u0004*\u0015\u0001!\u0013gM\u001b8smjtH\u0002\u0003&\u0001\u00011#!\u0004\u001fm_\u000e\fG\u000eI2iS2$ghE\u0002%O=\u0002\"\u0001K\u0017\u000e\u0003%R!AK\u0016\u0002\t1\fgn\u001a\u0006\u0002Y\u0005!!.\u0019<b\u0013\tq\u0013F\u0001\u0004PE*,7\r\u001e\t\u0003a\u0001i\u0011AB\u0005\u0003e\u0019\u0011\u0011BQ1uG\"\u001c\u0016N_3\n\u0005Q2!\u0001\u0005'2%\u0016<W\u000f\\1sSj\fG/[8o\u0013\t1dA\u0001\tMeI+w-\u001e7be&T\u0018\r^5p]&\u0011\u0001H\u0002\u0002\u000e\u001b\u0006D\u0018\n^3sCRLwN\\:\u000b\u0005i2\u0011a\u0003)sK\u001a,'OQ1uG\"T!\u0001\u0010\u0004\u0002\u0019A\u0013XMZ3s\u001f:d\u0017N\\3\n\u0005y2!!D*uKB\u001c\u0016N_3TG\u0006dW-\u0003\u0002A\r\tIAk\u001c7fe\u0006t7-Z\u0001\u0013\u001fB$\u0018.\\5{CRLwN\\(qi&|g\u000e\u0005\u00021\u0005M\u0011!\u0001D\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\t\u000bQB\u001a:p[>\u0003H\u000fU1sC6\u001cHCA\u0018I\u0011\u0015IE\u00011\u0001\u0016\u0003%y\u0007\u000f\u001e)be\u0006l7\u000f"
)
public interface OptimizationOption extends Function1 {
   static OptimizationOption fromOptParams(final FirstOrderMinimizer.OptParams optParams) {
      return OptimizationOption$.MODULE$.fromOptParams(optParams);
   }
}
