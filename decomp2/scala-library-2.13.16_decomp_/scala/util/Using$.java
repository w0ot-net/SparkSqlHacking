package scala.util;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.util.control.ControlThrowable;
import scala.util.control.NonFatal$;

public final class Using$ {
   public static final Using$ MODULE$ = new Using$();

   public Try apply(final Function0 resource, final Function1 f, final Using.Releasable evidence$1) {
      Try$ var10000 = Try$.MODULE$;

      try {
         Using$ var47 = MODULE$;
         Object $anonfun$apply$1_resource_resource = resource.apply();
         Using$ $anonfun$apply$1_resource_this = var47;
         if ($anonfun$apply$1_resource_resource == null) {
            throw new NullPointerException("null resource");
         } else {
            label207: {
               Throwable $anonfun$apply$1_resource_toThrow = null;
               boolean var33 = false;

               try {
                  var33 = true;
                  var47 = (Using$)f.apply($anonfun$apply$1_resource_resource);
                  var33 = false;
               } catch (Throwable var38) {
                  $anonfun$apply$1_resource_toThrow = var38;
                  var47 = null;
                  var33 = false;
               } finally {
                  if (var33) {
                     if ($anonfun$apply$1_resource_toThrow != null) {
                        try {
                           evidence$1.release($anonfun$apply$1_resource_resource);
                        } catch (Throwable var34) {
                           $anonfun$apply$1_resource_toThrow = $anonfun$apply$1_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$apply$1_resource_toThrow, var34);
                        } finally {
                           throw $anonfun$apply$1_resource_toThrow;
                        }
                     }

                     evidence$1.release($anonfun$apply$1_resource_resource);
                  }
               }

               Object var9 = var47;
               if ($anonfun$apply$1_resource_toThrow == null) {
                  evidence$1.release($anonfun$apply$1_resource_resource);
                  var47 = (Using$)var9;
                  Object var41 = null;
                  $anonfun$apply$1_resource_resource = null;
                  var9 = null;
                  Object apply_r1 = var47;
                  return new Success(apply_r1);
               } else {
                  try {
                     evidence$1.release($anonfun$apply$1_resource_resource);
                  } catch (Throwable var36) {
                     $anonfun$apply$1_resource_toThrow = $anonfun$apply$1_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$apply$1_resource_toThrow, var36);
                  } finally {
                     throw $anonfun$apply$1_resource_toThrow;
                  }
               }
            }
         }
      } catch (Throwable var40) {
         if (var40 != null && NonFatal$.MODULE$.apply(var40)) {
            return new Failure(var40);
         } else {
            throw var40;
         }
      }
   }

   public Throwable scala$util$Using$$preferentiallySuppress(final Throwable primary, final Throwable secondary) {
      if (score$1(secondary) > score$1(primary)) {
         secondary.addSuppressed(primary);
         return secondary;
      } else {
         primary.addSuppressed(secondary);
         return primary;
      }
   }

   public Object resource(final Object resource, final Function1 body, final Using.Releasable releasable) {
      if (resource == null) {
         throw new NullPointerException("null resource");
      } else {
         label157: {
            Throwable toThrow = null;
            boolean var26 = false;

            Object var10000;
            try {
               var26 = true;
               var10000 = body.apply(resource);
               var26 = false;
            } catch (Throwable var31) {
               toThrow = var31;
               var10000 = null;
               var26 = false;
            } finally {
               if (var26) {
                  if (toThrow != null) {
                     try {
                        releasable.release(resource);
                     } catch (Throwable var27) {
                        toThrow = this.scala$util$Using$$preferentiallySuppress(toThrow, var27);
                     } finally {
                        throw toThrow;
                     }
                  }

                  releasable.release(resource);
               }
            }

            Object var5 = var10000;
            if (toThrow == null) {
               releasable.release(resource);
               return var5;
            } else {
               try {
                  releasable.release(resource);
               } catch (Throwable var29) {
                  toThrow = this.scala$util$Using$$preferentiallySuppress(toThrow, var29);
               } finally {
                  throw toThrow;
               }
            }
         }
      }
   }

   public Object resources(final Object resource1, final Function0 resource2, final Function2 body, final Using.Releasable evidence$4, final Using.Releasable evidence$5) {
      if (resource1 == null) {
         throw new NullPointerException("null resource");
      } else {
         label501: {
            Throwable resource_toThrow = null;
            boolean var52 = false;

            Object var10000;
            try {
               var52 = true;
               Using$ var111 = MODULE$;
               Object $anonfun$resources$1_resource_resource = resource2.apply();
               Using$ $anonfun$resources$1_resource_this = var111;
               if ($anonfun$resources$1_resource_resource == null) {
                  throw new NullPointerException("null resource");
               }

               Throwable $anonfun$resources$1_resource_toThrow = null;
               boolean var89 = false;

               try {
                  var89 = true;
                  var111 = (Using$)body.apply(resource1, $anonfun$resources$1_resource_resource);
                  var89 = false;
               } catch (Throwable var98) {
                  $anonfun$resources$1_resource_toThrow = var98;
                  var111 = null;
                  var89 = false;
               } finally {
                  if (var89) {
                     if ($anonfun$resources$1_resource_toThrow != null) {
                        try {
                           evidence$5.release($anonfun$resources$1_resource_resource);
                        } catch (Throwable var94) {
                           $anonfun$resources$1_resource_toThrow = $anonfun$resources$1_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$1_resource_toThrow, var94);
                        } finally {
                           throw $anonfun$resources$1_resource_toThrow;
                        }
                     }

                     evidence$5.release($anonfun$resources$1_resource_resource);
                  }
               }

               Object var13 = var111;
               if ($anonfun$resources$1_resource_toThrow != null) {
                  try {
                     evidence$5.release($anonfun$resources$1_resource_resource);
                  } catch (Throwable var96) {
                     $anonfun$resources$1_resource_toThrow = $anonfun$resources$1_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$1_resource_toThrow, var96);
                  } finally {
                     throw $anonfun$resources$1_resource_toThrow;
                  }
               }

               evidence$5.release($anonfun$resources$1_resource_resource);
               var10000 = var13;
               Object var102 = null;
               $anonfun$resources$1_resource_resource = null;
               var13 = null;
               var52 = false;
            } catch (Throwable var100) {
               resource_toThrow = var100;
               var10000 = null;
               var52 = false;
            } finally {
               if (var52) {
                  if (resource_toThrow != null) {
                     try {
                        evidence$4.release(resource1);
                     } catch (Throwable var90) {
                        resource_toThrow = this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var90);
                     } finally {
                        throw resource_toThrow;
                     }
                  }

                  evidence$4.release(resource1);
               }
            }

            Object var7 = var10000;
            if (resource_toThrow == null) {
               evidence$4.release(resource1);
               return var7;
            } else {
               try {
                  evidence$4.release(resource1);
               } catch (Throwable var92) {
                  resource_toThrow = this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var92);
               } finally {
                  throw resource_toThrow;
               }
            }
         }
      }
   }

   public Object resources(final Object resource1, final Function0 resource2, final Function0 resource3, final Function3 body, final Using.Releasable evidence$6, final Using.Releasable evidence$7, final Using.Releasable evidence$8) {
      if (resource1 == null) {
         throw new NullPointerException("null resource");
      } else {
         label1042: {
            Throwable resource_toThrow = null;
            boolean var78 = false;

            Object var10000;
            try {
               var78 = true;
               Using$ var226 = MODULE$;
               Object $anonfun$resources$3_resource_resource = resource2.apply();
               Using$ $anonfun$resources$3_resource_this = var226;
               if ($anonfun$resources$3_resource_resource == null) {
                  throw new NullPointerException("null resource");
               }

               Throwable $anonfun$resources$3_resource_toThrow = null;
               boolean var133 = false;

               try {
                  var133 = true;
                  var226 = MODULE$;
                  Object $anonfun$resources$3_$anonfun$resources$4_resource_resource = resource3.apply();
                  Using$ $anonfun$resources$3_$anonfun$resources$4_resource_this = var226;
                  if ($anonfun$resources$3_$anonfun$resources$4_resource_resource == null) {
                     throw new NullPointerException("null resource");
                  }

                  Throwable $anonfun$resources$3_$anonfun$resources$4_resource_toThrow = null;
                  boolean var188 = false;

                  try {
                     var188 = true;
                     var226 = (Using$)body.apply(resource1, $anonfun$resources$3_resource_resource, $anonfun$resources$3_$anonfun$resources$4_resource_resource);
                     var188 = false;
                  } catch (Throwable var201) {
                     $anonfun$resources$3_$anonfun$resources$4_resource_toThrow = var201;
                     var226 = null;
                     var188 = false;
                  } finally {
                     if (var188) {
                        if ($anonfun$resources$3_$anonfun$resources$4_resource_toThrow != null) {
                           try {
                              evidence$8.release($anonfun$resources$3_$anonfun$resources$4_resource_resource);
                           } catch (Throwable var197) {
                              $anonfun$resources$3_$anonfun$resources$4_resource_toThrow = $anonfun$resources$3_$anonfun$resources$4_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$3_$anonfun$resources$4_resource_toThrow, var197);
                           } finally {
                              throw $anonfun$resources$3_$anonfun$resources$4_resource_toThrow;
                           }
                        }

                        evidence$8.release($anonfun$resources$3_$anonfun$resources$4_resource_resource);
                     }
                  }

                  Object var21 = var226;
                  if ($anonfun$resources$3_$anonfun$resources$4_resource_toThrow != null) {
                     try {
                        evidence$8.release($anonfun$resources$3_$anonfun$resources$4_resource_resource);
                     } catch (Throwable var199) {
                        $anonfun$resources$3_$anonfun$resources$4_resource_toThrow = $anonfun$resources$3_$anonfun$resources$4_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$3_$anonfun$resources$4_resource_toThrow, var199);
                     } finally {
                        throw $anonfun$resources$3_$anonfun$resources$4_resource_toThrow;
                     }
                  }

                  evidence$8.release($anonfun$resources$3_$anonfun$resources$4_resource_resource);
                  var226 = (Using$)var21;
                  Object var210 = null;
                  $anonfun$resources$3_$anonfun$resources$4_resource_resource = null;
                  var21 = null;
                  var133 = false;
               } catch (Throwable var203) {
                  $anonfun$resources$3_resource_toThrow = var203;
                  var226 = null;
                  var133 = false;
               } finally {
                  if (var133) {
                     if ($anonfun$resources$3_resource_toThrow != null) {
                        try {
                           evidence$7.release($anonfun$resources$3_resource_resource);
                        } catch (Throwable var193) {
                           $anonfun$resources$3_resource_toThrow = $anonfun$resources$3_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$3_resource_toThrow, var193);
                        } finally {
                           throw $anonfun$resources$3_resource_toThrow;
                        }
                     }

                     evidence$7.release($anonfun$resources$3_resource_resource);
                  }
               }

               Object var15 = var226;
               if ($anonfun$resources$3_resource_toThrow != null) {
                  try {
                     evidence$7.release($anonfun$resources$3_resource_resource);
                  } catch (Throwable var195) {
                     $anonfun$resources$3_resource_toThrow = $anonfun$resources$3_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$3_resource_toThrow, var195);
                  } finally {
                     throw $anonfun$resources$3_resource_toThrow;
                  }
               }

               evidence$7.release($anonfun$resources$3_resource_resource);
               var10000 = var15;
               Object var207 = null;
               $anonfun$resources$3_resource_resource = null;
               var15 = null;
               Object var211 = null;
               Object var213 = null;
               Object var214 = null;
               Object var216 = null;
               Object var22 = null;
               Object $anonfun$resources$3_$anonfun$resources$4_resource_other = null;
               var78 = false;
            } catch (Throwable var205) {
               resource_toThrow = var205;
               var10000 = null;
               var78 = false;
            } finally {
               if (var78) {
                  if (resource_toThrow != null) {
                     try {
                        evidence$6.release(resource1);
                     } catch (Throwable var189) {
                        resource_toThrow = this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var189);
                     } finally {
                        throw resource_toThrow;
                     }
                  }

                  evidence$6.release(resource1);
               }
            }

            Object var9 = var10000;
            if (resource_toThrow == null) {
               evidence$6.release(resource1);
               return var9;
            } else {
               try {
                  evidence$6.release(resource1);
               } catch (Throwable var191) {
                  resource_toThrow = this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var191);
               } finally {
                  throw resource_toThrow;
               }
            }
         }
      }
   }

   public Object resources(final Object resource1, final Function0 resource2, final Function0 resource3, final Function0 resource4, final Function4 body, final Using.Releasable evidence$9, final Using.Releasable evidence$10, final Using.Releasable evidence$11, final Using.Releasable evidence$12) {
      if (resource1 == null) {
         throw new NullPointerException("null resource");
      } else {
         label1777: {
            Throwable resource_toThrow = null;
            boolean var104 = false;

            Object var10000;
            try {
               var104 = true;
               Using$ var373 = MODULE$;
               Object $anonfun$resources$6_resource_resource = resource2.apply();
               Using$ $anonfun$resources$6_resource_this = var373;
               if ($anonfun$resources$6_resource_resource == null) {
                  throw new NullPointerException("null resource");
               }

               Throwable $anonfun$resources$6_resource_toThrow = null;
               boolean var177 = false;

               try {
                  var177 = true;
                  var373 = MODULE$;
                  Object $anonfun$resources$6_$anonfun$resources$7_resource_resource = resource3.apply();
                  Using$ $anonfun$resources$6_$anonfun$resources$7_resource_this = var373;
                  if ($anonfun$resources$6_$anonfun$resources$7_resource_resource == null) {
                     throw new NullPointerException("null resource");
                  }

                  Throwable $anonfun$resources$6_$anonfun$resources$7_resource_toThrow = null;
                  boolean var250 = false;

                  try {
                     var250 = true;
                     var373 = MODULE$;
                     Object $anonfun$resources$8_resource_resource = resource4.apply();
                     Using$ $anonfun$resources$8_resource_this = var373;
                     if ($anonfun$resources$8_resource_resource == null) {
                        throw new NullPointerException("null resource");
                     }

                     Throwable $anonfun$resources$8_resource_toThrow = null;
                     boolean var323 = false;

                     try {
                        var323 = true;
                        var373 = (Using$)body.apply(resource1, $anonfun$resources$6_resource_resource, $anonfun$resources$6_$anonfun$resources$7_resource_resource, $anonfun$resources$8_resource_resource);
                        var323 = false;
                     } catch (Throwable var340) {
                        $anonfun$resources$8_resource_toThrow = var340;
                        var373 = null;
                        var323 = false;
                     } finally {
                        if (var323) {
                           if ($anonfun$resources$8_resource_toThrow != null) {
                              try {
                                 evidence$12.release($anonfun$resources$8_resource_resource);
                              } catch (Throwable var336) {
                                 $anonfun$resources$8_resource_toThrow = $anonfun$resources$8_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$8_resource_toThrow, var336);
                              } finally {
                                 throw $anonfun$resources$8_resource_toThrow;
                              }
                           }

                           evidence$12.release($anonfun$resources$8_resource_resource);
                        }
                     }

                     Object var29 = var373;
                     if ($anonfun$resources$8_resource_toThrow != null) {
                        try {
                           evidence$12.release($anonfun$resources$8_resource_resource);
                        } catch (Throwable var338) {
                           $anonfun$resources$8_resource_toThrow = $anonfun$resources$8_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$8_resource_toThrow, var338);
                        } finally {
                           throw $anonfun$resources$8_resource_toThrow;
                        }
                     }

                     evidence$12.release($anonfun$resources$8_resource_resource);
                     var373 = (Using$)var29;
                     Object var358 = null;
                     $anonfun$resources$8_resource_resource = null;
                     var29 = null;
                     var250 = false;
                  } catch (Throwable var342) {
                     $anonfun$resources$6_$anonfun$resources$7_resource_toThrow = var342;
                     var373 = null;
                     var250 = false;
                  } finally {
                     if (var250) {
                        if ($anonfun$resources$6_$anonfun$resources$7_resource_toThrow != null) {
                           try {
                              evidence$11.release($anonfun$resources$6_$anonfun$resources$7_resource_resource);
                           } catch (Throwable var332) {
                              $anonfun$resources$6_$anonfun$resources$7_resource_toThrow = $anonfun$resources$6_$anonfun$resources$7_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$6_$anonfun$resources$7_resource_toThrow, var332);
                           } finally {
                              throw $anonfun$resources$6_$anonfun$resources$7_resource_toThrow;
                           }
                        }

                        evidence$11.release($anonfun$resources$6_$anonfun$resources$7_resource_resource);
                     }
                  }

                  Object var23 = var373;
                  if ($anonfun$resources$6_$anonfun$resources$7_resource_toThrow != null) {
                     try {
                        evidence$11.release($anonfun$resources$6_$anonfun$resources$7_resource_resource);
                     } catch (Throwable var334) {
                        $anonfun$resources$6_$anonfun$resources$7_resource_toThrow = $anonfun$resources$6_$anonfun$resources$7_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$6_$anonfun$resources$7_resource_toThrow, var334);
                     } finally {
                        throw $anonfun$resources$6_$anonfun$resources$7_resource_toThrow;
                     }
                  }

                  evidence$11.release($anonfun$resources$6_$anonfun$resources$7_resource_resource);
                  var373 = (Using$)var23;
                  Object var351 = null;
                  $anonfun$resources$6_$anonfun$resources$7_resource_resource = null;
                  var23 = null;
                  var177 = false;
               } catch (Throwable var344) {
                  $anonfun$resources$6_resource_toThrow = var344;
                  var373 = null;
                  var177 = false;
               } finally {
                  if (var177) {
                     if ($anonfun$resources$6_resource_toThrow != null) {
                        try {
                           evidence$10.release($anonfun$resources$6_resource_resource);
                        } catch (Throwable var328) {
                           $anonfun$resources$6_resource_toThrow = $anonfun$resources$6_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$6_resource_toThrow, var328);
                        } finally {
                           throw $anonfun$resources$6_resource_toThrow;
                        }
                     }

                     evidence$10.release($anonfun$resources$6_resource_resource);
                  }
               }

               Object var17 = var373;
               if ($anonfun$resources$6_resource_toThrow != null) {
                  try {
                     evidence$10.release($anonfun$resources$6_resource_resource);
                  } catch (Throwable var330) {
                     $anonfun$resources$6_resource_toThrow = $anonfun$resources$6_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$6_resource_toThrow, var330);
                  } finally {
                     throw $anonfun$resources$6_resource_toThrow;
                  }
               }

               evidence$10.release($anonfun$resources$6_resource_resource);
               var10000 = var17;
               Object var348 = null;
               $anonfun$resources$6_resource_resource = null;
               var17 = null;
               Object var352 = null;
               Object var354 = null;
               Object var355 = null;
               Object var357 = null;
               Object var24 = null;
               Object $anonfun$resources$6_$anonfun$resources$7_resource_other = null;
               var104 = false;
            } catch (Throwable var346) {
               resource_toThrow = var346;
               var10000 = null;
               var104 = false;
            } finally {
               if (var104) {
                  if (resource_toThrow != null) {
                     try {
                        evidence$9.release(resource1);
                     } catch (Throwable var324) {
                        resource_toThrow = this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var324);
                     } finally {
                        throw resource_toThrow;
                     }
                  }

                  evidence$9.release(resource1);
               }
            }

            Object var11 = var10000;
            if (resource_toThrow == null) {
               evidence$9.release(resource1);
               return var11;
            } else {
               try {
                  evidence$9.release(resource1);
               } catch (Throwable var326) {
                  resource_toThrow = this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var326);
               } finally {
                  throw resource_toThrow;
               }
            }
         }
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$apply$1(final Function0 resource$1, final Function1 f$1, final Using.Releasable evidence$1$1) {
      Using$ var10000 = MODULE$;
      Object resource_resource = resource$1.apply();
      Using$ resource_this = var10000;
      if (resource_resource == null) {
         throw new NullPointerException("null resource");
      } else {
         label157: {
            Throwable resource_toThrow = null;
            boolean var27 = false;

            try {
               var27 = true;
               var10000 = (Using$)f$1.apply(resource_resource);
               var27 = false;
            } catch (Throwable var32) {
               resource_toThrow = var32;
               var10000 = null;
               var27 = false;
            } finally {
               if (var27) {
                  if (resource_toThrow != null) {
                     try {
                        evidence$1$1.release(resource_resource);
                     } catch (Throwable var28) {
                        resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var28);
                     } finally {
                        throw resource_toThrow;
                     }
                  }

                  evidence$1$1.release(resource_resource);
               }
            }

            Object var6 = var10000;
            if (resource_toThrow == null) {
               evidence$1$1.release(resource_resource);
               return var6;
            } else {
               try {
                  evidence$1$1.release(resource_resource);
               } catch (Throwable var30) {
                  resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var30);
               } finally {
                  throw resource_toThrow;
               }
            }
         }
      }
   }

   private static final int score$1(final Throwable t) {
      if (t instanceof VirtualMachineError) {
         return 4;
      } else if (t instanceof LinkageError) {
         return 3;
      } else if (t instanceof InterruptedException ? true : t instanceof ThreadDeath) {
         return 2;
      } else if (t instanceof ControlThrowable) {
         return 0;
      } else {
         return !NonFatal$.MODULE$.apply(t) ? 1 : -1;
      }
   }

   private static final Throwable suppress$1(final Throwable t, final Throwable suppressed) {
      t.addSuppressed(suppressed);
      return t;
   }

   // $FF: synthetic method
   public static final Object $anonfun$resources$2(final Function2 body$1, final Object r1$1, final Object r2) {
      return body$1.apply(r1$1, r2);
   }

   // $FF: synthetic method
   public static final Object $anonfun$resources$1(final Function0 resource2$1, final Function2 body$1, final Using.Releasable evidence$5$1, final Object r1) {
      Using$ var10000 = MODULE$;
      Object resource_resource = resource2$1.apply();
      Using$ resource_this = var10000;
      if (resource_resource == null) {
         throw new NullPointerException("null resource");
      } else {
         label156: {
            Throwable resource_toThrow = null;
            boolean var28 = false;

            try {
               var28 = true;
               var10000 = (Using$)body$1.apply(r1, resource_resource);
               var28 = false;
            } catch (Throwable var33) {
               resource_toThrow = var33;
               var10000 = null;
               var28 = false;
            } finally {
               if (var28) {
                  if (resource_toThrow != null) {
                     try {
                        evidence$5$1.release(resource_resource);
                     } catch (Throwable var29) {
                        resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var29);
                     } finally {
                        throw resource_toThrow;
                     }
                  }

                  evidence$5$1.release(resource_resource);
               }
            }

            Object var7 = var10000;
            if (resource_toThrow == null) {
               evidence$5$1.release(resource_resource);
               return var7;
            } else {
               try {
                  evidence$5$1.release(resource_resource);
               } catch (Throwable var31) {
                  resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var31);
               } finally {
                  throw resource_toThrow;
               }
            }
         }
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$resources$5(final Function3 body$2, final Object r1$2, final Object r2$1, final Object r3) {
      return body$2.apply(r1$2, r2$1, r3);
   }

   // $FF: synthetic method
   public static final Object $anonfun$resources$4(final Function0 resource3$1, final Function3 body$2, final Object r1$2, final Using.Releasable evidence$8$1, final Object r2) {
      Using$ var10000 = MODULE$;
      Object resource_resource = resource3$1.apply();
      Using$ resource_this = var10000;
      if (resource_resource == null) {
         throw new NullPointerException("null resource");
      } else {
         label156: {
            Throwable resource_toThrow = null;
            boolean var29 = false;

            try {
               var29 = true;
               var10000 = (Using$)body$2.apply(r1$2, r2, resource_resource);
               var29 = false;
            } catch (Throwable var34) {
               resource_toThrow = var34;
               var10000 = null;
               var29 = false;
            } finally {
               if (var29) {
                  if (resource_toThrow != null) {
                     try {
                        evidence$8$1.release(resource_resource);
                     } catch (Throwable var30) {
                        resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var30);
                     } finally {
                        throw resource_toThrow;
                     }
                  }

                  evidence$8$1.release(resource_resource);
               }
            }

            Object var8 = var10000;
            if (resource_toThrow == null) {
               evidence$8$1.release(resource_resource);
               return var8;
            } else {
               try {
                  evidence$8$1.release(resource_resource);
               } catch (Throwable var32) {
                  resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var32);
               } finally {
                  throw resource_toThrow;
               }
            }
         }
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$resources$3(final Function0 resource2$2, final Function0 resource3$1, final Function3 body$2, final Using.Releasable evidence$8$1, final Using.Releasable evidence$7$1, final Object r1) {
      Using$ var10000 = MODULE$;
      Object resource_resource = resource2$2.apply();
      Using$ resource_this = var10000;
      if (resource_resource == null) {
         throw new NullPointerException("null resource");
      } else {
         label501: {
            Throwable resource_toThrow = null;
            boolean var54 = false;

            try {
               var54 = true;
               var10000 = MODULE$;
               Object $anonfun$resources$4_resource_resource = resource3$1.apply();
               Using$ $anonfun$resources$4_resource_this = var10000;
               if ($anonfun$resources$4_resource_resource == null) {
                  throw new NullPointerException("null resource");
               }

               Throwable $anonfun$resources$4_resource_toThrow = null;
               boolean var91 = false;

               try {
                  var91 = true;
                  var10000 = (Using$)body$2.apply(r1, resource_resource, $anonfun$resources$4_resource_resource);
                  var91 = false;
               } catch (Throwable var100) {
                  $anonfun$resources$4_resource_toThrow = var100;
                  var10000 = null;
                  var91 = false;
               } finally {
                  if (var91) {
                     if ($anonfun$resources$4_resource_toThrow != null) {
                        try {
                           evidence$8$1.release($anonfun$resources$4_resource_resource);
                        } catch (Throwable var96) {
                           $anonfun$resources$4_resource_toThrow = $anonfun$resources$4_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$4_resource_toThrow, var96);
                        } finally {
                           throw $anonfun$resources$4_resource_toThrow;
                        }
                     }

                     evidence$8$1.release($anonfun$resources$4_resource_resource);
                  }
               }

               Object var15 = var10000;
               if ($anonfun$resources$4_resource_toThrow != null) {
                  try {
                     evidence$8$1.release($anonfun$resources$4_resource_resource);
                  } catch (Throwable var98) {
                     $anonfun$resources$4_resource_toThrow = $anonfun$resources$4_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$4_resource_toThrow, var98);
                  } finally {
                     throw $anonfun$resources$4_resource_toThrow;
                  }
               }

               evidence$8$1.release($anonfun$resources$4_resource_resource);
               var10000 = (Using$)var15;
               Object var104 = null;
               $anonfun$resources$4_resource_resource = null;
               var15 = null;
               var54 = false;
            } catch (Throwable var102) {
               resource_toThrow = var102;
               var10000 = null;
               var54 = false;
            } finally {
               if (var54) {
                  if (resource_toThrow != null) {
                     try {
                        evidence$7$1.release(resource_resource);
                     } catch (Throwable var92) {
                        resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var92);
                     } finally {
                        throw resource_toThrow;
                     }
                  }

                  evidence$7$1.release(resource_resource);
               }
            }

            Object var9 = var10000;
            if (resource_toThrow == null) {
               evidence$7$1.release(resource_resource);
               return var9;
            } else {
               try {
                  evidence$7$1.release(resource_resource);
               } catch (Throwable var94) {
                  resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var94);
               } finally {
                  throw resource_toThrow;
               }
            }
         }
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$resources$9(final Function4 body$3, final Object r1$3, final Object r2$2, final Object r3$1, final Object r4) {
      return body$3.apply(r1$3, r2$2, r3$1, r4);
   }

   // $FF: synthetic method
   public static final Object $anonfun$resources$8(final Function0 resource4$1, final Function4 body$3, final Object r1$3, final Object r2$2, final Using.Releasable evidence$12$1, final Object r3) {
      Using$ var10000 = MODULE$;
      Object resource_resource = resource4$1.apply();
      Using$ resource_this = var10000;
      if (resource_resource == null) {
         throw new NullPointerException("null resource");
      } else {
         label156: {
            Throwable resource_toThrow = null;
            boolean var30 = false;

            try {
               var30 = true;
               var10000 = (Using$)body$3.apply(r1$3, r2$2, r3, resource_resource);
               var30 = false;
            } catch (Throwable var35) {
               resource_toThrow = var35;
               var10000 = null;
               var30 = false;
            } finally {
               if (var30) {
                  if (resource_toThrow != null) {
                     try {
                        evidence$12$1.release(resource_resource);
                     } catch (Throwable var31) {
                        resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var31);
                     } finally {
                        throw resource_toThrow;
                     }
                  }

                  evidence$12$1.release(resource_resource);
               }
            }

            Object var9 = var10000;
            if (resource_toThrow == null) {
               evidence$12$1.release(resource_resource);
               return var9;
            } else {
               try {
                  evidence$12$1.release(resource_resource);
               } catch (Throwable var33) {
                  resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var33);
               } finally {
                  throw resource_toThrow;
               }
            }
         }
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$resources$7(final Function0 resource3$2, final Function0 resource4$1, final Function4 body$3, final Object r1$3, final Using.Releasable evidence$12$1, final Using.Releasable evidence$11$1, final Object r2) {
      Using$ var10000 = MODULE$;
      Object resource_resource = resource3$2.apply();
      Using$ resource_this = var10000;
      if (resource_resource == null) {
         throw new NullPointerException("null resource");
      } else {
         label501: {
            Throwable resource_toThrow = null;
            boolean var55 = false;

            try {
               var55 = true;
               var10000 = MODULE$;
               Object $anonfun$resources$8_resource_resource = resource4$1.apply();
               Using$ $anonfun$resources$8_resource_this = var10000;
               if ($anonfun$resources$8_resource_resource == null) {
                  throw new NullPointerException("null resource");
               }

               Throwable $anonfun$resources$8_resource_toThrow = null;
               boolean var92 = false;

               try {
                  var92 = true;
                  var10000 = (Using$)body$3.apply(r1$3, r2, resource_resource, $anonfun$resources$8_resource_resource);
                  var92 = false;
               } catch (Throwable var101) {
                  $anonfun$resources$8_resource_toThrow = var101;
                  var10000 = null;
                  var92 = false;
               } finally {
                  if (var92) {
                     if ($anonfun$resources$8_resource_toThrow != null) {
                        try {
                           evidence$12$1.release($anonfun$resources$8_resource_resource);
                        } catch (Throwable var97) {
                           $anonfun$resources$8_resource_toThrow = $anonfun$resources$8_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$8_resource_toThrow, var97);
                        } finally {
                           throw $anonfun$resources$8_resource_toThrow;
                        }
                     }

                     evidence$12$1.release($anonfun$resources$8_resource_resource);
                  }
               }

               Object var16 = var10000;
               if ($anonfun$resources$8_resource_toThrow != null) {
                  try {
                     evidence$12$1.release($anonfun$resources$8_resource_resource);
                  } catch (Throwable var99) {
                     $anonfun$resources$8_resource_toThrow = $anonfun$resources$8_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$8_resource_toThrow, var99);
                  } finally {
                     throw $anonfun$resources$8_resource_toThrow;
                  }
               }

               evidence$12$1.release($anonfun$resources$8_resource_resource);
               var10000 = (Using$)var16;
               Object var105 = null;
               $anonfun$resources$8_resource_resource = null;
               var16 = null;
               var55 = false;
            } catch (Throwable var103) {
               resource_toThrow = var103;
               var10000 = null;
               var55 = false;
            } finally {
               if (var55) {
                  if (resource_toThrow != null) {
                     try {
                        evidence$11$1.release(resource_resource);
                     } catch (Throwable var93) {
                        resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var93);
                     } finally {
                        throw resource_toThrow;
                     }
                  }

                  evidence$11$1.release(resource_resource);
               }
            }

            Object var10 = var10000;
            if (resource_toThrow == null) {
               evidence$11$1.release(resource_resource);
               return var10;
            } else {
               try {
                  evidence$11$1.release(resource_resource);
               } catch (Throwable var95) {
                  resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var95);
               } finally {
                  throw resource_toThrow;
               }
            }
         }
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$resources$6(final Function0 resource2$3, final Function0 resource3$2, final Function0 resource4$1, final Function4 body$3, final Using.Releasable evidence$12$1, final Using.Releasable evidence$11$1, final Using.Releasable evidence$10$1, final Object r1) {
      Using$ var10000 = MODULE$;
      Object resource_resource = resource2$3.apply();
      Using$ resource_this = var10000;
      if (resource_resource == null) {
         throw new NullPointerException("null resource");
      } else {
         label1041: {
            Throwable resource_toThrow = null;
            boolean var80 = false;

            try {
               var80 = true;
               var10000 = MODULE$;
               Object $anonfun$resources$7_resource_resource = resource3$2.apply();
               Using$ $anonfun$resources$7_resource_this = var10000;
               if ($anonfun$resources$7_resource_resource == null) {
                  throw new NullPointerException("null resource");
               }

               Throwable $anonfun$resources$7_resource_toThrow = null;
               boolean var135 = false;

               try {
                  var135 = true;
                  var10000 = MODULE$;
                  Object $anonfun$resources$8_resource_resource = resource4$1.apply();
                  Using$ $anonfun$resources$8_resource_this = var10000;
                  if ($anonfun$resources$8_resource_resource == null) {
                     throw new NullPointerException("null resource");
                  }

                  Throwable $anonfun$resources$8_resource_toThrow = null;
                  boolean var190 = false;

                  try {
                     var190 = true;
                     var10000 = (Using$)body$3.apply(r1, resource_resource, $anonfun$resources$7_resource_resource, $anonfun$resources$8_resource_resource);
                     var190 = false;
                  } catch (Throwable var203) {
                     $anonfun$resources$8_resource_toThrow = var203;
                     var10000 = null;
                     var190 = false;
                  } finally {
                     if (var190) {
                        if ($anonfun$resources$8_resource_toThrow != null) {
                           try {
                              evidence$12$1.release($anonfun$resources$8_resource_resource);
                           } catch (Throwable var199) {
                              $anonfun$resources$8_resource_toThrow = $anonfun$resources$8_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$8_resource_toThrow, var199);
                           } finally {
                              throw $anonfun$resources$8_resource_toThrow;
                           }
                        }

                        evidence$12$1.release($anonfun$resources$8_resource_resource);
                     }
                  }

                  Object var23 = var10000;
                  if ($anonfun$resources$8_resource_toThrow != null) {
                     try {
                        evidence$12$1.release($anonfun$resources$8_resource_resource);
                     } catch (Throwable var201) {
                        $anonfun$resources$8_resource_toThrow = $anonfun$resources$8_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$8_resource_toThrow, var201);
                     } finally {
                        throw $anonfun$resources$8_resource_toThrow;
                     }
                  }

                  evidence$12$1.release($anonfun$resources$8_resource_resource);
                  var10000 = (Using$)var23;
                  Object var212 = null;
                  $anonfun$resources$8_resource_resource = null;
                  var23 = null;
                  var135 = false;
               } catch (Throwable var205) {
                  $anonfun$resources$7_resource_toThrow = var205;
                  var10000 = null;
                  var135 = false;
               } finally {
                  if (var135) {
                     if ($anonfun$resources$7_resource_toThrow != null) {
                        try {
                           evidence$11$1.release($anonfun$resources$7_resource_resource);
                        } catch (Throwable var195) {
                           $anonfun$resources$7_resource_toThrow = $anonfun$resources$7_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$7_resource_toThrow, var195);
                        } finally {
                           throw $anonfun$resources$7_resource_toThrow;
                        }
                     }

                     evidence$11$1.release($anonfun$resources$7_resource_resource);
                  }
               }

               Object var17 = var10000;
               if ($anonfun$resources$7_resource_toThrow != null) {
                  try {
                     evidence$11$1.release($anonfun$resources$7_resource_resource);
                  } catch (Throwable var197) {
                     $anonfun$resources$7_resource_toThrow = $anonfun$resources$7_resource_this.scala$util$Using$$preferentiallySuppress($anonfun$resources$7_resource_toThrow, var197);
                  } finally {
                     throw $anonfun$resources$7_resource_toThrow;
                  }
               }

               evidence$11$1.release($anonfun$resources$7_resource_resource);
               var10000 = (Using$)var17;
               Object var209 = null;
               $anonfun$resources$7_resource_resource = null;
               var17 = null;
               var80 = false;
            } catch (Throwable var207) {
               resource_toThrow = var207;
               var10000 = null;
               var80 = false;
            } finally {
               if (var80) {
                  if (resource_toThrow != null) {
                     try {
                        evidence$10$1.release(resource_resource);
                     } catch (Throwable var191) {
                        resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var191);
                     } finally {
                        throw resource_toThrow;
                     }
                  }

                  evidence$10$1.release(resource_resource);
               }
            }

            Object var11 = var10000;
            if (resource_toThrow == null) {
               evidence$10$1.release(resource_resource);
               return var11;
            } else {
               try {
                  evidence$10$1.release(resource_resource);
               } catch (Throwable var193) {
                  resource_toThrow = resource_this.scala$util$Using$$preferentiallySuppress(resource_toThrow, var193);
               } finally {
                  throw resource_toThrow;
               }
            }
         }
      }
   }

   private Using$() {
   }
}
