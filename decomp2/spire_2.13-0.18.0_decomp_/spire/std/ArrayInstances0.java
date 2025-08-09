package spire.std;

import algebra.ring.CommutativeRing;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.NotGiven;
import spire.algebra.CModule;

@ScalaSignature(
   bytes = "\u0006\u0005=4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!#\u0002\u0003\u0017\u0001\u00019\u0002\"\u0002\u0019\u0001\t\u0007\t$aD!se\u0006L\u0018J\\:uC:\u001cWm\u001d\u0019\u000b\u0005\u00199\u0011aA:uI*\t\u0001\"A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001Y\u0001C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002'A\u0011A\u0002F\u0005\u0003+5\u0011A!\u00168ji\n\u0019a*\u0013\u0019\u0016\u0005a9\u0003cA\r\u001b95\tq!\u0003\u0002\u001c\u000f\tAaj\u001c;HSZ,g\u000e\u0005\u0003\u001eA\t*S\"\u0001\u0010\u000b\u0005}9\u0011aB1mO\u0016\u0014'/Y\u0005\u0003Cy\u00111BV3di>\u00148\u000b]1dKB\u0019AbI\u0013\n\u0005\u0011j!!B!se\u0006L\bC\u0001\u0014(\u0019\u0001!Q\u0001\u000b\u0002C\u0002%\u0012\u0011!Q\t\u0003U5\u0002\"\u0001D\u0016\n\u00051j!a\u0002(pi\"Lgn\u001a\t\u0003\u00199J!aL\u0007\u0003\u0007\u0005s\u00170\u0001\u0007BeJ\f\u0017pQ'pIVdW-\u0006\u00023qQ!1G\u0016.d!\u0011iBGN\u001c\n\u0005Ur\"aB\"N_\u0012,H.\u001a\t\u0004\u0019\r:\u0004C\u0001\u00149\t%A3\u0001)A\u0001\u0002\u000b\u0007\u0011\u0006\u000b\u00049uu:E*\u0015\t\u0003\u0019mJ!\u0001P\u0007\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006Gyz\u0014\t\u0011\b\u0003\u0019}J!\u0001Q\u0007\u0002\u0007%sG/\r\u0003%\u0005\u001asaBA\"G\u001b\u0005!%BA#\n\u0003\u0019a$o\\8u}%\ta\"M\u0003$\u0011&[%J\u0004\u0002\r\u0013&\u0011!*D\u0001\u0005\u0019>tw-\r\u0003%\u0005\u001as\u0011'B\u0012N\u001dB{eB\u0001\u0007O\u0013\tyU\"A\u0003GY>\fG/\r\u0003%\u0005\u001as\u0011'B\u0012S'V#fB\u0001\u0007T\u0013\t!V\"\u0001\u0004E_V\u0014G.Z\u0019\u0005I\t3e\u0002C\u0004X\u0007\u0005\u0005\t9\u0001-\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\r\t\u00043\n9T\"\u0001\u0001\t\u000fm\u001b\u0011\u0011!a\u00029\u0006YQM^5eK:\u001cW\rJ\u00194!\ri\u0006m\u000e\b\u00033yK!aX\u0004\u0002\u000fA\f7m[1hK&\u0011\u0011M\u0019\u0002\t\u00072\f7o\u001d+bO*\u0011ql\u0002\u0005\bI\u000e\t\t\u0011q\u0001f\u0003-)g/\u001b3f]\u000e,G%\r\u001b\u0011\u0007\u0019dwG\u0004\u0002hW:\u0011\u0001N\u001b\b\u0003\u0007&L\u0011\u0001C\u0005\u0003?\u001dI!a\u0018\u0010\n\u00055t'!B\"SS:<'BA0\u001f\u0001"
)
public interface ArrayInstances0 {
   // $FF: synthetic method
   static CModule ArrayCModule$(final ArrayInstances0 $this, final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return $this.ArrayCModule(evidence$12, evidence$13, evidence$14);
   }

   default CModule ArrayCModule(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return new ArrayCModule(evidence$13, evidence$14, evidence$12);
   }

   // $FF: synthetic method
   static CModule ArrayCModule$mDc$sp$(final ArrayInstances0 $this, final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return $this.ArrayCModule$mDc$sp(evidence$12, evidence$13, evidence$14);
   }

   default CModule ArrayCModule$mDc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return new ArrayCModule$mcD$sp(evidence$13, evidence$14, evidence$12);
   }

   // $FF: synthetic method
   static CModule ArrayCModule$mFc$sp$(final ArrayInstances0 $this, final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return $this.ArrayCModule$mFc$sp(evidence$12, evidence$13, evidence$14);
   }

   default CModule ArrayCModule$mFc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return new ArrayCModule$mcF$sp(evidence$13, evidence$14, evidence$12);
   }

   // $FF: synthetic method
   static CModule ArrayCModule$mIc$sp$(final ArrayInstances0 $this, final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return $this.ArrayCModule$mIc$sp(evidence$12, evidence$13, evidence$14);
   }

   default CModule ArrayCModule$mIc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return new ArrayCModule$mcI$sp(evidence$13, evidence$14, evidence$12);
   }

   // $FF: synthetic method
   static CModule ArrayCModule$mJc$sp$(final ArrayInstances0 $this, final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return $this.ArrayCModule$mJc$sp(evidence$12, evidence$13, evidence$14);
   }

   default CModule ArrayCModule$mJc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return new ArrayCModule$mcJ$sp(evidence$13, evidence$14, evidence$12);
   }

   static void $init$(final ArrayInstances0 $this) {
   }
}
