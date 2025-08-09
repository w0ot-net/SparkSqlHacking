package org.bouncycastle.pqc.legacy.crypto.gmss;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.encoders.Hex;

public class GMSSRootCalc {
   private int heightOfTree;
   private int mdLength;
   private Treehash[] treehash;
   private Vector[] retain;
   private byte[] root;
   private byte[][] AuthPath;
   private int K;
   private Vector tailStack;
   private Vector heightOfNodes;
   private Digest messDigestTree;
   private GMSSDigestProvider digestProvider;
   private int[] index;
   private boolean isInitialized;
   private boolean isFinished;
   private int indexForNextSeed;
   private int heightOfNextSeed;

   public GMSSRootCalc(int var1, int var2, GMSSDigestProvider var3) {
      this.heightOfTree = var1;
      this.digestProvider = var3;
      this.messDigestTree = var3.get();
      this.mdLength = this.messDigestTree.getDigestSize();
      this.K = var2;
      this.index = new int[var1];
      this.AuthPath = new byte[var1][this.mdLength];
      this.root = new byte[this.mdLength];
      this.retain = new Vector[this.K - 1];

      for(int var4 = 0; var4 < var2 - 1; ++var4) {
         this.retain[var4] = new Vector();
      }

   }

   public void initialize(Vector var1) {
      this.treehash = new Treehash[this.heightOfTree - this.K];

      for(int var2 = 0; var2 < this.heightOfTree - this.K; ++var2) {
         this.treehash[var2] = new Treehash(var1, var2, this.digestProvider.get());
      }

      this.index = new int[this.heightOfTree];
      this.AuthPath = new byte[this.heightOfTree][this.mdLength];
      this.root = new byte[this.mdLength];
      this.tailStack = new Vector();
      this.heightOfNodes = new Vector();
      this.isInitialized = true;
      this.isFinished = false;

      for(int var3 = 0; var3 < this.heightOfTree; ++var3) {
         this.index[var3] = -1;
      }

      this.retain = new Vector[this.K - 1];

      for(int var4 = 0; var4 < this.K - 1; ++var4) {
         this.retain[var4] = new Vector();
      }

      this.indexForNextSeed = 3;
      this.heightOfNextSeed = 0;
   }

   public void update(byte[] var1, byte[] var2) {
      if (this.heightOfNextSeed < this.heightOfTree - this.K && this.indexForNextSeed - 2 == this.index[0]) {
         this.initializeTreehashSeed(var1, this.heightOfNextSeed);
         ++this.heightOfNextSeed;
         this.indexForNextSeed *= 2;
      }

      this.update(var2);
   }

   public void update(byte[] var1) {
      if (this.isFinished) {
         System.out.print("Too much updates for Tree!!");
      } else if (!this.isInitialized) {
         System.err.println("GMSSRootCalc not initialized!");
      } else {
         int var10002 = this.index[0]++;
         if (this.index[0] == 1) {
            System.arraycopy(var1, 0, this.AuthPath[0], 0, this.mdLength);
         } else if (this.index[0] == 3 && this.heightOfTree > this.K) {
            this.treehash[0].setFirstNode(var1);
         }

         if ((this.index[0] - 3) % 2 == 0 && this.index[0] >= 3 && this.heightOfTree == this.K) {
            this.retain[0].insertElementAt(var1, 0);
         }

         if (this.index[0] == 0) {
            this.tailStack.addElement(var1);
            this.heightOfNodes.addElement(Integers.valueOf(0));
         } else {
            byte[] var2 = new byte[this.mdLength];
            byte[] var3 = new byte[this.mdLength << 1];
            System.arraycopy(var1, 0, var2, 0, this.mdLength);
            int var4 = 0;

            while(this.tailStack.size() > 0 && var4 == (Integer)this.heightOfNodes.lastElement()) {
               System.arraycopy(this.tailStack.lastElement(), 0, var3, 0, this.mdLength);
               this.tailStack.removeElementAt(this.tailStack.size() - 1);
               this.heightOfNodes.removeElementAt(this.heightOfNodes.size() - 1);
               System.arraycopy(var2, 0, var3, this.mdLength, this.mdLength);
               this.messDigestTree.update(var3, 0, var3.length);
               var2 = new byte[this.messDigestTree.getDigestSize()];
               this.messDigestTree.doFinal(var2, 0);
               ++var4;
               if (var4 < this.heightOfTree) {
                  var10002 = this.index[var4]++;
                  if (this.index[var4] == 1) {
                     System.arraycopy(var2, 0, this.AuthPath[var4], 0, this.mdLength);
                  }

                  if (var4 >= this.heightOfTree - this.K) {
                     if (var4 == 0) {
                        System.out.println("M���P");
                     }

                     if ((this.index[var4] - 3) % 2 == 0 && this.index[var4] >= 3) {
                        this.retain[var4 - (this.heightOfTree - this.K)].insertElementAt(var2, 0);
                     }
                  } else if (this.index[var4] == 3) {
                     this.treehash[var4].setFirstNode(var2);
                  }
               }
            }

            this.tailStack.addElement(var2);
            this.heightOfNodes.addElement(Integers.valueOf(var4));
            if (var4 == this.heightOfTree) {
               this.isFinished = true;
               this.isInitialized = false;
               this.root = (byte[])this.tailStack.lastElement();
            }
         }

      }
   }

