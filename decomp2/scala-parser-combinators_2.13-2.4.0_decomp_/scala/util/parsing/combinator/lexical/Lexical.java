package scala.util.parsing.combinator.lexical;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar.;
import scala.util.parsing.combinator.Parsers;
import scala.util.parsing.combinator.token.Tokens;
import scala.util.parsing.input.Reader;

@ScalaSignature(
   bytes = "\u0006\u0005m2QAB\u0004\u0002\u0002IAQ!\t\u0001\u0005\u0002\tBQ\u0001\n\u0001\u0005\u0002\u0015BQa\f\u0001\u0005\u0002\u0015BQ\u0001\r\u0001\u0005\u0002EBQA\u000f\u0001\u0005\u0002\u0015\u0012q\u0001T3yS\u000e\fGN\u0003\u0002\t\u0013\u00059A.\u001a=jG\u0006d'B\u0001\u0006\f\u0003)\u0019w.\u001c2j]\u0006$xN\u001d\u0006\u0003\u00195\tq\u0001]1sg&twM\u0003\u0002\u000f\u001f\u0005!Q\u000f^5m\u0015\u0005\u0001\u0012!B:dC2\f7\u0001A\n\u0005\u0001M92\u0004\u0005\u0002\u0015+5\tq\"\u0003\u0002\u0017\u001f\t1\u0011I\\=SK\u001a\u0004\"\u0001G\r\u000e\u0003\u001dI!AG\u0004\u0003\u0011M\u001b\u0017M\u001c8feN\u0004\"\u0001H\u0010\u000e\u0003uQ!AH\u0005\u0002\u000bQ|7.\u001a8\n\u0005\u0001j\"A\u0002+pW\u0016t7/\u0001\u0004=S:LGO\u0010\u000b\u0002GA\u0011\u0001\u0004A\u0001\u0007Y\u0016$H/\u001a:\u0016\u0003\u0019\u00022a\n\u0015-\u001b\u0005\u0001\u0011BA\u0015+\u0005\u0019\u0001\u0016M]:fe&\u00111&\u0003\u0002\b!\u0006\u00148/\u001a:t!\t9S&\u0003\u0002/3\t!Q\t\\3n\u0003\u0015!\u0017nZ5u\u0003%\u0019\u0007N]#yG\u0016\u0004H\u000f\u0006\u0002'e!)1\u0007\u0002a\u0001i\u0005\u00111m\u001d\t\u0004)U:\u0014B\u0001\u001c\u0010\u0005)a$/\u001a9fCR,GM\u0010\t\u0003)aJ!!O\b\u0003\t\rC\u0017M]\u0001\u000fo\"LG/Z:qC\u000e,7\t[1s\u0001"
)
public abstract class Lexical implements Scanners, Tokens {
   private volatile Tokens.ErrorToken$ ErrorToken$module;
   private volatile Tokens.EOF$ EOF$module;
   private volatile Parsers.Success$ Success$module;
   private volatile Parsers.NoSuccess$ NoSuccess$module;
   private volatile Parsers.Failure$ Failure$module;
   private volatile Parsers.Error$ Error$module;
   private volatile Parsers.$tilde$ $tilde$module;

   public Tokens.Token errorToken(final String msg) {
      return Tokens.errorToken$(this, msg);
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

   public Tokens.ErrorToken$ ErrorToken() {
      if (this.ErrorToken$module == null) {
         this.ErrorToken$lzycompute$1();
      }

      return this.ErrorToken$module;
   }

   public Tokens.EOF$ EOF() {
      if (this.EOF$module == null) {
         this.EOF$lzycompute$1();
      }

      return this.EOF$module;
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

   public Parsers.Parser letter() {
      return this.elem("letter", (x$1) -> BoxesRunTime.boxToBoolean($anonfun$letter$1(BoxesRunTime.unboxToChar(x$1))));
   }

   public Parsers.Parser digit() {
      return this.elem("digit", (x$2) -> BoxesRunTime.boxToBoolean($anonfun$digit$1(BoxesRunTime.unboxToChar(x$2))));
   }

   public Parsers.Parser chrExcept(final Seq cs) {
      return this.elem("", (ch) -> BoxesRunTime.boxToBoolean($anonfun$chrExcept$1(cs, BoxesRunTime.unboxToChar(ch))));
   }

   public Parsers.Parser whitespaceChar() {
      return this.elem("space char", (ch) -> BoxesRunTime.boxToBoolean($anonfun$whitespaceChar$1(BoxesRunTime.unboxToChar(ch))));
   }

   private final void ErrorToken$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ErrorToken$module == null) {
            this.ErrorToken$module = new Tokens.ErrorToken$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void EOF$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.EOF$module == null) {
            this.EOF$module = new Tokens.EOF$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

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

   // $FF: synthetic method
   public static final boolean $anonfun$letter$1(final char x$1) {
      return .MODULE$.isLetter$extension(scala.Predef..MODULE$.charWrapper(x$1));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$digit$1(final char x$2) {
      return .MODULE$.isDigit$extension(scala.Predef..MODULE$.charWrapper(x$2));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$chrExcept$1(final Seq cs$1, final char ch) {
      return !cs$1.contains(BoxesRunTime.boxToCharacter(ch));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$whitespaceChar$1(final char ch) {
      return ch <= ' ' && ch != 26;
   }

   public Lexical() {
      Parsers.$init$(this);
      Scanners.$init$(this);
      Tokens.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
