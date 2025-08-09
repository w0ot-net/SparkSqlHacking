package breeze.signal.support;

import breeze.signal.OptDesignMethod;
import breeze.signal.OptOverhang;
import breeze.signal.OptPadding;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3qa\u0002\u0005\u0011\u0002G\u0005q\u0002C\u0003\u0018\u0001\u0019\u0005\u0001dB\u0003K\u0011!\u00051JB\u0003\b\u0011!\u0005Q\nC\u0003O\u0007\u0011\u0005q\nC\u0004Q\u0007\t\u0007I1A)\t\re\u001b\u0001\u0015!\u0003S\u00055\u0019\u0015M\u001c$jYR,'\u000f\u0014)I!*\u0011\u0011BC\u0001\bgV\u0004\bo\u001c:u\u0015\tYA\"\u0001\u0004tS\u001et\u0017\r\u001c\u0006\u0002\u001b\u00051!M]3fu\u0016\u001c\u0001!F\u0002\u0011Om\u0019\"\u0001A\t\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g\u0003\u0015\t\u0007\u000f\u001d7z)%IB%\u000b\u00181ki\u0002U\t\u0005\u0002\u001b71\u0001A!\u0002\u000f\u0001\u0005\u0004i\"AB(viB,H/\u0005\u0002\u001fCA\u0011!cH\u0005\u0003AM\u0011qAT8uQ&tw\r\u0005\u0002\u0013E%\u00111e\u0005\u0002\u0004\u0003:L\b\"B\u0013\u0002\u0001\u00041\u0013\u0001\u00023bi\u0006\u0004\"AG\u0014\u0005\u000b!\u0002!\u0019A\u000f\u0003\u000b%s\u0007/\u001e;\t\u000b)\n\u0001\u0019A\u0016\u0002\u000b=lWmZ1\u0011\u0005Ia\u0013BA\u0017\u0014\u0005\u0019!u.\u001e2mK\")q&\u0001a\u0001W\u0005Q1/Y7qY\u0016\u0014\u0016\r^3\t\u000bE\n\u0001\u0019\u0001\u001a\u0002\tQ\f\u0007o\u001d\t\u0003%MJ!\u0001N\n\u0003\u0007%sG\u000fC\u00037\u0003\u0001\u0007q'A\u0004m_^\u0004\u0016m]:\u0011\u0005IA\u0014BA\u001d\u0014\u0005\u001d\u0011un\u001c7fC:DQaO\u0001A\u0002q\n!b[3s]\u0016dG+\u001f9f!\tid(D\u0001\u000b\u0013\ty$BA\bPaR$Um]5h]6+G\u000f[8e\u0011\u0015\t\u0015\u00011\u0001C\u0003!yg/\u001a:iC:<\u0007CA\u001fD\u0013\t!%BA\u0006PaR|e/\u001a:iC:<\u0007\"\u0002$\u0002\u0001\u00049\u0015a\u00029bI\u0012Lgn\u001a\t\u0003{!K!!\u0013\u0006\u0003\u0015=\u0003H\u000fU1eI&tw-A\u0007DC:4\u0015\u000e\u001c;fe2\u0003\u0006\n\u0015\t\u0003\u0019\u000ei\u0011\u0001C\n\u0003\u0007E\ta\u0001P5oSRtD#A&\u0002)\u00114Hi\\;cY\u0016\fDIR5mi\u0016\u0014H\n\u0015%Q+\u0005\u0011\u0006\u0003\u0002'\u0001'N\u00032\u0001V,,\u001b\u0005)&B\u0001,\r\u0003\u0019a\u0017N\\1mO&\u0011\u0001,\u0016\u0002\f\t\u0016t7/\u001a,fGR|'/A\u000bem\u0012{WO\u00197fc\u00113\u0015\u000e\u001c;fe2\u0003\u0006\n\u0015\u0011"
)
public interface CanFilterLPHP {
   static CanFilterLPHP dvDouble1DFilterLPHP() {
      return CanFilterLPHP$.MODULE$.dvDouble1DFilterLPHP();
   }

   Object apply(final Object data, final double omega, final double sampleRate, final int taps, final boolean lowPass, final OptDesignMethod kernelType, final OptOverhang overhang, final OptPadding padding);
}
