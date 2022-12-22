package principal;

public enum TipoEnum {
    AMIGO("A"),FAMILIAR("F"),ENEMIGO("E"),TRABAJO("T");
    
    private TipoEnum(String abreviatura){
        this.abreviatura=abreviatura;
    }
    
    private String abreviatura;
}
