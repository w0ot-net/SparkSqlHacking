package spire.std;

import algebra.ring.Rig;
import scala.Tuple11;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%a\u0001\u0003\b\u0010!\u0003\r\t!E\n\t\u000bq\u0003A\u0011A/\t\u000b\u0005\u0004a1\u00012\t\u000b\u0011\u0004a1A3\t\u000b\u001d\u0004a1\u00015\t\u000b)\u0004a1A6\t\u000b5\u0004a1\u00018\t\u000bA\u0004a1A9\t\u000bM\u0004a1\u0001;\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001C\u0001\u0003\u000f\u0011ABU5h!J|G-^2ucER!\u0001E\t\u0002\u0007M$HMC\u0001\u0013\u0003\u0015\u0019\b/\u001b:f+1!\u0012g\u000f B\t\u001eSU\nU*W'\u0011\u0001Qc\u0007-\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\r\u0005s\u0017PU3g!\ra\u0012\u0006\f\b\u0003;\u0019r!A\b\u0013\u000f\u0005}\u0019S\"\u0001\u0011\u000b\u0005\u0005\u0012\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003II!!J\t\u0002\u000f\u0005dw-\u001a2sC&\u0011q\u0005K\u0001\ba\u0006\u001c7.Y4f\u0015\t)\u0013#\u0003\u0002+W\t\u0019!+[4\u000b\u0005\u001dB\u0003#\u0004\f._ij\u0004i\u0011$J\u0019>\u0013V+\u0003\u0002//\t9A+\u001e9mKF\n\u0004C\u0001\u00192\u0019\u0001!QA\r\u0001C\u0002M\u0012\u0011!Q\t\u0003i]\u0002\"AF\u001b\n\u0005Y:\"a\u0002(pi\"Lgn\u001a\t\u0003-aJ!!O\f\u0003\u0007\u0005s\u0017\u0010\u0005\u00021w\u0011)A\b\u0001b\u0001g\t\t!\t\u0005\u00021}\u0011)q\b\u0001b\u0001g\t\t1\t\u0005\u00021\u0003\u0012)!\t\u0001b\u0001g\t\tA\t\u0005\u00021\t\u0012)Q\t\u0001b\u0001g\t\tQ\t\u0005\u00021\u000f\u0012)\u0001\n\u0001b\u0001g\t\ta\t\u0005\u00021\u0015\u0012)1\n\u0001b\u0001g\t\tq\t\u0005\u00021\u001b\u0012)a\n\u0001b\u0001g\t\t\u0001\n\u0005\u00021!\u0012)\u0011\u000b\u0001b\u0001g\t\t\u0011\n\u0005\u00021'\u0012)A\u000b\u0001b\u0001g\t\t!\n\u0005\u00021-\u0012)q\u000b\u0001b\u0001g\t\t1\nE\u0007Z5>RT\bQ\"G\u00132{%+V\u0007\u0002\u001f%\u00111l\u0004\u0002\u0012'\u0016l\u0017N]5oOB\u0013x\u000eZ;diF\n\u0014A\u0002\u0013j]&$H\u0005F\u0001_!\t1r,\u0003\u0002a/\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002GB\u0019A$K\u0018\u0002\u0015M$(/^2ukJ,''F\u0001g!\ra\u0012FO\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#A5\u0011\u0007qIS(\u0001\u0006tiJ,8\r^;sKR*\u0012\u0001\u001c\t\u00049%\u0002\u0015AC:ueV\u001cG/\u001e:fkU\tq\u000eE\u0002\u001dS\r\u000b!b\u001d;sk\u000e$XO]37+\u0005\u0011\bc\u0001\u000f*\r\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0003U\u00042\u0001H\u0015J\u0003)\u0019HO];diV\u0014X\rO\u000b\u0002qB\u0019A$\u000b'\u0002\u0015M$(/^2ukJ,\u0017(F\u0001|!\ra\u0012fT\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'F\u0001\u007f!\ra\u0012FU\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u0002\u0004A\u0019A$K+\u0002\u0007=tW-F\u0001-\u0001"
)
public interface RigProduct11 extends Rig, SemiringProduct11 {
   Rig structure1();

   Rig structure2();

   Rig structure3();

   Rig structure4();

   Rig structure5();

   Rig structure6();

   Rig structure7();

   Rig structure8();

   Rig structure9();

   Rig structure10();

   Rig structure11();

   // $FF: synthetic method
   static Tuple11 one$(final RigProduct11 $this) {
      return $this.one();
   }

   default Tuple11 one() {
      return new Tuple11(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one());
   }

   static void $init$(final RigProduct11 $this) {
   }
}
