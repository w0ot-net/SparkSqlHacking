package org.apache.spark.deploy.master;

import org.apache.spark.deploy.ExecutorDescription;
import org.apache.spark.deploy.ExecutorState$;
import scala.Enumeration;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a!\u0002\r\u001a\u0001e\u0019\u0003\u0002\u0003\u0016\u0001\u0005\u000b\u0007I\u0011\u0001\u0017\t\u0011A\u0002!\u0011!Q\u0001\n5B\u0001\"\r\u0001\u0003\u0006\u0004%\tA\r\u0005\to\u0001\u0011\t\u0011)A\u0005g!A\u0001\b\u0001BC\u0002\u0013\u0005\u0011\b\u0003\u0005>\u0001\t\u0005\t\u0015!\u0003;\u0011!q\u0004A!b\u0001\n\u0003a\u0003\u0002C \u0001\u0005\u0003\u0005\u000b\u0011B\u0017\t\u0011\u0001\u0003!Q1A\u0005\u00021B\u0001\"\u0011\u0001\u0003\u0002\u0003\u0006I!\f\u0005\t\u0005\u0002\u0011)\u0019!C\u0001\u0007\"A\u0001\f\u0001B\u0001B\u0003%A\t\u0003\u0005Z\u0001\t\u0015\r\u0011\"\u0001-\u0011!Q\u0006A!A!\u0002\u0013i\u0003\"B.\u0001\t\u0003a\u0006bB3\u0001\u0001\u0004%\tA\u001a\u0005\ba\u0002\u0001\r\u0011\"\u0001r\u0011\u00199\b\u0001)Q\u0005O\")\u0001\u0010\u0001C\u0001s\"1q\u0010\u0001C\u0001\u0003\u0003Aq!a\u0001\u0001\t\u0003\n)\u0001C\u0004\u0002\u0018\u0001!\t%!\u0007\t\u000f\u0005m\u0001\u0001\"\u0011\u0002\u001e\taQ\t_3dkR|'\u000fR3tG*\u0011!dG\u0001\u0007[\u0006\u001cH/\u001a:\u000b\u0005qi\u0012A\u00023fa2|\u0017P\u0003\u0002\u001f?\u0005)1\u000f]1sW*\u0011\u0001%I\u0001\u0007CB\f7\r[3\u000b\u0003\t\n1a\u001c:h'\t\u0001A\u0005\u0005\u0002&Q5\taEC\u0001(\u0003\u0015\u00198-\u00197b\u0013\tIcE\u0001\u0004B]f\u0014VMZ\u0001\u0003S\u0012\u001c\u0001!F\u0001.!\t)c&\u0003\u00020M\t\u0019\u0011J\u001c;\u0002\u0007%$\u0007%A\u0006baBd\u0017nY1uS>tW#A\u001a\u0011\u0005Q*T\"A\r\n\u0005YJ\"aD!qa2L7-\u0019;j_:LeNZ8\u0002\u0019\u0005\u0004\b\u000f\\5dCRLwN\u001c\u0011\u0002\r]|'o[3s+\u0005Q\u0004C\u0001\u001b<\u0013\ta\u0014D\u0001\u0006X_J\\WM]%oM>\fqa^8sW\u0016\u0014\b%A\u0003d_J,7/\u0001\u0004d_J,7\u000fI\u0001\u0007[\u0016lwN]=\u0002\u000f5,Wn\u001c:zA\u0005I!/Z:pkJ\u001cWm]\u000b\u0002\tB!Q\tT(S\u001d\t1%\n\u0005\u0002HM5\t\u0001J\u0003\u0002JW\u00051AH]8pizJ!a\u0013\u0014\u0002\rA\u0013X\rZ3g\u0013\tieJA\u0002NCBT!a\u0013\u0014\u0011\u0005\u0015\u0003\u0016BA)O\u0005\u0019\u0019FO]5oOB\u00111KV\u0007\u0002)*\u0011Q+H\u0001\te\u0016\u001cx.\u001e:dK&\u0011q\u000b\u0016\u0002\u0014%\u0016\u001cx.\u001e:dK&sgm\u001c:nCRLwN\\\u0001\u000be\u0016\u001cx.\u001e:dKN\u0004\u0013\u0001\u0002:q\u0013\u0012\fQA\u001d9JI\u0002\na\u0001P5oSRtD\u0003C/_?\u0002\f'm\u00193\u0011\u0005Q\u0002\u0001\"\u0002\u0016\u0010\u0001\u0004i\u0003\"B\u0019\u0010\u0001\u0004\u0019\u0004\"\u0002\u001d\u0010\u0001\u0004Q\u0004\"\u0002 \u0010\u0001\u0004i\u0003\"\u0002!\u0010\u0001\u0004i\u0003\"\u0002\"\u0010\u0001\u0004!\u0005\"B-\u0010\u0001\u0004i\u0013!B:uCR,W#A4\u0011\u0005!dgBA5k\u001b\u0005Y\u0012BA6\u001c\u00035)\u00050Z2vi>\u00148\u000b^1uK&\u0011QN\u001c\u0002\u0006-\u0006dW/Z\u0005\u0003_\u001a\u00121\"\u00128v[\u0016\u0014\u0018\r^5p]\u0006I1\u000f^1uK~#S-\u001d\u000b\u0003eV\u0004\"!J:\n\u0005Q4#\u0001B+oSRDqA^\t\u0002\u0002\u0003\u0007q-A\u0002yIE\naa\u001d;bi\u0016\u0004\u0013!C2paf\u001cF/\u0019;f)\t\u0011(\u0010C\u0003|'\u0001\u0007A0\u0001\u0005fq\u0016\u001cG)Z:d!\tIW0\u0003\u0002\u007f7\t\u0019R\t_3dkR|'\u000fR3tGJL\u0007\u000f^5p]\u00061a-\u001e7m\u0013\u0012,\u0012aT\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u001d\u0011Q\u0002\t\u0004K\u0005%\u0011bAA\u0006M\t9!i\\8mK\u0006t\u0007bBA\b+\u0001\u0007\u0011\u0011C\u0001\u0006_RDWM\u001d\t\u0004K\u0005M\u0011bAA\u000bM\t\u0019\u0011I\\=\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aT\u0001\tQ\u0006\u001c\bnQ8eKR\tQ\u0006"
)
public class ExecutorDesc {
   private final int id;
   private final ApplicationInfo application;
   private final WorkerInfo worker;
   private final int cores;
   private final int memory;
   private final Map resources;
   private final int rpId;
   private Enumeration.Value state;

