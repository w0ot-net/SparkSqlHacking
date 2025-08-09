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
   bytes = "\u0006\u0005\t\u0015da\u0002,X!\u0003\r\tA\u0019\u0005\u0006W\u0002!\t\u0001\u001c\u0004\u0005a\u0002\u0001\u0015\u000f\u0003\u0006\u0002\f\t\u0011)\u001a!C\u0001\u0003\u001bA!\"a\b\u0003\u0005#\u0005\u000b\u0011BA\b\u0011\u001d\t\tC\u0001C\u0001\u0003GAq!!\u000b\u0003\t\u0003\nY\u0003C\u0005\u0002<\t\t\t\u0011\"\u0001\u0002>!I\u0011\u0011\t\u0002\u0012\u0002\u0013\u0005\u00111\t\u0005\n\u00033\u0012\u0011\u0011!C!\u00037B\u0011\"!\u0018\u0003\u0003\u0003%\t!a\u0018\t\u0013\u0005\u001d$!!A\u0005\u0002\u0005%\u0004\"CA;\u0005\u0005\u0005I\u0011IA<\u0011%\t)IAA\u0001\n\u0003\t9\tC\u0005\u0002\u0012\n\t\t\u0011\"\u0011\u0002\u0014\"I\u0011q\u0013\u0002\u0002\u0002\u0013\u0005\u0013\u0011\u0014\u0005\n\u00037\u0013\u0011\u0011!C!\u0003;;\u0011\"!)\u0001\u0003\u0003E\t!a)\u0007\u0011A\u0004\u0011\u0011!E\u0001\u0003KCq!!\t\u0013\t\u0003\ti\fC\u0005\u0002*I\t\t\u0011\"\u0012\u0002,!I\u0011q\u0018\n\u0002\u0002\u0013\u0005\u0015\u0011\u0019\u0005\n\u0003\u000b\u0014\u0012\u0011!CA\u0003\u000f4a!a5\u0001\u0001\u0006U\u0007BCA\u0006/\tU\r\u0011\"\u0001\u0002\u000e!Q\u0011qD\f\u0003\u0012\u0003\u0006I!a\u0004\t\u000f\u0005\u0005r\u0003\"\u0001\u0002X\"9\u0011\u0011F\f\u0005B\u0005u\u0007\"CA\u001e/\u0005\u0005I\u0011AAp\u0011%\t\teFI\u0001\n\u0003\t\u0019\u0005C\u0005\u0002Z]\t\t\u0011\"\u0011\u0002\\!I\u0011QL\f\u0002\u0002\u0013\u0005\u0011q\f\u0005\n\u0003O:\u0012\u0011!C\u0001\u0003GD\u0011\"!\u001e\u0018\u0003\u0003%\t%a\u001e\t\u0013\u0005\u0015u#!A\u0005\u0002\u0005\u001d\b\"CAI/\u0005\u0005I\u0011IAv\u0011%\t9jFA\u0001\n\u0003\nI\nC\u0005\u0002\u001c^\t\t\u0011\"\u0011\u0002p\u001eI\u00111\u001f\u0001\u0002\u0002#\u0005\u0011Q\u001f\u0004\n\u0003'\u0004\u0011\u0011!E\u0001\u0003oDq!!\t(\t\u0003\tY\u0010C\u0005\u0002*\u001d\n\t\u0011\"\u0012\u0002,!I\u0011qX\u0014\u0002\u0002\u0013\u0005\u0015Q \u0005\n\u0003\u000b<\u0013\u0011!CA\u0005\u00031aA!\u0002\u0001\u0001\n\u001d\u0001BCA\u0006Y\tU\r\u0011\"\u0001\u0002\u000e!Q\u0011q\u0004\u0017\u0003\u0012\u0003\u0006I!a\u0004\t\u000f\u0005\u0005B\u0006\"\u0001\u0003\n!9\u0011\u0011\u0006\u0017\u0005B\u0005-\u0002\"CA\u001eY\u0005\u0005I\u0011\u0001B\b\u0011%\t\t\u0005LI\u0001\n\u0003\t\u0019\u0005C\u0005\u0002Z1\n\t\u0011\"\u0011\u0002\\!I\u0011Q\f\u0017\u0002\u0002\u0013\u0005\u0011q\f\u0005\n\u0003Ob\u0013\u0011!C\u0001\u0005'A\u0011\"!\u001e-\u0003\u0003%\t%a\u001e\t\u0013\u0005\u0015E&!A\u0005\u0002\t]\u0001\"CAIY\u0005\u0005I\u0011\tB\u000e\u0011%\t9\nLA\u0001\n\u0003\nI\nC\u0005\u0002\u001c2\n\t\u0011\"\u0011\u0003 \u001dI!1\u0005\u0001\u0002\u0002#\u0005!Q\u0005\u0004\n\u0005\u000b\u0001\u0011\u0011!E\u0001\u0005OAq!!\t=\t\u0003\u0011Y\u0003C\u0005\u0002*q\n\t\u0011\"\u0012\u0002,!I\u0011q\u0018\u001f\u0002\u0002\u0013\u0005%Q\u0006\u0005\n\u0003\u000bd\u0014\u0011!CA\u0005c1aA!\u000e\u0001\u0001\n]\u0002BCA\u0006\u0003\nU\r\u0011\"\u0001\u0002\u000e!Q\u0011qD!\u0003\u0012\u0003\u0006I!a\u0004\t\u000f\u0005\u0005\u0012\t\"\u0001\u0003:!9\u0011\u0011F!\u0005B\u0005-\u0002\"CA\u001e\u0003\u0006\u0005I\u0011\u0001B \u0011%\t\t%QI\u0001\n\u0003\t\u0019\u0005C\u0005\u0002Z\u0005\u000b\t\u0011\"\u0011\u0002\\!I\u0011QL!\u0002\u0002\u0013\u0005\u0011q\f\u0005\n\u0003O\n\u0015\u0011!C\u0001\u0005\u0007B\u0011\"!\u001eB\u0003\u0003%\t%a\u001e\t\u0013\u0005\u0015\u0015)!A\u0005\u0002\t\u001d\u0003\"CAI\u0003\u0006\u0005I\u0011\tB&\u0011%\t9*QA\u0001\n\u0003\nI\nC\u0005\u0002\u001c\u0006\u000b\t\u0011\"\u0011\u0003P\u001dI!1\u000b\u0001\u0002\u0002#\u0005!Q\u000b\u0004\n\u0005k\u0001\u0011\u0011!E\u0001\u0005/Bq!!\tR\t\u0003\u0011Y\u0006C\u0005\u0002*E\u000b\t\u0011\"\u0012\u0002,!I\u0011qX)\u0002\u0002\u0013\u0005%Q\f\u0005\n\u0003\u000b\f\u0016\u0011!CA\u0005C\u0012\u0011b\u0015;e)>\\WM\\:\u000b\u0005aK\u0016!\u0002;pW\u0016t'B\u0001.\\\u0003)\u0019w.\u001c2j]\u0006$xN\u001d\u0006\u00039v\u000bq\u0001]1sg&twM\u0003\u0002_?\u0006!Q\u000f^5m\u0015\u0005\u0001\u0017!B:dC2\f7\u0001A\n\u0004\u0001\r<\u0007C\u00013f\u001b\u0005y\u0016B\u00014`\u0005\u0019\te.\u001f*fMB\u0011\u0001.[\u0007\u0002/&\u0011!n\u0016\u0002\u0007)>\\WM\\:\u0002\r\u0011Jg.\u001b;%)\u0005i\u0007C\u00013o\u0013\tywL\u0001\u0003V]&$(aB&fs^|'\u000fZ\n\u0005\u0005I4\u0018\u0010\u0005\u0002ti6\t\u0001!\u0003\u0002vS\n)Ak\\6f]B\u0011Am^\u0005\u0003q~\u0013q\u0001\u0015:pIV\u001cG\u000fE\u0002{\u0003\u000bq1a_A\u0001\u001d\tax0D\u0001~\u0015\tq\u0018-\u0001\u0004=e>|GOP\u0005\u0002A&\u0019\u00111A0\u0002\u000fA\f7m[1hK&!\u0011qAA\u0005\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\r\t\u0019aX\u0001\u0006G\"\f'o]\u000b\u0003\u0003\u001f\u0001B!!\u0005\u0002\u001a9!\u00111CA\u000b!\tax,C\u0002\u0002\u0018}\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA\u000e\u0003;\u0011aa\u0015;sS:<'bAA\f?\u000611\r[1sg\u0002\na\u0001P5oSRtD\u0003BA\u0013\u0003O\u0001\"a\u001d\u0002\t\u000f\u0005-Q\u00011\u0001\u0002\u0010\u0005AAo\\*ue&tw\r\u0006\u0002\u0002.A!\u0011qFA\u001d\u001b\t\t\tD\u0003\u0003\u00024\u0005U\u0012\u0001\u00027b]\u001eT!!a\u000e\u0002\t)\fg/Y\u0005\u0005\u00037\t\t$\u0001\u0003d_BLH\u0003BA\u0013\u0003\u007fA\u0011\"a\u0003\b!\u0003\u0005\r!a\u0004\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011Q\t\u0016\u0005\u0003\u001f\t9e\u000b\u0002\u0002JA!\u00111JA+\u001b\t\tiE\u0003\u0003\u0002P\u0005E\u0013!C;oG\",7m[3e\u0015\r\t\u0019fX\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA,\u0003\u001b\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011QF\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003C\u00022\u0001ZA2\u0013\r\t)g\u0018\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003W\n\t\bE\u0002e\u0003[J1!a\u001c`\u0005\r\te.\u001f\u0005\n\u0003gZ\u0011\u0011!a\u0001\u0003C\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA=!\u0019\tY(!!\u0002l5\u0011\u0011Q\u0010\u0006\u0004\u0003\u007fz\u0016AC2pY2,7\r^5p]&!\u00111QA?\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005%\u0015q\u0012\t\u0004I\u0006-\u0015bAAG?\n9!i\\8mK\u0006t\u0007\"CA:\u001b\u0005\u0005\t\u0019AA6\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u00055\u0012Q\u0013\u0005\n\u0003gr\u0011\u0011!a\u0001\u0003C\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003C\na!Z9vC2\u001cH\u0003BAE\u0003?C\u0011\"a\u001d\u0011\u0003\u0003\u0005\r!a\u001b\u0002\u000f-+\u0017p^8sIB\u00111OE\n\u0006%\u0005\u001d\u00161\u0017\t\t\u0003S\u000by+a\u0004\u0002&5\u0011\u00111\u0016\u0006\u0004\u0003[{\u0016a\u0002:v]RLW.Z\u0005\u0005\u0003c\u000bYKA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!.\u0002<6\u0011\u0011q\u0017\u0006\u0005\u0003s\u000b)$\u0001\u0002j_&!\u0011qAA\\)\t\t\u0019+A\u0003baBd\u0017\u0010\u0006\u0003\u0002&\u0005\r\u0007bBA\u0006+\u0001\u0007\u0011qB\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tI-a4\u0011\u000b\u0011\fY-a\u0004\n\u0007\u00055wL\u0001\u0004PaRLwN\u001c\u0005\n\u0003#4\u0012\u0011!a\u0001\u0003K\t1\u0001\u001f\u00131\u0005)qU/\\3sS\u000ed\u0015\u000e^\n\u0005/I4\u0018\u0010\u0006\u0003\u0002Z\u0006m\u0007CA:\u0018\u0011\u001d\tYA\u0007a\u0001\u0003\u001f!\"!a\u0004\u0015\t\u0005e\u0017\u0011\u001d\u0005\n\u0003\u0017a\u0002\u0013!a\u0001\u0003\u001f!B!a\u001b\u0002f\"I\u00111\u000f\u0011\u0002\u0002\u0003\u0007\u0011\u0011\r\u000b\u0005\u0003\u0013\u000bI\u000fC\u0005\u0002t\t\n\t\u00111\u0001\u0002lQ!\u0011QFAw\u0011%\t\u0019hIA\u0001\u0002\u0004\t\t\u0007\u0006\u0003\u0002\n\u0006E\b\"CA:K\u0005\u0005\t\u0019AA6\u0003)qU/\\3sS\u000ed\u0015\u000e\u001e\t\u0003g\u001e\u001aRaJA}\u0003g\u0003\u0002\"!+\u00020\u0006=\u0011\u0011\u001c\u000b\u0003\u0003k$B!!7\u0002\u0000\"9\u00111\u0002\u0016A\u0002\u0005=A\u0003BAe\u0005\u0007A\u0011\"!5,\u0003\u0003\u0005\r!!7\u0003\u0013M#(/\u001b8h\u0019&$8\u0003\u0002\u0017smf$BAa\u0003\u0003\u000eA\u00111\u000f\f\u0005\b\u0003\u0017y\u0003\u0019AA\b)\u0011\u0011YA!\u0005\t\u0013\u0005-\u0011\u0007%AA\u0002\u0005=A\u0003BA6\u0005+A\u0011\"a\u001d6\u0003\u0003\u0005\r!!\u0019\u0015\t\u0005%%\u0011\u0004\u0005\n\u0003g:\u0014\u0011!a\u0001\u0003W\"B!!\f\u0003\u001e!I\u00111\u000f\u001d\u0002\u0002\u0003\u0007\u0011\u0011\r\u000b\u0005\u0003\u0013\u0013\t\u0003C\u0005\u0002ti\n\t\u00111\u0001\u0002l\u0005I1\u000b\u001e:j]\u001ed\u0015\u000e\u001e\t\u0003gr\u001aR\u0001\u0010B\u0015\u0003g\u0003\u0002\"!+\u00020\u0006=!1\u0002\u000b\u0003\u0005K!BAa\u0003\u00030!9\u00111B A\u0002\u0005=A\u0003BAe\u0005gA\u0011\"!5A\u0003\u0003\u0005\rAa\u0003\u0003\u0015%#WM\u001c;jM&,'o\u0005\u0003BeZLH\u0003\u0002B\u001e\u0005{\u0001\"a]!\t\u000f\u0005-A\t1\u0001\u0002\u0010Q!!1\bB!\u0011%\tYA\u0012I\u0001\u0002\u0004\ty\u0001\u0006\u0003\u0002l\t\u0015\u0003\"CA:\u0015\u0006\u0005\t\u0019AA1)\u0011\tII!\u0013\t\u0013\u0005MD*!AA\u0002\u0005-D\u0003BA\u0017\u0005\u001bB\u0011\"a\u001dN\u0003\u0003\u0005\r!!\u0019\u0015\t\u0005%%\u0011\u000b\u0005\n\u0003gz\u0015\u0011!a\u0001\u0003W\n!\"\u00133f]RLg-[3s!\t\u0019\u0018kE\u0003R\u00053\n\u0019\f\u0005\u0005\u0002*\u0006=\u0016q\u0002B\u001e)\t\u0011)\u0006\u0006\u0003\u0003<\t}\u0003bBA\u0006)\u0002\u0007\u0011q\u0002\u000b\u0005\u0003\u0013\u0014\u0019\u0007C\u0005\u0002RV\u000b\t\u00111\u0001\u0003<\u0001"
)
public interface StdTokens extends Tokens {
   Keyword$ Keyword();

