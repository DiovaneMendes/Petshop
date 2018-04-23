package negocio;

//Tratando exceções aqui para não ter que botar campo por campo
public class NegocioException extends Exception{    
    
    //Mostrando apenas mensagem de erro
    public NegocioException(String message) {
        super(message);
    }
    
    //Mostrando mensagem e causa do erro 
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
    
    //Mostrando apenas causa do erro
    public NegocioException(Throwable cause) {
        super(cause);
    }
    
}