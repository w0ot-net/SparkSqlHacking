package scala.collection;

import scala.$less$colon$less;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Stream$;
import scala.collection.immutable.Vector;
import scala.collection.immutable.Vector$;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.Buffer;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;

public final class IterableOnceExtensionMethods$ {
   public static final IterableOnceExtensionMethods$ MODULE$ = new IterableOnceExtensionMethods$();

   /** @deprecated */
   public final Iterator withFilter$extension(final IterableOnce $this, final Function1 f) {
      return $this.iterator().withFilter(f);
   }

   /** @deprecated */
   public final Option reduceLeftOption$extension(final IterableOnce $this, final Function2 f) {
      return $this.iterator().reduceLeftOption(f);
   }

   /** @deprecated */
   public final Object min$extension(final IterableOnce $this, final Ordering ord) {
      return $this.iterator().min(ord);
   }

   /** @deprecated */
   public final boolean nonEmpty$extension(final IterableOnce $this) {
      return $this.iterator().nonEmpty();
   }

   /** @deprecated */
   public final Object max$extension(final IterableOnce $this, final Ordering ord) {
      return $this.iterator().max(ord);
   }

   /** @deprecated */
   public final Object reduceRight$extension(final IterableOnce $this, final Function2 f) {
      return $this.iterator().reduceRight(f);
   }

   /** @deprecated */
   public final Object maxBy$extension(final IterableOnce $this, final Function1 f, final Ordering cmp) {
      return $this.iterator().maxBy(f, cmp);
   }

   /** @deprecated */
   public final Object reduceLeft$extension(final IterableOnce $this, final Function2 f) {
      return $this.iterator().reduceLeft(f);
   }

   /** @deprecated */
   public final Object sum$extension(final IterableOnce $this, final Numeric num) {
      return $this.iterator().sum(num);
   }

   /** @deprecated */
   public final Object product$extension(final IterableOnce $this, final Numeric num) {
      return $this.iterator().product(num);
   }

   /** @deprecated */
   public final int count$extension(final IterableOnce $this, final Function1 f) {
      return $this.iterator().count(f);
   }

   /** @deprecated */
   public final Option reduceOption$extension(final IterableOnce $this, final Function2 f) {
      return $this.iterator().reduceOption(f);
   }

   /** @deprecated */
   public final Object minBy$extension(final IterableOnce $this, final Function1 f, final Ordering cmp) {
      return $this.iterator().minBy(f, cmp);
   }

   /** @deprecated */
   public final int size$extension(final IterableOnce $this) {
      return $this.iterator().size();
   }

   /** @deprecated */
   public final boolean forall$extension(final IterableOnce $this, final Function1 f) {
      return $this.iterator().forall(f);
   }

   /** @deprecated */
   public final Option collectFirst$extension(final IterableOnce $this, final PartialFunction f) {
      return $this.iterator().collectFirst(f);
   }

   /** @deprecated */
   public final Iterator filter$extension(final IterableOnce $this, final Function1 f) {
      return $this.iterator().filter(f);
   }

   /** @deprecated */
   public final boolean exists$extension(final IterableOnce $this, final Function1 f) {
      return $this.iterator().exists(f);
   }

   /** @deprecated */
   public final void copyToBuffer$extension(final IterableOnce $this, final Buffer dest) {
      Iterator var10000 = $this.iterator();
      if (var10000 == null) {
         throw null;
      } else {
         IterableOnceOps copyToBuffer_this = var10000;
         if (dest == null) {
            throw null;
         } else {
            dest.addAll(copyToBuffer_this);
         }
      }
   }

   /** @deprecated */
   public final Object reduce$extension(final IterableOnce $this, final Function2 f) {
      return $this.iterator().reduce(f);
   }

   /** @deprecated */
   public final Option reduceRightOption$extension(final IterableOnce $this, final Function2 f) {
      return $this.iterator().reduceRightOption(f);
   }

   /** @deprecated */
   public final IndexedSeq toIndexedSeq$extension(final IterableOnce $this) {
      return $this.iterator().toIndexedSeq();
   }

   /** @deprecated */
   public final void foreach$extension(final IterableOnce $this, final Function1 f) {
      if ($this instanceof Iterable) {
         ((Iterable)$this).foreach(f);
      } else {
         $this.iterator().foreach(f);
      }
   }

   /** @deprecated */
   public final Object to$extension(final IterableOnce $this, final Factory factory) {
      return factory.fromSpecific($this);
   }

   /** @deprecated */
   public final Buffer toBuffer$extension(final IterableOnce $this) {
      return ArrayBuffer$.MODULE$.from($this);
   }

   /** @deprecated */
   public final Object toArray$extension(final IterableOnce $this, final ClassTag evidence$1) {
      return $this instanceof Iterable ? ((Iterable)$this).toArray(evidence$1) : $this.iterator().toArray(evidence$1);
   }

   /** @deprecated */
   public final List toList$extension(final IterableOnce $this) {
      List$ var10000 = List$.MODULE$;
      return Nil$.MODULE$.prependedAll($this);
   }

