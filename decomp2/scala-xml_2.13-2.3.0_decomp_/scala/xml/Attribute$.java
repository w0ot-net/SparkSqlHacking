package scala.xml;

import java.io.Serializable;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.Tuple4;
import scala.None.;
import scala.collection.Seq;
import scala.runtime.ModuleSerializationProxy;

public final class Attribute$ implements Serializable {
   public static final Attribute$ MODULE$ = new Attribute$();

   public Option unapply(final Attribute x) {
      if (x instanceof PrefixedAttribute) {
         PrefixedAttribute var4 = (PrefixedAttribute)x;
         Some var5 = PrefixedAttribute$.MODULE$.unapply(var4);
         if (!var5.isEmpty()) {
            String key = (String)((Tuple4)var5.get())._2();
            Seq value = (Seq)((Tuple4)var5.get())._3();
            MetaData next = (MetaData)((Tuple4)var5.get())._4();
            return new Some(new Tuple3(key, value, next));
         }
      }

      if (x instanceof UnprefixedAttribute) {
         UnprefixedAttribute var9 = (UnprefixedAttribute)x;
         Some var10 = UnprefixedAttribute$.MODULE$.unapply(var9);
         if (!var10.isEmpty()) {
            String key = (String)((Tuple3)var10.get())._1();
            Seq value = (Seq)((Tuple3)var10.get())._2();
            MetaData next = (MetaData)((Tuple3)var10.get())._3();
            return new Some(new Tuple3(key, value, next));
         }
      }

      return .MODULE$;
   }

   public Attribute apply(final String key, final Seq value, final MetaData next) {
      return new UnprefixedAttribute(key, value, next);
   }

   public Attribute apply(final String pre, final String key, final String value, final MetaData next) {
      if (pre != null) {
         String var5 = "";
         if (pre == null) {
            if (var5 == null) {
               return new UnprefixedAttribute(key, value, next);
            }
         } else if (pre.equals(var5)) {
            return new UnprefixedAttribute(key, value, next);
         }

         return new PrefixedAttribute(pre, key, value, next);
      } else {
         return new UnprefixedAttribute(key, value, next);
      }
   }

   public Attribute apply(final String pre, final String key, final Seq value, final MetaData next) {
      if (pre != null) {
         String var5 = "";
         if (pre == null) {
            if (var5 == null) {
               return new UnprefixedAttribute(key, value, next);
            }
         } else if (pre.equals(var5)) {
            return new UnprefixedAttribute(key, value, next);
         }

         return new PrefixedAttribute(pre, key, value, next);
      } else {
         return new UnprefixedAttribute(key, value, next);
      }
   }

   public Attribute apply(final Option pre, final String key, final Seq value, final MetaData next) {
      if (.MODULE$.equals(pre)) {
         return new UnprefixedAttribute(key, value, next);
      } else if (pre instanceof Some) {
         Some var7 = (Some)pre;
         String p = (String)var7.value();
         return new PrefixedAttribute(p, key, value, next);
      } else {
         throw new MatchError(pre);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Attribute$.class);
   }

   private Attribute$() {
   }
}
