package org.apache.spark.sql.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg!B\u0012%\u0001\u001ar\u0003\u0002C%\u0001\u0005+\u0007I\u0011\u0001&\t\u0011E\u0003!\u0011#Q\u0001\n-C\u0001B\u0015\u0001\u0003\u0016\u0004%\ta\u0015\u0005\t/\u0002\u0011\t\u0012)A\u0005)\"A\u0001\f\u0001BK\u0002\u0013\u0005\u0013\f\u0003\u0005c\u0001\tE\t\u0015!\u0003[\u0011\u0015\u0019\u0007\u0001\"\u0001e\u0011\u0019I\u0007\u0001\"\u0011%U\")q\u0005\u0001C!W\"1A\u000f\u0001C!IUDqA\u001f\u0001\u0002\u0002\u0013\u00051\u0010\u0003\u0005\u0000\u0001E\u0005I\u0011AA\u0001\u0011%\t9\u0002AI\u0001\n\u0003\tI\u0002C\u0005\u0002\u001e\u0001\t\n\u0011\"\u0001\u0002 !I\u00111\u0005\u0001\u0002\u0002\u0013\u0005\u0013Q\u0005\u0005\n\u0003k\u0001\u0011\u0011!C\u0001\u0003oA\u0011\"a\u0010\u0001\u0003\u0003%\t!!\u0011\t\u0013\u00055\u0003!!A\u0005B\u0005=\u0003\"CA/\u0001\u0005\u0005I\u0011AA0\u0011%\tI\u0007AA\u0001\n\u0003\nY\u0007C\u0005\u0002p\u0001\t\t\u0011\"\u0011\u0002r!I\u00111\u000f\u0001\u0002\u0002\u0013\u0005\u0013Q\u000f\u0005\n\u0003o\u0002\u0011\u0011!C!\u0003s:!\"! %\u0003\u0003E\tAJA@\r%\u0019C%!A\t\u0002\u0019\n\t\t\u0003\u0004d3\u0011\u0005\u0011\u0011\u0014\u0005\n\u0003gJ\u0012\u0011!C#\u0003kB\u0011\"a'\u001a\u0003\u0003%\t)!(\t\u0013\u0005\u0015\u0016$%A\u0005\u0002\u0005e\u0001\"CAT3E\u0005I\u0011AA\u0010\u0011%\tI+GA\u0001\n\u0003\u000bY\u000bC\u0005\u0002:f\t\n\u0011\"\u0001\u0002\u001a!I\u00111X\r\u0012\u0002\u0013\u0005\u0011q\u0004\u0005\n\u0003{K\u0012\u0011!C\u0005\u0003\u007f\u0013\u0011cQ1tK^CWM\\(uQ\u0016\u0014x/[:f\u0015\t)c%\u0001\u0005j]R,'O\\1m\u0015\t9\u0003&A\u0002tc2T!!\u000b\u0016\u0002\u000bM\u0004\u0018M]6\u000b\u0005-b\u0013AB1qC\u000eDWMC\u0001.\u0003\ry'oZ\n\u0006\u0001=*\u0014\b\u0010\t\u0003aMj\u0011!\r\u0006\u0002e\u0005)1oY1mC&\u0011A'\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Y:T\"\u0001\u0013\n\u0005a\"#AC\"pYVlgNT8eKB\u0011\u0001GO\u0005\u0003wE\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002>\r:\u0011a\b\u0012\b\u0003\u007f\rk\u0011\u0001\u0011\u0006\u0003\u0003\n\u000ba\u0001\u0010:p_Rt4\u0001A\u0005\u0002e%\u0011Q)M\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0005J\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Fc\u0005A!M]1oG\",7/F\u0001L!\riDJT\u0005\u0003\u001b\"\u00131aU3r!\u0011\u0001t*N\u001b\n\u0005A\u000b$A\u0002+va2,''A\u0005ce\u0006t7\r[3tA\u0005Iq\u000e\u001e5fe^L7/Z\u000b\u0002)B\u0019\u0001'V\u001b\n\u0005Y\u000b$AB(qi&|g.\u0001\u0006pi\",'o^5tK\u0002\naa\u001c:jO&tW#\u0001.\u0011\u0005m\u0003W\"\u0001/\u000b\u0005us\u0016!\u0002;sK\u0016\u001c(BA0'\u0003!\u0019\u0017\r^1msN$\u0018BA1]\u0005\u0019y%/[4j]\u00069qN]5hS:\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003fM\u001eD\u0007C\u0001\u001c\u0001\u0011\u0015Iu\u00011\u0001L\u0011\u001d\u0011v\u0001%AA\u0002QCq\u0001W\u0004\u0011\u0002\u0003\u0007!,A\u0005o_Jl\u0017\r\\5{KR\tQ-F\u0001m!\ti\u0017O\u0004\u0002o_B\u0011q(M\u0005\u0003aF\na\u0001\u0015:fI\u00164\u0017B\u0001:t\u0005\u0019\u0019FO]5oO*\u0011\u0001/M\u0001\tG\"LG\u000e\u001a:f]V\ta\u000fE\u0002>\u0019^\u0004\"A\u000e=\n\u0005e$#AD\"pYVlgNT8eK2K7.Z\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003fyvt\bbB%\f!\u0003\u0005\ra\u0013\u0005\b%.\u0001\n\u00111\u0001U\u0011\u001dA6\u0002%AA\u0002i\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u0004)\u001a1*!\u0002,\u0005\u0005\u001d\u0001\u0003BA\u0005\u0003'i!!a\u0003\u000b\t\u00055\u0011qB\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u00052\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003+\tYAA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002\u001c)\u001aA+!\u0002\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011\u0011\u0005\u0016\u00045\u0006\u0015\u0011!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002(A!\u0011\u0011FA\u001a\u001b\t\tYC\u0003\u0003\u0002.\u0005=\u0012\u0001\u00027b]\u001eT!!!\r\u0002\t)\fg/Y\u0005\u0004e\u0006-\u0012\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u001d!\r\u0001\u00141H\u0005\u0004\u0003{\t$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\"\u0003\u0013\u00022\u0001MA#\u0013\r\t9%\r\u0002\u0004\u0003:L\b\"CA&#\u0005\u0005\t\u0019AA\u001d\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u000b\t\u0007\u0003'\nI&a\u0011\u000e\u0005\u0005U#bAA,c\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005m\u0013Q\u000b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002b\u0005\u001d\u0004c\u0001\u0019\u0002d%\u0019\u0011QM\u0019\u0003\u000f\t{w\u000e\\3b]\"I\u00111J\n\u0002\u0002\u0003\u0007\u00111I\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002(\u00055\u0004\"CA&)\u0005\u0005\t\u0019AA\u001d\u0003!A\u0017m\u001d5D_\u0012,GCAA\u001d\u0003!!xn\u0015;sS:<GCAA\u0014\u0003\u0019)\u0017/^1mgR!\u0011\u0011MA>\u0011%\tYeFA\u0001\u0002\u0004\t\u0019%A\tDCN,w\u000b[3o\u001fRDWM]<jg\u0016\u0004\"AN\r\u0014\u000be\t\u0019)a$\u0011\u0011\u0005\u0015\u00151R&U5\u0016l!!a\"\u000b\u0007\u0005%\u0015'A\u0004sk:$\u0018.\\3\n\t\u00055\u0015q\u0011\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BAI\u0003/k!!a%\u000b\t\u0005U\u0015qF\u0001\u0003S>L1aRAJ)\t\ty(A\u0003baBd\u0017\u0010F\u0004f\u0003?\u000b\t+a)\t\u000b%c\u0002\u0019A&\t\u000fIc\u0002\u0013!a\u0001)\"9\u0001\f\bI\u0001\u0002\u0004Q\u0016aD1qa2LH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIM\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002.\u0006U\u0006\u0003\u0002\u0019V\u0003_\u0003b\u0001MAY\u0017RS\u0016bAAZc\t1A+\u001e9mKNB\u0001\"a. \u0003\u0003\u0005\r!Z\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0003\u0004B!!\u000b\u0002D&!\u0011QYA\u0016\u0005\u0019y%M[3di\u0002"
)
public class CaseWhenOtherwise implements ColumnNode, Product, Serializable {
   private final Seq branches;
   private final Option otherwise;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$3() {
      return CaseWhenOtherwise$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return CaseWhenOtherwise$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final CaseWhenOtherwise x$0) {
      return CaseWhenOtherwise$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$3() {
      return CaseWhenOtherwise$.MODULE$.apply$default$3();
   }

   public static Option apply$default$2() {
      return CaseWhenOtherwise$.MODULE$.apply$default$2();
   }

   public static CaseWhenOtherwise apply(final Seq branches, final Option otherwise, final Origin origin) {
      return CaseWhenOtherwise$.MODULE$.apply(branches, otherwise, origin);
   }

   public static Function1 tupled() {
      return CaseWhenOtherwise$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return CaseWhenOtherwise$.MODULE$.curried();
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

   public Seq branches() {
      return this.branches;
   }

   public Option otherwise() {
      return this.otherwise;
   }

   public Origin origin() {
      return this.origin;
   }

   public CaseWhenOtherwise normalize() {
      return this.copy((Seq)this.branches().map((kv) -> new Tuple2(((ColumnNode)kv._1()).normalize(), ((ColumnNode)kv._2()).normalize())), ColumnNode$.MODULE$.normalize(this.otherwise()), ColumnNode$.MODULE$.NO_ORIGIN());
   }

   public String sql() {
      String var10000 = ((IterableOnceOps)this.branches().map((cv) -> {
         String var10000 = ((ColumnNode)cv._1()).sql();
         return " WHEN " + var10000 + " THEN " + ((ColumnNode)cv._2()).sql();
      })).mkString();
      return "CASE" + var10000 + this.otherwise().map((o) -> " ELSE " + o.sql()).getOrElse(() -> "") + " END";
   }

   public Seq children() {
      Seq branchChildren = (Seq)this.branches().flatMap((x0$1) -> {
         if (x0$1 != null) {
            ColumnNode condition = (ColumnNode)x0$1._1();
            ColumnNode value = (ColumnNode)x0$1._2();
            return new .colon.colon(condition, new .colon.colon(value, scala.collection.immutable.Nil..MODULE$));
         } else {
            throw new MatchError(x0$1);
         }
      });
      return (Seq)branchChildren.$plus$plus(this.otherwise());
   }

   public CaseWhenOtherwise copy(final Seq branches, final Option otherwise, final Origin origin) {
      return new CaseWhenOtherwise(branches, otherwise, origin);
   }

   public Seq copy$default$1() {
      return this.branches();
   }

   public Option copy$default$2() {
      return this.otherwise();
   }

   public Origin copy$default$3() {
      return this.origin();
   }

   public String productPrefix() {
      return "CaseWhenOtherwise";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.branches();
         }
         case 1 -> {
            return this.otherwise();
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
      return x$1 instanceof CaseWhenOtherwise;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "branches";
         }
         case 1 -> {
            return "otherwise";
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
            if (x$1 instanceof CaseWhenOtherwise) {
               label56: {
                  CaseWhenOtherwise var4 = (CaseWhenOtherwise)x$1;
                  Seq var10000 = this.branches();
                  Seq var5 = var4.branches();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  Option var8 = this.otherwise();
                  Option var6 = var4.otherwise();
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

   public CaseWhenOtherwise(final Seq branches, final Option otherwise, final Origin origin) {
      this.branches = branches;
      this.otherwise = otherwise;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.assert(branches.nonEmpty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
