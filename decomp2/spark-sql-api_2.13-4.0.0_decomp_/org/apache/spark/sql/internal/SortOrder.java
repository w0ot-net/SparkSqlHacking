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
   bytes = "\u0006\u0005\tUb!B\u001d;\u0001r\"\u0005\u0002C0\u0001\u0005+\u0007I\u0011\u00011\t\u0011\u0005\u0004!\u0011#Q\u0001\n-C\u0001B\u0019\u0001\u0003\u0016\u0004%\ta\u0019\u0005\n\u0003[\u0003!\u0011#Q\u0001\n\u0011D!\"a\u0016\u0001\u0005+\u0007I\u0011AAX\u0011)\t\t\f\u0001B\tB\u0003%\u0011\u0011\f\u0005\u000b\u0003;\u0002!Q3A\u0005B\u0005M\u0006BCA[\u0001\tE\t\u0015!\u0003\u0002`!1\u0001\u000f\u0001C\u0001\u0003oC\u0001\"!1\u0001\t\u0003R\u00141\u0019\u0005\u0006{\u0001!\te\u001e\u0005\b\u0003\u0017\u0001A\u0011IA\u0007\u0011%\t)\rAA\u0001\n\u0003\t9\rC\u0005\u0002R\u0002\t\n\u0011\"\u0001\u0002T\"I\u0011q\u001b\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u001c\u0005\n\u0003;\u0004\u0011\u0013!C\u0001\u0003?D\u0011\"a9\u0001#\u0003%\t!!\u001d\t\u0013\u0005\u0015\b!!A\u0005B\u0005\u001d\b\"CAw\u0001\u0005\u0005I\u0011AAx\u0011%\t9\u0010AA\u0001\n\u0003\tI\u0010C\u0005\u0003\u0006\u0001\t\t\u0011\"\u0011\u0003\b!I!Q\u0003\u0001\u0002\u0002\u0013\u0005!q\u0003\u0005\n\u0005C\u0001\u0011\u0011!C!\u0005GA\u0011Ba\n\u0001\u0003\u0003%\tE!\u000b\t\u0013\t-\u0002!!A\u0005B\t5\u0002\"\u0003B\u0018\u0001\u0005\u0005I\u0011\tB\u0019\u000f\u00191'\b#\u0001=O\u001a1\u0011H\u000fE\u0001y!DQ\u0001\u001d\u000f\u0005\u0002E4QA\u001d\u000f\u0002\"MD\u0001\"\u0010\u0010\u0003\u0006\u0004%\te\u001e\u0005\n\u0003\u0003q\"\u0011!Q\u0001\naDa\u0001\u001d\u0010\u0005\u0002\u0005\r\u0001\u0002CA\u0006=\u0011\u0005#(!\u0004\b\u000f\u0005\u001dB\u0004#\u0001\u0002\u001e\u00199\u0011q\u0003\u000f\t\u0002\u0005e\u0001B\u00029%\t\u0003\tYbB\u0004\u0002*qA\t!!\n\u0007\u000f\u0005}A\u0004#\u0001\u0002\"!1\u0001o\nC\u0001\u0003G1q!a\u000b\u001d\u0003C\ti\u0003\u0003\u0005>S\t\u0015\r\u0011\"\u0011x\u0011%\t\t!\u000bB\u0001B\u0003%\u0001\u0010\u0003\u0004qS\u0011\u0005\u0011q\u0006\u0005\t\u0003\u0017IC\u0011\t\u001e\u0002\u000e\u001d9\u0011q\t\u000f\t\u0002\u0005ubaBA\u001c9!\u0005\u0011\u0011\b\u0005\u0007a>\"\t!a\u000f\b\u000f\u0005%C\u0004#\u0001\u0002F\u00199\u0011q\b\u000f\t\u0002\u0005\u0005\u0003B\u000293\t\u0003\t\u0019\u0005C\u0005\u0002Lq\t\t\u0011\"!\u0002N!I\u0011q\u000e\u000f\u0012\u0002\u0013\u0005\u0011\u0011\u000f\u0005\n\u0003\u000fc\u0012\u0011!CA\u0003\u0013C\u0011\"a'\u001d#\u0003%\t!!\u001d\t\u0013\u0005uE$!A\u0005\n\u0005}%!C*peR|%\u000fZ3s\u0015\tYD(\u0001\u0005j]R,'O\\1m\u0015\tid(A\u0002tc2T!a\u0010!\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0005\u0013\u0015AB1qC\u000eDWMC\u0001D\u0003\ry'oZ\n\u0006\u0001\u0015[uJ\u0015\t\u0003\r&k\u0011a\u0012\u0006\u0002\u0011\u0006)1oY1mC&\u0011!j\u0012\u0002\u0007\u0003:L(+\u001a4\u0011\u00051kU\"\u0001\u001e\n\u00059S$AC\"pYVlgNT8eKB\u0011a\tU\u0005\u0003#\u001e\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002T9:\u0011AK\u0017\b\u0003+fk\u0011A\u0016\u0006\u0003/b\u000ba\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u0011&\u00111lR\u0001\ba\u0006\u001c7.Y4f\u0013\tifL\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002\\\u000f\u0006)1\r[5mIV\t1*\u0001\u0004dQ&dG\rI\u0001\u000eg>\u0014H\u000fR5sK\u000e$\u0018n\u001c8\u0016\u0003\u0011\u0004\"!\u001a\u0010\u000f\u00051[\u0012!C*peR|%\u000fZ3s!\taEdE\u0002\u001d\u000b&\u0004\"A[8\u000e\u0003-T!\u0001\\7\u0002\u0005%|'\"\u00018\u0002\t)\fg/Y\u0005\u0003;.\fa\u0001P5oSRtD#A4\u0003\u001bM{'\u000f\u001e#je\u0016\u001cG/[8o'\rqR\t\u001e\t\u0003\u0019VL!A\u001e\u001e\u0003\u001d\r{G.^7o\u001d>$W\rT5lKV\t\u0001\u0010\u0005\u0002z{:\u0011!p\u001f\t\u0003+\u001eK!\u0001`$\u0002\rA\u0013X\rZ3g\u0013\tqxP\u0001\u0004TiJLgn\u001a\u0006\u0003y\u001e\u000bAa]9mAQ!\u0011QAA\u0005!\r\t9AH\u0007\u00029!)Q(\ta\u0001q\u0006A1\r[5mIJ,g.\u0006\u0002\u0002\u0010A!1+!\u0005u\u0013\r\t\u0019B\u0018\u0002\u0004'\u0016\f\u0018f\u0001\u0010%O\tI\u0011i]2f]\u0012LgnZ\n\u0004I\u0005\u0015ACAA\u000f!\r\t9\u0001\n\u0002\u000b\t\u0016\u001c8-\u001a8eS:<7cA\u0014\u0002\u0006Q\u0011\u0011Q\u0005\t\u0004\u0003\u000f9\u0013!C!tG\u0016tG-\u001b8h\u0003)!Um]2f]\u0012Lgn\u001a\u0002\r\u001dVdGn\u0014:eKJLgnZ\n\u0004S\u0015#H\u0003BA\u0019\u0003g\u00012!a\u0002*\u0011\u0015iD\u00061\u0001yS\rIsF\r\u0002\u000b\u001dVdGn\u001d$jeN$8cA\u0018\u00022Q\u0011\u0011Q\b\t\u0004\u0003\u000fy#!\u0003(vY2\u001cH*Y:u'\r\u0011\u0014\u0011\u0007\u000b\u0003\u0003\u000b\u00022!a\u00023\u0003)qU\u000f\u001c7t\r&\u00148\u000f^\u0001\n\u001dVdGn\u001d'bgR\fQ!\u00199qYf$\"\"a\u0014\u0002R\u0005M\u0013QKA.!\ta\u0005\u0001C\u0003`i\u0001\u00071\nC\u0003ci\u0001\u0007A\rC\u0004\u0002XQ\u0002\r!!\u0017\u0002\u00199,H\u000e\\(sI\u0016\u0014\u0018N\\4\u0011\u0005\u0015L\u0003\"CA/iA\u0005\t\u0019AA0\u0003\u0019y'/[4j]B!\u0011\u0011MA6\u001b\t\t\u0019G\u0003\u0003\u0002f\u0005\u001d\u0014!\u0002;sK\u0016\u001c(bAA5y\u0005A1-\u0019;bYf\u001cH/\u0003\u0003\u0002n\u0005\r$AB(sS\u001eLg.A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\t\u0019H\u000b\u0003\u0002`\u0005U4FAA<!\u0011\tI(a!\u000e\u0005\u0005m$\u0002BA?\u0003\u007f\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0005u)\u0001\u0006b]:|G/\u0019;j_:LA!!\"\u0002|\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u000fUt\u0017\r\u001d9msR!\u00111RAL!\u00151\u0015QRAI\u0013\r\tyi\u0012\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0013\u0019\u000b\u0019j\u00133\u0002Z\u0005}\u0013bAAK\u000f\n1A+\u001e9mKRB\u0011\"!'7\u0003\u0003\u0005\r!a\u0014\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003C\u0003B!a)\u0002*6\u0011\u0011Q\u0015\u0006\u0004\u0003Ok\u0017\u0001\u00027b]\u001eLA!a+\u0002&\n1qJ\u00196fGR\fab]8si\u0012K'/Z2uS>t\u0007%\u0006\u0002\u0002Z\u0005ia.\u001e7m\u001fJ$WM]5oO\u0002*\"!a\u0018\u0002\u000f=\u0014\u0018nZ5oAQQ\u0011qJA]\u0003w\u000bi,a0\t\u000b}K\u0001\u0019A&\t\u000b\tL\u0001\u0019\u00013\t\u000f\u0005]\u0013\u00021\u0001\u0002Z!I\u0011QL\u0005\u0011\u0002\u0003\u0007\u0011qL\u0001\n]>\u0014X.\u00197ju\u0016$\"!a\u0014\u0002\t\r|\u0007/\u001f\u000b\u000b\u0003\u001f\nI-a3\u0002N\u0006=\u0007bB0\u000e!\u0003\u0005\ra\u0013\u0005\bE6\u0001\n\u00111\u0001e\u0011%\t9&\u0004I\u0001\u0002\u0004\tI\u0006C\u0005\u0002^5\u0001\n\u00111\u0001\u0002`\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAAkU\rY\u0015QO\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\tYNK\u0002e\u0003k\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002b*\"\u0011\u0011LA;\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAu!\u0011\t\u0019+a;\n\u0007y\f)+\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002rB\u0019a)a=\n\u0007\u0005UxIA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002|\n\u0005\u0001c\u0001$\u0002~&\u0019\u0011q`$\u0003\u0007\u0005s\u0017\u0010C\u0005\u0003\u0004Q\t\t\u00111\u0001\u0002r\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!\u0003\u0011\r\t-!\u0011CA~\u001b\t\u0011iAC\u0002\u0003\u0010\u001d\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011\u0019B!\u0004\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u00053\u0011y\u0002E\u0002G\u00057I1A!\bH\u0005\u001d\u0011un\u001c7fC:D\u0011Ba\u0001\u0017\u0003\u0003\u0005\r!a?\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003S\u0014)\u0003C\u0005\u0003\u0004]\t\t\u00111\u0001\u0002r\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002r\u0006AAo\\*ue&tw\r\u0006\u0002\u0002j\u00061Q-];bYN$BA!\u0007\u00034!I!1\u0001\u000e\u0002\u0002\u0003\u0007\u00111 "
)
public class SortOrder implements ColumnNode, Product, Serializable {
   private final ColumnNode child;
   private final SortDirection sortDirection;
   private final NullOrdering nullOrdering;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$4() {
      return SortOrder$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option unapply(final SortOrder x$0) {
      return SortOrder$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$4() {
      return SortOrder$.MODULE$.apply$default$4();
   }

   public static SortOrder apply(final ColumnNode child, final SortDirection sortDirection, final NullOrdering nullOrdering, final Origin origin) {
      return SortOrder$.MODULE$.apply(child, sortDirection, nullOrdering, origin);
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

   public SortDirection sortDirection() {
      return this.sortDirection;
   }

   public NullOrdering nullOrdering() {
      return this.nullOrdering;
   }

   public Origin origin() {
      return this.origin;
   }

   public SortOrder normalize() {
      ColumnNode x$1 = this.child().normalize();
      Origin x$2 = ColumnNode$.MODULE$.NO_ORIGIN();
      SortDirection x$3 = this.copy$default$2();
      NullOrdering x$4 = this.copy$default$3();
      return this.copy(x$1, x$3, x$4, x$2);
   }

   public String sql() {
      String var10000 = this.child().sql();
      return var10000 + " " + this.sortDirection().sql() + " " + this.nullOrdering().sql();
   }

   public Seq children() {
      return new .colon.colon(this.child(), new .colon.colon(this.sortDirection(), new .colon.colon(this.nullOrdering(), scala.collection.immutable.Nil..MODULE$)));
   }

   public SortOrder copy(final ColumnNode child, final SortDirection sortDirection, final NullOrdering nullOrdering, final Origin origin) {
      return new SortOrder(child, sortDirection, nullOrdering, origin);
   }

   public ColumnNode copy$default$1() {
      return this.child();
   }

   public SortDirection copy$default$2() {
      return this.sortDirection();
   }

   public NullOrdering copy$default$3() {
      return this.nullOrdering();
   }

   public Origin copy$default$4() {
      return this.origin();
   }

   public String productPrefix() {
      return "SortOrder";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.child();
         }
         case 1 -> {
            return this.sortDirection();
         }
         case 2 -> {
            return this.nullOrdering();
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
      return x$1 instanceof SortOrder;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "child";
         }
         case 1 -> {
            return "sortDirection";
         }
         case 2 -> {
            return "nullOrdering";
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
            if (x$1 instanceof SortOrder) {
               label64: {
                  SortOrder var4 = (SortOrder)x$1;
                  ColumnNode var10000 = this.child();
                  ColumnNode var5 = var4.child();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label64;
                  }

                  SortDirection var9 = this.sortDirection();
                  SortDirection var6 = var4.sortDirection();
                  if (var9 == null) {
                     if (var6 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var6)) {
                     break label64;
                  }

                  NullOrdering var10 = this.nullOrdering();
                  NullOrdering var7 = var4.nullOrdering();
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

   public SortOrder(final ColumnNode child, final SortDirection sortDirection, final NullOrdering nullOrdering, final Origin origin) {
      this.child = child;
      this.sortDirection = sortDirection;
      this.nullOrdering = nullOrdering;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }

   public abstract static class SortDirection implements ColumnNodeLike {
      private final String sql;

      public ColumnNodeLike normalize() {
         return ColumnNodeLike.normalize$(this);
      }

      public void foreach(final Function1 f) {
         ColumnNodeLike.foreach$(this, f);
      }

      public Seq collect(final PartialFunction pf) {
         return ColumnNodeLike.collect$(this, pf);
      }

      public String sql() {
         return this.sql;
      }

      public Seq children() {
         return (Seq)scala.package..MODULE$.Seq().empty();
      }

      public SortDirection(final String sql) {
         this.sql = sql;
         ColumnNodeLike.$init$(this);
      }
   }

   public static class Ascending$ extends SortDirection {
      public static final Ascending$ MODULE$ = new Ascending$();

      public Ascending$() {
         super("ASC");
      }
   }

   public static class Descending$ extends SortDirection {
      public static final Descending$ MODULE$ = new Descending$();

      public Descending$() {
         super("DESC");
      }
   }

   public abstract static class NullOrdering implements ColumnNodeLike {
      private final String sql;

      public ColumnNodeLike normalize() {
         return ColumnNodeLike.normalize$(this);
      }

      public void foreach(final Function1 f) {
         ColumnNodeLike.foreach$(this, f);
      }

      public Seq collect(final PartialFunction pf) {
         return ColumnNodeLike.collect$(this, pf);
      }

      public String sql() {
         return this.sql;
      }

      public Seq children() {
         return (Seq)scala.package..MODULE$.Seq().empty();
      }

      public NullOrdering(final String sql) {
         this.sql = sql;
         ColumnNodeLike.$init$(this);
      }
   }

   public static class NullsFirst$ extends NullOrdering {
      public static final NullsFirst$ MODULE$ = new NullsFirst$();

      public NullsFirst$() {
         super("NULLS FIRST");
      }
   }

   public static class NullsLast$ extends NullOrdering {
      public static final NullsLast$ MODULE$ = new NullsLast$();

      public NullsLast$() {
         super("NULLS LAST");
      }
   }
}
