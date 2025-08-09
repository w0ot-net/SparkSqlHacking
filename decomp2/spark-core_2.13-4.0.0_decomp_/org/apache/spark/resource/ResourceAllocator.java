package org.apache.spark.resource;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0001BC\u0006\u0011\u0002\u0007\u0005Qb\u0005\u0005\u00065\u0001!\t\u0001\b\u0005\u0006A\u00011\t\"\t\u0005\u0006[\u00011\tB\f\u0005\tq\u0001A)\u0019!C\u0005s!)Q\t\u0001C\u0001\r\")!\n\u0001C\u0001]!11\n\u0001C\u0001\u001b9BQ\u0001\u0014\u0001\u0005\u00025CQ\u0001\u0015\u0001\u0005\u0002E\u0013\u0011CU3t_V\u00148-Z!mY>\u001c\u0017\r^8s\u0015\taQ\"\u0001\u0005sKN|WO]2f\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7C\u0001\u0001\u0015!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u001e!\t)b$\u0003\u0002 -\t!QK\\5u\u00031\u0011Xm]8ve\u000e,g*Y7f+\u0005\u0011\u0003CA\u0012+\u001d\t!\u0003\u0006\u0005\u0002&-5\taE\u0003\u0002(7\u00051AH]8pizJ!!\u000b\f\u0002\rA\u0013X\rZ3g\u0013\tYCF\u0001\u0004TiJLgn\u001a\u0006\u0003SY\t\u0011C]3t_V\u00148-Z!eIJ,7o]3t+\u0005y\u0003c\u0001\u00196E9\u0011\u0011g\r\b\u0003KIJ\u0011aF\u0005\u0003iY\tq\u0001]1dW\u0006<W-\u0003\u00027o\t\u00191+Z9\u000b\u0005Q2\u0012AF1eIJ,7o]!wC&d\u0017MY5mSRLX*\u00199\u0016\u0003i\u0002Ba\u000f!#\u00056\tAH\u0003\u0002>}\u00059Q.\u001e;bE2,'BA \u0017\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003\u0003r\u0012q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0002\u0016\u0007&\u0011AI\u0006\u0002\u0005\u0019>tw-\u0001\tsKN|WO]2fg\u0006kw.\u001e8ugV\tq\t\u0005\u0003$\u0011\n\u0012\u0015BA%-\u0005\ri\u0015\r]\u0001\u000fCZ\f\u0017\u000e\\1cY\u0016\fE\r\u001a:t\u00035\t7o]5h]\u0016$\u0017\t\u001a3sg\u00069\u0011mY9vSJ,GCA\u000fO\u0011\u0015y\u0005\u00021\u0001H\u0003A\tG\r\u001a:fgN,7/Q7pk:$8/A\u0004sK2,\u0017m]3\u0015\u0005u\u0011\u0006\"B(\n\u0001\u00049\u0005"
)
public interface ResourceAllocator {
   String resourceName();

   Seq resourceAddresses();

   // $FF: synthetic method
   static HashMap org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap$(final ResourceAllocator $this) {
      return $this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap();
   }

   default HashMap org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap() {
      return (HashMap).MODULE$.apply((Seq)this.resourceAddresses().map((address) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(address), BoxesRunTime.boxToLong(ResourceAmountUtils$.MODULE$.ONE_ENTIRE_RESOURCE()))));
   }

   // $FF: synthetic method
   static Map resourcesAmounts$(final ResourceAllocator $this) {
      return $this.resourcesAmounts();
   }

   default Map resourcesAmounts() {
      return this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap().toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   static Seq availableAddrs$(final ResourceAllocator $this) {
      return $this.availableAddrs();
   }

   default Seq availableAddrs() {
      return (Seq)((MapOps)this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap().filter((addresses) -> BoxesRunTime.boxToBoolean($anonfun$availableAddrs$1(addresses)))).keys().toSeq().sorted(scala.math.Ordering.String..MODULE$);
   }

   // $FF: synthetic method
   static Seq assignedAddrs$(final ResourceAllocator $this) {
      return $this.assignedAddrs();
   }

   default Seq assignedAddrs() {
      return (Seq)((MapOps)this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap().filter((addresses) -> BoxesRunTime.boxToBoolean($anonfun$assignedAddrs$1(addresses)))).keys().toSeq().sorted(scala.math.Ordering.String..MODULE$);
   }

   // $FF: synthetic method
   static void acquire$(final ResourceAllocator $this, final Map addressesAmounts) {
      $this.acquire(addressesAmounts);
   }

   default void acquire(final Map addressesAmounts) {
      addressesAmounts.foreach((x0$1) -> {
         $anonfun$acquire$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static void release$(final ResourceAllocator $this, final Map addressesAmounts) {
      $this.release(addressesAmounts);
   }

   default void release(final Map addressesAmounts) {
      addressesAmounts.foreach((x0$1) -> {
         $anonfun$release$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static boolean $anonfun$availableAddrs$1(final Tuple2 addresses) {
      return addresses._2$mcJ$sp() > 0L;
   }

   // $FF: synthetic method
   static boolean $anonfun$assignedAddrs$1(final Tuple2 addresses) {
      return addresses._2$mcJ$sp() < ResourceAmountUtils$.MODULE$.ONE_ENTIRE_RESOURCE();
   }

   // $FF: synthetic method
   static void $anonfun$acquire$1(final ResourceAllocator $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String address = (String)x0$1._1();
         long amount = x0$1._2$mcJ$sp();
         long prevAmount = BoxesRunTime.unboxToLong($this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap().getOrElse(address, () -> {
            String var10002 = $this.resourceName();
            throw new SparkException("Try to acquire an address that doesn't exist. " + var10002 + " address " + address + " doesn't exist.");
         }));
         long left = prevAmount - amount;
         if (left < 0L) {
            String var10002 = $this.resourceName();
            throw new SparkException("Try to acquire " + var10002 + " address " + address + " amount: " + ResourceAmountUtils$.MODULE$.toFractionalResource(amount) + ", but only " + ResourceAmountUtils$.MODULE$.toFractionalResource(prevAmount) + " left.");
         } else {
            $this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap().update(address, BoxesRunTime.boxToLong(left));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   static void $anonfun$release$1(final ResourceAllocator $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String address = (String)x0$1._1();
         long amount = x0$1._2$mcJ$sp();
         long prevAmount = BoxesRunTime.unboxToLong($this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap().getOrElse(address, () -> {
            String var10002 = $this.resourceName();
            throw new SparkException("Try to release an address that doesn't exist. " + var10002 + " address " + address + " doesn't exist.");
         }));
         long total = prevAmount + amount;
         if (total > ResourceAmountUtils$.MODULE$.ONE_ENTIRE_RESOURCE()) {
            String var10002 = $this.resourceName();
            throw new SparkException("Try to release " + var10002 + " address " + address + " amount: " + ResourceAmountUtils$.MODULE$.toFractionalResource(amount) + ". But the total amount: " + ResourceAmountUtils$.MODULE$.toFractionalResource(total) + " after release should be <= 1");
         } else {
            $this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap().update(address, BoxesRunTime.boxToLong(total));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   static void $init$(final ResourceAllocator $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