   NumericLit$ NumericLit();

   StringLit$ StringLit();

   Identifier$ Identifier();

   static void $init$(final StdTokens $this) {
   }

   public class Keyword extends Tokens.Token implements Product, Serializable {
      private final String chars;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String chars() {
         return this.chars;
      }

      public String toString() {
         return (new StringBuilder(2)).append("'").append(this.chars()).append("'").toString();
      }

      public Keyword copy(final String chars) {
         return this.scala$util$parsing$combinator$token$StdTokens$Keyword$$$outer().new Keyword(chars);
      }

      public String copy$default$1() {
         return this.chars();
      }

      public String productPrefix() {
         return "Keyword";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.chars();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Keyword;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "chars";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label52: {
               if (x$1 instanceof Keyword && ((Keyword)x$1).scala$util$parsing$combinator$token$StdTokens$Keyword$$$outer() == this.scala$util$parsing$combinator$token$StdTokens$Keyword$$$outer()) {
                  label42: {
                     Keyword var4 = (Keyword)x$1;
                     String var10000 = this.chars();
                     String var5 = var4.chars();
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
      public StdTokens scala$util$parsing$combinator$token$StdTokens$Keyword$$$outer() {
         return (StdTokens)this.$outer;
      }

      public Keyword(final String chars) {
         this.chars = chars;
         Product.$init$(this);
      }
   }

   public class Keyword$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final StdTokens $outer;

      public final String toString() {
         return "Keyword";
      }

      public Keyword apply(final String chars) {
         return this.$outer.new Keyword(chars);
      }

      public Option unapply(final Keyword x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.chars()));
      }

      public Keyword$() {
         if (StdTokens.this == null) {
            throw null;
         } else {
            this.$outer = StdTokens.this;
            super();
         }
      }
   }

