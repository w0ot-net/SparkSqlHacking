package org.json4s.scalap;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001B\b\u0011\u0001]A\u0001b\b\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\u0006k\u0001!\tA\u000e\u0005\u0006s\u0001!\tA\u000f\u0005\u0006\u007f\u0001!\t\u0001\u0011\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006)\u0002!\t!\u0016\u0005\u0006=\u0002!\ta\u0018\u0005\u0006g\u0002!\t\u0001\u001e\u0005\u0007\u007f\u0002!\t!!\u0001\t\u000f\u0005u\u0001\u0001\"\u0001\u0002 !9\u0011\u0011\u0007\u0001\u0005\u0002\u0005M\u0002bBA\"\u0001\u0011\u0005\u0011Q\t\u0005\b\u0003;\u0002A\u0011AA0\u0011\u001d\t\u0019\b\u0001C\u0001\u0003k\u0012qaU3r%VdWM\u0003\u0002\u0012%\u000511oY1mCBT!a\u0005\u000b\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005)\u0012aA8sO\u000e\u0001Q\u0003\u0002\r'aM\u001a\"\u0001A\r\u0011\u0005iiR\"A\u000e\u000b\u0003q\tQa]2bY\u0006L!AH\u000e\u0003\r\u0005s\u0017PU3g\u0003\u0011\u0011X\u000f\\3\u0011\r\u0005\u0012C\u0005J\u00183\u001b\u0005\u0001\u0012BA\u0012\u0011\u0005\u0011\u0011V\u000f\\3\u0011\u0005\u00152C\u0002\u0001\u0003\u0006O\u0001\u0011\r\u0001\u000b\u0002\u0002'F\u0011\u0011\u0006\f\t\u00035)J!aK\u000e\u0003\u000f9{G\u000f[5oOB\u0011!$L\u0005\u0003]m\u00111!\u00118z!\t)\u0003\u0007\u0002\u00042\u0001\u0011\u0015\r\u0001\u000b\u0002\u0002\u0003B\u0011Qe\r\u0003\u0007i\u0001!)\u0019\u0001\u0015\u0003\u0003a\u000ba\u0001P5oSRtDCA\u001c9!\u0015\t\u0003\u0001J\u00183\u0011\u0015y\"\u00011\u0001!\u0003\u0019!\u0013/\\1sWV\t1\b\u0005\u0004\"E\u0011\"CH\r\t\u00045uz\u0013B\u0001 \u001c\u0005\u0019y\u0005\u000f^5p]\u0006aA%\\5okN$\u0013/\\1sWV\t\u0011\t\u0005\u0004\"E\u0011\"#I\r\t\u00035\rK!\u0001R\u000e\u0003\u000f\t{w\u000e\\3b]\u00061A\u0005^5nKN,\u0012a\u0012\t\u0007C\t\"C\u0005\u0013\u001a\u0011\u0007%\u000bvF\u0004\u0002K\u001f:\u00111JT\u0007\u0002\u0019*\u0011QJF\u0001\u0007yI|w\u000e\u001e \n\u0003qI!\u0001U\u000e\u0002\u000fA\f7m[1hK&\u0011!k\u0015\u0002\u0005\u0019&\u001cHO\u0003\u0002Q7\u0005)A\u0005\u001d7vgV\ta\u000b\u0005\u0004\"E\u0011\"sK\r\t\u00041v{S\"A-\u000b\u0005i[\u0016!C5n[V$\u0018M\u00197f\u0015\ta6$\u0001\u0006d_2dWm\u0019;j_:L!AU-\u0002)\u0011\"\u0018\u000e\u001c3fI\u001d\u0014X-\u0019;fe\u0012\nX.\u0019:l+\r\u00017m\u001a\u000b\u0003C*\u0004b!\t\u0012%I\t4\u0007CA\u0013d\t\u0015!wA1\u0001f\u0005\u0005\u0011\u0015CA\u0018-!\t)s\rB\u0003i\u000f\t\u0007\u0011N\u0001\u0002YeE\u0011!\u0007\f\u0005\u0007W\u001e!\t\u0019\u00017\u0002\u0003\u0019\u00042AG7p\u0013\tq7D\u0001\u0005=Eft\u0017-\\3?!\u0019\t#\u0005\n\u0013qMB!!$\u001d2c\u0013\t\u00118DA\u0005Gk:\u001cG/[8oc\u0005!B\u0005^5mI\u0016$sM]3bi\u0016\u0014H\u0005^5nKN,2!\u001e={)\t18\u0010\u0005\u0004\"E\u0011\"s/\u001f\t\u0003Ka$Q\u0001\u001a\u0005C\u0002\u0015\u0004\"!\n>\u0005\u000b!D!\u0019A5\t\r-DA\u00111\u0001}!\rQR. \t\u0007C\t\"CE`=\u0011\ti\txo^\u0001\u0013IQLG\u000eZ3%i&lWm\u001d\u0013uS2$W-\u0006\u0004\u0002\u0004\u0005%\u0011Q\u0002\u000b\u0005\u0003\u000b\ty\u0001\u0005\u0005\"E\u0011\"\u0013qAA\u0006!\r)\u0013\u0011\u0002\u0003\u0006I&\u0011\r!\u001a\t\u0004K\u00055A!\u00025\n\u0005\u0004I\u0007\u0002CA\t\u0013\u0011\u0005\r!a\u0005\u0002\t)|\u0017N\u001c\t\u000555\f)\u0002\u0005\u0005\"E\u0011\"\u0013qCA\u0006!%Q\u0012\u0011DA\u0004\u0003\u000f\t9!C\u0002\u0002\u001cm\u0011\u0011BR;oGRLwN\u001c\u001a\u0002\u0013\u0011\u0002H.^:%I&4X\u0003BA\u0011\u0003O!B!a\t\u0002*A9\u0011E\t\u0013%/\u0006\u0015\u0002cA\u0013\u0002(\u0011)\u0001N\u0003b\u0001S\"A\u00111\u0006\u0006\u0005\u0002\u0004\ti#A\u0002tKB\u0004BAG7\u00020A9\u0011E\t\u0013%Y\u0005\u0015\u0012A\u0003\u0013uS6,7\u000f\n3jmV!\u0011QGA\u001e)\u0011\t9$!\u0010\u0011\u000f\u0005\u0012C\u0005J,\u0002:A\u0019Q%a\u000f\u0005\u000b!\\!\u0019A5\t\u0011\u0005-2\u0002\"a\u0001\u0003\u007f\u0001BAG7\u0002BA9\u0011E\t\u0013%Y\u0005e\u0012A\u0005\u0013uS6,7\u000f\n;jY\u0012,G%\\5okN,b!a\u0012\u0002N\u0005MC\u0003BA%\u0003+\u0002\u0002\"\t\u0012%\u0003\u0017B\u0015\u0011\u000b\t\u0004K\u00055CABA(\u0019\t\u0007\u0001FA\u0002PkR\u00042!JA*\t\u0015AGB1\u0001j\u0011!\t9\u0006\u0004CA\u0002\u0005e\u0013aA3oIB!!$\\A.!!\t#\u0005JA&Y\u0005E\u0013!\u0005\u0013qYV\u001cH\u0005^5mI\u0016$S.\u001b8vgV1\u0011\u0011MA4\u0003W\"B!a\u0019\u0002nAA\u0011E\t\u0013\u0002f]\u000bI\u0007E\u0002&\u0003O\"a!a\u0014\u000e\u0005\u0004A\u0003cA\u0013\u0002l\u0011)\u0001.\u0004b\u0001S\"A\u0011qK\u0007\u0005\u0002\u0004\ty\u0007\u0005\u0003\u001b[\u0006E\u0004\u0003C\u0011#I\u0005\u0015D&!\u001b\u0002\u000bQLW.Z:\u0015\t\u0005]\u0014q\u0010\t\bC\t\"C%!\u001f3!\u0011I\u00151P\u0018\n\u0007\u0005u4KA\u0002TKFDq!!!\u000f\u0001\u0004\t\u0019)A\u0002ok6\u00042AGAC\u0013\r\t9i\u0007\u0002\u0004\u0013:$\b"
)
public class SeqRule {
   private final Rule rule;

