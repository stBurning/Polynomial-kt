import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DoubleSpinner implements SpinnerModel {
    private Double defaultValue;
    private ChangeListener listener;
    private Double step;

    public DoubleSpinner(Double defaultValue, Double step) {
        this.defaultValue = defaultValue;
        this.step = step;
    }



    public Double getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Object getValue() {
        return getDefaultValue();
    }

    @Override
    public void setValue(Object value) {
        setDefaultValue((Double) value);
    }
    @Override
    public Object getNextValue(){
        defaultValue += step;
        listener.stateChanged(new ChangeEvent(this));
        return defaultValue;
    }

    @Override
    public Object getPreviousValue() {
        defaultValue -= step;
        listener.stateChanged(new ChangeEvent(this));
        return defaultValue;
    }

    @Override
    public void addChangeListener(ChangeListener l) {
        listener = l;
    }

    @Override
    public void removeChangeListener(ChangeListener l) {

    }
}
