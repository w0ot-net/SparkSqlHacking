package org.json4s.scalap.scalasig;

import java.lang.invoke.SerializedLambda;
import org.json4s.scalap.Failure$;
import org.json4s.scalap.InRule;
import org.json4s.scalap.Result;
import org.json4s.scalap.Rule;
import org.json4s.scalap.Rules;
import org.json4s.scalap.RulesWithState;
import org.json4s.scalap.SeqRule;
import org.json4s.scalap.StateRules;
import org.json4s.scalap.Success;
import scala.Function0;
import scala.Function1;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class ScalaSigAttributeParsers$ implements ByteCodeReader {
   public static final ScalaSigAttributeParsers$ MODULE$ = new ScalaSigAttributeParsers$();
   private static final Rule nat;
   private static final Rule rawBytes;
   private static final Rule entry;
   private static final Rule symtab;
   private static final Rule scalaSig;
   private static final Rule utf8;
   private static final Rule longValue;
   private static Rule byte;
   private static Rule u1;
   private static Rule u2;
   private static Rule u4;
   private static RulesWithState factory;

   static {
      Rules.$init$(MODULE$);
      StateRules.$init$(MODULE$);
      RulesWithState.$init$(MODULE$);
      ByteCodeReader.$init$(MODULE$);
      nat = MODULE$.apply((in) -> $this.natN$1(in, 0));
      rawBytes = MODULE$.nat().$greater$greater((n) -> $anonfun$rawBytes$1(BoxesRunTime.unboxToInt(n)));
      entry = MODULE$.nat().$tilde(() -> MODULE$.rawBytes());
      Rule var10000 = MODULE$.nat();
      SeqRule var0 = MODULE$.seqRule(MODULE$.entry());
      symtab = var10000.$greater$greater((num) -> $anonfun$symtab$1(var0, BoxesRunTime.unboxToInt(num)));
      scalaSig = MODULE$.nat().$tilde(() -> MODULE$.nat()).$tilde(() -> MODULE$.symtab()).$up$tilde$tilde$up(ScalaSig$.MODULE$, .MODULE$.$conforms());
      utf8 = MODULE$.read((x) -> x.fromUTF8StringAndBytes().string());
      longValue = MODULE$.read((x$2) -> BoxesRunTime.boxToLong($anonfun$longValue$1(x$2)));
   }

   public Rule bytes(final int n) {
      return ByteCodeReader.bytes$(this, n);
   }

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

   public Rule rule(final Function1 f) {
      return Rules.rule$(this, f);
   }

   public InRule inRule(final Rule rule) {
      return Rules.inRule$(this, rule);
   }

   public SeqRule seqRule(final Rule rule) {
      return Rules.seqRule$(this, rule);
   }

   public Rules.FromRule from() {
      return Rules.from$(this);
   }

   public StateRules state() {
      return Rules.state$(this);
   }

   public Rule success(final Object out, final Object a) {
      return Rules.success$(this, out, a);
   }

   public Rule failure() {
      return Rules.failure$(this);
   }

   public Rule error() {
      return Rules.error$(this);
   }

   public Rule error(final Object err) {
      return Rules.error$(this, err);
   }

   public Rule oneOf(final Seq rules) {
      return Rules.oneOf$(this, rules);
   }

   public Rule ruleWithName(final String _name, final Function1 f) {
      return Rules.ruleWithName$(this, _name, f);
   }

   public Function1 expect(final Rule rule) {
      return Rules.expect$(this, rule);
   }

   public Rule byte() {
      return byte;
   }

   public Rule u1() {
      return u1;
   }

   public Rule u2() {
      return u2;
   }

   public Rule u4() {
      return u4;
   }

   public void org$json4s$scalap$scalasig$ByteCodeReader$_setter_$byte_$eq(final Rule x$1) {
      byte = x$1;
   }

   public void org$json4s$scalap$scalasig$ByteCodeReader$_setter_$u1_$eq(final Rule x$1) {
      u1 = x$1;
   }

   public void org$json4s$scalap$scalasig$ByteCodeReader$_setter_$u2_$eq(final Rule x$1) {
      u2 = x$1;
   }

   public void org$json4s$scalap$scalasig$ByteCodeReader$_setter_$u4_$eq(final Rule x$1) {
      u4 = x$1;
   }

   public RulesWithState factory() {
      return factory;
   }

   public void org$json4s$scalap$RulesWithState$_setter_$factory_$eq(final RulesWithState x$1) {
      factory = x$1;
   }

   public ScalaSig parse(final ByteCode byteCode) {
      return (ScalaSig)this.expect(this.scalaSig()).apply(byteCode);
   }

   public Rule nat() {
      return nat;
   }

   public Rule rawBytes() {
      return rawBytes;
   }

   public Rule entry() {
      return entry;
   }

   public Rule symtab() {
      return symtab;
   }

   public Rule scalaSig() {
      return scalaSig;
   }

   public Rule utf8() {
      return utf8;
   }

   public Rule longValue() {
      return longValue;
   }

   private final Result natN$1(final ByteCode in, final int x) {
      while(true) {
         Result var5 = in.nextByte();
         Object var4;
         if (var5 instanceof Success) {
            Success var6 = (Success)var5;
            ByteCode out = (ByteCode)var6.out();
            byte b = BoxesRunTime.unboxToByte(var6.value());
            int y = (x << 7) + (b & 127);
            if ((b & 128) != 0) {
               x = y;
               in = out;
               continue;
            }

            var4 = new Success(out, BoxesRunTime.boxToInteger(y));
         } else {
            var4 = Failure$.MODULE$;
         }

         return (Result)var4;
      }
   }

   // $FF: synthetic method
   public static final Rule $anonfun$rawBytes$1(final int n) {
      return MODULE$.bytes(n);
   }

   // $FF: synthetic method
   public static final Rule $anonfun$symtab$1(final SeqRule eta$0$1$1, final int num) {
      return eta$0$1$1.times(num);
   }

   // $FF: synthetic method
   public static final long $anonfun$longValue$1(final ByteCode x$2) {
      return x$2.toLong();
   }

   private ScalaSigAttributeParsers$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
