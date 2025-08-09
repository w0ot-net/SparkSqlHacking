package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.resource.ResourceProfile;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc\u0001\u0002\f\u0018\u0001\u0002B\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005y!)1\t\u0001C\u0001\t\"9q\tAA\u0001\n\u0003A\u0005b\u0002&\u0001#\u0003%\ta\u0013\u0005\b-\u0002\t\t\u0011\"\u0011X\u0011\u001d\u0001\u0007!!A\u0005\u0002\u0005Dq!\u001a\u0001\u0002\u0002\u0013\u0005a\rC\u0004m\u0001\u0005\u0005I\u0011I7\t\u000fQ\u0004\u0011\u0011!C\u0001k\"9!\u0010AA\u0001\n\u0003Z\bbB?\u0001\u0003\u0003%\tE \u0005\t\u007f\u0002\t\t\u0011\"\u0011\u0002\u0002!I\u00111\u0001\u0001\u0002\u0002\u0013\u0005\u0013QA\u0004\n\u0003C9\u0012\u0011!E\u0001\u0003G1\u0001BF\f\u0002\u0002#\u0005\u0011Q\u0005\u0005\u0007\u0007B!\t!!\u0010\t\u0011}\u0004\u0012\u0011!C#\u0003\u0003A\u0011\"a\u0010\u0011\u0003\u0003%\t)!\u0011\t\u0013\u0005\u0015\u0003#!A\u0005\u0002\u0006\u001d\u0003\"CA*!\u0005\u0005I\u0011BA+\u0005\u0005\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feJ+7o\\;sG\u0016\u0004&o\u001c4jY\u0016\fE\rZ3e\u0015\tA\u0012$A\u0005tG\",G-\u001e7fe*\u0011!dG\u0001\u0006gB\f'o\u001b\u0006\u00039u\ta!\u00199bG\",'\"\u0001\u0010\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001\tse\u000b\u0018\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\r\u0005s\u0017PU3g!\tA\u0013&D\u0001\u0018\u0013\tQsC\u0001\nTa\u0006\u00148\u000eT5ti\u0016tWM]#wK:$\bC\u0001\u0012-\u0013\ti3EA\u0004Qe>$Wo\u0019;\u0011\u0005=:dB\u0001\u00196\u001d\t\tD'D\u00013\u0015\t\u0019t$\u0001\u0004=e>|GOP\u0005\u0002I%\u0011agI\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0014H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00027G\u0005y!/Z:pkJ\u001cW\r\u0015:pM&dW-F\u0001=!\ti\u0004)D\u0001?\u0015\ty\u0014$\u0001\u0005sKN|WO]2f\u0013\t\teHA\bSKN|WO]2f!J|g-\u001b7f\u0003A\u0011Xm]8ve\u000e,\u0007K]8gS2,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u000b\u001a\u0003\"\u0001\u000b\u0001\t\u000bi\u001a\u0001\u0019\u0001\u001f\u0002\t\r|\u0007/\u001f\u000b\u0003\u000b&CqA\u000f\u0003\u0011\u0002\u0003\u0007A(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u00031S#\u0001P',\u00039\u0003\"a\u0014+\u000e\u0003AS!!\u0015*\u0002\u0013Ut7\r[3dW\u0016$'BA*$\u0003)\tgN\\8uCRLwN\\\u0005\u0003+B\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0001\f\u0005\u0002Z=6\t!L\u0003\u0002\\9\u0006!A.\u00198h\u0015\u0005i\u0016\u0001\u00026bm\u0006L!a\u0018.\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005\u0011\u0007C\u0001\u0012d\u0013\t!7EA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002hUB\u0011!\u0005[\u0005\u0003S\u000e\u00121!\u00118z\u0011\u001dY\u0007\"!AA\u0002\t\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u00018\u0011\u0007=\u0014x-D\u0001q\u0015\t\t8%\u0001\u0006d_2dWm\u0019;j_:L!a\u001d9\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003mf\u0004\"AI<\n\u0005a\u001c#a\u0002\"p_2,\u0017M\u001c\u0005\bW*\t\t\u00111\u0001h\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005ac\bbB6\f\u0003\u0003\u0005\rAY\u0001\tQ\u0006\u001c\bnQ8eKR\t!-\u0001\u0005u_N#(/\u001b8h)\u0005A\u0016AB3rk\u0006d7\u000fF\u0002w\u0003\u000fAqa\u001b\b\u0002\u0002\u0003\u0007q\rK\u0002\u0001\u0003\u0017\u0001B!!\u0004\u0002\u00125\u0011\u0011q\u0002\u0006\u0003'fIA!a\u0005\u0002\u0010\taA)\u001a<fY>\u0004XM]!qS\"*\u0001!a\u0006\u0002\u001eA!\u0011QBA\r\u0013\u0011\tY\"a\u0004\u0003\u000bMKgnY3\"\u0005\u0005}\u0011!B\u001a/c9\u0002\u0014!I*qCJ\\G*[:uK:,'OU3t_V\u00148-\u001a)s_\u001aLG.Z!eI\u0016$\u0007C\u0001\u0015\u0011'\u0015\u0001\u0012qEA\u001a!\u0019\tI#a\f=\u000b6\u0011\u00111\u0006\u0006\u0004\u0003[\u0019\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003c\tYCA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!\u000e\u0002<5\u0011\u0011q\u0007\u0006\u0004\u0003sa\u0016AA5p\u0013\rA\u0014q\u0007\u000b\u0003\u0003G\tQ!\u00199qYf$2!RA\"\u0011\u0015Q4\u00031\u0001=\u0003\u001d)h.\u00199qYf$B!!\u0013\u0002PA!!%a\u0013=\u0013\r\tie\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005EC#!AA\u0002\u0015\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\u0006E\u0002Z\u00033J1!a\u0017[\u0005\u0019y%M[3di\u0002"
)
public class SparkListenerResourceProfileAdded implements SparkListenerEvent, Product, Serializable {
   private final ResourceProfile resourceProfile;

   public static Option unapply(final SparkListenerResourceProfileAdded x$0) {
      return SparkListenerResourceProfileAdded$.MODULE$.unapply(x$0);
   }

   public static SparkListenerResourceProfileAdded apply(final ResourceProfile resourceProfile) {
      return SparkListenerResourceProfileAdded$.MODULE$.apply(resourceProfile);
   }

   public static Function1 andThen(final Function1 g) {
      return SparkListenerResourceProfileAdded$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SparkListenerResourceProfileAdded$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public ResourceProfile resourceProfile() {
      return this.resourceProfile;
   }

   public SparkListenerResourceProfileAdded copy(final ResourceProfile resourceProfile) {
      return new SparkListenerResourceProfileAdded(resourceProfile);
   }

   public ResourceProfile copy$default$1() {
      return this.resourceProfile();
   }

   public String productPrefix() {
      return "SparkListenerResourceProfileAdded";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.resourceProfile();
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
      return x$1 instanceof SparkListenerResourceProfileAdded;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "resourceProfile";
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
            if (x$1 instanceof SparkListenerResourceProfileAdded) {
               label40: {
                  SparkListenerResourceProfileAdded var4 = (SparkListenerResourceProfileAdded)x$1;
                  ResourceProfile var10000 = this.resourceProfile();
                  ResourceProfile var5 = var4.resourceProfile();
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

   public SparkListenerResourceProfileAdded(final ResourceProfile resourceProfile) {
      this.resourceProfile = resourceProfile;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
