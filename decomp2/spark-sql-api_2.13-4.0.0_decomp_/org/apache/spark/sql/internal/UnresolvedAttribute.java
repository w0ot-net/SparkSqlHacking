package org.apache.spark.sql.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Function1;
import scala.None;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}h!B\u0016-\u0001:2\u0004\u0002C)\u0001\u0005+\u0007I\u0011\u0001*\t\u0011y\u0003!\u0011#Q\u0001\nMC\u0001b\u0018\u0001\u0003\u0016\u0004%\t\u0001\u0019\u0005\tO\u0002\u0011\t\u0012)A\u0005C\"A\u0001\u000e\u0001BK\u0002\u0013\u0005\u0011\u000e\u0003\u0005n\u0001\tE\t\u0015!\u0003k\u0011!q\u0007A!f\u0001\n\u0003z\u0007\u0002\u0003=\u0001\u0005#\u0005\u000b\u0011\u00029\t\u000be\u0004A\u0011\u0001>\t\u0011\u0005\u0005\u0001\u0001\"\u0011-\u0003\u0007Aaa\f\u0001\u0005B\u0005\u0015\u0001\u0002CA\u0004\u0001\u0011\u0005C&!\u0003\t\u0013\u0005M\u0001!!A\u0005\u0002\u0005U\u0001\"CA\u0010\u0001E\u0005I\u0011AA\u0011\u0011%\t9\u0004AI\u0001\n\u0003\tI\u0004C\u0005\u0002>\u0001\t\n\u0011\"\u0001\u0002@!I\u00111\t\u0001\u0012\u0002\u0013\u0005\u0011Q\t\u0005\n\u0003\u0013\u0002\u0011\u0011!C!\u0003\u0017B\u0011\"a\u0017\u0001\u0003\u0003%\t!!\u0018\t\u0013\u0005\u0015\u0004!!A\u0005\u0002\u0005\u001d\u0004\"CA:\u0001\u0005\u0005I\u0011IA;\u0011%\t\u0019\tAA\u0001\n\u0003\t)\tC\u0005\u0002\n\u0002\t\t\u0011\"\u0011\u0002\f\"I\u0011q\u0012\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0013\u0005\n\u0003'\u0003\u0011\u0011!C!\u0003+C\u0011\"a&\u0001\u0003\u0003%\t%!'\b\u0011\u0005uE\u0006#\u0001/\u0003?3qa\u000b\u0017\t\u00029\n\t\u000b\u0003\u0004z9\u0011\u0005\u0011Q\u0016\u0005\b\u0003_cB\u0011AAY\u0011\u001d\ty\u000b\bC\u0001\u0003{Cq!a,\u001d\t\u0003\t)\rC\u0004\u00020r!\t!a3\t\u0013\u0005=F$!A\u0005\u0002\u0006=\u0007\"CAm9E\u0005I\u0011AA\u001d\u0011%\tY\u000eHI\u0001\n\u0003\ty\u0004C\u0005\u0002^r\t\n\u0011\"\u0001\u0002F!I\u0011q\u001c\u000f\u0002\u0002\u0013\u0005\u0015\u0011\u001d\u0005\n\u0003_d\u0012\u0013!C\u0001\u0003sA\u0011\"!=\u001d#\u0003%\t!a\u0010\t\u0013\u0005MH$%A\u0005\u0002\u0005\u0015\u0003\"CA{9\u0005\u0005I\u0011BA|\u0005M)fN]3t_24X\rZ!uiJL'-\u001e;f\u0015\tic&\u0001\u0005j]R,'O\\1m\u0015\ty\u0003'A\u0002tc2T!!\r\u001a\u0002\u000bM\u0004\u0018M]6\u000b\u0005M\"\u0014AB1qC\u000eDWMC\u00016\u0003\ry'oZ\n\u0006\u0001]j\u0014\t\u0012\t\u0003qmj\u0011!\u000f\u0006\u0002u\u0005)1oY1mC&\u0011A(\u000f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005yzT\"\u0001\u0017\n\u0005\u0001c#AC\"pYVlgNT8eKB\u0011\u0001HQ\u0005\u0003\u0007f\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002F\u001d:\u0011a\t\u0014\b\u0003\u000f.k\u0011\u0001\u0013\u0006\u0003\u0013*\u000ba\u0001\u0010:p_Rt4\u0001A\u0005\u0002u%\u0011Q*O\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0005K\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Ns\u0005Ia.Y7f!\u0006\u0014Ho]\u000b\u0002'B\u0019Q\t\u0016,\n\u0005U\u0003&aA*fcB\u0011qk\u0017\b\u00031f\u0003\"aR\u001d\n\u0005iK\u0014A\u0002)sK\u0012,g-\u0003\u0002];\n11\u000b\u001e:j]\u001eT!AW\u001d\u0002\u00159\fW.\u001a)beR\u001c\b%\u0001\u0004qY\u0006t\u0017\nZ\u000b\u0002CB\u0019\u0001H\u00193\n\u0005\rL$AB(qi&|g\u000e\u0005\u00029K&\u0011a-\u000f\u0002\u0005\u0019>tw-A\u0004qY\u0006t\u0017\n\u001a\u0011\u0002!%\u001cX*\u001a;bI\u0006$\u0018mQ8mk6tW#\u00016\u0011\u0005aZ\u0017B\u00017:\u0005\u001d\u0011un\u001c7fC:\f\u0011#[:NKR\fG-\u0019;b\u0007>dW/\u001c8!\u0003\u0019y'/[4j]V\t\u0001\u000f\u0005\u0002rm6\t!O\u0003\u0002ti\u0006)AO]3fg*\u0011QOL\u0001\tG\u0006$\u0018\r\\=ti&\u0011qO\u001d\u0002\u0007\u001fJLw-\u001b8\u0002\u000f=\u0014\u0018nZ5oA\u00051A(\u001b8jiz\"Ra\u001f?~}~\u0004\"A\u0010\u0001\t\u000bEK\u0001\u0019A*\t\u000f}K\u0001\u0013!a\u0001C\"9\u0001.\u0003I\u0001\u0002\u0004Q\u0007b\u00028\n!\u0003\u0005\r\u0001]\u0001\n]>\u0014X.\u00197ju\u0016$\u0012a_\u000b\u0002-\u0006A1\r[5mIJ,g.\u0006\u0002\u0002\fA!Q\tVA\u0007!\rq\u0014qB\u0005\u0004\u0003#a#AD\"pYVlgNT8eK2K7.Z\u0001\u0005G>\u0004\u0018\u0010F\u0005|\u0003/\tI\"a\u0007\u0002\u001e!9\u0011+\u0004I\u0001\u0002\u0004\u0019\u0006bB0\u000e!\u0003\u0005\r!\u0019\u0005\bQ6\u0001\n\u00111\u0001k\u0011\u001dqW\u0002%AA\u0002A\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002$)\u001a1+!\n,\u0005\u0005\u001d\u0002\u0003BA\u0015\u0003gi!!a\u000b\u000b\t\u00055\u0012qF\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\r:\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003k\tYCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002<)\u001a\u0011-!\n\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011\u0011\t\u0016\u0004U\u0006\u0015\u0012AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003\u000fR3\u0001]A\u0013\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\n\t\u0005\u0003\u001f\nI&\u0004\u0002\u0002R)!\u00111KA+\u0003\u0011a\u0017M\\4\u000b\u0005\u0005]\u0013\u0001\u00026bm\u0006L1\u0001XA)\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\ty\u0006E\u00029\u0003CJ1!a\u0019:\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tI'a\u001c\u0011\u0007a\nY'C\u0002\u0002ne\u00121!\u00118z\u0011%\t\t\bFA\u0001\u0002\u0004\ty&A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003o\u0002b!!\u001f\u0002\u0000\u0005%TBAA>\u0015\r\ti(O\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAA\u0003w\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019!.a\"\t\u0013\u0005Ed#!AA\u0002\u0005%\u0014A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0014\u0002\u000e\"I\u0011\u0011O\f\u0002\u0002\u0003\u0007\u0011qL\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011qL\u0001\ti>\u001cFO]5oOR\u0011\u0011QJ\u0001\u0007KF,\u0018\r\\:\u0015\u0007)\fY\nC\u0005\u0002ri\t\t\u00111\u0001\u0002j\u0005\u0019RK\u001c:fg>dg/\u001a3BiR\u0014\u0018NY;uKB\u0011a\bH\n\u00059]\n\u0019\u000b\u0005\u0003\u0002&\u0006-VBAAT\u0015\u0011\tI+!\u0016\u0002\u0005%|\u0017bA(\u0002(R\u0011\u0011qT\u0001\u0006CB\u0004H.\u001f\u000b\nw\u0006M\u0016qWA]\u0003wCa!!.\u001f\u0001\u00041\u0016AE;oa\u0006\u00148/\u001a3JI\u0016tG/\u001b4jKJDQa\u0018\u0010A\u0002\u0005DQ\u0001\u001b\u0010A\u0002)DQA\u001c\u0010A\u0002A$ra_A`\u0003\u0003\f\u0019\r\u0003\u0004\u00026~\u0001\rA\u0016\u0005\u0006?~\u0001\r!\u0019\u0005\u0006Q~\u0001\rA\u001b\u000b\u0006w\u0006\u001d\u0017\u0011\u001a\u0005\u0007\u0003k\u0003\u0003\u0019\u0001,\t\u000b}\u0003\u0003\u0019A1\u0015\u0007m\fi\r\u0003\u0004\u00026\u0006\u0002\rA\u0016\u000b\nw\u0006E\u00171[Ak\u0003/DQ!\u0015\u0012A\u0002MCqa\u0018\u0012\u0011\u0002\u0003\u0007\u0011\rC\u0004iEA\u0005\t\u0019\u00016\t\u000f9\u0014\u0003\u0013!a\u0001a\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$#'A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00134\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\"\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003G\fY\u000f\u0005\u00039E\u0006\u0015\bc\u0002\u001d\u0002hN\u000b'\u000e]\u0005\u0004\u0003SL$A\u0002+va2,G\u0007\u0003\u0005\u0002n\u001a\n\t\u00111\u0001|\u0003\rAH\u0005M\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%i\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011 \t\u0005\u0003\u001f\nY0\u0003\u0003\u0002~\u0006E#AB(cU\u0016\u001cG\u000f"
)
public class UnresolvedAttribute implements ColumnNode, Product, Serializable {
   private final Seq nameParts;
   private final Option planId;
   private final boolean isMetadataColumn;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$4() {
      return UnresolvedAttribute$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean $lessinit$greater$default$3() {
      return UnresolvedAttribute$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return UnresolvedAttribute$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final UnresolvedAttribute x$0) {
      return UnresolvedAttribute$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$4() {
      return UnresolvedAttribute$.MODULE$.apply$default$4();
   }

   public static boolean apply$default$3() {
      return UnresolvedAttribute$.MODULE$.apply$default$3();
   }

   public static Option apply$default$2() {
      return UnresolvedAttribute$.MODULE$.apply$default$2();
   }

   public static UnresolvedAttribute apply(final Seq nameParts, final Option planId, final boolean isMetadataColumn, final Origin origin) {
      return UnresolvedAttribute$.MODULE$.apply(nameParts, planId, isMetadataColumn, origin);
   }

   public static UnresolvedAttribute apply(final String unparsedIdentifier) {
      return UnresolvedAttribute$.MODULE$.apply(unparsedIdentifier);
   }

   public static UnresolvedAttribute apply(final String unparsedIdentifier, final Option planId) {
      return UnresolvedAttribute$.MODULE$.apply(unparsedIdentifier, planId);
   }

   public static UnresolvedAttribute apply(final String unparsedIdentifier, final Option planId, final boolean isMetadataColumn) {
      return UnresolvedAttribute$.MODULE$.apply(unparsedIdentifier, planId, isMetadataColumn);
   }

   public static UnresolvedAttribute apply(final String unparsedIdentifier, final Option planId, final boolean isMetadataColumn, final Origin origin) {
      return UnresolvedAttribute$.MODULE$.apply(unparsedIdentifier, planId, isMetadataColumn, origin);
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

   public Seq nameParts() {
      return this.nameParts;
   }

   public Option planId() {
      return this.planId;
   }

   public boolean isMetadataColumn() {
      return this.isMetadataColumn;
   }

   public Origin origin() {
      return this.origin;
   }

   public UnresolvedAttribute normalize() {
      None x$1 = .MODULE$;
      Origin x$2 = ColumnNode$.MODULE$.NO_ORIGIN();
      Seq x$3 = this.copy$default$1();
      boolean x$4 = this.copy$default$3();
      return this.copy(x$3, x$1, x$4, x$2);
   }

   public String sql() {
      return ((IterableOnceOps)this.nameParts().map((n) -> n.contains(".") ? "`" + n + "`" : n)).mkString(".");
   }

   public Seq children() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public UnresolvedAttribute copy(final Seq nameParts, final Option planId, final boolean isMetadataColumn, final Origin origin) {
      return new UnresolvedAttribute(nameParts, planId, isMetadataColumn, origin);
   }

   public Seq copy$default$1() {
      return this.nameParts();
   }

   public Option copy$default$2() {
      return this.planId();
   }

   public boolean copy$default$3() {
      return this.isMetadataColumn();
   }

   public Origin copy$default$4() {
      return this.origin();
   }

   public String productPrefix() {
      return "UnresolvedAttribute";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.nameParts();
         }
         case 1 -> {
            return this.planId();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.isMetadataColumn());
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
      return x$1 instanceof UnresolvedAttribute;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "nameParts";
         }
         case 1 -> {
            return "planId";
         }
         case 2 -> {
            return "isMetadataColumn";
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
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.nameParts()));
      var1 = Statics.mix(var1, Statics.anyHash(this.planId()));
      var1 = Statics.mix(var1, this.isMetadataColumn() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.origin()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label67: {
            if (x$1 instanceof UnresolvedAttribute) {
               UnresolvedAttribute var4 = (UnresolvedAttribute)x$1;
               if (this.isMetadataColumn() == var4.isMetadataColumn()) {
                  label60: {
                     Seq var10000 = this.nameParts();
                     Seq var5 = var4.nameParts();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label60;
                     }

                     Option var8 = this.planId();
                     Option var6 = var4.planId();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label60;
                        }
                     } else if (!var8.equals(var6)) {
                        break label60;
                     }

                     Origin var9 = this.origin();
                     Origin var7 = var4.origin();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label60;
                        }
                     } else if (!var9.equals(var7)) {
                        break label60;
                     }

                     if (var4.canEqual(this)) {
                        break label67;
                     }
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

   public UnresolvedAttribute(final Seq nameParts, final Option planId, final boolean isMetadataColumn, final Origin origin) {
      this.nameParts = nameParts;
      this.planId = planId;
      this.isMetadataColumn = isMetadataColumn;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
