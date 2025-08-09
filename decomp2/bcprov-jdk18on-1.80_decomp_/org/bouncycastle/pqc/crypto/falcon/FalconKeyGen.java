package org.bouncycastle.pqc.crypto.falcon;

class FalconKeyGen {
   FPREngine fpr = new FPREngine();
   FalconSmallPrimeList primes = new FalconSmallPrimeList();
   FalconFFT fft = new FalconFFT();
   FalconCodec codec = new FalconCodec();
   FalconVrfy vrfy = new FalconVrfy();
   private short[] REV10 = new short[]{0, 512, 256, 768, 128, 640, 384, 896, 64, 576, 320, 832, 192, 704, 448, 960, 32, 544, 288, 800, 160, 672, 416, 928, 96, 608, 352, 864, 224, 736, 480, 992, 16, 528, 272, 784, 144, 656, 400, 912, 80, 592, 336, 848, 208, 720, 464, 976, 48, 560, 304, 816, 176, 688, 432, 944, 112, 624, 368, 880, 240, 752, 496, 1008, 8, 520, 264, 776, 136, 648, 392, 904, 72, 584, 328, 840, 200, 712, 456, 968, 40, 552, 296, 808, 168, 680, 424, 936, 104, 616, 360, 872, 232, 744, 488, 1000, 24, 536, 280, 792, 152, 664, 408, 920, 88, 600, 344, 856, 216, 728, 472, 984, 56, 568, 312, 824, 184, 696, 440, 952, 120, 632, 376, 888, 248, 760, 504, 1016, 4, 516, 260, 772, 132, 644, 388, 900, 68, 580, 324, 836, 196, 708, 452, 964, 36, 548, 292, 804, 164, 676, 420, 932, 100, 612, 356, 868, 228, 740, 484, 996, 20, 532, 276, 788, 148, 660, 404, 916, 84, 596, 340, 852, 212, 724, 468, 980, 52, 564, 308, 820, 180, 692, 436, 948, 116, 628, 372, 884, 244, 756, 500, 1012, 12, 524, 268, 780, 140, 652, 396, 908, 76, 588, 332, 844, 204, 716, 460, 972, 44, 556, 300, 812, 172, 684, 428, 940, 108, 620, 364, 876, 236, 748, 492, 1004, 28, 540, 284, 796, 156, 668, 412, 924, 92, 604, 348, 860, 220, 732, 476, 988, 60, 572, 316, 828, 188, 700, 444, 956, 124, 636, 380, 892, 252, 764, 508, 1020, 2, 514, 258, 770, 130, 642, 386, 898, 66, 578, 322, 834, 194, 706, 450, 962, 34, 546, 290, 802, 162, 674, 418, 930, 98, 610, 354, 866, 226, 738, 482, 994, 18, 530, 274, 786, 146, 658, 402, 914, 82, 594, 338, 850, 210, 722, 466, 978, 50, 562, 306, 818, 178, 690, 434, 946, 114, 626, 370, 882, 242, 754, 498, 1010, 10, 522, 266, 778, 138, 650, 394, 906, 74, 586, 330, 842, 202, 714, 458, 970, 42, 554, 298, 810, 170, 682, 426, 938, 106, 618, 362, 874, 234, 746, 490, 1002, 26, 538, 282, 794, 154, 666, 410, 922, 90, 602, 346, 858, 218, 730, 474, 986, 58, 570, 314, 826, 186, 698, 442, 954, 122, 634, 378, 890, 250, 762, 506, 1018, 6, 518, 262, 774, 134, 646, 390, 902, 70, 582, 326, 838, 198, 710, 454, 966, 38, 550, 294, 806, 166, 678, 422, 934, 102, 614, 358, 870, 230, 742, 486, 998, 22, 534, 278, 790, 150, 662, 406, 918, 86, 598, 342, 854, 214, 726, 470, 982, 54, 566, 310, 822, 182, 694, 438, 950, 118, 630, 374, 886, 246, 758, 502, 1014, 14, 526, 270, 782, 142, 654, 398, 910, 78, 590, 334, 846, 206, 718, 462, 974, 46, 558, 302, 814, 174, 686, 430, 942, 110, 622, 366, 878, 238, 750, 494, 1006, 30, 542, 286, 798, 158, 670, 414, 926, 94, 606, 350, 862, 222, 734, 478, 990, 62, 574, 318, 830, 190, 702, 446, 958, 126, 638, 382, 894, 254, 766, 510, 1022, 1, 513, 257, 769, 129, 641, 385, 897, 65, 577, 321, 833, 193, 705, 449, 961, 33, 545, 289, 801, 161, 673, 417, 929, 97, 609, 353, 865, 225, 737, 481, 993, 17, 529, 273, 785, 145, 657, 401, 913, 81, 593, 337, 849, 209, 721, 465, 977, 49, 561, 305, 817, 177, 689, 433, 945, 113, 625, 369, 881, 241, 753, 497, 1009, 9, 521, 265, 777, 137, 649, 393, 905, 73, 585, 329, 841, 201, 713, 457, 969, 41, 553, 297, 809, 169, 681, 425, 937, 105, 617, 361, 873, 233, 745, 489, 1001, 25, 537, 281, 793, 153, 665, 409, 921, 89, 601, 345, 857, 217, 729, 473, 985, 57, 569, 313, 825, 185, 697, 441, 953, 121, 633, 377, 889, 249, 761, 505, 1017, 5, 517, 261, 773, 133, 645, 389, 901, 69, 581, 325, 837, 197, 709, 453, 965, 37, 549, 293, 805, 165, 677, 421, 933, 101, 613, 357, 869, 229, 741, 485, 997, 21, 533, 277, 789, 149, 661, 405, 917, 85, 597, 341, 853, 213, 725, 469, 981, 53, 565, 309, 821, 181, 693, 437, 949, 117, 629, 373, 885, 245, 757, 501, 1013, 13, 525, 269, 781, 141, 653, 397, 909, 77, 589, 333, 845, 205, 717, 461, 973, 45, 557, 301, 813, 173, 685, 429, 941, 109, 621, 365, 877, 237, 749, 493, 1005, 29, 541, 285, 797, 157, 669, 413, 925, 93, 605, 349, 861, 221, 733, 477, 989, 61, 573, 317, 829, 189, 701, 445, 957, 125, 637, 381, 893, 253, 765, 509, 1021, 3, 515, 259, 771, 131, 643, 387, 899, 67, 579, 323, 835, 195, 707, 451, 963, 35, 547, 291, 803, 163, 675, 419, 931, 99, 611, 355, 867, 227, 739, 483, 995, 19, 531, 275, 787, 147, 659, 403, 915, 83, 595, 339, 851, 211, 723, 467, 979, 51, 563, 307, 819, 179, 691, 435, 947, 115, 627, 371, 883, 243, 755, 499, 1011, 11, 523, 267, 779, 139, 651, 395, 907, 75, 587, 331, 843, 203, 715, 459, 971, 43, 555, 299, 811, 171, 683, 427, 939, 107, 619, 363, 875, 235, 747, 491, 1003, 27, 539, 283, 795, 155, 667, 411, 923, 91, 603, 347, 859, 219, 731, 475, 987, 59, 571, 315, 827, 187, 699, 443, 955, 123, 635, 379, 891, 251, 763, 507, 1019, 7, 519, 263, 775, 135, 647, 391, 903, 71, 583, 327, 839, 199, 711, 455, 967, 39, 551, 295, 807, 167, 679, 423, 935, 103, 615, 359, 871, 231, 743, 487, 999, 23, 535, 279, 791, 151, 663, 407, 919, 87, 599, 343, 855, 215, 727, 471, 983, 55, 567, 311, 823, 183, 695, 439, 951, 119, 631, 375, 887, 247, 759, 503, 1015, 15, 527, 271, 783, 143, 655, 399, 911, 79, 591, 335, 847, 207, 719, 463, 975, 47, 559, 303, 815, 175, 687, 431, 943, 111, 623, 367, 879, 239, 751, 495, 1007, 31, 543, 287, 799, 159, 671, 415, 927, 95, 607, 351, 863, 223, 735, 479, 991, 63, 575, 319, 831, 191, 703, 447, 959, 127, 639, 383, 895, 255, 767, 511, 1023};
   final long[] gauss_1024_12289 = new long[]{1283868770400643928L, 6416574995475331444L, 4078260278032692663L, 2353523259288686585L, 1227179971273316331L, 575931623374121527L, 242543240509105209L, 91437049221049666L, 30799446349977173L, 9255276791179340L, 2478152334826140L, 590642893610164L, 125206034929641L, 23590435911403L, 3948334035941L, 586753615614L, 77391054539L, 9056793210L, 940121950L, 86539696L, 7062824L, 510971L, 32764L, 1862L, 94L, 4L, 0L};
   final int[] MAX_BL_SMALL = new int[]{1, 1, 2, 2, 4, 7, 14, 27, 53, 106, 209};
   final int[] MAX_BL_LARGE = new int[]{2, 2, 5, 7, 12, 21, 40, 78, 157, 308};
   final int[] bitlength_avg = new int[]{4, 11, 24, 50, 102, 202, 401, 794, 1577, 3138, 6308};
   final int[] bitlength_std = new int[]{0, 1, 1, 1, 1, 2, 4, 5, 8, 13, 25};
   final int DEPTH_INT_FG = 4;

   private static int mkn(int var0) {
      return 1 << var0;
   }

   int modp_set(int var1, int var2) {
      int var3 = var1 + (var2 & -(var1 >>> 31));
      return var3;
   }

   int modp_norm(int var1, int var2) {
      return var1 - (var2 & (var1 - (var2 + 1 >>> 1) >>> 31) - 1);
   }

   int modp_ninv31(int var1) {
      int var2 = 2 - var1;
      var2 *= 2 - var1 * var2;
      var2 *= 2 - var1 * var2;
      var2 *= 2 - var1 * var2;
      var2 *= 2 - var1 * var2;
      return Integer.MAX_VALUE & -var2;
   }

