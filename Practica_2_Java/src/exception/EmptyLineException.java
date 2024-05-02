package exception;

/**
 * Класс для проверки на пустую строку
 */
public class EmptyLineException extends Exception {
  public EmptyLineException(String string) {
    super(string);
  }
}