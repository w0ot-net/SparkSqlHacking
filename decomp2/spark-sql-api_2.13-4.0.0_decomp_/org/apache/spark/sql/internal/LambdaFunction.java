package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=f!B\u0010!\u0001\nR\u0003\u0002C#\u0001\u0005+\u0007I\u0011\u0001$\t\u0011\u001d\u0003!\u0011#Q\u0001\nEB\u0001\u0002\u0013\u0001\u0003\u0016\u0004%\t!\u0013\u0005\t!\u0002\u0011\t\u0012)A\u0005\u0015\"A\u0011\u000b\u0001BK\u0002\u0013\u0005#\u000b\u0003\u0005\\\u0001\tE\t\u0015!\u0003T\u0011\u0015a\u0006\u0001\"\u0001^\u0011\u0019\u0011\u0007\u0001\"\u0011!G\")1\u0005\u0001C!I\"1Q\u000e\u0001C!A9Dqa\u001d\u0001\u0002\u0002\u0013\u0005A\u000fC\u0004y\u0001E\u0005I\u0011A=\t\u0013\u0005%\u0001!%A\u0005\u0002\u0005-\u0001\"CA\b\u0001E\u0005I\u0011AA\t\u0011%\t)\u0002AA\u0001\n\u0003\n9\u0002C\u0005\u0002(\u0001\t\t\u0011\"\u0001\u0002*!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003\u007f\u0001\u0011\u0011!C!\u0003\u0003B\u0011\"a\u0014\u0001\u0003\u0003%\t!!\u0015\t\u0013\u0005m\u0003!!A\u0005B\u0005u\u0003\"CA1\u0001\u0005\u0005I\u0011IA2\u0011%\t)\u0007AA\u0001\n\u0003\n9\u0007C\u0005\u0002j\u0001\t\t\u0011\"\u0011\u0002l\u001d9\u0011q\u000e\u0011\t\u0002\u0005EdAB\u0010!\u0011\u0003\t\u0019\b\u0003\u0004]3\u0011\u0005\u0011q\u0010\u0005\b\u0003\u0003KB\u0011AAB\u0011%\t\t)GA\u0001\n\u0003\u000bI\tC\u0005\u0002\u0012f\t\t\u0011\"!\u0002\u0014\"I\u0011QU\r\u0002\u0002\u0013%\u0011q\u0015\u0002\u000f\u0019\u0006l'\rZ1Gk:\u001cG/[8o\u0015\t\t#%\u0001\u0005j]R,'O\\1m\u0015\t\u0019C%A\u0002tc2T!!\n\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dB\u0013AB1qC\u000eDWMC\u0001*\u0003\ry'oZ\n\u0006\u0001-\nT\u0007\u000f\t\u0003Y=j\u0011!\f\u0006\u0002]\u0005)1oY1mC&\u0011\u0001'\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005I\u001aT\"\u0001\u0011\n\u0005Q\u0002#AC\"pYVlgNT8eKB\u0011AFN\u0005\u0003o5\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002:\u0005:\u0011!\b\u0011\b\u0003w}j\u0011\u0001\u0010\u0006\u0003{y\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002]%\u0011\u0011)L\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019EI\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002B[\u0005Aa-\u001e8di&|g.F\u00012\u0003%1WO\\2uS>t\u0007%A\u0005be\u001e,X.\u001a8ugV\t!\nE\u0002:\u00176K!\u0001\u0014#\u0003\u0007M+\u0017\u000f\u0005\u00023\u001d&\u0011q\n\t\u0002\u001e+:\u0014Xm]8mm\u0016$g*Y7fI2\u000bWN\u00193b-\u0006\u0014\u0018.\u00192mK\u0006Q\u0011M]4v[\u0016tGo\u001d\u0011\u0002\r=\u0014\u0018nZ5o+\u0005\u0019\u0006C\u0001+Z\u001b\u0005)&B\u0001,X\u0003\u0015!(/Z3t\u0015\tA&%\u0001\u0005dCR\fG._:u\u0013\tQVK\u0001\u0004Pe&<\u0017N\\\u0001\b_JLw-\u001b8!\u0003\u0019a\u0014N\\5u}Q!al\u00181b!\t\u0011\u0004\u0001C\u0003F\u000f\u0001\u0007\u0011\u0007C\u0003I\u000f\u0001\u0007!\nC\u0003R\u000f\u0001\u00071+A\u0005o_Jl\u0017\r\\5{KR\ta,F\u0001f!\t1'N\u0004\u0002hQB\u00111(L\u0005\u0003S6\na\u0001\u0015:fI\u00164\u0017BA6m\u0005\u0019\u0019FO]5oO*\u0011\u0011.L\u0001\tG\"LG\u000e\u001a:f]V\tq\u000eE\u0002:\u0017B\u0004\"AM9\n\u0005I\u0004#AD\"pYVlgNT8eK2K7.Z\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003_kZ<\bbB#\f!\u0003\u0005\r!\r\u0005\b\u0011.\u0001\n\u00111\u0001K\u0011\u001d\t6\u0002%AA\u0002M\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001{U\t\t4pK\u0001}!\ri\u0018QA\u0007\u0002}*\u0019q0!\u0001\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0002[\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0007\u0005\u001daPA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002\u000e)\u0012!j_\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\t\u0019B\u000b\u0002Tw\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u0007\u0011\t\u0005m\u0011QE\u0007\u0003\u0003;QA!a\b\u0002\"\u0005!A.\u00198h\u0015\t\t\u0019#\u0001\u0003kCZ\f\u0017bA6\u0002\u001e\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u00111\u0006\t\u0004Y\u00055\u0012bAA\u0018[\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011QGA\u001e!\ra\u0013qG\u0005\u0004\u0003si#aA!os\"I\u0011QH\t\u0002\u0002\u0003\u0007\u00111F\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\r\u0003CBA#\u0003\u0017\n)$\u0004\u0002\u0002H)\u0019\u0011\u0011J\u0017\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002N\u0005\u001d#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0015\u0002ZA\u0019A&!\u0016\n\u0007\u0005]SFA\u0004C_>dW-\u00198\t\u0013\u0005u2#!AA\u0002\u0005U\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0007\u0002`!I\u0011Q\b\u000b\u0002\u0002\u0003\u0007\u00111F\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u00111F\u0001\ti>\u001cFO]5oOR\u0011\u0011\u0011D\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005M\u0013Q\u000e\u0005\n\u0003{9\u0012\u0011!a\u0001\u0003k\ta\u0002T1nE\u0012\fg)\u001e8di&|g\u000e\u0005\u000233M!\u0011dKA;!\u0011\t9(! \u000e\u0005\u0005e$\u0002BA>\u0003C\t!![8\n\u0007\r\u000bI\b\u0006\u0002\u0002r\u0005)\u0011\r\u001d9msR)a,!\"\u0002\b\")Qi\u0007a\u0001c!)\u0001j\u0007a\u0001\u0015R9a,a#\u0002\u000e\u0006=\u0005\"B#\u001d\u0001\u0004\t\u0004\"\u0002%\u001d\u0001\u0004Q\u0005\"B)\u001d\u0001\u0004\u0019\u0016aB;oCB\u0004H.\u001f\u000b\u0005\u0003+\u000b\t\u000bE\u0003-\u0003/\u000bY*C\u0002\u0002\u001a6\u0012aa\u00149uS>t\u0007C\u0002\u0017\u0002\u001eFR5+C\u0002\u0002 6\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CAR;\u0005\u0005\t\u0019\u00010\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002*B!\u00111DAV\u0013\u0011\ti+!\b\u0003\r=\u0013'.Z2u\u0001"
)
public class LambdaFunction implements ColumnNode, Product, Serializable {
   private final ColumnNode function;
   private final Seq arguments;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Option unapply(final LambdaFunction x$0) {
      return LambdaFunction$.MODULE$.unapply(x$0);
   }

