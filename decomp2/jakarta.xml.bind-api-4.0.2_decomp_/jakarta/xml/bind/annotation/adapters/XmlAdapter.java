package jakarta.xml.bind.annotation.adapters;

public abstract class XmlAdapter {
   protected XmlAdapter() {
   }

   public abstract Object unmarshal(Object var1) throws Exception;

   public abstract Object marshal(Object var1) throws Exception;
}
