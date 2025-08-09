package com.ibm.icu.impl.number;

import com.ibm.icu.impl.FormattedStringBuilder;
import com.ibm.icu.impl.StandardPlural;
import java.text.Format;

public interface Modifier {
   int apply(FormattedStringBuilder var1, int var2, int var3);

   int getPrefixLength();

   int getCodePointCount();

   boolean isStrong();

   boolean containsField(Format.Field var1);

   Parameters getParameters();

   boolean strictEquals(Modifier var1);

   default boolean semanticallyEquivalent(Modifier other) {
      Parameters paramsThis = this.getParameters();
      Parameters paramsOther = other.getParameters();
      if (paramsThis == null && paramsOther == null) {
         return this.strictEquals(other);
      } else if (paramsThis != null && paramsOther != null) {
         if (paramsThis.obj == null && paramsOther.obj == null) {
            return this.strictEquals(other);
         } else if (paramsThis.obj != null && paramsOther.obj != null) {
            for(Signum signum : Modifier.Signum.VALUES) {
               for(StandardPlural plural : StandardPlural.VALUES) {
                  Modifier mod1 = paramsThis.obj.getModifier(signum, plural);
                  Modifier mod2 = paramsOther.obj.getModifier(signum, plural);
                  if (mod1 != mod2) {
                     if (mod1 == null || mod2 == null) {
                        return false;
                     }

                     if (!mod1.strictEquals(mod2)) {
                        return false;
                     }
                  }
               }
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static enum Signum {
      NEG,
      NEG_ZERO,
      POS_ZERO,
      POS;

      static final int COUNT = values().length;
      public static final Signum[] VALUES = values();
   }

   public static class Parameters {
      public ModifierStore obj;
      public Signum signum;
      public StandardPlural plural;
   }
}
