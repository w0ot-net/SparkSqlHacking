package scala.reflect.io;

import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005u4A\u0001G\r\u0001A!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u00053\u0001\t\u0005\t\u0015!\u0003(\u0011!\u0019\u0004A!A!\u0002\u0013!\u0004\"B\u001d\u0001\t\u0003Q\u0004\"B\u001f\u0001\t\u00031\u0003\"\u0002 \u0001\t\u0003y\u0004\"\u0002!\u0001\t\u0003y\u0004\"B!\u0001\t\u0003\u0011\u0005\"\u0002$\u0001\t\u0003\u0012\u0005bB$\u0001\u0005\u0004%\t\u0001\u0013\u0005\u0007\u0019\u0002\u0001\u000b\u0011B%\t\u000b5\u0003A\u0011\t(\t\u000bI\u0003A\u0011I*\t\u000b]\u0003A\u0011I*\t\u000ba\u0003A\u0011A-\t\u000bu\u0003A\u0011A-\t\u000by\u0003A\u0011A0\t\r\r\u0004\u0001\u0015!\u0003e\u0011\u0015a\u0007\u0001\"\u0001n\u0011\u0015\u0011\b\u0001\"\u0011t\u0011\u00151\b\u0001\"\u0011x\u0011\u0015I\b\u0001\"\u0011{\u0011\u0015a\b\u0001\"\u0001Z\u0005A1\u0016N\u001d;vC2$\u0015N]3di>\u0014\u0018P\u0003\u0002\u001b7\u0005\u0011\u0011n\u001c\u0006\u00039u\tqA]3gY\u0016\u001cGOC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001A\u0011\u0011\u0005\t\u001aS\"A\r\n\u0005\u0011J\"\u0001D!cgR\u0014\u0018m\u0019;GS2,\u0017\u0001\u00028b[\u0016,\u0012a\n\t\u0003Q=r!!K\u0017\u0011\u0005)jR\"A\u0016\u000b\u00051z\u0012A\u0002\u001fs_>$h(\u0003\u0002/;\u00051\u0001K]3eK\u001aL!\u0001M\u0019\u0003\rM#(/\u001b8h\u0015\tqS$A\u0003oC6,\u0007%\u0001\bnCf\u0014WmQ8oi\u0006Lg.\u001a:\u0011\u0007U2\u0004(D\u0001\u001e\u0013\t9TD\u0001\u0004PaRLwN\u001c\t\u0003E\u0001\ta\u0001P5oSRtDc\u0001\u001d<y!)Q\u0005\u0002a\u0001O!)1\u0007\u0002a\u0001i\u0005!\u0001/\u0019;i\u0003!\t'm]8mkR,W#\u0001\u001d\u0002\u0013\r|g\u000e^1j]\u0016\u0014\u0018aC5t\t&\u0014Xm\u0019;pef,\u0012a\u0011\t\u0003k\u0011K!!R\u000f\u0003\u000f\t{w\u000e\\3b]\u0006I\u0011n\u001d,jeR,\u0018\r\\\u0001\rY\u0006\u001cH/T8eS\u001aLW\rZ\u000b\u0002\u0013B\u0011QGS\u0005\u0003\u0017v\u0011A\u0001T8oO\u0006iA.Y:u\u001b>$\u0017NZ5fI\u0002\nAAZ5mKV\tq\n\u0005\u00026!&\u0011\u0011+\b\u0002\u0005\u001dVdG.A\u0003j]B,H/F\u0001U!\t)T+\u0003\u0002W;\t9aj\u001c;iS:<\u0017AB8viB,H/\u0001\u0004de\u0016\fG/\u001a\u000b\u00025B\u0011QgW\u0005\u00039v\u0011A!\u00168ji\u00061A-\u001a7fi\u0016\f1\u0003\\8pWV\u0004h*Y7f+:\u001c\u0007.Z2lK\u0012$2!\t1b\u0011\u0015)\u0013\u00031\u0001(\u0011\u0015\u0011\u0017\u00031\u0001D\u0003%!\u0017N]3di>\u0014\u00180A\u0003gS2,7\u000f\u0005\u0003fU\u001e\nS\"\u00014\u000b\u0005\u001dD\u0017aB7vi\u0006\u0014G.\u001a\u0006\u0003Sv\t!bY8mY\u0016\u001cG/[8o\u0013\tYgMA\u0002NCB\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0002]B\u0019q\u000e]\u0011\u000e\u0003!L!!\u001d5\u0003\u0011%#XM]1u_J\f!\u0002\\8pWV\u0004h*Y7f)\r\tC/\u001e\u0005\u0006KQ\u0001\ra\n\u0005\u0006ER\u0001\raQ\u0001\nM&dWMT1nK\u0012$\"!\t=\t\u000b\u0015*\u0002\u0019A\u0014\u0002#M,(\rZ5sK\u000e$xN]=OC6,G\r\u0006\u0002\"w\")QE\u0006a\u0001O\u0005)1\r\\3be\u0002"
)
public class VirtualDirectory extends AbstractFile {
   private final String name;
   private final Option maybeContainer;
   private final long lastModified;
   private final Map files;

