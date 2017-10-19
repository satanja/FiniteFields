package Values;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;

/**
 * Created by s152124 on 10-10-2017.
 */
public class FiniteField {



    private ZmodP F;
    private Polynomial f;
    private Polynomial element; //element is in F/(f(X)), element may be null for addition and multiplication tables

    public FiniteField(Polynomial g, ZmodP field, Polynomial a) {
        f = g;
        F = field;
        element = a;
    }

    public int getOrder() {

        int order = F.getP();

        for (int i = 0; i < f.getDegree(); i++) {
            order *= order;
        }


        return order;
    }

    public int getClassCount() {
        return getOrder() / F.getP();
    }


    public Polynomial getElement() {
        return element;
    }


    public FiniteField add(FiniteField b) {

        Polynomial g = element.add(b.getElement());

        if (g.getDegree() >= f.getDegree()) {
            g.mod(f);
        }

        return new FiniteField(f, F, g);


    }

    public FiniteField multiply(FiniteField b) {

        Polynomial g = element.multiply(b.getElement());

        if (g.getDegree() >= f.getDegree()) {
            g.mod(f);
        }

        return new FiniteField(f, F, g);

    }

    public FiniteField qoutient(FiniteField b) {


        return null;

    }






}
