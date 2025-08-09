package org.apache.spark.internal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md\u0001B\u000e\u001d\u0001\u0016B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005{!Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005Q\u0001\tE\t\u0015!\u0003I\u0011\u0015\t\u0006\u0001\"\u0001S\u0011\u00159\u0006\u0001\"\u0001Y\u0011\u0015Y\u0006\u0001\"\u0001]\u0011\u001di\u0006!!A\u0005\u0002yCq!\u0019\u0001\u0012\u0002\u0013\u0005!\rC\u0004n\u0001E\u0005I\u0011\u00018\t\u000fA\u0004\u0011\u0011!C!c\"9q\u000fAA\u0001\n\u0003A\bb\u0002?\u0001\u0003\u0003%\t! \u0005\n\u0003\u000f\u0001\u0011\u0011!C!\u0003\u0013A\u0011\"a\u0006\u0001\u0003\u0003%\t!!\u0007\t\u0013\u0005\r\u0002!!A\u0005B\u0005\u0015\u0002\"CA\u0015\u0001\u0005\u0005I\u0011IA\u0016\u0011%\ti\u0003AA\u0001\n\u0003\ny\u0003C\u0005\u00022\u0001\t\t\u0011\"\u0011\u00024\u001dI\u0011q\u0007\u000f\u0002\u0002#\u0005\u0011\u0011\b\u0004\t7q\t\t\u0011#\u0001\u0002<!1\u0011+\u0006C\u0001\u0003'B\u0011\"!\f\u0016\u0003\u0003%)%a\f\t\u0013\u0005US#!A\u0005\u0002\u0006]\u0003\"CA/+\u0005\u0005I\u0011QA0\u0011%\t\t(FA\u0001\n\u0013\t\u0019H\u0001\nNKN\u001c\u0018mZ3XSRD7i\u001c8uKb$(BA\u000f\u001f\u0003!Ig\u000e^3s]\u0006d'BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0004\u0001M!\u0001A\n\u00170!\t9#&D\u0001)\u0015\u0005I\u0013!B:dC2\f\u0017BA\u0016)\u0005\u0019\te.\u001f*fMB\u0011q%L\u0005\u0003]!\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00021q9\u0011\u0011G\u000e\b\u0003eUj\u0011a\r\u0006\u0003i\u0011\na\u0001\u0010:p_Rt\u0014\"A\u0015\n\u0005]B\u0013a\u00029bG.\fw-Z\u0005\u0003si\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!a\u000e\u0015\u0002\u000f5,7o]1hKV\tQ\b\u0005\u0002?\u0005:\u0011q\b\u0011\t\u0003e!J!!\u0011\u0015\u0002\rA\u0013X\rZ3g\u0013\t\u0019EI\u0001\u0004TiJLgn\u001a\u0006\u0003\u0003\"\n\u0001\"\\3tg\u0006<W\rI\u0001\bG>tG/\u001a=u+\u0005A\u0005\u0003B%O{uj\u0011A\u0013\u0006\u0003\u00172\u000bA!\u001e;jY*\tQ*\u0001\u0003kCZ\f\u0017BA(K\u0005\ri\u0015\r]\u0001\tG>tG/\u001a=uA\u00051A(\u001b8jiz\"2aU+W!\t!\u0006!D\u0001\u001d\u0011\u0015YT\u00011\u0001>\u0011\u00151U\u00011\u0001I\u0003\u0015!\u0003\u000f\\;t)\t\u0019\u0016\fC\u0003[\r\u0001\u00071+A\u0002nI\u000e\f1b\u001d;sSBl\u0015M]4j]V\t1+\u0001\u0003d_BLHcA*`A\"91\b\u0003I\u0001\u0002\u0004i\u0004b\u0002$\t!\u0003\u0005\r\u0001S\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\u0019'FA\u001feW\u0005)\u0007C\u00014l\u001b\u00059'B\u00015j\u0003%)hn\u00195fG.,GM\u0003\u0002kQ\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00051<'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A8+\u0005!#\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001s!\t\u0019h/D\u0001u\u0015\t)H*\u0001\u0003mC:<\u0017BA\"u\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005I\bCA\u0014{\u0013\tY\bFA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002\u007f\u0003\u0007\u0001\"aJ@\n\u0007\u0005\u0005\u0001FA\u0002B]fD\u0001\"!\u0002\u000e\u0003\u0003\u0005\r!_\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005-\u0001#BA\u0007\u0003'qXBAA\b\u0015\r\t\t\u0002K\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u000b\u0003\u001f\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111DA\u0011!\r9\u0013QD\u0005\u0004\u0003?A#a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003\u000by\u0011\u0011!a\u0001}\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r\u0011\u0018q\u0005\u0005\t\u0003\u000b\u0001\u0012\u0011!a\u0001s\u0006A\u0001.Y:i\u0007>$W\rF\u0001z\u0003!!xn\u0015;sS:<G#\u0001:\u0002\r\u0015\fX/\u00197t)\u0011\tY\"!\u000e\t\u0011\u0005\u00151#!AA\u0002y\f!#T3tg\u0006<WmV5uQ\u000e{g\u000e^3yiB\u0011A+F\n\u0006+\u0005u\u0012\u0011\n\t\b\u0003\u007f\t)%\u0010%T\u001b\t\t\tEC\u0002\u0002D!\nqA];oi&lW-\u0003\u0003\u0002H\u0005\u0005#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u00111JA)\u001b\t\tiEC\u0002\u0002P1\u000b!![8\n\u0007e\ni\u0005\u0006\u0002\u0002:\u0005)\u0011\r\u001d9msR)1+!\u0017\u0002\\!)1\b\u0007a\u0001{!)a\t\u0007a\u0001\u0011\u00069QO\\1qa2LH\u0003BA1\u0003[\u0002RaJA2\u0003OJ1!!\u001a)\u0005\u0019y\u0005\u000f^5p]B)q%!\u001b>\u0011&\u0019\u00111\u000e\u0015\u0003\rQ+\b\u000f\\33\u0011!\ty'GA\u0001\u0002\u0004\u0019\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u000f\t\u0004g\u0006]\u0014bAA=i\n1qJ\u00196fGR\u0004"
)
public class MessageWithContext implements Product, Serializable {
   private final String message;
   private final Map context;

