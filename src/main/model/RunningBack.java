package model;
 //NOTE: this is not yet needed, for future implementation
// This subclass of Player represents a running back

public class RunningBack extends Player {

    //EFFECTS: super(player) and set position to "Running Back"
    public RunningBack(String name, int num) {
        super(name, num);
        position = "Running Back";
    }
}
