package org.codehaus.janino.samples;

import java.io.FileReader;
import java.io.IOException;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.Java;
import org.codehaus.janino.Parser;
import org.codehaus.janino.Scanner;
import org.codehaus.janino.util.AbstractTraverser;

public class DeclarationCounter extends AbstractTraverser {
   private int classDeclarationCount;
   private int interfaceDeclarationCount;
   private int fieldCount;
   private int localVariableCount;

   public static void main(String[] args) throws CompileException, IOException {
      DeclarationCounter dc = new DeclarationCounter();

      for(String fileName : args) {
         FileReader r = new FileReader(fileName);

         Java.AbstractCompilationUnit acu;
         try {
            acu = (new Parser(new Scanner(fileName, r))).parseAbstractCompilationUnit();
         } finally {
            r.close();
         }

         dc.visitAbstractCompilationUnit(acu);
      }

      System.out.println("Class declarations:     " + dc.classDeclarationCount);
      System.out.println("Interface declarations: " + dc.interfaceDeclarationCount);
      System.out.println("Fields:                 " + dc.fieldCount);
      System.out.println("Local variables:        " + dc.localVariableCount);
   }

   public void traverseClassDeclaration(Java.AbstractClassDeclaration cd) {
      ++this.classDeclarationCount;
      super.traverseClassDeclaration(cd);
   }

   public void traverseInterfaceDeclaration(Java.InterfaceDeclaration id) {
      ++this.interfaceDeclarationCount;
      super.traverseInterfaceDeclaration(id);
   }

   public void traverseFieldDeclaration(Java.FieldDeclaration fd) {
      this.fieldCount += fd.variableDeclarators.length;
      super.traverseFieldDeclaration(fd);
   }

   public void traverseLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement lvds) {
      this.localVariableCount += lvds.variableDeclarators.length;
      super.traverseLocalVariableDeclarationStatement(lvds);
   }
}
