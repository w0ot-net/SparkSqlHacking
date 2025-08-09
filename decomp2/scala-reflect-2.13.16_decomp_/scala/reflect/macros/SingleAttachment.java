package scala.reflect.macros;

import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ma\u0001\u0002\t\u0012\raA\u0001B\b\u0001\u0003\u0006\u0004%\te\b\u0005\tY\u0001\u0011\t\u0011)A\u0005A!AQ\u0006\u0001BC\u0002\u0013\u0005a\u0006\u0003\u00050\u0001\t\u0005\t\u0015!\u0003*\u0011\u0015\u0001\u0004\u0001\"\u00012\u000b\u0011)\u0004\u0001\u0001\u0011\t\u000bY\u0002A\u0011A\u001c\t\u000bu\u0002A\u0011\t \t\u000b\t\u0003A\u0011I\"\t\u000b!\u0003A\u0011I%\t\u000bI\u0003A\u0011I*\t\u000b\t\u0004A\u0011I2\t\u000b1\u0004A\u0011I7\t\u000be\u0004A\u0011\t>\t\u000f\u0005\u001d\u0001\u0001\"\u0011\u0002\n\t\u00012+\u001b8hY\u0016\fE\u000f^1dQ6,g\u000e\u001e\u0006\u0003%M\ta!\\1de>\u001c(B\u0001\u000b\u0016\u0003\u001d\u0011XM\u001a7fGRT\u0011AF\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tI\"e\u0005\u0002\u00015A\u00111\u0004H\u0007\u0002#%\u0011Q$\u0005\u0002\f\u0003R$\u0018m\u00195nK:$8/A\u0002q_N,\u0012\u0001\t\t\u0003C\tb\u0001\u0001B\u0003$\u0001\t\u0007AEA\u0001Q#\t)\u0013\u0006\u0005\u0002'O5\tQ#\u0003\u0002)+\t!a*\u001e7m!\t1#&\u0003\u0002,+\t\u0019\u0011I\\=\u0002\tA|7\u000fI\u0001\u0004CR$X#A\u0015\u0002\t\u0005$H\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007I\u001aD\u0007E\u0002\u001c\u0001\u0001BQAH\u0003A\u0002\u0001BQ!L\u0003A\u0002%\u00121\u0001U8t\u0003\u001d9\u0018\u000e\u001e5Q_N$\"\u0001O\u001e\u0011\u0007m\u0001\u0011\b\u0005\u0002;\r5\t\u0001\u0001C\u0003=\u000f\u0001\u0007\u0011(\u0001\u0004oK^\u0004vn]\u0001\bSN,U\u000e\u001d;z+\u0005y\u0004C\u0001\u0014A\u0013\t\tUCA\u0004C_>dW-\u00198\u0002!\rdwN\\3BiR\f7\r[7f]R\u001cX#\u0001#\u0013\u0005\u0015Sb\u0001\u0002$\u0001\u0001\u0011\u0013A\u0002\u0010:fM&tW-\\3oiz*A!N#!A\u0005\u0019\u0011\r\u001c7\u0016\u0003)\u00032a\u0013)*\u001b\u0005a%BA'O\u0003%IW.\\;uC\ndWM\u0003\u0002P+\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Ec%aA*fi\u0006A1m\u001c8uC&t7/\u0006\u0002U9R\u0011q(\u0016\u0005\u0006-.\u0001\u001daV\u0001\u0003iR\u00042\u0001W-\\\u001b\u0005\u0019\u0012B\u0001.\u0014\u0005!\u0019E.Y:t)\u0006<\u0007CA\u0011]\t\u0015i6B1\u0001_\u0005\u0005!\u0016CA0*!\t1\u0003-\u0003\u0002b+\t9aj\u001c;iS:<\u0017aA4fiV\u0011A-\u001b\u000b\u0003K*\u00042A\n4i\u0013\t9WC\u0001\u0004PaRLwN\u001c\t\u0003C%$Q!\u0018\u0007C\u0002yCQA\u0016\u0007A\u0004-\u00042\u0001W-i\u0003\u0019)\b\u000fZ1uKV\u0011aN\u001e\u000b\u0003_^$\"\u0001]:\u0013\u0005ETb\u0001\u0002$\u0001\u0001A,A!N9\u0001s!)a+\u0004a\u0002iB\u0019\u0001,W;\u0011\u0005\u00052H!B/\u000e\u0005\u0004q\u0006\"\u0002=\u000e\u0001\u0004)\u0018A\u00028fo\u0006#H/\u0001\u0004sK6|g/Z\u000b\u0004w\u0006\u0015AC\u0001?\u0000%\ti(D\u0002\u0003G\u0001\u0001aX\u0001B\u001b~\u0001eBaA\u0016\bA\u0004\u0005\u0005\u0001\u0003\u0002-Z\u0003\u0007\u00012!IA\u0003\t\u0015ifB1\u0001_\u0003!!xn\u0015;sS:<GCAA\u0006!\u0011\ti!a\u0006\u000e\u0005\u0005=!\u0002BA\t\u0003'\tA\u0001\\1oO*\u0011\u0011QC\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u001a\u0005=!AB*ue&tw\r"
)
public final class SingleAttachment extends Attachments {
   private final Object pos;
   private final Object att;

   public Object pos() {
      return this.pos;
   }

   public Object att() {
      return this.att;
   }

   public SingleAttachment withPos(final Object newPos) {
      return new SingleAttachment(newPos, this.att());
   }

   public boolean isEmpty() {
      return false;
   }

   public Attachments cloneAttachments() {
      return new SingleAttachment(this.pos(), this.att());
   }

   public Set all() {
      if (.MODULE$.Set() == null) {
         throw null;
      } else {
         Set.EmptySet var10000 = scala.collection.immutable.Set.EmptySet..MODULE$;
         Object $plus_elem = this.att();
         return new Set.Set1($plus_elem);
      }
   }

   public boolean contains(final ClassTag tt) {
      return tt.runtimeClass().isInstance(this.att());
   }

   public Option get(final ClassTag tt) {
      return (Option)(this.contains(tt) ? new Some(this.att()) : scala.None..MODULE$);
   }

   public Attachments update(final Object newAtt, final ClassTag tt) {
      if (this.contains(tt)) {
         return new SingleAttachment(this.pos(), newAtt);
      } else {
         NonemptyAttachments var10000 = new NonemptyAttachments;
         Object var10002 = this.pos();
         if (.MODULE$.Set() == null) {
            throw null;
         } else {
            Set.EmptySet var10003 = scala.collection.immutable.Set.EmptySet..MODULE$;
            Object $plus_elem = this.att();
            Set.Set1 var5 = new Set.Set1($plus_elem);
            $plus_elem = null;
            var10000.<init>(var10002, var5.incl(newAtt));
            return var10000;
         }
      }
   }

   public Attachments remove(final ClassTag tt) {
      return (Attachments)(this.contains(tt) ? (Attachments)this.pos() : this);
   }

   public String toString() {
      return (new StringBuilder(22)).append("SingleAttachment at ").append(this.pos()).append(": ").append(this.att()).toString();
   }

   public SingleAttachment(final Object pos, final Object att) {
      this.pos = pos;
      this.att = att;
   }
}
