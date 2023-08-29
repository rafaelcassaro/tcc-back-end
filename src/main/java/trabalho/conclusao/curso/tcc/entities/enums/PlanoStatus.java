package trabalho.conclusao.curso.tcc.entities.enums;

public enum PlanoStatus {

    FINALIZADO(1),
    ATIVO(2)
    ;

    private int code;

    PlanoStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PlanoStatus valueOf(int code) {
        for (PlanoStatus value : PlanoStatus.values()) {
            //verificar se o valor tem na classe enum
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid PlanoStatus code");
    }
}
