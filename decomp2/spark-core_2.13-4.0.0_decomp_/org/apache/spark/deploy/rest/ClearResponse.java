package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}1Qa\u0001\u0003\u0001\u00119AQa\u0005\u0001\u0005\u0002UAQa\u0006\u0001\u0005Ra\u0011Qb\u00117fCJ\u0014Vm\u001d9p]N,'BA\u0003\u0007\u0003\u0011\u0011Xm\u001d;\u000b\u0005\u001dA\u0011A\u00023fa2|\u0017P\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h'\t\u0001q\u0002\u0005\u0002\u0011#5\tA!\u0003\u0002\u0013\t\tQ2+\u001e2nSR\u0014Vm\u001d;Qe>$xnY8m%\u0016\u001c\bo\u001c8tK\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0017!\t\u0001\u0002!\u0001\u0006e_Z\u000bG.\u001b3bi\u0016$\u0012!\u0007\t\u00035ui\u0011a\u0007\u0006\u00029\u0005)1oY1mC&\u0011ad\u0007\u0002\u0005+:LG\u000f"
)
public class ClearResponse extends SubmitRestProtocolResponse {
   public void doValidate() {
      super.doValidate();
      this.assertFieldIsSet(this.success(), "success");
   }
}
