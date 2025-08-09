package org.json4s.reflect;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2QAA\u0002\u0002\")AQ\u0001\t\u0001\u0005\u0002\u0005\u0012!\u0002R3tGJL\u0007\u000f^8s\u0015\t!Q!A\u0004sK\u001adWm\u0019;\u000b\u0005\u00199\u0011A\u00026t_:$4OC\u0001\t\u0003\ry'oZ\u0002\u0001'\u0011\u00011\"\u0005\u000b\u0011\u00051yQ\"A\u0007\u000b\u00039\tQa]2bY\u0006L!\u0001E\u0007\u0003\r\u0005s\u0017PU3g!\ta!#\u0003\u0002\u0014\u001b\t9\u0001K]8ek\u000e$\bCA\u000b\u001e\u001d\t12D\u0004\u0002\u001855\t\u0001D\u0003\u0002\u001a\u0013\u00051AH]8pizJ\u0011AD\u0005\u000395\tq\u0001]1dW\u0006<W-\u0003\u0002\u001f?\ta1+\u001a:jC2L'0\u00192mK*\u0011A$D\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\t\u0002\"a\t\u0001\u000e\u0003\rIc\u0001A\u0013(S-j\u0013B\u0001\u0014\u0004\u0005U\u0019uN\\:ueV\u001cGo\u001c:EKN\u001c'/\u001b9u_JL!\u0001K\u0002\u00035\r{gn\u001d;sk\u000e$xN\u001d)be\u0006lG)Z:de&\u0004Ho\u001c:\n\u0005)\u001a!\u0001E(cU\u0016\u001cG\u000fR3tGJL\u0007\u000f^8s\u0013\ta3A\u0001\nQe>\u0004XM\u001d;z\t\u0016\u001c8M]5qi>\u0014\u0018B\u0001\u0018\u0004\u0005M\u0019\u0016N\\4mKR|g\u000eR3tGJL\u0007\u000f^8s\u0001"
)
public abstract class Descriptor implements Product, Serializable {
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

   public Descriptor() {
      Product.$init$(this);
   }
}
