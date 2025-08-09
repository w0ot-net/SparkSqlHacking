package scala.util.parsing.combinator.syntactical;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.util.parsing.combinator.Parsers;
import scala.util.parsing.combinator.lexical.StdLexical;
import scala.util.parsing.input.Reader;

@ScalaSignature(
   bytes = "\u0006\u0005\t3AAB\u0004\u0001%!)1\u0004\u0001C\u00019\u0015!a\u0004\u0001\u0001 \u0011\u001d)\u0003A1A\u0005\u0002\u0019Ba\u0001\f\u0001!\u0002\u00139\u0003\"B\u0017\u0001\t\u0007r#\u0001F*uC:$\u0017M\u001d3U_.,g\u000eU1sg\u0016\u00148O\u0003\u0002\t\u0013\u0005Y1/\u001f8uC\u000e$\u0018nY1m\u0015\tQ1\"\u0001\u0006d_6\u0014\u0017N\\1u_JT!\u0001D\u0007\u0002\u000fA\f'o]5oO*\u0011abD\u0001\u0005kRLGNC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001A\n\u0018!\t!R#D\u0001\u0010\u0013\t1rB\u0001\u0004B]f\u0014VM\u001a\t\u00031ei\u0011aB\u0005\u00035\u001d\u0011qb\u0015;e)>\\WM\u001c)beN,'o]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003u\u0001\"\u0001\u0007\u0001\u0003\rQ{7.\u001a8t!\t\u00013%D\u0001\"\u0015\t\u0011\u0013\"A\u0003u_.,g.\u0003\u0002%C\tI1\u000b\u001e3U_.,gn]\u0001\bY\u0016D\u0018nY1m+\u00059\u0003C\u0001\u0015+\u001b\u0005I#BA\u0013\n\u0013\tY\u0013F\u0001\u0006Ti\u0012dU\r_5dC2\f\u0001\u0002\\3yS\u000e\fG\u000eI\u0001\bW\u0016Lxo\u001c:e)\ty\u0003\tE\u00021cUj\u0011\u0001A\u0005\u0003eM\u0012a\u0001U1sg\u0016\u0014\u0018B\u0001\u001b\n\u0005\u001d\u0001\u0016M]:feN\u0004\"AN\u001f\u000f\u0005]Z\u0004C\u0001\u001d\u0010\u001b\u0005I$B\u0001\u001e\u0012\u0003\u0019a$o\\8u}%\u0011AhD\u0001\u0007!J,G-\u001a4\n\u0005yz$AB*ue&twM\u0003\u0002=\u001f!)\u0011)\u0002a\u0001k\u0005)1\r[1sg\u0002"
)
public class StandardTokenParsers implements StdTokenParsers {
   private final StdLexical lexical;
   private HashMap keywordCache;
   private volatile Parsers.Success$ Success$module;
   private volatile Parsers.NoSuccess$ NoSuccess$module;
   private volatile Parsers.Failure$ Failure$module;
   private volatile Parsers.Error$ Error$module;
   private volatile Parsers.$tilde$ $tilde$module;

   public Parsers.Parser numericLit() {
      return StdTokenParsers.numericLit$(this);
   }

   public Parsers.Parser stringLit() {
      return StdTokenParsers.stringLit$(this);
   }

   public Parsers.Parser ident() {
      return StdTokenParsers.ident$(this);
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
      return Parsers.accept$(this, (Object)es, (Function1)f);
   }

   public Parsers.Parser accept(final String expected, final PartialFunction f) {
      return Parsers.accept$(this, (String)expected, (PartialFunction)f);
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

   public Parsers.Parser err(final String msg) {
      return Parsers.err$(this, msg);
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

   public Parsers.Parser positioned(final Function0 p) {
      return Parsers.positioned$(this, p);
   }

   public Parsers.Parser phrase(final Parsers.Parser p) {
      return Parsers.phrase$(this, p);
   }

   public Function1 mkList() {
      return Parsers.mkList$(this);
   }

   public HashMap keywordCache() {
      return this.keywordCache;
   }

   public void scala$util$parsing$combinator$syntactical$StdTokenParsers$_setter_$keywordCache_$eq(final HashMap x$1) {
      this.keywordCache = x$1;
   }

   public Parsers.Success$ Success() {
      if (this.Success$module == null) {
         this.Success$lzycompute$1();
      }

      return this.Success$module;
   }

   public Parsers.NoSuccess$ NoSuccess() {
      if (this.NoSuccess$module == null) {
         this.NoSuccess$lzycompute$1();
      }

      return this.NoSuccess$module;
   }

   public Parsers.Failure$ Failure() {
      if (this.Failure$module == null) {
         this.Failure$lzycompute$1();
      }

      return this.Failure$module;
   }

   public Parsers.Error$ Error() {
      if (this.Error$module == null) {
         this.Error$lzycompute$1();
      }

      return this.Error$module;
   }

   public Parsers.$tilde$ $tilde() {
      if (this.$tilde$module == null) {
         this.$tilde$lzycompute$1();
      }

      return this.$tilde$module;
   }

   public StdLexical lexical() {
      return this.lexical;
   }

   public Parsers.Parser keyword(final String chars) {
      return !this.lexical().reserved().contains(chars) && !this.lexical().delimiters().contains(chars) ? this.failure((new StringBuilder(135)).append("You are trying to parse \"").append(chars).append("\", but it is neither contained in the delimiters list, nor in the reserved keyword list of your lexical object").toString()) : StdTokenParsers.keyword$(this, chars);
   }

   private final void Success$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Success$module == null) {
            this.Success$module = new Parsers.Success$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void NoSuccess$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NoSuccess$module == null) {
            this.NoSuccess$module = new Parsers.NoSuccess$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Failure$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Failure$module == null) {
            this.Failure$module = new Parsers.Failure$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Error$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Error$module == null) {
            this.Error$module = new Parsers.Error$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void $tilde$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.$tilde$module == null) {
            this.$tilde$module = new Parsers.$tilde$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public StandardTokenParsers() {
      Parsers.$init$(this);
      StdTokenParsers.$init$(this);
      this.lexical = new StdLexical();
      Statics.releaseFence();
   }
}
