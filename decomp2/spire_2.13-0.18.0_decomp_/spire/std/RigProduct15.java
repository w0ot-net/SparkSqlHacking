package spire.std;

import algebra.ring.Rig;
import scala.Tuple15;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c\u0001\u0003\n\u0014!\u0003\r\t!F\f\t\u000b1\u0004A\u0011A7\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001A\u0011AA \u00051\u0011\u0016n\u001a)s_\u0012,8\r^\u00196\u0015\t!R#A\u0002ti\u0012T\u0011AF\u0001\u0006gBL'/Z\u000b\u00111Uz$)\u0012%L\u001dF#vKW/aG\u001a\u001cB\u0001A\r QB\u0011!$H\u0007\u00027)\tA$A\u0003tG\u0006d\u0017-\u0003\u0002\u001f7\t1\u0011I\\=SK\u001a\u00042\u0001I\u00171\u001d\t\t#F\u0004\u0002#Q9\u00111eJ\u0007\u0002I)\u0011QEJ\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\ta#\u0003\u0002*+\u00059\u0011\r\\4fEJ\f\u0017BA\u0016-\u0003\u001d\u0001\u0018mY6bO\u0016T!!K\u000b\n\u00059z#a\u0001*jO*\u00111\u0006\f\t\u00125E\u001ad(\u0011#H\u00156\u00036KV-]?\n,\u0017B\u0001\u001a\u001c\u0005\u001d!V\u000f\u001d7fcU\u0002\"\u0001N\u001b\r\u0001\u0011)a\u0007\u0001b\u0001o\t\t\u0011)\u0005\u00029wA\u0011!$O\u0005\u0003um\u0011qAT8uQ&tw\r\u0005\u0002\u001by%\u0011Qh\u0007\u0002\u0004\u0003:L\bC\u0001\u001b@\t\u0015\u0001\u0005A1\u00018\u0005\u0005\u0011\u0005C\u0001\u001bC\t\u0015\u0019\u0005A1\u00018\u0005\u0005\u0019\u0005C\u0001\u001bF\t\u00151\u0005A1\u00018\u0005\u0005!\u0005C\u0001\u001bI\t\u0015I\u0005A1\u00018\u0005\u0005)\u0005C\u0001\u001bL\t\u0015a\u0005A1\u00018\u0005\u00051\u0005C\u0001\u001bO\t\u0015y\u0005A1\u00018\u0005\u00059\u0005C\u0001\u001bR\t\u0015\u0011\u0006A1\u00018\u0005\u0005A\u0005C\u0001\u001bU\t\u0015)\u0006A1\u00018\u0005\u0005I\u0005C\u0001\u001bX\t\u0015A\u0006A1\u00018\u0005\u0005Q\u0005C\u0001\u001b[\t\u0015Y\u0006A1\u00018\u0005\u0005Y\u0005C\u0001\u001b^\t\u0015q\u0006A1\u00018\u0005\u0005a\u0005C\u0001\u001ba\t\u0015\t\u0007A1\u00018\u0005\u0005i\u0005C\u0001\u001bd\t\u0015!\u0007A1\u00018\u0005\u0005q\u0005C\u0001\u001bg\t\u00159\u0007A1\u00018\u0005\u0005y\u0005#E5kgy\nEi\u0012&N!N3\u0016\fX0cK6\t1#\u0003\u0002l'\t\t2+Z7je&tw\r\u0015:pIV\u001cG/M\u001b\u0002\r\u0011Jg.\u001b;%)\u0005q\u0007C\u0001\u000ep\u0013\t\u00018D\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t1\u000fE\u0002![M\n!b\u001d;sk\u000e$XO]33+\u00051\bc\u0001\u0011.}\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003e\u00042\u0001I\u0017B\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002yB\u0019\u0001%\f#\u0002\u0015M$(/^2ukJ,W'F\u0001\u0000!\r\u0001SfR\u0001\u000bgR\u0014Xo\u0019;ve\u00164TCAA\u0003!\r\u0001SFS\u0001\u000bgR\u0014Xo\u0019;ve\u0016<TCAA\u0006!\r\u0001S&T\u0001\u000bgR\u0014Xo\u0019;ve\u0016DTCAA\t!\r\u0001S\u0006U\u0001\u000bgR\u0014Xo\u0019;ve\u0016LTCAA\f!\r\u0001SfU\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'\u0006\u0002\u0002\u001eA\u0019\u0001%\f,\u0002\u0017M$(/^2ukJ,\u0017'M\u000b\u0003\u0003G\u00012\u0001I\u0017Z\u0003-\u0019HO];diV\u0014X-\r\u001a\u0016\u0005\u0005%\u0002c\u0001\u0011.9\u0006Y1\u000f\u001e:vGR,(/Z\u00194+\t\ty\u0003E\u0002![}\u000b1b\u001d;sk\u000e$XO]32iU\u0011\u0011Q\u0007\t\u0004A5\u0012\u0017aC:ueV\u001cG/\u001e:fcU*\"!a\u000f\u0011\u0007\u0001jS-A\u0002p]\u0016,\u0012\u0001\r"
)
public interface RigProduct15 extends Rig, SemiringProduct15 {
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

   Rig structure12();

   Rig structure13();

   Rig structure14();

   Rig structure15();

   // $FF: synthetic method
   static Tuple15 one$(final RigProduct15 $this) {
      return $this.one();
   }

   default Tuple15 one() {
      return new Tuple15(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one());
   }

   static void $init$(final RigProduct15 $this) {
   }
}
