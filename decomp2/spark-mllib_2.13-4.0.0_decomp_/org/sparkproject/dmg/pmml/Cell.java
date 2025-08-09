package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.eclipse.persistence.oxm.annotations.XmlValueExtension;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlType(
   name = "",
   propOrder = {"value"}
)
@XmlTransient
public abstract class Cell extends PMMLObject {
   @XmlValue
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @XmlValueExtension
   private Object value = null;

   public Cell() {
   }

   @ValueConstructor
   public Cell(@Property("value") Object value) {
      this.value = value;
   }

   public abstract QName getName();

   public Object getValue() {
      return this.value;
   }

   public Cell setValue(@Property("value") Object value) {
      this.value = value;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
