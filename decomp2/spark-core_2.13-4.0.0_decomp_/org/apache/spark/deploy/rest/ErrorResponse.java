package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2QAB\u0004\u0001\u000fEAQA\u0006\u0001\u0005\u0002aAqA\u0007\u0001A\u0002\u0013\u00051\u0004C\u0004*\u0001\u0001\u0007I\u0011\u0001\u0016\t\rE\u0002\u0001\u0015)\u0003\u001d\u0011\u0015\u0011\u0004\u0001\"\u00154\u00055)%O]8s%\u0016\u001c\bo\u001c8tK*\u0011\u0001\"C\u0001\u0005e\u0016\u001cHO\u0003\u0002\u000b\u0017\u00051A-\u001a9m_fT!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\n\u0003\u0001I\u0001\"a\u0005\u000b\u000e\u0003\u001dI!!F\u0004\u00035M+(-\\5u%\u0016\u001cH\u000f\u0015:pi>\u001cw\u000e\u001c*fgB|gn]3\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!\u0007\t\u0003'\u0001\ta\u0003[5hQ\u0016\u001cH\u000f\u0015:pi>\u001cw\u000e\u001c,feNLwN\\\u000b\u00029A\u0011QD\n\b\u0003=\u0011\u0002\"a\b\u0012\u000e\u0003\u0001R!!I\f\u0002\rq\u0012xn\u001c;?\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0003\u0019\u0001&/\u001a3fM&\u0011q\u0005\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0015\u0012\u0013A\u00075jO\",7\u000f\u001e)s_R|7m\u001c7WKJ\u001c\u0018n\u001c8`I\u0015\fHCA\u00160!\taS&D\u0001#\u0013\tq#E\u0001\u0003V]&$\bb\u0002\u0019\u0004\u0003\u0003\u0005\r\u0001H\u0001\u0004q\u0012\n\u0014a\u00065jO\",7\u000f\u001e)s_R|7m\u001c7WKJ\u001c\u0018n\u001c8!\u0003)!wNV1mS\u0012\fG/\u001a\u000b\u0002W\u0001"
)
public class ErrorResponse extends SubmitRestProtocolResponse {
   private String highestProtocolVersion = null;

   public String highestProtocolVersion() {
      return this.highestProtocolVersion;
   }

   public void highestProtocolVersion_$eq(final String x$1) {
      this.highestProtocolVersion = x$1;
   }

   public void doValidate() {
      super.doValidate();
      this.assertFieldIsSet(this.message(), "message");
   }
}
