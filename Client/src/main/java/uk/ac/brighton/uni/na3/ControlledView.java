package uk.ac.brighton.uni.na3;

public abstract class ControlledView {
    private ScreenController parent;

    public ScreenController getParent() {
        return parent;
    }

    public void setParent(ScreenController parent) {
        this.parent = parent;
    }
}