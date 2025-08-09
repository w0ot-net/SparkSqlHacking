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
   bytes = "\u0006\u0005\u0005=f!B\u0011#\u0001\u0012b\u0003\u0002C$\u0001\u0005+\u0007I\u0011\u0001%\t\u0011%\u0003!\u0011#Q\u0001\nMB\u0001B\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0013\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005g!AA\n\u0001BK\u0002\u0013\u0005S\n\u0003\u0005W\u0001\tE\t\u0015!\u0003O\u0011\u00159\u0006\u0001\"\u0001Y\u0011\u0019i\u0006\u0001\"\u0011#=\")Q\u0005\u0001C!?\"1\u0001\u000e\u0001C!E%Dq\u0001\u001d\u0001\u0002\u0002\u0013\u0005\u0011\u000fC\u0004v\u0001E\u0005I\u0011\u0001<\t\u0011\u0005\r\u0001!%A\u0005\u0002YD\u0011\"!\u0002\u0001#\u0003%\t!a\u0002\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\u000f\u0001\u0005\u0005I\u0011AA\u0010\u0011%\t9\u0003AA\u0001\n\u0003\tI\u0003C\u0005\u00026\u0001\t\t\u0011\"\u0011\u00028!I\u0011Q\t\u0001\u0002\u0002\u0013\u0005\u0011q\t\u0005\n\u0003#\u0002\u0011\u0011!C!\u0003'B\u0011\"a\u0016\u0001\u0003\u0003%\t%!\u0017\t\u0013\u0005m\u0003!!A\u0005B\u0005u\u0003\"CA0\u0001\u0005\u0005I\u0011IA1\u000f)\t)GIA\u0001\u0012\u0003!\u0013q\r\u0004\nC\t\n\t\u0011#\u0001%\u0003SBaaV\r\u0005\u0002\u0005\u0005\u0005\"CA.3\u0005\u0005IQIA/\u0011%\t\u0019)GA\u0001\n\u0003\u000b)\tC\u0005\u0002\u000ef\t\n\u0011\"\u0001\u0002\b!I\u0011qR\r\u0002\u0002\u0013\u0005\u0015\u0011\u0013\u0005\n\u0003GK\u0012\u0013!C\u0001\u0003\u000fA\u0011\"!*\u001a\u0003\u0003%I!a*\u0003-Us'/Z:pYZ,G-\u0012=ue\u0006\u001cGOV1mk\u0016T!a\t\u0013\u0002\u0011%tG/\u001a:oC2T!!\n\u0014\u0002\u0007M\fHN\u0003\u0002(Q\u0005)1\u000f]1sW*\u0011\u0011FK\u0001\u0007CB\f7\r[3\u000b\u0003-\n1a\u001c:h'\u0015\u0001QfM\u001c;!\tq\u0013'D\u00010\u0015\u0005\u0001\u0014!B:dC2\f\u0017B\u0001\u001a0\u0005\u0019\te.\u001f*fMB\u0011A'N\u0007\u0002E%\u0011aG\t\u0002\u000b\u0007>dW/\u001c8O_\u0012,\u0007C\u0001\u00189\u0013\tItFA\u0004Qe>$Wo\u0019;\u0011\u0005m\"eB\u0001\u001fC\u001d\ti\u0014)D\u0001?\u0015\ty\u0004)\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0001\u0014BA\"0\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0012$\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\r{\u0013!B2iS2$W#A\u001a\u0002\r\rD\u0017\u000e\u001c3!\u0003))\u0007\u0010\u001e:bGRLwN\\\u0001\fKb$(/Y2uS>t\u0007%\u0001\u0004pe&<\u0017N\\\u000b\u0002\u001dB\u0011q\nV\u0007\u0002!*\u0011\u0011KU\u0001\u0006iJ,Wm\u001d\u0006\u0003'\u0012\n\u0001bY1uC2L8\u000f^\u0005\u0003+B\u0013aa\u0014:jO&t\u0017aB8sS\u001eLg\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\teS6\f\u0018\t\u0003i\u0001AQaR\u0004A\u0002MBQAS\u0004A\u0002MBq\u0001T\u0004\u0011\u0002\u0003\u0007a*A\u0005o_Jl\u0017\r\\5{KR\t\u0011,F\u0001a!\t\tWM\u0004\u0002cGB\u0011QhL\u0005\u0003I>\na\u0001\u0015:fI\u00164\u0017B\u00014h\u0005\u0019\u0019FO]5oO*\u0011AmL\u0001\tG\"LG\u000e\u001a:f]V\t!\u000eE\u0002<W6L!\u0001\u001c$\u0003\u0007M+\u0017\u000f\u0005\u00025]&\u0011qN\t\u0002\u000f\u0007>dW/\u001c8O_\u0012,G*[6f\u0003\u0011\u0019w\u000e]=\u0015\te\u00138\u000f\u001e\u0005\b\u000f.\u0001\n\u00111\u00014\u0011\u001dQ5\u0002%AA\u0002MBq\u0001T\u0006\u0011\u0002\u0003\u0007a*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003]T#a\r=,\u0003e\u0004\"A_@\u000e\u0003mT!\u0001`?\u0002\u0013Ut7\r[3dW\u0016$'B\u0001@0\u0003)\tgN\\8uCRLwN\\\u0005\u0004\u0003\u0003Y(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003\u0013Q#A\u0014=\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ty\u0001\u0005\u0003\u0002\u0012\u0005mQBAA\n\u0015\u0011\t)\"a\u0006\u0002\t1\fgn\u001a\u0006\u0003\u00033\tAA[1wC&\u0019a-a\u0005\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u0005\u0002c\u0001\u0018\u0002$%\u0019\u0011QE\u0018\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005-\u0012\u0011\u0007\t\u0004]\u00055\u0012bAA\u0018_\t\u0019\u0011I\\=\t\u0013\u0005M\u0012#!AA\u0002\u0005\u0005\u0012a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002:A1\u00111HA!\u0003Wi!!!\u0010\u000b\u0007\u0005}r&\u0001\u0006d_2dWm\u0019;j_:LA!a\u0011\u0002>\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tI%a\u0014\u0011\u00079\nY%C\u0002\u0002N=\u0012qAQ8pY\u0016\fg\u000eC\u0005\u00024M\t\t\u00111\u0001\u0002,\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\ty!!\u0016\t\u0013\u0005MB#!AA\u0002\u0005\u0005\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u0005\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005=\u0011AB3rk\u0006d7\u000f\u0006\u0003\u0002J\u0005\r\u0004\"CA\u001a/\u0005\u0005\t\u0019AA\u0016\u0003Y)fN]3t_24X\rZ#yiJ\f7\r\u001e,bYV,\u0007C\u0001\u001b\u001a'\u0015I\u00121NA<!!\ti'a\u001d4g9KVBAA8\u0015\r\t\thL\u0001\beVtG/[7f\u0013\u0011\t)(a\u001c\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002z\u0005}TBAA>\u0015\u0011\ti(a\u0006\u0002\u0005%|\u0017bA#\u0002|Q\u0011\u0011qM\u0001\u0006CB\u0004H.\u001f\u000b\b3\u0006\u001d\u0015\u0011RAF\u0011\u00159E\u00041\u00014\u0011\u0015QE\u00041\u00014\u0011\u001daE\u0004%AA\u00029\u000bq\"\u00199qYf$C-\u001a4bk2$HeM\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\u0019*a(\u0011\u000b9\n)*!'\n\u0007\u0005]uF\u0001\u0004PaRLwN\u001c\t\u0007]\u0005m5g\r(\n\u0007\u0005uuF\u0001\u0004UkBdWm\r\u0005\t\u0003Cs\u0012\u0011!a\u00013\u0006\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u000b\u0005\u0003\u0002\u0012\u0005-\u0016\u0002BAW\u0003'\u0011aa\u00142kK\u000e$\b"
)
public class UnresolvedExtractValue implements ColumnNode, Product, Serializable {
   private final ColumnNode child;
   private final ColumnNode extraction;
   private final Origin origin;
   private ColumnNode normalized;
   private volatile boolean bitmap$0;