   public String name() {
      return this.name;
   }

   public String path() {
      Option var1 = this.maybeContainer;
      if (.MODULE$.equals(var1)) {
         return this.name();
      } else if (var1 instanceof Some) {
         VirtualDirectory parent = (VirtualDirectory)((Some)var1).value();
         return (new StringBuilder(11)).append(parent.path()).append('/').append(this.name()).toString();
      } else {
         throw new MatchError(var1);
      }
   }

   public VirtualDirectory absolute() {
      return this;
   }

   public VirtualDirectory container() {
      return (VirtualDirectory)this.maybeContainer.get();
   }

   public boolean isDirectory() {
      return true;
   }

   public boolean isVirtual() {
      return true;
   }

   public long lastModified() {
      return this.lastModified;
   }

   public Null file() {
      return null;
   }

   public Nothing input() {
      throw new IllegalStateException("directories cannot be read");
   }

   public Nothing output() {
      throw new IllegalStateException("directories cannot be written");
   }

   public void create() {
      throw this.unsupported();
   }

   public void delete() {
      throw this.unsupported();
   }

   public AbstractFile lookupNameUnchecked(final String name, final boolean directory) {
      throw this.unsupported();
   }

   public Iterator iterator() {
      return this.files.values().toList().iterator();
   }

   public AbstractFile lookupName(final String name, final boolean directory) {
      Option var10000 = this.files.get(name);
      if (var10000 == null) {
         throw null;
      } else {
         label20: {
            Option filter_this = var10000;
            if (!filter_this.isEmpty()) {
               AbstractFile var6 = (AbstractFile)filter_this.get();
               if (!$anonfun$lookupName$1(directory, var6)) {
                  var8 = .MODULE$;
                  break label20;
               }
            }

            var8 = filter_this;
         }

         Object var7 = null;
         scala..eq.colon.eq orNull_ev = scala..less.colon.less..MODULE$.refl();
         Option orNull_this = (Option)var8;
         return (AbstractFile)(orNull_this.isEmpty() ? ((scala..less.colon.less)orNull_ev).apply((Object)null) : orNull_this.get());
      }
   }

   public AbstractFile fileNamed(final String name) {
      Option var10000 = scala.Option..MODULE$.apply(this.lookupName(name, false));
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         return (AbstractFile)(getOrElse_this.isEmpty() ? $anonfun$fileNamed$1(this, name) : getOrElse_this.get());
      }
   }

   public AbstractFile subdirectoryNamed(final String name) {
      Option var10000 = scala.Option..MODULE$.apply(this.lookupName(name, true));
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         return (AbstractFile)(getOrElse_this.isEmpty() ? $anonfun$subdirectoryNamed$1(this, name) : getOrElse_this.get());
      }
   }

   public void clear() {
      this.files.clear();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$lookupName$1(final boolean directory$1, final AbstractFile x$1) {
      return x$1.isDirectory() == directory$1;
   }

   // $FF: synthetic method
   public static final VirtualFile $anonfun$fileNamed$1(final VirtualDirectory $this, final String name$1) {
      VirtualFile newFile = new VirtualFile(name$1, (new StringBuilder(11)).append($this.path()).append('/').append(name$1).toString());
      $this.files.update(name$1, newFile);
      return newFile;
   }

   // $FF: synthetic method
   public static final VirtualDirectory $anonfun$subdirectoryNamed$1(final VirtualDirectory $this, final String name$2) {
      VirtualDirectory dir = new VirtualDirectory(name$2, new Some($this));
      $this.files.update(name$2, dir);
      return dir;
   }

   public VirtualDirectory(final String name, final Option maybeContainer) {
      this.name = name;
      this.maybeContainer = maybeContainer;
      this.lastModified = System.currentTimeMillis();
      this.files = (Map)scala.collection.mutable.Map..MODULE$.empty();
   }

   // $FF: synthetic method
   public static final Object $anonfun$lookupName$1$adapted(final boolean directory$1, final AbstractFile x$1) {
      return BoxesRunTime.boxToBoolean($anonfun$lookupName$1(directory$1, x$1));
   }
}
