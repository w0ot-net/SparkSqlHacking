package scala.util.parsing.combinator.lexical;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.collection.immutable.List;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.util.parsing.combinator.Parsers;
import scala.util.parsing.combinator.token.StdTokens;
import scala.util.parsing.combinator.token.Tokens;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005a\u0001\u0002\b\u0010\u0001iAQ!\n\u0001\u0005\u0002\u0019BQA\t\u0001\u0005\u0002!BQ\u0001\u000e\u0001\u0005\u0002UBQ\u0001\u0010\u0001\u0005\nuBQ!\u0014\u0001\u0005\u00029CQa\u0015\u0001\u0005\u00129Cq\u0001\u0016\u0001C\u0002\u0013\u0005Q\u000b\u0003\u0004j\u0001\u0001\u0006IA\u0016\u0005\bU\u0002\u0011\r\u0011\"\u0001V\u0011\u0019Y\u0007\u0001)A\u0005-\")A\u000e\u0001C\t[\"Aa\u0010\u0001EC\u0002\u0013%\u0001\u0006C\u0003\u0000\u0001\u0011E\u0001F\u0001\u0006Ti\u0012dU\r_5dC2T!\u0001E\t\u0002\u000f1,\u00070[2bY*\u0011!cE\u0001\u000bG>l'-\u001b8bi>\u0014(B\u0001\u000b\u0016\u0003\u001d\u0001\u0018M]:j]\u001eT!AF\f\u0002\tU$\u0018\u000e\u001c\u0006\u00021\u0005)1oY1mC\u000e\u00011c\u0001\u0001\u001c?A\u0011A$H\u0007\u0002\u001f%\u0011ad\u0004\u0002\b\u0019\u0016D\u0018nY1m!\t\u00013%D\u0001\"\u0015\t\u0011\u0013#A\u0003u_.,g.\u0003\u0002%C\tI1\u000b\u001e3U_.,gn]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u001d\u0002\"\u0001\b\u0001\u0016\u0003%\u00022AK\u00160\u001b\u0005\u0001\u0011B\u0001\u0017.\u0005\u0019\u0001\u0016M]:fe&\u0011a&\u0005\u0002\b!\u0006\u00148/\u001a:t!\tQ\u0003'\u0003\u00022e\t)Ak\\6f]&\u00111'\t\u0002\u0007)>\\WM\\:\u0002\u0013%$WM\u001c;DQ\u0006\u0014X#\u0001\u001c\u0011\u0007)Zs\u0007\u0005\u0002+q%\u0011\u0011H\u000f\u0002\u0005\u000b2,W.\u0003\u0002<\u001f\tA1kY1o]\u0016\u00148/A\u0005tiJLgnZ#oIR\u0019\u0011F\u0010#\t\u000b}\"\u0001\u0019\u0001!\u0002\u0013E,x\u000e^3DQ\u0006\u0014\bCA!C\u001b\u00059\u0012BA\"\u0018\u0005\u0011\u0019\u0005.\u0019:\t\u000b\u0015#\u0001\u0019\u0001$\u0002\u000b\rD\u0017M]:\u0011\u0007\u001dS\u0005I\u0004\u0002B\u0011&\u0011\u0011jF\u0001\ba\u0006\u001c7.Y4f\u0013\tYEJ\u0001\u0003MSN$(BA%\u0018\u0003)9\b.\u001b;fgB\f7-Z\u000b\u0002\u001fB\u0019!f\u000b)\u0011\u0005\u0005\u000b\u0016B\u0001*\u0018\u0005\r\te._\u0001\bG>lW.\u001a8u\u0003!\u0011Xm]3sm\u0016$W#\u0001,\u0011\u0007]cf,D\u0001Y\u0015\tI&,A\u0004nkR\f'\r\\3\u000b\u0005m;\u0012AC2pY2,7\r^5p]&\u0011Q\f\u0017\u0002\b\u0011\u0006\u001c\bnU3u!\tyfM\u0004\u0002aIB\u0011\u0011mF\u0007\u0002E*\u00111-G\u0001\u0007yI|w\u000e\u001e \n\u0005\u0015<\u0012A\u0002)sK\u0012,g-\u0003\u0002hQ\n11\u000b\u001e:j]\u001eT!!Z\f\u0002\u0013I,7/\u001a:wK\u0012\u0004\u0013A\u00033fY&l\u0017\u000e^3sg\u0006YA-\u001a7j[&$XM]:!\u00031\u0001(o\\2fgNLE-\u001a8u)\tqGP\u0005\u0003pc>\"h\u0001\u00029\u0001\u00019\u0014A\u0002\u0010:fM&tW-\\3oiz\u0002\"!\u0011:\n\u0005M<\"a\u0002)s_\u0012,8\r\u001e\t\u0003kjl\u0011A\u001e\u0006\u0003ob\f!![8\u000b\u0003e\fAA[1wC&\u00111P\u001e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\u0006{.\u0001\rAX\u0001\u0005]\u0006lW-\u0001\u0004`I\u0016d\u0017.\\\u0001\u0006I\u0016d\u0017.\u001c"
)
public class StdLexical extends Lexical implements StdTokens {
   private Parsers.Parser _delim;
   private final HashSet reserved;
   private final HashSet delimiters;
   private volatile StdTokens.Keyword$ Keyword$module;
   private volatile StdTokens.NumericLit$ NumericLit$module;
   private volatile StdTokens.StringLit$ StringLit$module;
   private volatile StdTokens.Identifier$ Identifier$module;
   private volatile boolean bitmap$0;

