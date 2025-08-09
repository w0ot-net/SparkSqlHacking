package org.stringtemplate.v4.gui;

import java.util.List;
import java.util.Map;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.antlr.runtime.tree.CommonTree;
import org.stringtemplate.v4.InstanceScope;
import org.stringtemplate.v4.Interpreter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.debug.AddAttributeEvent;

public class JTreeScopeStackModel implements TreeModel {
   CommonTree root = new StringTree("Scope stack:");

   public JTreeScopeStackModel(InstanceScope scope) {
      for(InstanceScope s : Interpreter.getScopeStack(scope, true)) {
         StringTree templateNode = new StringTree(s.st.getName());
         this.root.addChild(templateNode);
         this.addAttributeDescriptions(s.st, templateNode);
      }

   }

   public void addAttributeDescriptions(ST st, StringTree node) {
      Map<String, Object> attrs = st.getAttributes();
      if (attrs != null) {
         for(String a : attrs.keySet()) {
            String descr = null;
            if (st.debugState != null && st.debugState.addAttrEvents != null) {
               List<AddAttributeEvent> events = (List)st.debugState.addAttrEvents.get(a);
               StringBuilder locations = new StringBuilder();
               int i = 0;
               if (events != null) {
                  for(AddAttributeEvent ae : events) {
                     if (i > 0) {
                        locations.append(", ");
                     }

                     locations.append(ae.getFileName() + ":" + ae.getLine());
                     ++i;
                  }
               }

               if (locations.length() > 0) {
                  descr = a + " = " + attrs.get(a) + " @ " + locations.toString();
               } else {
                  descr = a + " = " + attrs.get(a);
               }
            } else {
               descr = a + " = " + attrs.get(a);
            }

            node.addChild(new StringTree(descr));
         }

      }
   }

   public Object getRoot() {
      return this.root;
   }

   public Object getChild(Object parent, int i) {
      StringTree t = (StringTree)parent;
      return t.getChild(i);
   }

   public int getChildCount(Object parent) {
      StringTree t = (StringTree)parent;
      return t.getChildCount();
   }

   public boolean isLeaf(Object node) {
      return this.getChildCount(node) == 0;
   }

   public int getIndexOfChild(Object parent, Object child) {
      StringTree c = (StringTree)child;
      return c.getChildIndex();
   }

   public void valueForPathChanged(TreePath treePath, Object o) {
   }

   public void addTreeModelListener(TreeModelListener treeModelListener) {
   }

   public void removeTreeModelListener(TreeModelListener treeModelListener) {
   }

   public static class StringTree extends CommonTree {
      String text;

      public StringTree(String text) {
         this.text = text;
      }

      public boolean isNil() {
         return this.text == null;
      }

      public String toString() {
         return !this.isNil() ? this.text.toString() : "nil";
      }
   }
}
