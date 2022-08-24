package fr.todd.zanniv.exception;

public class UsernameAlreadyExistsException  extends Exception {
    public String getMessage() {
        return "Nom d'utilisateur déjà pris";
    }

    @Override
    public String toString() {
        return "Nom d'utilisateur déjà pris";
    }
}
