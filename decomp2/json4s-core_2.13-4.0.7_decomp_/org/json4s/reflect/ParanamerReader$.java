package org.json4s.reflect;

import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;

public final class ParanamerReader$ implements ParameterNameReader {
   public static final ParanamerReader$ MODULE$ = new ParanamerReader$();
   private static final CachingParanamer paranamer = new CachingParanamer(new BytecodeReadingParanamer());

   public Seq lookupParameterNames(final Executable constructor) {
      return .MODULE$.toSeq$extension(scala.Predef..MODULE$.refArrayOps((Object[])paranamer.lookupParameterNames(constructor.getAsAccessibleObject())));
   }

   private ParanamerReader$() {
   }
}
