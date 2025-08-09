package spire.random;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\r4qAB\u0004\u0011\u0002\u0007\u0005B\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\u0005!\u0004C\u00032\u0001\u0011\u0005!\u0007C\u0003:\u0001\u0011\u0015!\bC\u0003X\u0001\u0011\u0005\u0001L\u0001\u0002Pa*\u0011\u0001\"C\u0001\u0007e\u0006tGm\\7\u000b\u0003)\tQa\u001d9je\u0016\u001c\u0001!\u0006\u0002\u000e_M\u0011\u0001A\u0004\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u00051\u0002CA\b\u0018\u0013\tA\u0002C\u0001\u0003V]&$\u0018a\u00024mCRl\u0015\r]\u000b\u00037\u0001\"\"\u0001H\u0015\u0011\u0007u\u0001a$D\u0001\b!\ty\u0002\u0005\u0004\u0001\u0005\u000b\u0005\u0012!\u0019\u0001\u0012\u0003\u0003\t\u000b\"a\t\u0014\u0011\u0005=!\u0013BA\u0013\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aD\u0014\n\u0005!\u0002\"aA!os\")!F\u0001a\u0001W\u0005\ta\r\u0005\u0003\u0010Y9b\u0012BA\u0017\u0011\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002 _\u00111\u0001\u0007\u0001CC\u0002\t\u0012\u0011!Q\u0001\u0004[\u0006\u0004XCA\u001a7)\t!t\u0007E\u0002\u001e\u0001U\u0002\"a\b\u001c\u0005\u000b\u0005\u001a!\u0019\u0001\u0012\t\u000b)\u001a\u0001\u0019\u0001\u001d\u0011\t=ac&N\u0001\u0007e\u0016\u001cX/\\3\u0015\u0005mZ\u0005\u0003\u0002\u001fE\u000f:r!!\u0010\"\u000f\u0005y\nU\"A \u000b\u0005\u0001[\u0011A\u0002\u001fs_>$h(C\u0001\u0012\u0013\t\u0019\u0005#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u00153%AB#ji\",'O\u0003\u0002D!A\u0019q\u0002\u0013&\n\u0005%\u0003\"!\u0003$v]\u000e$\u0018n\u001c81!\ri\u0002A\f\u0005\u0006\u0019\u0012\u0001\r!T\u0001\u0004O\u0016t\u0007CA\u000fO\u0013\tyuAA\u0005HK:,'/\u0019;pe\"\u0012A!\u0015\t\u0003%Vk\u0011a\u0015\u0006\u0003)B\t!\"\u00198o_R\fG/[8o\u0013\t16KA\u0004uC&d'/Z2\u0002\u0007I,h\u000e\u0006\u0002/3\")A*\u0002a\u0001\u001b&*\u0001aW/`C&\u0011Al\u0002\u0002\u0006\u0007>t7\u000f^\u0005\u0003=\u001e\u0011qA\u00127bi6\u000b\u0007/\u0003\u0002a\u000f\t!Qj\u001c:f\u0013\t\u0011wA\u0001\u0003OKb$\b"
)
public interface Op {
   // $FF: synthetic method
   static Op flatMap$(final Op $this, final Function1 f) {
      return $this.flatMap(f);
   }

   default Op flatMap(final Function1 f) {
      FlatMap var2;
      if (this instanceof FlatMap) {
         FlatMap var4 = (FlatMap)this;
         var2 = new FlatMap(var4.sub(), (x) -> ((Op)var4.k().apply(x)).flatMap(f));
      } else {
         var2 = new FlatMap(this, f);
      }

      return var2;
   }

   // $FF: synthetic method
   static Op map$(final Op $this, final Function1 f) {
      return $this.map(f);
   }

   default Op map(final Function1 f) {
      return this.flatMap((a) -> new Const(f.apply(a)));
   }

   // $FF: synthetic method
   static Either resume$(final Op $this, final Generator gen) {
      return $this.resume(gen);
   }

   default Either resume(final Generator gen) {
      while(true) {
         Object var3;
         if (this instanceof Const) {
            Const var6 = (Const)this;
            Object a = var6.a();
            var3 = .MODULE$.Right().apply(a);
         } else if (this instanceof More) {
            More var8 = (More)this;
            Function0 k = var8.k();
            var3 = .MODULE$.Left().apply(k);
         } else if (this instanceof Next) {
            Next var10 = (Next)this;
            Function1 f = var10.f();
            var3 = .MODULE$.Right().apply(f.apply(gen));
         } else {
            if (!(this instanceof FlatMap)) {
               throw new MatchError(this);
            }

            FlatMap var12 = (FlatMap)this;
            Op a = var12.sub();
            Function1 f = var12.k();
            if (a instanceof Const) {
               Const var16 = (Const)a;
               Object x = var16.a();
               Op var24 = (Op)f.apply(x);
               gen = gen;
               this = var24;
               continue;
            }

            if (!(a instanceof More)) {
               if (a instanceof Next) {
                  Next var20 = (Next)a;
                  Function1 g = var20.f();
                  Op var23 = (Op)f.apply(g.apply(gen));
                  gen = gen;
                  this = var23;
                  continue;
               }

               if (a instanceof FlatMap) {
                  FlatMap var22 = (FlatMap)a;
                  FlatMap var10000 = new FlatMap(var22.sub(), (xx) -> ((Op)var22.k().apply(xx)).flatMap(f));
                  gen = gen;
                  this = var10000;
                  continue;
               }

               throw new MatchError(a);
            }

            More var18 = (More)a;
            Function0 k = var18.k();
            Left var4 = .MODULE$.Left().apply((Function0)() -> new FlatMap((Op)k.apply(), f));
            var3 = var4;
         }

         return (Either)var3;
      }
   }

   // $FF: synthetic method
   static Object run$(final Op $this, final Generator gen) {
      return $this.run(gen);
   }

   default Object run(final Generator gen) {
      return this.loop$1(this.resume(gen), gen);
   }

   private Object loop$1(final Either e, final Generator gen$1) {
      while(!(e instanceof Right)) {
         if (!(e instanceof Left)) {
            throw new MatchError(e);
         }

         Left var8 = (Left)e;
         Function0 k = (Function0)var8.value();
         e = ((Op)k.apply()).resume(gen$1);
      }

      Right var6 = (Right)e;
      Object a = var6.value();
      return a;
   }

   static void $init$(final Op $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
