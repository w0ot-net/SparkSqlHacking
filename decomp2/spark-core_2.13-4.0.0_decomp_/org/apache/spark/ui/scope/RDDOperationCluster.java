package org.apache.spark.ui.scope;

import java.lang.invoke.SerializedLambda;
import java.util.Objects;
import org.apache.spark.rdd.DeterministicLevel$;
import scala.Enumeration;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a!B\r\u001b\u0001y!\u0003\u0002C\u0016\u0001\u0005\u000b\u0007I\u0011A\u0017\t\u0011e\u0002!\u0011!Q\u0001\n9B\u0001B\u000f\u0001\u0003\u0006\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005y!A\u0001\t\u0001BA\u0002\u0013%Q\u0006\u0003\u0005B\u0001\t\u0005\r\u0011\"\u0003C\u0011!A\u0005A!A!B\u0013q\u0003\"B%\u0001\t\u0003Q\u0005b\u0002)\u0001\u0005\u0004%I!\u0015\u0005\u0007;\u0002\u0001\u000b\u0011\u0002*\t\u000fy\u0003!\u0019!C\u0005?\"1\u0011\r\u0001Q\u0001\n\u0001DQA\u0019\u0001\u0005\u00025BQa\u0019\u0001\u0005\u0002\u0011DQa\u001a\u0001\u0005\u0002!DQA\u001d\u0001\u0005\u0002MDQ!\u001e\u0001\u0005\u0002YDQ!\u001f\u0001\u0005\u0002iDQ! \u0001\u0005\u0002!DQA \u0001\u0005\u0002MDQa \u0001\u0005\u0002!Dq!!\u0001\u0001\t\u0003\t\u0019\u0001C\u0004\u0002\u0010\u0001!\t%!\u0005\t\u000f\u0005U\u0001\u0001\"\u0011\u0002\u0018\t\u0019\"\u000b\u0012#Pa\u0016\u0014\u0018\r^5p]\u000ecWo\u001d;fe*\u00111\u0004H\u0001\u0006g\u000e|\u0007/\u001a\u0006\u0003;y\t!!^5\u000b\u0005}\u0001\u0013!B:qCJ\\'BA\u0011#\u0003\u0019\t\u0007/Y2iK*\t1%A\u0002pe\u001e\u001c\"\u0001A\u0013\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\r\u0005s\u0017PU3g\u0003\tIGm\u0001\u0001\u0016\u00039\u0002\"a\f\u001c\u000f\u0005A\"\u0004CA\u0019(\u001b\u0005\u0011$BA\u001a-\u0003\u0019a$o\\8u}%\u0011QgJ\u0001\u0007!J,G-\u001a4\n\u0005]B$AB*ue&twM\u0003\u00026O\u0005\u0019\u0011\u000e\u001a\u0011\u0002\u000f\t\f'O]5feV\tA\b\u0005\u0002'{%\u0011ah\n\u0002\b\u0005>|G.Z1o\u0003!\u0011\u0017M\u001d:jKJ\u0004\u0013!B0oC6,\u0017!C0oC6,w\fJ3r)\t\u0019e\t\u0005\u0002'\t&\u0011Qi\n\u0002\u0005+:LG\u000fC\u0004H\r\u0005\u0005\t\u0019\u0001\u0018\u0002\u0007a$\u0013'\u0001\u0004`]\u0006lW\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\t-kej\u0014\t\u0003\u0019\u0002i\u0011A\u0007\u0005\u0006W!\u0001\rA\f\u0005\u0006u!\u0001\r\u0001\u0010\u0005\u0006\u0001\"\u0001\rAL\u0001\f?\u000eD\u0017\u000e\u001c3O_\u0012,7/F\u0001S!\r\u0019\u0006LW\u0007\u0002)*\u0011QKV\u0001\b[V$\u0018M\u00197f\u0015\t9v%\u0001\u0006d_2dWm\u0019;j_:L!!\u0017+\u0003\u00151K7\u000f\u001e\"vM\u001a,'\u000f\u0005\u0002M7&\u0011AL\u0007\u0002\u0011%\u0012#u\n]3sCRLwN\u001c(pI\u0016\fAbX2iS2$gj\u001c3fg\u0002\nabX2iS2$7\t\\;ti\u0016\u00148/F\u0001a!\r\u0019\u0006lS\u0001\u0010?\u000eD\u0017\u000e\u001c3DYV\u001cH/\u001a:tA\u0005!a.Y7f\u0003\u001d\u0019X\r\u001e(b[\u0016$\"aQ3\t\u000b\u0019t\u0001\u0019\u0001\u0018\u0002\u00039\f!b\u00195jY\u0012tu\u000eZ3t+\u0005I\u0007c\u00016p5:\u00111.\u001c\b\u0003c1L\u0011\u0001K\u0005\u0003]\u001e\nq\u0001]1dW\u0006<W-\u0003\u0002qc\n\u00191+Z9\u000b\u00059<\u0013!D2iS2$7\t\\;ti\u0016\u00148/F\u0001u!\rQwnS\u0001\u0010CR$\u0018m\u00195DQ&dGMT8eKR\u00111i\u001e\u0005\u0006qF\u0001\rAW\u0001\nG\"LG\u000e\u001a(pI\u0016\f!#\u0019;uC\u000eD7\t[5mI\u000ecWo\u001d;feR\u00111i\u001f\u0005\u0006yJ\u0001\raS\u0001\rG\"LG\u000eZ\"mkN$XM]\u0001\u000fO\u0016$8)Y2iK\u0012tu\u000eZ3t\u0003I9W\r\u001e\"beJLWM]\"mkN$XM]:\u0002+\u001d,G/\u00138eKR,'/\\5oCR,gj\u001c3fg\u0006A1-\u00198FcV\fG\u000eF\u0002=\u0003\u000bAq!a\u0002\u0017\u0001\u0004\tI!A\u0003pi\",'\u000fE\u0002'\u0003\u0017I1!!\u0004(\u0005\r\te._\u0001\u0007KF,\u0018\r\\:\u0015\u0007q\n\u0019\u0002C\u0004\u0002\b]\u0001\r!!\u0003\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0007\u0011\u0007\u0019\nY\"C\u0002\u0002\u001e\u001d\u00121!\u00138u\u0001"
)
public class RDDOperationCluster {
   private final String id;
   private final boolean barrier;
   private String _name;
   private final ListBuffer _childNodes;
   private final ListBuffer _childClusters;

