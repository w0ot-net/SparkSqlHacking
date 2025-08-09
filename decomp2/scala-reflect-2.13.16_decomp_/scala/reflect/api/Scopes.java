package scala.reflect.api;

import scala.collection.Iterable;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005qB\u0010\u0005\u0006)\u0001!\t!\u0006\u0003\u00063\u0001\u0011\tA\u0007\u0004\bG\u0001\u0001\n1%\u0001%\t\u0015\t\u0004A!\u00013\r\u001d1\u0004\u0001%A\u0012\u0002]BQ\u0001O\u0003\u0007\u0002e\u0012aaU2pa\u0016\u001c(BA\u0005\u000b\u0003\r\t\u0007/\u001b\u0006\u0003\u00171\tqA]3gY\u0016\u001cGOC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\t\u0011\u0005E\u0011R\"\u0001\u0007\n\u0005Ma!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002-A\u0011\u0011cF\u0005\u000311\u0011A!\u00168ji\n)1kY8qKF\u00111D\b\t\u0003#qI!!\b\u0007\u0003\t9+H\u000e\u001c\n\u0004?A\tc\u0001\u0002\u0011\u0001\u0001y\u0011A\u0002\u0010:fM&tW-\\3oiz\u0002\"AI\u0002\u000e\u0003\u0001\u0011\u0001bU2pa\u0016\f\u0005/[\n\u0004\u0007A)\u0003c\u0001\u0014*Y9\u0011\u0011cJ\u0005\u0003Q1\tq\u0001]1dW\u0006<W-\u0003\u0002+W\tA\u0011\n^3sC\ndWM\u0003\u0002)\u0019A\u0011!%L\u0005\u0003]=\u0012aaU=nE>d\u0017B\u0001\u0019\t\u0005\u001d\u0019\u00160\u001c2pYN\u00141\"T3nE\u0016\u00148kY8qKF\u00111d\r\n\u0005iA)TH\u0002\u0003!\u0001\u0001\u0019\u0004C\u0001\u0012\u0006\u00059iU-\u001c2feN\u001bw\u000e]3Ba&\u001c2!\u0002\t\"\u0003\u0019\u0019xN\u001d;fIV\t!\bE\u0002'w1J!\u0001P\u0016\u0003\t1K7\u000f\u001e\t\u0003E\t\u0001\"a\u0010!\u000e\u0003!I!!\u0011\u0005\u0003\u0011Us\u0017N^3sg\u0016\u0004"
)
public interface Scopes {
   static void $init$(final Scopes $this) {
   }

   public interface MemberScopeApi extends ScopeApi {
      List sorted();
   }

   public interface ScopeApi extends Iterable {
   }
}
