package org.apache.spark.util.collection;

import java.lang.invoke.SerializedLambda;
import java.util.Collections;
import java.util.HashMap;
import org.apache.spark.util.SparkCollectionUtils;
import org.sparkproject.guava.collect.Iterators;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.jdk.CollectionConverters.;
import scala.math.Ordering;
import scala.runtime.BoxesRunTime;

public final class Utils$ implements SparkCollectionUtils {
   public static final Utils$ MODULE$ = new Utils$();

   static {
      SparkCollectionUtils.$init$(MODULE$);
   }

   public Map toMapWithIndex(final Iterable keys) {
      return SparkCollectionUtils.toMapWithIndex$(this, keys);
   }

   public Iterator takeOrdered(final Iterator input, final int num, final Ordering ord) {
      org.sparkproject.guava.collect.Ordering ordering = new org.sparkproject.guava.collect.Ordering(ord) {
         private final Ordering ord$1;

         public int compare(final Object l, final Object r) {
            return this.ord$1.compare(l, r);
         }

         public {
            this.ord$1 = ord$1;
         }
      };
      return .MODULE$.IteratorHasAsScala(ordering.leastOf(.MODULE$.IteratorHasAsJava(input).asJava(), num).iterator()).asScala();
   }

   public Iterator mergeOrdered(final Iterable inputs, final Ordering ord) {
      org.sparkproject.guava.collect.Ordering ordering = new org.sparkproject.guava.collect.Ordering(ord) {
         private final Ordering ord$2;

         public int compare(final Object l, final Object r) {
            return this.ord$2.compare(l, r);
         }

         public {
            this.ord$2 = ord$2;
         }
      };
      return .MODULE$.IteratorHasAsScala(Iterators.mergeSorted(.MODULE$.IterableHasAsJava((Iterable)inputs.map((x$1) -> .MODULE$.IteratorHasAsJava(x$1.iterator()).asJava())).asJava(), ordering)).asScala();
   }

   public Option sequenceToOption(final Seq input) {
      return (Option)(input.forall((x$2) -> BoxesRunTime.boxToBoolean($anonfun$sequenceToOption$1(x$2))) ? new Some(input.flatten(scala.Predef..MODULE$.$conforms())) : scala.None..MODULE$);
   }

   public Map toMap(final Iterable keys, final Iterable values) {
      Builder builder = scala.collection.immutable.Map..MODULE$.newBuilder();
      Iterator keyIter = keys.iterator();
      Iterator valueIter = values.iterator();

      while(keyIter.hasNext() && valueIter.hasNext()) {
         builder.$plus$eq(new Tuple2(keyIter.next(), valueIter.next()));
      }

      return (Map)builder.result();
   }

   public java.util.Map toJavaMap(final Iterable keys, final Iterable values) {
      HashMap map = new HashMap();
      Iterator keyIter = keys.iterator();
      Iterator valueIter = values.iterator();

      while(keyIter.hasNext() && valueIter.hasNext()) {
         map.put(keyIter.next(), valueIter.next());
      }

      return Collections.unmodifiableMap(map);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$sequenceToOption$1(final Option x$2) {
      return x$2.isDefined();
   }

   private Utils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
