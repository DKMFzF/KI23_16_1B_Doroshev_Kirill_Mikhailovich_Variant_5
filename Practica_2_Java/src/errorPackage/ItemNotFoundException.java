package errorPackage;

public class ItemNotFoundException extends Exception {
  public ItemNotFoundException(String string) {
    super(string);
  }
}