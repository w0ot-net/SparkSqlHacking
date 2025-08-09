package org.bouncycastle.pqc.asn1;

import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.pqc.legacy.crypto.gmss.GMSSLeaf;
import org.bouncycastle.pqc.legacy.crypto.gmss.GMSSParameters;
import org.bouncycastle.pqc.legacy.crypto.gmss.GMSSRootCalc;
import org.bouncycastle.pqc.legacy.crypto.gmss.GMSSRootSig;
import org.bouncycastle.pqc.legacy.crypto.gmss.Treehash;

public class GMSSPrivateKey extends ASN1Object {
   private ASN1Primitive primitive;

   public static GMSSPrivateKey getInstance(Object var0) {
      if (var0 instanceof GMSSPrivateKey) {
         return (GMSSPrivateKey)var0;
      } else {
         return var0 != null ? new GMSSPrivateKey(ASN1Sequence.getInstance(var0)) : null;
      }
   }

   private GMSSPrivateKey(ASN1Sequence var1) {
      ASN1Sequence var2 = (ASN1Sequence)var1.getObjectAt(0);
      int[] var3 = new int[var2.size()];

      for(int var4 = 0; var4 < var2.size(); ++var4) {
         var3[var4] = checkBigIntegerInIntRange(var2.getObjectAt(var4));
      }

      ASN1Sequence var21 = (ASN1Sequence)var1.getObjectAt(1);
      byte[][] var5 = new byte[var21.size()][];

      for(int var6 = 0; var6 < var5.length; ++var6) {
         var5[var6] = ((DEROctetString)var21.getObjectAt(var6)).getOctets();
      }

      ASN1Sequence var22 = (ASN1Sequence)var1.getObjectAt(2);
      byte[][] var7 = new byte[var22.size()][];

      for(int var8 = 0; var8 < var7.length; ++var8) {
         var7[var8] = ((DEROctetString)var22.getObjectAt(var8)).getOctets();
      }

      ASN1Sequence var23 = (ASN1Sequence)var1.getObjectAt(3);
      byte[][][] var10 = new byte[var23.size()][][];

      for(int var11 = 0; var11 < var10.length; ++var11) {
         ASN1Sequence var9 = (ASN1Sequence)var23.getObjectAt(var11);
         var10[var11] = new byte[var9.size()][];

         for(int var12 = 0; var12 < var10[var11].length; ++var12) {
            var10[var11][var12] = ((DEROctetString)var9.getObjectAt(var12)).getOctets();
         }
      }

      ASN1Sequence var24 = (ASN1Sequence)var1.getObjectAt(4);
      byte[][][] var13 = new byte[var24.size()][][];

      for(int var14 = 0; var14 < var13.length; ++var14) {
         ASN1Sequence var25 = (ASN1Sequence)var24.getObjectAt(var14);
         var13[var14] = new byte[var25.size()][];

         for(int var15 = 0; var15 < var13[var14].length; ++var15) {
            var13[var14][var15] = ((DEROctetString)var25.getObjectAt(var15)).getOctets();
         }
      }

      ASN1Sequence var26 = (ASN1Sequence)var1.getObjectAt(5);
      Treehash[][] var20 = new Treehash[var26.size()][];
   }

   public GMSSPrivateKey(int[] var1, byte[][] var2, byte[][] var3, byte[][][] var4, byte[][][] var5, Treehash[][] var6, Treehash[][] var7, Vector[] var8, Vector[] var9, Vector[][] var10, Vector[][] var11, byte[][][] var12, GMSSLeaf[] var13, GMSSLeaf[] var14, GMSSLeaf[] var15, int[] var16, byte[][] var17, GMSSRootCalc[] var18, byte[][] var19, GMSSRootSig[] var20, GMSSParameters var21, AlgorithmIdentifier var22) {
      AlgorithmIdentifier[] var23 = new AlgorithmIdentifier[]{var22};
      this.primitive = this.encode(var1, var2, var3, var4, var5, var12, var6, var7, var8, var9, var10, var11, var13, var14, var15, var16, var17, var18, var19, var20, var21, var23);
   }

