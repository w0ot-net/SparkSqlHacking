package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ue!\u0002\u0010 \u0001\u0006J\u0003\u0002\u0003#\u0001\u0005+\u0007I\u0011A#\t\u0011\u0019\u0003!\u0011#Q\u0001\nAB\u0001b\u0012\u0001\u0003\u0016\u0004%\t\u0005\u0013\u0005\t#\u0002\u0011\t\u0012)A\u0005\u0013\")!\u000b\u0001C\u0001'\"1q\u000b\u0001C!?aCQA\t\u0001\u0005BeCaA\u0019\u0001\u0005B}\u0019\u0007b\u00026\u0001\u0003\u0003%\ta\u001b\u0005\b]\u0002\t\n\u0011\"\u0001p\u0011\u001dQ\b!%A\u0005\u0002mDq! \u0001\u0002\u0002\u0013\u0005c\u0010C\u0005\u0002\u000e\u0001\t\t\u0011\"\u0001\u0002\u0010!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0004\u0005\n\u0003K\u0001\u0011\u0011!C!\u0003OA\u0011\"!\u000e\u0001\u0003\u0003%\t!a\u000e\t\u0013\u0005\u0005\u0003!!A\u0005B\u0005\r\u0003\"CA$\u0001\u0005\u0005I\u0011IA%\u0011%\tY\u0005AA\u0001\n\u0003\ni\u0005C\u0005\u0002P\u0001\t\t\u0011\"\u0011\u0002R\u001dQ\u0011QK\u0010\u0002\u0002#\u0005\u0011%a\u0016\u0007\u0013yy\u0012\u0011!E\u0001C\u0005e\u0003B\u0002*\u0017\t\u0003\t\t\bC\u0005\u0002LY\t\t\u0011\"\u0012\u0002N!I\u00111\u000f\f\u0002\u0002\u0013\u0005\u0015Q\u000f\u0005\t\u0003w2\u0012\u0013!C\u0001w\"I\u0011Q\u0010\f\u0002\u0002\u0013\u0005\u0015q\u0010\u0005\t\u0003#3\u0012\u0013!C\u0001w\"I\u00111\u0013\f\u0002\u0002\u0013%\u0011Q\u0013\u0002\u000f\u0019\u0006T\u00180\u0012=qe\u0016\u001c8/[8o\u0015\t\u0001\u0013%\u0001\u0005j]R,'O\\1m\u0015\t\u00113%A\u0002tc2T!\u0001J\u0013\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0019:\u0013AB1qC\u000eDWMC\u0001)\u0003\ry'oZ\n\u0006\u0001)\u0002Dg\u000e\t\u0003W9j\u0011\u0001\f\u0006\u0002[\u0005)1oY1mC&\u0011q\u0006\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E\u0012T\"A\u0010\n\u0005Mz\"AC\"pYVlgNT8eKB\u00111&N\u0005\u0003m1\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00029\u0003:\u0011\u0011h\u0010\b\u0003uyj\u0011a\u000f\u0006\u0003yu\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002[%\u0011\u0001\tL\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00115I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002AY\u0005)1\r[5mIV\t\u0001'\u0001\u0004dQ&dG\rI\u0001\u0007_JLw-\u001b8\u0016\u0003%\u0003\"AS(\u000e\u0003-S!\u0001T'\u0002\u000bQ\u0014X-Z:\u000b\u00059\u000b\u0013\u0001C2bi\u0006d\u0017p\u001d;\n\u0005A[%AB(sS\u001eLg.A\u0004pe&<\u0017N\u001c\u0011\u0002\rqJg.\u001b;?)\r!VK\u0016\t\u0003c\u0001AQ\u0001R\u0003A\u0002ABqaR\u0003\u0011\u0002\u0003\u0007\u0011*A\u0005o_Jl\u0017\r\\5{KR\t\u0001'F\u0001[!\tYvL\u0004\u0002];B\u0011!\bL\u0005\u0003=2\na\u0001\u0015:fI\u00164\u0017B\u00011b\u0005\u0019\u0019FO]5oO*\u0011a\fL\u0001\tG\"LG\u000e\u001a:f]V\tA\rE\u00029K\u001eL!AZ\"\u0003\u0007M+\u0017\u000f\u0005\u00022Q&\u0011\u0011n\b\u0002\u000f\u0007>dW/\u001c8O_\u0012,G*[6f\u0003\u0011\u0019w\u000e]=\u0015\u0007QcW\u000eC\u0004E\u0013A\u0005\t\u0019\u0001\u0019\t\u000f\u001dK\u0001\u0013!a\u0001\u0013\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00019+\u0005A\n8&\u0001:\u0011\u0005MDX\"\u0001;\u000b\u0005U4\u0018!C;oG\",7m[3e\u0015\t9H&\u0001\u0006b]:|G/\u0019;j_:L!!\u001f;\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003qT#!S9\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005y\b\u0003BA\u0001\u0003\u0017i!!a\u0001\u000b\t\u0005\u0015\u0011qA\u0001\u0005Y\u0006twM\u0003\u0002\u0002\n\u0005!!.\u0019<b\u0013\r\u0001\u00171A\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003#\u00012aKA\n\u0013\r\t)\u0002\f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u00037\t\t\u0003E\u0002,\u0003;I1!a\b-\u0005\r\te.\u001f\u0005\n\u0003Gq\u0011\u0011!a\u0001\u0003#\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0015!\u0019\tY#!\r\u0002\u001c5\u0011\u0011Q\u0006\u0006\u0004\u0003_a\u0013AC2pY2,7\r^5p]&!\u00111GA\u0017\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005e\u0012q\b\t\u0004W\u0005m\u0012bAA\u001fY\t9!i\\8mK\u0006t\u0007\"CA\u0012!\u0005\u0005\t\u0019AA\u000e\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007}\f)\u0005C\u0005\u0002$E\t\t\u00111\u0001\u0002\u0012\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0012\u0005AAo\\*ue&tw\rF\u0001\u0000\u0003\u0019)\u0017/^1mgR!\u0011\u0011HA*\u0011%\t\u0019\u0003FA\u0001\u0002\u0004\tY\"\u0001\bMCjLX\t\u001f9sKN\u001c\u0018n\u001c8\u0011\u0005E22#\u0002\f\u0002\\\u0005\u001d\u0004cBA/\u0003G\u0002\u0014\nV\u0007\u0003\u0003?R1!!\u0019-\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u001a\u0002`\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005%\u0014qN\u0007\u0003\u0003WRA!!\u001c\u0002\b\u0005\u0011\u0011n\\\u0005\u0004\u0005\u0006-DCAA,\u0003\u0015\t\u0007\u000f\u001d7z)\u0015!\u0016qOA=\u0011\u0015!\u0015\u00041\u00011\u0011\u001d9\u0015\u0004%AA\u0002%\u000bq\"\u00199qYf$C-\u001a4bk2$HEM\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t)!$\u0011\u000b-\n\u0019)a\"\n\u0007\u0005\u0015EF\u0001\u0004PaRLwN\u001c\t\u0006W\u0005%\u0005'S\u0005\u0004\u0003\u0017c#A\u0002+va2,'\u0007\u0003\u0005\u0002\u0010n\t\t\u00111\u0001U\u0003\rAH\u0005M\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005]\u0005\u0003BA\u0001\u00033KA!a'\u0002\u0004\t1qJ\u00196fGR\u0004"
)
public class LazyExpression implements ColumnNode, Product, Serializable {
   private final ColumnNode child;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$2() {
      return LazyExpression$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final LazyExpression x$0) {
      return LazyExpression$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$2() {
      return LazyExpression$.MODULE$.apply$default$2();
   }

   public static LazyExpression apply(final ColumnNode child, final Origin origin) {
      return LazyExpression$.MODULE$.apply(child, origin);
   }

   public static Function1 tupled() {
      return LazyExpression$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return LazyExpression$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public void foreach(final Function1 f) {
      ColumnNodeLike.foreach$(this, f);
   }

   public Seq collect(final PartialFunction pf) {
      return ColumnNodeLike.collect$(this, pf);
   }

   private ColumnNode normalized$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.normalized = ColumnNode.normalized$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalized;
   }

   public ColumnNode normalized() {
      return !this.bitmap$0 ? this.normalized$lzycompute() : this.normalized;
   }

   public ColumnNode child() {
      return this.child;
   }

   public Origin origin() {
      return this.origin;
   }

   public ColumnNode normalize() {
      return this.copy(this.child().normalize(), ColumnNode$.MODULE$.NO_ORIGIN());
   }

   public String sql() {
      ColumnNode$ var10000 = ColumnNode$.MODULE$;
      .colon.colon var10001 = new .colon.colon(this.child(), scala.collection.immutable.Nil..MODULE$);
      return "lazy" + var10000.argumentsToSql(var10001);
   }

   public Seq children() {
      return new .colon.colon(this.child(), scala.collection.immutable.Nil..MODULE$);
   }

   public LazyExpression copy(final ColumnNode child, final Origin origin) {
      return new LazyExpression(child, origin);
   }

   public ColumnNode copy$default$1() {
      return this.child();
   }

   public Origin copy$default$2() {
      return this.origin();
   }

   public String productPrefix() {
      return "LazyExpression";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.child();
         }
         case 1 -> {
            return this.origin();
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
      return x$1 instanceof LazyExpression;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "child";
         }
         case 1 -> {
            return "origin";
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
            if (x$1 instanceof LazyExpression) {
               label48: {
                  LazyExpression var4 = (LazyExpression)x$1;
                  ColumnNode var10000 = this.child();
                  ColumnNode var5 = var4.child();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Origin var7 = this.origin();
                  Origin var6 = var4.origin();
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

   public LazyExpression(final ColumnNode child, final Origin origin) {
      this.child = child;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
