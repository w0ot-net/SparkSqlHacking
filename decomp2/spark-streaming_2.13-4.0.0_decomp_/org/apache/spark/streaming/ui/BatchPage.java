package org.apache.spark.streaming.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.NoSuchElementException;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.streaming.Time;
import org.apache.spark.ui.WebUIPage;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractSeq;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.xml.Comment;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;
import scala.xml.Null.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-h!B\f\u0019\u0001a\u0011\u0003\u0002\u0003\u0015\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\u000b9\u0002A\u0011A\u0018\t\u000fI\u0002!\u0019!C\u0005g!1q\u0007\u0001Q\u0001\nQBq\u0001\u000f\u0001C\u0002\u0013%\u0011\b\u0003\u0004A\u0001\u0001\u0006IA\u000f\u0005\u0006\u0003\u0002!IA\u0011\u0005\u0006/\u0002!I\u0001\u0017\u0005\b\u0003\u001b\u0001A\u0011BA\b\u0011\u001d\t9\u0002\u0001C\u0005\u00033Aq!a\u000f\u0001\t\u0013\ti\u0004C\u0004\u0002N\u0001!I!a\u0014\t\u000f\u0005m\u0003\u0001\"\u0003\u0002^!9\u00111\r\u0001\u0005\n\u0005\u0015\u0004bBAJ\u0001\u0011%\u0011Q\u0013\u0005\b\u0003G\u0003A\u0011BAS\u0011\u001d\tY\u000b\u0001C\u0005\u0003[Cq!a/\u0001\t\u0003\ti\fC\u0004\u0002B\u0002!\t!a1\t\u000f\u0005E\u0007\u0001\"\u0001\u0002T\"9\u0011\u0011\u001c\u0001\u0005\n\u0005m\u0007bBAq\u0001\u0011%\u00111\u001d\u0002\n\u0005\u0006$8\r\u001b)bO\u0016T!!\u0007\u000e\u0002\u0005UL'BA\u000e\u001d\u0003%\u0019HO]3b[&twM\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h'\t\u00011\u0005\u0005\u0002%M5\tQE\u0003\u0002\u001a9%\u0011q%\n\u0002\n/\u0016\u0014W+\u0013)bO\u0016\fa\u0001]1sK:$8\u0001\u0001\t\u0003W1j\u0011\u0001G\u0005\u0003[a\u0011Ab\u0015;sK\u0006l\u0017N\\4UC\n\fa\u0001P5oSRtDC\u0001\u00192!\tY\u0003\u0001C\u0003)\u0005\u0001\u0007!&A\ttiJ,\u0017-\\5oO2K7\u000f^3oKJ,\u0012\u0001\u000e\t\u0003WUJ!A\u000e\r\u00039M#(/Z1nS:<'j\u001c2Qe><'/Z:t\u0019&\u001cH/\u001a8fe\u0006\u00112\u000f\u001e:fC6Lgn\u001a'jgR,g.\u001a:!\u0003\u0015\u0019Ho\u001c:f+\u0005Q\u0004CA\u001e?\u001b\u0005a$BA\u001f\u001d\u0003\u0019\u0019H/\u0019;vg&\u0011q\b\u0010\u0002\u000f\u0003B\u00048\u000b^1ukN\u001cFo\u001c:f\u0003\u0019\u0019Ho\u001c:fA\u000591m\u001c7v[:\u001cX#A\"\u0011\u0007\u0011s\u0015K\u0004\u0002F\u0017:\u0011a)S\u0007\u0002\u000f*\u0011\u0001*K\u0001\u0007yI|w\u000e\u001e \n\u0003)\u000bQa]2bY\u0006L!\u0001T'\u0002\u000fA\f7m[1hK*\t!*\u0003\u0002P!\n\u00191+Z9\u000b\u00051k\u0005C\u0001*V\u001b\u0005\u0019&B\u0001+N\u0003\rAX\u000e\\\u0005\u0003-N\u0013AAT8eK\u0006qq-\u001a8fe\u0006$XMS8c%><H#C\"ZK*dg\u000f`A\u0002\u0011\u0015Q\u0006\u00021\u0001\\\u0003\u001d\u0011X-];fgR\u0004\"\u0001X2\u000e\u0003uS!AX0\u0002\t!$H\u000f\u001d\u0006\u0003A\u0006\fqa]3sm2,GOC\u0001c\u0003\u001dQ\u0017m[1si\u0006L!\u0001Z/\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f\u001e\u0005\u0006M\"\u0001\raZ\u0001\r_V$\b/\u001e;Pa\u0012\u000bG/\u0019\t\u0003W!L!!\u001b\r\u0003+=+H\u000f];u\u001fB,'/\u0019;j_:,\u0016\nR1uC\")1\u000e\u0003a\u0001\u0007\u0006\u0019r.\u001e;qkR|\u0005\u000fR3tGJL\u0007\u000f^5p]\")Q\u000e\u0003a\u0001]\u0006Ibm\u001c:nCR$X\rZ(viB,Ho\u00149EkJ\fG/[8o!\ty7O\u0004\u0002qcB\u0011a)T\u0005\u0003e6\u000ba\u0001\u0015:fI\u00164\u0017B\u0001;v\u0005\u0019\u0019FO]5oO*\u0011!/\u0014\u0005\u0006o\"\u0001\r\u0001_\u0001\u001a]Vl7\u000b]1sW*{'MU8xg&sw*\u001e;qkR|\u0005\u000f\u0005\u0002zu6\tQ*\u0003\u0002|\u001b\n\u0019\u0011J\u001c;\t\u000buD\u0001\u0019\u0001@\u0002\u0015%\u001ch)\u001b:tiJ{w\u000f\u0005\u0002z\u007f&\u0019\u0011\u0011A'\u0003\u000f\t{w\u000e\\3b]\"9\u0011Q\u0001\u0005A\u0002\u0005\u001d\u0011!\u00046pE&#w+\u001b;i\t\u0006$\u0018\rE\u0002,\u0003\u0013I1!a\u0003\u0019\u0005Q\u0019\u0006/\u0019:l\u0015>\u0014\u0017\nZ,ji\",\u0016\nR1uC\u0006\u0019s-\u001a8fe\u0006$XmT;uaV$x\n\u001d*po^KG\u000f[8viN\u0003\u0018M]6K_\n\u001cHcB\"\u0002\u0012\u0005M\u0011Q\u0003\u0005\u0006M&\u0001\ra\u001a\u0005\u0006W&\u0001\ra\u0011\u0005\u0006[&\u0001\rA\\\u0001\u0015O\u0016tWM]1uK:{'/\\1m\u0015>\u0014'k\\<\u0015\u001f\r\u000bY\"!\b\u0002 \u0005\u0005\u00121EA\u0013\u0003OAQA\u0017\u0006A\u0002mCQA\u001a\u0006A\u0002\u001dDQa\u001b\u0006A\u0002\rCQ!\u001c\u0006A\u00029DQa\u001e\u0006A\u0002aDQ! \u0006A\u0002yDq!!\u000b\u000b\u0001\u0004\tY#\u0001\u0005ta\u0006\u00148NS8c!\u0011\ti#a\u000e\u000e\u0005\u0005=\"\u0002BA\u0019\u0003g\t!A^\u0019\u000b\u0007\u0005UB(A\u0002ba&LA!!\u000f\u00020\t9!j\u001c2ECR\f\u0017!F4f]\u0016\u0014\u0018\r^3Ee>\u0004\b/\u001a3K_\n\u0014vn\u001e\u000b\u000e\u0007\u0006}\u0012\u0011IA\"\u0003\u000b\n9%!\u0013\t\u000b\u0019\\\u0001\u0019A4\t\u000b-\\\u0001\u0019A\"\t\u000b5\\\u0001\u0019\u00018\t\u000b]\\\u0001\u0019\u0001=\t\u000bu\\\u0001\u0019\u0001@\t\r\u0005-3\u00021\u0001y\u0003\u0015QwNY%e\u0003U9WM\\3sCR,w*\u001e;qkR|\u0005/\u00133S_^$raQA)\u0003'\n)\u0006C\u0003[\u0019\u0001\u00071\fC\u0003g\u0019\u0001\u0007q\rC\u0004\u0002X1\u0001\r!!\u0017\u0002\u0013M\u0004\u0018M]6K_\n\u001c\b\u0003\u0002#O\u0003\u000f\t1dZ3oKJ\fG/Z(viB,Ho\u00149EKN\u001c'/\u001b9uS>tGcA\"\u0002`!1\u0011\u0011M\u0007A\u0002\u001d\f\u0001b\\;uaV$x\n]\u0001\u000bO\u0016$(j\u001c2ECR\fG\u0003BA4\u0003[\u0002R!_A5\u0003WI1!a\u001bN\u0005\u0019y\u0005\u000f^5p]\"9\u0011q\u000e\bA\u0002\u0005E\u0014AC:qCJ\\'j\u001c2JIB!\u00111OAG\u001d\u0011\t)(!#\u000f\t\u0005]\u0014q\u0011\b\u0005\u0003s\n)I\u0004\u0003\u0002|\u0005\re\u0002BA?\u0003\u0003s1ARA@\u0013\u0005\t\u0013BA\u0010!\u0013\tib$\u0003\u0002\u001c9%\u0011\u0011DG\u0005\u0004\u0003\u0017C\u0012\u0001H*ue\u0016\fW.\u001b8h\u0015>\u0014\u0007K]8he\u0016\u001c8\u000fT5ti\u0016tWM]\u0005\u0005\u0003\u001f\u000b\tJ\u0001\u0006Ta\u0006\u00148NS8c\u0013\u0012T1!a#\u0019\u000319W\r^*uC\u001e,G)\u0019;b)\u0011\t9*a(\u0011\u000be\fI'!'\u0011\t\u00055\u00121T\u0005\u0005\u0003;\u000byCA\u0005Ti\u0006<W\rR1uC\"1\u0011\u0011U\bA\u0002a\fqa\u001d;bO\u0016LE-\u0001\u0012hK:,'/\u0019;f\u001fV$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]N#\u0018\r^;t\r>\u0014X+\u0013\u000b\u0004]\u0006\u001d\u0006BBAU!\u0001\u0007a.A\u0004gC&dWO]3\u0002!\u001d,g.\u001a:bi\u0016TuN\u0019+bE2,G#B\"\u00020\u0006E\u0006\"\u0002.\u0012\u0001\u0004Y\u0006bBAZ#\u0001\u0007\u0011QW\u0001\fE\u0006$8\r[+J\t\u0006$\u0018\rE\u0002,\u0003oK1!!/\u0019\u0005-\u0011\u0015\r^2i+&#\u0015\r^1\u0002\rI,g\u000eZ3s)\r\u0019\u0015q\u0018\u0005\u00065J\u0001\raW\u0001\u001bO\u0016tWM]1uK&s\u0007/\u001e;NKR\fG-\u0019;b)\u0006\u0014G.\u001a\u000b\u0004\u0007\u0006\u0015\u0007bBAd'\u0001\u0007\u0011\u0011Z\u0001\u000fS:\u0004X\u000f^'fi\u0006$\u0017\r^1t!\u0011!e*a3\u0011\u000be\fi\r\u001f8\n\u0007\u0005=WJ\u0001\u0004UkBdWMM\u0001\u0019O\u0016tWM]1uK&s\u0007/\u001e;NKR\fG-\u0019;b%><HcA\"\u0002V\"9\u0011q\u001b\u000bA\u0002\u0005-\u0017!D5oaV$X*\u001a;bI\u0006$\u0018-A\rnKR\fG-\u0019;b\t\u0016\u001c8M]5qi&|g\u000eV8I)6cEcA\"\u0002^\"1\u0011q\\\u000bA\u00029\f1#\\3uC\u0012\fG/\u0019#fg\u000e\u0014\u0018\u000e\u001d;j_:\f!c\\;uaV$x\n]*uCR,8oQ3mYR)1)!:\u0002h\"1\u0011\u0011\r\fA\u0002\u001dDa!!;\u0017\u0001\u0004A\u0018a\u0002:poN\u0004\u0018M\u001c"
)
public class BatchPage extends WebUIPage {
   private final StreamingTab parent;
   private final StreamingJobProgressListener streamingListener;
   private final AppStatusStore store;

