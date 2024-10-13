public class Fork {
    private boolean inUse;

    public boolean forkCondition() {
        return inUse;
    }

    public void setForkCondition(boolean inUse) {
        this.inUse = inUse;
    }
}