   public class NumericLit extends Tokens.Token implements Product, Serializable {
      private final String chars;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String chars() {
         return this.chars;
      }

      public String toString() {
         return this.chars();
      }

      public NumericLit copy(final String chars) {
         return this.scala$util$parsing$combinator$token$StdTokens$NumericLit$$$outer().new NumericLit(chars);
      }

      public String copy$default$1() {
         return this.chars();
      }

      public String productPrefix() {
         return "NumericLit";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.chars();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof NumericLit;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "chars";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label52: {
               if (x$1 instanceof NumericLit && ((NumericLit)x$1).scala$util$parsing$combinator$token$StdTokens$NumericLit$$$outer() == this.scala$util$parsing$combinator$token$StdTokens$NumericLit$$$outer()) {
                  label42: {
                     NumericLit var4 = (NumericLit)x$1;
                     String var10000 = this.chars();
                     String var5 = var4.chars();
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
      public StdTokens scala$util$parsing$combinator$token$StdTokens$NumericLit$$$outer() {
         return (StdTokens)this.$outer;
      }

      public NumericLit(final String chars) {
         this.chars = chars;
         Product.$init$(this);
      }
   }

   public class NumericLit$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final StdTokens $outer;

      public final String toString() {
         return "NumericLit";
      }

      public NumericLit apply(final String chars) {
         return this.$outer.new NumericLit(chars);
      }

      public Option unapply(final NumericLit x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.chars()));
      }

      public NumericLit$() {
         if (StdTokens.this == null) {
            throw null;
         } else {
            this.$outer = StdTokens.this;
            super();
         }
      }
   }

