package main;

import main.functionsMain.FunctionsMain;
import classProd.Product;
import errorPackage.EmptyArrayException;
import errorPackage.EmptyFilterArrayException;
import errorPackage.ItemNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Вариант 5 - Товар <br>
 * !!РЕАЛИЗАЦИЯ ПРАКТИКИ 7 НАЧИНАЕТСЯ С case "6"!!
 * @author Дорошев Кирилл Михайлович
 */

public class Main {

  private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
  public static final Scanner input = new Scanner(System.in);

  public static void main(String[] args) {

    ArrayList<Product> objListDATA = new ArrayList<>();

    System.out.println("""
            ------------------------------------------
            |              __СПРАВКА__               |
            | Артикул товара создаётся автоматически |
            ------------------------------------------
            """);

    while (true) {
      FunctionsMain.menu();

      System.out.print("Выберете пункт -> ");
      var menu = input.nextLine().replaceAll("\\s", "");

      switch (menu) {
        // Практика 5
        // Блок добовления "по умолчания" объекта в массив
        case "1":
          objListDATA.add(new Product());
          System.out.println("""

                  -----------------
                  | Объект создан |
                  -----------------""");

          FunctionsMain.exitBlock(); // метод продолжения программы (сделан для удобства)
          break;

        // Блок создания объекта с помощью параметризированного конструктора
        case "2":
          System.out.println("""

                  ---------------------------------
                  | Введите значения для создания |
                  ---------------------------------
                  """);

          // Создание артикула
          var addArticle = new Random().nextInt(100000000);

          System.out.print("Введите название бренда товара -> ");
          String addBrand = input.nextLine(); // checkString();

          System.out.print("Введите название товара -> ");
          var addTitleProd = input.nextLine();

          System.out.print("Введите цену товара -> ");
          double addPrice = FunctionsMain.numberDoouble();

          // Рандомный блок для просрочки
          var isValue = new Random().nextBoolean();
          Product obj;
          if (isValue) {
            System.out.println("Вам повезло товар не просрочен!!");
            obj = new Product(addArticle, addPrice, addTitleProd, addBrand, false);
          } else {
            System.out.println("Вам не повезло товар просрочен!!");
            obj = new Product(addArticle, addPrice, addTitleProd, addBrand, true);
          }

          System.out.println(obj);
          System.out.println("""

                  -----------------
                  | Объект создан |
                  -----------------""");

          objListDATA.add(obj);
          FunctionsMain.exitBlock();
          break;

        // Блок редактирование объекта
        case "3":

          // Проверка на пустой массив
          try {
            if (objListDATA.isEmpty()) {
              throw new IndexOutOfBoundsException();
            }
          } catch (IndexOutOfBoundsException e) {
            LOGGER.warning("Массв пуст \n");
            FunctionsMain.extracted();
            FunctionsMain.exitBlock();
            break;
          }

          // Флаг выхода
          boolean exit = true;
          System.out.println("""

                  Выберете объект который хотите отредактировать:
                  """);
          for (int i = 0; i < objListDATA.size(); i++) {
            System.out.printf("%d) %s \n", i + 1, objListDATA.get(i).getTitleProd());
          }

          // Выбор элемента в массиве
          System.out.print("""
                 
                  Введите номер элемента, который хотите изменить ->ㅤ""");
          var point = FunctionsMain.checkPointNumb();

          if (point <= objListDATA.size()) {
            // Цикл второго меню для выбора изменений объекта
            while (exit) {
              System.out.println("""
                      
                      -------------------------------------------
                      | Выберете что хотите поменять в объекте: |
                      | 1) Артикул                              |
                      | 2) Цену                                 |
                      | 3) Название товара                      |
                      | 4) Бренд                                |
                      | 5) Просрочка                            |
                      -------------------------------------------
                      """);

              System.out.print("Выберете пункт -> ");
              var subMenu = input.nextLine().replaceAll("\\s", "");

              switch (subMenu) {
                case "1":
                  // Блок изменения артикула
                  FunctionsMain.changeArtAdd(objListDATA, point);
                  exit = false;

                  FunctionsMain.exitBlock();
                  break;

                case "2":
                  // Блок изменения цены
                  FunctionsMain.changePriceAdd(objListDATA, point);
                  exit = false;

                  FunctionsMain.exitBlock();
                  break;

                case "3":
                  // Блок изменения названия
                  FunctionsMain.changeNameAdd(objListDATA, point);
                  exit = false;

                  FunctionsMain.exitBlock();
                  break;

                case "4":
                  // Блок изменения бренда
                  FunctionsMain.changeBrandAdd(objListDATA, point);
                  exit = false;

                  // exitBlock();
                  break;
                case "5":
                  System.out.print("""
                          
                          true/false
                          Введите срок ->ㅤ""");
                  var value = input.nextLine();

                  if (Objects.equals(value, "true") || Objects.equals(value, "false")) {
                    objListDATA.get(point - 1).setValue(Boolean.parseBoolean(value));
                    System.out.println("""
                            
                            ----------------------
                            | Срок был изменён   |
                            ----------------------""");
                    FunctionsMain.getINFO(objListDATA, point);
                  } else {
                    System.out.println("Ошбка: вы не ввели true || false");
                  }

                  exit = false;
                  break;

                default:
                  FunctionsMain.extractedError();
              }
            }
          } else {
            System.out.println("""
                    
                    ---------------------------------------
                    |             !!ОШИБКА!!              |
                    |  Вы выбрали не существующий объект  |
                    ---------------------------------------""");
            FunctionsMain.exitBlock();
          }
          break;

        case "4":

          // Проверка на пустой массив
          try {
            if (objListDATA.isEmpty()) {
              throw new IndexOutOfBoundsException();
            }
          } catch (IndexOutOfBoundsException e) {
            LOGGER.warning("Массв пуст \n");
            FunctionsMain.extracted();
            FunctionsMain.exitBlock();
            break;
          }

          // Блок вывода всех объектов и значения их методов
          System.out.println(); // декор
          for (int i = 0; i < objListDATA.size(); i++) {
            System.out.println("---------------------ОБЪЕКТ " + (i + 1) + " --------------------------");
            objListDATA.get(i).info();
            System.out.println("Работа функции: ");
            objListDATA.get(i).getNDS();
          }
          System.out.println("--------------------------------------------------------");
          FunctionsMain.exitBlock();
          break;

        case "5":

          // Проверка на пустой массив
          try {
            if (objListDATA.isEmpty()) {
              throw new IndexOutOfBoundsException();
            }
          } catch (IndexOutOfBoundsException e) {
            LOGGER.warning("Массв пуст \n");
            FunctionsMain.extracted();
            FunctionsMain.exitBlock();
            break;
          }

          // Создание объекта Comparator класса для сортировки
          Comparator<Product> comparator = Comparator.comparing(Product::getPrice);
          assert comparator != null : "Компаратор не должен быть пустым";
          objListDATA.sort(comparator);
          System.out.println("""
                  
                  -----------------------
                  | Сортровка выполненна |
                  ------------------------""");

          FunctionsMain.exitBlock();
          break;

        // Практика 7
        case "6": // Создание из массива потока и вывод элементов на экран
          Stream<Product> streamArrPrint = objListDATA.stream();
          streamArrPrint.forEach(System.out::println);
          FunctionsMain.exitBlock();
          break;

        case "7": // Фильтрация объектов
          FunctionsMain.getInfoFilterObj();
          try {
            if (objListDATA.isEmpty()) throw new EmptyArrayException("Массив товаров пуст");
            System.out.print("Введите цену для фильтрации -> ");
            double sortPrice = Double.parseDouble(input.nextLine());
            List<Product> filteredProducts = objListDATA.stream()
                    .filter(product -> product.getPrice() >= sortPrice)
                    .toList();
            if (filteredProducts.isEmpty()) throw new EmptyFilterArrayException("Таких товаров нет");
            System.out.println("Вывод фильтрованных элементов:");
            filteredProducts.forEach(System.out::println);
          } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
          }
          FunctionsMain.exitBlock();
          break;

        case "8": // Изъятие из массива списка дубликатов
          try {
            if (objListDATA.isEmpty()) throw new EmptyArrayException("Массив товаров пуст");
            objListDATA = (ArrayList<Product>) objListDATA.stream()
                    .distinct()
                    .collect(Collectors.toList());
            System.out.println("Новая версия массива:");
            objListDATA.forEach(System.out::println);
          } catch (EmptyArrayException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
          }
          FunctionsMain.exitBlock();
          break;

        case "9": // Операция сведения и накопления (Сумма цен)
          try {
            if (objListDATA.isEmpty()) throw new EmptyArrayException("Массив товаров пуст");
            double totalProduct = objListDATA.stream()
                    .mapToDouble(Product::getPrice)
                    .sum();
            System.out.format("Сумма цен всех товаров -> %f", totalProduct);
          } catch (EmptyArrayException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
          }
          FunctionsMain.exitBlock();
          break;

        case "10": // Работа с Optional
          FunctionsMain.getInfoCase10();
          try {
            if (objListDATA.isEmpty()) throw new EmptyArrayException("Массив товаров пуст");
            System.out.print("Введите артикул товара -> ");
            int targetArticle = FunctionsMain.numberInt();
            Optional<Product> foundProduct = objListDATA.stream()
                    .filter(product -> product.getArticle() == targetArticle)
                    .findFirst();
            if (foundProduct.isEmpty()) throw new ItemNotFoundException("Элемент не найден");
            System.out.println("Товар найден!");
            Product product = foundProduct.get();
            product.getNDS();
          } catch (EmptyArrayException | ItemNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
          }
          FunctionsMain.exitBlock();
          break;

        case "11":
          try {
            if (objListDATA.isEmpty()) throw new EmptyArrayException("Массив товаров пуст");
            Map<Boolean, Long> groupedByValueCount = objListDATA.stream()
                    .collect(Collectors.groupingBy(Product::isValue, Collectors.counting()));
            groupedByValueCount.forEach((value, count) ->
                    System.out.println("Значение срока годности: " + value + ", Количество товаров: " + count));
          } catch (EmptyArrayException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
          }
          FunctionsMain.exitBlock();
          break;

        case "12": // SummaryStatistics
          try {
            if (objListDATA.isEmpty()) throw new EmptyArrayException("Массив товаров пуст");
            FunctionsMain.getInfoCase11();
            DoubleSummaryStatistics stats = objListDATA.stream()
                    .mapToDouble(Product::getPrice)
                    .summaryStatistics();
            System.out.format("""
                    Максимальная цена: %f
                    Минимальная цена: %f
                    Средняя цена: %f
                    Сумма цен: %f
                    """, stats.getMax(), stats.getMin(), stats.getAverage(), stats.getSum());
          } catch (EmptyArrayException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
          }
          FunctionsMain.exitBlock();
          break;

        case "13": // Сохранение и загрузка в файловой системе
          FunctionsMain.subMenuCase13();
          System.out.print("Ввидите: ");
          var subMenu = FunctionsMain.addLine();

          if (subMenu.equals("1")) { // Сохранение
            try (FileWriter writer =
                         new FileWriter("Practica_2_Java/data/products.txt", true)) {
              if (objListDATA.isEmpty()) throw new EmptyArrayException("Массив товаров пуст");
              for (Product product : objListDATA) {
                writer.write(product.getArticle()
                        + ";" + product.getPrice()
                        + ";" + product.getTitleProd()
                        + ";" + product.getBrand()
                        + ";" + product.isValue() + "\n");
              }
              System.out.println("Данные массива сохранены в файл");
            } catch (Exception e) {
              System.out.println(e.getMessage());
              System.out.println(Arrays.toString(e.getStackTrace()));
            }
            FunctionsMain.exitBlock();
            break;
          } else if (subMenu.equals("2")) { // Загрузка
            try (BufferedReader reader =
                         new BufferedReader(new FileReader("Practica_2_Java/data/products.txt"))) {
              String line;
              while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int article = Integer.parseInt(parts[0]);
                double price = Double.parseDouble(parts[1]);
                String titleProd = parts[2];
                String brand = parts[3];
                boolean value = Boolean.parseBoolean(parts[4]);
                objListDATA.add(new Product(article, price, titleProd, brand, value));
              }
            } catch (IOException e) {
              System.out.println(e.getMessage());
              System.out.println(Arrays.toString(e.getStackTrace()));
            }
            System.out.println("Загруженные товары в массив: ");
            objListDATA.forEach(System.out::println);
            System.out.println("Загрузка завершена");
            FunctionsMain.exitBlock();
            break;
          } else { // Неверный ввод
            FunctionsMain.exitBlock();
            break;
          }

        case "14":
          System.out.println("""
                  
                  --------------------------
                  | Завершение работы .... |
                  --------------------------
                  """);
          return;

        default:
          FunctionsMain.extractedError();
          FunctionsMain.exitBlock();
      }
    }
  }
}