   int modp_R(int var1) {
      return Integer.MIN_VALUE - var1;
   }

   int modp_add(int var1, int var2, int var3) {
      int var4 = var1 + var2 - var3;
      var4 += var3 & -(var4 >>> 31);
      return var4;
   }

   int modp_sub(int var1, int var2, int var3) {
      int var4 = var1 - var2;
      var4 += var3 & -(var4 >>> 31);
      return var4;
   }

   int modp_montymul(int var1, int var2, int var3, int var4) {
      long var5 = this.toUnsignedLong(var1) * this.toUnsignedLong(var2);
      long var7 = (var5 * (long)var4 & this.toUnsignedLong(Integer.MAX_VALUE)) * (long)var3;
      int var9 = (int)(var5 + var7 >>> 31) - var3;
      var9 += var3 & -(var9 >>> 31);
      return var9;
   }

   int modp_R2(int var1, int var2) {
      int var3 = this.modp_R(var1);
      var3 = this.modp_add(var3, var3, var1);
      var3 = this.modp_montymul(var3, var3, var1, var2);
      var3 = this.modp_montymul(var3, var3, var1, var2);
      var3 = this.modp_montymul(var3, var3, var1, var2);
      var3 = this.modp_montymul(var3, var3, var1, var2);
      var3 = this.modp_montymul(var3, var3, var1, var2);
      var3 = var3 + (var1 & -(var3 & 1)) >>> 1;
      return var3;
   }

   int modp_Rx(int var1, int var2, int var3, int var4) {
      --var1;
      int var6 = var4;
      int var7 = this.modp_R(var2);

      for(int var5 = 0; 1 << var5 <= var1; ++var5) {
         if ((var1 & 1 << var5) != 0) {
            var7 = this.modp_montymul(var7, var6, var2, var3);
         }

         var6 = this.modp_montymul(var6, var6, var2, var3);
      }

      return var7;
   }

   int modp_div(int var1, int var2, int var3, int var4, int var5) {
      int var7 = var3 - 2;
      int var6 = var5;

      for(int var8 = 30; var8 >= 0; --var8) {
         var6 = this.modp_montymul(var6, var6, var3, var4);
         int var9 = this.modp_montymul(var6, var2, var3, var4);
         var6 ^= (var6 ^ var9) & -(var7 >>> var8 & 1);
      }

      var6 = this.modp_montymul(var6, 1, var3, var4);
      return this.modp_montymul(var1, var6, var3, var4);
   }

   void modp_mkgm2(int[] var1, int var2, int[] var3, int var4, int var5, int var6, int var7, int var8) {
      int var10 = mkn(var5);
      int var15 = this.modp_R2(var7, var8);
      var6 = this.modp_montymul(var6, var15, var7, var8);

      for(int var11 = var5; var11 < 10; ++var11) {
         var6 = this.modp_montymul(var6, var6, var7, var8);
      }

      int var12 = this.modp_div(var15, var6, var7, var8, this.modp_R(var7));
      int var18 = 10 - var5;
      int var14;
      int var13 = var14 = this.modp_R(var7);

      for(int var9 = 0; var9 < var10; ++var9) {
         short var16 = this.REV10[var9 << var18];
         var1[var2 + var16] = var13;
         var3[var4 + var16] = var14;
         var13 = this.modp_montymul(var13, var6, var7, var8);
         var14 = this.modp_montymul(var14, var12, var7, var8);
      }

   }

   void modp_NTT2_ext(int[] var1, int var2, int var3, int[] var4, int var5, int var6, int var7, int var8) {
      if (var6 != 0) {
         int var11 = mkn(var6);
         int var9 = var11;

         for(int var10 = 1; var10 < var11; var10 <<= 1) {
            int var12 = var9 >> 1;
            int var13 = 0;

            for(int var14 = 0; var13 < var10; var14 += var9) {
               int var15 = var4[var5 + var10 + var13];
               int var17 = var2 + var14 * var3;
               int var18 = var17 + var12 * var3;

               for(int var16 = 0; var16 < var12; var18 += var3) {
                  int var19 = var1[var17];
                  int var20 = this.modp_montymul(var1[var18], var15, var7, var8);
                  var1[var17] = this.modp_add(var19, var20, var7);
                  var1[var18] = this.modp_sub(var19, var20, var7);
                  ++var16;
                  var17 += var3;
               }

               ++var13;
            }

            var9 = var12;
         }

      }
   }

   void modp_iNTT2_ext(int[] var1, int var2, int var3, int[] var4, int var5, int var6, int var7, int var8) {
      if (var6 != 0) {
         int var11 = mkn(var6);
         int var9 = 1;

         for(int var10 = var11; var10 > 1; var10 >>= 1) {
            int var15 = var10 >> 1;
            int var16 = var9 << 1;
            int var17 = 0;

            for(int var18 = 0; var17 < var15; var18 += var16) {
               int var19 = var4[var5 + var15 + var17];
               int var21 = var2 + var18 * var3;
               int var22 = var21 + var9 * var3;

               for(int var20 = 0; var20 < var9; var22 += var3) {
                  int var23 = var1[var21];
                  int var24 = var1[var22];
                  var1[var21] = this.modp_add(var23, var24, var7);
                  var1[var22] = this.modp_montymul(this.modp_sub(var23, var24, var7), var19, var7, var8);
                  ++var20;
                  var21 += var3;
               }

               ++var17;
            }

            var9 = var16;
         }

         int var13 = 1 << 31 - var6;
         int var12 = 0;

         for(int var14 = var2; var12 < var11; var14 += var3) {
            var1[var14] = this.modp_montymul(var1[var14], var13, var7, var8);
            ++var12;
         }

      }
   }

   void modp_NTT2(int[] var1, int var2, int[] var3, int var4, int var5, int var6, int var7) {
      this.modp_NTT2_ext(var1, var2, 1, var3, var4, var5, var6, var7);
   }

   void modp_iNTT2(int[] var1, int var2, int[] var3, int var4, int var5, int var6, int var7) {
      this.modp_iNTT2_ext(var1, var2, 1, var3, var4, var5, var6, var7);
   }

   void modp_poly_rec_res(int[] var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = 1 << var3 - 1;

      for(int var8 = 0; var8 < var7; ++var8) {
         int var9 = var1[var2 + (var8 << 1) + 0];
         int var10 = var1[var2 + (var8 << 1) + 1];
         var1[var2 + var8] = this.modp_montymul(this.modp_montymul(var9, var10, var4, var5), var6, var4, var5);
      }

   }

   int zint_sub(int[] var1, int var2, int[] var3, int var4, int var5, int var6) {
      int var8 = 0;
      int var9 = -var6;

      for(int var7 = 0; var7 < var5; ++var7) {
         int var10 = var1[var2 + var7];
         int var11 = var10 - var3[var4 + var7] - var8;
         var8 = var11 >>> 31;
         var10 ^= (var11 & Integer.MAX_VALUE ^ var10) & var9;
         var1[var2 + var7] = var10;
      }

      return var8;
   }

   int zint_mul_small(int[] var1, int var2, int var3, int var4) {
      int var6 = 0;

      for(int var5 = 0; var5 < var3; ++var5) {
         long var7 = this.toUnsignedLong(var1[var2 + var5]) * this.toUnsignedLong(var4) + (long)var6;
         var1[var2 + var5] = (int)var7 & Integer.MAX_VALUE;
         var6 = (int)(var7 >> 31);
      }

      return var6;
   }

   int zint_mod_small_unsigned(int[] var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = 0;

      int var11;
      for(int var8 = var3; var8-- > 0; var7 = this.modp_add(var7, var11, var4)) {
         var7 = this.modp_montymul(var7, var6, var4, var5);
         var11 = var1[var2 + var8] - var4;
         var11 += var4 & -(var11 >>> 31);
      }

      return var7;
   }

   int zint_mod_small_signed(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      if (var3 == 0) {
         return 0;
      } else {
         int var8 = this.zint_mod_small_unsigned(var1, var2, var3, var4, var5, var6);
         var8 = this.modp_sub(var8, var7 & -(var1[var2 + var3 - 1] >>> 30), var4);
         return var8;
      }
   }

   void zint_add_mul_small(int[] var1, int var2, int[] var3, int var4, int var5, int var6) {
      int var8 = 0;

      for(int var7 = 0; var7 < var5; ++var7) {
         int var9 = var1[var2 + var7];
         int var10 = var3[var4 + var7];
         long var11 = this.toUnsignedLong(var10) * this.toUnsignedLong(var6) + this.toUnsignedLong(var9) + this.toUnsignedLong(var8);
         var1[var2 + var7] = (int)var11 & Integer.MAX_VALUE;
         var8 = (int)(var11 >>> 31);
      }

      var1[var2 + var5] = var8;
   }

   void zint_norm_zero(int[] var1, int var2, int[] var3, int var4, int var5) {
      int var7 = 0;
      int var8 = 0;

      int var12;
      for(int var6 = var5; var6-- > 0; var7 |= var12 & (var7 & 1) - 1) {
         int var9 = var1[var2 + var6];
         int var10 = var3[var4 + var6] >>> 1 | var8 << 30;
         var8 = var3[var4 + var6] & 1;
         var12 = var10 - var9;
         var12 = -var12 >>> 31 | -(var12 >>> 31);
      }

      this.zint_sub(var1, var2, var3, var4, var5, var7 >>> 31);
   }

   void zint_rebuild_CRT(int[] var1, int var2, int var3, int var4, int var5, FalconSmallPrime[] var6, int var7, int[] var8, int var9) {
      var8[var9 + 0] = var6[0].p;

      for(int var10 = 1; var10 < var3; ++var10) {
         int var12 = var6[var10].p;
         int var14 = var6[var10].s;
         int var13 = this.modp_ninv31(var12);
         int var15 = this.modp_R2(var12, var13);
         int var16 = 0;

         for(int var11 = var2; var16 < var5; var11 += var4) {
            int var17 = var1[var11 + var10];
            int var18 = this.zint_mod_small_unsigned(var1, var11, var10, var12, var13, var15);
            int var19 = this.modp_montymul(var14, this.modp_sub(var17, var18, var12), var12, var13);
            this.zint_add_mul_small(var1, var11, var8, var9, var10, var19);
            ++var16;
         }

         var8[var9 + var10] = this.zint_mul_small(var8, var9, var10, var12);
      }

      if (var7 != 0) {
         int var20 = 0;

         for(int var21 = var2; var20 < var5; var21 += var4) {
            this.zint_norm_zero(var1, var21, var8, var9, var3);
            ++var20;
         }
      }

   }

