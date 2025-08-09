package scala.util.parsing.combinator;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b!C\u0004\t!\u0003\r\t!EA\u0016\u0011\u00151\u0002\u0001\"\u0001\u0018\u0011\u0015Y\u0002\u0001b\u0001\u001d\u0011\u0015i\u0004\u0001b\u0001?\u0011\u0015\u0001\u0006\u0001b\u0001R\u0011\u00151\u0007\u0001b\u0001h\u0011\u0019y\b\u0001b\u0001\u0002\u0002\t\u0019\u0012*\u001c9mS\u000eLGoQ8om\u0016\u00148/[8og*\u0011\u0011BC\u0001\u000bG>l'-\u001b8bi>\u0014(BA\u0006\r\u0003\u001d\u0001\u0018M]:j]\u001eT!!\u0004\b\u0002\tU$\u0018\u000e\u001c\u0006\u0002\u001f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u0013!\t\u0019B#D\u0001\u000f\u0013\t)bB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003a\u0001\"aE\r\n\u0005iq!\u0001B+oSR\f\u0001B\u001a7biR,gNM\u000b\u0005;%\u001ad\u0007\u0006\u0002\u001fqA!1cH\u00116\u0013\t\u0001cBA\u0005Gk:\u001cG/[8ocA!!eI\u00143\u001b\u0005\u0001\u0011B\u0001\u0013&\u0005\u0019!C/\u001b7eK&\u0011a\u0005\u0003\u0002\b!\u0006\u00148/\u001a:t!\tA\u0013\u0006\u0004\u0001\u0005\u000b)\u0012!\u0019A\u0016\u0003\u0003\u0005\u000b\"\u0001L\u0018\u0011\u0005Mi\u0013B\u0001\u0018\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u0005\u0019\n\u0005Er!aA!osB\u0011\u0001f\r\u0003\u0006i\t\u0011\ra\u000b\u0002\u0002\u0005B\u0011\u0001F\u000e\u0003\u0006o\t\u0011\ra\u000b\u0002\u0002\u0007\")\u0011H\u0001a\u0001u\u0005\ta\rE\u0003\u0014w\u001d\u0012T'\u0003\u0002=\u001d\tIa)\u001e8di&|gNM\u0001\tM2\fG\u000f^3ogU)q\b\u0012$I\u0015R\u0011\u0001\t\u0014\t\u0005'}\t\u0015\n\u0005\u0003#G\t;\u0005\u0003\u0002\u0012$\u0007\u0016\u0003\"\u0001\u000b#\u0005\u000b)\u001a!\u0019A\u0016\u0011\u0005!2E!\u0002\u001b\u0004\u0005\u0004Y\u0003C\u0001\u0015I\t\u001594A1\u0001,!\tA#\nB\u0003L\u0007\t\u00071FA\u0001E\u0011\u0015I4\u00011\u0001N!\u0019\u0019bjQ#H\u0013&\u0011qJ\u0004\u0002\n\rVt7\r^5p]N\n\u0001B\u001a7biR,g\u000eN\u000b\u0007%bSFL\u00181\u0015\u0005M\u0013\u0007\u0003B\n )~\u0003BAI\u0012V;B!!e\t,\\!\u0011\u00113eV-\u0011\u0005!BF!\u0002\u0016\u0005\u0005\u0004Y\u0003C\u0001\u0015[\t\u0015!DA1\u0001,!\tAC\fB\u00038\t\t\u00071\u0006\u0005\u0002)=\u0012)1\n\u0002b\u0001WA\u0011\u0001\u0006\u0019\u0003\u0006C\u0012\u0011\ra\u000b\u0002\u0002\u000b\")\u0011\b\u0002a\u0001GB91\u0003Z,Z7v{\u0016BA3\u000f\u0005%1UO\\2uS>tG'\u0001\u0005gY\u0006$H/\u001a86+\u001dAw.]:vof$\"![>\u0011\tMy\"\u000e\u001f\t\u0005E\rZg\u000f\u0005\u0003#G1$\b\u0003\u0002\u0012$[J\u0004BAI\u0012oaB\u0011\u0001f\u001c\u0003\u0006U\u0015\u0011\ra\u000b\t\u0003QE$Q\u0001N\u0003C\u0002-\u0002\"\u0001K:\u0005\u000b]*!\u0019A\u0016\u0011\u0005!*H!B&\u0006\u0005\u0004Y\u0003C\u0001\u0015x\t\u0015\tWA1\u0001,!\tA\u0013\u0010B\u0003{\u000b\t\u00071FA\u0001G\u0011\u0015IT\u00011\u0001}!!\u0019RP\u001c9siZD\u0018B\u0001@\u000f\u0005%1UO\\2uS>tW'A\fiK\u0006$w\n\u001d;j_:$\u0016-\u001b7U_\u001a+h\u000eT5tiV1\u00111AA\u0006\u0003G!B!!\u0002\u0002(A11cHA\u0004\u0003C\u0001bAI\u0012\u0002\n\u00055\u0001c\u0001\u0015\u0002\f\u0011)!F\u0002b\u0001WA)1#a\u0004\u0002\u0014%\u0019\u0011\u0011\u0003\b\u0003\r=\u0003H/[8o!\u0019\t)\"a\u0007\u0002\n9\u00191#a\u0006\n\u0007\u0005ea\"A\u0004qC\u000e\\\u0017mZ3\n\t\u0005u\u0011q\u0004\u0002\u0005\u0019&\u001cHOC\u0002\u0002\u001a9\u00012\u0001KA\u0012\t\u0019\t)C\u0002b\u0001W\t\tA\u000b\u0003\u0004:\r\u0001\u0007\u0011\u0011\u0006\t\u0007'}\t\u0019\"!\t\u0013\r\u00055\u0012\u0011GA\u001b\r\u0019\ty\u0003\u0001\u0001\u0002,\taAH]3gS:,W.\u001a8u}A\u0019\u00111\u0007\u0001\u000e\u0003!\u00012!a\r&\u0001"
)
public interface ImplicitConversions {
   // $FF: synthetic method
   static Function1 flatten2$(final ImplicitConversions $this, final Function2 f) {
      return $this.flatten2(f);
   }

