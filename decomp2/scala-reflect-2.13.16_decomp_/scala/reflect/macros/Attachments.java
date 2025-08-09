package scala.reflect.macros;

import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ClassValueCompat;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d!B\u000b\u0017\u0003\u0003i\u0002\"\u0002\u0012\u0001\t\u0003\u0019C!\u0002\u0014\u0001\u0005\u00039\u0003\"\u0002\u0018\u0001\r\u0003y\u0003\"\u0002\u001a\u0001\r\u0003\u0019\u0004\"\u0002\u001e\u0001\r\u0003Y\u0004\"B$\u0001\t\u0013A\u0005\"\u00020\u0001\t\u0003y\u0006\"B5\u0001\t\u0003Q\u0007\"B9\u0001\t\u0003\u0011\bBB@\u0001\t\u0003\t\t\u0001C\u0004\u0002\u0016\u0001!)!a\u0006\t\u000f\u0005\u001d\u0002\u0001\"\u0002\u0002*!9\u0011\u0011\b\u0001\u0005\u0006\u0005m\u0002bBA!\u0001\u0019\u0005\u00111\t\u0005\b\u0003\u000b\u0002A\u0011AA$\u000f\u001d\tyE\u0006E\u0005\u0003#2a!\u0006\f\t\n\u0005M\u0003B\u0002\u0012\u0012\t\u0003\t)\u0006C\u0005\u0002XE\u0011\r\u0011\"\u0003\u0002Z!A\u0011qM\t!\u0002\u0013\tYFA\u0006BiR\f7\r[7f]R\u001c(BA\f\u0019\u0003\u0019i\u0017m\u0019:pg*\u0011\u0011DG\u0001\be\u00164G.Z2u\u0015\u0005Y\u0012!B:dC2\f7\u0001A\n\u0003\u0001y\u0001\"a\b\u0011\u000e\u0003iI!!\t\u000e\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\tA\u0005\u0005\u0002&\u00015\taCA\u0002Q_N\f\"\u0001K\u0016\u0011\u0005}I\u0013B\u0001\u0016\u001b\u0005\u0011qU\u000f\u001c7\u0011\u0005}a\u0013BA\u0017\u001b\u0005\r\te._\u0001\u0004a>\u001cX#\u0001\u0019\u0011\u0005E\u0012Q\"\u0001\u0001\u0002\u000f]LG\u000f\u001b)pgR\u0011A\u0007\u000f\n\u0003k\u00112AA\u000e\u0001\u0001i\taAH]3gS:,W.\u001a8u}\u0015!a%\u000e\u00111\u0011\u0015ID\u00011\u00011\u0003\u0019qWm\u001e)pg\u0006\u0019\u0011\r\u001c7\u0016\u0003q\u00022!\u0010#,\u001d\tq$\t\u0005\u0002@55\t\u0001I\u0003\u0002B9\u00051AH]8pizJ!a\u0011\u000e\u0002\rA\u0013X\rZ3g\u0013\t)eIA\u0002TKRT!a\u0011\u000e\u0002\u00155\fGo\u00195fgR\u000bw-\u0006\u0002J1R\u0011!\n\u0015\t\u0005?-[S*\u0003\u0002M5\tIa)\u001e8di&|g.\r\t\u0003?9K!a\u0014\u000e\u0003\u000f\t{w\u000e\\3b]\"9\u0011KBA\u0001\u0002\b\u0011\u0016AC3wS\u0012,gnY3%cA\u00191\u000b\u0016,\u000e\u0003aI!!\u0016\r\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"a\u0016-\r\u0001\u0011)\u0011L\u0002b\u00015\n\tA+\u0005\u0002\\WA\u0011q\u0004X\u0005\u0003;j\u0011qAT8uQ&tw-A\u0002hKR,\"\u0001Y3\u0015\u0005\u00054\u0007cA\u0010cI&\u00111M\u0007\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005]+G!B-\b\u0005\u0004Q\u0006bB4\b\u0003\u0003\u0005\u001d\u0001[\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004cA*UI\u0006A1m\u001c8uC&t7/\u0006\u0002laR\u0011Q\n\u001c\u0005\b[\"\t\t\u0011q\u0001o\u0003))g/\u001b3f]\u000e,Ge\r\t\u0004'R{\u0007CA,q\t\u0015I\u0006B1\u0001[\u0003\u0019)\b\u000fZ1uKV\u00111\u000f \u000b\u0003iv$\"!\u001e=\u0013\u0005Y$c\u0001\u0002\u001c\u0001\u0001U,AA\n<!a!9\u00110CA\u0001\u0002\bQ\u0018AC3wS\u0012,gnY3%iA\u00191\u000bV>\u0011\u0005]cH!B-\n\u0005\u0004Q\u0006\"\u0002@\n\u0001\u0004Y\u0018AC1ui\u0006\u001c\u0007.\\3oi\u00061!/Z7pm\u0016,B!a\u0001\u0002\u0014Q!\u0011QAA\u0006%\r\t9\u0001\n\u0004\u0006m\u0001\u0001\u0011QA\u0003\u0006M\u0005\u001d\u0001\u0005\r\u0005\n\u0003\u001bQ\u0011\u0011!a\u0002\u0003\u001f\t!\"\u001a<jI\u0016t7-\u001a\u00136!\u0011\u0019F+!\u0005\u0011\u0007]\u000b\u0019\u0002B\u0003Z\u0015\t\u0007!,A\u0007sK6|g/Z#mK6,g\u000e^\u000b\u0005\u00033\t)\u0003\u0006\u0003\u0002\u001c\u0005\u0005\"cAA\u000fI\u0019)a\u0007\u0001\u0001\u0002\u001c\u0015)a%!\b!a!1ap\u0003a\u0001\u0003G\u00012aVA\u0013\t\u0015I6B1\u0001[\u0003)\tG\rZ#mK6,g\u000e^\u000b\u0005\u0003W\t9\u0004\u0006\u0003\u0002.\u0005M\"cAA\u0018I\u0019)a\u0007\u0001\u0001\u0002.\u0015)a%a\f!a!1a\u0010\u0004a\u0001\u0003k\u00012aVA\u001c\t\u0015IFB1\u0001[\u0003=\u0019wN\u001c;bS:\u001cX\t\\3nK:$HcA'\u0002>!1\u0011qH\u0007A\u0002-\nq!\u001a7f[\u0016tG/A\u0004jg\u0016k\u0007\u000f^=\u0016\u00035\u000b\u0001c\u00197p]\u0016\fE\u000f^1dQ6,g\u000e^:\u0016\u0005\u0005%#cAA&I\u0019)a\u0007\u0001\u0001\u0002J\u0015)a%a\u0013!a\u0005Y\u0011\t\u001e;bG\"lWM\u001c;t!\t)\u0013c\u0005\u0002\u0012=Q\u0011\u0011\u0011K\u0001\u0010[\u0006$8\r[3t)\u0006<7)Y2iKV\u0011\u00111\f\t\u0006\u0003;\n\u0019GS\u0007\u0003\u0003?R1!!\u0019\u001b\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u001a\u0002`\t\u00012\t\\1tgZ\u000bG.^3D_6\u0004\u0018\r^\u0001\u0011[\u0006$8\r[3t)\u0006<7)Y2iK\u0002\u0002"
)
public abstract class Attachments {
   public abstract Object pos();