   public Rule $qmark() {
      return this.rule.factory().inRule(this.rule).mapRule((x0$1) -> {
         Function1 var1;
         if (x0$1 instanceof Success) {
            Success var3 = (Success)x0$1;
            Object out = var3.out();
            Object a = var3.value();
            var1 = (in) -> new Success(out, new Some(a));
         } else if (Failure$.MODULE$.equals(x0$1)) {
            var1 = (in) -> new Success(in, .MODULE$);
         } else {
            if (!(x0$1 instanceof Error)) {
               throw new MatchError(x0$1);
            }

            Error var6 = (Error)x0$1;
            Object x = var6.error();
            var1 = (in) -> new Error(x);
         }

         return var1;
      });
   }

   public Rule $minus$qmark() {
      return this.$qmark().map((x$1) -> BoxesRunTime.boxToBoolean($anonfun$$minus$qmark$1(x$1)));
   }

   public Rule $times() {
      return this.rule.factory().from().apply((in) -> this.rep$1(in, scala.package..MODULE$.Nil()));
   }

   public Rule $plus() {
      return this.rule.$tilde$plus$plus(() -> this.$times());
   }

   public Rule $tilde$greater$qmark(final Function0 f) {
      return this.rule.flatMap((a) -> this.rule.factory().seqRule((Rule)f.apply()).$qmark().map((fs) -> scala.Option..MODULE$.option2Iterable(fs).foldLeft(a, (b, f) -> f.apply(b))));
   }

