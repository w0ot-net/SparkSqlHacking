package org.json4s.scalap;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.LazyRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005aaB\u0003\u0007!\u0003\r\t!\u0004\u0005\u00061\u0001!\t!\u0007\u0005\u0006;\u0001!\tA\b\u0005\u0006\u0013\u0002!\tE\u0013\u0005\fY\u0002\u0001\n1!A\u0001\n\u0013iwPA\bNK6|\u0017n]1cY\u0016\u0014V\u000f\\3t\u0015\t9\u0001\"\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u0003\u0013)\taA[:p]R\u001a(\"A\u0006\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001qA\u0003\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0003+Yi\u0011AB\u0005\u0003/\u0019\u0011QAU;mKN\fa\u0001J5oSR$C#\u0001\u000e\u0011\u0005=Y\u0012B\u0001\u000f\u0011\u0005\u0011)f.\u001b;\u0002\t5,Wn\\\u000b\u0006?\u0019\u0002tG\u000f\u000b\u0003A\u001d#\"!\t\u001f\u0011\rU\u0011Ce\f\u001c:\u0013\t\u0019cA\u0001\u0003Sk2,\u0007CA\u0013'\u0019\u0001!Qa\n\u0002C\u0002!\u0012!!\u00138\u0012\u0005%b\u0003CA\b+\u0013\tY\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005Ui\u0013B\u0001\u0018\u0007\u0005)iU-\\8jg\u0006\u0014G.\u001a\t\u0003KA\"Q!\r\u0002C\u0002I\u00121aT;u#\tI3\u0007\u0005\u0002\u0010i%\u0011Q\u0007\u0005\u0002\u0004\u0003:L\bCA\u00138\t\u0015A$A1\u00013\u0005\u0005\t\u0005CA\u0013;\t\u0015Y$A1\u00013\u0005\u0005A\u0006BB\u001f\u0003\t\u0003\u0007a(\u0001\u0004u_J+H.\u001a\t\u0004\u001f}\n\u0015B\u0001!\u0011\u0005!a$-\u001f8b[\u0016t\u0004\u0003B\bCI\u0011K!a\u0011\t\u0003\u0013\u0019+hn\u0019;j_:\f\u0004#B\u000bF_YJ\u0014B\u0001$\u0007\u0005\u0019\u0011Vm];mi\")\u0001J\u0001a\u0001\u001d\u0005\u00191.Z=\u0002\u0019I,H.Z,ji\"t\u0015-\\3\u0016\u000b-\u000b6+V,\u0015\u00071[\u0006NE\u0002N\u001fb3AA\u0014\u0001\u0001\u0019\naAH]3gS:,W.\u001a8u}A1QC\t)S)Z\u0003\"!J)\u0005\u000b\u001d\u001a!\u0019\u0001\u001a\u0011\u0005\u0015\u001aF!B\u0019\u0004\u0005\u0004\u0011\u0004CA\u0013V\t\u0015A4A1\u00013!\t)s\u000bB\u0003<\u0007\t\u0007!\u0007\u0005\u0002\u00163&\u0011!L\u0002\u0002\u0005\u001d\u0006lW\rC\u0003]\u0007\u0001\u0007Q,\u0001\u0003oC6,\u0007C\u00010f\u001d\ty6\r\u0005\u0002a!5\t\u0011M\u0003\u0002c\u0019\u00051AH]8pizJ!\u0001\u001a\t\u0002\rA\u0013X\rZ3g\u0013\t1wM\u0001\u0004TiJLgn\u001a\u0006\u0003IBAQ![\u0002A\u0002)\f\u0011A\u001a\t\u0005\u001f\t\u00036\u000eE\u0003\u0016\u000bJ#f+\u0001\ntkB,'\u000f\n:vY\u0016<\u0016\u000e\u001e5OC6,W#\u00028tk^LHcA8{yJ\u0019\u0001/\u001d-\u0007\t9\u0003\u0001a\u001c\t\u0007+\t\u0012HO\u001e=\u0011\u0005\u0015\u001aH!B\u0014\u0005\u0005\u0004\u0011\u0004CA\u0013v\t\u0015\tDA1\u00013!\t)s\u000fB\u00039\t\t\u0007!\u0007\u0005\u0002&s\u0012)1\b\u0002b\u0001e!)1\u0010\u0002a\u0001;\u0006)qL\\1nK\")\u0011\u000e\u0002a\u0001{B!qB\u0011:\u007f!\u0015)R\t\u001e<y\u0013\tIe\u0003"
)
public interface MemoisableRules extends Rules {
   // $FF: synthetic method
   Rule org$json4s$scalap$MemoisableRules$$super$ruleWithName(final String _name, final Function1 f);

   // $FF: synthetic method
   static Rule memo$(final MemoisableRules $this, final Object key, final Function0 toRule) {
      return $this.memo(key, toRule);
   }

   default Rule memo(final Object key, final Function0 toRule) {
      LazyRef rule$lzy = new LazyRef();
      return this.from().apply((in) -> (Result)in.memo(key, () -> (Result)rule$1(rule$lzy, toRule).apply(in)));
   }

   // $FF: synthetic method
   static Rule ruleWithName$(final MemoisableRules $this, final String name, final Function1 f) {
      return $this.ruleWithName(name, f);
   }

   default Rule ruleWithName(final String name, final Function1 f) {
      return this.org$json4s$scalap$MemoisableRules$$super$ruleWithName(name, (in) -> {
         Result var3;
         if (in instanceof Memoisable) {
            var3 = (Result)((Memoisable)in).memo(name, () -> (Result)f.apply(in));
         } else {
            var3 = (Result)f.apply(in);
         }

         return var3;
      });
   }

   // $FF: synthetic method
   private static Function1 rule$lzycompute$1(final LazyRef rule$lzy$1, final Function0 toRule$1) {
      synchronized(rule$lzy$1){}

      Function1 var3;
      try {
         var3 = rule$lzy$1.initialized() ? (Function1)rule$lzy$1.value() : (Function1)rule$lzy$1.initialize(toRule$1.apply());
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private static Function1 rule$1(final LazyRef rule$lzy$1, final Function0 toRule$1) {
      return rule$lzy$1.initialized() ? (Function1)rule$lzy$1.value() : rule$lzycompute$1(rule$lzy$1, toRule$1);
   }

   static void $init$(final MemoisableRules $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