   default Function1 flatten2(final Function2 f) {
      return (p) -> {
         if (p != null) {
            Object a = p._1();
            Object b = p._2();
            return f.apply(a, b);
         } else {
            throw new MatchError(p);
         }
      };
   }

   // $FF: synthetic method
   static Function1 flatten3$(final ImplicitConversions $this, final Function3 f) {
      return $this.flatten3(f);
   }

   default Function1 flatten3(final Function3 f) {
      return (p) -> {
         if (p != null) {
            Parsers.$tilde var4 = (Parsers.$tilde)p._1();
            Object c = p._2();
            if (var4 != null) {
               Object a = var4._1();
               Object b = var4._2();
               return f.apply(a, b, c);
            }
         }

         throw new MatchError(p);
      };
   }

   // $FF: synthetic method
   static Function1 flatten4$(final ImplicitConversions $this, final Function4 f) {
      return $this.flatten4(f);
   }

   default Function1 flatten4(final Function4 f) {
      return (p) -> {
         if (p != null) {
            Parsers.$tilde var4 = (Parsers.$tilde)p._1();
            Object d = p._2();
            if (var4 != null) {
               Parsers.$tilde var6 = (Parsers.$tilde)var4._1();
               Object c = var4._2();
               if (var6 != null) {
                  Object a = var6._1();
                  Object b = var6._2();
                  return f.apply(a, b, c, d);
               }
            }
         }

         throw new MatchError(p);
      };
   }

   // $FF: synthetic method
   static Function1 flatten5$(final ImplicitConversions $this, final Function5 f) {
      return $this.flatten5(f);
   }

   default Function1 flatten5(final Function5 f) {
      return (p) -> {
         if (p != null) {
            Parsers.$tilde var4 = (Parsers.$tilde)p._1();
            Object e = p._2();
            if (var4 != null) {
               Parsers.$tilde var6 = (Parsers.$tilde)var4._1();
               Object d = var4._2();
               if (var6 != null) {
                  Parsers.$tilde var8 = (Parsers.$tilde)var6._1();
                  Object c = var6._2();
                  if (var8 != null) {
                     Object a = var8._1();
                     Object b = var8._2();
                     return f.apply(a, b, c, d, e);
                  }
               }
            }
         }

         throw new MatchError(p);
      };
   }

   // $FF: synthetic method
   static Function1 headOptionTailToFunList$(final ImplicitConversions $this, final Function1 f) {
      return $this.headOptionTailToFunList(f);
   }

   default Function1 headOptionTailToFunList(final Function1 f) {
      return (p) -> {
         Object var3 = p._1();
         Option var4 = (Option)p._2();
         Object var10001;
         if (var4 instanceof Some) {
            Some var5 = (Some)var4;
            List xs = (List)var5.value();
            var10001 = xs;
         } else {
            if (!.MODULE$.equals(var4)) {
               throw new MatchError(var4);
            }

            var10001 = scala.collection.immutable.Nil..MODULE$;
         }

         return f.apply(((List)var10001).$colon$colon(var3));
      };
   }

   static void $init$(final ImplicitConversions $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
