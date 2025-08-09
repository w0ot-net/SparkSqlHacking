package scala.concurrent;

import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u000553A!\u0003\u0006\u0001\u001f!)Q\u0003\u0001C\u0001-!9A\u0005\u0001b\u0001\n\u001b)\u0003BB\u0015\u0001A\u00035a%\u0002\u0003+\u0001\u00111\u0003BB\u0016\u0001A\u0003&A\u0006\u0003\u0004;\u0001\u0001\u0006Ka\u000f\u0005\u0006{\u0001!\tA\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0002\f'ft7m\u00115b]:,GN\u0003\u0002\f\u0019\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u00035\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u00117M\u0011\u0001!\u0005\t\u0003%Mi\u0011\u0001D\u0005\u0003)1\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0018!\rA\u0002!G\u0007\u0002\u0015A\u0011!d\u0007\u0007\u0001\t\u0015a\u0002A1\u0001\u001e\u0005\u0005\t\u0015C\u0001\u0010\"!\t\u0011r$\u0003\u0002!\u0019\t9aj\u001c;iS:<\u0007C\u0001\n#\u0013\t\u0019CBA\u0002B]f\faaU5h]\u0006dW#\u0001\u0014\u0011\u0005I9\u0013B\u0001\u0015\r\u0005\u0011)f.\u001b;\u0002\u000fMKwM\\1mA\t11+[4oC2\fQ\u0002]3oI&twm\u0016:ji\u0016\u001c\bcA\u00173i5\taF\u0003\u00020a\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003c1\t!bY8mY\u0016\u001cG/[8o\u0013\t\u0019dF\u0001\u0003MSN$\b\u0003\u0002\n63]J!A\u000e\u0007\u0003\rQ+\b\u000f\\33!\rA\u0002HJ\u0005\u0003s)\u0011qaU=oGZ\u000b'/\u0001\u0007qK:$\u0017N\\4SK\u0006$7\u000fE\u0002.eq\u00022\u0001\u0007\u001d\u001a\u0003\u00159(/\u001b;f)\t1s\bC\u0003A\u000f\u0001\u0007\u0011$\u0001\u0003eCR\f\u0017\u0001\u0002:fC\u0012,\u0012!\u0007\u0015\u0007\u0001\u0011;\u0005JS&\u0011\u0005I)\u0015B\u0001$\r\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\u0005I\u0015!L+tK\u0002\u0002'.\u0019<b]U$\u0018\u000e\u001c\u0018d_:\u001cWO\u001d:f]RtS\t_2iC:<WM\u001d1!S:\u001cH/Z1e]\u0005)1/\u001b8dK\u0006\nA*\u0001\u00043]E\u001ad\u0006\r"
)
public class SyncChannel {
   private final BoxedUnit Signal;
   private List pendingWrites;
   private List pendingReads;

   private final void Signal() {
   }

   public void write(final Object data) {
      SyncVar writeReq = new SyncVar();
      synchronized(this){}

      try {
         if (this.pendingReads.nonEmpty()) {
            SyncVar readReq = (SyncVar)this.pendingReads.head();
            this.pendingReads = (List)this.pendingReads.tail();
            readReq.put(data);
            writeReq.put(BoxedUnit.UNIT);
         } else {
            List var4 = this.pendingWrites;
            this.pendingWrites = (new $colon$colon(new Tuple2(data, writeReq), Nil$.MODULE$)).$colon$colon$colon(var4);
         }
      } catch (Throwable var6) {
         throw var6;
      }

      writeReq.get();
   }

   public Object read() {
      SyncVar readReq = new SyncVar();
      synchronized(this){}

      try {
         if (this.pendingWrites.nonEmpty()) {
            Tuple2 var2 = (Tuple2)this.pendingWrites.head();
            if (var2 == null) {
               throw new MatchError((Object)null);
            }

            Object data = var2._1();
            SyncVar writeReq = (SyncVar)var2._2();
            this.pendingWrites = (List)this.pendingWrites.tail();
            writeReq.put(BoxedUnit.UNIT);
            readReq.put(data);
         } else {
            List var5 = this.pendingReads;
            this.pendingReads = (new $colon$colon(readReq, Nil$.MODULE$)).$colon$colon$colon(var5);
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return readReq.get();
   }

   public SyncChannel() {
      this.Signal = BoxedUnit.UNIT;
      this.pendingWrites = Nil$.MODULE$;
      this.pendingReads = Nil$.MODULE$;
   }
}