   void zint_negate(int[] var1, int var2, int var3, int var4) {
      int var6 = var4;
      int var7 = -var4 >>> 1;

      for(int var5 = 0; var5 < var3; ++var5) {
         int var8 = var1[var2 + var5];
         var8 = (var8 ^ var7) + var6;
         var1[var2 + var5] = var8 & Integer.MAX_VALUE;
         var6 = var8 >>> 31;
      }

   }

   int zint_co_reduce(int[] var1, int var2, int[] var3, int var4, int var5, long var6, long var8, long var10, long var12) {
      long var15 = 0L;
      long var17 = 0L;

      for(int var14 = 0; var14 < var5; ++var14) {
         int var21 = var1[var2 + var14];
         int var22 = var3[var4 + var14];
         long var23 = (long)var21 * var6 + (long)var22 * var8 + var15;
         long var25 = (long)var21 * var10 + (long)var22 * var12 + var17;
         if (var14 > 0) {
            var1[var2 + var14 - 1] = (int)var23 & Integer.MAX_VALUE;
            var3[var4 + var14 - 1] = (int)var25 & Integer.MAX_VALUE;
         }

         var15 = var23 >> 31;
         var17 = var25 >> 31;
      }

      var1[var2 + var5 - 1] = (int)var15;
      var3[var4 + var5 - 1] = (int)var17;
      int var19 = (int)(var15 >>> 63);
      int var20 = (int)(var17 >>> 63);
      this.zint_negate(var1, var2, var5, var19);
      this.zint_negate(var3, var4, var5, var20);
      return var19 | var20 << 1;
   }

   void zint_finish_mod(int[] var1, int var2, int var3, int[] var4, int var5, int var6) {
      int var8 = 0;

      for(int var7 = 0; var7 < var3; ++var7) {
         var8 = var1[var2 + var7] - var4[var5 + var7] - var8 >>> 31;
      }

      int var9 = -var6 >>> 1;
      int var10 = -(var6 | 1 - var8);
      var8 = var6;

      for(int var13 = 0; var13 < var3; ++var13) {
         int var11 = var1[var2 + var13];
         int var12 = (var4[var5 + var13] ^ var9) & var10;
         var11 = var11 - var12 - var8;
         var1[var2 + var13] = var11 & Integer.MAX_VALUE;
         var8 = var11 >>> 31;
      }

   }

   void zint_co_reduce_mod(int[] var1, int var2, int[] var3, int var4, int[] var5, int var6, int var7, int var8, long var9, long var11, long var13, long var15) {
      long var18 = 0L;
      long var20 = 0L;
      int var22 = (var1[var2 + 0] * (int)var9 + var3[var4 + 0] * (int)var11) * var8 & Integer.MAX_VALUE;
      int var23 = (var1[var2 + 0] * (int)var13 + var3[var4 + 0] * (int)var15) * var8 & Integer.MAX_VALUE;

      for(int var17 = 0; var17 < var7; ++var17) {
         int var24 = var1[var2 + var17];
         int var25 = var3[var4 + var17];
         long var26 = (long)var24 * var9 + (long)var25 * var11 + (long)var5[var6 + var17] * this.toUnsignedLong(var22) + var18;
         long var28 = (long)var24 * var13 + (long)var25 * var15 + (long)var5[var6 + var17] * this.toUnsignedLong(var23) + var20;
         if (var17 > 0) {
            var1[var2 + var17 - 1] = (int)var26 & Integer.MAX_VALUE;
            var3[var4 + var17 - 1] = (int)var28 & Integer.MAX_VALUE;
         }

         var18 = var26 >> 31;
         var20 = var28 >> 31;
      }

      var1[var2 + var7 - 1] = (int)var18;
      var3[var4 + var7 - 1] = (int)var20;
      this.zint_finish_mod(var1, var2, var7, var5, var6, (int)(var18 >>> 63));
      this.zint_finish_mod(var3, var4, var7, var5, var6, (int)(var20 >>> 63));
   }

   int zint_bezout(int[] var1, int var2, int[] var3, int var4, int[] var5, int var6, int[] var7, int var8, int var9, int[] var10, int var11) {
      if (var9 == 0) {
         return 0;
      } else {
         int var12 = var2;
         int var14 = var4;
         int var13 = var11;
         int var15 = var11 + var9;
         int var16 = var15 + var9;
         int var17 = var16 + var9;
         int var18 = this.modp_ninv31(var5[var6 + 0]);
         int var19 = this.modp_ninv31(var7[var8 + 0]);
         System.arraycopy(var5, var6, var10, var16, var9);
         System.arraycopy(var7, var8, var10, var17, var9);
         var1[var2 + 0] = 1;
         var3[var4 + 0] = 0;

         for(int var23 = 1; var23 < var9; ++var23) {
            var1[var12 + var23] = 0;
            var3[var14 + var23] = 0;
         }

         System.arraycopy(var7, var8, var10, var11, var9);
         System.arraycopy(var5, var6, var10, var15, var9);
         --var10[var15 + 0];

         for(int var20 = 62 * var9 + 30; var20 >= 30; var20 -= 30) {
            int var54 = -1;
            int var24 = -1;
            int var25 = 0;
            int var26 = 0;
            int var27 = 0;
            int var28 = 0;

            int var45;
            int var46;
            for(int var22 = var9; var22-- > 0; var54 &= ((var45 | var46) + Integer.MAX_VALUE >>> 31) - 1) {
               var45 = var10[var16 + var22];
               var46 = var10[var17 + var22];
               var25 ^= (var25 ^ var45) & var54;
               var26 ^= (var26 ^ var45) & var24;
               var27 ^= (var27 ^ var46) & var54;
               var28 ^= (var28 ^ var46) & var24;
               var24 = var54;
            }

            var26 |= var25 & var24;
            var25 &= ~var24;
            var28 |= var27 & var24;
            var27 &= ~var24;
            long var29 = (this.toUnsignedLong(var25) << 31) + this.toUnsignedLong(var26);
            long var31 = (this.toUnsignedLong(var27) << 31) + this.toUnsignedLong(var28);
            int var33 = var10[var16 + 0];
            int var34 = var10[var17 + 0];
            long var35 = 1L;
            long var37 = 0L;
            long var39 = 0L;
            long var41 = 1L;

            for(int var43 = 0; var43 < 31; ++var43) {
               long var51 = var31 - var29;
               var45 = (int)((var51 ^ (var29 ^ var31) & (var29 ^ var51)) >>> 63);
               var46 = var33 >> var43 & 1;
               int var47 = var34 >> var43 & 1;
               int var48 = var46 & var47 & var45;
               int var49 = var46 & var47 & ~var45;
               int var50 = var48 | var46 ^ 1;
               var33 -= var34 & -var48;
               var29 -= var31 & -this.toUnsignedLong(var48);
               var35 -= var39 & -((long)var48);
               var37 -= var41 & -((long)var48);
               var34 -= var33 & -var49;
               var31 -= var29 & -this.toUnsignedLong(var49);
               var39 -= var35 & -((long)var49);
               var41 -= var37 & -((long)var49);
               var33 += var33 & var50 - 1;
               var35 += var35 & (long)var50 - 1L;
               var37 += var37 & (long)var50 - 1L;
               var29 ^= (var29 ^ var29 >> 1) & -this.toUnsignedLong(var50);
               var34 += var34 & -var50;
               var39 += var39 & -((long)var50);
               var41 += var41 & -((long)var50);
               var31 ^= (var31 ^ var31 >> 1) & this.toUnsignedLong(var50) - 1L;
            }

            int var44 = this.zint_co_reduce(var10, var16, var10, var17, var9, var35, var37, var39, var41);
            var35 -= var35 + var35 & -((long)(var44 & 1));
            var37 -= var37 + var37 & -((long)(var44 & 1));
            var39 -= var39 + var39 & -((long)(var44 >>> 1));
            var41 -= var41 + var41 & -((long)(var44 >>> 1));
            this.zint_co_reduce_mod(var1, var12, var10, var13, var7, var8, var9, var19, var35, var37, var39, var41);
            this.zint_co_reduce_mod(var3, var14, var10, var15, var5, var6, var9, var18, var35, var37, var39, var41);
         }

         int var21 = var10[var16 + 0] ^ 1;

         for(int var53 = 1; var53 < var9; ++var53) {
            var21 |= var10[var16 + var53];
         }

         return 1 - ((var21 | -var21) >>> 31) & var5[var6 + 0] & var7[var8 + 0];
      }
   }

   void zint_add_scaled_mul_small(int[] var1, int var2, int var3, int[] var4, int var5, int var6, int var7, int var8, int var9) {
      if (var6 != 0) {
         int var11 = -(var4[var5 + var6 - 1] >>> 30) >>> 1;
         int var12 = 0;
         int var13 = 0;

         for(int var10 = var8; var10 < var3; ++var10) {
            int var14 = var10 - var8;
            int var15 = var14 < var6 ? var4[var5 + var14] : var11;
            int var16 = var15 << var9 & Integer.MAX_VALUE | var12;
            var12 = var15 >>> 31 - var9;
            long var18 = this.toUnsignedLong(var16) * (long)var7 + this.toUnsignedLong(var1[var2 + var10]) + (long)var13;
            var1[var2 + var10] = (int)var18 & Integer.MAX_VALUE;
            int var17 = (int)(var18 >>> 31);
            var13 = var17;
         }

      }
   }

