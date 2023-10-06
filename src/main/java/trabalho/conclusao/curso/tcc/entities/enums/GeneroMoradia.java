package trabalho.conclusao.curso.tcc.entities.enums;

public enum GeneroMoradia {

    MASCULINA(1),
    FEMININA(2),
    MISTA(3);

    private int code;

    GeneroMoradia(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static GeneroMoradia valueOf(int code) {
        for (GeneroMoradia value : GeneroMoradia.values()) {
            //verificar se o valor tem na classe enum
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Codigo de Genero Moradia invalido");
    }
}
