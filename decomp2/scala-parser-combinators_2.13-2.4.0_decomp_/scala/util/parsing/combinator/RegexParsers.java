package scala.util.parsing.combinator;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.util.matching.Regex;
import scala.util.parsing.input.CharSequenceReader;
import scala.util.parsing.input.PagedSeq$;
import scala.util.parsing.input.PagedSeqReader;
import scala.util.parsing.input.Reader;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055gaB\u000b\u0017!\u0003\r\ta\b\u0005\u0006Q\u0001!\t!K\u0003\u0005[\u0001\u0001a\u0006C\u00042\u0001\t\u0007I\u0011\u0003\u001a\t\u000be\u0002A\u0011\u0001\u001e\t\u000by\u0002A\u0011C \t\u000b=\u0003A1\u0001)\t\u000b\t\u0004A1A2\t\u000b\u0019\u0004A\u0011I4\t\u000bu\u0004A\u0011\u0002@\t\u000f\u0005E\u0001\u0001\"\u0011\u0002\u0014!9\u00111\u0004\u0001\u0005B\u0005u\u0001bBA\u0015\u0001\u0011\u0005\u00111\u0006\u0005\b\u0003S\u0001A\u0011AA$\u0011\u001d\tI\u0003\u0001C\u0001\u0003/Bq!!\u001d\u0001\t\u0003\t\u0019\bC\u0004\u0002r\u0001!\t!a!\t\u000f\u0005E\u0004\u0001\"\u0001\u0002\u0014\"q\u00111\u0015\u0001\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002&\u0006M\u0006BDA[\u0001A\u0005\u0019\u0011!A\u0005\n\u0005]\u00161\u0018\u0005\u000f\u0003{\u0003\u0001\u0013aA\u0001\u0002\u0013%\u0011qXAf\u00051\u0011VmZ3y!\u0006\u00148/\u001a:t\u0015\t9\u0002$\u0001\u0006d_6\u0014\u0017N\\1u_JT!!\u0007\u000e\u0002\u000fA\f'o]5oO*\u00111\u0004H\u0001\u0005kRLGNC\u0001\u001e\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001\u0001\u0011%!\t\t#%D\u0001\u001d\u0013\t\u0019CD\u0001\u0004B]f\u0014VM\u001a\t\u0003K\u0019j\u0011AF\u0005\u0003OY\u0011q\u0001U1sg\u0016\u00148/\u0001\u0004%S:LG\u000f\n\u000b\u0002UA\u0011\u0011eK\u0005\u0003Yq\u0011A!\u00168ji\n!Q\t\\3n!\t\ts&\u0003\u000219\t!1\t[1s\u0003)9\b.\u001b;f'B\f7-Z\u000b\u0002gA\u0011AgN\u0007\u0002k)\u0011aGG\u0001\t[\u0006$8\r[5oO&\u0011\u0001(\u000e\u0002\u0006%\u0016<W\r_\u0001\u000fg.L\u0007o\u00165ji\u0016\u001c\b/Y2f+\u0005Y\u0004CA\u0011=\u0013\tiDDA\u0004C_>dW-\u00198\u0002!!\fg\u000e\u001a7f/\"LG/Z*qC\u000e,Gc\u0001!D\u001bB\u0011\u0011%Q\u0005\u0003\u0005r\u00111!\u00138u\u0011\u0015!U\u00011\u0001F\u0003\u0019\u0019x.\u001e:dKB\u0011aiS\u0007\u0002\u000f*\u0011\u0001*S\u0001\u0005Y\u0006twMC\u0001K\u0003\u0011Q\u0017M^1\n\u00051;%\u0001D\"iCJ\u001cV-];f]\u000e,\u0007\"\u0002(\u0006\u0001\u0004\u0001\u0015AB8gMN,G/A\u0004mSR,'/\u00197\u0015\u0005E\u0003\u0007c\u0001*T+6\t\u0001!\u0003\u0002UM\t1\u0001+\u0019:tKJ\u0004\"AV/\u000f\u0005][\u0006C\u0001-\u001d\u001b\u0005I&B\u0001.\u001f\u0003\u0019a$o\\8u}%\u0011A\fH\u0001\u0007!J,G-\u001a4\n\u0005y{&AB*ue&twM\u0003\u0002]9!)\u0011M\u0002a\u0001+\u0006\t1/A\u0003sK\u001e,\u0007\u0010\u0006\u0002RI\")Qm\u0002a\u0001g\u0005\t!/\u0001\u0006q_NLG/[8oK\u0012,\"\u0001\u001b7\u0015\u0005%D\bc\u0001*TUB\u00111\u000e\u001c\u0007\u0001\t\u0015i\u0007B1\u0001o\u0005\u0005!\u0016CA8s!\t\t\u0003/\u0003\u0002r9\t9aj\u001c;iS:<\u0007CA:w\u001b\u0005!(BA;\u0019\u0003\u0015Ig\u000e];u\u0013\t9HO\u0001\u0006Q_NLG/[8oC2Da!\u001f\u0005\u0005\u0002\u0004Q\u0018!\u00019\u0011\u0007\u0005Z\u0018.\u0003\u0002}9\tAAHY=oC6,g(\u0001\u0002xgV\u0019q0!\u0002\u0015\t\u0005\u0005\u0011q\u0002\t\u0005%N\u000b\u0019\u0001E\u0002l\u0003\u000b!a!\\\u0005C\u0002\u0005\u001d\u0011cA8\u0002\nA\u0019\u0011%a\u0003\n\u0007\u00055ADA\u0002B]fDa!_\u0005A\u0002\u0005\u0005\u0011aA3seR!\u0011QCA\f!\r\u00116k\u001c\u0005\u0007\u00033Q\u0001\u0019A+\u0002\u00075\u001cx-\u0001\u0004qQJ\f7/Z\u000b\u0005\u0003?\t)\u0003\u0006\u0003\u0002\"\u0005\u001d\u0002\u0003\u0002*T\u0003G\u00012a[A\u0013\t\u0019i7B1\u0001\u0002\b!1\u0011p\u0003a\u0001\u0003C\tQ\u0001]1sg\u0016,B!!\f\u00028Q1\u0011qFA\u001d\u0003{\u0001RAUA\u0019\u0003kI1!a\r'\u0005-\u0001\u0016M]:f%\u0016\u001cX\u000f\u001c;\u0011\u0007-\f9\u0004\u0002\u0004n\u0019\t\u0007\u0011q\u0001\u0005\u0007s2\u0001\r!a\u000f\u0011\tI\u001b\u0016Q\u0007\u0005\b\u0003\u007fa\u0001\u0019AA!\u0003\tIg\u000e\u0005\u0003t\u0003\u0007r\u0013bAA#i\n1!+Z1eKJ,B!!\u0013\u0002PQ1\u00111JA)\u0003+\u0002RAUA\u0019\u0003\u001b\u00022a[A(\t\u0019iWB1\u0001\u0002\b!1\u00110\u0004a\u0001\u0003'\u0002BAU*\u0002N!1\u0011qH\u0007A\u0002\u0015+B!!\u0017\u0002`Q1\u00111LA1\u0003K\u0002RAUA\u0019\u0003;\u00022a[A0\t\u0019igB1\u0001\u0002\b!1\u0011P\u0004a\u0001\u0003G\u0002BAU*\u0002^!9\u0011q\b\bA\u0002\u0005\u001d\u0004\u0003BA5\u0003_j!!a\u001b\u000b\u0007\u00055\u0014*\u0001\u0002j_&!\u0011QIA6\u0003!\u0001\u0018M]:f\u00032dW\u0003BA;\u0003w\"b!a\u001e\u0002~\u0005\u0005\u0005#\u0002*\u00022\u0005e\u0004cA6\u0002|\u00111Qn\u0004b\u0001\u0003\u000fAa!_\bA\u0002\u0005}\u0004\u0003\u0002*T\u0003sBq!a\u0010\u0010\u0001\u0004\t\t%\u0006\u0003\u0002\u0006\u0006-ECBAD\u0003\u001b\u000b\t\nE\u0003S\u0003c\tI\tE\u0002l\u0003\u0017#a!\u001c\tC\u0002\u0005\u001d\u0001BB=\u0011\u0001\u0004\ty\t\u0005\u0003S'\u0006%\u0005bBA !\u0001\u0007\u0011qM\u000b\u0005\u0003+\u000bY\n\u0006\u0004\u0002\u0018\u0006u\u0015\u0011\u0015\t\u0006%\u0006E\u0012\u0011\u0014\t\u0004W\u0006mEAB7\u0012\u0005\u0004\t9\u0001\u0003\u0004z#\u0001\u0007\u0011q\u0014\t\u0005%N\u000bI\n\u0003\u0004\u0002@E\u0001\r!R\u0001\u0011gV\u0004XM\u001d\u0013q_NLG/[8oK\u0012,B!a*\u0002.R!\u0011\u0011VAX!\u0011\u00116+a+\u0011\u0007-\fi\u000bB\u0003n%\t\u0007a\u000eC\u0004z%\u0011\u0005\r!!-\u0011\t\u0005Z\u0018\u0011V\u0005\u0003M\u001a\n\u0011b];qKJ$SM\u001d:\u0015\t\u0005U\u0011\u0011\u0018\u0005\u0007\u00033\u0019\u0002\u0019A+\n\u0007\u0005Ea%\u0001\u0007tkB,'\u000f\n9ie\u0006\u001cX-\u0006\u0003\u0002B\u0006\u001dG\u0003BAb\u0003\u0013\u0004BAU*\u0002FB\u00191.a2\u0005\r5$\"\u0019AA\u0004\u0011\u0019IH\u00031\u0001\u0002D&\u0019\u00111\u0004\u0014"
)
public interface RegexParsers extends Parsers {
   void scala$util$parsing$combinator$RegexParsers$_setter_$whiteSpace_$eq(final Regex x$1);

