package org.apache.spark.deploy.worker;

import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3\u0001b\u0002\u0005\u0011\u0002G\u0005!B\u0005\u0005\u00063\u00011\ta\u0007\u0005\u0006I\u00011\t!J\u0004\u0007u!A\tAC\u001e\u0007\r\u001dA\u0001\u0012\u0001\u0006>\u0011\u0015qD\u0001\"\u0001@\u0011\u0015\u0001E\u0001\"\u0001B\u0005I\u0001&o\\2fgN\u0014U/\u001b7eKJd\u0015n[3\u000b\u0005%Q\u0011AB<pe.,'O\u0003\u0002\f\u0019\u00051A-\u001a9m_fT!!\u0004\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0001\u0012AB1qC\u000eDWMC\u0001\u0012\u0003\ry'oZ\n\u0003\u0001M\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0017!B:uCJ$8\u0001\u0001\u000b\u00029A\u0011QDI\u0007\u0002=)\u0011q\u0004I\u0001\u0005Y\u0006twMC\u0001\"\u0003\u0011Q\u0017M^1\n\u0005\rr\"a\u0002)s_\u000e,7o]\u0001\bG>lW.\u00198e+\u00051\u0003cA\u00140e9\u0011\u0001&\f\b\u0003S1j\u0011A\u000b\u0006\u0003Wi\ta\u0001\u0010:p_Rt\u0014\"\u0001\f\n\u00059*\u0012a\u00029bG.\fw-Z\u0005\u0003aE\u00121aU3r\u0015\tqS\u0003\u0005\u00024o9\u0011A'\u000e\t\u0003SUI!AN\u000b\u0002\rA\u0013X\rZ3g\u0013\tA\u0014H\u0001\u0004TiJLgn\u001a\u0006\u0003mU\t!\u0003\u0015:pG\u0016\u001c8OQ;jY\u0012,'\u000fT5lKB\u0011A\bB\u0007\u0002\u0011M\u0011AaE\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003m\nQ!\u00199qYf$\"AQ\"\u0011\u0005q\u0002\u0001\"\u0002#\u0007\u0001\u0004)\u0015A\u00049s_\u000e,7o\u001d\"vS2$WM\u001d\t\u0003;\u0019K!a\u0012\u0010\u0003\u001dA\u0013xnY3tg\n+\u0018\u000e\u001c3fe\u0002"
)
public interface ProcessBuilderLike {
   static ProcessBuilderLike apply(final ProcessBuilder processBuilder) {
      return ProcessBuilderLike$.MODULE$.apply(processBuilder);
   }

   Process start();

   Seq command();
}
