package fr.todd.zanniv.exception;

public class BirthdayNotFoundException extends RuntimeException {
    public String getMessage() {
        return "Birthday non trouvé ¯\\_(ツ)_/¯";
    }

    @Override
    public String toString() {
        return "Birthday non trouvé ¯\\_(ツ)_/¯";
    }
}
