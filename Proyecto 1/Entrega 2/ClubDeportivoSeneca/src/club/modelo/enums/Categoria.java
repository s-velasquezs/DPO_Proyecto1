package club.modelo.enums;

public enum Categoria{
	SUB13(0, 13),
    SUB15(14, 15),
    SUB17(16, 17),
    MAYORES(18, 120);

    private final int edadMin;
    private final int edadMax;
    
	private Categoria(int edadMin, int edadMax) {
		this.edadMin = edadMin;
		this.edadMax = edadMax;
	}
	
	public boolean corresponde(int edad) {
        return edad >= edadMin && edad <= edadMax;
    }
}