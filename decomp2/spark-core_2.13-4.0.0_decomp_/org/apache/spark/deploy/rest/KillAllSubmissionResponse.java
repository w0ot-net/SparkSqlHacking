package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}1Qa\u0001\u0003\u0001\u00119AQa\u0005\u0001\u0005\u0002UAQa\u0006\u0001\u0005Ra\u0011\u0011dS5mY\u0006cGnU;c[&\u001c8/[8o%\u0016\u001c\bo\u001c8tK*\u0011QAB\u0001\u0005e\u0016\u001cHO\u0003\u0002\b\u0011\u00051A-\u001a9m_fT!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\n\u0003\u0001=\u0001\"\u0001E\t\u000e\u0003\u0011I!A\u0005\u0003\u00035M+(-\\5u%\u0016\u001cH\u000f\u0015:pi>\u001cw\u000e\u001c*fgB|gn]3\u0002\rqJg.\u001b;?\u0007\u0001!\u0012A\u0006\t\u0003!\u0001\t!\u0002Z8WC2LG-\u0019;f)\u0005I\u0002C\u0001\u000e\u001e\u001b\u0005Y\"\"\u0001\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005yY\"\u0001B+oSR\u0004"
)
public class KillAllSubmissionResponse extends SubmitRestProtocolResponse {
   public void doValidate() {
      super.doValidate();
      this.assertFieldIsSet(this.success(), "success");
   }
}
