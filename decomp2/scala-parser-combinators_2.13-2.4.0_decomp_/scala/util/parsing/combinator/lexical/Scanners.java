package scala.util.parsing.combinator.lexical;

import scala.MatchError;
import scala.Tuple3;
import scala.reflect.ScalaSignature;
import scala.util.parsing.combinator.Parsers;
import scala.util.parsing.input.CharArrayReader;
import scala.util.parsing.input.CharArrayReader$;
import scala.util.parsing.input.Position;
import scala.util.parsing.input.Reader;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055baB\r\u001b!\u0003\r\t!\n\u0005\u0006]\u0001!\taL\u0003\u0005g\u0001\u0001A\u0007B\u00038\u0001\t\u0005\u0001\bC\u0003@\u0001\u0019\u0005\u0001\tC\u0003Q\u0001\u0019\u0005\u0011\u000bC\u0003V\u0001\u0019\u0005aK\u0002\u0003Y\u0001\u0001I\u0006\u0002\u00031\b\u0005\u0003\u0005\u000b\u0011B1\t\u000b\t<A\u0011A2\t\u000b\t<A\u0011\u00014\t\u0015!<\u0001\u0013!A\u0002B\u0003%\u0011\u000eC\u0004p\u000f\t\u0007I\u0011\u00029\t\rE<\u0001\u0015!\u0003B\u0011\u001d\u0011xA1A\u0005\nMDa\u0001^\u0004!\u0002\u0013a\u0007bB;\b\u0005\u0004%Ia\u001d\u0005\u0007m\u001e\u0001\u000b\u0011\u00027\t\u000b]<A\u0011\u0002=\t\u000bi<A\u0011I>\t\u000f\u0005%q\u0001\"\u0011\u0002\f!1\u00111C\u0004\u0005\u0002ADq!!\u0006\b\t\u0003\t9\u0002C\u0004\u0002\u001a\u001d!\t!a\u0007\t\u000f\u0005\rr\u0001\"\u0001\u0002&\tA1kY1o]\u0016\u00148O\u0003\u0002\u001c9\u00059A.\u001a=jG\u0006d'BA\u000f\u001f\u0003)\u0019w.\u001c2j]\u0006$xN\u001d\u0006\u0003?\u0001\nq\u0001]1sg&twM\u0003\u0002\"E\u0005!Q\u000f^5m\u0015\u0005\u0019\u0013!B:dC2\f7\u0001A\n\u0004\u0001\u0019R\u0003CA\u0014)\u001b\u0005\u0011\u0013BA\u0015#\u0005\u0019\te.\u001f*fMB\u00111\u0006L\u0007\u00029%\u0011Q\u0006\b\u0002\b!\u0006\u00148/\u001a:t\u0003\u0019!\u0013N\\5uIQ\t\u0001\u0007\u0005\u0002(c%\u0011!G\t\u0002\u0005+:LGO\u0001\u0003FY\u0016l\u0007CA\u00146\u0013\t1$E\u0001\u0003DQ\u0006\u0014(!\u0002+pW\u0016t\u0017CA\u001d=!\t9#(\u0003\u0002<E\t9aj\u001c;iS:<\u0007CA\u0014>\u0013\tq$EA\u0002B]f\f!\"\u001a:s_J$vn[3o)\t\t5\t\u0005\u0002C\u00075\t\u0001\u0001C\u0003E\t\u0001\u0007Q)A\u0002ng\u001e\u0004\"AR'\u000f\u0005\u001d[\u0005C\u0001%#\u001b\u0005I%B\u0001&%\u0003\u0019a$o\\8u}%\u0011AJI\u0001\u0007!J,G-\u001a4\n\u00059{%AB*ue&twM\u0003\u0002ME\u0005)Ao\\6f]V\t!\u000bE\u0002C'\u0006K!\u0001\u0016\u0017\u0003\rA\u000b'o]3s\u0003)9\b.\u001b;fgB\f7-Z\u000b\u0002/B\u0019!i\u0015\u001f\u0003\u000fM\u001b\u0017M\u001c8feN\u0011qA\u0017\t\u00047z\u000bU\"\u0001/\u000b\u0005us\u0012!B5oaV$\u0018BA0]\u0005\u0019\u0011V-\u00193fe\u0006\u0011\u0011N\u001c\t\u00047z#\u0014A\u0002\u001fj]&$h\b\u0006\u0002eKB\u0011!i\u0002\u0005\u0006A&\u0001\r!\u0019\u000b\u0003I\u001eDQ\u0001\u0019\u0006A\u0002\u0015\u000b1\u0001\u001f\u00132!\u00159#.\u00117m\u0013\tY'E\u0001\u0004UkBdWm\r\t\u0003\u00056L!A\u001c\u0017\u0003\u000b%s\u0007/\u001e;\u0002\u0007Q|7.F\u0001B\u0003\u0011!xn\u001b\u0011\u0002\u000bI,7\u000f^\u0019\u0016\u00031\faA]3tiF\u0002\u0013!\u0002:fgR\u0014\u0014A\u0002:fgR\u0014\u0004%\u0001\u0003tW&\u0004HCA1z\u0011\u0015\u0001'\u00031\u0001b\u0003\u0019\u0019x.\u001e:dKV\tA\u0010E\u0002~\u0003\u000bi\u0011A \u0006\u0004\u007f\u0006\u0005\u0011\u0001\u00027b]\u001eT!!a\u0001\u0002\t)\fg/Y\u0005\u0004\u0003\u000fq(\u0001D\"iCJ\u001cV-];f]\u000e,\u0017AB8gMN,G/\u0006\u0002\u0002\u000eA\u0019q%a\u0004\n\u0007\u0005E!EA\u0002J]R\fQAZ5sgR\fAA]3tiV\tA-A\u0002q_N,\"!!\b\u0011\u0007m\u000by\"C\u0002\u0002\"q\u0013\u0001\u0002U8tSRLwN\\\u0001\u0006CR,e\u000eZ\u000b\u0003\u0003O\u00012aJA\u0015\u0013\r\tYC\t\u0002\b\u0005>|G.Z1o\u0001"
)
public interface Scanners extends Parsers {
   Object errorToken(final String msg);

