package org.apache.spark.sql.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]f!B\u0011#\u0001\u0012b\u0003\u0002C$\u0001\u0005+\u0007I\u0011\u0001%\t\u0011=\u0003!\u0011#Q\u0001\n%C\u0001\u0002\u0015\u0001\u0003\u0016\u0004%\t!\u0015\u0005\t-\u0002\u0011\t\u0012)A\u0005%\"Aq\u000b\u0001BK\u0002\u0013\u0005\u0001\f\u0003\u0005`\u0001\tE\t\u0015!\u0003Z\u0011\u0015\u0001\u0007\u0001\"\u0001b\u0011\u00191\u0007\u0001\"\u0011#O\"1Q\u0005\u0001C!E!Da!\u001d\u0001\u0005B\t\u0012\bb\u0002;\u0001\u0003\u0003%\t!\u001e\u0005\bs\u0002\t\n\u0011\"\u0001{\u0011%\tY\u0001AI\u0001\n\u0003\ti\u0001C\u0005\u0002\u0012\u0001\t\n\u0011\"\u0001\u0002\u0014!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0004\u0005\n\u0003S\u0001\u0011\u0011!C\u0001\u0003WA\u0011\"a\r\u0001\u0003\u0003%\t!!\u000e\t\u0013\u0005\u0005\u0003!!A\u0005B\u0005\r\u0003\"CA)\u0001\u0005\u0005I\u0011AA*\u0011%\ti\u0006AA\u0001\n\u0003\ny\u0006C\u0005\u0002d\u0001\t\t\u0011\"\u0011\u0002f!I\u0011q\r\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u000e\u0005\n\u0003W\u0002\u0011\u0011!C!\u0003[:!\"!\u001d#\u0003\u0003E\t\u0001JA:\r%\t#%!A\t\u0002\u0011\n)\b\u0003\u0004a3\u0011\u0005\u0011Q\u0012\u0005\n\u0003OJ\u0012\u0011!C#\u0003SB\u0011\"a$\u001a\u0003\u0003%\t)!%\t\u0013\u0005e\u0015$%A\u0005\u0002\u0005M\u0001\"CAN3\u0005\u0005I\u0011QAO\u0011%\tY+GI\u0001\n\u0003\t\u0019\u0002C\u0005\u0002.f\t\t\u0011\"\u0003\u00020\nQq+\u001b8e_^\u001c\u0006/Z2\u000b\u0005\r\"\u0013\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u00152\u0013aA:rY*\u0011q\u0005K\u0001\u0006gB\f'o\u001b\u0006\u0003S)\na!\u00199bG\",'\"A\u0016\u0002\u0007=\u0014xmE\u0003\u0001[M:$\b\u0005\u0002/c5\tqFC\u00011\u0003\u0015\u00198-\u00197b\u0013\t\u0011tF\u0001\u0004B]f\u0014VM\u001a\t\u0003iUj\u0011AI\u0005\u0003m\t\u0012abQ8mk6tgj\u001c3f\u0019&\\W\r\u0005\u0002/q%\u0011\u0011h\f\u0002\b!J|G-^2u!\tYDI\u0004\u0002=\u0005:\u0011Q(Q\u0007\u0002})\u0011q\bQ\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0001'\u0003\u0002D_\u00059\u0001/Y2lC\u001e,\u0017BA#G\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019u&\u0001\tqCJ$\u0018\u000e^5p]\u000e{G.^7ogV\t\u0011\nE\u0002<\u00152K!a\u0013$\u0003\u0007M+\u0017\u000f\u0005\u00025\u001b&\u0011aJ\t\u0002\u000b\u0007>dW/\u001c8O_\u0012,\u0017!\u00059beRLG/[8o\u0007>dW/\u001c8tA\u0005Y1o\u001c:u\u0007>dW/\u001c8t+\u0005\u0011\u0006cA\u001eK'B\u0011A\u0007V\u0005\u0003+\n\u0012\u0011bU8si>\u0013H-\u001a:\u0002\u0019M|'\u000f^\"pYVlgn\u001d\u0011\u0002\u000b\u0019\u0014\u0018-\\3\u0016\u0003e\u00032A\f.]\u0013\tYvF\u0001\u0004PaRLwN\u001c\t\u0003iuK!A\u0018\u0012\u0003\u0017]Kg\u000eZ8x\rJ\fW.Z\u0001\u0007MJ\fW.\u001a\u0011\u0002\rqJg.\u001b;?)\u0011\u00117\rZ3\u0011\u0005Q\u0002\u0001\"B$\b\u0001\u0004I\u0005\"\u0002)\b\u0001\u0004\u0011\u0006bB,\b!\u0003\u0005\r!W\u0001\n]>\u0014X.\u00197ju\u0016$\u0012AY\u000b\u0002SB\u0011!N\u001c\b\u0003W2\u0004\"!P\u0018\n\u00055|\u0013A\u0002)sK\u0012,g-\u0003\u0002pa\n11\u000b\u001e:j]\u001eT!!\\\u0018\u0002\u0011\rD\u0017\u000e\u001c3sK:,\u0012a\u001d\t\u0004w)\u001b\u0014\u0001B2paf$BA\u0019<xq\"9qi\u0003I\u0001\u0002\u0004I\u0005b\u0002)\f!\u0003\u0005\rA\u0015\u0005\b/.\u0001\n\u00111\u0001Z\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u001f\u0016\u0003\u0013r\\\u0013! \t\u0004}\u0006\u001dQ\"A@\u000b\t\u0005\u0005\u00111A\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u00020\u0003)\tgN\\8uCRLwN\\\u0005\u0004\u0003\u0013y(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA\bU\t\u0011F0\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005U!FA-}\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\u0004\t\u0005\u0003;\t9#\u0004\u0002\u0002 )!\u0011\u0011EA\u0012\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u0015\u0012\u0001\u00026bm\u0006L1a\\A\u0010\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\ti\u0003E\u0002/\u0003_I1!!\r0\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t9$!\u0010\u0011\u00079\nI$C\u0002\u0002<=\u00121!\u00118z\u0011%\ty$EA\u0001\u0002\u0004\ti#A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u000b\u0002b!a\u0012\u0002N\u0005]RBAA%\u0015\r\tYeL\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA(\u0003\u0013\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QKA.!\rq\u0013qK\u0005\u0004\u00033z#a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003\u007f\u0019\u0012\u0011!a\u0001\u0003o\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111DA1\u0011%\ty\u0004FA\u0001\u0002\u0004\ti#\u0001\u0005iCND7i\u001c3f)\t\ti#\u0001\u0005u_N#(/\u001b8h)\t\tY\"\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003+\ny\u0007C\u0005\u0002@]\t\t\u00111\u0001\u00028\u0005Qq+\u001b8e_^\u001c\u0006/Z2\u0011\u0005QJ2#B\r\u0002x\u0005\r\u0005\u0003CA=\u0003\u007fJ%+\u00172\u000e\u0005\u0005m$bAA?_\u00059!/\u001e8uS6,\u0017\u0002BAA\u0003w\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\t))a#\u000e\u0005\u0005\u001d%\u0002BAE\u0003G\t!![8\n\u0007\u0015\u000b9\t\u0006\u0002\u0002t\u0005)\u0011\r\u001d9msR9!-a%\u0002\u0016\u0006]\u0005\"B$\u001d\u0001\u0004I\u0005\"\u0002)\u001d\u0001\u0004\u0011\u0006bB,\u001d!\u0003\u0005\r!W\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u00059QO\\1qa2LH\u0003BAP\u0003O\u0003BA\f.\u0002\"B1a&a)J%fK1!!*0\u0005\u0019!V\u000f\u001d7fg!A\u0011\u0011\u0016\u0010\u0002\u0002\u0003\u0007!-A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001a\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAY!\u0011\ti\"a-\n\t\u0005U\u0016q\u0004\u0002\u0007\u001f\nTWm\u0019;"
)
public class WindowSpec implements ColumnNodeLike, Product, Serializable {
   private final Seq partitionColumns;
   private final Seq sortColumns;
   private final Option frame;

