package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3a\u0001D\u0007\u0002\u000259\u0002\"\u0002\u000f\u0001\t\u0003q\u0002b\u0002\u0011\u0001\u0001\u0004%\t!\t\u0005\b_\u0001\u0001\r\u0011\"\u00011\u0011\u00199\u0004\u0001)Q\u0005E!9\u0001\b\u0001a\u0001\n\u0003I\u0004b\u0002\"\u0001\u0001\u0004%\ta\u0011\u0005\u0007\u000b\u0002\u0001\u000b\u0015\u0002\u001e\t\u000f\u0019\u0003\u0001\u0019!C\u0001\u000f\"91\n\u0001a\u0001\n\u0003a\u0005B\u0002(\u0001A\u0003&\u0001\nC\u0003P\u0001\u0011E\u0003K\u0001\u000eTk\nl\u0017\u000e\u001e*fgR\u0004&o\u001c;pG>d'+Z:q_:\u001cXM\u0003\u0002\u000f\u001f\u0005!!/Z:u\u0015\t\u0001\u0012#\u0001\u0004eKBdw.\u001f\u0006\u0003%M\tQa\u001d9be.T!\u0001F\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0012aA8sON\u0011\u0001\u0001\u0007\t\u00033ii\u0011!D\u0005\u000375\u0011\u0011dU;c[&$(+Z:u!J|Go\\2pY6+7o]1hK\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001 !\tI\u0002!\u0001\ntKJ4XM]*qCJ\\g+\u001a:tS>tW#\u0001\u0012\u0011\u0005\rbcB\u0001\u0013+!\t)\u0003&D\u0001'\u0015\t9S$\u0001\u0004=e>|GO\u0010\u0006\u0002S\u0005)1oY1mC&\u00111\u0006K\u0001\u0007!J,G-\u001a4\n\u00055r#AB*ue&twM\u0003\u0002,Q\u000512/\u001a:wKJ\u001c\u0006/\u0019:l-\u0016\u00148/[8o?\u0012*\u0017\u000f\u0006\u00022kA\u0011!gM\u0007\u0002Q%\u0011A\u0007\u000b\u0002\u0005+:LG\u000fC\u00047\u0007\u0005\u0005\t\u0019\u0001\u0012\u0002\u0007a$\u0013'A\ntKJ4XM]*qCJ\\g+\u001a:tS>t\u0007%A\u0004tk\u000e\u001cWm]:\u0016\u0003i\u0002\"a\u000f!\u000e\u0003qR!!\u0010 \u0002\t1\fgn\u001a\u0006\u0002\u007f\u0005!!.\u0019<b\u0013\t\tEHA\u0004C_>dW-\u00198\u0002\u0017M,8mY3tg~#S-\u001d\u000b\u0003c\u0011CqA\u000e\u0004\u0002\u0002\u0003\u0007!(\u0001\u0005tk\u000e\u001cWm]:!\u00035)hn\u001b8po:4\u0015.\u001a7egV\t\u0001\nE\u00023\u0013\nJ!A\u0013\u0015\u0003\u000b\u0005\u0013(/Y=\u0002#Ut7N\\8x]\u001aKW\r\u001c3t?\u0012*\u0017\u000f\u0006\u00022\u001b\"9a'CA\u0001\u0002\u0004A\u0015AD;oW:|wO\u001c$jK2$7\u000fI\u0001\u000bI>4\u0016\r\\5eCR,G#A\u0019"
)
public abstract class SubmitRestProtocolResponse extends SubmitRestProtocolMessage {
   private String serverSparkVersion = null;
   private Boolean success = null;
   private String[] unknownFields = null;

   public String serverSparkVersion() {
      return this.serverSparkVersion;
   }

   public void serverSparkVersion_$eq(final String x$1) {
      this.serverSparkVersion = x$1;
   }

   public Boolean success() {
      return this.success;
   }

   public void success_$eq(final Boolean x$1) {
      this.success = x$1;
   }

   public String[] unknownFields() {
      return this.unknownFields;
   }

   public void unknownFields_$eq(final String[] x$1) {
      this.unknownFields = x$1;
   }

   public void doValidate() {
      super.doValidate();
      this.assertFieldIsSet(this.serverSparkVersion(), "serverSparkVersion");
   }
}
