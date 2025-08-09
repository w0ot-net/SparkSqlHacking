package org.apache.spark.storage;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!\u0002\f\u0018\u0001fy\u0002\u0002C\u001c\u0001\u0005+\u0007I\u0011\u0001\u001d\t\u0011\u0005\u0003!\u0011#Q\u0001\neBQA\u0011\u0001\u0005\u0002\rCQA\u0012\u0001\u0005BaBqa\u0012\u0001\u0002\u0002\u0013\u0005\u0001\nC\u0004K\u0001E\u0005I\u0011A&\t\u000fY\u0003\u0011\u0011!C!/\"9q\fAA\u0001\n\u0003\u0001\u0007b\u00023\u0001\u0003\u0003%\t!\u001a\u0005\bW\u0002\t\t\u0011\"\u0011m\u0011\u001d\u0019\b!!A\u0005\u0002QDq!\u001f\u0001\u0002\u0002\u0013\u0005#\u0010C\u0004}\u0001\u0005\u0005I\u0011I?\t\u000fy\u0004\u0011\u0011!C!\u007f\u001eQ\u00111A\f\u0002\u0002#\u0005\u0011$!\u0002\u0007\u0013Y9\u0012\u0011!E\u00013\u0005\u001d\u0001B\u0002\"\u0011\t\u0003\ty\u0002C\u0005\u0002\"A\t\t\u0011\"\u0012\u0002$!I\u0011Q\u0005\t\u0002\u0002\u0013\u0005\u0015q\u0005\u0005\n\u0003W\u0001\u0012\u0011!CA\u0003[A\u0011\"!\u000f\u0011\u0003\u0003%I!a\u000f\u0003\u0017Q+7\u000f\u001e\"m_\u000e\\\u0017\n\u001a\u0006\u00031e\tqa\u001d;pe\u0006<WM\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h'\u0011\u0001\u0001\u0005\n\u0016\u0011\u0005\u0005\u0012S\"A\f\n\u0005\r:\"a\u0002\"m_\u000e\\\u0017\n\u001a\t\u0003K!j\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\b!J|G-^2u!\tYCG\u0004\u0002-e9\u0011Q&M\u0007\u0002])\u0011q\u0006M\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq%\u0003\u00024M\u00059\u0001/Y2lC\u001e,\u0017BA\u001b7\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019d%\u0001\u0002jIV\t\u0011\b\u0005\u0002;}9\u00111\b\u0010\t\u0003[\u0019J!!\u0010\u0014\u0002\rA\u0013X\rZ3g\u0013\ty\u0004I\u0001\u0004TiJLgn\u001a\u0006\u0003{\u0019\n1!\u001b3!\u0003\u0019a\u0014N\\5u}Q\u0011A)\u0012\t\u0003C\u0001AQaN\u0002A\u0002e\nAA\\1nK\u0006!1m\u001c9z)\t!\u0015\nC\u00048\u000bA\u0005\t\u0019A\u001d\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tAJ\u000b\u0002:\u001b.\na\n\u0005\u0002P)6\t\u0001K\u0003\u0002R%\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003'\u001a\n!\"\u00198o_R\fG/[8o\u0013\t)\u0006KA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001-\u0011\u0005esV\"\u0001.\u000b\u0005mc\u0016\u0001\u00027b]\u001eT\u0011!X\u0001\u0005U\u00064\u0018-\u0003\u0002@5\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0011\r\u0005\u0002&E&\u00111M\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003M&\u0004\"!J4\n\u0005!4#aA!os\"9!.CA\u0001\u0002\u0004\t\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001n!\rq\u0017OZ\u0007\u0002_*\u0011\u0001OJ\u0001\u000bG>dG.Z2uS>t\u0017B\u0001:p\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005UD\bCA\u0013w\u0013\t9hEA\u0004C_>dW-\u00198\t\u000f)\\\u0011\u0011!a\u0001M\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\tA6\u0010C\u0004k\u0019\u0005\u0005\t\u0019A1\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!Y\u0001\u0007KF,\u0018\r\\:\u0015\u0007U\f\t\u0001C\u0004k\u001d\u0005\u0005\t\u0019\u00014\u0002\u0017Q+7\u000f\u001e\"m_\u000e\\\u0017\n\u001a\t\u0003CA\u0019R\u0001EA\u0005\u0003+\u0001b!a\u0003\u0002\u0012e\"UBAA\u0007\u0015\r\tyAJ\u0001\beVtG/[7f\u0013\u0011\t\u0019\"!\u0004\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u0018\u0005uQBAA\r\u0015\r\tY\u0002X\u0001\u0003S>L1!NA\r)\t\t)!\u0001\u0005u_N#(/\u001b8h)\u0005A\u0016!B1qa2LHc\u0001#\u0002*!)qg\u0005a\u0001s\u00059QO\\1qa2LH\u0003BA\u0018\u0003k\u0001B!JA\u0019s%\u0019\u00111\u0007\u0014\u0003\r=\u0003H/[8o\u0011!\t9\u0004FA\u0001\u0002\u0004!\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\b\t\u00043\u0006}\u0012bAA!5\n1qJ\u00196fGR\u0004"
)
public class TestBlockId extends BlockId implements Product, Serializable {
   private final String id;

   public static Option unapply(final TestBlockId x$0) {
      return TestBlockId$.MODULE$.unapply(x$0);
   }

   public static TestBlockId apply(final String id) {
      return TestBlockId$.MODULE$.apply(id);
   }

   public static Function1 andThen(final Function1 g) {
      return TestBlockId$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return TestBlockId$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String id() {
      return this.id;
   }

   public String name() {
      return "test_" + this.id();
   }

   public TestBlockId copy(final String id) {
      return new TestBlockId(id);
   }

   public String copy$default$1() {
      return this.id();
   }

   public String productPrefix() {
      return "TestBlockId";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.id();
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
      return x$1 instanceof TestBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof TestBlockId) {
               label40: {
                  TestBlockId var4 = (TestBlockId)x$1;
                  String var10000 = this.id();
                  String var5 = var4.id();
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

   public TestBlockId(final String id) {
      this.id = id;
      Product.$init$(this);
   }
}
