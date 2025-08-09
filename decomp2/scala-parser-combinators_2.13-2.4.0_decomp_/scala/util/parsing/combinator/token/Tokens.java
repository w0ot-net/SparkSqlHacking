package scala.util.parsing.combinator.token;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%eaB\u0014)!\u0003\r\ta\r\u0005\u0006q\u0001!\t!\u000f\u0004\u0006{\u0001\t\tA\u0010\u0005\u0006\u007f\t!\t\u0001\u0011\u0005\u0006\u0007\n1\t\u0001\u0012\u0004\u0005!\u0002\u0001\u0015\u000b\u0003\u0005_\u000b\tU\r\u0011\"\u0001E\u0011!yVA!E!\u0002\u0013)\u0005\"B \u0006\t\u0003\u0001\u0007\"B\"\u0006\t\u0003\u0019\u0007bB6\u0006\u0003\u0003%\t\u0001\u001c\u0005\b]\u0016\t\n\u0011\"\u0001p\u0011\u001dQX!!A\u0005B\rDqa_\u0003\u0002\u0002\u0013\u0005A\u0010C\u0005\u0002\u0002\u0015\t\t\u0011\"\u0001\u0002\u0004!I\u0011qB\u0003\u0002\u0002\u0013\u0005\u0013\u0011\u0003\u0005\n\u0003?)\u0011\u0011!C\u0001\u0003CA\u0011\"a\u000b\u0006\u0003\u0003%\t%!\f\t\u0013\u0005ER!!A\u0005B\u0005M\u0002\"CA\u001b\u000b\u0005\u0005I\u0011IA\u001c\u0011%\tI$BA\u0001\n\u0003\nYdB\u0005\u0002@\u0001\t\t\u0011#\u0001\u0002B\u0019A\u0001\u000bAA\u0001\u0012\u0003\t\u0019\u0005\u0003\u0004@-\u0011\u0005\u00111\f\u0005\n\u0003k1\u0012\u0011!C#\u0003oA\u0011\"!\u0018\u0017\u0003\u0003%\t)a\u0018\t\u0013\u0005\rd#!A\u0005\u0002\u0006\u0015taBA9\u0001!\u0005\u00151\u000f\u0004\b\u0003k\u0002\u0001\u0012QA<\u0011\u0019yD\u0004\"\u0001\u0002z!)1\t\bC\u0001G\"9!\u0010HA\u0001\n\u0003\u001a\u0007bB>\u001d\u0003\u0003%\t\u0001 \u0005\n\u0003\u0003a\u0012\u0011!C\u0001\u0003wB\u0011\"a\u0004\u001d\u0003\u0003%\t%!\u0005\t\u0013\u0005}A$!A\u0005\u0002\u0005}\u0004\"CA\u00199\u0005\u0005I\u0011IA\u001a\u0011%\t)\u0004HA\u0001\n\u0003\n9\u0004C\u0004\u0002\u0004\u0002!\t!!\"\u0003\rQ{7.\u001a8t\u0015\tI#&A\u0003u_.,gN\u0003\u0002,Y\u0005Q1m\\7cS:\fGo\u001c:\u000b\u00055r\u0013a\u00029beNLgn\u001a\u0006\u0003_A\nA!\u001e;jY*\t\u0011'A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001!\u0004CA\u001b7\u001b\u0005\u0001\u0014BA\u001c1\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012A\u000f\t\u0003kmJ!\u0001\u0010\u0019\u0003\tUs\u0017\u000e\u001e\u0002\u0006)>\\WM\\\n\u0003\u0005Q\na\u0001P5oSRtD#A!\u0011\u0005\t\u0013Q\"\u0001\u0001\u0002\u000b\rD\u0017M]:\u0016\u0003\u0015\u0003\"AR'\u000f\u0005\u001d[\u0005C\u0001%1\u001b\u0005I%B\u0001&3\u0003\u0019a$o\\8u}%\u0011A\nM\u0001\u0007!J,G-\u001a4\n\u00059{%AB*ue&twM\u0003\u0002Ma\tQQI\u001d:peR{7.\u001a8\u0014\t\u0015\t%+\u0016\t\u0003kMK!\u0001\u0016\u0019\u0003\u000fA\u0013x\u000eZ;diB\u0011ak\u0017\b\u0003/fs!\u0001\u0013-\n\u0003EJ!A\u0017\u0019\u0002\u000fA\f7m[1hK&\u0011A,\u0018\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u00035B\n1!\\:h\u0003\u0011i7o\u001a\u0011\u0015\u0005\u0005\u0014\u0007C\u0001\"\u0006\u0011\u0015q\u0006\u00021\u0001F+\u0005!\u0007CA3k\u001b\u00051'BA4i\u0003\u0011a\u0017M\\4\u000b\u0003%\fAA[1wC&\u0011aJZ\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002b[\"9aL\u0003I\u0001\u0002\u0004)\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002a*\u0012Q)]\u0016\u0002eB\u00111\u000f_\u0007\u0002i*\u0011QO^\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u001e\u0019\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002zi\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005i\bCA\u001b\u007f\u0013\ty\bGA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u0006\u0005-\u0001cA\u001b\u0002\b%\u0019\u0011\u0011\u0002\u0019\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002\u000e9\t\t\u00111\u0001~\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\u0003\t\u0007\u0003+\tY\"!\u0002\u000e\u0005\u0005]!bAA\ra\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005u\u0011q\u0003\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002$\u0005%\u0002cA\u001b\u0002&%\u0019\u0011q\u0005\u0019\u0003\u000f\t{w\u000e\\3b]\"I\u0011Q\u0002\t\u0002\u0002\u0003\u0007\u0011QA\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002e\u0003_A\u0001\"!\u0004\u0012\u0003\u0003\u0005\r!`\u0001\tQ\u0006\u001c\bnQ8eKR\tQ0\u0001\u0005u_N#(/\u001b8h)\u0005!\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002$\u0005u\u0002\"CA\u0007)\u0005\u0005\t\u0019AA\u0003\u0003))%O]8s)>\\WM\u001c\t\u0003\u0005Z\u0019RAFA#\u0003#\u0002b!a\u0012\u0002N\u0015\u000bWBAA%\u0015\r\tY\u0005M\u0001\beVtG/[7f\u0013\u0011\ty%!\u0013\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002T\u0005eSBAA+\u0015\r\t9\u0006[\u0001\u0003S>L1\u0001XA+)\t\t\t%A\u0003baBd\u0017\u0010F\u0002b\u0003CBQAX\rA\u0002\u0015\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002h\u00055\u0004\u0003B\u001b\u0002j\u0015K1!a\u001b1\u0005\u0019y\u0005\u000f^5p]\"A\u0011q\u000e\u000e\u0002\u0002\u0003\u0007\u0011-A\u0002yIA\n1!R(G!\t\u0011EDA\u0002F\u001f\u001a\u001bB\u0001H!S+R\u0011\u00111\u000f\u000b\u0005\u0003\u000b\ti\b\u0003\u0005\u0002\u000e\u0005\n\t\u00111\u0001~)\u0011\t\u0019#!!\t\u0013\u000551%!AA\u0002\u0005\u0015\u0011AC3se>\u0014Hk\\6f]R\u0019\u0011)a\"\t\u000by3\u0003\u0019A#"
)
public interface Tokens {
   ErrorToken$ ErrorToken();

