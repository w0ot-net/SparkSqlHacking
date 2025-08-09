package breeze.signal.support;

import breeze.signal.OptDesignMethod;
import breeze.signal.OptOverhang;
import breeze.signal.OptPadding;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3qa\u0002\u0005\u0011\u0002G\u0005q\u0002C\u0003\u0018\u0001\u0019\u0005\u0001dB\u0003N\u0011!\u0005aJB\u0003\b\u0011!\u0005\u0001\u000bC\u0003R\u0007\u0011\u0005!\u000bC\u0004T\u0007\t\u0007I1\u0001+\t\rq\u001b\u0001\u0015!\u0003V\u00055\u0019\u0015M\u001c$jYR,'O\u0011)C'*\u0011\u0011BC\u0001\bgV\u0004\bo\u001c:u\u0015\tYA\"\u0001\u0004tS\u001et\u0017\r\u001c\u0006\u0002\u001b\u00051!M]3fu\u0016\u001c\u0001!F\u0002\u0011Om\u0019\"\u0001A\t\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g\u0003\u0015\t\u0007\u000f\u001d7z)%IB%K\u00194qu\u001a\u0005\n\u0005\u0002\u001b71\u0001A!\u0002\u000f\u0001\u0005\u0004i\"AB(viB,H/\u0005\u0002\u001fCA\u0011!cH\u0005\u0003AM\u0011qAT8uQ&tw\r\u0005\u0002\u0013E%\u00111e\u0005\u0002\u0004\u0003:L\b\"B\u0013\u0002\u0001\u00041\u0013\u0001\u00023bi\u0006\u0004\"AG\u0014\u0005\u000b!\u0002!\u0019A\u000f\u0003\u000b%s\u0007/\u001e;\t\u000b)\n\u0001\u0019A\u0016\u0002\u000b=lWmZ1\u0011\tIacFL\u0005\u0003[M\u0011a\u0001V;qY\u0016\u0014\u0004C\u0001\n0\u0013\t\u00014C\u0001\u0004E_V\u0014G.\u001a\u0005\u0006e\u0005\u0001\rAL\u0001\u000bg\u0006l\u0007\u000f\\3SCR,\u0007\"\u0002\u001b\u0002\u0001\u0004)\u0014\u0001\u0002;baN\u0004\"A\u0005\u001c\n\u0005]\u001a\"aA%oi\")\u0011(\u0001a\u0001u\u0005A!-\u00198e'R|\u0007\u000f\u0005\u0002\u0013w%\u0011Ah\u0005\u0002\b\u0005>|G.Z1o\u0011\u0015q\u0014\u00011\u0001@\u0003)YWM\u001d8fYRK\b/\u001a\t\u0003\u0001\u0006k\u0011AC\u0005\u0003\u0005*\u0011qb\u00149u\t\u0016\u001c\u0018n\u001a8NKRDw\u000e\u001a\u0005\u0006\t\u0006\u0001\r!R\u0001\t_Z,'\u000f[1oOB\u0011\u0001IR\u0005\u0003\u000f*\u00111b\u00149u\u001fZ,'\u000f[1oO\")\u0011*\u0001a\u0001\u0015\u00069\u0001/\u00193eS:<\u0007C\u0001!L\u0013\ta%B\u0001\u0006PaR\u0004\u0016\r\u001a3j]\u001e\fQbQ1o\r&dG/\u001a:C!\n\u001b\u0006CA(\u0004\u001b\u0005A1CA\u0002\u0012\u0003\u0019a\u0014N\\5u}Q\ta*\u0001\u000bem\u0012{WO\u00197fc\u00113\u0015\u000e\u001c;fe\n\u0003&iU\u000b\u0002+B!q\n\u0001,W!\r9&LL\u0007\u00021*\u0011\u0011\fD\u0001\u0007Y&t\u0017\r\\4\n\u0005mC&a\u0003#f]N,g+Z2u_J\fQ\u0003\u001a<E_V\u0014G.Z\u0019E\r&dG/\u001a:C!\n\u001b\u0006\u0005"
)
public interface CanFilterBPBS {
   static CanFilterBPBS dvDouble1DFilterBPBS() {
      return CanFilterBPBS$.MODULE$.dvDouble1DFilterBPBS();
   }

   Object apply(final Object data, final Tuple2 omega, final double sampleRate, final int taps, final boolean bandStop, final OptDesignMethod kernelType, final OptOverhang overhang, final OptPadding padding);
}