   public StdTokens.Keyword$ Keyword() {
      if (this.Keyword$module == null) {
         this.Keyword$lzycompute$1();
      }

      return this.Keyword$module;
   }

   public StdTokens.NumericLit$ NumericLit() {
      if (this.NumericLit$module == null) {
         this.NumericLit$lzycompute$1();
      }

      return this.NumericLit$module;
   }

   public StdTokens.StringLit$ StringLit() {
      if (this.StringLit$module == null) {
         this.StringLit$lzycompute$1();
      }

      return this.StringLit$module;
   }

   public StdTokens.Identifier$ Identifier() {
      if (this.Identifier$module == null) {
         this.Identifier$lzycompute$1();
      }

      return this.Identifier$module;
   }

   public Parsers.Parser token() {
      return this.identChar().$tilde(() -> this.rep(() -> this.identChar().$bar(() -> this.digit()))).$up$up((x0$1) -> {
         if (x0$1 != null) {
            char first = BoxesRunTime.unboxToChar(x0$1._1());
            List rest = (List)x0$1._2();
            return this.processIdent(rest.$colon$colon(BoxesRunTime.boxToCharacter(first)).mkString(""));
         } else {
            throw new MatchError(x0$1);
         }
      }).$bar(() -> this.digit().$tilde(() -> this.rep(() -> this.digit())).$up$up((x0$2) -> {
            if (x0$2 != null) {
               char first = BoxesRunTime.unboxToChar(x0$2._1());
               List rest = (List)x0$2._2();
               return this.new NumericLit(rest.$colon$colon(BoxesRunTime.boxToCharacter(first)).mkString(""));
            } else {
               throw new MatchError(x0$2);
            }
         })).$bar(() -> this.accept(BoxesRunTime.boxToCharacter('\'')).$tilde$greater(() -> this.rep(() -> this.chrExcept(.MODULE$.wrapCharArray(new char[]{'\'', '\n'})))).$greater$greater((chars) -> this.stringEnd('\'', chars))).$bar(() -> this.accept(BoxesRunTime.boxToCharacter('"')).$tilde$greater(() -> this.rep(() -> this.chrExcept(.MODULE$.wrapCharArray(new char[]{'"', '\n'})))).$greater$greater((chars) -> this.stringEnd('"', chars))).$bar(() -> this.accept(BoxesRunTime.boxToCharacter('\u001a')).$up$up$up(() -> this.EOF())).$bar(() -> this.delim()).$bar(() -> this.failure("illegal character"));
   }

   public Parsers.Parser identChar() {
      return this.letter().$bar(() -> this.elem(BoxesRunTime.boxToCharacter('_')));
   }

   private Parsers.Parser stringEnd(final char quoteChar, final List chars) {
      return this.elem(BoxesRunTime.boxToCharacter(quoteChar)).$up$up$up(() -> this.new StringLit(chars.mkString(""))).$bar(() -> this.err("unclosed string literal"));
   }

