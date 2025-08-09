package org.apache.spark.sql.catalyst.parser;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.BinaryType$;
import org.apache.spark.sql.types.BooleanType$;
import org.apache.spark.sql.types.ByteType$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DateType$;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.DecimalType$;
import org.apache.spark.sql.types.DoubleType$;
import org.apache.spark.sql.types.FloatType$;
import org.apache.spark.sql.types.IntegerType$;
import org.apache.spark.sql.types.LongType$;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.ShortType$;
import org.apache.spark.sql.types.StringType$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructField$;
import org.apache.spark.sql.types.StructType$;
import org.apache.spark.sql.types.TimestampType$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.matching.Regex;
import scala.util.parsing.combinator.Parsers;
import scala.util.parsing.combinator.RegexParsers;
import scala.util.parsing.input.Reader;

public final class LegacyTypeStringParser$ implements RegexParsers {
   public static final LegacyTypeStringParser$ MODULE$ = new LegacyTypeStringParser$();
   private static Parsers.Parser primitiveType;
   private static Parsers.Parser fixedDecimalType;
   private static Parsers.Parser arrayType;
   private static Parsers.Parser mapType;
   private static Parsers.Parser structField;
   private static Parsers.Parser boolVal;
   private static Parsers.Parser structType;
   private static Parsers.Parser dataType;
   private static Regex whiteSpace;
   private static volatile Parsers.Success Success$module;
   private static volatile Parsers.NoSuccess NoSuccess$module;
   private static volatile Parsers.Failure Failure$module;
   private static volatile Parsers.Error Error$module;
   private static volatile Parsers..tilde $tilde$module;
   private static volatile byte bitmap$0;

   static {
      Parsers.$init$(MODULE$);
      RegexParsers.$init$(MODULE$);
   }

   // $FF: synthetic method
   public Parsers.Parser scala$util$parsing$combinator$RegexParsers$$super$positioned(final Function0 p) {
      return Parsers.positioned$(this, p);
   }

   // $FF: synthetic method
   public Parsers.Parser scala$util$parsing$combinator$RegexParsers$$super$err(final String msg) {
      return Parsers.err$(this, msg);
   }

   // $FF: synthetic method
   public Parsers.Parser scala$util$parsing$combinator$RegexParsers$$super$phrase(final Parsers.Parser p) {
      return Parsers.phrase$(this, p);
   }

   public boolean skipWhitespace() {
      return RegexParsers.skipWhitespace$(this);
   }

   public int handleWhiteSpace(final CharSequence source, final int offset) {
      return RegexParsers.handleWhiteSpace$(this, source, offset);
   }

   public Parsers.Parser literal(final String s) {
      return RegexParsers.literal$(this, s);
   }

   public Parsers.Parser regex(final Regex r) {
      return RegexParsers.regex$(this, r);
   }

   public Parsers.Parser positioned(final Function0 p) {
      return RegexParsers.positioned$(this, p);
   }

   public Parsers.Parser err(final String msg) {
      return RegexParsers.err$(this, msg);
   }

   public Parsers.Parser phrase(final Parsers.Parser p) {
      return RegexParsers.phrase$(this, p);
   }

   public Parsers.ParseResult parse(final Parsers.Parser p, final Reader in) {
      return RegexParsers.parse$(this, p, in);
   }

   public Parsers.ParseResult parse(final Parsers.Parser p, final CharSequence in) {
      return RegexParsers.parse$(this, p, in);
   }

   public Parsers.ParseResult parse(final Parsers.Parser p, final java.io.Reader in) {
      return RegexParsers.parse$(this, p, in);
   }

   public Parsers.ParseResult parseAll(final Parsers.Parser p, final Reader in) {
      return RegexParsers.parseAll$(this, p, in);
   }

   public Parsers.ParseResult parseAll(final Parsers.Parser p, final java.io.Reader in) {
      return RegexParsers.parseAll$(this, p, in);
   }

   public Parsers.ParseResult parseAll(final Parsers.Parser p, final CharSequence in) {
      return RegexParsers.parseAll$(this, p, in);
   }

   public Parsers.Parser Parser(final Function1 f) {
      return Parsers.Parser$(this, f);
   }

   public Parsers.ParseResult Success(final Object res, final Reader next, final Option failure) {
      return Parsers.Success$(this, res, next, failure);
   }

   public Option selectLastFailure(final Option failure0, final Option failure1) {
      return Parsers.selectLastFailure$(this, failure0, failure1);
   }