   void zint_sub_scaled(int[] var1, int var2, int var3, int[] var4, int var5, int var6, int var7, int var8) {
      if (var6 != 0) {
         int var10 = -(var4[var5 + var6 - 1] >>> 30) >>> 1;
         int var11 = 0;
         int var12 = 0;

         for(int var9 = var7; var9 < var3; ++var9) {
            int var13 = var9 - var7;
            int var15 = var13 < var6 ? var4[var5 + var13] : var10;
            int var16 = var15 << var8 & Integer.MAX_VALUE | var11;
            var11 = var15 >>> 31 - var8;
            int var14 = var1[var2 + var9] - var16 - var12;
            var1[var2 + var9] = var14 & Integer.MAX_VALUE;
            var12 = var14 >>> 31;
         }

      }
   }

   int zint_one_to_plain(int[] var1, int var2) {
      int var3 = var1[var2 + 0];
      var3 |= (var3 & 1073741824) << 1;
      return var3;
   }

   void poly_big_to_fp(FalconFPR[] var1, int var2, int[] var3, int var4, int var5, int var6, int var7) {
      int var8 = mkn(var7);
      if (var5 == 0) {
         for(int var17 = 0; var17 < var8; ++var17) {
            var1[var2 + var17] = this.fpr.fpr_zero;
         }

      } else {
         for(int var9 = 0; var9 < var8; var4 += var6) {
            int var11 = -(var3[var4 + var5 - 1] >>> 30);
            int var13 = var11 >>> 1;
            int var12 = var11 & 1;
            FalconFPR var14 = this.fpr.fpr_zero;
            FalconFPR var15 = this.fpr.fpr_one;

            for(int var10 = 0; var10 < var5; var15 = this.fpr.fpr_mul(var15, this.fpr.fpr_ptwo31)) {
               int var16 = (var3[var4 + var10] ^ var13) + var12;
               var12 = var16 >>> 31;
               var16 &= Integer.MAX_VALUE;
               var16 -= var16 << 1 & var11;
               var14 = this.fpr.fpr_add(var14, this.fpr.fpr_mul(this.fpr.fpr_of((long)var16), var15));
               ++var10;
            }

            var1[var2 + var9] = var14;
            ++var9;
         }

      }
   }

   int poly_big_to_small(byte[] var1, int var2, int[] var3, int var4, int var5, int var6) {
      int var7 = mkn(var6);

      for(int var8 = 0; var8 < var7; ++var8) {
         int var9 = this.zint_one_to_plain(var3, var4 + var8);
         if (var9 < -var5 || var9 > var5) {
            return 0;
         }

         var1[var2 + var8] = (byte)var9;
      }

      return 1;
   }

   void poly_sub_scaled(int[] var1, int var2, int var3, int var4, int[] var5, int var6, int var7, int var8, int[] var9, int var10, int var11, int var12, int var13) {
      int var14 = mkn(var13);

      for(int var15 = 0; var15 < var14; ++var15) {
         int var16 = -var9[var10 + var15];
         int var18 = var2 + var15 * var4;
         int var19 = var6;

         for(int var17 = 0; var17 < var14; ++var17) {
            this.zint_add_scaled_mul_small(var1, var18, var3, var5, var19, var7, var16, var11, var12);
            if (var15 + var17 == var14 - 1) {
               var18 = var2;
               var16 = -var16;
            } else {
               var18 += var4;
            }

            var19 += var8;
         }
      }

   }

   void poly_sub_scaled_ntt(int[] var1, int var2, int var3, int var4, int[] var5, int var6, int var7, int var8, int[] var9, int var10, int var11, int var12, int var13, int[] var14, int var15) {
      int var22 = mkn(var13);
      int var24 = var7 + 1;
      int var16 = var15;
      int var17 = var15 + mkn(var13);
      int var18 = var17 + mkn(var13);
      int var19 = var18 + var22 * var24;
      FalconSmallPrimeList var10000 = this.primes;
      FalconSmallPrime[] var25 = FalconSmallPrimeList.PRIMES;

      for(int var23 = 0; var23 < var24; ++var23) {
         int var26 = var25[var23].p;
         int var27 = this.modp_ninv31(var26);
         int var28 = this.modp_R2(var26, var27);
         int var29 = this.modp_Rx(var7, var26, var27, var28);
         this.modp_mkgm2(var14, var16, var14, var17, var13, var25[var23].g, var26, var27);

         for(int var30 = 0; var30 < var22; ++var30) {
            var14[var19 + var30] = this.modp_set(var9[var10 + var30], var26);
         }

         this.modp_NTT2(var14, var19, var14, var16, var13, var26, var27);
         int var35 = 0;
         int var21 = var6;

         for(int var20 = var18 + var23; var35 < var22; var20 += var24) {
            var14[var20] = this.zint_mod_small_signed(var5, var21, var7, var26, var27, var28, var29);
            ++var35;
            var21 += var8;
         }

         this.modp_NTT2_ext(var14, var18 + var23, var24, var14, var16, var13, var26, var27);
         var35 = 0;

         for(int var31 = var18 + var23; var35 < var22; var31 += var24) {
            var14[var31] = this.modp_montymul(this.modp_montymul(var14[var19 + var35], var14[var31], var26, var27), var28, var26, var27);
            ++var35;
         }

         this.modp_iNTT2_ext(var14, var18 + var23, var24, var14, var17, var13, var26, var27);
      }

      this.zint_rebuild_CRT(var14, var18, var24, var24, var22, var25, 1, var14, var19);
      int var34 = 0;
      int var32 = var2;

      for(int var33 = var18; var34 < var22; var33 += var24) {
         this.zint_sub_scaled(var1, var32, var3, var14, var33, var24, var11, var12);
         ++var34;
         var32 += var4;
      }

   }

   long get_rng_u64(SHAKE256 var1) {
      byte[] var2 = new byte[8];
      var1.inner_shake256_extract(var2, 0, var2.length);
      return (long)var2[0] & 255L | ((long)var2[1] & 255L) << 8 | ((long)var2[2] & 255L) << 16 | ((long)var2[3] & 255L) << 24 | ((long)var2[4] & 255L) << 32 | ((long)var2[5] & 255L) << 40 | ((long)var2[6] & 255L) << 48 | ((long)var2[7] & 255L) << 56;
   }

   int mkgauss(SHAKE256 var1, int var2) {
      int var4 = 1 << 10 - var2;
      int var5 = 0;

      for(int var3 = 0; var3 < var4; ++var3) {
         long var6 = this.get_rng_u64(var1);
         int var11 = (int)(var6 >>> 63);
         var6 &= Long.MAX_VALUE;
         int var8 = (int)(var6 - this.gauss_1024_12289[0] >>> 63);
         int var9 = 0;
         var6 = this.get_rng_u64(var1);
         var6 &= Long.MAX_VALUE;

         for(int var10 = 1; var10 < this.gauss_1024_12289.length; ++var10) {
            int var12 = (int)(var6 - this.gauss_1024_12289[var10] >>> 63) ^ 1;
            var9 |= var10 & -(var12 & (var8 ^ 1));
            var8 |= var12;
         }

         var9 = (var9 ^ -var11) + var11;
         var5 += var9;
      }

      return var5;
   }

   int poly_small_sqnorm(byte[] var1, int var2, int var3) {
      int var4 = mkn(var3);
      int var6 = 0;
      int var7 = 0;

      for(int var5 = 0; var5 < var4; ++var5) {
         byte var8 = var1[var2 + var5];
         var6 += var8 * var8;
         var7 |= var6;
      }

      return var6 | -(var7 >>> 31);
   }

   void poly_small_to_fp(FalconFPR[] var1, int var2, byte[] var3, int var4, int var5) {
      int var6 = mkn(var5);

      for(int var7 = 0; var7 < var6; ++var7) {
         var1[var2 + var7] = this.fpr.fpr_of((long)var3[var4 + var7]);
      }

   }

