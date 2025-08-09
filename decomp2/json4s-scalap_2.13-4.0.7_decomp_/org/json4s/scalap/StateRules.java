package org.json4s.scalap;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=eaB\t\u0013!\u0003\r\t!\u0007\u0005\u0006A\u0001!\t!\t\u0003\u0006K\u0001\u0011\tAJ\u0003\u0005[\u0001\u0001a\u0006C\u0004<\u0001\t\u0007i\u0011\u0001\u001f\t\u000b\u0001\u0003A\u0011A!\t\u000bA\u0003A\u0011A)\t\u000bm\u0003A\u0011\u0001/\t\u000b\r\u0004A\u0011\u00013\t\u000b\u0019\u0004A\u0011A4\t\u000b-\u0004A\u0011\u00017\t\u000b=\u0004A\u0011\u00019\t\u000f\u0005\u0015\u0001\u0001\"\u0001\u0002\b!9\u0011\u0011\u0003\u0001\u0005\u0002\u0005M\u0001bBA\u0010\u0001\u0011\u0005\u0011\u0011\u0005\u0005\b\u0003\u0017\u0002A\u0011AA'\u0011\u001d\t9\u0007\u0001C\u0001\u0003S\u0012!b\u0015;bi\u0016\u0014V\u000f\\3t\u0015\t\u0019B#\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u0003+Y\taA[:p]R\u001a(\"A\f\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001Q\u0002CA\u000e\u001f\u001b\u0005a\"\"A\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}a\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002EA\u00111dI\u0005\u0003Iq\u0011A!\u00168ji\n\t1+\u0005\u0002(UA\u00111\u0004K\u0005\u0003Sq\u0011qAT8uQ&tw\r\u0005\u0002\u001cW%\u0011A\u0006\b\u0002\u0004\u0003:L(\u0001\u0002*vY\u0016,2a\f\u001c:!\u0019\u0001\u0014G\r\u001a5q5\t!#\u0003\u0002.%A\u00111GA\u0007\u0002\u0001A\u0011QG\u000e\u0007\u0001\t\u001994\u0001\"b\u0001M\t\t\u0011\t\u0005\u00026s\u00111!h\u0001CC\u0002\u0019\u0012\u0011\u0001W\u0001\bM\u0006\u001cGo\u001c:z+\u0005i\u0004C\u0001\u0019?\u0013\ty$CA\u0003Sk2,7/A\u0003baBd\u00170F\u0002C\u000b\u001e#\"a\u0011%\u0011\rA\n$G\r#G!\t)T\tB\u00038\u000b\t\u0007a\u0005\u0005\u00026\u000f\u0012)!(\u0002b\u0001M!)\u0011*\u0002a\u0001\u0015\u0006\ta\r\u0005\u0003\u001c\u0017Jj\u0015B\u0001'\u001d\u0005%1UO\\2uS>t\u0017\u0007E\u00031\u001dJ\"e)\u0003\u0002P%\t1!+Z:vYR\fA!\u001e8jiV\u0011!+\u0016\u000b\u0003'Z\u0003b\u0001M\u00193eQ;\u0003CA\u001bV\t\u00159dA1\u0001'\u0011\u00199f\u0001\"a\u00011\u0006\t\u0011\rE\u0002\u001c3RK!A\u0017\u000f\u0003\u0011q\u0012\u0017P\\1nKz\nAA]3bIV\u0011Q\f\u0019\u000b\u0003=\u0006\u0004b\u0001M\u00193e};\u0003CA\u001ba\t\u00159tA1\u0001'\u0011\u0015Iu\u00011\u0001c!\u0011Y2JM0\u0002\u0007\u001d,G/F\u0001f!\u0019\u0001\u0014G\r\u001a3O\u0005\u00191/\u001a;\u0015\u0005\u0015D\u0007BB5\n\t\u0003\u0007!.A\u0001t!\rY\u0012LM\u0001\u0007kB$\u0017\r^3\u0015\u0005\u0015l\u0007\"B%\u000b\u0001\u0004q\u0007\u0003B\u000eLeI\n1A\\5m+\u0005\t\bC\u0002\u00192eI\u0012xE\u0004\u0002t\u007f:\u0011A\u000f \b\u0003kjt!A^=\u000e\u0003]T!\u0001\u001f\r\u0002\rq\u0012xn\u001c;?\u0013\u0005i\u0012BA>\u001d\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003{z\f\u0011\"[7nkR\f'\r\\3\u000b\u0005md\u0012\u0002BA\u0001\u0003\u0007\t1AT5m\u0015\tih0\u0001\u0003o_:,WCAA\u0005!\u001d\u0001\u0014G\r\u001a\u0002\f\u001dr1aGA\u0007\u0013\r\ty\u0001H\u0001\u0005\u001d>tW-\u0001\u0003d_:$GcA3\u0002\u0016!1\u0011*\u0004a\u0001\u0003/\u0001RaG&3\u00033\u00012aGA\u000e\u0013\r\ti\u0002\b\u0002\b\u0005>|G.Z1o\u0003\u0015\tG\u000e\\(g+\u0019\t\u0019#!\u000f\u0002>Q!\u0011QEA !\u0015Y2JMA\u0014!\u001d\u0001dJMA\u0015\u0003w\u0001b!a\u000b\u00022\u0005]bbA;\u0002.%\u0019\u0011q\u0006\u000f\u0002\u000fA\f7m[1hK&!\u00111GA\u001b\u0005\u0011a\u0015n\u001d;\u000b\u0007\u0005=B\u0004E\u00026\u0003s!Qa\u000e\bC\u0002\u0019\u00022!NA\u001f\t\u0015QdB1\u0001'\u0011\u001d\t\tE\u0004a\u0001\u0003\u0007\nQA];mKN\u0004b!a\u000b\u0002F\u0005%\u0013\u0002BA$\u0003k\u00111aU3r!\u0019\u00194!a\u000e\u0002<\u0005)\u0011M\\=PMV1\u0011qJA.\u0003?\"B!!\u0015\u0002bAA\u0001'\r\u001a3\u0003'\ni\u0006\u0005\u0004\u0002V\u0005]\u0013\u0011L\u0007\u0003\u0003\u0007IA!a\r\u0002\u0004A\u0019Q'a\u0017\u0005\u000b]z!\u0019\u0001\u0014\u0011\u0007U\ny\u0006B\u0003;\u001f\t\u0007a\u0005C\u0004\u0002B=\u0001\r!a\u0019\u0011\r\u0005-\u0012QIA3!\u0019\u00194!!\u0017\u0002^\u0005Y!/\u001a9fCR,f\u000e^5m+\u0019\tY'!\u001e\u0002|Q!\u0011QNAD)\u0011\ty'!!\u0015\t\u0005E\u0014Q\u0010\t\taE\u0012$'a\u001d\u0002zA\u0019Q'!\u001e\u0005\r\u0005]\u0004C1\u0001'\u0005\u0005!\u0006cA\u001b\u0002|\u0011)!\b\u0005b\u0001M!9\u0011q\u0010\tA\u0002\u0005M\u0014aB5oSRL\u0017\r\u001c\u0005\b\u0003\u0007\u0003\u0002\u0019AAC\u0003!1\u0017N\\5tQ\u0016$\u0007CB\u000eL\u0003g\nI\u0002C\u0004\u0002\nB\u0001\r!a#\u0002\tI,H.\u001a\t\u0007g\r\ti)!\u001f\u0011\rmY\u00151OA:\u0001"
)
public interface StateRules {
   Rules factory();

