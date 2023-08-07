package Enums;

import java.util.ArrayList;

public enum MovementType {
    WALKING, FLYING, SWIMMING, NONE;


    public static boolean hasMatchingTypes(MovementType[] arr1, MovementType[] arr2) {
        for (MovementType type : arr1) {
            for (MovementType value : arr2) {
                if (type == value) {
                    System.out.println("MovementTypes matching: " + type);
                    return true;
                }
            }
        }
        return false;
    }

}
