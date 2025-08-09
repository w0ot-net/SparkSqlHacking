package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.util.matching.Regex;
import scala.util.parsing.combinator.Parsers;
import scala.util.parsing.combinator.RegexParsers;
import scala.util.parsing.input.Reader;

public final class RFormulaParser$ implements RegexParsers {
   public static final RFormulaParser$ MODULE$ = new RFormulaParser$();
   private static final Parsers.Parser intercept;
   private static final Parsers.Parser columnRef;
   private static final Parsers.Parser empty;
   private static final Parsers.Parser label;
   private static final Parsers.Parser dot;
   private static final Parsers.Parser parens;
   private static final Parsers.Parser term;
   private static final Parsers.Parser pow;
   private static final Parsers.Parser interaction;
   private static final Parsers.Parser factor;
   private static final Parsers.Parser sum;
   private static final Parsers.Parser expr;
   private static final Parsers.Parser formula;
   private static Regex whiteSpace;
   private static volatile Parsers.Success Success$module;
   private static volatile Parsers.NoSuccess NoSuccess$module;
   private static volatile Parsers.Failure Failure$module;
   private static volatile Parsers.Error Error$module;
   private static volatile Parsers..tilde $tilde$module;

   static {
      Parsers.$init$(MODULE$);
      RegexParsers.$init$(MODULE$);
      intercept = MODULE$.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("([01])"))).$up$up((x0$1) -> {
         Intercept var10000;
         boolean var10002;
         label17: {
            label16: {
               var10000 = new Intercept;
               String var3 = "1";
               if (x0$1 == null) {
                  if (var3 == null) {
                     break label16;
                  }
               } else if (x0$1.equals(var3)) {
                  break label16;
               }

               var10002 = false;
               break label17;
            }

            var10002 = true;
         }

         var10000.<init>(var10002);
         return var10000;
      });
      columnRef = MODULE$.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("([a-zA-Z]|\\.[a-zA-Z_])[a-zA-Z0-9._]*"))).$up$up((x0$2) -> new ColumnRef(x0$2));
      empty = MODULE$.literal("").$up$up((x0$3) -> new ColumnRef(""));
      label = MODULE$.columnRef().$bar(() -> MODULE$.empty());
      dot = MODULE$.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("\\."))).$up$up((x0$4) -> Dot$.MODULE$);
      parens = MODULE$.literal("(").$tilde$greater(() -> MODULE$.expr()).$less$tilde(() -> MODULE$.literal(")"));
      term = MODULE$.parens().$bar(() -> MODULE$.intercept()).$bar(() -> MODULE$.columnRef()).$bar(() -> MODULE$.dot());
      pow = MODULE$.term().$tilde(() -> MODULE$.literal("^")).$tilde(() -> MODULE$.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^[1-9]\\d*")))).$up$up((x0$5) -> {
         if (x0$5 != null) {
            Parsers..tilde var3 = (Parsers..tilde)x0$5._1();
            String degree = (String)x0$5._2();
            if (var3 != null) {
               Term base = (Term)var3._1();
               String var6 = (String)var3._2();
               if ("^".equals(var6)) {
                  return MODULE$.power(base, .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(degree)));
               }
            }
         }

         throw new IllegalArgumentException("Invalid term: " + x0$5);
      }).$bar(() -> MODULE$.term());
      interaction = MODULE$.pow().$times(() -> MODULE$.literal(":").$up$up$up(() -> (left, right) -> MODULE$.interact(left, right)));
      factor = MODULE$.interaction().$times(() -> MODULE$.literal("*").$up$up$up(() -> (left, right) -> MODULE$.cross(left, right)));
      sum = MODULE$.factor().$times(() -> MODULE$.literal("+").$up$up$up(() -> (left, right) -> MODULE$.add(left, right)).$bar(() -> MODULE$.literal("-").$up$up$up(() -> (left, right) -> MODULE$.subtract(left, right))));
      expr = MODULE$.sum().$bar(() -> MODULE$.term());
      formula = MODULE$.label().$tilde(() -> MODULE$.literal("~")).$tilde(() -> MODULE$.expr()).$up$up((x0$6) -> {
         if (x0$6 != null) {
            Parsers..tilde var3 = (Parsers..tilde)x0$6._1();
            Term t = (Term)x0$6._2();
            if (var3 != null) {
               ColumnRef r = (ColumnRef)var3._1();
               String var6 = (String)var3._2();
               if ("~".equals(var6)) {
                  return new ParsedRFormula(r, t.asTerms().terms());
               }
            }
         }

         throw new IllegalArgumentException("Invalid term: " + x0$6);
      });
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

   private Term add(final Term left, final Term right) {
      return left.add(right);
   }

   private Term subtract(final Term left, final Term right) {
      return left.subtract(right);
   }

   private Term interact(final Term left, final Term right) {
      return left.interact(right);
   }

   private Term cross(final Term left, final Term right) {
      return left.add(right).add(left.interact(right));
   }

   private Term power(final Term base, final int degree) {
      List exprs = (List)scala.package..MODULE$.List().fill(degree, () -> base);
      boolean var5 = false;
      scala.collection.immutable..colon.colon var6 = null;
      if (scala.collection.immutable.Nil..MODULE$.equals(exprs)) {
         return EmptyTerm$.MODULE$;
      } else {
         if (exprs instanceof scala.collection.immutable..colon.colon) {
            var5 = true;
            var6 = (scala.collection.immutable..colon.colon)exprs;
            Term x = (Term)var6.head();
            List var9 = var6.next$access$1();
            if (scala.collection.immutable.Nil..MODULE$.equals(var9)) {
               return x;
            }
         }

         if (var5) {
            Term x = (Term)var6.head();
            List xs = var6.next$access$1();
            return (Term)xs.foldLeft(x, (left, right) -> MODULE$.cross(left, right));
         } else {
            throw new MatchError(exprs);
         }
      }
   }

   private Parsers.Parser intercept() {
      return intercept;
   }

   private Parsers.Parser columnRef() {
      return columnRef;
   }

   private Parsers.Parser empty() {
      return empty;
   }

   private Parsers.Parser label() {
      return label;
   }

   private Parsers.Parser dot() {
      return dot;
   }

   private Parsers.Parser parens() {
      return parens;
   }

   private Parsers.Parser term() {
      return term;
   }

   private Parsers.Parser pow() {
      return pow;
   }

   private Parsers.Parser interaction() {
      return interaction;
   }

   private Parsers.Parser factor() {
      return factor;
   }

   private Parsers.Parser sum() {
      return sum;
   }

   private Parsers.Parser expr() {
      return expr;
   }

   private Parsers.Parser formula() {
      return formula;
   }

   public ParsedRFormula parse(final String value) {
      Parsers.ParseResult var3 = this.parseAll(this.formula(), (CharSequence)value);
      if (var3 instanceof Parsers.Success var4) {
         ParsedRFormula result = (ParsedRFormula)var4.result();
         return result;
      } else if (var3 instanceof Parsers.NoSuccess) {
         throw new IllegalArgumentException("Could not parse formula: " + value);
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

   private RFormulaParser$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
