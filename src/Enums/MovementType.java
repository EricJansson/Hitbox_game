package Enums;

import java.util.ArrayList;
import java.util.Arrays;

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

    public static boolean isMatching(MovementType[] arr1, MovementType[] arr2) {
        if (arr1.length != arr2.length) { return false; }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

}
