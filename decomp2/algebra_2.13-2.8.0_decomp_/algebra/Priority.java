package algebra;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.util.Either;

@ScalaSignature(
   bytes = "\u0006\u0005\t]ea\u0002\u001e<!\u0003\r\tC\u0010\u0005\u0006\r\u0002!\ta\u0012\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006Q\u0002!\t!\u001b\u0005\u0006e\u0002!\ta\u001d\u0005\b\u0003\u000b\u0001A\u0011AA\u0004\u0011\u001d\t\t\u0003\u0001C\u0001\u0003GAq!a\u000b\u0001\t\u0003\t\u0019\u0003C\u0004\u0002.\u0001!\t!a\f\t\u000f\u0005]\u0002\u0001\"\u0001\u0002:\u001d9!QS\u001e\t\u0002\u00055cA\u0002\u001e<\u0011\u0003\t\t\u0005C\u0004\u0002J-!\t!a\u0013\u0007\r\u0005=3\u0002QA)\u0011)\t9'\u0004BK\u0002\u0013\u0005\u0011\u0011\u000e\u0005\u000b\u0003Wj!\u0011#Q\u0001\n\u0005]\u0003bBA%\u001b\u0011\u0005\u0011Q\u000e\u0005\n\u0003kj\u0011\u0011!C\u0001\u0003oB\u0011\"a!\u000e#\u0003%\t!!\"\t\u0013\u0005}U\"!A\u0005B\u0005\u0005\u0006\"CAZ\u001b\u0005\u0005I\u0011AA[\u0011%\ti,DA\u0001\n\u0003\ty\fC\u0005\u0002F6\t\t\u0011\"\u0011\u0002H\"I\u0011Q[\u0007\u0002\u0002\u0013\u0005\u0011q\u001b\u0005\n\u00037l\u0011\u0011!C!\u0003;D\u0011\"!9\u000e\u0003\u0003%\t%a9\t\u0013\u0005\u0015X\"!A\u0005B\u0005\u001d\b\"CAu\u001b\u0005\u0005I\u0011IAv\u000f%\tyoCA\u0001\u0012\u0003\t\tPB\u0005\u0002P-\t\t\u0011#\u0001\u0002t\"9\u0011\u0011J\u000f\u0005\u0002\u0005}\b\"CAs;\u0005\u0005IQIAt\u0011%\u0011\t!HA\u0001\n\u0003\u0013\u0019\u0001C\u0005\u0003\u0010u\t\t\u0011\"!\u0003\u0012!I!\u0011E\u000f\u0002\u0002\u0013%!1\u0005\u0004\u0007\u0003\u007fY\u0001Ia\u0018\t\u0015\u0005\u001d4E!f\u0001\n\u0003\u0011I\u0007\u0003\u0006\u0002l\r\u0012\t\u0012)A\u0005\u0005KBq!!\u0013$\t\u0003\u0011Y\u0007C\u0005\u0002v\r\n\t\u0011\"\u0001\u0003r!I\u00111Q\u0012\u0012\u0002\u0013\u0005!Q\u0010\u0005\n\u0003?\u001b\u0013\u0011!C!\u0003CC\u0011\"a-$\u0003\u0003%\t!!.\t\u0013\u0005u6%!A\u0005\u0002\t\u0015\u0005\"CAcG\u0005\u0005I\u0011IAd\u0011%\t)nIA\u0001\n\u0003\u0011I\tC\u0005\u0002\\\u000e\n\t\u0011\"\u0011\u0003\u000e\"I\u0011\u0011]\u0012\u0002\u0002\u0013\u0005\u00131\u001d\u0005\n\u0003K\u001c\u0013\u0011!C!\u0003OD\u0011\"!;$\u0003\u0003%\tE!%\b\u0013\t-2\"!A\t\u0002\t5b!CA \u0017\u0005\u0005\t\u0012\u0001B\u0018\u0011\u001d\tIe\rC\u0001\u0005cA\u0011\"!:4\u0003\u0003%)%a:\t\u0013\t\u00051'!A\u0005\u0002\nM\u0002\"\u0003B\bg\u0005\u0005I\u0011\u0011B \u0011%\u0011\tcMA\u0001\n\u0013\u0011\u0019\u0003C\u0004\u0003\u0002-!\tA!\u0014\u0003\u0011A\u0013\u0018n\u001c:jifT\u0011\u0001P\u0001\bC2<WM\u0019:b\u0007\u0001)2a\u00104a'\t\u0001\u0001\t\u0005\u0002B\t6\t!IC\u0001D\u0003\u0015\u00198-\u00197b\u0013\t)%I\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003!\u0003\"!Q%\n\u0005)\u0013%\u0001B+oSR\fAAZ8mIV\u0011Q*\u0015\u000b\u0003\u001d\n$\"a\u0014.\u0011\u0005A\u000bF\u0002\u0001\u0003\u0006%\n\u0011\ra\u0015\u0002\u0002\u0005F\u0011Ak\u0016\t\u0003\u0003VK!A\u0016\"\u0003\u000f9{G\u000f[5oOB\u0011\u0011\tW\u0005\u00033\n\u00131!\u00118z\u0011\u0015Y&\u00011\u0001]\u0003\t1'\u0007\u0005\u0003B;~{\u0015B\u00010C\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002QA\u00121\u0011\r\u0001CC\u0002M\u0013\u0011A\u0012\u0005\u0006G\n\u0001\r\u0001Z\u0001\u0003MF\u0002B!Q/f\u001fB\u0011\u0001K\u001a\u0003\u0007O\u0002!)\u0019A*\u0003\u0003A\u000bAA[8j]V\u0011!\u000e\\\u000b\u0002WB\u0011\u0001\u000b\u001c\u0003\u0006[\u000e\u0011\rA\u001c\u0002\u0002+F\u0011qn\u0016\n\u0004a\u0016|f\u0001B9\u0001\u0001=\u0014A\u0002\u0010:fM&tW-\\3oiz\nQAY5nCB,2\u0001^=})\r)\u0018\u0011\u0001\u000b\u0003mz\u0004Ba\u001e\u0001yw6\t1\b\u0005\u0002Qs\u0012)!\u0010\u0002b\u0001'\n\u0011\u0001K\r\t\u0003!r$Q! \u0003C\u0002M\u0013!A\u0012\u001a\t\u000bm#\u0001\u0019A@\u0011\t\u0005kvl\u001f\u0005\u0007G\u0012\u0001\r!a\u0001\u0011\t\u0005kV\r_\u0001\ti>,\u0015\u000e\u001e5feV\u0011\u0011\u0011\u0002\t\u0007\u0003\u0017\tY\"Z0\u000f\t\u00055\u0011q\u0003\b\u0005\u0003\u001f\t)\"\u0004\u0002\u0002\u0012)\u0019\u00111C\u001f\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0015bAA\r\u0005\u00069\u0001/Y2lC\u001e,\u0017\u0002BA\u000f\u0003?\u0011a!R5uQ\u0016\u0014(bAA\r\u0005\u0006Y\u0011n\u001d)sK\u001a,'O]3e+\t\t)\u0003E\u0002B\u0003OI1!!\u000bC\u0005\u001d\u0011un\u001c7fC:\f!\"[:GC2d'-Y2l\u000319W\r\u001e)sK\u001a,'O]3e+\t\t\t\u0004\u0005\u0003B\u0003g)\u0017bAA\u001b\u0005\n1q\n\u001d;j_:\f1bZ3u\r\u0006dGNY1dWV\u0011\u00111\b\t\u0005\u0003\u0006Mr,K\u0002\u0001G5\u0011\u0001BR1mY\n\f7m[\n\u0005\u0017\u0001\u000b\u0019\u0005E\u0002x\u0003\u000bJ1!a\u0012<\u000551\u0015N\u001c3Qe\u00164WM\u001d:fI\u00061A(\u001b8jiz\"\"!!\u0014\u0011\u0005]\\!!\u0003)sK\u001a,'O]3e+\u0011\t\u0019&!\u0017\u0014\u00115\u0001\u0015QKA.\u0003C\u0002Ra\u001e\u0001\u0002XQ\u00032\u0001UA-\t\u00159WB1\u0001T!\r\t\u0015QL\u0005\u0004\u0003?\u0012%a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003\u0017\t\u0019'\u0003\u0003\u0002f\u0005}!\u0001D*fe&\fG.\u001b>bE2,\u0017aA4fiV\u0011\u0011qK\u0001\u0005O\u0016$\b\u0005\u0006\u0003\u0002p\u0005M\u0004#BA9\u001b\u0005]S\"A\u0006\t\u000f\u0005\u001d\u0004\u00031\u0001\u0002X\u0005!1m\u001c9z+\u0011\tI(a \u0015\t\u0005m\u0014\u0011\u0011\t\u0006\u0003cj\u0011Q\u0010\t\u0004!\u0006}D!B4\u0012\u0005\u0004\u0019\u0006\"CA4#A\u0005\t\u0019AA?\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*B!a\"\u0002\u001eV\u0011\u0011\u0011\u0012\u0016\u0005\u0003/\nYi\u000b\u0002\u0002\u000eB!\u0011qRAM\u001b\t\t\tJ\u0003\u0003\u0002\u0014\u0006U\u0015!C;oG\",7m[3e\u0015\r\t9JQ\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAN\u0003#\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u00159'C1\u0001T\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\u0015\t\u0005\u0003K\u000by+\u0004\u0002\u0002(*!\u0011\u0011VAV\u0003\u0011a\u0017M\\4\u000b\u0005\u00055\u0016\u0001\u00026bm\u0006LA!!-\u0002(\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a.\u0011\u0007\u0005\u000bI,C\u0002\u0002<\n\u00131!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2aVAa\u0011%\t\u0019-FA\u0001\u0002\u0004\t9,A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0013\u0004R!a3\u0002R^k!!!4\u000b\u0007\u0005=')\u0001\u0006d_2dWm\u0019;j_:LA!a5\u0002N\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t)#!7\t\u0011\u0005\rw#!AA\u0002]\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111UAp\u0011%\t\u0019\rGA\u0001\u0002\u0004\t9,\u0001\u0005iCND7i\u001c3f)\t\t9,\u0001\u0005u_N#(/\u001b8h)\t\t\u0019+\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003K\ti\u000f\u0003\u0005\u0002Dn\t\t\u00111\u0001X\u0003%\u0001&/\u001a4feJ,G\rE\u0002\u0002ru\u0019B!\b!\u0002vB!\u0011q_A\u007f\u001b\t\tIP\u0003\u0003\u0002|\u0006-\u0016AA5p\u0013\u0011\t)'!?\u0015\u0005\u0005E\u0018!B1qa2LX\u0003\u0002B\u0003\u0005\u0017!BAa\u0002\u0003\u000eA)\u0011\u0011O\u0007\u0003\nA\u0019\u0001Ka\u0003\u0005\u000b\u001d\u0004#\u0019A*\t\u000f\u0005\u001d\u0004\u00051\u0001\u0003\n\u00059QO\\1qa2LX\u0003\u0002B\n\u00053!BA!\u0006\u0003\u001cA)\u0011)a\r\u0003\u0018A\u0019\u0001K!\u0007\u0005\u000b\u001d\f#\u0019A*\t\u0013\tu\u0011%!AA\u0002\t}\u0011a\u0001=%aA)\u0011\u0011O\u0007\u0003\u0018\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!Q\u0005\t\u0005\u0003K\u00139#\u0003\u0003\u0003*\u0005\u001d&AB(cU\u0016\u001cG/\u0001\u0005GC2d'-Y2l!\r\t\thM\n\u0005g\u0001\u000b)\u0010\u0006\u0002\u0003.U!!Q\u0007B\u001e)\u0011\u00119D!\u0010\u0011\u000b\u0005E4E!\u000f\u0011\u0007A\u0013Y\u0004B\u0003bm\t\u00071\u000bC\u0004\u0002hY\u0002\rA!\u000f\u0016\t\t\u0005#q\t\u000b\u0005\u0005\u0007\u0012I\u0005E\u0003B\u0003g\u0011)\u0005E\u0002Q\u0005\u000f\"Q!Y\u001cC\u0002MC\u0011B!\b8\u0003\u0003\u0005\rAa\u0013\u0011\u000b\u0005E4E!\u0012\u0016\r\t=#Q\u000bB-)\u0011\u0011\tFa\u0017\u0011\r]\u0004!1\u000bB,!\r\u0001&Q\u000b\u0003\u0006Of\u0012\ra\u0015\t\u0004!\neC!B1:\u0005\u0004\u0019\u0006b\u0002B/s\u0001\u000f!\u0011K\u0001\u0003KZ,BA!\u0019\u0003hMA1\u0005\u0011B2\u00037\n\t\u0007E\u0003x\u0001Q\u0013)\u0007E\u0002Q\u0005O\"Q!Y\u0012C\u0002M+\"A!\u001a\u0015\t\t5$q\u000e\t\u0006\u0003c\u001a#Q\r\u0005\b\u0003O2\u0003\u0019\u0001B3+\u0011\u0011\u0019H!\u001f\u0015\t\tU$1\u0010\t\u0006\u0003c\u001a#q\u000f\t\u0004!\neD!B1(\u0005\u0004\u0019\u0006\"CA4OA\u0005\t\u0019\u0001B<+\u0011\u0011yHa!\u0016\u0005\t\u0005%\u0006\u0002B3\u0003\u0017#Q!\u0019\u0015C\u0002M#2a\u0016BD\u0011%\t\u0019mKA\u0001\u0002\u0004\t9\f\u0006\u0003\u0002&\t-\u0005\u0002CAb[\u0005\u0005\t\u0019A,\u0015\t\u0005\r&q\u0012\u0005\n\u0003\u0007t\u0013\u0011!a\u0001\u0003o#B!!\n\u0003\u0014\"A\u00111Y\u0019\u0002\u0002\u0003\u0007q+\u0001\u0005Qe&|'/\u001b;z\u0001"
)
public interface Priority {
   static Priority apply(final Priority ev) {
      return Priority$.MODULE$.apply(ev);
   }

