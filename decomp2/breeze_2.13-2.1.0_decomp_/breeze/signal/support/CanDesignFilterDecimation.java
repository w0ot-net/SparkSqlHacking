package breeze.signal.support;

import breeze.signal.OptDesignMethod;
import breeze.signal.OptFilterTaps;
import breeze.signal.OptWindowFunction;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3qa\u0002\u0005\u0011\u0002G\u0005q\u0002C\u0003\u0018\u0001\u0019\u0005\u0001dB\u0003?\u0011!\u0005qHB\u0003\b\u0011!\u0005\u0011\tC\u0003C\u0007\u0011\u00051\tC\u0003E\u0007\u0011\rQ\tC\u0003K\u0007\u0011\r1JA\rDC:$Um]5h]\u001aKG\u000e^3s\t\u0016\u001c\u0017.\\1uS>t'BA\u0005\u000b\u0003\u001d\u0019X\u000f\u001d9peRT!a\u0003\u0007\u0002\rMLwM\\1m\u0015\u0005i\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005AY2C\u0001\u0001\u0012!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fM\u0006)\u0011\r\u001d9msR1\u0011\u0004J\u0015/ie\u0002\"AG\u000e\r\u0001\u0011)A\u0004\u0001b\u0001;\t1q*\u001e;qkR\f\"AH\u0011\u0011\u0005Iy\u0012B\u0001\u0011\u0014\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0005\u0012\n\u0005\r\u001a\"aA!os\")Q%\u0001a\u0001M\u00051a-Y2u_J\u0004\"AE\u0014\n\u0005!\u001a\"aA%oi\")!&\u0001a\u0001W\u0005QQ.\u001e7uSBd\u0017.\u001a:\u0011\u0005Ia\u0013BA\u0017\u0014\u0005\u0019!u.\u001e2mK\")q&\u0001a\u0001a\u0005yq\u000e\u001d;EKNLwM\\'fi\"|G\r\u0005\u00022e5\t!\"\u0003\u00024\u0015\tyq\n\u001d;EKNLwM\\'fi\"|G\rC\u00036\u0003\u0001\u0007a'A\u0005paR<\u0016N\u001c3poB\u0011\u0011gN\u0005\u0003q)\u0011\u0011c\u00149u/&tGm\\<Gk:\u001cG/[8o\u0011\u0015Q\u0014\u00011\u0001<\u00039y\u0007\u000f\u001e$jYR,'o\u0014:eKJ\u0004\"!\r\u001f\n\u0005uR!!D(qi\u001aKG\u000e^3s)\u0006\u00048/A\rDC:$Um]5h]\u001aKG\u000e^3s\t\u0016\u001c\u0017.\\1uS>t\u0007C\u0001!\u0004\u001b\u0005A1CA\u0002\u0012\u0003\u0019a\u0014N\\5u}Q\tq(\u0001\feK\u000eLW.\u0019;j_:4\u0015\u000e\u001c;fe\u0012{WO\u00197f+\u00051\u0005c\u0001!\u0001\u000fB\u0019\u0001\tS\u0016\n\u0005%C!a\u0003$J%.+'O\\3mc\u0011\u000bA\u0003Z3dS6\fG/[8o\r&dG/\u001a:M_:<W#\u0001'\u0011\u0007\u0001\u0003Q\nE\u0002A\u0011:\u0003\"AE(\n\u0005A\u001b\"\u0001\u0002'p]\u001e\u0004"
)
public interface CanDesignFilterDecimation {
   static CanDesignFilterDecimation decimationFilterLong() {
      return CanDesignFilterDecimation$.MODULE$.decimationFilterLong();
   }

   static CanDesignFilterDecimation decimationFilterDouble() {
      return CanDesignFilterDecimation$.MODULE$.decimationFilterDouble();
   }

   Object apply(final int factor, final double multiplier, final OptDesignMethod optDesignMethod, final OptWindowFunction optWindow, final OptFilterTaps optFilterOrder);
}
