package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd!B\r\u001b\u0001z!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011%\u0003!\u0011#Q\u0001\n\u0005C\u0001B\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0003\")A\n\u0001C\u0001\u001b\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\n\u0011\"\u0001W\u0011\u001d\u0011\u0007!!A\u0005B\rDqa\u001b\u0001\u0002\u0002\u0013\u0005A\u000eC\u0004q\u0001\u0005\u0005I\u0011A9\t\u000f]\u0004\u0011\u0011!C!q\"Aq\u0010AA\u0001\n\u0003\t\t\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003+\u0001\u0011\u0011!C!\u0003/A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\b\u0015\u0005}!$!A\t\u0002y\t\tCB\u0005\u001a5\u0005\u0005\t\u0012\u0001\u0010\u0002$!1Aj\u0005C\u0001\u0003wA\u0011\"!\u0006\u0014\u0003\u0003%)%a\u0006\t\u0013\u0005u2#!A\u0005\u0002\u0006}\u0002\"CA#'\u0005\u0005I\u0011QA$\u0011%\tIfEA\u0001\n\u0013\tYFA\fLk\n,'O\\3uKNtei\u0015,pYVlWmQ8oM*\u00111\u0004H\u0001\u0004Wb\u001a(BA\u000f\u001f\u0003\u0019!W\r\u001d7ps*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xmE\u0003\u0001K-z#\u0007\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsE\u0001\u0004B]f\u0014VM\u001a\t\u0003Y5j\u0011AG\u0005\u0003]i\u0011AdS;cKJtW\r^3t->dW/\\3Ta\u0016\u001c\u0017NZ5d\u0007>tg\r\u0005\u0002'a%\u0011\u0011g\n\u0002\b!J|G-^2u!\t\u0019DH\u0004\u00025u9\u0011Q'O\u0007\u0002m)\u0011q\u0007O\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0001&\u0003\u0002<O\u00059\u0001/Y2lC\u001e,\u0017BA\u001f?\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tYt%\u0001\u0003qCRDW#A!\u0011\u0005\t3eBA\"E!\t)t%\u0003\u0002FO\u00051\u0001K]3eK\u001aL!a\u0012%\u0003\rM#(/\u001b8h\u0015\t)u%A\u0003qCRD\u0007%\u0001\u0004tKJ4XM]\u0001\bg\u0016\u0014h/\u001a:!\u0003\u0019a\u0014N\\5u}Q\u0019aj\u0014)\u0011\u00051\u0002\u0001\"B \u0006\u0001\u0004\t\u0005\"\u0002&\u0006\u0001\u0004\t\u0015\u0001B2paf$2AT*U\u0011\u001dyd\u0001%AA\u0002\u0005CqA\u0013\u0004\u0011\u0002\u0003\u0007\u0011)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003]S#!\u0011-,\u0003e\u0003\"AW0\u000e\u0003mS!\u0001X/\u0002\u0013Ut7\r[3dW\u0016$'B\u00010(\u0003)\tgN\\8uCRLwN\\\u0005\u0003An\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00013\u0011\u0005\u0015TW\"\u00014\u000b\u0005\u001dD\u0017\u0001\u00027b]\u001eT\u0011![\u0001\u0005U\u00064\u0018-\u0003\u0002HM\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tQ\u000e\u0005\u0002']&\u0011qn\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003eV\u0004\"AJ:\n\u0005Q<#aA!os\"9aoCA\u0001\u0002\u0004i\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001z!\rQXP]\u0007\u0002w*\u0011ApJ\u0001\u000bG>dG.Z2uS>t\u0017B\u0001@|\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\r\u0011\u0011\u0002\t\u0004M\u0005\u0015\u0011bAA\u0004O\t9!i\\8mK\u0006t\u0007b\u0002<\u000e\u0003\u0003\u0005\rA]\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002e\u0003\u001fAqA\u001e\b\u0002\u0002\u0003\u0007Q.\u0001\u0005iCND7i\u001c3f)\u0005i\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0011\fa!Z9vC2\u001cH\u0003BA\u0002\u0003;AqA^\t\u0002\u0002\u0003\u0007!/A\fLk\n,'O\\3uKNtei\u0015,pYVlWmQ8oMB\u0011AfE\n\u0006'\u0005\u0015\u0012\u0011\u0007\t\b\u0003O\ti#Q!O\u001b\t\tICC\u0002\u0002,\u001d\nqA];oi&lW-\u0003\u0003\u00020\u0005%\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u00111GA\u001d\u001b\t\t)DC\u0002\u00028!\f!![8\n\u0007u\n)\u0004\u0006\u0002\u0002\"\u0005)\u0011\r\u001d9msR)a*!\u0011\u0002D!)qH\u0006a\u0001\u0003\")!J\u0006a\u0001\u0003\u00069QO\\1qa2LH\u0003BA%\u0003+\u0002RAJA&\u0003\u001fJ1!!\u0014(\u0005\u0019y\u0005\u000f^5p]B)a%!\u0015B\u0003&\u0019\u00111K\u0014\u0003\rQ+\b\u000f\\33\u0011!\t9fFA\u0001\u0002\u0004q\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\f\t\u0004K\u0006}\u0013bAA1M\n1qJ\u00196fGR\u0004"
)
public class KubernetesNFSVolumeConf implements KubernetesVolumeSpecificConf, Product, Serializable {
   private final String path;
   private final String server;

   public static Option unapply(final KubernetesNFSVolumeConf x$0) {
      return KubernetesNFSVolumeConf$.MODULE$.unapply(x$0);
   }

   public static KubernetesNFSVolumeConf apply(final String path, final String server) {
      return KubernetesNFSVolumeConf$.MODULE$.apply(path, server);
   }

   public static Function1 tupled() {
      return KubernetesNFSVolumeConf$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return KubernetesNFSVolumeConf$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String path() {
      return this.path;
   }

   public String server() {
      return this.server;
   }

   public KubernetesNFSVolumeConf copy(final String path, final String server) {
      return new KubernetesNFSVolumeConf(path, server);
   }

   public String copy$default$1() {
      return this.path();
   }

   public String copy$default$2() {
      return this.server();
   }

   public String productPrefix() {
      return "KubernetesNFSVolumeConf";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.path();
         }
         case 1 -> {
            return this.server();
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
      return x$1 instanceof KubernetesNFSVolumeConf;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "path";
         }
         case 1 -> {
            return "server";
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof KubernetesNFSVolumeConf) {
               label48: {
                  KubernetesNFSVolumeConf var4 = (KubernetesNFSVolumeConf)x$1;
                  String var10000 = this.path();
                  String var5 = var4.path();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.server();
                  String var6 = var4.server();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public KubernetesNFSVolumeConf(final String path, final String server) {
      this.path = path;
      this.server = server;
      Product.$init$(this);
   }
}