   void make_fg_step(int[] var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = 1 << var3;
      int var8 = var7 >> 1;
      int var10 = this.MAX_BL_SMALL[var4];
      int var11 = this.MAX_BL_SMALL[var4 + 1];
      FalconSmallPrimeList var10000 = this.primes;
      FalconSmallPrime[] var19 = FalconSmallPrimeList.PRIMES;
      int var12 = var2;
      int var13 = var2 + var8 * var11;
      int var14 = var13 + var8 * var11;
      int var15 = var14 + var7 * var10;
      int var16 = var15 + var7 * var10;
      int var17 = var16 + var7;
      int var18 = var17 + var7;
      System.arraycopy(var1, var2, var1, var14, 2 * var7 * var10);

      for(int var9 = 0; var9 < var10; ++var9) {
         int var20 = var19[var9].p;
         int var21 = this.modp_ninv31(var20);
         int var22 = this.modp_R2(var20, var21);
         this.modp_mkgm2(var1, var16, var1, var17, var3, var19[var9].g, var20, var21);
         int var23 = 0;

         for(int var24 = var14 + var9; var23 < var7; var24 += var10) {
            var1[var18 + var23] = var1[var24];
            ++var23;
         }

         if (var5 == 0) {
            this.modp_NTT2(var1, var18, var1, var16, var3, var20, var21);
         }

         var23 = 0;

         for(int var36 = var12 + var9; var23 < var8; var36 += var11) {
            int var25 = var1[var18 + (var23 << 1) + 0];
            int var26 = var1[var18 + (var23 << 1) + 1];
            var1[var36] = this.modp_montymul(this.modp_montymul(var25, var26, var20, var21), var22, var20, var21);
            ++var23;
         }

         if (var5 != 0) {
            this.modp_iNTT2_ext(var1, var14 + var9, var10, var1, var17, var3, var20, var21);
         }

         var23 = 0;

         for(int var37 = var15 + var9; var23 < var7; var37 += var10) {
            var1[var18 + var23] = var1[var37];
            ++var23;
         }

         if (var5 == 0) {
            this.modp_NTT2(var1, var18, var1, var16, var3, var20, var21);
         }

         var23 = 0;

         for(int var38 = var13 + var9; var23 < var8; var38 += var11) {
            int var43 = var1[var18 + (var23 << 1) + 0];
            int var48 = var1[var18 + (var23 << 1) + 1];
            var1[var38] = this.modp_montymul(this.modp_montymul(var43, var48, var20, var21), var22, var20, var21);
            ++var23;
         }

         if (var5 != 0) {
            this.modp_iNTT2_ext(var1, var15 + var9, var10, var1, var17, var3, var20, var21);
         }

         if (var6 == 0) {
            this.modp_iNTT2_ext(var1, var12 + var9, var11, var1, var17, var3 - 1, var20, var21);
            this.modp_iNTT2_ext(var1, var13 + var9, var11, var1, var17, var3 - 1, var20, var21);
         }
      }

      this.zint_rebuild_CRT(var1, var14, var10, var10, var7, var19, 1, var1, var16);
      this.zint_rebuild_CRT(var1, var15, var10, var10, var7, var19, 1, var1, var16);

      for(int var28 = var10; var28 < var11; ++var28) {
         int var29 = var19[var28].p;
         int var30 = this.modp_ninv31(var29);
         int var31 = this.modp_R2(var29, var30);
         int var35 = this.modp_Rx(var10, var29, var30, var31);
         this.modp_mkgm2(var1, var16, var1, var17, var3, var19[var28].g, var29, var30);
         int var39 = 0;

         for(int var44 = var14; var39 < var7; var44 += var10) {
            var1[var18 + var39] = this.zint_mod_small_signed(var1, var44, var10, var29, var30, var31, var35);
            ++var39;
         }

         this.modp_NTT2(var1, var18, var1, var16, var3, var29, var30);
         var39 = 0;

         for(int var45 = var12 + var28; var39 < var8; var45 += var11) {
            int var49 = var1[var18 + (var39 << 1) + 0];
            int var27 = var1[var18 + (var39 << 1) + 1];
            var1[var45] = this.modp_montymul(this.modp_montymul(var49, var27, var29, var30), var31, var29, var30);
            ++var39;
         }

         var39 = 0;

         for(int var46 = var15; var39 < var7; var46 += var10) {
            var1[var18 + var39] = this.zint_mod_small_signed(var1, var46, var10, var29, var30, var31, var35);
            ++var39;
         }

         this.modp_NTT2(var1, var18, var1, var16, var3, var29, var30);
         var39 = 0;

         for(int var47 = var13 + var28; var39 < var8; var47 += var11) {
            int var50 = var1[var18 + (var39 << 1) + 0];
            int var51 = var1[var18 + (var39 << 1) + 1];
            var1[var47] = this.modp_montymul(this.modp_montymul(var50, var51, var29, var30), var31, var29, var30);
            ++var39;
         }

         if (var6 == 0) {
            this.modp_iNTT2_ext(var1, var12 + var28, var11, var1, var17, var3 - 1, var29, var30);
            this.modp_iNTT2_ext(var1, var13 + var28, var11, var1, var17, var3 - 1, var29, var30);
         }
      }

   }

   void make_fg(int[] var1, int var2, byte[] var3, int var4, byte[] var5, int var6, int var7, int var8, int var9) {
      int var10 = mkn(var7);
      int var12 = var2;
      int var13 = var2 + var10;
      FalconSmallPrimeList var10000 = this.primes;
      FalconSmallPrime[] var16 = FalconSmallPrimeList.PRIMES;
      int var14 = var16[0].p;

      for(int var11 = 0; var11 < var10; ++var11) {
         var1[var12 + var11] = this.modp_set(var3[var4 + var11], var14);
         var1[var13 + var11] = this.modp_set(var5[var6 + var11], var14);
      }

      if (var8 == 0 && var9 != 0) {
         int var19 = var16[0].p;
         int var20 = this.modp_ninv31(var19);
         int var17 = var13 + var10;
         int var18 = var17 + var10;
         this.modp_mkgm2(var1, var17, var1, var18, var7, var16[0].g, var19, var20);
         this.modp_NTT2(var1, var12, var1, var17, var7, var19, var20);
         this.modp_NTT2(var1, var13, var1, var17, var7, var19, var20);
      } else {
         for(int var15 = 0; var15 < var8; ++var15) {
            this.make_fg_step(var1, var2, var7 - var15, var15, var15 != 0 ? 1 : 0, var15 + 1 >= var8 && var9 == 0 ? 0 : 1);
         }

      }
   }

   int solve_NTRU_deepest(int var1, byte[] var2, int var3, byte[] var4, int var5, int[] var6, int var7) {
      int var8 = this.MAX_BL_SMALL[var1];
      FalconSmallPrimeList var10000 = this.primes;
      FalconSmallPrime[] var15 = FalconSmallPrimeList.PRIMES;
      int var10 = var7 + var8;
      int var11 = var10 + var8;
      int var12 = var11 + var8;
      int var13 = var12 + var8;
      this.make_fg(var6, var11, var2, var3, var4, var5, var1, var1, 0);
      this.zint_rebuild_CRT(var6, var11, var8, var8, 2, var15, 0, var6, var13);
      if (this.zint_bezout(var6, var10, var6, var7, var6, var11, var6, var12, var8, var6, var13) == 0) {
         return 0;
      } else {
         short var14 = 12289;
         return this.zint_mul_small(var6, var7, var8, var14) == 0 && this.zint_mul_small(var6, var10, var8, var14) == 0 ? 1 : 0;
      }
   }