   public String id() {
      return this.id;
   }

   public boolean barrier() {
      return this.barrier;
   }

   private String _name() {
      return this._name;
   }

   private void _name_$eq(final String x$1) {
      this._name = x$1;
   }

   private ListBuffer _childNodes() {
      return this._childNodes;
   }

   private ListBuffer _childClusters() {
      return this._childClusters;
   }

   public String name() {
      return this._name();
   }

   public void setName(final String n) {
      this._name_$eq(n);
   }

   public Seq childNodes() {
      return this._childNodes().iterator().toSeq();
   }

   public Seq childClusters() {
      return this._childClusters().iterator().toSeq();
   }

   public void attachChildNode(final RDDOperationNode childNode) {
      this._childNodes().$plus$eq(childNode);
   }

   public void attachChildCluster(final RDDOperationCluster childCluster) {
      this._childClusters().$plus$eq(childCluster);
   }

   public Seq getCachedNodes() {
      return ((IterableOnceOps)((IterableOps)this._childNodes().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getCachedNodes$1(x$1)))).$plus$plus((IterableOnce)this._childClusters().flatMap((x$2) -> x$2.getCachedNodes()))).toSeq();
   }

   public Seq getBarrierClusters() {
      return ((IterableOnceOps)((IterableOps)this._childClusters().filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getBarrierClusters$1(x$3)))).$plus$plus((IterableOnce)this._childClusters().flatMap((x$4) -> x$4.getBarrierClusters()))).toSeq();
   }

   public Seq getIndeterminateNodes() {
      return ((IterableOnceOps)((IterableOps)this._childNodes().filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$getIndeterminateNodes$1(x$5)))).$plus$plus((IterableOnce)this._childClusters().flatMap((x$6) -> x$6.getIndeterminateNodes()))).toSeq();
   }

   public boolean canEqual(final Object other) {
      return other instanceof RDDOperationCluster;
   }

   public boolean equals(final Object other) {
      if (!(other instanceof RDDOperationCluster var4)) {
         return false;
      } else {
         boolean var10;
         label53: {
            if (var4.canEqual(this)) {
               label47: {
                  ListBuffer var10000 = this._childClusters();
                  ListBuffer var5 = var4._childClusters();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label47;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label47;
                  }

                  String var8 = this.id();
                  String var6 = var4.id();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label47;
                     }
                  } else if (!var8.equals(var6)) {
                     break label47;
                  }

                  var8 = this._name();
                  String var7 = var4._name();
                  if (var8 == null) {
                     if (var7 == null) {
                        break label53;
                     }
                  } else if (var8.equals(var7)) {
                     break label53;
                  }
               }
            }

            var10 = false;
            return var10;
         }

         var10 = true;
         return var10;
      }
   }

   public int hashCode() {
      Seq state = new .colon.colon(this._childClusters(), new .colon.colon(this.id(), new .colon.colon(this._name(), scala.collection.immutable.Nil..MODULE$)));
      return BoxesRunTime.unboxToInt(((IterableOnceOps)state.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$hashCode$1(x$1)))).foldLeft(BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(a, b) -> 31 * a + b));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCachedNodes$1(final RDDOperationNode x$1) {
      return x$1.cached();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getBarrierClusters$1(final RDDOperationCluster x$3) {
      return x$3.barrier();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getIndeterminateNodes$1(final RDDOperationNode x$5) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$5.outputDeterministicLevel();
         Enumeration.Value var1 = DeterministicLevel$.MODULE$.INDETERMINATE();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final int $anonfun$hashCode$1(final Object x$1) {
      return Objects.hashCode(x$1);
   }

   public RDDOperationCluster(final String id, final boolean barrier, final String _name) {
      this.id = id;
      this.barrier = barrier;
      this._name = _name;
      super();
      this._childNodes = new ListBuffer();
      this._childClusters = new ListBuffer();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
