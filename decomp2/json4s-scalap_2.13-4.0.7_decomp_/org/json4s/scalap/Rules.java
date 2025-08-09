package org.json4s.scalap;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Function6;
import scala.Function7;
import scala.MatchError;
import scala.PartialFunction;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tMaa\u0002\f\u0018!\u0003\r\tA\b\u0005\u0006K\u0001!\tA\n\u0005\u0006U\u0001!\u0019a\u000b\u0005\u0006\u001b\u0002!\u0019A\u0014\u0005\u0006;\u0002!\u0019A\u0018\u0004\bW\u0002\u0001\n1%\u0001m\u0011\u0015qWA\"\u0001p\u0011\u0015i\b\u0001\"\u0001\u007f\u0011\u001d\ty\u0001\u0001C\u0001\u0003#Aq!!\u000b\u0001\t\u0003\tY\u0003C\u0004\u0002B\u0001!\t!a\u0011\t\u000f\u0005\u001d\u0003\u0001\"\u0001\u0002J!9\u0011q\t\u0001\u0005\u0002\u0005M\u0003bBA1\u0001\u0011\u0005\u00111\r\u0005\b\u0003\u0007\u0003A\u0011AAC\r\u0019\t)\r\u0001\u0001\u0002H\"Iai\u0004B\u0001B\u0003%\u0011Q\u001c\u0005\b\u0003C|A\u0011AAr\u0011%\tIo\u0004b\u0001\n\u0003\tY\u000f\u0003\u0005\u0002p>\u0001\u000b\u0011BAw\u0011\u0019qw\u0002\"\u0001\u0002r\"9\u0011q\u001f\u0001\u0005\u0002\u0005e(!\u0002*vY\u0016\u001c(B\u0001\r\u001a\u0003\u0019\u00198-\u00197ba*\u0011!dG\u0001\u0007UN|g\u000eN:\u000b\u0003q\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\u0010\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tq\u0005\u0005\u0002!Q%\u0011\u0011&\t\u0002\u0005+:LG/\u0001\u0003sk2,W#\u0002\u00174{\u0001\u001bECA\u0017F!\u0019qs&\r\u001f@\u00056\tq#\u0003\u00021/\t!!+\u001e7f!\t\u00114\u0007\u0004\u0001\u0005\u000bQ\u0012!\u0019A\u001b\u0003\u0005%s\u0017C\u0001\u001c:!\t\u0001s'\u0003\u00029C\t9aj\u001c;iS:<\u0007C\u0001\u0011;\u0013\tY\u0014EA\u0002B]f\u0004\"AM\u001f\u0005\u000by\u0012!\u0019A\u001b\u0003\u0007=+H\u000f\u0005\u00023\u0001\u0012)\u0011I\u0001b\u0001k\t\t\u0011\t\u0005\u00023\u0007\u0012)AI\u0001b\u0001k\t\t\u0001\fC\u0003G\u0005\u0001\u0007q)A\u0001g!\u0011\u0001\u0003*\r&\n\u0005%\u000b#!\u0003$v]\u000e$\u0018n\u001c82!\u0015q3\nP C\u0013\tauC\u0001\u0004SKN,H\u000e^\u0001\u0007S:\u0014V\u000f\\3\u0016\u000b=#f\u000b\u0017.\u0015\u0005A[\u0006C\u0002\u0018R'V;\u0016,\u0003\u0002S/\t1\u0011J\u001c*vY\u0016\u0004\"A\r+\u0005\u000bQ\u001a!\u0019A\u001b\u0011\u0005I2F!\u0002 \u0004\u0005\u0004)\u0004C\u0001\u001aY\t\u0015\t5A1\u00016!\t\u0011$\fB\u0003E\u0007\t\u0007Q\u0007C\u0003+\u0007\u0001\u0007A\f\u0005\u0004/_M+v+W\u0001\bg\u0016\f(+\u001e7f+\u0011yFM\u001a5\u0015\u0005\u0001L\u0007#\u0002\u0018bG\u0016<\u0017B\u00012\u0018\u0005\u001d\u0019V-\u001d*vY\u0016\u0004\"A\r3\u0005\u000bQ\"!\u0019A\u001b\u0011\u0005I2G!B!\u0005\u0005\u0004)\u0004C\u0001\u001ai\t\u0015!EA1\u00016\u0011\u0015QC\u00011\u0001k!\u0019qsfY2fO\nAaI]8n%VdW-\u0006\u0002ngN\u0011QaH\u0001\u0006CB\u0004H._\u000b\u0005aV<\u0018\u0010\u0006\u0002ruB1af\f:umb\u0004\"AM:\u0005\u000bQ*!\u0019A\u001b\u0011\u0005I*H!\u0002 \u0007\u0005\u0004)\u0004C\u0001\u001ax\t\u0015\teA1\u00016!\t\u0011\u0014\u0010B\u0003E\r\t\u0007Q\u0007C\u0003G\r\u0001\u00071\u0010\u0005\u0003!\u0011Jd\b#\u0002\u0018LiZD\u0018\u0001\u00024s_6,2a`A\u0007+\t\t\tAE\u0003\u0002\u0004}\t9A\u0002\u0004\u0002\u0006\u001d\u0001\u0011\u0011\u0001\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0006\u0003\u0013)\u00111B\u0007\u0002\u0001A\u0019!'!\u0004\u0005\u000bQ:!\u0019A\u001b\u0002\u000bM$\u0018\r^3\u0016\t\u0005M\u0011QE\u000b\u0003\u0003+\u0011R!a\u0006 \u000331a!!\u0002\t\u0001\u0005U\u0001c\u0001\u0018\u0002\u001c%\u0019\u0011QD\f\u0003\u0015M#\u0018\r^3Sk2,7/B\u0004\u0002\"\u0005]\u0001!a\t\u0003\u0003M\u00032AMA\u0013\t\u0019\t9\u0003\u0003b\u0001k\t\t1/A\u0004tk\u000e\u001cWm]:\u0016\r\u00055\u00121GA\u001c)\u0019\ty#!\u000f\u0002>AAafL\u001d\u00022\u0005Ub\u0007E\u00023\u0003g!QAP\u0005C\u0002U\u00022AMA\u001c\t\u0015\t\u0015B1\u00016\u0011\u001d\tY$\u0003a\u0001\u0003c\t1a\\;u\u0011\u001d\ty$\u0003a\u0001\u0003k\t\u0011!Y\u0001\bM\u0006LG.\u001e:f+\t\t)\u0005\u0005\u0004/_e2dGN\u0001\u0006KJ\u0014xN]\u000b\u0005\u0003\u0017\n\t&\u0006\u0002\u0002NAAafLA(mY\ny\u0005E\u00023\u0003#\"Q\u0001N\u0006C\u0002U*B!!\u0016\u0002\\Q!\u0011qKA/!\u001dqs&\u000f\u001c7\u00033\u00022AMA.\t\u0015!EB1\u00016\u0011\u001d\ty\u0006\u0004a\u0001\u00033\n1!\u001a:s\u0003\u0015yg.Z(g+)\t)'a\u001b\u0002p\u0005M\u0014q\u000f\u000b\u0005\u0003O\nI\b\u0005\u0006/_\u0005%\u0014QNA9\u0003k\u00022AMA6\t\u0015!TB1\u00016!\r\u0011\u0014q\u000e\u0003\u0006}5\u0011\r!\u000e\t\u0004e\u0005MD!B!\u000e\u0005\u0004)\u0004c\u0001\u001a\u0002x\u0011)A)\u0004b\u0001k!9\u00111P\u0007A\u0002\u0005u\u0014!\u0002:vY\u0016\u001c\b#\u0002\u0011\u0002\u0000\u0005\u001d\u0014bAAAC\tQAH]3qK\u0006$X\r\u001a \u0002\u0019I,H.Z,ji\"t\u0015-\\3\u0016\u0015\u0005\u001d\u0015\u0011SAK\u00033\u000bi\n\u0006\u0004\u0002\n\u0006\u0015\u0016q\u0018\n\u0007\u0003\u0017\u000bi)a(\u0007\r\u0005\u0015\u0001\u0001AAE!)qs&a$\u0002\u0014\u0006]\u00151\u0014\t\u0004e\u0005EE!\u0002\u001b\u000f\u0005\u0004)\u0004c\u0001\u001a\u0002\u0016\u0012)aH\u0004b\u0001kA\u0019!'!'\u0005\u000b\u0005s!\u0019A\u001b\u0011\u0007I\ni\nB\u0003E\u001d\t\u0007Q\u0007E\u0002/\u0003CK1!a)\u0018\u0005\u0011q\u0015-\\3\t\u000f\u0005\u001df\u00021\u0001\u0002*\u0006)qL\\1nKB!\u00111VA]\u001d\u0011\ti+!.\u0011\u0007\u0005=\u0016%\u0004\u0002\u00022*\u0019\u00111W\u000f\u0002\rq\u0012xn\u001c;?\u0013\r\t9,I\u0001\u0007!J,G-\u001a4\n\t\u0005m\u0016Q\u0018\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005]\u0016\u0005\u0003\u0004G\u001d\u0001\u0007\u0011\u0011\u0019\t\u0007A!\u000by)a1\u0011\u00119Z\u00151SAL\u00037\u00131\u0002R3gCVdGOU;mKVQ\u0011\u0011ZAh\u0003'\f9.a7\u0014\t=y\u00121\u001a\t\u000b]=\ni-!5\u0002V\u0006e\u0007c\u0001\u001a\u0002P\u0012)Ag\u0004b\u0001kA\u0019!'a5\u0005\u000byz!\u0019A\u001b\u0011\u0007I\n9\u000eB\u0003B\u001f\t\u0007Q\u0007E\u00023\u00037$Q\u0001R\bC\u0002U\u0002b\u0001\t%\u0002N\u0006}\u0007\u0003\u0003\u0018L\u0003#\f).!7\u0002\rqJg.\u001b;?)\u0011\t)/a:\u0011\u0017\u0005%q\"!4\u0002R\u0006U\u0017\u0011\u001c\u0005\u0007\rF\u0001\r!!8\u0002\u000f\u0019\f7\r^8ssV\u0011\u0011Q\u001e\t\u0003]\u0001\t\u0001BZ1di>\u0014\u0018\u0010\t\u000b\u0005\u0003?\f\u0019\u0010C\u0004\u0002vR\u0001\r!!4\u0002\u0005%t\u0017AB3ya\u0016\u001cG/\u0006\u0006\u0002|\n\u0005!Q\u0002B\u0003\u0005#!B!!@\u0003\bA1\u0001\u0005SA\u0000\u0005\u0007\u00012A\rB\u0001\t\u0015!TC1\u00016!\r\u0011$Q\u0001\u0003\u0006\u0003V\u0011\r!\u000e\u0005\u0007UU\u0001\rA!\u0003\u0011\u00159z\u0013q B\u0006\u0005\u0007\u0011y\u0001E\u00023\u0005\u001b!QAP\u000bC\u0002U\u00022A\rB\t\t\u0015YTC1\u00016\u0001"
)
public interface Rules {
   // $FF: synthetic method
   static Rule rule$(final Rules $this, final Function1 f) {
      return $this.rule(f);
   }