   public class StringLit extends Tokens.Token implements Product, Serializable {
      private final String chars;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String chars() {
         return this.chars;
      }

      public String toString() {
         return (new StringBuilder(2)).append("\"").append(this.chars()).append("\"").toString();
      }

      public StringLit copy(final String chars) {
         return this.scala$util$parsing$combinator$token$StdTokens$StringLit$$$outer().new StringLit(chars);
      }

      public String copy$default$1() {
         return this.chars();
      }

      public String productPrefix() {
         return "StringLit";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.chars();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof StringLit;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "chars";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label52: {
               if (x$1 instanceof StringLit && ((StringLit)x$1).scala$util$parsing$combinator$token$StdTokens$StringLit$$$outer() == this.scala$util$parsing$combinator$token$StdTokens$StringLit$$$outer()) {
                  label42: {
                     StringLit var4 = (StringLit)x$1;
                     String var10000 = this.chars();
                     String var5 = var4.chars();
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
      public StdTokens scala$util$parsing$combinator$token$StdTokens$StringLit$$$outer() {
         return (StdTokens)this.$outer;
      }

      public StringLit(final String chars) {
         this.chars = chars;
         Product.$init$(this);
      }
   }

   public class StringLit$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final StdTokens $outer;

      public final String toString() {
         return "StringLit";
      }

      public StringLit apply(final String chars) {
         return this.$outer.new StringLit(chars);
      }

      public Option unapply(final StringLit x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.chars()));
      }

      public StringLit$() {
         if (StdTokens.this == null) {
            throw null;
         } else {
            this.$outer = StdTokens.this;
            super();
         }
      }
   }

