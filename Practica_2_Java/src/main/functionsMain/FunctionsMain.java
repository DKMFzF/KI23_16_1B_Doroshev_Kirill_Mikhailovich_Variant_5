package main.functionsMain;

import classProd.Product;
import main.Main;
import errorPackage.EmptyLineException;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Random;

public class FunctionsMain {
  public static void subMenuCase13() {
    System.out.println("""
            Что вы хотите сделать?
            1) Сохранить
            2) Загрузить
            """);
  }

  public static void getInfoCase11() {
    System.out.println("""
            Применение SummaryStatistics
            """);
  }

  public static void getInfoCase10() {
    System.out.println("""
            Нахождение товара по артиклю и расчета для него НДС
            """);
  }

  public static void getInfoFilterObj() {
    System.out.println("""
            ---------------------------------------------------------------
            | Фильтрация будет происходить по заданной пользователем цене |
            | (Вам будут выведены товары ценой выше заданной)             |
            ---------------------------------------------------------------
            """);
  }

  // Меню
  public static void menu() {
    System.out.println("""
            Практика - 5
            -------------------------------------------------------------
            |                       __МЕНЮ__                            |
            | 1. Добовление пустого объекта                             |
            | 2. Добовление объекта (заполняется пользователём)         |
            | 3. Редактирование поля выбранного объекта                 |
            | 4. Вывод информации обо всех объектах + результат функции |
            | 5. Сортировка объектов по цене                            |
            -------------------------------------------------------------
            
            Практика - 6
            ------------------------------------------------------------------------------------
            |                               __МЕНЮ__                                           |
            | 6. Создание потока из массива/списка объектов и вывод их на экран.               |
            | 7. Фильтрация объектов по некоторому признаку                                    |
            | 8. Изъятие из массива/списка дубликатов.                                         |
            | 9. Демонстрация умения работать с операциями сведения с накоплением              |
            | 10. Демонстрация умения работать с типом Optional                                |
            | 11. Сгруппировать объекты по некоторому полю и вывести число элементов в группе. |
            | 12. Демонстрация умения работать с SummaryStatistics.                            |
            | 13. Загрузка и сохранение данных в файловой системе.                             |
            | 14. Завершение работы                                                            |
            ------------------------------------------------------------------------------------
            """);
  }


  // Метод что бы не повторять код
  public static void extractedError() {
    System.out.println("""
           
            -------------------------------
            |         !!ОШИБКА!!          |
            | Такого пункта не существует |
            -------------------------------""");
  }

  // Метод что бы не повторять код
  public static void extracted() {
    System.out.println("""
            
            -------------------------------
            |         !!ОШИБКА!!          |
            |  Сначала создайте объекты   |
            -------------------------------""");
  }

  /**
   * Метод для добовления нового названия бренда
   *
   * @param objListDATA массив объектов
   * @param point       номер элемента
   */
  public static void changeBrandAdd(ArrayList<Product> objListDATA, int point) {
    System.out.print("""
            
            Ввидите новое название бренда ->ㅤ""");

    var brand = Main.input.nextLine();
    try {
      if (brand.isEmpty()) throw new EmptyLineException("Вы ввели пустую строку");
    } catch (EmptyLineException e) {
      System.out.println("Введите заполненную строку");
      return;
    }

    objListDATA.get(point - 1).setBrand(brand);

    System.out.println("""
            
            --------------------------
            | Название было изменено |
            --------------------------""");

    getINFO(objListDATA, point);
  }

  /**
   * Метод для добовления нового названия для товара
   *
   * @param objListDATA массив объектов
   * @param point       номер элемента
   */
  public static void changeNameAdd(ArrayList<Product> objListDATA, int point) {
    System.out.print("""
            
            Ввидите новое название товара ->ㅤ""");

    var titleProd = Main.input.nextLine();
    try {
      if (titleProd.isEmpty()) throw new EmptyLineException("Вы ввели пустую строку");
    } catch (EmptyLineException e) {
      System.out.println("Введите заполненную строку");
      return;
    }

    objListDATA.get(point - 1).setTitleProd(titleProd);

    System.out.println("""
            
            --------------------------
            | Название было изменено |
            --------------------------""");

    getINFO(objListDATA, point);
  }