   public static Option unapply(final MessageWithContext x$0) {
      return MessageWithContext$.MODULE$.unapply(x$0);
   }

   public static MessageWithContext apply(final String message, final Map context) {
      return MessageWithContext$.MODULE$.apply(message, context);
   }

   public static Function1 tupled() {
      return MessageWithContext$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return MessageWithContext$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String message() {
      return this.message;
   }

   public Map context() {
      return this.context;
   }

   public MessageWithContext $plus(final MessageWithContext mdc) {
      HashMap resultMap = new HashMap(this.context());
      resultMap.putAll(mdc.context());
      return new MessageWithContext(this.message() + mdc.message(), resultMap);
   }

   public MessageWithContext stripMargin() {
      return this.copy(.MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString(this.message())), this.copy$default$2());
   }

   public MessageWithContext copy(final String message, final Map context) {
      return new MessageWithContext(message, context);
   }

   public String copy$default$1() {
      return this.message();
   }

   public Map copy$default$2() {
      return this.context();
   }

   public String productPrefix() {
      return "MessageWithContext";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.message();
         }
         case 1 -> {
            return this.context();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof MessageWithContext;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "message";
         }
         case 1 -> {
            return "context";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof MessageWithContext) {
               label48: {
                  MessageWithContext var4 = (MessageWithContext)x$1;
                  String var10000 = this.message();
                  String var5 = var4.message();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Map var7 = this.context();
                  Map var6 = var4.context();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public MessageWithContext(final String message, final Map context) {
      this.message = message;
      this.context = context;
      Product.$init$(this);
   }
}
