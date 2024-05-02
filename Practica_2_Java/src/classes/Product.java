package classes;

import exception.EmptyLineException;
import exception.NegativeNumberException;

import java.util.Objects;
import java.util.Scanner;

/**
 * Вариант 5 - Товар
 *
 * @author Дорошев Кирилл Михайлович
 */

// Класс Товар

public class Product {

  private int article; // Артикль
  private double price; // Цена
  private String titleProd; // Название товара
  private String brand; // Бренд товара
  private boolean value; // переменная срока годности
  private static final Scanner input = new Scanner(System.in);

  public Product() {
    this.value = false;
    this.article = 0;
    this.brand = "None";
    this.price = 0;
    this.titleProd = "None";
  }

  public Product(int article, double price, String titleProd, String brand, boolean value) {
    setArticle(article);
    setPrice(price);
    setTitleProd(titleProd);
    setBrand(brand);
    setValue(value);
  }

  public void info() {
    System.out.printf("""

            ------------------------
                    INFO
              1. Артикул: %d
              2. Название товара: %s
              3. Название бренда: %s
              4. Цена: %f
              5. Срок: %b
            ------------------------
            """, this.article, this.titleProd, this.brand, this.price, this.value);
  }

  // Расчет НДС для товара
  public void getNDS() {
    System.out.printf("""
            НДС для товара -> %f
            Сумма товара с учетом НДС -> %f \n
            """, (this.price * 20) / 100, this.price + (this.price * 20) / 100);
  }

  public int getArticle() {
    return article;
  }

  public void setArticle(int article) {
    this.article = article;
  }

  public double getPrice() {
    return price;
  }

  // Сеттер Цены с проверкой входимых аргументов
  public void setPrice(double price) {
    try {
      if (price < 0) {
        throw new NegativeNumberException("Вы ввели число которое равно или меньше нуля");
      }
      this.price = price;
    } catch (NegativeNumberException e) {
      System.out.println(e.getMessage());
      extractMethod();
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage());
      System.out.println("Вы не ввели число");
      extractMethod();
    }
  }

  public String getTitleProd() {
    return titleProd;
  }

  // Сеттер названия продукта с проверкой входимых аргументов
  public void setTitleProd(String titleProd) {
    try {
      if (titleProd.isEmpty()) throw new EmptyLineException("Вы ввели пустую строку");
    } catch (EmptyLineException e) {
      System.out.println("Введите заполненную строку");
      extractedString();
    }
  }

  public String getBrand() {
    return brand;
  }

  // Сеттер названия фирмы с проверкой входимых аргументов
  public void setBrand(String brand) {
    try {
      if (brand.isEmpty()) throw new EmptyLineException("Вы ввели пустую строку");
      this.brand = brand;
    } catch (EmptyLineException e) {
      System.out.println("Введите заполненную строку");
      extractedString();
    }
  }

  public boolean isValue() {
    return value;
  }

  public void setValue(boolean value) {
    this.value = value;
  }

  // Метод для проверки строки
  private void extractedString() {
    while (true) {
      var addBrand = input.nextLine();
      try {
        if (addBrand.isEmpty()) throw new EmptyLineException("Вы ввели пустую строку");
        this.brand = addBrand;
        return;
      } catch (EmptyLineException j) {
        System.out.println("Введите заполненную строку");
      }
    }
  }

  // Метод проверки числа
  private void extractMethod() {
    while (true) {
      System.out.print("Введите повторно -> ");
      try {
        var addPrice = Double.parseDouble(input.nextLine());
        if (addPrice <= 0) throw new NegativeNumberException("Вы ввели число которое равно или меньше нуля");
        this.price = addPrice;
        return;

      } catch (NegativeNumberException j) {
        System.out.println(j.getMessage());

      } catch (NumberFormatException j) {
        System.out.println(j.getMessage());
        System.out.println("Введите число");
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return article == product.article && Double.compare(price, product.price) == 0
            && value == product.value && Objects.equals(titleProd, product.titleProd)
            && Objects.equals(brand, product.brand);
  }

  @Override
  public int hashCode() {
    return Objects.hash(article, price, titleProd, brand, value);
  }

  @Override
  public String toString() {
    return String.format("""

            __Product__
            Артикль: %d
            Цена: %f
            Название товара: %s
            Бренд товара: %s
            переменная срока годности: %b
            """, article, price, titleProd, brand, value);
  }
}
