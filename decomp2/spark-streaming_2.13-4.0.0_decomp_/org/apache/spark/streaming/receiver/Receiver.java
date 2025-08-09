package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.storage.StorageLevel;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005]g!\u0002\u0012$\u0003\u0003q\u0003\u0002\u0003\"\u0001\u0005\u000b\u0007I\u0011A\"\t\u0011)\u0003!\u0011!Q\u0001\n\u0011CQa\u0013\u0001\u0005\u00021CQa\u0017\u0001\u0007\u0002qCQ\u0001\u0019\u0001\u0007\u0002qCQ!\u0019\u0001\u0005\u0002\tDQA\u001c\u0001\u0005\u0002=DQA\u001c\u0001\u0005\u0002IDQA\u001c\u0001\u0005\u0002uDaA\u001c\u0001\u0005\u0002\u0005\r\u0001B\u00028\u0001\t\u0003\ty\u0001\u0003\u0004o\u0001\u0011\u0005\u00111\u0005\u0005\u0007]\u0002!\t!a\n\t\r9\u0004A\u0011AA\u0017\u0011\u0019q\u0007\u0001\"\u0001\u0002@!9\u0011Q\t\u0001\u0005\u0002\u0005\u001d\u0003bBA,\u0001\u0011\u0005\u0011\u0011\f\u0005\b\u0003/\u0002A\u0011AA/\u0011\u001d\t9\u0006\u0001C\u0001\u0003KBq!!\u001e\u0001\t\u0003\t9\bC\u0004\u0002v\u0001!\t!a\u001f\t\u000f\u0005\u0005\u0005\u0001\"\u0001\u0002\u0004\"9\u00111\u0012\u0001\u0005\u0002\u0005\r\u0005bBAG\u0001\u0011\u0005\u0011q\u0012\u0005\n\u0003#\u0003\u0001\u0019!C\u0005\u0003\u001fC\u0011\"a%\u0001\u0001\u0004%I!!&\t\u0011\u0005m\u0005\u0001)Q\u0005\u0003_B\u0011\"!(\u0001\u0001\u0004%I!a(\t\u0013\u0005\u001d\u0006\u00011A\u0005\n\u0005%\u0006\u0002CAW\u0001\u0001\u0006K!!)\t\u0011\u0005]\u0006\u0001\"\u0001&\u0003sC\u0001\"a0\u0001\t\u0003)\u0013\u0011\u0019\u0005\t\u0003\u000f\u0004A\u0011A\u0013\u0002 \nA!+Z2fSZ,'O\u0003\u0002%K\u0005A!/Z2fSZ,'O\u0003\u0002'O\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003Q%\nQa\u001d9be.T!AK\u0016\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0013aA8sO\u000e\u0001QCA\u0018R'\r\u0001\u0001G\u000e\t\u0003cQj\u0011A\r\u0006\u0002g\u0005)1oY1mC&\u0011QG\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]zdB\u0001\u001d>\u001d\tID(D\u0001;\u0015\tYT&\u0001\u0004=e>|GOP\u0005\u0002g%\u0011aHM\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0015I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002?e\u0005a1\u000f^8sC\u001e,G*\u001a<fYV\tA\t\u0005\u0002F\u00116\taI\u0003\u0002HO\u000591\u000f^8sC\u001e,\u0017BA%G\u00051\u0019Fo\u001c:bO\u0016dUM^3m\u00035\u0019Ho\u001c:bO\u0016dUM^3mA\u00051A(\u001b8jiz\"\"!\u0014.\u0011\u00079\u0003q*D\u0001$!\t\u0001\u0016\u000b\u0004\u0001\u0005\u000bI\u0003!\u0019A*\u0003\u0003Q\u000b\"\u0001V,\u0011\u0005E*\u0016B\u0001,3\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\r-\n\u0005e\u0013$aA!os\")!i\u0001a\u0001\t\u00069qN\\*uCJ$H#A/\u0011\u0005Er\u0016BA03\u0005\u0011)f.\u001b;\u0002\r=t7\u000b^8q\u0003E\u0001(/\u001a4feJ,G\rT8dCRLwN\\\u000b\u0002GB\u0019\u0011\u0007\u001a4\n\u0005\u0015\u0014$AB(qi&|g\u000e\u0005\u0002hW:\u0011\u0001.\u001b\t\u0003sIJ!A\u001b\u001a\u0002\rA\u0013X\rZ3g\u0013\taWN\u0001\u0004TiJLgn\u001a\u0006\u0003UJ\nQa\u001d;pe\u0016$\"!\u00189\t\u000bE<\u0001\u0019A(\u0002\u0011\u0011\fG/Y%uK6$\"!X:\t\u000bQD\u0001\u0019A;\u0002\u0015\u0011\fG/\u0019\"vM\u001a,'\u000fE\u0002ww>k\u0011a\u001e\u0006\u0003qf\fq!\\;uC\ndWM\u0003\u0002{e\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005q<(aC!se\u0006L()\u001e4gKJ$2!\u0018@\u0000\u0011\u0015!\u0018\u00021\u0001v\u0011\u0019\t\t!\u0003a\u0001/\u0006AQ.\u001a;bI\u0006$\u0018\rF\u0002^\u0003\u000bAq!a\u0002\u000b\u0001\u0004\tI!\u0001\u0007eCR\f\u0017\n^3sCR|'\u000f\u0005\u00038\u0003\u0017y\u0015bAA\u0007\u0003\nA\u0011\n^3sCR|'\u000fF\u0003^\u0003#\t\t\u0003C\u0004\u0002\b-\u0001\r!a\u0005\u0011\u000b\u0005U\u0011qD(\u000e\u0005\u0005]!\u0002BA\r\u00037\tA!\u001e;jY*\u0011\u0011QD\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u000e\u0005]\u0001BBA\u0001\u0017\u0001\u0007q\u000bF\u0002^\u0003KAq!a\u0002\r\u0001\u0004\t\u0019\u0002F\u0003^\u0003S\tY\u0003C\u0004\u0002\b5\u0001\r!!\u0003\t\r\u0005\u0005Q\u00021\u0001X)\ri\u0016q\u0006\u0005\b\u0003cq\u0001\u0019AA\u001a\u0003\u0015\u0011\u0017\u0010^3t!\u0011\t)$a\u000f\u000e\u0005\u0005]\"\u0002BA\u001d\u00037\t1A\\5p\u0013\u0011\ti$a\u000e\u0003\u0015\tKH/\u001a\"vM\u001a,'\u000fF\u0003^\u0003\u0003\n\u0019\u0005C\u0004\u00022=\u0001\r!a\r\t\r\u0005\u0005q\u00021\u0001X\u0003-\u0011X\r]8si\u0016\u0013(o\u001c:\u0015\u000bu\u000bI%!\u0014\t\r\u0005-\u0003\u00031\u0001g\u0003\u001diWm]:bO\u0016Dq!a\u0014\u0011\u0001\u0004\t\t&A\u0005uQJ|w/\u00192mKB\u0019q'a\u0015\n\u0007\u0005U\u0013IA\u0005UQJ|w/\u00192mK\u00069!/Z:uCJ$HcA/\u0002\\!1\u00111J\tA\u0002\u0019$R!XA0\u0003CBa!a\u0013\u0013\u0001\u00041\u0007bBA2%\u0001\u0007\u0011\u0011K\u0001\u0006KJ\u0014xN\u001d\u000b\b;\u0006\u001d\u0014\u0011NA6\u0011\u0019\tYe\u0005a\u0001M\"9\u00111M\nA\u0002\u0005E\u0003bBA7'\u0001\u0007\u0011qN\u0001\f[&dG.[:fG>tG\rE\u00022\u0003cJ1!a\u001d3\u0005\rIe\u000e^\u0001\u0005gR|\u0007\u000fF\u0002^\u0003sBa!a\u0013\u0015\u0001\u00041G#B/\u0002~\u0005}\u0004BBA&+\u0001\u0007a\rC\u0004\u0002dU\u0001\r!!\u0015\u0002\u0013%\u001c8\u000b^1si\u0016$GCAAC!\r\t\u0014qQ\u0005\u0004\u0003\u0013\u0013$a\u0002\"p_2,\u0017M\\\u0001\nSN\u001cFo\u001c9qK\u0012\f\u0001b\u001d;sK\u0006l\u0017\nZ\u000b\u0003\u0003_\n!!\u001b3\u0002\r%$w\fJ3r)\ri\u0016q\u0013\u0005\n\u00033S\u0012\u0011!a\u0001\u0003_\n1\u0001\u001f\u00132\u0003\rIG\rI\u0001\f?N,\b/\u001a:wSN|'/\u0006\u0002\u0002\"B\u0019a*a)\n\u0007\u0005\u00156E\u0001\nSK\u000e,\u0017N^3s'V\u0004XM\u001d<jg>\u0014\u0018aD0tkB,'O^5t_J|F%Z9\u0015\u0007u\u000bY\u000bC\u0005\u0002\u001av\t\t\u00111\u0001\u0002\"\u0006aql];qKJ4\u0018n]8sA!\u001aa$!-\u0011\u0007E\n\u0019,C\u0002\u00026J\u0012\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u001bM,GOU3dK&4XM]%e)\ri\u00161\u0018\u0005\b\u0003{{\u0002\u0019AA8\u0003\ry\u0016\u000eZ\u0001\u0011CR$\u0018m\u00195TkB,'O^5t_J$2!XAb\u0011\u001d\t)\r\ta\u0001\u0003C\u000bA!\u001a=fG\u0006Q1/\u001e9feZL7o\u001c:)\u0007\u0001\tY\r\u0005\u0003\u0002N\u0006MWBAAh\u0015\r\t\tnJ\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAk\u0003\u001f\u0014A\u0002R3wK2|\u0007/\u001a:Ba&\u0004"
)
public abstract class Receiver implements Serializable {
   private final StorageLevel storageLevel;
   private int id;
   private transient ReceiverSupervisor _supervisor;