   public class Identifier extends Tokens.Token implements Product, Serializable {
      private final String chars;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String chars() {
         return this.chars;
      }

      public String toString() {
         return (new StringBuilder(11)).append("identifier ").append(this.chars()).toString();
      }

      public Identifier copy(final String chars) {
         return this.scala$util$parsing$combinator$token$StdTokens$Identifier$$$outer().new Identifier(chars);
      }

      public String copy$default$1() {
         return this.chars();
      }

      public String productPrefix() {
         return "Identifier";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.chars();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Identifier;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "chars";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label52: {
               if (x$1 instanceof Identifier && ((Identifier)x$1).scala$util$parsing$combinator$token$StdTokens$Identifier$$$outer() == this.scala$util$parsing$combinator$token$StdTokens$Identifier$$$outer()) {
                  label42: {
                     Identifier var4 = (Identifier)x$1;
                     String var10000 = this.chars();
                     String var5 = var4.chars();
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
      public StdTokens scala$util$parsing$combinator$token$StdTokens$Identifier$$$outer() {
         return (StdTokens)this.$outer;
      }

      public Identifier(final String chars) {
         this.chars = chars;
         Product.$init$(this);
      }
   }

   public class Identifier$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final StdTokens $outer;

      public final String toString() {
         return "Identifier";
      }

      public Identifier apply(final String chars) {
         return this.$outer.new Identifier(chars);
      }

      public Option unapply(final Identifier x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.chars()));
      }

      public Identifier$() {
         if (StdTokens.this == null) {
            throw null;
         } else {
            this.$outer = StdTokens.this;
            super();
         }
      }
   }
}