   public static Option $lessinit$greater$default$3() {
      return WindowSpec$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final WindowSpec x$0) {
      return WindowSpec$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$3() {
      return WindowSpec$.MODULE$.apply$default$3();
   }

   public static WindowSpec apply(final Seq partitionColumns, final Seq sortColumns, final Option frame) {
      return WindowSpec$.MODULE$.apply(partitionColumns, sortColumns, frame);
   }

   public static Function1 tupled() {
      return WindowSpec$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return WindowSpec$.MODULE$.curried();
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

   public Seq partitionColumns() {
      return this.partitionColumns;
   }

   public Seq sortColumns() {
      return this.sortColumns;
   }

   public Option frame() {
      return this.frame;
   }

   public WindowSpec normalize() {
      return this.copy(ColumnNode$.MODULE$.normalize(this.partitionColumns()), ColumnNode$.MODULE$.normalize(this.sortColumns()), ColumnNode$.MODULE$.normalize(this.frame()));
   }

   public String sql() {
      Seq parts = new .colon.colon(ColumnNode$.MODULE$.elementsToSql(this.partitionColumns(), "PARTITION BY "), new .colon.colon(ColumnNode$.MODULE$.elementsToSql(this.sortColumns(), "ORDER BY "), new .colon.colon(ColumnNode$.MODULE$.optionToSql(this.frame()), scala.collection.immutable.Nil..MODULE$)));
      return ((IterableOnceOps)parts.filter((x$8) -> BoxesRunTime.boxToBoolean($anonfun$sql$4(x$8)))).mkString(" ");
   }

   public Seq children() {
      return (Seq)((IterableOps)this.partitionColumns().$plus$plus(this.sortColumns())).$plus$plus(this.frame());
   }

   public WindowSpec copy(final Seq partitionColumns, final Seq sortColumns, final Option frame) {
      return new WindowSpec(partitionColumns, sortColumns, frame);
   }

   public Seq copy$default$1() {
      return this.partitionColumns();
   }

   public Seq copy$default$2() {
      return this.sortColumns();
   }

   public Option copy$default$3() {
      return this.frame();
   }

   public String productPrefix() {
      return "WindowSpec";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.partitionColumns();
         }
         case 1 -> {
            return this.sortColumns();
         }
         case 2 -> {
            return this.frame();
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
      return x$1 instanceof WindowSpec;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "partitionColumns";
         }
         case 1 -> {
            return "sortColumns";
         }
         case 2 -> {
            return "frame";
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
            if (x$1 instanceof WindowSpec) {
               label56: {
                  WindowSpec var4 = (WindowSpec)x$1;
                  Seq var10000 = this.partitionColumns();
                  Seq var5 = var4.partitionColumns();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  var10000 = this.sortColumns();
                  Seq var6 = var4.sortColumns();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label56;
                  }

                  Option var9 = this.frame();
                  Option var7 = var4.frame();
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

   // $FF: synthetic method
   public static final boolean $anonfun$sql$4(final String x$8) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$8));
   }

   public WindowSpec(final Seq partitionColumns, final Seq sortColumns, final Option frame) {
      this.partitionColumns = partitionColumns;
      this.sortColumns = sortColumns;
      this.frame = frame;
      ColumnNodeLike.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
