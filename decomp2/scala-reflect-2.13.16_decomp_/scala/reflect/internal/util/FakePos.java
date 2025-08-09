package scala.reflect.internal.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b\u0001\u0002\f\u0018\u0001\u0002B\u0001\"\u000e\u0001\u0003\u0016\u0004%\tA\u000e\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005o!)\u0001\t\u0001C\u0001\u0003\")A\t\u0001C!\u000b\"9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dq\u0006!!A\u0005\u0002}Cqa\u0019\u0001\u0002\u0002\u0013\u0005A\rC\u0004k\u0001\u0005\u0005I\u0011I6\t\u000fI\u0004\u0011\u0011!C\u0001g\"9\u0001\u0010AA\u0001\n\u0003J\bbB>\u0001\u0003\u0003%\t\u0005 \u0005\b{\u0002\t\t\u0011\"\u0011\u007f\u000f%\t\taFA\u0001\u0012\u0003\t\u0019A\u0002\u0005\u0017/\u0005\u0005\t\u0012AA\u0003\u0011\u0019\u0001\u0005\u0003\"\u0001\u0002\u001e!AA\tEA\u0001\n\u000b\ny\u0002C\u0005\u0002\"A\t\t\u0011\"!\u0002$!I\u0011q\u0005\t\u0002\u0002\u0013\u0005\u0015\u0011\u0006\u0005\n\u0003k\u0001\u0012\u0011!C\u0005\u0003o\u0011qAR1lKB{7O\u0003\u0002\u00193\u0005!Q\u000f^5m\u0015\tQ2$\u0001\u0005j]R,'O\\1m\u0015\taR$A\u0004sK\u001adWm\u0019;\u000b\u0003y\tQa]2bY\u0006\u001c\u0001a\u0005\u0003\u0001C\u0015J\u0003C\u0001\u0012$\u001b\u00059\u0012B\u0001\u0013\u0018\u0005E)f\u000eZ3gS:,G\rU8tSRLwN\u001c\t\u0003M\u001dj\u0011!H\u0005\u0003Qu\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002+e9\u00111\u0006\r\b\u0003Y=j\u0011!\f\u0006\u0003]}\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0010\n\u0005Ej\u0012a\u00029bG.\fw-Z\u0005\u0003gQ\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!M\u000f\u0002\u00075\u001cx-F\u00018!\tADH\u0004\u0002:uA\u0011A&H\u0005\u0003wu\ta\u0001\u0015:fI\u00164\u0017BA\u001f?\u0005\u0019\u0019FO]5oO*\u00111(H\u0001\u0005[N<\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0005\u000e\u0003\"A\t\u0001\t\u000bU\u001a\u0001\u0019A\u001c\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aN\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002C\u0011\"9Q'\u0002I\u0001\u0002\u00049\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0017*\u0012q\u0007T\u0016\u0002\u001bB\u0011ajU\u0007\u0002\u001f*\u0011\u0001+U\u0001\nk:\u001c\u0007.Z2lK\u0012T!AU\u000f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002U\u001f\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0011a\u0017M\\4\u000b\u0003q\u000bAA[1wC&\u0011Q(W\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002AB\u0011a%Y\u0005\u0003Ev\u00111!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u001a5\u0011\u0005\u00192\u0017BA4\u001e\u0005\r\te.\u001f\u0005\bS&\t\t\u00111\u0001a\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tA\u000eE\u0002na\u0016l\u0011A\u001c\u0006\u0003_v\t!bY8mY\u0016\u001cG/[8o\u0013\t\thN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGC\u0001;x!\t1S/\u0003\u0002w;\t9!i\\8mK\u0006t\u0007bB5\f\u0003\u0003\u0005\r!Z\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Xu\"9\u0011\u000eDA\u0001\u0002\u0004\u0001\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0001\fa!Z9vC2\u001cHC\u0001;\u0000\u0011\u001dIg\"!AA\u0002\u0015\fqAR1lKB{7\u000f\u0005\u0002#!M)\u0001#a\u0002\u0002\u0014A1\u0011\u0011BA\bo\tk!!a\u0003\u000b\u0007\u00055Q$A\u0004sk:$\u0018.\\3\n\t\u0005E\u00111\u0002\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u000b\u00037i!!a\u0006\u000b\u0007\u0005e1,\u0001\u0002j_&\u00191'a\u0006\u0015\u0005\u0005\rA#A,\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\t\u000b)\u0003C\u00036'\u0001\u0007q'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u0012\u0011\u0007\t\u0005M\u00055r'C\u0002\u00020u\u0011aa\u00149uS>t\u0007\u0002CA\u001a)\u0005\u0005\t\u0019\u0001\"\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002:A\u0019\u0001,a\u000f\n\u0007\u0005u\u0012L\u0001\u0004PE*,7\r\u001e"
)
public class FakePos extends UndefinedPosition implements Product, Serializable {
   private final String msg;

   public static Option unapply(final FakePos x$0) {
      return FakePos$.MODULE$.unapply(x$0);
   }

   public static FakePos apply(final String msg) {
      FakePos$ var10000 = FakePos$.MODULE$;
      return new FakePos(msg);
   }

   public static Function1 andThen(final Function1 g) {
      return Function1::$anonfun$andThen$1;
   }

   public static Function1 compose(final Function1 g) {
      return Function1::$anonfun$compose$1;
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String msg() {
      return this.msg;
   }

   public String toString() {
      return this.msg();
   }

   public FakePos copy(final String msg) {
      return new FakePos(msg);
   }

   public String copy$default$1() {
      return this.msg();
   }

   public String productPrefix() {
      return "FakePos";
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
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof FakePos;
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
      return .MODULE$.productHash(this, -889275714, false);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof FakePos) {
            FakePos var2 = (FakePos)x$1;
            String var10000 = this.msg();
            String var3 = var2.msg();
            if (var10000 == null) {
               if (var3 != null) {
                  return false;
               }
            } else if (!var10000.equals(var3)) {
               return false;
            }

            if (var2.canEqual(this)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public FakePos(final String msg) {
      this.msg = msg;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
