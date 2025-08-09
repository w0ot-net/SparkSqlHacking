package org.json4s;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class JValue$ implements Merge.Mergeable, Serializable {
   public static final JValue$ MODULE$ = new JValue$();
   private static MergeDep ooo;
   private static MergeDep aaa;

   static {
      LowPriorityMergeDep.$init$(MODULE$);
      MergeDeps.$init$(MODULE$);
      Merge.Mergeable.$init$(MODULE$);
   }

   public JValue j2m(final JValue json) {
      return Merge.Mergeable.j2m$(this, json);
   }

   public MergeDep jjj() {
      return LowPriorityMergeDep.jjj$(this);
   }

   public MergeDep ooo() {
      return ooo;
   }

   public MergeDep aaa() {
      return aaa;
   }

   public void org$json4s$MergeDeps$_setter_$ooo_$eq(final MergeDep x$1) {
      ooo = x$1;
   }

   public void org$json4s$MergeDeps$_setter_$aaa_$eq(final MergeDep x$1) {
      aaa = x$1;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JValue$.class);
   }

   private JValue$() {
   }
}
