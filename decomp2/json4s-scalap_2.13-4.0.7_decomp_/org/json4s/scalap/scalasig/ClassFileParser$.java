package org.json4s.scalap.scalasig;

import java.lang.invoke.SerializedLambda;
import org.json4s.scalap.InRule;
import org.json4s.scalap.Rule;
import org.json4s.scalap.Rules;
import org.json4s.scalap.RulesWithState;
import org.json4s.scalap.SeqRule;
import org.json4s.scalap.StateRules;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class ClassFileParser$ implements ByteCodeReader {
   public static final ClassFileParser$ MODULE$ = new ClassFileParser$();
   private static final Rule magicNumber;
   private static final Rule version;
   private static final Rule constantPool;
   private static final Rule utf8String;
   private static final Rule intConstant;
   private static final Rule floatConstant;
   private static final Rule longConstant;
   private static final Rule doubleConstant;
   private static final Rule classRef;
   private static final Rule stringRef;
   private static final Rule fieldRef;
   private static final Rule methodRef;
   private static final Rule interfaceMethodRef;
   private static final Rule nameAndType;
   private static final Rule methodHandle;
   private static final Rule methodType;
   private static final Rule invokeDynamic;
   private static final Rule constantModule;
   private static final Rule constantPackage;
   private static final Rule constantPoolEntry;
   private static final Rule interfaces;
   private static final Rule attribute;
   private static final Rule attributes;
   private static final Rule element_value_pair;
   private static final Rule annotation;
   private static final Rule annotations;
   private static final Rule field;
   private static final Rule fields;
   private static final Rule method;
   private static final Rule methods;
   private static final Rule header;
   private static final Rule classFile;
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
      magicNumber = MODULE$.u4().filter((JFunction1.mcZI.sp)(x$6) -> x$6 == -889275714).$bar(() -> MODULE$.error("Not a valid class file"));
      version = MODULE$.u2().$tilde(() -> MODULE$.u2()).$up$up((x0$8) -> {
         if (x0$8 != null) {
            int minor = BoxesRunTime.unboxToInt(x0$8._1());
            int major = BoxesRunTime.unboxToInt(x0$8._2());
            Tuple2.mcII.sp var1 = new Tuple2.mcII.sp(major, minor);
            return var1;
         } else {
            throw new MatchError(x0$8);
         }
      });
      Rule var10000 = MODULE$.u2().$up$up(ConstantPool$.MODULE$);
      Function1 var0 = (x$7) -> BoxesRunTime.boxToBoolean($anonfun$constantPool$1(x$7));
      constantPool = var10000.$greater$greater((initial) -> MODULE$.repeatUntil(MODULE$.constantPoolEntry(), var0, initial));
      var10000 = MODULE$.u2().$greater$greater((n) -> $anonfun$utf8String$1(BoxesRunTime.unboxToInt(n)));
      Function1 var1 = (raw) -> (pool) -> raw.fromUTF8StringAndBytes();
      utf8String = var10000.$up$up((raw) -> (pool) -> MODULE$.add1(var1, raw, pool));
      var10000 = MODULE$.u4();
      Function1 var2 = (x) -> $anonfun$intConstant$1(BoxesRunTime.unboxToInt(x));
      intConstant = var10000.$up$up((raw) -> $anonfun$intConstant$3(var2, BoxesRunTime.unboxToInt(raw)));
      var10000 = MODULE$.bytes(4);
      Function1 var3 = (raw) -> (pool) -> "Float: TODO";
      floatConstant = var10000.$up$up((raw) -> (pool) -> MODULE$.add1(var3, raw, pool));
      var10000 = MODULE$.bytes(8);
      Function1 var4 = (raw) -> (pool) -> BoxesRunTime.boxToLong($anonfun$longConstant$2(raw, pool));
      longConstant = var10000.$up$up((raw) -> (pool) -> MODULE$.add2(var4, raw, pool));
      var10000 = MODULE$.bytes(8);
      Function1 var5 = (raw) -> (pool) -> "Double: TODO";
      doubleConstant = var10000.$up$up((raw) -> (pool) -> MODULE$.add2(var5, raw, pool));
      var10000 = MODULE$.u2();
      Function1 var6 = (x) -> $anonfun$classRef$1(BoxesRunTime.unboxToInt(x));
      classRef = var10000.$up$up((raw) -> $anonfun$classRef$3(var6, BoxesRunTime.unboxToInt(raw)));
      var10000 = MODULE$.u2();
      Function1 var7 = (x) -> $anonfun$stringRef$1(BoxesRunTime.unboxToInt(x));
      stringRef = var10000.$up$up((raw) -> $anonfun$stringRef$3(var7, BoxesRunTime.unboxToInt(raw)));
      fieldRef = MODULE$.memberRef("Field");
      methodRef = MODULE$.memberRef("Method");
      interfaceMethodRef = MODULE$.memberRef("InterfaceMethod");
      var10000 = MODULE$.u2().$tilde(() -> MODULE$.u2());
      Function1 var8 = (x0$2) -> {
         if (x0$2 != null) {
            int name = BoxesRunTime.unboxToInt(x0$2._1());
            int descriptor = BoxesRunTime.unboxToInt(x0$2._2());
            Function1 var1 = (pool) -> (new StringBuilder(15)).append("NameAndType: ").append(pool.apply(name)).append(", ").append(pool.apply(descriptor)).toString();
            return var1;
         } else {
            throw new MatchError(x0$2);
         }
      };
      nameAndType = var10000.$up$up((raw) -> (pool) -> MODULE$.add1(var8, raw, pool));
      var10000 = MODULE$.u1().$tilde(() -> MODULE$.u2());
      Function1 var9 = (x0$3) -> {
         if (x0$3 != null) {
            int referenceKind = BoxesRunTime.unboxToInt(x0$3._1());
            int referenceIndex = BoxesRunTime.unboxToInt(x0$3._2());
            Function1 var1 = (pool) -> (new StringBuilder(16)).append("MethodHandle: ").append(referenceKind).append(", ").append(pool.apply(referenceIndex)).toString();
            return var1;
         } else {
            throw new MatchError(x0$3);
         }
      };
      methodHandle = var10000.$up$up((raw) -> (pool) -> MODULE$.add1(var9, raw, pool));
      var10000 = MODULE$.u2();
      Function1 var10 = (x0$4) -> $anonfun$methodType$1(BoxesRunTime.unboxToInt(x0$4));
      methodType = var10000.$up$up((raw) -> $anonfun$methodType$3(var10, BoxesRunTime.unboxToInt(raw)));
      var10000 = MODULE$.u2().$tilde(() -> MODULE$.u2());
      Function1 var11 = (x0$5) -> {
         if (x0$5 != null) {
            int bootstrapMethodAttrIndex = BoxesRunTime.unboxToInt(x0$5._1());
            int nameAndTypeIndex = BoxesRunTime.unboxToInt(x0$5._2());
            Function1 var1 = (pool) -> (new StringBuilder(44)).append("InvokeDynamic: bootstrapMethodAttrIndex = ").append(bootstrapMethodAttrIndex).append(", ").append(pool.apply(nameAndTypeIndex)).toString();
            return var1;
         } else {
            throw new MatchError(x0$5);
         }
      };
      invokeDynamic = var10000.$up$up((raw) -> (pool) -> MODULE$.add1(var11, raw, pool));
      var10000 = MODULE$.u2();
      Function1 var12 = (x0$6) -> $anonfun$constantModule$1(BoxesRunTime.unboxToInt(x0$6));
      constantModule = var10000.$up$up((raw) -> $anonfun$constantModule$3(var12, BoxesRunTime.unboxToInt(raw)));
      var10000 = MODULE$.u2();
      Function1 var13 = (x0$7) -> $anonfun$constantPackage$1(BoxesRunTime.unboxToInt(x0$7));
      constantPackage = var10000.$up$up((raw) -> $anonfun$constantPackage$3(var13, BoxesRunTime.unboxToInt(raw)));
      constantPoolEntry = MODULE$.u1().$greater$greater((x0$1) -> $anonfun$constantPoolEntry$1(BoxesRunTime.unboxToInt(x0$1)));
      var10000 = MODULE$.u2();
      SeqRule var14 = MODULE$.seqRule(MODULE$.u2());
      interfaces = var10000.$greater$greater((num) -> $anonfun$interfaces$1(var14, BoxesRunTime.unboxToInt(num)));
      attribute = MODULE$.u2().$tilde(() -> MODULE$.u4().$greater$greater((n) -> $anonfun$attribute$2(BoxesRunTime.unboxToInt(n)))).$up$tilde$up(Attribute$.MODULE$, .MODULE$.$conforms());
      var10000 = MODULE$.u2();
      SeqRule var15 = MODULE$.seqRule(MODULE$.attribute());
      attributes = var10000.$greater$greater((num) -> $anonfun$attributes$1(var15, BoxesRunTime.unboxToInt(num)));
      element_value_pair = MODULE$.u2().$tilde(() -> MODULE$.element_value()).$up$tilde$up(ClassFileParser.AnnotationElement$.MODULE$, .MODULE$.$conforms());
      annotation = MODULE$.u2().$tilde(() -> {
         Rule var10000 = MODULE$.u2();
         SeqRule var0 = MODULE$.seqRule(MODULE$.element_value_pair());
         return var10000.$greater$greater((num) -> $anonfun$annotation$2(var0, BoxesRunTime.unboxToInt(num)));
      }).$up$tilde$up(ClassFileParser.Annotation$.MODULE$, .MODULE$.$conforms());
      var10000 = MODULE$.u2();
      SeqRule var16 = MODULE$.seqRule(MODULE$.annotation());
      annotations = var10000.$greater$greater((num) -> $anonfun$annotations$1(var16, BoxesRunTime.unboxToInt(num)));
      field = MODULE$.u2().$tilde(() -> MODULE$.u2()).$tilde(() -> MODULE$.u2()).$tilde(() -> MODULE$.attributes()).$up$tilde$tilde$tilde$up(Field$.MODULE$, .MODULE$.$conforms());
      var10000 = MODULE$.u2();
      SeqRule var17 = MODULE$.seqRule(MODULE$.field());
      fields = var10000.$greater$greater((num) -> $anonfun$fields$1(var17, BoxesRunTime.unboxToInt(num)));
      method = MODULE$.u2().$tilde(() -> MODULE$.u2()).$tilde(() -> MODULE$.u2()).$tilde(() -> MODULE$.attributes()).$up$tilde$tilde$tilde$up(Method$.MODULE$, .MODULE$.$conforms());
      var10000 = MODULE$.u2();
      SeqRule var18 = MODULE$.seqRule(MODULE$.method());
      methods = var10000.$greater$greater((num) -> $anonfun$methods$1(var18, BoxesRunTime.unboxToInt(num)));
      header = MODULE$.magicNumber().$minus$tilde(() -> MODULE$.u2().$tilde(() -> MODULE$.u2()).$tilde(() -> MODULE$.constantPool()).$tilde(() -> MODULE$.u2()).$tilde(() -> MODULE$.u2()).$tilde(() -> MODULE$.u2()).$tilde(() -> MODULE$.interfaces())).$up$tilde$tilde$tilde$tilde$tilde$tilde$up(ClassFileHeader$.MODULE$, .MODULE$.$conforms());
      classFile = MODULE$.header().$tilde(() -> MODULE$.fields()).$tilde(() -> MODULE$.methods()).$tilde(() -> MODULE$.attributes()).$tilde$minus(() -> MODULE$.inRule(MODULE$.u1()).unary_$bang()).$up$tilde$tilde$tilde$up(ClassFile$.MODULE$, .MODULE$.$conforms());
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

   public ClassFile parse(final ByteCode byteCode) {
      return (ClassFile)this.expect(this.classFile()).apply(byteCode);
   }

   public Seq parseAnnotations(final ByteCode byteCode) {
      return (Seq)this.expect(this.annotations()).apply(byteCode);
   }

   public Rule magicNumber() {
      return magicNumber;
   }

   public Rule version() {
      return version;
   }

   public Rule constantPool() {
      return constantPool;
   }

   public Rule utf8String() {
      return utf8String;
   }

   public Rule intConstant() {
      return intConstant;
   }

   public Rule floatConstant() {
      return floatConstant;
   }

   public Rule longConstant() {
      return longConstant;
   }

   public Rule doubleConstant() {
      return doubleConstant;
   }

   public Rule classRef() {
      return classRef;
   }

   public Rule stringRef() {
      return stringRef;
   }

   public Rule fieldRef() {
      return fieldRef;
   }

   public Rule methodRef() {
      return methodRef;
   }

   public Rule interfaceMethodRef() {
      return interfaceMethodRef;
   }

   public Rule nameAndType() {
      return nameAndType;
   }

   public Rule methodHandle() {
      return methodHandle;
   }

   public Rule methodType() {
      return methodType;
   }

   public Rule invokeDynamic() {
      return invokeDynamic;
   }

   public Rule constantModule() {
      return constantModule;
   }

   public Rule constantPackage() {
      return constantPackage;
   }

   public Rule constantPoolEntry() {
      return constantPoolEntry;
   }

   public Rule interfaces() {
      return interfaces;
   }

   public Rule attribute() {
      return attribute;
   }

   public Rule attributes() {
      return attributes;
   }

   public Rule element_value() {
      return this.u1().$greater$greater((x0$1) -> $anonfun$element_value$1(BoxesRunTime.unboxToInt(x0$1)));
   }

   public Rule element_value_pair() {
      return element_value_pair;
   }

   public Rule annotation() {
      return annotation;
   }

   public Rule annotations() {
      return annotations;
   }

   public Rule field() {
      return field;
   }

   public Rule fields() {
      return fields;
   }

   public Rule method() {
      return method;
   }

   public Rule methods() {
      return methods;
   }

   public Rule header() {
      return header;
   }

   public Rule classFile() {
      return classFile;
   }

   public Rule memberRef(final String description) {
      Rule var10000 = this.u2().$tilde(() -> MODULE$.u2());
      Function1 var2 = (x0$1) -> {
         if (x0$1 != null) {
            int classRef = BoxesRunTime.unboxToInt(x0$1._1());
            int nameAndTypeRef = BoxesRunTime.unboxToInt(x0$1._2());
            Function1 var2 = (pool) -> (new StringBuilder(4)).append(description).append(": ").append(pool.apply(classRef)).append(", ").append(pool.apply(nameAndTypeRef)).toString();
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      };
      return var10000.$up$up((raw) -> (pool) -> MODULE$.add1(var2, raw, pool));
   }

   public ConstantPool add1(final Function1 f, final Object raw, final ConstantPool pool) {
      return pool.add((Function1)f.apply(raw));
   }

   public ConstantPool add2(final Function1 f, final Object raw, final ConstantPool pool) {
      return pool.add((Function1)f.apply(raw)).add((poolx) -> "<empty>");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$constantPool$1(final ConstantPool x$7) {
      return x$7.isFull();
   }

   // $FF: synthetic method
   public static final Rule $anonfun$utf8String$1(final int n) {
      return MODULE$.bytes(n);
   }

   // $FF: synthetic method
   public static final int $anonfun$intConstant$2(final int x$8, final ConstantPool pool) {
      return x$8;
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$intConstant$1(final int x) {
      return (pool) -> BoxesRunTime.boxToInteger($anonfun$intConstant$2(x, pool));
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$intConstant$3(final Function1 eta$0$2$1, final int raw) {
      return (pool) -> MODULE$.add1(eta$0$2$1, BoxesRunTime.boxToInteger(raw), pool);
   }

   // $FF: synthetic method
   public static final long $anonfun$longConstant$2(final ByteCode raw$5, final ConstantPool pool) {
      return raw$5.toLong();
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$classRef$1(final int x) {
      return (pool) -> (new StringBuilder(7)).append("Class: ").append(pool.apply(x)).toString();
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$classRef$3(final Function1 eta$0$6$1, final int raw) {
      return (pool) -> MODULE$.add1(eta$0$6$1, BoxesRunTime.boxToInteger(raw), pool);
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$stringRef$1(final int x) {
      return (pool) -> (new StringBuilder(8)).append("String: ").append(pool.apply(x)).toString();
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$stringRef$3(final Function1 eta$0$7$1, final int raw) {
      return (pool) -> MODULE$.add1(eta$0$7$1, BoxesRunTime.boxToInteger(raw), pool);
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$methodType$1(final int x0$4) {
      Function1 var1 = (pool) -> (new StringBuilder(12)).append("MethodType: ").append(pool.apply(x0$4)).toString();
      return var1;
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$methodType$3(final Function1 eta$0$10$1, final int raw) {
      return (pool) -> MODULE$.add1(eta$0$10$1, BoxesRunTime.boxToInteger(raw), pool);
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$constantModule$1(final int x0$6) {
      Function1 var1 = (pool) -> {
         Object var4 = pool.apply(x0$6);
         if (var4 instanceof StringBytesPair) {
            StringBytesPair var5 = (StringBytesPair)var4;
            String value = var5.string();
            return (new StringBuilder(16)).append("ConstantModule: ").append(value).toString();
         } else {
            throw new MatchError(var4);
         }
      };
      return var1;
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$constantModule$3(final Function1 eta$0$12$1, final int raw) {
      return (pool) -> MODULE$.add1(eta$0$12$1, BoxesRunTime.boxToInteger(raw), pool);
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$constantPackage$1(final int x0$7) {
      Function1 var1 = (pool) -> {
         Object var4 = pool.apply(x0$7);
         if (var4 instanceof StringBytesPair) {
            StringBytesPair var5 = (StringBytesPair)var4;
            String value = var5.string();
            return (new StringBuilder(17)).append("ConstantPackage: ").append(value).toString();
         } else {
            throw new MatchError(var4);
         }
      };
      return var1;
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$constantPackage$3(final Function1 eta$0$13$1, final int raw) {
      return (pool) -> MODULE$.add1(eta$0$13$1, BoxesRunTime.boxToInteger(raw), pool);
   }

   // $FF: synthetic method
   public static final Rule $anonfun$constantPoolEntry$1(final int x0$1) {
      Rule var10000;
      switch (x0$1) {
         case 1:
            var10000 = MODULE$.utf8String();
            break;
         case 2:
         case 13:
         case 14:
         case 17:
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(x0$1));
         case 3:
            var10000 = MODULE$.intConstant();
            break;
         case 4:
            var10000 = MODULE$.floatConstant();
            break;
         case 5:
            var10000 = MODULE$.longConstant();
            break;
         case 6:
            var10000 = MODULE$.doubleConstant();
            break;
         case 7:
            var10000 = MODULE$.classRef();
            break;
         case 8:
            var10000 = MODULE$.stringRef();
            break;
         case 9:
            var10000 = MODULE$.fieldRef();
            break;
         case 10:
            var10000 = MODULE$.methodRef();
            break;
         case 11:
            var10000 = MODULE$.interfaceMethodRef();
            break;
         case 12:
            var10000 = MODULE$.nameAndType();
            break;
         case 15:
            var10000 = MODULE$.methodHandle();
            break;
         case 16:
            var10000 = MODULE$.methodType();
            break;
         case 18:
            var10000 = MODULE$.invokeDynamic();
            break;
         case 19:
            var10000 = MODULE$.constantModule();
            break;
         case 20:
            var10000 = MODULE$.constantPackage();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final Rule $anonfun$interfaces$1(final SeqRule eta$0$15$1, final int num) {
      return eta$0$15$1.times(num);
   }

   // $FF: synthetic method
   public static final Rule $anonfun$attribute$2(final int n) {
      return MODULE$.bytes(n);
   }

   // $FF: synthetic method
   public static final Rule $anonfun$attributes$1(final SeqRule eta$0$16$1, final int num) {
      return eta$0$16$1.times(num);
   }

   // $FF: synthetic method
   public static final Rule $anonfun$element_value$3(final SeqRule eta$0$1$2, final int num) {
      return eta$0$1$2.times(num);
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$element_value$1(final int x0$1) {
      Rule var10000;
      switch (x0$1) {
         case 64:
            var10000 = MODULE$.annotation();
            break;
         case 66:
         case 67:
         case 68:
         case 70:
         case 73:
         case 74:
         case 83:
         case 90:
         case 115:
            var10000 = MODULE$.u2().$up$up(ClassFileParser.ConstValueIndex$.MODULE$);
            break;
         case 91:
            var10000 = MODULE$.u2();
            SeqRule var2 = MODULE$.seqRule(MODULE$.element_value());
            var10000 = var10000.$greater$greater((num) -> $anonfun$element_value$3(var2, BoxesRunTime.unboxToInt(num))).$up$up(ClassFileParser.ArrayValue$.MODULE$);
            break;
         case 99:
            var10000 = MODULE$.u2().$up$up(ClassFileParser.ClassInfoIndex$.MODULE$);
            break;
         case 101:
            var10000 = MODULE$.u2().$tilde(() -> MODULE$.u2()).$up$tilde$up(ClassFileParser.EnumConstValue$.MODULE$, .MODULE$.$conforms());
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(x0$1));
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final Rule $anonfun$annotation$2(final SeqRule eta$0$20$1, final int num) {
      return eta$0$20$1.times(num);
   }

   // $FF: synthetic method
   public static final Rule $anonfun$annotations$1(final SeqRule eta$0$19$1, final int num) {
      return eta$0$19$1.times(num);
   }

   // $FF: synthetic method
   public static final Rule $anonfun$fields$1(final SeqRule eta$0$17$1, final int num) {
      return eta$0$17$1.times(num);
   }

   // $FF: synthetic method
   public static final Rule $anonfun$methods$1(final SeqRule eta$0$18$1, final int num) {
      return eta$0$18$1.times(num);
   }

   private ClassFileParser$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
