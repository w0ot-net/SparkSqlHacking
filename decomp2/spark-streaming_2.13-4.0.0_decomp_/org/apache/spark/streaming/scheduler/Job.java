package org.apache.spark.streaming.scheduler;

import org.apache.spark.streaming.Time;
import org.apache.spark.util.CallSite;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Try;
import scala.util.Try.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rf!B\u0013'\u0001!\u0002\u0004\u0002C\u001c\u0001\u0005\u000b\u0007I\u0011A\u001d\t\u0011y\u0002!\u0011!Q\u0001\niB\u0001b\u0010\u0001\u0003\u0002\u0003\u0006I\u0001\u0011\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\n3\u0002\u0001\r\u00111A\u0005\niC\u0011B\u001a\u0001A\u0002\u0003\u0007I\u0011B4\t\u00135\u0004\u0001\u0019!A!B\u0013Y\u0006\"\u00038\u0001\u0001\u0004\u0005\r\u0011\"\u0003p\u0011%\u0019\b\u00011AA\u0002\u0013%A\u000fC\u0005w\u0001\u0001\u0007\t\u0011)Q\u0005a\"9q\u000f\u0001a\u0001\n\u0013A\bb\u0002?\u0001\u0001\u0004%I! \u0005\u0007\u007f\u0002\u0001\u000b\u0015B=\t\u0013\u0005\u0005\u0001\u00011A\u0005\n\u0005\r\u0001\"CA\u000e\u0001\u0001\u0007I\u0011BA\u000f\u0011!\tI\u0002\u0001Q!\n\u0005\u0015\u0001\"CA\u0015\u0001\u0001\u0007I\u0011BA\u0016\u0011%\t9\u0004\u0001a\u0001\n\u0013\tI\u0004\u0003\u0005\u0002>\u0001\u0001\u000b\u0015BA\u0017\u0011%\ty\u0004\u0001a\u0001\n\u0013\t\t\u0005C\u0005\u0002P\u0001\u0001\r\u0011\"\u0003\u0002R!A\u0011Q\u000b\u0001!B\u0013\t\u0019\u0005C\u0005\u0002X\u0001\u0001\r\u0011\"\u0003\u0002B!I\u0011\u0011\f\u0001A\u0002\u0013%\u00111\f\u0005\t\u0003?\u0002\u0001\u0015)\u0003\u0002D!9\u0011\u0011\r\u0001\u0005\u0002\u0005\r\u0004bBA3\u0001\u0011\u0005\u0011q\r\u0005\u0007\u0003g\u0002A\u0011\u0001.\t\r\u0005U\u0004\u0001\"\u0001p\u0011\u001d\t9\b\u0001C\u0001\u0003sBq!! \u0001\t\u0003\ty\bC\u0004\u0002\u0004\u0002!\t!a\u000b\t\u000f\u0005\u0015\u0005\u0001\"\u0001\u0002\b\"9\u0011Q\u0012\u0001\u0005\u0002\u0005=\u0005bBAK\u0001\u0011\u0005\u0011q\u0013\u0005\b\u0003?\u0003A\u0011IAQ\u0005\rQuN\u0019\u0006\u0003O!\n\u0011b]2iK\u0012,H.\u001a:\u000b\u0005%R\u0013!C:ue\u0016\fW.\u001b8h\u0015\tYC&A\u0003ta\u0006\u00148N\u0003\u0002.]\u00051\u0011\r]1dQ\u0016T\u0011aL\u0001\u0004_J<7C\u0001\u00012!\t\u0011T'D\u00014\u0015\u0005!\u0014!B:dC2\f\u0017B\u0001\u001c4\u0005\u0019\te.\u001f*fM\u0006!A/[7f\u0007\u0001)\u0012A\u000f\t\u0003wqj\u0011\u0001K\u0005\u0003{!\u0012A\u0001V5nK\u0006)A/[7fA\u0005!a-\u001e8da\t\te\tE\u00023\u0005\u0012K!aQ\u001a\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0004CA#G\u0019\u0001!\u0011bR\u0002\u0002\u0002\u0003\u0005)\u0011\u0001%\u0003\u0007}#\u0013'\u0005\u0002J\u0019B\u0011!GS\u0005\u0003\u0017N\u0012qAT8uQ&tw\r\u0005\u00023\u001b&\u0011aj\r\u0002\u0004\u0003:L\u0018A\u0002\u001fj]&$h\bF\u0002R'R\u0003\"A\u0015\u0001\u000e\u0003\u0019BQa\u000e\u0003A\u0002iBQa\u0010\u0003A\u0002U\u0003$A\u0016-\u0011\u0007I\u0012u\u000b\u0005\u0002F1\u0012Iq\tVA\u0001\u0002\u0003\u0015\t\u0001S\u0001\u0004?&$W#A.\u0011\u0005q\u001bgBA/b!\tq6'D\u0001`\u0015\t\u0001\u0007(\u0001\u0004=e>|GOP\u0005\u0003EN\na\u0001\u0015:fI\u00164\u0017B\u00013f\u0005\u0019\u0019FO]5oO*\u0011!mM\u0001\b?&$w\fJ3r)\tA7\u000e\u0005\u00023S&\u0011!n\r\u0002\u0005+:LG\u000fC\u0004m\r\u0005\u0005\t\u0019A.\u0002\u0007a$\u0013'\u0001\u0003`S\u0012\u0004\u0013aC0pkR\u0004X\u000f^(q\u0013\u0012,\u0012\u0001\u001d\t\u0003eEL!A]\u001a\u0003\u0007%sG/A\b`_V$\b/\u001e;Pa&#w\fJ3r)\tAW\u000fC\u0004m\u0013\u0005\u0005\t\u0019\u00019\u0002\u0019}{W\u000f\u001e9vi>\u0003\u0018\n\u001a\u0011\u0002\u000b%\u001c8+\u001a;\u0016\u0003e\u0004\"A\r>\n\u0005m\u001c$a\u0002\"p_2,\u0017M\\\u0001\nSN\u001cV\r^0%KF$\"\u0001\u001b@\t\u000f1d\u0011\u0011!a\u0001s\u00061\u0011n]*fi\u0002\nqa\u0018:fgVdG/\u0006\u0002\u0002\u0006A\"\u0011qAA\u000b!\u0019\tI!a\u0004\u0002\u00145\u0011\u00111\u0002\u0006\u0004\u0003\u001b\u0019\u0014\u0001B;uS2LA!!\u0005\u0002\f\t\u0019AK]=\u0011\u0007\u0015\u000b)\u0002\u0002\u0006\u0002\u0018A\t\t\u0011!A\u0003\u0002!\u00131a\u0018\u00133\u0003!y&/Z:vYR\u0004\u0013aC0sKN,H\u000e^0%KF$2\u0001[A\u0010\u0011!aw\"!AA\u0002\u0005\u0005\u0002\u0007BA\u0012\u0003O\u0001b!!\u0003\u0002\u0010\u0005\u0015\u0002cA#\u0002(\u0011Y\u0011qCA\u0010\u0003\u0003\u0005\tQ!\u0001I\u0003%y6-\u00197m'&$X-\u0006\u0002\u0002.A!\u0011qFA\u001a\u001b\t\t\tDC\u0002\u0002\u000e)JA!!\u000e\u00022\tA1)\u00197m'&$X-A\u0007`G\u0006dGnU5uK~#S-\u001d\u000b\u0004Q\u0006m\u0002\u0002\u00037\u0013\u0003\u0003\u0005\r!!\f\u0002\u0015}\u001b\u0017\r\u001c7TSR,\u0007%\u0001\u0006`gR\f'\u000f\u001e+j[\u0016,\"!a\u0011\u0011\u000bI\n)%!\u0013\n\u0007\u0005\u001d3G\u0001\u0004PaRLwN\u001c\t\u0004e\u0005-\u0013bAA'g\t!Aj\u001c8h\u00039y6\u000f^1siRKW.Z0%KF$2\u0001[A*\u0011!aW#!AA\u0002\u0005\r\u0013aC0ti\u0006\u0014H\u000fV5nK\u0002\n\u0001bX3oIRKW.Z\u0001\r?\u0016tG\rV5nK~#S-\u001d\u000b\u0004Q\u0006u\u0003\u0002\u00037\u0019\u0003\u0003\u0005\r!a\u0011\u0002\u0013}+g\u000e\u001a+j[\u0016\u0004\u0013a\u0001:v]R\t\u0001.\u0001\u0004sKN,H\u000e^\u000b\u0003\u0003S\u0002D!a\u001b\u0002pA1\u0011\u0011BA\b\u0003[\u00022!RA8\t)\t\thGA\u0001\u0002\u0003\u0015\t\u0001\u0013\u0002\u0004?\u0012\u001a\u0014AA5e\u0003)yW\u000f\u001e9vi>\u0003\u0018\nZ\u0001\u000eg\u0016$x*\u001e;qkR|\u0005/\u00133\u0015\u0007!\fY\b\u0003\u0004\u0002vy\u0001\r\u0001]\u0001\fg\u0016$8)\u00197m'&$X\rF\u0002i\u0003\u0003Cq!a! \u0001\u0004\ti#\u0001\u0005dC2d7+\u001b;f\u00031\u0019X\r^*uCJ$H+[7f)\rA\u0017\u0011\u0012\u0005\b\u0003\u0017\u000b\u0003\u0019AA%\u0003%\u0019H/\u0019:u)&lW-\u0001\u0006tKR,e\u000e\u001a+j[\u0016$2\u0001[AI\u0011\u001d\t\u0019J\ta\u0001\u0003\u0013\nq!\u001a8e)&lW-A\u000bu_>+H\u000f];u\u001fB,'/\u0019;j_:LeNZ8\u0016\u0005\u0005e\u0005c\u0001*\u0002\u001c&\u0019\u0011Q\u0014\u0014\u0003'=+H\u000f];u\u001fB,'/\u0019;j_:LeNZ8\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a\u0017"
)
public class Job {
   private final Time time;
   private final Function0 func;
   private String _id;
   private int _outputOpId;
   private boolean isSet;
   private Try _result;
   private CallSite _callSite;
   private Option _startTime;
   private Option _endTime;

