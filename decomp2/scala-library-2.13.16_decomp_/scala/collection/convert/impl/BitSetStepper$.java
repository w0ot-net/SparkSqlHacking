package scala.collection.convert.impl;

import scala.collection.BitSetOps;
import scala.collection.IntStepper;

public final class BitSetStepper$ {
   public static final BitSetStepper$ MODULE$ = new BitSetStepper$();

   public IntStepper from(final BitSetOps bs) {
      return new BitSetStepper(bs.nwords() <= 2 ? null : bs, bs.nwords() <= 0 ? -1L : bs.word(0), bs.nwords() <= 1 ? -1L : bs.word(1), 0, bs.nwords() * 64, 0);
   }

   private BitSetStepper$() {
   }
}
