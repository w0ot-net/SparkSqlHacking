package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513Qa\u0004\t\u0001)iAQa\b\u0001\u0005\u0002\u0005Bqa\t\u0001A\u0002\u0013\u0005A\u0005C\u00043\u0001\u0001\u0007I\u0011A\u001a\t\ri\u0002\u0001\u0015)\u0003&\u0011\u001dY\u0004\u00011A\u0005\u0002\u0011Bq\u0001\u0010\u0001A\u0002\u0013\u0005Q\b\u0003\u0004@\u0001\u0001\u0006K!\n\u0005\b\u0001\u0002\u0001\r\u0011\"\u0001%\u0011\u001d\t\u0005\u00011A\u0005\u0002\tCa\u0001\u0012\u0001!B\u0013)\u0003bB#\u0001\u0001\u0004%\t\u0001\n\u0005\b\r\u0002\u0001\r\u0011\"\u0001H\u0011\u0019I\u0005\u0001)Q\u0005K!)!\n\u0001C)\u0017\nA2+\u001e2nSN\u001c\u0018n\u001c8Ti\u0006$Xo\u001d*fgB|gn]3\u000b\u0005E\u0011\u0012\u0001\u0002:fgRT!a\u0005\u000b\u0002\r\u0011,\u0007\u000f\\8z\u0015\t)b#A\u0003ta\u0006\u00148N\u0003\u0002\u00181\u00051\u0011\r]1dQ\u0016T\u0011!G\u0001\u0004_J<7C\u0001\u0001\u001c!\taR$D\u0001\u0011\u0013\tq\u0002C\u0001\u000eTk\nl\u0017\u000e\u001e*fgR\u0004&o\u001c;pG>d'+Z:q_:\u001cX-\u0001\u0004=S:LGOP\u0002\u0001)\u0005\u0011\u0003C\u0001\u000f\u0001\u00031\u0019XOY7jgNLwN\\%e+\u0005)\u0003C\u0001\u00140\u001d\t9S\u0006\u0005\u0002)W5\t\u0011F\u0003\u0002+A\u00051AH]8pizR\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\na\u0001\u0015:fI\u00164\u0017B\u0001\u00192\u0005\u0019\u0019FO]5oO*\u0011afK\u0001\u0011gV\u0014W.[:tS>t\u0017\nZ0%KF$\"\u0001\u000e\u001d\u0011\u0005U2T\"A\u0016\n\u0005]Z#\u0001B+oSRDq!O\u0002\u0002\u0002\u0003\u0007Q%A\u0002yIE\nQb];c[&\u001c8/[8o\u0013\u0012\u0004\u0013a\u00033sSZ,'o\u0015;bi\u0016\fq\u0002\u001a:jm\u0016\u00148\u000b^1uK~#S-\u001d\u000b\u0003iyBq!\u000f\u0004\u0002\u0002\u0003\u0007Q%\u0001\u0007ee&4XM]*uCR,\u0007%\u0001\u0005x_J\\WM]%e\u000319xN]6fe&#w\fJ3r)\t!4\tC\u0004:\u0013\u0005\u0005\t\u0019A\u0013\u0002\u0013]|'o[3s\u0013\u0012\u0004\u0013AD<pe.,'\u000fS8tiB{'\u000f^\u0001\u0013o>\u00148.\u001a:I_N$\bk\u001c:u?\u0012*\u0017\u000f\u0006\u00025\u0011\"9\u0011\bDA\u0001\u0002\u0004)\u0013aD<pe.,'\u000fS8tiB{'\u000f\u001e\u0011\u0002\u0015\u0011|g+\u00197jI\u0006$X\rF\u00015\u0001"
)
public class SubmissionStatusResponse extends SubmitRestProtocolResponse {
   private String submissionId = null;
   private String driverState = null;
   private String workerId = null;
   private String workerHostPort = null;

   public String submissionId() {
      return this.submissionId;
   }

   public void submissionId_$eq(final String x$1) {
      this.submissionId = x$1;
   }

   public String driverState() {
      return this.driverState;
   }

   public void driverState_$eq(final String x$1) {
      this.driverState = x$1;
   }

   public String workerId() {
      return this.workerId;
   }

   public void workerId_$eq(final String x$1) {
      this.workerId = x$1;
   }

   public String workerHostPort() {
      return this.workerHostPort;
   }

   public void workerHostPort_$eq(final String x$1) {
      this.workerHostPort = x$1;
   }

   public void doValidate() {
      super.doValidate();
      this.assertFieldIsSet(this.submissionId(), "submissionId");
      this.assertFieldIsSet(this.success(), "success");
   }
}
