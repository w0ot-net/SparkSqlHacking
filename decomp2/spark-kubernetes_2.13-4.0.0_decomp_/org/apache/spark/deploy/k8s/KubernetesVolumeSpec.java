package org.apache.spark.deploy.k8s;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]f!B\u0013'\u0001*\u0002\u0004\u0002C$\u0001\u0005+\u0007I\u0011\u0001%\t\u0011E\u0003!\u0011#Q\u0001\n%C\u0001B\u0015\u0001\u0003\u0016\u0004%\t\u0001\u0013\u0005\t'\u0002\u0011\t\u0012)A\u0005\u0013\"AA\u000b\u0001BK\u0002\u0013\u0005\u0001\n\u0003\u0005V\u0001\tE\t\u0015!\u0003J\u0011!1\u0006A!f\u0001\n\u0003A\u0005\u0002C,\u0001\u0005#\u0005\u000b\u0011B%\t\u0011a\u0003!Q3A\u0005\u0002eC\u0001\"\u0018\u0001\u0003\u0012\u0003\u0006IA\u0017\u0005\t=\u0002\u0011)\u001a!C\u0001?\"AA\r\u0001B\tB\u0003%\u0001\rC\u0003f\u0001\u0011\u0005a\rC\u0004o\u0001\u0005\u0005I\u0011A8\t\u000fY\u0004\u0011\u0013!C\u0001o\"A\u0011Q\u0001\u0001\u0012\u0002\u0013\u0005q\u000f\u0003\u0005\u0002\b\u0001\t\n\u0011\"\u0001x\u0011!\tI\u0001AI\u0001\n\u00039\b\"CA\u0006\u0001E\u0005I\u0011AA\u0007\u0011%\t\t\u0002AI\u0001\n\u0003\t\u0019\u0002C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0011\u0002\u001a!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00111\u0006\u0005\n\u0003g\u0001\u0011\u0011!C\u0001\u0003kA\u0011\"!\u0011\u0001\u0003\u0003%\t%a\u0011\t\u0013\u0005E\u0003!!A\u0005\u0002\u0005M\u0003\"CA,\u0001\u0005\u0005I\u0011IA-\u0011%\ti\u0006AA\u0001\n\u0003\ny\u0006C\u0005\u0002b\u0001\t\t\u0011\"\u0011\u0002d!I\u0011Q\r\u0001\u0002\u0002\u0013\u0005\u0013qM\u0004\u000b\u0003W2\u0013\u0011!E\u0001U\u00055d!C\u0013'\u0003\u0003E\tAKA8\u0011\u0019)w\u0004\"\u0001\u0002\b\"I\u0011\u0011M\u0010\u0002\u0002\u0013\u0015\u00131\r\u0005\n\u0003\u0013{\u0012\u0011!CA\u0003\u0017C\u0011\"!' \u0003\u0003%\t)a'\t\u0013\u00055v$!A\u0005\n\u0005=&\u0001F&vE\u0016\u0014h.\u001a;fgZ{G.^7f'B,7M\u0003\u0002(Q\u0005\u00191\u000eO:\u000b\u0005%R\u0013A\u00023fa2|\u0017P\u0003\u0002,Y\u0005)1\u000f]1sW*\u0011QFL\u0001\u0007CB\f7\r[3\u000b\u0003=\n1a\u001c:h'\u0011\u0001\u0011g\u000e\u001e\u0011\u0005I*T\"A\u001a\u000b\u0003Q\nQa]2bY\u0006L!AN\u001a\u0003\r\u0005s\u0017PU3g!\t\u0011\u0004(\u0003\u0002:g\t9\u0001K]8ek\u000e$\bCA\u001eE\u001d\ta$I\u0004\u0002>\u00036\taH\u0003\u0002@\u0001\u00061AH]8piz\u001a\u0001!C\u00015\u0013\t\u00195'A\u0004qC\u000e\\\u0017mZ3\n\u0005\u00153%\u0001D*fe&\fG.\u001b>bE2,'BA\"4\u0003)1x\u000e\\;nK:\u000bW.Z\u000b\u0002\u0013B\u0011!J\u0014\b\u0003\u00172\u0003\"!P\u001a\n\u00055\u001b\u0014A\u0002)sK\u0012,g-\u0003\u0002P!\n11\u000b\u001e:j]\u001eT!!T\u001a\u0002\u0017Y|G.^7f\u001d\u0006lW\rI\u0001\n[>,h\u000e\u001e)bi\"\f!\"\\8v]R\u0004\u0016\r\u001e5!\u00031iw.\u001e8u'V\u0014\u0007+\u0019;i\u00035iw.\u001e8u'V\u0014\u0007+\u0019;iA\u0005\u0001Rn\\;oiN+(\rU1uQ\u0016C\bO]\u0001\u0012[>,h\u000e^*vEB\u000bG\u000f[#yaJ\u0004\u0013!D7pk:$(+Z1e\u001f:d\u00170F\u0001[!\t\u00114,\u0003\u0002]g\t9!i\\8mK\u0006t\u0017AD7pk:$(+Z1e\u001f:d\u0017\u0010I\u0001\u000bm>dW/\\3D_:4W#\u00011\u0011\u0005\u0005\u0014W\"\u0001\u0014\n\u0005\r4#\u0001H&vE\u0016\u0014h.\u001a;fgZ{G.^7f'B,7-\u001b4jG\u000e{gNZ\u0001\fm>dW/\\3D_:4\u0007%\u0001\u0004=S:LGO\u0010\u000b\bO\"L'n\u001b7n!\t\t\u0007\u0001C\u0003H\u001b\u0001\u0007\u0011\nC\u0003S\u001b\u0001\u0007\u0011\nC\u0003U\u001b\u0001\u0007\u0011\nC\u0003W\u001b\u0001\u0007\u0011\nC\u0003Y\u001b\u0001\u0007!\fC\u0003_\u001b\u0001\u0007\u0001-\u0001\u0003d_BLHcB4qcJ\u001cH/\u001e\u0005\b\u000f:\u0001\n\u00111\u0001J\u0011\u001d\u0011f\u0002%AA\u0002%Cq\u0001\u0016\b\u0011\u0002\u0003\u0007\u0011\nC\u0004W\u001dA\u0005\t\u0019A%\t\u000fas\u0001\u0013!a\u00015\"9aL\u0004I\u0001\u0002\u0004\u0001\u0017AD2paf$C-\u001a4bk2$H%M\u000b\u0002q*\u0012\u0011*_\u0016\u0002uB\u001910!\u0001\u000e\u0003qT!! @\u0002\u0013Ut7\r[3dW\u0016$'BA@4\u0003)\tgN\\8uCRLwN\\\u0005\u0004\u0003\u0007a(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU*\"!a\u0004+\u0005iK\u0018AD2paf$C-\u001a4bk2$HEN\u000b\u0003\u0003+Q#\u0001Y=\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tY\u0002\u0005\u0003\u0002\u001e\u0005\u001dRBAA\u0010\u0015\u0011\t\t#a\t\u0002\t1\fgn\u001a\u0006\u0003\u0003K\tAA[1wC&\u0019q*a\b\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u00055\u0002c\u0001\u001a\u00020%\u0019\u0011\u0011G\u001a\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005]\u0012Q\b\t\u0004e\u0005e\u0012bAA\u001eg\t\u0019\u0011I\\=\t\u0013\u0005}r#!AA\u0002\u00055\u0012a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002FA1\u0011qIA'\u0003oi!!!\u0013\u000b\u0007\u0005-3'\u0001\u0006d_2dWm\u0019;j_:LA!a\u0014\u0002J\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\rQ\u0016Q\u000b\u0005\n\u0003\u007fI\u0012\u0011!a\u0001\u0003o\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111DA.\u0011%\tyDGA\u0001\u0002\u0004\ti#\u0001\u0005iCND7i\u001c3f)\t\ti#\u0001\u0005u_N#(/\u001b8h)\t\tY\"\u0001\u0004fcV\fGn\u001d\u000b\u00045\u0006%\u0004\"CA ;\u0005\u0005\t\u0019AA\u001c\u0003QYUOY3s]\u0016$Xm\u001d,pYVlWm\u00159fGB\u0011\u0011mH\n\u0006?\u0005E\u0014Q\u0010\t\f\u0003g\nI(S%J\u0013j\u0003w-\u0004\u0002\u0002v)\u0019\u0011qO\u001a\u0002\u000fI,h\u000e^5nK&!\u00111PA;\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\u000e\t\u0005\u0003\u007f\n))\u0004\u0002\u0002\u0002*!\u00111QA\u0012\u0003\tIw.C\u0002F\u0003\u0003#\"!!\u001c\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u001b\u001d\fi)a$\u0002\u0012\u0006M\u0015QSAL\u0011\u00159%\u00051\u0001J\u0011\u0015\u0011&\u00051\u0001J\u0011\u0015!&\u00051\u0001J\u0011\u00151&\u00051\u0001J\u0011\u0015A&\u00051\u0001[\u0011\u0015q&\u00051\u0001a\u0003\u001d)h.\u00199qYf$B!!(\u0002*B)!'a(\u0002$&\u0019\u0011\u0011U\u001a\u0003\r=\u0003H/[8o!%\u0011\u0014QU%J\u0013&S\u0006-C\u0002\u0002(N\u0012a\u0001V;qY\u00164\u0004\u0002CAVG\u0005\u0005\t\u0019A4\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00022B!\u0011QDAZ\u0013\u0011\t),a\b\u0003\r=\u0013'.Z2u\u0001"
)
public class KubernetesVolumeSpec implements Product, Serializable {
   private final String volumeName;
   private final String mountPath;
   private final String mountSubPath;
   private final String mountSubPathExpr;
   private final boolean mountReadOnly;
   private final KubernetesVolumeSpecificConf volumeConf;

