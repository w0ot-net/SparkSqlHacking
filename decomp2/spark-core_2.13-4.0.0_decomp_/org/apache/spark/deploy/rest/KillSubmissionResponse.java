package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2QAB\u0004\u0001\u0017EAQA\u0006\u0001\u0005\u0002aAqA\u0007\u0001A\u0002\u0013\u00051\u0004C\u0004*\u0001\u0001\u0007I\u0011\u0001\u0016\t\rE\u0002\u0001\u0015)\u0003\u001d\u0011\u0015\u0011\u0004\u0001\"\u00154\u0005YY\u0015\u000e\u001c7Tk\nl\u0017n]:j_:\u0014Vm\u001d9p]N,'B\u0001\u0005\n\u0003\u0011\u0011Xm\u001d;\u000b\u0005)Y\u0011A\u00023fa2|\u0017P\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h'\t\u0001!\u0003\u0005\u0002\u0014)5\tq!\u0003\u0002\u0016\u000f\tQ2+\u001e2nSR\u0014Vm\u001d;Qe>$xnY8m%\u0016\u001c\bo\u001c8tK\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u001a!\t\u0019\u0002!\u0001\u0007tk\nl\u0017n]:j_:LE-F\u0001\u001d!\tibE\u0004\u0002\u001fIA\u0011qDI\u0007\u0002A)\u0011\u0011eF\u0001\u0007yI|w\u000e\u001e \u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0002\rA\u0013X\rZ3g\u0013\t9\u0003F\u0001\u0004TiJLgn\u001a\u0006\u0003K\t\n\u0001c];c[&\u001c8/[8o\u0013\u0012|F%Z9\u0015\u0005-z\u0003C\u0001\u0017.\u001b\u0005\u0011\u0013B\u0001\u0018#\u0005\u0011)f.\u001b;\t\u000fA\u001a\u0011\u0011!a\u00019\u0005\u0019\u0001\u0010J\u0019\u0002\u001bM,(-\\5tg&|g.\u00133!\u0003)!wNV1mS\u0012\fG/\u001a\u000b\u0002W\u0001"
)
public class KillSubmissionResponse extends SubmitRestProtocolResponse {
   private String submissionId = null;

   public String submissionId() {
      return this.submissionId;
   }

   public void submissionId_$eq(final String x$1) {
      this.submissionId = x$1;
   }

   public void doValidate() {
      super.doValidate();
      this.assertFieldIsSet(this.submissionId(), "submissionId");
      this.assertFieldIsSet(this.success(), "success");
   }
}
