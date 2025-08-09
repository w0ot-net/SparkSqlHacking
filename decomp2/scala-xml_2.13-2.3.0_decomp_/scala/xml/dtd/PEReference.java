package scala.xml.dtd;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.xml.Utility$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%c\u0001\u0002\f\u0018\u0001zA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0001\u000e\u0005\t{\u0001\u0011\t\u0012)A\u0005k!)a\b\u0001C\u0001\u007f!)!\t\u0001C!\u0007\"9!\nAA\u0001\n\u0003Y\u0005bB'\u0001#\u0003%\tA\u0014\u0005\b3\u0002\t\t\u0011\"\u0011[\u0011\u001d\u0011\u0007!!A\u0005\u0002\rDqa\u001a\u0001\u0002\u0002\u0013\u0005\u0001\u000eC\u0004o\u0001\u0005\u0005I\u0011I8\t\u000fY\u0004\u0011\u0011!C\u0001o\"9A\u0010AA\u0001\n\u0003j\b\u0002C@\u0001\u0003\u0003%\t%!\u0001\t\u0013\u0005\r\u0001!!A\u0005B\u0005\u0015q!CA\u0005/\u0005\u0005\t\u0012AA\u0006\r!1r#!A\t\u0002\u00055\u0001B\u0002 \u0011\t\u0003\t)\u0003C\u0005\u0002(A\t\t\u0011\"\u0012\u0002*!I\u00111\u0006\t\u0002\u0002\u0013\u0005\u0015Q\u0006\u0005\n\u0003c\u0001\u0012\u0011!CA\u0003gA\u0011\"a\u0010\u0011\u0003\u0003%I!!\u0011\u0003\u0017A+%+\u001a4fe\u0016t7-\u001a\u0006\u00031e\t1\u0001\u001a;e\u0015\tQ2$A\u0002y[2T\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0011\u0001qdI\u0014\u0011\u0005\u0001\nS\"A\f\n\u0005\t:\"AC'be.,\b\u000fR3dYB\u0011A%J\u0007\u00027%\u0011ae\u0007\u0002\b!J|G-^2u!\tA\u0003G\u0004\u0002*]9\u0011!&L\u0007\u0002W)\u0011A&H\u0001\u0007yI|w\u000e\u001e \n\u0003qI!aL\u000e\u0002\u000fA\f7m[1hK&\u0011\u0011G\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003_m\t1!\u001a8u+\u0005)\u0004C\u0001\u001c;\u001d\t9\u0004\b\u0005\u0002+7%\u0011\u0011hG\u0001\u0007!J,G-\u001a4\n\u0005mb$AB*ue&twM\u0003\u0002:7\u0005!QM\u001c;!\u0003\u0019a\u0014N\\5u}Q\u0011\u0001)\u0011\t\u0003A\u0001AQaM\u0002A\u0002U\n1BY;jY\u0012\u001cFO]5oOR\u0011A\t\u0013\t\u0003\u000b\u001as!\u0001\n\u0018\n\u0005\u001d\u0013$!D*ue&twMQ;jY\u0012,'\u000fC\u0003J\t\u0001\u0007A)\u0001\u0002tE\u0006!1m\u001c9z)\t\u0001E\nC\u00044\u000bA\u0005\t\u0019A\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tqJ\u000b\u00026!.\n\u0011\u000b\u0005\u0002S/6\t1K\u0003\u0002U+\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003-n\t!\"\u00198o_R\fG/[8o\u0013\tA6KA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A.\u0011\u0005q\u000bW\"A/\u000b\u0005y{\u0016\u0001\u00027b]\u001eT\u0011\u0001Y\u0001\u0005U\u00064\u0018-\u0003\u0002<;\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tA\r\u0005\u0002%K&\u0011am\u0007\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003S2\u0004\"\u0001\n6\n\u0005-\\\"aA!os\"9Q.CA\u0001\u0002\u0004!\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001q!\r\tH/[\u0007\u0002e*\u00111oG\u0001\u000bG>dG.Z2uS>t\u0017BA;s\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005a\\\bC\u0001\u0013z\u0013\tQ8DA\u0004C_>dW-\u00198\t\u000f5\\\u0011\u0011!a\u0001S\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\tYf\u0010C\u0004n\u0019\u0005\u0005\t\u0019\u00013\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001Z\u0001\u0007KF,\u0018\r\\:\u0015\u0007a\f9\u0001C\u0004n\u001d\u0005\u0005\t\u0019A5\u0002\u0017A+%+\u001a4fe\u0016t7-\u001a\t\u0003AA\u0019R\u0001EA\b\u00037\u0001b!!\u0005\u0002\u0018U\u0002UBAA\n\u0015\r\t)bG\u0001\beVtG/[7f\u0013\u0011\tI\"a\u0005\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u001e\u0005\rRBAA\u0010\u0015\r\t\tcX\u0001\u0003S>L1!MA\u0010)\t\tY!\u0001\u0005u_N#(/\u001b8h)\u0005Y\u0016!B1qa2LHc\u0001!\u00020!)1g\u0005a\u0001k\u00059QO\\1qa2LH\u0003BA\u001b\u0003w\u0001B\u0001JA\u001ck%\u0019\u0011\u0011H\u000e\u0003\r=\u0003H/[8o\u0011!\ti\u0004FA\u0001\u0002\u0004\u0001\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\t\t\u00049\u0006\u0015\u0013bAA$;\n1qJ\u00196fGR\u0004"
)
public class PEReference extends MarkupDecl implements Product, Serializable {
   private final String ent;

   public static Option unapply(final PEReference x$0) {
      return PEReference$.MODULE$.unapply(x$0);
   }

   public static PEReference apply(final String ent) {
      return PEReference$.MODULE$.apply(ent);
   }

   public static Function1 andThen(final Function1 g) {
      return PEReference$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return PEReference$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String ent() {
      return this.ent;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return sb.append((new java.lang.StringBuilder(2)).append("%").append(this.ent()).append(";").toString());
   }

   public PEReference copy(final String ent) {
      return new PEReference(ent);
   }

   public String copy$default$1() {
      return this.ent();
   }

   public String productPrefix() {
      return "PEReference";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.ent();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof PEReference;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "ent";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof PEReference) {
               label40: {
                  PEReference var4 = (PEReference)x$1;
                  String var10000 = this.ent();
                  String var5 = var4.ent();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public PEReference(final String ent) {
      this.ent = ent;
      Product.$init$(this);
      if (!Utility$.MODULE$.isName(ent)) {
         throw new IllegalArgumentException("ent must be an XML Name");
      }
   }
}