   public static Option unapply(final KubernetesVolumeSpec x$0) {
      return KubernetesVolumeSpec$.MODULE$.unapply(x$0);
   }

   public static KubernetesVolumeSpec apply(final String volumeName, final String mountPath, final String mountSubPath, final String mountSubPathExpr, final boolean mountReadOnly, final KubernetesVolumeSpecificConf volumeConf) {
      return KubernetesVolumeSpec$.MODULE$.apply(volumeName, mountPath, mountSubPath, mountSubPathExpr, mountReadOnly, volumeConf);
   }

   public static Function1 tupled() {
      return KubernetesVolumeSpec$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return KubernetesVolumeSpec$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String volumeName() {
      return this.volumeName;
   }

   public String mountPath() {
      return this.mountPath;
   }

   public String mountSubPath() {
      return this.mountSubPath;
   }

   public String mountSubPathExpr() {
      return this.mountSubPathExpr;
   }

   public boolean mountReadOnly() {
      return this.mountReadOnly;
   }

   public KubernetesVolumeSpecificConf volumeConf() {
      return this.volumeConf;
   }

   public KubernetesVolumeSpec copy(final String volumeName, final String mountPath, final String mountSubPath, final String mountSubPathExpr, final boolean mountReadOnly, final KubernetesVolumeSpecificConf volumeConf) {
      return new KubernetesVolumeSpec(volumeName, mountPath, mountSubPath, mountSubPathExpr, mountReadOnly, volumeConf);
   }

   public String copy$default$1() {
      return this.volumeName();
   }

   public String copy$default$2() {
      return this.mountPath();
   }

   public String copy$default$3() {
      return this.mountSubPath();
   }

   public String copy$default$4() {
      return this.mountSubPathExpr();
   }

   public boolean copy$default$5() {
      return this.mountReadOnly();
   }

   public KubernetesVolumeSpecificConf copy$default$6() {
      return this.volumeConf();
   }

   public String productPrefix() {
      return "KubernetesVolumeSpec";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.volumeName();
         }
         case 1 -> {
            return this.mountPath();
         }
         case 2 -> {
            return this.mountSubPath();
         }
         case 3 -> {
            return this.mountSubPathExpr();
         }
         case 4 -> {
            return BoxesRunTime.boxToBoolean(this.mountReadOnly());
         }
         case 5 -> {
            return this.volumeConf();
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
      return x$1 instanceof KubernetesVolumeSpec;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "volumeName";
         }
         case 1 -> {
            return "mountPath";
         }
         case 2 -> {
            return "mountSubPath";
         }
         case 3 -> {
            return "mountSubPathExpr";
         }
         case 4 -> {
            return "mountReadOnly";
         }
         case 5 -> {
            return "volumeConf";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.volumeName()));
      var1 = Statics.mix(var1, Statics.anyHash(this.mountPath()));
      var1 = Statics.mix(var1, Statics.anyHash(this.mountSubPath()));
      var1 = Statics.mix(var1, Statics.anyHash(this.mountSubPathExpr()));
      var1 = Statics.mix(var1, this.mountReadOnly() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.volumeConf()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label83: {
            if (x$1 instanceof KubernetesVolumeSpec) {
               KubernetesVolumeSpec var4 = (KubernetesVolumeSpec)x$1;
               if (this.mountReadOnly() == var4.mountReadOnly()) {
                  label76: {
                     String var10000 = this.volumeName();
                     String var5 = var4.volumeName();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label76;
                     }

                     var10000 = this.mountPath();
                     String var6 = var4.mountPath();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label76;
                     }

                     var10000 = this.mountSubPath();
                     String var7 = var4.mountSubPath();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label76;
                     }

                     var10000 = this.mountSubPathExpr();
                     String var8 = var4.mountSubPathExpr();
                     if (var10000 == null) {
                        if (var8 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var8)) {
                        break label76;
                     }

                     KubernetesVolumeSpecificConf var13 = this.volumeConf();
                     KubernetesVolumeSpecificConf var9 = var4.volumeConf();
                     if (var13 == null) {
                        if (var9 != null) {
                           break label76;
                        }
                     } else if (!var13.equals(var9)) {
                        break label76;
                     }

                     if (var4.canEqual(this)) {
                        break label83;
                     }
                  }
               }
            }

            var14 = false;
            return var14;
         }
      }

      var14 = true;
      return var14;
   }

   public KubernetesVolumeSpec(final String volumeName, final String mountPath, final String mountSubPath, final String mountSubPathExpr, final boolean mountReadOnly, final KubernetesVolumeSpecificConf volumeConf) {
      this.volumeName = volumeName;
      this.mountPath = mountPath;
      this.mountSubPath = mountSubPath;
      this.mountSubPathExpr = mountSubPathExpr;
      this.mountReadOnly = mountReadOnly;
      this.volumeConf = volumeConf;
      Product.$init$(this);
   }
}
