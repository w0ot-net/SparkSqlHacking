package scala.collection.parallel;

import scala.Function0;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001daaB\t\u0013!\u0003\r\t!\u0007\u0005\u0006=\u0001!\ta\b\u0005\tG\u0001\u0011\r\u0011\"\u0001\u0013I!1a\u0007\u0001C\u0001%]2qA\u000f\u0001\u0011\u0002\u0007\u00051\bC\u0003\u001f\t\u0011\u0005q\u0004C\u0004>\t\t\u0007i\u0011\u0001 \t\u000bE#a\u0011\u0001*\t\u000bq#a\u0011A\u0010\t\u000bu#a\u0011A\u0010\t\u000by#a\u0011A\u0010\t\u000b}#a\u0011\u00011\t\u000b\u0011$A\u0011A\u0010\t\u000f\u0015\u0004!\u0019!D\u0001M\")q\r\u0001D\u0001Q\")A\u000f\u0001D\u0001k\")a\u0010\u0001D\u0001\u007f\n)A+Y:lg*\u00111\u0003F\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011QCF\u0001\u000bG>dG.Z2uS>t'\"A\f\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0007\t\u00037qi\u0011AF\u0005\u0003;Y\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001!!\tY\u0012%\u0003\u0002#-\t!QK\\5u\u00035!WMY;h\u001b\u0016\u001c8/Y4fgV\tQ\u0005E\u0002'S-j\u0011a\n\u0006\u0003QQ\tq!\\;uC\ndW-\u0003\u0002+O\tY\u0011I\u001d:bs\n+hMZ3s!\ta3G\u0004\u0002.cA\u0011aFF\u0007\u0002_)\u0011\u0001\u0007G\u0001\u0007yI|w\u000e\u001e \n\u0005I2\u0012A\u0002)sK\u0012,g-\u0003\u00025k\t11\u000b\u001e:j]\u001eT!A\r\f\u0002\u0011\u0011,'-^4m_\u001e$\"!\n\u001d\t\u000be\u001a\u0001\u0019A\u0016\u0002\u0003M\u00141b\u0016:baB,G\rV1tWV\u0019A(R(\u0014\u0005\u0011Q\u0012\u0001\u00022pIf,\u0012a\u0010\t\u0005\u0001\u0006\u001be*D\u0001\u0013\u0013\t\u0011%C\u0001\u0003UCN\\\u0007C\u0001#F\u0019\u0001!QA\u0012\u0003C\u0002\u001d\u0013\u0011AU\t\u0003\u0011.\u0003\"aG%\n\u0005)3\"a\u0002(pi\"Lgn\u001a\t\u000371K!!\u0014\f\u0003\u0007\u0005s\u0017\u0010\u0005\u0002E\u001f\u00121\u0001\u000b\u0002CC\u0002\u001d\u0013!\u0001\u00169\u0002\u000bM\u0004H.\u001b;\u0016\u0003M\u00032\u0001V,[\u001d\tYR+\u0003\u0002W-\u00059\u0001/Y2lC\u001e,\u0017B\u0001-Z\u0005\r\u0019V-\u001d\u0006\u0003-Z\u0001Ba\u0017\u0003D\u001d6\t\u0001!A\u0004d_6\u0004X\u000f^3\u0002\u000bM$\u0018M\u001d;\u0002\tMLhnY\u0001\niJL8)\u00198dK2$\u0012!\u0019\t\u00037\tL!a\u0019\f\u0003\u000f\t{w\u000e\\3b]\u00069!/\u001a7fCN,\u0017aC3om&\u0014xN\\7f]R,\u0012AG\u0001\bKb,7-\u001e;f+\rIgn\u001d\u000b\u0003U>\u00042aG6n\u0013\tagCA\u0005Gk:\u001cG/[8oaA\u0011AI\u001c\u0003\u0006\r:\u0011\ra\u0012\u0005\u0006a:\u0001\r!]\u0001\u0007M*$\u0018m]6\u0011\t\u0001\u000bUN\u001d\t\u0003\tN$Q\u0001\u0015\bC\u0002\u001d\u000bA#\u001a=fGV$X-\u00118e/\u0006LGOU3tk2$Xc\u0001<y{R\u0011q/\u001f\t\u0003\tb$QAR\bC\u0002\u001dCQA_\bA\u0002m\fA\u0001^1tWB!\u0001)Q<}!\t!U\u0010B\u0003Q\u001f\t\u0007q)\u0001\tqCJ\fG\u000e\\3mSNlG*\u001a<fYV\u0011\u0011\u0011\u0001\t\u00047\u0005\r\u0011bAA\u0003-\t\u0019\u0011J\u001c;"
)
public interface Tasks {
   void scala$collection$parallel$Tasks$_setter_$debugMessages_$eq(final ArrayBuffer x$1);

   ArrayBuffer debugMessages();

   // $FF: synthetic method
   static ArrayBuffer debuglog$(final Tasks $this, final String s) {
      return $this.debuglog(s);
   }

   default ArrayBuffer debuglog(final String s) {
      synchronized(this){}

      ArrayBuffer var3;
      try {
         var3 = (ArrayBuffer)this.debugMessages().$plus$eq(s);
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   Object environment();

   Function0 execute(final Task fjtask);

   Object executeAndWaitResult(final Task task);

   int parallelismLevel();

   static void $init$(final Tasks $this) {
      $this.scala$collection$parallel$Tasks$_setter_$debugMessages_$eq((ArrayBuffer).MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   public interface WrappedTask {
      Task body();

      Seq split();

      void compute();

      void start();

      void sync();

      boolean tryCancel();

      // $FF: synthetic method
      static void release$(final WrappedTask $this) {
         $this.release();
      }

      default void release() {
      }

      // $FF: synthetic method
      Tasks scala$collection$parallel$Tasks$WrappedTask$$$outer();

      static void $init$(final WrappedTask $this) {
      }
   }
}
