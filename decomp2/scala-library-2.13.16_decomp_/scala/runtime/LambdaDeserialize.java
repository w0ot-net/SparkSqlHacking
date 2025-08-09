package scala.runtime;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleInfo;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SerializedLambda;
import java.util.HashMap;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=b\u0001\u0002\b\u0010\u0005QA\u0001\"\u0007\u0001\u0003\u0002\u0003\u0006IA\u0007\u0005\tQ\u0001\u0011\t\u0011)A\u0005S!)q\u0006\u0001C\u0005a!9Q\u0007\u0001b\u0001\n\u00131\u0004B\u0002%\u0001A\u0003%q\u0007C\u0004J\u0001\t\u0007I\u0011\u0002\u001c\t\r)\u0003\u0001\u0015!\u00038\u0011\u0015Y\u0005\u0001\"\u0001M\u000f\u0015\u0011v\u0002#\u0001T\r\u0015qq\u0002#\u0001U\u0011\u0015y#\u0002\"\u0001V\u0011\u00151&\u0002\"\u0001X\u0011\u001d\t\u0019C\u0003C\u0001\u0003K\u0011\u0011\u0003T1nE\u0012\fG)Z:fe&\fG.\u001b>f\u0015\t\u0001\u0012#A\u0004sk:$\u0018.\\3\u000b\u0003I\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001+A\u0011acF\u0007\u0002#%\u0011\u0001$\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\r1|wn[;q!\tYRE\u0004\u0002\u001dG5\tQD\u0003\u0002\u001f?\u00051\u0011N\u001c<pW\u0016T!\u0001I\u0011\u0002\t1\fgn\u001a\u0006\u0002E\u0005!!.\u0019<b\u0013\t!S$A\u0007NKRDw\u000e\u001a%b]\u0012dWm]\u0005\u0003M\u001d\u0012a\u0001T8pWV\u0004(B\u0001\u0013\u001e\u00035!\u0018M]4fi6+G\u000f[8egB\u0019aC\u000b\u0017\n\u0005-\n\"!B!se\u0006L\bC\u0001\u000f.\u0013\tqSD\u0001\u0007NKRDw\u000e\u001a%b]\u0012dW-\u0001\u0004=S:LGO\u0010\u000b\u0004cM\"\u0004C\u0001\u001a\u0001\u001b\u0005y\u0001\"B\r\u0004\u0001\u0004Q\u0002\"\u0002\u0015\u0004\u0001\u0004I\u0013a\u0004;be\u001e,G/T3uQ>$W*\u00199\u0016\u0003]\u0002B\u0001O\u001e>Y5\t\u0011H\u0003\u0002;C\u0005!Q\u000f^5m\u0013\ta\u0014HA\u0004ICNDW*\u00199\u0011\u0005y*eBA D!\t\u0001\u0015#D\u0001B\u0015\t\u00115#\u0001\u0004=e>|GOP\u0005\u0003\tF\ta\u0001\u0015:fI\u00164\u0017B\u0001$H\u0005\u0019\u0019FO]5oO*\u0011A)E\u0001\u0011i\u0006\u0014x-\u001a;NKRDw\u000eZ'ba\u0002\nQaY1dQ\u0016\faaY1dQ\u0016\u0004\u0013!\u00053fg\u0016\u0014\u0018.\u00197ju\u0016d\u0015-\u001c2eCR\u0011Q#\u0014\u0005\u0006\u001d\"\u0001\raT\u0001\u000bg\u0016\u0014\u0018.\u00197ju\u0016$\u0007C\u0001\u000fQ\u0013\t\tVD\u0001\tTKJL\u0017\r\\5{K\u0012d\u0015-\u001c2eC\u0006\tB*Y7cI\u0006$Um]3sS\u0006d\u0017N_3\u0011\u0005IR1C\u0001\u0006\u0016)\u0005\u0019\u0016!\u00032p_R\u001cHO]1q)\u0015A6\f\u00180d!\ta\u0012,\u0003\u0002[;\tA1)\u00197m'&$X\rC\u0003\u001a\u0019\u0001\u0007!\u0004C\u0003^\u0019\u0001\u0007Q(A\u0006j]Z|7.\u001a3OC6,\u0007\"B0\r\u0001\u0004\u0001\u0017aC5om>\\W\r\u001a+za\u0016\u0004\"\u0001H1\n\u0005\tl\"AC'fi\"|G\rV=qK\")\u0001\u0006\u0004a\u0001IB\u0019a#\u001a\u0017\n\u0005\u0019\f\"A\u0003\u001fsKB,\u0017\r^3e}!\u0012A\u0002\u001b\t\u0003S2l\u0011A\u001b\u0006\u0003WF\t!\"\u00198o_R\fG/[8o\u0013\ti'NA\u0004wCJ\f'oZ:)\u00071y7\u0010E\u0002\u0017aJL!!]\t\u0003\rQD'o\\<t!\t\u0019\bP\u0004\u0002um:\u0011\u0001)^\u0005\u0002%%\u0011q/E\u0001\ba\u0006\u001c7.Y4f\u0013\tI(PA\u0005UQJ|w/\u00192mK*\u0011q/E\u0019\u0006=ub\u0018\u0011E\u0019\tGu\f\u0019!a\u0006\u0002\u0006U\u0011ap`\u000b\u0002{\u00119\u0011\u0011A\nC\u0002\u0005-!!\u0001+\n\t\u0005\u0015\u0011qA\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u000b\u0007\u0005%\u0011#\u0001\u0004uQJ|wo]\t\u0005\u0003\u001b\t\u0019\u0002E\u0002\u0017\u0003\u001fI1!!\u0005\u0012\u0005\u001dqu\u000e\u001e5j]\u001e\u00042!!\u0006y\u001d\t1b/M\u0005$\u00033\tY\"!\b\u0002\n9\u0019a#a\u0007\n\u0007\u0005%\u0011#M\u0003#-E\tyBA\u0003tG\u0006d\u0017-\r\u0002'e\u0006!b.Y7f\u0003:$G)Z:de&\u0004Ho\u001c:LKf$R!PA\u0014\u0003WAa!!\u000b\u000e\u0001\u0004i\u0014\u0001\u00028b[\u0016Da!!\f\u000e\u0001\u0004i\u0014A\u00033fg\u000e\u0014\u0018\u000e\u001d;pe\u0002"
)
public final class LambdaDeserialize {
   private final MethodHandles.Lookup lookup;
   private final HashMap targetMethodMap;
   private final HashMap cache;

