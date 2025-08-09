package org.json4s.scalap.scalasig;

import java.lang.invoke.SerializedLambda;
import org.json4s.scalap.InRule;
import org.json4s.scalap.MemoisableRules;
import org.json4s.scalap.Rule;
import org.json4s.scalap.Rules;
import org.json4s.scalap.RulesWithState;
import org.json4s.scalap.SeqRule;
import org.json4s.scalap.StateRules;
import org.json4s.scalap.Success;
import scala.Function0;
import scala.Function1;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;

public final class ScalaSigParsers$ implements RulesWithState, MemoisableRules {
   public static final ScalaSigParsers$ MODULE$ = new ScalaSigParsers$();
   private static Rule entries;
   private static Rule symbols;
   private static Rule methods;
   private static Rule attributes;
   private static Rule topLevelClasses;
   private static Rule topLevelObjects;
   private static final Rule symTab;
   private static final Rule size;
   private static RulesWithState factory;
   private static volatile byte bitmap$0;

   static {
      Rules.$init$(MODULE$);
      StateRules.$init$(MODULE$);
      RulesWithState.$init$(MODULE$);
      MemoisableRules.$init$(MODULE$);
      symTab = MODULE$.read((x$4) -> x$4.table());
      size = MODULE$.symTab().$up$up((x$5) -> BoxesRunTime.boxToInteger($anonfun$size$1(x$5)));
   }

   // $FF: synthetic method
   public Rule org$json4s$scalap$MemoisableRules$$super$ruleWithName(final String _name, final Function1 f) {
      return Rules.ruleWithName$(this, _name, f);
   }

   public Rule memo(final Object key, final Function0 toRule) {
      return MemoisableRules.memo$(this, key, toRule);
   }

   public Rule ruleWithName(final String name, final Function1 f) {
      return MemoisableRules.ruleWithName$(this, name, f);
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

   public Function1 expect(final Rule rule) {
      return Rules.expect$(this, rule);
   }

   public RulesWithState factory() {
      return factory;
   }

   public void org$json4s$scalap$RulesWithState$_setter_$factory_$eq(final RulesWithState x$1) {
      factory = x$1;
   }

   public Rule symTab() {
      return symTab;
   }

   public Rule size() {
      return size;
   }

   public Rule entry(final int index) {
      return this.memo(new Tuple2("entry", BoxesRunTime.boxToInteger(index)), () -> MODULE$.cond((x$6) -> BoxesRunTime.boxToBoolean($anonfun$entry$3(index, x$6))).$minus$tilde(() -> MODULE$.read((x$7) -> x$7.getEntry(index))).$greater$minus$greater((entry) -> new Success(entry, BoxesRunTime.boxToInteger(entry.entryType()))));
   }

   public Rule parseEntry(final Rule parser, final int index) {
      return this.entry(index).$minus$tilde(() -> parser).$greater$greater((a) -> (entry) -> new Success(entry.scalaSig(), a));
   }

   public Rule allEntries(final Rule f) {
      return this.size().$greater$greater((n) -> $anonfun$allEntries$1(f, BoxesRunTime.unboxToInt(n)));
   }

   private Rule entries$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            entries = this.allEntries(ScalaSigEntryParsers$.MODULE$.entry()).as("entries");
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return entries;
   }

   public Rule entries() {
      return (byte)(bitmap$0 & 1) == 0 ? this.entries$lzycompute() : entries;
   }

   private Rule symbols$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            symbols = this.allEntries(ScalaSigEntryParsers$.MODULE$.symbol()).as("symbols");
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return symbols;
   }

   public Rule symbols() {
      return (byte)(bitmap$0 & 2) == 0 ? this.symbols$lzycompute() : symbols;
   }

   private Rule methods$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 4) == 0) {
            methods = this.allEntries(ScalaSigEntryParsers$.MODULE$.methodSymbol()).as("methods");
            bitmap$0 = (byte)(bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return methods;
   }

   public Rule methods() {
      return (byte)(bitmap$0 & 4) == 0 ? this.methods$lzycompute() : methods;
   }

   private Rule attributes$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 8) == 0) {
            attributes = this.allEntries(ScalaSigEntryParsers$.MODULE$.attributeInfo()).as("attributes");
            bitmap$0 = (byte)(bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return attributes;
   }

   public Rule attributes() {
      return (byte)(bitmap$0 & 8) == 0 ? this.attributes$lzycompute() : attributes;
   }

   private Rule topLevelClasses$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 16) == 0) {
            topLevelClasses = this.allEntries(ScalaSigEntryParsers$.MODULE$.topLevelClass());
            bitmap$0 = (byte)(bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return topLevelClasses;
   }

   public Rule topLevelClasses() {
      return (byte)(bitmap$0 & 16) == 0 ? this.topLevelClasses$lzycompute() : topLevelClasses;
   }

   private Rule topLevelObjects$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 32) == 0) {
            topLevelObjects = this.allEntries(ScalaSigEntryParsers$.MODULE$.topLevelObject());
            bitmap$0 = (byte)(bitmap$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return topLevelObjects;
   }

   public Rule topLevelObjects() {
      return (byte)(bitmap$0 & 32) == 0 ? this.topLevelObjects$lzycompute() : topLevelObjects;
   }

   // $FF: synthetic method
   public static final int $anonfun$size$1(final Seq x$5) {
      return x$5.size();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$entry$3(final int index$1, final ScalaSig x$6) {
      return x$6.hasEntry(index$1);
   }

   // $FF: synthetic method
   public static final Rule $anonfun$allEntries$2(final Rule f$1, final int index) {
      return MODULE$.parseEntry(f$1, index);
   }

   // $FF: synthetic method
   public static final Rule $anonfun$allEntries$1(final Rule f$1, final int n) {
      return MODULE$.anyOf(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n).map((index) -> $anonfun$allEntries$2(f$1, BoxesRunTime.unboxToInt(index))));
   }

   private ScalaSigParsers$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