   // $FF: synthetic method
   static Rule apply$(final StateRules $this, final Function1 f) {
      return $this.apply(f);
   }

   default Rule apply(final Function1 f) {
      return this.factory().rule(f);
   }

   // $FF: synthetic method
   static Rule unit$(final StateRules $this, final Function0 a) {
      return $this.unit(a);
   }

   default Rule unit(final Function0 a) {
      return this.apply((s) -> new Success(s, a.apply()));
   }

   // $FF: synthetic method
   static Rule read$(final StateRules $this, final Function1 f) {
      return $this.read(f);
   }

   default Rule read(final Function1 f) {
      return this.apply((s) -> new Success(s, f.apply(s)));
   }

   // $FF: synthetic method
   static Rule get$(final StateRules $this) {
      return $this.get();
   }

   default Rule get() {
      return this.apply((s) -> new Success(s, s));
   }

   // $FF: synthetic method
   static Rule set$(final StateRules $this, final Function0 s) {
      return $this.set(s);
   }

   default Rule set(final Function0 s) {
      return this.apply((oldS) -> new Success(s.apply(), oldS));
   }

   // $FF: synthetic method
   static Rule update$(final StateRules $this, final Function1 f) {
      return $this.update(f);
   }

   default Rule update(final Function1 f) {
      return this.apply((s) -> new Success(s, f.apply(s)));
   }

