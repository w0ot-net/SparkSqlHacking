package org.apache.spark.deploy.k8s.submit;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Stable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Stable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ed\u0001\u0002\f\u0018\u0001\u0012B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0001\")A\n\u0001C\u0001\u001b\"9\u0001\u000bAA\u0001\n\u0003\t\u0006bB*\u0001#\u0003%\t\u0001\u0016\u0005\b?\u0002\t\t\u0011\"\u0011a\u0011\u001dA\u0007!!A\u0005\u0002%Dq!\u001c\u0001\u0002\u0002\u0013\u0005a\u000eC\u0004u\u0001\u0005\u0005I\u0011I;\t\u000fq\u0004\u0011\u0011!C\u0001{\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003\u0017\u0001\u0011\u0011!C!\u0003\u001bA\u0011\"a\u0004\u0001\u0003\u0003%\t%!\u0005\t\u0013\u0005M\u0001!!A\u0005B\u0005Uq!CA\u001d/\u0005\u0005\t\u0012AA\u001e\r!1r#!A\t\u0002\u0005u\u0002B\u0002'\u0011\t\u0003\t)\u0006C\u0005\u0002\u0010A\t\t\u0011\"\u0012\u0002\u0012!I\u0011q\u000b\t\u0002\u0002\u0013\u0005\u0015\u0011\f\u0005\n\u0003;\u0002\u0012\u0011!CA\u0003?B\u0011\"a\u001a\u0011\u0003\u0003%I!!\u001b\u0003')\u000bg/Y'bS:\f\u0005\u000f\u001d*fg>,(oY3\u000b\u0005aI\u0012AB:vE6LGO\u0003\u0002\u001b7\u0005\u00191\u000eO:\u000b\u0005qi\u0012A\u00023fa2|\u0017P\u0003\u0002\u001f?\u0005)1\u000f]1sW*\u0011\u0001%I\u0001\u0007CB\f7\r[3\u000b\u0003\t\n1a\u001c:h\u0007\u0001\u0019R\u0001A\u0013,_I\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0007C\u0001\u0017.\u001b\u00059\u0012B\u0001\u0018\u0018\u0005=i\u0015-\u001b8BaB\u0014Vm]8ve\u000e,\u0007C\u0001\u00141\u0013\t\ttEA\u0004Qe>$Wo\u0019;\u0011\u0005MZdB\u0001\u001b:\u001d\t)\u0004(D\u00017\u0015\t94%\u0001\u0004=e>|GOP\u0005\u0002Q%\u0011!hJ\u0001\ba\u0006\u001c7.Y4f\u0013\taTH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002;O\u0005y\u0001O]5nCJL(+Z:pkJ\u001cW-F\u0001A!\r1\u0013iQ\u0005\u0003\u0005\u001e\u0012aa\u00149uS>t\u0007C\u0001#I\u001d\t)e\t\u0005\u00026O%\u0011qiJ\u0001\u0007!J,G-\u001a4\n\u0005%S%AB*ue&twM\u0003\u0002HO\u0005\u0001\u0002O]5nCJL(+Z:pkJ\u001cW\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00059{\u0005C\u0001\u0017\u0001\u0011\u0015q4\u00011\u0001A\u0003\u0011\u0019w\u000e]=\u0015\u00059\u0013\u0006b\u0002 \u0005!\u0003\u0005\r\u0001Q\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005)&F\u0001!WW\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003%)hn\u00195fG.,GM\u0003\u0002]O\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005yK&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u0019\t\u0003E\u001el\u0011a\u0019\u0006\u0003I\u0016\fA\u0001\\1oO*\ta-\u0001\u0003kCZ\f\u0017BA%d\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005Q\u0007C\u0001\u0014l\u0013\tawEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002peB\u0011a\u0005]\u0005\u0003c\u001e\u00121!\u00118z\u0011\u001d\u0019\b\"!AA\u0002)\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001<\u0011\u0007]Tx.D\u0001y\u0015\tIx%\u0001\u0006d_2dWm\u0019;j_:L!a\u001f=\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004}\u0006\r\u0001C\u0001\u0014\u0000\u0013\r\t\ta\n\u0002\b\u0005>|G.Z1o\u0011\u001d\u0019(\"!AA\u0002=\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u0011-!\u0003\t\u000fM\\\u0011\u0011!a\u0001U\u0006A\u0001.Y:i\u0007>$W\rF\u0001k\u0003!!xn\u0015;sS:<G#A1\u0002\r\u0015\fX/\u00197t)\rq\u0018q\u0003\u0005\bg:\t\t\u00111\u0001pQ\r\u0001\u00111\u0004\t\u0005\u0003;\t\t#\u0004\u0002\u0002 )\u0011A,H\u0005\u0005\u0003G\tyB\u0001\u0004Ti\u0006\u0014G.\u001a\u0015\u0004\u0001\u0005\u001d\u0002\u0003BA\u000f\u0003SIA!a\u000b\u0002 \taA)\u001a<fY>\u0004XM]!qS\"*\u0001!a\f\u00026A!\u0011QDA\u0019\u0013\u0011\t\u0019$a\b\u0003\u000bMKgnY3\"\u0005\u0005]\u0012!B\u001a/a9\u0002\u0014a\u0005&bm\u0006l\u0015-\u001b8BaB\u0014Vm]8ve\u000e,\u0007C\u0001\u0017\u0011'\u0015\u0001\u0012qHA&!\u0019\t\t%a\u0012A\u001d6\u0011\u00111\t\u0006\u0004\u0003\u000b:\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u0013\n\u0019EA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!\u0014\u0002T5\u0011\u0011q\n\u0006\u0004\u0003#*\u0017AA5p\u0013\ra\u0014q\n\u000b\u0003\u0003w\tQ!\u00199qYf$2ATA.\u0011\u0015q4\u00031\u0001A\u0003\u001d)h.\u00199qYf$B!!\u0019\u0002dA\u0019a%\u0011!\t\u0011\u0005\u0015D#!AA\u00029\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tY\u0007E\u0002c\u0003[J1!a\u001cd\u0005\u0019y%M[3di\u0002"
)
public class JavaMainAppResource implements MainAppResource, Product, Serializable {
   private final Option primaryResource;

   public static Option unapply(final JavaMainAppResource x$0) {
      return JavaMainAppResource$.MODULE$.unapply(x$0);
   }

   public static JavaMainAppResource apply(final Option primaryResource) {
      return JavaMainAppResource$.MODULE$.apply(primaryResource);
   }

   public static Function1 andThen(final Function1 g) {
      return JavaMainAppResource$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JavaMainAppResource$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Option primaryResource() {
      return this.primaryResource;
   }

   public JavaMainAppResource copy(final Option primaryResource) {
      return new JavaMainAppResource(primaryResource);
   }

   public Option copy$default$1() {
      return this.primaryResource();
   }

   public String productPrefix() {
      return "JavaMainAppResource";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.primaryResource();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof JavaMainAppResource;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "primaryResource";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof JavaMainAppResource) {
               label40: {
                  JavaMainAppResource var4 = (JavaMainAppResource)x$1;
                  Option var10000 = this.primaryResource();
                  Option var5 = var4.primaryResource();
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

   public JavaMainAppResource(final Option primaryResource) {
      this.primaryResource = primaryResource;
      Product.$init$(this);
   }
}
