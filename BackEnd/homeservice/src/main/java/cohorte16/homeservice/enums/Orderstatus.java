package cohorte16.homeservice.enums;

public enum Orderstatus {
    Inicial("Inicial"),
    Pendiente("Pendiente"),
    Aprobada("Aprobada"),
    Finalizada("Finalizada");

    private final String descripcion;

    Orderstatus(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescription() {
        return descripcion;
    }

    public static Orderstatus fromString(String text) {
        for (Orderstatus status : Orderstatus.values()) {
            if (status.descripcion.equalsIgnoreCase(text)) {
                return status;
            }
        }
        Orderstatus or = null;
        return or;
    }

}
