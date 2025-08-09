package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.QueryContext;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.errors.DataTypeErrorsBase;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]g!B\u0012%\u0001\u001ar\u0003\u0002C(\u0001\u0005+\u0007I\u0011\u0001)\t\u0011Q\u0003!\u0011#Q\u0001\nEC\u0001\"\u0016\u0001\u0003\u0016\u0004%\tA\u0016\u0005\tA\u0002\u0011\t\u0012)A\u0005/\"A\u0011\r\u0001BK\u0002\u0013\u0005#\r\u0003\u0005l\u0001\tE\t\u0015!\u0003d\u0011\u0015a\u0007\u0001\"\u0001n\u0011\u0019\u0011\b\u0001\"\u0011%g\")q\u0005\u0001C!i\"1Q\u0010\u0001C!IyD\u0011\"a\u0003\u0001\u0003\u0003%\t!!\u0004\t\u0013\u0005U\u0001!%A\u0005\u0002\u0005]\u0001\"CA\u0017\u0001E\u0005I\u0011AA\u0018\u0011%\t\u0019\u0004AI\u0001\n\u0003\t)\u0004C\u0005\u0002:\u0001\t\t\u0011\"\u0011\u0002<!I\u00111\n\u0001\u0002\u0002\u0013\u0005\u0011Q\n\u0005\n\u0003+\u0002\u0011\u0011!C\u0001\u0003/B\u0011\"!\u0018\u0001\u0003\u0003%\t%a\u0018\t\u0013\u00055\u0004!!A\u0005\u0002\u0005=\u0004\"CA=\u0001\u0005\u0005I\u0011IA>\u0011%\ty\bAA\u0001\n\u0003\n\t\tC\u0005\u0002\u0004\u0002\t\t\u0011\"\u0011\u0002\u0006\"I\u0011q\u0011\u0001\u0002\u0002\u0013\u0005\u0013\u0011R\u0004\u000b\u0003\u001b#\u0013\u0011!E\u0001M\u0005=e!C\u0012%\u0003\u0003E\tAJAI\u0011\u0019a\u0017\u0004\"\u0001\u0002*\"I\u00111Q\r\u0002\u0002\u0013\u0015\u0013Q\u0011\u0005\n\u0003WK\u0012\u0011!CA\u0003[C\u0011\"!.\u001a#\u0003%\t!a\f\t\u0013\u0005]\u0016$%A\u0005\u0002\u0005U\u0002\"CA]3\u0005\u0005I\u0011QA^\u0011%\tI-GI\u0001\n\u0003\ty\u0003C\u0005\u0002Lf\t\n\u0011\"\u0001\u00026!I\u0011QZ\r\u0002\u0002\u0013%\u0011q\u001a\u0002\b\u0019&$XM]1m\u0015\t)c%\u0001\u0005j]R,'O\\1m\u0015\t9\u0003&A\u0002tc2T!!\u000b\u0016\u0002\u000bM\u0004\u0018M]6\u000b\u0005-b\u0013AB1qC\u000eDWMC\u0001.\u0003\ry'oZ\n\u0007\u0001=*\u0014h\u0010\"\u0011\u0005A\u001aT\"A\u0019\u000b\u0003I\nQa]2bY\u0006L!\u0001N\u0019\u0003\r\u0005s\u0017PU3g!\t1t'D\u0001%\u0013\tADE\u0001\u0006D_2,XN\u001c(pI\u0016\u0004\"AO\u001f\u000e\u0003mR!\u0001\u0010\u0014\u0002\r\u0015\u0014(o\u001c:t\u0013\tq4H\u0001\nECR\fG+\u001f9f\u000bJ\u0014xN]:CCN,\u0007C\u0001\u0019A\u0013\t\t\u0015GA\u0004Qe>$Wo\u0019;\u0011\u0005\rceB\u0001#K\u001d\t)\u0015*D\u0001G\u0015\t9\u0005*\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0011\u0014BA&2\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0014(\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005-\u000b\u0014!\u0002<bYV,W#A)\u0011\u0005A\u0012\u0016BA*2\u0005\r\te._\u0001\u0007m\u0006dW/\u001a\u0011\u0002\u0011\u0011\fG/\u0019+za\u0016,\u0012a\u0016\t\u0004aaS\u0016BA-2\u0005\u0019y\u0005\u000f^5p]B\u00111LX\u0007\u00029*\u0011QLJ\u0001\u0006if\u0004Xm]\u0005\u0003?r\u0013\u0001\u0002R1uCRK\b/Z\u0001\nI\u0006$\u0018\rV=qK\u0002\naa\u001c:jO&tW#A2\u0011\u0005\u0011LW\"A3\u000b\u0005\u0019<\u0017!\u0002;sK\u0016\u001c(B\u00015'\u0003!\u0019\u0017\r^1msN$\u0018B\u00016f\u0005\u0019y%/[4j]\u00069qN]5hS:\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003o_B\f\bC\u0001\u001c\u0001\u0011\u0015yu\u00011\u0001R\u0011\u001d)v\u0001%AA\u0002]Cq!Y\u0004\u0011\u0002\u0003\u00071-A\u0005o_Jl\u0017\r\\5{KR\ta.F\u0001v!\t1(P\u0004\u0002xqB\u0011Q)M\u0005\u0003sF\na\u0001\u0015:fI\u00164\u0017BA>}\u0005\u0019\u0019FO]5oO*\u0011\u00110M\u0001\tG\"LG\u000e\u001a:f]V\tq\u0010E\u0003D\u0003\u0003\t)!C\u0002\u0002\u00049\u00131aU3r!\r1\u0014qA\u0005\u0004\u0003\u0013!#AD\"pYVlgNT8eK2K7.Z\u0001\u0005G>\u0004\u0018\u0010F\u0004o\u0003\u001f\t\t\"a\u0005\t\u000f=[\u0001\u0013!a\u0001#\"9Qk\u0003I\u0001\u0002\u00049\u0006bB1\f!\u0003\u0005\raY\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tIBK\u0002R\u00037Y#!!\b\u0011\t\u0005}\u0011\u0011F\u0007\u0003\u0003CQA!a\t\u0002&\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003O\t\u0014AC1o]>$\u0018\r^5p]&!\u00111FA\u0011\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\t\tDK\u0002X\u00037\tabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u00028)\u001a1-a\u0007\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ti\u0004\u0005\u0003\u0002@\u0005%SBAA!\u0015\u0011\t\u0019%!\u0012\u0002\t1\fgn\u001a\u0006\u0003\u0003\u000f\nAA[1wC&\u001910!\u0011\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005=\u0003c\u0001\u0019\u0002R%\u0019\u00111K\u0019\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007E\u000bI\u0006C\u0005\u0002\\E\t\t\u00111\u0001\u0002P\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0019\u0011\u000b\u0005\r\u0014\u0011N)\u000e\u0005\u0005\u0015$bAA4c\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005-\u0014Q\r\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002r\u0005]\u0004c\u0001\u0019\u0002t%\u0019\u0011QO\u0019\u0003\u000f\t{w\u000e\\3b]\"A\u00111L\n\u0002\u0002\u0003\u0007\u0011+\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u001f\u0003{B\u0011\"a\u0017\u0015\u0003\u0003\u0005\r!a\u0014\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0014\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0010\u0002\r\u0015\fX/\u00197t)\u0011\t\t(a#\t\u0011\u0005ms#!AA\u0002E\u000bq\u0001T5uKJ\fG\u000e\u0005\u000273M)\u0011$a%\u0002 BA\u0011QSAN#^\u001bg.\u0004\u0002\u0002\u0018*\u0019\u0011\u0011T\u0019\u0002\u000fI,h\u000e^5nK&!\u0011QTAL\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003C\u000b9+\u0004\u0002\u0002$*!\u0011QUA#\u0003\tIw.C\u0002N\u0003G#\"!a$\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000f9\fy+!-\u00024\")q\n\ba\u0001#\"9Q\u000b\bI\u0001\u0002\u00049\u0006bB1\u001d!\u0003\u0005\raY\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$3'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005u\u0016Q\u0019\t\u0005aa\u000by\f\u0005\u00041\u0003\u0003\fvkY\u0005\u0004\u0003\u0007\f$A\u0002+va2,7\u0007\u0003\u0005\u0002H~\t\t\u00111\u0001o\u0003\rAH\u0005M\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u000e\u0005\u0003\u0002@\u0005M\u0017\u0002BAk\u0003\u0003\u0012aa\u00142kK\u000e$\b"
)
public class Literal implements ColumnNode, DataTypeErrorsBase, Product, Serializable {
   private final Object value;
   private final Option dataType;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$3() {
      return Literal$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return Literal$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final Literal x$0) {
      return Literal$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$3() {
      return Literal$.MODULE$.apply$default$3();
   }

   public static Option apply$default$2() {
      return Literal$.MODULE$.apply$default$2();
   }

   public static Literal apply(final Object value, final Option dataType, final Origin origin) {
      return Literal$.MODULE$.apply(value, dataType, origin);
   }

   public static Function1 tupled() {
      return Literal$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Literal$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String toSQLId(final String parts) {
      return DataTypeErrorsBase.toSQLId$(this, (String)parts);
   }

   public String toSQLId(final Seq parts) {
      return DataTypeErrorsBase.toSQLId$(this, (Seq)parts);
   }

   public String toSQLStmt(final String text) {
      return DataTypeErrorsBase.toSQLStmt$(this, text);
   }

   public String toSQLConf(final String conf) {
      return DataTypeErrorsBase.toSQLConf$(this, conf);
   }

   public String toSQLType(final String text) {
      return DataTypeErrorsBase.toSQLType$(this, (String)text);
   }

   public String toSQLType(final AbstractDataType t) {
      return DataTypeErrorsBase.toSQLType$(this, (AbstractDataType)t);
   }

   public String toSQLValue(final String value) {
      return DataTypeErrorsBase.toSQLValue$(this, (String)value);
   }

   public String toSQLValue(final UTF8String value) {
      return DataTypeErrorsBase.toSQLValue$(this, (UTF8String)value);
   }

   public String toSQLValue(final short value) {
      return DataTypeErrorsBase.toSQLValue$(this, (short)value);
   }

   public String toSQLValue(final int value) {
      return DataTypeErrorsBase.toSQLValue$(this, (int)value);
   }

   public String toSQLValue(final long value) {
      return DataTypeErrorsBase.toSQLValue$(this, value);
   }

   public String toSQLValue(final float value) {
      return DataTypeErrorsBase.toSQLValue$(this, value);
   }

   public String toSQLValue(final double value) {
      return DataTypeErrorsBase.toSQLValue$(this, value);
   }

   public String quoteByDefault(final String elem) {
      return DataTypeErrorsBase.quoteByDefault$(this, elem);
   }

   public String getSummary(final QueryContext sqlContext) {
      return DataTypeErrorsBase.getSummary$(this, sqlContext);
   }

   public QueryContext[] getQueryContext(final QueryContext context) {
      return DataTypeErrorsBase.getQueryContext$(this, context);
   }

   public String toDSOption(final String option) {
      return DataTypeErrorsBase.toDSOption$(this, option);
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

   public Object value() {
      return this.value;
   }

   public Option dataType() {
      return this.dataType;
   }

   public Origin origin() {
      return this.origin;
   }

   public Literal normalize() {
      Origin x$1 = ColumnNode$.MODULE$.NO_ORIGIN();
      Object x$2 = this.copy$default$1();
      Option x$3 = this.copy$default$2();
      return this.copy(x$2, x$3, x$1);
   }

   public String sql() {
      Object var2 = this.value();
      if (var2 == null) {
         return "NULL";
      } else if (var2 instanceof String) {
         String var3 = (String)var2;
         return this.toSQLValue(var3);
      } else if (var2 instanceof Long) {
         long var4 = BoxesRunTime.unboxToLong(var2);
         return this.toSQLValue(var4);
      } else if (var2 instanceof Float) {
         float var6 = BoxesRunTime.unboxToFloat(var2);
         return this.toSQLValue(var6);
      } else if (var2 instanceof Double) {
         double var7 = BoxesRunTime.unboxToDouble(var2);
         return this.toSQLValue(var7);
      } else if (var2 instanceof Short) {
         short var9 = BoxesRunTime.unboxToShort(var2);
         return this.toSQLValue(var9);
      } else {
         return this.value().toString();
      }
   }

   public Seq children() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Literal copy(final Object value, final Option dataType, final Origin origin) {
      return new Literal(value, dataType, origin);
   }

   public Object copy$default$1() {
      return this.value();
   }

   public Option copy$default$2() {
      return this.dataType();
   }

   public Origin copy$default$3() {
      return this.origin();
   }

   public String productPrefix() {
      return "Literal";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.value();
         }
         case 1 -> {
            return this.dataType();
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
      return x$1 instanceof Literal;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "value";
         }
         case 1 -> {
            return "dataType";
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
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof Literal) {
               Literal var4 = (Literal)x$1;
               if (BoxesRunTime.equals(this.value(), var4.value())) {
                  label52: {
                     Option var10000 = this.dataType();
                     Option var5 = var4.dataType();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Origin var7 = this.origin();
                     Origin var6 = var4.origin();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
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

   public Literal(final Object value, final Option dataType, final Origin origin) {
      this.value = value;
      this.dataType = dataType;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      DataTypeErrorsBase.$init$(this);
      Product.$init$(this);
   }
}
