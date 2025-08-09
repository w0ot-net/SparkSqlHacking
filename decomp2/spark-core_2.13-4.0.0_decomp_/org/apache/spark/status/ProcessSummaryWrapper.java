package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.status.api.v1.ProcessSummary;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3Q\u0001C\u0005\u0001\u0017EA\u0001\u0002\u0007\u0001\u0003\u0006\u0004%\tA\u0007\u0005\tG\u0001\u0011\t\u0011)A\u00057!)A\u0005\u0001C\u0001K!)\u0011\u0006\u0001C\u0005U!)A\n\u0001C\u0005\u001b\"9Q\u000b\u0001b\u0001\n\u0003Q\u0003B\u0002,\u0001A\u0003%1FA\u000bQe>\u001cWm]:Tk6l\u0017M]=Xe\u0006\u0004\b/\u001a:\u000b\u0005)Y\u0011AB:uCR,8O\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h'\t\u0001!\u0003\u0005\u0002\u0014-5\tACC\u0001\u0016\u0003\u0015\u00198-\u00197b\u0013\t9BC\u0001\u0004B]f\u0014VMZ\u0001\u0005S:4wn\u0001\u0001\u0016\u0003m\u0001\"\u0001H\u0011\u000e\u0003uQ!AH\u0010\u0002\u0005Y\f$B\u0001\u0011\n\u0003\r\t\u0007/[\u0005\u0003Eu\u0011a\u0002\u0015:pG\u0016\u001c8oU;n[\u0006\u0014\u00180A\u0003j]\u001a|\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003M!\u0002\"a\n\u0001\u000e\u0003%AQ\u0001G\u0002A\u0002m\t!!\u001b3\u0016\u0003-\u0002\"\u0001L\u001a\u000f\u00055\n\u0004C\u0001\u0018\u0015\u001b\u0005y#B\u0001\u0019\u001a\u0003\u0019a$o\\8u}%\u0011!\u0007F\u0001\u0007!J,G-\u001a4\n\u0005Q*$AB*ue&twM\u0003\u00023)!\u0012Aa\u000e\t\u0003q\u0005k\u0011!\u000f\u0006\u0003um\n!\"\u00198o_R\fG/[8o\u0015\taT(A\u0004kC\u000e\\7o\u001c8\u000b\u0005yz\u0014!\u00034bgR,'\u000f_7m\u0015\u0005\u0001\u0015aA2p[&\u0011!)\u000f\u0002\u000b\u0015N|g.S4o_J,\u0007F\u0001\u0003E!\t)%*D\u0001G\u0015\t9\u0005*A\u0004lmN$xN]3\u000b\u0005%[\u0011\u0001B;uS2L!a\u0013$\u0003\u000f-3\u0016J\u001c3fq\u00061\u0011m\u0019;jm\u0016,\u0012A\u0014\t\u0003'=K!\u0001\u0015\u000b\u0003\u000f\t{w\u000e\\3b]\"\u0012Qa\u000e\u0015\u0005\u000b\u0011\u001bF+A\u0003wC2,X-I\u0001M\u0003\u0011Awn\u001d;\u0002\u000b!|7\u000f\u001e\u0011)\u0005\u001d9\u0004\u0006B\u0004E'f\u000b\u0013!\u0016"
)
public class ProcessSummaryWrapper {
   private final ProcessSummary info;
   @JsonIgnore
   @KVIndex("host")
   private final String host;

   public ProcessSummary info() {
      return this.info;
   }

   @JsonIgnore
   @KVIndex
   private String id() {
      return this.info().id();
   }

   @JsonIgnore
   @KVIndex("active")
   private boolean active() {
      return this.info().isActive();
   }

   public String host() {
      return this.host;
   }

   public ProcessSummaryWrapper(final ProcessSummary info) {
      this.info = info;
      this.host = (String)Utils$.MODULE$.parseHostPort(info.hostPort())._1();
   }
}