   public Parsers.OnceParser OnceParser(final Function1 f) {
      return Parsers.OnceParser$(this, f);
   }

   public Parsers.Parser commit(final Function0 p) {
      return Parsers.commit$(this, p);
   }

   public Parsers.Parser elem(final String kind, final Function1 p) {
      return Parsers.elem$(this, kind, p);
   }

   public Parsers.Parser elem(final Object e) {
      return Parsers.elem$(this, e);
   }

   public Parsers.Parser accept(final Object e) {
      return Parsers.accept$(this, e);
   }

   public Parsers.Parser accept(final Object es, final Function1 f) {
      return Parsers.accept$(this, es, f);
   }

   public Parsers.Parser accept(final String expected, final PartialFunction f) {
      return Parsers.accept$(this, expected, f);
   }

   public Parsers.Parser acceptIf(final Function1 p, final Function1 err) {
      return Parsers.acceptIf$(this, p, err);
   }

   public Parsers.Parser acceptMatch(final String expected, final PartialFunction f) {
      return Parsers.acceptMatch$(this, expected, f);
   }

   public Parsers.Parser acceptSeq(final Object es, final Function1 f) {
      return Parsers.acceptSeq$(this, es, f);
   }

   public Parsers.Parser failure(final String msg) {
      return Parsers.failure$(this, msg);
   }

   public Parsers.Parser success(final Object v) {
      return Parsers.success$(this, v);
   }

   public Parsers.Parser log(final Function0 p, final String name) {
      return Parsers.log$(this, p, name);
   }

   public Parsers.Parser rep(final Function0 p) {
      return Parsers.rep$(this, p);
   }

   public Parsers.Parser repsep(final Function0 p, final Function0 q) {
      return Parsers.repsep$(this, p, q);
   }

   public Parsers.Parser rep1(final Function0 p) {
      return Parsers.rep1$(this, p);
   }

   public Parsers.Parser rep1(final Function0 first, final Function0 p0) {
      return Parsers.rep1$(this, first, p0);
   }

   public Parsers.Parser repN(final int num, final Function0 p) {
      return Parsers.repN$(this, num, p);
   }

   public Parsers.Parser repNM(final int n, final int m, final Parsers.Parser p, final Parsers.Parser sep) {
      return Parsers.repNM$(this, n, m, p, sep);
   }

   public Parsers.Parser repNM$default$4() {
      return Parsers.repNM$default$4$(this);
   }

   public Parsers.Parser rep1sep(final Function0 p, final Function0 q) {
      return Parsers.rep1sep$(this, p, q);
   }

   public Parsers.Parser chainl1(final Function0 p, final Function0 q) {
      return Parsers.chainl1$(this, p, q);
   }

   public Parsers.Parser chainl1(final Function0 first, final Function0 p, final Function0 q) {
      return Parsers.chainl1$(this, first, p, q);
   }

   public Parsers.Parser chainr1(final Function0 p, final Function0 q, final Function2 combine, final Object first) {
      return Parsers.chainr1$(this, p, q, combine, first);
   }

   public Parsers.Parser opt(final Function0 p) {
      return Parsers.opt$(this, p);
   }

   public Parsers.Parser not(final Function0 p) {
      return Parsers.not$(this, p);
   }

   public Parsers.Parser guard(final Function0 p) {
      return Parsers.guard$(this, p);
   }

   public Function1 mkList() {
      return Parsers.mkList$(this);
   }

   public Regex whiteSpace() {
      return whiteSpace;
   }

   public void scala$util$parsing$combinator$RegexParsers$_setter_$whiteSpace_$eq(final Regex x$1) {
      whiteSpace = x$1;
   }

   public Parsers.Success Success() {
      if (Success$module == null) {
         this.Success$lzycompute$1();
      }

      return Success$module;
   }

   public Parsers.NoSuccess NoSuccess() {
      if (NoSuccess$module == null) {
         this.NoSuccess$lzycompute$1();
      }

      return NoSuccess$module;
   }

   public Parsers.Failure Failure() {
      if (Failure$module == null) {
         this.Failure$lzycompute$1();
      }

      return Failure$module;
   }

   public Parsers.Error Error() {
      if (Error$module == null) {
         this.Error$lzycompute$1();
      }

      return Error$module;
   }

   public Parsers..tilde $tilde() {
      if ($tilde$module == null) {
         this.$tilde$lzycompute$1();
      }

      return $tilde$module;
   }

