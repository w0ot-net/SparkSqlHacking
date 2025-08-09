package org.apache.ivy.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.apache.ivy.core.IvyPatternHelper;

public class Configurator {
   private FileResolver fileResolver;
   private Map typedefs;
   private Map macrodefs;
   private Stack objectStack;
   private static final List TRUE_VALUES = Arrays.asList("true", "yes", "on");

   public Configurator() {
      this.fileResolver = FileResolver.DEFAULT;
      this.typedefs = new HashMap();
      this.macrodefs = new HashMap();
      this.objectStack = new Stack();
   }

   public void typeDef(String name, String className) throws ClassNotFoundException {
      this.typeDef(name, Class.forName(className));
   }

   public void typeDef(String name, Class clazz) {
      this.typedefs.put(name, clazz);
   }

   public void setRoot(Object root) {
      if (root == null) {
         throw new NullPointerException();
      } else {
         this.objectStack.clear();
         this.setCurrent(root, (String)null);
      }
   }

   public void clear() {
      this.objectStack.clear();
   }

   private void setCurrent(Object object, String name) {
      this.objectStack.push(new ObjectDescriptor(object, name));
   }

   public Object startCreateChild(String name) {
      if (this.objectStack.isEmpty()) {
         throw new IllegalStateException("set root before creating child");
      } else {
         ObjectDescriptor parentOD = (ObjectDescriptor)this.objectStack.peek();
         Object parent = parentOD.getObject();
         if (parent instanceof MacroDef && !"attribute".equals(name) && !"element".equals(name)) {
            MacroRecord record = ((MacroDef)parent).recordCreateChild(name);
            this.setCurrent(record, name);
            return record;
         } else if (parent instanceof Macro) {
            MacroRecord record = ((Macro)parent).recordCreateChild(name);
            this.setCurrent(record, name);
            return record;
         } else if (parent instanceof MacroRecord) {
            MacroRecord record = ((MacroRecord)parent).recordChild(name);
            this.setCurrent(record, name);
            return record;
         } else {
            Object child = null;
            MacroDef macrodef = (MacroDef)this.macrodefs.get(name);
            if (macrodef != null) {
               Macro macro = macrodef.createMacro();
               this.setCurrent(macro, name);
               return macro;
            } else {
               Class<?> childClass = (Class)this.typedefs.get(name);
               Method addChild = null;

               try {
                  if (childClass != null) {
                     return this.addChild(parentOD, childClass, name, (Object)null);
                  }

                  addChild = parentOD.getCreateMethod(name);
                  if (addChild != null) {
                     child = addChild.invoke(parent);
                     this.setCurrent(child, name);
                     return child;
                  }

                  addChild = parentOD.getAddMethod(name);
                  if (addChild != null) {
                     childClass = addChild.getParameterTypes()[0];
                     child = childClass.newInstance();
                     addChild.invoke(parent, child);
                     this.setCurrent(child, name);
                     return child;
                  }

                  addChild = parentOD.getAddConfiguredMethod(name);
                  if (addChild != null) {
                     childClass = addChild.getParameterTypes()[0];
                     if (Map.class == childClass) {
                        child = new HashMap();
                     } else {
                        child = childClass.newInstance();
                     }

                     this.setCurrent(child, name);
                     return child;
                  }
               } catch (InstantiationException var9) {
                  throw new IllegalArgumentException("no default constructor on " + childClass + " for adding " + name + " on " + parent.getClass());
               } catch (Exception ex) {
                  throw new IllegalArgumentException("bad method found for " + name + " on " + parent.getClass(), ex);
               }

               throw new IllegalArgumentException("no appropriate method found for adding " + name + " on " + parent.getClass());
            }
         }
      }
   }

   public void addChild(String name, Object child) {
      if (this.objectStack.isEmpty()) {
         throw new IllegalStateException("set root before creating child");
      } else {
         ObjectDescriptor parentOD = (ObjectDescriptor)this.objectStack.peek();

         try {
            this.addChild(parentOD, child.getClass(), name, child);
         } catch (InstantiationException var5) {
            throw new IllegalArgumentException("no default constructor on " + child.getClass() + " for adding " + name + " on " + parentOD.getObject().getClass());
         } catch (Exception ex) {
            throw new IllegalArgumentException("bad method found for " + name + " on " + parentOD.getObject().getClass(), ex);
         }
      }
   }

