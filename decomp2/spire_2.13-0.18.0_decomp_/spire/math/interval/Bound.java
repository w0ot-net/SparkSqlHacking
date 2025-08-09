package spire.math.interval;

import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeSemigroup;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=ga\u0002\f\u0018!\u0003\r\tC\b\u0005\u0006M\u0001!\ta\n\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006G\u0002!\t\u0001\u001a\u0005\u0006S\u0002!\tA\u001b\u0005\u0006e\u0002!\ta\u001d\u0005\u0006o\u0002!\t\u0001\u001f\u0005\u0007\u007f\u0002!\t!!\u0001\t\r%\u0004A\u0011AA\u0005\u0011\u0019\u0011\b\u0001\"\u0001\u0002\u0012!1q\u000f\u0001C\u0001\u00033Aaa \u0001\u0005\u0002\u0005\u0005raBA\u001c/!\u0005\u0011\u0011\b\u0004\u0007-]A\t!a\u000f\t\u000f\u0005ur\u0002\"\u0001\u0002@!A\u0011\u0011I\b\u0005\u0002m\t\u0019\u0005\u0003\u0005\u0002j=!\taGA6\u0011!\t)i\u0004C\u00017\u0005\u001d\u0005\u0002CAP\u001f\u0011\u00051$!)\t\u000f\u0005ev\u0002b\u0001\u0002<\n)!i\\;oI*\u0011\u0001$G\u0001\tS:$XM\u001d<bY*\u0011!dG\u0001\u0005[\u0006$\bNC\u0001\u001d\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)\"aH!\u0014\u0005\u0001\u0001\u0003CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002QA\u0011\u0011%K\u0005\u0003U\t\u0012A!\u00168ji\u0006\u0019Q.\u00199\u0016\u00055\u0012DC\u0001\u0018<!\ry\u0003\u0001M\u0007\u0002/A\u0011\u0011G\r\u0007\u0001\t\u0015\u0019$A1\u00015\u0005\u0005\u0011\u0015CA\u001b9!\t\tc'\u0003\u00028E\t9aj\u001c;iS:<\u0007CA\u0011:\u0013\tQ$EA\u0002B]fDQ\u0001\u0010\u0002A\u0002u\n\u0011A\u001a\t\u0005Cy\u0002\u0005'\u0003\u0002@E\tIa)\u001e8di&|g.\r\t\u0003c\u0005#QA\u0011\u0001C\u0002Q\u0012\u0011!Q\u0001\bG>l'-\u001b8f+\t)e\n\u0006\u0002G\u0019R\u0011q\t\u0013\t\u0004_\u0001\u0001\u0005\"\u0002\u001f\u0004\u0001\u0004I\u0005#B\u0011K\u0001\u0002\u0003\u0015BA&#\u0005%1UO\\2uS>t'\u0007C\u0003N\u0007\u0001\u0007q)A\u0002sQN$QaM\u0002C\u0002Q\nA\"\u001e8bef|F%\\5okN$\"aR)\t\u000bI#\u00019A*\u0002\u0005\u00154\bc\u0001+a\u0001:\u0011Q+\u0018\b\u0003-ns!a\u0016.\u000e\u0003aS!!W\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0012B\u0001/\u001c\u0003\u001d\tGnZ3ce\u0006L!AX0\u0002\u000fA\f7m[1hK*\u0011AlG\u0005\u0003C\n\u0014Q\"\u00113eSRLg/Z$s_V\u0004(B\u00010`\u0003)\u0011XmY5qe>\u001c\u0017\r\u001c\u000b\u0003\u000f\u0016DQAU\u0003A\u0004\u0019\u00042\u0001V4A\u0013\tA'MA\nNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3He>,\b/A\u0003%a2,8\u000f\u0006\u0002laR\u0011q\t\u001c\u0005\u0006%\u001a\u0001\u001d!\u001c\t\u0004):\u0004\u0015BA8c\u0005E\tE\rZ5uSZ,7+Z7jOJ|W\u000f\u001d\u0005\u0006c\u001a\u0001\r\u0001Q\u0001\u0002C\u00061A%\\5okN$\"\u0001\u001e<\u0015\u0005\u001d+\b\"\u0002*\b\u0001\b\u0019\u0006\"B9\b\u0001\u0004\u0001\u0015A\u0002\u0013uS6,7\u000f\u0006\u0002z}R\u0011qI\u001f\u0005\u0006%\"\u0001\u001da\u001f\t\u0004)r\u0004\u0015BA?c\u0005]iU\u000f\u001c;ja2L7-\u0019;jm\u0016\u001cV-\\5he>,\b\u000fC\u0003r\u0011\u0001\u0007\u0001)\u0001\u0003%I&4H\u0003BA\u0002\u0003\u000f!2aRA\u0003\u0011\u0015\u0011\u0016\u0002q\u0001g\u0011\u0015\t\u0018\u00021\u0001A)\u0011\tY!a\u0004\u0015\u0007\u001d\u000bi\u0001C\u0003S\u0015\u0001\u000fQ\u000eC\u0003N\u0015\u0001\u0007q\t\u0006\u0003\u0002\u0014\u0005]AcA$\u0002\u0016!)!k\u0003a\u0002'\")Qj\u0003a\u0001\u000fR!\u00111DA\u0010)\r9\u0015Q\u0004\u0005\u0006%2\u0001\u001da\u001f\u0005\u0006\u001b2\u0001\ra\u0012\u000b\u0005\u0003G\t9\u0003F\u0002H\u0003KAQAU\u0007A\u0004\u0019DQ!T\u0007A\u0002\u001dKs\u0001AA\u0016\u0003_\t\u0019$C\u0002\u0002.]\u0011!\"R7qif\u0014u.\u001e8e\u0013\r\t\td\u0006\u0002\b+:\u0014w.\u001e8e\u0013\r\t)d\u0006\u0002\u000b-\u0006dW/\u001a\"pk:$\u0017!\u0002\"pk:$\u0007CA\u0018\u0010'\ty\u0001%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003s\t\u0001\"\\5o\u0019><XM]\u000b\u0005\u0003\u000b\ni\u0005\u0006\u0005\u0002H\u0005e\u0013QLA0)\u0011\tI%a\u0014\u0011\t=\u0002\u00111\n\t\u0004c\u00055C!\u0002\"\u0012\u0005\u0004!\u0004\"CA)#\u0005\u0005\t9AA*\u0003))g/\u001b3f]\u000e,G%\r\t\u0006)\u0006U\u00131J\u0005\u0004\u0003/\u0012'!B(sI\u0016\u0014\bbBA.#\u0001\u0007\u0011\u0011J\u0001\u0004Y\"\u001c\bBB'\u0012\u0001\u0004\tI\u0005C\u0004\u0002bE\u0001\r!a\u0019\u0002\u0015\u0015l\u0007\u000f^=Jg6Kg\u000eE\u0002\"\u0003KJ1!a\u001a#\u0005\u001d\u0011un\u001c7fC:\f\u0001\"\\1y\u0019><XM]\u000b\u0005\u0003[\n)\b\u0006\u0005\u0002p\u0005u\u0014qPAA)\u0011\t\t(a\u001e\u0011\t=\u0002\u00111\u000f\t\u0004c\u0005UD!\u0002\"\u0013\u0005\u0004!\u0004\"CA=%\u0005\u0005\t9AA>\u0003))g/\u001b3f]\u000e,GE\r\t\u0006)\u0006U\u00131\u000f\u0005\b\u00037\u0012\u0002\u0019AA9\u0011\u0019i%\u00031\u0001\u0002r!9\u00111\u0011\nA\u0002\u0005\r\u0014AC3naRL\u0018j]'bq\u0006AQ.\u001b8VaB,'/\u0006\u0003\u0002\n\u0006EE\u0003CAF\u00033\u000bY*!(\u0015\t\u00055\u00151\u0013\t\u0005_\u0001\ty\tE\u00022\u0003##QAQ\nC\u0002QB\u0011\"!&\u0014\u0003\u0003\u0005\u001d!a&\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007E\u0003U\u0003+\ny\tC\u0004\u0002\\M\u0001\r!!$\t\r5\u001b\u0002\u0019AAG\u0011\u001d\t\tg\u0005a\u0001\u0003G\n\u0001\"\\1y+B\u0004XM]\u000b\u0005\u0003G\u000bY\u000b\u0006\u0005\u0002&\u0006M\u0016QWA\\)\u0011\t9+!,\u0011\t=\u0002\u0011\u0011\u0016\t\u0004c\u0005-F!\u0002\"\u0015\u0005\u0004!\u0004\"CAX)\u0005\u0005\t9AAY\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0006)\u0006U\u0013\u0011\u0016\u0005\b\u00037\"\u0002\u0019AAT\u0011\u0019iE\u00031\u0001\u0002(\"9\u00111\u0011\u000bA\u0002\u0005\r\u0014AA3r+\u0011\ti,!3\u0015\t\u0005}\u00161\u001a\t\u0006)\u0006\u0005\u0017QY\u0005\u0004\u0003\u0007\u0014'AA#r!\u0011y\u0003!a2\u0011\u0007E\nI\rB\u0003C+\t\u0007A\u0007\u0003\u0004S+\u0001\u000f\u0011Q\u001a\t\u0006)\u0006\u0005\u0017q\u0019"
)
public interface Bound {
   // $FF: synthetic method
   static Bound map$(final Bound $this, final Function1 f) {
      return $this.map(f);
   }

