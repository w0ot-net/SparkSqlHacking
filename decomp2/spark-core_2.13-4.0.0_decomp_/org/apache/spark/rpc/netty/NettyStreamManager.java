package org.apache.spark.rpc.netty;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.spark.network.buffer.FileSegmentManagedBuffer;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.server.StreamManager;
import org.apache.spark.rpc.RpcEnvFileServer;
import org.apache.spark.util.Utils$;
import scala.Array;
import scala.MatchError;
import scala.Predef;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ea!B\t\u0013\u0001Ia\u0002\u0002C\u0015\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\u000b=\u0002A\u0011\u0001\u0019\t\u000fM\u0002!\u0019!C\u0005i!1!\u000b\u0001Q\u0001\nUBqa\u0015\u0001C\u0002\u0013%A\u0007\u0003\u0004U\u0001\u0001\u0006I!\u000e\u0005\b+\u0002\u0011\r\u0011\"\u00035\u0011\u00191\u0006\u0001)A\u0005k!)q\u000b\u0001C!1\")q\f\u0001C!A\")!\r\u0001C!G\")A\u000f\u0001C!k\")q\u000f\u0001C!q\")1\u0010\u0001C!y\")a\u0010\u0001C!\u007f\"9\u0011\u0011\u0002\u0001\u0005B\u0005-!A\u0005(fiRL8\u000b\u001e:fC6l\u0015M\\1hKJT!a\u0005\u000b\u0002\u000b9,G\u000f^=\u000b\u0005U1\u0012a\u0001:qG*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xmE\u0002\u0001;\u0015\u0002\"AH\u0012\u000e\u0003}Q!\u0001I\u0011\u0002\rM,'O^3s\u0015\t\u0011c#A\u0004oKR<xN]6\n\u0005\u0011z\"!D*ue\u0016\fW.T1oC\u001e,'\u000f\u0005\u0002'O5\tA#\u0003\u0002))\t\u0001\"\u000b]2F]Z4\u0015\u000e\\3TKJ4XM]\u0001\u0007eB\u001cWI\u001c<\u0004\u0001A\u0011A&L\u0007\u0002%%\u0011aF\u0005\u0002\f\u001d\u0016$H/\u001f*qG\u0016sg/\u0001\u0004=S:LGO\u0010\u000b\u0003cI\u0002\"\u0001\f\u0001\t\u000b%\u0012\u0001\u0019A\u0016\u0002\u000b\u0019LG.Z:\u0016\u0003U\u0002BAN\u001f@\u00196\tqG\u0003\u00029s\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005iZ\u0014\u0001B;uS2T\u0011\u0001P\u0001\u0005U\u00064\u0018-\u0003\u0002?o\t\t2i\u001c8dkJ\u0014XM\u001c;ICNDW*\u00199\u0011\u0005\u0001KeBA!H!\t\u0011U)D\u0001D\u0015\t!%&\u0001\u0004=e>|GO\u0010\u0006\u0002\r\u0006)1oY1mC&\u0011\u0001*R\u0001\u0007!J,G-\u001a4\n\u0005)[%AB*ue&twM\u0003\u0002I\u000bB\u0011Q\nU\u0007\u0002\u001d*\u0011qjO\u0001\u0003S>L!!\u0015(\u0003\t\u0019KG.Z\u0001\u0007M&dWm\u001d\u0011\u0002\t)\f'o]\u0001\u0006U\u0006\u00148\u000fI\u0001\u0005I&\u00148/A\u0003eSJ\u001c\b%\u0001\u0006sK6|g/\u001a$jY\u0016$\"!W/\u0011\u0005i[V\"A#\n\u0005q+%\u0001B+oSRDQAX\u0005A\u0002}\n1a[3z\u0003%\u0011X-\\8wK*\u000b'\u000f\u0006\u0002ZC\")aL\u0003a\u0001\u007f\u0005Aq-\u001a;DQVt7\u000eF\u0002eU>\u0004\"!\u001a5\u000e\u0003\u0019T!aZ\u0011\u0002\r\t,hMZ3s\u0013\tIgMA\u0007NC:\fw-\u001a3Ck\u001a4WM\u001d\u0005\u0006W.\u0001\r\u0001\\\u0001\tgR\u0014X-Y7JIB\u0011!,\\\u0005\u0003]\u0016\u0013A\u0001T8oO\")\u0001o\u0003a\u0001c\u0006Q1\r[;oW&sG-\u001a=\u0011\u0005i\u0013\u0018BA:F\u0005\rIe\u000e^\u0001\u000b_B,gn\u0015;sK\u0006lGC\u00013w\u0011\u0015YG\u00021\u0001@\u0003\u001d\tG\r\u001a$jY\u0016$\"aP=\t\u000bil\u0001\u0019\u0001'\u0002\t\u0019LG.Z\u0001\u0007C\u0012$'*\u0019:\u0015\u0005}j\b\"\u0002>\u000f\u0001\u0004a\u0015\u0001D1eI\u0012K'/Z2u_JLH#B \u0002\u0002\u0005\u0015\u0001BBA\u0002\u001f\u0001\u0007q(A\u0004cCN,WK]5\t\r\u0005\u001dq\u00021\u0001M\u0003\u0011\u0001\u0018\r\u001e5\u0002)\u0005$G\rR5sK\u000e$xN]=JM\u0006\u00137/\u001a8u)\u0015y\u0014QBA\b\u0011\u0019\t\u0019\u0001\u0005a\u0001\u007f!1\u0011q\u0001\tA\u00021\u0003"
)
public class NettyStreamManager extends StreamManager implements RpcEnvFileServer {
   private final NettyRpcEnv rpcEnv;
   private final ConcurrentHashMap files;
   private final ConcurrentHashMap jars;
   private final ConcurrentHashMap dirs;