   // $FF: synthetic method
   Parsers.Parser scala$util$parsing$combinator$RegexParsers$$super$positioned(final Function0 p);

   // $FF: synthetic method
   Parsers.Parser scala$util$parsing$combinator$RegexParsers$$super$err(final String msg);

   // $FF: synthetic method
   Parsers.Parser scala$util$parsing$combinator$RegexParsers$$super$phrase(final Parsers.Parser p);

   Regex whiteSpace();

   // $FF: synthetic method
   static boolean skipWhitespace$(final RegexParsers $this) {
      return $this.skipWhitespace();
   }

   default boolean skipWhitespace() {
      return this.whiteSpace().toString().length() > 0;
   }

   // $FF: synthetic method
   static int handleWhiteSpace$(final RegexParsers $this, final CharSequence source, final int offset) {
      return $this.handleWhiteSpace(source, offset);
   }

   default int handleWhiteSpace(final CharSequence source, final int offset) {
      if (this.skipWhitespace()) {
         Option var4 = this.whiteSpace().findPrefixMatchOf(new SubSequence(source, offset));
         if (var4 instanceof Some) {
            Some var5 = (Some)var4;
            Regex.Match matched = (Regex.Match)var5.value();
            return offset + matched.end();
         } else if (.MODULE$.equals(var4)) {
            return offset;
         } else {
            throw new MatchError(var4);
         }
      } else {
         return offset;
      }
   }

