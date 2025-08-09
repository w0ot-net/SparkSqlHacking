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
   bytes = "\u0006\u0005\u0005\u0015h!\u0002\u0014(\u0001&\n\u0004\u0002\u0003'\u0001\u0005+\u0007I\u0011A'\t\u00119\u0003!\u0011#Q\u0001\naB\u0001b\u0014\u0001\u0003\u0016\u0004%\t\u0001\u0015\u0005\t9\u0002\u0011\t\u0012)A\u0005#\"AQ\f\u0001BK\u0002\u0013\u0005a\f\u0003\u0005i\u0001\tE\t\u0015!\u0003`\u0011!I\u0007A!f\u0001\n\u0003R\u0007\u0002C:\u0001\u0005#\u0005\u000b\u0011B6\t\u000bQ\u0004A\u0011A;\t\rm\u0004A\u0011I\u0014}\u0011\u0015Q\u0003\u0001\"\u0011~\u0011\u0019q\b\u0001\"\u0011(\u007f\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00111\u0002\u0005\n\u0003+\u0001\u0011\u0013!C\u0001\u0003/A\u0011\"!\f\u0001#\u0003%\t!a\f\t\u0013\u0005M\u0002!%A\u0005\u0002\u0005U\u0002\"CA\u001d\u0001E\u0005I\u0011AA\u001e\u0011%\ty\u0004AA\u0001\n\u0003\n\t\u0005C\u0005\u0002R\u0001\t\t\u0011\"\u0001\u0002T!I\u00111\f\u0001\u0002\u0002\u0013\u0005\u0011Q\f\u0005\n\u0003S\u0002\u0011\u0011!C!\u0003WB\u0011\"!\u001f\u0001\u0003\u0003%\t!a\u001f\t\u0013\u0005\u0015\u0005!!A\u0005B\u0005\u001d\u0005\"CAF\u0001\u0005\u0005I\u0011IAG\u0011%\ty\tAA\u0001\n\u0003\n\t\nC\u0005\u0002\u0014\u0002\t\t\u0011\"\u0011\u0002\u0016\u001eQ\u0011\u0011T\u0014\u0002\u0002#\u0005\u0011&a'\u0007\u0013\u0019:\u0013\u0011!E\u0001S\u0005u\u0005B\u0002;\u001d\t\u0003\t)\fC\u0005\u0002\u0010r\t\t\u0011\"\u0012\u0002\u0012\"I\u0011q\u0017\u000f\u0002\u0002\u0013\u0005\u0015\u0011\u0018\u0005\n\u0003\u0007d\u0012\u0013!C\u0001\u0003kA\u0011\"!2\u001d#\u0003%\t!a\u000f\t\u0013\u0005\u001dG$!A\u0005\u0002\u0006%\u0007\"CAl9E\u0005I\u0011AA\u001b\u0011%\tI\u000eHI\u0001\n\u0003\tY\u0004C\u0005\u0002\\r\t\t\u0011\"\u0003\u0002^\n)\u0011\t\\5bg*\u0011\u0001&K\u0001\tS:$XM\u001d8bY*\u0011!fK\u0001\u0004gFd'B\u0001\u0017.\u0003\u0015\u0019\b/\u0019:l\u0015\tqs&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002a\u0005\u0019qN]4\u0014\u000b\u0001\u0011\u0004\bP \u0011\u0005M2T\"\u0001\u001b\u000b\u0003U\nQa]2bY\u0006L!a\u000e\u001b\u0003\r\u0005s\u0017PU3g!\tI$(D\u0001(\u0013\tYtE\u0001\u0006D_2,XN\u001c(pI\u0016\u0004\"aM\u001f\n\u0005y\"$a\u0002)s_\u0012,8\r\u001e\t\u0003\u0001&s!!Q$\u000f\u0005\t3U\"A\"\u000b\u0005\u0011+\u0015A\u0002\u001fs_>$hh\u0001\u0001\n\u0003UJ!\u0001\u0013\u001b\u0002\u000fA\f7m[1hK&\u0011!j\u0013\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0011R\nQa\u00195jY\u0012,\u0012\u0001O\u0001\u0007G\"LG\u000e\u001a\u0011\u0002\t9\fW.Z\u000b\u0002#B\u0019\u0001I\u0015+\n\u0005M[%aA*fcB\u0011Q+\u0017\b\u0003-^\u0003\"A\u0011\u001b\n\u0005a#\u0014A\u0002)sK\u0012,g-\u0003\u0002[7\n11\u000b\u001e:j]\u001eT!\u0001\u0017\u001b\u0002\u000b9\fW.\u001a\u0011\u0002\u00115,G/\u00193bi\u0006,\u0012a\u0018\t\u0004g\u0001\u0014\u0017BA15\u0005\u0019y\u0005\u000f^5p]B\u00111MZ\u0007\u0002I*\u0011Q-K\u0001\u0006if\u0004Xm]\u0005\u0003O\u0012\u0014\u0001\"T3uC\u0012\fG/Y\u0001\n[\u0016$\u0018\rZ1uC\u0002\naa\u001c:jO&tW#A6\u0011\u00051\fX\"A7\u000b\u00059|\u0017!\u0002;sK\u0016\u001c(B\u00019*\u0003!\u0019\u0017\r^1msN$\u0018B\u0001:n\u0005\u0019y%/[4j]\u00069qN]5hS:\u0004\u0013A\u0002\u001fj]&$h\bF\u0003wobL(\u0010\u0005\u0002:\u0001!)A*\u0003a\u0001q!)q*\u0003a\u0001#\"9Q,\u0003I\u0001\u0002\u0004y\u0006bB5\n!\u0003\u0005\ra[\u0001\n]>\u0014X.\u00197ju\u0016$\u0012A^\u000b\u0002)\u0006A1\r[5mIJ,g.\u0006\u0002\u0002\u0002A!\u0001IUA\u0002!\rI\u0014QA\u0005\u0004\u0003\u000f9#AD\"pYVlgNT8eK2K7.Z\u0001\u0005G>\u0004\u0018\u0010F\u0005w\u0003\u001b\ty!!\u0005\u0002\u0014!9A*\u0004I\u0001\u0002\u0004A\u0004bB(\u000e!\u0003\u0005\r!\u0015\u0005\b;6\u0001\n\u00111\u0001`\u0011\u001dIW\u0002%AA\u0002-\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u001a)\u001a\u0001(a\u0007,\u0005\u0005u\u0001\u0003BA\u0010\u0003Si!!!\t\u000b\t\u0005\r\u0012QE\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\n5\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003W\t\tCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u00022)\u001a\u0011+a\u0007\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011q\u0007\u0016\u0004?\u0006m\u0011AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003{Q3a[A\u000e\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\t\t\u0005\u0003\u000b\ny%\u0004\u0002\u0002H)!\u0011\u0011JA&\u0003\u0011a\u0017M\\4\u000b\u0005\u00055\u0013\u0001\u00026bm\u0006L1AWA$\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t)\u0006E\u00024\u0003/J1!!\u00175\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\ty&!\u001a\u0011\u0007M\n\t'C\u0002\u0002dQ\u00121!\u00118z\u0011%\t9\u0007FA\u0001\u0002\u0004\t)&A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003[\u0002b!a\u001c\u0002v\u0005}SBAA9\u0015\r\t\u0019\bN\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA<\u0003c\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QPAB!\r\u0019\u0014qP\u0005\u0004\u0003\u0003#$a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003O2\u0012\u0011!a\u0001\u0003?\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111IAE\u0011%\t9gFA\u0001\u0002\u0004\t)&\u0001\u0005iCND7i\u001c3f)\t\t)&\u0001\u0005u_N#(/\u001b8h)\t\t\u0019%\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003{\n9\nC\u0005\u0002hi\t\t\u00111\u0001\u0002`\u0005)\u0011\t\\5bgB\u0011\u0011\bH\n\u00069\u0005}\u00151\u0016\t\n\u0003C\u000b9\u000bO)`WZl!!a)\u000b\u0007\u0005\u0015F'A\u0004sk:$\u0018.\\3\n\t\u0005%\u00161\u0015\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:$\u0004\u0003BAW\u0003gk!!a,\u000b\t\u0005E\u00161J\u0001\u0003S>L1ASAX)\t\tY*A\u0003baBd\u0017\u0010F\u0005w\u0003w\u000bi,a0\u0002B\")Aj\ba\u0001q!)qj\ba\u0001#\"9Ql\bI\u0001\u0002\u0004y\u0006bB5 !\u0003\u0005\ra[\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$C'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u00171\u001b\t\u0005g\u0001\fi\rE\u00044\u0003\u001fD\u0014kX6\n\u0007\u0005EGG\u0001\u0004UkBdW\r\u000e\u0005\t\u0003+\u0014\u0013\u0011!a\u0001m\u0006\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%i\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u001c\t\u0005\u0003\u000b\n\t/\u0003\u0003\u0002d\u0006\u001d#AB(cU\u0016\u001cG\u000f"
)
public class Alias implements ColumnNode, Product, Serializable {
   private final ColumnNode child;
   private final Seq name;
   private final Option metadata;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$4() {
      return Alias$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option $lessinit$greater$default$3() {
      return Alias$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final Alias x$0) {
      return Alias$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$4() {
      return Alias$.MODULE$.apply$default$4();
   }

   public static Option apply$default$3() {
      return Alias$.MODULE$.apply$default$3();
   }

   public static Alias apply(final ColumnNode child, final Seq name, final Option metadata, final Origin origin) {
      return Alias$.MODULE$.apply(child, name, metadata, origin);
   }

   public static Function1 tupled() {
      return Alias$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Alias$.MODULE$.curried();
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

   public Seq name() {
      return this.name;
   }

   public Option metadata() {
      return this.metadata;
   }

   public Origin origin() {
      return this.origin;
   }

   public Alias normalize() {
      ColumnNode x$1 = this.child().normalize();
      Origin x$2 = ColumnNode$.MODULE$.NO_ORIGIN();
      Seq x$3 = this.copy$default$2();
      Option x$4 = this.copy$default$3();
      return this.copy(x$1, x$3, x$4, x$2);
   }

   public String sql() {
      String var10000;
      label18: {
         Seq var3 = this.name();
         if (var3 != null) {
            SeqOps var4 = .MODULE$.Seq().unapplySeq(var3);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var4) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 1) == 0) {
               String single = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 0);
               var10000 = single;
               break label18;
            }
         }

         var10000 = ColumnNode$.MODULE$.textArgumentsToSql(var3);
      }