   public Time time() {
      return this.time;
   }

   private String _id() {
      return this._id;
   }

   private void _id_$eq(final String x$1) {
      this._id = x$1;
   }

   private int _outputOpId() {
      return this._outputOpId;
   }

   private void _outputOpId_$eq(final int x$1) {
      this._outputOpId = x$1;
   }

   private boolean isSet() {
      return this.isSet;
   }

   private void isSet_$eq(final boolean x$1) {
      this.isSet = x$1;
   }

   private Try _result() {
      return this._result;
   }

   private void _result_$eq(final Try x$1) {
      this._result = x$1;
   }

   private CallSite _callSite() {
      return this._callSite;
   }

   private void _callSite_$eq(final CallSite x$1) {
      this._callSite = x$1;
   }

   private Option _startTime() {
      return this._startTime;
   }

   private void _startTime_$eq(final Option x$1) {
      this._startTime = x$1;
   }

   private Option _endTime() {
      return this._endTime;
   }

   private void _endTime_$eq(final Option x$1) {
      this._endTime = x$1;
   }

   public void run() {
      this._result_$eq(.MODULE$.apply(this.func));
   }

   public Try result() {
      if (this._result() == null) {
         throw new IllegalStateException("Cannot access result before job finishes");
      } else {
         return this._result();
      }
   }