   /** @deprecated */
   public final scala.collection.immutable.Set toSet$extension(final IterableOnce $this) {
      return scala.collection.immutable.Set$.MODULE$.from($this);
   }

   /** @deprecated */
   public final Iterable toTraversable$extension(final IterableOnce $this) {
      return (Iterable)Iterable$.MODULE$.from($this);
   }

   /** @deprecated */
   public final Iterable toIterable$extension(final IterableOnce $this) {
      return (Iterable)Iterable$.MODULE$.from($this);
   }

   /** @deprecated */
   public final scala.collection.immutable.Seq toSeq$extension(final IterableOnce $this) {
      return scala.collection.immutable.Seq$.MODULE$.from($this);
   }

   /** @deprecated */
   public final Stream toStream$extension(final IterableOnce $this) {
      return Stream$.MODULE$.from($this);
   }

   /** @deprecated */
   public final Vector toVector$extension(final IterableOnce $this) {
      return Vector$.MODULE$.from($this);
   }

   /** @deprecated */
   public final scala.collection.immutable.Map toMap$extension(final IterableOnce $this, final $less$colon$less ev) {
      return scala.collection.immutable.Map$.MODULE$.from($this);
   }

   /** @deprecated */
   public final Iterator toIterator$extension(final IterableOnce $this) {
      return $this.iterator();
   }

   /** @deprecated */
   public final boolean isEmpty$extension(final IterableOnce $this) {
      return $this instanceof Iterable ? ((Iterable)$this).isEmpty() : $this.iterator().isEmpty();
   }

   /** @deprecated */
   public final String mkString$extension(final IterableOnce $this, final String start, final String sep, final String end) {
      return $this instanceof Iterable ? ((Iterable)$this).mkString(start, sep, end) : $this.iterator().mkString(start, sep, end);
   }

   /** @deprecated */
   public final String mkString$extension(final IterableOnce $this, final String sep) {
      if ($this instanceof Iterable) {
         Iterable var3 = (Iterable)$this;
         if (var3 == null) {
            throw null;
         } else {
            return var3.mkString("", sep, "");
         }
      } else {
         Iterator var10000 = $this.iterator();
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.mkString("", sep, "");
         }
      }
   }

   /** @deprecated */
   public final String mkString$extension(final IterableOnce $this) {
      if ($this instanceof Iterable) {
         Iterable var2 = (Iterable)$this;
         if (var2 == null) {
            throw null;
         } else {
            String mkString_mkString_sep = "";
            return var2.mkString("", mkString_mkString_sep, "");
         }
      } else {
         Iterator var10000 = $this.iterator();
         if (var10000 == null) {
            throw null;
         } else {
            IterableOnceOps mkString_this = var10000;
            String mkString_mkString_sep = "";
            return mkString_this.mkString("", mkString_mkString_sep, "");
         }
      }
   }

   /** @deprecated */
   public final Option find$extension(final IterableOnce $this, final Function1 p) {
      return $this.iterator().find(p);
   }

   /** @deprecated */
   public final Object foldLeft$extension(final IterableOnce $this, final Object z, final Function2 op) {
      return $this.iterator().foldLeft(z, op);
   }

   /** @deprecated */
   public final Object foldRight$extension(final IterableOnce $this, final Object z, final Function2 op) {
      return $this.iterator().foldRight(z, op);
   }

   /** @deprecated */
   public final Object fold$extension(final IterableOnce $this, final Object z, final Function2 op) {
      return $this.iterator().fold(z, op);
   }

   /** @deprecated */
   public final Object $div$colon$extension(final IterableOnce $this, final Object z, final Function2 op) {
      return $this.iterator().foldLeft(z, op);
   }

   /** @deprecated */
   public final Object $colon$bslash$extension(final IterableOnce $this, final Object z, final Function2 op) {
      return $this.iterator().foldRight(z, op);
   }

   /** @deprecated */
   public final IterableOnce map$extension(final IterableOnce $this, final Function1 f) {
      return (IterableOnce)($this instanceof Iterable ? (IterableOnce)((Iterable)$this).map(f) : $this.iterator().map(f));
   }

   /** @deprecated */
   public final IterableOnce flatMap$extension(final IterableOnce $this, final Function1 f) {
      return (IterableOnce)($this instanceof Iterable ? (IterableOnce)((Iterable)$this).flatMap(f) : $this.iterator().flatMap(f));
   }

   /** @deprecated */
   public final boolean sameElements$extension(final IterableOnce $this, final IterableOnce that) {
      return $this.iterator().sameElements(that);
   }

   public final int hashCode$extension(final IterableOnce $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final IterableOnce $this, final Object x$1) {
      if (x$1 instanceof IterableOnceExtensionMethods) {
         IterableOnce var3 = x$1 == null ? null : ((IterableOnceExtensionMethods)x$1).scala$collection$IterableOnceExtensionMethods$$it();
         if ($this == null) {
            if (var3 == null) {
               return true;
            }
         } else if ($this.equals(var3)) {
            return true;
         }
      }

      return false;
   }

   private IterableOnceExtensionMethods$() {
   }
}
