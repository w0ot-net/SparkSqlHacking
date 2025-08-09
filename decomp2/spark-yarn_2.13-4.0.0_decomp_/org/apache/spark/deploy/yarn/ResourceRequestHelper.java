package org.apache.spark.deploy.yarn;

import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uq!\u0002\b\u0010\u0011\u0013Qb!\u0002\u000f\u0010\u0011\u0013i\u0002\"\u0002\u0016\u0002\t\u0003Y\u0003b\u0002\u0017\u0002\u0005\u0004%I!\f\u0005\u0007m\u0005\u0001\u000b\u0011\u0002\u0018\t\u000f]\n\u0001\u0019!C\u0005q!9A(\u0001a\u0001\n\u0013i\u0004BB\"\u0002A\u0003&\u0011\b\u0003\u0004I\u0003\u0011\u0005q\"\u0013\u0005\u0007A\u0006!\taD1\t\r\r\fA\u0011A\be\u0011\u0015A\u0017\u0001\"\u0001j\u0011\u0015Y\u0017\u0001\"\u0001m\u0011\u0015a\u0018\u0001\"\u0003~\u0003U\u0011Vm]8ve\u000e,'+Z9vKN$\b*\u001a7qKJT!\u0001E\t\u0002\te\f'O\u001c\u0006\u0003%M\ta\u0001Z3qY>L(B\u0001\u000b\u0016\u0003\u0015\u0019\b/\u0019:l\u0015\t1r#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00021\u0005\u0019qN]4\u0004\u0001A\u00111$A\u0007\u0002\u001f\t)\"+Z:pkJ\u001cWMU3rk\u0016\u001cH\u000fS3ma\u0016\u00148cA\u0001\u001fIA\u0011qDI\u0007\u0002A)\t\u0011%A\u0003tG\u0006d\u0017-\u0003\u0002$A\t1\u0011I\\=SK\u001a\u0004\"!\n\u0015\u000e\u0003\u0019R!aJ\n\u0002\u0011%tG/\u001a:oC2L!!\u000b\u0014\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\"\u0012AG\u0001\u0016\u00036{UK\u0014+`\u0003:#u,\u0016(J)~\u0013ViR#Y+\u0005q\u0003CA\u00185\u001b\u0005\u0001$BA\u00193\u0003!i\u0017\r^2iS:<'BA\u001a!\u0003\u0011)H/\u001b7\n\u0005U\u0002$!\u0002*fO\u0016D\u0018AF!N\u001fVsEkX!O\t~+f*\u0013+`%\u0016;U\t\u0017\u0011\u0002#9,XNU3t_V\u00148-Z#se>\u00148/F\u0001:!\ty\"(\u0003\u0002<A\t\u0019\u0011J\u001c;\u0002+9,XNU3t_V\u00148-Z#se>\u00148o\u0018\u0013fcR\u0011a(\u0011\t\u0003?}J!\u0001\u0011\u0011\u0003\tUs\u0017\u000e\u001e\u0005\b\u0005\u001a\t\t\u00111\u0001:\u0003\rAH%M\u0001\u0013]Vl'+Z:pkJ\u001cW-\u0012:s_J\u001c\b\u0005\u000b\u0002\b\u000bB\u0011qDR\u0005\u0003\u000f\u0002\u0012\u0001B^8mCRLG.Z\u0001\u001bO\u0016$\u0018,\u0019:o%\u0016\u001cx.\u001e:dKN\fe\u000eZ!n_VtGo\u001d\u000b\u0004\u0015bs\u0006\u0003B&S+Vs!\u0001\u0014)\u0011\u00055\u0003S\"\u0001(\u000b\u0005=K\u0012A\u0002\u001fs_>$h(\u0003\u0002RA\u00051\u0001K]3eK\u001aL!a\u0015+\u0003\u00075\u000b\u0007O\u0003\u0002RAA\u00111JV\u0005\u0003/R\u0013aa\u0015;sS:<\u0007\"B-\t\u0001\u0004Q\u0016!C:qCJ\\7i\u001c8g!\tYF,D\u0001\u0014\u0013\ti6CA\u0005Ta\u0006\u00148nQ8oM\")q\f\u0003a\u0001+\u0006i1m\\7q_:,g\u000e\u001e(b[\u0016\facZ3u%\u0016\u001cx.\u001e:dK:\u000bW.Z'baBLgn\u001a\u000b\u0003\u0015\nDQ!W\u0005A\u0002i\u000b!eZ3u3\u0006\u0014hNU3t_V\u00148-Z:Ge>l7\u000b]1sWJ+7o\\;sG\u0016\u001cHc\u0001&fO\")aM\u0003a\u0001+\u0006Q1m\u001c8g!J,g-\u001b=\t\u000beS\u0001\u0019\u0001.\u0002#Y\fG.\u001b3bi\u0016\u0014Vm]8ve\u000e,7\u000f\u0006\u0002?U\")\u0011l\u0003a\u00015\u0006\u00192/\u001a;SKN|WO]2f%\u0016\fX/Z:ugR\u0019a(\\8\t\u000b9d\u0001\u0019\u0001&\u0002\u0013I,7o\\;sG\u0016\u001c\b\"\u00029\r\u0001\u0004\t\u0018\u0001\u0003:fg>,(oY3\u0011\u0005ITX\"A:\u000b\u0005Q,\u0018a\u0002:fG>\u0014Hm\u001d\u0006\u0003m^\f1!\u00199j\u0015\t\u0001\u0002P\u0003\u0002z+\u00051\u0001.\u00193p_BL!a_:\u0003\u0011I+7o\\;sG\u0016\f\u0011d\u0019:fCR,'+Z:pkJ\u001cW-\u00138g_Jl\u0017\r^5p]R9a0a\u0001\u0002\b\u0005E\u0001C\u0001:\u0000\u0013\r\t\ta\u001d\u0002\u0014%\u0016\u001cx.\u001e:dK&sgm\u001c:nCRLwN\u001c\u0005\u0007\u0003\u000bi\u0001\u0019A+\u0002\u0019I,7o\\;sG\u0016t\u0015-\\3\t\u000f\u0005%Q\u00021\u0001\u0002\f\u00051\u0011-\\8v]R\u00042aHA\u0007\u0013\r\ty\u0001\t\u0002\u0005\u0019>tw\r\u0003\u0004\u0002\u00145\u0001\r!V\u0001\u0005k:LG\u000f"
)
public final class ResourceRequestHelper {
   public static void setResourceRequests(final Map resources, final Resource resource) {
      ResourceRequestHelper$.MODULE$.setResourceRequests(resources, resource);
   }

   public static void validateResources(final SparkConf sparkConf) {
      ResourceRequestHelper$.MODULE$.validateResources(sparkConf);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return ResourceRequestHelper$.MODULE$.LogStringContext(sc);
   }
}