   default Bound map(final Function1 f) {
      Object var2;
      if (this instanceof Open) {
         Open var4 = (Open)this;
         Object a = var4.a();
         var2 = new Open(f.apply(a));
      } else if (this instanceof Closed) {
         Closed var6 = (Closed)this;
         Object a = var6.a();
         var2 = new Closed(f.apply(a));
      } else if (this instanceof Unbound) {
         var2 = new Unbound();
      } else {
         if (!(this instanceof EmptyBound)) {
            throw new MatchError(this);
         }

         var2 = new EmptyBound();
      }

      return (Bound)var2;
   }

   // $FF: synthetic method
   static Bound combine$(final Bound $this, final Bound rhs, final Function2 f) {
      return $this.combine(rhs, f);
   }

   default Bound combine(final Bound rhs, final Function2 f) {
      Tuple2 var4 = new Tuple2(this, rhs);
      Object var3;
      if (var4 != null) {
         Bound var5 = (Bound)var4._1();
         if (var5 instanceof EmptyBound) {
            var3 = this;
            return (Bound)var3;
         }
      }

      if (var4 != null) {
         Bound var6 = (Bound)var4._2();
         if (var6 instanceof EmptyBound) {
            var3 = rhs;
            return (Bound)var3;
         }
      }

      if (var4 != null) {
         Bound var7 = (Bound)var4._1();
         if (var7 instanceof Unbound) {
            var3 = this;
            return (Bound)var3;
         }
      }

      if (var4 != null) {
         Bound var8 = (Bound)var4._2();
         if (var8 instanceof Unbound) {
            var3 = rhs;
            return (Bound)var3;
         }
      }

      if (var4 != null) {
         Bound var9 = (Bound)var4._1();
         Bound var10 = (Bound)var4._2();
         if (var9 instanceof Closed) {
            Closed var11 = (Closed)var9;
            Object a = var11.a();
            if (var10 instanceof Closed) {
               Closed var13 = (Closed)var10;
               Object b = var13.a();
               var3 = new Closed(f.apply(a, b));
               return (Bound)var3;
            }
         }
      }

      if (var4 != null) {
         Bound var15 = (Bound)var4._1();
         Bound var16 = (Bound)var4._2();
         if (var15 instanceof Closed) {
            Closed var17 = (Closed)var15;
            Object a = var17.a();
            if (var16 instanceof Open) {
               Open var19 = (Open)var16;
               Object b = var19.a();
               var3 = new Open(f.apply(a, b));
               return (Bound)var3;
            }
         }
      }

      if (var4 != null) {
         Bound var21 = (Bound)var4._1();
         Bound var22 = (Bound)var4._2();
         if (var21 instanceof Open) {
            Open var23 = (Open)var21;
            Object a = var23.a();
            if (var22 instanceof Closed) {
               Closed var25 = (Closed)var22;
               Object b = var25.a();
               var3 = new Open(f.apply(a, b));
               return (Bound)var3;
            }
         }
      }

      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         Bound var27 = (Bound)var4._1();
         Bound var28 = (Bound)var4._2();
         if (!(var27 instanceof Open)) {
            throw new MatchError(var4);
         } else {
            Open var29 = (Open)var27;
            Object a = var29.a();
            if (!(var28 instanceof Open)) {
               throw new MatchError(var4);
            } else {
               Open var31 = (Open)var28;
               Object b = var31.a();
               var3 = new Open(f.apply(a, b));
               return (Bound)var3;
            }
         }
      }
   }

   // $FF: synthetic method
   static Bound unary_$minus$(final Bound $this, final AdditiveGroup ev) {
      return $this.unary_$minus(ev);
   }

   default Bound unary_$minus(final AdditiveGroup ev) {
      return this.map((x$1) -> ev.negate(x$1));
   }

   // $FF: synthetic method
   static Bound reciprocal$(final Bound $this, final MultiplicativeGroup ev) {
      return $this.reciprocal(ev);
   }

   default Bound reciprocal(final MultiplicativeGroup ev) {
      return this.map((x$2) -> ev.reciprocal(x$2));
   }

   // $FF: synthetic method
   static Bound $plus$(final Bound $this, final Object a, final AdditiveSemigroup ev) {
      return $this.$plus(a, ev);
   }

   default Bound $plus(final Object a, final AdditiveSemigroup ev) {
      return this.map((x$3) -> ev.plus(x$3, a));
   }

   // $FF: synthetic method
   static Bound $minus$(final Bound $this, final Object a, final AdditiveGroup ev) {
      return $this.$minus(a, ev);
   }

   default Bound $minus(final Object a, final AdditiveGroup ev) {
      return this.map((x$4) -> ev.minus(x$4, a));
   }

   // $FF: synthetic method
   static Bound $times$(final Bound $this, final Object a, final MultiplicativeSemigroup ev) {
      return $this.$times(a, ev);
   }

   default Bound $times(final Object a, final MultiplicativeSemigroup ev) {
      return this.map((x$5) -> ev.times(x$5, a));
   }

   // $FF: synthetic method
   static Bound $div$(final Bound $this, final Object a, final MultiplicativeGroup ev) {
      return $this.$div(a, ev);
   }

   default Bound $div(final Object a, final MultiplicativeGroup ev) {
      return this.map((x$6) -> ev.div(x$6, a));
   }

   // $FF: synthetic method
   static Bound $plus$(final Bound $this, final Bound rhs, final AdditiveSemigroup ev) {
      return $this.$plus(rhs, ev);
   }

   default Bound $plus(final Bound rhs, final AdditiveSemigroup ev) {
      return this.combine(rhs, (x$7, x$8) -> ev.plus(x$7, x$8));
   }

   // $FF: synthetic method
   static Bound $minus$(final Bound $this, final Bound rhs, final AdditiveGroup ev) {
      return $this.$minus(rhs, ev);
   }

   default Bound $minus(final Bound rhs, final AdditiveGroup ev) {
      return this.combine(rhs, (x$9, x$10) -> ev.minus(x$9, x$10));
   }

   // $FF: synthetic method
   static Bound $times$(final Bound $this, final Bound rhs, final MultiplicativeSemigroup ev) {
      return $this.$times(rhs, ev);
   }

   default Bound $times(final Bound rhs, final MultiplicativeSemigroup ev) {
      return this.combine(rhs, (x$11, x$12) -> ev.times(x$11, x$12));
   }

   // $FF: synthetic method
   static Bound $div$(final Bound $this, final Bound rhs, final MultiplicativeGroup ev) {
      return $this.$div(rhs, ev);
   }

   default Bound $div(final Bound rhs, final MultiplicativeGroup ev) {
      return this.combine(rhs, (x$13, x$14) -> ev.div(x$13, x$14));
   }

   static void $init$(final Bound $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