   public Parsers.Parser whitespace() {
      return this.rep(() -> this.whitespaceChar().$bar(() -> this.accept(BoxesRunTime.boxToCharacter('/')).$tilde(() -> this.accept(BoxesRunTime.boxToCharacter('*'))).$tilde(() -> this.comment())).$bar(() -> this.accept(BoxesRunTime.boxToCharacter('/')).$tilde(() -> this.accept(BoxesRunTime.boxToCharacter('/'))).$tilde(() -> this.rep(() -> this.chrExcept(.MODULE$.wrapCharArray(new char[]{'\u001a', '\n'}))))).$bar(() -> this.accept(BoxesRunTime.boxToCharacter('/')).$tilde(() -> this.accept(BoxesRunTime.boxToCharacter('*'))).$tilde(() -> this.rep(() -> this.elem("", (x$1) -> BoxesRunTime.boxToBoolean($anonfun$whitespace$13(BoxesRunTime.unboxToChar(x$1)))))).$tilde$greater(() -> this.err("unclosed comment"))));
   }

   public Parsers.Parser comment() {
      return this.rep(() -> this.chrExcept(.MODULE$.wrapCharArray(new char[]{'\u001a', '*'}))).$tilde(() -> this.accept(BoxesRunTime.boxToCharacter('*'))).$tilde(() -> this.accept(BoxesRunTime.boxToCharacter('/'))).$up$up((x$2) -> BoxesRunTime.boxToCharacter($anonfun$comment$4(x$2))).$bar(() -> this.rep(() -> this.chrExcept(.MODULE$.wrapCharArray(new char[]{'\u001a', '*'}))).$tilde(() -> this.accept(BoxesRunTime.boxToCharacter('*'))).$tilde(() -> this.comment()).$up$up((x$3) -> BoxesRunTime.boxToCharacter($anonfun$comment$9(x$3))));
   }

   public HashSet reserved() {
      return this.reserved;
   }

   public HashSet delimiters() {
      return this.delimiters;
   }

   public Tokens.Token processIdent(final String name) {
      return (Tokens.Token)(this.reserved().contains(name) ? new StdTokens.Keyword(name) : new StdTokens.Identifier(name));
   }

   private Parsers.Parser _delim$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            String[] d = new String[this.delimiters().size()];
            this.delimiters().copyToArray(d, 0);
            scala.util.Sorting..MODULE$.quickSort(d, scala.math.Ordering.String..MODULE$);
            this._delim = (Parsers.Parser)scala.Predef..MODULE$.wrapRefArray((Object[])d).toList().map((s) -> this.parseDelim$1(s)).foldRight(this.failure("no matching delimiter"), (x, y) -> y.$bar(() -> x));
            this.bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this._delim;
   }

   private Parsers.Parser _delim() {
      return !this.bitmap$0 ? this._delim$lzycompute() : this._delim;
   }

   public Parsers.Parser delim() {
      return this._delim();
   }

   private final void Keyword$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Keyword$module == null) {
            this.Keyword$module = new StdTokens.Keyword$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void NumericLit$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NumericLit$module == null) {
            this.NumericLit$module = new StdTokens.NumericLit$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void StringLit$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.StringLit$module == null) {
            this.StringLit$module = new StdTokens.StringLit$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Identifier$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Identifier$module == null) {
            this.Identifier$module = new StdTokens.Identifier$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$whitespace$13(final char x$1) {
      return true;
   }

   // $FF: synthetic method
   public static final char $anonfun$comment$4(final Parsers.$tilde x$2) {
      return ' ';
   }

   // $FF: synthetic method
   public static final char $anonfun$comment$9(final Parsers.$tilde x$3) {
      return ' ';
   }

   private final Parsers.Parser parseDelim$1(final String s) {
      return this.accept(scala.Predef..MODULE$.wrapString(s).toList(), scala.Predef..MODULE$.$conforms()).$up$up((x$4) -> this.new Keyword(s));
   }

   public StdLexical() {
      StdTokens.$init$(this);
      this.reserved = new HashSet();
      this.delimiters = new HashSet();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
