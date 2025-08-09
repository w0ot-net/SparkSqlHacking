package org.apache.spark.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc\u0001B\r\u001b\u0001\u000eB\u0001\"\u000f\u0001\u0003\u0016\u0004%\tA\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005w!A\u0001\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005F\u0001\tE\t\u0015!\u0003C\u0011\u00151\u0005\u0001\"\u0001H\u0011\u001dY\u0005!!A\u0005\u00021Cqa\u0014\u0001\u0012\u0002\u0013\u0005\u0001\u000bC\u0004\\\u0001E\u0005I\u0011\u0001/\t\u000fy\u0003\u0011\u0011!C!?\"9\u0001\u000eAA\u0001\n\u0003I\u0007bB7\u0001\u0003\u0003%\tA\u001c\u0005\bc\u0002\t\t\u0011\"\u0011s\u0011\u001dI\b!!A\u0005\u0002iD\u0001b \u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0001\u0005\n\u0003\u000b\u0001\u0011\u0011!C!\u0003\u000fA\u0011\"!\u0003\u0001\u0003\u0003%\t%a\u0003\t\u0013\u00055\u0001!!A\u0005B\u0005=qaBA\n5!\u0005\u0011Q\u0003\u0004\u00073iA\t!a\u0006\t\r\u0019\u001bB\u0011AA\u0012\u0011\u001d\t)c\u0005C\u0001\u0003OA\u0011\"!\f\u0014\u0003\u0003%\t)a\f\t\u0013\u0005U2#!A\u0005\u0002\u0006]\u0002\"CA%'\u0005\u0005I\u0011BA&\u0005\riEi\u0011\u0006\u00037q\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003;y\tQa\u001d9be.T!a\b\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0013aA8sO\u000e\u00011\u0003\u0002\u0001%U5\u0002\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012a!\u00118z%\u00164\u0007CA\u0013,\u0013\tacEA\u0004Qe>$Wo\u0019;\u0011\u000592dBA\u00185\u001d\t\u00014'D\u00012\u0015\t\u0011$%\u0001\u0004=e>|GOP\u0005\u0002O%\u0011QGJ\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0004H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00026M\u0005\u00191.Z=\u0016\u0003m\u0002\"\u0001P\u001f\u000e\u0003iI!A\u0010\u000e\u0003\r1{wmS3z\u0003\u0011YW-\u001f\u0011\u0002\u000bY\fG.^3\u0016\u0003\t\u0003\"!J\"\n\u0005\u00113#aA!os\u00061a/\u00197vK\u0002\na\u0001P5oSRtDc\u0001%J\u0015B\u0011A\b\u0001\u0005\u0006s\u0015\u0001\ra\u000f\u0005\u0006\u0001\u0016\u0001\rAQ\u0001\u0005G>\u0004\u0018\u0010F\u0002I\u001b:Cq!\u000f\u0004\u0011\u0002\u0003\u00071\bC\u0004A\rA\u0005\t\u0019\u0001\"\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0011K\u000b\u0002<%.\n1\u000b\u0005\u0002U36\tQK\u0003\u0002W/\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u00031\u001a\n!\"\u00198o_R\fG/[8o\u0013\tQVKA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001^U\t\u0011%+A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002AB\u0011\u0011MZ\u0007\u0002E*\u00111\rZ\u0001\u0005Y\u0006twMC\u0001f\u0003\u0011Q\u0017M^1\n\u0005\u001d\u0014'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001k!\t)3.\u0003\u0002mM\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011!i\u001c\u0005\ba.\t\t\u00111\u0001k\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t1\u000fE\u0002uo\nk\u0011!\u001e\u0006\u0003m\u001a\n!bY8mY\u0016\u001cG/[8o\u0013\tAXO\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA>\u007f!\t)C0\u0003\u0002~M\t9!i\\8mK\u0006t\u0007b\u00029\u000e\u0003\u0003\u0005\rAQ\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002a\u0003\u0007Aq\u0001\u001d\b\u0002\u0002\u0003\u0007!.\u0001\u0005iCND7i\u001c3f)\u0005Q\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0001\fa!Z9vC2\u001cHcA>\u0002\u0012!9\u0001/EA\u0001\u0002\u0004\u0011\u0015aA'E\u0007B\u0011AhE\n\u0005'\u0011\nI\u0002\u0005\u0003\u0002\u001c\u0005\u0005RBAA\u000f\u0015\r\ty\u0002Z\u0001\u0003S>L1aNA\u000f)\t\t)\"\u0001\u0002pMR)\u0001*!\u000b\u0002,!)\u0011(\u0006a\u0001w!)\u0001)\u0006a\u0001\u0005\u0006)\u0011\r\u001d9msR)\u0001*!\r\u00024!)\u0011H\u0006a\u0001w!)\u0001I\u0006a\u0001\u0005\u00069QO\\1qa2LH\u0003BA\u001d\u0003\u000b\u0002R!JA\u001e\u0003\u007fI1!!\u0010'\u0005\u0019y\u0005\u000f^5p]B)Q%!\u0011<\u0005&\u0019\u00111\t\u0014\u0003\rQ+\b\u000f\\33\u0011!\t9eFA\u0001\u0002\u0004A\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\n\t\u0004C\u0006=\u0013bAA)E\n1qJ\u00196fGR\u0004"
)
public class MDC implements Product, Serializable {
   private final LogKey key;
   private final Object value;

   public static Option unapply(final MDC x$0) {
      return MDC$.MODULE$.unapply(x$0);
   }

   public static MDC apply(final LogKey key, final Object value) {
      return MDC$.MODULE$.apply(key, value);
   }

   public static MDC of(final LogKey key, final Object value) {
      return MDC$.MODULE$.of(key, value);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public LogKey key() {
      return this.key;
   }

   public Object value() {
      return this.value;
   }

   public MDC copy(final LogKey key, final Object value) {
      return new MDC(key, value);
   }

   public LogKey copy$default$1() {
      return this.key();
   }

   public Object copy$default$2() {
      return this.value();
   }

   public String productPrefix() {
      return "MDC";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.key();
         }
         case 1 -> {
            return this.value();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof MDC;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "key";
         }
         case 1 -> {
            return "value";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
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
         label49: {
            if (x$1 instanceof MDC) {
               label42: {
                  MDC var4 = (MDC)x$1;
                  LogKey var10000 = this.key();
                  LogKey var5 = var4.key();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label42;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label42;
                  }

                  if (BoxesRunTime.equals(this.value(), var4.value()) && var4.canEqual(this)) {
                     break label49;
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

   public MDC(final LogKey key, final Object value) {
      this.key = key;
      this.value = value;
      Product.$init$(this);
      scala.Predef..MODULE$.require(!(value instanceof MessageWithContext), () -> "the class of value cannot be MessageWithContext");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
