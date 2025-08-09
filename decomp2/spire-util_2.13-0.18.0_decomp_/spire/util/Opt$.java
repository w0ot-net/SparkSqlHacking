package spire.util;

import cats.kernel.Eq;
import cats.kernel.Eq.;
import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.runtime.BoxesRunTime;
import scala.util.Either;

public final class Opt$ {
   public static final Opt$ MODULE$ = new Opt$();

   public Object apply(final Object a) {
      return a;
   }

   public Object empty() {
      return null;
   }

   public Object unapply(final Object n) {
      return n;
   }

   public Eq EqOpt(final Eq ev) {
      return .MODULE$.instance((x0$1, x1$1) -> BoxesRunTime.boxToBoolean($anonfun$EqOpt$1(ev, ((Opt)x0$1).ref(), ((Opt)x1$1).ref())));
   }

   public final boolean isDefined$extension(final Object $this) {
      return $this != null;
   }

   public final boolean nonEmpty$extension(final Object $this) {
      return $this != null;
   }

   public final boolean isEmpty$extension(final Object $this) {
      return $this == null;
   }

   public final Object get$extension(final Object $this) {
      if ($this == null) {
         throw new NoSuchElementException("Opt.empty.get");
      } else {
         return $this;
      }
   }

   public final String toString$extension(final Object $this) {
      return $this == null ? "Opt.empty" : (new StringBuilder(5)).append("Opt(").append($this).append(")").toString();
   }

   public final Object filter$extension(final Object $this, final Function1 f) {
      return $this != null && BoxesRunTime.unboxToBoolean(f.apply($this)) ? $this : this.empty();
   }

   public final Object map$extension(final Object $this, final Function1 f) {
      return $this == null ? this.empty() : this.apply(f.apply($this));
   }

   public final Object flatMap$extension(final Object $this, final Function1 f) {
      return $this == null ? this.empty() : ((Opt)f.apply($this)).ref();
   }

   public final Object fold$extension(final Object $this, final Function0 b, final Function1 f) {
      return $this == null ? b.apply() : f.apply($this);
   }

   public final Object getOrElse$extension(final Object $this, final Function0 default) {
      return $this == null ? default.apply() : $this;
   }

   public final Object getOrElseFast$extension(final Object $this, final Object default) {
      return $this == null ? default : $this;
   }

   public final Option toOption$extension(final Object $this) {
      return (Option)($this == null ? scala.None..MODULE$ : new Some($this));
   }

   public final List toList$extension(final Object $this) {
      return (List)($this == null ? scala.package..MODULE$.Nil() : scala.package..MODULE$.Nil().$colon$colon($this));
   }

   public final boolean contains$extension(final Object $this, final Object elem) {
      return $this == null ? false : BoxesRunTime.equals($this, elem);
   }

   public final boolean exists$extension(final Object $this, final Function1 p) {
      return $this == null ? false : BoxesRunTime.unboxToBoolean(p.apply($this));
   }

   public final boolean forall$extension(final Object $this, final Function1 p) {
      return $this == null ? true : BoxesRunTime.unboxToBoolean(p.apply($this));
   }

   public final void foreach$extension(final Object $this, final Function1 f) {
      if ($this != null) {
         f.apply($this);
      }

   }

   public final Iterator iterator$extension(final Object $this) {
      return $this == null ? scala.collection.Iterator..MODULE$.empty() : scala.collection.Iterator..MODULE$.single($this);
   }

   public final Either toRight$extension(final Object $this, final Function0 left) {
      return (Either)($this == null ? scala.package..MODULE$.Left().apply(left.apply()) : scala.package..MODULE$.Right().apply($this));
   }

   public final Either toLeft$extension(final Object $this, final Function0 right) {
      return (Either)($this == null ? scala.package..MODULE$.Right().apply(right.apply()) : scala.package..MODULE$.Left().apply($this));
   }

   public final int hashCode$extension(final Object $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Object $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof Opt) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         Object var5 = x$1 == null ? null : ((Opt)x$1).ref();
         if (BoxesRunTime.equals($this, var5)) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$EqOpt$1(final Eq ev$1, final Object x0$1, final Object x1$1) {
      Tuple2 var4 = new Tuple2(new Opt(x0$1), new Opt(x1$1));
      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         Object x = ((Opt)var4._1()).ref();
         Object y = ((Opt)var4._2()).ref();
         boolean var3 = MODULE$.isEmpty$extension(x) ? MODULE$.isEmpty$extension(y) : MODULE$.nonEmpty$extension(y) && ev$1.eqv(x, y);
         return var3;
      }
   }

   private Opt$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
