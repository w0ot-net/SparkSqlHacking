package algebra.instances;

import algebra.lattice.GenBool;
import algebra.ring.BoolRng;
import algebra.ring.Semiring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011\r\u0001\u0005C\u0003?\u0001\u0011\rq\bC\u0003K\u0001\u0011\u00051J\u0001\u0007TKRLen\u001d;b]\u000e,7O\u0003\u0002\b\u0011\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0002\u0013\u00059\u0011\r\\4fEJ\f7\u0001A\n\u0004\u00011\u0011\u0002CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\r\u0005\u0002\u001435\tAC\u0003\u0002\b+)\u0011acF\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003a\tAaY1ug&\u0011Q\u0001F\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003q\u0001\"!D\u000f\n\u0005yq!\u0001B+oSR\f!b]3u\u0019\u0006$H/[2f+\t\tS'F\u0001#!\r\u0019c\u0005K\u0007\u0002I)\u0011Q\u0005C\u0001\bY\u0006$H/[2f\u0013\t9CEA\u0004HK:\u0014un\u001c7\u0011\u0007%\u00024G\u0004\u0002+]A\u00111FD\u0007\u0002Y)\u0011QFC\u0001\u0007yI|w\u000e\u001e \n\u0005=r\u0011A\u0002)sK\u0012,g-\u0003\u00022e\t\u00191+\u001a;\u000b\u0005=r\u0001C\u0001\u001b6\u0019\u0001!QA\u000e\u0002C\u0002]\u0012\u0011!Q\t\u0003qm\u0002\"!D\u001d\n\u0005ir!a\u0002(pi\"Lgn\u001a\t\u0003\u001bqJ!!\u0010\b\u0003\u0007\u0005s\u00170A\u0006tKR\u001cV-\\5sS:<WC\u0001!J+\u0005\t\u0005c\u0001\"F\u000f6\t1I\u0003\u0002E\u0011\u0005!!/\u001b8h\u0013\t15I\u0001\u0005TK6L'/\u001b8h!\rI\u0003\u0007\u0013\t\u0003i%#QAN\u0002C\u0002]\n!b]3u\u0005>|GN\u00158h+\ta%+F\u0001N!\r\u0011e\nU\u0005\u0003\u001f\u000e\u0013qAQ8pYJsw\rE\u0002*aE\u0003\"\u0001\u000e*\u0005\u000bY\"!\u0019A\u001c"
)
public interface SetInstances extends cats.kernel.instances.SetInstances {
   // $FF: synthetic method
   static GenBool setLattice$(final SetInstances $this) {
      return $this.setLattice();
   }

   default GenBool setLattice() {
      return new SetLattice();
   }

   // $FF: synthetic method
   static Semiring setSemiring$(final SetInstances $this) {
      return $this.setSemiring();
   }

   default Semiring setSemiring() {
      return new SetSemiring();
   }

   // $FF: synthetic method
   static BoolRng setBoolRng$(final SetInstances $this) {
      return $this.setBoolRng();
   }

   default BoolRng setBoolRng() {
      return new SetBoolRng();
   }

   static void $init$(final SetInstances $this) {
   }
}
