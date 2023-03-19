package PizzaEuGostum.ufpb.br;

public class AmigoInexistenteException extends Exception{
    public static final long serialVersion = 1L;
    public AmigoInexistenteException (String msg){
        super(msg);
    }
}