   private Object addChild(ObjectDescriptor parentOD, Class childClass, String name, Object child) throws InstantiationException, IllegalAccessException, InvocationTargetException {
      Object parent = parentOD.getObject();
      if (parent instanceof MacroRecord) {
         MacroRecord record = (MacroRecord)parent;
         MacroRecord recordChild = record.recordChild(name, child);
         this.setCurrent(recordChild, name);
         return recordChild;
      } else {
         Method addChild = parentOD.getAddMethod(childClass);
         if (addChild != null) {
            if (child == null) {
               child = childClass.newInstance();
            }

            addChild.invoke(parent, child);
            this.setCurrent(child, name);
            return child;
         } else {
            addChild = parentOD.getAddConfiguredMethod(childClass);
            if (addChild != null) {
               if (child == null) {
                  if (Map.class == childClass) {
                     child = new HashMap();
                  } else {
                     child = childClass.newInstance();
                  }
               }

               this.setCurrent(child, name);
               return child;
            } else {
               throw new IllegalArgumentException("no appropriate method found for adding " + name + " on " + parent.getClass());
            }
         }
      }
   }

   public boolean isTopLevelMacroRecord() {
      if (this.objectStack.isEmpty()) {
         return false;
      } else {
         ObjectDescriptor od = (ObjectDescriptor)this.objectStack.peek();
         return od.getObject() instanceof MacroDef;
      }
   }

   public void setAttribute(String attributeName, String value) {
      if (this.objectStack.isEmpty()) {
         throw new IllegalStateException("set root before setting attribute");
      } else {
         ObjectDescriptor od = (ObjectDescriptor)this.objectStack.peek();
         if (od.getObject() instanceof Macro) {
            ((Macro)od.getObject()).defineAttribute(attributeName, value);
         } else if (od.getObject() instanceof MacroRecord) {
            ((MacroRecord)od.getObject()).recordAttribute(attributeName, value);
         } else {
            Method m = od.getSetMethod(attributeName);
            if (m == null) {
               if (od.getObject() instanceof Map) {
                  ((Map)od.getObject()).put(attributeName, value);
               } else {
                  throw new IllegalArgumentException("no set method found for " + attributeName + " on " + od.getObject().getClass());
               }
            } else {
               Object convertedValue = null;
               Class<?> paramClass = m.getParameterTypes()[0];

               try {
                  if (paramClass.equals(String.class)) {
                     convertedValue = value;
                  } else if (!paramClass.equals(Boolean.class) && !paramClass.equals(Boolean.TYPE)) {
                     if (!paramClass.equals(Character.class) && !paramClass.equals(Character.TYPE)) {
                        if (!paramClass.equals(Short.class) && !paramClass.equals(Short.TYPE)) {
                           if (!paramClass.equals(Integer.class) && !paramClass.equals(Integer.TYPE)) {
                              if (!paramClass.equals(Long.class) && !paramClass.equals(Long.TYPE)) {
                                 if (paramClass.equals(Class.class)) {
                                    convertedValue = Class.forName(value);
                                 } else if (paramClass.equals(File.class)) {
                                    convertedValue = this.fileResolver.resolveFile(value, od.getObjectName() + "." + attributeName);
                                 } else {
                                    convertedValue = paramClass.getConstructor(String.class).newInstance(value);
                                 }
                              } else {
                                 convertedValue = Long.valueOf(value);
                              }
                           } else {
                              convertedValue = Integer.valueOf(value);
                           }
                        } else {
                           convertedValue = Short.valueOf(value);
                        }
                     } else {
                        convertedValue = value.length() > 0 ? value.charAt(0) : ' ';
                     }
                  } else {
                     convertedValue = TRUE_VALUES.contains(value);
                  }
               } catch (Exception ex) {
                  throw new IllegalArgumentException("impossible to convert " + value + " to " + paramClass + " for setting " + attributeName + " on " + od.getObject().getClass() + ": " + ex.getMessage(), ex);
               }

               try {
                  m.invoke(od.getObject(), convertedValue);
               } catch (Exception ex) {
                  throw new IllegalArgumentException("impossible to set " + attributeName + " to " + convertedValue + " on " + od.getObject().getClass(), ex);
               }
            }
         }
      }
   }