   public void initializeTreehashSeed(byte[] var1, int var2) {
      this.treehash[var2].initializeSeed(var1);
   }

   public boolean wasInitialized() {
      return this.isInitialized;
   }

   public boolean wasFinished() {
      return this.isFinished;
   }

   public byte[][] getAuthPath() {
      return GMSSUtils.clone(this.AuthPath);
   }

   public Treehash[] getTreehash() {
      return GMSSUtils.clone(this.treehash);
   }

   public Vector[] getRetain() {
      return GMSSUtils.clone(this.retain);
   }

   public byte[] getRoot() {
      return Arrays.clone(this.root);
   }

   public Vector getStack() {
      Vector var1 = new Vector();
      Enumeration var2 = this.tailStack.elements();

      while(var2.hasMoreElements()) {
         var1.addElement(var2.nextElement());
      }

      return var1;
   }

   public byte[][] getStatByte() {
      int var1;
      if (this.tailStack == null) {
         var1 = 0;
      } else {
         var1 = this.tailStack.size();
      }

      byte[][] var2 = new byte[1 + this.heightOfTree + var1][64];
      var2[0] = this.root;

      for(int var3 = 0; var3 < this.heightOfTree; ++var3) {
         var2[1 + var3] = this.AuthPath[var3];
      }

      for(int var4 = 0; var4 < var1; ++var4) {
         var2[1 + this.heightOfTree + var4] = (byte[])this.tailStack.elementAt(var4);
      }

      return var2;
   }

   public int[] getStatInt() {
      int var1;
      if (this.tailStack == null) {
         var1 = 0;
      } else {
         var1 = this.tailStack.size();
      }

      int[] var2 = new int[8 + this.heightOfTree + var1];
      var2[0] = this.heightOfTree;
      var2[1] = this.mdLength;
      var2[2] = this.K;
      var2[3] = this.indexForNextSeed;
      var2[4] = this.heightOfNextSeed;
      if (this.isFinished) {
         var2[5] = 1;
      } else {
         var2[5] = 0;
      }

      if (this.isInitialized) {
         var2[6] = 1;
      } else {
         var2[6] = 0;
      }

      var2[7] = var1;

      for(int var3 = 0; var3 < this.heightOfTree; ++var3) {
         var2[8 + var3] = this.index[var3];
      }

      for(int var4 = 0; var4 < var1; ++var4) {
         var2[8 + this.heightOfTree + var4] = (Integer)this.heightOfNodes.elementAt(var4);
      }

      return var2;
   }

   public String toString() {
      String var1 = "";
      int var2;
      if (this.tailStack == null) {
         var2 = 0;
      } else {
         var2 = this.tailStack.size();
      }

      for(int var3 = 0; var3 < 8 + this.heightOfTree + var2; ++var3) {
         var1 = var1 + this.getStatInt()[var3] + " ";
      }

      for(int var5 = 0; var5 < 1 + this.heightOfTree + var2; ++var5) {
         var1 = var1 + new String(Hex.encode(this.getStatByte()[var5])) + " ";
      }

      var1 = var1 + "  " + this.digestProvider.get().getDigestSize();
      return var1;
   }
}
