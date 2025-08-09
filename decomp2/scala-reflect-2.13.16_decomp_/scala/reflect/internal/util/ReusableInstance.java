package scala.reflect.internal.util;

import scala.Function0;
import scala.Function1;
import scala.collection.SeqOps;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.util.ChainingOps;
import scala.util.ChainingOps.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015a\u0001\u0002\t\u0012\u0005iAA\u0002\t\u0001\u0005\u0002\u0003\u0015)\u0011!S\u0001\n\u0005B\u0001\u0002\f\u0001\u0003\u0002\u0003\u0006I!\f\u0005\u0006a\u0001!I!\r\u0005\ro\u0001!\t\u0011!B\u0001\u0002\u0003\u0006I\u0001\u000f\u0005\r\u0001\u0002!\t\u0011!B\u0001\u0002\u0003\u0006K!\f\u0005\u0006\u0003\u0002!\tAQ\u0004\u0006)FA\t!\u0016\u0004\u0006!EA\tA\u0016\u0005\u0006a!!\ta\u0016\u0005\b1\"\u0011\r\u0011\"\u0004Z\u0011\u0019a\u0006\u0002)A\u00075\")Q\f\u0003C\u0001=\")Q\f\u0003C\u0001M\")Q\f\u0003C\u0001[\")Q\f\u0003C\u0001s\n\u0001\"+Z;tC\ndW-\u00138ti\u0006t7-\u001a\u0006\u0003%M\tA!\u001e;jY*\u0011A#F\u0001\tS:$XM\u001d8bY*\u0011acF\u0001\be\u00164G.Z2u\u0015\u0005A\u0012!B:dC2\f7\u0001A\u000b\u00037\u0019\u001a\"\u0001\u0001\u000f\u0011\u0005uqR\"A\f\n\u0005}9\"AB!osJ+g-\u0001\u001atG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mIU$\u0018\u000e\u001c\u0013SKV\u001c\u0018M\u00197f\u0013:\u001cH/\u00198dK\u0012\"S.Y6f!\ri\"\u0005J\u0005\u0003G]\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\t\u0003K\u0019b\u0001\u0001B\u0003(\u0001\t\u0007\u0001FA\u0001U#\tIC\u0004\u0005\u0002\u001eU%\u00111f\u0006\u0002\b\u001d>$\b.\u001b8h\u0003-Ig.\u001b;jC2\u001c\u0016N_3\u0011\u0005uq\u0013BA\u0018\u0018\u0005\rIe\u000e^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007I\"d\u0007E\u00024\u0001\u0011j\u0011!\u0005\u0005\u0007k\r!\t\u0019A\u0011\u0002\t5\f7.\u001a\u0005\u0006Y\r\u0001\r!L\u00014g\u000e\fG.\u0019\u0013sK\u001adWm\u0019;%S:$XM\u001d8bY\u0012*H/\u001b7%%\u0016,8/\u00192mK&s7\u000f^1oG\u0016$CeY1dQ\u0016\u00042!\u000f %\u001b\u0005Q$BA\u001e=\u0003\u001diW\u000f^1cY\u0016T!!P\f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002@u\tY\u0011I\u001d:bs\n+hMZ3s\u0003M\u001a8-\u00197bII,g\r\\3di\u0012Jg\u000e^3s]\u0006dG%\u001e;jY\u0012\u0012V-^:bE2,\u0017J\\:uC:\u001cW\r\n\u0013uC.,g.A\u0003vg&tw-\u0006\u0002D\u000bR\u0011Ai\u0013\t\u0003K\u0015#QA\u0012\u0004C\u0002\u001d\u0013\u0011AU\t\u0003S!\u0003\"!H%\n\u0005);\"aA!os\")AJ\u0002a\u0001\u001b\u00061\u0011m\u0019;j_:\u0004B!\b(%\t&\u0011qj\u0006\u0002\n\rVt7\r^5p]FB#AB)\u0011\u0005u\u0011\u0016BA*\u0018\u0005\u0019Ig\u000e\\5oK\u0006\u0001\"+Z;tC\ndW-\u00138ti\u0006t7-\u001a\t\u0003g!\u0019\"\u0001\u0003\u000f\u0015\u0003U\u000b1\"\u00138ji&\fGnU5{KV\t!lD\u0001\\;\u0005!\u0011\u0001D%oSRL\u0017\r\\*ju\u0016\u0004\u0013!B1qa2LXCA0c)\r\u00017-\u001a\t\u0004g\u0001\t\u0007CA\u0013c\t\u00159CB1\u0001)\u0011\u0019)D\u0002\"a\u0001IB\u0019QDI1\t\u000b1b\u0001\u0019A\u0017\u0016\u0005\u001dTGC\u00015l!\r\u0019\u0004!\u001b\t\u0003K)$QaJ\u0007C\u0002!Ba!N\u0007\u0005\u0002\u0004a\u0007cA\u000f#SV\u0011a.\u001d\u000b\u0004_J$\bcA\u001a\u0001aB\u0011Q%\u001d\u0003\u0006O9\u0011\r\u0001\u000b\u0005\u0007k9!\t\u0019A:\u0011\u0007u\u0011\u0003\u000fC\u0003v\u001d\u0001\u0007a/A\u0004f]\u0006\u0014G.\u001a3\u0011\u0005u9\u0018B\u0001=\u0018\u0005\u001d\u0011un\u001c7fC:,\"A_?\u0015\rmt\u0018\u0011AA\u0002!\r\u0019\u0004\u0001 \t\u0003Ku$QaJ\bC\u0002!Ba!N\b\u0005\u0002\u0004y\bcA\u000f#y\")Af\u0004a\u0001[!)Qo\u0004a\u0001m\u0002"
)
public final class ReusableInstance {
   public final Function0 scala$reflect$internal$util$ReusableInstance$$make;
   public final ArrayBuffer scala$reflect$internal$util$ReusableInstance$$cache;
   public int scala$reflect$internal$util$ReusableInstance$$taken;

