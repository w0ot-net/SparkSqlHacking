package org.apache.spark.sql.types;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uu!\u0002\u000f\u001e\u0011\u0003Ac!\u0002\u0016\u001e\u0011\u0003Y\u0003\"B\u001c\u0002\t\u0003A\u0004BB\u001d\u0002\t\u0003z\"\b\u0003\u0004?\u0003\u0011\u0005sd\u0010\u0005\u0007\u0011\u0006!\teH%\t\u000fU\u000b\u0011\u0011!CA-\"I\u0011QP\u0001\u0002\u0002\u0013\u0005\u0015q\u0010\u0005\n\u0003'\u000b\u0011\u0011!C\u0005\u0003+3AAK\u000fA1\"AA-\u0003BK\u0002\u0013\u0005Q\r\u0003\u0005o\u0013\tE\t\u0015!\u0003g\u0011\u00159\u0014\u0002\"\u0001w\u0011\u0015a\u0018\u0002\"\u0011~\u0011\u0019\t\u0019!\u0003C\u0001u!)\u0001*\u0003C!\u0013\"1a(\u0003C!\u0003\u000bA\u0011\"!\u0003\n\u0003\u0003%\t!a\u0003\t\u0013\u0005=\u0011\"%A\u0005\u0002\u0005E\u0001\"CA\u001c\u0013\u0005\u0005I\u0011IA\u001d\u0011!\ty$CA\u0001\n\u0003i\b\"CA!\u0013\u0005\u0005I\u0011AA\"\u0011%\tI%CA\u0001\n\u0003\nY\u0005C\u0005\u0002Z%\t\t\u0011\"\u0001\u0002\\!I\u0011qL\u0005\u0002\u0002\u0013\u0005\u0013\u0011\r\u0005\n\u0003KJ\u0011\u0011!C!\u0003OB\u0011\"!\u001b\n\u0003\u0003%\t%a\u001b\t\u0013\u00055\u0014\"!A\u0005B\u0005=\u0014AC(cU\u0016\u001cG\u000fV=qK*\u0011adH\u0001\u0006if\u0004Xm\u001d\u0006\u0003A\u0005\n1a]9m\u0015\t\u00113%A\u0003ta\u0006\u00148N\u0003\u0002%K\u00051\u0011\r]1dQ\u0016T\u0011AJ\u0001\u0004_J<7\u0001\u0001\t\u0003S\u0005i\u0011!\b\u0002\u000b\u001f\nTWm\u0019;UsB,7cA\u0001-_A\u0011\u0011&L\u0005\u0003]u\u0011\u0001#\u00112tiJ\f7\r\u001e#bi\u0006$\u0016\u0010]3\u0011\u0005A*T\"A\u0019\u000b\u0005I\u001a\u0014AA5p\u0015\u0005!\u0014\u0001\u00026bm\u0006L!AN\u0019\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005A\u0013a\u00053fM\u0006,H\u000e^\"p]\u000e\u0014X\r^3UsB,W#A\u001e\u0011\u0005%b\u0014BA\u001f\u001e\u0005!!\u0015\r^1UsB,\u0017aC1dG\u0016\u0004Ho\u001d+za\u0016$\"\u0001\u0011$\u0011\u0005\u0005#U\"\u0001\"\u000b\u0003\r\u000bQa]2bY\u0006L!!\u0012\"\u0003\u000f\t{w\u000e\\3b]\")q\t\u0002a\u0001w\u0005)q\u000e\u001e5fe\u0006a1/[7qY\u0016\u001cFO]5oOV\t!\n\u0005\u0002L%:\u0011A\n\u0015\t\u0003\u001b\nk\u0011A\u0014\u0006\u0003\u001f\u001e\na\u0001\u0010:p_Rt\u0014BA)C\u0003\u0019\u0001&/\u001a3fM&\u00111\u000b\u0016\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005E\u0013\u0015!B1qa2LHcA,\u0002tA\u0011\u0011&C\n\u0005\u0013mJF\f\u0005\u0002B5&\u00111L\u0011\u0002\b!J|G-^2u!\ti&M\u0004\u0002_A:\u0011QjX\u0005\u0002\u0007&\u0011\u0011MQ\u0001\ba\u0006\u001c7.Y4f\u0013\t14M\u0003\u0002b\u0005\u0006\u00191\r\\:\u0016\u0003\u0019\u0004$a\u001a7\u0011\u0007-C'.\u0003\u0002j)\n)1\t\\1tgB\u00111\u000e\u001c\u0007\u0001\t%i7\"!A\u0001\u0002\u000b\u0005qNA\u0002`IE\nAa\u00197tAE\u0011\u0001o\u001d\t\u0003\u0003FL!A\u001d\"\u0003\u000f9{G\u000f[5oOB\u0011\u0011\t^\u0005\u0003k\n\u00131!\u00118z)\t9v\u000fC\u0003e\u0019\u0001\u0007\u0001\u0010\r\u0002zwB\u00191\n\u001b>\u0011\u0005-\\H!C7x\u0003\u0003\u0005\tQ!\u0001p\u0003-!WMZ1vYR\u001c\u0016N_3\u0016\u0003y\u0004\"!Q@\n\u0007\u0005\u0005!IA\u0002J]R\f!\"Y:Ok2d\u0017M\u00197f)\r\u0001\u0015q\u0001\u0005\u0006\u000fB\u0001\raO\u0001\u0005G>\u0004\u0018\u0010F\u0002X\u0003\u001bAq\u0001Z\t\u0011\u0002\u0003\u0007\u00010\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005M\u0001\u0007BA\u000b\u0003GQC!a\u0006\u0002&A1\u0011\u0011DA\u0010\u0003Ci!!a\u0007\u000b\u0007\u0005u1'\u0001\u0003mC:<\u0017bA5\u0002\u001cA\u00191.a\t\u0005\u00135\u0014\u0012\u0011!A\u0001\u0006\u0003y7FAA\u0014!\u0011\tI#a\r\u000e\u0005\u0005-\"\u0002BA\u0017\u0003_\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005E\")\u0001\u0006b]:|G/\u0019;j_:LA!!\u000e\u0002,\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tY\u0004\u0005\u0003\u0002\u001a\u0005u\u0012bA*\u0002\u001c\u0005a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA:\u0002F!A\u0011qI\u000b\u0002\u0002\u0003\u0007a0A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u001b\u0002R!a\u0014\u0002VMl!!!\u0015\u000b\u0007\u0005M#)\u0001\u0006d_2dWm\u0019;j_:LA!a\u0016\u0002R\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\r\u0001\u0015Q\f\u0005\t\u0003\u000f:\u0012\u0011!a\u0001g\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\tY$a\u0019\t\u0011\u0005\u001d\u0003$!AA\u0002y\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002}\u0006AAo\\*ue&tw\r\u0006\u0002\u0002<\u00051Q-];bYN$2\u0001QA9\u0011!\t9eGA\u0001\u0002\u0004\u0019\bB\u00023\u0007\u0001\u0004\t)\b\r\u0003\u0002x\u0005m\u0004\u0003B&i\u0003s\u00022a[A>\t)i\u00171OA\u0001\u0002\u0003\u0015\ta\\\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t)a$\u0011\u000b\u0005\u000b\u0019)a\"\n\u0007\u0005\u0015%I\u0001\u0004PaRLwN\u001c\u0019\u0005\u0003\u0013\u000bi\t\u0005\u0003LQ\u0006-\u0005cA6\u0002\u000e\u0012IQnBA\u0001\u0002\u0003\u0015\ta\u001c\u0005\t\u0003#;\u0011\u0011!a\u0001/\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005]\u0005\u0003BA\r\u00033KA!a'\u0002\u001c\t1qJ\u00196fGR\u0004"
)
public class ObjectType extends DataType implements Product, Serializable {
   private final Class cls;

   public static Option unapply(final ObjectType x$0) {
      return ObjectType$.MODULE$.unapply(x$0);
   }

   public static ObjectType apply(final Class cls) {
      return ObjectType$.MODULE$.apply(cls);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Class cls() {
      return this.cls;
   }

   public int defaultSize() {
      return 4096;
   }

   public DataType asNullable() {
      return this;
   }

   public String simpleString() {
      return this.cls().getName();
   }

   public boolean acceptsType(final DataType other) {
      if (other instanceof ObjectType var4) {
         Class otherCls = var4.cls();
         return this.cls().isAssignableFrom(otherCls);
      } else {
         return false;
      }
   }

   public ObjectType copy(final Class cls) {
      return new ObjectType(cls);
   }

   public Class copy$default$1() {
      return this.cls();
   }

   public String productPrefix() {
      return "ObjectType";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.cls();
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
      return x$1 instanceof ObjectType;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "cls";
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
         label47: {
            if (x$1 instanceof ObjectType) {
               label40: {
                  ObjectType var4 = (ObjectType)x$1;
                  Class var10000 = this.cls();
                  Class var5 = var4.cls();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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

   public ObjectType(final Class cls) {
      this.cls = cls;
      Product.$init$(this);
   }
}
