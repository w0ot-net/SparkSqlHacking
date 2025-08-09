package org.apache.spark;

import java.io.Serializable;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed!B\n\u0015\u0001QQ\u0002\u0002\u0003\u0016\u0001\u0005\u000b\u0007I\u0011\u0001\u0017\t\u0011A\u0003!\u0011!Q\u0001\n5B\u0001\"\u0017\u0001\u0003\u0006\u0004%\tA\u0017\u0005\t9\u0002\u0011\t\u0011)A\u00057\")Q\f\u0001C\u0001=\u001e)\u0001\u000e\u0006E\u0001S\u001a)1\u0003\u0006E\u0001U\")Ql\u0002C\u0001W\"1An\u0002C\u0001)5DQA`\u0004\u0005\u0004}Dq!!\u0003\b\t\u0007\tY\u0001C\u0004\u0002\u0016\u001d!\u0019!a\u0006\t\u000f\u0005\u0005r\u0001b\u0001\u0002$!9\u0011QF\u0004\u0005\u0004\u0005=\u0002bBA\u001d\u000f\u0011\r\u00111\b\u0005\b\u0003\u0017:A1AA'\u0011\u001d\t9f\u0002C\u0002\u00033B\u0011\"!\u001b\b\u0003\u0003%I!a\u001b\u0003\u001f]\u0013\u0018\u000e^1cY\u00164\u0015m\u0019;pefT!!\u0006\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005]A\u0012AB1qC\u000eDWMC\u0001\u001a\u0003\ry'oZ\u000b\u00037a\u001a2\u0001\u0001\u000f#!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\u0019\te.\u001f*fMB\u00111\u0005K\u0007\u0002I)\u0011QEJ\u0001\u0003S>T\u0011aJ\u0001\u0005U\u00064\u0018-\u0003\u0002*I\ta1+\u001a:jC2L'0\u00192mK\u0006iqO]5uC\ndWm\u00117bgN\u001c\u0001!F\u0001.!\u0011ib\u0006M!\n\u0005=r\"!\u0003$v]\u000e$\u0018n\u001c82!\r\tDGN\u0007\u0002e)\u00111GH\u0001\be\u00164G.Z2u\u0013\t)$G\u0001\u0005DY\u0006\u001c8\u000fV1h!\t9\u0004\b\u0004\u0001\u0005\u000be\u0002!\u0019\u0001\u001e\u0003\u0003Q\u000b\"a\u000f \u0011\u0005ua\u0014BA\u001f\u001f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!H \n\u0005\u0001s\"aA!osB\u0012!I\u0014\t\u0004\u0007*keB\u0001#I!\t)e$D\u0001G\u0015\t95&\u0001\u0004=e>|GOP\u0005\u0003\u0013z\ta\u0001\u0015:fI\u00164\u0017BA&M\u0005\u0015\u0019E.Y:t\u0015\tIe\u0004\u0005\u00028\u001d\u0012IqJAA\u0001\u0002\u0003\u0015\t!\u0015\u0002\u0005?\u0012\nd'\u0001\bxe&$\u0018M\u00197f\u00072\f7o\u001d\u0011\u0012\u0005m\u0012\u0006CA*X\u001b\u0005!&BA\u0013V\u0015\t1f#\u0001\u0004iC\u0012|w\u000e]\u0005\u00031R\u0013\u0001b\u0016:ji\u0006\u0014G.Z\u0001\bG>tg/\u001a:u+\u0005Y\u0006\u0003B\u000f/mI\u000b\u0001bY8om\u0016\u0014H\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007}\u000bw\rE\u0002a\u0001Yj\u0011\u0001\u0006\u0005\u0006U\u0015\u0001\rA\u0019\t\u0005;9\u00024\r\r\u0002eMB\u00191IS3\u0011\u0005]2G!C(b\u0003\u0003\u0005\tQ!\u0001R\u0011\u0015IV\u00011\u0001\\\u0003=9&/\u001b;bE2,g)Y2u_JL\bC\u00011\b'\r9AD\t\u000b\u0002S\u0006)2/[7qY\u0016<&/\u001b;bE2,g)Y2u_JLXc\u00018suR\u0011q\u000e \u000b\u0004aN4\bc\u00011\u0001cB\u0011qG\u001d\u0003\u0006s%\u0011\rA\u000f\u0005\bi&\t\t\u0011q\u0001v\u0003-)g/\u001b3f]\u000e,GE\r\u0019\u0011\u0007E\"\u0014\u000fC\u0004x\u0013\u0005\u0005\t9\u0001=\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#'\r\t\u0004cQJ\bCA\u001c{\t\u0015Y\u0018B1\u0001R\u0005\u00059\u0006\"B-\n\u0001\u0004i\b\u0003B\u000f/cf\f!#\u001b8u/JLG/\u00192mK\u001a\u000b7\r^8ssV\u0011\u0011\u0011\u0001\t\u0005A\u0002\t\u0019\u0001E\u0002\u001e\u0003\u000bI1!a\u0002\u001f\u0005\rIe\u000e^\u0001\u0014Y>twm\u0016:ji\u0006\u0014G.\u001a$bGR|'/_\u000b\u0003\u0003\u001b\u0001B\u0001\u0019\u0001\u0002\u0010A\u0019Q$!\u0005\n\u0007\u0005MaD\u0001\u0003M_:<\u0017\u0001\u00064m_\u0006$xK]5uC\ndWMR1di>\u0014\u00180\u0006\u0002\u0002\u001aA!\u0001\rAA\u000e!\ri\u0012QD\u0005\u0004\u0003?q\"!\u0002$m_\u0006$\u0018!\u00063pk\ndWm\u0016:ji\u0006\u0014G.\u001a$bGR|'/_\u000b\u0003\u0003K\u0001B\u0001\u0019\u0001\u0002(A\u0019Q$!\u000b\n\u0007\u0005-bD\u0001\u0004E_V\u0014G.Z\u0001\u0017E>|G.Z1o/JLG/\u00192mK\u001a\u000b7\r^8ssV\u0011\u0011\u0011\u0007\t\u0005A\u0002\t\u0019\u0004E\u0002\u001e\u0003kI1!a\u000e\u001f\u0005\u001d\u0011un\u001c7fC:\fACY=uKN<&/\u001b;bE2,g)Y2u_JLXCAA\u001f!\u0011\u0001\u0007!a\u0010\u0011\u000bu\t\t%!\u0012\n\u0007\u0005\rcDA\u0003BeJ\f\u0017\u0010E\u0002\u001e\u0003\u000fJ1!!\u0013\u001f\u0005\u0011\u0011\u0015\u0010^3\u0002+M$(/\u001b8h/JLG/\u00192mK\u001a\u000b7\r^8ssV\u0011\u0011q\n\t\u0005A\u0002\t\t\u0006E\u0002D\u0003'J1!!\u0016M\u0005\u0019\u0019FO]5oO\u00069rO]5uC\ndWm\u0016:ji\u0006\u0014G.\u001a$bGR|'/_\u000b\u0005\u00037\n\t\u0007\u0006\u0003\u0002^\u0005\r\u0004\u0003\u00021\u0001\u0003?\u00022aNA1\t\u0015I\u0014C1\u0001R\u0011%\t)'EA\u0001\u0002\b\t9'A\u0006fm&$WM\\2fII\u0012\u0004\u0003B\u00195\u0003?\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u001c\u0011\t\u0005=\u0014QO\u0007\u0003\u0003cR1!a\u001d'\u0003\u0011a\u0017M\\4\n\t\u0005]\u0014\u0011\u000f\u0002\u0007\u001f\nTWm\u0019;"
)
public class WritableFactory implements Serializable {
   private final Function1 writableClass;
   private final Function1 convert;

