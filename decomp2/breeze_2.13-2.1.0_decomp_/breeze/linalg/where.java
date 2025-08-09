package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.math.Semiring;
import scala.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q<Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0004yAQ!U\u0001\u0005\u0004I\u000bQa\u001e5fe\u0016T!a\u0002\u0005\u0002\r1Lg.\u00197h\u0015\u0005I\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u00051\tQ\"\u0001\u0004\u0003\u000b]DWM]3\u0014\u0007\u0005yQ\u0003\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VM\u001a\t\u0003-ei\u0011a\u0006\u0006\u00031!\tqaZ3oKJL7-\u0003\u0002\u001b/\t)QKR;oG\u00061A(\u001b8jiz\"\u0012aC\u0001\u001fo\",'/\u001a$s_6$&/\u0019<feN,7*Z=WC2,X\rU1jeN,Ba\b\u0014=\u000fR\u0019\u0001EP%\u0011\t\u0005\u0012CeL\u0007\u0002\u0003%\u00111%\u0007\u0002\u0005\u00136\u0004H\u000e\u0005\u0002&M1\u0001A!B\u0014\u0004\u0005\u0004A#!\u0001+\u0012\u0005%b\u0003C\u0001\t+\u0013\tY\u0013CA\u0004O_RD\u0017N\\4\u0011\u0005Ai\u0013B\u0001\u0018\u0012\u0005\r\te.\u001f\t\u0004aaZdBA\u00197\u001d\t\u0011T'D\u00014\u0015\t!$\"\u0001\u0004=e>|GOP\u0005\u0002%%\u0011q'E\u0001\ba\u0006\u001c7.Y4f\u0013\tI$H\u0001\u0006J]\u0012,\u00070\u001a3TKFT!aN\t\u0011\u0005\u0015bD!B\u001f\u0004\u0005\u0004A#!A&\t\u000b}\u001a\u00019\u0001!\u0002\tQ\u0014\u0018M\u001e\t\u0006\u0003\u0012#3HR\u0007\u0002\u0005*\u00111IB\u0001\bgV\u0004\bo\u001c:u\u0013\t)%I\u0001\rDC:$&/\u0019<feN,7*Z=WC2,X\rU1jeN\u0004\"!J$\u0005\u000b!\u001b!\u0019\u0001\u0015\u0003\u0003YCQAS\u0002A\u0004-\u000bAa]3nSB\u0019Aj\u0014$\u000e\u00035S!A\u0014\u0005\u0002\t5\fG\u000f[\u0005\u0003!6\u0013\u0001bU3nSJLgnZ\u0001#o\",'/Z\u001aBe\u001e4%o\\7Ue\u00064XM]:f\u0017\u0016Lh+\u00197vKB\u000b\u0017N]:\u0016\u000fMC&\f[9k;R!Ak\u00187s!\u0019\tSkV-Z9&\u0011a+\u0007\u0002\u0006\u00136\u0004Hn\r\t\u0003Ka#Qa\n\u0003C\u0002!\u0002\"!\n.\u0005\u000bm#!\u0019\u0001\u0015\u0003\u0003E\u0003\"!J/\u0005\u000by#!\u0019\u0001\u0015\u0003\u0003UCQ\u0001\u0019\u0003A\u0004\u0005\f!!\u001a<\u0011\tA\u0011\u0017\fZ\u0005\u0003GF\u0011\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u0011\t1)w-[\u0005\u0003M\u001a\u00111\"U;bg&$VM\\:peB\u0011Q\u0005\u001b\u0003\u0006{\u0011\u0011\r\u0001\u000b\t\u0003K)$Qa\u001b\u0003C\u0002!\u0012!A\u0016\u001a\t\u000b}\"\u00019A7\u0011\u000f\u0005swk\u001a9j9&\u0011qN\u0011\u0002\u0014\u0007\u0006tW*\u00199LKf4\u0016\r\\;f!\u0006L'o\u001d\t\u0003KE$Q\u0001\u0013\u0003C\u0002!BQA\u0013\u0003A\u0004M\u00042\u0001T(q\u0001"
)
public final class where {
   public static UFunc.UImpl3 where3ArgFromTraverseKeyValuePairs(final .less.colon.less ev, final CanMapKeyValuePairs trav, final Semiring semi) {
      return where$.MODULE$.where3ArgFromTraverseKeyValuePairs(ev, trav, semi);
   }

   public static UFunc.UImpl whereFromTraverseKeyValuePairs(final CanTraverseKeyValuePairs trav, final Semiring semi) {
      return where$.MODULE$.whereFromTraverseKeyValuePairs(trav, semi);
   }

   public static Object withSink(final Object s) {
      return where$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return where$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return where$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return where$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return where$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return where$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return where$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return where$.MODULE$.apply(v, impl);
   }
}