   // $FF: synthetic method
   static Rule nil$(final StateRules $this) {
      return $this.nil();
   }

   default Rule nil() {
      return this.unit(() -> .MODULE$.Nil());
   }

   // $FF: synthetic method
   static Rule none$(final StateRules $this) {
      return $this.none();
   }

   default Rule none() {
      return this.unit(() -> scala.None..MODULE$);
   }

   // $FF: synthetic method
   static Rule cond$(final StateRules $this, final Function1 f) {
      return $this.cond(f);
   }

   default Rule cond(final Function1 f) {
      return this.get().filter(f);
   }

   // $FF: synthetic method
   static Function1 allOf$(final StateRules $this, final Seq rules) {
      return $this.allOf(rules);
   }

   default Function1 allOf(final Seq rules) {
      return (in) -> this.rep$1(in, rules.toList(), .MODULE$.Nil());
   }

   // $FF: synthetic method
   static Rule anyOf$(final StateRules $this, final Seq rules) {
      return $this.anyOf(rules);
   }

   default Rule anyOf(final Seq rules) {
      return this.factory().rule(this.allOf((Seq)rules.map((x$1) -> this.factory().seqRule(x$1).$qmark()))).$up$up((opts) -> opts.flatMap((x) -> x));
   }

   // $FF: synthetic method
   static Rule repeatUntil$(final StateRules $this, final Rule rule, final Function1 finished, final Object initial) {
      return $this.repeatUntil(rule, finished, initial);
   }

   default Rule repeatUntil(final Rule rule, final Function1 finished, final Object initial) {
      return this.apply((in) -> this.rep$2(in, initial, finished, rule));
   }

   private Result rep$1(final Object in, final List rules, final List results) {
      Object var5;
      while(true) {
         Nil var10000 = .MODULE$.Nil();
         if (var10000 == null) {
            if (rules == null) {
               break;
            }
         } else if (var10000.equals(rules)) {
            break;
         }

         if (!(rules instanceof scala.collection.immutable..colon.colon)) {
            throw new MatchError(rules);
         }

         scala.collection.immutable..colon.colon var9 = (scala.collection.immutable..colon.colon)rules;
         Rule rule = (Rule)var9.head();
         List tl = var9.next$access$1();
         Result var12 = (Result)rule.apply(in);
         Object var6;
         if (Failure$.MODULE$.equals(var12)) {
            var6 = Failure$.MODULE$;
         } else {
            if (!(var12 instanceof Error)) {
               if (var12 instanceof Success) {
                  Success var15 = (Success)var12;
                  Object out = var15.out();
                  Object v = var15.value();
                  results = results.$colon$colon(v);
                  rules = tl;
                  in = out;
                  continue;
               }

               throw new MatchError(var12);
            }

            Error var13 = (Error)var12;
            Object x = var13.error();
            var6 = new Error(x);
         }

         var5 = var6;
         return (Result)var5;
      }

      var5 = new Success(in, results.reverse());
      return (Result)var5;
   }

   private Result rep$2(final Object in, final Object t, final Function1 finished$1, final Rule rule$2) {
      while(true) {
         Object var10000;
         if (BoxesRunTime.unboxToBoolean(finished$1.apply(t))) {
            var10000 = new Success(in, t);
         } else {
            Result var7 = (Result)rule$2.apply(in);
            if (var7 instanceof Success) {
               Success var8 = (Success)var7;
               Object out = var8.out();
               Function1 f = (Function1)var8.value();
               t = f.apply(t);
               in = out;
               continue;
            }

            Object var6;
            if (Failure$.MODULE$.equals(var7)) {
               var6 = Failure$.MODULE$;
            } else {
               if (!(var7 instanceof Error)) {
                  throw new MatchError(var7);
               }

               Error var11 = (Error)var7;
               Object x = var11.error();
               var6 = new Error(x);
            }

            var10000 = var6;
         }

         return (Result)var10000;
      }
   }

   static void $init$(final StateRules $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