   public String id() {
      if (!this.isSet()) {
         throw new IllegalStateException("Cannot access id before calling setId");
      } else {
         return this._id();
      }
   }

   public int outputOpId() {
      if (!this.isSet()) {
         throw new IllegalStateException("Cannot access number before calling setId");
      } else {
         return this._outputOpId();
      }
   }

   public void setOutputOpId(final int outputOpId) {
      if (this.isSet()) {
         throw new IllegalStateException("Cannot call setOutputOpId more than once");
      } else {
         this.isSet_$eq(true);
         Time var10001 = this.time();
         this._id_$eq("streaming job " + var10001 + "." + outputOpId);
         this._outputOpId_$eq(outputOpId);
      }
   }

   public void setCallSite(final CallSite callSite) {
      this._callSite_$eq(callSite);
   }

   public CallSite callSite() {
      return this._callSite();
   }

   public void setStartTime(final long startTime) {
      this._startTime_$eq(new Some(BoxesRunTime.boxToLong(startTime)));
   }

   public void setEndTime(final long endTime) {
      this._endTime_$eq(new Some(BoxesRunTime.boxToLong(endTime)));
   }

   public OutputOperationInfo toOutputOperationInfo() {
      Option failureReason = (Option)(this._result() != null && this._result().isFailure() ? new Some(org.apache.spark.util.Utils..MODULE$.exceptionString(((Failure)this._result()).exception())) : scala.None..MODULE$);
      return new OutputOperationInfo(this.time(), this.outputOpId(), this.callSite().shortForm(), this.callSite().longForm(), this._startTime(), this._endTime(), failureReason);
   }

   public String toString() {
      return this.id();
   }

   public Job(final Time time, final Function0 func) {
      this.time = time;
      this.func = func;
      this.isSet = false;
      this._result = null;
      this._callSite = null;
      this._startTime = scala.None..MODULE$;
      this._endTime = scala.None..MODULE$;
   }
}