   public void addText(String text) {
      if (this.objectStack.isEmpty()) {
         throw new IllegalStateException("set root before adding text");
      } else {
         ObjectDescriptor od = (ObjectDescriptor)this.objectStack.peek();

         try {
            od.getObject().getClass().getMethod("addText", String.class).invoke(od.getObject(), text);
         } catch (Exception ex) {
            throw new IllegalArgumentException("impossible to add text on " + od.getObject().getClass(), ex);
         }
      }
   }

   public Object endCreateChild() {
      if (this.objectStack.isEmpty()) {
         throw new IllegalStateException("set root before ending child");
      } else {
         ObjectDescriptor od = (ObjectDescriptor)this.objectStack.pop();
         if (this.objectStack.isEmpty()) {
            this.objectStack.push(od);
            throw new IllegalStateException("cannot end root");
         } else if (od.getObject() instanceof Macro) {
            return ((Macro)od.getObject()).play(this);
         } else {
            ObjectDescriptor parentOD = (ObjectDescriptor)this.objectStack.peek();
            String name = od.getObjectName();
            Class<?> childClass = (Class)this.typedefs.get(name);
            Method m = childClass == null ? parentOD.getAddConfiguredMethod(name) : parentOD.getAddConfiguredMethod(childClass);

            try {
               if (m != null) {
                  m.invoke(parentOD.getObject(), od.getObject());
               }

               return od.getObject();
            } catch (Exception ex) {
               throw new IllegalArgumentException("impossible to add configured child for " + name + " on " + parentOD.getObject().getClass() + ": " + StringUtils.getErrorMessage(ex), ex);
            }
         }
      }
   }

   public Object getCurrent() {
      return this.objectStack.isEmpty() ? null : ((ObjectDescriptor)this.objectStack.peek()).getObject();
   }

   public int getDepth() {
      return this.objectStack.size();
   }

   public MacroDef startMacroDef(String macroName) {
      MacroDef macroDef = new MacroDef(macroName);
      this.setCurrent(macroDef, macroName);
      return macroDef;
   }

   public void addMacroAttribute(String attName, String attDefaultValue) {
      ((MacroDef)this.getCurrent()).addAttribute(attName, attDefaultValue);
   }

   public void addMacroElement(String elementName, boolean optional) {
      ((MacroDef)this.getCurrent()).addElement(elementName, optional);
   }

   public void endMacroDef() {
      this.addConfiguredMacrodef((MacroDef)this.getCurrent());
      this.objectStack.pop();
   }

   public void addConfiguredMacrodef(MacroDef macrodef) {
      this.macrodefs.put(macrodef.getName(), macrodef);
   }

   public Class getTypeDef(String name) {
      return (Class)this.typedefs.get(name);
   }

   public FileResolver getFileResolver() {
      return this.fileResolver;
   }

   public void setFileResolver(FileResolver fileResolver) {
      Checks.checkNotNull(fileResolver, "fileResolver");
      this.fileResolver = fileResolver;
   }

   public static class Macro {
      private MacroDef macrodef;
      private Map attValues = new HashMap();
      private Map macroRecords = new HashMap();

      public Macro(MacroDef def) {
         this.macrodef = def;
      }

      public void defineAttribute(String attributeName, String value) {
         if (this.macrodef.getAttribute(attributeName) == null) {
            throw new IllegalArgumentException("undeclared attribute " + attributeName + " on macro " + this.macrodef.getName());
         } else {
            this.attValues.put(attributeName, value);
         }
      }

      public MacroRecord recordCreateChild(String name) {
         MacroRecord macroRecord = new MacroRecord(name);
         List<MacroRecord> records = (List)this.macroRecords.get(name);
         if (records == null) {
            records = new ArrayList();
            this.macroRecords.put(name, records);
         }

         records.add(macroRecord);
         return macroRecord;
      }

      public Object play(Configurator conf) {
         return this.macrodef.play(conf, this.attValues, this.macroRecords);
      }
   }

