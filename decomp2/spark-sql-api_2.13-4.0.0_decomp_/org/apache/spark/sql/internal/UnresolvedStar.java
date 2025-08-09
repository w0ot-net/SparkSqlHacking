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
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg!B\u0012%\u0001\u001ar\u0003\u0002C%\u0001\u0005+\u0007I\u0011\u0001&\t\u0011Y\u0003!\u0011#Q\u0001\n-C\u0001b\u0016\u0001\u0003\u0016\u0004%\t\u0001\u0017\u0005\t;\u0002\u0011\t\u0012)A\u00053\"Aa\f\u0001BK\u0002\u0013\u0005s\f\u0003\u0005i\u0001\tE\t\u0015!\u0003a\u0011\u0015I\u0007\u0001\"\u0001k\u0011\u0019y\u0007\u0001\"\u0011%a\")q\u0005\u0001C!c\"1!\u000f\u0001C!IMDqA\u001f\u0001\u0002\u0002\u0013\u00051\u0010\u0003\u0005\u0000\u0001E\u0005I\u0011AA\u0001\u0011%\t9\u0002AI\u0001\n\u0003\tI\u0002C\u0005\u0002\u001e\u0001\t\n\u0011\"\u0001\u0002 !I\u00111\u0005\u0001\u0002\u0002\u0013\u0005\u0013Q\u0005\u0005\n\u0003k\u0001\u0011\u0011!C\u0001\u0003oA\u0011\"a\u0010\u0001\u0003\u0003%\t!!\u0011\t\u0013\u00055\u0003!!A\u0005B\u0005=\u0003\"CA/\u0001\u0005\u0005I\u0011AA0\u0011%\tI\u0007AA\u0001\n\u0003\nY\u0007C\u0005\u0002p\u0001\t\t\u0011\"\u0011\u0002r!I\u00111\u000f\u0001\u0002\u0002\u0013\u0005\u0013Q\u000f\u0005\n\u0003o\u0002\u0011\u0011!C!\u0003s:!\"! %\u0003\u0003E\tAJA@\r%\u0019C%!A\t\u0002\u0019\n\t\t\u0003\u0004j3\u0011\u0005\u0011\u0011\u0014\u0005\n\u0003gJ\u0012\u0011!C#\u0003kB\u0011\"a'\u001a\u0003\u0003%\t)!(\t\u0013\u0005\u0015\u0016$%A\u0005\u0002\u0005e\u0001\"CAT3E\u0005I\u0011AA\u0010\u0011%\tI+GA\u0001\n\u0003\u000bY\u000bC\u0005\u0002:f\t\n\u0011\"\u0001\u0002\u001a!I\u00111X\r\u0012\u0002\u0013\u0005\u0011q\u0004\u0005\n\u0003{K\u0012\u0011!C\u0005\u0003\u007f\u0013a\"\u00168sKN|GN^3e'R\f'O\u0003\u0002&M\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002(Q\u0005\u00191/\u001d7\u000b\u0005%R\u0013!B:qCJ\\'BA\u0016-\u0003\u0019\t\u0007/Y2iK*\tQ&A\u0002pe\u001e\u001cR\u0001A\u00186sq\u0002\"\u0001M\u001a\u000e\u0003ER\u0011AM\u0001\u0006g\u000e\fG.Y\u0005\u0003iE\u0012a!\u00118z%\u00164\u0007C\u0001\u001c8\u001b\u0005!\u0013B\u0001\u001d%\u0005)\u0019u\u000e\\;n]:{G-\u001a\t\u0003aiJ!aO\u0019\u0003\u000fA\u0013x\u000eZ;diB\u0011QH\u0012\b\u0003}\u0011s!aP\"\u000e\u0003\u0001S!!\u0011\"\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AM\u0005\u0003\u000bF\nq\u0001]1dW\u0006<W-\u0003\u0002H\u0011\na1+\u001a:jC2L'0\u00192mK*\u0011Q)M\u0001\u000fk:\u0004\u0018M]:fIR\u000b'oZ3u+\u0005Y\u0005c\u0001\u0019M\u001d&\u0011Q*\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005=\u001bfB\u0001)R!\ty\u0014'\u0003\u0002Sc\u00051\u0001K]3eK\u001aL!\u0001V+\u0003\rM#(/\u001b8h\u0015\t\u0011\u0016'A\bv]B\f'o]3e)\u0006\u0014x-\u001a;!\u0003\u0019\u0001H.\u00198JIV\t\u0011\fE\u00021\u0019j\u0003\"\u0001M.\n\u0005q\u000b$\u0001\u0002'p]\u001e\fq\u0001\u001d7b]&#\u0007%\u0001\u0004pe&<\u0017N\\\u000b\u0002AB\u0011\u0011MZ\u0007\u0002E*\u00111\rZ\u0001\u0006iJ,Wm\u001d\u0006\u0003K\u001a\n\u0001bY1uC2L8\u000f^\u0005\u0003O\n\u0014aa\u0014:jO&t\u0017aB8sS\u001eLg\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\t-dWN\u001c\t\u0003m\u0001AQ!S\u0004A\u0002-CqaV\u0004\u0011\u0002\u0003\u0007\u0011\fC\u0004_\u000fA\u0005\t\u0019\u00011\u0002\u00139|'/\\1mSj,G#A6\u0016\u00039\u000b\u0001b\u00195jY\u0012\u0014XM\\\u000b\u0002iB\u0019Q(^<\n\u0005YD%aA*fcB\u0011a\u0007_\u0005\u0003s\u0012\u0012abQ8mk6tgj\u001c3f\u0019&\\W-\u0001\u0003d_BLH\u0003B6}{zDq!S\u0006\u0011\u0002\u0003\u00071\nC\u0004X\u0017A\u0005\t\u0019A-\t\u000fy[\u0001\u0013!a\u0001A\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\u0002U\rY\u0015QA\u0016\u0003\u0003\u000f\u0001B!!\u0003\u0002\u00145\u0011\u00111\u0002\u0006\u0005\u0003\u001b\ty!A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011C\u0019\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0016\u0005-!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA\u000eU\rI\u0016QA\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\t\tCK\u0002a\u0003\u000b\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0014!\u0011\tI#a\r\u000e\u0005\u0005-\"\u0002BA\u0017\u0003_\tA\u0001\\1oO*\u0011\u0011\u0011G\u0001\u0005U\u00064\u0018-C\u0002U\u0003W\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u000f\u0011\u0007A\nY$C\u0002\u0002>E\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0011\u0002JA\u0019\u0001'!\u0012\n\u0007\u0005\u001d\u0013GA\u0002B]fD\u0011\"a\u0013\u0012\u0003\u0003\u0005\r!!\u000f\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\t\u0006\u0005\u0004\u0002T\u0005e\u00131I\u0007\u0003\u0003+R1!a\u00162\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u00037\n)F\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA1\u0003O\u00022\u0001MA2\u0013\r\t)'\r\u0002\b\u0005>|G.Z1o\u0011%\tYeEA\u0001\u0002\u0004\t\u0019%\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0014\u0003[B\u0011\"a\u0013\u0015\u0003\u0003\u0005\r!!\u000f\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u000f\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\n\u0002\r\u0015\fX/\u00197t)\u0011\t\t'a\u001f\t\u0013\u0005-s#!AA\u0002\u0005\r\u0013AD+oe\u0016\u001cx\u000e\u001c<fIN#\u0018M\u001d\t\u0003me\u0019R!GAB\u0003\u001f\u0003\u0002\"!\"\u0002\f.K\u0006m[\u0007\u0003\u0003\u000fS1!!#2\u0003\u001d\u0011XO\u001c;j[\u0016LA!!$\u0002\b\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005E\u0015qS\u0007\u0003\u0003'SA!!&\u00020\u0005\u0011\u0011n\\\u0005\u0004\u000f\u0006MECAA@\u0003\u0015\t\u0007\u000f\u001d7z)\u001dY\u0017qTAQ\u0003GCQ!\u0013\u000fA\u0002-Cqa\u0016\u000f\u0011\u0002\u0003\u0007\u0011\fC\u0004_9A\u0005\t\u0019\u00011\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uII\nq\"\u00199qYf$C-\u001a4bk2$HeM\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti+!.\u0011\tAb\u0015q\u0016\t\u0007a\u0005E6*\u00171\n\u0007\u0005M\u0016G\u0001\u0004UkBdWm\r\u0005\t\u0003o{\u0012\u0011!a\u0001W\u0006\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%g\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u0019\t\u0005\u0003S\t\u0019-\u0003\u0003\u0002F\u0006-\"AB(cU\u0016\u001cG\u000f"
)
public class UnresolvedStar implements ColumnNode, Product, Serializable {
   private final Option unparsedTarget;
   private final Option planId;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$3() {
      return UnresolvedStar$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return UnresolvedStar$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final UnresolvedStar x$0) {
      return UnresolvedStar$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$3() {
      return UnresolvedStar$.MODULE$.apply$default$3();
   }

   public static Option apply$default$2() {
      return UnresolvedStar$.MODULE$.apply$default$2();
   }

   public static UnresolvedStar apply(final Option unparsedTarget, final Option planId, final Origin origin) {
      return UnresolvedStar$.MODULE$.apply(unparsedTarget, planId, origin);
   }

   public static Function1 tupled() {
      return UnresolvedStar$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return UnresolvedStar$.MODULE$.curried();
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

   public Option unparsedTarget() {
      return this.unparsedTarget;
   }

   public Option planId() {
      return this.planId;
   }

   public Origin origin() {
      return this.origin;
   }

   public UnresolvedStar normalize() {
      None x$1 = .MODULE$;
      Origin x$2 = ColumnNode$.MODULE$.NO_ORIGIN();
      Option x$3 = this.copy$default$1();
      return this.copy(x$3, x$1, x$2);
   }

   public String sql() {
      return (String)this.unparsedTarget().map((x$7) -> x$7 + ".*").getOrElse(() -> "*");
   }

   public Seq children() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public UnresolvedStar copy(final Option unparsedTarget, final Option planId, final Origin origin) {
      return new UnresolvedStar(unparsedTarget, planId, origin);
   }

   public Option copy$default$1() {
      return this.unparsedTarget();
   }

   public Option copy$default$2() {
      return this.planId();
   }

   public Origin copy$default$3() {
      return this.origin();
   }

   public String productPrefix() {
      return "UnresolvedStar";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.unparsedTarget();
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
      return x$1 instanceof UnresolvedStar;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "unparsedTarget";
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
            if (x$1 instanceof UnresolvedStar) {
               label56: {
                  UnresolvedStar var4 = (UnresolvedStar)x$1;
                  Option var10000 = this.unparsedTarget();
                  Option var5 = var4.unparsedTarget();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  var10000 = this.planId();
                  Option var6 = var4.planId();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var6)) {
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

   public UnresolvedStar(final Option unparsedTarget, final Option planId, final Origin origin) {
      this.unparsedTarget = unparsedTarget;
      this.planId = planId;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
