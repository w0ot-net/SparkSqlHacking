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
   bytes = "\u0006\u0005\u0005Ud\u0001\u0002\f\u0018\u0001\u0012B\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0007\")A\n\u0001C\u0001\u001b\"9\u0001\u000bAA\u0001\n\u0003\t\u0006bB*\u0001#\u0003%\t\u0001\u0016\u0005\b?\u0002\t\t\u0011\"\u0011a\u0011\u001dA\u0007!!A\u0005\u0002%Dq!\u001c\u0001\u0002\u0002\u0013\u0005a\u000eC\u0004u\u0001\u0005\u0005I\u0011I;\t\u000fq\u0004\u0011\u0011!C\u0001{\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003\u0017\u0001\u0011\u0011!C!\u0003\u001bA\u0011\"a\u0004\u0001\u0003\u0003%\t%!\u0005\t\u0013\u0005M\u0001!!A\u0005B\u0005Uq!CA\u001d/\u0005\u0005\t\u0012AA\u001e\r!1r#!A\t\u0002\u0005u\u0002B\u0002'\u0011\t\u0003\t)\u0006C\u0005\u0002\u0010A\t\t\u0011\"\u0012\u0002\u0012!I\u0011q\u000b\t\u0002\u0002\u0013\u0005\u0015\u0011\f\u0005\n\u0003;\u0002\u0012\u0011!CA\u0003?B\u0011\"a\u001b\u0011\u0003\u0003%I!!\u001c\u0003!Ik\u0015-\u001b8BaB\u0014Vm]8ve\u000e,'B\u0001\r\u001a\u0003\u0019\u0019XOY7ji*\u0011!dG\u0001\u0004Wb\u001a(B\u0001\u000f\u001e\u0003\u0019!W\r\u001d7ps*\u0011adH\u0001\u0006gB\f'o\u001b\u0006\u0003A\u0005\na!\u00199bG\",'\"\u0001\u0012\u0002\u0007=\u0014xm\u0001\u0001\u0014\r\u0001)3f\f\u001a6!\t1\u0013&D\u0001(\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016(\u0005\u0019\te.\u001f*fMB\u0011A&L\u0007\u0002/%\u0011af\u0006\u0002\u0010\u001b\u0006Lg.\u00119q%\u0016\u001cx.\u001e:dKB\u0011A\u0006M\u0005\u0003c]\u0011aBT8o\u0015Zk%+Z:pkJ\u001cW\r\u0005\u0002'g%\u0011Ag\n\u0002\b!J|G-^2u!\t1dH\u0004\u00028y9\u0011\u0001hO\u0007\u0002s)\u0011!hI\u0001\u0007yI|w\u000e\u001e \n\u0003!J!!P\u0014\u0002\u000fA\f7m[1hK&\u0011q\b\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003{\u001d\nq\u0002\u001d:j[\u0006\u0014\u0018PU3t_V\u00148-Z\u000b\u0002\u0007B\u0011A\t\u0013\b\u0003\u000b\u001a\u0003\"\u0001O\u0014\n\u0005\u001d;\u0013A\u0002)sK\u0012,g-\u0003\u0002J\u0015\n11\u000b\u001e:j]\u001eT!aR\u0014\u0002!A\u0014\u0018.\\1ssJ+7o\\;sG\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002O\u001fB\u0011A\u0006\u0001\u0005\u0006\u0003\u000e\u0001\raQ\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002O%\"9\u0011\t\u0002I\u0001\u0002\u0004\u0019\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002+*\u00121IV\u0016\u0002/B\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001X\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002_3\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005\t\u0007C\u00012h\u001b\u0005\u0019'B\u00013f\u0003\u0011a\u0017M\\4\u000b\u0003\u0019\fAA[1wC&\u0011\u0011jY\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002UB\u0011ae[\u0005\u0003Y\u001e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u001c:\u0011\u0005\u0019\u0002\u0018BA9(\u0005\r\te.\u001f\u0005\bg\"\t\t\u00111\u0001k\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\ta\u000fE\u0002xu>l\u0011\u0001\u001f\u0006\u0003s\u001e\n!bY8mY\u0016\u001cG/[8o\u0013\tY\bP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGc\u0001@\u0002\u0004A\u0011ae`\u0005\u0004\u0003\u00039#a\u0002\"p_2,\u0017M\u001c\u0005\bg*\t\t\u00111\u0001p\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007\u0005\fI\u0001C\u0004t\u0017\u0005\u0005\t\u0019\u00016\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A[\u0001\ti>\u001cFO]5oOR\t\u0011-\u0001\u0004fcV\fGn\u001d\u000b\u0004}\u0006]\u0001bB:\u000f\u0003\u0003\u0005\ra\u001c\u0015\u0004\u0001\u0005m\u0001\u0003BA\u000f\u0003Ci!!a\b\u000b\u0005qk\u0012\u0002BA\u0012\u0003?\u0011aa\u0015;bE2,\u0007f\u0001\u0001\u0002(A!\u0011QDA\u0015\u0013\u0011\tY#a\b\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5)\u000b\u0001\ty#!\u000e\u0011\t\u0005u\u0011\u0011G\u0005\u0005\u0003g\tyBA\u0003TS:\u001cW-\t\u0002\u00028\u0005)!G\f\u001b/a\u0005\u0001\"+T1j]\u0006\u0003\bOU3t_V\u00148-\u001a\t\u0003YA\u0019R\u0001EA \u0003\u0017\u0002b!!\u0011\u0002H\rsUBAA\"\u0015\r\t)eJ\u0001\beVtG/[7f\u0013\u0011\tI%a\u0011\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002N\u0005MSBAA(\u0015\r\t\t&Z\u0001\u0003S>L1aPA()\t\tY$A\u0003baBd\u0017\u0010F\u0002O\u00037BQ!Q\nA\u0002\r\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002b\u0005\u001d\u0004\u0003\u0002\u0014\u0002d\rK1!!\u001a(\u0005\u0019y\u0005\u000f^5p]\"A\u0011\u0011\u000e\u000b\u0002\u0002\u0003\u0007a*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u001c\u0011\u0007\t\f\t(C\u0002\u0002t\r\u0014aa\u00142kK\u000e$\b"
)
public class RMainAppResource implements MainAppResource, NonJVMResource, Product, Serializable {
   private final String primaryResource;

   public static Option unapply(final RMainAppResource x$0) {
      return RMainAppResource$.MODULE$.unapply(x$0);
   }

   public static RMainAppResource apply(final String primaryResource) {
      return RMainAppResource$.MODULE$.apply(primaryResource);
   }

   public static Function1 andThen(final Function1 g) {
      return RMainAppResource$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return RMainAppResource$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String primaryResource() {
      return this.primaryResource;
   }

   public RMainAppResource copy(final String primaryResource) {
      return new RMainAppResource(primaryResource);
   }

   public String copy$default$1() {
      return this.primaryResource();
   }

   public String productPrefix() {
      return "RMainAppResource";
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
      return x$1 instanceof RMainAppResource;
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
            if (x$1 instanceof RMainAppResource) {
               label40: {
                  RMainAppResource var4 = (RMainAppResource)x$1;
                  String var10000 = this.primaryResource();
                  String var5 = var4.primaryResource();
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

   public RMainAppResource(final String primaryResource) {
      this.primaryResource = primaryResource;
      Product.$init$(this);
   }
}
