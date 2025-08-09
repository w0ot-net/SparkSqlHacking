package breeze.signal.support;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2Q\u0001B\u0003\u0002\u00021AQ\u0001\u0006\u0001\u0005\u0002UAqa\t\u0001C\u0002\u001b\u0005A\u0005C\u00031\u0001\u0011\u0005\u0013G\u0001\u0007GS2$XM]&fe:,GN\u0003\u0002\u0007\u000f\u000591/\u001e9q_J$(B\u0001\u0005\n\u0003\u0019\u0019\u0018n\u001a8bY*\t!\"\u0001\u0004ce\u0016,'0Z\u0002\u0001+\ti!d\u0005\u0002\u0001\u001dA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\f\u0011\u0007]\u0001\u0001$D\u0001\u0006!\tI\"\u0004\u0004\u0001\u0005\u000bm\u0001!\u0019\u0001\u000f\u0003\u0003Q\u000b\"!\b\u0011\u0011\u0005=q\u0012BA\u0010\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aD\u0011\n\u0005\t\u0002\"aA!os\u0006QA-Z:jO:$V\r\u001f;\u0016\u0003\u0015\u0002\"AJ\u0017\u000f\u0005\u001dZ\u0003C\u0001\u0015\u0011\u001b\u0005I#B\u0001\u0016\f\u0003\u0019a$o\\8u}%\u0011A\u0006E\u0001\u0007!J,G-\u001a4\n\u00059z#AB*ue&twM\u0003\u0002-!\u0005AAo\\*ue&tw\rF\u00013!\t\u0019\u0004(D\u00015\u0015\t)d'\u0001\u0003mC:<'\"A\u001c\u0002\t)\fg/Y\u0005\u0003]Q\u0002"
)
public abstract class FilterKernel {
   public abstract String designText();

   public String toString() {
      return (new StringBuilder(4)).append(this.getClass().getSimpleName()).append("(): ").append(this.designText()).toString();
   }
}
