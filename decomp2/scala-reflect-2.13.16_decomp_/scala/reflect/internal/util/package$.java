package scala.reflect.internal.util;

import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.AbstractIterable;
import scala.collection.ArrayOps;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar;
import scala.runtime.Statics;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final List ListOfNil;
   private static final Option SomeOfNil;

   static {
      Nil var0 = .MODULE$;
      List $colon$colon_this = .MODULE$;
      scala.collection.immutable..colon.colon var10000 = new scala.collection.immutable..colon.colon(var0, $colon$colon_this);
      $colon$colon_this = null;
      ListOfNil = var10000;
      SomeOfNil = new Some(.MODULE$);
   }

   public List ListOfNil() {
      return ListOfNil;
   }

   public Option SomeOfNil() {
      return SomeOfNil;
   }

   public boolean andFalse(final BoxedUnit body) {
      return false;
   }

   private String shortenName(final String name) {
      String var2 = "";
      if (name != null) {
         if (name.equals(var2)) {
            return "";
         }
      }

      ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(scala.collection.StringOps..MODULE$.split$extension(name, '$'));
      if (var10000 == null) {
         throw null;
      } else {
         List segments = IterableOnceOps.toList$(var10000);
         String last = (String)segments.last();
         if (last.length() == 0) {
            List var9 = segments.takeRight(2);
            String mkString_sep = "$";
            if (var9 == null) {
               throw null;
            } else {
               AbstractIterable mkString_this = var9;
               String mkString_end = "";
               String mkString_start = "";
               return IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
            }
         } else {
            return last;
         }
      }
   }

   public String shortClassOfInstance(final Object x) {
      return this.shortClass(x.getClass());
   }

   public String shortClass(final Class clazz) {
      String name = (String)scala.collection.ArrayOps..MODULE$.last$extension(scala.collection.StringOps..MODULE$.split$extension(clazz.getName(), '.'));
      if (name.endsWith("$")) {
         Object filterNot$extension_$this;
         StringBuilder var33;
         Object var44;
         ArrayOps var10001;
         label145: {
            label163: {
               var33 = new StringBuilder(1);
               var10001 = scala.collection.ArrayOps..MODULE$;
               Object[] refArrayOps_xs = scala.collection.StringOps..MODULE$.split$extension(name, '$');
               Object var18 = null;
               filterNot$extension_$this = refArrayOps_xs;
               ArrayBuilder var34 = scala.collection.mutable.ArrayBuilder..MODULE$;
               ClassTag filterNot$extension_filter$extension_make_evidence$1 = scala.reflect.ClassTag..MODULE$.apply(refArrayOps_xs.getClass().getComponentType());
               Class var11 = filterNot$extension_filter$extension_make_evidence$1.runtimeClass();
               Class var35 = Byte.TYPE;
               if (var35 == null) {
                  if (var11 == null) {
                     break label163;
                  }
               } else if (var35.equals(var11)) {
                  break label163;
               }

               label164: {
                  var35 = Short.TYPE;
                  if (var35 == null) {
                     if (var11 == null) {
                        break label164;
                     }
                  } else if (var35.equals(var11)) {
                     break label164;
                  }

                  label165: {
                     var35 = Character.TYPE;
                     if (var35 == null) {
                        if (var11 == null) {
                           break label165;
                        }
                     } else if (var35.equals(var11)) {
                        break label165;
                     }

                     label166: {
                        var35 = Integer.TYPE;
                        if (var35 == null) {
                           if (var11 == null) {
                              break label166;
                           }
                        } else if (var35.equals(var11)) {
                           break label166;
                        }

                        label167: {
                           var35 = Long.TYPE;
                           if (var35 == null) {
                              if (var11 == null) {
                                 break label167;
                              }
                           } else if (var35.equals(var11)) {
                              break label167;
                           }

                           label168: {
                              var35 = Float.TYPE;
                              if (var35 == null) {
                                 if (var11 == null) {
                                    break label168;
                                 }
                              } else if (var35.equals(var11)) {
                                 break label168;
                              }

                              label169: {
                                 var35 = Double.TYPE;
                                 if (var35 == null) {
                                    if (var11 == null) {
                                       break label169;
                                    }
                                 } else if (var35.equals(var11)) {
                                    break label169;
                                 }

                                 label170: {
                                    var35 = Boolean.TYPE;
                                    if (var35 == null) {
                                       if (var11 == null) {
                                          break label170;
                                       }
                                    } else if (var35.equals(var11)) {
                                       break label170;
                                    }

                                    label88: {
                                       var35 = Void.TYPE;
                                       if (var35 == null) {
                                          if (var11 == null) {
                                             break label88;
                                          }
                                       } else if (var35.equals(var11)) {
                                          break label88;
                                       }

                                       var44 = new ArrayBuilder.ofRef(filterNot$extension_filter$extension_make_evidence$1);
                                       break label145;
                                    }

                                    var44 = new ArrayBuilder.ofUnit();
                                    break label145;
                                 }

                                 var44 = new ArrayBuilder.ofBoolean();
                                 break label145;
                              }

                              var44 = new ArrayBuilder.ofDouble();
                              break label145;
                           }

                           var44 = new ArrayBuilder.ofFloat();
                           break label145;
                        }

                        var44 = new ArrayBuilder.ofLong();
                        break label145;
                     }

                     var44 = new ArrayBuilder.ofInt();
                     break label145;
                  }

                  var44 = new ArrayBuilder.ofChar();
                  break label145;
               }

               var44 = new ArrayBuilder.ofShort();
               break label145;
            }

            var44 = new ArrayBuilder.ofByte();
         }

         Object var22 = null;
         Object var23 = null;
         ArrayBuilder filterNot$extension_filter$extension_res = (ArrayBuilder)var44;

         for(int filterNot$extension_filter$extension_i = 0; filterNot$extension_filter$extension_i < ((Object[])filterNot$extension_$this).length; ++filterNot$extension_filter$extension_i) {
            Object filterNot$extension_filter$extension_x = ((Object[])filterNot$extension_$this)[filterNot$extension_filter$extension_i];
            if (!$anonfun$shortClass$2((String)filterNot$extension_filter$extension_x)) {
               filterNot$extension_filter$extension_res.addOne(filterNot$extension_filter$extension_x);
            }
         }

         var44 = filterNot$extension_filter$extension_res.result();
         filterNot$extension_$this = null;
         filterNot$extension_filter$extension_res = null;
         Object var21 = null;
         return var33.append((String)var10001.last$extension(var44)).append("$").toString();
      } else if (!isAnon$1(name)) {
         return this.shortenName(name);
      } else {
         Class var3 = clazz.getSuperclass();
         ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(clazz.getInterfaces());
         if (var10000 == null) {
            throw null;
         } else {
            List var30 = IterableOnceOps.toList$(var10000);
            if (var30 == null) {
               throw null;
            } else {
               List $colon$colon_this = var30;
               scala.collection.immutable..colon.colon var31 = new scala.collection.immutable..colon.colon(var3, $colon$colon_this);
               Object var29 = null;
               List map_this = var31;
               Object var32;
               if (map_this == .MODULE$) {
                  var32 = .MODULE$;
               } else {
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$shortClass$3((Class)map_this.head()), .MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = ((scala.collection.immutable..colon.colon)map_this).next(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$shortClass$3((Class)map_rest.head()), .MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var32 = map_h;
               }

               map_this = null;
               Object var25 = null;
               Object var26 = null;
               Object var27 = null;
               Object var28 = null;
               String mkString_sep = " with ";
               return ((IterableOnceOps)var32).mkString("", mkString_sep, "");
            }
         }
      }
   }

   public package.StringContextStripMarginOps StringContextStripMarginOps(final StringContext stringContext) {
      return new package.StringContextStripMarginOps(stringContext);
   }

   private static final boolean isModule$1(final String name$1) {
      return name$1.endsWith("$");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$shortClass$1(final char x$1) {
      RichChar var10000 = scala.runtime.RichChar..MODULE$;
      return Character.isDigit(x$1);
   }

   private static final boolean isAnon$1(final String name$1) {
      String augmentString_x = (String)scala.collection.ArrayOps..MODULE$.last$extension(scala.collection.StringOps..MODULE$.split$extension(name$1, '$'));
      Object var5 = null;
      String forall$extension_$this = augmentString_x;
      int forall$extension_i = 0;

      for(int forall$extension_len = augmentString_x.length(); forall$extension_i < forall$extension_len; ++forall$extension_i) {
         if (!$anonfun$shortClass$1(forall$extension_$this.charAt(forall$extension_i))) {
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$shortClass$2(final String x$2) {
      String var1 = "";
      if (x$2 != null) {
         if (x$2.equals(var1)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final String $anonfun$shortClass$3(final Class c) {
      return MODULE$.shortClass(c);
   }

   private package$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$shortClass$2$adapted(final String x$2) {
      return BoxesRunTime.boxToBoolean($anonfun$shortClass$2(x$2));
   }

   // $FF: synthetic method
   public static final Object $anonfun$shortClass$1$adapted(final Object x$1) {
      return BoxesRunTime.boxToBoolean($anonfun$shortClass$1(BoxesRunTime.unboxToChar(x$1)));
   }
}