   private ASN1Primitive encode(int[] var1, byte[][] var2, byte[][] var3, byte[][][] var4, byte[][][] var5, byte[][][] var6, Treehash[][] var7, Treehash[][] var8, Vector[] var9, Vector[] var10, Vector[][] var11, Vector[][] var12, GMSSLeaf[] var13, GMSSLeaf[] var14, GMSSLeaf[] var15, int[] var16, byte[][] var17, GMSSRootCalc[] var18, byte[][] var19, GMSSRootSig[] var20, GMSSParameters var21, AlgorithmIdentifier[] var22) {
      ASN1EncodableVector var23 = new ASN1EncodableVector();
      ASN1EncodableVector var24 = new ASN1EncodableVector();

      for(int var25 = 0; var25 < var1.length; ++var25) {
         var24.add(new ASN1Integer((long)var1[var25]));
      }

      var23.add(new DERSequence(var24));
      ASN1EncodableVector var72 = new ASN1EncodableVector();

      for(int var26 = 0; var26 < var2.length; ++var26) {
         var72.add(new DEROctetString(var2[var26]));
      }

      var23.add(new DERSequence(var72));
      ASN1EncodableVector var73 = new ASN1EncodableVector();

      for(int var27 = 0; var27 < var3.length; ++var27) {
         var73.add(new DEROctetString(var3[var27]));
      }

      var23.add(new DERSequence(var73));
      ASN1EncodableVector var74 = new ASN1EncodableVector();
      ASN1EncodableVector var28 = new ASN1EncodableVector();

      for(int var29 = 0; var29 < var4.length; ++var29) {
         for(int var30 = 0; var30 < var4[var29].length; ++var30) {
            var74.add(new DEROctetString(var4[var29][var30]));
         }

         var28.add(new DERSequence(var74));
         var74 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var28));
      ASN1EncodableVector var75 = new ASN1EncodableVector();
      ASN1EncodableVector var76 = new ASN1EncodableVector();