      String alias = var10000;
      var10000 = this.child().sql();
      return var10000 + " AS " + alias;
   }

   public Seq children() {
      return new scala.collection.immutable..colon.colon(this.child(), scala.collection.immutable.Nil..MODULE$);
   }

   public Alias copy(final ColumnNode child, final Seq name, final Option metadata, final Origin origin) {
      return new Alias(child, name, metadata, origin);
   }

   public ColumnNode copy$default$1() {
      return this.child();
   }

   public Seq copy$default$2() {
      return this.name();
   }

   public Option copy$default$3() {
      return this.metadata();
   }

   public Origin copy$default$4() {
      return this.origin();
   }

   public String productPrefix() {
      return "Alias";
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
            return this.name();
         }
         case 2 -> {
            return this.metadata();
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
      return x$1 instanceof Alias;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "child";
         }
         case 1 -> {
            return "name";
         }
         case 2 -> {
            return "metadata";
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
            if (x$1 instanceof Alias) {
               label64: {
                  Alias var4 = (Alias)x$1;
                  ColumnNode var10000 = this.child();
                  ColumnNode var5 = var4.child();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label64;
                  }

                  Seq var9 = this.name();
                  Seq var6 = var4.name();
                  if (var9 == null) {
                     if (var6 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var6)) {
                     break label64;
                  }

                  Option var10 = this.metadata();
                  Option var7 = var4.metadata();
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

   public Alias(final ColumnNode child, final Seq name, final Option metadata, final Origin origin) {
      this.child = child;
      this.name = name;
      this.metadata = metadata;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