   public static class Attribute {
      private String name;
      private String defaultValue;

      public String getDefault() {
         return this.defaultValue;
      }

      public void setDefault(String default1) {
         this.defaultValue = default1;
      }

      public String getName() {
         return this.name;
      }

      public void setName(String name) {
         this.name = name;
      }
   }

   public static class Element {
      private String name;
      private boolean optional = false;

      public String getName() {
         return this.name;
      }

      public void setName(String name) {
         this.name = name;
      }

      public boolean isOptional() {
         return this.optional;
      }

      public void setOptional(boolean optional) {
         this.optional = optional;
      }
   }

   public static class MacroRecord {
      private String name;
      private Map attributes = new LinkedHashMap();
      private List children = new ArrayList();
      private Object object;

      public MacroRecord(String name) {
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public void recordAttribute(String name, String value) {
         this.attributes.put(name, value);
      }

      public MacroRecord recordChild(String name) {
         MacroRecord child = new MacroRecord(name);
         this.children.add(child);
         return child;
      }

      public MacroRecord recordChild(String name, Object object) {
         MacroRecord child = this.recordChild(name);
         child.object = object;
         return child;
      }

      public Map getAttributes() {
         return this.attributes;
      }

      public List getChildren() {
         return this.children;
      }

      public Object getObject() {
         return this.object;
      }
   }

   public static class MacroDef {
      private String name;
      private Map attributes = new HashMap();
      private Map elements = new HashMap();
      private MacroRecord macroRecord;

      public MacroDef(String macroName) {
         this.name = macroName;
      }

      public Attribute getAttribute(String attributeName) {
         return (Attribute)this.attributes.get(attributeName);
      }

      public Object play(Configurator conf, Map attValues, Map macroRecords) {
         for(Attribute att : this.attributes.values()) {
            String val = (String)attValues.get(att.getName());
            if (val == null) {
               if (att.getDefault() == null) {
                  throw new IllegalArgumentException("attribute " + att.getName() + " is required in " + this.getName());
               }

               attValues.put(att.getName(), att.getDefault());
            }
         }

         return this.play(conf, this.macroRecord, attValues, macroRecords);
      }

      private Object play(Configurator conf, MacroRecord macroRecord, Map attValues, Map childrenRecords) {
         if (macroRecord.getObject() != null) {
            conf.addChild(macroRecord.getName(), macroRecord.getObject());
            conf.endCreateChild();
            return macroRecord.getObject();
         } else {
            conf.startCreateChild(macroRecord.getName());

            for(Map.Entry attribute : macroRecord.getAttributes().entrySet()) {
               conf.setAttribute((String)attribute.getKey(), this.replaceParam((String)attribute.getValue(), attValues));
            }

            for(MacroRecord child : macroRecord.getChildren()) {
               Element elt = (Element)this.elements.get(child.getName());
               if (elt != null) {
                  List<MacroRecord> elements = (List)childrenRecords.get(child.getName());
                  if (elements != null) {
                     for(MacroRecord element : elements) {
                        for(MacroRecord r : element.getChildren()) {
                           this.play(conf, r, attValues, Collections.emptyMap());
                        }
                     }
                  } else if (!elt.isOptional()) {
                     throw new IllegalArgumentException("non optional element is not specified: " + elt.getName() + " in macro " + this.getName());
                  }
               } else {
                  this.play(conf, child, attValues, childrenRecords);
               }
            }

            return conf.endCreateChild();
         }
      }

      private String replaceParam(String string, Map attValues) {
         return IvyPatternHelper.substituteParams(string, attValues);
      }

      public String getName() {
         return this.name;
      }

      public void addConfiguredAttribute(Attribute att) {
         this.attributes.put(att.getName(), att);
      }

      public void addConfiguredElement(Element elt) {
         this.elements.put(elt.getName(), elt);
      }

      public Macro createMacro() {
         return new Macro(this);
      }

      public void addAttribute(String attName, String attDefaultValue) {
         Attribute att = new Attribute();
         att.setName(attName);
         att.setDefault(attDefaultValue);
         this.addConfiguredAttribute(att);
      }

