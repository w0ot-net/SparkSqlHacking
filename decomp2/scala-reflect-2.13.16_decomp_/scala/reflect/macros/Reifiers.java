package scala.reflect.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;

@ScalaSignature(
   bytes = "\u0006\u000553\u0001\u0002C\u0005\u0011\u0002\u0007\u0005\u0001c\u0012\u0005\u0006+\u00011\tA\u0006\u0005\u0006G\u00011\t\u0001\n\u0005\bc\u0001\t\n\u0011\"\u00013\u0011\u0015i\u0004A\"\u0001?\u0011\u001d\t\u0005!%A\u0005\u0002IBQA\u0011\u0001\u0007\u0002\rCQ\u0001\u0012\u0001\u0007\u0002\u0015\u0013\u0001BU3jM&,'o\u001d\u0006\u0003\u0015-\ta!\\1de>\u001c(B\u0001\u0007\u000e\u0003\u001d\u0011XM\u001a7fGRT\u0011AD\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001\u0011\u0003\u0005\u0002\u0013'5\tQ\"\u0003\u0002\u0015\u001b\t1\u0011I\\=SK\u001a\f\u0011B]3jMf$&/Z3\u0015\t]ir$\t\t\u00031ei\u0011\u0001A\u0005\u00035m\u0011A\u0001\u0016:fK&\u0011A$\u0003\u0002\b\u00032L\u0017m]3t\u0011\u0015q\u0012\u00011\u0001\u0018\u0003!)h.\u001b<feN,\u0007\"\u0002\u0011\u0002\u0001\u00049\u0012AB7jeJ|'\u000fC\u0003#\u0003\u0001\u0007q#\u0001\u0003ue\u0016,\u0017!\u0003:fS\u001aLH+\u001f9f)\u00159REJ\u0014-\u0011\u0015q\"\u00011\u0001\u0018\u0011\u0015\u0001#\u00011\u0001\u0018\u0011\u0015A#\u00011\u0001*\u0003\r!\b/\u001a\t\u00031)J!aK\u000e\u0003\tQK\b/\u001a\u0005\b[\t\u0001\n\u00111\u0001/\u0003!\u0019wN\\2sKR,\u0007C\u0001\n0\u0013\t\u0001TBA\u0004C_>dW-\u00198\u0002'I,\u0017NZ=UsB,G\u0005Z3gCVdG\u000f\n\u001b\u0016\u0003MR#A\f\u001b,\u0003U\u0002\"AN\u001e\u000e\u0003]R!\u0001O\u001d\u0002\u0013Ut7\r[3dW\u0016$'B\u0001\u001e\u000e\u0003)\tgN\\8uCRLwN\\\u0005\u0003y]\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003E\u0011X-\u001b4z%VtG/[7f\u00072\f7o\u001d\u000b\u0004/}\u0002\u0005\"\u0002\u0015\u0005\u0001\u0004I\u0003bB\u0017\u0005!\u0003\u0005\rAL\u0001\u001ce\u0016Lg-\u001f*v]RLW.Z\"mCN\u001cH\u0005Z3gCVdG\u000f\n\u001a\u00025I,\u0017NZ=F]\u000edwn]5oOJ+h\u000e^5nK\u000ec\u0017m]:\u0016\u0003]\t1\"\u001e8sK&4\u0017\u0010\u0016:fKR\u0011qC\u0012\u0005\u0006E\u001d\u0001\ra\u0006\t\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015&\t\u0001B\u00197bG.\u0014w\u000e_\u0005\u0003\u0019&\u0013qaQ8oi\u0016DH\u000f"
)
public interface Reifiers {
   Trees.TreeApi reifyTree(final Trees.TreeApi universe, final Trees.TreeApi mirror, final Trees.TreeApi tree);

   Trees.TreeApi reifyType(final Trees.TreeApi universe, final Trees.TreeApi mirror, final Types.TypeApi tpe, final boolean concrete);

   // $FF: synthetic method
   static boolean reifyType$default$4$(final Reifiers $this) {
      return $this.reifyType$default$4();
   }

   default boolean reifyType$default$4() {
      return false;
   }

   Trees.TreeApi reifyRuntimeClass(final Types.TypeApi tpe, final boolean concrete);

   // $FF: synthetic method
   static boolean reifyRuntimeClass$default$2$(final Reifiers $this) {
      return $this.reifyRuntimeClass$default$2();
   }

   default boolean reifyRuntimeClass$default$2() {
      return true;
   }

   Trees.TreeApi reifyEnclosingRuntimeClass();

   Trees.TreeApi unreifyTree(final Trees.TreeApi tree);
}
