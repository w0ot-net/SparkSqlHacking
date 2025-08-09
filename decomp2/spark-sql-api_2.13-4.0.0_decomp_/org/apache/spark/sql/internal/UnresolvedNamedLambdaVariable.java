package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uf!B\u0011#\u0001\u0012b\u0003\u0002C$\u0001\u0005+\u0007I\u0011\u0001%\t\u0011E\u0003!\u0011#Q\u0001\n%C\u0001B\u0015\u0001\u0003\u0016\u0004%\te\u0015\u0005\t9\u0002\u0011\t\u0012)A\u0005)\")Q\f\u0001C\u0001=\"1!\r\u0001C!E\rDQ!\n\u0001\u0005B!Ca\u0001\u001a\u0001\u0005B\t*\u0007b\u00027\u0001\u0003\u0003%\t!\u001c\u0005\ba\u0002\t\n\u0011\"\u0001r\u0011\u001da\b!%A\u0005\u0002uD\u0001b \u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0001\u0005\n\u0003#\u0001\u0011\u0011!C\u0001\u0003'A\u0011\"a\u0007\u0001\u0003\u0003%\t!!\b\t\u0013\u0005%\u0002!!A\u0005B\u0005-\u0002\"CA\u001d\u0001\u0005\u0005I\u0011AA\u001e\u0011%\t)\u0005AA\u0001\n\u0003\n9\u0005C\u0005\u0002L\u0001\t\t\u0011\"\u0011\u0002N!I\u0011q\n\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u000b\u0005\n\u0003'\u0002\u0011\u0011!C!\u0003+:q!!\u0017#\u0011\u0003\tYF\u0002\u0004\"E!\u0005\u0011Q\f\u0005\u0007;Z!\t!!\u001b\t\u0013\u0005-dC1A\u0005\n\u00055\u0004\u0002CAB-\u0001\u0006I!a\u001c\t\u000f\u0005\u0015e\u0003\"\u0001\u0002\b\"A\u00111\u0012\f\u0005\u0002\u0011\ni\tC\u0005\u0002\u0006Z\t\t\u0011\"!\u0002\u0016\"A\u00111\u0014\f\u0012\u0002\u0013\u0005Q\u0010C\u0005\u0002\u001eZ\t\t\u0011\"!\u0002 \"A\u0011\u0011\u0017\f\u0012\u0002\u0013\u0005Q\u0010C\u0005\u00024Z\t\t\u0011\"\u0003\u00026\niRK\u001c:fg>dg/\u001a3OC6,G\rT1nE\u0012\fg+\u0019:jC\ndWM\u0003\u0002$I\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002&M\u0005\u00191/\u001d7\u000b\u0005\u001dB\u0013!B:qCJ\\'BA\u0015+\u0003\u0019\t\u0007/Y2iK*\t1&A\u0002pe\u001e\u001cR\u0001A\u00174oi\u0002\"AL\u0019\u000e\u0003=R\u0011\u0001M\u0001\u0006g\u000e\fG.Y\u0005\u0003e=\u0012a!\u00118z%\u00164\u0007C\u0001\u001b6\u001b\u0005\u0011\u0013B\u0001\u001c#\u0005)\u0019u\u000e\\;n]:{G-\u001a\t\u0003]aJ!!O\u0018\u0003\u000fA\u0013x\u000eZ;diB\u00111\b\u0012\b\u0003y\ts!!P!\u000e\u0003yR!a\u0010!\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001M\u0005\u0003\u0007>\nq\u0001]1dW\u0006<W-\u0003\u0002F\r\na1+\u001a:jC2L'0\u00192mK*\u00111iL\u0001\u0005]\u0006lW-F\u0001J!\tQeJ\u0004\u0002L\u0019B\u0011QhL\u0005\u0003\u001b>\na\u0001\u0015:fI\u00164\u0017BA(Q\u0005\u0019\u0019FO]5oO*\u0011QjL\u0001\u0006]\u0006lW\rI\u0001\u0007_JLw-\u001b8\u0016\u0003Q\u0003\"!\u0016.\u000e\u0003YS!a\u0016-\u0002\u000bQ\u0014X-Z:\u000b\u0005e#\u0013\u0001C2bi\u0006d\u0017p\u001d;\n\u0005m3&AB(sS\u001eLg.A\u0004pe&<\u0017N\u001c\u0011\u0002\rqJg.\u001b;?)\ry\u0006-\u0019\t\u0003i\u0001AQaR\u0003A\u0002%CqAU\u0003\u0011\u0002\u0003\u0007A+A\u0005o_Jl\u0017\r\\5{KR\tq,\u0001\u0005dQ&dGM]3o+\u00051\u0007cA\u001ehS&\u0011\u0001N\u0012\u0002\u0004'\u0016\f\bC\u0001\u001bk\u0013\tY'E\u0001\bD_2,XN\u001c(pI\u0016d\u0015n[3\u0002\t\r|\u0007/\u001f\u000b\u0004?:|\u0007bB$\n!\u0003\u0005\r!\u0013\u0005\b%&\u0001\n\u00111\u0001U\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u001d\u0016\u0003\u0013N\\\u0013\u0001\u001e\t\u0003kjl\u0011A\u001e\u0006\u0003ob\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005e|\u0013AC1o]>$\u0018\r^5p]&\u00111P\u001e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002}*\u0012Ak]\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\r\u0001\u0003BA\u0003\u0003\u001fi!!a\u0002\u000b\t\u0005%\u00111B\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u000e\u0005!!.\u0019<b\u0013\ry\u0015qA\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003+\u00012ALA\f\u0013\r\tIb\f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003?\t)\u0003E\u0002/\u0003CI1!a\t0\u0005\r\te.\u001f\u0005\n\u0003Oq\u0011\u0011!a\u0001\u0003+\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0017!\u0019\ty#!\u000e\u0002 5\u0011\u0011\u0011\u0007\u0006\u0004\u0003gy\u0013AC2pY2,7\r^5p]&!\u0011qGA\u0019\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005u\u00121\t\t\u0004]\u0005}\u0012bAA!_\t9!i\\8mK\u0006t\u0007\"CA\u0014!\u0005\u0005\t\u0019AA\u0010\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005\r\u0011\u0011\n\u0005\n\u0003O\t\u0012\u0011!a\u0001\u0003+\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003+\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u0007\ta!Z9vC2\u001cH\u0003BA\u001f\u0003/B\u0011\"a\n\u0015\u0003\u0003\u0005\r!a\b\u0002;Us'/Z:pYZ,GMT1nK\u0012d\u0015-\u001c2eCZ\u000b'/[1cY\u0016\u0004\"\u0001\u000e\f\u0014\tYi\u0013q\f\t\u0005\u0003C\n9'\u0004\u0002\u0002d)!\u0011QMA\u0006\u0003\tIw.C\u0002F\u0003G\"\"!a\u0017\u0002\r9,\u0007\u0010^%e+\t\ty\u0007\u0005\u0003\u0002r\u0005}TBAA:\u0015\u0011\t)(a\u001e\u0002\r\u0005$x.\\5d\u0015\u0011\tI(a\u001f\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0003\u0002~\u0005-\u0011\u0001B;uS2LA!!!\u0002t\tQ\u0011\t^8nS\u000eduN\\4\u0002\u000f9,\u0007\u0010^%eA\u0005)\u0011\r\u001d9msR\u0019q,!#\t\u000b\u001dS\u0002\u0019A%\u0002!I,7/\u001a;JI\u001e+g.\u001a:bi>\u0014HCAAH!\rq\u0013\u0011S\u0005\u0004\u0003'{#\u0001B+oSR$RaXAL\u00033CQa\u0012\u000fA\u0002%CqA\u0015\u000f\u0011\u0002\u0003\u0007A+A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00133\u0003\u001d)h.\u00199qYf$B!!)\u0002.B)a&a)\u0002(&\u0019\u0011QU\u0018\u0003\r=\u0003H/[8o!\u0015q\u0013\u0011V%U\u0013\r\tYk\f\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005=f$!AA\u0002}\u000b1\u0001\u001f\u00131\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u0017\t\u0005\u0003\u000b\tI,\u0003\u0003\u0002<\u0006\u001d!AB(cU\u0016\u001cG\u000f"
)
public class UnresolvedNamedLambdaVariable implements ColumnNode, Product, Serializable {
   private final String name;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$2() {
      return UnresolvedNamedLambdaVariable$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final UnresolvedNamedLambdaVariable x$0) {
      return UnresolvedNamedLambdaVariable$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$2() {
      return UnresolvedNamedLambdaVariable$.MODULE$.apply$default$2();
   }

   public static UnresolvedNamedLambdaVariable apply(final String name, final Origin origin) {
      return UnresolvedNamedLambdaVariable$.MODULE$.apply(name, origin);
   }

   public static UnresolvedNamedLambdaVariable apply(final String name) {
      return UnresolvedNamedLambdaVariable$.MODULE$.apply(name);
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

   public String name() {
      return this.name;
   }

   public Origin origin() {
      return this.origin;
   }

   public UnresolvedNamedLambdaVariable normalize() {
      Origin x$1 = ColumnNode$.MODULE$.NO_ORIGIN();
      String x$2 = this.copy$default$1();
      return this.copy(x$2, x$1);
   }

   public String sql() {
      return this.name();
   }

   public Seq children() {
      return (Seq).MODULE$.Seq().empty();
   }

   public UnresolvedNamedLambdaVariable copy(final String name, final Origin origin) {
      return new UnresolvedNamedLambdaVariable(name, origin);
   }

   public String copy$default$1() {
      return this.name();
   }

   public Origin copy$default$2() {
      return this.origin();
   }

   public String productPrefix() {
      return "UnresolvedNamedLambdaVariable";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.name();
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
      return x$1 instanceof UnresolvedNamedLambdaVariable;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "name";
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
            if (x$1 instanceof UnresolvedNamedLambdaVariable) {
               label48: {
                  UnresolvedNamedLambdaVariable var4 = (UnresolvedNamedLambdaVariable)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
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

   public UnresolvedNamedLambdaVariable(final String name, final Origin origin) {
      this.name = name;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
