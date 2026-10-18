package club.modelo.usuarios;

public abstract class Usuario {
	private String username;
    private String password;
    
    public Usuario(String username, String password) {
    	this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean autenticar(String user, String pass) {
        return username == user && password == pass;
    }
    
    public boolean cambiarPassword(String actual, String nueva) {
        if (!password.equals(actual) || nueva == null || nueva.isBlank()) {
            return false;
        }
        this.password = nueva;
        return true;
    }	

}