   Parsers.Parser token();

   Parsers.Parser whitespace();

   static void $init$(final Scanners $this) {
   }

   public class Scanner extends Reader {
      private final Reader in;
      // $FF: synthetic field
      private final Tuple3 x$1;
      private final Object tok;
      private final Reader rest1;
      private final Reader rest2;
      // $FF: synthetic field
      public final Scanners $outer;

      private Object tok() {
         return this.tok;
      }

      private Reader rest1() {
         return this.rest1;
      }

      private Reader rest2() {
         return this.rest2;
      }

      private Reader skip(final Reader in) {
         return in.atEnd() ? in : in.rest();
      }

      public CharSequence source() {
         return this.in.source();
      }

      public int offset() {
         return this.in.offset();
      }

      public Object first() {
         return this.tok();
      }

      public Scanner rest() {
         return this.scala$util$parsing$combinator$lexical$Scanners$Scanner$$$outer().new Scanner(this.rest2());
      }

      public Position pos() {
         return this.rest1().pos();
      }

      public boolean atEnd() {
         boolean var5;
         if (!this.in.atEnd()) {
            Parsers.ParseResult var2 = this.scala$util$parsing$combinator$lexical$Scanners$Scanner$$$outer().whitespace().apply(this.in);
            if (var2 instanceof Parsers.Success) {
               Parsers.Success var3 = (Parsers.Success)var2;
               Reader in1 = var3.next();
               var5 = in1.atEnd();
            } else {
               var5 = false;
            }

            if (!var5) {
               var5 = false;
               return var5;
            }
         }

         var5 = true;
         return var5;
      }

      // $FF: synthetic method
      public Scanners scala$util$parsing$combinator$lexical$Scanners$Scanner$$$outer() {
         return this.$outer;
      }

      public Scanner(final Reader in) {
         this.in = in;
         if (Scanners.this == null) {
            throw null;
         } else {
            this.$outer = Scanners.this;
            super();
            Parsers.ParseResult var7 = Scanners.this.whitespace().apply(in);
            Tuple3 var10001;
            if (var7 instanceof Parsers.Success) {
               Parsers.Success var8 = (Parsers.Success)var7;
               Reader in1 = var8.next();
               Parsers.ParseResult var10 = Scanners.this.token().apply(in1);
               if (var10 instanceof Parsers.Success) {
                  Parsers.Success var11 = (Parsers.Success)var10;
                  Object tok = var11.result();
                  Reader in2 = var11.next();
                  var10001 = new Tuple3(tok, in1, in2);
               } else {
                  if (!(var10 instanceof Parsers.NoSuccess)) {
                     throw new MatchError(var10);
                  }

                  Parsers.NoSuccess var14 = (Parsers.NoSuccess)var10;
                  var10001 = new Tuple3(Scanners.this.errorToken(var14.msg()), var14.next(), this.skip(var14.next()));
               }
            } else {
               if (!(var7 instanceof Parsers.NoSuccess)) {
                  throw new MatchError(var7);
               }

               Parsers.NoSuccess var15 = (Parsers.NoSuccess)var7;
               var10001 = new Tuple3(Scanners.this.errorToken(var15.msg()), var15.next(), this.skip(var15.next()));
            }

            Tuple3 var6 = var10001;
            if (var6 != null) {
               Object tok = var6._1();
               Reader rest1 = (Reader)var6._2();
               Reader rest2 = (Reader)var6._3();
               this.x$1 = new Tuple3(tok, rest1, rest2);
               this.tok = this.x$1._1();
               this.rest1 = (Reader)this.x$1._2();
               this.rest2 = (Reader)this.x$1._3();
            } else {
               throw new MatchError(var6);
            }
         }
      }

      public Scanner(final String in) {
         this((Reader)(new CharArrayReader(in.toCharArray(), CharArrayReader$.MODULE$.$lessinit$greater$default$2())));
      }
   }
}