   private StreamingJobProgressListener streamingListener() {
      return this.streamingListener;
   }

   private AppStatusStore store() {
      return this.store;
   }

   private Seq columns() {
      NodeBuffer $buf = new NodeBuffer();
      Null var10005 = .MODULE$;
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Output Op Id"));
      $buf.$amp$plus(new Elem((String)null, "th", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      var10005 = .MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Description"));
      $buf.$amp$plus(new Elem((String)null, "th", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      var10005 = .MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Output Op Duration "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.tooltip("Time taken for all the jobs of this batch to finish processing from the time they were submitted.", "top"));
      $buf.$amp$plus(new Elem((String)null, "th", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      var10005 = .MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Status"));
      $buf.$amp$plus(new Elem((String)null, "th", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      var10005 = .MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Job Id"));
      $buf.$amp$plus(new Elem((String)null, "th", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      var10005 = .MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Job Duration "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.tooltip("Time taken from submission time to completion time of the job", "top"));
      $buf.$amp$plus(new Elem((String)null, "th", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      MetaData $md = .MODULE$;
      MetaData var13 = new UnprefixedAttribute("class", new Text("sorttable_nosort"), $md);
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Stages: Succeeded/Total"));
      $buf.$amp$plus(new Elem((String)null, "th", var13, var10006, false, var10008.seqToNodeSeq($buf)));
      MetaData $md = .MODULE$;
      MetaData var14 = new UnprefixedAttribute("class", new Text("sorttable_nosort"), $md);
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Tasks (for all stages): Succeeded/Total"));
      $buf.$amp$plus(new Elem((String)null, "th", var14, var10006, false, var10008.seqToNodeSeq($buf)));
      var10005 = .MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Error"));
      $buf.$amp$plus(new Elem((String)null, "th", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      return scala.xml.NodeSeq..MODULE$.seqToNodeSeq($buf);
   }

   private Seq generateJobRow(final HttpServletRequest request, final OutputOperationUIData outputOpData, final Seq outputOpDescription, final String formattedOutputOpDuration, final int numSparkJobRowsInOutputOp, final boolean isFirstRow, final SparkJobIdWithUIData jobIdWithData) {
      return jobIdWithData.jobData().isDefined() ? this.generateNormalJobRow(request, outputOpData, outputOpDescription, formattedOutputOpDuration, numSparkJobRowsInOutputOp, isFirstRow, (JobData)jobIdWithData.jobData().get()) : this.generateDroppedJobRow(outputOpData, outputOpDescription, formattedOutputOpDuration, numSparkJobRowsInOutputOp, isFirstRow, jobIdWithData.sparkJobId());
   }

   private Seq generateOutputOpRowWithoutSparkJobs(final OutputOperationUIData outputOpData, final Seq outputOpDescription, final String formattedOutputOpDuration) {
      Null var10004 = .MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = .MODULE$;
      MetaData var14 = new UnprefixedAttribute("class", new Text("output-op-id-cell"), $md);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(Integer.toString(outputOpData.id()));
      $buf.$amp$plus(new Elem((String)null, "td", var14, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(outputOpDescription);
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(formattedOutputOpDuration);
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(this.outputOpStatusCell(outputOpData, 1));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Comment(" Job Id "));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("-"));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Comment(" Duration "));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("-"));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Comment(" Stages: Succeeded/Total "));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("-"));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Comment(" Tasks (for all stages): Succeeded/Total "));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("-"));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Comment(" Error "));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("-"));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq generateNormalJobRow(final HttpServletRequest request, final OutputOperationUIData outputOpData, final Seq outputOpDescription, final String formattedOutputOpDuration, final int numSparkJobRowsInOutputOp, final boolean isFirstRow, final JobData sparkJob) {
      Option duration = sparkJob.submissionTime().map((start) -> BoxesRunTime.boxToLong($anonfun$generateNormalJobRow$1(sparkJob, start)));
      String lastFailureReason = (String)((IterableOps)((IterableOps)((IterableOps)((IterableOps)((IterableOps)sparkJob.stageIds().sorted(scala.math.Ordering.Int..MODULE$.reverse())).flatMap((stageId) -> $anonfun$generateNormalJobRow$4(this, BoxesRunTime.unboxToInt(stageId)))).dropWhile((x$2) -> BoxesRunTime.boxToBoolean($anonfun$generateNormalJobRow$5(x$2)))).take(1)).flatMap((info) -> info.failureReason())).headOption().getOrElse(() -> "");
      String formattedDuration = (String)duration.map((d) -> $anonfun$generateNormalJobRow$8(BoxesRunTime.unboxToLong(d))).getOrElse(() -> "-");
      String var10000 = org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri(request, this.parent.basePath(), org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri$default$3());
      String detailUrl = var10000 + "/jobs/job/?id=" + sparkJob.jobId();
      Object var40;
      if (isFirstRow) {
         NodeBuffer $buf = new NodeBuffer();
         MetaData $md = .MODULE$;
         MetaData var31 = new UnprefixedAttribute("rowspan", Integer.toString(numSparkJobRowsInOutputOp), $md);
         var31 = new UnprefixedAttribute("class", new Text("output-op-id-cell"), var31);
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(Integer.toString(outputOpData.id()));
         $buf.$amp$plus(new Elem((String)null, "td", var31, var10006, false, var10008.seqToNodeSeq($buf)));
         MetaData $md = .MODULE$;
         MetaData var33 = new UnprefixedAttribute("rowspan", Integer.toString(numSparkJobRowsInOutputOp), $md);
         var10006 = scala.xml.TopScope..MODULE$;
         var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(outputOpDescription);
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "td", var33, var10006, false, var10008.seqToNodeSeq($buf)));
         MetaData $md = .MODULE$;
         MetaData var34 = new UnprefixedAttribute("rowspan", Integer.toString(numSparkJobRowsInOutputOp), $md);
         var10006 = scala.xml.TopScope..MODULE$;
         var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(formattedOutputOpDuration);
         $buf.$amp$plus(new Elem((String)null, "td", var34, var10006, false, var10008.seqToNodeSeq($buf)));
         var40 = (AbstractSeq)$buf.$plus$plus(this.outputOpStatusCell(outputOpData, numSparkJobRowsInOutputOp));
      } else {
         var40 = scala.collection.immutable.Nil..MODULE$;
      }

      AbstractSeq prefixCells = (AbstractSeq)var40;
      Null var10004 = .MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(prefixCells);
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = .MODULE$;
      MetaData var35 = new UnprefixedAttribute("sorttable_customkey", Integer.toString(sparkJob.jobId()), $md);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = .MODULE$;
      MetaData var36 = new UnprefixedAttribute("href", detailUrl, $md);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(sparkJob.jobId()));
      $buf.$amp$plus(sparkJob.jobGroup().map((id) -> "(" + id + ")").getOrElse(() -> ""));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "a", var36, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var35, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = .MODULE$;
      MetaData var37 = new UnprefixedAttribute("sorttable_customkey", duration.getOrElse((JFunction0.mcJ.sp)() -> Long.MAX_VALUE).toString(), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(formattedDuration);
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var37, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = .MODULE$;
      MetaData var38 = new UnprefixedAttribute("class", new Text("stage-progress-cell"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(sparkJob.numCompletedStages()));
      $buf.$amp$plus(new Text("/"));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(sparkJob.stageIds().size() - sparkJob.numSkippedStages()));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(sparkJob.numFailedStages() > 0 ? "(" + sparkJob.numFailedStages() + " failed)" : BoxedUnit.UNIT);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(sparkJob.numSkippedStages() > 0 ? "(" + sparkJob.numSkippedStages() + " skipped)" : BoxedUnit.UNIT);
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var38, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = .MODULE$;
      MetaData var39 = new UnprefixedAttribute("class", new Text("progress-cell"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.makeProgressBar(sparkJob.numActiveTasks(), sparkJob.numCompletedTasks(), sparkJob.numFailedTasks(), sparkJob.numSkippedTasks(), sparkJob.killedTasksSummary(), sparkJob.numTasks() - sparkJob.numSkippedTasks()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var39, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(UIUtils$.MODULE$.failureReasonCell(lastFailureReason, UIUtils$.MODULE$.failureReasonCell$default$2(), UIUtils$.MODULE$.failureReasonCell$default$3()));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq generateDroppedJobRow(final OutputOperationUIData outputOpData, final Seq outputOpDescription, final String formattedOutputOpDuration, final int numSparkJobRowsInOutputOp, final boolean isFirstRow, final int jobId) {
      Object var10000;
      if (isFirstRow) {
         NodeBuffer $buf = new NodeBuffer();
         MetaData $md = .MODULE$;
         MetaData var22 = new UnprefixedAttribute("rowspan", Integer.toString(numSparkJobRowsInOutputOp), $md);
         var22 = new UnprefixedAttribute("class", new Text("output-op-id-cell"), var22);
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(Integer.toString(outputOpData.id()));
         $buf.$amp$plus(new Elem((String)null, "td", var22, var10006, false, var10008.seqToNodeSeq($buf)));
         MetaData $md = .MODULE$;
         MetaData var24 = new UnprefixedAttribute("rowspan", Integer.toString(numSparkJobRowsInOutputOp), $md);
         var10006 = scala.xml.TopScope..MODULE$;
         var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(outputOpDescription);
         $buf.$amp$plus(new Elem((String)null, "td", var24, var10006, false, var10008.seqToNodeSeq($buf)));
         MetaData $md = .MODULE$;
         MetaData var25 = new UnprefixedAttribute("rowspan", Integer.toString(numSparkJobRowsInOutputOp), $md);
         var10006 = scala.xml.TopScope..MODULE$;
         var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(formattedOutputOpDuration);
         $buf.$amp$plus(new Elem((String)null, "td", var25, var10006, false, var10008.seqToNodeSeq($buf)));
         var10000 = (AbstractSeq)$buf.$plus$plus(this.outputOpStatusCell(outputOpData, numSparkJobRowsInOutputOp));
      } else {
         var10000 = scala.collection.immutable.Nil..MODULE$;
      }

      AbstractSeq prefixCells = (AbstractSeq)var10000;
      Null var10004 = .MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(prefixCells);
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = .MODULE$;
      MetaData var26 = new UnprefixedAttribute("sorttable_customkey", Integer.toString(jobId), $md);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(jobId >= 0 ? Integer.toString(jobId) : "-");
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var26, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Comment(" Duration "));
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("-"));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Comment(" Stages: Succeeded/Total "));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("-"));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Comment(" Tasks (for all stages): Succeeded/Total "));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("-"));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Comment(" Error "));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("-"));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq generateOutputOpIdRow(final HttpServletRequest request, final OutputOperationUIData outputOpData, final Seq sparkJobs) {
      String formattedOutputOpDuration = outputOpData.duration().isEmpty() ? "-" : org.apache.spark.ui.UIUtils..MODULE$.formatDuration(BoxesRunTime.unboxToLong(outputOpData.duration().get()));
      Seq description = this.generateOutputOpDescription(outputOpData);
      if (sparkJobs.isEmpty()) {
         return this.generateOutputOpRowWithoutSparkJobs(outputOpData, description, formattedOutputOpDuration);
      } else {
         Seq firstRow = this.generateJobRow(request, outputOpData, description, formattedOutputOpDuration, sparkJobs.size(), true, (SparkJobIdWithUIData)sparkJobs.head());
         Seq tailRows = (Seq)((IterableOps)sparkJobs.tail()).map((sparkJob) -> this.generateJobRow(request, outputOpData, description, formattedOutputOpDuration, sparkJobs.size(), false, sparkJob));
         return (Seq)((IterableOps)firstRow.$plus$plus(tailRows)).flatten(scala.Predef..MODULE$.$conforms());
      }
   }

   private Seq generateOutputOpDescription(final OutputOperationUIData outputOp) {
      Null var10004 = .MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(outputOp.name());
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = .MODULE$;
      MetaData var8 = new UnprefixedAttribute("class", new Text("expand-details"), $md);
      var8 = new UnprefixedAttribute("onclick", new Text("this.parentNode.querySelector('.stage-details').classList.toggle('collapsed')"), var8);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          +details\n      "));
      $buf.$amp$plus(new Elem((String)null, "span", var8, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = .MODULE$;
      MetaData var10 = new UnprefixedAttribute("class", new Text("stage-details collapsed"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      Null var10022 = .MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(outputOp.description());
      $buf.$amp$plus(new Elem((String)null, "pre", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "div", var10, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Option getJobData(final int sparkJobId) {
      Object var10000;
      try {
         var10000 = new Some(this.store().job(sparkJobId));
      } catch (NoSuchElementException var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   private Option getStageData(final int stageId) {
      Object var10000;
      try {
         var10000 = new Some(this.store().lastStageAttempt(stageId));
      } catch (NoSuchElementException var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   private String generateOutputOperationStatusForUI(final String failure) {
      if (failure.startsWith("org.apache.spark.SparkException")) {
         return "Failed due to Spark job error\n" + failure;
      } else {
         int nextLineIndex = failure.indexOf("\n");
         if (nextLineIndex < 0) {
            nextLineIndex = failure.length();
         }

         String firstLine = failure.substring(0, nextLineIndex);
         return "Failed due to error: " + firstLine + "\n" + failure;
      }
   }

   private Seq generateJobTable(final HttpServletRequest request, final BatchUIData batchUIData) {
      Map outputOpIdToSparkJobIds = (Map)batchUIData.outputOpIdSparkJobIdPairs().groupBy((x$3) -> BoxesRunTime.boxToInteger($anonfun$generateJobTable$1(x$3))).map((x0$1) -> {
         if (x0$1 != null) {
            int outputOpId = x0$1._1$mcI$sp();
            Iterable outputOpIdAndSparkJobIds = (Iterable)x0$1._2();
            return new Tuple2(BoxesRunTime.boxToInteger(outputOpId), ((IterableOnceOps)outputOpIdAndSparkJobIds.map((x$4) -> BoxesRunTime.boxToInteger($anonfun$generateJobTable$3(x$4)))).toSeq().sorted(scala.math.Ordering.Int..MODULE$));
         } else {
            throw new MatchError(x0$1);
         }
      });
      Seq outputOps = (Seq)batchUIData.outputOperations().map((x0$2) -> {
         if (x0$2 != null) {
            int outputOpId = x0$2._1$mcI$sp();
            OutputOperationUIData outputOperation = (OutputOperationUIData)x0$2._2();
            Seq sparkJobIds = (Seq)outputOpIdToSparkJobIds.getOrElse(BoxesRunTime.boxToInteger(outputOpId), () -> (Seq)scala.package..MODULE$.Seq().empty());
            return new Tuple2(outputOperation, sparkJobIds);
         } else {
            throw new MatchError(x0$2);
         }
      }).toSeq().sortBy((x$5) -> BoxesRunTime.boxToInteger($anonfun$generateJobTable$6(x$5)), scala.math.Ordering.Int..MODULE$);
      Seq outputOpWithJobs = (Seq)outputOps.map((x0$3) -> {
         if (x0$3 != null) {
            OutputOperationUIData outputOpData = (OutputOperationUIData)x0$3._1();
            Seq sparkJobIds = (Seq)x0$3._2();
            return new Tuple2(outputOpData, sparkJobIds.map((jobId) -> $anonfun$generateJobTable$8(this, BoxesRunTime.unboxToInt(jobId))));
         } else {
            throw new MatchError(x0$3);
         }
      });
      MetaData $md = .MODULE$;
      MetaData var10 = new UnprefixedAttribute("class", new Text("table table-bordered table-striped table-sm"), $md);
      var10 = new UnprefixedAttribute("id", new Text("batch-job-table"), var10);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(this.columns());
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "thead", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(outputOpWithJobs.map((x0$4) -> {
         if (x0$4 != null) {
            OutputOperationUIData outputOpData = (OutputOperationUIData)x0$4._1();
            Seq sparkJobs = (Seq)x0$4._2();
            return this.generateOutputOpIdRow(request, outputOpData, sparkJobs);
         } else {
            throw new MatchError(x0$4);
         }
      }));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "tbody", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "table", var10, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public Seq render(final HttpServletRequest request) {
      synchronized(this.streamingListener()){}

      Seq var3;
      try {
         Time batchTime = (Time)scala.Option..MODULE$.apply(request.getParameter("id")).map((id) -> new Time(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(id)))).getOrElse(() -> {
            throw new IllegalArgumentException("Missing id parameter");
         });
         String formattedBatchTime = org.apache.spark.ui.UIUtils..MODULE$.formatBatchTime(batchTime.milliseconds(), this.streamingListener().batchDuration(), org.apache.spark.ui.UIUtils..MODULE$.formatBatchTime$default$3(), org.apache.spark.ui.UIUtils..MODULE$.formatBatchTime$default$4());
         BatchUIData batchUIData = (BatchUIData)this.streamingListener().getBatchUIData(batchTime).getOrElse(() -> {
            throw new IllegalArgumentException("Batch " + formattedBatchTime + " does not exist");
         });
         String formattedSchedulingDelay = (String)batchUIData.schedulingDelay().map((milliseconds) -> $anonfun$render$4(BoxesRunTime.unboxToLong(milliseconds))).getOrElse(() -> "-");
         String formattedProcessingTime = (String)batchUIData.processingDelay().map((milliseconds) -> $anonfun$render$6(BoxesRunTime.unboxToLong(milliseconds))).getOrElse(() -> "-");
         String formattedTotalDelay = (String)batchUIData.totalDelay().map((milliseconds) -> $anonfun$render$8(BoxesRunTime.unboxToLong(milliseconds))).getOrElse(() -> "-");
         Seq inputMetadatas = ((IterableOnceOps)batchUIData.streamIdToInputInfo().values().flatMap((inputInfo) -> inputInfo.metadataDescription().map((desc) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(inputInfo.inputStreamId())), desc)))).toSeq();
         Elem var10000 = new Elem;
         Null var10004 = .MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = .MODULE$;
         MetaData var30 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
         Elem var10009 = new Elem;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10022 = .MODULE$;
         TopScope var10023 = scala.xml.TopScope..MODULE$;
         NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         Null var10031 = .MODULE$;
         TopScope var10032 = scala.xml.TopScope..MODULE$;
         NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Batch Duration: "));
         $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.formatDuration(this.streamingListener().batchDuration()));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10022 = .MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         var10031 = .MODULE$;
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Input data size: "));
         $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(BoxesRunTime.boxToLong(batchUIData.numRecords()));
         $buf.$amp$plus(new Text(" records\n          "));
         $buf.$amp$plus(new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10022 = .MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         var10031 = .MODULE$;
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Scheduling delay: "));
         $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(formattedSchedulingDelay);
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10022 = .MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         var10031 = .MODULE$;
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Processing time: "));
         $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(formattedProcessingTime);
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10022 = .MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         var10031 = .MODULE$;
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Total delay: "));
         $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(formattedTotalDelay);
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         Object var10018;
         if (inputMetadatas.nonEmpty()) {
            var10022 = .MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            var10031 = .MODULE$;
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Input Metadata:"));
            $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(this.generateInputMetadataTable(inputMetadatas));
            $buf.$amp$plus(new Text("\n              "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n        "));
         var10009.<init>((String)null, "ul", var30, var10014, false, var10016.seqToNodeSeq($buf));
         $buf.$amp$plus(var10009);
         $buf.$amp$plus(new Text("\n      "));
         var10000.<init>((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
         NodeSeq summary = var10000;
         NodeSeq content = summary.$plus$plus(this.generateJobTable(request, batchUIData));
         var3 = org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage(request, "Details of batch at " + formattedBatchTime, () -> content, this.parent, org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage$default$5(), org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage$default$6(), org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage$default$7());
      } catch (Throwable var29) {
         throw var29;
      }

      return var3;
   }

   public Seq generateInputMetadataTable(final Seq inputMetadatas) {
      MetaData $md = .MODULE$;
      MetaData var9 = new UnprefixedAttribute("class", org.apache.spark.ui.UIUtils..MODULE$.TABLE_CLASS_STRIPED_SORTABLE(), $md);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      Null var10022 = .MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      Null var10031 = .MODULE$;
      TopScope var10032 = scala.xml.TopScope..MODULE$;
      NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Input"));
      $buf.$amp$plus(new Elem((String)null, "th", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      var10031 = .MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Metadata "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.tooltip("Batch Input Details", "right"));
      $buf.$amp$plus(new Elem((String)null, "th", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "tr", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "thead", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(inputMetadatas.flatMap((inputMetadata) -> this.generateInputMetadataRow(inputMetadata)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "tbody", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "table", var9, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public Seq generateInputMetadataRow(final Tuple2 inputMetadata) {
      int streamId = inputMetadata._1$mcI$sp();
      Null var10004 = .MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(this.streamingListener().streamName(streamId).getOrElse(() -> "Stream-" + streamId));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(this.metadataDescriptionToHTML((String)inputMetadata._2()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq metadataDescriptionToHTML(final String metadataDescription) {
      return scala.xml.Unparsed..MODULE$.apply(StringEscapeUtils.escapeHtml4(metadataDescription).replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("\n", "<br/>"));
   }

   private Seq outputOpStatusCell(final OutputOperationUIData outputOp, final int rowspan) {
      Option var4 = outputOp.failureReason();
      if (var4 instanceof Some var5) {
         String failureReason = (String)var5.value();
         String failureReasonForUI = UIUtils$.MODULE$.createOutputOperationFailureForUI(failureReason);
         return UIUtils$.MODULE$.failureReasonCell(failureReasonForUI, rowspan, false);
      } else if (scala.None..MODULE$.equals(var4)) {
         if (outputOp.endTime().isEmpty()) {
            MetaData $md = .MODULE$;
            MetaData var12 = new UnprefixedAttribute("rowspan", Integer.toString(rowspan), $md);
            TopScope var14 = scala.xml.TopScope..MODULE$;
            NodeSeq var15 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("-"));
            return new Elem((String)null, "td", var12, var14, false, var15.seqToNodeSeq($buf));
         } else {
            MetaData $md = .MODULE$;
            MetaData var13 = new UnprefixedAttribute("rowspan", Integer.toString(rowspan), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Succeeded"));
            return new Elem((String)null, "td", var13, var10005, false, var10007.seqToNodeSeq($buf));
         }
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$generateNormalJobRow$2(final Date x$1) {
      return x$1.getTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$generateNormalJobRow$1(final JobData sparkJob$1, final Date start) {
      long end = BoxesRunTime.unboxToLong(sparkJob$1.completionTime().map((x$1) -> BoxesRunTime.boxToLong($anonfun$generateNormalJobRow$2(x$1))).getOrElse((JFunction0.mcJ.sp)() -> System.currentTimeMillis()));
      return end - start.getTime();
   }

   // $FF: synthetic method
   public static final Option $anonfun$generateNormalJobRow$4(final BatchPage $this, final int stageId) {
      return $this.getStageData(stageId);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$generateNormalJobRow$5(final StageData x$2) {
      boolean var2;
      label23: {
         Option var10000 = x$2.failureReason();
         None var1 = scala.None..MODULE$;
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

   // $FF: synthetic method
   public static final String $anonfun$generateNormalJobRow$8(final long d) {
      return org.apache.spark.ui.UIUtils..MODULE$.formatDuration(d);
   }

   // $FF: synthetic method
   public static final int $anonfun$generateJobTable$1(final OutputOpIdAndSparkJobId x$3) {
      return x$3.outputOpId();
   }

   // $FF: synthetic method
   public static final int $anonfun$generateJobTable$3(final OutputOpIdAndSparkJobId x$4) {
      return x$4.sparkJobId();
   }

   // $FF: synthetic method
   public static final int $anonfun$generateJobTable$6(final Tuple2 x$5) {
      return ((OutputOperationUIData)x$5._1()).id();
   }

   // $FF: synthetic method
   public static final SparkJobIdWithUIData $anonfun$generateJobTable$8(final BatchPage $this, final int jobId) {
      return new SparkJobIdWithUIData(jobId, $this.getJobData(jobId));
   }

   // $FF: synthetic method
   public static final String $anonfun$render$4(final long milliseconds) {
      return org.apache.spark.ui.UIUtils..MODULE$.formatDuration(milliseconds);
   }

   // $FF: synthetic method
   public static final String $anonfun$render$6(final long milliseconds) {
      return org.apache.spark.ui.UIUtils..MODULE$.formatDuration(milliseconds);
   }

   // $FF: synthetic method
   public static final String $anonfun$render$8(final long milliseconds) {
      return org.apache.spark.ui.UIUtils..MODULE$.formatDuration(milliseconds);
   }

   public BatchPage(final StreamingTab parent) {
      super("batch");
      this.parent = parent;
      this.streamingListener = parent.listener();
      this.store = parent.parent().store();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