      public void addElement(String elementName, boolean optional) {
         Element elt = new Element();
         elt.setName(elementName);
         elt.setOptional(optional);
         this.addConfiguredElement(elt);
      }

      public MacroRecord recordCreateChild(String name) {
         this.macroRecord = new MacroRecord(name);
         return this.macroRecord;
      }
   }

   private static class ObjectDescriptor {
      private Object obj;
      private String objName;
      private Map createMethods = new HashMap();
      private Map addMethods = new HashMap();
      private Map addConfiguredMethods = new HashMap();
      private Map setMethods = new HashMap();
      private Map typeAddMethods = new HashMap();
      private Map typeAddConfiguredMethods = new HashMap();

      public ObjectDescriptor(Object object, String objName) {
         this.obj = object;
         this.objName = objName;

         for(Method m : object.getClass().getMethods()) {
            if (m.getName().startsWith("create") && m.getParameterTypes().length == 0 && !Void.TYPE.equals(m.getReturnType())) {
               String name = StringUtils.uncapitalize(m.getName().substring("create".length()));
               if (name.length() != 0) {
                  this.addCreateMethod(name, m);
               }
            } else if (m.getName().startsWith("addConfigured") && m.getParameterTypes().length == 1 && Void.TYPE.equals(m.getReturnType())) {
               String name = StringUtils.uncapitalize(m.getName().substring("addConfigured".length()));
               if (name.length() == 0) {
                  this.addAddConfiguredMethod(m);
               }

               this.addAddConfiguredMethod(name, m);
            } else if (m.getName().startsWith("add") && !m.getName().startsWith("addConfigured") && m.getParameterTypes().length == 1 && Void.TYPE.equals(m.getReturnType())) {
               String name = StringUtils.uncapitalize(m.getName().substring("add".length()));
               if (name.length() == 0) {
                  this.addAddMethod(m);
               }

               this.addAddMethod(name, m);
            } else if (m.getName().startsWith("set") && m.getParameterTypes().length == 1 && Void.TYPE.equals(m.getReturnType())) {
               String name = StringUtils.uncapitalize(m.getName().substring("set".length()));
               if (name.length() != 0) {
                  this.addSetMethod(name, m);
               }
            }
         }

      }

      public void addCreateMethod(String name, Method m) {
         this.createMethods.put(name, m);
      }

      public void addAddMethod(String name, Method m) {
         this.addMethods.put(name, m);
      }

      public void addAddConfiguredMethod(String name, Method m) {
         this.addConfiguredMethods.put(name, m);
      }

      private void addAddMethod(Method m) {
         this.typeAddMethods.put(m.getParameterTypes()[0], m);
      }

      private void addAddConfiguredMethod(Method m) {
         this.typeAddConfiguredMethods.put(m.getParameterTypes()[0], m);
      }

      public void addSetMethod(String name, Method m) {
         Method current = (Method)this.setMethods.get(name);
         if (current == null || current.getParameterTypes()[0] != String.class) {
            this.setMethods.put(name, m);
         }
      }

      public Object getObject() {
         return this.obj;
      }

      public Method getCreateMethod(String name) {
         return (Method)this.createMethods.get(name);
      }

      public Method getAddMethod(String name) {
         return (Method)this.addMethods.get(name);
      }

      public Method getAddConfiguredMethod(String name) {
         return (Method)this.addConfiguredMethods.get(name);
      }

      public Method getAddMethod(Class type) {
         return this.getTypeMatchingMethod(type, this.typeAddMethods);
      }

      public Method getAddConfiguredMethod(Class type) {
         return this.getTypeMatchingMethod(type, this.typeAddConfiguredMethods);
      }

      private Method getTypeMatchingMethod(Class type, Map typeMethods) {
         Method m = (Method)typeMethods.get(type);
         if (m != null) {
            return m;
         } else {
            for(Map.Entry method : typeMethods.entrySet()) {
               if (((Class)method.getKey()).isAssignableFrom(type)) {
                  return (Method)method.getValue();
               }
            }

            return null;
         }
      }

      public Method getSetMethod(String name) {
         return (Method)this.setMethods.get(name);
      }

      public String getObjectName() {
         return this.objName;
      }
   }
}
