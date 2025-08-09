package org.json4s;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2aa\u0001\u0003\u0002\"\u0011A\u0001\"B\u0010\u0001\t\u0003\u0001\u0003bB\u0012\u0001\u0005\u00045\t\u0001\n\u0002\b'\u0016<W.\u001a8u\u0015\t)a!\u0001\u0004kg>tGg\u001d\u0006\u0002\u000f\u0005\u0019qN]4\u0014\t\u0001IqB\u0005\t\u0003\u00155i\u0011a\u0003\u0006\u0002\u0019\u0005)1oY1mC&\u0011ab\u0003\u0002\u0007\u0003:L(+\u001a4\u0011\u0005)\u0001\u0012BA\t\f\u0005\u001d\u0001&o\u001c3vGR\u0004\"a\u0005\u000f\u000f\u0005QQbBA\u000b\u001a\u001b\u00051\"BA\f\u0019\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0007\n\u0005mY\u0011a\u00029bG.\fw-Z\u0005\u0003;y\u0011AbU3sS\u0006d\u0017N_1cY\u0016T!aG\u0006\u0002\rqJg.\u001b;?)\u0005\t\u0003C\u0001\u0012\u0001\u001b\u0005!\u0011aA:fOV\tQ\u0005E\u0002\u000bM!J!aJ\u0006\u0003\u000b\u0005\u0013(/Y=\u0011\u0005)I\u0013B\u0001\u0016\f\u0005\u0011\u0019\u0005.\u0019:*\u0007\u0001a\u0003'\u0003\u0002.]\t\tB)[:q_N\f'\r\\3TK\u001elWM\u001c;\u000b\u0005=\"\u0011\u0001C*fO6,g\u000e^:\n\u0005Er#a\u0004*fGf\u001cG.\u001a3TK\u001elWM\u001c;"
)
public abstract class Segment implements Product, Serializable {
   public Iterator productIterator() {
      return Product.productIterator$(this);
   }

   public String productPrefix() {
      return Product.productPrefix$(this);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public abstract char[] seg();

   public Segment() {
      Product.$init$(this);
   }
}
