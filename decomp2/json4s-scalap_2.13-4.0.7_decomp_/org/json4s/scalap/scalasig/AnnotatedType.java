package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005I!AQ\b\u0001BK\u0002\u0013\u0005a\b\u0003\u0005F\u0001\tE\t\u0015!\u0003@\u0011\u00151\u0005\u0001\"\u0001H\u0011\u001dY\u0005!!A\u0005\u00021Cqa\u0014\u0001\u0012\u0002\u0013\u0005\u0001\u000bC\u0004\\\u0001E\u0005I\u0011\u0001/\t\u000fy\u0003\u0011\u0011!C!?\"9\u0001\u000eAA\u0001\n\u0003I\u0007b\u00026\u0001\u0003\u0003%\ta\u001b\u0005\bc\u0002\t\t\u0011\"\u0011s\u0011\u001dI\b!!A\u0005\u0002iD\u0001b \u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0001\u0005\n\u0003\u000b\u0001\u0011\u0011!C!\u0003\u000fA\u0011\"!\u0003\u0001\u0003\u0003%\t%a\u0003\t\u0013\u00055\u0001!!A\u0005B\u0005=q!CA\n5\u0005\u0005\t\u0012AA\u000b\r!I\"$!A\t\u0002\u0005]\u0001B\u0002$\u0014\t\u0003\ty\u0003C\u0005\u0002\nM\t\t\u0011\"\u0012\u0002\f!I\u0011\u0011G\n\u0002\u0002\u0013\u0005\u00151\u0007\u0005\n\u0003s\u0019\u0012\u0011!CA\u0003wA\u0011\"!\u0014\u0014\u0003\u0003%I!a\u0014\u0003\u001b\u0005sgn\u001c;bi\u0016$G+\u001f9f\u0015\tYB$\u0001\u0005tG\u0006d\u0017m]5h\u0015\tib$\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u0003?\u0001\naA[:p]R\u001a(\"A\u0011\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001!\u0003F\f\t\u0003K\u0019j\u0011AG\u0005\u0003Oi\u0011A\u0001V=qKB\u0011\u0011\u0006L\u0007\u0002U)\t1&A\u0003tG\u0006d\u0017-\u0003\u0002.U\t9\u0001K]8ek\u000e$\bCA\u00188\u001d\t\u0001TG\u0004\u00022i5\t!G\u0003\u00024E\u00051AH]8pizJ\u0011aK\u0005\u0003m)\nq\u0001]1dW\u0006<W-\u0003\u00029s\ta1+\u001a:jC2L'0\u00192mK*\u0011aGK\u0001\bif\u0004XMU3g+\u0005!\u0013\u0001\u0003;za\u0016\u0014VM\u001a\u0011\u0002\u001d\u0005$HO]5c)J,WMU3ggV\tq\bE\u00020\u0001\nK!!Q\u001d\u0003\t1K7\u000f\u001e\t\u0003S\rK!\u0001\u0012\u0016\u0003\u0007%sG/A\bbiR\u0014\u0018N\u0019+sK\u0016\u0014VMZ:!\u0003\u0019a\u0014N\\5u}Q\u0019\u0001*\u0013&\u0011\u0005\u0015\u0002\u0001\"\u0002\u001e\u0006\u0001\u0004!\u0003\"B\u001f\u0006\u0001\u0004y\u0014\u0001B2paf$2\u0001S'O\u0011\u001dQd\u0001%AA\u0002\u0011Bq!\u0010\u0004\u0011\u0002\u0003\u0007q(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003ES#\u0001\n*,\u0003M\u0003\"\u0001V-\u000e\u0003US!AV,\u0002\u0013Ut7\r[3dW\u0016$'B\u0001-+\u0003)\tgN\\8uCRLwN\\\u0005\u00035V\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012!\u0018\u0016\u0003\u007fI\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00011\u0011\u0005\u00054W\"\u00012\u000b\u0005\r$\u0017\u0001\u00027b]\u001eT\u0011!Z\u0001\u0005U\u00064\u0018-\u0003\u0002hE\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012AQ\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\taw\u000e\u0005\u0002*[&\u0011aN\u000b\u0002\u0004\u0003:L\bb\u00029\f\u0003\u0003\u0005\rAQ\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003M\u00042\u0001^<m\u001b\u0005)(B\u0001<+\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003qV\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u00111P \t\u0003SqL!! \u0016\u0003\u000f\t{w\u000e\\3b]\"9\u0001/DA\u0001\u0002\u0004a\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2\u0001YA\u0002\u0011\u001d\u0001h\"!AA\u0002\t\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u0005\u0006AAo\\*ue&tw\rF\u0001a\u0003\u0019)\u0017/^1mgR\u001910!\u0005\t\u000fA\f\u0012\u0011!a\u0001Y\u0006i\u0011I\u001c8pi\u0006$X\r\u001a+za\u0016\u0004\"!J\n\u0014\u000bM\tI\"!\n\u0011\u000f\u0005m\u0011\u0011\u0005\u0013@\u00116\u0011\u0011Q\u0004\u0006\u0004\u0003?Q\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003G\tiBA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\n\u0002.5\u0011\u0011\u0011\u0006\u0006\u0004\u0003W!\u0017AA5p\u0013\rA\u0014\u0011\u0006\u000b\u0003\u0003+\tQ!\u00199qYf$R\u0001SA\u001b\u0003oAQA\u000f\fA\u0002\u0011BQ!\u0010\fA\u0002}\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002>\u0005%\u0003#B\u0015\u0002@\u0005\r\u0013bAA!U\t1q\n\u001d;j_:\u0004R!KA#I}J1!a\u0012+\u0005\u0019!V\u000f\u001d7fe!A\u00111J\f\u0002\u0002\u0003\u0007\u0001*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0015\u0011\u0007\u0005\f\u0019&C\u0002\u0002V\t\u0014aa\u00142kK\u000e$\b"
)
public class AnnotatedType extends Type implements Product, Serializable {
   private final Type typeRef;
   private final List attribTreeRefs;

   public static Option unapply(final AnnotatedType x$0) {
      return AnnotatedType$.MODULE$.unapply(x$0);
   }

   public static AnnotatedType apply(final Type typeRef, final List attribTreeRefs) {
      return AnnotatedType$.MODULE$.apply(typeRef, attribTreeRefs);
   }

   public static Function1 tupled() {
      return AnnotatedType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return AnnotatedType$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Type typeRef() {
      return this.typeRef;
   }

   public List attribTreeRefs() {
      return this.attribTreeRefs;
   }

   public AnnotatedType copy(final Type typeRef, final List attribTreeRefs) {
      return new AnnotatedType(typeRef, attribTreeRefs);
   }

   public Type copy$default$1() {
      return this.typeRef();
   }

   public List copy$default$2() {
      return this.attribTreeRefs();
   }

   public String productPrefix() {
      return "AnnotatedType";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.typeRef();
            break;
         case 1:
            var10000 = this.attribTreeRefs();
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
      return x$1 instanceof AnnotatedType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "typeRef";
            break;
         case 1:
            var10000 = "attribTreeRefs";
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
            if (x$1 instanceof AnnotatedType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     AnnotatedType var4 = (AnnotatedType)x$1;
                     Type var10000 = this.typeRef();
                     Type var5 = var4.typeRef();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     List var7 = this.attribTreeRefs();
                     List var6 = var4.attribTreeRefs();
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

   public AnnotatedType(final Type typeRef, final List attribTreeRefs) {
      this.typeRef = typeRef;
      this.attribTreeRefs = attribTreeRefs;
      Product.$init$(this);
   }
}
