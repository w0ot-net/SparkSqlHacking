package scala.xml.dtd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.PartialFunction.;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.xml.Utility$;
import scala.xml.dtd.impl.Base;
import scala.xml.dtd.impl.WordExp;

public final class ContentModel$ extends WordExp {
   public static final ContentModel$ MODULE$ = new ContentModel$();

   public boolean isMixed(final ContentModel cm) {
      return .MODULE$.cond(cm, new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final ContentModel x1, final Function1 default) {
            return x1 instanceof MIXED ? BoxesRunTime.boxToBoolean(true) : default.apply(x1);
         }

         public final boolean isDefinedAt(final ContentModel x1) {
            return x1 instanceof MIXED;
         }
      });
   }

   public boolean containsText(final ContentModel cm) {
      boolean var10000;
      label18: {
         PCDATA$ var2 = PCDATA$.MODULE$;
         if (cm == null) {
            if (var2 == null) {
               break label18;
            }
         } else if (cm.equals(var2)) {
            break label18;
         }

         if (!this.isMixed(cm)) {
            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Set getLabels(final Base.RegExp r) {
      return this.traverse$1(r);
   }

   public String buildString(final Base.RegExp r) {
      return Utility$.MODULE$.sbToString((x$1) -> {
         $anonfun$buildString$1(r, x$1);
         return BoxedUnit.UNIT;
      });
   }

   private void buildString(final Seq rs, final StringBuilder sb, final char sep) {
      this.buildString((Base.RegExp)rs.head(), sb);
      ((IterableOnceOps)rs.tail()).foreach((z) -> {
         sb.append(sep);
         return MODULE$.buildString(z, sb);
      });
   }

   public StringBuilder buildString(final ContentModel c, final StringBuilder sb) {
      if (ANY$.MODULE$.equals(c)) {
         return sb.append("ANY");
      } else if (EMPTY$.MODULE$.equals(c)) {
         return sb.append("EMPTY");
      } else if (PCDATA$.MODULE$.equals(c)) {
         return sb.append("(#PCDATA)");
      } else if (c instanceof ELEMENTS ? true : c instanceof MIXED) {
         return c.buildString(sb);
      } else {
         throw new MatchError(c);
      }
   }

   public StringBuilder buildString(final Base.RegExp r, final StringBuilder sb) {
      if (this.Eps().equals(r)) {
         return sb;
      } else {
         if (r instanceof Base.Sequ) {
            Base.Sequ var5 = (Base.Sequ)r;
            Some var6 = this.Sequ().unapplySeq(var5);
            if (!var6.isEmpty()) {
               scala.collection.immutable.Seq rs = (scala.collection.immutable.Seq)var6.get();
               sb.append('(');
               this.buildString(rs, sb, ',');
               return sb.append(')');
            }
         }

         if (r instanceof Base.Alt) {
            Base.Alt var8 = (Base.Alt)r;
            Some var9 = this.Alt().unapplySeq(var8);
            if (!var9.isEmpty()) {
               scala.collection.immutable.Seq rs = (scala.collection.immutable.Seq)var9.get();
               sb.append('(');
               this.buildString(rs, sb, '|');
               return sb.append(')');
            }
         }

         if (r instanceof Base.Star) {
            Base.Star var11 = (Base.Star)r;
            Base.RegExp r = var11.r();
            if (r != null) {
               sb.append('(');
               this.buildString(r, sb);
               return sb.append(")*");
            }
         }

         if (r instanceof WordExp.Letter) {
            WordExp.Letter var14 = (WordExp.Letter)r;
            ContentModel.ElemName var15 = (ContentModel.ElemName)var14.a();
            if (var15 != null) {
               String name = var15.name();
               return sb.append(name);
            }
         }

         throw new MatchError(r);
      }
   }

   private final Set traverse$1(final Base.RegExp r) {
      while(true) {
         if (r instanceof WordExp.Letter) {
            WordExp.Letter var5 = (WordExp.Letter)r;
            ContentModel.ElemName var6 = (ContentModel.ElemName)var5.a();
            if (var6 != null) {
               String name = var6.name();
               return (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name})));
            }
         }

         if (!(r instanceof Base.Star)) {
            if (r instanceof Base.Sequ) {
               Base.Sequ var10 = (Base.Sequ)r;
               Some var11 = this.Sequ().unapplySeq(var10);
               if (!var11.isEmpty()) {
                  scala.collection.immutable.Seq xs = (scala.collection.immutable.Seq)var11.get();
                  return (Set)scala.Predef..MODULE$.Set().apply((scala.collection.immutable.Seq)xs.flatMap((rx) -> this.traverse$1(rx)));
               }
            }

            if (r instanceof Base.Alt) {
               Base.Alt var13 = (Base.Alt)r;
               Some var14 = this.Alt().unapplySeq(var13);
               if (!var14.isEmpty()) {
                  scala.collection.immutable.Seq xs = (scala.collection.immutable.Seq)var14.get();
                  return (Set)scala.Predef..MODULE$.Set().apply((scala.collection.immutable.Seq)xs.flatMap((rx) -> this.traverse$1(rx)));
               }
            }

            throw new MatchError(r);
         }

         Base.Star var8 = (Base.Star)r;
         Base.RegExp x = var8.r();
         r = x;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$buildString$1(final Base.RegExp r$1, final StringBuilder x$1) {
      MODULE$.buildString(r$1, x$1);
   }

   private ContentModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