   public String validateDirectoryUri(final String baseUri) {
      return RpcEnvFileServer.validateDirectoryUri$(this, baseUri);
   }

   private ConcurrentHashMap files() {
      return this.files;
   }

   private ConcurrentHashMap jars() {
      return this.jars;
   }

   private ConcurrentHashMap dirs() {
      return this.dirs;
   }

   public void removeFile(final String key) {
      this.files().remove(key);
   }

   public void removeJar(final String key) {
      this.jars().remove(key);
   }

   public ManagedBuffer getChunk(final long streamId, final int chunkIndex) {
      throw new UnsupportedOperationException();
   }

   public ManagedBuffer openStream(final String streamId) {
      String[] var5 = .MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(streamId), "/").split("/", 2);
      if (var5 != null) {
         Object var6 = scala.Array..MODULE$.unapplySeq(var5);
         if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var6) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var6)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var6), 2) == 0) {
            File var10000;
            label52: {
               String ftype = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var6), 0);
               String fname = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var6), 1);
               Tuple2 var4 = new Tuple2(ftype, fname);
               String ftype = (String)var4._1();
               String fname = (String)var4._2();
               switch (ftype == null ? 0 : ftype.hashCode()) {
                  case 3254712:
                     if ("jars".equals(ftype)) {
                        var10000 = (File)this.jars().get(fname);
                        break label52;
                     }
                     break;
                  case 97434231:
                     if ("files".equals(ftype)) {
                        var10000 = (File)this.files().get(fname);
                        break label52;
                     }
               }

               File dir = (File)this.dirs().get(ftype);
               scala.Predef..MODULE$.require(dir != null, () -> "Invalid stream URI: " + ftype + " not found.");
               var10000 = new File(dir, fname);
            }

            File file = var10000;
            if (file != null && file.isFile()) {
               return new FileSegmentManagedBuffer(this.rpcEnv.transportConf(), file, 0L, file.length());
            }

            return null;
         }
      }

      throw new MatchError(var5);
   }

   public String addFile(final File file) {
      File existingPath;
      Predef var10000;
      boolean var10001;
      label24: {
         File canonicalFile = file.getCanonicalFile();
         existingPath = (File)this.files().putIfAbsent(file.getName(), canonicalFile);
         var10000 = scala.Predef..MODULE$;
         if (existingPath != null) {
            label23: {
               if (existingPath == null) {
                  if (canonicalFile == null) {
                     break label23;
                  }
               } else if (existingPath.equals(canonicalFile)) {
                  break label23;
               }

               var10001 = false;
               break label24;
            }
         }

         var10001 = true;
      }

      var10000.require(var10001, () -> "File " + file.getName() + " was already registered with a different path (old path = " + existingPath + ", new path = " + file);
      String var5 = this.rpcEnv.address().toSparkURL();
      return var5 + "/files/" + Utils$.MODULE$.encodeFileNameToURIRawPath(file.getName());
   }

   public String addJar(final File file) {
      File existingPath;
      Predef var10000;
      boolean var10001;
      label24: {
         File canonicalFile = file.getCanonicalFile();
         existingPath = (File)this.jars().putIfAbsent(file.getName(), canonicalFile);
         var10000 = scala.Predef..MODULE$;
         if (existingPath != null) {
            label23: {
               if (existingPath == null) {
                  if (canonicalFile == null) {
                     break label23;
                  }
               } else if (existingPath.equals(canonicalFile)) {
                  break label23;
               }

               var10001 = false;
               break label24;
            }
         }

         var10001 = true;
      }

      var10000.require(var10001, () -> "File " + file.getName() + " was already registered with a different path (old path = " + existingPath + ", new path = " + file);
      String var5 = this.rpcEnv.address().toSparkURL();
      return var5 + "/jars/" + Utils$.MODULE$.encodeFileNameToURIRawPath(file.getName());
   }

   public String addDirectory(final String baseUri, final File path) {
      String fixedBaseUri = this.validateDirectoryUri(baseUri);
      scala.Predef..MODULE$.require(this.dirs().putIfAbsent(.MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(fixedBaseUri), "/"), path.getCanonicalFile()) == null, () -> "URI '" + fixedBaseUri + "' already registered.");
      String var10000 = this.rpcEnv.address().toSparkURL();
      return var10000 + fixedBaseUri;
   }

   public String addDirectoryIfAbsent(final String baseUri, final File path) {
      String fixedBaseUri = this.validateDirectoryUri(baseUri);
      this.dirs().putIfAbsent(.MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(fixedBaseUri), "/"), path.getCanonicalFile());
      String var10000 = this.rpcEnv.address().toSparkURL();
      return var10000 + fixedBaseUri;
   }

   public NettyStreamManager(final NettyRpcEnv rpcEnv) {
      this.rpcEnv = rpcEnv;
      RpcEnvFileServer.$init$(this);
      this.files = new ConcurrentHashMap();
      this.jars = new ConcurrentHashMap();
      this.dirs = new ConcurrentHashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
