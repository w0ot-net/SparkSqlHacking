package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Statistics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u000593\u0001BB\u0004\u0011\u0002\u0007\u0005a\u0002\u0013\u0005\u0006'\u0001!\t\u0001\u0006\u0005\b1\u0001\u0011\rQ\"\u0001\u001a\u0011\u001dq\u0002A1A\u0005\u0002}Aq\u0001\u000b\u0001C\u0002\u0013\u0005\u0011\u0006C\u0004.\u0001\t\u0007I\u0011\u0001\u0018\u0003\u0015Q\u0013X-Z:Ti\u0006$8O\u0003\u0002\t\u0013\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u000b\u0017\u00059!/\u001a4mK\u000e$(\"\u0001\u0007\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001a\u0004\t\u0003!Ei\u0011aC\u0005\u0003%-\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0016!\t\u0001b#\u0003\u0002\u0018\u0017\t!QK\\5u\u0003-\u0019\u00180\u001c2pYR\u000b'\r\\3\u0016\u0003i\u0001\"a\u0007\u000f\u000e\u0003\u001dI!!H\u0004\u0003\u0017MKXNY8m)\u0006\u0014G.Z\u0001\u000eiJ,WMT8eK\u000e{WO\u001c;\u0016\u0003\u0001\u0002\"!\t\u0012\u000e\u0003\u0001I!a\t\u0013\u0003\tYKWm^\u0005\u0003K\u0019\u0012!b\u0015;bi&\u001cH/[2t\u0015\t9s!\u0001\u0003vi&d\u0017!\u0004:fi\u0006Lg.\u001a3D_VtG/F\u0001+!\t\t3&\u0003\u0002-I\t91i\\;oi\u0016\u0014\u0018A\u0004:fi\u0006Lg.\u001a3CsRK\b/Z\u000b\u0002_A!\u0011\u0005\r\u001a+\u0013\t\tDE\u0001\u0005Rk\u0006tG/T1qa\t\u0019T\bE\u00025smj\u0011!\u000e\u0006\u0003m]\nA\u0001\\1oO*\t\u0001(\u0001\u0003kCZ\f\u0017B\u0001\u001e6\u0005\u0015\u0019E.Y:t!\taT\b\u0004\u0001\u0005\u0013y\u0002\u0011\u0011!A\u0001\u0006\u0003\t%aA0%c%\u0011\u0001\tJ\u0001\u000b]\u0016<()_\"mCN\u001c\u0018C\u0001\"F!\t\u00012)\u0003\u0002E\u0017\t9aj\u001c;iS:<\u0007C\u0001\tG\u0013\t95BA\u0002B]f\u00142!S&M\r\u0011Q\u0005\u0001\u0001%\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0005m\u0001\u0001CA'%\u001b\u00051\u0003"
)
public interface TreesStats {
   void scala$reflect$internal$TreesStats$_setter_$treeNodeCount_$eq(final Statistics.View x$1);

   void scala$reflect$internal$TreesStats$_setter_$retainedCount_$eq(final Statistics.Counter x$1);

   void scala$reflect$internal$TreesStats$_setter_$retainedByType_$eq(final Statistics.QuantMap x$1);

   SymbolTable symbolTable();

   Statistics.View treeNodeCount();

   Statistics.Counter retainedCount();

   Statistics.QuantMap retainedByType();

   static void $init$(final TreesStats $this) {
      $this.scala$reflect$internal$TreesStats$_setter_$treeNodeCount_$eq(((Statistics)$this).newView("#created tree nodes", .MODULE$, (JFunction0.mcI.sp)() -> ((Statistics)$this).symbolTable().nodeCount()));
      $this.scala$reflect$internal$TreesStats$_setter_$retainedCount_$eq(((Statistics)$this).newCounter("#retained tree nodes", .MODULE$));
      $this.scala$reflect$internal$TreesStats$_setter_$retainedByType_$eq(((Statistics)$this).newByClass("#retained tree nodes by type", .MODULE$, () -> ((Statistics)$this).newCounter("", .MODULE$), scala..less.colon.less..MODULE$.refl()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
