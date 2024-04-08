package cohorte16.homeservice.enums;

import lombok.Getter;

@Getter
public enum Profession {

    OTHERS("OTHERS"),
    PLUMBER("PLUMBER"),
    ELECTRICIAN("ELECTRICIAN"),
    CONSTRUCTION_WORKER("CONSTRUCTION_WORKER"),
    PAINTER("PAINTER"),
    GARDENER("GARDENER"),
    GASMAN("GASMAN"),
    TEACHER("TEACHER"),
    PROGRAMMER("PROGRAMMER"),
    DESIGNER("DESIGNER"),
    NANNY("NANNY");

    private final String description;

    Profession(String description) {
        this.description = description;
    }

    /*
    public static Profession fromString(String text) {
        for (Profession profession : Profession.values()) {
            if (profession.getDescription().equalsIgnoreCase(text)) {
                return profession;
            }
        }
        return null;
    }*/
}
