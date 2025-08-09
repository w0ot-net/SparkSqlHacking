package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import java.net.URI;
import org.apache.spark.sql.classic.SparkSession;
import org.apache.spark.sql.hive.client.HiveClient;
import org.apache.spark.sql.internal.SessionResourceLoader;
import org.apache.spark.util.Utils.;
import scala.Function0;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005-3Aa\u0002\u0005\u0001'!A!\u0004\u0001B\u0001B\u0003%1\u0004\u0003\u0005\"\u0001\t\u0005\t\u0015!\u0003#\u0011\u0015q\u0003\u0001\"\u00010\u0011!Y\u0003\u0001#b\u0001\n\u0013!\u0004\"B\u001b\u0001\t\u00032\u0004bC$\u0001!\u0003\r\t\u0011!C\u0005\u0011*\u0013\u0011\u0004S5wKN+7o]5p]J+7o\\;sG\u0016du.\u00193fe*\u0011\u0011BC\u0001\u0005Q&4XM\u0003\u0002\f\u0019\u0005\u00191/\u001d7\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001)A\u0011Q\u0003G\u0007\u0002-)\u0011qCC\u0001\tS:$XM\u001d8bY&\u0011\u0011D\u0006\u0002\u0016'\u0016\u001c8/[8o%\u0016\u001cx.\u001e:dK2{\u0017\rZ3s\u0003\u001d\u0019Xm]:j_:\u0004\"\u0001H\u0010\u000e\u0003uQ!A\b\u0006\u0002\u000f\rd\u0017m]:jG&\u0011\u0001%\b\u0002\r'B\f'o[*fgNLwN\\\u0001\u000eG2LWM\u001c;Ck&dG-\u001a:\u0011\u0007\r2\u0003&D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005%1UO\\2uS>t\u0007\u0007\u0005\u0002*Y5\t!F\u0003\u0002,\u0011\u000511\r\\5f]RL!!\f\u0016\u0003\u0015!Kg/Z\"mS\u0016tG/\u0001\u0004=S:LGO\u0010\u000b\u0004aI\u001a\u0004CA\u0019\u0001\u001b\u0005A\u0001\"\u0002\u000e\u0004\u0001\u0004Y\u0002\"B\u0011\u0004\u0001\u0004\u0011S#\u0001\u0015\u0002\r\u0005$GMS1s)\t9$\b\u0005\u0002$q%\u0011\u0011\b\n\u0002\u0005+:LG\u000fC\u0003<\u000b\u0001\u0007A(\u0001\u0003qCRD\u0007CA\u001fE\u001d\tq$\t\u0005\u0002@I5\t\u0001I\u0003\u0002B%\u00051AH]8pizJ!a\u0011\u0013\u0002\rA\u0013X\rZ3g\u0013\t)eI\u0001\u0004TiJLgn\u001a\u0006\u0003\u0007\u0012\nAb];qKJ$\u0013\r\u001a3KCJ$\"aN%\t\u000bm2\u0001\u0019\u0001\u001f\n\u0005UB\u0002"
)
public class HiveSessionResourceLoader extends SessionResourceLoader {
   private HiveClient client;
   private Function0 clientBuilder;
   private volatile boolean bitmap$0;

   // $FF: synthetic method
   private void super$addJar(final String path) {
      super.addJar(path);
   }

   private HiveClient client$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.client = (HiveClient)this.clientBuilder.apply();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      this.clientBuilder = null;
      return this.client;
   }

   private HiveClient client() {
      return !this.bitmap$0 ? this.client$lzycompute() : this.client;
   }

   public void addJar(final String path) {
      URI uri = .MODULE$.resolveURI(path);
      this.resolveJars(uri).foreach((p) -> {
         $anonfun$addJar$1(this, p);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$addJar$1(final HiveSessionResourceLoader $this, final String p) {
      $this.client().addJar(p);
      $this.super$addJar(p);
   }

   public HiveSessionResourceLoader(final SparkSession session, final Function0 clientBuilder) {
      this.clientBuilder = clientBuilder;
      super(session);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
