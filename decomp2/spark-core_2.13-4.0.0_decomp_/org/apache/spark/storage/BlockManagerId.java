package org.apache.spark.storage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext$;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.util.Utils$;
import org.sparkproject.guava.cache.LoadingCache;
import scala.Option;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=g\u0001B\u0015+\u0001MB\u0001B\u0011\u0001\u0003\u0002\u0004%Ia\u0011\u0005\t#\u0002\u0011\t\u0019!C\u0005%\"A\u0011\f\u0001B\u0001B\u0003&A\t\u0003\u0005[\u0001\t\u0005\r\u0011\"\u0003D\u0011!Y\u0006A!a\u0001\n\u0013a\u0006\u0002\u00030\u0001\u0005\u0003\u0005\u000b\u0015\u0002#\t\u0011}\u0003!\u00111A\u0005\n\u0001D\u0001\u0002\u001a\u0001\u0003\u0002\u0004%I!\u001a\u0005\tO\u0002\u0011\t\u0011)Q\u0005C\"A\u0001\u000e\u0001BA\u0002\u0013%\u0011\u000e\u0003\u0005n\u0001\t\u0005\r\u0011\"\u0003o\u0011!\u0001\bA!A!B\u0013Q\u0007\"B9\u0001\t\u0013\u0011\b\"B9\u0001\t\u0013I\b\"\u0002>\u0001\t\u0003\u0019\u0005\"B>\u0001\t\u0003\u0019\u0005\"\u0002?\u0001\t\u0003\u0019\u0005\"B?\u0001\t\u0003\u0001\u0007\"\u0002@\u0001\t\u0003I\u0007BB@\u0001\t\u0003\t\t\u0001C\u0004\u0002\n\u0001!\t%a\u0003\t\u000f\u0005]\u0001\u0001\"\u0011\u0002\u001a!9\u0011Q\u0005\u0001\u0005\n\u0005\u001d\u0002bBA\u001d\u0001\u0011\u0005\u00131\b\u0005\b\u0003{\u0001A\u0011IA \u0011\u001d\t\t\u0005\u0001C!\u0003\u0007:\u0001\"!\u0018+\u0011\u0003a\u0013q\f\u0004\bS)B\t\u0001LA1\u0011\u0019\tH\u0004\"\u0001\u0002p!9\u0011\u0011\u000f\u000f\u0005\u0002\u0005M\u0004\"CA@9E\u0005I\u0011AAA\u0011\u001d\t\t\b\bC\u0001\u0003+C\u0011\"!'\u001d\u0005\u0004%\t!a'\t\u0011\u0005UF\u0004)A\u0005\u0003;Cq!a.\u001d\t\u0003\tI\f\u0003\u0006\u0002@r\u0011\r\u0011\"\u0001-\u0003\u0003D\u0001\"a2\u001dA\u0003%\u00111\u0019\u0005\u000b\u0003\u0013d\"\u0019!C\u0001Y\u0005\u0005\u0007\u0002CAf9\u0001\u0006I!a1\t\u0013\u00055G$!A\u0005\n\u0005\u001d\"A\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\n\u001a\u0006\u0003W1\nqa\u001d;pe\u0006<WM\u0003\u0002.]\u0005)1\u000f]1sW*\u0011q\u0006M\u0001\u0007CB\f7\r[3\u000b\u0003E\n1a\u001c:h\u0007\u0001\u00192\u0001\u0001\u001b=!\t)$(D\u00017\u0015\t9\u0004(\u0001\u0003mC:<'\"A\u001d\u0002\t)\fg/Y\u0005\u0003wY\u0012aa\u00142kK\u000e$\bCA\u001fA\u001b\u0005q$BA 9\u0003\tIw.\u0003\u0002B}\tqQ\t\u001f;fe:\fG.\u001b>bE2,\u0017aC3yK\u000e,Ho\u001c:JI~+\u0012\u0001\u0012\t\u0003\u000b:s!A\u0012'\u0011\u0005\u001dSU\"\u0001%\u000b\u0005%\u0013\u0014A\u0002\u001fs_>$hHC\u0001L\u0003\u0015\u00198-\u00197b\u0013\ti%*\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001fB\u0013aa\u0015;sS:<'BA'K\u0003=)\u00070Z2vi>\u0014\u0018\nZ0`I\u0015\fHCA*X!\t!V+D\u0001K\u0013\t1&J\u0001\u0003V]&$\bb\u0002-\u0003\u0003\u0003\u0005\r\u0001R\u0001\u0004q\u0012\n\u0014\u0001D3yK\u000e,Ho\u001c:JI~\u0003\u0013!\u00025pgR|\u0016!\u00035pgR|v\fJ3r)\t\u0019V\fC\u0004Y\u000b\u0005\u0005\t\u0019\u0001#\u0002\r!|7\u000f^0!\u0003\u0015\u0001xN\u001d;`+\u0005\t\u0007C\u0001+c\u0013\t\u0019'JA\u0002J]R\f\u0011\u0002]8si~{F%Z9\u0015\u0005M3\u0007b\u0002-\t\u0003\u0003\u0005\r!Y\u0001\u0007a>\u0014Ho\u0018\u0011\u0002\u001bQ|\u0007o\u001c7pOfLeNZ8`+\u0005Q\u0007c\u0001+l\t&\u0011AN\u0013\u0002\u0007\u001fB$\u0018n\u001c8\u0002#Q|\u0007o\u001c7pOfLeNZ8`?\u0012*\u0017\u000f\u0006\u0002T_\"9\u0001lCA\u0001\u0002\u0004Q\u0017A\u0004;pa>dwnZ=J]\u001a|w\fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000bM,ho\u001e=\u0011\u0005Q\u0004Q\"\u0001\u0016\t\u000b\tk\u0001\u0019\u0001#\t\u000bik\u0001\u0019\u0001#\t\u000b}k\u0001\u0019A1\t\u000b!l\u0001\u0019\u00016\u0015\u0003M\f!\"\u001a=fGV$xN]%e\u0003!Awn\u001d;Q_J$\u0018\u0001\u00025pgR\fA\u0001]8si\u0006aAo\u001c9pY><\u00170\u00138g_\u0006A\u0011n\u001d#sSZ,'/\u0006\u0002\u0002\u0004A\u0019A+!\u0002\n\u0007\u0005\u001d!JA\u0004C_>dW-\u00198\u0002\u001b]\u0014\u0018\u000e^3FqR,'O\\1m)\r\u0019\u0016Q\u0002\u0005\b\u0003\u001f)\u0002\u0019AA\t\u0003\ryW\u000f\u001e\t\u0004{\u0005M\u0011bAA\u000b}\taqJ\u00196fGR|U\u000f\u001e9vi\u0006a!/Z1e\u000bb$XM\u001d8bYR\u00191+a\u0007\t\u000f\u0005ua\u00031\u0001\u0002 \u0005\u0011\u0011N\u001c\t\u0004{\u0005\u0005\u0012bAA\u0012}\tYqJ\u00196fGRLe\u000e];u\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0003QBSaFA\u0016\u0003o\u0001R\u0001VA\u0017\u0003cI1!a\fK\u0005\u0019!\bN]8xgB\u0019Q(a\r\n\u0007\u0005UbHA\u0006J\u001f\u0016C8-\u001a9uS>t7EAA\u0019\u0003!!xn\u0015;sS:<G#\u0001#\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!Y\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\r\u0011Q\t\u0005\b\u0003\u000fR\u0002\u0019AA%\u0003\u0011!\b.\u0019;\u0011\u0007Q\u000bY%C\u0002\u0002N)\u00131!\u00118zQ\r\u0001\u0011\u0011\u000b\t\u0005\u0003'\nI&\u0004\u0002\u0002V)\u0019\u0011q\u000b\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\\\u0005U#\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017A\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\n\u001a\t\u0003ir\u0019R\u0001HA2\u0003S\u00022\u0001VA3\u0013\r\t9G\u0013\u0002\u0007\u0003:L(+\u001a4\u0011\u0007u\nY'C\u0002\u0002ny\u0012AbU3sS\u0006d\u0017N_1cY\u0016$\"!a\u0018\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0013M\f)(!\u001f\u0002|\u0005u\u0004BBA<=\u0001\u0007A)\u0001\u0004fq\u0016\u001c\u0017\n\u001a\u0005\u0006yz\u0001\r\u0001\u0012\u0005\u0006{z\u0001\r!\u0019\u0005\b}z\u0001\n\u00111\u0001k\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\"TCAABU\rQ\u0017QQ\u0016\u0003\u0003\u000f\u0003B!!#\u0002\u00126\u0011\u00111\u0012\u0006\u0005\u0003\u001b\u000by)A\u0005v]\u000eDWmY6fI*\u0019\u0011q\u000b&\n\t\u0005M\u00151\u0012\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,GcA:\u0002\u0018\"9\u0011Q\u0004\u0011A\u0002\u0005}\u0011a\u00052m_\u000e\\W*\u00198bO\u0016\u0014\u0018\nZ\"bG\",WCAAO!\u0019\ty*!-tg6\u0011\u0011\u0011\u0015\u0006\u0005\u0003G\u000b)+A\u0003dC\u000eDWM\u0003\u0003\u0002(\u0006%\u0016AB2p[6|gN\u0003\u0003\u0002,\u00065\u0016AB4p_\u001edWM\u0003\u0002\u00020\u0006\u00191m\\7\n\t\u0005M\u0016\u0011\u0015\u0002\r\u0019>\fG-\u001b8h\u0007\u0006\u001c\u0007.Z\u0001\u0015E2|7m['b]\u0006<WM]%e\u0007\u0006\u001c\u0007.\u001a\u0011\u0002/\u001d,GoQ1dQ\u0016$'\t\\8dW6\u000bg.Y4fe&#GcA:\u0002<\"1\u0011QX\u0012A\u0002M\f!!\u001b3\u00023MCUK\u0012$M\u000b~kUIU$F%~KE)\u0012(U\u0013\u001aKUIU\u000b\u0003\u0003\u0007\u00042!NAc\u0013\tye'\u0001\u000eT\u0011V3e\tT#`\u001b\u0016\u0013v)\u0012*`\u0013\u0012+e\nV%G\u0013\u0016\u0013\u0006%A\nJ\u001dZ\u000bE*\u0013#`\u000bb+5)\u0016+P%~KE)\u0001\u000bJ\u001dZ\u000bE*\u0013#`\u000bb+5)\u0016+P%~KE\tI\u0001\roJLG/\u001a*fa2\f7-\u001a"
)
public class BlockManagerId implements Externalizable {
   private String executorId_;
   private String host_;
   private int port_;
   private Option topologyInfo_;