   static Priority preferred(final Object ev) {
      return Priority$.MODULE$.preferred(ev);
   }

   static Priority fallback(final Object ev) {
      return Priority$.MODULE$.fallback(ev);
   }

   default Object fold(final Function1 f1, final Function1 f2) {
      Object var3;
      if (this instanceof Preferred) {
         Preferred var5 = (Preferred)this;
         Object x = var5.get();
         var3 = f1.apply(x);
      } else {
         if (!(this instanceof Fallback)) {
            throw new MatchError(this);
         }

         Fallback var7 = (Fallback)this;
         Object y = var7.get();
         var3 = f2.apply(y);
      }

      return var3;
   }

   default Object join() {
      return this.fold((x$1) -> x$1, (x$2) -> x$2);
   }

   default Priority bimap(final Function1 f1, final Function1 f2) {
      Object var3;
      if (this instanceof Preferred) {
         Preferred var5 = (Preferred)this;
         Object x = var5.get();
         var3 = new Preferred(f1.apply(x));
      } else {
         if (!(this instanceof Fallback)) {
            throw new MatchError(this);
         }

         Fallback var7 = (Fallback)this;
         Object y = var7.get();
         var3 = new Fallback(f2.apply(y));
      }

      return (Priority)var3;
   }

   default Either toEither() {
      return (Either)this.fold((p) -> .MODULE$.Left().apply(p), (f) -> .MODULE$.Right().apply(f));
   }