   public int id() {
      return this.id;
   }

   public ApplicationInfo application() {
      return this.application;
   }

   public WorkerInfo worker() {
      return this.worker;
   }

   public int cores() {
      return this.cores;
   }

   public int memory() {
      return this.memory;
   }

   public Map resources() {
      return this.resources;
   }

   public int rpId() {
      return this.rpId;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public void state_$eq(final Enumeration.Value x$1) {
      this.state = x$1;
   }

   public void copyState(final ExecutorDescription execDesc) {
      this.state_$eq(execDesc.state());
   }

   public String fullId() {
      String var10000 = this.application().id();
      return var10000 + "/" + this.id();
   }

   public boolean equals(final Object other) {
      if (!(other instanceof ExecutorDesc var4)) {
         return false;
      } else {
         boolean var8;
         label38: {
            String var10000 = this.fullId();
            String var5 = var4.fullId();
            if (var10000 == null) {
               if (var5 != null) {
                  break label38;
               }
            } else if (!var10000.equals(var5)) {
               break label38;
            }

            var10000 = this.worker().id();
            String var6 = var4.worker().id();
            if (var10000 == null) {
               if (var6 != null) {
                  break label38;
               }
            } else if (!var10000.equals(var6)) {
               break label38;
            }

            if (this.cores() == var4.cores() && this.memory() == var4.memory()) {
               var8 = true;
               return var8;
            }
         }

         var8 = false;
         return var8;
      }
   }

   public String toString() {
      return this.fullId();
   }

   public int hashCode() {
      return this.toString().hashCode();
   }

   public ExecutorDesc(final int id, final ApplicationInfo application, final WorkerInfo worker, final int cores, final int memory, final Map resources, final int rpId) {
      this.id = id;
      this.application = application;
      this.worker = worker;
      this.cores = cores;
      this.memory = memory;
      this.resources = resources;
      this.rpId = rpId;
      this.state = ExecutorState$.MODULE$.LAUNCHING();
   }
}
