package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2QAB\u0004\u0001\u0017EAQA\u0006\u0001\u0005\u0002aAqA\u0007\u0001A\u0002\u0013\u00051\u0004C\u0004*\u0001\u0001\u0007I\u0011\u0001\u0016\t\rE\u0002\u0001\u0015)\u0003\u001d\u0011\u0015\u0011\u0004\u0001\"\u00154\u0005a\u0019%/Z1uKN+(-\\5tg&|gNU3ta>t7/\u001a\u0006\u0003\u0011%\tAA]3ti*\u0011!bC\u0001\u0007I\u0016\u0004Hn\\=\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\"\u0001\u0001\n\u0011\u0005M!R\"A\u0004\n\u0005U9!AG*vE6LGOU3tiB\u0013x\u000e^8d_2\u0014Vm\u001d9p]N,\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003e\u0001\"a\u0005\u0001\u0002\u0019M,(-\\5tg&|g.\u00133\u0016\u0003q\u0001\"!\b\u0014\u000f\u0005y!\u0003CA\u0010#\u001b\u0005\u0001#BA\u0011\u0018\u0003\u0019a$o\\8u})\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\u00051\u0001K]3eK\u001aL!a\n\u0015\u0003\rM#(/\u001b8h\u0015\t)#%\u0001\ttk\nl\u0017n]:j_:LEm\u0018\u0013fcR\u00111f\f\t\u0003Y5j\u0011AI\u0005\u0003]\t\u0012A!\u00168ji\"9\u0001gAA\u0001\u0002\u0004a\u0012a\u0001=%c\u0005i1/\u001e2nSN\u001c\u0018n\u001c8JI\u0002\n!\u0002Z8WC2LG-\u0019;f)\u0005Y\u0003"
)
public class CreateSubmissionResponse extends SubmitRestProtocolResponse {
   private String submissionId = null;

   public String submissionId() {
      return this.submissionId;
   }

   public void submissionId_$eq(final String x$1) {
      this.submissionId = x$1;
   }

   public void doValidate() {
      super.doValidate();
      this.assertFieldIsSet(this.success(), "success");
   }
}
