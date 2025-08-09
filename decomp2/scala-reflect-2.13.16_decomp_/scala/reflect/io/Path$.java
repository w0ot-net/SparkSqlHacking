package scala.reflect.io;

import java.lang.invoke.SerializedLambda;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.ArraySeq;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.Statics;

public final class Path$ {
   public static final Path$ MODULE$ = new Path$();

   public boolean isExtensionJarOrZip(final java.io.File jfile) {
      return this.isExtensionJarOrZip(jfile.getName());
   }

   public boolean isExtensionJarOrZip(final String name) {
      int var2 = name.lastIndexOf(46);
      switch (var2) {
         default:
            if (var2 >= 0) {
               String xt = name.substring(var2 + 1);
               return xt.equalsIgnoreCase("jar") || xt.equalsIgnoreCase("zip");
            } else {
               return false;
            }
      }
   }

   public String extension(final String name) {
      int i = name.lastIndexOf(46);
      return i < 0 ? "" : name.substring(i + 1).toLowerCase();
   }

   public Path string2path(final String s) {
      return this.apply(s);
   }

   public Path jfile2path(final java.io.File jfile) {
      return this.apply(jfile);
   }

   public Iterator onlyDirs(final Iterator xs) {
      return xs.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$onlyDirs$1(x$1))).map((x$2) -> x$2.toDirectory());
   }

   public List onlyDirs(final List xs) {
      if (xs == null) {
         throw null;
      } else {
         boolean filter_filterCommon_isFlipped = false;
         List filter_filterCommon_noneIn$1_l = xs;

         Object var10000;
         while(true) {
            if (filter_filterCommon_noneIn$1_l.isEmpty()) {
               var10000 = .MODULE$;
               break;
            }

            Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
            List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
            if (((Path)filter_filterCommon_noneIn$1_h).isDirectory() != filter_filterCommon_isFlipped) {
               List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

               while(true) {
                  if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                     var10000 = filter_filterCommon_noneIn$1_l;
                     break;
                  }

                  Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                  if (((Path)filter_filterCommon_noneIn$1_allIn$1_x).isDirectory() == filter_filterCommon_isFlipped) {
                     scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), .MODULE$);
                     List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                     scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                     for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                        filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                        filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                     }

                     List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                     while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                        Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                        if (((Path)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head).isDirectory() != filter_filterCommon_isFlipped) {
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                        } else {
                           while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                              scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                           }

                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                        }
                     }

                     if (!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                        filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                     }

                     var10000 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                     Object var30 = null;
                     Object var33 = null;
                     Object var36 = null;
                     Object var39 = null;
                     Object var42 = null;
                     Object var45 = null;
                     Object var48 = null;
                     Object var51 = null;
                     break;
                  }

                  filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
               }

               Object var26 = null;
               Object var28 = null;
               Object var31 = null;
               Object var34 = null;
               Object var37 = null;
               Object var40 = null;
               Object var43 = null;
               Object var46 = null;
               Object var49 = null;
               Object var52 = null;
               break;
            }

            filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
         }

         Object var23 = null;
         Object var24 = null;
         Object var25 = null;
         Object var27 = null;
         Object var29 = null;
         Object var32 = null;
         Object var35 = null;
         Object var38 = null;
         Object var41 = null;
         Object var44 = null;
         Object var47 = null;
         Object var50 = null;
         Object var53 = null;
         List filter_filterCommon_result = (List)var10000;
         Statics.releaseFence();
         var10000 = filter_filterCommon_result;
         filter_filterCommon_result = null;
         List map_this = (List)var10000;
         if (map_this == .MODULE$) {
            return .MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Path)map_this.head()).toDirectory(), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Path)map_rest.head()).toDirectory(), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   public Iterator onlyFiles(final Iterator xs) {
      return xs.filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$onlyFiles$1(x$5))).map((x$6) -> x$6.toFile());
   }

   public List roots() {
      ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(java.io.File.listRoots());
      if (var10000 == null) {
         throw null;
      } else {
         List var6 = IterableOnceOps.toList$(var10000);
         if (var6 == null) {
            throw null;
         } else {
            List map_this = var6;
            if (map_this == .MODULE$) {
               return .MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$roots$1((java.io.File)map_this.head()), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$roots$1((java.io.File)map_rest.head()), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               return map_h;
            }
         }
      }
   }

   public Path apply(final String path) {
      return this.apply(new java.io.File(path));
   }

   public Path apply(final java.io.File jfile) {
      Object var10000;
      try {
         if (jfile.isFile()) {
            var10000 = new File(jfile, scala.io.Codec..MODULE$.fallbackSystemCodec());
         } else {
            if (!jfile.isDirectory()) {
               return new Path(jfile);
            }

            var10000 = new Directory(jfile);
         }
      } catch (SecurityException var2) {
         var10000 = new Path(jfile);
      }

      return (Path)var10000;
   }

   public String randomPrefix() {
      LazyList var10000 = scala.util.Random..MODULE$.alphanumeric().take(6);
      String mkString_sep = "";
      if (var10000 == null) {
         throw null;
      } else {
         AbstractIterable mkString_this = var10000;
         String mkString_end = "";
         String mkString_start = "";
         return IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
      }
   }

   public Nothing fail(final String msg) {
      throw new FileOperationException(msg);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onlyDirs$1(final Path x$1) {
      return x$1.isDirectory();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onlyDirs$3(final Path x$3) {
      return x$3.isDirectory();
   }

   // $FF: synthetic method
   public static final Directory $anonfun$onlyDirs$4(final Path x$4) {
      return x$4.toDirectory();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onlyFiles$1(final Path x$5) {
      return x$5.isFile();
   }

   // $FF: synthetic method
   public static final Path $anonfun$roots$1(final java.io.File jfile) {
      return MODULE$.apply(jfile);
   }

   private static final boolean isFile$1(final java.io.File jfile$1) {
      return jfile$1.isFile();
   }

   private static final boolean isDirectory$1(final java.io.File jfile$1) {
      return jfile$1.isDirectory();
   }

   private Path$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$onlyDirs$3$adapted(final Path x$3) {
      return BoxesRunTime.boxToBoolean($anonfun$onlyDirs$3(x$3));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