   default Rule rule(final Function1 f) {
      return new DefaultRule(f);
   }

   // $FF: synthetic method
   static InRule inRule$(final Rules $this, final Rule rule) {
      return $this.inRule(rule);
   }

   default InRule inRule(final Rule rule) {
      return new InRule(rule);
   }

   // $FF: synthetic method
   static SeqRule seqRule$(final Rules $this, final Rule rule) {
      return $this.seqRule(rule);
   }

   default SeqRule seqRule(final Rule rule) {
      return new SeqRule(rule);
   }

   // $FF: synthetic method
   static FromRule from$(final Rules $this) {
      return $this.from();
   }

   default FromRule from() {
      return new FromRule() {
         // $FF: synthetic field
         private final Rules $outer;

         public Rule apply(final Function1 f) {
            return this.$outer.rule(f);
         }

         public {
            if (Rules.this == null) {
               throw null;
            } else {
               this.$outer = Rules.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static StateRules state$(final Rules $this) {
      return $this.state();
   }

   default StateRules state() {
      return new StateRules() {
         private final Rules factory;

         public Rule apply(final Function1 f) {
            return StateRules.apply$(this, f);
         }

         public Rule unit(final Function0 a) {
            return StateRules.unit$(this, a);
         }

         public Rule read(final Function1 f) {
            return StateRules.read$(this, f);
         }

         public Rule get() {
            return StateRules.get$(this);
         }

         public Rule set(final Function0 s) {
            return StateRules.set$(this, s);
         }

         public Rule update(final Function1 f) {
            return StateRules.update$(this, f);
         }

         public Rule nil() {
            return StateRules.nil$(this);
         }

         public Rule none() {
            return StateRules.none$(this);
         }

         public Rule cond(final Function1 f) {
            return StateRules.cond$(this, f);
         }

         public Function1 allOf(final Seq rules) {
            return StateRules.allOf$(this, rules);
         }

         public Rule anyOf(final Seq rules) {
            return StateRules.anyOf$(this, rules);
         }

         public Rule repeatUntil(final Rule rule, final Function1 finished, final Object initial) {
            return StateRules.repeatUntil$(this, rule, finished, initial);
         }

         public Rules factory() {
            return this.factory;
         }

         public {
            StateRules.$init$(this);
            this.factory = Rules.this;
         }
      };
   }

   // $FF: synthetic method
   static Rule success$(final Rules $this, final Object out, final Object a) {
      return $this.success(out, a);
   }

   default Rule success(final Object out, final Object a) {
      return this.rule((in) -> new Success(out, a));
   }

   // $FF: synthetic method
   static Rule failure$(final Rules $this) {
      return $this.failure();
   }

   default Rule failure() {
      return this.rule((in) -> Failure$.MODULE$);
   }

   // $FF: synthetic method
   static Rule error$(final Rules $this) {
      return $this.error();
   }

   default Rule error() {
      return this.rule((in) -> new Error(in));
   }

   // $FF: synthetic method
   static Rule error$(final Rules $this, final Object err) {
      return $this.error(err);
   }

   default Rule error(final Object err) {
      return this.rule((in) -> new Error(err));
   }

   // $FF: synthetic method
   static Rule oneOf$(final Rules $this, final Seq rules) {
      return $this.oneOf(rules);
   }

   default Rule oneOf(final Seq rules) {
      return new Choice(rules) {
         private final Rules factory;
         private final List choices;

         public Result apply(final Object in) {
            return Choice.apply$(this, in);
         }

         public Rule orElse(final Function0 other) {
            return Choice.orElse$(this, other);
         }

         public Rule as(final String name) {
            return Rule.as$(this, name);
         }

         public Rule flatMap(final Function1 fa2ruleb) {
            return Rule.flatMap$(this, fa2ruleb);
         }

         public Rule map(final Function1 fa2b) {
            return Rule.map$(this, fa2b);
         }

         public Rule filter(final Function1 f) {
            return Rule.filter$(this, f);
         }

         public Rule mapResult(final Function1 f) {
            return Rule.mapResult$(this, f);
         }

         public Rule orError() {
            return Rule.orError$(this);
         }

         public Rule $bar(final Function0 other) {
            return Rule.$bar$(this, other);
         }

         public Rule $up$up(final Function1 fa2b) {
            return Rule.$up$up$(this, fa2b);
         }

         public Rule $up$up$qmark(final PartialFunction pf) {
            return Rule.$up$up$qmark$(this, pf);
         }

         public Rule $qmark$qmark(final PartialFunction pf) {
            return Rule.$qmark$qmark$(this, pf);
         }

         public Rule $minus$up(final Object b) {
            return Rule.$minus$up$(this, b);
         }

         public Rule $bang$up(final Function1 fx2y) {
            return Rule.$bang$up$(this, fx2y);
         }

         public Rule $greater$greater(final Function1 fa2ruleb) {
            return Rule.$greater$greater$(this, fa2ruleb);
         }

         public Rule $greater$minus$greater(final Function1 fa2resultb) {
            return Rule.$greater$minus$greater$(this, fa2resultb);
         }

         public Rule $greater$greater$qmark(final PartialFunction pf) {
            return Rule.$greater$greater$qmark$(this, pf);
         }

         public Rule $greater$greater$amp(final Function1 fa2ruleb) {
            return Rule.$greater$greater$amp$(this, fa2ruleb);
         }

         public Rule $tilde(final Function0 next) {
            return Rule.$tilde$(this, next);
         }

         public Rule $tilde$minus(final Function0 next) {
            return Rule.$tilde$minus$(this, next);
         }

         public Rule $minus$tilde(final Function0 next) {
            return Rule.$minus$tilde$(this, next);
         }

         public Rule $tilde$plus$plus(final Function0 next) {
            return Rule.$tilde$plus$plus$(this, next);
         }

         public Rule $tilde$greater(final Function0 next) {
            return Rule.$tilde$greater$(this, next);
         }

         public Rule $less$tilde$colon(final Function0 prev) {
            return Rule.$less$tilde$colon$(this, prev);
         }

         public Rule $tilde$bang(final Function0 next) {
            return Rule.$tilde$bang$(this, next);
         }

         public Rule $tilde$minus$bang(final Function0 next) {
            return Rule.$tilde$minus$bang$(this, next);
         }

         public Rule $minus$tilde$bang(final Function0 next) {
            return Rule.$minus$tilde$bang$(this, next);
         }

         public Rule $minus(final Function0 exclude) {
            return Rule.$minus$(this, exclude);
         }

         public Rule $up$tilde$up(final Function2 f, final Function1 A) {
            return Rule.$up$tilde$up$(this, f, A);
         }

         public Rule $up$tilde$tilde$up(final Function3 f, final Function1 A) {
            return Rule.$up$tilde$tilde$up$(this, f, A);
         }

         public Rule $up$tilde$tilde$tilde$up(final Function4 f, final Function1 A) {
            return Rule.$up$tilde$tilde$tilde$up$(this, f, A);
         }

         public Rule $up$tilde$tilde$tilde$tilde$up(final Function5 f, final Function1 A) {
            return Rule.$up$tilde$tilde$tilde$tilde$up$(this, f, A);
         }

         public Rule $up$tilde$tilde$tilde$tilde$tilde$up(final Function6 f, final Function1 A) {
            return Rule.$up$tilde$tilde$tilde$tilde$tilde$up$(this, f, A);
         }

         public Rule $up$tilde$tilde$tilde$tilde$tilde$tilde$up(final Function7 f, final Function1 A) {
            return Rule.$up$tilde$tilde$tilde$tilde$tilde$tilde$up$(this, f, A);
         }

         public Rule $greater$tilde$greater(final Function2 f, final Function1 A) {
            return Rule.$greater$tilde$greater$(this, f, A);
         }

         public Rule $up$minus$up(final Function2 f) {
            return Rule.$up$minus$up$(this, f);
         }

         public Rule $up$tilde$greater$tilde$up(final Function3 f, final Function1 A) {
            return Rule.$up$tilde$greater$tilde$up$(this, f, A);
         }

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public Function1 andThen(final Function1 g) {
            return Function1.andThen$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         public Rules factory() {
            return this.factory;
         }

         public List choices() {
            return this.choices;
         }

         public {
            Function1.$init$(this);
            Rule.$init$(this);
            Choice.$init$(this);
            this.factory = Rules.this;
            this.choices = rules$1.toList();
         }
      };
   }

   // $FF: synthetic method
   static Rule ruleWithName$(final Rules $this, final String _name, final Function1 f) {
      return $this.ruleWithName(_name, f);
   }

   default Rule ruleWithName(final String _name, final Function1 f) {
      return new Name(f, _name) {
         private final String name;

         public String toString() {
            return Name.toString$(this);
         }

         public String name() {
            return this.name;
         }

         public {
            Name.$init$(this);
            this.name = _name$1;
         }
      };
   }

   // $FF: synthetic method
   static Function1 expect$(final Rules $this, final Rule rule) {
      return $this.expect(rule);
   }

   default Function1 expect(final Rule rule) {
      return (in) -> {
         Result var3 = (Result)rule.apply(in);
         if (var3 instanceof Success) {
            Success var4 = (Success)var3;
            Object a = var4.value();
            return a;
         } else if (Failure$.MODULE$.equals(var3)) {
            throw new ScalaSigParserError("Unexpected failure");
         } else if (var3 instanceof Error) {
            Error var6 = (Error)var3;
            Object x = var6.error();
            throw new ScalaSigParserError((new StringBuilder(18)).append("Unexpected error: ").append(x).toString());
         } else {
            throw new MatchError(var3);
         }
      };
   }

   static void $init$(final Rules $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class DefaultRule implements Rule {
      private final Function1 f;
      private final Rules factory;
      // $FF: synthetic field
      public final Rules $outer;

      public Rule as(final String name) {
         return Rule.as$(this, name);
      }

      public Rule flatMap(final Function1 fa2ruleb) {
         return Rule.flatMap$(this, fa2ruleb);
      }

      public Rule map(final Function1 fa2b) {
         return Rule.map$(this, fa2b);
      }

      public Rule filter(final Function1 f) {
         return Rule.filter$(this, f);
      }

      public Rule mapResult(final Function1 f) {
         return Rule.mapResult$(this, f);
      }

      public Rule orElse(final Function0 other) {
         return Rule.orElse$(this, other);
      }

      public Rule orError() {
         return Rule.orError$(this);
      }

      public Rule $bar(final Function0 other) {
         return Rule.$bar$(this, other);
      }

      public Rule $up$up(final Function1 fa2b) {
         return Rule.$up$up$(this, fa2b);
      }

      public Rule $up$up$qmark(final PartialFunction pf) {
         return Rule.$up$up$qmark$(this, pf);
      }

      public Rule $qmark$qmark(final PartialFunction pf) {
         return Rule.$qmark$qmark$(this, pf);
      }

      public Rule $minus$up(final Object b) {
         return Rule.$minus$up$(this, b);
      }

      public Rule $bang$up(final Function1 fx2y) {
         return Rule.$bang$up$(this, fx2y);
      }

      public Rule $greater$greater(final Function1 fa2ruleb) {
         return Rule.$greater$greater$(this, fa2ruleb);
      }

      public Rule $greater$minus$greater(final Function1 fa2resultb) {
         return Rule.$greater$minus$greater$(this, fa2resultb);
      }

      public Rule $greater$greater$qmark(final PartialFunction pf) {
         return Rule.$greater$greater$qmark$(this, pf);
      }

      public Rule $greater$greater$amp(final Function1 fa2ruleb) {
         return Rule.$greater$greater$amp$(this, fa2ruleb);
      }

      public Rule $tilde(final Function0 next) {
         return Rule.$tilde$(this, next);
      }

      public Rule $tilde$minus(final Function0 next) {
         return Rule.$tilde$minus$(this, next);
      }

      public Rule $minus$tilde(final Function0 next) {
         return Rule.$minus$tilde$(this, next);
      }

      public Rule $tilde$plus$plus(final Function0 next) {
         return Rule.$tilde$plus$plus$(this, next);
      }

      public Rule $tilde$greater(final Function0 next) {
         return Rule.$tilde$greater$(this, next);
      }

      public Rule $less$tilde$colon(final Function0 prev) {
         return Rule.$less$tilde$colon$(this, prev);
      }

      public Rule $tilde$bang(final Function0 next) {
         return Rule.$tilde$bang$(this, next);
      }

      public Rule $tilde$minus$bang(final Function0 next) {
         return Rule.$tilde$minus$bang$(this, next);
      }

      public Rule $minus$tilde$bang(final Function0 next) {
         return Rule.$minus$tilde$bang$(this, next);
      }

      public Rule $minus(final Function0 exclude) {
         return Rule.$minus$(this, exclude);
      }

      public Rule $up$tilde$up(final Function2 f, final Function1 A) {
         return Rule.$up$tilde$up$(this, f, A);
      }

      public Rule $up$tilde$tilde$up(final Function3 f, final Function1 A) {
         return Rule.$up$tilde$tilde$up$(this, f, A);
      }

      public Rule $up$tilde$tilde$tilde$up(final Function4 f, final Function1 A) {
         return Rule.$up$tilde$tilde$tilde$up$(this, f, A);
      }

      public Rule $up$tilde$tilde$tilde$tilde$up(final Function5 f, final Function1 A) {
         return Rule.$up$tilde$tilde$tilde$tilde$up$(this, f, A);
      }

      public Rule $up$tilde$tilde$tilde$tilde$tilde$up(final Function6 f, final Function1 A) {
         return Rule.$up$tilde$tilde$tilde$tilde$tilde$up$(this, f, A);
      }

      public Rule $up$tilde$tilde$tilde$tilde$tilde$tilde$up(final Function7 f, final Function1 A) {
         return Rule.$up$tilde$tilde$tilde$tilde$tilde$tilde$up$(this, f, A);
      }

      public Rule $greater$tilde$greater(final Function2 f, final Function1 A) {
         return Rule.$greater$tilde$greater$(this, f, A);
      }

      public Rule $up$minus$up(final Function2 f) {
         return Rule.$up$minus$up$(this, f);
      }

      public Rule $up$tilde$greater$tilde$up(final Function3 f, final Function1 A) {
         return Rule.$up$tilde$greater$tilde$up$(this, f, A);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      public Rules factory() {
         return this.factory;
      }

      public Result apply(final Object in) {
         return (Result)this.f.apply(in);
      }

      // $FF: synthetic method
      public Rules org$json4s$scalap$Rules$DefaultRule$$$outer() {
         return this.$outer;
      }

      public DefaultRule(final Function1 f) {
         this.f = f;
         if (Rules.this == null) {
            throw null;
         } else {
            this.$outer = Rules.this;
            super();
            Function1.$init$(this);
            Rule.$init$(this);
            this.factory = Rules.this;
         }
      }
   }

   public interface FromRule {
      Rule apply(final Function1 f);
   }
}
