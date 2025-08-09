package org.json4s.reflect;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd\u0001B\u000e\u001d\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!A\u0001\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005L\u0001\tE\t\u0015!\u0003C\u0011\u0015a\u0005\u0001\"\u0001N\u0011\u001d\t\u0006!!A\u0005\u0002ICq!\u0016\u0001\u0012\u0002\u0013\u0005a\u000bC\u0004b\u0001E\u0005I\u0011\u00012\t\u000f\u0011\u0004\u0011\u0011!C!K\"9a\u000eAA\u0001\n\u0003y\u0007bB:\u0001\u0003\u0003%\t\u0001\u001e\u0005\bo\u0002\t\t\u0011\"\u0011y\u0011!y\b!!A\u0005\u0002\u0005\u0005\u0001\"CA\u0006\u0001\u0005\u0005I\u0011IA\u0007\u0011%\t\t\u0002AA\u0001\n\u0003\n\u0019\u0002C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131D\u0004\n\u0003?a\u0012\u0011!E\u0001\u0003C1\u0001b\u0007\u000f\u0002\u0002#\u0005\u00111\u0005\u0005\u0007\u0019N!\t!a\u000f\t\u0013\u0005U1#!A\u0005F\u0005]\u0001\"CA\u001f'\u0005\u0005I\u0011QA \u0011!\t)eEI\u0001\n\u0003\u0011\u0007\"CA$'\u0005\u0005I\u0011QA%\u0011!\t9fEI\u0001\n\u0003\u0011\u0007\"CA-'\u0005\u0005I\u0011BA.\u0005M\u0001&/[7ji&4X\rR3tGJL\u0007\u000f^8s\u0015\tib$A\u0004sK\u001adWm\u0019;\u000b\u0005}\u0001\u0013A\u00026t_:$4OC\u0001\"\u0003\ry'oZ\u0002\u0001'\u0011\u0001A\u0005\u000b\u0018\u0011\u0005\u00152S\"\u0001\u000f\n\u0005\u001db\"\u0001E(cU\u0016\u001cG\u000fR3tGJL\u0007\u000f^8s!\tIC&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u001d\u0001&o\u001c3vGR\u0004\"aL\u001c\u000f\u0005A*dBA\u00195\u001b\u0005\u0011$BA\u001a#\u0003\u0019a$o\\8u}%\t1&\u0003\u00027U\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001d:\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t1$&A\u0004fe\u0006\u001cXO]3\u0016\u0003q\u0002\"!J\u001f\n\u0005yb\"!C*dC2\fG+\u001f9f\u0003!)'/Y:ve\u0016\u0004\u0013a\u00023fM\u0006,H\u000e^\u000b\u0002\u0005B\u0019\u0011fQ#\n\u0005\u0011S#AB(qi&|g\u000eE\u0002*\r\"K!a\u0012\u0016\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0004CA\u0015J\u0013\tQ%FA\u0002B]f\f\u0001\u0002Z3gCVdG\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00079{\u0005\u000b\u0005\u0002&\u0001!)!(\u0002a\u0001y!9\u0001)\u0002I\u0001\u0002\u0004\u0011\u0015\u0001B2paf$2AT*U\u0011\u001dQd\u0001%AA\u0002qBq\u0001\u0011\u0004\u0011\u0002\u0003\u0007!)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003]S#\u0001\u0010-,\u0003e\u0003\"AW0\u000e\u0003mS!\u0001X/\u0002\u0013Ut7\r[3dW\u0016$'B\u00010+\u0003)\tgN\\8uCRLwN\\\u0005\u0003An\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012a\u0019\u0016\u0003\u0005b\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00014\u0011\u0005\u001ddW\"\u00015\u000b\u0005%T\u0017\u0001\u00027b]\u001eT\u0011a[\u0001\u0005U\u00064\u0018-\u0003\u0002nQ\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012\u0001\u001d\t\u0003SEL!A\u001d\u0016\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005!+\bb\u0002<\f\u0003\u0003\u0005\r\u0001]\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003e\u00042A_?I\u001b\u0005Y(B\u0001?+\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003}n\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111AA\u0005!\rI\u0013QA\u0005\u0004\u0003\u000fQ#a\u0002\"p_2,\u0017M\u001c\u0005\bm6\t\t\u00111\u0001I\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007\u0019\fy\u0001C\u0004w\u001d\u0005\u0005\t\u0019\u00019\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001]\u0001\ti>\u001cFO]5oOR\ta-\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0007\ti\u0002C\u0004w#\u0005\u0005\t\u0019\u0001%\u0002'A\u0013\u0018.\\5uSZ,G)Z:de&\u0004Ho\u001c:\u0011\u0005\u0015\u001a2#B\n\u0002&\u0005E\u0002cBA\u0014\u0003[a$IT\u0007\u0003\u0003SQ1!a\u000b+\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\f\u0002*\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005M\u0012\u0011H\u0007\u0003\u0003kQ1!a\u000ek\u0003\tIw.C\u00029\u0003k!\"!!\t\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b9\u000b\t%a\u0011\t\u000bi2\u0002\u0019\u0001\u001f\t\u000f\u00013\u0002\u0013!a\u0001\u0005\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$#'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u00131\u000b\t\u0005S\r\u000bi\u0005E\u0003*\u0003\u001fb$)C\u0002\u0002R)\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA+1\u0005\u0005\t\u0019\u0001(\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003;\u00022aZA0\u0013\r\t\t\u0007\u001b\u0002\u0007\u001f\nTWm\u0019;"
)
public class PrimitiveDescriptor extends ObjectDescriptor {
   private final ScalaType erasure;
   private final Option default;

   public static Option $lessinit$greater$default$2() {
      return PrimitiveDescriptor$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final PrimitiveDescriptor x$0) {
      return PrimitiveDescriptor$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$2() {
      return PrimitiveDescriptor$.MODULE$.apply$default$2();
   }

   public static PrimitiveDescriptor apply(final ScalaType erasure, final Option default) {
      return PrimitiveDescriptor$.MODULE$.apply(erasure, default);
   }

   public static Function1 tupled() {
      return PrimitiveDescriptor$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return PrimitiveDescriptor$.MODULE$.curried();
   }

   public ScalaType erasure() {
      return this.erasure;
   }

   public Option default() {
      return this.default;
   }

   public PrimitiveDescriptor copy(final ScalaType erasure, final Option default) {
      return new PrimitiveDescriptor(erasure, default);
   }

   public ScalaType copy$default$1() {
      return this.erasure();
   }

   public Option copy$default$2() {
      return this.default();
   }

   public String productPrefix() {
      return "PrimitiveDescriptor";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.erasure();
            break;
         case 1:
            var10000 = this.default();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof PrimitiveDescriptor;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "erasure";
            break;
         case 1:
            var10000 = "default";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof PrimitiveDescriptor) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     PrimitiveDescriptor var4 = (PrimitiveDescriptor)x$1;
                     ScalaType var10000 = this.erasure();
                     ScalaType var5 = var4.erasure();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Option var7 = this.default();
                     Option var6 = var4.default();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public PrimitiveDescriptor(final ScalaType erasure, final Option default) {
      this.erasure = erasure;
      this.default = default;
   }
}