   int solve_NTRU_intermediate(int var1, byte[] var2, int var3, byte[] var4, int var5, int var6, int[] var7, int var8) {
      int var9 = var1 - var6;
      int var10 = 1 << var9;
      int var11 = var10 >> 1;
      int var12 = this.MAX_BL_SMALL[var6];
      int var13 = this.MAX_BL_SMALL[var6 + 1];
      int var14 = this.MAX_BL_LARGE[var6];
      FalconSmallPrimeList var10000 = this.primes;
      FalconSmallPrime[] var38 = FalconSmallPrimeList.PRIMES;
      int var19 = var8 + var13 * var11;
      int var22 = var19 + var13 * var11;
      this.make_fg(var7, var22, var2, var3, var4, var5, var1, var6, 1);
      int var20 = var8;
      int var21 = var8 + var10 * var14;
      int var24 = var21 + var10 * var14;
      System.arraycopy(var7, var22, var7, var24, 2 * var10 * var12);
      var22 = var24;
      int var23 = var24 + var12 * var10;
      var24 = var23 + var12 * var10;
      System.arraycopy(var7, var8, var7, var24, 2 * var11 * var13);
      int var18 = var24;
      var19 = var24 + var11 * var13;

      for(int var17 = 0; var17 < var14; ++var17) {
         int var39 = var38[var17].p;
         int var40 = this.modp_ninv31(var39);
         int var41 = this.modp_R2(var39, var40);
         int var42 = this.modp_Rx(var13, var39, var40, var41);
         int var43 = 0;
         int var44 = var18;
         int var45 = var19;
         int var46 = var20 + var17;

         for(int var47 = var21 + var17; var43 < var11; var47 += var14) {
            var7[var46] = this.zint_mod_small_signed(var7, var44, var13, var39, var40, var41, var42);
            var7[var47] = this.zint_mod_small_signed(var7, var45, var13, var39, var40, var41, var42);
            ++var43;
            var44 += var13;
            var45 += var13;
            var46 += var14;
         }
      }

      for(int var56 = 0; var56 < var14; ++var56) {
         int var71 = var38[var56].p;
         int var75 = this.modp_ninv31(var71);
         int var79 = this.modp_R2(var71, var75);
         if (var56 == var12) {
            this.zint_rebuild_CRT(var7, var22, var12, var12, var10, var38, 1, var7, var24);
            this.zint_rebuild_CRT(var7, var23, var12, var12, var10, var38, 1, var7, var24);
         }

         int var82 = var24 + var10;
         int var84 = var82 + var10;
         int var86 = var84 + var10;
         this.modp_mkgm2(var7, var24, var7, var82, var9, var38[var56].g, var71, var75);
         if (var56 < var12) {
            int var48 = 0;
            int var35 = var22 + var56;

            for(int var36 = var23 + var56; var48 < var10; var36 += var12) {
               var7[var84 + var48] = var7[var35];
               var7[var86 + var48] = var7[var36];
               ++var48;
               var35 += var12;
            }

            this.modp_iNTT2_ext(var7, var22 + var56, var12, var7, var82, var9, var71, var75);
            this.modp_iNTT2_ext(var7, var23 + var56, var12, var7, var82, var9, var71, var75);
         } else {
            int var49 = this.modp_Rx(var12, var71, var75, var79);
            int var91 = 0;
            int var63 = var22;

            for(int var67 = var23; var91 < var10; var67 += var12) {
               var7[var84 + var91] = this.zint_mod_small_signed(var7, var63, var12, var71, var75, var79, var49);
               var7[var86 + var91] = this.zint_mod_small_signed(var7, var67, var12, var71, var75, var79, var49);
               ++var91;
               var63 += var12;
            }

            this.modp_NTT2(var7, var84, var7, var24, var9, var71, var75);
            this.modp_NTT2(var7, var86, var7, var24, var9, var71, var75);
         }

         int var88 = var86 + var10;
         int var90 = var88 + var11;
         int var92 = 0;
         int var64 = var20 + var56;

         for(int var68 = var21 + var56; var92 < var11; var68 += var14) {
            var7[var88 + var92] = var7[var64];
            var7[var90 + var92] = var7[var68];
            ++var92;
            var64 += var14;
         }

         this.modp_NTT2(var7, var88, var7, var24, var9 - 1, var71, var75);
         this.modp_NTT2(var7, var90, var7, var24, var9 - 1, var71, var75);
         var92 = 0;
         var64 = var20 + var56;

         for(int var69 = var21 + var56; var92 < var11; var69 += var14 << 1) {
            int var94 = var7[var84 + (var92 << 1) + 0];
            int var50 = var7[var84 + (var92 << 1) + 1];
            int var51 = var7[var86 + (var92 << 1) + 0];
            int var52 = var7[var86 + (var92 << 1) + 1];
            int var53 = this.modp_montymul(var7[var88 + var92], var79, var71, var75);
            int var54 = this.modp_montymul(var7[var90 + var92], var79, var71, var75);
            var7[var64 + 0] = this.modp_montymul(var52, var53, var71, var75);
            var7[var64 + var14] = this.modp_montymul(var51, var53, var71, var75);
            var7[var69 + 0] = this.modp_montymul(var50, var54, var71, var75);
            var7[var69 + var14] = this.modp_montymul(var94, var54, var71, var75);
            ++var92;
            var64 += var14 << 1;
         }

         this.modp_iNTT2_ext(var7, var20 + var56, var14, var7, var82, var9, var71, var75);
         this.modp_iNTT2_ext(var7, var21 + var56, var14, var7, var82, var9, var71, var75);
      }

      this.zint_rebuild_CRT(var7, var20, var14, var14, var10, var38, 1, var7, var24);
      this.zint_rebuild_CRT(var7, var21, var14, var14, var10, var38, 1, var7, var24);
      FalconFPR[] var25 = new FalconFPR[var10];
      FalconFPR[] var26 = new FalconFPR[var10];
      FalconFPR[] var27 = new FalconFPR[var10];
      FalconFPR[] var28 = new FalconFPR[var10];
      FalconFPR[] var29 = new FalconFPR[var10 >> 1];
      int[] var37 = new int[var10];
      int var15 = var12 > 10 ? 10 : var12;
      this.poly_big_to_fp(var27, 0, var7, var22 + var12 - var15, var15, var12, var9);
      this.poly_big_to_fp(var28, 0, var7, var23 + var12 - var15, var15, var12, var9);
      int var30 = 31 * (var12 - var15);
      int var31 = this.bitlength_avg[var6] - 6 * this.bitlength_std[var6];
      int var32 = this.bitlength_avg[var6] + 6 * this.bitlength_std[var6];
      this.fft.FFT(var27, 0, var9);
      this.fft.FFT(var28, 0, var9);
      this.fft.poly_invnorm2_fft(var29, 0, var27, 0, var28, 0, var9);
      this.fft.poly_adj_fft(var27, 0, var9);
      this.fft.poly_adj_fft(var28, 0, var9);
      int var16 = var14;
      int var33 = 31 * var14;
      int var34 = var33 - var31;

      while(true) {
         var15 = var16 > 10 ? 10 : var16;
         int var72 = 31 * (var16 - var15);
         this.poly_big_to_fp(var25, 0, var7, var20 + var16 - var15, var15, var14, var9);
         this.poly_big_to_fp(var26, 0, var7, var21 + var16 - var15, var15, var14, var9);
         this.fft.FFT(var25, 0, var9);
         this.fft.FFT(var26, 0, var9);
         this.fft.poly_mul_fft(var25, 0, var27, 0, var9);
         this.fft.poly_mul_fft(var26, 0, var28, 0, var9);
         this.fft.poly_add(var26, 0, var25, 0, var9);
         this.fft.poly_mul_autoadj_fft(var26, 0, var29, 0, var9);
         this.fft.iFFT(var26, 0, var9);
         int var76 = var34 - var72 + var30;
         FalconFPR var87;
         if (var76 < 0) {
            var76 = -var76;
            var87 = this.fpr.fpr_two;
         } else {
            var87 = this.fpr.fpr_onehalf;
         }

         FalconFPR var85;
         for(var85 = this.fpr.fpr_one; var76 != 0; var87 = this.fpr.fpr_sqr(var87)) {
            if ((var76 & 1) != 0) {
               var85 = this.fpr.fpr_mul(var85, var87);
            }

            var76 >>= 1;
         }

         for(int var57 = 0; var57 < var10; ++var57) {
            FalconFPR var89 = this.fpr.fpr_mul(var26[var57], var85);
            if (!this.fpr.fpr_lt(this.fpr.fpr_mtwo31m1, var89) || !this.fpr.fpr_lt(var89, this.fpr.fpr_ptwo31m1)) {
               return 0;
            }

            var37[var57] = (int)this.fpr.fpr_rint(var89);
         }

         int var83 = var34 / 31;
         int var81 = var34 % 31;
         if (var6 <= 4) {
            this.poly_sub_scaled_ntt(var7, var20, var16, var14, var7, var22, var12, var12, var37, 0, var83, var81, var9, var7, var24);
            this.poly_sub_scaled_ntt(var7, var21, var16, var14, var7, var23, var12, var12, var37, 0, var83, var81, var9, var7, var24);
         } else {
            this.poly_sub_scaled(var7, var20, var16, var14, var7, var22, var12, var12, var37, 0, var83, var81, var9);
            this.poly_sub_scaled(var7, var21, var16, var14, var7, var23, var12, var12, var37, 0, var83, var81, var9);
         }

         int var80 = var34 + var32 + 10;
         if (var80 < var33) {
            var33 = var80;
            if (var16 * 31 >= var80 + 31) {
               --var16;
            }
         }

         if (var34 <= 0) {
            if (var16 < var12) {
               for(int var58 = 0; var58 < var10; var21 += var14) {
                  var76 = -(var7[var20 + var16 - 1] >>> 30) >>> 1;

                  for(int var73 = var16; var73 < var12; ++var73) {
                     var7[var20 + var73] = var76;
                  }

                  var76 = -(var7[var21 + var16 - 1] >>> 30) >>> 1;

                  for(int var74 = var16; var74 < var12; ++var74) {
                     var7[var21 + var74] = var76;
                  }

                  ++var58;
                  var20 += var14;
               }
            }

            int var59 = 0;
            int var66 = var8;

            for(int var70 = var8; var59 < var10 << 1; var70 += var14) {
               System.arraycopy(var7, var70, var7, var66, var12);
               ++var59;
               var66 += var12;
            }

            return 1;
         }

         var34 -= 25;
         if (var34 < 0) {
            var34 = 0;
         }
      }
   }

