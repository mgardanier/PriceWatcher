package Model;

/**
 * Created by michael.gardanier on 6/9/17.
 */
public class Parameters {

    public enum ElementType{
        P, SPAN, OTHER
    }

    public enum Selector{
        CLASS, ID, ITEMP_PROP, OTHER
    }

    private String value;
    private String selectorValue;
    private Selector selector;
    private ElementType elementType;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public String getSelectorValue() {
        return selectorValue;
    }

    public void setSelectorValue(String selectorValue) {
        this.selectorValue = selectorValue;
    }
}