  /**
   * Метод для добовления новой цены
   *
   * @param objListDATA массив объектов
   * @param point       номер элемента
   */
  public static void changePriceAdd(ArrayList<Product> objListDATA, int point) {
    System.out.print("""
            
            Введите новую цену ->ㅤ""");

    double price;

    // Подавлние исключения
    try {
      price = Double.parseDouble(Main.input.nextLine());
    } catch (Exception e) {
      System.out.println("Введённое значение не подходит\nСработало подавление");
      return;
    }

    objListDATA.get(point - 1).setPrice(price);

    System.out.println("""
            
            ----------------------
            | Цена была изменена |
            ----------------------""");

    getINFO(objListDATA, point);
  }

  /**
   * Метод для добовления нового артикула
   *
   * @param objListDATA массив объектов
   * @param point       номер элемента
   */
  public static void changeArtAdd(ArrayList<Product> objListDATA, int point) {
    objListDATA.get(point - 1).setArticle(new Random().nextInt(100000000));

    System.out.println("""
            
            -----------------------
            | Артикул был изменён |
            -----------------------""");

    getINFO(objListDATA, point);
  }

  /**
   * Проверка числа для case3 переменной point
   *
   * @return addPrice_clone - проверенное вводимое пользователем значение
   */
  public static int checkPointNumb() {
    while (true) {

      var addPrice_clone = Main.input.nextLine();

      if (NumberUtils.isCreatable(addPrice_clone) && !addPrice_clone.matches(".*\\s.*")) {
        return Integer.parseInt(addPrice_clone);
      } else {
        System.out.print("""

                ------------------------
                |      !!ОШИБКА!!      |
                | Введёные вами данные |
                | не корректны         |
                ------------------------
                
                Введите снова ->ㅤ""");
      }
    }
  }

  public static double numberDoouble() {
    while (true) {

      var addNumberClone = Main.input.nextLine();

      if (NumberUtils.isCreatable(addNumberClone) && !addNumberClone.matches(".*\\s.*")) {
        return Double.parseDouble(addNumberClone);
      } else {
        System.out.print("""

                -------------------------
                |     !!ОШИБКА!!        |
                | Введённые вами данные |
                | не корректны          |
                -------------------------

                Введите снова ->ㅤ""");
      }
    }
  }

  /**
   * Метод для вывода информации объекта
   *
   * @param objListDATA массив с объектами
   * @param point       индекс элемента
   */
  public static void getINFO(ArrayList<Product> objListDATA, int point) {
    System.out.printf("""
            
            ------------------------
                    INFO
              1. Артикул: %d
              2. Название товара: %s
              3. Название бренда: %s
              4. Цена: %f
              5. Срок: %b
            ------------------------
            """, objListDATA.get(point - 1).getArticle(),
            objListDATA.get(point - 1).getTitleProd(),
            objListDATA.get(point - 1).getBrand(),
            objListDATA.get(point - 1).getPrice(),
            objListDATA.get(point - 1).isValue());

    // objListDATA.get(point - 1).getNDS();
  }

  public static int numberInt() {
    while (true) {

      var addNumberClone = Main.input.nextLine();

      if (NumberUtils.isCreatable(addNumberClone) && !addNumberClone.matches(".*\\s.*")) {
        return Integer.parseInt(addNumberClone);
      } else {
        System.out.print("""
                -------------------------
                |     !!ОШИБКА!!        |
                | Введённые вами данные |
                | не корректны          |
                -------------------------
                Введите снова ->ㅤ""");
      }
    }
  }

  public static String addLine() {
    while (true) {
      var addLineClone = Main.input.nextLine().trim();

      // Проверка на пустую строку
      if (!addLineClone.isEmpty()) {
        // Строка с заглавной буквы
        addLineClone = addLineClone.substring(0, 1).toUpperCase() + addLineClone.substring(1);
        return addLineClone;

      } else {
        System.out.print("""

                -------------------------
                |     !!ОШИБКА!!        |
                | Введённые вами данные |
                | не корректны          |
                -------------------------

                Введите снова ->ㅤ""");
      }
    }
  }

  /**
   * Метод для продолжения
   */
  public static void exitBlock() {
    System.out.println("""
            
            --НАЖМИТЕ ЛЮБУЮ КНОПКУ ДЛЯ ПРОДОЛЖЕНИЯ--""");
    Main.input.nextLine();
  }
}
