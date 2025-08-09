package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Function1;
import scala.None;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rg!B\u0012%\u0001\u001ar\u0003\u0002C%\u0001\u0005+\u0007I\u0011\u0001&\t\u0011M\u0003!\u0011#Q\u0001\n-C\u0001\u0002\u0016\u0001\u0003\u0016\u0004%\t!\u0016\u0005\t9\u0002\u0011\t\u0012)A\u0005-\"AQ\f\u0001BK\u0002\u0013\u0005c\f\u0003\u0005h\u0001\tE\t\u0015!\u0003`\u0011\u0015A\u0007\u0001\"\u0001j\u0011\u0019q\u0007\u0001\"\u0011%_\")q\u0005\u0001C!\u0015\"1\u0001\u000f\u0001C!IEDq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0001E\u0005I\u0011\u0001@\t\u0013\u0005M\u0001!%A\u0005\u0002\u0005U\u0001\"CA\r\u0001E\u0005I\u0011AA\u000e\u0011%\ty\u0002AA\u0001\n\u0003\n\t\u0003C\u0005\u00022\u0001\t\t\u0011\"\u0001\u00024!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0011Q\b\u0005\n\u0003\u0013\u0002\u0011\u0011!C!\u0003\u0017B\u0011\"!\u0017\u0001\u0003\u0003%\t!a\u0017\t\u0013\u0005\u0015\u0004!!A\u0005B\u0005\u001d\u0004\"CA6\u0001\u0005\u0005I\u0011IA7\u0011%\ty\u0007AA\u0001\n\u0003\n\t\bC\u0005\u0002t\u0001\t\t\u0011\"\u0011\u0002v\u001dQ\u0011\u0011\u0010\u0013\u0002\u0002#\u0005a%a\u001f\u0007\u0013\r\"\u0013\u0011!E\u0001M\u0005u\u0004B\u00025\u001a\t\u0003\t)\nC\u0005\u0002pe\t\t\u0011\"\u0012\u0002r!I\u0011qS\r\u0002\u0002\u0013\u0005\u0015\u0011\u0014\u0005\n\u0003CK\u0012\u0013!C\u0001\u0003+A\u0011\"a)\u001a#\u0003%\t!a\u0007\t\u0013\u0005\u0015\u0016$!A\u0005\u0002\u0006\u001d\u0006\"CA[3E\u0005I\u0011AA\u000b\u0011%\t9,GI\u0001\n\u0003\tY\u0002C\u0005\u0002:f\t\t\u0011\"\u0003\u0002<\nyQK\u001c:fg>dg/\u001a3SK\u001e,\u0007P\u0003\u0002&M\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002(Q\u0005\u00191/\u001d7\u000b\u0005%R\u0013!B:qCJ\\'BA\u0016-\u0003\u0019\t\u0007/Y2iK*\tQ&A\u0002pe\u001e\u001cR\u0001A\u00186sq\u0002\"\u0001M\u001a\u000e\u0003ER\u0011AM\u0001\u0006g\u000e\fG.Y\u0005\u0003iE\u0012a!\u00118z%\u00164\u0007C\u0001\u001c8\u001b\u0005!\u0013B\u0001\u001d%\u0005)\u0019u\u000e\\;n]:{G-\u001a\t\u0003aiJ!aO\u0019\u0003\u000fA\u0013x\u000eZ;diB\u0011QH\u0012\b\u0003}\u0011s!aP\"\u000e\u0003\u0001S!!\u0011\"\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AM\u0005\u0003\u000bF\nq\u0001]1dW\u0006<W-\u0003\u0002H\u0011\na1+\u001a:jC2L'0\u00192mK*\u0011Q)M\u0001\u0006e\u0016<W\r_\u000b\u0002\u0017B\u0011A\n\u0015\b\u0003\u001b:\u0003\"aP\u0019\n\u0005=\u000b\u0014A\u0002)sK\u0012,g-\u0003\u0002R%\n11\u000b\u001e:j]\u001eT!aT\u0019\u0002\rI,w-\u001a=!\u0003\u0019\u0001H.\u00198JIV\ta\u000bE\u00021/fK!\u0001W\u0019\u0003\r=\u0003H/[8o!\t\u0001$,\u0003\u0002\\c\t!Aj\u001c8h\u0003\u001d\u0001H.\u00198JI\u0002\naa\u001c:jO&tW#A0\u0011\u0005\u0001,W\"A1\u000b\u0005\t\u001c\u0017!\u0002;sK\u0016\u001c(B\u00013'\u0003!\u0019\u0017\r^1msN$\u0018B\u00014b\u0005\u0019y%/[4j]\u00069qN]5hS:\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003kW2l\u0007C\u0001\u001c\u0001\u0011\u0015Iu\u00011\u0001L\u0011\u001d!v\u0001%AA\u0002YCq!X\u0004\u0011\u0002\u0003\u0007q,A\u0005o_Jl\u0017\r\\5{KR\t!.\u0001\u0005dQ&dGM]3o+\u0005\u0011\bcA\u001ftk&\u0011A\u000f\u0013\u0002\u0004'\u0016\f\bC\u0001\u001cw\u0013\t9HE\u0001\bD_2,XN\u001c(pI\u0016d\u0015n[3\u0002\t\r|\u0007/\u001f\u000b\u0005Uj\\H\u0010C\u0004J\u0017A\u0005\t\u0019A&\t\u000fQ[\u0001\u0013!a\u0001-\"9Ql\u0003I\u0001\u0002\u0004y\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u007f*\u001a1*!\u0001,\u0005\u0005\r\u0001\u0003BA\u0003\u0003\u001fi!!a\u0002\u000b\t\u0005%\u00111B\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u00042\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003#\t9AA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002\u0018)\u001aa+!\u0001\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011Q\u0004\u0016\u0004?\u0006\u0005\u0011!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002$A!\u0011QEA\u0018\u001b\t\t9C\u0003\u0003\u0002*\u0005-\u0012\u0001\u00027b]\u001eT!!!\f\u0002\t)\fg/Y\u0005\u0004#\u0006\u001d\u0012\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u001b!\r\u0001\u0014qG\u0005\u0004\u0003s\t$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA \u0003\u000b\u00022\u0001MA!\u0013\r\t\u0019%\r\u0002\u0004\u0003:L\b\"CA$#\u0005\u0005\t\u0019AA\u001b\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\n\t\u0007\u0003\u001f\n)&a\u0010\u000e\u0005\u0005E#bAA*c\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005]\u0013\u0011\u000b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002^\u0005\r\u0004c\u0001\u0019\u0002`%\u0019\u0011\u0011M\u0019\u0003\u000f\t{w\u000e\\3b]\"I\u0011qI\n\u0002\u0002\u0003\u0007\u0011qH\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002$\u0005%\u0004\"CA$)\u0005\u0005\t\u0019AA\u001b\u0003!A\u0017m\u001d5D_\u0012,GCAA\u001b\u0003!!xn\u0015;sS:<GCAA\u0012\u0003\u0019)\u0017/^1mgR!\u0011QLA<\u0011%\t9eFA\u0001\u0002\u0004\ty$A\bV]J,7o\u001c7wK\u0012\u0014VmZ3y!\t1\u0014dE\u0003\u001a\u0003\u007f\nY\t\u0005\u0005\u0002\u0002\u0006\u001d5JV0k\u001b\t\t\u0019IC\u0002\u0002\u0006F\nqA];oi&lW-\u0003\u0003\u0002\n\u0006\r%!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogA!\u0011QRAJ\u001b\t\tyI\u0003\u0003\u0002\u0012\u0006-\u0012AA5p\u0013\r9\u0015q\u0012\u000b\u0003\u0003w\nQ!\u00199qYf$rA[AN\u0003;\u000by\nC\u0003J9\u0001\u00071\nC\u0004U9A\u0005\t\u0019\u0001,\t\u000fuc\u0002\u0013!a\u0001?\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$#'A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00134\u0003\u001d)h.\u00199qYf$B!!+\u00022B!\u0001gVAV!\u0019\u0001\u0014QV&W?&\u0019\u0011qV\u0019\u0003\rQ+\b\u000f\\34\u0011!\t\u0019lHA\u0001\u0002\u0004Q\u0017a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001a\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA_!\u0011\t)#a0\n\t\u0005\u0005\u0017q\u0005\u0002\u0007\u001f\nTWm\u0019;"
)
public class UnresolvedRegex implements ColumnNode, Product, Serializable {
   private final String regex;
   private final Option planId;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$3() {
      return UnresolvedRegex$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return UnresolvedRegex$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final UnresolvedRegex x$0) {
      return UnresolvedRegex$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$3() {
      return UnresolvedRegex$.MODULE$.apply$default$3();
   }

   public static Option apply$default$2() {
      return UnresolvedRegex$.MODULE$.apply$default$2();
   }

   public static UnresolvedRegex apply(final String regex, final Option planId, final Origin origin) {
      return UnresolvedRegex$.MODULE$.apply(regex, planId, origin);
   }

   public static Function1 tupled() {
      return UnresolvedRegex$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return UnresolvedRegex$.MODULE$.curried();
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

   public String regex() {
      return this.regex;
   }

   public Option planId() {
      return this.planId;
   }

   public Origin origin() {
      return this.origin;
   }

   public UnresolvedRegex normalize() {
      None x$1 = .MODULE$;
      Origin x$2 = ColumnNode$.MODULE$.NO_ORIGIN();
      String x$3 = this.copy$default$1();
      return this.copy(x$3, x$1, x$2);
   }

   public String sql() {
      return this.regex();
   }

   public Seq children() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public UnresolvedRegex copy(final String regex, final Option planId, final Origin origin) {
      return new UnresolvedRegex(regex, planId, origin);
   }

   public String copy$default$1() {
      return this.regex();
   }

   public Option copy$default$2() {
      return this.planId();
   }

   public Origin copy$default$3() {
      return this.origin();
   }

   public String productPrefix() {
      return "UnresolvedRegex";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.regex();
         }
         case 1 -> {
            return this.planId();
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
      return x$1 instanceof UnresolvedRegex;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "regex";
         }
         case 1 -> {
            return "planId";
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
            if (x$1 instanceof UnresolvedRegex) {
               label56: {
                  UnresolvedRegex var4 = (UnresolvedRegex)x$1;
                  String var10000 = this.regex();
                  String var5 = var4.regex();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  Option var8 = this.planId();
                  Option var6 = var4.planId();
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

   public UnresolvedRegex(final String regex, final Option planId, final Origin origin) {
      this.regex = regex;
      this.planId = planId;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
