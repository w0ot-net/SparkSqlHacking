package org.json4s.scalap.scalasig;

import java.lang.invoke.SerializedLambda;
import org.json4s.scalap.InRule;
import org.json4s.scalap.MemoisableRules;
import org.json4s.scalap.Result;
import org.json4s.scalap.Rule;
import org.json4s.scalap.Rules;
import org.json4s.scalap.RulesWithState;
import org.json4s.scalap.SeqRule;
import org.json4s.scalap.StateRules;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class ScalaSigEntryParsers$ implements RulesWithState, MemoisableRules {
   public static final ScalaSigEntryParsers$ MODULE$ = new ScalaSigEntryParsers$();
   private static Rule entry;
   private static Rule nameRef;
   private static Rule symbolRef;
   private static Rule typeRef;
   private static Rule constantRef;
   private static Rule symbol;
   private static Rule typeEntry;
   private static Rule literal;
   private static Rule attributeInfo;
   private static Rule children;
   private static Rule annotInfo;
   private static Rule topLevelClass;
   private static Rule topLevelObject;
   private static final Rule index;
   private static final Rule key;
   private static final Rule ref;
   private static final Rule termName;
   private static final Rule typeName;
   private static final Rule name;
   private static final Rule symbolInfo;
   private static final Rule noSymbol;
   private static final Rule typeSymbol;
   private static final Rule aliasSymbol;
   private static final Rule classSymbol;
   private static final Rule objectSymbol;
   private static final Rule methodSymbol;
   private static final Rule extRef;
   private static final Rule extModClassRef;
   private static final Rule classSymRef;
   private static final Rule attribTreeRef;
   private static final Rule typeLevel;
   private static final Rule typeIndex;
   private static RulesWithState factory;
   private static volatile int bitmap$0;

   static {
      Rules.$init$(MODULE$);
      StateRules.$init$(MODULE$);
      RulesWithState.$init$(MODULE$);
      MemoisableRules.$init$(MODULE$);
      index = MODULE$.read((x$10) -> BoxesRunTime.boxToInteger($anonfun$index$1(x$10)));
      key = MODULE$.read((x$11) -> BoxesRunTime.boxToInteger($anonfun$key$1(x$11)));
      ref = MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.nat());
      termName = MODULE$.entryType(1).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.utf8()));
      typeName = MODULE$.entryType(2).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.utf8()));
      name = MODULE$.termName().$bar(() -> MODULE$.typeName()).as("name");
      symbolInfo = MODULE$.nameRef().$tilde(() -> MODULE$.symbolRef()).$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.nat())).$tilde(() -> MODULE$.seqRule(MODULE$.symbolRef()).$qmark()).$tilde(() -> MODULE$.ref()).$tilde(() -> MODULE$.get()).$up$tilde$tilde$tilde$tilde$tilde$up(SymbolInfo$.MODULE$, .MODULE$.$conforms());
      noSymbol = MODULE$.entryType(3).$minus$up(NoSymbol$.MODULE$);
      typeSymbol = MODULE$.symbolEntry(4).$up$up(TypeSymbol$.MODULE$).as("typeSymbol");
      aliasSymbol = MODULE$.symbolEntry(5).$up$up(AliasSymbol$.MODULE$).as("alias");
      classSymbol = MODULE$.symbolEntry(6).$tilde(() -> MODULE$.seqRule(MODULE$.ref()).$qmark()).$up$tilde$up(ClassSymbol$.MODULE$, .MODULE$.$conforms()).as("class");
      objectSymbol = MODULE$.symbolEntry(7).$up$up(ObjectSymbol$.MODULE$).as("object");
      methodSymbol = MODULE$.symHeader(8).$minus$tilde(() -> MODULE$.symbolInfo().$tilde(() -> MODULE$.seqRule(MODULE$.ref()).$qmark())).$up$tilde$up(MethodSymbol$.MODULE$, .MODULE$.$conforms()).as("method");
      extRef = MODULE$.entryType(9).$minus$tilde(() -> MODULE$.nameRef().$tilde(() -> MODULE$.seqRule(MODULE$.symbolRef()).$qmark()).$tilde(() -> MODULE$.get())).$up$tilde$tilde$up(ExternalSymbol$.MODULE$, .MODULE$.$conforms()).as("extRef");
      extModClassRef = MODULE$.entryType(10).$minus$tilde(() -> MODULE$.nameRef().$tilde(() -> MODULE$.seqRule(MODULE$.symbolRef()).$qmark()).$tilde(() -> MODULE$.get())).$up$tilde$tilde$up(ExternalSymbol$.MODULE$, .MODULE$.$conforms()).as("extModClassRef");
      classSymRef = MODULE$.refTo(MODULE$.classSymbol());
      attribTreeRef = MODULE$.ref();
      typeLevel = ScalaSigAttributeParsers$.MODULE$.nat();
      typeIndex = ScalaSigAttributeParsers$.MODULE$.nat();
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

   public Rule byteCodeEntryParser(final Rule rule) {
      return this.apply((entry) -> ((Result)rule.apply(entry.byteCode())).mapOut((x$8) -> entry.setByteCode(x$8)));
   }

   public Rule toEntry(final int index) {
      return this.apply((sigEntry) -> (Result)ScalaSigParsers$.MODULE$.entry(index).apply(sigEntry.scalaSig()));
   }

   public Rule parseEntry(final Rule parser, final int index) {
      return this.toEntry(index).$minus$tilde(() -> parser);
   }

   public Rule entryType(final int code) {
      return this.key().filter((JFunction1.mcZI.sp)(x$9) -> x$9 == code);
   }

   public Rule index() {
      return index;
   }

   public Rule key() {
      return key;
   }

   private Rule entry$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 1) == 0) {
            entry = this.symbol().$bar(() -> MODULE$.typeEntry()).$bar(() -> MODULE$.literal()).$bar(() -> MODULE$.name()).$bar(() -> MODULE$.attributeInfo()).$bar(() -> MODULE$.annotInfo()).$bar(() -> MODULE$.children()).$bar(() -> MODULE$.get());
            bitmap$0 |= 1;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return entry;
   }

   public Rule entry() {
      return (bitmap$0 & 1) == 0 ? this.entry$lzycompute() : entry;
   }

   public Rule ref() {
      return ref;
   }

   public Rule termName() {
      return termName;
   }

   public Rule typeName() {
      return typeName;
   }

   public Rule name() {
      return name;
   }

   public Rule refTo(final Rule rule) {
      return this.ref().$greater$greater$amp((index) -> $anonfun$refTo$1(rule, BoxesRunTime.unboxToInt(index)));
   }

   private Rule nameRef$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 2) == 0) {
            nameRef = this.refTo(this.name());
            bitmap$0 |= 2;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return nameRef;
   }

   public Rule nameRef() {
      return (bitmap$0 & 2) == 0 ? this.nameRef$lzycompute() : nameRef;
   }

   private Rule symbolRef$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 4) == 0) {
            symbolRef = this.refTo(this.symbol());
            bitmap$0 |= 4;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return symbolRef;
   }

   public Rule symbolRef() {
      return (bitmap$0 & 4) == 0 ? this.symbolRef$lzycompute() : symbolRef;
   }

   private Rule typeRef$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 8) == 0) {
            typeRef = this.refTo(this.typeEntry());
            bitmap$0 |= 8;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return typeRef;
   }

   public Rule typeRef() {
      return (bitmap$0 & 8) == 0 ? this.typeRef$lzycompute() : typeRef;
   }

   private Rule constantRef$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 16) == 0) {
            constantRef = this.refTo(this.literal());
            bitmap$0 |= 16;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return constantRef;
   }

   public Rule constantRef() {
      return (bitmap$0 & 16) == 0 ? this.constantRef$lzycompute() : constantRef;
   }

   public Rule symbolInfo() {
      return symbolInfo;
   }

   public Rule symHeader(final int key) {
      return this.entryType(key).$minus$tilde(() -> MODULE$.none()).$bar(() -> MODULE$.entryType(key + 64).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.nat())));
   }

   public Rule symbolEntry(final int key) {
      return this.symHeader(key).$minus$tilde(() -> MODULE$.symbolInfo());
   }

   public Rule noSymbol() {
      return noSymbol;
   }

   public Rule typeSymbol() {
      return typeSymbol;
   }

   public Rule aliasSymbol() {
      return aliasSymbol;
   }

   public Rule classSymbol() {
      return classSymbol;
   }

   public Rule objectSymbol() {
      return objectSymbol;
   }

   public Rule methodSymbol() {
      return methodSymbol;
   }

   public Rule extRef() {
      return extRef;
   }

   public Rule extModClassRef() {
      return extModClassRef;
   }

   private Rule symbol$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 32) == 0) {
            symbol = this.oneOf(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Rule[]{this.noSymbol(), this.typeSymbol(), this.aliasSymbol(), this.classSymbol(), this.objectSymbol(), this.methodSymbol(), this.extRef(), this.extModClassRef()})).as("symbol");
            bitmap$0 |= 32;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return symbol;
   }

   public Rule symbol() {
      return (bitmap$0 & 32) == 0 ? this.symbol$lzycompute() : symbol;
   }

   public Rule classSymRef() {
      return classSymRef;
   }

   public Rule attribTreeRef() {
      return attribTreeRef;
   }

   public Rule typeLevel() {
      return typeLevel;
   }

   public Rule typeIndex() {
      return typeIndex;
   }

   private Rule typeEntry$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 64) == 0) {
            typeEntry = this.oneOf(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Rule[]{this.entryType(11).$minus$up(NoType$.MODULE$), this.entryType(12).$minus$up(NoPrefixType$.MODULE$), this.entryType(13).$minus$tilde(() -> MODULE$.symbolRef()).$up$up(ThisType$.MODULE$), this.entryType(14).$minus$tilde(() -> MODULE$.typeRef().$tilde(() -> MODULE$.symbolRef())).$up$tilde$up(SingleType$.MODULE$, .MODULE$.$conforms()), this.entryType(15).$minus$tilde(() -> MODULE$.constantRef()).$up$up(ConstantType$.MODULE$), this.entryType(16).$minus$tilde(() -> MODULE$.typeRef().$tilde(() -> MODULE$.symbolRef()).$tilde(() -> MODULE$.seqRule(MODULE$.typeRef()).$times())).$up$tilde$tilde$up(TypeRefType$.MODULE$, .MODULE$.$conforms()), this.entryType(17).$minus$tilde(() -> MODULE$.typeRef().$tilde(() -> MODULE$.typeRef())).$up$tilde$up(TypeBoundsType$.MODULE$, .MODULE$.$conforms()), this.entryType(18).$minus$tilde(() -> MODULE$.classSymRef().$tilde(() -> MODULE$.seqRule(MODULE$.typeRef()).$times())).$up$tilde$up(RefinedType$.MODULE$, .MODULE$.$conforms()), this.entryType(19).$minus$tilde(() -> MODULE$.symbolRef().$tilde(() -> MODULE$.seqRule(MODULE$.typeRef()).$times())).$up$tilde$up(ClassInfoType$.MODULE$, .MODULE$.$conforms()), this.entryType(20).$minus$tilde(() -> MODULE$.typeRef().$tilde(() -> MODULE$.seqRule(MODULE$.symbolRef()).$times())).$up$tilde$up(MethodType$.MODULE$, .MODULE$.$conforms()), this.entryType(21).$minus$tilde(() -> MODULE$.typeRef().$tilde(() -> MODULE$.seqRule(MODULE$.refTo(MODULE$.typeSymbol())).$plus())).$up$tilde$up(PolyType$.MODULE$, .MODULE$.$conforms()), this.entryType(21).$minus$tilde(() -> MODULE$.typeRef()).$up$up(NullaryMethodType$.MODULE$), this.entryType(22).$minus$tilde(() -> MODULE$.typeRef().$tilde(() -> MODULE$.seqRule(MODULE$.symbolRef()).$times())).$up$tilde$up(MethodType$.MODULE$, .MODULE$.$conforms()), this.entryType(42).$minus$tilde(() -> MODULE$.typeRef().$tilde(() -> MODULE$.seqRule(MODULE$.attribTreeRef()).$times())).$up$tilde$up(AnnotatedType$.MODULE$, .MODULE$.$conforms()), this.entryType(51).$minus$tilde(() -> MODULE$.typeRef().$tilde(() -> MODULE$.symbolRef()).$tilde(() -> MODULE$.seqRule(MODULE$.attribTreeRef()).$times())).$up$tilde$tilde$up(AnnotatedWithSelfType$.MODULE$, .MODULE$.$conforms()), this.entryType(48).$minus$tilde(() -> MODULE$.typeRef().$tilde(() -> MODULE$.seqRule(MODULE$.symbolRef()).$times())).$up$tilde$up(ExistentialType$.MODULE$, .MODULE$.$conforms())})).as("type");
            bitmap$0 |= 64;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return typeEntry;
   }

   public Rule typeEntry() {
      return (bitmap$0 & 64) == 0 ? this.typeEntry$lzycompute() : typeEntry;
   }

   private Rule literal$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 128) == 0) {
            literal = this.oneOf(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Rule[]{this.entryType(24).$minus$up(BoxedUnit.UNIT), this.entryType(25).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.longValue())).$up$up((JFunction1.mcZJ.sp)(x$12) -> x$12 != 0L), this.entryType(26).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.longValue())).$up$up((x$13) -> BoxesRunTime.boxToByte($anonfun$literal$4(BoxesRunTime.unboxToLong(x$13)))), this.entryType(27).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.longValue())).$up$up((x$14) -> BoxesRunTime.boxToShort($anonfun$literal$6(BoxesRunTime.unboxToLong(x$14)))), this.entryType(28).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.longValue())).$up$up((x$15) -> BoxesRunTime.boxToCharacter($anonfun$literal$8(BoxesRunTime.unboxToLong(x$15)))), this.entryType(29).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.longValue())).$up$up((JFunction1.mcIJ.sp)(x$16) -> (int)x$16), this.entryType(30).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.longValue())).$up$up((JFunction1.mcJJ.sp)(x$17) -> x$17), this.entryType(31).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.longValue())).$up$up((JFunction1.mcFJ.sp)(l) -> Float.intBitsToFloat((int)l)), this.entryType(32).$minus$tilde(() -> MODULE$.byteCodeEntryParser(ScalaSigAttributeParsers$.MODULE$.longValue())).$up$up((JFunction1.mcDJ.sp)(x$1) -> Double.longBitsToDouble(x$1)), this.entryType(33).$minus$tilde(() -> MODULE$.nameRef()), this.entryType(34).$minus$up((Object)null), this.entryType(35).$minus$tilde(() -> MODULE$.typeRef()), this.entryType(36).$minus$tilde(() -> MODULE$.symbolRef())}));
            bitmap$0 |= 128;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return literal;
   }

   public Rule literal() {
      return (bitmap$0 & 128) == 0 ? this.literal$lzycompute() : literal;
   }

   private Rule attributeInfo$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 256) == 0) {
            attributeInfo = this.entryType(40).$minus$tilde(() -> MODULE$.symbolRef().$tilde(() -> MODULE$.typeRef()).$tilde(() -> MODULE$.seqRule(MODULE$.constantRef()).$qmark()).$tilde(() -> MODULE$.seqRule(MODULE$.nameRef().$tilde(() -> MODULE$.constantRef())).$times())).$up$tilde$tilde$tilde$up(AttributeInfo$.MODULE$, .MODULE$.$conforms());
            bitmap$0 |= 256;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return attributeInfo;
   }

   public Rule attributeInfo() {
      return (bitmap$0 & 256) == 0 ? this.attributeInfo$lzycompute() : attributeInfo;
   }

   private Rule children$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 512) == 0) {
            children = this.entryType(41).$minus$tilde(() -> MODULE$.byteCodeEntryParser(MODULE$.seqRule(ScalaSigAttributeParsers$.MODULE$.nat()).$times())).$up$up(Children$.MODULE$);
            bitmap$0 |= 512;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return children;
   }

   public Rule children() {
      return (bitmap$0 & 512) == 0 ? this.children$lzycompute() : children;
   }

   private Rule annotInfo$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 1024) == 0) {
            annotInfo = this.entryType(43).$minus$tilde(() -> MODULE$.byteCodeEntryParser(MODULE$.seqRule(ScalaSigAttributeParsers$.MODULE$.nat()).$times())).$up$up(AnnotInfo$.MODULE$);
            bitmap$0 |= 1024;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return annotInfo;
   }

   public Rule annotInfo() {
      return (bitmap$0 & 1024) == 0 ? this.annotInfo$lzycompute() : annotInfo;
   }

   private Rule topLevelClass$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 2048) == 0) {
            topLevelClass = this.classSymbol().filter((symbol) -> BoxesRunTime.boxToBoolean($anonfun$topLevelClass$1(symbol)));
            bitmap$0 |= 2048;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return topLevelClass;
   }

   public Rule topLevelClass() {
      return (bitmap$0 & 2048) == 0 ? this.topLevelClass$lzycompute() : topLevelClass;
   }

   private Rule topLevelObject$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 4096) == 0) {
            topLevelObject = this.objectSymbol().filter((symbol) -> BoxesRunTime.boxToBoolean($anonfun$topLevelObject$1(symbol)));
            bitmap$0 |= 4096;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return topLevelObject;
   }

   public Rule topLevelObject() {
      return (bitmap$0 & 4096) == 0 ? this.topLevelObject$lzycompute() : topLevelObject;
   }

   public boolean isTopLevel(final Symbol symbol) {
      Option var3 = symbol.parent();
      boolean var2;
      if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         if (var4.value() instanceof ExternalSymbol) {
            var2 = true;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   public boolean isTopLevelClass(final Symbol symbol) {
      return !symbol.isModule() && this.isTopLevel(symbol);
   }

   // $FF: synthetic method
   public static final int $anonfun$index$1(final ScalaSig.Entry x$10) {
      return x$10.index();
   }

   // $FF: synthetic method
   public static final int $anonfun$key$1(final ScalaSig.Entry x$11) {
      return x$11.entryType();
   }

   // $FF: synthetic method
   public static final Rule $anonfun$refTo$1(final Rule rule$2, final int index) {
      return MODULE$.parseEntry(rule$2, index);
   }

   // $FF: synthetic method
   public static final byte $anonfun$literal$4(final long x$13) {
      return (byte)((int)x$13);
   }

   // $FF: synthetic method
   public static final short $anonfun$literal$6(final long x$14) {
      return (short)((int)x$14);
   }

   // $FF: synthetic method
   public static final char $anonfun$literal$8(final long x$15) {
      return (char)((int)x$15);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$topLevelClass$1(final Symbol symbol) {
      return MODULE$.isTopLevelClass(symbol);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$topLevelObject$1(final Symbol symbol) {
      return MODULE$.isTopLevel(symbol);
   }

   private ScalaSigEntryParsers$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