   public static Origin $lessinit$greater$default$3() {
      return UnresolvedExtractValue$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final UnresolvedExtractValue x$0) {
      return UnresolvedExtractValue$.MODULE$.unapply(x$0);
   }

   public static Origin apply$default$3() {
      return UnresolvedExtractValue$.MODULE$.apply$default$3();
   }

   public static UnresolvedExtractValue apply(final ColumnNode child, final ColumnNode extraction, final Origin origin) {
      return UnresolvedExtractValue$.MODULE$.apply(child, extraction, origin);
   }

   public static Function1 tupled() {
      return UnresolvedExtractValue$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return UnresolvedExtractValue$.MODULE$.curried();
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

   public ColumnNode extraction() {
      return this.extraction;
   }

   public Origin origin() {
      return this.origin;
   }

   public UnresolvedExtractValue normalize() {
      return this.copy(this.child().normalize(), this.extraction().normalize(), ColumnNode$.MODULE$.NO_ORIGIN());
   }

   public String sql() {
      String var10000 = this.child().sql();
      return var10000 + "[" + this.extraction().sql() + "]";
   }

   public Seq children() {
      return new .colon.colon(this.child(), new .colon.colon(this.extraction(), scala.collection.immutable.Nil..MODULE$));
   }

   public UnresolvedExtractValue copy(final ColumnNode child, final ColumnNode extraction, final Origin origin) {
      return new UnresolvedExtractValue(child, extraction, origin);
   }

   public ColumnNode copy$default$1() {
      return this.child();
   }

   public ColumnNode copy$default$2() {
      return this.extraction();
   }

   public Origin copy$default$3() {
      return this.origin();
   }

   public String productPrefix() {
      return "UnresolvedExtractValue";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.child();
         }
         case 1 -> {
            return this.extraction();
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
      return x$1 instanceof UnresolvedExtractValue;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "child";
         }
         case 1 -> {
            return "extraction";
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
            if (x$1 instanceof UnresolvedExtractValue) {
               label56: {
                  UnresolvedExtractValue var4 = (UnresolvedExtractValue)x$1;
                  ColumnNode var10000 = this.child();
                  ColumnNode var5 = var4.child();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  var10000 = this.extraction();
                  ColumnNode var6 = var4.extraction();
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

   public UnresolvedExtractValue(final ColumnNode child, final ColumnNode extraction, final Origin origin) {
      this.child = child;
      this.extraction = extraction;
      this.origin = origin;
      ColumnNodeLike.$init$(this);
      ColumnNode.$init$(this);
      Product.$init$(this);
   }
}
