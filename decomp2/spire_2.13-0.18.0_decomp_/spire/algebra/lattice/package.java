package spire.algebra.lattice;

import algebra.lattice.BoundedJoinSemilattice;
import algebra.lattice.BoundedLattice;
import algebra.lattice.BoundedMeetSemilattice;
import algebra.lattice.DeMorgan;
import algebra.lattice.Heyting;
import algebra.lattice.JoinSemilattice;
import algebra.lattice.Lattice;
import algebra.lattice.Logic;
import algebra.lattice.MeetSemilattice;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}s!B\u0010!\u0011\u00039c!B\u0015!\u0011\u0003Q\u0003\"B\u0019\u0002\t\u0003\u0011T\u0001B\u001a\u0002\u0001QBq!R\u0001C\u0002\u0013\u0005a\t\u0003\u0004Q\u0003\u0001\u0006IaR\u0003\u0005#\u0006\u0001!\u000bC\u0004X\u0003\t\u0007I\u0011\u0001-\t\rm\u000b\u0001\u0015!\u0003Z\u000b\u0011a\u0016\u0001A/\t\u000f\t\f!\u0019!C\u0001G\"1a-\u0001Q\u0001\n\u0011,AaZ\u0001\u0001Q\"9Q.\u0001b\u0001\n\u0003q\u0007BB9\u0002A\u0003%q.\u0002\u0003s\u0003\u0001\u0019\bb\u0002=\u0002\u0005\u0004%\t!\u001f\u0005\u0007y\u0006\u0001\u000b\u0011\u0002>\u0006\tu\f\u0001A \u0005\n\u0003\u000f\t!\u0019!C\u0001\u0003\u0013A\u0001\"a\u0004\u0002A\u0003%\u00111B\u0003\u0007\u0003#\t\u0001!a\u0005\t\u0013\u0005u\u0011A1A\u0005\u0002\u0005}\u0001\u0002CA\u0013\u0003\u0001\u0006I!!\t\u0006\r\u0005\u001d\u0012\u0001AA\u0015\u0011%\t\u0019$\u0001b\u0001\n\u0003\t)\u0004\u0003\u0005\u0002<\u0005\u0001\u000b\u0011BA\u001c\u000b\u0019\ti$\u0001\u0001\u0002@!I\u0011\u0011J\u0001C\u0002\u0013\u0005\u00111\n\u0005\t\u0003#\n\u0001\u0015!\u0003\u0002N\u00151\u00111K\u0001\u0001\u0003+\nq\u0001]1dW\u0006<WM\u0003\u0002\"E\u00059A.\u0019;uS\u000e,'BA\u0012%\u0003\u001d\tGnZ3ce\u0006T\u0011!J\u0001\u0006gBL'/Z\u0002\u0001!\tA\u0013!D\u0001!\u0005\u001d\u0001\u0018mY6bO\u0016\u001c\"!A\u0016\u0011\u00051zS\"A\u0017\u000b\u00039\nQa]2bY\u0006L!\u0001M\u0017\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\tqEA\u0003M_\u001eL7-\u0006\u00026yA\u0019a'\u000f\u001e\u000e\u0003]R!!\t\u001d\u000b\u0003\rJ!aM\u001c\u0011\u0005mbD\u0002\u0001\u0003\u0006{\r\u0011\rA\u0010\u0002\u0002\u0003F\u0011qH\u0011\t\u0003Y\u0001K!!Q\u0017\u0003\u000f9{G\u000f[5oOB\u0011AfQ\u0005\u0003\t6\u00121!\u00118z\u0003\u0015aunZ5d+\u00059eB\u0001%P\u001d\tIeJ\u0004\u0002K\u001b6\t1J\u0003\u0002MM\u00051AH]8pizJ\u0011aI\u0005\u0003CaJ!!R\u001c\u0002\r1{w-[2!\u0005\u001dAU-\u001f;j]\u001e,\"a\u0015,\u0011\u0007Y\"V+\u0003\u0002RoA\u00111H\u0016\u0003\u0006{\u0019\u0011\rAP\u0001\b\u0011\u0016LH/\u001b8h+\u0005IfB\u0001%[\u0013\t9v'\u0001\u0005IKf$\u0018N\\4!\u0005!!U-T8sO\u0006tWC\u00010b!\r1t\fY\u0005\u00039^\u0002\"aO1\u0005\u000buJ!\u0019\u0001 \u0002\u0011\u0011+Wj\u001c:hC:,\u0012\u0001\u001a\b\u0003\u0011\u0016L!AY\u001c\u0002\u0013\u0011+Wj\u001c:hC:\u0004#a\u0002'biRL7-Z\u000b\u0003S2\u00042A\u000e6l\u0013\t9w\u0007\u0005\u0002<Y\u0012)Q\b\u0004b\u0001}\u00059A*\u0019;uS\u000e,W#A8\u000f\u0005!\u0003\u0018BA78\u0003!a\u0015\r\u001e;jG\u0016\u0004#aD'fKR\u001cV-\\5mCR$\u0018nY3\u0016\u0005Q<\bc\u0001\u001cvm&\u0011!o\u000e\t\u0003w]$Q!P\bC\u0002y\nq\"T3fiN+W.\u001b7biRL7-Z\u000b\u0002u:\u0011\u0001j_\u0005\u0003q^\n\u0001#T3fiN+W.\u001b7biRL7-\u001a\u0011\u0003\u001f){\u0017N\\*f[&d\u0017\r\u001e;jG\u0016,2a`A\u0003!\u00151\u0014\u0011AA\u0002\u0013\tix\u0007E\u0002<\u0003\u000b!Q!\u0010\nC\u0002y\nqBS8j]N+W.\u001b7biRL7-Z\u000b\u0003\u0003\u0017q1\u0001SA\u0007\u0013\r\t9aN\u0001\u0011\u0015>LgnU3nS2\fG\u000f^5dK\u0002\u0012aBQ8v]\u0012,G\rT1ui&\u001cW-\u0006\u0003\u0002\u0016\u0005m\u0001#\u0002\u001c\u0002\u0018\u0005e\u0011bAA\toA\u00191(a\u0007\u0005\u000bu*\"\u0019\u0001 \u0002\u001d\t{WO\u001c3fI2\u000bG\u000f^5dKV\u0011\u0011\u0011\u0005\b\u0004\u0011\u0006\r\u0012bAA\u000fo\u0005y!i\\;oI\u0016$G*\u0019;uS\u000e,\u0007E\u0001\fC_VtG-\u001a3K_&t7+Z7jY\u0006$H/[2f+\u0011\tY#!\r\u0011\u000bY\ni#a\f\n\u0007\u0005\u001dr\u0007E\u0002<\u0003c!Q!\u0010\rC\u0002y\naCQ8v]\u0012,GMS8j]N+W.\u001b7biRL7-Z\u000b\u0003\u0003oq1\u0001SA\u001d\u0013\r\t\u0019dN\u0001\u0018\u0005>,h\u000eZ3e\u0015>LgnU3nS2\fG\u000f^5dK\u0002\u0012aCQ8v]\u0012,G-T3fiN+W.\u001b7biRL7-Z\u000b\u0005\u0003\u0003\n9\u0005E\u00037\u0003\u0007\n)%C\u0002\u0002>]\u00022aOA$\t\u0015i4D1\u0001?\u0003Y\u0011u.\u001e8eK\u0012lU-\u001a;TK6LG.\u0019;uS\u000e,WCAA'\u001d\rA\u0015qJ\u0005\u0004\u0003\u0013:\u0014a\u0006\"pk:$W\rZ'fKR\u001cV-\\5mCR$\u0018nY3!\u00055i\u0015N\\'bq2\u000bG\u000f^5dKV!\u0011qKA/!\u00151\u0014\u0011LA.\u0013\r\t\u0019f\u000e\t\u0004w\u0005uC!B\u001f\u001f\u0005\u0004q\u0004"
)
public final class package {
   public static BoundedMeetSemilattice BoundedMeetSemilattice() {
      return package$.MODULE$.BoundedMeetSemilattice();
   }

   public static BoundedJoinSemilattice BoundedJoinSemilattice() {
      return package$.MODULE$.BoundedJoinSemilattice();
   }

   public static BoundedLattice BoundedLattice() {
      return package$.MODULE$.BoundedLattice();
   }

   public static JoinSemilattice JoinSemilattice() {
      return package$.MODULE$.JoinSemilattice();
   }

   public static MeetSemilattice MeetSemilattice() {
      return package$.MODULE$.MeetSemilattice();
   }

   public static Lattice Lattice() {
      return package$.MODULE$.Lattice();
   }

   public static DeMorgan DeMorgan() {
      return package$.MODULE$.DeMorgan();
   }

   public static Heyting Heyting() {
      return package$.MODULE$.Heyting();
   }

   public static Logic Logic() {
      return package$.MODULE$.Logic();
   }
}
