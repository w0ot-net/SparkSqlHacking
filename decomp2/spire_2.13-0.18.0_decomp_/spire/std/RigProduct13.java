package spire.std;

import algebra.ring.Rig;
import scala.Tuple13;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b\u0001\u0003\t\u0012!\u0003\r\taE\u000b\t\u000b\u0011\u0004A\u0011A3\t\u000b%\u0004a1\u00016\t\u000b1\u0004a1A7\t\u000b=\u0004a1\u00019\t\u000bI\u0004a1A:\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\t\u0003\t\u0019C\u0001\u0007SS\u001e\u0004&o\u001c3vGR\f4G\u0003\u0002\u0013'\u0005\u00191\u000f\u001e3\u000b\u0003Q\tQa\u001d9je\u0016,bBF\u001a>\u0001\u000e3\u0015\nT(S+b[fl\u0005\u0003\u0001/u\u0001\u0007C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"AB!osJ+g\rE\u0002\u001fW9r!a\b\u0015\u000f\u0005\u00012cBA\u0011&\u001b\u0005\u0011#BA\u0012%\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u000b\n\u0005\u001d\u001a\u0012aB1mO\u0016\u0014'/Y\u0005\u0003S)\nq\u0001]1dW\u0006<WM\u0003\u0002('%\u0011A&\f\u0002\u0004%&<'BA\u0015+!=Ar&\r\u001f@\u0005\u0016C5JT)U/jk\u0016B\u0001\u0019\u001a\u0005\u001d!V\u000f\u001d7fcM\u0002\"AM\u001a\r\u0001\u0011)A\u0007\u0001b\u0001k\t\t\u0011)\u0005\u00027sA\u0011\u0001dN\u0005\u0003qe\u0011qAT8uQ&tw\r\u0005\u0002\u0019u%\u00111(\u0007\u0002\u0004\u0003:L\bC\u0001\u001a>\t\u0015q\u0004A1\u00016\u0005\u0005\u0011\u0005C\u0001\u001aA\t\u0015\t\u0005A1\u00016\u0005\u0005\u0019\u0005C\u0001\u001aD\t\u0015!\u0005A1\u00016\u0005\u0005!\u0005C\u0001\u001aG\t\u00159\u0005A1\u00016\u0005\u0005)\u0005C\u0001\u001aJ\t\u0015Q\u0005A1\u00016\u0005\u00051\u0005C\u0001\u001aM\t\u0015i\u0005A1\u00016\u0005\u00059\u0005C\u0001\u001aP\t\u0015\u0001\u0006A1\u00016\u0005\u0005A\u0005C\u0001\u001aS\t\u0015\u0019\u0006A1\u00016\u0005\u0005I\u0005C\u0001\u001aV\t\u00151\u0006A1\u00016\u0005\u0005Q\u0005C\u0001\u001aY\t\u0015I\u0006A1\u00016\u0005\u0005Y\u0005C\u0001\u001a\\\t\u0015a\u0006A1\u00016\u0005\u0005a\u0005C\u0001\u001a_\t\u0015y\u0006A1\u00016\u0005\u0005i\u0005cD1ccqz$)\u0012%L\u001dF#vKW/\u000e\u0003EI!aY\t\u0003#M+W.\u001b:j]\u001e\u0004&o\u001c3vGR\f4'\u0001\u0004%S:LG\u000f\n\u000b\u0002MB\u0011\u0001dZ\u0005\u0003Qf\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003-\u00042AH\u00162\u0003)\u0019HO];diV\u0014XMM\u000b\u0002]B\u0019ad\u000b\u001f\u0002\u0015M$(/^2ukJ,7'F\u0001r!\rq2fP\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u0001;\u0011\u0007yY#)\u0001\u0006tiJ,8\r^;sKV*\u0012a\u001e\t\u0004=-*\u0015AC:ueV\u001cG/\u001e:fmU\t!\u0010E\u0002\u001fW!\u000b!b\u001d;sk\u000e$XO]38+\u0005i\bc\u0001\u0010,\u0017\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005\u0005\u0001c\u0001\u0010,\u001d\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005\u001d\u0001c\u0001\u0010,#\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\ti\u0001E\u0002\u001fWQ\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u00111\u0003\t\u0004=-:\u0016aC:ueV\u001cG/\u001e:fcI*\"!!\u0007\u0011\u0007yY#,A\u0006tiJ,8\r^;sKF\u001aTCAA\u0010!\rq2&X\u0001\u0004_:,W#\u0001\u0018"
)
public interface RigProduct13 extends Rig, SemiringProduct13 {
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

   // $FF: synthetic method
   static Tuple13 one$(final RigProduct13 $this) {
      return $this.one();
   }

   default Tuple13 one() {
      return new Tuple13(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one());
   }

   static void $init$(final RigProduct13 $this) {
   }
}
