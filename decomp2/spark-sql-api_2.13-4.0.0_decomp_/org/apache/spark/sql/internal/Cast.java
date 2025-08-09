package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.types.DataType;
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
   bytes = "\u0006\u0005\teb!B\u001c9\u0001j\u0012\u0005\u0002C/\u0001\u0005+\u0007I\u0011\u00010\t\u0011}\u0003!\u0011#Q\u0001\n%C\u0001\u0002\u0019\u0001\u0003\u0016\u0004%\t!\u0019\u0005\tQ\u0002\u0011\t\u0012)A\u0005E\"A\u0011\u000e\u0001BK\u0002\u0013\u0005!\u000eC\u0005\u0002:\u0002\u0011\t\u0012)A\u0005W\"Q\u0011q\u000f\u0001\u0003\u0016\u0004%\t%a/\t\u0015\u0005u\u0006A!E!\u0002\u0013\tI\b\u0003\u0004{\u0001\u0011\u0005\u0011q\u0018\u0005\t\u0003\u0013\u0004A\u0011\t\u001d\u0002L\"11\b\u0001C!\u0003\u0007A\u0001\"a\b\u0001\t\u0003B\u0014\u0011\u0005\u0005\n\u0003\u001b\u0004\u0011\u0011!C\u0001\u0003\u001fD\u0011\"!7\u0001#\u0003%\t!a7\t\u0013\u0005}\u0007!%A\u0005\u0002\u0005\u0005\b\"CAs\u0001E\u0005I\u0011AAF\u0011%\t9\u000fAI\u0001\n\u0003\t\t\nC\u0005\u0002j\u0002\t\t\u0011\"\u0011\u0002l\"I\u0011\u0011\u001f\u0001\u0002\u0002\u0013\u0005\u00111\u001f\u0005\n\u0003w\u0004\u0011\u0011!C\u0001\u0003{D\u0011B!\u0003\u0001\u0003\u0003%\tEa\u0003\t\u0013\te\u0001!!A\u0005\u0002\tm\u0001\"\u0003B\u0013\u0001\u0005\u0005I\u0011\tB\u0014\u0011%\u0011Y\u0003AA\u0001\n\u0003\u0012i\u0003C\u0005\u00030\u0001\t\t\u0011\"\u0011\u00032!I!1\u0007\u0001\u0002\u0002\u0013\u0005#QG\u0004\u0007abB\tAO9\u0007\r]B\u0004\u0012\u0001\u001es\u0011\u0015QH\u0004\"\u0001|\r\u0015aH$!\t~\u0011%YdD!b\u0001\n\u0003\n\u0019\u0001\u0003\u0006\u0002\u0016y\u0011\t\u0011)A\u0005\u0003\u000bAaA\u001f\u0010\u0005\u0002\u0005]\u0001\u0002CA\u0010=\u0011\u0005\u0003(!\t\b\u0013\u0005\rC$!A\t\u0002\u0005\u0015c\u0001\u0003?\u001d\u0003\u0003E\t!a\u0012\t\ri$C\u0011AA%\u0011%\tY\u0005JI\u0001\n\u0003\tieB\u0004\u0002dqA\t!!\u000f\u0007\u000f\u0005MB\u0004#\u0001\u00026!1!\u0010\u000bC\u0001\u0003o9q!!\u001a\u001d\u0011\u0003\t\tDB\u0004\u0002,qA\t!!\f\t\ri\\C\u0011AA\u0018\u000f\u001d\t9\u0007\bE\u0001\u0003\u00032q!a\u000f\u001d\u0011\u0003\ti\u0004\u0003\u0004{]\u0011\u0005\u0011q\b\u0005\n\u0003Sb\u0012\u0011!CA\u0003WB\u0011\"!#\u001d#\u0003%\t!a#\t\u0013\u0005=E$%A\u0005\u0002\u0005E\u0005\"CAK9\u0005\u0005I\u0011QAL\u0011%\t)\u000bHI\u0001\n\u0003\tY\tC\u0005\u0002(r\t\n\u0011\"\u0001\u0002\u0012\"I\u0011\u0011\u0016\u000f\u0002\u0002\u0013%\u00111\u0016\u0002\u0005\u0007\u0006\u001cHO\u0003\u0002:u\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002<y\u0005\u00191/\u001d7\u000b\u0005ur\u0014!B:qCJ\\'BA A\u0003\u0019\t\u0007/Y2iK*\t\u0011)A\u0002pe\u001e\u001cR\u0001A\"J\u001bB\u0003\"\u0001R$\u000e\u0003\u0015S\u0011AR\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0011\u0016\u0013a!\u00118z%\u00164\u0007C\u0001&L\u001b\u0005A\u0014B\u0001'9\u0005)\u0019u\u000e\\;n]:{G-\u001a\t\u0003\t:K!aT#\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011K\u0017\b\u0003%bs!aU,\u000e\u0003QS!!\u0016,\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AR\u0005\u00033\u0016\u000bq\u0001]1dW\u0006<W-\u0003\u0002\\9\na1+\u001a:jC2L'0\u00192mK*\u0011\u0011,R\u0001\u0006G\"LG\u000eZ\u000b\u0002\u0013\u000611\r[5mI\u0002\n\u0001\u0002Z1uCRK\b/Z\u000b\u0002EB\u00111MZ\u0007\u0002I*\u0011QMO\u0001\u0006if\u0004Xm]\u0005\u0003O\u0012\u0014\u0001\u0002R1uCRK\b/Z\u0001\nI\u0006$\u0018\rV=qK\u0002\n\u0001\"\u001a<bY6{G-Z\u000b\u0002WB\u0019A\t\u001c8\n\u00055,%AB(qi&|g\u000e\u0005\u0002p=9\u0011!jG\u0001\u0005\u0007\u0006\u001cH\u000f\u0005\u0002K9M\u0019AdQ:\u0011\u0005QLX\"A;\u000b\u0005Y<\u0018AA5p\u0015\u0005A\u0018\u0001\u00026bm\u0006L!aW;\u0002\rqJg.\u001b;?)\u0005\t(\u0001C#wC2lu\u000eZ3\u0014\u0007y\u0019e\u0010\u0005\u0002K\u007f&\u0019\u0011\u0011\u0001\u001d\u0003\u001d\r{G.^7o\u001d>$W\rT5lKV\u0011\u0011Q\u0001\t\u0005\u0003\u000f\tyA\u0004\u0003\u0002\n\u0005-\u0001CA*F\u0013\r\ti!R\u0001\u0007!J,G-\u001a4\n\t\u0005E\u00111\u0003\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u00055Q)\u0001\u0003tc2\u0004C\u0003BA\r\u0003;\u00012!a\u0007\u001f\u001b\u0005a\u0002\u0002C\u001e\"!\u0003\u0005\r!!\u0002\u0002\u0011\rD\u0017\u000e\u001c3sK:,\"!a\t\u0011\tE\u000b)C`\u0005\u0004\u0003Oa&aA*fc&\"ad\u000b\u0015/\u0005\u0011\ten]5\u0014\u0007-\nI\u0002\u0006\u0002\u00022A\u0019\u00111D\u0016\u0003\r1+w-Y2z'\rA\u0013\u0011\u0004\u000b\u0003\u0003s\u00012!a\u0007)\u0005\r!&/_\n\u0004]\u0005eACAA!!\r\tYBL\u0001\t\u000bZ\fG.T8eKB\u0019\u00111\u0004\u0013\u0014\u0005\u0011\u001aECAA#\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\n\u0016\u0005\u0003\u000b\t\tf\u000b\u0002\u0002TA!\u0011QKA0\u001b\t\t9F\u0003\u0003\u0002Z\u0005m\u0013!C;oG\",7m[3e\u0015\r\ti&R\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA1\u0003/\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u0019aUmZ1ds\u0006!\u0011I\\:j\u0003\r!&/_\u0001\u0006CB\u0004H.\u001f\u000b\u000b\u0003[\ny'!\u001d\u0002t\u0005U\u0004C\u0001&\u0001\u0011\u0015i\u0006\u00071\u0001J\u0011\u0015\u0001\u0007\u00071\u0001c\u0011\u001dI\u0007\u0007%AA\u0002-D\u0011\"a\u001e1!\u0003\u0005\r!!\u001f\u0002\r=\u0014\u0018nZ5o!\u0011\tY(!\"\u000e\u0005\u0005u$\u0002BA@\u0003\u0003\u000bQ\u0001\u001e:fKNT1!a!;\u0003!\u0019\u0017\r^1msN$\u0018\u0002BAD\u0003{\u0012aa\u0014:jO&t\u0017aD1qa2LH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u00055%fA6\u0002R\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$C'\u0006\u0002\u0002\u0014*\"\u0011\u0011PA)\u0003\u001d)h.\u00199qYf$B!!'\u0002\"B!A\t\\AN!!!\u0015QT%cW\u0006e\u0014bAAP\u000b\n1A+\u001e9mKRB\u0011\"a)4\u0003\u0003\u0005\r!!\u001c\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u00055\u0006\u0003BAX\u0003kk!!!-\u000b\u0007\u0005Mv/\u0001\u0003mC:<\u0017\u0002BA\\\u0003c\u0013aa\u00142kK\u000e$\u0018!C3wC2lu\u000eZ3!+\t\tI(A\u0004pe&<\u0017N\u001c\u0011\u0015\u0015\u00055\u0014\u0011YAb\u0003\u000b\f9\rC\u0003^\u0013\u0001\u0007\u0011\nC\u0003a\u0013\u0001\u0007!\rC\u0004j\u0013A\u0005\t\u0019A6\t\u0013\u0005]\u0014\u0002%AA\u0002\u0005e\u0014!\u00038pe6\fG.\u001b>f)\t\ti'\u0001\u0003d_BLHCCA7\u0003#\f\u0019.!6\u0002X\"9Q,\u0004I\u0001\u0002\u0004I\u0005b\u00021\u000e!\u0003\u0005\rA\u0019\u0005\bS6\u0001\n\u00111\u0001l\u0011%\t9(\u0004I\u0001\u0002\u0004\tI(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005u'fA%\u0002R\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAArU\r\u0011\u0017\u0011K\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAw!\u0011\ty+a<\n\t\u0005E\u0011\u0011W\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003k\u00042\u0001RA|\u0013\r\tI0\u0012\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u007f\u0014)\u0001E\u0002E\u0005\u0003I1Aa\u0001F\u0005\r\te.\u001f\u0005\n\u0005\u000f!\u0012\u0011!a\u0001\u0003k\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B\u0007!\u0019\u0011yA!\u0006\u0002\u00006\u0011!\u0011\u0003\u0006\u0004\u0005')\u0015AC2pY2,7\r^5p]&!!q\u0003B\t\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\tu!1\u0005\t\u0004\t\n}\u0011b\u0001B\u0011\u000b\n9!i\\8mK\u0006t\u0007\"\u0003B\u0004-\u0005\u0005\t\u0019AA\u0000\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u00055(\u0011\u0006\u0005\n\u0005\u000f9\u0012\u0011!a\u0001\u0003k\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003k\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003[\fa!Z9vC2\u001cH\u0003\u0002B\u000f\u0005oA\u0011Ba\u0002\u001b\u0003\u0003\u0005\r!a@"
)
public class Cast implements ColumnNode, Product, Serializable {
   private final ColumnNode child;
   private final DataType dataType;
   private final Option evalMode;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$4() {
      return Cast$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option $lessinit$greater$default$3() {
      return Cast$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final Cast x$0) {
      return Cast$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$4() {
      return Cast$.MODULE$.apply$default$4();
   }

   public static Option apply$default$3() {
      return Cast$.MODULE$.apply$default$3();
   }

   public static Cast apply(final ColumnNode child, final DataType dataType, final Option evalMode, final Origin origin) {
      return Cast$.MODULE$.apply(child, dataType, evalMode, origin);
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

   public DataType dataType() {
      return this.dataType;
   }

   public Option evalMode() {
      return this.evalMode;
   }

   public Origin origin() {
      return this.origin;
   }

   public Cast normalize() {
      ColumnNode x$1 = this.child().normalize();
      Origin x$2 = ColumnNode$.MODULE$.NO_ORIGIN();
      DataType x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$3();
      return this.copy(x$1, x$3, x$4, x$2);
   }

   public String sql() {
      String var10000 = ColumnNode$.MODULE$.optionToSql(this.evalMode());
      return var10000 + "CAST(" + this.child().sql() + " AS " + this.dataType().sql() + ")";
   }

   public Seq children() {
      return (Seq)(new .colon.colon(this.child(), scala.collection.immutable.Nil..MODULE$)).$plus$plus(this.evalMode());
   }

   public Cast copy(final ColumnNode child, final DataType dataType, final Option evalMode, final Origin origin) {
      return new Cast(child, dataType, evalMode, origin);
   }

   public ColumnNode copy$default$1() {
      return this.child();
   }

   public DataType copy$default$2() {
      return this.dataType();
   }

   public Option copy$default$3() {
      return this.evalMode();
   }

   public Origin copy$default$4() {
      return this.origin();
   }

   public String productPrefix() {
      return "Cast";
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
            return this.dataType();
         }
         case 2 -> {
            return this.evalMode();
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
      return x$1 instanceof Cast;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "child";
         }
         case 1 -> {
            return "dataType";
         }
         case 2 -> {
            return "evalMode";
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
            if (x$1 instanceof Cast) {
               label64: {
                  Cast var4 = (Cast)x$1;
                  ColumnNode var10000 = this.child();
                  ColumnNode var5 = var4.child();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label64;
                  }

                  DataType var9 = this.dataType();
                  DataType var6 = var4.dataType();
                  if (var9 == null) {
                     if (var6 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var6)) {
                     break label64;
                  }

                  Option var10 = this.evalMode();
                  Option var7 = var4.evalMode();
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

   public Cast(final ColumnNode child, final DataType dataType, final Option evalMode, final Origin origin) {
      this.child = child;
      this.dataType = dataType;
      this.evalMode = evalMode;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }

   public abstract static class EvalMode implements ColumnNodeLike {
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

      public EvalMode(final String sql) {
         this.sql = sql;
         ColumnNodeLike.$init$(this);
      }
   }

   public static class EvalMode$ {
      public static final EvalMode$ MODULE$ = new EvalMode$();

      public String $lessinit$greater$default$1() {
         return "";
      }
   }

   public static class Legacy$ extends EvalMode {
      public static final Legacy$ MODULE$ = new Legacy$();

      public Legacy$() {
         super(Cast.EvalMode$.MODULE$.$lessinit$greater$default$1());
      }
   }

   public static class Ansi$ extends EvalMode {
      public static final Ansi$ MODULE$ = new Ansi$();

      public Ansi$() {
         super(Cast.EvalMode$.MODULE$.$lessinit$greater$default$1());
      }
   }

   public static class Try$ extends EvalMode {
      public static final Try$ MODULE$ = new Try$();

      public Try$() {
         super("TRY_");
      }
   }
}