      for(int var31 = 0; var31 < var5.length; ++var31) {
         for(int var32 = 0; var32 < var5[var31].length; ++var32) {
            var75.add(new DEROctetString(var5[var31][var32]));
         }

         var76.add(new DERSequence(var75));
         var75 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var76));
      ASN1EncodableVector var77 = new ASN1EncodableVector();
      ASN1EncodableVector var79 = new ASN1EncodableVector();
      ASN1EncodableVector var33 = new ASN1EncodableVector();
      ASN1EncodableVector var34 = new ASN1EncodableVector();
      ASN1EncodableVector var35 = new ASN1EncodableVector();

      for(int var36 = 0; var36 < var7.length; ++var36) {
         for(int var37 = 0; var37 < var7[var36].length; ++var37) {
            var33.add(new DERSequence(var22[0]));
            int var38 = var7[var36][var37].getStatInt()[1];
            var34.add(new DEROctetString(var7[var36][var37].getStatByte()[0]));
            var34.add(new DEROctetString(var7[var36][var37].getStatByte()[1]));
            var34.add(new DEROctetString(var7[var36][var37].getStatByte()[2]));

            for(int var39 = 0; var39 < var38; ++var39) {
               var34.add(new DEROctetString(var7[var36][var37].getStatByte()[3 + var39]));
            }

            var33.add(new DERSequence(var34));
            var34 = new ASN1EncodableVector();
            var35.add(new ASN1Integer((long)var7[var36][var37].getStatInt()[0]));
            var35.add(new ASN1Integer((long)var38));
            var35.add(new ASN1Integer((long)var7[var36][var37].getStatInt()[2]));
            var35.add(new ASN1Integer((long)var7[var36][var37].getStatInt()[3]));
            var35.add(new ASN1Integer((long)var7[var36][var37].getStatInt()[4]));
            var35.add(new ASN1Integer((long)var7[var36][var37].getStatInt()[5]));

            for(int var103 = 0; var103 < var38; ++var103) {
               var35.add(new ASN1Integer((long)var7[var36][var37].getStatInt()[6 + var103]));
            }

            var33.add(new DERSequence(var35));
            var35 = new ASN1EncodableVector();
            var79.add(new DERSequence(var33));
            var33 = new ASN1EncodableVector();
         }

         var77.add(new DERSequence(var79));
         var79 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var77));
      var77 = new ASN1EncodableVector();
      var79 = new ASN1EncodableVector();
      var33 = new ASN1EncodableVector();
      var34 = new ASN1EncodableVector();
      var35 = new ASN1EncodableVector();

      for(int var96 = 0; var96 < var8.length; ++var96) {
         for(int var98 = 0; var98 < var8[var96].length; ++var98) {
            var33.add(new DERSequence(var22[0]));
            int var100 = var8[var96][var98].getStatInt()[1];
            var34.add(new DEROctetString(var8[var96][var98].getStatByte()[0]));
            var34.add(new DEROctetString(var8[var96][var98].getStatByte()[1]));
            var34.add(new DEROctetString(var8[var96][var98].getStatByte()[2]));

            for(int var104 = 0; var104 < var100; ++var104) {
               var34.add(new DEROctetString(var8[var96][var98].getStatByte()[3 + var104]));
            }

            var33.add(new DERSequence(var34));
            var34 = new ASN1EncodableVector();
            var35.add(new ASN1Integer((long)var8[var96][var98].getStatInt()[0]));
            var35.add(new ASN1Integer((long)var100));
            var35.add(new ASN1Integer((long)var8[var96][var98].getStatInt()[2]));
            var35.add(new ASN1Integer((long)var8[var96][var98].getStatInt()[3]));
            var35.add(new ASN1Integer((long)var8[var96][var98].getStatInt()[4]));
            var35.add(new ASN1Integer((long)var8[var96][var98].getStatInt()[5]));

            for(int var105 = 0; var105 < var100; ++var105) {
               var35.add(new ASN1Integer((long)var8[var96][var98].getStatInt()[6 + var105]));
            }

            var33.add(new DERSequence(var35));
            var35 = new ASN1EncodableVector();
            var79.add(new DERSequence(var33));
            var33 = new ASN1EncodableVector();
         }

         var77.add(new DERSequence(new DERSequence(var79)));
         var79 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var77));
      ASN1EncodableVector var97 = new ASN1EncodableVector();
      ASN1EncodableVector var99 = new ASN1EncodableVector();

      for(int var101 = 0; var101 < var6.length; ++var101) {
         for(int var106 = 0; var106 < var6[var101].length; ++var106) {
            var97.add(new DEROctetString(var6[var101][var106]));
         }

         var99.add(new DERSequence(var97));
         var97 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var99));
      ASN1EncodableVector var102 = new ASN1EncodableVector();
      ASN1EncodableVector var107 = new ASN1EncodableVector();

      for(int var40 = 0; var40 < var9.length; ++var40) {
         for(int var41 = 0; var41 < var9[var40].size(); ++var41) {
            var102.add(new DEROctetString((byte[])var9[var40].elementAt(var41)));
         }

         var107.add(new DERSequence(var102));
         var102 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var107));
      ASN1EncodableVector var108 = new ASN1EncodableVector();
      ASN1EncodableVector var109 = new ASN1EncodableVector();

      for(int var42 = 0; var42 < var10.length; ++var42) {
         for(int var43 = 0; var43 < var10[var42].size(); ++var43) {
            var108.add(new DEROctetString((byte[])var10[var42].elementAt(var43)));
         }

         var109.add(new DERSequence(var108));
         var108 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var109));
      ASN1EncodableVector var110 = new ASN1EncodableVector();
      ASN1EncodableVector var112 = new ASN1EncodableVector();
      ASN1EncodableVector var44 = new ASN1EncodableVector();

      for(int var45 = 0; var45 < var11.length; ++var45) {
         for(int var46 = 0; var46 < var11[var45].length; ++var46) {
            for(int var47 = 0; var47 < var11[var45][var46].size(); ++var47) {
               var110.add(new DEROctetString((byte[])var11[var45][var46].elementAt(var47)));
            }

            var112.add(new DERSequence(var110));
            var110 = new ASN1EncodableVector();
         }

         var44.add(new DERSequence(var112));
         var112 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var44));
      ASN1EncodableVector var113 = new ASN1EncodableVector();
      ASN1EncodableVector var114 = new ASN1EncodableVector();
      ASN1EncodableVector var115 = new ASN1EncodableVector();

      for(int var48 = 0; var48 < var12.length; ++var48) {
         for(int var49 = 0; var49 < var12[var48].length; ++var49) {
            for(int var50 = 0; var50 < var12[var48][var49].size(); ++var50) {
               var113.add(new DEROctetString((byte[])var12[var48][var49].elementAt(var50)));
            }

            var114.add(new DERSequence(var113));
            var113 = new ASN1EncodableVector();
         }

         var115.add(new DERSequence(var114));
         var114 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var115));
      ASN1EncodableVector var116 = new ASN1EncodableVector();
      var33 = new ASN1EncodableVector();
      var34 = new ASN1EncodableVector();
      var35 = new ASN1EncodableVector();

      for(int var117 = 0; var117 < var13.length; ++var117) {
         var33.add(new DERSequence(var22[0]));
         byte[][] var119 = var13[var117].getStatByte();
         var34.add(new DEROctetString(var119[0]));
         var34.add(new DEROctetString(var119[1]));
         var34.add(new DEROctetString(var119[2]));
         var34.add(new DEROctetString(var119[3]));
         var33.add(new DERSequence(var34));
         var34 = new ASN1EncodableVector();
         int[] var51 = var13[var117].getStatInt();
         var35.add(new ASN1Integer((long)var51[0]));
         var35.add(new ASN1Integer((long)var51[1]));
         var35.add(new ASN1Integer((long)var51[2]));
         var35.add(new ASN1Integer((long)var51[3]));
         var33.add(new DERSequence(var35));
         var35 = new ASN1EncodableVector();
         var116.add(new DERSequence(var33));
         var33 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var116));
      ASN1EncodableVector var118 = new ASN1EncodableVector();
      var33 = new ASN1EncodableVector();
      var34 = new ASN1EncodableVector();
      var35 = new ASN1EncodableVector();

      for(int var120 = 0; var120 < var14.length; ++var120) {
         var33.add(new DERSequence(var22[0]));
         byte[][] var122 = var14[var120].getStatByte();
         var34.add(new DEROctetString(var122[0]));
         var34.add(new DEROctetString(var122[1]));
         var34.add(new DEROctetString(var122[2]));
         var34.add(new DEROctetString(var122[3]));
         var33.add(new DERSequence(var34));
         var34 = new ASN1EncodableVector();
         int[] var52 = var14[var120].getStatInt();
         var35.add(new ASN1Integer((long)var52[0]));
         var35.add(new ASN1Integer((long)var52[1]));
         var35.add(new ASN1Integer((long)var52[2]));
         var35.add(new ASN1Integer((long)var52[3]));
         var33.add(new DERSequence(var35));
         var35 = new ASN1EncodableVector();
         var118.add(new DERSequence(var33));
         var33 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var118));
      ASN1EncodableVector var121 = new ASN1EncodableVector();
      var33 = new ASN1EncodableVector();
      var34 = new ASN1EncodableVector();
      var35 = new ASN1EncodableVector();

      for(int var123 = 0; var123 < var15.length; ++var123) {
         var33.add(new DERSequence(var22[0]));
         byte[][] var125 = var15[var123].getStatByte();
         var34.add(new DEROctetString(var125[0]));
         var34.add(new DEROctetString(var125[1]));
         var34.add(new DEROctetString(var125[2]));
         var34.add(new DEROctetString(var125[3]));
         var33.add(new DERSequence(var34));
         var34 = new ASN1EncodableVector();
         int[] var53 = var15[var123].getStatInt();
         var35.add(new ASN1Integer((long)var53[0]));
         var35.add(new ASN1Integer((long)var53[1]));
         var35.add(new ASN1Integer((long)var53[2]));
         var35.add(new ASN1Integer((long)var53[3]));
         var33.add(new DERSequence(var35));
         var35 = new ASN1EncodableVector();
         var121.add(new DERSequence(var33));
         var33 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var121));
      ASN1EncodableVector var124 = new ASN1EncodableVector();

      for(int var126 = 0; var126 < var16.length; ++var126) {
         var124.add(new ASN1Integer((long)var16[var126]));
      }

      var23.add(new DERSequence(var124));
      ASN1EncodableVector var127 = new ASN1EncodableVector();

      for(int var128 = 0; var128 < var17.length; ++var128) {
         var127.add(new DEROctetString(var17[var128]));
      }

      var23.add(new DERSequence(var127));
      ASN1EncodableVector var129 = new ASN1EncodableVector();
      ASN1EncodableVector var54 = new ASN1EncodableVector();
      new ASN1EncodableVector();
      ASN1EncodableVector var56 = new ASN1EncodableVector();
      ASN1EncodableVector var57 = new ASN1EncodableVector();
      ASN1EncodableVector var58 = new ASN1EncodableVector();
      ASN1EncodableVector var59 = new ASN1EncodableVector();

      for(int var60 = 0; var60 < var18.length; ++var60) {
         var54.add(new DERSequence(var22[0]));
         new ASN1EncodableVector();
         int var61 = var18[var60].getStatInt()[0];
         int var62 = var18[var60].getStatInt()[7];
         var56.add(new DEROctetString(var18[var60].getStatByte()[0]));

         for(int var63 = 0; var63 < var61; ++var63) {
            var56.add(new DEROctetString(var18[var60].getStatByte()[1 + var63]));
         }

         for(int var135 = 0; var135 < var62; ++var135) {
            var56.add(new DEROctetString(var18[var60].getStatByte()[1 + var61 + var135]));
         }

         var54.add(new DERSequence(var56));
         var56 = new ASN1EncodableVector();
         var57.add(new ASN1Integer((long)var61));
         var57.add(new ASN1Integer((long)var18[var60].getStatInt()[1]));
         var57.add(new ASN1Integer((long)var18[var60].getStatInt()[2]));
         var57.add(new ASN1Integer((long)var18[var60].getStatInt()[3]));
         var57.add(new ASN1Integer((long)var18[var60].getStatInt()[4]));
         var57.add(new ASN1Integer((long)var18[var60].getStatInt()[5]));
         var57.add(new ASN1Integer((long)var18[var60].getStatInt()[6]));
         var57.add(new ASN1Integer((long)var62));

         for(int var136 = 0; var136 < var61; ++var136) {
            var57.add(new ASN1Integer((long)var18[var60].getStatInt()[8 + var136]));
         }

         for(int var137 = 0; var137 < var62; ++var137) {
            var57.add(new ASN1Integer((long)var18[var60].getStatInt()[8 + var61 + var137]));
         }

         var54.add(new DERSequence(var57));
         var57 = new ASN1EncodableVector();
         var33 = new ASN1EncodableVector();
         var34 = new ASN1EncodableVector();
         var35 = new ASN1EncodableVector();
         if (var18[var60].getTreehash() != null) {
            for(int var138 = 0; var138 < var18[var60].getTreehash().length; ++var138) {
               var33.add(new DERSequence(var22[0]));
               var62 = var18[var60].getTreehash()[var138].getStatInt()[1];
               var34.add(new DEROctetString(var18[var60].getTreehash()[var138].getStatByte()[0]));
               var34.add(new DEROctetString(var18[var60].getTreehash()[var138].getStatByte()[1]));
               var34.add(new DEROctetString(var18[var60].getTreehash()[var138].getStatByte()[2]));

               for(int var64 = 0; var64 < var62; ++var64) {
                  var34.add(new DEROctetString(var18[var60].getTreehash()[var138].getStatByte()[3 + var64]));
               }

               var33.add(new DERSequence(var34));
               var34 = new ASN1EncodableVector();
               var35.add(new ASN1Integer((long)var18[var60].getTreehash()[var138].getStatInt()[0]));
               var35.add(new ASN1Integer((long)var62));
               var35.add(new ASN1Integer((long)var18[var60].getTreehash()[var138].getStatInt()[2]));
               var35.add(new ASN1Integer((long)var18[var60].getTreehash()[var138].getStatInt()[3]));
               var35.add(new ASN1Integer((long)var18[var60].getTreehash()[var138].getStatInt()[4]));
               var35.add(new ASN1Integer((long)var18[var60].getTreehash()[var138].getStatInt()[5]));

               for(int var140 = 0; var140 < var62; ++var140) {
                  var35.add(new ASN1Integer((long)var18[var60].getTreehash()[var138].getStatInt()[6 + var140]));
               }

               var33.add(new DERSequence(var35));
               var35 = new ASN1EncodableVector();
               var58.add(new DERSequence(var33));
               var33 = new ASN1EncodableVector();
            }
         }

         var54.add(new DERSequence(var58));
         var58 = new ASN1EncodableVector();
         var110 = new ASN1EncodableVector();
         if (var18[var60].getRetain() != null) {
            for(int var139 = 0; var139 < var18[var60].getRetain().length; ++var139) {
               for(int var141 = 0; var141 < var18[var60].getRetain()[var139].size(); ++var141) {
                  var110.add(new DEROctetString((byte[])var18[var60].getRetain()[var139].elementAt(var141)));
               }

               var59.add(new DERSequence(var110));
               var110 = new ASN1EncodableVector();
            }
         }

         var54.add(new DERSequence(var59));
         var59 = new ASN1EncodableVector();
         var129.add(new DERSequence(var54));
         var54 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var129));
      ASN1EncodableVector var130 = new ASN1EncodableVector();

      for(int var131 = 0; var131 < var19.length; ++var131) {
         var130.add(new DEROctetString(var19[var131]));
      }

      var23.add(new DERSequence(var130));
      ASN1EncodableVector var132 = new ASN1EncodableVector();
      ASN1EncodableVector var134 = new ASN1EncodableVector();
      new ASN1EncodableVector();
      ASN1EncodableVector var142 = new ASN1EncodableVector();
      ASN1EncodableVector var65 = new ASN1EncodableVector();

      for(int var66 = 0; var66 < var20.length; ++var66) {
         var134.add(new DERSequence(var22[0]));
         new ASN1EncodableVector();
         var142.add(new DEROctetString(var20[var66].getStatByte()[0]));
         var142.add(new DEROctetString(var20[var66].getStatByte()[1]));
         var142.add(new DEROctetString(var20[var66].getStatByte()[2]));
         var142.add(new DEROctetString(var20[var66].getStatByte()[3]));
         var142.add(new DEROctetString(var20[var66].getStatByte()[4]));
         var134.add(new DERSequence(var142));
         var142 = new ASN1EncodableVector();
         var65.add(new ASN1Integer((long)var20[var66].getStatInt()[0]));
         var65.add(new ASN1Integer((long)var20[var66].getStatInt()[1]));
         var65.add(new ASN1Integer((long)var20[var66].getStatInt()[2]));
         var65.add(new ASN1Integer((long)var20[var66].getStatInt()[3]));
         var65.add(new ASN1Integer((long)var20[var66].getStatInt()[4]));
         var65.add(new ASN1Integer((long)var20[var66].getStatInt()[5]));
         var65.add(new ASN1Integer((long)var20[var66].getStatInt()[6]));
         var65.add(new ASN1Integer((long)var20[var66].getStatInt()[7]));
         var65.add(new ASN1Integer((long)var20[var66].getStatInt()[8]));
         var134.add(new DERSequence(var65));
         var65 = new ASN1EncodableVector();
         var132.add(new DERSequence(var134));
         var134 = new ASN1EncodableVector();
      }

      var23.add(new DERSequence(var132));
      ASN1EncodableVector var143 = new ASN1EncodableVector();
      ASN1EncodableVector var67 = new ASN1EncodableVector();
      ASN1EncodableVector var68 = new ASN1EncodableVector();
      ASN1EncodableVector var69 = new ASN1EncodableVector();

      for(int var70 = 0; var70 < var21.getHeightOfTrees().length; ++var70) {
         var67.add(new ASN1Integer((long)var21.getHeightOfTrees()[var70]));
         var68.add(new ASN1Integer((long)var21.getWinternitzParameter()[var70]));
         var69.add(new ASN1Integer((long)var21.getK()[var70]));
      }

      var143.add(new ASN1Integer((long)var21.getNumOfLayers()));
      var143.add(new DERSequence(var67));
      var143.add(new DERSequence(var68));
      var143.add(new DERSequence(var69));
      var23.add(new DERSequence(var143));
      ASN1EncodableVector var144 = new ASN1EncodableVector();

      for(int var71 = 0; var71 < var22.length; ++var71) {
         var144.add(var22[var71]);
      }

      var23.add(new DERSequence(var144));
      return new DERSequence(var23);
   }

   private static int checkBigIntegerInIntRange(ASN1Encodable var0) {
      return ((ASN1Integer)var0).intValueExact();
   }

   public ASN1Primitive toASN1Primitive() {
      return this.primitive;
   }
}
