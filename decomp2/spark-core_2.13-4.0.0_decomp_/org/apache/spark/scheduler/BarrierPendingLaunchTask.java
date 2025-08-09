package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005h!\u0002\u0015*\u0001&\n\u0004\u0002\u0003%\u0001\u0005+\u0007I\u0011A%\t\u0011I\u0003!\u0011#Q\u0001\n)C\u0001b\u0015\u0001\u0003\u0016\u0004%\t!\u0013\u0005\t)\u0002\u0011\t\u0012)A\u0005\u0015\"AQ\u000b\u0001BK\u0002\u0013\u0005a\u000b\u0003\u0005[\u0001\tE\t\u0015!\u0003X\u0011!Y\u0006A!f\u0001\n\u0003a\u0006\u0002C3\u0001\u0005#\u0005\u000b\u0011B/\t\u0011\u0019\u0004!Q3A\u0005\u0002\u001dD\u0001b\u001c\u0001\u0003\u0012\u0003\u0006I\u0001\u001b\u0005\u0006a\u0002!\t!\u001d\u0005\nq\u0002\u0001\r\u00111A\u0005\u0002YC\u0011\"\u001f\u0001A\u0002\u0003\u0007I\u0011\u0001>\t\u0015\u0005\u0005\u0001\u00011A\u0001B\u0003&q\u000b\u0003\u0005\u0002\u0004\u0001\u0001\r\u0011\"\u0001W\u0011%\t)\u0001\u0001a\u0001\n\u0003\t9\u0001C\u0004\u0002\f\u0001\u0001\u000b\u0015B,\t\u0013\u00055\u0001!!A\u0005\u0002\u0005=\u0001\"CA\u000e\u0001E\u0005I\u0011AA\u000f\u0011%\t\u0019\u0004AI\u0001\n\u0003\ti\u0002C\u0005\u00026\u0001\t\n\u0011\"\u0001\u00028!I\u00111\b\u0001\u0012\u0002\u0013\u0005\u0011Q\b\u0005\n\u0003\u0003\u0002\u0011\u0013!C\u0001\u0003\u0007B\u0011\"a\u0012\u0001\u0003\u0003%\t%!\u0013\t\u0011\u0005e\u0003!!A\u0005\u0002YC\u0011\"a\u0017\u0001\u0003\u0003%\t!!\u0018\t\u0013\u0005\u001d\u0004!!A\u0005B\u0005%\u0004\"CA<\u0001\u0005\u0005I\u0011AA=\u0011%\t\u0019\tAA\u0001\n\u0003\n)\tC\u0005\u0002\n\u0002\t\t\u0011\"\u0011\u0002\f\"I\u0011Q\u0012\u0001\u0002\u0002\u0013\u0005\u0013q\u0012\u0005\n\u0003#\u0003\u0011\u0011!C!\u0003';!\"a&*\u0003\u0003E\t!KAM\r%A\u0013&!A\t\u0002%\nY\n\u0003\u0004qE\u0011\u0005\u00111\u0017\u0005\n\u0003\u001b\u0013\u0013\u0011!C#\u0003\u001fC\u0011\"!.#\u0003\u0003%\t)a.\t\u0013\u0005\r'%!A\u0005\u0002\u0006\u0015\u0007\"CAlE\u0005\u0005I\u0011BAm\u0005a\u0011\u0015M\u001d:jKJ\u0004VM\u001c3j]\u001ed\u0015-\u001e8dQR\u000b7o\u001b\u0006\u0003U-\n\u0011b]2iK\u0012,H.\u001a:\u000b\u00051j\u0013!B:qCJ\\'B\u0001\u00180\u0003\u0019\t\u0007/Y2iK*\t\u0001'A\u0002pe\u001e\u001cB\u0001\u0001\u001a9wA\u00111GN\u0007\u0002i)\tQ'A\u0003tG\u0006d\u0017-\u0003\u00028i\t1\u0011I\\=SK\u001a\u0004\"aM\u001d\n\u0005i\"$a\u0002)s_\u0012,8\r\u001e\t\u0003y\u0015s!!P\"\u000f\u0005y\u0012U\"A \u000b\u0005\u0001\u000b\u0015A\u0002\u001fs_>$hh\u0001\u0001\n\u0003UJ!\u0001\u0012\u001b\u0002\u000fA\f7m[1hK&\u0011ai\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\tR\na!\u001a=fG&#W#\u0001&\u0011\u0005-{eB\u0001'N!\tqD'\u0003\u0002Oi\u00051\u0001K]3eK\u001aL!\u0001U)\u0003\rM#(/\u001b8h\u0015\tqE'A\u0004fq\u0016\u001c\u0017\n\u001a\u0011\u0002\t!|7\u000f^\u0001\u0006Q>\u001cH\u000fI\u0001\u0006S:$W\r_\u000b\u0002/B\u00111\u0007W\u0005\u00033R\u00121!\u00138u\u0003\u0019Ig\u000eZ3yA\u0005aA/Y:l\u0019>\u001c\u0017\r\\5usV\tQ\f\u0005\u0002_E:\u0011q\fY\u0007\u0002S%\u0011\u0011-K\u0001\r)\u0006\u001c8\u000eT8dC2LG/_\u0005\u0003G\u0012\u0014A\u0002V1tW2{7-\u00197jifT!!Y\u0015\u0002\u001bQ\f7o\u001b'pG\u0006d\u0017\u000e^=!\u0003E\t7o]5h]\u0016$'+Z:pkJ\u001cWm]\u000b\u0002QB!1*\u001b&l\u0013\tQ\u0017KA\u0002NCB\u0004BaS5KYB\u00111'\\\u0005\u0003]R\u0012A\u0001T8oO\u0006\u0011\u0012m]:jO:,GMU3t_V\u00148-Z:!\u0003\u0019a\u0014N\\5u}Q1!o\u001d;vm^\u0004\"a\u0018\u0001\t\u000b![\u0001\u0019\u0001&\t\u000bM[\u0001\u0019\u0001&\t\u000bU[\u0001\u0019A,\t\u000bm[\u0001\u0019A/\t\u000b\u0019\\\u0001\u0019\u00015\u0002%\u0005\u001c8/[4oK\u0012|eMZ3s\u0013:$W\r_\u0001\u0017CN\u001c\u0018n\u001a8fI>3g-\u001a:J]\u0012,\u0007p\u0018\u0013fcR\u00111P \t\u0003gqL!! \u001b\u0003\tUs\u0017\u000e\u001e\u0005\b\u007f6\t\t\u00111\u0001X\u0003\rAH%M\u0001\u0014CN\u001c\u0018n\u001a8fI>3g-\u001a:J]\u0012,\u0007\u0010I\u0001\u000eCN\u001c\u0018n\u001a8fI\u000e{'/Z:\u0002#\u0005\u001c8/[4oK\u0012\u001cuN]3t?\u0012*\u0017\u000fF\u0002|\u0003\u0013Aqa \t\u0002\u0002\u0003\u0007q+\u0001\bbgNLwM\\3e\u0007>\u0014Xm\u001d\u0011\u0002\t\r|\u0007/\u001f\u000b\fe\u0006E\u00111CA\u000b\u0003/\tI\u0002C\u0004I%A\u0005\t\u0019\u0001&\t\u000fM\u0013\u0002\u0013!a\u0001\u0015\"9QK\u0005I\u0001\u0002\u00049\u0006bB.\u0013!\u0003\u0005\r!\u0018\u0005\bMJ\u0001\n\u00111\u0001i\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a\b+\u0007)\u000b\tc\u000b\u0002\u0002$A!\u0011QEA\u0018\u001b\t\t9C\u0003\u0003\u0002*\u0005-\u0012!C;oG\",7m[3e\u0015\r\ti\u0003N\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0019\u0003O\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002:)\u001aq+!\t\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011q\b\u0016\u0004;\u0006\u0005\u0012AD2paf$C-\u001a4bk2$H%N\u000b\u0003\u0003\u000bR3\u0001[A\u0011\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\n\t\u0005\u0003\u001b\n9&\u0004\u0002\u0002P)!\u0011\u0011KA*\u0003\u0011a\u0017M\\4\u000b\u0005\u0005U\u0013\u0001\u00026bm\u0006L1\u0001UA(\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0018\u0002fA\u00191'!\u0019\n\u0007\u0005\rDGA\u0002B]fDqa \u000e\u0002\u0002\u0003\u0007q+A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\u0007\u0005\u0004\u0002n\u0005M\u0014qL\u0007\u0003\u0003_R1!!\u001d5\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003k\nyG\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA>\u0003\u0003\u00032aMA?\u0013\r\ty\b\u000e\u0002\b\u0005>|G.Z1o\u0011!yH$!AA\u0002\u0005}\u0013A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u0013\u0002\b\"9q0HA\u0001\u0002\u00049\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003]\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u0017\na!Z9vC2\u001cH\u0003BA>\u0003+C\u0001b \u0011\u0002\u0002\u0003\u0007\u0011qL\u0001\u0019\u0005\u0006\u0014(/[3s!\u0016tG-\u001b8h\u0019\u0006,hn\u00195UCN\\\u0007CA0#'\u0015\u0011\u0013QTAU!)\ty*!*K\u0015^k\u0006N]\u0007\u0003\u0003CS1!a)5\u0003\u001d\u0011XO\u001c;j[\u0016LA!a*\u0002\"\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001b\u0011\t\u0005-\u0016\u0011W\u0007\u0003\u0003[SA!a,\u0002T\u0005\u0011\u0011n\\\u0005\u0004\r\u00065FCAAM\u0003\u0015\t\u0007\u000f\u001d7z)-\u0011\u0018\u0011XA^\u0003{\u000by,!1\t\u000b!+\u0003\u0019\u0001&\t\u000bM+\u0003\u0019\u0001&\t\u000bU+\u0003\u0019A,\t\u000bm+\u0003\u0019A/\t\u000b\u0019,\u0003\u0019\u00015\u0002\u000fUt\u0017\r\u001d9msR!\u0011qYAj!\u0015\u0019\u0014\u0011ZAg\u0013\r\tY\r\u000e\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0011M\nyM\u0013&X;\"L1!!55\u0005\u0019!V\u000f\u001d7fk!A\u0011Q\u001b\u0014\u0002\u0002\u0003\u0007!/A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a7\u0011\t\u00055\u0013Q\\\u0005\u0005\u0003?\fyE\u0001\u0004PE*,7\r\u001e"
)
public class BarrierPendingLaunchTask implements Product, Serializable {
   private final String execId;
   private final String host;
   private final int index;
   private final Enumeration.Value taskLocality;
   private final Map assignedResources;
   private int assignedOfferIndex;
   private int assignedCores;