   public static CallSite bootstrap(final MethodHandles.Lookup lookup, final String invokedName, final MethodType invokedType, final MethodHandle... targetMethods) {
      return LambdaDeserialize$.MODULE$.bootstrap(lookup, invokedName, invokedType, targetMethods);
   }

   public static String nameAndDescriptorKey(final String name, final String descriptor) {
      return LambdaDeserialize$.MODULE$.nameAndDescriptorKey(name, descriptor);
   }

   public static CallSite bootstrap(final MethodHandles.Lookup lookup, final String invokedName, final MethodType invokedType, final Seq targetMethods) throws Throwable {
      return LambdaDeserialize$.MODULE$.bootstrap(lookup, invokedName, invokedType, targetMethods);
   }

   private HashMap targetMethodMap() {
      return this.targetMethodMap;
   }

   private HashMap cache() {
      return this.cache;
   }

   public Object deserializeLambda(final SerializedLambda serialized) {
      return LambdaDeserializer$.MODULE$.deserializeLambda(this.lookup, this.cache(), this.targetMethodMap(), serialized);
   }

   // $FF: synthetic method
   public static final MethodHandle $anonfun$new$1(final LambdaDeserialize $this, final MethodHandle targetMethod) {
      MethodHandleInfo info = $this.lookup.revealDirect(targetMethod);
      String key = LambdaDeserialize$.MODULE$.nameAndDescriptorKey(info.getName(), info.getMethodType().toMethodDescriptorString());
      return (MethodHandle)$this.targetMethodMap().put(key, targetMethod);
   }

   public LambdaDeserialize(final MethodHandles.Lookup lookup, final MethodHandle[] targetMethods) {
      this.lookup = lookup;
      this.targetMethodMap = new HashMap(targetMethods.length);

      for(MethodHandle var5 : targetMethods) {
         $anonfun$new$1(this, var5);
      }

      this.cache = new HashMap();
   }
}
