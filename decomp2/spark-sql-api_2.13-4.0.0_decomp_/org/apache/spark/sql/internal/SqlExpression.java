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
   bytes = "\u0006\u0005\u0005me!\u0002\u0010 \u0001\u0006J\u0003\u0002\u0003#\u0001\u0005+\u0007I\u0011A#\t\u00119\u0003!\u0011#Q\u0001\n\u0019C\u0001b\u0014\u0001\u0003\u0016\u0004%\t\u0005\u0015\u0005\t3\u0002\u0011\t\u0012)A\u0005#\")!\f\u0001C\u00017\"1q\f\u0001C!?\u0001DQA\t\u0001\u0005B\u0015Ca!\u0019\u0001\u0005B}\u0011\u0007bB5\u0001\u0003\u0003%\tA\u001b\u0005\b[\u0002\t\n\u0011\"\u0001o\u0011\u001dI\b!%A\u0005\u0002iDq\u0001 \u0001\u0002\u0002\u0013\u0005S\u0010C\u0005\u0002\f\u0001\t\t\u0011\"\u0001\u0002\u000e!I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0011q\u0003\u0005\n\u0003G\u0001\u0011\u0011!C!\u0003KA\u0011\"a\r\u0001\u0003\u0003%\t!!\u000e\t\u0013\u0005}\u0002!!A\u0005B\u0005\u0005\u0003\"CA#\u0001\u0005\u0005I\u0011IA$\u0011%\tI\u0005AA\u0001\n\u0003\nY\u0005C\u0005\u0002N\u0001\t\t\u0011\"\u0011\u0002P\u001dQ\u00111K\u0010\u0002\u0002#\u0005\u0011%!\u0016\u0007\u0013yy\u0012\u0011!E\u0001C\u0005]\u0003B\u0002.\u0017\t\u0003\ty\u0007C\u0005\u0002JY\t\t\u0011\"\u0012\u0002L!I\u0011\u0011\u000f\f\u0002\u0002\u0013\u0005\u00151\u000f\u0005\t\u0003s2\u0012\u0013!C\u0001u\"I\u00111\u0010\f\u0002\u0002\u0013\u0005\u0015Q\u0010\u0005\t\u0003\u001f3\u0012\u0013!C\u0001u\"I\u0011\u0011\u0013\f\u0002\u0002\u0013%\u00111\u0013\u0002\u000e'FdW\t\u001f9sKN\u001c\u0018n\u001c8\u000b\u0005\u0001\n\u0013\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\t\u001a\u0013aA:rY*\u0011A%J\u0001\u0006gB\f'o\u001b\u0006\u0003M\u001d\na!\u00199bG\",'\"\u0001\u0015\u0002\u0007=\u0014xmE\u0003\u0001UA\"t\u0007\u0005\u0002,]5\tAFC\u0001.\u0003\u0015\u00198-\u00197b\u0013\tyCF\u0001\u0004B]f\u0014VM\u001a\t\u0003cIj\u0011aH\u0005\u0003g}\u0011!bQ8mk6tgj\u001c3f!\tYS'\u0003\u00027Y\t9\u0001K]8ek\u000e$\bC\u0001\u001dB\u001d\tItH\u0004\u0002;}5\t1H\u0003\u0002={\u00051AH]8piz\u001a\u0001!C\u0001.\u0013\t\u0001E&A\u0004qC\u000e\\\u0017mZ3\n\u0005\t\u001b%\u0001D*fe&\fG.\u001b>bE2,'B\u0001!-\u0003))\u0007\u0010\u001d:fgNLwN\\\u000b\u0002\rB\u0011qi\u0013\b\u0003\u0011&\u0003\"A\u000f\u0017\n\u0005)c\u0013A\u0002)sK\u0012,g-\u0003\u0002M\u001b\n11\u000b\u001e:j]\u001eT!A\u0013\u0017\u0002\u0017\u0015D\bO]3tg&|g\u000eI\u0001\u0007_JLw-\u001b8\u0016\u0003E\u0003\"AU,\u000e\u0003MS!\u0001V+\u0002\u000bQ\u0014X-Z:\u000b\u0005Y\u000b\u0013\u0001C2bi\u0006d\u0017p\u001d;\n\u0005a\u001b&AB(sS\u001eLg.A\u0004pe&<\u0017N\u001c\u0011\u0002\rqJg.\u001b;?)\raVL\u0018\t\u0003c\u0001AQ\u0001R\u0003A\u0002\u0019CqaT\u0003\u0011\u0002\u0003\u0007\u0011+A\u0005o_Jl\u0017\r\\5{KR\tA,\u0001\u0005dQ&dGM]3o+\u0005\u0019\u0007c\u0001\u001deM&\u0011Qm\u0011\u0002\u0004'\u0016\f\bCA\u0019h\u0013\tAwD\u0001\bD_2,XN\u001c(pI\u0016d\u0015n[3\u0002\t\r|\u0007/\u001f\u000b\u00049.d\u0007b\u0002#\n!\u0003\u0005\rA\u0012\u0005\b\u001f&\u0001\n\u00111\u0001R\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u001c\u0016\u0003\rB\\\u0013!\u001d\t\u0003e^l\u0011a\u001d\u0006\u0003iV\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005Yd\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001p\u001d\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002w*\u0012\u0011\u000b]\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003y\u00042a`A\u0005\u001b\t\t\tA\u0003\u0003\u0002\u0004\u0005\u0015\u0011\u0001\u00027b]\u001eT!!a\u0002\u0002\t)\fg/Y\u0005\u0004\u0019\u0006\u0005\u0011\u0001\u00049s_\u0012,8\r^!sSRLXCAA\b!\rY\u0013\u0011C\u0005\u0004\u0003'a#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\r\u0003?\u00012aKA\u000e\u0013\r\ti\u0002\f\u0002\u0004\u0003:L\b\"CA\u0011\u001d\u0005\u0005\t\u0019AA\b\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u0005\t\u0007\u0003S\ty#!\u0007\u000e\u0005\u0005-\"bAA\u0017Y\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005E\u00121\u0006\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00028\u0005u\u0002cA\u0016\u0002:%\u0019\u00111\b\u0017\u0003\u000f\t{w\u000e\\3b]\"I\u0011\u0011\u0005\t\u0002\u0002\u0003\u0007\u0011\u0011D\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002\u007f\u0003\u0007B\u0011\"!\t\u0012\u0003\u0003\u0005\r!a\u0004\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0004\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A`\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005]\u0012\u0011\u000b\u0005\n\u0003C!\u0012\u0011!a\u0001\u00033\tQbU9m\u000bb\u0004(/Z:tS>t\u0007CA\u0019\u0017'\u00151\u0012\u0011LA3!\u001d\tY&!\u0019G#rk!!!\u0018\u000b\u0007\u0005}C&A\u0004sk:$\u0018.\\3\n\t\u0005\r\u0014Q\f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA4\u0003[j!!!\u001b\u000b\t\u0005-\u0014QA\u0001\u0003S>L1AQA5)\t\t)&A\u0003baBd\u0017\u0010F\u0003]\u0003k\n9\bC\u0003E3\u0001\u0007a\tC\u0004P3A\u0005\t\u0019A)\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uII\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u0000\u0005-\u0005#B\u0016\u0002\u0002\u0006\u0015\u0015bAABY\t1q\n\u001d;j_:\u0004RaKAD\rFK1!!#-\u0005\u0019!V\u000f\u001d7fe!A\u0011QR\u000e\u0002\u0002\u0003\u0007A,A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAK!\ry\u0018qS\u0005\u0005\u00033\u000b\tA\u0001\u0004PE*,7\r\u001e"
)
public class SqlExpression implements ColumnNode, Product, Serializable {
   private final String expression;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$2() {
      return SqlExpression$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final SqlExpression x$0) {
      return SqlExpression$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$2() {
      return SqlExpression$.MODULE$.apply$default$2();
   }

   public static SqlExpression apply(final String expression, final Origin origin) {
      return SqlExpression$.MODULE$.apply(expression, origin);
   }

   public static Function1 tupled() {
      return SqlExpression$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SqlExpression$.MODULE$.curried();
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

   public String expression() {
      return this.expression;
   }

   public Origin origin() {
      return this.origin;
   }

   public SqlExpression normalize() {
      Origin x$1 = ColumnNode$.MODULE$.NO_ORIGIN();
      String x$2 = this.copy$default$1();
      return this.copy(x$2, x$1);
   }

   public String sql() {
      return this.expression();
   }

   public Seq children() {
      return (Seq).MODULE$.Seq().empty();
   }

   public SqlExpression copy(final String expression, final Origin origin) {
      return new SqlExpression(expression, origin);
   }

   public String copy$default$1() {
      return this.expression();
   }

   public Origin copy$default$2() {
      return this.origin();
   }

   public String productPrefix() {
      return "SqlExpression";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.expression();
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
      return x$1 instanceof SqlExpression;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "expression";
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
            if (x$1 instanceof SqlExpression) {
               label48: {
                  SqlExpression var4 = (SqlExpression)x$1;
                  String var10000 = this.expression();
                  String var5 = var4.expression();
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

   public SqlExpression(final String expression, final Origin origin) {
      this.expression = expression;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