   public static LambdaFunction apply(final ColumnNode function, final Seq arguments, final Origin origin) {
      return LambdaFunction$.MODULE$.apply(function, arguments, origin);
   }

   public static LambdaFunction apply(final ColumnNode function, final Seq arguments) {
      return LambdaFunction$.MODULE$.apply(function, arguments);
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

   public ColumnNode function() {
      return this.function;
   }

   public Seq arguments() {
      return this.arguments;
   }

   public Origin origin() {
      return this.origin;
   }

   public LambdaFunction normalize() {
      return this.copy(this.function().normalize(), ColumnNode$.MODULE$.normalize(this.arguments()), ColumnNode$.MODULE$.NO_ORIGIN());
   }

   public String sql() {
      String var10000;
      label18: {
         Seq var3 = this.arguments();
         if (var3 != null) {
            SeqOps var4 = .MODULE$.Seq().unapplySeq(var3);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var4) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 1) == 0) {
               UnresolvedNamedLambdaVariable arg = (UnresolvedNamedLambdaVariable)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 0);
               var10000 = arg.sql();
               break label18;
            }
         }

         var10000 = ColumnNode$.MODULE$.argumentsToSql(this.arguments());
      }

      String argumentsSql = var10000;
      return argumentsSql + " -> " + this.function().sql();
   }

   public Seq children() {
      ColumnNode var1 = this.function();
      return (Seq)this.arguments().$plus$colon(var1);
   }

   public LambdaFunction copy(final ColumnNode function, final Seq arguments, final Origin origin) {
      return new LambdaFunction(function, arguments, origin);
   }

   public ColumnNode copy$default$1() {
      return this.function();
   }

   public Seq copy$default$2() {
      return this.arguments();
   }

   public Origin copy$default$3() {
      return this.origin();
   }

   public String productPrefix() {
      return "LambdaFunction";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.function();
         }
         case 1 -> {
            return this.arguments();
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
      return x$1 instanceof LambdaFunction;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "function";
         }
         case 1 -> {
            return "arguments";
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
            if (x$1 instanceof LambdaFunction) {
               label56: {
                  LambdaFunction var4 = (LambdaFunction)x$1;
                  ColumnNode var10000 = this.function();
                  ColumnNode var5 = var4.function();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  Seq var8 = this.arguments();
                  Seq var6 = var4.arguments();
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

   public LambdaFunction(final ColumnNode function, final Seq arguments, final Origin origin) {
      this.function = function;
      this.arguments = arguments;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