   public Rule $tilde$greater$times(final Function0 f) {
      return this.rule.flatMap((a) -> this.rule.factory().seqRule((Rule)f.apply()).$times().map((fs) -> fs.foldLeft(a, (b, f) -> f.apply(b))));
   }

   public Rule $tilde$times$tilde(final Function0 join) {
      return this.$tilde$greater$times(() -> ((Rule)join.apply()).flatMap((f) -> this.rule.map((a) -> (x$2) -> f.apply(x$2, a))));
   }

   public Rule $plus$div(final Function0 sep) {
      return this.rule.$tilde$plus$plus(() -> this.rule.factory().seqRule(((Rule)sep.apply()).$minus$tilde(() -> this.rule)).$times());
   }

   public Rule $times$div(final Function0 sep) {
      return this.$plus$div(sep).$bar(() -> this.rule.factory().state().nil());
   }

   public Rule $times$tilde$minus(final Function0 end) {
      return this.rule.factory().seqRule(this.rule.$minus(end)).$times().$tilde$minus(end);
   }

   public Rule $plus$tilde$minus(final Function0 end) {
      return this.rule.factory().seqRule(this.rule.$minus(end)).$plus().$tilde$minus(end);
   }

   public Rule times(final int num) {
      return this.rule.factory().from().apply((in) -> this.rep$2(scala.package..MODULE$.Nil(), 0, in, num));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$minus$qmark$1(final Option x$1) {
      return x$1.isDefined();
   }

   private final Result rep$1(final Object in, final List acc) {
      while(true) {
         Result var5 = (Result)this.rule.apply(in);
         if (!(var5 instanceof Success)) {
            Object var4;
            if (Failure$.MODULE$.equals(var5)) {
               var4 = new Success(in, acc.reverse());
            } else {
               if (!(var5 instanceof Error)) {
                  throw new MatchError(var5);
               }

               Error var10 = (Error)var5;
               var4 = var10;
            }

            return (Result)var4;
         }

         Success var6 = (Success)var5;
         Object out = var6.out();
         Object a = var6.value();
         acc = acc.$colon$colon(a);
         in = out;
      }
   }

   private final Result rep$2(final List result, final int i, final Object in, final int num$1) {
      while(true) {
         Object var10000;
         if (i == num$1) {
            var10000 = new Success(in, result.reverse());
         } else {
            Result var7 = (Result)this.rule.apply(in);
            if (var7 instanceof Success) {
               Success var8 = (Success)var7;
               Object out = var8.out();
               Object a = var8.value();
               List var13 = result.$colon$colon(a);
               int var10001 = i + 1;
               in = out;
               i = var10001;
               result = var13;
               continue;
            }

            Object var6;
            if (Failure$.MODULE$.equals(var7)) {
               var6 = Failure$.MODULE$;
            } else {
               if (!(var7 instanceof Error)) {
                  throw new MatchError(var7);
               }

               Error var12 = (Error)var7;
               var6 = var12;
            }

            var10000 = var6;
         }

         return (Result)var10000;
      }
   }

   public SeqRule(final Rule rule) {
      this.rule = rule;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