   private Parsers.Parser primitiveType$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            primitiveType = this.literal("StringType").$up$up$up(() -> StringType$.MODULE$).$bar(() -> MODULE$.literal("FloatType").$up$up$up(() -> FloatType$.MODULE$)).$bar(() -> MODULE$.literal("IntegerType").$up$up$up(() -> IntegerType$.MODULE$)).$bar(() -> MODULE$.literal("ByteType").$up$up$up(() -> ByteType$.MODULE$)).$bar(() -> MODULE$.literal("ShortType").$up$up$up(() -> ShortType$.MODULE$)).$bar(() -> MODULE$.literal("DoubleType").$up$up$up(() -> DoubleType$.MODULE$)).$bar(() -> MODULE$.literal("LongType").$up$up$up(() -> LongType$.MODULE$)).$bar(() -> MODULE$.literal("BinaryType").$up$up$up(() -> BinaryType$.MODULE$)).$bar(() -> MODULE$.literal("BooleanType").$up$up$up(() -> BooleanType$.MODULE$)).$bar(() -> MODULE$.literal("DateType").$up$up$up(() -> DateType$.MODULE$)).$bar(() -> MODULE$.literal("DecimalType()").$up$up$up(() -> DecimalType$.MODULE$.USER_DEFAULT())).$bar(() -> MODULE$.fixedDecimalType()).$bar(() -> MODULE$.literal("TimestampType").$up$up$up(() -> TimestampType$.MODULE$));
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return primitiveType;
   }

   public Parsers.Parser primitiveType() {
      return (byte)(bitmap$0 & 1) == 0 ? this.primitiveType$lzycompute() : primitiveType;
   }

   private Parsers.Parser fixedDecimalType$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            fixedDecimalType = this.literal("DecimalType(").$tilde$greater(() -> MODULE$.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("[0-9]+")))).$tilde(() -> MODULE$.literal(",").$tilde$greater(() -> MODULE$.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("[0-9]+")))).$less$tilde(() -> MODULE$.literal(")"))).$up$up((x0$1) -> {
               if (x0$1 != null) {
                  String precision = (String)x0$1._1();
                  String scale = (String)x0$1._2();
                  return new DecimalType(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(precision)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(scale)));
               } else {
                  throw new MatchError(x0$1);
               }
            });
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return fixedDecimalType;
   }

   public Parsers.Parser fixedDecimalType() {
      return (byte)(bitmap$0 & 2) == 0 ? this.fixedDecimalType$lzycompute() : fixedDecimalType;
   }

   private Parsers.Parser arrayType$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 4) == 0) {
            arrayType = this.literal("ArrayType").$tilde$greater(() -> MODULE$.literal("(")).$tilde$greater(() -> MODULE$.dataType()).$tilde(() -> MODULE$.literal(",")).$tilde(() -> MODULE$.boolVal()).$less$tilde(() -> MODULE$.literal(")")).$up$up((x0$1) -> {
               if (x0$1 != null) {
                  Parsers..tilde var3 = (Parsers..tilde)x0$1._1();
                  boolean containsNull = BoxesRunTime.unboxToBoolean(x0$1._2());
                  if (var3 != null) {
                     DataType tpe = (DataType)var3._1();
                     return new ArrayType(tpe, containsNull);
                  }
               }

               throw new MatchError(x0$1);
            });
            bitmap$0 = (byte)(bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return arrayType;
   }

   public Parsers.Parser arrayType() {
      return (byte)(bitmap$0 & 4) == 0 ? this.arrayType$lzycompute() : arrayType;
   }

   private Parsers.Parser mapType$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 8) == 0) {
            mapType = this.literal("MapType").$tilde$greater(() -> MODULE$.literal("(")).$tilde$greater(() -> MODULE$.dataType()).$tilde(() -> MODULE$.literal(",")).$tilde(() -> MODULE$.dataType()).$tilde(() -> MODULE$.literal(",")).$tilde(() -> MODULE$.boolVal()).$less$tilde(() -> MODULE$.literal(")")).$up$up((x0$1) -> {
               if (x0$1 != null) {
                  Parsers..tilde var3 = (Parsers..tilde)x0$1._1();
                  boolean valueContainsNull = BoxesRunTime.unboxToBoolean(x0$1._2());
                  if (var3 != null) {
                     Parsers..tilde var5 = (Parsers..tilde)var3._1();
                     if (var5 != null) {
                        Parsers..tilde var6 = (Parsers..tilde)var5._1();
                        DataType t2 = (DataType)var5._2();
                        if (var6 != null) {
                           DataType t1 = (DataType)var6._1();
                           return new MapType(t1, t2, valueContainsNull);
                        }
                     }
                  }
               }

               throw new MatchError(x0$1);
            });
            bitmap$0 = (byte)(bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return mapType;
   }

   public Parsers.Parser mapType() {
      return (byte)(bitmap$0 & 8) == 0 ? this.mapType$lzycompute() : mapType;
   }

   private Parsers.Parser structField$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 16) == 0) {
            structField = this.literal("StructField(").$tilde$greater(() -> MODULE$.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("[a-zA-Z0-9_]*")))).$tilde(() -> MODULE$.literal(",").$tilde$greater(() -> MODULE$.dataType())).$tilde(() -> MODULE$.literal(",").$tilde$greater(() -> MODULE$.boolVal()).$less$tilde(() -> MODULE$.literal(")"))).$up$up((x0$1) -> {
               if (x0$1 != null) {
                  Parsers..tilde var3 = (Parsers..tilde)x0$1._1();
                  boolean nullable = BoxesRunTime.unboxToBoolean(x0$1._2());
                  if (var3 != null) {
                     String name = (String)var3._1();
                     DataType tpe = (DataType)var3._2();
                     return new StructField(name, tpe, nullable, StructField$.MODULE$.apply$default$4());
                  }
               }

               throw new MatchError(x0$1);
            });
            bitmap$0 = (byte)(bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return structField;
   }

   public Parsers.Parser structField() {
      return (byte)(bitmap$0 & 16) == 0 ? this.structField$lzycompute() : structField;
   }

   private Parsers.Parser boolVal$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 32) == 0) {
            boolVal = this.literal("true").$up$up$up((JFunction0.mcZ.sp)() -> true).$bar(() -> MODULE$.literal("false").$up$up$up((JFunction0.mcZ.sp)() -> false));
            bitmap$0 = (byte)(bitmap$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return boolVal;
   }

   public Parsers.Parser boolVal() {
      return (byte)(bitmap$0 & 32) == 0 ? this.boolVal$lzycompute() : boolVal;
   }

   private Parsers.Parser structType$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 64) == 0) {
            structType = this.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("StructType\\([A-zA-z]*\\("))).$tilde$greater(() -> MODULE$.repsep(() -> MODULE$.structField(), () -> MODULE$.literal(","))).$less$tilde(() -> MODULE$.literal("))")).$up$up((x0$1) -> StructType$.MODULE$.apply((Seq)x0$1));
            bitmap$0 = (byte)(bitmap$0 | 64);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return structType;
   }

   public Parsers.Parser structType() {
      return (byte)(bitmap$0 & 64) == 0 ? this.structType$lzycompute() : structType;
   }

   private Parsers.Parser dataType$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 128) == 0) {
            dataType = this.arrayType().$bar(() -> MODULE$.mapType()).$bar(() -> MODULE$.structType()).$bar(() -> MODULE$.primitiveType());
            bitmap$0 = (byte)(bitmap$0 | 128);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return dataType;
   }

   public Parsers.Parser dataType() {
      return (byte)(bitmap$0 & 128) == 0 ? this.dataType$lzycompute() : dataType;
   }

   public DataType parseString(final String asString) {
      Parsers.ParseResult var3 = this.parseAll(this.dataType(), (CharSequence)asString);
      if (var3 instanceof Parsers.Success var4) {
         DataType result = (DataType)var4.result();
         return result;
      } else if (var3 instanceof Parsers.NoSuccess var6) {
         throw DataTypeErrors$.MODULE$.dataTypeUnsupportedError(asString, var6.toString());
      } else {
         throw new MatchError(var3);
      }
   }

   private final void Success$lzycompute$1() {
      synchronized(this){}

      try {
         if (Success$module == null) {
            Success$module = new Parsers.Success(this);
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void NoSuccess$lzycompute$1() {
      synchronized(this){}

      try {
         if (NoSuccess$module == null) {
            NoSuccess$module = new Parsers.NoSuccess(this);
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Failure$lzycompute$1() {
      synchronized(this){}

      try {
         if (Failure$module == null) {
            Failure$module = new Parsers.Failure(this);
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Error$lzycompute$1() {
      synchronized(this){}

      try {
         if (Error$module == null) {
            Error$module = new Parsers.Error(this);
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void $tilde$lzycompute$1() {
      synchronized(this){}

      try {
         if ($tilde$module == null) {
            $tilde$module = new Parsers..tilde(this);
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private LegacyTypeStringParser$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
