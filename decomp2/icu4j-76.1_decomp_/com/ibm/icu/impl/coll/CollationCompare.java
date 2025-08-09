package com.ibm.icu.impl.coll;

public final class CollationCompare {
   public static int compareUpToQuaternary(CollationIterator left, CollationIterator right, CollationSettings settings) {
      int options = settings.options;
      long variableTop;
      if ((options & 12) == 0) {
         variableTop = 0L;
      } else {
         variableTop = settings.variableTop + 1L;
      }

      boolean anyVariable = false;

      while(true) {
         long ce = left.nextCE();
         long leftPrimary = ce >>> 32;
         if (leftPrimary < variableTop && leftPrimary > 33554432L) {
            anyVariable = true;

            label354:
            while(true) {
               left.setCurrentCE(ce & -4294967296L);

               while(true) {
                  ce = left.nextCE();
                  leftPrimary = ce >>> 32;
                  if (leftPrimary != 0L) {
                     if (leftPrimary >= variableTop || leftPrimary <= 33554432L) {
                        break label354;
                     }
                     break;
                  }

                  left.setCurrentCE(0L);
               }
            }
         }

         if (leftPrimary != 0L) {
            label379:
            do {
               long ce = right.nextCE();
               ce = ce >>> 32;
               if (ce < variableTop && ce > 33554432L) {
                  anyVariable = true;

                  while(true) {
                     right.setCurrentCE(ce & -4294967296L);

                     while(true) {
                        ce = right.nextCE();
                        ce = ce >>> 32;
                        if (ce != 0L) {
                           if (ce >= variableTop || ce <= 33554432L) {
                              continue label379;
                           }
                           break;
                        }

                        right.setCurrentCE(0L);
                     }
                  }
               }
            } while(ce == 0L);

            if (leftPrimary != ce) {
               if (settings.hasReordering()) {
                  leftPrimary = settings.reorder(leftPrimary);
                  ce = settings.reorder(ce);
               }

               return leftPrimary < ce ? -1 : 1;
            }

            if (leftPrimary == 1L) {
               if (CollationSettings.getStrength(options) >= 1) {
                  if ((options & 2048) == 0) {
                     int leftIndex = 0;
                     int rightIndex = 0;

                     while(true) {
                        int leftSecondary = (int)left.getCE(leftIndex++) >>> 16;
                        if (leftSecondary != 0) {
                           int rightSecondary;
                           do {
                              rightSecondary = (int)right.getCE(rightIndex++) >>> 16;
                           } while(rightSecondary == 0);

                           if (leftSecondary != rightSecondary) {
                              return leftSecondary < rightSecondary ? -1 : 1;
                           }

                           if (leftSecondary == 256) {
                              break;
                           }
                        }
                     }
                  } else {
                     int leftStart = 0;
                     int rightStart = 0;

                     while(true) {
                        int leftLimit;
                        for(leftLimit = leftStart; (ce = left.getCE(leftLimit) >>> 32) > 33554432L || ce == 0L; ++leftLimit) {
                        }

                        int rightLimit;
                        for(rightLimit = rightStart; (ce = right.getCE(rightLimit) >>> 32) > 33554432L || ce == 0L; ++rightLimit) {
                        }

                        int leftIndex = leftLimit;
                        int rightIndex = rightLimit;

                        int leftSecondary;
                        do {
                           for(leftSecondary = 0; leftSecondary == 0 && leftIndex > leftStart; leftSecondary = (int)left.getCE(leftIndex) >>> 16) {
                              --leftIndex;
                           }

                           int rightSecondary;
                           for(rightSecondary = 0; rightSecondary == 0 && rightIndex > rightStart; rightSecondary = (int)right.getCE(rightIndex) >>> 16) {
                              --rightIndex;
                           }

                           if (leftSecondary != rightSecondary) {
                              return leftSecondary < rightSecondary ? -1 : 1;
                           }
                        } while(leftSecondary != 0);

                        assert left.getCE(leftLimit) == right.getCE(rightLimit);

                        if (ce == 1L) {
                           break;
                        }

                        leftStart = leftLimit + 1;
                        rightStart = rightLimit + 1;
                     }
                  }
               }

               if ((options & 1024) != 0) {
                  int strength = CollationSettings.getStrength(options);
                  int leftIndex = 0;
                  int rightIndex = 0;

                  int leftLower32;
                  do {
                     int leftCase;
                     int rightCase;
                     if (strength == 0) {
                        while(true) {
                           long ce = left.getCE(leftIndex++);
                           leftCase = (int)ce;
                           if (ce >>> 32 != 0L && leftCase != 0) {
                              leftLower32 = leftCase;
                              leftCase &= 49152;

                              do {
                                 ce = right.getCE(rightIndex++);
                                 rightCase = (int)ce;
                              } while(ce >>> 32 == 0L || rightCase == 0);

                              rightCase &= 49152;
                              break;
                           }
                        }
                     } else {
                        do {
                           leftCase = (int)left.getCE(leftIndex++);
                        } while((leftCase & -65536) == 0);

                        leftLower32 = leftCase;
                        leftCase &= 49152;

                        do {
                           rightCase = (int)right.getCE(rightIndex++);
                        } while((rightCase & -65536) == 0);

                        rightCase &= 49152;
                     }

                     if (leftCase != rightCase) {
                        if ((options & 256) == 0) {
                           return leftCase < rightCase ? -1 : 1;
                        }

                        return leftCase < rightCase ? 1 : -1;
                     }
                  } while(leftLower32 >>> 16 != 256);
               }

               if (CollationSettings.getStrength(options) <= 1) {
                  return 0;
               }

               int tertiaryMask = CollationSettings.getTertiaryMask(options);
               int leftIndex = 0;
               int rightIndex = 0;
               int anyQuaternaries = 0;

               while(true) {
                  int leftLower32 = (int)left.getCE(leftIndex++);
                  anyQuaternaries |= leftLower32;

                  assert (leftLower32 & 16191) != 0 || (leftLower32 & '샀') == 0;

                  int leftTertiary = leftLower32 & tertiaryMask;
                  if (leftTertiary != 0) {
                     int rightLower32;
                     int rightTertiary;
                     do {
                        rightLower32 = (int)right.getCE(rightIndex++);
                        anyQuaternaries |= rightLower32;

                        assert (rightLower32 & 16191) != 0 || (rightLower32 & '샀') == 0;

                        rightTertiary = rightLower32 & tertiaryMask;
                     } while(rightTertiary == 0);

                     if (leftTertiary != rightTertiary) {
                        if (CollationSettings.sortsTertiaryUpperCaseFirst(options)) {
                           if (leftTertiary > 256) {
                              if ((leftLower32 & -65536) != 0) {
                                 leftTertiary ^= 49152;
                              } else {
                                 leftTertiary += 16384;
                              }
                           }

                           if (rightTertiary > 256) {
                              if ((rightLower32 & -65536) != 0) {
                                 rightTertiary ^= 49152;
                              } else {
                                 rightTertiary += 16384;
                              }
                           }
                        }

                        return leftTertiary < rightTertiary ? -1 : 1;
                     }

                     if (leftTertiary == 256) {
                        if (CollationSettings.getStrength(options) <= 2) {
                           return 0;
                        }

                        if (!anyVariable && (anyQuaternaries & 192) == 0) {
                           return 0;
                        }

                        leftIndex = 0;
                        rightIndex = 0;

                        while(true) {
                           long ce = left.getCE(leftIndex++);
                           long leftQuaternary = ce & 65535L;
                           if (leftQuaternary <= 256L) {
                              leftQuaternary = ce >>> 32;
                           } else {
                              leftQuaternary |= 4294967103L;
                           }

                           if (leftQuaternary != 0L) {
                              do {
                                 long ce = right.getCE(rightIndex++);
                                 ce = ce & 65535L;
                                 if (ce <= 256L) {
                                    ce = ce >>> 32;
                                 } else {
                                    ce |= 4294967103L;
                                 }
                              } while(ce == 0L);

                              if (leftQuaternary != ce) {
                                 if (settings.hasReordering()) {
                                    leftQuaternary = settings.reorder(leftQuaternary);
                                    ce = settings.reorder(ce);
                                 }

                                 return leftQuaternary < ce ? -1 : 1;
                              }

                              if (leftQuaternary == 1L) {
                                 return 0;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
