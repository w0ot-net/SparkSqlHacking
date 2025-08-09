package scala.collection.immutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Tuple2;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ImmutableBuilder;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public final class OldHashMap$ implements MapFactory {
   public static final OldHashMap$ MODULE$ = new OldHashMap$();
   private static final long serialVersionUID = 3L;
   private static final OldHashMap.Merger defaultMerger;

   static {
      MapFactory.$init$(MODULE$);
      defaultMerger = MODULE$.liftMerger0((a, b) -> a);
   }

   public Object apply(final Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public OldHashMap empty() {
      return OldHashMap.EmptyOldHashMap$.MODULE$;
   }

   public OldHashMap from(final IterableOnce it) {
      if (it instanceof OldHashMap) {
         OldHashMap var4 = (OldHashMap)it;
         return var4;
      } else {
         return (OldHashMap)((Builder)this.newBuilder().$plus$plus$eq(it)).result();
      }
   }

   public Builder newBuilder() {
      return new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((OldHashMap)this.elems()).$plus(elem));
            return this;
         }
      };
   }

   public OldHashMap.Merger scala$collection$immutable$OldHashMap$$liftMerger(final Function2 mergef) {
      return mergef == null ? defaultMerger : this.liftMerger0(mergef);
   }

   private OldHashMap.Merger liftMerger0(final Function2 mergef) {
      return new OldHashMap.Merger(mergef) {
         private final OldHashMap.Merger invert;
         public final Function2 mergef$1;

         public Tuple2 apply(final Tuple2 kv1, final Tuple2 kv2) {
            return (Tuple2)this.mergef$1.apply(kv1, kv2);
         }

         public OldHashMap.Merger invert() {
            return this.invert;
         }

         public {
            this.mergef$1 = mergef$1;
            this.invert = new OldHashMap.Merger() {
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public Tuple2 apply(final Tuple2 kv1, final Tuple2 kv2) {
                  return (Tuple2)this.$outer.mergef$1.apply(kv2, kv1);
               }

               public OldHashMap.Merger invert() {
                  return this.$outer;
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                  }
               }
            };
         }
      };
   }

   public OldHashMap.HashTrieMap scala$collection$immutable$OldHashMap$$makeHashTrieMap(final int hash0, final OldHashMap elem0, final int hash1, final OldHashMap elem1, final int level, final int size) {
      int index0 = hash0 >>> level & 31;
      int index1 = hash1 >>> level & 31;
      if (index0 != index1) {
         int bitmap = 1 << index0 | 1 << index1;
         OldHashMap[] elems = new OldHashMap[2];
         if (index0 < index1) {
            elems[0] = elem0;
            elems[1] = elem1;
         } else {
            elems[0] = elem1;
            elems[1] = elem0;
         }

         return new OldHashMap.HashTrieMap(bitmap, elems, size);
      } else {
         OldHashMap[] elems = new OldHashMap[1];
         int bitmap = 1 << index0;
         elems[0] = this.scala$collection$immutable$OldHashMap$$makeHashTrieMap(hash0, elem0, hash1, elem1, level + 5, size);
         return new OldHashMap.HashTrieMap(bitmap, elems, size);
      }
   }

   public int scala$collection$immutable$OldHashMap$$bufferSize(final int size) {
      return .MODULE$.min(size + 6, 224);
   }

   public OldHashMap scala$collection$immutable$OldHashMap$$nullToEmpty(final OldHashMap m) {
      return m == null ? this.empty() : m;
   }

   private void writeObject(final ObjectOutputStream out) {
   }

   private void readObject(final ObjectInputStream in) {
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OldHashMap$.class);
   }

   private OldHashMap$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
