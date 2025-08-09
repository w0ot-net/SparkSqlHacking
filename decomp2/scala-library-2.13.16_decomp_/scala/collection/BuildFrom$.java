package scala.collection;

import scala.collection.immutable.WrappedString;
import scala.collection.immutable.WrappedString$;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.reflect.ClassTag;

public final class BuildFrom$ implements BuildFromLowPriority1 {
   public static final BuildFrom$ MODULE$ = new BuildFrom$();
   private static final BuildFrom buildFromString;
   private static final BuildFrom buildFromWrappedString;

   static {
      BuildFrom$ var10000 = MODULE$;
      var10000 = MODULE$;
      buildFromString = new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public String fromSpecific(final String from, final IterableOnce it) {
            return (String)Factory$.MODULE$.stringFactory().fromSpecific(it);
         }

         public Builder newBuilder(final String from) {
            return Factory$.MODULE$.stringFactory().newBuilder();
         }
      };
      buildFromWrappedString = new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public WrappedString fromSpecific(final WrappedString from, final IterableOnce it) {
            return WrappedString$.MODULE$.fromSpecific(it);
         }

         public Builder newBuilder(final WrappedString from) {
            return WrappedString$.MODULE$.newBuilder();
         }
      };
   }

   public BuildFrom buildFromSortedSetOps(final Ordering evidence$3) {
      return BuildFromLowPriority1.buildFromSortedSetOps$(this, evidence$3);
   }

   public BuildFrom fallbackStringCanBuildFrom() {
      return BuildFromLowPriority1.fallbackStringCanBuildFrom$(this);
   }

   public BuildFrom buildFromIterableOps() {
      return BuildFromLowPriority2.buildFromIterableOps$(this);
   }

   public BuildFrom buildFromIterator() {
      return BuildFromLowPriority2.buildFromIterator$(this);
   }

   public BuildFrom buildFromMapOps() {
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Builder newBuilder(final Map from) {
            return from.mapFactory().newBuilder();
         }

         public Map fromSpecific(final Map from, final IterableOnce it) {
            return (Map)from.mapFactory().from(it);
         }
      };
   }

   public BuildFrom buildFromSortedMapOps(final Ordering evidence$1) {
      return new BuildFrom(evidence$1) {
         private final Ordering evidence$1$1;

         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Builder newBuilder(final SortedMap from) {
            return from.sortedMapFactory().newBuilder(this.evidence$1$1);
         }

         public SortedMap fromSpecific(final SortedMap from, final IterableOnce it) {
            return (SortedMap)from.sortedMapFactory().from(it, this.evidence$1$1);
         }

         public {
            this.evidence$1$1 = evidence$1$1;
         }
      };
   }

   public BuildFrom buildFromBitSet() {
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public BitSet fromSpecific(final BitSet from, final IterableOnce it) {
            return (BitSet)from.bitSetFactory().fromSpecific(it);
         }

         public Builder newBuilder(final BitSet from) {
            return from.bitSetFactory().newBuilder();
         }
      };
   }

   public BuildFrom buildFromString() {
      return buildFromString;
   }

   public BuildFrom buildFromWrappedString() {
      return buildFromWrappedString;
   }

   public BuildFrom buildFromArray(final ClassTag evidence$2) {
      return new BuildFrom(evidence$2) {
         private final ClassTag evidence$2$1;

         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Object fromSpecific(final Object from, final IterableOnce it) {
            Factory$ var10000 = Factory$.MODULE$;
            ClassTag arrayFactory_evidence$1 = this.evidence$2$1;
            Factory.ArrayFactory var5 = new Factory.ArrayFactory(arrayFactory_evidence$1);
            arrayFactory_evidence$1 = null;
            return var5.fromSpecific(it);
         }

         public Builder newBuilder(final Object from) {
            Factory$ var10000 = Factory$.MODULE$;
            ClassTag arrayFactory_evidence$1 = this.evidence$2$1;
            Factory.ArrayFactory var4 = new Factory.ArrayFactory(arrayFactory_evidence$1);
            arrayFactory_evidence$1 = null;
            return var4.newBuilder();
         }

         public {
            this.evidence$2$1 = evidence$2$1;
         }
      };
   }

   public BuildFrom buildFromView() {
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public View fromSpecific(final View from, final IterableOnce it) {
            return View$.MODULE$.from(it);
         }

         public Builder newBuilder(final View from) {
            return View$.MODULE$.newBuilder();
         }
      };
   }

   private BuildFrom$() {
   }
}
