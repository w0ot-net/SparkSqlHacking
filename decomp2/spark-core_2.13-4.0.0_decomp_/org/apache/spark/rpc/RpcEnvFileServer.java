package org.apache.spark.rpc;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import scala.Predef;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0005\u0005\u00063\u0001!\ta\u0007\u0005\u0006?\u00011\t\u0001\t\u0005\u0006m\u00011\ta\u000e\u0005\u0006s\u00011\tA\u000f\u0005\u0006\u007f\u00011\t\u0001\u0011\u0005\u0006\u0007\u0002!\t\u0002\u0012\u0005\u0006\r\u00021\ta\u0012\u0005\u0006\u0015\u00021\ta\u0013\u0002\u0011%B\u001cWI\u001c<GS2,7+\u001a:wKJT!a\u0003\u0007\u0002\u0007I\u00048M\u0003\u0002\u000e\u001d\u0005)1\u000f]1sW*\u0011q\u0002E\u0001\u0007CB\f7\r[3\u000b\u0003E\t1a\u001c:h'\t\u00011\u0003\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tA\u0004\u0005\u0002\u0015;%\u0011a$\u0006\u0002\u0005+:LG/A\u0004bI\u00124\u0015\u000e\\3\u0015\u0005\u0005b\u0003C\u0001\u0012*\u001d\t\u0019s\u0005\u0005\u0002%+5\tQE\u0003\u0002'5\u00051AH]8pizJ!\u0001K\u000b\u0002\rA\u0013X\rZ3g\u0013\tQ3F\u0001\u0004TiJLgn\u001a\u0006\u0003QUAQ!\f\u0002A\u00029\nAAZ5mKB\u0011q\u0006N\u0007\u0002a)\u0011\u0011GM\u0001\u0003S>T\u0011aM\u0001\u0005U\u00064\u0018-\u0003\u00026a\t!a)\u001b7f\u0003\u0019\tG\r\u001a&beR\u0011\u0011\u0005\u000f\u0005\u0006[\r\u0001\rAL\u0001\rC\u0012$G)\u001b:fGR|'/\u001f\u000b\u0004Cmj\u0004\"\u0002\u001f\u0005\u0001\u0004\t\u0013a\u00022bg\u0016,&/\u001b\u0005\u0006}\u0011\u0001\rAL\u0001\u0005a\u0006$\b.\u0001\u000bbI\u0012$\u0015N]3di>\u0014\u00180\u00134BEN,g\u000e\u001e\u000b\u0004C\u0005\u0013\u0005\"\u0002\u001f\u0006\u0001\u0004\t\u0003\"\u0002 \u0006\u0001\u0004q\u0013\u0001\u0006<bY&$\u0017\r^3ESJ,7\r^8ssV\u0013\u0018\u000e\u0006\u0002\"\u000b\")AH\u0002a\u0001C\u0005Q!/Z7pm\u00164\u0015\u000e\\3\u0015\u0005qA\u0005\"B%\b\u0001\u0004\t\u0013aA6fs\u0006I!/Z7pm\u0016T\u0015M\u001d\u000b\u000391CQ!\u0013\u0005A\u0002\u0005\u0002"
)
public interface RpcEnvFileServer {
   String addFile(final File file);

   String addJar(final File file);

   String addDirectory(final String baseUri, final File path);

   String addDirectoryIfAbsent(final String baseUri, final File path);

   // $FF: synthetic method
   static String validateDirectoryUri$(final RpcEnvFileServer $this, final String baseUri) {
      return $this.validateDirectoryUri(baseUri);
   }

   default String validateDirectoryUri(final String baseUri) {
      String fixedBaseUri;
      Predef var6;
      boolean var7;
      label26: {
         label25: {
            label24: {
               String baseCanonicalUri = (new URI(baseUri)).normalize().getPath();
               StringOps var10000 = .MODULE$;
               String var10001 = scala.Predef..MODULE$.augmentString(.MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(baseCanonicalUri), "/"));
               fixedBaseUri = "/" + var10000.stripSuffix$extension(var10001, "/");
               var6 = scala.Predef..MODULE$;
               String var4 = "/files";
               if (fixedBaseUri == null) {
                  if (var4 == null) {
                     break label24;
                  }
               } else if (fixedBaseUri.equals(var4)) {
                  break label24;
               }

               String var5 = "/jars";
               if (fixedBaseUri == null) {
                  if (var5 != null) {
                     break label25;
                  }
               } else if (!fixedBaseUri.equals(var5)) {
                  break label25;
               }
            }

            var7 = false;
            break label26;
         }

         var7 = true;
      }

      var6.require(var7, () -> "Directory URI cannot be /files nor /jars.");
      return fixedBaseUri;
   }

   void removeFile(final String key);

   void removeJar(final String key);

   static void $init$(final RpcEnvFileServer $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
