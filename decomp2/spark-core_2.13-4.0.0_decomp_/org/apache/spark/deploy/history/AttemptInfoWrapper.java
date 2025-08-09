package org.apache.spark.deploy.history;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i4QAE\n\u0001'uA\u0001\u0002\n\u0001\u0003\u0006\u0004%\tA\n\u0005\tc\u0001\u0011\t\u0011)A\u0005O!A!\u0007\u0001BC\u0002\u0013\u00051\u0007\u0003\u0005@\u0001\t\u0005\t\u0015!\u00035\u0011!\u0001\u0005A!b\u0001\n\u0003\t\u0005\u0002C#\u0001\u0005\u0003\u0005\u000b\u0011\u0002\"\t\u0011\u0019\u0003!Q1A\u0005\u0002\u001dC\u0001b\u0013\u0001\u0003\u0002\u0003\u0006I\u0001\u0013\u0005\t\u0019\u0002\u0011)\u0019!C\u0001\u001b\"Aq\n\u0001B\u0001B\u0003%a\n\u0003\u0005Q\u0001\t\u0015\r\u0011\"\u0001N\u0011!\t\u0006A!A!\u0002\u0013q\u0005\u0002\u0003*\u0001\u0005\u000b\u0007I\u0011A'\t\u0011M\u0003!\u0011!Q\u0001\n9C\u0001\u0002\u0016\u0001\u0003\u0006\u0004%\t!\u0014\u0005\t+\u0002\u0011\t\u0011)A\u0005\u001d\")a\u000b\u0001C\u0001/\n\u0011\u0012\t\u001e;f[B$\u0018J\u001c4p/J\f\u0007\u000f]3s\u0015\t!R#A\u0004iSN$xN]=\u000b\u0005Y9\u0012A\u00023fa2|\u0017P\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h'\t\u0001a\u0004\u0005\u0002 E5\t\u0001EC\u0001\"\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0003E\u0001\u0004B]f\u0014VMZ\u0001\u0005S:4wn\u0001\u0001\u0016\u0003\u001d\u0002\"\u0001K\u0018\u000e\u0003%R!AK\u0016\u0002\u0005Y\f$B\u0001\u0017.\u0003\r\t\u0007/\u001b\u0006\u0003]]\taa\u001d;biV\u001c\u0018B\u0001\u0019*\u0005Y\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8BiR,W\u000e\u001d;J]\u001a|\u0017!B5oM>\u0004\u0013a\u00027pOB\u000bG\u000f[\u000b\u0002iA\u0011Q\u0007\u0010\b\u0003mi\u0002\"a\u000e\u0011\u000e\u0003aR!!O\u0013\u0002\rq\u0012xn\u001c;?\u0013\tY\u0004%\u0001\u0004Qe\u0016$WMZ\u0005\u0003{y\u0012aa\u0015;sS:<'BA\u001e!\u0003!awn\u001a)bi\"\u0004\u0013\u0001\u00034jY\u0016\u001c\u0016N_3\u0016\u0003\t\u0003\"aH\"\n\u0005\u0011\u0003#\u0001\u0002'p]\u001e\f\u0011BZ5mKNK'0\u001a\u0011\u0002\u00131\f7\u000f^%oI\u0016DX#\u0001%\u0011\u0007}I%)\u0003\u0002KA\t1q\n\u001d;j_:\f!\u0002\\1ti&sG-\u001a=!\u0003%\tG-\\5o\u0003\u000ed7/F\u0001O!\ry\u0012\nN\u0001\u000bC\u0012l\u0017N\\!dYN\u0004\u0013\u0001\u0003<jK^\f5\r\\:\u0002\u0013YLWm^!dYN\u0004\u0013aD1e[&t\u0017i\u00197t\u000fJ|W\u000f]:\u0002!\u0005$W.\u001b8BG2\u001cxI]8vaN\u0004\u0013A\u0004<jK^\f5\r\\:He>,\bo]\u0001\u0010m&,w/Q2mg\u001e\u0013x.\u001e9tA\u00051A(\u001b8jiz\"\u0012\u0002\u0017.\\9v3x\u000f_=\u0011\u0005e\u0003Q\"A\n\t\u000b\u0011\n\u0002\u0019A\u0014\t\u000bI\n\u0002\u0019\u0001\u001b\t\u000b\u0001\u000b\u0002\u0019\u0001\"\t\u000b\u0019\u000b\u0002\u0019\u0001%)\tu{VN\u001c\t\u0003A.l\u0011!\u0019\u0006\u0003E\u000e\f!\"\u00198o_R\fG/[8o\u0015\t!W-\u0001\u0005eCR\f'-\u001b8e\u0015\t1w-A\u0004kC\u000e\\7o\u001c8\u000b\u0005!L\u0017!\u00034bgR,'\u000f_7m\u0015\u0005Q\u0017aA2p[&\u0011A.\u0019\u0002\u0010\u0015N|g\u000eR3tKJL\u0017\r\\5{K\u0006I1m\u001c8uK:$\u0018i]\u0012\u0002_B\u0011\u0001/^\u0007\u0002c*\u0011!o]\u0001\u0005Y\u0006twMC\u0001u\u0003\u0011Q\u0017M^1\n\u0005\u0011\u000b\b\"\u0002'\u0012\u0001\u0004q\u0005\"\u0002)\u0012\u0001\u0004q\u0005\"\u0002*\u0012\u0001\u0004q\u0005\"\u0002+\u0012\u0001\u0004q\u0005"
)
public class AttemptInfoWrapper {
   private final ApplicationAttemptInfo info;
   private final String logPath;
   private final long fileSize;
   private final Option lastIndex;
   private final Option adminAcls;
   private final Option viewAcls;
   private final Option adminAclsGroups;
   private final Option viewAclsGroups;

   public ApplicationAttemptInfo info() {
      return this.info;
   }

   public String logPath() {
      return this.logPath;
   }

   public long fileSize() {
      return this.fileSize;
   }

   public Option lastIndex() {
      return this.lastIndex;
   }

   public Option adminAcls() {
      return this.adminAcls;
   }

   public Option viewAcls() {
      return this.viewAcls;
   }

   public Option adminAclsGroups() {
      return this.adminAclsGroups;
   }

   public Option viewAclsGroups() {
      return this.viewAclsGroups;
   }

   public AttemptInfoWrapper(final ApplicationAttemptInfo info, final String logPath, final long fileSize, @JsonDeserialize(contentAs = Long.class) final Option lastIndex, final Option adminAcls, final Option viewAcls, final Option adminAclsGroups, final Option viewAclsGroups) {
      this.info = info;
      this.logPath = logPath;
      this.fileSize = fileSize;
      this.lastIndex = lastIndex;
      this.adminAcls = adminAcls;
      this.viewAcls = viewAcls;
      this.adminAclsGroups = adminAclsGroups;
      this.viewAclsGroups = viewAclsGroups;
   }
}