   public StorageLevel storageLevel() {
      return this.storageLevel;
   }

   public abstract void onStart();

   public abstract void onStop();

   public Option preferredLocation() {
      return .MODULE$;
   }

   public void store(final Object dataItem) {
      this.supervisor().pushSingle(dataItem);
   }

   public void store(final ArrayBuffer dataBuffer) {
      this.supervisor().pushArrayBuffer(dataBuffer, .MODULE$, .MODULE$);
   }

   public void store(final ArrayBuffer dataBuffer, final Object metadata) {
      this.supervisor().pushArrayBuffer(dataBuffer, new Some(metadata), .MODULE$);
   }

   public void store(final Iterator dataIterator) {
      this.supervisor().pushIterator(dataIterator, .MODULE$, .MODULE$);
   }

   public void store(final java.util.Iterator dataIterator, final Object metadata) {
      this.supervisor().pushIterator(scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(dataIterator).asScala(), new Some(metadata), .MODULE$);
   }

   public void store(final java.util.Iterator dataIterator) {
      this.supervisor().pushIterator(scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(dataIterator).asScala(), .MODULE$, .MODULE$);
   }

   public void store(final Iterator dataIterator, final Object metadata) {
      this.supervisor().pushIterator(dataIterator, new Some(metadata), .MODULE$);
   }