   int solve_NTRU_binary_depth1(int var1, byte[] var2, int var3, byte[] var4, int var5, int[] var6, int var7) {
      byte var8 = 1;
      int var10 = 1 << var1;
      int var9 = var1 - var8;
      int var11 = 1 << var9;
      int var12 = var11 >> 1;
      int var13 = this.MAX_BL_SMALL[var8];
      int var14 = this.MAX_BL_SMALL[var8 + 1];
      int var15 = this.MAX_BL_LARGE[var8];
      int var17 = var7;
      int var18 = var7 + var14 * var12;
      int var19 = var18 + var14 * var12;
      int var20 = var19 + var15 * var11;

      for(int var16 = 0; var16 < var15; ++var16) {
         FalconSmallPrimeList var10000 = this.primes;
         int var32 = FalconSmallPrimeList.PRIMES[var16].p;
         int var33 = this.modp_ninv31(var32);
         int var34 = this.modp_R2(var32, var33);
         int var35 = this.modp_Rx(var14, var32, var33, var34);
         int var36 = 0;
         int var37 = var17;
         int var38 = var18;
         int var39 = var19 + var16;

         for(int var40 = var20 + var16; var36 < var12; var40 += var15) {
            var6[var39] = this.zint_mod_small_signed(var6, var37, var14, var32, var33, var34, var35);
            var6[var40] = this.zint_mod_small_signed(var6, var38, var14, var32, var33, var34, var35);
            ++var36;
            var37 += var14;
            var38 += var14;
            var39 += var15;
         }
      }

      System.arraycopy(var6, var19, var6, var7, var15 * var11);
      var19 = var7;
      System.arraycopy(var6, var20, var6, var7 + var15 * var11, var15 * var11);
      var20 = var7 + var15 * var11;
      int var21 = var20 + var15 * var11;
      int var22 = var21 + var13 * var11;
      int var23 = var22 + var13 * var11;

      for(int var49 = 0; var49 < var15; ++var49) {
         FalconSmallPrimeList var73 = this.primes;
         int var61 = FalconSmallPrimeList.PRIMES[var49].p;
         int var63 = this.modp_ninv31(var61);
         int var64 = this.modp_R2(var61, var63);
         int var65 = var23 + var10;
         int var66 = var65 + var11;
         int var67 = var66 + var10;
         FalconSmallPrimeList var10006 = this.primes;
         this.modp_mkgm2(var6, var23, var6, var65, var1, FalconSmallPrimeList.PRIMES[var49].g, var61, var63);

         for(int var42 = 0; var42 < var10; ++var42) {
            var6[var66 + var42] = this.modp_set(var2[var3 + var42], var61);
            var6[var67 + var42] = this.modp_set(var4[var5 + var42], var61);
         }

         this.modp_NTT2(var6, var66, var6, var23, var1, var61, var63);
         this.modp_NTT2(var6, var67, var6, var23, var1, var61, var63);

         for(int var41 = var1; var41 > var9; --var41) {
            this.modp_poly_rec_res(var6, var66, var41, var61, var63, var64);
            this.modp_poly_rec_res(var6, var67, var41, var61, var63, var64);
         }

         if (var8 > 0) {
            System.arraycopy(var6, var65, var6, var23 + var11, var11);
            var65 = var23 + var11;
            System.arraycopy(var6, var66, var6, var65 + var11, var11);
            var66 = var65 + var11;
            System.arraycopy(var6, var67, var6, var66 + var11, var11);
            var67 = var66 + var11;
         }

         int var68 = var67 + var11;
         int var69 = var68 + var12;
         int var70 = 0;
         int var30 = var19 + var49;

         for(int var31 = var20 + var49; var70 < var12; var31 += var15) {
            var6[var68 + var70] = var6[var30];
            var6[var69 + var70] = var6[var31];
            ++var70;
            var30 += var15;
         }

         this.modp_NTT2(var6, var68, var6, var23, var9 - 1, var61, var63);
         this.modp_NTT2(var6, var69, var6, var23, var9 - 1, var61, var63);
         var70 = 0;
         var30 = var19 + var49;

         for(int var59 = var20 + var49; var70 < var12; var59 += var15 << 1) {
            int var43 = var6[var66 + (var70 << 1) + 0];
            int var44 = var6[var66 + (var70 << 1) + 1];
            int var45 = var6[var67 + (var70 << 1) + 0];
            int var46 = var6[var67 + (var70 << 1) + 1];
            int var47 = this.modp_montymul(var6[var68 + var70], var64, var61, var63);
            int var48 = this.modp_montymul(var6[var69 + var70], var64, var61, var63);
            var6[var30 + 0] = this.modp_montymul(var46, var47, var61, var63);
            var6[var30 + var15] = this.modp_montymul(var45, var47, var61, var63);
            var6[var59 + 0] = this.modp_montymul(var44, var48, var61, var63);
            var6[var59 + var15] = this.modp_montymul(var43, var48, var61, var63);
            ++var70;
            var30 += var15 << 1;
         }

         this.modp_iNTT2_ext(var6, var19 + var49, var15, var6, var65, var9, var61, var63);
         this.modp_iNTT2_ext(var6, var20 + var49, var15, var6, var65, var9, var61, var63);
         if (var49 < var13) {
            this.modp_iNTT2(var6, var66, var6, var65, var9, var61, var63);
            this.modp_iNTT2(var6, var67, var6, var65, var9, var61, var63);
            var70 = 0;
            var30 = var21 + var49;

            for(int var60 = var22 + var49; var70 < var11; var60 += var13) {
               var6[var30] = var6[var66 + var70];
               var6[var60] = var6[var67 + var70];
               ++var70;
               var30 += var13;
            }
         }
      }

      int var10005 = var11 << 1;
      FalconSmallPrimeList var75 = this.primes;
      this.zint_rebuild_CRT(var6, var19, var15, var15, var10005, FalconSmallPrimeList.PRIMES, 1, var6, var23);
      var10005 = var11 << 1;
      var75 = this.primes;
      this.zint_rebuild_CRT(var6, var21, var13, var13, var10005, FalconSmallPrimeList.PRIMES, 1, var6, var23);
      FalconFPR[] var24 = new FalconFPR[var11];
      FalconFPR[] var25 = new FalconFPR[var11];
      this.poly_big_to_fp(var24, 0, var6, var19, var15, var15, var9);
      this.poly_big_to_fp(var25, 0, var6, var20, var15, var15, var9);
      System.arraycopy(var6, var21, var6, var7, 2 * var13 * var11);
      var22 = var7 + var13 * var11;
      FalconFPR[] var26 = new FalconFPR[var11];
      FalconFPR[] var27 = new FalconFPR[var11];
      this.poly_big_to_fp(var26, 0, var6, var7, var13, var13, var9);
      this.poly_big_to_fp(var27, 0, var6, var22, var13, var13, var9);
      this.fft.FFT(var24, 0, var9);
      this.fft.FFT(var25, 0, var9);
      this.fft.FFT(var26, 0, var9);
      this.fft.FFT(var27, 0, var9);
      FalconFPR[] var28 = new FalconFPR[var11];
      FalconFPR[] var29 = new FalconFPR[var11 >> 1];
      this.fft.poly_add_muladj_fft(var28, 0, var24, 0, var25, 0, var26, 0, var27, 0, var9);
      this.fft.poly_invnorm2_fft(var29, 0, var26, 0, var27, 0, var9);
      this.fft.poly_mul_autoadj_fft(var28, 0, var29, 0, var9);
      this.fft.iFFT(var28, 0, var9);

      for(int var50 = 0; var50 < var11; ++var50) {
         FalconFPR var62 = var28[var50];
         if (!this.fpr.fpr_lt(var62, this.fpr.fpr_ptwo63m1) || !this.fpr.fpr_lt(this.fpr.fpr_mtwo63m1, var62)) {
            return 0;
         }

         var28[var50] = this.fpr.fpr_of(this.fpr.fpr_rint(var62));
      }

      this.fft.FFT(var28, 0, var9);
      this.fft.poly_mul_fft(var26, 0, var28, 0, var9);
      this.fft.poly_mul_fft(var27, 0, var28, 0, var9);
      this.fft.poly_sub(var24, 0, var26, 0, var9);
      this.fft.poly_sub(var25, 0, var27, 0, var9);
      this.fft.iFFT(var24, 0, var9);
      this.fft.iFFT(var25, 0, var9);
      var19 = var7;
      var20 = var7 + var11;

      for(int var51 = 0; var51 < var11; ++var51) {
         var6[var19 + var51] = (int)this.fpr.fpr_rint(var24[var51]);
         var6[var20 + var51] = (int)this.fpr.fpr_rint(var25[var51]);
      }

      return 1;
   }

   int solve_NTRU_binary_depth0(int var1, byte[] var2, int var3, byte[] var4, int var5, int[] var6, int var7) {
      int var8 = 1 << var1;
      int var9 = var8 >> 1;
      FalconSmallPrimeList var10000 = this.primes;
      int var11 = FalconSmallPrimeList.PRIMES[0].p;
      int var12 = this.modp_ninv31(var11);
      int var13 = this.modp_R2(var11, var12);
      int var14 = var7;
      int var15 = var7 + var9;
      int var23 = var15 + var9;
      int var24 = var23 + var8;
      int var21 = var24 + var8;
      int var22 = var21 + var8;
      FalconSmallPrimeList var10006 = this.primes;
      this.modp_mkgm2(var6, var21, var6, var22, var1, FalconSmallPrimeList.PRIMES[0].g, var11, var12);

      for(int var10 = 0; var10 < var9; ++var10) {
         var6[var14 + var10] = this.modp_set(this.zint_one_to_plain(var6, var14 + var10), var11);
         var6[var15 + var10] = this.modp_set(this.zint_one_to_plain(var6, var15 + var10), var11);
      }

      this.modp_NTT2(var6, var14, var6, var21, var1 - 1, var11, var12);
      this.modp_NTT2(var6, var15, var6, var21, var1 - 1, var11, var12);

      for(int var34 = 0; var34 < var8; ++var34) {
         var6[var23 + var34] = this.modp_set(var2[var3 + var34], var11);
         var6[var24 + var34] = this.modp_set(var4[var5 + var34], var11);
      }

      this.modp_NTT2(var6, var23, var6, var21, var1, var11, var12);
      this.modp_NTT2(var6, var24, var6, var21, var1, var11, var12);

      for(int var35 = 0; var35 < var8; var35 += 2) {
         int var28 = var6[var23 + var35 + 0];
         int var29 = var6[var23 + var35 + 1];
         int var30 = var6[var24 + var35 + 0];
         int var31 = var6[var24 + var35 + 1];
         int var32 = this.modp_montymul(var6[var14 + (var35 >> 1)], var13, var11, var12);
         int var33 = this.modp_montymul(var6[var15 + (var35 >> 1)], var13, var11, var12);
         var6[var23 + var35 + 0] = this.modp_montymul(var31, var32, var11, var12);
         var6[var23 + var35 + 1] = this.modp_montymul(var30, var32, var11, var12);
         var6[var24 + var35 + 0] = this.modp_montymul(var29, var33, var11, var12);
         var6[var24 + var35 + 1] = this.modp_montymul(var28, var33, var11, var12);
      }

      this.modp_iNTT2(var6, var23, var6, var22, var1, var11, var12);
      this.modp_iNTT2(var6, var24, var6, var22, var1, var11, var12);
      var15 = var14 + var8;
      int var16 = var15 + var8;
      System.arraycopy(var6, var23, var6, var14, 2 * var8);
      int var17 = var16 + var8;
      int var18 = var17 + var8;
      int var19 = var18 + var8;
      int var20 = var19 + var8;
      var10006 = this.primes;
      this.modp_mkgm2(var6, var16, var6, var17, var1, FalconSmallPrimeList.PRIMES[0].g, var11, var12);
      this.modp_NTT2(var6, var14, var6, var16, var1, var11, var12);
      this.modp_NTT2(var6, var15, var6, var16, var1, var11, var12);
      var6[var19 + 0] = var6[var20 + 0] = this.modp_set(var2[var3 + 0], var11);

      for(int var36 = 1; var36 < var8; ++var36) {
         var6[var19 + var36] = this.modp_set(var2[var3 + var36], var11);
         var6[var20 + var8 - var36] = this.modp_set(-var2[var3 + var36], var11);
      }

      this.modp_NTT2(var6, var19, var6, var16, var1, var11, var12);
      this.modp_NTT2(var6, var20, var6, var16, var1, var11, var12);

      for(int var37 = 0; var37 < var8; ++var37) {
         int var53 = this.modp_montymul(var6[var20 + var37], var13, var11, var12);
         var6[var17 + var37] = this.modp_montymul(var53, var6[var14 + var37], var11, var12);
         var6[var18 + var37] = this.modp_montymul(var53, var6[var19 + var37], var11, var12);
      }

      var6[var19 + 0] = var6[var20 + 0] = this.modp_set(var4[var5 + 0], var11);

      for(int var38 = 1; var38 < var8; ++var38) {
         var6[var19 + var38] = this.modp_set(var4[var5 + var38], var11);
         var6[var20 + var8 - var38] = this.modp_set(-var4[var5 + var38], var11);
      }

      this.modp_NTT2(var6, var19, var6, var16, var1, var11, var12);
      this.modp_NTT2(var6, var20, var6, var16, var1, var11, var12);

      for(int var39 = 0; var39 < var8; ++var39) {
         int var54 = this.modp_montymul(var6[var20 + var39], var13, var11, var12);
         var6[var17 + var39] = this.modp_add(var6[var17 + var39], this.modp_montymul(var54, var6[var15 + var39], var11, var12), var11);
         var6[var18 + var39] = this.modp_add(var6[var18 + var39], this.modp_montymul(var54, var6[var19 + var39], var11, var12), var11);
      }

      var10006 = this.primes;
      this.modp_mkgm2(var6, var16, var6, var19, var1, FalconSmallPrimeList.PRIMES[0].g, var11, var12);
      this.modp_iNTT2(var6, var17, var6, var19, var1, var11, var12);
      this.modp_iNTT2(var6, var18, var6, var19, var1, var11, var12);

      for(int var40 = 0; var40 < var8; ++var40) {
         var6[var16 + var40] = this.modp_norm(var6[var17 + var40], var11);
         var6[var17 + var40] = this.modp_norm(var6[var18 + var40], var11);
      }

      FalconFPR[] var55 = new FalconFPR[3 * var8];
      byte var25 = 0;
      int var26 = var25 + var8;
      int var27 = var26 + var8;

      for(int var41 = 0; var41 < var8; ++var41) {
         var55[var27 + var41] = this.fpr.fpr_of((long)var6[var17 + var41]);
      }

      this.fft.FFT(var55, var27, var1);
      System.arraycopy(var55, var27, var55, var26, var9);
      var27 = var26 + var9;

      for(int var42 = 0; var42 < var8; ++var42) {
         var55[var27 + var42] = this.fpr.fpr_of((long)var6[var16 + var42]);
      }

      this.fft.FFT(var55, var27, var1);
      this.fft.poly_div_autoadj_fft(var55, var27, var55, var26, var1);
      this.fft.iFFT(var55, var27, var1);

      for(int var43 = 0; var43 < var8; ++var43) {
         var6[var16 + var43] = this.modp_set((int)this.fpr.fpr_rint(var55[var27 + var43]), var11);
      }

      var17 = var16 + var8;
      var18 = var17 + var8;
      var19 = var18 + var8;
      var20 = var19 + var8;
      var10006 = this.primes;
      this.modp_mkgm2(var6, var17, var6, var18, var1, FalconSmallPrimeList.PRIMES[0].g, var11, var12);

      for(int var44 = 0; var44 < var8; ++var44) {
         var6[var19 + var44] = this.modp_set(var2[var3 + var44], var11);
         var6[var20 + var44] = this.modp_set(var4[var5 + var44], var11);
      }

      this.modp_NTT2(var6, var16, var6, var17, var1, var11, var12);
      this.modp_NTT2(var6, var19, var6, var17, var1, var11, var12);
      this.modp_NTT2(var6, var20, var6, var17, var1, var11, var12);

      for(int var45 = 0; var45 < var8; ++var45) {
         int var56 = this.modp_montymul(var6[var16 + var45], var13, var11, var12);
         var6[var14 + var45] = this.modp_sub(var6[var14 + var45], this.modp_montymul(var56, var6[var19 + var45], var11, var12), var11);
         var6[var15 + var45] = this.modp_sub(var6[var15 + var45], this.modp_montymul(var56, var6[var20 + var45], var11, var12), var11);
      }

      this.modp_iNTT2(var6, var14, var6, var18, var1, var11, var12);
      this.modp_iNTT2(var6, var15, var6, var18, var1, var11, var12);

      for(int var46 = 0; var46 < var8; ++var46) {
         var6[var14 + var46] = this.modp_norm(var6[var14 + var46], var11);
         var6[var15 + var46] = this.modp_norm(var6[var15 + var46], var11);
      }

      return 1;
   }

