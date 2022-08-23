package fr.todd.zanniv.exception;

public class UserNotFoundException extends RuntimeException {
    public String getMessage() {
        return "User non trouvé ¯\\_(ツ)_/¯";
    }

    @Override
    public String toString() {
        return "User non trouvé ¯\\_(ツ)_/¯";
    }
}