   // $FF: synthetic method
   static Parsers.Parser literal$(final RegexParsers $this, final String s) {
      return $this.literal(s);
   }

   default Parsers.Parser literal(final String s) {
      return new Parsers.Parser(s) {
         // $FF: synthetic field
         private final RegexParsers $outer;
         private final String s$1;

         public Parsers.ParseResult apply(final Reader in) {
            CharSequence source = in.source();
            int offset = in.offset();
            int start = this.$outer.handleWhiteSpace(source, offset);
            int i = 0;

            int j;
            for(j = start; i < this.s$1.length() && j < source.length() && this.s$1.charAt(i) == source.charAt(j); ++j) {
               ++i;
            }

            if (i == this.s$1.length()) {
               return this.$outer.Success(source.subSequence(start, j).toString(), in.drop(j - offset), .MODULE$);
            } else {
               String found = start == source.length() ? "end of source" : (new StringBuilder(2)).append("'").append(source.charAt(start)).append("'").toString();
               return this.$outer.new Failure((new StringBuilder(22)).append("'").append(this.s$1).append("' expected but ").append(found).append(" found").toString(), in.drop(start - offset));
            }
         }

         public {
            if (RegexParsers.this == null) {
               throw null;
            } else {
               this.$outer = RegexParsers.this;
               this.s$1 = s$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static Parsers.Parser regex$(final RegexParsers $this, final Regex r) {
      return $this.regex(r);
   }

   default Parsers.Parser regex(final Regex r) {
      return new Parsers.Parser(r) {
         // $FF: synthetic field
         private final RegexParsers $outer;
         private final Regex r$1;

         public Parsers.ParseResult apply(final Reader in) {
            CharSequence source = in.source();
            int offset = in.offset();
            int start = this.$outer.handleWhiteSpace(source, offset);
            Option var6 = this.r$1.findPrefixMatchOf(new SubSequence(source, start));
            if (var6 instanceof Some) {
               Some var7 = (Some)var6;
               Regex.Match matched = (Regex.Match)var7.value();
               return this.$outer.Success(source.subSequence(start, start + matched.end()).toString(), in.drop(start + matched.end() - offset), .MODULE$);
            } else if (.MODULE$.equals(var6)) {
               String found = start == source.length() ? "end of source" : (new StringBuilder(2)).append("'").append(source.charAt(start)).append("'").toString();
               return this.$outer.new Failure((new StringBuilder(44)).append("string matching regex '").append(this.r$1).append("' expected but ").append(found).append(" found").toString(), in.drop(start - offset));
            } else {
               throw new MatchError(var6);
            }
         }

         public {
            if (RegexParsers.this == null) {
               throw null;
            } else {
               this.$outer = RegexParsers.this;
               this.r$1 = r$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static Parsers.Parser positioned$(final RegexParsers $this, final Function0 p) {
      return $this.positioned(p);
   }

   default Parsers.Parser positioned(final Function0 p) {
      Parsers.Parser pp = this.scala$util$parsing$combinator$RegexParsers$$super$positioned(p);
      return new Parsers.Parser(pp) {
         // $FF: synthetic field
         private final RegexParsers $outer;
         private final Parsers.Parser pp$1;

         public Parsers.ParseResult apply(final Reader in) {
            int offset = in.offset();
            int start = this.$outer.handleWhiteSpace(in.source(), offset);
            return this.pp$1.apply(in.drop(start - offset));
         }

         public {
            if (RegexParsers.this == null) {
               throw null;
            } else {
               this.$outer = RegexParsers.this;
               this.pp$1 = pp$1;
            }
         }
      };
   }

   private Parsers.Parser ws(final Parsers.Parser p) {
      return new Parsers.Parser(p) {
         // $FF: synthetic field
         private final RegexParsers $outer;
         private final Parsers.Parser p$1;

         public Parsers.ParseResult apply(final Reader in) {
            int offset = in.offset();
            int start = this.$outer.handleWhiteSpace(in.source(), offset);
            return this.p$1.apply(in.drop(start - offset));
         }

         public {
            if (RegexParsers.this == null) {
               throw null;
            } else {
               this.$outer = RegexParsers.this;
               this.p$1 = p$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static Parsers.Parser err$(final RegexParsers $this, final String msg) {
      return $this.err(msg);
   }

   default Parsers.Parser err(final String msg) {
      return this.ws(this.scala$util$parsing$combinator$RegexParsers$$super$err(msg));
   }

   // $FF: synthetic method
   static Parsers.Parser phrase$(final RegexParsers $this, final Parsers.Parser p) {
      return $this.phrase(p);
   }

   default Parsers.Parser phrase(final Parsers.Parser p) {
      return this.scala$util$parsing$combinator$RegexParsers$$super$phrase(p.$less$tilde(() -> this.regex(scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("")))));
   }

   // $FF: synthetic method
   static Parsers.ParseResult parse$(final RegexParsers $this, final Parsers.Parser p, final Reader in) {
      return $this.parse(p, in);
   }

   default Parsers.ParseResult parse(final Parsers.Parser p, final Reader in) {
      return p.apply(in);
   }

   // $FF: synthetic method
   static Parsers.ParseResult parse$(final RegexParsers $this, final Parsers.Parser p, final CharSequence in) {
      return $this.parse(p, in);
   }

   default Parsers.ParseResult parse(final Parsers.Parser p, final CharSequence in) {
      return p.apply(new CharSequenceReader(in));
   }

   // $FF: synthetic method
   static Parsers.ParseResult parse$(final RegexParsers $this, final Parsers.Parser p, final java.io.Reader in) {
      return $this.parse(p, in);
   }

   default Parsers.ParseResult parse(final Parsers.Parser p, final java.io.Reader in) {
      return p.apply(new PagedSeqReader(PagedSeq$.MODULE$.fromReader(in)));
   }

   // $FF: synthetic method
   static Parsers.ParseResult parseAll$(final RegexParsers $this, final Parsers.Parser p, final Reader in) {
      return $this.parseAll(p, in);
   }

   default Parsers.ParseResult parseAll(final Parsers.Parser p, final Reader in) {
      return this.parse(this.phrase(p), in);
   }

   // $FF: synthetic method
   static Parsers.ParseResult parseAll$(final RegexParsers $this, final Parsers.Parser p, final java.io.Reader in) {
      return $this.parseAll(p, in);
   }

   default Parsers.ParseResult parseAll(final Parsers.Parser p, final java.io.Reader in) {
      return this.parse(this.phrase(p), in);
   }

   // $FF: synthetic method
   static Parsers.ParseResult parseAll$(final RegexParsers $this, final Parsers.Parser p, final CharSequence in) {
      return $this.parseAll(p, in);
   }

   default Parsers.ParseResult parseAll(final Parsers.Parser p, final CharSequence in) {
      return this.parse(this.phrase(p), in);
   }

   static void $init$(final RegexParsers $this) {
      $this.scala$util$parsing$combinator$RegexParsers$_setter_$whiteSpace_$eq(scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("\\s+")));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