   EOF$ EOF();

   // $FF: synthetic method
   static Token errorToken$(final Tokens $this, final String msg) {
      return $this.errorToken(msg);
   }

   default Token errorToken(final String msg) {
      return new ErrorToken(msg);
   }

   static void $init$(final Tokens $this) {
   }

   public abstract class Token {
      // $FF: synthetic field
      public final Tokens $outer;

      public abstract String chars();

      // $FF: synthetic method
      public Tokens scala$util$parsing$combinator$token$Tokens$Token$$$outer() {
         return this.$outer;
      }

      public Token() {
         if (Tokens.this == null) {
            throw null;
         } else {
            this.$outer = Tokens.this;
            super();
         }
      }
   }

   public class ErrorToken extends Token implements Product, Serializable {
      private final String msg;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String msg() {
         return this.msg;
      }

      public String chars() {
         return (new StringBuilder(11)).append("*** error: ").append(this.msg()).toString();
      }

      public ErrorToken copy(final String msg) {
         return this.scala$util$parsing$combinator$token$Tokens$ErrorToken$$$outer().new ErrorToken(msg);
      }

      public String copy$default$1() {
         return this.msg();
      }

      public String productPrefix() {
         return "ErrorToken";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.msg();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ErrorToken;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "msg";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label52: {
               if (x$1 instanceof ErrorToken && ((ErrorToken)x$1).scala$util$parsing$combinator$token$Tokens$ErrorToken$$$outer() == this.scala$util$parsing$combinator$token$Tokens$ErrorToken$$$outer()) {
                  label42: {
                     ErrorToken var4 = (ErrorToken)x$1;
                     String var10000 = this.msg();
                     String var5 = var4.msg();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label42;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label42;
                     }

                     if (var4.canEqual(this)) {
                        break label52;
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      // $FF: synthetic method
      public Tokens scala$util$parsing$combinator$token$Tokens$ErrorToken$$$outer() {
         return this.$outer;
      }

      public ErrorToken(final String msg) {
         this.msg = msg;
         Product.$init$(this);
      }
   }

   public class ErrorToken$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final Tokens $outer;

      public final String toString() {
         return "ErrorToken";
      }

      public ErrorToken apply(final String msg) {
         return this.$outer.new ErrorToken(msg);
      }

      public Option unapply(final ErrorToken x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.msg()));
      }

      public ErrorToken$() {
         if (Tokens.this == null) {
            throw null;
         } else {
            this.$outer = Tokens.this;
            super();
         }
      }
   }

   public class EOF$ extends Token implements Product, Serializable {
      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String chars() {
         return "<eof>";
      }

      public String productPrefix() {
         return "EOF";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof EOF$;
      }

      public int hashCode() {
         return 68828;
      }

      public String toString() {
         return "EOF";
      }

      public EOF$() {
         Product.$init$(this);
      }
   }
}