   public void store(final ByteBuffer bytes) {
      this.supervisor().pushBytes(bytes, .MODULE$, .MODULE$);
   }

   public void store(final ByteBuffer bytes, final Object metadata) {
      this.supervisor().pushBytes(bytes, new Some(metadata), .MODULE$);
   }

   public void reportError(final String message, final Throwable throwable) {
      this.supervisor().reportError(message, throwable);
   }

   public void restart(final String message) {
      ReceiverSupervisor qual$1 = this.supervisor();
      Option x$2 = qual$1.restartReceiver$default$2();
      qual$1.restartReceiver(message, x$2);
   }

   public void restart(final String message, final Throwable error) {
      this.supervisor().restartReceiver(message, new Some(error));
   }

   public void restart(final String message, final Throwable error, final int millisecond) {
      this.supervisor().restartReceiver(message, new Some(error), millisecond);
   }

   public void stop(final String message) {
      this.supervisor().stop(message, .MODULE$);
   }

   public void stop(final String message, final Throwable error) {
      this.supervisor().stop(message, new Some(error));
   }

   public boolean isStarted() {
      return this.supervisor().isReceiverStarted();
   }

   public boolean isStopped() {
      return this.supervisor().isReceiverStopped();
   }

   public int streamId() {
      return this.id();
   }

   private int id() {
      return this.id;
   }

   private void id_$eq(final int x$1) {
      this.id = x$1;
   }

   private ReceiverSupervisor _supervisor() {
      return this._supervisor;
   }

   private void _supervisor_$eq(final ReceiverSupervisor x$1) {
      this._supervisor = x$1;
   }

   public void setReceiverId(final int _id) {
      this.id_$eq(_id);
   }

   public void attachSupervisor(final ReceiverSupervisor exec) {
      scala.Predef..MODULE$.assert(this._supervisor() == null);
      this._supervisor_$eq(exec);
   }

   public ReceiverSupervisor supervisor() {
      scala.Predef..MODULE$.assert(this._supervisor() != null, () -> "A ReceiverSupervisor has not been attached to the receiver yet. Maybe you are starting some computation in the receiver before the Receiver.onStart() has been called.");
      return this._supervisor();
   }

   public Receiver(final StorageLevel storageLevel) {
      this.storageLevel = storageLevel;
      this.id = -1;
      this._supervisor = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