   public abstract Attachments withPos(final Object newPos);

   public abstract Set all();

   private Function1 matchesTag(final ClassTag evidence$1) {
      ClassValueCompat var10000 = Attachments$.MODULE$.scala$reflect$macros$Attachments$$matchesTagCache();
      scala.reflect.package var10001 = .MODULE$;
      return (Function1)var10000.get(evidence$1.runtimeClass());
   }

   public Option get(final ClassTag evidence$2) {
      Iterator it = this.all().iterator();
      Function1 matchesTagFn = this.matchesTag(evidence$2);

      while(it.hasNext()) {
         Object datum = it.next();
         if (BoxesRunTime.unboxToBoolean(matchesTagFn.apply(datum))) {
            return new Some(datum);
         }
      }

      return scala.None..MODULE$;
   }

   public boolean contains(final ClassTag evidence$3) {
      return !this.isEmpty() && this.all().exists(this.matchesTag(evidence$3));
   }

   public Attachments update(final Object attachment, final ClassTag evidence$4) {
      NonemptyAttachments var10000 = new NonemptyAttachments;
      Object var10002 = this.pos();
      Set var10003 = this.remove(evidence$4).all();
      if (var10003 == null) {
         throw null;
      } else {
         var10000.<init>(var10002, (Set)var10003.incl(attachment));
         return var10000;
      }
   }

   public Attachments remove(final ClassTag evidence$5) {
      if (!this.all().exists(this.matchesTag(evidence$5))) {
         return this;
      } else {
         Set newAll = (Set)this.all().filterNot(this.matchesTag(evidence$5));
         if (newAll.isEmpty()) {
            return (Attachments)this.pos();
         } else {
            return (Attachments)(newAll.size() == 1 ? new SingleAttachment(this.pos(), newAll.head()) : new NonemptyAttachments(this.pos(), newAll));
         }
      }
   }

   public final Attachments removeElement(final Object attachment) {
      Set var10000 = this.all();
      if (var10000 == null) {
         throw null;
      } else {
         Set newAll = (Set)var10000.excl(attachment);
         if (newAll == this.all()) {
            return this;
         } else {
            return (Attachments)(newAll.isEmpty() ? (Attachments)this.pos() : new NonemptyAttachments(this.pos(), newAll));
         }
      }
   }

   public final Attachments addElement(final Object attachment) {
      Set var10000 = this.all();
      if (var10000 == null) {
         throw null;
      } else {
         Set newAll = (Set)var10000.incl(attachment);
         return (Attachments)(newAll == this.all() ? this : new NonemptyAttachments(this.pos(), newAll));
      }
   }

   public final boolean containsElement(final Object element) {
      return this.all().contains(element);
   }

   public abstract boolean isEmpty();

   public Attachments cloneAttachments() {
      return this;
   }
}