   public static BlockManagerId getCachedBlockManagerId(final BlockManagerId id) {
      return BlockManagerId$.MODULE$.getCachedBlockManagerId(id);
   }

   public static LoadingCache blockManagerIdCache() {
      return BlockManagerId$.MODULE$.blockManagerIdCache();
   }

   public static BlockManagerId apply(final ObjectInput in) {
      return BlockManagerId$.MODULE$.apply(in);
   }

   public static Option apply$default$4() {
      return BlockManagerId$.MODULE$.apply$default$4();
   }

   public static BlockManagerId apply(final String execId, final String host, final int port, final Option topologyInfo) {
      return BlockManagerId$.MODULE$.apply(execId, host, port, topologyInfo);
   }

   private String executorId_() {
      return this.executorId_;
   }

   private void executorId__$eq(final String x$1) {
      this.executorId_ = x$1;
   }

   private String host_() {
      return this.host_;
   }

   private void host__$eq(final String x$1) {
      this.host_ = x$1;
   }

   private int port_() {
      return this.port_;
   }

   private void port__$eq(final int x$1) {
      this.port_ = x$1;
   }

   private Option topologyInfo_() {
      return this.topologyInfo_;
   }

   private void topologyInfo__$eq(final Option x$1) {
      this.topologyInfo_ = x$1;
   }

