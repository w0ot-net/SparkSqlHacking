package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.Params;
import org.apache.spark.mllib.tree.impurity.Entropy$;
import org.apache.spark.mllib.tree.impurity.Gini$;
import org.apache.spark.mllib.tree.impurity.Impurity;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054\u0001b\u0003\u0007\u0011\u0002\u0007\u0005aB\u0006\u0005\u0006G\u0001!\t!\n\u0005\bS\u0001\u0011\r\u0011\"\u0002+\u0011\u0015I\u0004\u0001\"\u0002;\u0011\u0019Y\u0004\u0001\"\u0001\u000fy\u001d1Q\t\u0004E\u0001\u001d\u00193aa\u0003\u0007\t\u00029A\u0005\"B)\u0007\t\u0003\u0011\u0006bB*\u0007\u0005\u0004%)\u0001\u0016\u0005\u00071\u001a\u0001\u000bQB+\t\u000fe3\u0011\u0011!C\u00055\n!BK]3f\u00072\f7o]5gS\u0016\u0014\b+\u0019:b[NT!!\u0004\b\u0002\tQ\u0014X-\u001a\u0006\u0003\u001fA\t!!\u001c7\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c2\u0001A\f\u001e!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fMB\u0011a$I\u0007\u0002?)\u0011\u0001ED\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003E}\u0011a\u0001U1sC6\u001c\u0018A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003\u0019\u0002\"\u0001G\u0014\n\u0005!J\"\u0001B+oSR\f\u0001\"[7qkJLG/_\u000b\u0002WA\u0019a\u0004\f\u0018\n\u00055z\"!\u0002)be\u0006l\u0007CA\u00187\u001d\t\u0001D\u0007\u0005\u0002235\t!G\u0003\u00024I\u00051AH]8pizJ!!N\r\u0002\rA\u0013X\rZ3g\u0013\t9\u0004H\u0001\u0004TiJLgn\u001a\u0006\u0003ke\t1bZ3u\u00136\u0004XO]5usV\ta&\u0001\bhKR|E\u000eZ%naV\u0014\u0018\u000e^=\u0016\u0003u\u0002\"AP\"\u000e\u0003}R!!\u000b!\u000b\u00055\t%B\u0001\"\u0011\u0003\u0015iG\u000e\\5c\u0013\t!uH\u0001\u0005J[B,(/\u001b;z\u0003Q!&/Z3DY\u0006\u001c8/\u001b4jKJ\u0004\u0016M]1ngB\u0011qIB\u0007\u0002\u0019M\u0019aaF%\u0011\u0005){U\"A&\u000b\u00051k\u0015AA5p\u0015\u0005q\u0015\u0001\u00026bm\u0006L!\u0001U&\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u00051\u0015aE:vaB|'\u000f^3e\u00136\u0004XO]5uS\u0016\u001cX#A+\u0011\u0007a1f&\u0003\u0002X3\t)\u0011I\u001d:bs\u0006!2/\u001e9q_J$X\rZ%naV\u0014\u0018\u000e^5fg\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012a\u0017\t\u00039~k\u0011!\u0018\u0006\u0003=6\u000bA\u0001\\1oO&\u0011\u0001-\u0018\u0002\u0007\u001f\nTWm\u0019;"
)
public interface TreeClassifierParams extends Params {
   static String[] supportedImpurities() {
      return TreeClassifierParams$.MODULE$.supportedImpurities();
   }

   void org$apache$spark$ml$tree$TreeClassifierParams$_setter_$impurity_$eq(final Param x$1);

   Param impurity();

   // $FF: synthetic method
   static String getImpurity$(final TreeClassifierParams $this) {
      return $this.getImpurity();
   }

   default String getImpurity() {
      return ((String)this.$(this.impurity())).toLowerCase(Locale.ROOT);
   }

   // $FF: synthetic method
   static Impurity getOldImpurity$(final TreeClassifierParams $this) {
      return $this.getOldImpurity();
   }

   default Impurity getOldImpurity() {
      String var2 = this.getImpurity();
      switch (var2 == null ? 0 : var2.hashCode()) {
         case -1591567247:
            if ("entropy".equals(var2)) {
               return Entropy$.MODULE$;
            }
            break;
         case 3172893:
            if ("gini".equals(var2)) {
               return Gini$.MODULE$;
            }
      }

      throw new RuntimeException("TreeClassifierParams was given unrecognized impurity: " + this.impurity() + ".");
   }

   // $FF: synthetic method
   static boolean $anonfun$impurity$1(final String value) {
      return .MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])TreeClassifierParams$.MODULE$.supportedImpurities()), value.toLowerCase(Locale.ROOT));
   }

   static void $init$(final TreeClassifierParams $this) {
      $this.org$apache$spark$ml$tree$TreeClassifierParams$_setter_$impurity_$eq(new Param($this, "impurity", "Criterion used for information gain calculation (case-insensitive). Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])TreeClassifierParams$.MODULE$.supportedImpurities()).mkString(", "), (value) -> BoxesRunTime.boxToBoolean($anonfun$impurity$1(value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.impurity().$minus$greater("gini")}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