   int solve_NTRU(int var1, byte[] var2, int var3, byte[] var4, int var5, byte[] var6, int var7, byte[] var8, int var9, int var10, int[] var11, int var12) {
      int var13 = mkn(var1);
      if (this.solve_NTRU_deepest(var1, var6, var7, var8, var9, var11, var12) == 0) {
         return 0;
      } else {
         if (var1 <= 2) {
            int var24 = var1;

            while(var24-- > 0) {
               if (this.solve_NTRU_intermediate(var1, var6, var7, var8, var9, var24, var11, var12) == 0) {
                  return 0;
               }
            }
         } else {
            int var27 = var1;

            while(var27-- > 2) {
               if (this.solve_NTRU_intermediate(var1, var6, var7, var8, var9, var27, var11, var12) == 0) {
                  return 0;
               }
            }

            if (this.solve_NTRU_binary_depth1(var1, var6, var7, var8, var9, var11, var12) == 0) {
               return 0;
            }

            if (this.solve_NTRU_binary_depth0(var1, var6, var7, var8, var9, var11, var12) == 0) {
               return 0;
            }
         }

         if (var4 == null) {
            var5 = 0;
            var4 = new byte[var13];
         }

         if (this.poly_big_to_small(var2, var3, var11, var12, var10, var1) != 0 && this.poly_big_to_small(var4, var5, var11, var12 + var13, var10, var1) != 0) {
            int var18 = var12;
            int var15 = var12 + var13;
            int var16 = var15 + var13;
            int var17 = var16 + var13;
            int var19 = var17 + var13;
            FalconSmallPrimeList var30 = this.primes;
            FalconSmallPrime[] var23 = FalconSmallPrimeList.PRIMES;
            int var20 = var23[0].p;
            int var21 = this.modp_ninv31(var20);
            this.modp_mkgm2(var11, var19, var11, var12, var1, var23[0].g, var20, var21);

            for(int var14 = 0; var14 < var13; ++var14) {
               var11[var18 + var14] = this.modp_set(var4[var5 + var14], var20);
            }

            for(int var25 = 0; var25 < var13; ++var25) {
               var11[var15 + var25] = this.modp_set(var6[var7 + var25], var20);
               var11[var16 + var25] = this.modp_set(var8[var9 + var25], var20);
               var11[var17 + var25] = this.modp_set(var2[var3 + var25], var20);
            }

            this.modp_NTT2(var11, var15, var11, var19, var1, var20, var21);
            this.modp_NTT2(var11, var16, var11, var19, var1, var20, var21);
            this.modp_NTT2(var11, var17, var11, var19, var1, var20, var21);
            this.modp_NTT2(var11, var18, var11, var19, var1, var20, var21);
            int var22 = this.modp_montymul(12289, 1, var20, var21);

            for(int var26 = 0; var26 < var13; ++var26) {
               int var28 = this.modp_sub(this.modp_montymul(var11[var15 + var26], var11[var18 + var26], var20, var21), this.modp_montymul(var11[var16 + var26], var11[var17 + var26], var20, var21), var20);
               if (var28 != var22) {
                  return 0;
               }
            }

            return 1;
         } else {
            return 0;
         }
      }
   }

   void poly_small_mkgauss(SHAKE256 var1, byte[] var2, int var3, int var4) {
      int var5 = mkn(var4);
      int var7 = 0;

      for(int var6 = 0; var6 < var5; ++var6) {
         int var8;
         while(true) {
            var8 = this.mkgauss(var1, var4);
            if (var8 >= -127 && var8 <= 127) {
               if (var6 != var5 - 1) {
                  var7 ^= var8 & 1;
                  break;
               }

               if ((var7 ^ var8 & 1) != 0) {
                  break;
               }
            }
         }

         var2[var3 + var6] = (byte)var8;
      }

   }

   void keygen(SHAKE256 var1, byte[] var2, int var3, byte[] var4, int var5, byte[] var6, int var7, byte[] var8, int var9, short[] var10, int var11, int var12) {
      int var13 = mkn(var12);
      SHAKE256 var21 = var1;

      while(true) {
         FalconFPR[] var18 = new FalconFPR[3 * var13];
         this.poly_small_mkgauss(var21, var2, var3, var12);
         this.poly_small_mkgauss(var21, var4, var5, var12);
         int var29 = 1 << this.codec.max_fg_bits[var12] - 1;

         for(int var14 = 0; var14 < var13; ++var14) {
            if (var2[var3 + var14] >= var29 || var2[var3 + var14] <= -var29 || var4[var5 + var14] >= var29 || var4[var5 + var14] <= -var29) {
               var29 = -1;
               break;
            }
         }

         if (var29 >= 0) {
            int var26 = this.poly_small_sqnorm(var2, var3, var12);
            int var27 = this.poly_small_sqnorm(var4, var5, var12);
            int var28 = var26 + var27 | -((var26 | var27) >>> 31);
            if (((long)var28 & 4294967295L) < 16823L) {
               byte var22 = 0;
               int var23 = var22 + var13;
               int var24 = var23 + var13;
               this.poly_small_to_fp(var18, var22, var2, var3, var12);
               this.poly_small_to_fp(var18, var23, var4, var5, var12);
               this.fft.FFT(var18, var22, var12);
               this.fft.FFT(var18, var23, var12);
               this.fft.poly_invnorm2_fft(var18, var24, var18, var22, var18, var23, var12);
               this.fft.poly_adj_fft(var18, var22, var12);
               this.fft.poly_adj_fft(var18, var23, var12);
               this.fft.poly_mulconst(var18, var22, this.fpr.fpr_q, var12);
               this.fft.poly_mulconst(var18, var23, this.fpr.fpr_q, var12);
               this.fft.poly_mul_autoadj_fft(var18, var22, var18, var24, var12);
               this.fft.poly_mul_autoadj_fft(var18, var23, var18, var24, var12);
               this.fft.iFFT(var18, var22, var12);
               this.fft.iFFT(var18, var23, var12);
               FalconFPR var25 = this.fpr.fpr_zero;

               for(int var30 = 0; var30 < var13; ++var30) {
                  var25 = this.fpr.fpr_add(var25, this.fpr.fpr_sqr(var18[var22 + var30]));
                  var25 = this.fpr.fpr_add(var25, this.fpr.fpr_sqr(var18[var23 + var30]));
               }

               if (this.fpr.fpr_lt(var25, this.fpr.fpr_bnorm_max)) {
                  short[] var17 = new short[2 * var13];
                  int var19;
                  int var20;
                  if (var10 == null) {
                     var19 = 0;
                     var10 = var17;
                     var20 = var19 + var13;
                  } else {
                     var19 = var11;
                     var20 = 0;
                  }

                  if (this.vrfy.compute_public(var10, var19, var2, var3, var4, var5, var12, var17, var20) != 0) {
                     int[] var15 = var12 > 2 ? new int[28 * var13] : new int[28 * var13 * 3];
                     var29 = (1 << this.codec.max_FG_bits[var12] - 1) - 1;
                     if (this.solve_NTRU(var12, var6, var7, var8, var9, var2, var3, var4, var5, var29, var15, 0) != 0) {
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private long toUnsignedLong(int var1) {
      return (long)var1 & 4294967295L;
   }
}
