package algebra.ring;

import cats.kernel.CommutativeSemigroup;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003A\u0001\u0011\u0005\u0011\tC\u0003F\u0001\u0011\u0005ciB\u0003P\u0015!\u0005\u0001KB\u0003\n\u0015!\u0005\u0011\u000bC\u0003b\t\u0011\u0005!\rC\u0003d\t\u0011\u0015A\rC\u0003F\t\u0011\u0015q\u000eC\u0004x\t\u0005\u0005I\u0011\u0002=\u00039\u0005#G-\u001b;jm\u0016\u001cu.\\7vi\u0006$\u0018N^3TK6LwM]8va*\u00111\u0002D\u0001\u0005e&twMC\u0001\u000e\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u0011;M\u0019\u0001!E\f\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\u0007\u0005s\u0017\u0010E\u0002\u00193mi\u0011AC\u0005\u00035)\u0011\u0011#\u00113eSRLg/Z*f[&<'o\\;q!\taR\u0004\u0004\u0001\u0005\u0013y\u0001\u0001\u0015!A\u0001\u0006\u0004y\"!A!\u0012\u0005\u0001\n\u0002C\u0001\n\"\u0013\t\u00113CA\u0004O_RD\u0017N\\4)\ru!s%\r\u001c<!\t\u0011R%\u0003\u0002''\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019\u0003&K\u0016+\u001d\t\u0011\u0012&\u0003\u0002+'\u0005\u0019\u0011J\u001c;2\t\u0011b\u0003\u0007\u0006\b\u0003[Aj\u0011A\f\u0006\u0003_9\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000b2\u000b\r\u00124'\u000e\u001b\u000f\u0005I\u0019\u0014B\u0001\u001b\u0014\u0003\u0011auN\\42\t\u0011b\u0003\u0007F\u0019\u0006G]B$(\u000f\b\u0003%aJ!!O\n\u0002\u000b\u0019cw.\u0019;2\t\u0011b\u0003\u0007F\u0019\u0006GqjtH\u0010\b\u0003%uJ!AP\n\u0002\r\u0011{WO\u00197fc\u0011!C\u0006\r\u000b\u0002\r\u0011Jg.\u001b;%)\u0005\u0011\u0005C\u0001\nD\u0013\t!5C\u0001\u0003V]&$\u0018\u0001C1eI&$\u0018N^3\u0016\u0003\u001d\u00032\u0001\u0013'\u001c\u001d\tI%*D\u0001\r\u0013\tYE\"A\u0004qC\u000e\\\u0017mZ3\n\u00055s%\u0001F\"p[6,H/\u0019;jm\u0016\u001cV-\\5he>,\bO\u0003\u0002L\u0019\u0005a\u0012\t\u001a3ji&4XmQ8n[V$\u0018\r^5wKN+W.[4s_V\u0004\bC\u0001\r\u0005'\u0011!!+V-\u0011\u0005I\u0019\u0016B\u0001+\u0014\u0005\u0019\te.\u001f*fMB\u0019\u0001D\u0016-\n\u0005]S!AG!eI&$\u0018N^3TK6LwM]8va\u001a+hn\u0019;j_:\u001c\bC\u0001\r\u0001!\tQv,D\u0001\\\u0015\taV,\u0001\u0002j_*\ta,\u0001\u0003kCZ\f\u0017B\u00011\\\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t\u0001+A\u0003baBd\u00170\u0006\u0002fQR\u0011a-\u001b\t\u00041\u00019\u0007C\u0001\u000fi\t\u0015qbA1\u0001 \u0011\u0015Qg\u0001q\u0001g\u0003\t)g\u000f\u000b\u0002\u0007YB\u0011!#\\\u0005\u0003]N\u0011a!\u001b8mS:,WC\u00019t)\t\tH\u000fE\u0002I\u0019J\u0004\"\u0001H:\u0005\u000by9!\u0019A\u0010\t\u000b)<\u00019A;\u0011\u0007a\u0001!\u000f\u000b\u0002\bY\u0006aqO]5uKJ+\u0007\u000f\\1dKR\t\u0011\u0010\u0005\u0002{{6\t1P\u0003\u0002};\u0006!A.\u00198h\u0013\tq8P\u0001\u0004PE*,7\r\u001e"
)
public interface AdditiveCommutativeSemigroup extends AdditiveSemigroup {
   static AdditiveCommutativeSemigroup apply(final AdditiveCommutativeSemigroup ev) {
      return AdditiveCommutativeSemigroup$.MODULE$.apply(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return AdditiveCommutativeSemigroup$.MODULE$.isAdditiveCommutative(ev);
   }

   // $FF: synthetic method
   static CommutativeSemigroup additive$(final AdditiveCommutativeSemigroup $this) {
      return $this.additive();
   }

   default CommutativeSemigroup additive() {
      return new CommutativeSemigroup() {
         // $FF: synthetic field
         private final AdditiveCommutativeSemigroup $outer;

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Object combine(final Object x, final Object y) {
            return this.$outer.plus(x, y);
         }

         public Option combineAllOption(final IterableOnce as) {
            return this.$outer.trySum(as);
         }

         public {
            if (AdditiveCommutativeSemigroup.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveCommutativeSemigroup.this;
               Semigroup.$init$(this);
               CommutativeSemigroup.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static CommutativeSemigroup additive$mcD$sp$(final AdditiveCommutativeSemigroup $this) {
      return $this.additive$mcD$sp();
   }

   default CommutativeSemigroup additive$mcD$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static CommutativeSemigroup additive$mcF$sp$(final AdditiveCommutativeSemigroup $this) {
      return $this.additive$mcF$sp();
   }

   default CommutativeSemigroup additive$mcF$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static CommutativeSemigroup additive$mcI$sp$(final AdditiveCommutativeSemigroup $this) {
      return $this.additive$mcI$sp();
   }

   default CommutativeSemigroup additive$mcI$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static CommutativeSemigroup additive$mcJ$sp$(final AdditiveCommutativeSemigroup $this) {
      return $this.additive$mcJ$sp();
   }

   default CommutativeSemigroup additive$mcJ$sp() {
      return this.additive();
   }

   static void $init$(final AdditiveCommutativeSemigroup $this) {
   }
}