   public String executorId() {
      return this.executorId_();
   }

   public String hostPort() {
      Utils$.MODULE$.checkHost(this.host());
      .MODULE$.assert(this.port() > 0);
      String var10000 = this.host();
      return var10000 + ":" + this.port();
   }

   public String host() {
      return this.host_();
   }

   public int port() {
      return this.port_();
   }

   public Option topologyInfo() {
      return this.topologyInfo_();
   }

   public boolean isDriver() {
      boolean var2;
      label23: {
         String var10000 = this.executorId();
         String var1 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public void writeExternal(final ObjectOutput out) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         out.writeUTF(this.executorId_());
         out.writeUTF(this.host_());
         out.writeInt(this.port_());
         out.writeBoolean(this.topologyInfo_().isDefined());
         this.topologyInfo().foreach((x$1) -> {
            $anonfun$writeExternal$2(out, x$1);
            return BoxedUnit.UNIT;
         });
      });
   }

   public void readExternal(final ObjectInput in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.executorId__$eq(in.readUTF());
         this.host__$eq(in.readUTF());
         this.port__$eq(in.readInt());
         boolean isTopologyInfoAvailable = in.readBoolean();
         this.topologyInfo__$eq((Option)(isTopologyInfoAvailable ? scala.Option..MODULE$.apply(in.readUTF()) : scala.None..MODULE$));
      });
   }

   private Object readResolve() throws IOException {
      return BlockManagerId$.MODULE$.getCachedBlockManagerId(this);
   }

   public String toString() {
      String var10000 = this.executorId();
      return "BlockManagerId(" + var10000 + ", " + this.host() + ", " + this.port() + ", " + this.topologyInfo() + ")";
   }

   public int hashCode() {
      return ((this.executorId().hashCode() * 41 + this.host().hashCode()) * 41 + this.port()) * 41 + this.topologyInfo().hashCode();
   }

   public boolean equals(final Object that) {
      if (!(that instanceof BlockManagerId var4)) {
         return false;
      } else {
         boolean var10;
         label52: {
            label45: {
               String var10000 = this.executorId();
               String var5 = var4.executorId();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label45;
                  }
               } else if (!var10000.equals(var5)) {
                  break label45;
               }

               if (this.port() == var4.port()) {
                  label47: {
                     var10000 = this.host();
                     String var6 = var4.host();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label47;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label47;
                     }

                     Option var9 = this.topologyInfo();
                     Option var7 = var4.topologyInfo();
                     if (var9 == null) {
                        if (var7 == null) {
                           break label52;
                        }
                     } else if (var9.equals(var7)) {
                        break label52;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }

         var10 = true;
         return var10;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$writeExternal$2(final ObjectOutput out$1, final String x$1) {
      out$1.writeUTF(x$1);
   }

   public BlockManagerId(final String executorId_, final String host_, final int port_, final Option topologyInfo_) {
      this.executorId_ = executorId_;
      this.host_ = host_;
      this.port_ = port_;
      this.topologyInfo_ = topologyInfo_;
      super();
      if (this.host_() != null) {
         Utils$.MODULE$.checkHost(this.host_());
         .MODULE$.assert(this.port_() > 0);
      }

   }

   public BlockManagerId() {
      this((String)null, (String)null, 0, scala.None..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
