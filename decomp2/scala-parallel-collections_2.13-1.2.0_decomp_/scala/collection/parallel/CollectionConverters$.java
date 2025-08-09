package scala.collection.parallel;

import scala.collection.Iterable;
import scala.collection.Map;
import scala.collection.Parallelizable;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.concurrent.TrieMap;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.Range;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.HashSet;

public final class CollectionConverters$ {
   public static final CollectionConverters$ MODULE$ = new CollectionConverters$();

   public Iterable IterableIsParallelizable(final Iterable coll) {
      return coll;
   }

   public scala.collection.mutable.Iterable MutableIterableIsParallelizable(final scala.collection.mutable.Iterable coll) {
      return coll;
   }

   public scala.collection.immutable.Iterable ImmutableIterableIsParallelizable(final scala.collection.immutable.Iterable coll) {
      return coll;
   }

   public Parallelizable seqIsParallelizable(final Seq coll) {
      if (coll instanceof scala.collection.mutable.Seq) {
         scala.collection.mutable.Seq var4 = (scala.collection.mutable.Seq)coll;
         return new CollectionConverters.MutableSeqIsParallelizable(var4);
      } else if (coll instanceof scala.collection.immutable.Seq) {
         scala.collection.immutable.Seq var5 = (scala.collection.immutable.Seq)coll;
         return new CollectionConverters.ImmutableSeqIsParallelizable(var5);
      } else {
         throw new IllegalArgumentException((new StringBuilder(118)).append("Unexpected type ").append(coll.getClass().getName()).append(" - every scala.collection.Seq must be a scala.collection.mutable.Seq or scala.collection.immutable.Seq").toString());
      }
   }

   public scala.collection.mutable.Seq MutableSeqIsParallelizable(final scala.collection.mutable.Seq coll) {
      return coll;
   }

   public ArraySeq MutableArraySeqIsParallelizable(final ArraySeq coll) {
      return coll;
   }

   public ArrayBuffer MutableArrayBufferIsParallelizable(final ArrayBuffer coll) {
      return coll;
   }

   public scala.collection.immutable.Seq ImmutableSeqIsParallelizable(final scala.collection.immutable.Seq coll) {
      return coll;
   }

   public Range RangeIsParallelizable(final Range coll) {
      return coll;
   }

   public Vector VectorIsParallelizable(final Vector coll) {
      return coll;
   }

   public Set SetIsParallelizable(final Set coll) {
      return coll;
   }

   public scala.collection.immutable.Set ImmutableSetIsParallelizable(final scala.collection.immutable.Set coll) {
      return coll;
   }

   public scala.collection.mutable.Set MutableSetIsParallelizable(final scala.collection.mutable.Set coll) {
      return coll;
   }

   public HashSet MutableHashSetIsParallelizable(final HashSet coll) {
      return coll;
   }

   public scala.collection.immutable.HashSet ImmutableHashSetIsParallelizable(final scala.collection.immutable.HashSet coll) {
      return coll;
   }

   public Map MapIsParallelizable(final Map coll) {
      return coll;
   }

   public scala.collection.immutable.Map ImmutableMapIsParallelizable(final scala.collection.immutable.Map coll) {
      return coll;
   }

   public scala.collection.mutable.Map MutableMapIsParallelizable(final scala.collection.mutable.Map coll) {
      return coll;
   }

   public HashMap ImmutableHashMapIsParallelizable(final HashMap coll) {
      return coll;
   }

   public scala.collection.mutable.HashMap MutableHashMapIsParallelizable(final scala.collection.mutable.HashMap coll) {
      return coll;
   }

   public TrieMap ConcurrentTrieMapIsParallelizable(final TrieMap coll) {
      return coll;
   }

   public Object ArrayIsParallelizable(final Object a) {
      return a;
   }

   private CollectionConverters$() {
   }
}
