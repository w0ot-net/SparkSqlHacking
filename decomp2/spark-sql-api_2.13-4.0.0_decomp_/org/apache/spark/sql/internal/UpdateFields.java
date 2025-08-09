package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ug!\u0002\u0014(\u0001&\n\u0004\u0002\u0003'\u0001\u0005+\u0007I\u0011A'\t\u00119\u0003!\u0011#Q\u0001\naB\u0001b\u0014\u0001\u0003\u0016\u0004%\t\u0001\u0015\u0005\t3\u0002\u0011\t\u0012)A\u0005#\"A!\f\u0001BK\u0002\u0013\u00051\f\u0003\u0005`\u0001\tE\t\u0015!\u0003]\u0011!\u0001\u0007A!f\u0001\n\u0003\n\u0007\u0002\u00036\u0001\u0005#\u0005\u000b\u0011\u00022\t\u000b-\u0004A\u0011\u00017\t\rI\u0004A\u0011I\u0014t\u0011\u0015Q\u0003\u0001\"\u0011Q\u0011\u0019!\b\u0001\"\u0011(k\"9A\u0010AA\u0001\n\u0003i\b\"CA\u0003\u0001E\u0005I\u0011AA\u0004\u0011%\ti\u0002AI\u0001\n\u0003\ty\u0002C\u0005\u0002$\u0001\t\n\u0011\"\u0001\u0002&!I\u0011\u0011\u0006\u0001\u0012\u0002\u0013\u0005\u00111\u0006\u0005\n\u0003_\u0001\u0011\u0011!C!\u0003cA\u0011\"!\u0011\u0001\u0003\u0003%\t!a\u0011\t\u0013\u0005-\u0003!!A\u0005\u0002\u00055\u0003\"CA-\u0001\u0005\u0005I\u0011IA.\u0011%\tI\u0007AA\u0001\n\u0003\tY\u0007C\u0005\u0002v\u0001\t\t\u0011\"\u0011\u0002x!I\u00111\u0010\u0001\u0002\u0002\u0013\u0005\u0013Q\u0010\u0005\n\u0003\u007f\u0002\u0011\u0011!C!\u0003\u0003C\u0011\"a!\u0001\u0003\u0003%\t%!\"\b\u0015\u0005%u%!A\t\u0002%\nYIB\u0005'O\u0005\u0005\t\u0012A\u0015\u0002\u000e\"11\u000e\bC\u0001\u0003KC\u0011\"a \u001d\u0003\u0003%)%!!\t\u0013\u0005\u001dF$!A\u0005\u0002\u0006%\u0006\"CAZ9E\u0005I\u0011AA\u0013\u0011%\t)\fHI\u0001\n\u0003\tY\u0003C\u0005\u00028r\t\t\u0011\"!\u0002:\"I\u0011q\u0019\u000f\u0012\u0002\u0013\u0005\u0011Q\u0005\u0005\n\u0003\u0013d\u0012\u0013!C\u0001\u0003WA\u0011\"a3\u001d\u0003\u0003%I!!4\u0003\u0019U\u0003H-\u0019;f\r&,G\u000eZ:\u000b\u0005!J\u0013\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005)Z\u0013aA:rY*\u0011A&L\u0001\u0006gB\f'o\u001b\u0006\u0003]=\na!\u00199bG\",'\"\u0001\u0019\u0002\u0007=\u0014xmE\u0003\u0001eabt\b\u0005\u00024m5\tAGC\u00016\u0003\u0015\u00198-\u00197b\u0013\t9DG\u0001\u0004B]f\u0014VM\u001a\t\u0003sij\u0011aJ\u0005\u0003w\u001d\u0012!bQ8mk6tgj\u001c3f!\t\u0019T(\u0003\u0002?i\t9\u0001K]8ek\u000e$\bC\u0001!J\u001d\t\tuI\u0004\u0002C\r6\t1I\u0003\u0002E\u000b\u00061AH]8piz\u001a\u0001!C\u00016\u0013\tAE'A\u0004qC\u000e\\\u0017mZ3\n\u0005)[%\u0001D*fe&\fG.\u001b>bE2,'B\u0001%5\u0003A\u0019HO];di\u0016C\bO]3tg&|g.F\u00019\u0003E\u0019HO];di\u0016C\bO]3tg&|g\u000eI\u0001\nM&,G\u000e\u001a(b[\u0016,\u0012!\u0015\t\u0003%Zs!a\u0015+\u0011\u0005\t#\u0014BA+5\u0003\u0019\u0001&/\u001a3fM&\u0011q\u000b\u0017\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005U#\u0014A\u00034jK2$g*Y7fA\u0005ya/\u00197vK\u0016C\bO]3tg&|g.F\u0001]!\r\u0019T\fO\u0005\u0003=R\u0012aa\u00149uS>t\u0017\u0001\u0005<bYV,W\t\u001f9sKN\u001c\u0018n\u001c8!\u0003\u0019y'/[4j]V\t!\r\u0005\u0002dQ6\tAM\u0003\u0002fM\u0006)AO]3fg*\u0011q-K\u0001\tG\u0006$\u0018\r\\=ti&\u0011\u0011\u000e\u001a\u0002\u0007\u001fJLw-\u001b8\u0002\u000f=\u0014\u0018nZ5oA\u00051A(\u001b8jiz\"R!\u001c8paF\u0004\"!\u000f\u0001\t\u000b1K\u0001\u0019\u0001\u001d\t\u000b=K\u0001\u0019A)\t\u000fiK\u0001\u0013!a\u00019\"9\u0001-\u0003I\u0001\u0002\u0004\u0011\u0017!\u00038pe6\fG.\u001b>f)\u0005i\u0017\u0001C2iS2$'/\u001a8\u0016\u0003Y\u00042\u0001Q<z\u0013\tA8JA\u0002TKF\u0004\"!\u000f>\n\u0005m<#AD\"pYVlgNT8eK2K7.Z\u0001\u0005G>\u0004\u0018\u0010F\u0004n}~\f\t!a\u0001\t\u000f1k\u0001\u0013!a\u0001q!9q*\u0004I\u0001\u0002\u0004\t\u0006b\u0002.\u000e!\u0003\u0005\r\u0001\u0018\u0005\bA6\u0001\n\u00111\u0001c\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!\u0003+\u0007a\nYa\u000b\u0002\u0002\u000eA!\u0011qBA\r\u001b\t\t\tB\u0003\u0003\u0002\u0014\u0005U\u0011!C;oG\",7m[3e\u0015\r\t9\u0002N\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u000e\u0003#\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!!\t+\u0007E\u000bY!\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005\u001d\"f\u0001/\u0002\f\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"TCAA\u0017U\r\u0011\u00171B\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005M\u0002\u0003BA\u001b\u0003\u007fi!!a\u000e\u000b\t\u0005e\u00121H\u0001\u0005Y\u0006twM\u0003\u0002\u0002>\u0005!!.\u0019<b\u0013\r9\u0016qG\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u000b\u00022aMA$\u0013\r\tI\u0005\u000e\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u001f\n)\u0006E\u00024\u0003#J1!a\u00155\u0005\r\te.\u001f\u0005\n\u0003/\"\u0012\u0011!a\u0001\u0003\u000b\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA/!\u0019\ty&!\u001a\u0002P5\u0011\u0011\u0011\r\u0006\u0004\u0003G\"\u0014AC2pY2,7\r^5p]&!\u0011qMA1\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u00055\u00141\u000f\t\u0004g\u0005=\u0014bAA9i\t9!i\\8mK\u0006t\u0007\"CA,-\u0005\u0005\t\u0019AA(\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005M\u0012\u0011\u0010\u0005\n\u0003/:\u0012\u0011!a\u0001\u0003\u000b\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u000b\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003g\ta!Z9vC2\u001cH\u0003BA7\u0003\u000fC\u0011\"a\u0016\u001b\u0003\u0003\u0005\r!a\u0014\u0002\u0019U\u0003H-\u0019;f\r&,G\u000eZ:\u0011\u0005eb2#\u0002\u000f\u0002\u0010\u0006m\u0005#CAI\u0003/C\u0014\u000b\u00182n\u001b\t\t\u0019JC\u0002\u0002\u0016R\nqA];oi&lW-\u0003\u0003\u0002\u001a\u0006M%!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oiA!\u0011QTAR\u001b\t\tyJ\u0003\u0003\u0002\"\u0006m\u0012AA5p\u0013\rQ\u0015q\u0014\u000b\u0003\u0003\u0017\u000bQ!\u00199qYf$\u0012\"\\AV\u0003[\u000by+!-\t\u000b1{\u0002\u0019\u0001\u001d\t\u000b={\u0002\u0019A)\t\u000fi{\u0002\u0013!a\u00019\"9\u0001m\bI\u0001\u0002\u0004\u0011\u0017aD1qa2LH\u0005Z3gCVdG\u000fJ\u001a\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIQ\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002<\u0006\r\u0007\u0003B\u001a^\u0003{\u0003raMA`qEc&-C\u0002\u0002BR\u0012a\u0001V;qY\u0016$\u0004\u0002CAcE\u0005\u0005\t\u0019A7\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005=\u0007\u0003BA\u001b\u0003#LA!a5\u00028\t1qJ\u00196fGR\u0004"
)
public class UpdateFields implements ColumnNode, Product, Serializable {
   private final ColumnNode structExpression;
   private final String fieldName;
   private final Option valueExpression;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$4() {
      return UpdateFields$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option $lessinit$greater$default$3() {
      return UpdateFields$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final UpdateFields x$0) {
      return UpdateFields$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$4() {
      return UpdateFields$.MODULE$.apply$default$4();
   }

   public static Option apply$default$3() {
      return UpdateFields$.MODULE$.apply$default$3();
   }

   public static UpdateFields apply(final ColumnNode structExpression, final String fieldName, final Option valueExpression, final Origin origin) {
      return UpdateFields$.MODULE$.apply(structExpression, fieldName, valueExpression, origin);
   }

   public static Function1 tupled() {
      return UpdateFields$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return UpdateFields$.MODULE$.curried();
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

   public ColumnNode structExpression() {
      return this.structExpression;
   }

   public String fieldName() {
      return this.fieldName;
   }

   public Option valueExpression() {
      return this.valueExpression;
   }

   public Origin origin() {
      return this.origin;
   }

   public UpdateFields normalize() {
      ColumnNode x$1 = this.structExpression().normalize();
      Option x$2 = ColumnNode$.MODULE$.normalize(this.valueExpression());
      Origin x$3 = ColumnNode$.MODULE$.NO_ORIGIN();
      String x$4 = this.copy$default$2();
      return this.copy(x$1, x$4, x$2, x$3);
   }

   public String sql() {
      Option var2 = this.valueExpression();
      if (var2 instanceof Some var3) {
         ColumnNode value = (ColumnNode)var3.value();
         String var5 = this.structExpression().sql();
         return "update_field(" + var5 + ", " + this.fieldName() + ", " + value.sql() + ")";
      } else if (.MODULE$.equals(var2)) {
         String var10000 = this.structExpression().sql();
         return "drop_field(" + var10000 + ", " + this.fieldName() + ")";
      } else {
         throw new MatchError(var2);
      }
   }

   public Seq children() {
      ColumnNode var1 = this.structExpression();
      return (Seq)scala.Option..MODULE$.option2Iterable(this.valueExpression()).toSeq().$plus$colon(var1);
   }

   public UpdateFields copy(final ColumnNode structExpression, final String fieldName, final Option valueExpression, final Origin origin) {
      return new UpdateFields(structExpression, fieldName, valueExpression, origin);
   }

   public ColumnNode copy$default$1() {
      return this.structExpression();
   }

   public String copy$default$2() {
      return this.fieldName();
   }

   public Option copy$default$3() {
      return this.valueExpression();
   }

   public Origin copy$default$4() {
      return this.origin();
   }

   public String productPrefix() {
      return "UpdateFields";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.structExpression();
         }
         case 1 -> {
            return this.fieldName();
         }
         case 2 -> {
            return this.valueExpression();
         }
         case 3 -> {
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
      return x$1 instanceof UpdateFields;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "structExpression";
         }
         case 1 -> {
            return "fieldName";
         }
         case 2 -> {
            return "valueExpression";
         }
         case 3 -> {
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
      boolean var12;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof UpdateFields) {
               label64: {
                  UpdateFields var4 = (UpdateFields)x$1;
                  ColumnNode var10000 = this.structExpression();
                  ColumnNode var5 = var4.structExpression();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label64;
                  }

                  String var9 = this.fieldName();
                  String var6 = var4.fieldName();
                  if (var9 == null) {
                     if (var6 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var6)) {
                     break label64;
                  }

                  Option var10 = this.valueExpression();
                  Option var7 = var4.valueExpression();
                  if (var10 == null) {
                     if (var7 != null) {
                        break label64;
                     }
                  } else if (!var10.equals(var7)) {
                     break label64;
                  }

                  Origin var11 = this.origin();
                  Origin var8 = var4.origin();
                  if (var11 == null) {
                     if (var8 != null) {
                        break label64;
                     }
                  } else if (!var11.equals(var8)) {
                     break label64;
                  }

                  if (var4.canEqual(this)) {
                     break label71;
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public UpdateFields(final ColumnNode structExpression, final String fieldName, final Option valueExpression, final Origin origin) {
      this.structExpression = structExpression;
      this.fieldName = fieldName;
      this.valueExpression = valueExpression;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