   public static ReusableInstance apply(final Function0 make, final int initialSize, final boolean enabled) {
      ReusableInstance$ var10000 = ReusableInstance$.MODULE$;
      if (enabled) {
         return new ReusableInstance(make, initialSize);
      } else {
         int apply_apply_initialSize = -1;
         return new ReusableInstance(make, apply_apply_initialSize);
      }
   }

   public static ReusableInstance apply(final Function0 make, final boolean enabled) {
      ReusableInstance$ var10000 = ReusableInstance$.MODULE$;
      if (enabled) {
         int apply_apply_apply_initialSize = 4;
         return new ReusableInstance(make, apply_apply_apply_initialSize);
      } else {
         int apply_apply_initialSize = -1;
         return new ReusableInstance(make, apply_apply_initialSize);
      }
   }

   public static ReusableInstance apply(final Function0 make) {
      ReusableInstance$ var10000 = ReusableInstance$.MODULE$;
      int apply_apply_initialSize = 4;
      return new ReusableInstance(make, apply_apply_initialSize);
   }

   public static ReusableInstance apply(final Function0 make, final int initialSize) {
      ReusableInstance$ var10000 = ReusableInstance$.MODULE$;
      return new ReusableInstance(make, initialSize);
   }

   public Object using(final Function1 action) {
      if (this.scala$reflect$internal$util$ReusableInstance$$cache == null) {
         return action.apply(this.scala$reflect$internal$util$ReusableInstance$$make.apply());
      } else {
         int var10000 = this.scala$reflect$internal$util$ReusableInstance$$taken;
         ArrayBuffer var10001 = this.scala$reflect$internal$util$ReusableInstance$$cache;
         if (var10001 == null) {
            throw null;
         } else {
            if (var10000 == SeqOps.size$(var10001)) {
               ArrayBuffer var7 = this.scala$reflect$internal$util$ReusableInstance$$cache;
               Object $plus$eq_elem = this.scala$reflect$internal$util$ReusableInstance$$make.apply();
               if (var7 == null) {
                  throw null;
               }

               var7.addOne($plus$eq_elem);
               $plus$eq_elem = null;
            }

            ++this.scala$reflect$internal$util$ReusableInstance$$taken;

            try {
               var8 = action.apply(this.scala$reflect$internal$util$ReusableInstance$$cache.apply(this.scala$reflect$internal$util$ReusableInstance$$taken - 1));
            } finally {
               --this.scala$reflect$internal$util$ReusableInstance$$taken;
            }

            return var8;
         }
      }
   }

   // $FF: synthetic method
   public static final ArrayBuffer $anonfun$scala$reflect$internal$util$ReusableInstance$$cache$1(final ReusableInstance $this, final ArrayBuffer x$1) {
      return x$1.addOne($this.scala$reflect$internal$util$ReusableInstance$$make.apply());
   }

   public ReusableInstance(final Function0 make, final int initialSize) {
      this.scala$reflect$internal$util$ReusableInstance$$make = make;
      Object var9;
      if (initialSize > 0) {
         ChainingOps var10001 = .MODULE$;
         scala.util.package.chaining var7 = scala.util.package.chaining..MODULE$;
         Object scalaUtilChainingOps_a = new ArrayBuffer(initialSize);
         Object var8 = scalaUtilChainingOps_a;
         scalaUtilChainingOps_a = null;
         Object tap$extension_$this = var8;
         $anonfun$scala$reflect$internal$util$ReusableInstance$$cache$1(this, (ArrayBuffer)tap$extension_$this);
         var9 = tap$extension_$this;
         tap$extension_$this = null;
      } else {
         var9 = null;
      }

      this.scala$reflect$internal$util$ReusableInstance$$cache = (ArrayBuffer)var9;
      this.scala$reflect$internal$util$ReusableInstance$$taken = 0;
   }
}