   public static Option unapply(final BarrierPendingLaunchTask x$0) {
      return BarrierPendingLaunchTask$.MODULE$.unapply(x$0);
   }

   public static BarrierPendingLaunchTask apply(final String execId, final String host, final int index, final Enumeration.Value taskLocality, final Map assignedResources) {
      return BarrierPendingLaunchTask$.MODULE$.apply(execId, host, index, taskLocality, assignedResources);
   }

   public static Function1 tupled() {
      return BarrierPendingLaunchTask$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BarrierPendingLaunchTask$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String execId() {
      return this.execId;
   }

   public String host() {
      return this.host;
   }

   public int index() {
      return this.index;
   }

   public Enumeration.Value taskLocality() {
      return this.taskLocality;
   }

   public Map assignedResources() {
      return this.assignedResources;
   }

   public int assignedOfferIndex() {
      return this.assignedOfferIndex;
   }

   public void assignedOfferIndex_$eq(final int x$1) {
      this.assignedOfferIndex = x$1;
   }

   public int assignedCores() {
      return this.assignedCores;
   }

   public void assignedCores_$eq(final int x$1) {
      this.assignedCores = x$1;
   }

   public BarrierPendingLaunchTask copy(final String execId, final String host, final int index, final Enumeration.Value taskLocality, final Map assignedResources) {
      return new BarrierPendingLaunchTask(execId, host, index, taskLocality, assignedResources);
   }

   public String copy$default$1() {
      return this.execId();
   }

   public String copy$default$2() {
      return this.host();
   }

   public int copy$default$3() {
      return this.index();
   }

   public Enumeration.Value copy$default$4() {
      return this.taskLocality();
   }

   public Map copy$default$5() {
      return this.assignedResources();
   }

   public String productPrefix() {
      return "BarrierPendingLaunchTask";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.execId();
         }
         case 1 -> {
            return this.host();
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.index());
         }
         case 3 -> {
            return this.taskLocality();
         }
         case 4 -> {
            return this.assignedResources();
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
      return x$1 instanceof BarrierPendingLaunchTask;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "execId";
         }
         case 1 -> {
            return "host";
         }
         case 2 -> {
            return "index";
         }
         case 3 -> {
            return "taskLocality";
         }
         case 4 -> {
            return "assignedResources";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.execId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.host()));
      var1 = Statics.mix(var1, this.index());
      var1 = Statics.mix(var1, Statics.anyHash(this.taskLocality()));
      var1 = Statics.mix(var1, Statics.anyHash(this.assignedResources()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label75: {
            if (x$1 instanceof BarrierPendingLaunchTask) {
               BarrierPendingLaunchTask var4 = (BarrierPendingLaunchTask)x$1;
               if (this.index() == var4.index()) {
                  label68: {
                     String var10000 = this.execId();
                     String var5 = var4.execId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label68;
                     }

                     var10000 = this.host();
                     String var6 = var4.host();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label68;
                     }

                     Enumeration.Value var10 = this.taskLocality();
                     Enumeration.Value var7 = var4.taskLocality();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label68;
                        }
                     } else if (!var10.equals(var7)) {
                        break label68;
                     }

                     Map var11 = this.assignedResources();
                     Map var8 = var4.assignedResources();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label68;
                        }
                     } else if (!var11.equals(var8)) {
                        break label68;
                     }

                     if (var4.canEqual(this)) {
                        break label75;
                     }
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public BarrierPendingLaunchTask(final String execId, final String host, final int index, final Enumeration.Value taskLocality, final Map assignedResources) {
      this.execId = execId;
      this.host = host;
      this.index = index;
      this.taskLocality = taskLocality;
      this.assignedResources = assignedResources;
      Product.$init$(this);
      this.assignedCores = 0;
   }
}
