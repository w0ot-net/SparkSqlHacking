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
   bytes = "\u0006\u0005\u0005mf!B\u0011#\u0001\u0012b\u0003\u0002C$\u0001\u0005+\u0007I\u0011\u0001%\t\u0011%\u0003!\u0011#Q\u0001\nMB\u0001B\u0013\u0001\u0003\u0016\u0004%\ta\u0013\u0005\t\u001f\u0002\u0011\t\u0012)A\u0005\u0019\"A\u0001\u000b\u0001BK\u0002\u0013\u0005\u0013\u000b\u0003\u0005[\u0001\tE\t\u0015!\u0003S\u0011\u0015Y\u0006\u0001\"\u0001]\u0011\u0019\t\u0007\u0001\"\u0011#E\")Q\u0005\u0001C!G\"1A\u000e\u0001C!E5Dq\u0001\u001e\u0001\u0002\u0002\u0013\u0005Q\u000fC\u0004z\u0001E\u0005I\u0011\u0001>\t\u0013\u0005-\u0001!%A\u0005\u0002\u00055\u0001\"CA\t\u0001E\u0005I\u0011AA\n\u0011%\t9\u0002AA\u0001\n\u0003\nI\u0002C\u0005\u0002*\u0001\t\t\u0011\"\u0001\u0002,!I\u00111\u0007\u0001\u0002\u0002\u0013\u0005\u0011Q\u0007\u0005\n\u0003\u0003\u0002\u0011\u0011!C!\u0003\u0007B\u0011\"!\u0015\u0001\u0003\u0003%\t!a\u0015\t\u0013\u0005u\u0003!!A\u0005B\u0005}\u0003\"CA2\u0001\u0005\u0005I\u0011IA3\u0011%\t9\u0007AA\u0001\n\u0003\nI\u0007C\u0005\u0002l\u0001\t\t\u0011\"\u0011\u0002n\u001dQ\u0011\u0011\u000f\u0012\u0002\u0002#\u0005A%a\u001d\u0007\u0013\u0005\u0012\u0013\u0011!E\u0001I\u0005U\u0004BB.\u001a\t\u0003\ti\tC\u0005\u0002he\t\t\u0011\"\u0012\u0002j!I\u0011qR\r\u0002\u0002\u0013\u0005\u0015\u0011\u0013\u0005\n\u00033K\u0012\u0013!C\u0001\u0003'A\u0011\"a'\u001a\u0003\u0003%\t)!(\t\u0013\u0005=\u0016$%A\u0005\u0002\u0005M\u0001\"CAY3\u0005\u0005I\u0011BAZ\u0005\u00199\u0016N\u001c3po*\u00111\u0005J\u0001\tS:$XM\u001d8bY*\u0011QEJ\u0001\u0004gFd'BA\u0014)\u0003\u0015\u0019\b/\u0019:l\u0015\tI#&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002W\u0005\u0019qN]4\u0014\u000b\u0001i3g\u000e\u001e\u0011\u00059\nT\"A\u0018\u000b\u0003A\nQa]2bY\u0006L!AM\u0018\u0003\r\u0005s\u0017PU3g!\t!T'D\u0001#\u0013\t1$E\u0001\u0006D_2,XN\u001c(pI\u0016\u0004\"A\f\u001d\n\u0005ez#a\u0002)s_\u0012,8\r\u001e\t\u0003w\u0011s!\u0001\u0010\"\u000f\u0005u\nU\"\u0001 \u000b\u0005}\u0002\u0015A\u0002\u001fs_>$hh\u0001\u0001\n\u0003AJ!aQ\u0018\u0002\u000fA\f7m[1hK&\u0011QI\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0007>\nab^5oI><h)\u001e8di&|g.F\u00014\u0003=9\u0018N\u001c3po\u001a+hn\u0019;j_:\u0004\u0013AC<j]\u0012|wo\u00159fGV\tA\n\u0005\u00025\u001b&\u0011aJ\t\u0002\u000b/&tGm\\<Ta\u0016\u001c\u0017aC<j]\u0012|wo\u00159fG\u0002\naa\u001c:jO&tW#\u0001*\u0011\u0005MCV\"\u0001+\u000b\u0005U3\u0016!\u0002;sK\u0016\u001c(BA,%\u0003!\u0019\u0017\r^1msN$\u0018BA-U\u0005\u0019y%/[4j]\u00069qN]5hS:\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003^=~\u0003\u0007C\u0001\u001b\u0001\u0011\u00159u\u00011\u00014\u0011\u0015Qu\u00011\u0001M\u0011\u001d\u0001v\u0001%AA\u0002I\u000b\u0011B\\8s[\u0006d\u0017N_3\u0015\u0003u+\u0012\u0001\u001a\t\u0003K&t!AZ4\u0011\u0005uz\u0013B\u000150\u0003\u0019\u0001&/\u001a3fM&\u0011!n\u001b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!|\u0013\u0001C2iS2$'/\u001a8\u0016\u00039\u00042aO8r\u0013\t\u0001hIA\u0002TKF\u0004\"\u0001\u000e:\n\u0005M\u0014#AD\"pYVlgNT8eK2K7.Z\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003^m^D\bbB$\f!\u0003\u0005\ra\r\u0005\b\u0015.\u0001\n\u00111\u0001M\u0011\u001d\u00016\u0002%AA\u0002I\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001|U\t\u0019DpK\u0001~!\rq\u0018qA\u0007\u0002\u007f*!\u0011\u0011AA\u0002\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0006=\n!\"\u00198o_R\fG/[8o\u0013\r\tIa \u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003\u001fQ#\u0001\u0014?\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011Q\u0003\u0016\u0003%r\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u000e!\u0011\ti\"a\n\u000e\u0005\u0005}!\u0002BA\u0011\u0003G\tA\u0001\\1oO*\u0011\u0011QE\u0001\u0005U\u00064\u0018-C\u0002k\u0003?\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\f\u0011\u00079\ny#C\u0002\u00022=\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u000e\u0002>A\u0019a&!\u000f\n\u0007\u0005mrFA\u0002B]fD\u0011\"a\u0010\u0012\u0003\u0003\u0005\r!!\f\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t)\u0005\u0005\u0004\u0002H\u00055\u0013qG\u0007\u0003\u0003\u0013R1!a\u00130\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u001f\nIE\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA+\u00037\u00022ALA,\u0013\r\tIf\f\u0002\b\u0005>|G.Z1o\u0011%\tydEA\u0001\u0002\u0004\t9$\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u000e\u0003CB\u0011\"a\u0010\u0015\u0003\u0003\u0005\r!!\f\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\f\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0007\u0002\r\u0015\fX/\u00197t)\u0011\t)&a\u001c\t\u0013\u0005}r#!AA\u0002\u0005]\u0012AB,j]\u0012|w\u000f\u0005\u000253M)\u0011$a\u001e\u0002\u0004BA\u0011\u0011PA@g1\u0013V,\u0004\u0002\u0002|)\u0019\u0011QP\u0018\u0002\u000fI,h\u000e^5nK&!\u0011\u0011QA>\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003\u000b\u000bY)\u0004\u0002\u0002\b*!\u0011\u0011RA\u0012\u0003\tIw.C\u0002F\u0003\u000f#\"!a\u001d\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fu\u000b\u0019*!&\u0002\u0018\")q\t\ba\u0001g!)!\n\ba\u0001\u0019\"9\u0001\u000b\bI\u0001\u0002\u0004\u0011\u0016aD1qa2LH\u0005Z3gCVdG\u000fJ\u001a\u0002\u000fUt\u0017\r\u001d9msR!\u0011qTAV!\u0015q\u0013\u0011UAS\u0013\r\t\u0019k\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r9\n9k\r'S\u0013\r\tIk\f\u0002\u0007)V\u0004H.Z\u001a\t\u0011\u00055f$!AA\u0002u\u000b1\u0001\u001f\u00131\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%g\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0017\t\u0005\u0003;\t9,\u0003\u0003\u0002:\u0006}!AB(cU\u0016\u001cG\u000f"
)
public class Window implements ColumnNode, Product, Serializable {
   private final ColumnNode windowFunction;
   private final WindowSpec windowSpec;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$3() {
      return Window$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final Window x$0) {
      return Window$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$3() {
      return Window$.MODULE$.apply$default$3();
   }

   public static Window apply(final ColumnNode windowFunction, final WindowSpec windowSpec, final Origin origin) {
      return Window$.MODULE$.apply(windowFunction, windowSpec, origin);
   }

   public static Function1 tupled() {
      return Window$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Window$.MODULE$.curried();
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

   public ColumnNode windowFunction() {
      return this.windowFunction;
   }

   public WindowSpec windowSpec() {
      return this.windowSpec;
   }

   public Origin origin() {
      return this.origin;
   }

   public Window normalize() {
      return this.copy(this.windowFunction().normalize(), this.windowSpec().normalize(), ColumnNode$.MODULE$.NO_ORIGIN());
   }

   public String sql() {
      String var10000 = this.windowFunction().sql();
      return var10000 + " OVER (" + this.windowSpec().sql() + ")";
   }

   public Seq children() {
      return new .colon.colon(this.windowFunction(), new .colon.colon(this.windowSpec(), scala.collection.immutable.Nil..MODULE$));
   }

   public Window copy(final ColumnNode windowFunction, final WindowSpec windowSpec, final Origin origin) {
      return new Window(windowFunction, windowSpec, origin);
   }

   public ColumnNode copy$default$1() {
      return this.windowFunction();
   }

   public WindowSpec copy$default$2() {
      return this.windowSpec();
   }

   public Origin copy$default$3() {
      return this.origin();
   }

   public String productPrefix() {
      return "Window";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.windowFunction();
         }
         case 1 -> {
            return this.windowSpec();
         }
         case 2 -> {
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
      return x$1 instanceof Window;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "windowFunction";
         }
         case 1 -> {
            return "windowSpec";
         }
         case 2 -> {
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
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof Window) {
               label56: {
                  Window var4 = (Window)x$1;
                  ColumnNode var10000 = this.windowFunction();
                  ColumnNode var5 = var4.windowFunction();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  WindowSpec var8 = this.windowSpec();
                  WindowSpec var6 = var4.windowSpec();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  Origin var9 = this.origin();
                  Origin var7 = var4.origin();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public Window(final ColumnNode windowFunction, final WindowSpec windowSpec, final Origin origin) {
      this.windowFunction = windowFunction;
      this.windowSpec = windowSpec;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
