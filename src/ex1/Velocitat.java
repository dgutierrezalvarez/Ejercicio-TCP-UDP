package ex1;


public class Velocitat {
    int velocitat, max;

    public Velocitat(int max) {
        this.max = max;
    }

    public int getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(int vel) {
        this.velocitat = vel;
    }

    public int simuladorVelocitat() {
        setVelocitat((int) (Math.random() * max) + 1);
        return getVelocitat();
    }
}

