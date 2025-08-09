package breeze.signal.support;

import breeze.signal.OptOverhang;
import breeze.signal.OptPadding;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014qa\u0003\u0007\u0011\u0002G\u00051\u0003C\u0003\u001c\u0001\u0019\u0005AdB\u0003>\u0019!\u0005aHB\u0003\f\u0019!\u0005\u0001\tC\u0003B\u0007\u0011\u0005!\tC\u0004D\u0007\t\u0007I1\u0001#\t\rI\u001b\u0001\u0015!\u0003F\u0011\u001d\u00196A1A\u0005\u0004QCaaW\u0002!\u0002\u0013)\u0006b\u0002/\u0004\u0005\u0004%\u0019!\u0018\u0005\u0007?\u000e\u0001\u000b\u0011\u00020\u0003\u0013\r\u000bgNR5mi\u0016\u0014(BA\u0007\u000f\u0003\u001d\u0019X\u000f\u001d9peRT!a\u0004\t\u0002\rMLwM\\1m\u0015\u0005\t\u0012A\u00022sK\u0016TXm\u0001\u0001\u0016\tQY\u0003gH\n\u0003\u0001U\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0017!B1qa2LH#B\u000f)[IB\u0004C\u0001\u0010 \u0019\u0001!Q\u0001\t\u0001C\u0002\u0005\u0012aaT;uaV$\u0018C\u0001\u0012&!\t12%\u0003\u0002%/\t9aj\u001c;iS:<\u0007C\u0001\f'\u0013\t9sCA\u0002B]fDQ!K\u0001A\u0002)\nA\u0001Z1uCB\u0011ad\u000b\u0003\u0006Y\u0001\u0011\r!\t\u0002\u0006\u0013:\u0004X\u000f\u001e\u0005\u0006]\u0005\u0001\raL\u0001\u0007W\u0016\u0014h.\u001a7\u0011\u0005y\u0001D!B\u0019\u0001\u0005\u0004\t#AC&fe:,G\u000eV=qK\")1'\u0001a\u0001i\u0005AqN^3sQ\u0006tw\r\u0005\u00026m5\ta\"\u0003\u00028\u001d\tYq\n\u001d;Pm\u0016\u0014\b.\u00198h\u0011\u0015I\u0014\u00011\u0001;\u0003\u001d\u0001\u0018\r\u001a3j]\u001e\u0004\"!N\u001e\n\u0005qr!AC(qiB\u000bG\rZ5oO\u0006I1)\u00198GS2$XM\u001d\t\u0003\u007f\ri\u0011\u0001D\n\u0003\u0007U\ta\u0001P5oSRtD#\u0001 \u0002!\u00114Hi\\;cY\u0016\fDIR5mi\u0016\u0014X#A#\u0011\u000b}\u0002ai\u0014$\u0011\u0007\u001dSE*D\u0001I\u0015\tI\u0005#\u0001\u0004mS:\fGnZ\u0005\u0003\u0017\"\u00131\u0002R3og\u00164Vm\u0019;peB\u0011a#T\u0005\u0003\u001d^\u0011a\u0001R8vE2,\u0007cA Q\u0019&\u0011\u0011\u000b\u0004\u0002\f\r&\u00136*\u001a:oK2\fD)A\tem\u0012{WO\u00197fc\u00113\u0015\u000e\u001c;fe\u0002\nQ\u0002\u001a<J]R\fDIR5mi\u0016\u0014X#A+\u0011\u000b}\u0002aK\u0017,\u0011\u0007\u001dSu\u000b\u0005\u0002\u00171&\u0011\u0011l\u0006\u0002\u0004\u0013:$\bcA Q/\u0006qAM^%oiF\"e)\u001b7uKJ\u0004\u0013\u0001\b3w\t>,(\r\\32\t\u001aKG\u000e^3s-\u0016\u001cGo\u001c:LKJtW\r\\\u000b\u0002=B)q\b\u0001$G\r\u0006iBM\u001e#pk\ndW-\r#GS2$XM\u001d,fGR|'oS3s]\u0016d\u0007\u0005"
)
public interface CanFilter {
   static CanFilter dvDouble1DFilterVectorKernel() {
      return CanFilter$.MODULE$.dvDouble1DFilterVectorKernel();
   }

   static CanFilter dvInt1DFilter() {
      return CanFilter$.MODULE$.dvInt1DFilter();
   }

   static CanFilter dvDouble1DFilter() {
      return CanFilter$.MODULE$.dvDouble1DFilter();
   }

   Object apply(final Object data, final Object kernel, final OptOverhang overhang, final OptPadding padding);
}
