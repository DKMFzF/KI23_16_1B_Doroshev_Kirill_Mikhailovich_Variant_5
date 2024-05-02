package exception;

/**
 * Класс для проверки на отрицательное число
 */
public class NegativeNumberException extends Exception {
  public NegativeNumberException(String string) {
    super(string);
  }
}