   default boolean isPreferred() {
      return BoxesRunTime.unboxToBoolean(this.fold((x$3) -> BoxesRunTime.boxToBoolean($anonfun$isPreferred$1(x$3)), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$isPreferred$2(x$4))));
   }

   default boolean isFallback() {
      return BoxesRunTime.unboxToBoolean(this.fold((x$5) -> BoxesRunTime.boxToBoolean($anonfun$isFallback$1(x$5)), (x$6) -> BoxesRunTime.boxToBoolean($anonfun$isFallback$2(x$6))));
   }

   default Option getPreferred() {
      return (Option)this.fold((p) -> new Some(p), (x$7) -> scala.None..MODULE$);
   }

   default Option getFallback() {
      return (Option)this.fold((x$8) -> scala.None..MODULE$, (f) -> new Some(f));
   }

   // $FF: synthetic method
   static boolean $anonfun$isPreferred$1(final Object x$3) {
      return true;
   }

   // $FF: synthetic method
   static boolean $anonfun$isPreferred$2(final Object x$4) {
      return false;
   }

   // $FF: synthetic method
   static boolean $anonfun$isFallback$1(final Object x$5) {
      return false;
   }

   // $FF: synthetic method
   static boolean $anonfun$isFallback$2(final Object x$6) {
      return true;
   }

   static void $init$(final Priority $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Preferred implements Priority, Product, Serializable {
      private final Object get;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object fold(final Function1 f1, final Function1 f2) {
         return Priority.super.fold(f1, f2);
      }

      public Object join() {
         return Priority.super.join();
      }

      public Priority bimap(final Function1 f1, final Function1 f2) {
         return Priority.super.bimap(f1, f2);
      }

      public Either toEither() {
         return Priority.super.toEither();
      }

      public boolean isPreferred() {
         return Priority.super.isPreferred();
      }

      public boolean isFallback() {
         return Priority.super.isFallback();
      }

      public Option getPreferred() {
         return Priority.super.getPreferred();
      }

      public Option getFallback() {
         return Priority.super.getFallback();
      }

      public Object get() {
         return this.get;
      }

      public Preferred copy(final Object get) {
         return new Preferred(get);
      }

      public Object copy$default$1() {
         return this.get();
      }

      public String productPrefix() {
         return "Preferred";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.get();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Preferred;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "get";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof Preferred) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Preferred var4 = (Preferred)x$1;
                  if (BoxesRunTime.equals(this.get(), var4.get()) && var4.canEqual(this)) {
                     break label49;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Preferred(final Object get) {
         this.get = get;
         Priority.$init$(this);
         Product.$init$(this);
      }
   }

   public static class Preferred$ implements Serializable {
      public static final Preferred$ MODULE$ = new Preferred$();

      public final String toString() {
         return "Preferred";
      }

      public Preferred apply(final Object get) {
         return new Preferred(get);
      }

      public Option unapply(final Preferred x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.get()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Preferred$.class);
      }
   }

   public static class Fallback implements Priority, Product, Serializable {
      private final Object get;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object fold(final Function1 f1, final Function1 f2) {
         return Priority.super.fold(f1, f2);
      }

      public Object join() {
         return Priority.super.join();
      }

      public Priority bimap(final Function1 f1, final Function1 f2) {
         return Priority.super.bimap(f1, f2);
      }

      public Either toEither() {
         return Priority.super.toEither();
      }

      public boolean isPreferred() {
         return Priority.super.isPreferred();
      }

      public boolean isFallback() {
         return Priority.super.isFallback();
      }

      public Option getPreferred() {
         return Priority.super.getPreferred();
      }

      public Option getFallback() {
         return Priority.super.getFallback();
      }

      public Object get() {
         return this.get;
      }

      public Fallback copy(final Object get) {
         return new Fallback(get);
      }

      public Object copy$default$1() {
         return this.get();
      }

      public String productPrefix() {
         return "Fallback";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.get();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Fallback;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "get";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof Fallback) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Fallback var4 = (Fallback)x$1;
                  if (BoxesRunTime.equals(this.get(), var4.get()) && var4.canEqual(this)) {
                     break label49;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Fallback(final Object get) {
         this.get = get;
         Priority.$init$(this);
         Product.$init$(this);
      }
   }

   public static class Fallback$ implements Serializable {
      public static final Fallback$ MODULE$ = new Fallback$();

      public final String toString() {
         return "Fallback";
      }

      public Fallback apply(final Object get) {
         return new Fallback(get);
      }

      public Option unapply(final Fallback x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.get()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Fallback$.class);
      }
   }
}