   public static WritableFactory writableWritableFactory(final ClassTag evidence$22) {
      return WritableFactory$.MODULE$.writableWritableFactory(evidence$22);
   }

   public static WritableFactory stringWritableFactory() {
      return WritableFactory$.MODULE$.stringWritableFactory();
   }

   public static WritableFactory bytesWritableFactory() {
      return WritableFactory$.MODULE$.bytesWritableFactory();
   }

   public static WritableFactory booleanWritableFactory() {
      return WritableFactory$.MODULE$.booleanWritableFactory();
   }

   public static WritableFactory doubleWritableFactory() {
      return WritableFactory$.MODULE$.doubleWritableFactory();
   }

   public static WritableFactory floatWritableFactory() {
      return WritableFactory$.MODULE$.floatWritableFactory();
   }

   public static WritableFactory longWritableFactory() {
      return WritableFactory$.MODULE$.longWritableFactory();
   }

   public static WritableFactory intWritableFactory() {
      return WritableFactory$.MODULE$.intWritableFactory();
   }

   public Function1 writableClass() {
      return this.writableClass;
   }

   public Function1 convert() {
      return this.convert;
   }

   public WritableFactory(final Function1 writableClass, final Function1 convert) {
      this.writableClass = writableClass;
      this.convert = convert;
   }
}
