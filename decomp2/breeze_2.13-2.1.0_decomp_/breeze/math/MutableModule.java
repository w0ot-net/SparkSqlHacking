package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import scala.;
import scala.Function3;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%daB\u0007\u000f!\u0003\r\ta\u0005\u0005\u0006[\u0001!\tA\f\u0005\u0006e\u00011\u0019a\r\u0005\u0006y\u00011\u0019!\u0010\u0005\u0006\u0017\u0002!\u0019\u0001\u0014\u0005\u0006#\u00021\u0019A\u0015\u0005\u0006/\u00021\u0019\u0001\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006G\u00021\u0019\u0001Z\u0004\u0006Y:A\t!\u001c\u0004\u0006\u001b9A\tA\u001c\u0005\u0006_*!\t\u0001\u001d\u0005\u0006c*!\tA\u001d\u0002\u000e\u001bV$\u0018M\u00197f\u001b>$W\u000f\\3\u000b\u0005=\u0001\u0012\u0001B7bi\"T\u0011!E\u0001\u0007EJ,WM_3\u0004\u0001U\u0019A#I\u0016\u0014\u0007\u0001)2\u0004\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f\u0014VM\u001a\t\u00059uy\"&D\u0001\u000f\u0013\tqbB\u0001\u0004N_\u0012,H.\u001a\t\u0003A\u0005b\u0001\u0001B\u0003#\u0001\t\u00071EA\u0001W#\t!s\u0005\u0005\u0002\u0017K%\u0011ae\u0006\u0002\b\u001d>$\b.\u001b8h!\t1\u0002&\u0003\u0002*/\t\u0019\u0011I\\=\u0011\u0005\u0001ZC!\u0002\u0017\u0001\u0005\u0004\u0019#!A*\u0002\r\u0011Jg.\u001b;%)\u0005y\u0003C\u0001\f1\u0013\t\ttC\u0001\u0003V]&$\u0018\u0001B2paf,\u0012\u0001\u000e\t\u0004kizR\"\u0001\u001c\u000b\u0005]B\u0014aB:vaB|'\u000f\u001e\u0006\u0003sA\ta\u0001\\5oC2<\u0017BA\u001e7\u0005\u001d\u0019\u0015M\\\"paf\f\u0011\"\\;m\u0013:$xNV*\u0016\u0003y\u0002BaP# U9\u0011\u0001iQ\u0007\u0002\u0003*\u0011!\tO\u0001\n_B,'/\u0019;peNL!\u0001R!\u0002\u0017=\u0003X*\u001e7TG\u0006d\u0017M]\u0005\u0003\r\u001e\u0013A\"\u00138QY\u0006\u001cW-S7qYJJ!\u0001S%\u0003\u000bU3UO\\2\u000b\u0005)\u0003\u0012aB4f]\u0016\u0014\u0018nY\u0001\f[Vd\u0017J\u001c;p-N{V*F\u0001N!\u0011qUi\b\u0016\u000f\u0005\u0001{\u0015B\u0001)B\u0003-y\u0005/T;m\u001b\u0006$(/\u001b=\u0002\u0013\u0005$G-\u00138u_Z3V#A*\u0011\tQ+ud\b\b\u0003\u0001VK!AV!\u0002\u000b=\u0003\u0018\t\u001a3\u0002\u0013M,(-\u00138u_Z3V#A-\u0011\ti+ud\b\b\u0003\u0001nK!\u0001X!\u0002\u000b=\u00038+\u001e2\u0002\u0013M,G/\u00138u_Z3V#A0\u0011\t\u0001,ud\b\b\u0003\u0001\u0006L!AY!\u0002\u000b=\u00038+\u001a;\u0002\u0015M\u001c\u0017\r\\3BI\u00124f+F\u0001f!\u00151'n\b\u0016 \u001d\t9\u0007.D\u00019\u0013\tI\u0007(\u0001\u0005tG\u0006dW-\u00113e\u0013\tYwI\u0001\u0007J]Bc\u0017mY3J[Bd7'A\u0007NkR\f'\r\\3N_\u0012,H.\u001a\t\u00039)\u0019\"AC\u000b\u0002\rqJg.\u001b;?)\u0005i\u0017\u0001B7bW\u0016,2a]<z)\r!\u00181\u000b\u000b\u0018kj|\u0018\u0011BA\r\u0003G\tI#a\f\u00026\u0005m\u0012\u0011IA$\u0003\u001b\u0002B\u0001\b\u0001wqB\u0011\u0001e\u001e\u0003\u0006E1\u0011\ra\t\t\u0003Ae$Q\u0001\f\u0007C\u0002\rBQa\u001f\u0007A\u0004q\fQa\u0018:j]\u001e\u00042\u0001H?y\u0013\tqhB\u0001\u0003SS:<\u0007bBA\u0001\u0019\u0001\u000f\u00111A\u0001\n?j,'o\u001c'jW\u0016\u0004R!NA\u0003mZL1!a\u00027\u0005I\u0019\u0015M\\\"sK\u0006$XMW3s_Nd\u0015n[3\t\u000f\u0005-A\u0002q\u0001\u0002\u000e\u0005!ql\u001c9t!\u00191\u0012q\u0002<\u0002\u0014%\u0019\u0011\u0011C\f\u0003!\u0011bWm]:%G>dwN\u001c\u0013mKN\u001c\b\u0003B4\u0002\u0016YL1!a\u00069\u0005)qU/\\3sS\u000e|\u0005o\u001d\u0005\b\u00037a\u00019AA\u000f\u0003\u0019yV.\u001e7W'B1q(a\bwqZL1!!\tH\u0005\u0015IU\u000e\u001d73\u0011\u001d\t)\u0003\u0004a\u0002\u0003O\taaX1eIZ3\u0006C\u0002+\u0002 Y4h\u000fC\u0004\u0002,1\u0001\u001d!!\f\u0002\r}\u001bXO\u0019,W!\u0019Q\u0016q\u0004<wm\"9\u0011\u0011\u0007\u0007A\u0004\u0005M\u0012!B0d_BL\bcA\u001b;m\"9\u0011q\u0007\u0007A\u0004\u0005e\u0012AC0nk2Le\u000e^8W'B!q(\u0012<y\u0011\u001d\ti\u0004\u0004a\u0002\u0003\u007f\t!bX1eI&sGo\u001c,W!\u0011!VI\u001e<\t\u000f\u0005\rC\u0002q\u0001\u0002F\u0005Qql];c\u0013:$xN\u0016,\u0011\ti+eO\u001e\u0005\b\u0003\u0013b\u00019AA&\u0003)y6/\u001a;J]R|gK\u0016\t\u0005A\u00163h\u000fC\u0004\u0002P1\u0001\u001d!!\u0015\u0002\u0019}\u001b8-\u00197f\u0003\u0012$gk\u0015,\u0011\u000b\u0019Tg\u000f\u001f<\t\u000f\u0005UC\u00021\u0001\u0002X\u000591\r\\8tKR{\u0007#\u0003\f\u0002ZY4\u0018QLA2\u0013\r\tYf\u0006\u0002\n\rVt7\r^5p]N\u00022AFA0\u0013\r\t\tg\u0006\u0002\u0007\t>,(\r\\3\u0011\u0007Y\t)'C\u0002\u0002h]\u0011qAQ8pY\u0016\fg\u000e"
)
public interface MutableModule extends Module {
   static MutableModule make(final Function3 closeTo, final Ring _ring, final CanCreateZerosLike _zeroLike, final .less.colon.less _ops, final UFunc.UImpl2 _mulVS, final UFunc.UImpl2 _addVV, final UFunc.UImpl2 _subVV, final CanCopy _copy, final UFunc.InPlaceImpl2 _mulIntoVS, final UFunc.InPlaceImpl2 _addIntoVV, final UFunc.InPlaceImpl2 _subIntoVV, final UFunc.InPlaceImpl2 _setIntoVV, final UFunc.InPlaceImpl3 _scaleAddVSV) {
      return MutableModule$.MODULE$.make(closeTo, _ring, _zeroLike, _ops, _mulVS, _addVV, _subVV, _copy, _mulIntoVS, _addIntoVV, _subIntoVV, _setIntoVV, _scaleAddVSV);
   }

   CanCopy copy();

   UFunc.InPlaceImpl2 mulIntoVS();

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 mulIntoVS_M$(final MutableModule $this) {
      return $this.mulIntoVS_M();
   }

   default UFunc.InPlaceImpl2 mulIntoVS_M() {
      return this.mulIntoVS();
   }

   UFunc.InPlaceImpl2 addIntoVV();

   UFunc.InPlaceImpl2 subIntoVV();

   UFunc.InPlaceImpl2 setIntoVV();

   UFunc.InPlaceImpl3 scaleAddVV();

   static void $init$(final MutableModule $this) {
   }
}
